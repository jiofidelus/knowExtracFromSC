package org.imogene.epicam.client.ui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.event.create.CreateHorsUsageEvent;
import org.imogene.epicam.client.event.list.ListHorsUsageEvent;
import org.imogene.epicam.client.event.view.ViewHorsUsageEvent;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.filter.HorsUsageFilterPanel;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.HorsUsageProxy;
import org.imogene.epicam.shared.proxy.LocalizedTextProxy;
import org.imogene.epicam.shared.request.HorsUsageRequest;
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
 * Composite that displays the list of HorsUsage entries
 * @author MEDES-IMPS
 */
public class HorsUsageDynaTable extends ImogDynaTable<HorsUsageProxy> {

	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private PushButton deleteButton;

	public HorsUsageDynaTable(EpicamRequestFactory requestFactory,
			ImogBeanDataProvider<HorsUsageProxy> provider,
			boolean checkBoxesVisible) {
		super(requestFactory, provider, checkBoxesVisible);
	}

	public ImogFilterPanel getFilterPanel() {
		ImogFilterPanel filterPanel = new HorsUsageFilterPanel();
		super.configureFilterPanel(filterPanel);
		return filterPanel;
	}

	/**
	 * 
	 */
	@Override
	protected void setColumns() {

		if (AccessManager.canReadHorsUsageDescription()) {
			Column<HorsUsageProxy, String> lotColumn = new LotColumn();
			lotColumn.setSortable(true);
			table.addColumn(lotColumn, NLS.constants().horsUsage_field_s_lot());
		}
		if (AccessManager.canReadHorsUsageDescription()) {
			Column<HorsUsageProxy, String> typeColumn = new TypeColumn();
			typeColumn.setSortable(true);
			table.addColumn(typeColumn, NLS.constants()
					.horsUsage_field_s_type());
		}

	}

	@Override
	protected GwtEvent<?> getViewEvent(HorsUsageProxy value) {
		History.newItem(TokenHelper.TK_VIEW + "/horsusage/" + value.getId(),
				true);
		return null;
	}

	@Override
	protected String getDefaultSortProperty() {
		return "modified";
	}

	@Override
	protected boolean getDefaultSortPropertyOrder() {
		return false;
	}

	/**
	 * Creates the Create action command for the entity
	 * @return the create command
	 */
	public Command getCreateCommand() {

		if (AccessManager.canCreateHorsUsage()
				&& AccessManager.canEditHorsUsage()) {
			Command command = new Command() {
				public void execute() {
					History.newItem(TokenHelper.TK_NEW + "/horsusage/", true);
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

		if (AccessManager.canDeleteHorsUsage()
				&& AccessManager.canEditHorsUsage()) {
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

		if (AccessManager.canDeleteHorsUsage()
				&& AccessManager.canEditHorsUsage()) {

			// Click handler
			registrations.add(deleteButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {

					Set<HorsUsageProxy> selectedEntities = selectionModel
							.getSelectedSet();

					int count = selectedEntities.size();
					if (count > 0) {

						EpicamRenderer renderer = EpicamRenderer.get();

						StringBuffer msg = new StringBuffer();
						msg.append(BaseNLS.constants()
								.confirmation_delete_several1()
								+ " "
								+ NLS.constants().horsUsage_name()
								+ " "
								+ BaseNLS.constants()
										.confirmation_delete_several2() + ": ");
						int i = 0;
						for (HorsUsageProxy entity : selectedEntities) {
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

							Request<Void> deleteRequest = getHorsUsageRequest()
									.delete(selectedEntities);
							deleteRequest.fire(new Receiver<Void>() {
								@Override
								public void onSuccess(Void response) {
									//Window.alert("The selected HorsUsage entries have been deleted");
									requestFactory.getEventBus().fireEvent(
											new ListHorsUsageEvent());
								}

								@Override
								public void onFailure(ServerFailure error) {
									Window.alert("Error deleting the HorsUsage entries");
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
	 * Creates the action command that enables to export the HorsUsage
	 * entries in a csv file
	 * @return the command
	 */
	public Command getCsvExportButton() {

		if (AccessManager.canExportHorsUsage()) {

			Command command = new Command() {
				public void execute() {

					String url = GWT.getHostPageBaseURL()
							+ EpicamBirtConstants.HO_US_CSV_KEY + "?"
							+ EpicamBirtConstants.REPORT_NAME
							+ "=horsUsage_csv" + "&"
							+ EpicamBirtConstants.REPORT_LOCALE + "="
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

	private HorsUsageRequest getHorsUsageRequest() {
		EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory;
		return epicamRequestFactory.horsUsageRequest();
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
	 * Column for field Lot
	 * @author MEDES-IMPS
	 */
	private class LotColumn extends ImogColumn<HorsUsageProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public LotColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(HorsUsageProxy object) {
			String value = null;
			if (object != null) {
				if (object.getLot() == null)
					value = "";
				else
					value = renderer.getDisplayValue(object.getLot());
			}
			return value;
		}

		public String getPropertyName() {
			return "lot";
		}
	}

	/**
	 * Column for field Type
	 * @author MEDES-IMPS
	 */
	private class TypeColumn extends ImogColumn<HorsUsageProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public TypeColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(HorsUsageProxy object) {
			String value = null;
			if (object != null) {
				if (object.getType() == null)
					value = "";
				else
					value = renderer.getEnumDisplayValue(HorsUsageProxy.class,
							"type", object.getType());
			}
			return value;
		}

		public String getPropertyName() {
			return "type";
		}
	}

}
