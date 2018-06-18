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
import org.imogene.epicam.shared.proxy.ExamenSerologieProxy;
import org.imogene.epicam.shared.request.ExamenSerologieRequest;

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

import org.imogene.epicam.client.ui.workflow.panel.PatientFormPanel;
import org.imogene.epicam.client.event.save.SavePatientEvent;
import org.imogene.epicam.client.dataprovider.PatientDataProvider;
import org.imogene.epicam.client.ui.filter.PatientFilterPanel;
import org.imogene.epicam.shared.proxy.PatientProxy;

/**
 * Editor that provides the UI components that allow a ExamenSerologieProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class ExamenSerologieEditor extends Composite
		implements
			Editor<ExamenSerologieProxy>,
			HasEditorDelegate<ExamenSerologieProxy>,
			HasEditorErrors<ExamenSerologieProxy> {

	interface Binder extends UiBinder<Widget, ExamenSerologieEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<ExamenSerologieProxy> delegate;

	private ExamenSerologieProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;

	/* Description section widgets */
	@UiField
	@Ignore
	FieldGroupPanel descriptionSection;
	@UiField(provided = true)
	ImogSingleRelationBox<PatientProxy> patient;
	private PatientDataProvider patientDataProvider;
	@UiField
	ImogDateBox dateTest;
	@UiField
	ImogSingleEnumBox nature;
	@UiField
	ImogSingleEnumBox resultatVIH;
	@UiField
	ImogIntegerBox resultatCD4;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public ExamenSerologieEditor(EpicamRequestFactory factory,
			boolean hideButtons) {

		this.requestFactory = factory;
		this.hideButtons = hideButtons;

		setRelationFields();

		initWidget(BINDER.createAndBindUi(this));

		properties();
	}

	/**
	 * Constructor
	 * @param factory the application request factory
	 */
	public ExamenSerologieEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* Description section widgets */
		descriptionSection.setGroupTitle(NLS.constants()
				.examenSerologie_group_description());
		patient.setLabel(NLS.constants().examenSerologie_field_patient());
		dateTest.setLabel(NLS.constants().examenSerologie_field_dateTest());
		nature.setLabel(NLS.constants().examenSerologie_field_nature());
		nature.addItem(EpicamEnumConstants.EXAMENSEROLOGIE_NATURE_VIH, NLS
				.constants().examenSerologie_nature_vIH_option());
		nature.addItem(EpicamEnumConstants.EXAMENSEROLOGIE_NATURE_CD4, NLS
				.constants().examenSerologie_nature_cD4_option());
		// the value of nature affects the visibility of other fields
		nature.notifyChanges(requestFactory.getEventBus());
		resultatVIH.setLabel(NLS.constants()
				.examenSerologie_field_resultatVIH());
		resultatVIH.addItem(
				EpicamEnumConstants.EXAMENSEROLOGIE_RESULTATVIH_POSITIF, NLS
						.constants()
						.examenSerologie_resultatVIH_positif_option());
		resultatVIH.addItem(
				EpicamEnumConstants.EXAMENSEROLOGIE_RESULTATVIH_NEGATIF, NLS
						.constants()
						.examenSerologie_resultatVIH_negatif_option());
		// the visibility of resultatVIH depends on the value of other fields
		resultatVIH.addStyleName("dependentVisibility");
		resultatCD4.setLabel(NLS.constants()
				.examenSerologie_field_resultatCD4());
		// the visibility of resultatCD4 depends on the value of other fields
		resultatCD4.addStyleName("dependentVisibility");

	}

	/**
	 * Configures the widgets that manage relation fields
	 */
	private void setRelationFields() {

		/* field  patient */
		patientDataProvider = new PatientDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			patient = new ImogSingleRelationBox<PatientProxy>(
					patientDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreatePatient()
					&& AccessManager.canEditPatient())
				patient = new ImogSingleRelationBox<PatientProxy>(
						patientDataProvider, EpicamRenderer.get());
			else
				patient = new ImogSingleRelationBox<PatientProxy>(false,
						patientDataProvider, EpicamRenderer.get());
		}

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
		patient.setEdited(isEdited);
		dateTest.setEdited(isEdited);
		nature.setEdited(isEdited);
		resultatVIH.setEdited(isEdited);
		resultatCD4.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canReadExamenSerologieDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canEditExamenSerologieDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(ExamenSerologieRequest ctx) {
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

		// the visibility of field resultatVIH depends on the value of other fields
		if (allValidation || source.equals(nature)) {
			if ((nature.getValue() != null && nature.getValue().matches("0"))) {
				resultatVIH.setVisible(true);
			} else {
				resultatVIH.setVisible(false);
			}
		}

		// the visibility of field resultatCD4 depends on the value of other fields
		if (allValidation || source.equals(nature)) {
			if ((nature.getValue() != null && nature.getValue().matches("1"))) {
				resultatCD4.setVisible(true);
			} else {
				resultatCD4.setVisible(false);
			}
		}

	}

	/**
	 * Setter to inject a Patient value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setPatient(PatientProxy value, boolean isLocked) {
		patient.setLocked(isLocked);
		patient.setValue(value);

	}

	/** Widget update for field patient */
	private void clearPatientWidget() {
		patient.clear();
	}

	/**
	 * Configures the handlers of the widgets that manage relation fields
	 */
	private void setRelationHandlers() {

		/* 'Information' button for field Patient */
		patient.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (patient.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					PatientFormPanel form = new PatientFormPanel(
							requestFactory, patient.getValue().getId(),
							relationPopup, "patient");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field Patient */
		patient.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				PatientFormPanel form = new PatientFormPanel(requestFactory,
						null, relationPopup, "patient");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a Patient is created or updated from the relation field Patient */
		registrations.add(requestFactory.getEventBus().addHandler(
				SavePatientEvent.TYPE, new SavePatientEvent.Handler() {
					@Override
					public void savePatient(PatientProxy value) {
						patient.setValue(value);
					}
					@Override
					public void savePatient(PatientProxy value, String initField) {
						if (initField.equals("patient"))
							patient.setValue(value, true);
					}
				}));

	}

	/**
	 * Gets the ExamenSerologieProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public ExamenSerologieProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the ExamenSerologieProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(ExamenSerologieProxy editedValue) {
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

		// patient is a required field
		if (patient.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"patient");
		// dateTest is a required field
		if (dateTest.getValueWithoutParseException() == null
				&& dateTest.isValid())
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"dateTest");
		// nature is a required field
		if (nature.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"nature");

	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* Description field group */
		patient.setLabelWidth(width);
		dateTest.setLabelWidth(width);
		nature.setLabelWidth(width);
		resultatVIH.setLabelWidth(width);
		resultatCD4.setLabelWidth(width);

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* Description field group */
		patient.setBoxWidth(width);
		nature.setBoxWidth(width);
		resultatVIH.setBoxWidth(width);

	}

	@Override
	public void setDelegate(EditorDelegate<ExamenSerologieProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> patientFieldErrors = new ArrayList<EditorError>();
			List<EditorError> dateTestFieldErrors = new ArrayList<EditorError>();
			List<EditorError> natureFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("patient"))
						patientFieldErrors.add(error);
					if (field.equals("dateTest"))
						dateTestFieldErrors.add(error);
					if (field.equals("nature"))
						natureFieldErrors.add(error);

				}
			}
			if (patientFieldErrors.size() > 0)
				patient.showErrors(patientFieldErrors);
			if (dateTestFieldErrors.size() > 0)
				dateTest.showErrors(dateTestFieldErrors);
			if (natureFieldErrors.size() > 0)
				nature.showErrors(natureFieldErrors);
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
