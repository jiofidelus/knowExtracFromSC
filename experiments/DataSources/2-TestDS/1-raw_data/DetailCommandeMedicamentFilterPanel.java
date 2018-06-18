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
 * Filter panel to filter the DetailCommandeMedicament entries
 * @author MEDES-IMPS
 */
public class DetailCommandeMedicamentFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private IntegerBox quantiteRequiseBox;
	private ImogFilterBox quantiteRequiseFilterBox;
	private IntegerBox quantiteEnStockBox;
	private ImogFilterBox quantiteEnStockFilterBox;
	private TextBox medicament_designationBox;
	private ImogFilterBox medicament_designationFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public DetailCommandeMedicamentFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		quantiteRequiseBox.setValue(null);
		quantiteEnStockBox.setValue(null);
		medicament_designationBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		quantiteRequiseBox = new IntegerBox();
		quantiteRequiseFilterBox = new ImogFilterBox(quantiteRequiseBox);
		quantiteRequiseFilterBox.setFilterLabel(NLS.constants()
				.detailCommandeMedicament_field_quantiteRequise());
		addTableFilter(quantiteRequiseFilterBox);

		quantiteEnStockBox = new IntegerBox();
		quantiteEnStockFilterBox = new ImogFilterBox(quantiteEnStockBox);
		quantiteEnStockFilterBox.setFilterLabel(NLS.constants()
				.detailCommandeMedicament_field_quantiteEnStock());
		addTableFilter(quantiteEnStockFilterBox);

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

		FilterCriteria quantiteRequiseCrit = new FilterCriteria();
		quantiteRequiseCrit.setField("quantiteRequise");
		quantiteRequiseCrit.setFieldDisplayName(NLS.constants()
				.detailCommandeMedicament_field_quantiteRequise());
		quantiteRequiseCrit.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
		if (quantiteRequiseBox.getValue() == null)
			quantiteRequiseCrit.setValue(null);
		else
			quantiteRequiseCrit.setValue(String.valueOf(quantiteRequiseBox
					.getValue()));
		quantiteRequiseCrit.setValueDisplayName(String
				.valueOf(quantiteRequiseBox.getValue()));
		criteria.add(quantiteRequiseCrit);

		FilterCriteria quantiteEnStockCrit = new FilterCriteria();
		quantiteEnStockCrit.setField("quantiteEnStock");
		quantiteEnStockCrit.setFieldDisplayName(NLS.constants()
				.detailCommandeMedicament_field_quantiteEnStock());
		quantiteEnStockCrit.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
		if (quantiteEnStockBox.getValue() == null)
			quantiteEnStockCrit.setValue(null);
		else
			quantiteEnStockCrit.setValue(String.valueOf(quantiteEnStockBox
					.getValue()));
		quantiteEnStockCrit.setValueDisplayName(String
				.valueOf(quantiteEnStockBox.getValue()));
		criteria.add(quantiteEnStockCrit);

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

		if (!AccessManager.canReadDetailCommandeMedicamentDescription())
			quantiteRequiseFilterBox.setVisible(false);

		if (!AccessManager.canReadDetailCommandeMedicamentDescription())
			quantiteEnStockFilterBox.setVisible(false);

		if (!AccessManager.canReadMedicamentDescription())
			medicament_designationFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
