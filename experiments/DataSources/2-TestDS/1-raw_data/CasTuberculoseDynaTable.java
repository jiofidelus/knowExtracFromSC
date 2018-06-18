package org.imogene.epicam.client.ui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.event.create.CreateCasTuberculoseEvent;
import org.imogene.epicam.client.event.list.ListCasTuberculoseEvent;
import org.imogene.epicam.client.event.view.ViewCasTuberculoseEvent;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.filter.CasTuberculoseFilterPanel;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.CasTuberculoseProxy;
import org.imogene.epicam.shared.proxy.LocalizedTextProxy;
import org.imogene.epicam.shared.request.CasTuberculoseRequest;
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
 * Composite that displays the list of CasTuberculose entries
 * @author MEDES-IMPS
 */
public class CasTuberculoseDynaTable extends ImogDynaTable<CasTuberculoseProxy> {

	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private PushButton deleteButton;

	public CasTuberculoseDynaTable(EpicamRequestFactory requestFactory,
			ImogBeanDataProvider<CasTuberculoseProxy> provider,
			boolean checkBoxesVisible) {
		super(requestFactory, provider, checkBoxesVisible);
	}

	public ImogFilterPanel getFilterPanel() {
		ImogFilterPanel filterPanel = new CasTuberculoseFilterPanel();
		super.configureFilterPanel(filterPanel);
		return filterPanel;
	}

	/**
	 * 
	 */
	@Override
	protected void setColumns() {

		if (AccessManager.canReadCasTuberculoseInformations()) {
			Column<CasTuberculoseProxy, String> patientColumn = new PatientColumn();
			patientColumn.setSortable(true);
			table.addColumn(patientColumn, NLS.constants()
					.casTuberculose_field_s_patient());
		}
		if (AccessManager.canReadCasTuberculoseInformations()) {
			Column<CasTuberculoseProxy, String> dateDebutTraitementColumn = new DateDebutTraitementColumn();
			dateDebutTraitementColumn.setSortable(true);
			table.addColumn(dateDebutTraitementColumn, NLS.constants()
					.casTuberculose_field_s_dateDebutTraitement());
		}
		if (AccessManager.canReadCasTuberculoseInformations()) {
			Column<CasTuberculoseProxy, String> typePatientColumn = new TypePatientColumn();
			typePatientColumn.setSortable(true);
			table.addColumn(typePatientColumn, NLS.constants()
					.casTuberculose_field_s_typePatient());
		}
		if (AccessManager.canReadCasTuberculoseInformations()) {
			Column<CasTuberculoseProxy, String> formeMaladieColumn = new FormeMaladieColumn();
			formeMaladieColumn.setSortable(true);
			table.addColumn(formeMaladieColumn, NLS.constants()
					.casTuberculose_field_s_formeMaladie());
		}

	}

	@Override
	protected GwtEvent<?> getViewEvent(CasTuberculoseProxy value) {
		History.newItem(
				TokenHelper.TK_VIEW + "/castuberculose/" + value.getId(), true);
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

		if (AccessManager.canCreateCasTuberculose()
				&& AccessManager.canEditCasTuberculose()) {
			Command command = new Command() {
				public void execute() {
					History.newItem(TokenHelper.TK_NEW + "/castuberculose/",
							true);
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

		if (AccessManager.canDeleteCasTuberculose()
				&& AccessManager.canEditCasTuberculose()) {
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

		if (AccessManager.canDeleteCasTuberculose()
				&& AccessManager.canEditCasTuberculose()) {

			// Click handler
			registrations.add(deleteButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {

					Set<CasTuberculoseProxy> selectedEntities = selectionModel
							.getSelectedSet();

					int count = selectedEntities.size();
					if (count > 0) {

						EpicamRenderer renderer = EpicamRenderer.get();

						StringBuffer msg = new StringBuffer();
						msg.append(BaseNLS.constants()
								.confirmation_delete_several1()
								+ " "
								+ NLS.constants().casTuberculose_name()
								+ " "
								+ BaseNLS.constants()
										.confirmation_delete_several2() + ": ");
						int i = 0;
						for (CasTuberculoseProxy entity : selectedEntities) {
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

							Request<Void> deleteRequest = getCasTuberculoseRequest()
									.delete(selectedEntities);
							deleteRequest.fire(new Receiver<Void>() {
								@Override
								public void onSuccess(Void response) {
									//Window.alert("The selected CasTuberculose entries have been deleted");
									requestFactory.getEventBus().fireEvent(
											new ListCasTuberculoseEvent());
								}

								@Override
								public void onFailure(ServerFailure error) {
									Window.alert("Error deleting the CasTuberculose entries");
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
	 * Creates the action command that enables to export the CasTuberculose
	 * entries in a csv file
	 * @return the command
	 */
	public Command getCsvExportButton() {

		if (AccessManager.canExportCasTuberculose()) {

			Command command = new Command() {
				public void execute() {

					String url = GWT.getHostPageBaseURL()
							+ EpicamBirtConstants.TBCASE_CSV_KEY + "?"
							+ EpicamBirtConstants.REPORT_NAME
							+ "=casTuberculose_csv" + "&"
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

	private CasTuberculoseRequest getCasTuberculoseRequest() {
		EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory;
		return epicamRequestFactory.casTuberculoseRequest();
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
	private class PatientColumn extends ImogColumn<CasTuberculoseProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public PatientColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(CasTuberculoseProxy object) {
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
	 * Column for field DateDebutTraitement
	 * @author MEDES-IMPS
	 */
	private class DateDebutTraitementColumn
			extends
				ImogColumn<CasTuberculoseProxy, String> {

		public DateDebutTraitementColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(CasTuberculoseProxy object) {
			String value = null;
			if (object != null) {
				if (object.getDateDebutTraitement() == null)
					value = "";
				else
					value = DateUtil.getFormatedDate(object
							.getDateDebutTraitement());
			}
			return value;
		}

		public String getPropertyName() {
			return "dateDebutTraitement";
		}
	}

	/**
	 * Column for field TypePatient
	 * @author MEDES-IMPS
	 */
	private class TypePatientColumn
			extends
				ImogColumn<CasTuberculoseProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public TypePatientColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(CasTuberculoseProxy object) {
			String value = null;
			if (object != null) {
				if (object.getTypePatient() == null)
					value = "";
				else
					value = renderer.getEnumDisplayValue(
							CasTuberculoseProxy.class, "typePatient",
							object.getTypePatient());
			}
			return value;
		}

		public String getPropertyName() {
			return "typePatient";
		}
	}

	/**
	 * Column for field FormeMaladie
	 * @author MEDES-IMPS
	 */
	private class FormeMaladieColumn
			extends
				ImogColumn<CasTuberculoseProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public FormeMaladieColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(CasTuberculoseProxy object) {
			String value = null;
			if (object != null) {
				if (object.getFormeMaladie() == null)
					value = "";
				else
					value = renderer.getEnumDisplayValue(
							CasTuberculoseProxy.class, "formeMaladie",
							object.getFormeMaladie());
			}
			return value;
		}

		public String getPropertyName() {
			return "formeMaladie";
		}
	}

}
