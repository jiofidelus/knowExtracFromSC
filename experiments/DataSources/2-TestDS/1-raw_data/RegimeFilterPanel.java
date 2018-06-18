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
 * Filter panel to filter the Regime entries
 * @author MEDES-IMPS
 */
public class RegimeFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox nomBox;
	private ImogFilterBox nomFilterBox;
	private ListBox typeBox;
	private ImogFilterBox typeFilterBox;
	private IntegerBox dureeTraitementBox;
	private ImogFilterBox dureeTraitementFilterBox;
	private IntegerBox poidsMinBox;
	private ImogFilterBox poidsMinFilterBox;
	private IntegerBox poidsMaxBox;
	private ImogFilterBox poidsMaxFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public RegimeFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		nomBox.setValue(null);
		typeBox.setSelectedIndex(0);
		dureeTraitementBox.setValue(null);
		poidsMinBox.setValue(null);
		poidsMaxBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		nomBox = new TextBox();
		nomFilterBox = new ImogFilterBox(nomBox);
		nomFilterBox.setFilterLabel(NLS.constants().regime_field_nom());
		addTableFilter(nomFilterBox);

		typeBox = new ListBox();
		typeBox.addItem("", BaseNLS.constants().enumeration_unknown());
		typeBox.setSelectedIndex(0);
		typeBox.addItem(NLS.constants().regime_type_phaseInitiale_option(),
				EpicamEnumConstants.REGIME_TYPE_PHASEINITIALE);
		typeBox.addItem(NLS.constants().regime_type_phaseContinuation_option(),
				EpicamEnumConstants.REGIME_TYPE_PHASECONTINUATION);
		typeBox.addItem(NLS.constants().regime_type_independant_option(),
				EpicamEnumConstants.REGIME_TYPE_INDEPENDANT);
		typeFilterBox = new ImogFilterBox(typeBox);
		typeFilterBox.setFilterLabel(NLS.constants().regime_field_type());
		addTableFilter(typeFilterBox);

		dureeTraitementBox = new IntegerBox();
		dureeTraitementFilterBox = new ImogFilterBox(dureeTraitementBox);
		dureeTraitementFilterBox.setFilterLabel(NLS.constants()
				.regime_field_dureeTraitement());
		addTableFilter(dureeTraitementFilterBox);

		poidsMinBox = new IntegerBox();
		poidsMinFilterBox = new ImogFilterBox(poidsMinBox);
		poidsMinFilterBox.setFilterLabel(NLS.constants()
				.regime_field_poidsMin());
		addTableFilter(poidsMinFilterBox);

		poidsMaxBox = new IntegerBox();
		poidsMaxFilterBox = new ImogFilterBox(poidsMaxBox);
		poidsMaxFilterBox.setFilterLabel(NLS.constants()
				.regime_field_poidsMax());
		addTableFilter(poidsMaxFilterBox);

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
		nomCrit.setField("nom");
		nomCrit.setFieldDisplayName(NLS.constants().regime_field_nom());
		nomCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		nomCrit.setValue(nomBox.getValue());
		nomCrit.setValueDisplayName(nomBox.getValue());
		criteria.add(nomCrit);

		FilterCriteria typeCrit = new FilterCriteria();
		typeCrit.setField("type");
		typeCrit.setFieldDisplayName(NLS.constants().regime_field_type());
		typeCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
		typeCrit.setValue(typeBox.getValue(typeBox.getSelectedIndex()));
		typeCrit.setValueDisplayName(EpicamRenderer.get().getEnumDisplayValue(
				RegimeProxy.class, "type",
				typeBox.getValue(typeBox.getSelectedIndex())));
		criteria.add(typeCrit);

		FilterCriteria dureeTraitementCrit = new FilterCriteria();
		dureeTraitementCrit.setField("dureeTraitement");
		dureeTraitementCrit.setFieldDisplayName(NLS.constants()
				.regime_field_dureeTraitement());
		dureeTraitementCrit.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
		if (dureeTraitementBox.getValue() == null)
			dureeTraitementCrit.setValue(null);
		else
			dureeTraitementCrit.setValue(String.valueOf(dureeTraitementBox
					.getValue()));
		dureeTraitementCrit.setValueDisplayName(String
				.valueOf(dureeTraitementBox.getValue()));
		criteria.add(dureeTraitementCrit);

		FilterCriteria poidsMinCrit = new FilterCriteria();
		poidsMinCrit.setField("poidsMin");
		poidsMinCrit.setFieldDisplayName(NLS.constants()
				.regime_field_poidsMin());
		poidsMinCrit.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
		if (poidsMinBox.getValue() == null)
			poidsMinCrit.setValue(null);
		else
			poidsMinCrit.setValue(String.valueOf(poidsMinBox.getValue()));
		poidsMinCrit
				.setValueDisplayName(String.valueOf(poidsMinBox.getValue()));
		criteria.add(poidsMinCrit);

		FilterCriteria poidsMaxCrit = new FilterCriteria();
		poidsMaxCrit.setField("poidsMax");
		poidsMaxCrit.setFieldDisplayName(NLS.constants()
				.regime_field_poidsMax());
		poidsMaxCrit.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
		if (poidsMaxBox.getValue() == null)
			poidsMaxCrit.setValue(null);
		else
			poidsMaxCrit.setValue(String.valueOf(poidsMaxBox.getValue()));
		poidsMaxCrit
				.setValueDisplayName(String.valueOf(poidsMaxBox.getValue()));
		criteria.add(poidsMaxCrit);

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

		if (!AccessManager.canReadRegimeDescription())
			nomFilterBox.setVisible(false);

		if (!AccessManager.canReadRegimeDescription())
			typeFilterBox.setVisible(false);

		if (!AccessManager.canReadRegimeDescription())
			dureeTraitementFilterBox.setVisible(false);

		if (!AccessManager.canReadRegimeDescription())
			poidsMinFilterBox.setVisible(false);

		if (!AccessManager.canReadRegimeDescription())
			poidsMaxFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
