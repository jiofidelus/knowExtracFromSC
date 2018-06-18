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
 * Filter panel to filter the Lot entries
 * @author MEDES-IMPS
 */
public class LotFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox cdt_nomBox;
	private ImogFilterBox cdt_nomFilterBox;
	private TextBox numeroBox;
	private ImogFilterBox numeroFilterBox;
	private IntegerBox quantiteBox;
	private ImogFilterBox quantiteFilterBox;
	private DateBox datePeremptionBeforeBox;
	private ImogFilterBox datePeremptionBeforeFilterBox;
	private DateBox datePeremptionAfterBox;
	private ImogFilterBox datePeremptionAfterFilterBox;
	private TextBox intrant_identifiantBox;
	private ImogFilterBox intrant_identifiantFilterBox;
	private TextBox medicament_designationBox;
	private ImogFilterBox medicament_designationFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public LotFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		cdt_nomBox.setValue(null);
		numeroBox.setValue(null);
		quantiteBox.setValue(null);
		datePeremptionBeforeBox.setValue(null);
		datePeremptionAfterBox.setValue(null);
		intrant_identifiantBox.setValue(null);
		medicament_designationBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		cdt_nomBox = new TextBox();
		cdt_nomFilterBox = new ImogFilterBox(cdt_nomBox);
		cdt_nomFilterBox.setFilterLabel(NLS.constants()
				.centreDiagTrait_field_nom());
		addTableFilter(cdt_nomFilterBox);

		numeroBox = new TextBox();
		numeroFilterBox = new ImogFilterBox(numeroBox);
		numeroFilterBox.setFilterLabel(NLS.constants().lot_field_numero());
		addTableFilter(numeroFilterBox);

		quantiteBox = new IntegerBox();
		quantiteFilterBox = new ImogFilterBox(quantiteBox);
		quantiteFilterBox.setFilterLabel(NLS.constants().lot_field_quantite());
		addTableFilter(quantiteFilterBox);

		datePeremptionAfterBox = new DateBox();
		datePeremptionAfterBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		datePeremptionAfterFilterBox = new ImogFilterBox(datePeremptionAfterBox);
		datePeremptionAfterFilterBox.setFilterLabel(NLS.constants()
				.lot_field_datePeremption()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(datePeremptionAfterFilterBox);

		datePeremptionBeforeBox = new DateBox();
		datePeremptionBeforeBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		datePeremptionBeforeFilterBox = new ImogFilterBox(
				datePeremptionBeforeBox);
		datePeremptionBeforeFilterBox.setFilterLabel(NLS.constants()
				.lot_field_datePeremption()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(datePeremptionBeforeFilterBox);

		intrant_identifiantBox = new TextBox();
		intrant_identifiantFilterBox = new ImogFilterBox(intrant_identifiantBox);
		intrant_identifiantFilterBox.setFilterLabel(NLS.constants()
				.intrant_field_identifiant());
		addTableFilter(intrant_identifiantFilterBox);

		medicament_designationBox = new TextBox();
		medicament_designationFilterBox = new ImogFilterBox(
				medicament_designationBox);
		medicament_designationFilterBox.setFilterLabel(NLS.constants()
				.medicament_field_designation());
		addTableFilter(medicament_designationFilterBox);

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

		FilterCriteria cdt_nomCrit = new FilterCriteria();
		cdt_nomCrit.setField("cDT.nom");
		cdt_nomCrit.setFieldDisplayName(NLS.constants()
				.centreDiagTrait_field_nom());
		cdt_nomCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		cdt_nomCrit.setValue(cdt_nomBox.getValue());
		cdt_nomCrit.setValueDisplayName(cdt_nomBox.getValue());
		criteria.add(cdt_nomCrit);

		FilterCriteria numeroCrit = new FilterCriteria();
		numeroCrit.setField("numero");
		numeroCrit.setFieldDisplayName(NLS.constants().lot_field_numero());
		numeroCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		numeroCrit.setValue(numeroBox.getValue());
		numeroCrit.setValueDisplayName(numeroBox.getValue());
		criteria.add(numeroCrit);

		FilterCriteria quantiteCrit = new FilterCriteria();
		quantiteCrit.setField("quantite");
		quantiteCrit.setFieldDisplayName(NLS.constants().lot_field_quantite());
		quantiteCrit.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
		if (quantiteBox.getValue() == null)
			quantiteCrit.setValue(null);
		else
			quantiteCrit.setValue(String.valueOf(quantiteBox.getValue()));
		quantiteCrit
				.setValueDisplayName(String.valueOf(quantiteBox.getValue()));
		criteria.add(quantiteCrit);

		if (datePeremptionBeforeBox.getValue() != null) {
			FilterCriteria datePeremptionBeforeCrit = new FilterCriteria();
			datePeremptionBeforeCrit.setField("datePeremption");
			datePeremptionBeforeCrit.setFieldDisplayName(NLS.constants()
					.lot_field_datePeremption()
					+ BaseNLS.constants().search_operator_inferior());
			datePeremptionBeforeCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			datePeremptionBeforeCrit.setValue(DateUtil
					.getDate(datePeremptionBeforeBox.getValue()));
			datePeremptionBeforeCrit.setValueDisplayName(DateUtil
					.getDate(datePeremptionBeforeBox.getValue()));
			criteria.add(datePeremptionBeforeCrit);
		}

		if (datePeremptionAfterBox.getValue() != null) {
			FilterCriteria datePeremptionAfterCrit = new FilterCriteria();
			datePeremptionAfterCrit.setField("datePeremption");
			datePeremptionAfterCrit.setFieldDisplayName(NLS.constants()
					.lot_field_datePeremption()
					+ BaseNLS.constants().search_operator_superior());
			datePeremptionAfterCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			datePeremptionAfterCrit.setValue(DateUtil
					.getDate(datePeremptionAfterBox.getValue()));
			datePeremptionAfterCrit.setValueDisplayName(DateUtil
					.getDate(datePeremptionAfterBox.getValue()));
			criteria.add(datePeremptionAfterCrit);
		}

		FilterCriteria intrant_identifiantCrit = new FilterCriteria();
		intrant_identifiantCrit.setField("intrant.identifiant");
		intrant_identifiantCrit.setFieldDisplayName(NLS.constants()
				.intrant_field_identifiant());
		intrant_identifiantCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		intrant_identifiantCrit.setValue(intrant_identifiantBox.getValue());
		intrant_identifiantCrit.setValueDisplayName(intrant_identifiantBox
				.getValue());
		criteria.add(intrant_identifiantCrit);

		FilterCriteria medicament_designationCrit = new FilterCriteria();
		medicament_designationCrit.setField("medicament.designation");
		medicament_designationCrit.setFieldDisplayName(NLS.constants()
				.medicament_field_designation());
		medicament_designationCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		medicament_designationCrit.setValue(medicament_designationBox
				.getValue());
		medicament_designationCrit
				.setValueDisplayName(medicament_designationBox.getValue());
		criteria.add(medicament_designationCrit);

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

		if (!AccessManager.canReadCentreDiagTraitDescription())
			cdt_nomFilterBox.setVisible(false);

		if (!AccessManager.canReadLotDescription())
			numeroFilterBox.setVisible(false);

		if (!AccessManager.canReadLotDescription())
			quantiteFilterBox.setVisible(false);

		if (!AccessManager.canReadLotDescription()) {
			datePeremptionBeforeFilterBox.setVisible(false);
			datePeremptionAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.canReadIntrantDescription())
			intrant_identifiantFilterBox.setVisible(false);

		if (!AccessManager.canReadMedicamentDescription())
			medicament_designationFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
