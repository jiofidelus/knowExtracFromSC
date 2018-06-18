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
import org.imogene.epicam.shared.proxy.PriseMedicamenteuseProxy;
import org.imogene.epicam.shared.request.PriseMedicamenteuseRequest;

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

import org.imogene.epicam.client.ui.workflow.panel.CasTuberculoseFormPanel;
import org.imogene.epicam.client.event.save.SaveCasTuberculoseEvent;
import org.imogene.epicam.client.dataprovider.CasTuberculoseDataProvider;
import org.imogene.epicam.client.ui.filter.CasTuberculoseFilterPanel;
import org.imogene.epicam.shared.proxy.CasTuberculoseProxy;
import org.imogene.epicam.client.ui.workflow.panel.CasTuberculoseFormPanel;
import org.imogene.epicam.client.event.save.SaveCasTuberculoseEvent;
import org.imogene.epicam.client.dataprovider.CasTuberculoseDataProvider;
import org.imogene.epicam.client.ui.filter.CasTuberculoseFilterPanel;
import org.imogene.epicam.shared.proxy.CasTuberculoseProxy;

/**
 * Editor that provides the UI components that allow a PriseMedicamenteuseProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class PriseMedicamenteuseEditor extends Composite
		implements
			Editor<PriseMedicamenteuseProxy>,
			HasEditorDelegate<PriseMedicamenteuseProxy>,
			HasEditorErrors<PriseMedicamenteuseProxy> {

	interface Binder extends UiBinder<Widget, PriseMedicamenteuseEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<PriseMedicamenteuseProxy> delegate;

	private PriseMedicamenteuseProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;

	/* Description section widgets */
	@UiField
	@Ignore
	FieldGroupPanel descriptionSection;
	@UiField(provided = true)
	ImogSingleRelationBox<CasTuberculoseProxy> phaseIntensive;
	private CasTuberculoseDataProvider phaseIntensiveDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<CasTuberculoseProxy> phaseContinuation;
	private CasTuberculoseDataProvider phaseContinuationDataProvider;
	@UiField
	ImogDateBox dateEffective;
	@UiField
	ImogSingleEnumBox prise;
	@UiField
	ImogBooleanBox cotrimoxazole;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public PriseMedicamenteuseEditor(EpicamRequestFactory factory,
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
	public PriseMedicamenteuseEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* Description section widgets */
		descriptionSection.setGroupTitle(NLS.constants()
				.priseMedicamenteuse_group_description());
		phaseIntensive.setLabel(NLS.constants()
				.priseMedicamenteuse_field_phaseIntensive());
		phaseContinuation.setLabel(NLS.constants()
				.priseMedicamenteuse_field_phaseContinuation());
		dateEffective.setLabel(NLS.constants()
				.priseMedicamenteuse_field_dateEffective());
		prise.setLabel(NLS.constants().priseMedicamenteuse_field_prise());
		prise.addItem(
				EpicamEnumConstants.PRISEMEDICAMENTEUSE_PRISE_PRISESUPERVISEE,
				NLS.constants()
						.priseMedicamenteuse_prise_priseSupervisee_option());
		prise.addItem(
				EpicamEnumConstants.PRISEMEDICAMENTEUSE_PRISE_AUTOMEDICATION,
				NLS.constants()
						.priseMedicamenteuse_prise_automedication_option());
		prise.addItem(EpicamEnumConstants.PRISEMEDICAMENTEUSE_PRISE_NONVENU,
				NLS.constants().priseMedicamenteuse_prise_nonVenu_option());
		cotrimoxazole.setLabel(NLS.constants()
				.priseMedicamenteuse_field_cotrimoxazole());
		cotrimoxazole.isStrict(true);

	}

	/**
	 * Configures the widgets that manage relation fields
	 */
	private void setRelationFields() {

		/* field  phaseIntensive */
		phaseIntensiveDataProvider = new CasTuberculoseDataProvider(
				requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			phaseIntensive = new ImogSingleRelationBox<CasTuberculoseProxy>(
					phaseIntensiveDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateCasTuberculose()
					&& AccessManager.canEditCasTuberculose())
				phaseIntensive = new ImogSingleRelationBox<CasTuberculoseProxy>(
						phaseIntensiveDataProvider, EpicamRenderer.get());
			else
				phaseIntensive = new ImogSingleRelationBox<CasTuberculoseProxy>(
						false, phaseIntensiveDataProvider, EpicamRenderer.get());
		}

		/* field  phaseContinuation */
		phaseContinuationDataProvider = new CasTuberculoseDataProvider(
				requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			phaseContinuation = new ImogSingleRelationBox<CasTuberculoseProxy>(
					phaseContinuationDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateCasTuberculose()
					&& AccessManager.canEditCasTuberculose())
				phaseContinuation = new ImogSingleRelationBox<CasTuberculoseProxy>(
						phaseContinuationDataProvider, EpicamRenderer.get());
			else
				phaseContinuation = new ImogSingleRelationBox<CasTuberculoseProxy>(
						false, phaseContinuationDataProvider,
						EpicamRenderer.get());
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
		phaseIntensive.setEdited(isEdited);
		phaseContinuation.setEdited(isEdited);
		dateEffective.setEdited(isEdited);
		prise.setEdited(isEdited);
		cotrimoxazole.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canReadPriseMedicamenteuseDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canEditPriseMedicamenteuseDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(PriseMedicamenteuseRequest ctx) {
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
	 * Setter to inject a CasTuberculose value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setPhaseIntensive(CasTuberculoseProxy value, boolean isLocked) {
		phaseIntensive.setLocked(isLocked);
		phaseIntensive.setValue(value);

	}

	/** Widget update for field phaseIntensive */
	private void clearPhaseIntensiveWidget() {
		phaseIntensive.clear();
	}

	/**
	 * Setter to inject a CasTuberculose value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setPhaseContinuation(CasTuberculoseProxy value, boolean isLocked) {
		phaseContinuation.setLocked(isLocked);
		phaseContinuation.setValue(value);

	}

	/** Widget update for field phaseContinuation */
	private void clearPhaseContinuationWidget() {
		phaseContinuation.clear();
	}

	/**
	 * Configures the handlers of the widgets that manage relation fields
	 */
	private void setRelationHandlers() {

		/* 'Information' button for field PhaseIntensive */
		phaseIntensive.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (phaseIntensive.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					CasTuberculoseFormPanel form = new CasTuberculoseFormPanel(
							requestFactory, phaseIntensive.getValue().getId(),
							relationPopup, "phaseIntensive");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field PhaseIntensive */
		phaseIntensive.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				CasTuberculoseFormPanel form = new CasTuberculoseFormPanel(
						requestFactory, null, relationPopup, "phaseIntensive");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a CasTuberculose is created or updated from the relation field PhaseIntensive */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveCasTuberculoseEvent.TYPE,
				new SaveCasTuberculoseEvent.Handler() {
					@Override
					public void saveCasTuberculose(CasTuberculoseProxy value) {
						phaseIntensive.setValue(value);
					}
					@Override
					public void saveCasTuberculose(CasTuberculoseProxy value,
							String initField) {
						if (initField.equals("phaseIntensive"))
							phaseIntensive.setValue(value, true);
					}
				}));

		/* 'Information' button for field PhaseContinuation */
		phaseContinuation.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (phaseContinuation.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					CasTuberculoseFormPanel form = new CasTuberculoseFormPanel(
							requestFactory, phaseContinuation.getValue()
									.getId(), relationPopup,
							"phaseContinuation");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field PhaseContinuation */
		phaseContinuation.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				CasTuberculoseFormPanel form = new CasTuberculoseFormPanel(
						requestFactory, null, relationPopup,
						"phaseContinuation");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a CasTuberculose is created or updated from the relation field PhaseContinuation */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveCasTuberculoseEvent.TYPE,
				new SaveCasTuberculoseEvent.Handler() {
					@Override
					public void saveCasTuberculose(CasTuberculoseProxy value) {
						phaseContinuation.setValue(value);
					}
					@Override
					public void saveCasTuberculose(CasTuberculoseProxy value,
							String initField) {
						if (initField.equals("phaseContinuation"))
							phaseContinuation.setValue(value, true);
					}
				}));

	}

	/**
	 * Gets the PriseMedicamenteuseProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public PriseMedicamenteuseProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the PriseMedicamenteuseProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(PriseMedicamenteuseProxy editedValue) {
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

		// dateEffective is a required field
		if (dateEffective.getValueWithoutParseException() == null
				&& dateEffective.isValid())
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"dateEffective");
		// prise is a required field
		if (prise.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"prise");
		// cotrimoxazole is a required field
		if (cotrimoxazole.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"cotrimoxazole");
	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* Description field group */
		phaseIntensive.setLabelWidth(width);
		phaseContinuation.setLabelWidth(width);
		dateEffective.setLabelWidth(width);
		prise.setLabelWidth(width);
		cotrimoxazole.setLabelWidth(width);

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* Description field group */
		phaseIntensive.setBoxWidth(width);
		phaseContinuation.setBoxWidth(width);
		prise.setBoxWidth(width);

	}

	@Override
	public void setDelegate(EditorDelegate<PriseMedicamenteuseProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> dateEffectiveFieldErrors = new ArrayList<EditorError>();
			List<EditorError> priseFieldErrors = new ArrayList<EditorError>();
			List<EditorError> cotrimoxazoleFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("dateEffective"))
						dateEffectiveFieldErrors.add(error);
					if (field.equals("prise"))
						priseFieldErrors.add(error);
					if (field.equals("cotrimoxazole"))
						cotrimoxazoleFieldErrors.add(error);

				}
			}
			if (dateEffectiveFieldErrors.size() > 0)
				dateEffective.showErrors(dateEffectiveFieldErrors);
			if (priseFieldErrors.size() > 0)
				prise.showErrors(priseFieldErrors);
			if (cotrimoxazoleFieldErrors.size() > 0)
				cotrimoxazole.showErrors(cotrimoxazoleFieldErrors);
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
