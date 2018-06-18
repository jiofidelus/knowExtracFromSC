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
 * Filter panel to filter the ExamenATB entries
 * @author MEDES-IMPS
 */
public class ExamenATBFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox castb_patient_identifiantBox;
	private ImogFilterBox castb_patient_identifiantFilterBox;
	private TextBox castb_patient_nomBox;
	private ImogFilterBox castb_patient_nomFilterBox;
	private DateBox dateExamenBeforeBox;
	private ImogFilterBox dateExamenBeforeFilterBox;
	private DateBox dateExamenAfterBox;
	private ImogFilterBox dateExamenAfterFilterBox;
	private ListBox raisonDepistageBox;
	private ImogFilterBox raisonDepistageFilterBox;
	private TextBox resultatBox;
	private ImogFilterBox resultatFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public ExamenATBFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		castb_patient_identifiantBox.setValue(null);
		castb_patient_nomBox.setValue(null);
		dateExamenBeforeBox.setValue(null);
		dateExamenAfterBox.setValue(null);
		raisonDepistageBox.setSelectedIndex(0);
		resultatBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		castb_patient_identifiantBox = new TextBox();
		castb_patient_identifiantFilterBox = new ImogFilterBox(
				castb_patient_identifiantBox);
		castb_patient_identifiantFilterBox.setFilterLabel(NLS.constants()
				.patient_field_identifiant());
		addTableFilter(castb_patient_identifiantFilterBox);
		castb_patient_nomBox = new TextBox();
		castb_patient_nomFilterBox = new ImogFilterBox(castb_patient_nomBox);
		castb_patient_nomFilterBox.setFilterLabel(NLS.constants()
				.patient_field_nom());
		addTableFilter(castb_patient_nomFilterBox);

		dateExamenAfterBox = new DateBox();
		dateExamenAfterBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateExamenAfterFilterBox = new ImogFilterBox(dateExamenAfterBox);
		dateExamenAfterFilterBox.setFilterLabel(NLS.constants()
				.examenATB_field_dateExamen()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(dateExamenAfterFilterBox);

		dateExamenBeforeBox = new DateBox();
		dateExamenBeforeBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateExamenBeforeFilterBox = new ImogFilterBox(dateExamenBeforeBox);
		dateExamenBeforeFilterBox.setFilterLabel(NLS.constants()
				.examenATB_field_dateExamen()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(dateExamenBeforeFilterBox);

		raisonDepistageBox = new ListBox();
		raisonDepistageBox.addItem("", BaseNLS.constants()
				.enumeration_unknown());
		raisonDepistageBox.setSelectedIndex(0);
		raisonDepistageBox.addItem(NLS.constants()
				.examenATB_raisonDepistage_diagnostic_option(),
				EpicamEnumConstants.EXAMENATB_RAISONDEPISTAGE_DIAGNOSTIC);
		raisonDepistageBox.addItem(NLS.constants()
				.examenATB_raisonDepistage_suivi_option(),
				EpicamEnumConstants.EXAMENATB_RAISONDEPISTAGE_SUIVI);
		raisonDepistageFilterBox = new ImogFilterBox(raisonDepistageBox);
		raisonDepistageFilterBox.setFilterLabel(NLS.constants()
				.examenATB_field_raisonDepistage());
		addTableFilter(raisonDepistageFilterBox);

		resultatBox = new TextBox();
		resultatFilterBox = new ImogFilterBox(resultatBox);
		resultatFilterBox.setFilterLabel(NLS.constants()
				.examenATB_field_resultat());
		addTableFilter(resultatFilterBox);

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

		FilterCriteria castb_patient_identifiantCrit = new FilterCriteria();
		castb_patient_identifiantCrit.setField("casTb.patient.identifiant");
		castb_patient_identifiantCrit.setFieldDisplayName(NLS.constants()
				.patient_field_identifiant());
		castb_patient_identifiantCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		castb_patient_identifiantCrit.setValue(castb_patient_identifiantBox
				.getValue());
		castb_patient_identifiantCrit
				.setValueDisplayName(castb_patient_identifiantBox.getValue());
		criteria.add(castb_patient_identifiantCrit);
		FilterCriteria castb_patient_nomCrit = new FilterCriteria();
		castb_patient_nomCrit.setField("casTb.patient.nom");
		castb_patient_nomCrit.setFieldDisplayName(NLS.constants()
				.patient_field_nom());
		castb_patient_nomCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		castb_patient_nomCrit.setValue(castb_patient_nomBox.getValue());
		castb_patient_nomCrit.setValueDisplayName(castb_patient_nomBox
				.getValue());
		criteria.add(castb_patient_nomCrit);

		if (dateExamenBeforeBox.getValue() != null) {
			FilterCriteria dateExamenBeforeCrit = new FilterCriteria();
			dateExamenBeforeCrit.setField("dateExamen");
			dateExamenBeforeCrit.setFieldDisplayName(NLS.constants()
					.examenATB_field_dateExamen()
					+ BaseNLS.constants().search_operator_inferior());
			dateExamenBeforeCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			dateExamenBeforeCrit.setValue(DateUtil.getDate(dateExamenBeforeBox
					.getValue()));
			dateExamenBeforeCrit.setValueDisplayName(DateUtil
					.getDate(dateExamenBeforeBox.getValue()));
			criteria.add(dateExamenBeforeCrit);
		}

		if (dateExamenAfterBox.getValue() != null) {
			FilterCriteria dateExamenAfterCrit = new FilterCriteria();
			dateExamenAfterCrit.setField("dateExamen");
			dateExamenAfterCrit.setFieldDisplayName(NLS.constants()
					.examenATB_field_dateExamen()
					+ BaseNLS.constants().search_operator_superior());
			dateExamenAfterCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			dateExamenAfterCrit.setValue(DateUtil.getDate(dateExamenAfterBox
					.getValue()));
			dateExamenAfterCrit.setValueDisplayName(DateUtil
					.getDate(dateExamenAfterBox.getValue()));
			criteria.add(dateExamenAfterCrit);
		}

		FilterCriteria raisonDepistageCrit = new FilterCriteria();
		raisonDepistageCrit.setField("raisonDepistage");
		raisonDepistageCrit.setFieldDisplayName(NLS.constants()
				.examenATB_field_raisonDepistage());
		raisonDepistageCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
		raisonDepistageCrit.setValue(raisonDepistageBox
				.getValue(raisonDepistageBox.getSelectedIndex()));
		raisonDepistageCrit.setValueDisplayName(EpicamRenderer.get()
				.getEnumDisplayValue(
						ExamenATBProxy.class,
						"raisonDepistage",
						raisonDepistageBox.getValue(raisonDepistageBox
								.getSelectedIndex())));
		criteria.add(raisonDepistageCrit);

		FilterCriteria resultatCrit = new FilterCriteria();
		resultatCrit.setField("resultat");
		resultatCrit.setFieldDisplayName(NLS.constants()
				.examenATB_field_resultat());
		resultatCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		resultatCrit.setValue(resultatBox.getValue());
		resultatCrit.setValueDisplayName(resultatBox.getValue());
		criteria.add(resultatCrit);

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
			castb_patient_identifiantFilterBox.setVisible(false);
		if (!AccessManager.canReadPatientIdentification())
			castb_patient_nomFilterBox.setVisible(false);

		if (!AccessManager.canReadExamenATBExamen()) {
			dateExamenBeforeFilterBox.setVisible(false);
			dateExamenAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.canReadExamenATBExamen())
			raisonDepistageFilterBox.setVisible(false);

		if (!AccessManager.canReadExamenATBExamen())
			resultatFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
