package org.imogene.epicam.client.ui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.event.create.CreateDetailCommandeIntrantEvent;
import org.imogene.epicam.client.event.list.ListDetailCommandeIntrantEvent;
import org.imogene.epicam.client.event.view.ViewDetailCommandeIntrantEvent;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.filter.DetailCommandeIntrantFilterPanel;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.DetailCommandeIntrantProxy;
import org.imogene.epicam.shared.proxy.LocalizedTextProxy;
import org.imogene.epicam.shared.request.DetailCommandeIntrantRequest;
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
 * Composite that displays the list of DetailCommandeIntrant entries
 * @author MEDES-IMPS
 */
public class DetailCommandeIntrantDynaTable
		extends
			ImogDynaTable<DetailCommandeIntrantProxy> {

	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private PushButton deleteButton;

	public DetailCommandeIntrantDynaTable(EpicamRequestFactory requestFactory,
			ImogBeanDataProvider<DetailCommandeIntrantProxy> provider,
			boolean checkBoxesVisible) {
		super(requestFactory, provider, checkBoxesVisible);
	}

	public ImogFilterPanel getFilterPanel() {
		ImogFilterPanel filterPanel = new DetailCommandeIntrantFilterPanel();
		super.configureFilterPanel(filterPanel);
		return filterPanel;
	}

	/**
	 * 
	 */
	@Override
	protected void setColumns() {

		if (AccessManager.canReadDetailCommandeIntrantDescription()) {
			Column<DetailCommandeIntrantProxy, String> intrantColumn = new IntrantColumn();
			intrantColumn.setSortable(true);
			table.addColumn(intrantColumn, NLS.constants()
					.detailCommandeIntrant_field_s_intrant());
		}
		if (AccessManager.canReadDetailCommandeIntrantDescription()) {
			Column<DetailCommandeIntrantProxy, String> quantiteRequiseColumn = new QuantiteRequiseColumn();
			quantiteRequiseColumn.setSortable(true);
			table.addColumn(quantiteRequiseColumn, NLS.constants()
					.detailCommandeIntrant_field_s_quantiteRequise());
		}
		if (AccessManager.canReadDetailCommandeIntrantDescription()) {
			Column<DetailCommandeIntrantProxy, String> quantiteEnStockColumn = new QuantiteEnStockColumn();
			quantiteEnStockColumn.setSortable(true);
			table.addColumn(quantiteEnStockColumn, NLS.constants()
					.detailCommandeIntrant_field_s_quantiteEnStock());
		}

	}

	@Override
	protected GwtEvent<?> getViewEvent(DetailCommandeIntrantProxy value) {
		History.newItem(
				TokenHelper.TK_VIEW + "/detailcommandeintrant/" + value.getId(),
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

		if (AccessManager.canCreateDetailCommandeIntrant()
				&& AccessManager.canEditDetailCommandeIntrant()) {
			Command command = new Command() {
				public void execute() {
					History.newItem(TokenHelper.TK_NEW
							+ "/detailcommandeintrant/", true);
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

		if (AccessManager.canDeleteDetailCommandeIntrant()
				&& AccessManager.canEditDetailCommandeIntrant()) {
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

		if (AccessManager.canDeleteDetailCommandeIntrant()
				&& AccessManager.canEditDetailCommandeIntrant()) {

			// Click handler
			registrations.add(deleteButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {

					Set<DetailCommandeIntrantProxy> selectedEntities = selectionModel
							.getSelectedSet();

					int count = selectedEntities.size();
					if (count > 0) {

						EpicamRenderer renderer = EpicamRenderer.get();

						StringBuffer msg = new StringBuffer();
						msg.append(BaseNLS.constants()
								.confirmation_delete_several1()
								+ " "
								+ NLS.constants().detailCommandeIntrant_name()
								+ " "
								+ BaseNLS.constants()
										.confirmation_delete_several2() + ": ");
						int i = 0;
						for (DetailCommandeIntrantProxy entity : selectedEntities) {
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

							Request<Void> deleteRequest = getDetailCommandeIntrantRequest()
									.delete(selectedEntities);
							deleteRequest.fire(new Receiver<Void>() {
								@Override
								public void onSuccess(Void response) {
									//Window.alert("The selected DetailCommandeIntrant entries have been deleted");
									requestFactory
											.getEventBus()
											.fireEvent(
													new ListDetailCommandeIntrantEvent());
								}

								@Override
								public void onFailure(ServerFailure error) {
									Window.alert("Error deleting the DetailCommandeIntrant entries");
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
	 * Creates the action command that enables to export the DetailCommandeIntrant
	 * entries in a csv file
	 * @return the command
	 */
	public Command getCsvExportButton() {

		if (AccessManager.canExportDetailCommandeIntrant()) {

			Command command = new Command() {
				public void execute() {

					String url = GWT.getHostPageBaseURL()
							+ EpicamBirtConstants.DET_CMD_INT_CSV_KEY + "?"
							+ EpicamBirtConstants.REPORT_NAME
							+ "=detailCommandeIntrant_csv" + "&"
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

	private DetailCommandeIntrantRequest getDetailCommandeIntrantRequest() {
		EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory;
		return epicamRequestFactory.detailCommandeIntrantRequest();
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
	 * Column for field Intrant
	 * @author MEDES-IMPS
	 */
	private class IntrantColumn
			extends
				ImogColumn<DetailCommandeIntrantProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public IntrantColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(DetailCommandeIntrantProxy object) {
			String value = null;
			if (object != null) {
				if (object.getIntrant() == null)
					value = "";
				else
					value = renderer.getDisplayValue(object.getIntrant());
			}
			return value;
		}

		public String getPropertyName() {
			return "intrant";
		}
	}

	/**
	 * Column for field QuantiteRequise
	 * @author MEDES-IMPS
	 */
	private class QuantiteRequiseColumn
			extends
				ImogColumn<DetailCommandeIntrantProxy, String> {

		public QuantiteRequiseColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(DetailCommandeIntrantProxy object) {
			String value = null;
			if (object != null) {
				if (object.getQuantiteRequise() == null)
					value = "";
				else
					value = object.getQuantiteRequise().toString();
			}
			return value;
		}

		public String getPropertyName() {
			return "quantiteRequise";
		}
	}

	/**
	 * Column for field QuantiteEnStock
	 * @author MEDES-IMPS
	 */
	private class QuantiteEnStockColumn
			extends
				ImogColumn<DetailCommandeIntrantProxy, String> {

		public QuantiteEnStockColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(DetailCommandeIntrantProxy object) {
			String value = null;
			if (object != null) {
				if (object.getQuantiteEnStock() == null)
					value = "";
				else
					value = object.getQuantiteEnStock().toString();
			}
			return value;
		}

		public String getPropertyName() {
			return "quantiteEnStock";
		}
	}

}
