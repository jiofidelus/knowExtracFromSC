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
 * Filter panel to filter the ExamenSerologie entries
 * @author MEDES-IMPS
 */
public class ExamenSerologieFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox patient_identifiantBox;
	private ImogFilterBox patient_identifiantFilterBox;
	private TextBox patient_nomBox;
	private ImogFilterBox patient_nomFilterBox;
	private DateBox dateTestBeforeBox;
	private ImogFilterBox dateTestBeforeFilterBox;
	private DateBox dateTestAfterBox;
	private ImogFilterBox dateTestAfterFilterBox;
	private ListBox natureBox;
	private ImogFilterBox natureFilterBox;
	private IntegerBox resultatCD4Box;
	private ImogFilterBox resultatCD4FilterBox;
	private ListBox resultatVIHBox;
	private ImogFilterBox resultatVIHFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public ExamenSerologieFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		patient_identifiantBox.setValue(null);
		patient_nomBox.setValue(null);
		dateTestBeforeBox.setValue(null);
		dateTestAfterBox.setValue(null);
		natureBox.setSelectedIndex(0);
		resultatCD4Box.setValue(null);
		resultatVIHBox.setSelectedIndex(0);

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

		dateTestAfterBox = new DateBox();
		dateTestAfterBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateTestAfterFilterBox = new ImogFilterBox(dateTestAfterBox);
		dateTestAfterFilterBox.setFilterLabel(NLS.constants()
				.examenSerologie_field_dateTest()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(dateTestAfterFilterBox);

		dateTestBeforeBox = new DateBox();
		dateTestBeforeBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateTestBeforeFilterBox = new ImogFilterBox(dateTestBeforeBox);
		dateTestBeforeFilterBox.setFilterLabel(NLS.constants()
				.examenSerologie_field_dateTest()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(dateTestBeforeFilterBox);

		natureBox = new ListBox();
		natureBox.addItem("", BaseNLS.constants().enumeration_unknown());
		natureBox.setSelectedIndex(0);
		natureBox.addItem(NLS.constants().examenSerologie_nature_vIH_option(),
				EpicamEnumConstants.EXAMENSEROLOGIE_NATURE_VIH);
		natureBox.addItem(NLS.constants().examenSerologie_nature_cD4_option(),
				EpicamEnumConstants.EXAMENSEROLOGIE_NATURE_CD4);
		natureFilterBox = new ImogFilterBox(natureBox);
		natureFilterBox.setFilterLabel(NLS.constants()
				.examenSerologie_field_nature());
		addTableFilter(natureFilterBox);

		resultatCD4Box = new IntegerBox();
		resultatCD4FilterBox = new ImogFilterBox(resultatCD4Box);
		resultatCD4FilterBox.setFilterLabel(NLS.constants()
				.examenSerologie_field_resultatCD4());
		addTableFilter(resultatCD4FilterBox);

		resultatVIHBox = new ListBox();
		resultatVIHBox.addItem("", BaseNLS.constants().enumeration_unknown());
		resultatVIHBox.setSelectedIndex(0);
		resultatVIHBox.addItem(NLS.constants()
				.examenSerologie_resultatVIH_positif_option(),
				EpicamEnumConstants.EXAMENSEROLOGIE_RESULTATVIH_POSITIF);
		resultatVIHBox.addItem(NLS.constants()
				.examenSerologie_resultatVIH_negatif_option(),
				EpicamEnumConstants.EXAMENSEROLOGIE_RESULTATVIH_NEGATIF);
		resultatVIHFilterBox = new ImogFilterBox(resultatVIHBox);
		resultatVIHFilterBox.setFilterLabel(NLS.constants()
				.examenSerologie_field_resultatVIH());
		addTableFilter(resultatVIHFilterBox);

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

		if (dateTestBeforeBox.getValue() != null) {
			FilterCriteria dateTestBeforeCrit = new FilterCriteria();
			dateTestBeforeCrit.setField("dateTest");
			dateTestBeforeCrit.setFieldDisplayName(NLS.constants()
					.examenSerologie_field_dateTest()
					+ BaseNLS.constants().search_operator_inferior());
			dateTestBeforeCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			dateTestBeforeCrit.setValue(DateUtil.getDate(dateTestBeforeBox
					.getValue()));
			dateTestBeforeCrit.setValueDisplayName(DateUtil
					.getDate(dateTestBeforeBox.getValue()));
			criteria.add(dateTestBeforeCrit);
		}

		if (dateTestAfterBox.getValue() != null) {
			FilterCriteria dateTestAfterCrit = new FilterCriteria();
			dateTestAfterCrit.setField("dateTest");
			dateTestAfterCrit.setFieldDisplayName(NLS.constants()
					.examenSerologie_field_dateTest()
					+ BaseNLS.constants().search_operator_superior());
			dateTestAfterCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			dateTestAfterCrit.setValue(DateUtil.getDate(dateTestAfterBox
					.getValue()));
			dateTestAfterCrit.setValueDisplayName(DateUtil
					.getDate(dateTestAfterBox.getValue()));
			criteria.add(dateTestAfterCrit);
		}

		FilterCriteria natureCrit = new FilterCriteria();
		natureCrit.setField("nature");
		natureCrit.setFieldDisplayName(NLS.constants()
				.examenSerologie_field_nature());
		natureCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
		natureCrit.setValue(natureBox.getValue(natureBox.getSelectedIndex()));
		natureCrit.setValueDisplayName(EpicamRenderer.get()
				.getEnumDisplayValue(ExamenSerologieProxy.class, "nature",
						natureBox.getValue(natureBox.getSelectedIndex())));
		criteria.add(natureCrit);

		FilterCriteria resultatCD4Crit = new FilterCriteria();
		resultatCD4Crit.setField("resultatCD4");
		resultatCD4Crit.setFieldDisplayName(NLS.constants()
				.examenSerologie_field_resultatCD4());
		resultatCD4Crit.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
		if (resultatCD4Box.getValue() == null)
			resultatCD4Crit.setValue(null);
		else
			resultatCD4Crit.setValue(String.valueOf(resultatCD4Box.getValue()));
		resultatCD4Crit.setValueDisplayName(String.valueOf(resultatCD4Box
				.getValue()));
		criteria.add(resultatCD4Crit);

		FilterCriteria resultatVIHCrit = new FilterCriteria();
		resultatVIHCrit.setField("resultatVIH");
		resultatVIHCrit.setFieldDisplayName(NLS.constants()
				.examenSerologie_field_resultatVIH());
		resultatVIHCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
		resultatVIHCrit.setValue(resultatVIHBox.getValue(resultatVIHBox
				.getSelectedIndex()));
		resultatVIHCrit.setValueDisplayName(EpicamRenderer.get()
				.getEnumDisplayValue(
						ExamenSerologieProxy.class,
						"resultatVIH",
						resultatVIHBox.getValue(resultatVIHBox
								.getSelectedIndex())));
		criteria.add(resultatVIHCrit);

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

		if (!AccessManager.canReadExamenSerologieDescription()) {
			dateTestBeforeFilterBox.setVisible(false);
			dateTestAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.canReadExamenSerologieDescription())
			natureFilterBox.setVisible(false);

		if (!AccessManager.canReadExamenSerologieDescription())
			resultatCD4FilterBox.setVisible(false);

		if (!AccessManager.canReadExamenSerologieDescription())
			resultatVIHFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
