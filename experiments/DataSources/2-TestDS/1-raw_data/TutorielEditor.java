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
import org.imogene.epicam.shared.proxy.TutorielProxy;
import org.imogene.epicam.shared.request.TutorielRequest;

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
 * Editor that provides the UI components that allow a TutorielProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class TutorielEditor extends Composite
		implements
			Editor<TutorielProxy>,
			HasEditorDelegate<TutorielProxy>,
			HasEditorErrors<TutorielProxy> {

	interface Binder extends UiBinder<Widget, TutorielEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<TutorielProxy> delegate;

	private TutorielProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;
	private List<String> locales = Arrays.asList("fr", "en");

	/* Description section widgets */
	@UiField
	@Ignore
	FieldGroupPanel descriptionSection;
	@UiField
	ImogTextBox reference;
	@UiField(provided = true)
	ImogLocalizedTextBox nom;
	@UiField(provided = true)
	ImogLocalizedTextAreaBox description;
	@UiField
	ImogSingleEnumBox type;
	@UiField(provided = true)
	ImogAudioBox audioT;
	@UiField(provided = true)
	ImogVideoBox videoT;
	@UiField(provided = true)
	ImogBinaryBox textT;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public TutorielEditor(EpicamRequestFactory factory, boolean hideButtons) {

		this.requestFactory = factory;
		this.hideButtons = hideButtons;

		nom = new ImogLocalizedTextBox(locales, NLS.constants().locale());
		description = new ImogLocalizedTextAreaBox(locales, NLS.constants()
				.locale());
		audioT = new ImogAudioBox(factory);
		videoT = new ImogVideoBox(factory);
		textT = new ImogBinaryBox(factory);

		initWidget(BINDER.createAndBindUi(this));

		properties();
	}

	/**
	 * Constructor
	 * @param factory the application request factory
	 */
	public TutorielEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* Description section widgets */
		descriptionSection.setGroupTitle(NLS.constants()
				.tutoriel_group_description());
		reference.setLabel(NLS.constants().tutoriel_field_reference());
		nom.setLabel(NLS.constants().tutoriel_field_nom());
		description.setLabel(NLS.constants().tutoriel_field_description());
		type.setLabel(NLS.constants().tutoriel_field_type());
		type.addItem(EpicamEnumConstants.TUTORIEL_TYPE_TEXTE, NLS.constants()
				.tutoriel_type_texte_option());
		type.addItem(EpicamEnumConstants.TUTORIEL_TYPE_AUDIO, NLS.constants()
				.tutoriel_type_audio_option());
		type.addItem(EpicamEnumConstants.TUTORIEL_TYPE_VIDEO, NLS.constants()
				.tutoriel_type_video_option());
		// the value of type affects the visibility of other fields
		type.notifyChanges(requestFactory.getEventBus());
		audioT.setLabel(NLS.constants().tutoriel_field_audioT());
		// the visibility of audioT depends on the value of other fields
		audioT.addStyleName("dependentVisibility");
		videoT.setLabel(NLS.constants().tutoriel_field_videoT());
		// the visibility of videoT depends on the value of other fields
		videoT.addStyleName("dependentVisibility");
		textT.setLabel(NLS.constants().tutoriel_field_textT());
		// the visibility of textT depends on the value of other fields
		textT.addStyleName("dependentVisibility");

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
		reference.setEdited(isEdited);
		nom.setEdited(isEdited);
		description.setEdited(isEdited);
		type.setEdited(isEdited);
		audioT.setEdited(isEdited);
		videoT.setEdited(isEdited);
		textT.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canReadTutorielDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canEditTutorielDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(TutorielRequest ctx) {
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

		// the visibility of field audioT depends on the value of other fields
		if (allValidation || source.equals(type)) {
			if ((type.getValue() != null && type.getValue().matches("1"))) {
				audioT.setVisible(true);
			} else {
				audioT.setVisible(false);
			}
		}

		// the visibility of field videoT depends on the value of other fields
		if (allValidation || source.equals(type)) {
			if ((type.getValue() != null && type.getValue().matches("2"))) {
				videoT.setVisible(true);
			} else {
				videoT.setVisible(false);
			}
		}

		// the visibility of field textT depends on the value of other fields
		if (allValidation || source.equals(type)) {
			if ((type.getValue() != null && type.getValue().matches("0"))) {
				textT.setVisible(true);
			} else {
				textT.setVisible(false);
			}
		}

	}

	/**
	 * Gets the TutorielProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public TutorielProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the TutorielProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(TutorielProxy editedValue) {
		this.editedValue = editedValue;

	}

	/**
	 * Checks if a binary is being uploaded by the editor
	 * @return true if the editor is uploading a binary
	 */
	public boolean isUploading() {
		boolean result = false;
		if (audioT.isUploading())
			return true;
		if (videoT.isUploading())
			return true;
		if (textT.isUploading())
			return true;
		return result;
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

		// reference is a required field
		if (reference.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"reference");
		// nom is a required field
		if (nom.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"nom");
		// description is a required field
		if (description.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"description");
	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* Description field group */
		reference.setLabelWidth(width);
		nom.setLabelWidth(width);
		description.setLabelWidth(width);
		type.setLabelWidth(width);
		audioT.setLabelWidth(width);
		videoT.setLabelWidth(width);
		textT.setLabelWidth(width);

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* Description field group */
		reference.setBoxWidth(width);
		nom.setBoxWidth(width);
		description.setBoxWidth(width);
		type.setBoxWidth(width);

	}

	@Override
	public void setDelegate(EditorDelegate<TutorielProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> referenceFieldErrors = new ArrayList<EditorError>();
			List<EditorError> nomFieldErrors = new ArrayList<EditorError>();
			List<EditorError> descriptionFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("reference"))
						referenceFieldErrors.add(error);
					if (field.equals("nom"))
						nomFieldErrors.add(error);
					if (field.equals("description"))
						descriptionFieldErrors.add(error);

				}
			}
			if (referenceFieldErrors.size() > 0)
				reference.showErrors(referenceFieldErrors);
			if (nomFieldErrors.size() > 0)
				nom.showErrors(nomFieldErrors);
			if (descriptionFieldErrors.size() > 0)
				description.showErrors(descriptionFieldErrors);
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
