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
 * Filter panel to filter the ExamenBiologique entries
 * @author MEDES-IMPS
 */
public class ExamenBiologiqueFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox patient_identifiantBox;
	private ImogFilterBox patient_identifiantFilterBox;
	private TextBox patient_nomBox;
	private ImogFilterBox patient_nomFilterBox;
	private DateBox dateBeforeBox;
	private ImogFilterBox dateBeforeFilterBox;
	private DateBox dateAfterBox;
	private ImogFilterBox dateAfterFilterBox;
	private DoubleBox poidsBox;
	private ImogFilterBox poidsFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public ExamenBiologiqueFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		patient_identifiantBox.setValue(null);
		patient_nomBox.setValue(null);
		dateBeforeBox.setValue(null);
		dateAfterBox.setValue(null);
		poidsBox.setValue(null);

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

		dateAfterBox = new DateBox();
		dateAfterBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateAfterFilterBox = new ImogFilterBox(dateAfterBox);
		dateAfterFilterBox.setFilterLabel(NLS.constants()
				.examenBiologique_field_date()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(dateAfterFilterBox);

		dateBeforeBox = new DateBox();
		dateBeforeBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateBeforeFilterBox = new ImogFilterBox(dateBeforeBox);
		dateBeforeFilterBox.setFilterLabel(NLS.constants()
				.examenBiologique_field_date()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(dateBeforeFilterBox);

		poidsBox = new DoubleBox();
		poidsFilterBox = new ImogFilterBox(poidsBox);
		poidsFilterBox.setFilterLabel(NLS.constants()
				.examenBiologique_field_poids());
		addTableFilter(poidsFilterBox);

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

		if (dateBeforeBox.getValue() != null) {
			FilterCriteria dateBeforeCrit = new FilterCriteria();
			dateBeforeCrit.setField("date");
			dateBeforeCrit.setFieldDisplayName(NLS.constants()
					.examenBiologique_field_date()
					+ BaseNLS.constants().search_operator_inferior());
			dateBeforeCrit.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			dateBeforeCrit.setValue(DateUtil.getDate(dateBeforeBox.getValue()));
			dateBeforeCrit.setValueDisplayName(DateUtil.getDate(dateBeforeBox
					.getValue()));
			criteria.add(dateBeforeCrit);
		}

		if (dateAfterBox.getValue() != null) {
			FilterCriteria dateAfterCrit = new FilterCriteria();
			dateAfterCrit.setField("date");
			dateAfterCrit.setFieldDisplayName(NLS.constants()
					.examenBiologique_field_date()
					+ BaseNLS.constants().search_operator_superior());
			dateAfterCrit.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			dateAfterCrit.setValue(DateUtil.getDate(dateAfterBox.getValue()));
			dateAfterCrit.setValueDisplayName(DateUtil.getDate(dateAfterBox
					.getValue()));
			criteria.add(dateAfterCrit);
		}

		FilterCriteria poidsCrit = new FilterCriteria();
		poidsCrit.setField("poids");
		poidsCrit.setFieldDisplayName(NLS.constants()
				.examenBiologique_field_poids());
		poidsCrit.setOperation(CriteriaConstants.FLOAT_OPERATOR_EQUAL);
		if (poidsBox.getValue() == null)
			poidsCrit.setValue(null);
		else
			poidsCrit.setValue(String.valueOf(poidsBox.getValue()));
		poidsCrit.setValueDisplayName(String.valueOf(poidsBox.getValue()));
		criteria.add(poidsCrit);

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

		if (!AccessManager.canReadExamenBiologiqueDescription()) {
			dateBeforeFilterBox.setVisible(false);
			dateAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.canReadExamenBiologiqueDescription())
			poidsFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
