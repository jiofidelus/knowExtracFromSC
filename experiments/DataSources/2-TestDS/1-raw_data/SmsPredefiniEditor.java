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
import org.imogene.epicam.shared.proxy.SmsPredefiniProxy;
import org.imogene.epicam.shared.request.SmsPredefiniRequest;

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

/**
 * Editor that provides the UI components that allow a SmsPredefiniProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class SmsPredefiniEditor extends Composite
		implements
			Editor<SmsPredefiniProxy>,
			HasEditorDelegate<SmsPredefiniProxy>,
			HasEditorErrors<SmsPredefiniProxy> {

	interface Binder extends UiBinder<Widget, SmsPredefiniEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<SmsPredefiniProxy> delegate;

	private SmsPredefiniProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;
	private List<String> locales = Arrays.asList("fr", "en");

	/* Description section widgets */
	@UiField
	@Ignore
	FieldGroupPanel descriptionSection;
	@UiField
	ImogSingleEnumBox type;
	@UiField(provided = true)
	ImogLocalizedTextBox objet;
	@UiField
	ImogSingleEnumBox periodicite;
	@UiField
	ImogDateBox dateEnvoyeSMSPonctuel;
	@UiField
	ImogSingleEnumBox statut;
	@UiField(provided = true)
	ImogLocalizedTextAreaBox message;
	@UiField
	ImogTextBox reponse1;
	@UiField
	ImogTextBox reponse2;
	@UiField
	ImogTextBox bonneReponse;
	@UiField
	ImogDateBox envoyerAPartirDe;
	@UiField
	ImogDateBox arreterEnvoyerA;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public SmsPredefiniEditor(EpicamRequestFactory factory, boolean hideButtons) {

		this.requestFactory = factory;
		this.hideButtons = hideButtons;

		objet = new ImogLocalizedTextBox(locales, NLS.constants().locale());
		message = new ImogLocalizedTextAreaBox(locales, NLS.constants()
				.locale());

		initWidget(BINDER.createAndBindUi(this));

		properties();
	}

	/**
	 * Constructor
	 * @param factory the application request factory
	 */
	public SmsPredefiniEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* Description section widgets */
		descriptionSection.setGroupTitle(NLS.constants()
				.smsPredefini_group_description());
		type.setLabel(NLS.constants().smsPredefini_field_type());
		type.addItem(EpicamEnumConstants.SMSPREDEFINI_TYPE_SENSIBILISATION, NLS
				.constants().smsPredefini_type_sensibilisation_option());
		type.addItem(EpicamEnumConstants.SMSPREDEFINI_TYPE_QUIZZ, NLS
				.constants().smsPredefini_type_quizz_option());
		type.addItem(EpicamEnumConstants.SMSPREDEFINI_TYPE_RAPPELRDV, NLS
				.constants().smsPredefini_type_rappelRDV_option());
		type.addItem(EpicamEnumConstants.SMSPREDEFINI_TYPE_MEDICALRECORD, NLS
				.constants().smsPredefini_type_medicalRecord_option());
		// the value of type affects the visibility of other fields
		type.notifyChanges(requestFactory.getEventBus());
		objet.setLabel(NLS.constants().smsPredefini_field_objet());
		periodicite.setLabel(NLS.constants().smsPredefini_field_periodicite());
		periodicite.addItem(EpicamEnumConstants.SMSPREDEFINI_PERIODICITE_JOUR,
				NLS.constants().smsPredefini_periodicite_jour_option());
		periodicite.addItem(
				EpicamEnumConstants.SMSPREDEFINI_PERIODICITE_SEMAINE, NLS
						.constants().smsPredefini_periodicite_semaine_option());
		periodicite.addItem(EpicamEnumConstants.SMSPREDEFINI_PERIODICITE_MOIS,
				NLS.constants().smsPredefini_periodicite_mois_option());
		periodicite.addItem(
				EpicamEnumConstants.SMSPREDEFINI_PERIODICITE_TRIMESTRE, NLS
						.constants()
						.smsPredefini_periodicite_trimestre_option());
		periodicite
				.addItem(EpicamEnumConstants.SMSPREDEFINI_PERIODICITE_SEMESTRE,
						NLS.constants()
								.smsPredefini_periodicite_semestre_option());
		periodicite.addItem(
				EpicamEnumConstants.SMSPREDEFINI_PERIODICITE_PONCTUELLE, NLS
						.constants()
						.smsPredefini_periodicite_ponctuelle_option());
		dateEnvoyeSMSPonctuel.setLabel(NLS.constants()
				.smsPredefini_field_dateEnvoyeSMSPonctuel());
		// the visibility of dateEnvoyeSMSPonctuel depends on the value of other fields
		dateEnvoyeSMSPonctuel.addStyleName("dependentVisibility");
		statut.setLabel(NLS.constants().smsPredefini_field_statut());
		statut.addItem(EpicamEnumConstants.SMSPREDEFINI_STATUT_ACTIF, NLS
				.constants().smsPredefini_statut_actif_option());
		statut.addItem(EpicamEnumConstants.SMSPREDEFINI_STATUT_INACTIF, NLS
				.constants().smsPredefini_statut_inactif_option());
		message.setLabel(NLS.constants().smsPredefini_field_message());
		reponse1.setLabel(NLS.constants().smsPredefini_field_reponse1());
		// the visibility of reponse1 depends on the value of other fields
		reponse1.addStyleName("dependentVisibility");
		reponse2.setLabel(NLS.constants().smsPredefini_field_reponse2());
		// the visibility of reponse2 depends on the value of other fields
		reponse2.addStyleName("dependentVisibility");
		bonneReponse
				.setLabel(NLS.constants().smsPredefini_field_bonneReponse());
		// the visibility of bonneReponse depends on the value of other fields
		bonneReponse.addStyleName("dependentVisibility");
		envoyerAPartirDe.setLabel(NLS.constants()
				.smsPredefini_field_envoyerAPartirDe());
		arreterEnvoyerA.setLabel(NLS.constants()
				.smsPredefini_field_arreterEnvoyerA());

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
		type.setEdited(isEdited);
		objet.setEdited(isEdited);
		periodicite.setEdited(isEdited);
		dateEnvoyeSMSPonctuel.setEdited(isEdited);
		statut.setEdited(isEdited);
		message.setEdited(isEdited);
		reponse1.setEdited(isEdited);
		reponse2.setEdited(isEdited);
		bonneReponse.setEdited(isEdited);
		envoyerAPartirDe.setEdited(isEdited);
		arreterEnvoyerA.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canReadSmsPredefiniDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canEditSmsPredefiniDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(SmsPredefiniRequest ctx) {
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

		// the visibility of field dateEnvoyeSMSPonctuel depends on the value of other fields
		if (allValidation || source.equals(type)) {
			if ((type.getValue() != null && type.getValue().matches("5"))) {
				dateEnvoyeSMSPonctuel.setVisible(true);
			} else {
				dateEnvoyeSMSPonctuel.setVisible(false);
			}
		}

		// the visibility of field reponse1 depends on the value of other fields
		if (allValidation || source.equals(type)) {
			if ((type.getValue() != null && type.getValue().matches("1"))) {
				reponse1.setVisible(true);
			} else {
				reponse1.setVisible(false);
			}
		}

		// the visibility of field reponse2 depends on the value of other fields
		if (allValidation || source.equals(type)) {
			if ((type.getValue() != null && type.getValue().matches("1"))) {
				reponse2.setVisible(true);
			} else {
				reponse2.setVisible(false);
			}
		}

		// the visibility of field bonneReponse depends on the value of other fields
		if (allValidation || source.equals(type)) {
			if ((type.getValue() != null && type.getValue().matches("1"))) {
				bonneReponse.setVisible(true);
			} else {
				bonneReponse.setVisible(false);
			}
		}

	}

	/**
	 * Gets the SmsPredefiniProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public SmsPredefiniProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the SmsPredefiniProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(SmsPredefiniProxy editedValue) {
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

		// type is a required field
		if (type.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"type");
		// objet is a required field
		if (objet.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"objet");
		// periodicite is a required field
		if (periodicite.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"periodicite");
		// statut is a required field
		if (statut.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"statut");
		// message is a required field
		if (message.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"message");
		// envoyerAPartirDe is a required field
		if (envoyerAPartirDe.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"envoyerAPartirDe");
	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* Description field group */
		type.setLabelWidth(width);
		objet.setLabelWidth(width);
		periodicite.setLabelWidth(width);
		dateEnvoyeSMSPonctuel.setLabelWidth(width);
		statut.setLabelWidth(width);
		message.setLabelWidth(width);
		reponse1.setLabelWidth(width);
		reponse2.setLabelWidth(width);
		bonneReponse.setLabelWidth(width);
		envoyerAPartirDe.setLabelWidth(width);
		arreterEnvoyerA.setLabelWidth(width);

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* Description field group */
		type.setBoxWidth(width);
		objet.setBoxWidth(width);
		periodicite.setBoxWidth(width);
		statut.setBoxWidth(width);
		message.setBoxWidth(width);
		reponse1.setBoxWidth(width);
		reponse2.setBoxWidth(width);
		bonneReponse.setBoxWidth(width);

	}

	@Override
	public void setDelegate(EditorDelegate<SmsPredefiniProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> typeFieldErrors = new ArrayList<EditorError>();
			List<EditorError> objetFieldErrors = new ArrayList<EditorError>();
			List<EditorError> periodiciteFieldErrors = new ArrayList<EditorError>();
			List<EditorError> statutFieldErrors = new ArrayList<EditorError>();
			List<EditorError> messageFieldErrors = new ArrayList<EditorError>();
			List<EditorError> envoyerAPartirDeFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("type"))
						typeFieldErrors.add(error);
					if (field.equals("objet"))
						objetFieldErrors.add(error);
					if (field.equals("periodicite"))
						periodiciteFieldErrors.add(error);
					if (field.equals("statut"))
						statutFieldErrors.add(error);
					if (field.equals("message"))
						messageFieldErrors.add(error);
					if (field.equals("envoyerAPartirDe"))
						envoyerAPartirDeFieldErrors.add(error);

				}
			}
			if (typeFieldErrors.size() > 0)
				type.showErrors(typeFieldErrors);
			if (objetFieldErrors.size() > 0)
				objet.showErrors(objetFieldErrors);
			if (periodiciteFieldErrors.size() > 0)
				periodicite.showErrors(periodiciteFieldErrors);
			if (statutFieldErrors.size() > 0)
				statut.showErrors(statutFieldErrors);
			if (messageFieldErrors.size() > 0)
				message.showErrors(messageFieldErrors);
			if (envoyerAPartirDeFieldErrors.size() > 0)
				envoyerAPartirDe.showErrors(envoyerAPartirDeFieldErrors);
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
		setFieldValueChangeHandler();
		super.onLoad();
	}

}
