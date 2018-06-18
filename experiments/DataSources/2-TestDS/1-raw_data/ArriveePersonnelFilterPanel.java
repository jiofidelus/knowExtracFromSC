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
 * Filter panel to filter the ArriveePersonnel entries
 * @author MEDES-IMPS
 */
public class ArriveePersonnelFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private DateBox dateArriveeBeforeBox;
	private ImogFilterBox dateArriveeBeforeFilterBox;
	private DateBox dateArriveeAfterBox;
	private ImogFilterBox dateArriveeAfterFilterBox;
	private TextBox region_nomBox;
	private ImogFilterBox region_nomFilterBox;
	private TextBox districtsante_nomBox;
	private ImogFilterBox districtsante_nomFilterBox;
	private TextBox cdt_nomBox;
	private ImogFilterBox cdt_nomFilterBox;
	private TextBox personnel_nomBox;
	private ImogFilterBox personnel_nomFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public ArriveePersonnelFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		dateArriveeBeforeBox.setValue(null);
		dateArriveeAfterBox.setValue(null);
		region_nomBox.setValue(null);
		districtsante_nomBox.setValue(null);
		cdt_nomBox.setValue(null);
		personnel_nomBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		dateArriveeAfterBox = new DateBox();
		dateArriveeAfterBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateArriveeAfterFilterBox = new ImogFilterBox(dateArriveeAfterBox);
		dateArriveeAfterFilterBox.setFilterLabel(NLS.constants()
				.arriveePersonnel_field_dateArrivee()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(dateArriveeAfterFilterBox);

		dateArriveeBeforeBox = new DateBox();
		dateArriveeBeforeBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateArriveeBeforeFilterBox = new ImogFilterBox(dateArriveeBeforeBox);
		dateArriveeBeforeFilterBox.setFilterLabel(NLS.constants()
				.arriveePersonnel_field_dateArrivee()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(dateArriveeBeforeFilterBox);

		region_nomBox = new TextBox();
		region_nomFilterBox = new ImogFilterBox(region_nomBox);
		region_nomFilterBox.setFilterLabel(NLS.constants().region_field_nom());
		addTableFilter(region_nomFilterBox);

		districtsante_nomBox = new TextBox();
		districtsante_nomFilterBox = new ImogFilterBox(districtsante_nomBox);
		districtsante_nomFilterBox.setFilterLabel(NLS.constants()
				.districtSante_field_nom());
		addTableFilter(districtsante_nomFilterBox);

		cdt_nomBox = new TextBox();
		cdt_nomFilterBox = new ImogFilterBox(cdt_nomBox);
		cdt_nomFilterBox.setFilterLabel(NLS.constants()
				.centreDiagTrait_field_nom());
		addTableFilter(cdt_nomFilterBox);

		personnel_nomBox = new TextBox();
		personnel_nomFilterBox = new ImogFilterBox(personnel_nomBox);
		personnel_nomFilterBox.setFilterLabel(NLS.constants()
				.personnel_field_nom());
		addTableFilter(personnel_nomFilterBox);

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

		if (dateArriveeBeforeBox.getValue() != null) {
			FilterCriteria dateArriveeBeforeCrit = new FilterCriteria();
			dateArriveeBeforeCrit.setField("dateArrivee");
			dateArriveeBeforeCrit.setFieldDisplayName(NLS.constants()
					.arriveePersonnel_field_dateArrivee()
					+ BaseNLS.constants().search_operator_inferior());
			dateArriveeBeforeCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			dateArriveeBeforeCrit.setValue(DateUtil
					.getDate(dateArriveeBeforeBox.getValue()));
			dateArriveeBeforeCrit.setValueDisplayName(DateUtil
					.getDate(dateArriveeBeforeBox.getValue()));
			criteria.add(dateArriveeBeforeCrit);
		}

		if (dateArriveeAfterBox.getValue() != null) {
			FilterCriteria dateArriveeAfterCrit = new FilterCriteria();
			dateArriveeAfterCrit.setField("dateArrivee");
			dateArriveeAfterCrit.setFieldDisplayName(NLS.constants()
					.arriveePersonnel_field_dateArrivee()
					+ BaseNLS.constants().search_operator_superior());
			dateArriveeAfterCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			dateArriveeAfterCrit.setValue(DateUtil.getDate(dateArriveeAfterBox
					.getValue()));
			dateArriveeAfterCrit.setValueDisplayName(DateUtil
					.getDate(dateArriveeAfterBox.getValue()));
			criteria.add(dateArriveeAfterCrit);
		}

		FilterCriteria region_nomCrit = new FilterCriteria();
		if (locale.equals("fr"))
			region_nomCrit.setField("region.nom.francais");
		if (locale.equals("en"))
			region_nomCrit.setField("region.nom.english");
		region_nomCrit.setFieldDisplayName(NLS.constants().region_field_nom());
		region_nomCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		region_nomCrit.setValue(region_nomBox.getValue());
		region_nomCrit.setValueDisplayName(region_nomBox.getValue());
		criteria.add(region_nomCrit);

		FilterCriteria districtsante_nomCrit = new FilterCriteria();
		if (locale.equals("fr"))
			districtsante_nomCrit.setField("districtSante.nom.francais");
		if (locale.equals("en"))
			districtsante_nomCrit.setField("districtSante.nom.english");
		districtsante_nomCrit.setFieldDisplayName(NLS.constants()
				.districtSante_field_nom());
		districtsante_nomCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		districtsante_nomCrit.setValue(districtsante_nomBox.getValue());
		districtsante_nomCrit.setValueDisplayName(districtsante_nomBox
				.getValue());
		criteria.add(districtsante_nomCrit);

		FilterCriteria cdt_nomCrit = new FilterCriteria();
		cdt_nomCrit.setField("cDT.nom");
		cdt_nomCrit.setFieldDisplayName(NLS.constants()
				.centreDiagTrait_field_nom());
		cdt_nomCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		cdt_nomCrit.setValue(cdt_nomBox.getValue());
		cdt_nomCrit.setValueDisplayName(cdt_nomBox.getValue());
		criteria.add(cdt_nomCrit);

		FilterCriteria personnel_nomCrit = new FilterCriteria();
		personnel_nomCrit.setField("personnel.nom");
		personnel_nomCrit.setFieldDisplayName(NLS.constants()
				.personnel_field_nom());
		personnel_nomCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		personnel_nomCrit.setValue(personnel_nomBox.getValue());
		personnel_nomCrit.setValueDisplayName(personnel_nomBox.getValue());
		criteria.add(personnel_nomCrit);

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

		if (!AccessManager.canReadArriveePersonnelDescription()) {
			dateArriveeBeforeFilterBox.setVisible(false);
			dateArriveeAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.canReadRegionDescription())
			region_nomFilterBox.setVisible(false);

		if (!AccessManager.canReadDistrictSanteDescription())
			districtsante_nomFilterBox.setVisible(false);

		if (!AccessManager.canReadCentreDiagTraitDescription())
			cdt_nomFilterBox.setVisible(false);

		if (!AccessManager.canReadPersonnelIdentification())
			personnel_nomFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
