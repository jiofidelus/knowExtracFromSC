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
 * Filter panel to filter the HorsUsage entries
 * @author MEDES-IMPS
 */
public class HorsUsageFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox lot_lot_numeroBox;
	private ImogFilterBox lot_lot_numeroFilterBox;
	private TextBox lot_lot_intrant_identifiantBox;
	private ImogFilterBox lot_lot_intrant_identifiantFilterBox;
	private TextBox lot_lot_medicament_designationBox;
	private ImogFilterBox lot_lot_medicament_designationFilterBox;
	private IntegerBox lot_lot_quantiteBox;
	private ImogFilterBox lot_lot_quantiteFilterBox;
	private IntegerBox lot_quantiteBox;
	private ImogFilterBox lot_quantiteFilterBox;
	private ListBox typeBox;
	private ImogFilterBox typeFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public HorsUsageFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		lot_lot_numeroBox.setValue(null);
		lot_lot_intrant_identifiantBox.setValue(null);
		lot_lot_medicament_designationBox.setValue(null);
		lot_lot_quantiteBox.setValue(null);
		lot_quantiteBox.setValue(null);
		typeBox.setSelectedIndex(0);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		lot_lot_numeroBox = new TextBox();
		lot_lot_numeroFilterBox = new ImogFilterBox(lot_lot_numeroBox);
		lot_lot_numeroFilterBox.setFilterLabel(NLS.constants()
				.lot_field_numero());
		addTableFilter(lot_lot_numeroFilterBox);
		lot_lot_intrant_identifiantBox = new TextBox();
		lot_lot_intrant_identifiantFilterBox = new ImogFilterBox(
				lot_lot_intrant_identifiantBox);
		lot_lot_intrant_identifiantFilterBox.setFilterLabel(NLS.constants()
				.intrant_field_identifiant());
		addTableFilter(lot_lot_intrant_identifiantFilterBox);
		lot_lot_medicament_designationBox = new TextBox();
		lot_lot_medicament_designationFilterBox = new ImogFilterBox(
				lot_lot_medicament_designationBox);
		lot_lot_medicament_designationFilterBox.setFilterLabel(NLS.constants()
				.medicament_field_designation());
		addTableFilter(lot_lot_medicament_designationFilterBox);
		lot_lot_quantiteBox = new IntegerBox();
		lot_lot_quantiteFilterBox = new ImogFilterBox(lot_lot_quantiteBox);
		lot_lot_quantiteFilterBox.setFilterLabel(NLS.constants()
				.lot_field_quantite());
		addTableFilter(lot_lot_quantiteFilterBox);
		lot_quantiteBox = new IntegerBox();
		lot_quantiteFilterBox = new ImogFilterBox(lot_quantiteBox);
		lot_quantiteFilterBox.setFilterLabel(NLS.constants()
				.sortieLot_field_quantite());
		addTableFilter(lot_quantiteFilterBox);

		typeBox = new ListBox();
		typeBox.addItem("", BaseNLS.constants().enumeration_unknown());
		typeBox.setSelectedIndex(0);
		typeBox.addItem(NLS.constants().horsUsage_type_perimee_option(),
				EpicamEnumConstants.HORSUSAGE_TYPE_PERIMEE);
		typeBox.addItem(NLS.constants().horsUsage_type_casse_option(),
				EpicamEnumConstants.HORSUSAGE_TYPE_CASSE);
		typeFilterBox = new ImogFilterBox(typeBox);
		typeFilterBox.setFilterLabel(NLS.constants().horsUsage_field_type());
		addTableFilter(typeFilterBox);

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

		FilterCriteria lot_lot_numeroCrit = new FilterCriteria();
		lot_lot_numeroCrit.setField("lot.lot.numero");
		lot_lot_numeroCrit.setFieldDisplayName(NLS.constants()
				.lot_field_numero());
		lot_lot_numeroCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		lot_lot_numeroCrit.setValue(lot_lot_numeroBox.getValue());
		lot_lot_numeroCrit.setValueDisplayName(lot_lot_numeroBox.getValue());
		criteria.add(lot_lot_numeroCrit);
		FilterCriteria lot_lot_intrant_identifiantCrit = new FilterCriteria();
		lot_lot_intrant_identifiantCrit.setField("lot.lot.intrant.identifiant");
		lot_lot_intrant_identifiantCrit.setFieldDisplayName(NLS.constants()
				.intrant_field_identifiant());
		lot_lot_intrant_identifiantCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		lot_lot_intrant_identifiantCrit.setValue(lot_lot_intrant_identifiantBox
				.getValue());
		lot_lot_intrant_identifiantCrit
				.setValueDisplayName(lot_lot_intrant_identifiantBox.getValue());
		criteria.add(lot_lot_intrant_identifiantCrit);
		FilterCriteria lot_lot_medicament_designationCrit = new FilterCriteria();
		lot_lot_medicament_designationCrit
				.setField("lot.lot.medicament.designation");
		lot_lot_medicament_designationCrit.setFieldDisplayName(NLS.constants()
				.medicament_field_designation());
		lot_lot_medicament_designationCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		lot_lot_medicament_designationCrit
				.setValue(lot_lot_medicament_designationBox.getValue());
		lot_lot_medicament_designationCrit
				.setValueDisplayName(lot_lot_medicament_designationBox
						.getValue());
		criteria.add(lot_lot_medicament_designationCrit);
		FilterCriteria lot_lot_quantiteCrit = new FilterCriteria();
		lot_lot_quantiteCrit.setField("lot.lot.quantite");
		lot_lot_quantiteCrit.setFieldDisplayName(NLS.constants()
				.lot_field_quantite());
		lot_lot_quantiteCrit.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
		if (lot_lot_quantiteBox.getValue() == null)
			lot_lot_quantiteCrit.setValue(null);
		else
			lot_lot_quantiteCrit.setValue(String.valueOf(lot_lot_quantiteBox
					.getValue()));
		lot_lot_quantiteCrit.setValueDisplayName(String
				.valueOf(lot_lot_quantiteBox.getValue()));
		criteria.add(lot_lot_quantiteCrit);
		FilterCriteria lot_quantiteCrit = new FilterCriteria();
		lot_quantiteCrit.setField("lot.quantite");
		lot_quantiteCrit.setFieldDisplayName(NLS.constants()
				.sortieLot_field_quantite());
		lot_quantiteCrit.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
		if (lot_quantiteBox.getValue() == null)
			lot_quantiteCrit.setValue(null);
		else
			lot_quantiteCrit
					.setValue(String.valueOf(lot_quantiteBox.getValue()));
		lot_quantiteCrit.setValueDisplayName(String.valueOf(lot_quantiteBox
				.getValue()));
		criteria.add(lot_quantiteCrit);

		FilterCriteria typeCrit = new FilterCriteria();
		typeCrit.setField("type");
		typeCrit.setFieldDisplayName(NLS.constants().horsUsage_field_type());
		typeCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
		typeCrit.setValue(typeBox.getValue(typeBox.getSelectedIndex()));
		typeCrit.setValueDisplayName(EpicamRenderer.get().getEnumDisplayValue(
				HorsUsageProxy.class, "type",
				typeBox.getValue(typeBox.getSelectedIndex())));
		criteria.add(typeCrit);

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

		if (!AccessManager.canReadLotDescription())
			lot_lot_numeroFilterBox.setVisible(false);
		if (!AccessManager.canReadIntrantDescription())
			lot_lot_intrant_identifiantFilterBox.setVisible(false);
		if (!AccessManager.canReadMedicamentDescription())
			lot_lot_medicament_designationFilterBox.setVisible(false);
		if (!AccessManager.canReadLotDescription())
			lot_lot_quantiteFilterBox.setVisible(false);
		if (!AccessManager.canReadSortieLotDescription())
			lot_quantiteFilterBox.setVisible(false);

		if (!AccessManager.canReadHorsUsageDescription())
			typeFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
