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
 * Filter panel to filter the Intrant entries
 * @author MEDES-IMPS
 */
public class IntrantFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox identifiantBox;
	private ImogFilterBox identifiantFilterBox;
	private TextBox nomBox;
	private ImogFilterBox nomFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public IntrantFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		identifiantBox.setValue(null);
		nomBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		identifiantBox = new TextBox();
		identifiantFilterBox = new ImogFilterBox(identifiantBox);
		identifiantFilterBox.setFilterLabel(NLS.constants()
				.intrant_field_identifiant());
		addTableFilter(identifiantFilterBox);

		nomBox = new TextBox();
		nomFilterBox = new ImogFilterBox(nomBox);
		nomFilterBox.setFilterLabel(NLS.constants().intrant_field_nom());
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

		FilterCriteria identifiantCrit = new FilterCriteria();
		identifiantCrit.setField("identifiant");
		identifiantCrit.setFieldDisplayName(NLS.constants()
				.intrant_field_identifiant());
		identifiantCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		identifiantCrit.setValue(identifiantBox.getValue());
		identifiantCrit.setValueDisplayName(identifiantBox.getValue());
		criteria.add(identifiantCrit);

		FilterCriteria nomCrit = new FilterCriteria();
		nomCrit.setField("nom");
		nomCrit.setFieldDisplayName(NLS.constants().intrant_field_nom());
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

		if (!AccessManager.canReadIntrantDescription())
			identifiantFilterBox.setVisible(false);

		if (!AccessManager.canReadIntrantDescription())
			nomFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
