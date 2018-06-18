package org.imogene.epicam.client.ui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.event.create.CreateRegionEvent;
import org.imogene.epicam.client.event.list.ListRegionEvent;
import org.imogene.epicam.client.event.view.ViewRegionEvent;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.filter.RegionFilterPanel;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.RegionProxy;
import org.imogene.epicam.shared.proxy.LocalizedTextProxy;
import org.imogene.epicam.shared.request.RegionRequest;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.web.client.event.SelectionChangedInTableEvent;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.table.ImogBeanDataProvider;
import org.imogene.web.client.ui.table.ImogColumn;
import org.imogene.web.client.ui.table.ImogDynaTable;
import org.imogene.web.client.ui.table.filter.ImogFilterPanel;
import org.imogene.web.client.util.BooleanUtil;
import org.imogene.web.client.util.DateUtil;
import org.imogene.web.client.util.TokenHelper;
import org.imogene.epicam.shared.constants.EpicamBirtConstants;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PushButton;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * Composite that displays the list of Region entries
 * @author MEDES-IMPS
 */
public class RegionDynaTable extends ImogDynaTable<RegionProxy> {

	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private PushButton deleteButton;

	public RegionDynaTable(EpicamRequestFactory requestFactory,
			ImogBeanDataProvider<RegionProxy> provider,
			boolean checkBoxesVisible) {
		super(requestFactory, provider, checkBoxesVisible);
	}

	public ImogFilterPanel getFilterPanel() {
		ImogFilterPanel filterPanel = new RegionFilterPanel();
		super.configureFilterPanel(filterPanel);
		return filterPanel;
	}

	/**
	 * 
	 */
	@Override
	protected void setColumns() {

		if (AccessManager.canReadRegionDescription()) {
			Column<RegionProxy, String> codeColumn = new CodeColumn();
			codeColumn.setSortable(true);
			table.addColumn(codeColumn, NLS.constants().region_field_s_code());
		}
		if (AccessManager.canReadRegionDescription()) {
			Column<RegionProxy, String> nomColumn = new NomColumn();
			nomColumn.setSortable(true);
			table.addColumn(nomColumn, NLS.constants().region_field_s_nom());
		}

	}

	@Override
	protected GwtEvent<?> getViewEvent(RegionProxy value) {
		History.newItem(TokenHelper.TK_VIEW + "/region/" + value.getId(), true);
		return null;
	}

	@Override
	protected String getDefaultSortProperty() {
		return "nom";
	}

	@Override
	protected boolean getDefaultSortPropertyOrder() {
		return true;
	}

	/**
	 * Creates the Create action command for the entity
	 * @return the create command
	 */
	public Command getCreateCommand() {

		if (AccessManager.canCreateRegion() && AccessManager.canEditRegion()) {
			Command command = new Command() {
				public void execute() {
					History.newItem(TokenHelper.TK_NEW + "/region/", true);
				}
			};
			return command;
		} else
			return null;
	}

	/**
	 * Creates the Delete action command for the entity
	 * @return the delete command
	 */
	public PushButton getDeleteButton() {

		if (AccessManager.canDeleteRegion() && AccessManager.canEditRegion()) {
			deleteButton = new PushButton(BaseNLS.constants().button_delete());
			deleteButton.setStyleName(imogResources.imogStyle().imogButton());
			deleteButton.addStyleName("Dynatable-Button");
			deleteButton.setVisible(false);
			return deleteButton;
		}

		return null;
	}

	/**
	 * Creates the Handlers linked to the delete button
	 */
	private void setDeleteButtonHandlers() {

		if (AccessManager.canDeleteRegion() && AccessManager.canEditRegion()) {

			// Click handler
			registrations.add(deleteButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {

					Set<RegionProxy> selectedEntities = selectionModel
							.getSelectedSet();

					int count = selectedEntities.size();
					if (count > 0) {

						EpicamRenderer renderer = EpicamRenderer.get();

						StringBuffer msg = new StringBuffer();
						msg.append(BaseNLS.constants()
								.confirmation_delete_several1()
								+ " "
								+ NLS.constants().region_name()
								+ " "
								+ BaseNLS.constants()
										.confirmation_delete_several2() + ": ");
						int i = 0;
						for (RegionProxy entity : selectedEntities) {
							if (count == 1 || i == count - 1)
								msg.append("'"
										+ renderer.getDisplayValue(entity)
										+ "' ?");
							else
								msg.append("'"
										+ renderer.getDisplayValue(entity)
										+ "', ");
							i = i + 1;
						}

						boolean toDelete = Window.confirm(msg.toString());
						if (toDelete) {

							Request<Void> deleteRequest = getRegionRequest()
									.delete(selectedEntities);
							deleteRequest.fire(new Receiver<Void>() {
								@Override
								public void onSuccess(Void response) {
									//Window.alert("The selected Region entries have been deleted");
									requestFactory.getEventBus().fireEvent(
											new ListRegionEvent());
								}

								@Override
								public void onFailure(ServerFailure error) {
									Window.alert("Error deleting the Region entries");
									super.onFailure(error);
								}
							});
						}
					}

				}
			}));

			// Selection changed handler	
			registrations.add(requestFactory.getEventBus().addHandler(
					SelectionChangedInTableEvent.TYPE,
					new SelectionChangedInTableEvent.Handler() {
						@Override
						public void noticeSelectionChange(int selectedItems) {
							if (selectedItems > 0)
								deleteButton.setVisible(true);
							else
								deleteButton.setVisible(false);
						}
					}));
		}
	}

	/**
	 * Creates the action command that enables to export the Region
	 * entries in a csv file
	 * @return the command
	 */
	public Command getCsvExportButton() {

		if (AccessManager.canExportRegion()) {

			Command command = new Command() {
				public void execute() {

					String url = GWT.getHostPageBaseURL()
							+ EpicamBirtConstants.REG_CSV_KEY + "?"
							+ EpicamBirtConstants.REPORT_NAME + "=region_csv"
							+ "&" + EpicamBirtConstants.REPORT_LOCALE + "="
							+ NLS.constants().locale() + "&"
							+ EpicamBirtConstants.REPORT_FORMAT + "="
							+ EpicamBirtConstants.CSV;

					if (beanDataProvider.getSearchCriterions() != null)
						url = url + getDataProviderCriteria();

					Window.open(url, "_blank", "");
				}
			};
			return command;

		} else
			return null;
	}

	private RegionRequest getRegionRequest() {
		EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory;
		return epicamRequestFactory.regionRequest();
	}

	@Override
	protected void onUnload() {
		for (HandlerRegistration r : registrations)
			r.removeHandler();
		registrations.clear();
		super.onUnload();
	}

	@Override
	protected void onLoad() {
		setDeleteButtonHandlers();
		super.onLoad();
	}

	/**
	 * --------------------- * Internal classes * ----------------------
	 */

	/**
	 * Column for field Code
	 * @author MEDES-IMPS
	 */
	private class CodeColumn extends ImogColumn<RegionProxy, String> {

		public CodeColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(RegionProxy object) {
			String value = null;
			if (object != null) {
				if (object.getCode() == null)
					value = "";
				else
					value = object.getCode();
			}
			return value;
		}

		public String getPropertyName() {
			return "code";
		}
	}

	/**
	 * Column for field Nom
	 * @author MEDES-IMPS
	 */
	private class NomColumn extends ImogColumn<RegionProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public NomColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(RegionProxy object) {
			String value = null;
			if (object != null) {
				if (object.getNom() == null)
					value = "";
				else
					value = renderer.getLocalizedText(object.getNom());
			}
			return value;
		}

		public String getPropertyName() {
			String locale = NLS.constants().locale();
			if (locale.equals("fr"))
				return "nom.francais";
			if (locale.equals("en"))
				return "nom.english";
			return "nom";
		}
	}

}
