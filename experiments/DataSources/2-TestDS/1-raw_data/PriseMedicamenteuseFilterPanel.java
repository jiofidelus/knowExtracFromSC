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
 * Filter panel to filter the PriseMedicamenteuse entries
 * @author MEDES-IMPS
 */
public class PriseMedicamenteuseFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private DateBox dateEffectiveBeforeBox;
	private ImogFilterBox dateEffectiveBeforeFilterBox;
	private DateBox dateEffectiveAfterBox;
	private ImogFilterBox dateEffectiveAfterFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public PriseMedicamenteuseFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		dateEffectiveBeforeBox.setValue(null);
		dateEffectiveAfterBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		dateEffectiveAfterBox = new DateBox();
		dateEffectiveAfterBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateEffectiveAfterFilterBox = new ImogFilterBox(dateEffectiveAfterBox);
		dateEffectiveAfterFilterBox.setFilterLabel(NLS.constants()
				.priseMedicamenteuse_field_dateEffective()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(dateEffectiveAfterFilterBox);

		dateEffectiveBeforeBox = new DateBox();
		dateEffectiveBeforeBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateEffectiveBeforeFilterBox = new ImogFilterBox(dateEffectiveBeforeBox);
		dateEffectiveBeforeFilterBox.setFilterLabel(NLS.constants()
				.priseMedicamenteuse_field_dateEffective()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(dateEffectiveBeforeFilterBox);

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

		if (dateEffectiveBeforeBox.getValue() != null) {
			FilterCriteria dateEffectiveBeforeCrit = new FilterCriteria();
			dateEffectiveBeforeCrit.setField("dateEffective");
			dateEffectiveBeforeCrit.setFieldDisplayName(NLS.constants()
					.priseMedicamenteuse_field_dateEffective()
					+ BaseNLS.constants().search_operator_inferior());
			dateEffectiveBeforeCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			dateEffectiveBeforeCrit.setValue(DateUtil
					.getDate(dateEffectiveBeforeBox.getValue()));
			dateEffectiveBeforeCrit.setValueDisplayName(DateUtil
					.getDate(dateEffectiveBeforeBox.getValue()));
			criteria.add(dateEffectiveBeforeCrit);
		}

		if (dateEffectiveAfterBox.getValue() != null) {
			FilterCriteria dateEffectiveAfterCrit = new FilterCriteria();
			dateEffectiveAfterCrit.setField("dateEffective");
			dateEffectiveAfterCrit.setFieldDisplayName(NLS.constants()
					.priseMedicamenteuse_field_dateEffective()
					+ BaseNLS.constants().search_operator_superior());
			dateEffectiveAfterCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			dateEffectiveAfterCrit.setValue(DateUtil
					.getDate(dateEffectiveAfterBox.getValue()));
			dateEffectiveAfterCrit.setValueDisplayName(DateUtil
					.getDate(dateEffectiveAfterBox.getValue()));
			criteria.add(dateEffectiveAfterCrit);
		}

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

		if (!AccessManager.canReadPriseMedicamenteuseDescription()) {
			dateEffectiveBeforeFilterBox.setVisible(false);
			dateEffectiveAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
