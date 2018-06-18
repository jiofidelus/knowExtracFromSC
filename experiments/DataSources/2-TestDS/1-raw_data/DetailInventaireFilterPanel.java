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
 * Filter panel to filter the DetailInventaire entries
 * @author MEDES-IMPS
 */
public class DetailInventaireFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox lot_numeroBox;
	private ImogFilterBox lot_numeroFilterBox;
	private TextBox lot_intrant_identifiantBox;
	private ImogFilterBox lot_intrant_identifiantFilterBox;
	private TextBox lot_medicament_designationBox;
	private ImogFilterBox lot_medicament_designationFilterBox;
	private IntegerBox lot_quantiteBox;
	private ImogFilterBox lot_quantiteFilterBox;
	private IntegerBox quantiteTheoriqueBox;
	private ImogFilterBox quantiteTheoriqueFilterBox;
	private IntegerBox quantiteReelleBox;
	private ImogFilterBox quantiteReelleFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public DetailInventaireFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		lot_numeroBox.setValue(null);
		lot_intrant_identifiantBox.setValue(null);
		lot_medicament_designationBox.setValue(null);
		lot_quantiteBox.setValue(null);
		quantiteTheoriqueBox.setValue(null);
		quantiteReelleBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		lot_numeroBox = new TextBox();
		lot_numeroFilterBox = new ImogFilterBox(lot_numeroBox);
		lot_numeroFilterBox.setFilterLabel(NLS.constants().lot_field_numero());
		addTableFilter(lot_numeroFilterBox);
		lot_intrant_identifiantBox = new TextBox();
		lot_intrant_identifiantFilterBox = new ImogFilterBox(
				lot_intrant_identifiantBox);
		lot_intrant_identifiantFilterBox.setFilterLabel(NLS.constants()
				.intrant_field_identifiant());
		addTableFilter(lot_intrant_identifiantFilterBox);
		lot_medicament_designationBox = new TextBox();
		lot_medicament_designationFilterBox = new ImogFilterBox(
				lot_medicament_designationBox);
		lot_medicament_designationFilterBox.setFilterLabel(NLS.constants()
				.medicament_field_designation());
		addTableFilter(lot_medicament_designationFilterBox);
		lot_quantiteBox = new IntegerBox();
		lot_quantiteFilterBox = new ImogFilterBox(lot_quantiteBox);
		lot_quantiteFilterBox.setFilterLabel(NLS.constants()
				.lot_field_quantite());
		addTableFilter(lot_quantiteFilterBox);

		quantiteTheoriqueBox = new IntegerBox();
		quantiteTheoriqueFilterBox = new ImogFilterBox(quantiteTheoriqueBox);
		quantiteTheoriqueFilterBox.setFilterLabel(NLS.constants()
				.detailInventaire_field_quantiteTheorique());
		addTableFilter(quantiteTheoriqueFilterBox);

		quantiteReelleBox = new IntegerBox();
		quantiteReelleFilterBox = new ImogFilterBox(quantiteReelleBox);
		quantiteReelleFilterBox.setFilterLabel(NLS.constants()
				.detailInventaire_field_quantiteReelle());
		addTableFilter(quantiteReelleFilterBox);

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

		FilterCriteria lot_numeroCrit = new FilterCriteria();
		lot_numeroCrit.setField("lot.numero");
		lot_numeroCrit.setFieldDisplayName(NLS.constants().lot_field_numero());
		lot_numeroCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		lot_numeroCrit.setValue(lot_numeroBox.getValue());
		lot_numeroCrit.setValueDisplayName(lot_numeroBox.getValue());
		criteria.add(lot_numeroCrit);
		FilterCriteria lot_intrant_identifiantCrit = new FilterCriteria();
		lot_intrant_identifiantCrit.setField("lot.intrant.identifiant");
		lot_intrant_identifiantCrit.setFieldDisplayName(NLS.constants()
				.intrant_field_identifiant());
		lot_intrant_identifiantCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		lot_intrant_identifiantCrit.setValue(lot_intrant_identifiantBox
				.getValue());
		lot_intrant_identifiantCrit
				.setValueDisplayName(lot_intrant_identifiantBox.getValue());
		criteria.add(lot_intrant_identifiantCrit);
		FilterCriteria lot_medicament_designationCrit = new FilterCriteria();
		lot_medicament_designationCrit.setField("lot.medicament.designation");
		lot_medicament_designationCrit.setFieldDisplayName(NLS.constants()
				.medicament_field_designation());
		lot_medicament_designationCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		lot_medicament_designationCrit.setValue(lot_medicament_designationBox
				.getValue());
		lot_medicament_designationCrit
				.setValueDisplayName(lot_medicament_designationBox.getValue());
		criteria.add(lot_medicament_designationCrit);
		FilterCriteria lot_quantiteCrit = new FilterCriteria();
		lot_quantiteCrit.setField("lot.quantite");
		lot_quantiteCrit.setFieldDisplayName(NLS.constants()
				.lot_field_quantite());
		lot_quantiteCrit.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
		if (lot_quantiteBox.getValue() == null)
			lot_quantiteCrit.setValue(null);
		else
			lot_quantiteCrit
					.setValue(String.valueOf(lot_quantiteBox.getValue()));
		lot_quantiteCrit.setValueDisplayName(String.valueOf(lot_quantiteBox
				.getValue()));
		criteria.add(lot_quantiteCrit);

		FilterCriteria quantiteTheoriqueCrit = new FilterCriteria();
		quantiteTheoriqueCrit.setField("quantiteTheorique");
		quantiteTheoriqueCrit.setFieldDisplayName(NLS.constants()
				.detailInventaire_field_quantiteTheorique());
		quantiteTheoriqueCrit
				.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
		if (quantiteTheoriqueBox.getValue() == null)
			quantiteTheoriqueCrit.setValue(null);
		else
			quantiteTheoriqueCrit.setValue(String.valueOf(quantiteTheoriqueBox
					.getValue()));
		quantiteTheoriqueCrit.setValueDisplayName(String
				.valueOf(quantiteTheoriqueBox.getValue()));
		criteria.add(quantiteTheoriqueCrit);

		FilterCriteria quantiteReelleCrit = new FilterCriteria();
		quantiteReelleCrit.setField("quantiteReelle");
		quantiteReelleCrit.setFieldDisplayName(NLS.constants()
				.detailInventaire_field_quantiteReelle());
		quantiteReelleCrit.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
		if (quantiteReelleBox.getValue() == null)
			quantiteReelleCrit.setValue(null);
		else
			quantiteReelleCrit.setValue(String.valueOf(quantiteReelleBox
					.getValue()));
		quantiteReelleCrit.setValueDisplayName(String.valueOf(quantiteReelleBox
				.getValue()));
		criteria.add(quantiteReelleCrit);

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

		if (!AccessManager.canReadLotDescription())
			lot_numeroFilterBox.setVisible(false);
		if (!AccessManager.canReadIntrantDescription())
			lot_intrant_identifiantFilterBox.setVisible(false);
		if (!AccessManager.canReadMedicamentDescription())
			lot_medicament_designationFilterBox.setVisible(false);
		if (!AccessManager.canReadLotDescription())
			lot_quantiteFilterBox.setVisible(false);

		if (!AccessManager.canReadDetailInventaireDescription())
			quantiteTheoriqueFilterBox.setVisible(false);

		if (!AccessManager.canReadDetailInventaireDescription())
			quantiteReelleFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
