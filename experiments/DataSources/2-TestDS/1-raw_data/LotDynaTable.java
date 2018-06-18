package org.imogene.epicam.client.ui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.event.create.CreateLotEvent;
import org.imogene.epicam.client.event.list.ListLotEvent;
import org.imogene.epicam.client.event.view.ViewLotEvent;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.filter.LotFilterPanel;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.LotProxy;
import org.imogene.epicam.shared.proxy.LocalizedTextProxy;
import org.imogene.epicam.shared.request.LotRequest;
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
 * Composite that displays the list of Lot entries
 * @author MEDES-IMPS
 */
public class LotDynaTable extends ImogDynaTable<LotProxy> {

	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private PushButton deleteButton;

	public LotDynaTable(EpicamRequestFactory requestFactory,
			ImogBeanDataProvider<LotProxy> provider, boolean checkBoxesVisible) {
		super(requestFactory, provider, checkBoxesVisible);
	}

	public ImogFilterPanel getFilterPanel() {
		ImogFilterPanel filterPanel = new LotFilterPanel();
		super.configureFilterPanel(filterPanel);
		return filterPanel;
	}

	/**
	 * 
	 */
	@Override
	protected void setColumns() {

		if (AccessManager.canReadLotDescription()) {
			Column<LotProxy, String> cDTColumn = new CDTColumn();
			cDTColumn.setSortable(true);
			table.addColumn(cDTColumn, NLS.constants().lot_field_s_cDT());
		}
		if (AccessManager.canReadLotDescription()) {
			Column<LotProxy, String> numeroColumn = new NumeroColumn();
			numeroColumn.setSortable(true);
			table.addColumn(numeroColumn, NLS.constants().lot_field_s_numero());
		}
		if (AccessManager.canReadLotDescription()) {
			Column<LotProxy, String> quantiteColumn = new QuantiteColumn();
			quantiteColumn.setSortable(true);
			table.addColumn(quantiteColumn, NLS.constants()
					.lot_field_s_quantite());
		}
		if (AccessManager.canReadLotDescription()) {
			Column<LotProxy, String> datePeremptionColumn = new DatePeremptionColumn();
			datePeremptionColumn.setSortable(true);
			table.addColumn(datePeremptionColumn, NLS.constants()
					.lot_field_s_datePeremption());
		}
		if (AccessManager.canReadLotDescription()) {
			Column<LotProxy, String> intrantColumn = new IntrantColumn();
			intrantColumn.setSortable(true);
			table.addColumn(intrantColumn, NLS.constants()
					.lot_field_s_intrant());
		}
		if (AccessManager.canReadLotDescription()) {
			Column<LotProxy, String> medicamentColumn = new MedicamentColumn();
			medicamentColumn.setSortable(true);
			table.addColumn(medicamentColumn, NLS.constants()
					.lot_field_s_medicament());
		}

	}

	@Override
	protected GwtEvent<?> getViewEvent(LotProxy value) {
		History.newItem(TokenHelper.TK_VIEW + "/lot/" + value.getId(), true);
		return null;
	}

	@Override
	protected String getDefaultSortProperty() {
		return "datePeremption";
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

		if (AccessManager.canCreateLot() && AccessManager.canEditLot()) {
			Command command = new Command() {
				public void execute() {
					History.newItem(TokenHelper.TK_NEW + "/lot/", true);
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

		if (AccessManager.canDeleteLot() && AccessManager.canEditLot()) {
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

		if (AccessManager.canDeleteLot() && AccessManager.canEditLot()) {

			// Click handler
			registrations.add(deleteButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {

					Set<LotProxy> selectedEntities = selectionModel
							.getSelectedSet();

					int count = selectedEntities.size();
					if (count > 0) {

						EpicamRenderer renderer = EpicamRenderer.get();

						StringBuffer msg = new StringBuffer();
						msg.append(BaseNLS.constants()
								.confirmation_delete_several1()
								+ " "
								+ NLS.constants().lot_name()
								+ " "
								+ BaseNLS.constants()
										.confirmation_delete_several2() + ": ");
						int i = 0;
						for (LotProxy entity : selectedEntities) {
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

							Request<Void> deleteRequest = getLotRequest()
									.delete(selectedEntities);
							deleteRequest.fire(new Receiver<Void>() {
								@Override
								public void onSuccess(Void response) {
									//Window.alert("The selected Lot entries have been deleted");
									requestFactory.getEventBus().fireEvent(
											new ListLotEvent());
								}

								@Override
								public void onFailure(ServerFailure error) {
									Window.alert("Error deleting the Lot entries");
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
	 * Creates the action command that enables to export the Lot
	 * entries in a csv file
	 * @return the command
	 */
	public Command getCsvExportButton() {

		if (AccessManager.canExportLot()) {

			Command command = new Command() {
				public void execute() {

					String url = GWT.getHostPageBaseURL()
							+ EpicamBirtConstants.LOT_CSV_KEY + "?"
							+ EpicamBirtConstants.REPORT_NAME + "=lot_csv"
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

	private LotRequest getLotRequest() {
		EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory;
		return epicamRequestFactory.lotRequest();
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
	private class CDTColumn extends ImogColumn<LotProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public CDTColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(LotProxy object) {
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
	 * Column for field Numero
	 * @author MEDES-IMPS
	 */
	private class NumeroColumn extends ImogColumn<LotProxy, String> {

		public NumeroColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(LotProxy object) {
			String value = null;
			if (object != null) {
				if (object.getNumero() == null)
					value = "";
				else
					value = object.getNumero();
			}
			return value;
		}

		public String getPropertyName() {
			return "numero";
		}
	}

	/**
	 * Column for field Quantite
	 * @author MEDES-IMPS
	 */
	private class QuantiteColumn extends ImogColumn<LotProxy, String> {

		public QuantiteColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(LotProxy object) {
			String value = null;
			if (object != null) {
				if (object.getQuantite() == null)
					value = "";
				else
					value = object.getQuantite().toString();
			}
			return value;
		}

		public String getPropertyName() {
			return "quantite";
		}
	}

	/**
	 * Column for field DatePeremption
	 * @author MEDES-IMPS
	 */
	private class DatePeremptionColumn extends ImogColumn<LotProxy, String> {

		public DatePeremptionColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(LotProxy object) {
			String value = null;
			if (object != null) {
				if (object.getDatePeremption() == null)
					value = "";
				else
					value = DateUtil
							.getFormatedDate(object.getDatePeremption());
			}
			return value;
		}

		public String getPropertyName() {
			return "datePeremption";
		}
	}

	/**
	 * Column for field Intrant
	 * @author MEDES-IMPS
	 */
	private class IntrantColumn extends ImogColumn<LotProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public IntrantColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(LotProxy object) {
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
	 * Column for field Medicament
	 * @author MEDES-IMPS
	 */
	private class MedicamentColumn extends ImogColumn<LotProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public MedicamentColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(LotProxy object) {
			String value = null;
			if (object != null) {
				if (object.getMedicament() == null)
					value = "";
				else
					value = renderer.getDisplayValue(object.getMedicament());
			}
			return value;
		}

		public String getPropertyName() {
			return "medicament";
		}
	}

}
