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
 * Filter panel to filter the Qualification entries
 * @author MEDES-IMPS
 */
public class QualificationFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox codeBox;
	private ImogFilterBox codeFilterBox;
	private TextBox nomBox;
	private ImogFilterBox nomFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public QualificationFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		codeBox.setValue(null);
		nomBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		codeBox = new TextBox();
		codeFilterBox = new ImogFilterBox(codeBox);
		codeFilterBox
				.setFilterLabel(NLS.constants().qualification_field_code());
		addTableFilter(codeFilterBox);

		nomBox = new TextBox();
		nomFilterBox = new ImogFilterBox(nomBox);
		nomFilterBox.setFilterLabel(NLS.constants().qualification_field_nom());
		addTableFilter(nomFilterBox);

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
		codeCrit.setFieldDisplayName(NLS.constants().qualification_field_code());
		codeCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		codeCrit.setValue(codeBox.getValue());
		codeCrit.setValueDisplayName(codeBox.getValue());
		criteria.add(codeCrit);

		FilterCriteria nomCrit = new FilterCriteria();
		if (locale.equals("fr"))
			nomCrit.setField("nom.francais");
		if (locale.equals("en"))
			nomCrit.setField("nom.english");
		nomCrit.setFieldDisplayName(NLS.constants().qualification_field_nom());
		nomCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		nomCrit.setValue(nomBox.getValue());
		nomCrit.setValueDisplayName(nomBox.getValue());
		criteria.add(nomCrit);

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

		if (!AccessManager.canReadQualificationDescription())
			codeFilterBox.setVisible(false);

		if (!AccessManager.canReadQualificationDescription())
			nomFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
