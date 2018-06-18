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
 * Filter panel to filter the CasTuberculose entries
 * @author MEDES-IMPS
 */
public class CasTuberculoseFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox patient_identifiantBox;
	private ImogFilterBox patient_identifiantFilterBox;
	private TextBox patient_nomBox;
	private ImogFilterBox patient_nomFilterBox;
	private DateBox dateDebutTraitementBeforeBox;
	private ImogFilterBox dateDebutTraitementBeforeFilterBox;
	private DateBox dateDebutTraitementAfterBox;
	private ImogFilterBox dateDebutTraitementAfterFilterBox;
	private ListBox typePatientBox;
	private ImogFilterBox typePatientFilterBox;
	private ListBox formeMaladieBox;
	private ImogFilterBox formeMaladieFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public CasTuberculoseFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		patient_identifiantBox.setValue(null);
		patient_nomBox.setValue(null);
		dateDebutTraitementBeforeBox.setValue(null);
		dateDebutTraitementAfterBox.setValue(null);
		typePatientBox.setSelectedIndex(0);
		formeMaladieBox.setSelectedIndex(0);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		patient_identifiantBox = new TextBox();
		patient_identifiantFilterBox = new ImogFilterBox(patient_identifiantBox);
		patient_identifiantFilterBox.setFilterLabel(NLS.constants()
				.patient_field_identifiant());
		addTableFilter(patient_identifiantFilterBox);
		patient_nomBox = new TextBox();
		patient_nomFilterBox = new ImogFilterBox(patient_nomBox);
		patient_nomFilterBox
				.setFilterLabel(NLS.constants().patient_field_nom());
		addTableFilter(patient_nomFilterBox);

		dateDebutTraitementAfterBox = new DateBox();
		dateDebutTraitementAfterBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateDebutTraitementAfterFilterBox = new ImogFilterBox(
				dateDebutTraitementAfterBox);
		dateDebutTraitementAfterFilterBox.setFilterLabel(NLS.constants()
				.casTuberculose_field_dateDebutTraitement()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(dateDebutTraitementAfterFilterBox);

		dateDebutTraitementBeforeBox = new DateBox();
		dateDebutTraitementBeforeBox.setFormat(new SimpleImogDateFormat(
				DateUtil.getDateFormater()));
		dateDebutTraitementBeforeFilterBox = new ImogFilterBox(
				dateDebutTraitementBeforeBox);
		dateDebutTraitementBeforeFilterBox.setFilterLabel(NLS.constants()
				.casTuberculose_field_dateDebutTraitement()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(dateDebutTraitementBeforeFilterBox);

		typePatientBox = new ListBox();
		typePatientBox.addItem("", BaseNLS.constants().enumeration_unknown());
		typePatientBox.setSelectedIndex(0);
		typePatientBox.addItem(NLS.constants()
				.casTuberculose_typePatient_nouveauCas_option(),
				EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_NOUVEAUCAS);
		typePatientBox
				.addItem(
						NLS.constants()
								.casTuberculose_typePatient_repriseApresAbandon_option(),
						EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_REPRISEAPRESABANDON);
		typePatientBox.addItem(NLS.constants()
				.casTuberculose_typePatient_echec_option(),
				EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_ECHEC);
		typePatientBox.addItem(NLS.constants()
				.casTuberculose_typePatient_rechute_option(),
				EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_RECHUTE);
		typePatientBox.addItem(NLS.constants()
				.casTuberculose_typePatient_autre_option(),
				EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_AUTRE);
		typePatientFilterBox = new ImogFilterBox(typePatientBox);
		typePatientFilterBox.setFilterLabel(NLS.constants()
				.casTuberculose_field_typePatient());
		addTableFilter(typePatientFilterBox);

		formeMaladieBox = new ListBox();
		formeMaladieBox.addItem("", BaseNLS.constants().enumeration_unknown());
		formeMaladieBox.setSelectedIndex(0);
		formeMaladieBox.addItem(NLS.constants()
				.casTuberculose_formeMaladie_tPMPlus_option(),
				EpicamEnumConstants.CASTUBERCULOSE_FORMEMALADIE_TPMPLUS);
		formeMaladieBox.addItem(NLS.constants()
				.casTuberculose_formeMaladie_tPMMoins_option(),
				EpicamEnumConstants.CASTUBERCULOSE_FORMEMALADIE_TPMMOINS);
		formeMaladieBox
				.addItem(
						NLS.constants()
								.casTuberculose_formeMaladie_extra_Pulmonaire_option(),
						EpicamEnumConstants.CASTUBERCULOSE_FORMEMALADIE_EXTRA_PULMONAIRE);
		formeMaladieFilterBox = new ImogFilterBox(formeMaladieBox);
		formeMaladieFilterBox.setFilterLabel(NLS.constants()
				.casTuberculose_field_formeMaladie());
		addTableFilter(formeMaladieFilterBox);

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

		FilterCriteria patient_identifiantCrit = new FilterCriteria();
		patient_identifiantCrit.setField("patient.identifiant");
		patient_identifiantCrit.setFieldDisplayName(NLS.constants()
				.patient_field_identifiant());
		patient_identifiantCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		patient_identifiantCrit.setValue(patient_identifiantBox.getValue());
		patient_identifiantCrit.setValueDisplayName(patient_identifiantBox
				.getValue());
		criteria.add(patient_identifiantCrit);
		FilterCriteria patient_nomCrit = new FilterCriteria();
		patient_nomCrit.setField("patient.nom");
		patient_nomCrit
				.setFieldDisplayName(NLS.constants().patient_field_nom());
		patient_nomCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		patient_nomCrit.setValue(patient_nomBox.getValue());
		patient_nomCrit.setValueDisplayName(patient_nomBox.getValue());
		criteria.add(patient_nomCrit);

		if (dateDebutTraitementBeforeBox.getValue() != null) {
			FilterCriteria dateDebutTraitementBeforeCrit = new FilterCriteria();
			dateDebutTraitementBeforeCrit.setField("dateDebutTraitement");
			dateDebutTraitementBeforeCrit.setFieldDisplayName(NLS.constants()
					.casTuberculose_field_dateDebutTraitement()
					+ BaseNLS.constants().search_operator_inferior());
			dateDebutTraitementBeforeCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			dateDebutTraitementBeforeCrit.setValue(DateUtil
					.getDate(dateDebutTraitementBeforeBox.getValue()));
			dateDebutTraitementBeforeCrit.setValueDisplayName(DateUtil
					.getDate(dateDebutTraitementBeforeBox.getValue()));
			criteria.add(dateDebutTraitementBeforeCrit);
		}

		if (dateDebutTraitementAfterBox.getValue() != null) {
			FilterCriteria dateDebutTraitementAfterCrit = new FilterCriteria();
			dateDebutTraitementAfterCrit.setField("dateDebutTraitement");
			dateDebutTraitementAfterCrit.setFieldDisplayName(NLS.constants()
					.casTuberculose_field_dateDebutTraitement()
					+ BaseNLS.constants().search_operator_superior());
			dateDebutTraitementAfterCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			dateDebutTraitementAfterCrit.setValue(DateUtil
					.getDate(dateDebutTraitementAfterBox.getValue()));
			dateDebutTraitementAfterCrit.setValueDisplayName(DateUtil
					.getDate(dateDebutTraitementAfterBox.getValue()));
			criteria.add(dateDebutTraitementAfterCrit);
		}

		FilterCriteria typePatientCrit = new FilterCriteria();
		typePatientCrit.setField("typePatient");
		typePatientCrit.setFieldDisplayName(NLS.constants()
				.casTuberculose_field_typePatient());
		typePatientCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
		typePatientCrit.setValue(typePatientBox.getValue(typePatientBox
				.getSelectedIndex()));
		typePatientCrit.setValueDisplayName(EpicamRenderer.get()
				.getEnumDisplayValue(
						CasTuberculoseProxy.class,
						"typePatient",
						typePatientBox.getValue(typePatientBox
								.getSelectedIndex())));
		criteria.add(typePatientCrit);

		FilterCriteria formeMaladieCrit = new FilterCriteria();
		formeMaladieCrit.setField("formeMaladie");
		formeMaladieCrit.setFieldDisplayName(NLS.constants()
				.casTuberculose_field_formeMaladie());
		formeMaladieCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
		formeMaladieCrit.setValue(formeMaladieBox.getValue(formeMaladieBox
				.getSelectedIndex()));
		formeMaladieCrit.setValueDisplayName(EpicamRenderer.get()
				.getEnumDisplayValue(
						CasTuberculoseProxy.class,
						"formeMaladie",
						formeMaladieBox.getValue(formeMaladieBox
								.getSelectedIndex())));
		criteria.add(formeMaladieCrit);

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
			patient_identifiantFilterBox.setVisible(false);
		if (!AccessManager.canReadPatientIdentification())
			patient_nomFilterBox.setVisible(false);

		if (!AccessManager.canReadCasTuberculoseInformations()) {
			dateDebutTraitementBeforeFilterBox.setVisible(false);
			dateDebutTraitementAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.canReadCasTuberculoseInformations())
			typePatientFilterBox.setVisible(false);

		if (!AccessManager.canReadCasTuberculoseInformations())
			formeMaladieFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
