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
import org.imogene.epicam.shared.proxy.QualificationProxy;
import org.imogene.epicam.shared.request.QualificationRequest;

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
 * Editor that provides the UI components that allow a QualificationProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class QualificationEditor extends Composite
		implements
			Editor<QualificationProxy>,
			HasEditorDelegate<QualificationProxy>,
			HasEditorErrors<QualificationProxy> {

	interface Binder extends UiBinder<Widget, QualificationEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<QualificationProxy> delegate;

	private QualificationProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;
	private List<String> locales = Arrays.asList("fr", "en");

	/* Description section widgets */
	@UiField
	@Ignore
	FieldGroupPanel descriptionSection;
	@UiField
	ImogTextBox code;
	@UiField(provided = true)
	ImogLocalizedTextBox nom;
	@UiField(provided = true)
	ImogLocalizedTextAreaBox description;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public QualificationEditor(EpicamRequestFactory factory, boolean hideButtons) {

		this.requestFactory = factory;
		this.hideButtons = hideButtons;

		nom = new ImogLocalizedTextBox(locales, NLS.constants().locale());
		description = new ImogLocalizedTextAreaBox(locales, NLS.constants()
				.locale());

		initWidget(BINDER.createAndBindUi(this));

		properties();
	}

	/**
	 * Constructor
	 * @param factory the application request factory
	 */
	public QualificationEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* Description section widgets */
		descriptionSection.setGroupTitle(NLS.constants()
				.qualification_group_description());
		code.setLabel(NLS.constants().qualification_field_code());
		nom.setLabel(NLS.constants().qualification_field_nom());
		description.setLabel(NLS.constants().qualification_field_description());

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
		code.setEdited(isEdited);
		nom.setEdited(isEdited);
		description.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canReadQualificationDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canEditQualificationDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(QualificationRequest ctx) {
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

	}

	/**
	 * Gets the QualificationProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public QualificationProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the QualificationProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(QualificationProxy editedValue) {
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

		// code is a required field
		if (code.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"code");
		// nom is a required field
		if (nom.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"nom");
	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* Description field group */
		code.setLabelWidth(width);
		nom.setLabelWidth(width);
		description.setLabelWidth(width);

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* Description field group */
		code.setBoxWidth(width);
		nom.setBoxWidth(width);
		description.setBoxWidth(width);

	}

	@Override
	public void setDelegate(EditorDelegate<QualificationProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> codeFieldErrors = new ArrayList<EditorError>();
			List<EditorError> nomFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("code"))
						codeFieldErrors.add(error);
					if (field.equals("nom"))
						nomFieldErrors.add(error);

				}
			}
			if (codeFieldErrors.size() > 0)
				code.showErrors(codeFieldErrors);
			if (nomFieldErrors.size() > 0)
				nom.showErrors(nomFieldErrors);
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
