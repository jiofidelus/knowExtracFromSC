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
 * Filter panel to filter the Inventaire entries
 * @author MEDES-IMPS
 */
public class InventaireFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox cdt_nomBox;
	private ImogFilterBox cdt_nomFilterBox;
	private DateBox dateBeforeBox;
	private ImogFilterBox dateBeforeFilterBox;
	private DateBox dateAfterBox;
	private ImogFilterBox dateAfterFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public InventaireFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		cdt_nomBox.setValue(null);
		dateBeforeBox.setValue(null);
		dateAfterBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		cdt_nomBox = new TextBox();
		cdt_nomFilterBox = new ImogFilterBox(cdt_nomBox);
		cdt_nomFilterBox.setFilterLabel(NLS.constants()
				.centreDiagTrait_field_nom());
		addTableFilter(cdt_nomFilterBox);

		dateAfterBox = new DateBox();
		dateAfterBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateAfterFilterBox = new ImogFilterBox(dateAfterBox);
		dateAfterFilterBox.setFilterLabel(NLS.constants()
				.inventaire_field_date()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(dateAfterFilterBox);

		dateBeforeBox = new DateBox();
		dateBeforeBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateBeforeFilterBox = new ImogFilterBox(dateBeforeBox);
		dateBeforeFilterBox.setFilterLabel(NLS.constants()
				.inventaire_field_date()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(dateBeforeFilterBox);

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

		if (dateBeforeBox.getValue() != null) {
			FilterCriteria dateBeforeCrit = new FilterCriteria();
			dateBeforeCrit.setField("date");
			dateBeforeCrit.setFieldDisplayName(NLS.constants()
					.inventaire_field_date()
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
					.inventaire_field_date()
					+ BaseNLS.constants().search_operator_superior());
			dateAfterCrit.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			dateAfterCrit.setValue(DateUtil.getDate(dateAfterBox.getValue()));
			dateAfterCrit.setValueDisplayName(DateUtil.getDate(dateAfterBox
					.getValue()));
			criteria.add(dateAfterCrit);
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

		if (!AccessManager.canReadCentreDiagTraitDescription())
			cdt_nomFilterBox.setVisible(false);

		if (!AccessManager.canReadInventaireInformationsDepart()) {
			dateBeforeFilterBox.setVisible(false);
			dateAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
