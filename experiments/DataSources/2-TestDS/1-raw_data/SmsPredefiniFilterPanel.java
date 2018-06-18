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
 * Filter panel to filter the SmsPredefini entries
 * @author MEDES-IMPS
 */
public class SmsPredefiniFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private ListBox typeBox;
	private ImogFilterBox typeFilterBox;
	private TextBox objetBox;
	private ImogFilterBox objetFilterBox;
	private TextBox messageBox;
	private ImogFilterBox messageFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public SmsPredefiniFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		typeBox.setSelectedIndex(0);
		objetBox.setValue(null);
		messageBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		typeBox = new ListBox();
		typeBox.addItem("", BaseNLS.constants().enumeration_unknown());
		typeBox.setSelectedIndex(0);
		typeBox.addItem(NLS.constants()
				.smsPredefini_type_sensibilisation_option(),
				EpicamEnumConstants.SMSPREDEFINI_TYPE_SENSIBILISATION);
		typeBox.addItem(NLS.constants().smsPredefini_type_quizz_option(),
				EpicamEnumConstants.SMSPREDEFINI_TYPE_QUIZZ);
		typeBox.addItem(NLS.constants().smsPredefini_type_rappelRDV_option(),
				EpicamEnumConstants.SMSPREDEFINI_TYPE_RAPPELRDV);
		typeBox.addItem(NLS.constants()
				.smsPredefini_type_medicalRecord_option(),
				EpicamEnumConstants.SMSPREDEFINI_TYPE_MEDICALRECORD);
		typeFilterBox = new ImogFilterBox(typeBox);
		typeFilterBox.setFilterLabel(NLS.constants().smsPredefini_field_type());
		addTableFilter(typeFilterBox);

		objetBox = new TextBox();
		objetFilterBox = new ImogFilterBox(objetBox);
		objetFilterBox.setFilterLabel(NLS.constants()
				.smsPredefini_field_objet());
		addTableFilter(objetFilterBox);

		messageBox = new TextBox();
		messageFilterBox = new ImogFilterBox(messageBox);
		messageFilterBox.setFilterLabel(NLS.constants()
				.smsPredefini_field_message());
		addTableFilter(messageFilterBox);

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

		FilterCriteria typeCrit = new FilterCriteria();
		typeCrit.setField("type");
		typeCrit.setFieldDisplayName(NLS.constants().smsPredefini_field_type());
		typeCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
		typeCrit.setValue(typeBox.getValue(typeBox.getSelectedIndex()));
		typeCrit.setValueDisplayName(EpicamRenderer.get().getEnumDisplayValue(
				SmsPredefiniProxy.class, "type",
				typeBox.getValue(typeBox.getSelectedIndex())));
		criteria.add(typeCrit);

		FilterCriteria objetCrit = new FilterCriteria();
		if (locale.equals("fr"))
			objetCrit.setField("objet.francais");
		if (locale.equals("en"))
			objetCrit.setField("objet.english");
		objetCrit.setFieldDisplayName(NLS.constants()
				.smsPredefini_field_objet());
		objetCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		objetCrit.setValue(objetBox.getValue());
		objetCrit.setValueDisplayName(objetBox.getValue());
		criteria.add(objetCrit);

		FilterCriteria messageCrit = new FilterCriteria();
		if (locale.equals("fr"))
			messageCrit.setField("message.francais");
		if (locale.equals("en"))
			messageCrit.setField("message.english");
		messageCrit.setFieldDisplayName(NLS.constants()
				.smsPredefini_field_message());
		messageCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		messageCrit.setValue(messageBox.getValue());
		messageCrit.setValueDisplayName(messageBox.getValue());
		criteria.add(messageCrit);

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

		if (!AccessManager.canReadSmsPredefiniDescription())
			typeFilterBox.setVisible(false);

		if (!AccessManager.canReadSmsPredefiniDescription())
			objetFilterBox.setVisible(false);

		if (!AccessManager.canReadSmsPredefiniDescription())
			messageFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
