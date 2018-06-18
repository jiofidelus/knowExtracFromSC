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
 * Filter panel to filter the Formation entries
 * @author MEDES-IMPS
 */
public class FormationFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private DateBox dateDebutBeforeBox;
	private ImogFilterBox dateDebutBeforeFilterBox;
	private DateBox dateDebutAfterBox;
	private ImogFilterBox dateDebutAfterFilterBox;
	private TextBox libelleBox;
	private ImogFilterBox libelleFilterBox;
	private TextBox lieuBox;
	private ImogFilterBox lieuFilterBox;
	private ImogBooleanAsList effectueeBox;
	private ImogFilterBox effectueeFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public FormationFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		dateDebutBeforeBox.setValue(null);
		dateDebutAfterBox.setValue(null);
		libelleBox.setValue(null);
		lieuBox.setValue(null);
		effectueeBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		dateDebutAfterBox = new DateBox();
		dateDebutAfterBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateDebutAfterFilterBox = new ImogFilterBox(dateDebutAfterBox);
		dateDebutAfterFilterBox.setFilterLabel(NLS.constants()
				.formation_field_dateDebut()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(dateDebutAfterFilterBox);

		dateDebutBeforeBox = new DateBox();
		dateDebutBeforeBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateDebutBeforeFilterBox = new ImogFilterBox(dateDebutBeforeBox);
		dateDebutBeforeFilterBox.setFilterLabel(NLS.constants()
				.formation_field_dateDebut()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(dateDebutBeforeFilterBox);

		libelleBox = new TextBox();
		libelleFilterBox = new ImogFilterBox(libelleBox);
		libelleFilterBox.setFilterLabel(NLS.constants()
				.formation_field_libelle());
		addTableFilter(libelleFilterBox);

		lieuBox = new TextBox();
		lieuFilterBox = new ImogFilterBox(lieuBox);
		lieuFilterBox.setFilterLabel(NLS.constants().formation_field_lieu());
		addTableFilter(lieuFilterBox);

		effectueeBox = new ImogBooleanAsList();
		effectueeFilterBox = new ImogFilterBox(effectueeBox);
		effectueeFilterBox.setFilterLabel(NLS.constants()
				.formation_field_effectuee());
		addTableFilter(effectueeFilterBox);

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

		if (dateDebutBeforeBox.getValue() != null) {
			FilterCriteria dateDebutBeforeCrit = new FilterCriteria();
			dateDebutBeforeCrit.setField("dateDebut");
			dateDebutBeforeCrit.setFieldDisplayName(NLS.constants()
					.formation_field_dateDebut()
					+ BaseNLS.constants().search_operator_inferior());
			dateDebutBeforeCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			dateDebutBeforeCrit.setValue(DateUtil.getDate(dateDebutBeforeBox
					.getValue()));
			dateDebutBeforeCrit.setValueDisplayName(DateUtil
					.getDate(dateDebutBeforeBox.getValue()));
			criteria.add(dateDebutBeforeCrit);
		}

		if (dateDebutAfterBox.getValue() != null) {
			FilterCriteria dateDebutAfterCrit = new FilterCriteria();
			dateDebutAfterCrit.setField("dateDebut");
			dateDebutAfterCrit.setFieldDisplayName(NLS.constants()
					.formation_field_dateDebut()
					+ BaseNLS.constants().search_operator_superior());
			dateDebutAfterCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			dateDebutAfterCrit.setValue(DateUtil.getDate(dateDebutAfterBox
					.getValue()));
			dateDebutAfterCrit.setValueDisplayName(DateUtil
					.getDate(dateDebutAfterBox.getValue()));
			criteria.add(dateDebutAfterCrit);
		}

		FilterCriteria libelleCrit = new FilterCriteria();
		if (locale.equals("fr"))
			libelleCrit.setField("libelle.francais");
		if (locale.equals("en"))
			libelleCrit.setField("libelle.english");
		libelleCrit.setFieldDisplayName(NLS.constants()
				.formation_field_libelle());
		libelleCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		libelleCrit.setValue(libelleBox.getValue());
		libelleCrit.setValueDisplayName(libelleBox.getValue());
		criteria.add(libelleCrit);

		FilterCriteria lieuCrit = new FilterCriteria();
		if (locale.equals("fr"))
			lieuCrit.setField("lieu.francais");
		if (locale.equals("en"))
			lieuCrit.setField("lieu.english");
		lieuCrit.setFieldDisplayName(NLS.constants().formation_field_lieu());
		lieuCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		lieuCrit.setValue(lieuBox.getValue());
		lieuCrit.setValueDisplayName(lieuBox.getValue());
		criteria.add(lieuCrit);

		FilterCriteria effectueeCrit = new FilterCriteria();
		effectueeCrit.setField("effectuee");
		effectueeCrit.setFieldDisplayName(NLS.constants()
				.formation_field_effectuee());
		effectueeCrit.setOperation(CriteriaConstants.BOOLEAN_OPERATOR_EQUAL);
		if (effectueeBox.getValue() == null)
			effectueeCrit.setValue(null);
		else
			effectueeCrit.setValue(String.valueOf(effectueeBox.getValue()));
		effectueeCrit.setValueDisplayName(BooleanUtil
				.getBooleanDisplayValue(effectueeBox.getValue()));
		criteria.add(effectueeCrit);

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

		if (!AccessManager.canReadFormationDescription()) {
			dateDebutBeforeFilterBox.setVisible(false);
			dateDebutAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.canReadFormationDescription())
			libelleFilterBox.setVisible(false);

		if (!AccessManager.canReadFormationDescription())
			lieuFilterBox.setVisible(false);

		if (!AccessManager.canReadFormationDescription())
			effectueeFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
