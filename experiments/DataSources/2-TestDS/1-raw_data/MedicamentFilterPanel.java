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
 * Filter panel to filter the Medicament entries
 * @author MEDES-IMPS
 */
public class MedicamentFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox codeBox;
	private ImogFilterBox codeFilterBox;
	private TextBox designationBox;
	private ImogFilterBox designationFilterBox;
	private ImogBooleanAsList estMedicamentAntituberculeuxBox;
	private ImogFilterBox estMedicamentAntituberculeuxFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public MedicamentFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		codeBox.setValue(null);
		designationBox.setValue(null);
		estMedicamentAntituberculeuxBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		codeBox = new TextBox();
		codeFilterBox = new ImogFilterBox(codeBox);
		codeFilterBox.setFilterLabel(NLS.constants().medicament_field_code());
		addTableFilter(codeFilterBox);

		designationBox = new TextBox();
		designationFilterBox = new ImogFilterBox(designationBox);
		designationFilterBox.setFilterLabel(NLS.constants()
				.medicament_field_designation());
		addTableFilter(designationFilterBox);

		estMedicamentAntituberculeuxBox = new ImogBooleanAsList();
		estMedicamentAntituberculeuxFilterBox = new ImogFilterBox(
				estMedicamentAntituberculeuxBox);
		estMedicamentAntituberculeuxFilterBox.setFilterLabel(NLS.constants()
				.medicament_field_estMedicamentAntituberculeux());
		addTableFilter(estMedicamentAntituberculeuxFilterBox);

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

		FilterCriteria codeCrit = new FilterCriteria();
		codeCrit.setField("code");
		codeCrit.setFieldDisplayName(NLS.constants().medicament_field_code());
		codeCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		codeCrit.setValue(codeBox.getValue());
		codeCrit.setValueDisplayName(codeBox.getValue());
		criteria.add(codeCrit);

		FilterCriteria designationCrit = new FilterCriteria();
		designationCrit.setField("designation");
		designationCrit.setFieldDisplayName(NLS.constants()
				.medicament_field_designation());
		designationCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		designationCrit.setValue(designationBox.getValue());
		designationCrit.setValueDisplayName(designationBox.getValue());
		criteria.add(designationCrit);

		FilterCriteria estMedicamentAntituberculeuxCrit = new FilterCriteria();
		estMedicamentAntituberculeuxCrit
				.setField("estMedicamentAntituberculeux");
		estMedicamentAntituberculeuxCrit.setFieldDisplayName(NLS.constants()
				.medicament_field_estMedicamentAntituberculeux());
		estMedicamentAntituberculeuxCrit
				.setOperation(CriteriaConstants.BOOLEAN_OPERATOR_EQUAL);
		if (estMedicamentAntituberculeuxBox.getValue() == null)
			estMedicamentAntituberculeuxCrit.setValue(null);
		else
			estMedicamentAntituberculeuxCrit.setValue(String
					.valueOf(estMedicamentAntituberculeuxBox.getValue()));
		estMedicamentAntituberculeuxCrit.setValueDisplayName(BooleanUtil
				.getBooleanDisplayValue(estMedicamentAntituberculeuxBox
						.getValue()));
		criteria.add(estMedicamentAntituberculeuxCrit);

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

		if (!AccessManager.canReadMedicamentDescription())
			codeFilterBox.setVisible(false);

		if (!AccessManager.canReadMedicamentDescription())
			designationFilterBox.setVisible(false);

		if (!AccessManager.canReadMedicamentDescription())
			estMedicamentAntituberculeuxFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
