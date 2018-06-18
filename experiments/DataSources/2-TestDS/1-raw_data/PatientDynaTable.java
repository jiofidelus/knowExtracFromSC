package org.imogene.epicam.client.ui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.event.create.CreatePatientEvent;
import org.imogene.epicam.client.event.list.ListPatientEvent;
import org.imogene.epicam.client.event.view.ViewPatientEvent;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.filter.PatientFilterPanel;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.PatientProxy;
import org.imogene.epicam.shared.proxy.LocalizedTextProxy;
import org.imogene.epicam.shared.request.PatientRequest;
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
 * Composite that displays the list of Patient entries
 * @author MEDES-IMPS
 */
public class PatientDynaTable extends ImogDynaTable<PatientProxy> {

	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private PushButton deleteButton;

	public PatientDynaTable(EpicamRequestFactory requestFactory,
			ImogBeanDataProvider<PatientProxy> provider,
			boolean checkBoxesVisible) {
		super(requestFactory, provider, checkBoxesVisible);
	}

	public ImogFilterPanel getFilterPanel() {
		ImogFilterPanel filterPanel = new PatientFilterPanel();
		super.configureFilterPanel(filterPanel);
		return filterPanel;
	}

	/**
	 * 
	 */
	@Override
	protected void setColumns() {

		if (AccessManager.canReadPatientIdentification()) {
			Column<PatientProxy, String> identifiantColumn = new IdentifiantColumn();
			identifiantColumn.setSortable(true);
			table.addColumn(identifiantColumn, NLS.constants()
					.patient_field_s_identifiant());
		}
		if (AccessManager.canReadPatientIdentification()) {
			Column<PatientProxy, String> nomColumn = new NomColumn();
			nomColumn.setSortable(true);
			table.addColumn(nomColumn, NLS.constants().patient_field_s_nom());
		}
		if (AccessManager.canReadPatientIdentification()) {
			Column<PatientProxy, String> dateNaissanceColumn = new DateNaissanceColumn();
			dateNaissanceColumn.setSortable(true);
			table.addColumn(dateNaissanceColumn, NLS.constants()
					.patient_field_s_dateNaissance());
		}
		if (AccessManager.canReadPatientIdentification()) {
			Column<PatientProxy, String> ageColumn = new AgeColumn();
			ageColumn.setSortable(true);
			table.addColumn(ageColumn, NLS.constants().patient_field_s_age());
		}
		if (AccessManager.canReadPatientIdentification()) {
			Column<PatientProxy, String> sexeColumn = new SexeColumn();
			sexeColumn.setSortable(true);
			table.addColumn(sexeColumn, NLS.constants().patient_field_s_sexe());
		}
		if (AccessManager.canReadPatientIdentification()) {
			Column<PatientProxy, String> professionColumn = new ProfessionColumn();
			professionColumn.setSortable(true);
			table.addColumn(professionColumn, NLS.constants()
					.patient_field_s_profession());
		}
		if (AccessManager.canReadPatientContact()) {
			Column<PatientProxy, String> telephoneUnColumn = new TelephoneUnColumn();
			telephoneUnColumn.setSortable(true);
			table.addColumn(telephoneUnColumn, NLS.constants()
					.patient_field_s_telephoneUn());
		}
		if (AccessManager.canReadPatientContact()) {
			Column<PatientProxy, String> telephoneDeuxColumn = new TelephoneDeuxColumn();
			telephoneDeuxColumn.setSortable(true);
			table.addColumn(telephoneDeuxColumn, NLS.constants()
					.patient_field_s_telephoneDeux());
		}
		if (AccessManager.canReadPatientPersonneContact()) {
			Column<PatientProxy, String> pacNomColumn = new PacNomColumn();
			pacNomColumn.setSortable(true);
			table.addColumn(pacNomColumn, NLS.constants()
					.patient_field_s_pacNom());
		}
		if (AccessManager.canReadPatientPersonneContact()) {
			Column<PatientProxy, String> pacTelephoneUnColumn = new PacTelephoneUnColumn();
			pacTelephoneUnColumn.setSortable(true);
			table.addColumn(pacTelephoneUnColumn, NLS.constants()
					.patient_field_s_pacTelephoneUn());
		}

	}

	@Override
	protected GwtEvent<?> getViewEvent(PatientProxy value) {
		History.newItem(TokenHelper.TK_VIEW + "/patient/" + value.getId(), true);
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

		if (AccessManager.canCreatePatient() && AccessManager.canEditPatient()) {
			Command command = new Command() {
				public void execute() {
					History.newItem(TokenHelper.TK_NEW + "/patient/", true);
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

		if (AccessManager.canDeletePatient() && AccessManager.canEditPatient()) {
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

		if (AccessManager.canDeletePatient() && AccessManager.canEditPatient()) {

			// Click handler
			registrations.add(deleteButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {

					Set<PatientProxy> selectedEntities = selectionModel
							.getSelectedSet();

					int count = selectedEntities.size();
					if (count > 0) {

						EpicamRenderer renderer = EpicamRenderer.get();

						StringBuffer msg = new StringBuffer();
						msg.append(BaseNLS.constants()
								.confirmation_delete_several1()
								+ " "
								+ NLS.constants().patient_name()
								+ " "
								+ BaseNLS.constants()
										.confirmation_delete_several2() + ": ");
						int i = 0;
						for (PatientProxy entity : selectedEntities) {
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

							Request<Void> deleteRequest = getPatientRequest()
									.delete(selectedEntities);
							deleteRequest.fire(new Receiver<Void>() {
								@Override
								public void onSuccess(Void response) {
									//Window.alert("The selected Patient entries have been deleted");
									requestFactory.getEventBus().fireEvent(
											new ListPatientEvent());
								}

								@Override
								public void onFailure(ServerFailure error) {
									Window.alert("Error deleting the Patient entries");
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
	 * Creates the action command that enables to export the Patient
	 * entries in a csv file
	 * @return the command
	 */
	public Command getCsvExportButton() {

		if (AccessManager.canExportPatient()) {

			Command command = new Command() {
				public void execute() {

					String url = GWT.getHostPageBaseURL()
							+ EpicamBirtConstants.PAT_CSV_KEY + "?"
							+ EpicamBirtConstants.REPORT_NAME + "=patient_csv"
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

	private PatientRequest getPatientRequest() {
		EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory;
		return epicamRequestFactory.patientRequest();
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
	 * Column for field Identifiant
	 * @author MEDES-IMPS
	 */
	private class IdentifiantColumn extends ImogColumn<PatientProxy, String> {

		public IdentifiantColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(PatientProxy object) {
			String value = null;
			if (object != null) {
				if (object.getIdentifiant() == null)
					value = "";
				else
					value = object.getIdentifiant();
			}
			return value;
		}

		public String getPropertyName() {
			return "identifiant";
		}
	}

	/**
	 * Column for field Nom
	 * @author MEDES-IMPS
	 */
	private class NomColumn extends ImogColumn<PatientProxy, String> {

		public NomColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(PatientProxy object) {
			String value = null;
			if (object != null) {
				if (object.getNom() == null)
					value = "";
				else
					value = object.getNom();
			}
			return value;
		}

		public String getPropertyName() {
			return "nom";
		}
	}

	/**
	 * Column for field DateNaissance
	 * @author MEDES-IMPS
	 */
	private class DateNaissanceColumn extends ImogColumn<PatientProxy, String> {

		public DateNaissanceColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(PatientProxy object) {
			String value = null;
			if (object != null) {
				if (object.getDateNaissance() == null)
					value = "";
				else
					value = DateUtil.getFormatedDate(object.getDateNaissance());
			}
			return value;
		}

		public String getPropertyName() {
			return "dateNaissance";
		}
	}

	/**
	 * Column for field Age
	 * @author MEDES-IMPS
	 */
	private class AgeColumn extends ImogColumn<PatientProxy, String> {

		public AgeColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(PatientProxy object) {
			String value = null;
			if (object != null) {
				if (object.getAge() == null)
					value = "";
				else
					value = object.getAge().toString();
			}
			return value;
		}

		public String getPropertyName() {
			return "age";
		}
	}

	/**
	 * Column for field Sexe
	 * @author MEDES-IMPS
	 */
	private class SexeColumn extends ImogColumn<PatientProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public SexeColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(PatientProxy object) {
			String value = null;
			if (object != null) {
				if (object.getSexe() == null)
					value = "";
				else
					value = renderer.getEnumDisplayValue(PatientProxy.class,
							"sexe", object.getSexe());
			}
			return value;
		}

		public String getPropertyName() {
			return "sexe";
		}
	}

	/**
	 * Column for field Profession
	 * @author MEDES-IMPS
	 */
	private class ProfessionColumn extends ImogColumn<PatientProxy, String> {

		public ProfessionColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(PatientProxy object) {
			String value = null;
			if (object != null) {
				if (object.getProfession() == null)
					value = "";
				else
					value = object.getProfession();
			}
			return value;
		}

		public String getPropertyName() {
			return "profession";
		}
	}

	/**
	 * Column for field TelephoneUn
	 * @author MEDES-IMPS
	 */
	private class TelephoneUnColumn extends ImogColumn<PatientProxy, String> {

		public TelephoneUnColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(PatientProxy object) {
			String value = null;
			if (object != null) {
				if (object.getTelephoneUn() == null)
					value = "";
				else
					value = object.getTelephoneUn();
			}
			return value;
		}

		public String getPropertyName() {
			return "telephoneUn";
		}
	}

	/**
	 * Column for field TelephoneDeux
	 * @author MEDES-IMPS
	 */
	private class TelephoneDeuxColumn extends ImogColumn<PatientProxy, String> {

		public TelephoneDeuxColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(PatientProxy object) {
			String value = null;
			if (object != null) {
				if (object.getTelephoneDeux() == null)
					value = "";
				else
					value = object.getTelephoneDeux();
			}
			return value;
		}

		public String getPropertyName() {
			return "telephoneDeux";
		}
	}

	/**
	 * Column for field PacNom
	 * @author MEDES-IMPS
	 */
	private class PacNomColumn extends ImogColumn<PatientProxy, String> {

		public PacNomColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(PatientProxy object) {
			String value = null;
			if (object != null) {
				if (object.getPacNom() == null)
					value = "";
				else
					value = object.getPacNom();
			}
			return value;
		}

		public String getPropertyName() {
			return "pacNom";
		}
	}

	/**
	 * Column for field PacTelephoneUn
	 * @author MEDES-IMPS
	 */
	private class PacTelephoneUnColumn extends ImogColumn<PatientProxy, String> {

		public PacTelephoneUnColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(PatientProxy object) {
			String value = null;
			if (object != null) {
				if (object.getPacTelephoneUn() == null)
					value = "";
				else
					value = object.getPacTelephoneUn();
			}
			return value;
		}

		public String getPropertyName() {
			return "pacTelephoneUn";
		}
	}

}
