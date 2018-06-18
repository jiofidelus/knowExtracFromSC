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
import org.imogene.epicam.shared.proxy.CasTuberculoseProxy;
import org.imogene.epicam.shared.request.CasTuberculoseRequest;

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
import org.imogene.epicam.client.ui.workflow.panel.ExamenMicroscopieFormPanel;
import org.imogene.epicam.client.event.save.SaveExamenMicroscopieEvent;
import org.imogene.epicam.client.dataprovider.ExamenMicroscopieDataProvider;
import org.imogene.epicam.client.ui.filter.ExamenMicroscopieFilterPanel;
import org.imogene.epicam.shared.proxy.ExamenMicroscopieProxy;
import org.imogene.epicam.client.ui.workflow.panel.ExamenATBFormPanel;
import org.imogene.epicam.client.event.save.SaveExamenATBEvent;
import org.imogene.epicam.client.dataprovider.ExamenATBDataProvider;
import org.imogene.epicam.client.ui.filter.ExamenATBFilterPanel;
import org.imogene.epicam.shared.proxy.ExamenATBProxy;
import org.imogene.epicam.client.ui.workflow.panel.RegimeFormPanel;
import org.imogene.epicam.client.event.save.SaveRegimeEvent;
import org.imogene.epicam.client.dataprovider.RegimeDataProvider;
import org.imogene.epicam.client.ui.filter.RegimeFilterPanel;
import org.imogene.epicam.shared.proxy.RegimeProxy;
import org.imogene.epicam.client.ui.workflow.panel.RegimeFormPanel;
import org.imogene.epicam.client.event.save.SaveRegimeEvent;
import org.imogene.epicam.client.dataprovider.RegimeDataProvider;
import org.imogene.epicam.client.ui.filter.RegimeFilterPanel;
import org.imogene.epicam.shared.proxy.RegimeProxy;
import org.imogene.epicam.client.ui.workflow.panel.PriseMedicamenteuseFormPanel;
import org.imogene.epicam.client.event.save.SavePriseMedicamenteuseEvent;
import org.imogene.epicam.client.dataprovider.PriseMedicamenteuseDataProvider;
import org.imogene.epicam.client.ui.filter.PriseMedicamenteuseFilterPanel;
import org.imogene.epicam.shared.proxy.PriseMedicamenteuseProxy;
import org.imogene.epicam.client.ui.editor.nested.CasTuberculosePriseMedicamenteusePhaseInitialeListEditor;
import org.imogene.epicam.shared.request.CasTuberculoseRequest;
import org.imogene.epicam.client.ui.workflow.panel.PriseMedicamenteuseFormPanel;
import org.imogene.epicam.client.event.save.SavePriseMedicamenteuseEvent;
import org.imogene.epicam.client.dataprovider.PriseMedicamenteuseDataProvider;
import org.imogene.epicam.client.ui.filter.PriseMedicamenteuseFilterPanel;
import org.imogene.epicam.shared.proxy.PriseMedicamenteuseProxy;
import org.imogene.epicam.client.ui.editor.nested.CasTuberculosePriseMedicamenteusePhaseContinuationListEditor;
import org.imogene.epicam.shared.request.CasTuberculoseRequest;
import org.imogene.epicam.client.ui.workflow.panel.RendezVousFormPanel;
import org.imogene.epicam.client.event.save.SaveRendezVousEvent;
import org.imogene.epicam.client.dataprovider.RendezVousDataProvider;
import org.imogene.epicam.client.ui.filter.RendezVousFilterPanel;
import org.imogene.epicam.shared.proxy.RendezVousProxy;
import org.imogene.epicam.client.ui.editor.nested.CasTuberculoseRendezVousListEditor;
import org.imogene.epicam.shared.request.CasTuberculoseRequest;

/**
 * Editor that provides the UI components that allow a CasTuberculoseProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class CasTuberculoseEditor extends Composite
		implements
			Editor<CasTuberculoseProxy>,
			HasEditorDelegate<CasTuberculoseProxy>,
			HasEditorErrors<CasTuberculoseProxy> {

	interface Binder extends UiBinder<Widget, CasTuberculoseEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<CasTuberculoseProxy> delegate;

	private CasTuberculoseProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;

	/* Informations section widgets */
	@UiField
	@Ignore
	FieldGroupPanel informationsSection;
	@UiField
	ImogTextBox identifiant;
	@UiField
	ImogTextBox numRegTB;
	@UiField(provided = true)
	ImogSingleRelationBox<PatientProxy> patient;
	private PatientDataProvider patientDataProvider;
	@UiField
	ImogDateBox dateDebutTraitement;
	@UiField
	ImogSingleEnumBox typePatient;
	@UiField
	ImogTextAreaBox typePatientPrecisions;
	@UiField
	ImogSingleEnumBox formeMaladie;
	@UiField
	ImogTextAreaBox extraPulmonairePrecisions;
	@UiField
	ImogSingleEnumBox cotrimoxazole;
	@UiField
	ImogBooleanBox antiRetroViraux;
	@UiField
	ImogBooleanBox fumeur;
	@UiField
	ImogBooleanBox fumeurArreter;

	/* Examen section widgets */
	@UiField
	@Ignore
	FieldGroupPanel examenSection;
	@UiField(provided = true)
	ImogMultiRelationBox<ExamenMicroscopieProxy> examensMiscrocopies;
	private ExamenMicroscopieDataProvider examensMiscrocopiesDataProvider;
	@UiField(provided = true)
	ImogMultiRelationBox<ExamenATBProxy> examensATB;
	private ExamenATBDataProvider examensATBDataProvider;

	/* Traitement section widgets */
	@UiField
	@Ignore
	FieldGroupPanel traitementSection;
	@UiField(provided = true)
	ImogSingleRelationBox<RegimeProxy> regimePhaseInitiale;
	private RegimeDataProvider regimePhaseInitialeDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<RegimeProxy> regimePhaseContinuation;
	private RegimeDataProvider regimePhaseContinuationDataProvider;
	@UiField(provided = true)
	CasTuberculosePriseMedicamenteusePhaseInitialeListEditor priseMedicamenteusePhaseInitiale;
	private PriseMedicamenteuseDataProvider priseMedicamenteusePhaseInitialeDataProvider;
	@UiField(provided = true)
	CasTuberculosePriseMedicamenteusePhaseContinuationListEditor priseMedicamenteusePhaseContinuation;
	private PriseMedicamenteuseDataProvider priseMedicamenteusePhaseContinuationDataProvider;
	@UiField(provided = true)
	CasTuberculoseRendezVousListEditor rendezVous;
	private RendezVousDataProvider rendezVousDataProvider;

	/* FinTraitement section widgets */
	@UiField
	@Ignore
	FieldGroupPanel finTraitementSection;
	@UiField
	ImogDateBox dateFinTraitement;
	@UiField
	ImogSingleEnumBox devenirMalade;
	@UiField
	ImogTextAreaBox observation;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public CasTuberculoseEditor(EpicamRequestFactory factory,
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
	public CasTuberculoseEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* Informations section widgets */
		informationsSection.setGroupTitle(NLS.constants()
				.casTuberculose_group_informations());
		identifiant
				.setLabel(NLS.constants().casTuberculose_field_identifiant());
		numRegTB.setLabel(NLS.constants().casTuberculose_field_numRegTB());
		patient.setLabel(NLS.constants().casTuberculose_field_patient());
		dateDebutTraitement.setLabel(NLS.constants()
				.casTuberculose_field_dateDebutTraitement());
		typePatient
				.setLabel(NLS.constants().casTuberculose_field_typePatient());
		typePatient.addItem(
				EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_NOUVEAUCAS, NLS
						.constants()
						.casTuberculose_typePatient_nouveauCas_option());
		typePatient
				.addItem(
						EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_REPRISEAPRESABANDON,
						NLS.constants()
								.casTuberculose_typePatient_repriseApresAbandon_option());
		typePatient.addItem(
				EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_ECHEC, NLS
						.constants().casTuberculose_typePatient_echec_option());
		typePatient.addItem(
				EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_RECHUTE, NLS
						.constants()
						.casTuberculose_typePatient_rechute_option());
		typePatient.addItem(
				EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_AUTRE, NLS
						.constants().casTuberculose_typePatient_autre_option());
		// the value of typePatient affects the visibility of other fields
		typePatient.notifyChanges(requestFactory.getEventBus());
		typePatientPrecisions.setLabel(NLS.constants()
				.casTuberculose_field_typePatientPrecisions());
		// the visibility of typePatientPrecisions depends on the value of other fields
		typePatientPrecisions.addStyleName("dependentVisibility");
		formeMaladie.setLabel(NLS.constants()
				.casTuberculose_field_formeMaladie());
		formeMaladie.addItem(
				EpicamEnumConstants.CASTUBERCULOSE_FORMEMALADIE_TPMPLUS, NLS
						.constants()
						.casTuberculose_formeMaladie_tPMPlus_option());
		formeMaladie.addItem(
				EpicamEnumConstants.CASTUBERCULOSE_FORMEMALADIE_TPMMOINS, NLS
						.constants()
						.casTuberculose_formeMaladie_tPMMoins_option());
		formeMaladie
				.addItem(
						EpicamEnumConstants.CASTUBERCULOSE_FORMEMALADIE_EXTRA_PULMONAIRE,
						NLS.constants()
								.casTuberculose_formeMaladie_extra_Pulmonaire_option());
		// the value of formeMaladie affects the visibility of other fields
		formeMaladie.notifyChanges(requestFactory.getEventBus());
		extraPulmonairePrecisions.setLabel(NLS.constants()
				.casTuberculose_field_extraPulmonairePrecisions());
		// the visibility of extraPulmonairePrecisions depends on the value of other fields
		extraPulmonairePrecisions.addStyleName("dependentVisibility");
		cotrimoxazole.setLabel(NLS.constants()
				.casTuberculose_field_cotrimoxazole());
		cotrimoxazole.addItem(
				EpicamEnumConstants.CASTUBERCULOSE_COTRIMOXAZOLE_NON, NLS
						.constants().casTuberculose_cotrimoxazole_non_option());
		cotrimoxazole
				.addItem(
						EpicamEnumConstants.CASTUBERCULOSE_COTRIMOXAZOLE_COTRIMOXAZOLE_960,
						NLS.constants()
								.casTuberculose_cotrimoxazole_cotrimoxazole_960_option());
		cotrimoxazole
				.addItem(
						EpicamEnumConstants.CASTUBERCULOSE_COTRIMOXAZOLE_COTRIMOXAZOLE_480,
						NLS.constants()
								.casTuberculose_cotrimoxazole_cotrimoxazole_480_option());
		antiRetroViraux.setLabel(NLS.constants()
				.casTuberculose_field_antiRetroViraux());
		fumeur.setLabel(NLS.constants().casTuberculose_field_fumeur());
		// the value of fumeur affects the visibility of other fields
		fumeur.notifyChanges(requestFactory.getEventBus());
		fumeurArreter.setLabel(NLS.constants()
				.casTuberculose_field_fumeurArreter());
		// the visibility of fumeurArreter depends on the value of other fields
		fumeurArreter.addStyleName("dependentVisibility");

		/* Examen section widgets */
		examenSection.setGroupTitle(NLS.constants()
				.casTuberculose_group_examen());
		examensMiscrocopies.setLabel(NLS.constants()
				.casTuberculose_field_examensMiscrocopies());
		examensATB.setLabel(NLS.constants().casTuberculose_field_examensATB());

		/* Traitement section widgets */
		traitementSection.setGroupTitle(NLS.constants()
				.casTuberculose_group_traitement());
		regimePhaseInitiale.setLabel(NLS.constants()
				.casTuberculose_field_regimePhaseInitiale());
		regimePhaseContinuation.setLabel(NLS.constants()
				.casTuberculose_field_regimePhaseContinuation());

		/* FinTraitement section widgets */
		finTraitementSection.setGroupTitle(NLS.constants()
				.casTuberculose_group_finTraitement());
		dateFinTraitement.setLabel(NLS.constants()
				.casTuberculose_field_dateFinTraitement());
		devenirMalade.setLabel(NLS.constants()
				.casTuberculose_field_devenirMalade());
		devenirMalade.addItem(
				EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_GUERRIS, NLS
						.constants()
						.casTuberculose_devenirMalade_guerris_option());
		devenirMalade.addItem(
				EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_TERMINE, NLS
						.constants()
						.casTuberculose_devenirMalade_termine_option());
		devenirMalade.addItem(
				EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_ECHEC, NLS
						.constants()
						.casTuberculose_devenirMalade_echec_option());
		devenirMalade.addItem(
				EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_DECEDE, NLS
						.constants()
						.casTuberculose_devenirMalade_decede_option());
		devenirMalade.addItem(
				EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_PERDUDEVUE,
				NLS.constants()
						.casTuberculose_devenirMalade_perduDeVue_option());
		devenirMalade
				.addItem(
						EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_ARRETPRESCRIPTEUR,
						NLS.constants()
								.casTuberculose_devenirMalade_arretPrescripteur_option());
		devenirMalade
				.addItem(
						EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_ARRETEFFETSINDESI,
						NLS.constants()
								.casTuberculose_devenirMalade_arretEffetsIndesi_option());
		devenirMalade
				.addItem(
						EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_ARRETSURVENUTB,
						NLS.constants()
								.casTuberculose_devenirMalade_arretSurvenuTB_option());
		observation
				.setLabel(NLS.constants().casTuberculose_field_observation());

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

		/* field  examensMiscrocopies */
		examensMiscrocopiesDataProvider = new ExamenMicroscopieDataProvider(
				requestFactory, "casTb");
		if (hideButtons) // in popup, relation buttons hidden		
			examensMiscrocopies = new ImogMultiRelationBox<ExamenMicroscopieProxy>(
					examensMiscrocopiesDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled
			if (AccessManager.canCreateExamenMicroscopie()
					&& AccessManager.canEditExamenMicroscopie())
				examensMiscrocopies = new ImogMultiRelationBox<ExamenMicroscopieProxy>(
						examensMiscrocopiesDataProvider, EpicamRenderer.get(),
						null);
			else
				examensMiscrocopies = new ImogMultiRelationBox<ExamenMicroscopieProxy>(
						false, examensMiscrocopiesDataProvider,
						EpicamRenderer.get(), null);
		}
		examensMiscrocopies.setPopUpTitle(NLS.constants()
				.examenMicroscopie_select_title());
		examensMiscrocopies.setFilterPanel(new ExamenMicroscopieFilterPanel());

		/* field  examensATB */
		examensATBDataProvider = new ExamenATBDataProvider(requestFactory,
				"casTb");
		if (hideButtons) // in popup, relation buttons hidden		
			examensATB = new ImogMultiRelationBox<ExamenATBProxy>(
					examensATBDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled
			if (AccessManager.canCreateExamenATB()
					&& AccessManager.canEditExamenATB())
				examensATB = new ImogMultiRelationBox<ExamenATBProxy>(
						examensATBDataProvider, EpicamRenderer.get(), null);
			else
				examensATB = new ImogMultiRelationBox<ExamenATBProxy>(false,
						examensATBDataProvider, EpicamRenderer.get(), null);
		}
		examensATB.setPopUpTitle(NLS.constants().examenATB_select_title());
		examensATB.setFilterPanel(new ExamenATBFilterPanel());

		/* field  regimePhaseInitiale */
		regimePhaseInitialeDataProvider = new RegimeDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			regimePhaseInitiale = new ImogSingleRelationBox<RegimeProxy>(
					regimePhaseInitialeDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateRegime()
					&& AccessManager.canEditRegime())
				regimePhaseInitiale = new ImogSingleRelationBox<RegimeProxy>(
						regimePhaseInitialeDataProvider, EpicamRenderer.get());
			else
				regimePhaseInitiale = new ImogSingleRelationBox<RegimeProxy>(
						false, regimePhaseInitialeDataProvider,
						EpicamRenderer.get());
		}

		/* field  regimePhaseContinuation */
		regimePhaseContinuationDataProvider = new RegimeDataProvider(
				requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			regimePhaseContinuation = new ImogSingleRelationBox<RegimeProxy>(
					regimePhaseContinuationDataProvider, EpicamRenderer.get(),
					true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateRegime()
					&& AccessManager.canEditRegime())
				regimePhaseContinuation = new ImogSingleRelationBox<RegimeProxy>(
						regimePhaseContinuationDataProvider,
						EpicamRenderer.get());
			else
				regimePhaseContinuation = new ImogSingleRelationBox<RegimeProxy>(
						false, regimePhaseContinuationDataProvider,
						EpicamRenderer.get());
		}

		/* field  priseMedicamenteusePhaseInitiale */
		priseMedicamenteusePhaseInitiale = new CasTuberculosePriseMedicamenteusePhaseInitialeListEditor(
				requestFactory);

		/* field  priseMedicamenteusePhaseContinuation */
		priseMedicamenteusePhaseContinuation = new CasTuberculosePriseMedicamenteusePhaseContinuationListEditor(
				requestFactory);

		/* field  rendezVous */
		rendezVous = new CasTuberculoseRendezVousListEditor(requestFactory);

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

		/* Informations section widgets */
		// readonly field
		identifiant.setEdited(false);
		numRegTB.setEdited(isEdited);
		patient.setEdited(isEdited);
		dateDebutTraitement.setEdited(isEdited);
		typePatient.setEdited(isEdited);
		typePatientPrecisions.setEdited(isEdited);
		formeMaladie.setEdited(isEdited);
		extraPulmonairePrecisions.setEdited(isEdited);
		cotrimoxazole.setEdited(isEdited);
		antiRetroViraux.setEdited(isEdited);
		fumeur.setEdited(isEdited);
		fumeurArreter.setEdited(isEdited);

		/* Examen section widgets */
		examensMiscrocopies.setEdited(isEdited);
		examensATB.setEdited(isEdited);

		/* Traitement section widgets */
		regimePhaseInitiale.setEdited(isEdited);
		regimePhaseContinuation.setEdited(isEdited);
		priseMedicamenteusePhaseInitiale.setEdited(isEdited);
		priseMedicamenteusePhaseContinuation.setEdited(isEdited);
		rendezVous.setEdited(isEdited);

		/* FinTraitement section widgets */
		dateFinTraitement.setEdited(isEdited);
		devenirMalade.setEdited(isEdited);
		observation.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* Informations section widgets visibility */
		if (!AccessManager.canReadCasTuberculoseInformations())
			informationsSection.setVisible(false);

		/* Examen section widgets visibility */
		if (!AccessManager.canReadCasTuberculoseExamen())
			examenSection.setVisible(false);

		/* Traitement section widgets visibility */
		if (!AccessManager.canReadCasTuberculoseTraitement())
			traitementSection.setVisible(false);

		/* FinTraitement section widgets visibility */
		if (!AccessManager.canReadCasTuberculoseFinTraitement())
			finTraitementSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* Informations section widgets visibility */
		if (!AccessManager.canEditCasTuberculoseInformations())
			informationsSection.setVisible(false);

		/* Examen section widgets visibility */
		if (!AccessManager.canEditCasTuberculoseExamen())
			examenSection.setVisible(false);

		/* Traitement section widgets visibility */
		if (!AccessManager.canEditCasTuberculoseTraitement())
			traitementSection.setVisible(false);

		/* FinTraitement section widgets visibility */
		if (!AccessManager.canEditCasTuberculoseFinTraitement())
			finTraitementSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(CasTuberculoseRequest ctx) {
		priseMedicamenteusePhaseInitiale.setRequestContextForListEditors(ctx);
		priseMedicamenteusePhaseContinuation
				.setRequestContextForListEditors(ctx);
		rendezVous.setRequestContextForListEditors(ctx);
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

						//Test if patient is selected and build tb case id by patient id
						if (field.equals(patient)) {
							if (patient != null) {
								//+nombre d'épisode de tb du patient +1
								com.google.web.bindery.requestfactory.shared.Request<List<CasTuberculoseProxy>> listCasTB = requestFactory
										.casTuberculoseRequest()
										.listCasTuberculose("modified", false);
								listCasTB
										.fire(new Receiver<List<CasTuberculoseProxy>>() {
											@Override
											public void onSuccess(
													List<CasTuberculoseProxy> response) {
												int nbEpisodeTB = 0;
												String identifiantTB = patient
														.getValue()
														.getIdentifiant();
												for (CasTuberculoseProxy casTuberculoseProxy : response) {
													if (casTuberculoseProxy
															.getPatient()
															.getIdentifiant()
															.equals(patient
																	.getValue()
																	.getIdentifiant())) {
														nbEpisodeTB++;
													}
												}
												identifiant.setValue(patient
														.getValue()
														.getIdentifiant()
														+ "_" + nbEpisodeTB);
											}
										});
							}
						}
					}
				}));
	}

	/**
	 * Computes the field visibility
	 */
	public void computeVisibility(ImogField<?> source, boolean allValidation) {
		priseMedicamenteusePhaseInitiale.computeVisibility(source,
				allValidation);
		priseMedicamenteusePhaseContinuation.computeVisibility(source,
				allValidation);
		rendezVous.computeVisibility(source, allValidation);

		// the visibility of field typePatientPrecisions depends on the value of other fields
		if (allValidation || source.equals(typePatient)) {
			if ((typePatient.getValue() != null && typePatient.getValue()
					.matches("3"))) {
				typePatientPrecisions.setVisible(true);
			} else {
				typePatientPrecisions.setVisible(false);
			}
		}

		// the visibility of field extraPulmonairePrecisions depends on the value of other fields
		if (allValidation || source.equals(formeMaladie)) {
			if ((formeMaladie.getValue() != null && formeMaladie.getValue()
					.matches("2"))) {
				extraPulmonairePrecisions.setVisible(true);
			} else {
				extraPulmonairePrecisions.setVisible(false);
			}
		}

		// the visibility of field fumeurArreter depends on the value of other fields
		if (allValidation || source.equals(fumeur)) {
			if ((fumeur.getValue() != null && fumeur.getValue())) {
				fumeurArreter.setVisible(true);
			} else {
				fumeurArreter.setVisible(false);
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

	/** Widget update for field examensMiscrocopies */
	private void clearExamensMiscrocopiesWidget() {
		examensMiscrocopies.emptyList();
	}

	/** Widget update for field examensATB */
	private void clearExamensATBWidget() {
		examensATB.emptyList();
	}

	/**
	 * Setter to inject a Regime value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setRegimePhaseInitiale(RegimeProxy value, boolean isLocked) {
		regimePhaseInitiale.setLocked(isLocked);
		regimePhaseInitiale.setValue(value);

	}

	/** Widget update for field regimePhaseInitiale */
	private void clearRegimePhaseInitialeWidget() {
		regimePhaseInitiale.clear();
	}

	/**
	 * Setter to inject a Regime value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setRegimePhaseContinuation(RegimeProxy value, boolean isLocked) {
		regimePhaseContinuation.setLocked(isLocked);
		regimePhaseContinuation.setValue(value);

	}

	/** Widget update for field regimePhaseContinuation */
	private void clearRegimePhaseContinuationWidget() {
		regimePhaseContinuation.clear();
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

		/* 'Information' button for field ExamensMiscrocopies */
		examensMiscrocopies.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				if (!examensMiscrocopies.isEmpty()
						&& examensMiscrocopies.getSelectedEntities().size() > 0) {

					ExamenMicroscopieProxy value = examensMiscrocopies
							.getSelectedEntities().get(0);
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					ExamenMicroscopieFormPanel form = new ExamenMicroscopieFormPanel(
							requestFactory, value.getId(), relationPopup,
							"examensMiscrocopies");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field ExamensMiscrocopies */
		examensMiscrocopies.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				RelationPopupPanel relationPopup = new RelationPopupPanel();
				ExamenMicroscopieFormPanel form = new ExamenMicroscopieFormPanel(
						requestFactory, null, relationPopup,
						"examensMiscrocopies");
				form.setCasTb(editedValue, true);
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a ExamenMicroscopie is created or updated from the relation field ExamensMiscrocopies */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveExamenMicroscopieEvent.TYPE,
				new SaveExamenMicroscopieEvent.Handler() {
					@Override
					public void saveExamenMicroscopie(
							ExamenMicroscopieProxy value) {
						if (!examensMiscrocopies.isPresent(value))
							examensMiscrocopies.addValue(value);
					}
					public void saveExamenMicroscopie(
							ExamenMicroscopieProxy value, String initField) {
						if (initField.equals("examensMiscrocopies")) {
							if (examensMiscrocopies.isPresent(value))
								examensMiscrocopies.replaceValue(value);
							else
								examensMiscrocopies.addValue(value, true);
						}
					}
				}));

		/* 'Information' button for field ExamensATB */
		examensATB.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				if (!examensATB.isEmpty()
						&& examensATB.getSelectedEntities().size() > 0) {

					ExamenATBProxy value = examensATB.getSelectedEntities()
							.get(0);
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					ExamenATBFormPanel form = new ExamenATBFormPanel(
							requestFactory, value.getId(), relationPopup,
							"examensATB");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field ExamensATB */
		examensATB.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				RelationPopupPanel relationPopup = new RelationPopupPanel();
				ExamenATBFormPanel form = new ExamenATBFormPanel(
						requestFactory, null, relationPopup, "examensATB");
				form.setCasTb(editedValue, true);
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a ExamenATB is created or updated from the relation field ExamensATB */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveExamenATBEvent.TYPE, new SaveExamenATBEvent.Handler() {
					@Override
					public void saveExamenATB(ExamenATBProxy value) {
						if (!examensATB.isPresent(value))
							examensATB.addValue(value);
					}
					public void saveExamenATB(ExamenATBProxy value,
							String initField) {
						if (initField.equals("examensATB")) {
							if (examensATB.isPresent(value))
								examensATB.replaceValue(value);
							else
								examensATB.addValue(value, true);
						}
					}
				}));

		/* 'Information' button for field RegimePhaseInitiale */
		regimePhaseInitiale.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (regimePhaseInitiale.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					RegimeFormPanel form = new RegimeFormPanel(requestFactory,
							regimePhaseInitiale.getValue().getId(),
							relationPopup, "regimePhaseInitiale");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field RegimePhaseInitiale */
		regimePhaseInitiale.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				RegimeFormPanel form = new RegimeFormPanel(requestFactory,
						null, relationPopup, "regimePhaseInitiale");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a Regime is created or updated from the relation field RegimePhaseInitiale */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveRegimeEvent.TYPE, new SaveRegimeEvent.Handler() {
					@Override
					public void saveRegime(RegimeProxy value) {
						regimePhaseInitiale.setValue(value);
					}
					@Override
					public void saveRegime(RegimeProxy value, String initField) {
						if (initField.equals("regimePhaseInitiale"))
							regimePhaseInitiale.setValue(value, true);
					}
				}));

		/* 'Information' button for field RegimePhaseContinuation */
		regimePhaseContinuation.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (regimePhaseContinuation.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					RegimeFormPanel form = new RegimeFormPanel(requestFactory,
							regimePhaseContinuation.getValue().getId(),
							relationPopup, "regimePhaseContinuation");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field RegimePhaseContinuation */
		regimePhaseContinuation.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				RegimeFormPanel form = new RegimeFormPanel(requestFactory,
						null, relationPopup, "regimePhaseContinuation");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a Regime is created or updated from the relation field RegimePhaseContinuation */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveRegimeEvent.TYPE, new SaveRegimeEvent.Handler() {
					@Override
					public void saveRegime(RegimeProxy value) {
						regimePhaseContinuation.setValue(value);
					}
					@Override
					public void saveRegime(RegimeProxy value, String initField) {
						if (initField.equals("regimePhaseContinuation"))
							regimePhaseContinuation.setValue(value, true);
					}
				}));

	}

	/**
	 * Gets the CasTuberculoseProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public CasTuberculoseProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the CasTuberculoseProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(CasTuberculoseProxy editedValue) {
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

		// dateDebutTraitement is a required field
		if (dateDebutTraitement.getValueWithoutParseException() == null
				&& dateDebutTraitement.isValid())
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"dateDebutTraitement");
		// priseMedicamenteusePhaseInitiale nested form shall be validated
		priseMedicamenteusePhaseInitiale.validateFields();
		// priseMedicamenteusePhaseContinuation nested form shall be validated
		priseMedicamenteusePhaseContinuation.validateFields();
		// rendezVous nested form shall be validated
		rendezVous.validateFields();
	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* Informations field group */
		identifiant.setLabelWidth(width);
		numRegTB.setLabelWidth(width);
		patient.setLabelWidth(width);
		dateDebutTraitement.setLabelWidth(width);
		typePatient.setLabelWidth(width);
		typePatientPrecisions.setLabelWidth(width);
		formeMaladie.setLabelWidth(width);
		extraPulmonairePrecisions.setLabelWidth(width);
		cotrimoxazole.setLabelWidth(width);
		antiRetroViraux.setLabelWidth(width);
		fumeur.setLabelWidth(width);
		fumeurArreter.setLabelWidth(width);

		/* Examen field group */
		examensMiscrocopies.setLabelWidth(width);
		examensATB.setLabelWidth(width);

		/* Traitement field group */
		regimePhaseInitiale.setLabelWidth(width);
		regimePhaseContinuation.setLabelWidth(width);

		/* FinTraitement field group */
		dateFinTraitement.setLabelWidth(width);
		devenirMalade.setLabelWidth(width);
		observation.setLabelWidth(width);

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* Informations field group */
		identifiant.setBoxWidth(width);
		numRegTB.setBoxWidth(width);
		patient.setBoxWidth(width);
		typePatient.setBoxWidth(width);
		typePatientPrecisions.setBoxWidth(width);
		formeMaladie.setBoxWidth(width);
		extraPulmonairePrecisions.setBoxWidth(width);
		cotrimoxazole.setBoxWidth(width);

		/* Examen field group */
		examensMiscrocopies.setBoxWidth(width);
		examensATB.setBoxWidth(width);

		/* Traitement field group */
		regimePhaseInitiale.setBoxWidth(width);
		regimePhaseContinuation.setBoxWidth(width);

		/* FinTraitement field group */
		devenirMalade.setBoxWidth(width);
		observation.setBoxWidth(width);

	}

	@Override
	public void setDelegate(EditorDelegate<CasTuberculoseProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> dateDebutTraitementFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("dateDebutTraitement"))
						dateDebutTraitementFieldErrors.add(error);

				}
			}
			if (dateDebutTraitementFieldErrors.size() > 0)
				dateDebutTraitement.showErrors(dateDebutTraitementFieldErrors);
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
