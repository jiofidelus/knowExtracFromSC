package org.imogene.epicam.client.ui.filter;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.lib.common.constants.CriteriaConstants;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.field.widget.ImogBooleanAsRadio;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.field.widget.ImogBooleanAsList;
import org.imogene.web.client.ui.table.filter.ImogFilterBox;
import org.imogene.web.client.ui.table.filter.ImogFilterPanel;
import org.imogene.web.client.util.DateUtil;
import org.imogene.web.client.util.BooleanUtil;
import org.imogene.web.client.util.FilterCriteria;
import org.imogene.web.client.util.SimpleImogDateFormat;

import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;

/**
 * Filter panel to filter the Patient entries
 * @author MEDES-IMPS
 */
public class PatientFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox identifiantBox;
	private ImogFilterBox identifiantFilterBox;
	private TextBox nomBox;
	private ImogFilterBox nomFilterBox;
	private DateBox dateNaissanceBeforeBox;
	private ImogFilterBox dateNaissanceBeforeFilterBox;
	private DateBox dateNaissanceAfterBox;
	private ImogFilterBox dateNaissanceAfterFilterBox;
	private IntegerBox ageBox;
	private ImogFilterBox ageFilterBox;
	private ListBox sexeBox;
	private ImogFilterBox sexeFilterBox;
	private TextBox professionBox;
	private ImogFilterBox professionFilterBox;
	private TextBox telephoneUnBox;
	private ImogFilterBox telephoneUnFilterBox;
	private TextBox telephoneDeuxBox;
	private ImogFilterBox telephoneDeuxFilterBox;
	private TextBox pacNomBox;
	private ImogFilterBox pacNomFilterBox;
	private TextBox pacTelephoneUnBox;
	private ImogFilterBox pacTelephoneUnFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public PatientFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		identifiantBox.setValue(null);
		nomBox.setValue(null);
		dateNaissanceBeforeBox.setValue(null);
		dateNaissanceAfterBox.setValue(null);
		ageBox.setValue(null);
		sexeBox.setSelectedIndex(0);
		professionBox.setValue(null);
		telephoneUnBox.setValue(null);
		telephoneDeuxBox.setValue(null);
		pacNomBox.setValue(null);
		pacTelephoneUnBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		identifiantBox = new TextBox();
		identifiantFilterBox = new ImogFilterBox(identifiantBox);
		identifiantFilterBox.setFilterLabel(NLS.constants()
				.patient_field_identifiant());
		addTableFilter(identifiantFilterBox);

		nomBox = new TextBox();
		nomFilterBox = new ImogFilterBox(nomBox);
		nomFilterBox.setFilterLabel(NLS.constants().patient_field_nom());
		addTableFilter(nomFilterBox);

		dateNaissanceAfterBox = new DateBox();
		dateNaissanceAfterBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateNaissanceAfterFilterBox = new ImogFilterBox(dateNaissanceAfterBox);
		dateNaissanceAfterFilterBox.setFilterLabel(NLS.constants()
				.patient_field_dateNaissance()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(dateNaissanceAfterFilterBox);

		dateNaissanceBeforeBox = new DateBox();
		dateNaissanceBeforeBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateNaissanceBeforeFilterBox = new ImogFilterBox(dateNaissanceBeforeBox);
		dateNaissanceBeforeFilterBox.setFilterLabel(NLS.constants()
				.patient_field_dateNaissance()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(dateNaissanceBeforeFilterBox);

		ageBox = new IntegerBox();
		ageFilterBox = new ImogFilterBox(ageBox);
		ageFilterBox.setFilterLabel(NLS.constants().patient_field_age());
		addTableFilter(ageFilterBox);

		sexeBox = new ListBox();
		sexeBox.addItem("", BaseNLS.constants().enumeration_unknown());
		sexeBox.setSelectedIndex(0);
		sexeBox.addItem(NLS.constants().patient_sexe_masculin_option(),
				EpicamEnumConstants.PATIENT_SEXE_MASCULIN);
		sexeBox.addItem(NLS.constants().patient_sexe_feminin_option(),
				EpicamEnumConstants.PATIENT_SEXE_FEMININ);
		sexeFilterBox = new ImogFilterBox(sexeBox);
		sexeFilterBox.setFilterLabel(NLS.constants().patient_field_sexe());
		addTableFilter(sexeFilterBox);

		professionBox = new TextBox();
		professionFilterBox = new ImogFilterBox(professionBox);
		professionFilterBox.setFilterLabel(NLS.constants()
				.patient_field_profession());
		addTableFilter(professionFilterBox);

		telephoneUnBox = new TextBox();
		telephoneUnFilterBox = new ImogFilterBox(telephoneUnBox);
		telephoneUnFilterBox.setFilterLabel(NLS.constants()
				.patient_field_telephoneUn());
		addTableFilter(telephoneUnFilterBox);

		telephoneDeuxBox = new TextBox();
		telephoneDeuxFilterBox = new ImogFilterBox(telephoneDeuxBox);
		telephoneDeuxFilterBox.setFilterLabel(NLS.constants()
				.patient_field_telephoneDeux());
		addTableFilter(telephoneDeuxFilterBox);

		pacNomBox = new TextBox();
		pacNomFilterBox = new ImogFilterBox(pacNomBox);
		pacNomFilterBox.setFilterLabel(NLS.constants().patient_field_pacNom());
		addTableFilter(pacNomFilterBox);

		pacTelephoneUnBox = new TextBox();
		pacTelephoneUnFilterBox = new ImogFilterBox(pacTelephoneUnBox);
		pacTelephoneUnFilterBox.setFilterLabel(NLS.constants()
				.patient_field_pacTelephoneUn());
		addTableFilter(pacTelephoneUnFilterBox);

		deletedEntityBox = new ImogBooleanAsRadio();
		deletedEntityBox.isStrict(true);
		deletedEntityBox.setEnabled(true);
		deletedEntityBox.setValue(false);
		deletedEntityBoxFilterBox = new ImogFilterBox(deletedEntityBox);
		deletedEntityBoxFilterBox.setFilterLabel(BaseNLS.constants()
				.entity_field_deleted());
		addTableFilter(deletedEntityBoxFilterBox);
	}

	@Override
	public List<FilterCriteria> getFilterCriteria() {

		String locale = NLS.constants().locale();

		List<FilterCriteria> criteria = new ArrayList<FilterCriteria>();

		FilterCriteria identifiantCrit = new FilterCriteria();
		identifiantCrit.setField("identifiant");
		identifiantCrit.setFieldDisplayName(NLS.constants()
				.patient_field_identifiant());
		identifiantCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		identifiantCrit.setValue(identifiantBox.getValue());
		identifiantCrit.setValueDisplayName(identifiantBox.getValue());
		criteria.add(identifiantCrit);

		FilterCriteria nomCrit = new FilterCriteria();
		nomCrit.setField("nom");
		nomCrit.setFieldDisplayName(NLS.constants().patient_field_nom());
		nomCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		nomCrit.setValue(nomBox.getValue());
		nomCrit.setValueDisplayName(nomBox.getValue());
		criteria.add(nomCrit);

		if (dateNaissanceBeforeBox.getValue() != null) {
			FilterCriteria dateNaissanceBeforeCrit = new FilterCriteria();
			dateNaissanceBeforeCrit.setField("dateNaissance");
			dateNaissanceBeforeCrit.setFieldDisplayName(NLS.constants()
					.patient_field_dateNaissance()
					+ BaseNLS.constants().search_operator_inferior());
			dateNaissanceBeforeCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			dateNaissanceBeforeCrit.setValue(DateUtil
					.getDate(dateNaissanceBeforeBox.getValue()));
			dateNaissanceBeforeCrit.setValueDisplayName(DateUtil
					.getDate(dateNaissanceBeforeBox.getValue()));
			criteria.add(dateNaissanceBeforeCrit);
		}

		if (dateNaissanceAfterBox.getValue() != null) {
			FilterCriteria dateNaissanceAfterCrit = new FilterCriteria();
			dateNaissanceAfterCrit.setField("dateNaissance");
			dateNaissanceAfterCrit.setFieldDisplayName(NLS.constants()
					.patient_field_dateNaissance()
					+ BaseNLS.constants().search_operator_superior());
			dateNaissanceAfterCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			dateNaissanceAfterCrit.setValue(DateUtil
					.getDate(dateNaissanceAfterBox.getValue()));
			dateNaissanceAfterCrit.setValueDisplayName(DateUtil
					.getDate(dateNaissanceAfterBox.getValue()));
			criteria.add(dateNaissanceAfterCrit);
		}

		FilterCriteria ageCrit = new FilterCriteria();
		ageCrit.setField("age");
		ageCrit.setFieldDisplayName(NLS.constants().patient_field_age());
		ageCrit.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
		if (ageBox.getValue() == null)
			ageCrit.setValue(null);
		else
			ageCrit.setValue(String.valueOf(ageBox.getValue()));
		ageCrit.setValueDisplayName(String.valueOf(ageBox.getValue()));
		criteria.add(ageCrit);

		FilterCriteria sexeCrit = new FilterCriteria();
		sexeCrit.setField("sexe");
		sexeCrit.setFieldDisplayName(NLS.constants().patient_field_sexe());
		sexeCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
		sexeCrit.setValue(sexeBox.getValue(sexeBox.getSelectedIndex()));
		sexeCrit.setValueDisplayName(EpicamRenderer.get().getEnumDisplayValue(
				PatientProxy.class, "sexe",
				sexeBox.getValue(sexeBox.getSelectedIndex())));
		criteria.add(sexeCrit);

		FilterCriteria professionCrit = new FilterCriteria();
		professionCrit.setField("profession");
		professionCrit.setFieldDisplayName(NLS.constants()
				.patient_field_profession());
		professionCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		professionCrit.setValue(professionBox.getValue());
		professionCrit.setValueDisplayName(professionBox.getValue());
		criteria.add(professionCrit);

		FilterCriteria telephoneUnCrit = new FilterCriteria();
		telephoneUnCrit.setField("telephoneUn");
		telephoneUnCrit.setFieldDisplayName(NLS.constants()
				.patient_field_telephoneUn());
		telephoneUnCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		telephoneUnCrit.setValue(telephoneUnBox.getValue());
		telephoneUnCrit.setValueDisplayName(telephoneUnBox.getValue());
		criteria.add(telephoneUnCrit);

		FilterCriteria telephoneDeuxCrit = new FilterCriteria();
		telephoneDeuxCrit.setField("telephoneDeux");
		telephoneDeuxCrit.setFieldDisplayName(NLS.constants()
				.patient_field_telephoneDeux());
		telephoneDeuxCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		telephoneDeuxCrit.setValue(telephoneDeuxBox.getValue());
		telephoneDeuxCrit.setValueDisplayName(telephoneDeuxBox.getValue());
		criteria.add(telephoneDeuxCrit);

		FilterCriteria pacNomCrit = new FilterCriteria();
		pacNomCrit.setField("pacNom");
		pacNomCrit.setFieldDisplayName(NLS.constants().patient_field_pacNom());
		pacNomCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		pacNomCrit.setValue(pacNomBox.getValue());
		pacNomCrit.setValueDisplayName(pacNomBox.getValue());
		criteria.add(pacNomCrit);

		FilterCriteria pacTelephoneUnCrit = new FilterCriteria();
		pacTelephoneUnCrit.setField("pacTelephoneUn");
		pacTelephoneUnCrit.setFieldDisplayName(NLS.constants()
				.patient_field_pacTelephoneUn());
		pacTelephoneUnCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		pacTelephoneUnCrit.setValue(pacTelephoneUnBox.getValue());
		pacTelephoneUnCrit.setValueDisplayName(pacTelephoneUnBox.getValue());
		criteria.add(pacTelephoneUnCrit);

		FilterCriteria deletedEntityCrit = new FilterCriteria();
		deletedEntityCrit.setField("deleted");
		deletedEntityCrit.setFieldDisplayName(BaseNLS.constants()
				.entity_field_deleted());
		if (deletedEntityBox.getValue()) {
			deletedEntityCrit
					.setOperation(CriteriaConstants.OPERATOR_ISNOTNULL);
			deletedEntityCrit.setValue("true");
			deletedEntityCrit.setValueDisplayName(BaseNLS.constants()
					.boolean_true());
		} else {
			deletedEntityCrit.setOperation(CriteriaConstants.OPERATOR_ISNULL);
			deletedEntityCrit.setValue("false");
			deletedEntityCrit.setValueDisplayName(BaseNLS.constants()
					.boolean_false());
		}
		criteria.add(deletedEntityCrit);

		return criteria;
	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	public void setFieldReadAccess() {

		if (!AccessManager.canReadPatientIdentification())
			identifiantFilterBox.setVisible(false);

		if (!AccessManager.canReadPatientIdentification())
			nomFilterBox.setVisible(false);

		if (!AccessManager.canReadPatientIdentification()) {
			dateNaissanceBeforeFilterBox.setVisible(false);
			dateNaissanceAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.canReadPatientIdentification())
			ageFilterBox.setVisible(false);

		if (!AccessManager.canReadPatientIdentification())
			sexeFilterBox.setVisible(false);

		if (!AccessManager.canReadPatientIdentification())
			professionFilterBox.setVisible(false);

		if (!AccessManager.canReadPatientContact())
			telephoneUnFilterBox.setVisible(false);

		if (!AccessManager.canReadPatientContact())
			telephoneDeuxFilterBox.setVisible(false);

		if (!AccessManager.canReadPatientPersonneContact())
			pacNomFilterBox.setVisible(false);

		if (!AccessManager.canReadPatientPersonneContact())
			pacTelephoneUnFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
