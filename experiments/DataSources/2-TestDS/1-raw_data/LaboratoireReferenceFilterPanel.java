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
 * Filter panel to filter the LaboratoireReference entries
 * @author MEDES-IMPS
 */
public class LaboratoireReferenceFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox nomBox;
	private ImogFilterBox nomFilterBox;
	private ListBox natureBox;
	private ImogFilterBox natureFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public LaboratoireReferenceFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		nomBox.setValue(null);
		natureBox.setSelectedIndex(0);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		nomBox = new TextBox();
		nomFilterBox = new ImogFilterBox(nomBox);
		nomFilterBox.setFilterLabel(NLS.constants()
				.laboratoireReference_field_nom());
		addTableFilter(nomFilterBox);

		natureBox = new ListBox();
		natureBox.addItem("", BaseNLS.constants().enumeration_unknown());
		natureBox.setSelectedIndex(0);
		natureBox.addItem(NLS.constants()
				.laboratoireReference_nature_national_option(),
				EpicamEnumConstants.LABORATOIREREFERENCE_NATURE_NATIONAL);
		natureBox.addItem(NLS.constants()
				.laboratoireReference_nature_regional_option(),
				EpicamEnumConstants.LABORATOIREREFERENCE_NATURE_REGIONAL);
		natureFilterBox = new ImogFilterBox(natureBox);
		natureFilterBox.setFilterLabel(NLS.constants()
				.laboratoireReference_field_nature());
		addTableFilter(natureFilterBox);

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

		FilterCriteria nomCrit = new FilterCriteria();
		if (locale.equals("fr"))
			nomCrit.setField("nom.francais");
		if (locale.equals("en"))
			nomCrit.setField("nom.english");
		nomCrit.setFieldDisplayName(NLS.constants()
				.laboratoireReference_field_nom());
		nomCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		nomCrit.setValue(nomBox.getValue());
		nomCrit.setValueDisplayName(nomBox.getValue());
		criteria.add(nomCrit);

		FilterCriteria natureCrit = new FilterCriteria();
		natureCrit.setField("nature");
		natureCrit.setFieldDisplayName(NLS.constants()
				.laboratoireReference_field_nature());
		natureCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
		natureCrit.setValue(natureBox.getValue(natureBox.getSelectedIndex()));
		natureCrit.setValueDisplayName(EpicamRenderer.get()
				.getEnumDisplayValue(LaboratoireReferenceProxy.class, "nature",
						natureBox.getValue(natureBox.getSelectedIndex())));
		criteria.add(natureCrit);

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

		if (!AccessManager.canReadLaboratoireReferenceDescription())
			nomFilterBox.setVisible(false);

		if (!AccessManager.canReadLaboratoireReferenceDescription())
			natureFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
