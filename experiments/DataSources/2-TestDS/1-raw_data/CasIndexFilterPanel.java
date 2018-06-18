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
 * Filter panel to filter the CasIndex entries
 * @author MEDES-IMPS
 */
public class CasIndexFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox patient_identifiantBox;
	private ImogFilterBox patient_identifiantFilterBox;
	private TextBox patient_nomBox;
	private ImogFilterBox patient_nomFilterBox;
	private TextBox typeRelationBox;
	private ImogFilterBox typeRelationFilterBox;
	private TextBox patientlie_identifiantBox;
	private ImogFilterBox patientlie_identifiantFilterBox;
	private TextBox patientlie_nomBox;
	private ImogFilterBox patientlie_nomFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public CasIndexFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		patient_identifiantBox.setValue(null);
		patient_nomBox.setValue(null);
		typeRelationBox.setValue(null);
		patientlie_identifiantBox.setValue(null);
		patientlie_nomBox.setValue(null);

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

		typeRelationBox = new TextBox();
		typeRelationFilterBox = new ImogFilterBox(typeRelationBox);
		typeRelationFilterBox.setFilterLabel(NLS.constants()
				.casIndex_field_typeRelation());
		addTableFilter(typeRelationFilterBox);

		patientlie_identifiantBox = new TextBox();
		patientlie_identifiantFilterBox = new ImogFilterBox(
				patientlie_identifiantBox);
		patientlie_identifiantFilterBox.setFilterLabel(NLS.constants()
				.patient_field_identifiant());
		addTableFilter(patientlie_identifiantFilterBox);
		patientlie_nomBox = new TextBox();
		patientlie_nomFilterBox = new ImogFilterBox(patientlie_nomBox);
		patientlie_nomFilterBox.setFilterLabel(NLS.constants()
				.patient_field_nom());
		addTableFilter(patientlie_nomFilterBox);

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

		FilterCriteria typeRelationCrit = new FilterCriteria();
		typeRelationCrit.setField("typeRelation");
		typeRelationCrit.setFieldDisplayName(NLS.constants()
				.casIndex_field_typeRelation());
		typeRelationCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		typeRelationCrit.setValue(typeRelationBox.getValue());
		typeRelationCrit.setValueDisplayName(typeRelationBox.getValue());
		criteria.add(typeRelationCrit);

		FilterCriteria patientlie_identifiantCrit = new FilterCriteria();
		patientlie_identifiantCrit.setField("patientLie.identifiant");
		patientlie_identifiantCrit.setFieldDisplayName(NLS.constants()
				.patient_field_identifiant());
		patientlie_identifiantCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		patientlie_identifiantCrit.setValue(patientlie_identifiantBox
				.getValue());
		patientlie_identifiantCrit
				.setValueDisplayName(patientlie_identifiantBox.getValue());
		criteria.add(patientlie_identifiantCrit);
		FilterCriteria patientlie_nomCrit = new FilterCriteria();
		patientlie_nomCrit.setField("patientLie.nom");
		patientlie_nomCrit.setFieldDisplayName(NLS.constants()
				.patient_field_nom());
		patientlie_nomCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		patientlie_nomCrit.setValue(patientlie_nomBox.getValue());
		patientlie_nomCrit.setValueDisplayName(patientlie_nomBox.getValue());
		criteria.add(patientlie_nomCrit);

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

		if (!AccessManager.canReadCasIndexDescription())
			typeRelationFilterBox.setVisible(false);

		if (!AccessManager.canReadPatientIdentification())
			patientlie_identifiantFilterBox.setVisible(false);
		if (!AccessManager.canReadPatientIdentification())
			patientlie_nomFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
