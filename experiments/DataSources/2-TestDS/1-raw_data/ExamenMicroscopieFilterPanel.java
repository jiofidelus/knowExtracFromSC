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
 * Filter panel to filter the ExamenMicroscopie entries
 * @author MEDES-IMPS
 */
public class ExamenMicroscopieFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox castb_patient_identifiantBox;
	private ImogFilterBox castb_patient_identifiantFilterBox;
	private TextBox castb_patient_nomBox;
	private ImogFilterBox castb_patient_nomFilterBox;
	private DateBox dateBeforeBox;
	private ImogFilterBox dateBeforeFilterBox;
	private DateBox dateAfterBox;
	private ImogFilterBox dateAfterFilterBox;
	private ListBox raisonDepistageBox;
	private ImogFilterBox raisonDepistageFilterBox;
	private ListBox resultatBox;
	private ImogFilterBox resultatFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public ExamenMicroscopieFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		castb_patient_identifiantBox.setValue(null);
		castb_patient_nomBox.setValue(null);
		dateBeforeBox.setValue(null);
		dateAfterBox.setValue(null);
		raisonDepistageBox.setSelectedIndex(0);
		resultatBox.setSelectedIndex(0);

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

		dateAfterBox = new DateBox();
		dateAfterBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateAfterFilterBox = new ImogFilterBox(dateAfterBox);
		dateAfterFilterBox.setFilterLabel(NLS.constants()
				.examenMicroscopie_field_date()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(dateAfterFilterBox);

		dateBeforeBox = new DateBox();
		dateBeforeBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateBeforeFilterBox = new ImogFilterBox(dateBeforeBox);
		dateBeforeFilterBox.setFilterLabel(NLS.constants()
				.examenMicroscopie_field_date()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(dateBeforeFilterBox);

		raisonDepistageBox = new ListBox();
		raisonDepistageBox.addItem("", BaseNLS.constants()
				.enumeration_unknown());
		raisonDepistageBox.setSelectedIndex(0);
		raisonDepistageBox
				.addItem(
						NLS.constants()
								.examenMicroscopie_raisonDepistage_diagnostic_option(),
						EpicamEnumConstants.EXAMENMICROSCOPIE_RAISONDEPISTAGE_DIAGNOSTIC);
		raisonDepistageBox.addItem(NLS.constants()
				.examenMicroscopie_raisonDepistage_suivi_option(),
				EpicamEnumConstants.EXAMENMICROSCOPIE_RAISONDEPISTAGE_SUIVI);
		raisonDepistageFilterBox = new ImogFilterBox(raisonDepistageBox);
		raisonDepistageFilterBox.setFilterLabel(NLS.constants()
				.examenMicroscopie_field_raisonDepistage());
		addTableFilter(raisonDepistageFilterBox);

		resultatBox = new ListBox();
		resultatBox.addItem("", BaseNLS.constants().enumeration_unknown());
		resultatBox.setSelectedIndex(0);
		resultatBox.addItem(NLS.constants()
				.examenMicroscopie_resultat_negatif_option(),
				EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_NEGATIF);
		resultatBox.addItem(NLS.constants()
				.examenMicroscopie_resultat_rare_option(),
				EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_RARE);
		resultatBox.addItem(NLS.constants()
				.examenMicroscopie_resultat_unPlus_option(),
				EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_UNPLUS);
		resultatBox.addItem(NLS.constants()
				.examenMicroscopie_resultat_deuxPlus_option(),
				EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_DEUXPLUS);
		resultatBox.addItem(NLS.constants()
				.examenMicroscopie_resultat_troisPlus_option(),
				EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_TROISPLUS);
		resultatFilterBox = new ImogFilterBox(resultatBox);
		resultatFilterBox.setFilterLabel(NLS.constants()
				.examenMicroscopie_field_resultat());
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

		if (dateBeforeBox.getValue() != null) {
			FilterCriteria dateBeforeCrit = new FilterCriteria();
			dateBeforeCrit.setField("date");
			dateBeforeCrit.setFieldDisplayName(NLS.constants()
					.examenMicroscopie_field_date()
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
					.examenMicroscopie_field_date()
					+ BaseNLS.constants().search_operator_superior());
			dateAfterCrit.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			dateAfterCrit.setValue(DateUtil.getDate(dateAfterBox.getValue()));
			dateAfterCrit.setValueDisplayName(DateUtil.getDate(dateAfterBox
					.getValue()));
			criteria.add(dateAfterCrit);
		}

		FilterCriteria raisonDepistageCrit = new FilterCriteria();
		raisonDepistageCrit.setField("raisonDepistage");
		raisonDepistageCrit.setFieldDisplayName(NLS.constants()
				.examenMicroscopie_field_raisonDepistage());
		raisonDepistageCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
		raisonDepistageCrit.setValue(raisonDepistageBox
				.getValue(raisonDepistageBox.getSelectedIndex()));
		raisonDepistageCrit.setValueDisplayName(EpicamRenderer.get()
				.getEnumDisplayValue(
						ExamenMicroscopieProxy.class,
						"raisonDepistage",
						raisonDepistageBox.getValue(raisonDepistageBox
								.getSelectedIndex())));
		criteria.add(raisonDepistageCrit);

		FilterCriteria resultatCrit = new FilterCriteria();
		resultatCrit.setField("resultat");
		resultatCrit.setFieldDisplayName(NLS.constants()
				.examenMicroscopie_field_resultat());
		resultatCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
		resultatCrit.setValue(resultatBox.getValue(resultatBox
				.getSelectedIndex()));
		resultatCrit.setValueDisplayName(EpicamRenderer.get()
				.getEnumDisplayValue(ExamenMicroscopieProxy.class, "resultat",
						resultatBox.getValue(resultatBox.getSelectedIndex())));
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

		if (!AccessManager.canReadExamenMicroscopieExamen()) {
			dateBeforeFilterBox.setVisible(false);
			dateAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.canReadExamenMicroscopieExamen())
			raisonDepistageFilterBox.setVisible(false);

		if (!AccessManager.canReadExamenMicroscopieExamen())
			resultatFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
