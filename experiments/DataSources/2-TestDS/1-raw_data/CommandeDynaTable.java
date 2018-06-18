package org.imogene.epicam.client.ui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.event.create.CreateCommandeEvent;
import org.imogene.epicam.client.event.list.ListCommandeEvent;
import org.imogene.epicam.client.event.view.ViewCommandeEvent;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.filter.CommandeFilterPanel;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.CommandeProxy;
import org.imogene.epicam.shared.proxy.LocalizedTextProxy;
import org.imogene.epicam.shared.request.CommandeRequest;
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
 * Composite that displays the list of Commande entries
 * @author MEDES-IMPS
 */
public class CommandeDynaTable extends ImogDynaTable<CommandeProxy> {

	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private PushButton deleteButton;

	public CommandeDynaTable(EpicamRequestFactory requestFactory,
			ImogBeanDataProvider<CommandeProxy> provider,
			boolean checkBoxesVisible) {
		super(requestFactory, provider, checkBoxesVisible);
	}

	public ImogFilterPanel getFilterPanel() {
		ImogFilterPanel filterPanel = new CommandeFilterPanel();
		super.configureFilterPanel(filterPanel);
		return filterPanel;
	}

	/**
	 * 
	 */
	@Override
	protected void setColumns() {

		if (AccessManager.canReadCommandeInformationsDepart()) {
			Column<CommandeProxy, String> cDTColumn = new CDTColumn();
			cDTColumn.setSortable(true);
			table.addColumn(cDTColumn, NLS.constants().commande_field_s_cDT());
		}
		if (AccessManager.canReadCommandeInformationsDepart()) {
			Column<CommandeProxy, String> dateColumn = new DateColumn();
			dateColumn.setSortable(true);
			table.addColumn(dateColumn, NLS.constants().commande_field_s_date());
		}

	}

	@Override
	protected GwtEvent<?> getViewEvent(CommandeProxy value) {
		History.newItem(TokenHelper.TK_VIEW + "/commande/" + value.getId(),
				true);
		return null;
	}

	@Override
	protected String getDefaultSortProperty() {
		return "date";
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

		if (AccessManager.canCreateCommande()
				&& AccessManager.canEditCommande()) {
			Command command = new Command() {
				public void execute() {
					History.newItem(TokenHelper.TK_NEW + "/commande/", true);
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

		if (AccessManager.canDeleteCommande()
				&& AccessManager.canEditCommande()) {
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

		if (AccessManager.canDeleteCommande()
				&& AccessManager.canEditCommande()) {

			// Click handler
			registrations.add(deleteButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {

					Set<CommandeProxy> selectedEntities = selectionModel
							.getSelectedSet();

					int count = selectedEntities.size();
					if (count > 0) {

						EpicamRenderer renderer = EpicamRenderer.get();

						StringBuffer msg = new StringBuffer();
						msg.append(BaseNLS.constants()
								.confirmation_delete_several1()
								+ " "
								+ NLS.constants().commande_name()
								+ " "
								+ BaseNLS.constants()
										.confirmation_delete_several2() + ": ");
						int i = 0;
						for (CommandeProxy entity : selectedEntities) {
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

							Request<Void> deleteRequest = getCommandeRequest()
									.delete(selectedEntities);
							deleteRequest.fire(new Receiver<Void>() {
								@Override
								public void onSuccess(Void response) {
									//Window.alert("The selected Commande entries have been deleted");
									requestFactory.getEventBus().fireEvent(
											new ListCommandeEvent());
								}

								@Override
								public void onFailure(ServerFailure error) {
									Window.alert("Error deleting the Commande entries");
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
	 * Creates the action command that enables to export the Commande
	 * entries in a csv file
	 * @return the command
	 */
	public Command getCsvExportButton() {

		if (AccessManager.canExportCommande()) {

			Command command = new Command() {
				public void execute() {

					String url = GWT.getHostPageBaseURL()
							+ EpicamBirtConstants.CMD_CSV_KEY + "?"
							+ EpicamBirtConstants.REPORT_NAME + "=commande_csv"
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

	private CommandeRequest getCommandeRequest() {
		EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory;
		return epicamRequestFactory.commandeRequest();
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
	 * Column for field CDT
	 * @author MEDES-IMPS
	 */
	private class CDTColumn extends ImogColumn<CommandeProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public CDTColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(CommandeProxy object) {
			String value = null;
			if (object != null) {
				if (object.getCDT() == null)
					value = "";
				else
					value = renderer.getDisplayValue(object.getCDT());
			}
			return value;
		}

		public String getPropertyName() {
			return "CDT";
		}
	}

	/**
	 * Column for field Date
	 * @author MEDES-IMPS
	 */
	private class DateColumn extends ImogColumn<CommandeProxy, String> {

		public DateColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(CommandeProxy object) {
			String value = null;
			if (object != null) {
				if (object.getDate() == null)
					value = "";
				else
					value = DateUtil.getFormatedDate(object.getDate());
			}
			return value;
		}

		public String getPropertyName() {
			return "date";
		}
	}

}
