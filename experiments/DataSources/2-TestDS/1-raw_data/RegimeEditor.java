package org.imogene.epicam.client.ui.editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.google.web.bindery.requestfactory.shared.Receiver;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.epicam.client.ui.field.ImogLocalizedTextBox;
import org.imogene.epicam.client.ui.field.ImogLocalizedTextAreaBox;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.RegimeProxy;
import org.imogene.epicam.shared.request.RegimeRequest;

import org.imogene.epicam.shared.request.PatientRequest;
import org.imogene.web.shared.request.ImogEntityRequest;
import com.google.web.bindery.requestfactory.shared.Request;

import org.imogene.web.client.event.FieldValueChangeEvent;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.field.*;
import org.imogene.web.client.ui.field.binary.*;
import org.imogene.web.client.ui.field.group.FieldGroupPanel;
import org.imogene.web.client.ui.field.relation.multi.ImogMultiRelationBox;
import org.imogene.web.client.ui.field.relation.single.ImogSingleRelationBox;
import org.imogene.web.client.ui.panel.RelationPopupPanel;
import org.imogene.web.client.ui.panel.WrapperPanel;
import org.imogene.web.client.util.NumericUtil;
import org.imogene.web.client.util.DateUtil;
import org.imogene.web.shared.request.ImogEntityRequest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorDelegate;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;

import org.imogene.epicam.client.ui.workflow.panel.PriseMedicamentRegimeFormPanel;
import org.imogene.epicam.client.event.save.SavePriseMedicamentRegimeEvent;
import org.imogene.epicam.client.dataprovider.PriseMedicamentRegimeDataProvider;
import org.imogene.epicam.client.ui.filter.PriseMedicamentRegimeFilterPanel;
import org.imogene.epicam.shared.proxy.PriseMedicamentRegimeProxy;
import org.imogene.epicam.client.ui.editor.nested.RegimePrisesMedicamenteusesListEditor;
import org.imogene.epicam.shared.request.RegimeRequest;

/**
 * Editor that provides the UI components that allow a RegimeProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class RegimeEditor extends Composite
		implements
			Editor<RegimeProxy>,
			HasEditorDelegate<RegimeProxy>,
			HasEditorErrors<RegimeProxy> {

	interface Binder extends UiBinder<Widget, RegimeEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<RegimeProxy> delegate;

	private RegimeProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;
	private List<String> locales = Arrays.asList("fr", "en");

	/* Description section widgets */
	@UiField
	@Ignore
	FieldGroupPanel descriptionSection;
	@UiField
	ImogTextBox nom;
	@UiField
	ImogSingleEnumBox type;
	@UiField
	ImogIntegerBox dureeTraitement;
	@UiField
	ImogIntegerBox poidsMin;
	@UiField
	ImogIntegerBox poidsMax;
	@UiField(provided = true)
	ImogLocalizedTextAreaBox description;
	@UiField(provided = true)
	RegimePrisesMedicamenteusesListEditor prisesMedicamenteuses;
	private PriseMedicamentRegimeDataProvider prisesMedicamenteusesDataProvider;
	@UiField
	ImogBooleanBox actif;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public RegimeEditor(EpicamRequestFactory factory, boolean hideButtons) {

		this.requestFactory = factory;
		this.hideButtons = hideButtons;

		setRelationFields();

		description = new ImogLocalizedTextAreaBox(locales, NLS.constants()
				.locale());

		initWidget(BINDER.createAndBindUi(this));

		properties();
	}

	/**
	 * Constructor
	 * @param factory the application request factory
	 */
	public RegimeEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* Description section widgets */
		descriptionSection.setGroupTitle(NLS.constants()
				.regime_group_description());
		nom.setLabel(NLS.constants().regime_field_nom());
		type.setLabel(NLS.constants().regime_field_type());
		type.addItem(EpicamEnumConstants.REGIME_TYPE_PHASEINITIALE, NLS
				.constants().regime_type_phaseInitiale_option());
		type.addItem(EpicamEnumConstants.REGIME_TYPE_PHASECONTINUATION, NLS
				.constants().regime_type_phaseContinuation_option());
		type.addItem(EpicamEnumConstants.REGIME_TYPE_INDEPENDANT, NLS
				.constants().regime_type_independant_option());
		dureeTraitement
				.setLabel(NLS.constants().regime_field_dureeTraitement());
		dureeTraitement.setUnit("mois");
		poidsMin.setLabel(NLS.constants().regime_field_poidsMin());
		poidsMax.setLabel(NLS.constants().regime_field_poidsMax());
		description.setLabel(NLS.constants().regime_field_description());
		actif.setLabel(NLS.constants().regime_field_actif());

	}

	/**
	 * Configures the widgets that manage relation fields
	 */
	private void setRelationFields() {

		/* field  prisesMedicamenteuses */
		prisesMedicamenteuses = new RegimePrisesMedicamenteusesListEditor(
				requestFactory);

	}

	/**
	 * Sets the edition mode
	 * @param isEdited true to enable the edition of the form
	 */
	public void setEdited(boolean isEdited) {

		if (isEdited)
			setFieldEditAccess();
		else
			setFieldReadAccess();

		/* Description section widgets */
		nom.setEdited(isEdited);
		type.setEdited(isEdited);
		dureeTraitement.setEdited(isEdited);
		poidsMin.setEdited(isEdited);
		poidsMax.setEdited(isEdited);
		description.setEdited(isEdited);
		prisesMedicamenteuses.setEdited(isEdited);
		actif.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canReadRegimeDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canEditRegimeDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(RegimeRequest ctx) {
		prisesMedicamenteuses.setRequestContextForListEditors(ctx);
	}

	/**
	 * Manages editor updates when a field value changes
	 */
	private void setFieldValueChangeHandler() {

		registrations.add(requestFactory.getEventBus().addHandler(
				FieldValueChangeEvent.TYPE,
				new FieldValueChangeEvent.Handler() {
					@Override
					public void onValueChange(ImogField<?> field) {

						// field dependent visibility management
						computeVisibility(field, false);

					}
				}));
	}

	/**
	 * Computes the field visibility
	 */
	public void computeVisibility(ImogField<?> source, boolean allValidation) {
		prisesMedicamenteuses.computeVisibility(source, allValidation);

	}

	/**
	 * Configures the handlers of the widgets that manage relation fields
	 */
	private void setRelationHandlers() {

	}

	/**
	 * Gets the RegimeProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public RegimeProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the RegimeProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(RegimeProxy editedValue) {
		this.editedValue = editedValue;

	}

	/**
	 *
	 */
	public void raiseNotUniqueError(String field) {
		delegate.recordError(BaseNLS.messages().error_not_unique(), null, field);
	}

	/**
	 * Validate fields values
	 */
	public void validateFields() {

		// nom is a required field
		if (nom.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"nom");
		// type is a required field
		if (type.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"type");
		// dureeTraitement is a required field
		if (dureeTraitement.getValueWithoutParseException() == null
				&& dureeTraitement.isValid())
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"dureeTraitement");
		// dureeTraitement shall be superior or equal to '1'
		if (dureeTraitement.getValueWithoutParseException() != null
				&& !(dureeTraitement.getValueWithoutParseException() >= 1))
			delegate.recordError(
					BaseNLS.messages().error_min_num(
							NLS.constants().regime_field_dureeTraitement_min()),
					null, "dureeTraitement");

		// poidsMin is a required field
		if (poidsMin.getValueWithoutParseException() == null
				&& poidsMin.isValid())
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"poidsMin");
		// poidsMin shall be superior or equal to '0'
		if (poidsMin.getValueWithoutParseException() != null
				&& !(poidsMin.getValueWithoutParseException() >= 0))
			delegate.recordError(
					BaseNLS.messages().error_min_num(
							NLS.constants().regime_field_poidsMin_min()), null,
					"poidsMin");

		// poidsMax is a required field
		if (poidsMax.getValueWithoutParseException() == null
				&& poidsMax.isValid())
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"poidsMax");
		// poidsMax shall be superior or equal to '1'
		if (poidsMax.getValueWithoutParseException() != null
				&& !(poidsMax.getValueWithoutParseException() >= 1))
			delegate.recordError(
					BaseNLS.messages().error_min_num(
							NLS.constants().regime_field_poidsMax_min()), null,
					"poidsMax");

		// prisesMedicamenteuses nested form shall be validated
		prisesMedicamenteuses.validateFields();
	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* Description field group */
		nom.setLabelWidth(width);
		type.setLabelWidth(width);
		dureeTraitement.setLabelWidth(width);
		poidsMin.setLabelWidth(width);
		poidsMax.setLabelWidth(width);
		description.setLabelWidth(width);
		actif.setLabelWidth(width);

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* Description field group */
		nom.setBoxWidth(width);
		type.setBoxWidth(width);
		description.setBoxWidth(width);

	}

	@Override
	public void setDelegate(EditorDelegate<RegimeProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> nomFieldErrors = new ArrayList<EditorError>();
			List<EditorError> typeFieldErrors = new ArrayList<EditorError>();
			List<EditorError> dureeTraitementFieldErrors = new ArrayList<EditorError>();
			List<EditorError> poidsMinFieldErrors = new ArrayList<EditorError>();
			List<EditorError> poidsMaxFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("nom"))
						nomFieldErrors.add(error);
					if (field.equals("type"))
						typeFieldErrors.add(error);
					if (field.equals("dureeTraitement"))
						dureeTraitementFieldErrors.add(error);
					if (field.equals("poidsMin"))
						poidsMinFieldErrors.add(error);
					if (field.equals("poidsMax"))
						poidsMaxFieldErrors.add(error);

				}
			}
			if (nomFieldErrors.size() > 0)
				nom.showErrors(nomFieldErrors);
			if (typeFieldErrors.size() > 0)
				type.showErrors(typeFieldErrors);
			if (dureeTraitementFieldErrors.size() > 0)
				dureeTraitement.showErrors(dureeTraitementFieldErrors);
			if (poidsMinFieldErrors.size() > 0)
				poidsMin.showErrors(poidsMinFieldErrors);
			if (poidsMaxFieldErrors.size() > 0)
				poidsMax.showErrors(poidsMaxFieldErrors);
		}
	}

	@Override
	protected void onUnload() {
		for (HandlerRegistration r : registrations)
			r.removeHandler();
		registrations.clear();
		super.onUnload();
	}

	@Override
	protected void onLoad() {
		setRelationHandlers();
		setFieldValueChangeHandler();
		super.onLoad();
	}

}
