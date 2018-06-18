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
 * Filter panel to filter the DetailReceptionIntrant entries
 * @author MEDES-IMPS
 */
public class DetailReceptionIntrantFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox commande_cdt_nomBox;
	private ImogFilterBox commande_cdt_nomFilterBox;
	private DateBox commande_dateBeforeBox;
	private ImogFilterBox commande_dateBeforeFilterBox;
	private DateBox commande_dateAfterBox;
	private ImogFilterBox commande_dateAfterFilterBox;
	private TextBox detailcommande_intrant_identifiantBox;
	private ImogFilterBox detailcommande_intrant_identifiantFilterBox;
	private IntegerBox detailcommande_quantiteRequiseBox;
	private ImogFilterBox detailcommande_quantiteRequiseFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public DetailReceptionIntrantFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		commande_cdt_nomBox.setValue(null);
		commande_dateBeforeBox.setValue(null);
		commande_dateAfterBox.setValue(null);
		detailcommande_intrant_identifiantBox.setValue(null);
		detailcommande_quantiteRequiseBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		commande_cdt_nomBox = new TextBox();
		commande_cdt_nomFilterBox = new ImogFilterBox(commande_cdt_nomBox);
		commande_cdt_nomFilterBox.setFilterLabel(NLS.constants()
				.centreDiagTrait_field_nom());
		addTableFilter(commande_cdt_nomFilterBox);
		commande_dateAfterBox = new DateBox();
		commande_dateAfterBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		commande_dateAfterFilterBox = new ImogFilterBox(commande_dateAfterBox);
		commande_dateAfterFilterBox.setFilterLabel(NLS.constants()
				.commande_field_date()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(commande_dateAfterFilterBox);

		commande_dateBeforeBox = new DateBox();
		commande_dateBeforeBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		commande_dateBeforeFilterBox = new ImogFilterBox(commande_dateBeforeBox);
		commande_dateBeforeFilterBox.setFilterLabel(NLS.constants()
				.commande_field_date()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(commande_dateBeforeFilterBox);

		detailcommande_intrant_identifiantBox = new TextBox();
		detailcommande_intrant_identifiantFilterBox = new ImogFilterBox(
				detailcommande_intrant_identifiantBox);
		detailcommande_intrant_identifiantFilterBox.setFilterLabel(NLS
				.constants().intrant_field_identifiant());
		addTableFilter(detailcommande_intrant_identifiantFilterBox);
		detailcommande_quantiteRequiseBox = new IntegerBox();
		detailcommande_quantiteRequiseFilterBox = new ImogFilterBox(
				detailcommande_quantiteRequiseBox);
		detailcommande_quantiteRequiseFilterBox.setFilterLabel(NLS.constants()
				.detailCommandeIntrant_field_quantiteRequise());
		addTableFilter(detailcommande_quantiteRequiseFilterBox);

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

		FilterCriteria commande_cdt_nomCrit = new FilterCriteria();
		commande_cdt_nomCrit.setField("commande.cDT.nom");
		commande_cdt_nomCrit.setFieldDisplayName(NLS.constants()
				.centreDiagTrait_field_nom());
		commande_cdt_nomCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		commande_cdt_nomCrit.setValue(commande_cdt_nomBox.getValue());
		commande_cdt_nomCrit
				.setValueDisplayName(commande_cdt_nomBox.getValue());
		criteria.add(commande_cdt_nomCrit);

		if (commande_dateBeforeBox.getValue() != null) {
			FilterCriteria commande_dateBeforeCrit = new FilterCriteria();
			commande_dateBeforeCrit.setField("commande.date");
			commande_dateBeforeCrit.setFieldDisplayName(NLS.constants()
					.commande_field_date()
					+ BaseNLS.constants().search_operator_inferior());
			commande_dateBeforeCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			commande_dateBeforeCrit.setValue(DateUtil
					.getDate(commande_dateBeforeBox.getValue()));
			commande_dateBeforeCrit.setValueDisplayName(DateUtil
					.getDate(commande_dateBeforeBox.getValue()));
			criteria.add(commande_dateBeforeCrit);
		}

		if (commande_dateAfterBox.getValue() != null) {
			FilterCriteria commande_dateAfterCrit = new FilterCriteria();
			commande_dateAfterCrit.setField("commande.date");
			commande_dateAfterCrit.setFieldDisplayName(NLS.constants()
					.commande_field_date()
					+ BaseNLS.constants().search_operator_superior());
			commande_dateAfterCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			commande_dateAfterCrit.setValue(DateUtil
					.getDate(commande_dateAfterBox.getValue()));
			commande_dateAfterCrit.setValueDisplayName(DateUtil
					.getDate(commande_dateAfterBox.getValue()));
			criteria.add(commande_dateAfterCrit);
		}

		FilterCriteria detailcommande_intrant_identifiantCrit = new FilterCriteria();
		detailcommande_intrant_identifiantCrit
				.setField("detailCommande.intrant.identifiant");
		detailcommande_intrant_identifiantCrit.setFieldDisplayName(NLS
				.constants().intrant_field_identifiant());
		detailcommande_intrant_identifiantCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		detailcommande_intrant_identifiantCrit
				.setValue(detailcommande_intrant_identifiantBox.getValue());
		detailcommande_intrant_identifiantCrit
				.setValueDisplayName(detailcommande_intrant_identifiantBox
						.getValue());
		criteria.add(detailcommande_intrant_identifiantCrit);
		FilterCriteria detailcommande_quantiteRequiseCrit = new FilterCriteria();
		detailcommande_quantiteRequiseCrit
				.setField("detailCommande.quantiteRequise");
		detailcommande_quantiteRequiseCrit.setFieldDisplayName(NLS.constants()
				.detailCommandeIntrant_field_quantiteRequise());
		detailcommande_quantiteRequiseCrit
				.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
		if (detailcommande_quantiteRequiseBox.getValue() == null)
			detailcommande_quantiteRequiseCrit.setValue(null);
		else
			detailcommande_quantiteRequiseCrit.setValue(String
					.valueOf(detailcommande_quantiteRequiseBox.getValue()));
		detailcommande_quantiteRequiseCrit.setValueDisplayName(String
				.valueOf(detailcommande_quantiteRequiseBox.getValue()));
		criteria.add(detailcommande_quantiteRequiseCrit);

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
			commande_cdt_nomFilterBox.setVisible(false);
		if (!AccessManager.canReadCommandeInformationsDepart()) {
			commande_dateBeforeFilterBox.setVisible(false);
			commande_dateAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.canReadIntrantDescription())
			detailcommande_intrant_identifiantFilterBox.setVisible(false);
		if (!AccessManager.canReadDetailCommandeIntrantDescription())
			detailcommande_quantiteRequiseFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
