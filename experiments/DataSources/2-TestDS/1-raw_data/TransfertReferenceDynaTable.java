package org.imogene.epicam.client.ui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.event.create.CreateTransfertReferenceEvent;
import org.imogene.epicam.client.event.list.ListTransfertReferenceEvent;
import org.imogene.epicam.client.event.view.ViewTransfertReferenceEvent;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.filter.TransfertReferenceFilterPanel;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.TransfertReferenceProxy;
import org.imogene.epicam.shared.proxy.LocalizedTextProxy;
import org.imogene.epicam.shared.request.TransfertReferenceRequest;
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
 * Composite that displays the list of TransfertReference entries
 * @author MEDES-IMPS
 */
public class TransfertReferenceDynaTable
		extends
			ImogDynaTable<TransfertReferenceProxy> {

	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private PushButton deleteButton;

	public TransfertReferenceDynaTable(EpicamRequestFactory requestFactory,
			ImogBeanDataProvider<TransfertReferenceProxy> provider,
			boolean checkBoxesVisible) {
		super(requestFactory, provider, checkBoxesVisible);
	}

	public ImogFilterPanel getFilterPanel() {
		ImogFilterPanel filterPanel = new TransfertReferenceFilterPanel();
		super.configureFilterPanel(filterPanel);
		return filterPanel;
	}

	/**
	 * 
	 */
	@Override
	protected void setColumns() {

		if (AccessManager.canReadTransfertReferenceInformationsDepart()) {
			Column<TransfertReferenceProxy, String> patientColumn = new PatientColumn();
			patientColumn.setSortable(true);
			table.addColumn(patientColumn, NLS.constants()
					.transfertReference_field_s_patient());
		}
		if (AccessManager.canReadTransfertReferenceInformationsDepart()) {
			Column<TransfertReferenceProxy, String> natureColumn = new NatureColumn();
			natureColumn.setSortable(true);
			table.addColumn(natureColumn, NLS.constants()
					.transfertReference_field_s_nature());
		}
		if (AccessManager.canReadTransfertReferenceInformationsDepart()) {
			Column<TransfertReferenceProxy, String> cDTDepartColumn = new CDTDepartColumn();
			cDTDepartColumn.setSortable(true);
			table.addColumn(cDTDepartColumn, NLS.constants()
					.transfertReference_field_s_cDTDepart());
		}
		if (AccessManager.canReadTransfertReferenceInformationsDepart()) {
			Column<TransfertReferenceProxy, String> dateDepartColumn = new DateDepartColumn();
			dateDepartColumn.setSortable(true);
			table.addColumn(dateDepartColumn, NLS.constants()
					.transfertReference_field_s_dateDepart());
		}
		if (AccessManager.canReadTransfertReferenceInformationArrivee()) {
			Column<TransfertReferenceProxy, String> cDTArriveeColumn = new CDTArriveeColumn();
			cDTArriveeColumn.setSortable(true);
			table.addColumn(cDTArriveeColumn, NLS.constants()
					.transfertReference_field_s_cDTArrivee());
		}
		if (AccessManager.canReadTransfertReferenceInformationArrivee()) {
			Column<TransfertReferenceProxy, String> dateArriveeColumn = new DateArriveeColumn();
			dateArriveeColumn.setSortable(true);
			table.addColumn(dateArriveeColumn, NLS.constants()
					.transfertReference_field_s_dateArrivee());
		}

	}

	@Override
	protected GwtEvent<?> getViewEvent(TransfertReferenceProxy value) {
		History.newItem(
				TokenHelper.TK_VIEW + "/transfertreference/" + value.getId(),
				true);
		return null;
	}

	@Override
	protected String getDefaultSortProperty() {
		return "dateDepart";
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

		if (AccessManager.canCreateTransfertReference()
				&& AccessManager.canEditTransfertReference()) {
			Command command = new Command() {
				public void execute() {
					History.newItem(
							TokenHelper.TK_NEW + "/transfertreference/", true);
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

		if (AccessManager.canDeleteTransfertReference()
				&& AccessManager.canEditTransfertReference()) {
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

		if (AccessManager.canDeleteTransfertReference()
				&& AccessManager.canEditTransfertReference()) {

			// Click handler
			registrations.add(deleteButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {

					Set<TransfertReferenceProxy> selectedEntities = selectionModel
							.getSelectedSet();

					int count = selectedEntities.size();
					if (count > 0) {

						EpicamRenderer renderer = EpicamRenderer.get();

						StringBuffer msg = new StringBuffer();
						msg.append(BaseNLS.constants()
								.confirmation_delete_several1()
								+ " "
								+ NLS.constants().transfertReference_name()
								+ " "
								+ BaseNLS.constants()
										.confirmation_delete_several2() + ": ");
						int i = 0;
						for (TransfertReferenceProxy entity : selectedEntities) {
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

							Request<Void> deleteRequest = getTransfertReferenceRequest()
									.delete(selectedEntities);
							deleteRequest.fire(new Receiver<Void>() {
								@Override
								public void onSuccess(Void response) {
									//Window.alert("The selected TransfertReference entries have been deleted");
									requestFactory.getEventBus().fireEvent(
											new ListTransfertReferenceEvent());
								}

								@Override
								public void onFailure(ServerFailure error) {
									Window.alert("Error deleting the TransfertReference entries");
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
	 * Creates the action command that enables to export the TransfertReference
	 * entries in a csv file
	 * @return the command
	 */
	public Command getCsvExportButton() {

		if (AccessManager.canExportTransfertReference()) {

			Command command = new Command() {
				public void execute() {

					String url = GWT.getHostPageBaseURL()
							+ EpicamBirtConstants.TRANS_REF_CSV_KEY + "?"
							+ EpicamBirtConstants.REPORT_NAME
							+ "=transfertReference_csv" + "&"
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

	private TransfertReferenceRequest getTransfertReferenceRequest() {
		EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory;
		return epicamRequestFactory.transfertReferenceRequest();
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
	 * Column for field Patient
	 * @author MEDES-IMPS
	 */
	private class PatientColumn
			extends
				ImogColumn<TransfertReferenceProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public PatientColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(TransfertReferenceProxy object) {
			String value = null;
			if (object != null) {
				if (object.getPatient() == null)
					value = "";
				else
					value = renderer.getDisplayValue(object.getPatient());
			}
			return value;
		}

		public String getPropertyName() {
			return "patient";
		}
	}

	/**
	 * Column for field Nature
	 * @author MEDES-IMPS
	 */
	private class NatureColumn
			extends
				ImogColumn<TransfertReferenceProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public NatureColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(TransfertReferenceProxy object) {
			String value = null;
			if (object != null) {
				if (object.getNature() == null)
					value = "";
				else
					value = renderer.getEnumDisplayValue(
							TransfertReferenceProxy.class, "nature",
							object.getNature());
			}
			return value;
		}

		public String getPropertyName() {
			return "nature";
		}
	}

	/**
	 * Column for field CDTDepart
	 * @author MEDES-IMPS
	 */
	private class CDTDepartColumn
			extends
				ImogColumn<TransfertReferenceProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public CDTDepartColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(TransfertReferenceProxy object) {
			String value = null;
			if (object != null) {
				if (object.getCDTDepart() == null)
					value = "";
				else
					value = renderer.getDisplayValue(object.getCDTDepart());
			}
			return value;
		}

		public String getPropertyName() {
			return "CDTDepart";
		}
	}

	/**
	 * Column for field DateDepart
	 * @author MEDES-IMPS
	 */
	private class DateDepartColumn
			extends
				ImogColumn<TransfertReferenceProxy, String> {

		public DateDepartColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(TransfertReferenceProxy object) {
			String value = null;
			if (object != null) {
				if (object.getDateDepart() == null)
					value = "";
				else
					value = DateUtil.getFormatedDate(object.getDateDepart());
			}
			return value;
		}

		public String getPropertyName() {
			return "dateDepart";
		}
	}

	/**
	 * Column for field CDTArrivee
	 * @author MEDES-IMPS
	 */
	private class CDTArriveeColumn
			extends
				ImogColumn<TransfertReferenceProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public CDTArriveeColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(TransfertReferenceProxy object) {
			String value = null;
			if (object != null) {
				if (object.getCDTArrivee() == null)
					value = "";
				else
					value = renderer.getDisplayValue(object.getCDTArrivee());
			}
			return value;
		}

		public String getPropertyName() {
			return "CDTArrivee";
		}
	}

	/**
	 * Column for field DateArrivee
	 * @author MEDES-IMPS
	 */
	private class DateArriveeColumn
			extends
				ImogColumn<TransfertReferenceProxy, String> {

		public DateArriveeColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(TransfertReferenceProxy object) {
			String value = null;
			if (object != null) {
				if (object.getDateArrivee() == null)
					value = "";
				else
					value = DateUtil.getFormatedDate(object.getDateArrivee());
			}
			return value;
		}

		public String getPropertyName() {
			return "dateArrivee";
		}
	}

}
