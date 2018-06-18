package org.imogene.epicam.client.ui.editor.nested;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.epicam.client.ui.field.ImogLocalizedTextBox;
import org.imogene.epicam.client.ui.field.ImogLocalizedTextAreaBox;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.CasIndexProxy;

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
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;

import org.imogene.epicam.client.ui.workflow.panel.PatientFormPanel;
import org.imogene.epicam.client.event.save.SavePatientEvent;
import org.imogene.epicam.client.dataprovider.PatientDataProvider;
import org.imogene.epicam.client.ui.filter.PatientFilterPanel;
import org.imogene.epicam.shared.proxy.PatientProxy;

/**
 * Editor that provides the UI components that allow a CasIndexProxy to be viewed and edited
 * as a Nested Form
 * @author MEDES-IMPS
 */
public class CasIndexEditorNestedForm extends Composite
		implements
			Editor<CasIndexProxy>,
			HasEditorDelegate<CasIndexProxy>,
			HasEditorErrors<CasIndexProxy> {

	interface Binder extends UiBinder<Widget, CasIndexEditorNestedForm> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<CasIndexProxy> delegate;

	private boolean hideButtons = false;
	private int index = 0;
	private boolean isNewProxy = false;
	private boolean isClearImageActive = false;

	@UiField
	Image clearImage;

	@UiField
	@Ignore
	FieldGroupPanel casIndexSection;
	@UiField(provided = true)
	ImogSingleRelationBox<PatientProxy> patientLie;
	private PatientDataProvider patientLieDataProvider;
	@UiField
	ImogTextBox typeRelation;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public CasIndexEditorNestedForm(EpicamRequestFactory factory,
			boolean hideButtons) {

		this.requestFactory = factory;
		this.hideButtons = hideButtons;

		setRelationFields();

		initWidget(BINDER.createAndBindUi(this));

		clearImage.setTitle(BaseNLS.constants().button_remove());
		clearImage
				.setUrl(GWT.getModuleBaseURL() + "images/relation_remove.png");

		properties();
	}

	/**
	 * Constructor
	 * @param factory the application request factory
	 */
	public CasIndexEditorNestedForm(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	public void properties() {

		casIndexSection.setGroupTitle(NLS.constants().casIndex_name());
		casIndexSection.setLabelFontSize("12px");

		patientLie.setLabel(NLS.constants().casIndex_field_patientLie());
		typeRelation.setLabel(NLS.constants().casIndex_field_typeRelation());
	}

	/**
	 * Configures the widgets that manage relation fields
	 */
	public void setRelationFields() {

		/* field  patientLie */
		patientLieDataProvider = new PatientDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			patientLie = new ImogSingleRelationBox<PatientProxy>(
					patientLieDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreatePatient()
					&& AccessManager.canEditPatient())
				patientLie = new ImogSingleRelationBox<PatientProxy>(
						patientLieDataProvider, EpicamRenderer.get());
			else
				patientLie = new ImogSingleRelationBox<PatientProxy>(false,
						patientLieDataProvider, EpicamRenderer.get());
		}

	}

	/**
	 * Sets the edition mode
	 * @param isEdited true to enable the edition of the form
	 */
	public void setEdited(boolean isEdited) {

		if (isClearImageActive)
			clearImage.setVisible(isEdited);
		else
			clearImage.setVisible(false);

		if (isEdited)
			setFieldEditAccess();
		else
			setFieldReadAccess();

		patientLie.setEdited(isEdited);
		typeRelation.setEdited(isEdited);
	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	public void setFieldReadAccess() {

		if (!AccessManager.canReadCasIndexDescription())
			patientLie.setVisible(false);

		if (!AccessManager.canReadCasIndexDescription())
			typeRelation.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	public void setFieldEditAccess() {

		if (!AccessManager.canEditCasIndexDescription())
			patientLie.setVisible(false);

		if (!AccessManager.canEditCasIndexDescription())
			typeRelation.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors
	 */
	public void setRequestContextForListEditors(ImogEntityRequest ctx) {
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

	public void setDeleteClickHandler(ClickHandler handler) {
		//registrations.add(clearImage.addClickHandler(handler));
		clearImage.addClickHandler(handler);
		isClearImageActive = true;
	}

	/**
	 * Setter to inject a Patient value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setPatientLie(PatientProxy value, boolean isLocked) {
		patientLie.setLocked(isLocked);
		patientLie.setValue(value);

	}

	/** Widget update for field patientLie */
	private void clearPatientLieWidget() {
		patientLie.clear();
	}

	/**
	 * Configures the handlers of the widgets that manage relation fields
	 */
	private void setRelationHandlers() {

		/* 'Information' button for field PatientLie */
		patientLie.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (patientLie.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					PatientFormPanel form = new PatientFormPanel(
							requestFactory, patientLie.getValue().getId(),
							relationPopup, "patientLie");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field PatientLie */
		patientLie.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				PatientFormPanel form = new PatientFormPanel(requestFactory,
						null, relationPopup, "patientLie");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a Patient is created or updated from the relation field PatientLie */
		registrations.add(requestFactory.getEventBus().addHandler(
				SavePatientEvent.TYPE, new SavePatientEvent.Handler() {
					@Override
					public void savePatient(PatientProxy value) {
						patientLie.setValue(value);
					}
					@Override
					public void savePatient(PatientProxy value, String initField) {
						if (initField.equals("patientLie"))
							patientLie.setValue(value, true);
					}
				}));

	}

	public void setIndex(int newIndex) {
		this.index = newIndex;
	}

	public int getIndex() {
		return index;
	}

	public boolean isNewProxy() {
		return isNewProxy;
	}

	public void setNewProxy(boolean isNewProxy) {
		this.isNewProxy = isNewProxy;
	}

	/**
	 * Validate fields values
	 */
	public void validateFields() {

		// patientLie is a required field
		if (patientLie.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"patientLie");
		// typeRelation is a required field
		if (typeRelation.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"typeRelation");
	}

	/**
	 */
	public void updatecasIndexSectionLabel(String label) {
		casIndexSection.setGroupTitle(label);
	}

	@Override
	public void setDelegate(EditorDelegate<CasIndexProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> patientLieFieldErrors = new ArrayList<EditorError>();
			List<EditorError> typeRelationFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("patientLie"))
						patientLieFieldErrors.add(error);
					if (field.equals("typeRelation"))
						typeRelationFieldErrors.add(error);
				}
			}
			if (patientLieFieldErrors.size() > 0)
				patientLie.showErrors(patientLieFieldErrors);
			if (typeRelationFieldErrors.size() > 0)
				typeRelation.showErrors(typeRelationFieldErrors);
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
