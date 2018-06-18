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
 * Filter panel to filter the DetailRavitaillement entries
 * @author MEDES-IMPS
 */
public class DetailRavitaillementFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox ravitaillement_cdtdepart_nomBox;
	private ImogFilterBox ravitaillement_cdtdepart_nomFilterBox;
	private DateBox ravitaillement_dateDepartBeforeBox;
	private ImogFilterBox ravitaillement_dateDepartBeforeFilterBox;
	private DateBox ravitaillement_dateDepartAfterBox;
	private ImogFilterBox ravitaillement_dateDepartAfterFilterBox;
	private TextBox ravitaillement_cdtarrivee_nomBox;
	private ImogFilterBox ravitaillement_cdtarrivee_nomFilterBox;
	private DateBox ravitaillement_dateArriveeBeforeBox;
	private ImogFilterBox ravitaillement_dateArriveeBeforeFilterBox;
	private DateBox ravitaillement_dateArriveeAfterBox;
	private ImogFilterBox ravitaillement_dateArriveeAfterFilterBox;
	private TextBox sortielot_lot_numeroBox;
	private ImogFilterBox sortielot_lot_numeroFilterBox;
	private TextBox sortielot_lot_intrant_identifiantBox;
	private ImogFilterBox sortielot_lot_intrant_identifiantFilterBox;
	private TextBox sortielot_lot_medicament_designationBox;
	private ImogFilterBox sortielot_lot_medicament_designationFilterBox;
	private IntegerBox sortielot_lot_quantiteBox;
	private ImogFilterBox sortielot_lot_quantiteFilterBox;
	private IntegerBox sortielot_quantiteBox;
	private ImogFilterBox sortielot_quantiteFilterBox;
	private TextBox entreelot_lot_numeroBox;
	private ImogFilterBox entreelot_lot_numeroFilterBox;
	private TextBox entreelot_lot_intrant_identifiantBox;
	private ImogFilterBox entreelot_lot_intrant_identifiantFilterBox;
	private TextBox entreelot_lot_medicament_designationBox;
	private ImogFilterBox entreelot_lot_medicament_designationFilterBox;
	private IntegerBox entreelot_lot_quantiteBox;
	private ImogFilterBox entreelot_lot_quantiteFilterBox;
	private IntegerBox entreelot_quantiteBox;
	private ImogFilterBox entreelot_quantiteFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public DetailRavitaillementFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		ravitaillement_cdtdepart_nomBox.setValue(null);
		ravitaillement_dateDepartBeforeBox.setValue(null);
		ravitaillement_dateDepartAfterBox.setValue(null);
		ravitaillement_cdtarrivee_nomBox.setValue(null);
		ravitaillement_dateArriveeBeforeBox.setValue(null);
		ravitaillement_dateArriveeAfterBox.setValue(null);
		sortielot_lot_numeroBox.setValue(null);
		sortielot_lot_intrant_identifiantBox.setValue(null);
		sortielot_lot_medicament_designationBox.setValue(null);
		sortielot_lot_quantiteBox.setValue(null);
		sortielot_quantiteBox.setValue(null);
		entreelot_lot_numeroBox.setValue(null);
		entreelot_lot_intrant_identifiantBox.setValue(null);
		entreelot_lot_medicament_designationBox.setValue(null);
		entreelot_lot_quantiteBox.setValue(null);
		entreelot_quantiteBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		ravitaillement_cdtdepart_nomBox = new TextBox();
		ravitaillement_cdtdepart_nomFilterBox = new ImogFilterBox(
				ravitaillement_cdtdepart_nomBox);
		ravitaillement_cdtdepart_nomFilterBox.setFilterLabel(NLS.constants()
				.centreDiagTrait_field_nom());
		addTableFilter(ravitaillement_cdtdepart_nomFilterBox);
		ravitaillement_dateDepartAfterBox = new DateBox();
		ravitaillement_dateDepartAfterBox.setFormat(new SimpleImogDateFormat(
				DateUtil.getDateFormater()));
		ravitaillement_dateDepartAfterFilterBox = new ImogFilterBox(
				ravitaillement_dateDepartAfterBox);
		ravitaillement_dateDepartAfterFilterBox.setFilterLabel(NLS.constants()
				.ravitaillement_field_dateDepart()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(ravitaillement_dateDepartAfterFilterBox);

		ravitaillement_dateDepartBeforeBox = new DateBox();
		ravitaillement_dateDepartBeforeBox.setFormat(new SimpleImogDateFormat(
				DateUtil.getDateFormater()));
		ravitaillement_dateDepartBeforeFilterBox = new ImogFilterBox(
				ravitaillement_dateDepartBeforeBox);
		ravitaillement_dateDepartBeforeFilterBox.setFilterLabel(NLS.constants()
				.ravitaillement_field_dateDepart()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(ravitaillement_dateDepartBeforeFilterBox);
		ravitaillement_cdtarrivee_nomBox = new TextBox();
		ravitaillement_cdtarrivee_nomFilterBox = new ImogFilterBox(
				ravitaillement_cdtarrivee_nomBox);
		ravitaillement_cdtarrivee_nomFilterBox.setFilterLabel(NLS.constants()
				.centreDiagTrait_field_nom());
		addTableFilter(ravitaillement_cdtarrivee_nomFilterBox);
		ravitaillement_dateArriveeAfterBox = new DateBox();
		ravitaillement_dateArriveeAfterBox.setFormat(new SimpleImogDateFormat(
				DateUtil.getDateFormater()));
		ravitaillement_dateArriveeAfterFilterBox = new ImogFilterBox(
				ravitaillement_dateArriveeAfterBox);
		ravitaillement_dateArriveeAfterFilterBox.setFilterLabel(NLS.constants()
				.ravitaillement_field_dateArrivee()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(ravitaillement_dateArriveeAfterFilterBox);

		ravitaillement_dateArriveeBeforeBox = new DateBox();
		ravitaillement_dateArriveeBeforeBox.setFormat(new SimpleImogDateFormat(
				DateUtil.getDateFormater()));
		ravitaillement_dateArriveeBeforeFilterBox = new ImogFilterBox(
				ravitaillement_dateArriveeBeforeBox);
		ravitaillement_dateArriveeBeforeFilterBox.setFilterLabel(NLS
				.constants().ravitaillement_field_dateArrivee()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(ravitaillement_dateArriveeBeforeFilterBox);

		sortielot_lot_numeroBox = new TextBox();
		sortielot_lot_numeroFilterBox = new ImogFilterBox(
				sortielot_lot_numeroBox);
		sortielot_lot_numeroFilterBox.setFilterLabel(NLS.constants()
				.lot_field_numero());
		addTableFilter(sortielot_lot_numeroFilterBox);
		sortielot_lot_intrant_identifiantBox = new TextBox();
		sortielot_lot_intrant_identifiantFilterBox = new ImogFilterBox(
				sortielot_lot_intrant_identifiantBox);
		sortielot_lot_intrant_identifiantFilterBox.setFilterLabel(NLS
				.constants().intrant_field_identifiant());
		addTableFilter(sortielot_lot_intrant_identifiantFilterBox);
		sortielot_lot_medicament_designationBox = new TextBox();
		sortielot_lot_medicament_designationFilterBox = new ImogFilterBox(
				sortielot_lot_medicament_designationBox);
		sortielot_lot_medicament_designationFilterBox.setFilterLabel(NLS
				.constants().medicament_field_designation());
		addTableFilter(sortielot_lot_medicament_designationFilterBox);
		sortielot_lot_quantiteBox = new IntegerBox();
		sortielot_lot_quantiteFilterBox = new ImogFilterBox(
				sortielot_lot_quantiteBox);
		sortielot_lot_quantiteFilterBox.setFilterLabel(NLS.constants()
				.lot_field_quantite());
		addTableFilter(sortielot_lot_quantiteFilterBox);
		sortielot_quantiteBox = new IntegerBox();
		sortielot_quantiteFilterBox = new ImogFilterBox(sortielot_quantiteBox);
		sortielot_quantiteFilterBox.setFilterLabel(NLS.constants()
				.sortieLot_field_quantite());
		addTableFilter(sortielot_quantiteFilterBox);

		entreelot_lot_numeroBox = new TextBox();
		entreelot_lot_numeroFilterBox = new ImogFilterBox(
				entreelot_lot_numeroBox);
		entreelot_lot_numeroFilterBox.setFilterLabel(NLS.constants()
				.lot_field_numero());
		addTableFilter(entreelot_lot_numeroFilterBox);
		entreelot_lot_intrant_identifiantBox = new TextBox();
		entreelot_lot_intrant_identifiantFilterBox = new ImogFilterBox(
				entreelot_lot_intrant_identifiantBox);
		entreelot_lot_intrant_identifiantFilterBox.setFilterLabel(NLS
				.constants().intrant_field_identifiant());
		addTableFilter(entreelot_lot_intrant_identifiantFilterBox);
		entreelot_lot_medicament_designationBox = new TextBox();
		entreelot_lot_medicament_designationFilterBox = new ImogFilterBox(
				entreelot_lot_medicament_designationBox);
		entreelot_lot_medicament_designationFilterBox.setFilterLabel(NLS
				.constants().medicament_field_designation());
		addTableFilter(entreelot_lot_medicament_designationFilterBox);
		entreelot_lot_quantiteBox = new IntegerBox();
		entreelot_lot_quantiteFilterBox = new ImogFilterBox(
				entreelot_lot_quantiteBox);
		entreelot_lot_quantiteFilterBox.setFilterLabel(NLS.constants()
				.lot_field_quantite());
		addTableFilter(entreelot_lot_quantiteFilterBox);
		entreelot_quantiteBox = new IntegerBox();
		entreelot_quantiteFilterBox = new ImogFilterBox(entreelot_quantiteBox);
		entreelot_quantiteFilterBox.setFilterLabel(NLS.constants()
				.entreeLot_field_quantite());
		addTableFilter(entreelot_quantiteFilterBox);

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

		FilterCriteria ravitaillement_cdtdepart_nomCrit = new FilterCriteria();
		ravitaillement_cdtdepart_nomCrit
				.setField("ravitaillement.cDTDepart.nom");
		ravitaillement_cdtdepart_nomCrit.setFieldDisplayName(NLS.constants()
				.centreDiagTrait_field_nom());
		ravitaillement_cdtdepart_nomCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		ravitaillement_cdtdepart_nomCrit
				.setValue(ravitaillement_cdtdepart_nomBox.getValue());
		ravitaillement_cdtdepart_nomCrit
				.setValueDisplayName(ravitaillement_cdtdepart_nomBox.getValue());
		criteria.add(ravitaillement_cdtdepart_nomCrit);

		if (ravitaillement_dateDepartBeforeBox.getValue() != null) {
			FilterCriteria ravitaillement_dateDepartBeforeCrit = new FilterCriteria();
			ravitaillement_dateDepartBeforeCrit
					.setField("ravitaillement.dateDepart");
			ravitaillement_dateDepartBeforeCrit.setFieldDisplayName(NLS
					.constants().ravitaillement_field_dateDepart()
					+ BaseNLS.constants().search_operator_inferior());
			ravitaillement_dateDepartBeforeCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			ravitaillement_dateDepartBeforeCrit.setValue(DateUtil
					.getDate(ravitaillement_dateDepartBeforeBox.getValue()));
			ravitaillement_dateDepartBeforeCrit.setValueDisplayName(DateUtil
					.getDate(ravitaillement_dateDepartBeforeBox.getValue()));
			criteria.add(ravitaillement_dateDepartBeforeCrit);
		}

		if (ravitaillement_dateDepartAfterBox.getValue() != null) {
			FilterCriteria ravitaillement_dateDepartAfterCrit = new FilterCriteria();
			ravitaillement_dateDepartAfterCrit
					.setField("ravitaillement.dateDepart");
			ravitaillement_dateDepartAfterCrit.setFieldDisplayName(NLS
					.constants().ravitaillement_field_dateDepart()
					+ BaseNLS.constants().search_operator_superior());
			ravitaillement_dateDepartAfterCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			ravitaillement_dateDepartAfterCrit.setValue(DateUtil
					.getDate(ravitaillement_dateDepartAfterBox.getValue()));
			ravitaillement_dateDepartAfterCrit.setValueDisplayName(DateUtil
					.getDate(ravitaillement_dateDepartAfterBox.getValue()));
			criteria.add(ravitaillement_dateDepartAfterCrit);
		}
		FilterCriteria ravitaillement_cdtarrivee_nomCrit = new FilterCriteria();
		ravitaillement_cdtarrivee_nomCrit
				.setField("ravitaillement.cDTArrivee.nom");
		ravitaillement_cdtarrivee_nomCrit.setFieldDisplayName(NLS.constants()
				.centreDiagTrait_field_nom());
		ravitaillement_cdtarrivee_nomCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		ravitaillement_cdtarrivee_nomCrit
				.setValue(ravitaillement_cdtarrivee_nomBox.getValue());
		ravitaillement_cdtarrivee_nomCrit
				.setValueDisplayName(ravitaillement_cdtarrivee_nomBox
						.getValue());
		criteria.add(ravitaillement_cdtarrivee_nomCrit);

		if (ravitaillement_dateArriveeBeforeBox.getValue() != null) {
			FilterCriteria ravitaillement_dateArriveeBeforeCrit = new FilterCriteria();
			ravitaillement_dateArriveeBeforeCrit
					.setField("ravitaillement.dateArrivee");
			ravitaillement_dateArriveeBeforeCrit.setFieldDisplayName(NLS
					.constants().ravitaillement_field_dateArrivee()
					+ BaseNLS.constants().search_operator_inferior());
			ravitaillement_dateArriveeBeforeCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			ravitaillement_dateArriveeBeforeCrit.setValue(DateUtil
					.getDate(ravitaillement_dateArriveeBeforeBox.getValue()));
			ravitaillement_dateArriveeBeforeCrit.setValueDisplayName(DateUtil
					.getDate(ravitaillement_dateArriveeBeforeBox.getValue()));
			criteria.add(ravitaillement_dateArriveeBeforeCrit);
		}

		if (ravitaillement_dateArriveeAfterBox.getValue() != null) {
			FilterCriteria ravitaillement_dateArriveeAfterCrit = new FilterCriteria();
			ravitaillement_dateArriveeAfterCrit
					.setField("ravitaillement.dateArrivee");
			ravitaillement_dateArriveeAfterCrit.setFieldDisplayName(NLS
					.constants().ravitaillement_field_dateArrivee()
					+ BaseNLS.constants().search_operator_superior());
			ravitaillement_dateArriveeAfterCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			ravitaillement_dateArriveeAfterCrit.setValue(DateUtil
					.getDate(ravitaillement_dateArriveeAfterBox.getValue()));
			ravitaillement_dateArriveeAfterCrit.setValueDisplayName(DateUtil
					.getDate(ravitaillement_dateArriveeAfterBox.getValue()));
			criteria.add(ravitaillement_dateArriveeAfterCrit);
		}

		FilterCriteria sortielot_lot_numeroCrit = new FilterCriteria();
		sortielot_lot_numeroCrit.setField("sortieLot.lot.numero");
		sortielot_lot_numeroCrit.setFieldDisplayName(NLS.constants()
				.lot_field_numero());
		sortielot_lot_numeroCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		sortielot_lot_numeroCrit.setValue(sortielot_lot_numeroBox.getValue());
		sortielot_lot_numeroCrit.setValueDisplayName(sortielot_lot_numeroBox
				.getValue());
		criteria.add(sortielot_lot_numeroCrit);
		FilterCriteria sortielot_lot_intrant_identifiantCrit = new FilterCriteria();
		sortielot_lot_intrant_identifiantCrit
				.setField("sortieLot.lot.intrant.identifiant");
		sortielot_lot_intrant_identifiantCrit.setFieldDisplayName(NLS
				.constants().intrant_field_identifiant());
		sortielot_lot_intrant_identifiantCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		sortielot_lot_intrant_identifiantCrit
				.setValue(sortielot_lot_intrant_identifiantBox.getValue());
		sortielot_lot_intrant_identifiantCrit
				.setValueDisplayName(sortielot_lot_intrant_identifiantBox
						.getValue());
		criteria.add(sortielot_lot_intrant_identifiantCrit);
		FilterCriteria sortielot_lot_medicament_designationCrit = new FilterCriteria();
		sortielot_lot_medicament_designationCrit
				.setField("sortieLot.lot.medicament.designation");
		sortielot_lot_medicament_designationCrit.setFieldDisplayName(NLS
				.constants().medicament_field_designation());
		sortielot_lot_medicament_designationCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		sortielot_lot_medicament_designationCrit
				.setValue(sortielot_lot_medicament_designationBox.getValue());
		sortielot_lot_medicament_designationCrit
				.setValueDisplayName(sortielot_lot_medicament_designationBox
						.getValue());
		criteria.add(sortielot_lot_medicament_designationCrit);
		FilterCriteria sortielot_lot_quantiteCrit = new FilterCriteria();
		sortielot_lot_quantiteCrit.setField("sortieLot.lot.quantite");
		sortielot_lot_quantiteCrit.setFieldDisplayName(NLS.constants()
				.lot_field_quantite());
		sortielot_lot_quantiteCrit
				.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
		if (sortielot_lot_quantiteBox.getValue() == null)
			sortielot_lot_quantiteCrit.setValue(null);
		else
			sortielot_lot_quantiteCrit.setValue(String
					.valueOf(sortielot_lot_quantiteBox.getValue()));
		sortielot_lot_quantiteCrit.setValueDisplayName(String
				.valueOf(sortielot_lot_quantiteBox.getValue()));
		criteria.add(sortielot_lot_quantiteCrit);
		FilterCriteria sortielot_quantiteCrit = new FilterCriteria();
		sortielot_quantiteCrit.setField("sortieLot.quantite");
		sortielot_quantiteCrit.setFieldDisplayName(NLS.constants()
				.sortieLot_field_quantite());
		sortielot_quantiteCrit
				.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
		if (sortielot_quantiteBox.getValue() == null)
			sortielot_quantiteCrit.setValue(null);
		else
			sortielot_quantiteCrit.setValue(String
					.valueOf(sortielot_quantiteBox.getValue()));
		sortielot_quantiteCrit.setValueDisplayName(String
				.valueOf(sortielot_quantiteBox.getValue()));
		criteria.add(sortielot_quantiteCrit);

		FilterCriteria entreelot_lot_numeroCrit = new FilterCriteria();
		entreelot_lot_numeroCrit.setField("entreeLot.lot.numero");
		entreelot_lot_numeroCrit.setFieldDisplayName(NLS.constants()
				.lot_field_numero());
		entreelot_lot_numeroCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		entreelot_lot_numeroCrit.setValue(entreelot_lot_numeroBox.getValue());
		entreelot_lot_numeroCrit.setValueDisplayName(entreelot_lot_numeroBox
				.getValue());
		criteria.add(entreelot_lot_numeroCrit);
		FilterCriteria entreelot_lot_intrant_identifiantCrit = new FilterCriteria();
		entreelot_lot_intrant_identifiantCrit
				.setField("entreeLot.lot.intrant.identifiant");
		entreelot_lot_intrant_identifiantCrit.setFieldDisplayName(NLS
				.constants().intrant_field_identifiant());
		entreelot_lot_intrant_identifiantCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		entreelot_lot_intrant_identifiantCrit
				.setValue(entreelot_lot_intrant_identifiantBox.getValue());
		entreelot_lot_intrant_identifiantCrit
				.setValueDisplayName(entreelot_lot_intrant_identifiantBox
						.getValue());
		criteria.add(entreelot_lot_intrant_identifiantCrit);
		FilterCriteria entreelot_lot_medicament_designationCrit = new FilterCriteria();
		entreelot_lot_medicament_designationCrit
				.setField("entreeLot.lot.medicament.designation");
		entreelot_lot_medicament_designationCrit.setFieldDisplayName(NLS
				.constants().medicament_field_designation());
		entreelot_lot_medicament_designationCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		entreelot_lot_medicament_designationCrit
				.setValue(entreelot_lot_medicament_designationBox.getValue());
		entreelot_lot_medicament_designationCrit
				.setValueDisplayName(entreelot_lot_medicament_designationBox
						.getValue());
		criteria.add(entreelot_lot_medicament_designationCrit);
		FilterCriteria entreelot_lot_quantiteCrit = new FilterCriteria();
		entreelot_lot_quantiteCrit.setField("entreeLot.lot.quantite");
		entreelot_lot_quantiteCrit.setFieldDisplayName(NLS.constants()
				.lot_field_quantite());
		entreelot_lot_quantiteCrit
				.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
		if (entreelot_lot_quantiteBox.getValue() == null)
			entreelot_lot_quantiteCrit.setValue(null);
		else
			entreelot_lot_quantiteCrit.setValue(String
					.valueOf(entreelot_lot_quantiteBox.getValue()));
		entreelot_lot_quantiteCrit.setValueDisplayName(String
				.valueOf(entreelot_lot_quantiteBox.getValue()));
		criteria.add(entreelot_lot_quantiteCrit);
		FilterCriteria entreelot_quantiteCrit = new FilterCriteria();
		entreelot_quantiteCrit.setField("entreeLot.quantite");
		entreelot_quantiteCrit.setFieldDisplayName(NLS.constants()
				.entreeLot_field_quantite());
		entreelot_quantiteCrit
				.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
		if (entreelot_quantiteBox.getValue() == null)
			entreelot_quantiteCrit.setValue(null);
		else
			entreelot_quantiteCrit.setValue(String
					.valueOf(entreelot_quantiteBox.getValue()));
		entreelot_quantiteCrit.setValueDisplayName(String
				.valueOf(entreelot_quantiteBox.getValue()));
		criteria.add(entreelot_quantiteCrit);

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
			ravitaillement_cdtdepart_nomFilterBox.setVisible(false);
		if (!AccessManager.canReadRavitaillementInformationsDepart()) {
			ravitaillement_dateDepartBeforeFilterBox.setVisible(false);
			ravitaillement_dateDepartAfterFilterBox.setVisible(false);
		}
		if (!AccessManager.canReadCentreDiagTraitDescription())
			ravitaillement_cdtarrivee_nomFilterBox.setVisible(false);
		if (!AccessManager.canReadRavitaillementInformationArrivee()) {
			ravitaillement_dateArriveeBeforeFilterBox.setVisible(false);
			ravitaillement_dateArriveeAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.canReadLotDescription())
			sortielot_lot_numeroFilterBox.setVisible(false);
		if (!AccessManager.canReadIntrantDescription())
			sortielot_lot_intrant_identifiantFilterBox.setVisible(false);
		if (!AccessManager.canReadMedicamentDescription())
			sortielot_lot_medicament_designationFilterBox.setVisible(false);
		if (!AccessManager.canReadLotDescription())
			sortielot_lot_quantiteFilterBox.setVisible(false);
		if (!AccessManager.canReadSortieLotDescription())
			sortielot_quantiteFilterBox.setVisible(false);

		if (!AccessManager.canReadLotDescription())
			entreelot_lot_numeroFilterBox.setVisible(false);
		if (!AccessManager.canReadIntrantDescription())
			entreelot_lot_intrant_identifiantFilterBox.setVisible(false);
		if (!AccessManager.canReadMedicamentDescription())
			entreelot_lot_medicament_designationFilterBox.setVisible(false);
		if (!AccessManager.canReadLotDescription())
			entreelot_lot_quantiteFilterBox.setVisible(false);
		if (!AccessManager.canReadEntreeLotDescription())
			entreelot_quantiteFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
