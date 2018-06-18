package org.imogene.epicam.client.ui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.event.create.CreatePriseMedicamenteuseEvent;
import org.imogene.epicam.client.event.list.ListPriseMedicamenteuseEvent;
import org.imogene.epicam.client.event.view.ViewPriseMedicamenteuseEvent;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.filter.PriseMedicamenteuseFilterPanel;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.PriseMedicamenteuseProxy;
import org.imogene.epicam.shared.proxy.LocalizedTextProxy;
import org.imogene.epicam.shared.request.PriseMedicamenteuseRequest;
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
 * Composite that displays the list of PriseMedicamenteuse entries
 * @author MEDES-IMPS
 */
public class PriseMedicamenteuseDynaTable
		extends
			ImogDynaTable<PriseMedicamenteuseProxy> {

	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private PushButton deleteButton;

	public PriseMedicamenteuseDynaTable(EpicamRequestFactory requestFactory,
			ImogBeanDataProvider<PriseMedicamenteuseProxy> provider,
			boolean checkBoxesVisible) {
		super(requestFactory, provider, checkBoxesVisible);
	}

	public ImogFilterPanel getFilterPanel() {
		ImogFilterPanel filterPanel = new PriseMedicamenteuseFilterPanel();
		super.configureFilterPanel(filterPanel);
		return filterPanel;
	}

	/**
	 * 
	 */
	@Override
	protected void setColumns() {

		if (AccessManager.canReadPriseMedicamenteuseDescription()) {
			Column<PriseMedicamenteuseProxy, String> dateEffectiveColumn = new DateEffectiveColumn();
			dateEffectiveColumn.setSortable(true);
			table.addColumn(dateEffectiveColumn, NLS.constants()
					.priseMedicamenteuse_field_s_dateEffective());
		}

	}

	@Override
	protected GwtEvent<?> getViewEvent(PriseMedicamenteuseProxy value) {
		History.newItem(
				TokenHelper.TK_VIEW + "/prisemedicamenteuse/" + value.getId(),
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

		if (AccessManager.canCreatePriseMedicamenteuse()
				&& AccessManager.canEditPriseMedicamenteuse()) {
			Command command = new Command() {
				public void execute() {
					History.newItem(TokenHelper.TK_NEW
							+ "/prisemedicamenteuse/", true);
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

		if (AccessManager.canDeletePriseMedicamenteuse()
				&& AccessManager.canEditPriseMedicamenteuse()) {
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

		if (AccessManager.canDeletePriseMedicamenteuse()
				&& AccessManager.canEditPriseMedicamenteuse()) {

			// Click handler
			registrations.add(deleteButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {

					Set<PriseMedicamenteuseProxy> selectedEntities = selectionModel
							.getSelectedSet();

					int count = selectedEntities.size();
					if (count > 0) {

						EpicamRenderer renderer = EpicamRenderer.get();

						StringBuffer msg = new StringBuffer();
						msg.append(BaseNLS.constants()
								.confirmation_delete_several1()
								+ " "
								+ NLS.constants().priseMedicamenteuse_name()
								+ " "
								+ BaseNLS.constants()
										.confirmation_delete_several2() + ": ");
						int i = 0;
						for (PriseMedicamenteuseProxy entity : selectedEntities) {
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

							Request<Void> deleteRequest = getPriseMedicamenteuseRequest()
									.delete(selectedEntities);
							deleteRequest.fire(new Receiver<Void>() {
								@Override
								public void onSuccess(Void response) {
									//Window.alert("The selected PriseMedicamenteuse entries have been deleted");
									requestFactory.getEventBus().fireEvent(
											new ListPriseMedicamenteuseEvent());
								}

								@Override
								public void onFailure(ServerFailure error) {
									Window.alert("Error deleting the PriseMedicamenteuse entries");
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
	 * Creates the action command that enables to export the PriseMedicamenteuse
	 * entries in a csv file
	 * @return the command
	 */
	public Command getCsvExportButton() {

		if (AccessManager.canExportPriseMedicamenteuse()) {

			Command command = new Command() {
				public void execute() {

					String url = GWT.getHostPageBaseURL()
							+ EpicamBirtConstants.PRIS_MED_INIT_CSV_KEY + "?"
							+ EpicamBirtConstants.REPORT_NAME
							+ "=priseMedicamenteuse_csv" + "&"
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

	private PriseMedicamenteuseRequest getPriseMedicamenteuseRequest() {
		EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory;
		return epicamRequestFactory.priseMedicamenteuseRequest();
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
	 * Column for field DateEffective
	 * @author MEDES-IMPS
	 */
	private class DateEffectiveColumn
			extends
				ImogColumn<PriseMedicamenteuseProxy, String> {

		public DateEffectiveColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(PriseMedicamenteuseProxy object) {
			String value = null;
			if (object != null) {
				if (object.getDateEffective() == null)
					value = "";
				else
					value = DateUtil.getFormatedDate(object.getDateEffective());
			}
			return value;
		}

		public String getPropertyName() {
			return "dateEffective";
		}
	}

}
