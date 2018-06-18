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
import org.imogene.epicam.shared.proxy.ExamenATBProxy;
import org.imogene.epicam.shared.request.ExamenATBRequest;

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

import org.imogene.epicam.client.ui.workflow.panel.CentreDiagTraitFormPanel;
import org.imogene.epicam.client.event.save.SaveCentreDiagTraitEvent;
import org.imogene.epicam.client.dataprovider.CentreDiagTraitDataProvider;
import org.imogene.epicam.client.ui.filter.CentreDiagTraitFilterPanel;
import org.imogene.epicam.shared.proxy.CentreDiagTraitProxy;
import org.imogene.epicam.client.ui.workflow.panel.LaboratoireReferenceFormPanel;
import org.imogene.epicam.client.event.save.SaveLaboratoireReferenceEvent;
import org.imogene.epicam.client.dataprovider.LaboratoireReferenceDataProvider;
import org.imogene.epicam.client.ui.filter.LaboratoireReferenceFilterPanel;
import org.imogene.epicam.shared.proxy.LaboratoireReferenceProxy;
import org.imogene.epicam.client.ui.workflow.panel.CasTuberculoseFormPanel;
import org.imogene.epicam.client.event.save.SaveCasTuberculoseEvent;
import org.imogene.epicam.client.dataprovider.CasTuberculoseDataProvider;
import org.imogene.epicam.client.ui.filter.CasTuberculoseFilterPanel;
import org.imogene.epicam.shared.proxy.CasTuberculoseProxy;

/**
 * Editor that provides the UI components that allow a ExamenATBProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class ExamenATBEditor extends Composite
		implements
			Editor<ExamenATBProxy>,
			HasEditorDelegate<ExamenATBProxy>,
			HasEditorErrors<ExamenATBProxy> {

	interface Binder extends UiBinder<Widget, ExamenATBEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<ExamenATBProxy> delegate;

	private ExamenATBProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;

	/* CentreExamen section widgets */
	@UiField
	@Ignore
	FieldGroupPanel centreExamenSection;
	@UiField(provided = true)
	ImogSingleRelationBox<CentreDiagTraitProxy> CDT;
	private CentreDiagTraitDataProvider cDTDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<LaboratoireReferenceProxy> laboratoireReference;
	private LaboratoireReferenceDataProvider laboratoireReferenceDataProvider;

	/* Examen section widgets */
	@UiField
	@Ignore
	FieldGroupPanel examenSection;
	@UiField(provided = true)
	ImogSingleRelationBox<CasTuberculoseProxy> casTb;
	private CasTuberculoseDataProvider casTbDataProvider;
	@UiField
	ImogDateBox dateExamen;
	@UiField
	ImogSingleEnumBox raisonDepistage;
	@UiField
	ImogTextBox resultat;
	@UiField
	ImogTextAreaBox observations;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public ExamenATBEditor(EpicamRequestFactory factory, boolean hideButtons) {

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
	public ExamenATBEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* CentreExamen section widgets */
		centreExamenSection.setGroupTitle(NLS.constants()
				.examenATB_group_centreExamen());
		CDT.setLabel(NLS.constants().examenATB_field_cDT());
		laboratoireReference.setLabel(NLS.constants()
				.examenATB_field_laboratoireReference());

		/* Examen section widgets */
		examenSection.setGroupTitle(NLS.constants().examenATB_group_examen());
		casTb.setLabel(NLS.constants().examenATB_field_casTb());
		dateExamen.setLabel(NLS.constants().examenATB_field_dateExamen());
		raisonDepistage.setLabel(NLS.constants()
				.examenATB_field_raisonDepistage());
		raisonDepistage.addItem(
				EpicamEnumConstants.EXAMENATB_RAISONDEPISTAGE_DIAGNOSTIC, NLS
						.constants()
						.examenATB_raisonDepistage_diagnostic_option());
		raisonDepistage.addItem(
				EpicamEnumConstants.EXAMENATB_RAISONDEPISTAGE_SUIVI, NLS
						.constants().examenATB_raisonDepistage_suivi_option());
		resultat.setLabel(NLS.constants().examenATB_field_resultat());
		observations.setLabel(NLS.constants().examenATB_field_observations());

	}

	/**
	 * Configures the widgets that manage relation fields
	 */
	private void setRelationFields() {

		/* field  CDT */
		cDTDataProvider = new CentreDiagTraitDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			CDT = new ImogSingleRelationBox<CentreDiagTraitProxy>(
					cDTDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateCentreDiagTrait()
					&& AccessManager.canEditCentreDiagTrait())
				CDT = new ImogSingleRelationBox<CentreDiagTraitProxy>(
						cDTDataProvider, EpicamRenderer.get());
			else
				CDT = new ImogSingleRelationBox<CentreDiagTraitProxy>(false,
						cDTDataProvider, EpicamRenderer.get());
		}

		/* field  laboratoireReference */
		laboratoireReferenceDataProvider = new LaboratoireReferenceDataProvider(
				requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			laboratoireReference = new ImogSingleRelationBox<LaboratoireReferenceProxy>(
					laboratoireReferenceDataProvider, EpicamRenderer.get(),
					true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateLaboratoireReference()
					&& AccessManager.canEditLaboratoireReference())
				laboratoireReference = new ImogSingleRelationBox<LaboratoireReferenceProxy>(
						laboratoireReferenceDataProvider, EpicamRenderer.get());
			else
				laboratoireReference = new ImogSingleRelationBox<LaboratoireReferenceProxy>(
						false, laboratoireReferenceDataProvider,
						EpicamRenderer.get());
		}

		/* field  casTb */
		casTbDataProvider = new CasTuberculoseDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			casTb = new ImogSingleRelationBox<CasTuberculoseProxy>(
					casTbDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateCasTuberculose()
					&& AccessManager.canEditCasTuberculose())
				casTb = new ImogSingleRelationBox<CasTuberculoseProxy>(
						casTbDataProvider, EpicamRenderer.get());
			else
				casTb = new ImogSingleRelationBox<CasTuberculoseProxy>(false,
						casTbDataProvider, EpicamRenderer.get());
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

		/* CentreExamen section widgets */
		CDT.setEdited(isEdited);
		laboratoireReference.setEdited(isEdited);

		/* Examen section widgets */
		casTb.setEdited(isEdited);
		dateExamen.setEdited(isEdited);
		raisonDepistage.setEdited(isEdited);
		resultat.setEdited(isEdited);
		observations.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* CentreExamen section widgets visibility */
		if (!AccessManager.canReadExamenATBCentreExamen())
			centreExamenSection.setVisible(false);

		/* Examen section widgets visibility */
		if (!AccessManager.canReadExamenATBExamen())
			examenSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* CentreExamen section widgets visibility */
		if (!AccessManager.canEditExamenATBCentreExamen())
			centreExamenSection.setVisible(false);

		/* Examen section widgets visibility */
		if (!AccessManager.canEditExamenATBExamen())
			examenSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(ExamenATBRequest ctx) {
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
	 * Setter to inject a CentreDiagTrait value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setCDT(CentreDiagTraitProxy value, boolean isLocked) {
		CDT.setLocked(isLocked);
		CDT.setValue(value);

	}

	/** Widget update for field CDT */
	private void clearCDTWidget() {
		CDT.clear();

	}

	/**
	 * Setter to inject a LaboratoireReference value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setLaboratoireReference(LaboratoireReferenceProxy value,
			boolean isLocked) {
		laboratoireReference.setLocked(isLocked);
		laboratoireReference.setValue(value);

	}

	/** Widget update for field laboratoireReference */
	private void clearLaboratoireReferenceWidget() {
		laboratoireReference.clear();

	}

	/**
	 * Setter to inject a CasTuberculose value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setCasTb(CasTuberculoseProxy value, boolean isLocked) {
		casTb.setLocked(isLocked);
		casTb.setValue(value);

	}

	/** Widget update for field casTb */
	private void clearCasTbWidget() {
		casTb.clear();
	}

	/**
	 * Configures the handlers of the widgets that manage relation fields
	 */
	private void setRelationHandlers() {

		/* 'Information' button for field CDT */
		CDT.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (CDT.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					CentreDiagTraitFormPanel form = new CentreDiagTraitFormPanel(
							requestFactory, CDT.getValue().getId(),
							relationPopup, "CDT");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field CDT */
		CDT.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				CentreDiagTraitFormPanel form = new CentreDiagTraitFormPanel(
						requestFactory, null, relationPopup, "CDT");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a CentreDiagTrait is created or updated from the relation field CDT */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveCentreDiagTraitEvent.TYPE,
				new SaveCentreDiagTraitEvent.Handler() {
					@Override
					public void saveCentreDiagTrait(CentreDiagTraitProxy value) {
						CDT.setValue(value);
					}
					@Override
					public void saveCentreDiagTrait(CentreDiagTraitProxy value,
							String initField) {
						if (initField.equals("CDT"))
							CDT.setValue(value, true);
					}
				}));

		/* 'Information' button for field LaboratoireReference */
		laboratoireReference.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (laboratoireReference.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					LaboratoireReferenceFormPanel form = new LaboratoireReferenceFormPanel(
							requestFactory, laboratoireReference.getValue()
									.getId(), relationPopup,
							"laboratoireReference");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field LaboratoireReference */
		laboratoireReference.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				LaboratoireReferenceFormPanel form = new LaboratoireReferenceFormPanel(
						requestFactory, null, relationPopup,
						"laboratoireReference");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a LaboratoireReference is created or updated from the relation field LaboratoireReference */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveLaboratoireReferenceEvent.TYPE,
				new SaveLaboratoireReferenceEvent.Handler() {
					@Override
					public void saveLaboratoireReference(
							LaboratoireReferenceProxy value) {
						laboratoireReference.setValue(value);
					}
					@Override
					public void saveLaboratoireReference(
							LaboratoireReferenceProxy value, String initField) {
						if (initField.equals("laboratoireReference"))
							laboratoireReference.setValue(value, true);
					}
				}));

		/* 'Information' button for field CasTb */
		casTb.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (casTb.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					CasTuberculoseFormPanel form = new CasTuberculoseFormPanel(
							requestFactory, casTb.getValue().getId(),
							relationPopup, "casTb");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field CasTb */
		casTb.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				CasTuberculoseFormPanel form = new CasTuberculoseFormPanel(
						requestFactory, null, relationPopup, "casTb");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a CasTuberculose is created or updated from the relation field CasTb */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveCasTuberculoseEvent.TYPE,
				new SaveCasTuberculoseEvent.Handler() {
					@Override
					public void saveCasTuberculose(CasTuberculoseProxy value) {
						casTb.setValue(value);
					}
					@Override
					public void saveCasTuberculose(CasTuberculoseProxy value,
							String initField) {
						if (initField.equals("casTb"))
							casTb.setValue(value, true);
					}
				}));

	}

	/**
	 * Gets the ExamenATBProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public ExamenATBProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the ExamenATBProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(ExamenATBProxy editedValue) {
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

		// cDT is a required field
		if (CDT.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"cDT");
		// casTb is a required field
		if (casTb.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"casTb");
		// dateExamen is a required field
		if (dateExamen.getValueWithoutParseException() == null
				&& dateExamen.isValid())
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"dateExamen");
		// raisonDepistage is a required field
		if (raisonDepistage.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"raisonDepistage");
		// resultat is a required field
		if (resultat.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"resultat");
	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* CentreExamen field group */
		CDT.setLabelWidth(width);
		laboratoireReference.setLabelWidth(width);

		/* Examen field group */
		casTb.setLabelWidth(width);
		dateExamen.setLabelWidth(width);
		raisonDepistage.setLabelWidth(width);
		resultat.setLabelWidth(width);
		observations.setLabelWidth(width);

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* CentreExamen field group */
		CDT.setBoxWidth(width);
		laboratoireReference.setBoxWidth(width);

		/* Examen field group */
		casTb.setBoxWidth(width);
		raisonDepistage.setBoxWidth(width);
		resultat.setBoxWidth(width);
		observations.setBoxWidth(width);

	}

	@Override
	public void setDelegate(EditorDelegate<ExamenATBProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> cDTFieldErrors = new ArrayList<EditorError>();
			List<EditorError> casTbFieldErrors = new ArrayList<EditorError>();
			List<EditorError> dateExamenFieldErrors = new ArrayList<EditorError>();
			List<EditorError> raisonDepistageFieldErrors = new ArrayList<EditorError>();
			List<EditorError> resultatFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("cDT"))
						cDTFieldErrors.add(error);
					if (field.equals("casTb"))
						casTbFieldErrors.add(error);
					if (field.equals("dateExamen"))
						dateExamenFieldErrors.add(error);
					if (field.equals("raisonDepistage"))
						raisonDepistageFieldErrors.add(error);
					if (field.equals("resultat"))
						resultatFieldErrors.add(error);

				}
			}
			if (cDTFieldErrors.size() > 0)
				CDT.showErrors(cDTFieldErrors);
			if (casTbFieldErrors.size() > 0)
				casTb.showErrors(casTbFieldErrors);
			if (dateExamenFieldErrors.size() > 0)
				dateExamen.showErrors(dateExamenFieldErrors);
			if (raisonDepistageFieldErrors.size() > 0)
				raisonDepistage.showErrors(raisonDepistageFieldErrors);
			if (resultatFieldErrors.size() > 0)
				resultat.showErrors(resultatFieldErrors);
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
