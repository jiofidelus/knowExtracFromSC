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
import org.imogene.epicam.shared.proxy.PatientProxy;
import org.imogene.epicam.shared.request.PatientRequest;

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
import org.imogene.epicam.client.ui.workflow.panel.LieuDitFormPanel;
import org.imogene.epicam.client.event.save.SaveLieuDitEvent;
import org.imogene.epicam.client.dataprovider.LieuDitDataProvider;
import org.imogene.epicam.client.ui.filter.LieuDitFilterPanel;
import org.imogene.epicam.shared.proxy.LieuDitProxy;
import org.imogene.epicam.client.ui.workflow.panel.CasTuberculoseFormPanel;
import org.imogene.epicam.client.event.save.SaveCasTuberculoseEvent;
import org.imogene.epicam.client.dataprovider.CasTuberculoseDataProvider;
import org.imogene.epicam.client.ui.filter.CasTuberculoseFilterPanel;
import org.imogene.epicam.shared.proxy.CasTuberculoseProxy;
import org.imogene.epicam.client.ui.workflow.panel.CasIndexFormPanel;
import org.imogene.epicam.client.event.save.SaveCasIndexEvent;
import org.imogene.epicam.client.dataprovider.CasIndexDataProvider;
import org.imogene.epicam.client.ui.filter.CasIndexFilterPanel;
import org.imogene.epicam.shared.proxy.CasIndexProxy;
import org.imogene.epicam.client.ui.editor.nested.PatientCasIndexListEditor;
import org.imogene.epicam.shared.request.PatientRequest;
import org.imogene.epicam.client.ui.workflow.panel.ExamenBiologiqueFormPanel;
import org.imogene.epicam.client.event.save.SaveExamenBiologiqueEvent;
import org.imogene.epicam.client.dataprovider.ExamenBiologiqueDataProvider;
import org.imogene.epicam.client.ui.filter.ExamenBiologiqueFilterPanel;
import org.imogene.epicam.shared.proxy.ExamenBiologiqueProxy;
import org.imogene.epicam.client.ui.editor.nested.PatientExamensBiologiquesListEditor;
import org.imogene.epicam.shared.request.PatientRequest;
import org.imogene.epicam.client.ui.workflow.panel.ExamenSerologieFormPanel;
import org.imogene.epicam.client.event.save.SaveExamenSerologieEvent;
import org.imogene.epicam.client.dataprovider.ExamenSerologieDataProvider;
import org.imogene.epicam.client.ui.filter.ExamenSerologieFilterPanel;
import org.imogene.epicam.shared.proxy.ExamenSerologieProxy;
import org.imogene.epicam.client.ui.editor.nested.PatientSerologiesListEditor;
import org.imogene.epicam.shared.request.PatientRequest;

/**
 * Editor that provides the UI components that allow a PatientProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class PatientEditor extends Composite
		implements
			Editor<PatientProxy>,
			HasEditorDelegate<PatientProxy>,
			HasEditorErrors<PatientProxy> {

	interface Binder extends UiBinder<Widget, PatientEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<PatientProxy> delegate;

	private PatientProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;

	/* Identification section widgets */
	@UiField
	@Ignore
	FieldGroupPanel identificationSection;
	@UiField
	ImogTextBox identifiant;
	@UiField
	ImogTextBox nom;
	@UiField
	ImogSingleEnumBox sexe;
	@UiField
	ImogDateBox dateNaissance;
	@UiField
	ImogIntegerBox age;
	@UiField
	ImogTextBox profession;
	@UiField(provided = true)
	ImogMultiRelationBox<CentreDiagTraitProxy> centres;
	private CentreDiagTraitDataProvider centresDataProvider;
	@UiField
	ImogSingleEnumBox nationalite;
	@UiField
	ImogTextBox precisionSurNationalite;
	@UiField
	ImogBooleanBox recevoirCarnetTelPortable;

	/* Contact section widgets */
	@UiField
	@Ignore
	FieldGroupPanel contactSection;
	@UiField
	ImogTextBox telephoneUn;
	@UiField
	ImogTextBox telephoneDeux;
	@UiField
	ImogTextBox telephoneTrois;
	@UiField
	ImogEmailBox email;
	@UiField
	ImogSingleEnumBox libelle;
	@UiField
	ImogTextAreaBox complementAdresse;
	@UiField
	ImogTextBox quartier;
	@UiField
	ImogTextBox ville;
	@UiField
	ImogTextBox codePostal;
	@UiField(provided = true)
	ImogMultiRelationBox<LieuDitProxy> lieuxDits;
	private LieuDitDataProvider lieuxDitsDataProvider;

	/* PersonneContact section widgets */
	@UiField
	@Ignore
	FieldGroupPanel personneContactSection;
	@UiField
	ImogTextBox pacNom;
	@UiField
	ImogTextBox pacTelephoneUn;
	@UiField
	ImogTextBox pacTelephoneDeux;
	@UiField
	ImogTextBox pacTelephoneTrois;
	@UiField
	ImogEmailBox pacEmail;
	@UiField
	ImogSingleEnumBox pacLibelle;
	@UiField
	ImogTextAreaBox pacComplementAdresse;
	@UiField
	ImogTextBox pacQuartier;
	@UiField
	ImogTextBox pacVille;
	@UiField
	ImogTextBox pacCodePostal;

	/* Tuberculose section widgets */
	@UiField
	@Ignore
	FieldGroupPanel tuberculoseSection;
	@UiField(provided = true)
	ImogMultiRelationBox<CasTuberculoseProxy> casTuberculose;
	private CasTuberculoseDataProvider casTuberculoseDataProvider;
	@UiField(provided = true)
	PatientCasIndexListEditor casIndex;
	private CasIndexDataProvider casIndexDataProvider;

	/* Examens section widgets */
	@UiField
	@Ignore
	FieldGroupPanel examensSection;
	@UiField(provided = true)
	PatientExamensBiologiquesListEditor examensBiologiques;
	private ExamenBiologiqueDataProvider examensBiologiquesDataProvider;
	@UiField(provided = true)
	PatientSerologiesListEditor serologies;
	private ExamenSerologieDataProvider serologiesDataProvider;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public PatientEditor(EpicamRequestFactory factory, boolean hideButtons) {

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
	public PatientEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* Identification section widgets */
		identificationSection.setGroupTitle(NLS.constants()
				.patient_group_identification());
		identifiant.setLabel(NLS.constants().patient_field_identifiant());
		nom.setLabel(NLS.constants().patient_field_nom());
		sexe.setLabel(NLS.constants().patient_field_sexe());
		sexe.addItem(EpicamEnumConstants.PATIENT_SEXE_MASCULIN, NLS.constants()
				.patient_sexe_masculin_option());
		sexe.addItem(EpicamEnumConstants.PATIENT_SEXE_FEMININ, NLS.constants()
				.patient_sexe_feminin_option());
		dateNaissance.setLabel(NLS.constants().patient_field_dateNaissance());
		age.setLabel(NLS.constants().patient_field_age());
		profession.setLabel(NLS.constants().patient_field_profession());
		centres.setLabel(NLS.constants().patient_field_centres());
		nationalite.setLabel(NLS.constants().patient_field_nationalite());
		nationalite.addItem(
				EpicamEnumConstants.PATIENT_NATIONALITE_CAMEROUNAIS, NLS
						.constants().patient_nationalite_camerounais_option());
		nationalite.addItem(EpicamEnumConstants.PATIENT_NATIONALITE_ETRANGER,
				NLS.constants().patient_nationalite_etranger_option());
		// the value of nationalite affects the visibility of other fields
		nationalite.notifyChanges(requestFactory.getEventBus());
		precisionSurNationalite.setLabel(NLS.constants()
				.patient_field_precisionSurNationalite());
		// the visibility of precisionSurNationalite depends on the value of other fields
		precisionSurNationalite.addStyleName("dependentVisibility");
		recevoirCarnetTelPortable.setLabel(NLS.constants()
				.patient_field_recevoirCarnetTelPortable());

		/* Contact section widgets */
		contactSection.setGroupTitle(NLS.constants().patient_group_contact());
		telephoneUn.setLabel(NLS.constants().patient_field_telephoneUn());
		telephoneDeux.setLabel(NLS.constants().patient_field_telephoneDeux());
		telephoneTrois.setLabel(NLS.constants().patient_field_telephoneTrois());
		email.setLabel(NLS.constants().patient_field_email());
		libelle.setLabel(NLS.constants().patient_field_libelle());
		libelle.addItem(EpicamEnumConstants.PATIENT_LIBELLE_DOMICILE, NLS
				.constants().patient_libelle_domicile_option());
		libelle.addItem(EpicamEnumConstants.PATIENT_LIBELLE_BUREAU, NLS
				.constants().patient_libelle_bureau_option());
		libelle.addItem(EpicamEnumConstants.PATIENT_LIBELLE_AUTRE, NLS
				.constants().patient_libelle_autre_option());
		complementAdresse.setLabel(NLS.constants()
				.patient_field_complementAdresse());
		quartier.setLabel(NLS.constants().patient_field_quartier());
		ville.setLabel(NLS.constants().patient_field_ville());
		codePostal.setLabel(NLS.constants().patient_field_codePostal());
		lieuxDits.setLabel(NLS.constants().patient_field_lieuxDits());

		/* PersonneContact section widgets */
		personneContactSection.setGroupTitle(NLS.constants()
				.patient_group_personneContact());
		pacNom.setLabel(NLS.constants().patient_field_pacNom());
		pacTelephoneUn.setLabel(NLS.constants().patient_field_pacTelephoneUn());
		pacTelephoneDeux.setLabel(NLS.constants()
				.patient_field_pacTelephoneDeux());
		pacTelephoneTrois.setLabel(NLS.constants()
				.patient_field_pacTelephoneTrois());
		pacEmail.setLabel(NLS.constants().patient_field_pacEmail());
		pacLibelle.setLabel(NLS.constants().patient_field_pacLibelle());
		pacLibelle.addItem(EpicamEnumConstants.PATIENT_PACLIBELLE_DOMICILE, NLS
				.constants().patient_pacLibelle_domicile_option());
		pacLibelle.addItem(EpicamEnumConstants.PATIENT_PACLIBELLE_BUREAU, NLS
				.constants().patient_pacLibelle_bureau_option());
		pacLibelle.addItem(EpicamEnumConstants.PATIENT_PACLIBELLE_AUTRE, NLS
				.constants().patient_pacLibelle_autre_option());
		pacComplementAdresse.setLabel(NLS.constants()
				.patient_field_pacComplementAdresse());
		pacQuartier.setLabel(NLS.constants().patient_field_pacQuartier());
		pacVille.setLabel(NLS.constants().patient_field_pacVille());
		pacCodePostal.setLabel(NLS.constants().patient_field_pacCodePostal());

		/* Tuberculose section widgets */
		tuberculoseSection.setGroupTitle(NLS.constants()
				.patient_group_tuberculose());
		casTuberculose.setLabel(NLS.constants().patient_field_casTuberculose());

		/* Examens section widgets */
		examensSection.setGroupTitle(NLS.constants().patient_group_examens());

		dateNaissance.notifyChanges(requestFactory.getEventBus());
		age.notifyChanges(requestFactory.getEventBus());
		centres.notifyChanges(requestFactory.getEventBus());
		nationalite.notifyChanges(requestFactory.getEventBus());

		updateIdentifiant();
	}

	/**
	 * Configures the widgets that manage relation fields
	 */
	private void setRelationFields() {

		/* field  centres */
		centresDataProvider = new CentreDiagTraitDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden		
			centres = new ImogMultiRelationBox<CentreDiagTraitProxy>(
					centresDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled
			if (AccessManager.canCreateCentreDiagTrait()
					&& AccessManager.canEditCentreDiagTrait())
				centres = new ImogMultiRelationBox<CentreDiagTraitProxy>(
						centresDataProvider, EpicamRenderer.get(), null);
			else
				centres = new ImogMultiRelationBox<CentreDiagTraitProxy>(false,
						centresDataProvider, EpicamRenderer.get(), null);
		}
		centres.setPopUpTitle(NLS.constants().centreDiagTrait_select_title());
		centres.setFilterPanel(new CentreDiagTraitFilterPanel());

		/* field  lieuxDits */
		lieuxDitsDataProvider = new LieuDitDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden		
			lieuxDits = new ImogMultiRelationBox<LieuDitProxy>(
					lieuxDitsDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled
			if (AccessManager.canCreateLieuDit()
					&& AccessManager.canEditLieuDit())
				lieuxDits = new ImogMultiRelationBox<LieuDitProxy>(
						lieuxDitsDataProvider, EpicamRenderer.get(), null);
			else
				lieuxDits = new ImogMultiRelationBox<LieuDitProxy>(false,
						lieuxDitsDataProvider, EpicamRenderer.get(), null);
		}
		lieuxDits.setPopUpTitle(NLS.constants().lieuDit_select_title());
		lieuxDits.setFilterPanel(new LieuDitFilterPanel());

		/* field  casTuberculose */
		casTuberculoseDataProvider = new CasTuberculoseDataProvider(
				requestFactory, "patient");
		if (hideButtons) // in popup, relation buttons hidden		
			casTuberculose = new ImogMultiRelationBox<CasTuberculoseProxy>(
					casTuberculoseDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled
			if (AccessManager.canCreateCasTuberculose()
					&& AccessManager.canEditCasTuberculose())
				casTuberculose = new ImogMultiRelationBox<CasTuberculoseProxy>(
						casTuberculoseDataProvider, EpicamRenderer.get(), null);
			else
				casTuberculose = new ImogMultiRelationBox<CasTuberculoseProxy>(
						false, casTuberculoseDataProvider,
						EpicamRenderer.get(), null);
		}
		casTuberculose.setPopUpTitle(NLS.constants()
				.casTuberculose_select_title());
		casTuberculose.setFilterPanel(new CasTuberculoseFilterPanel());

		/* field  casIndex */
		casIndex = new PatientCasIndexListEditor(requestFactory);

		/* field  examensBiologiques */
		examensBiologiques = new PatientExamensBiologiquesListEditor(
				requestFactory);

		/* field  serologies */
		serologies = new PatientSerologiesListEditor(requestFactory);

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

		/* Identification section widgets */
		// readonly field
		identifiant.setEdited(false);
		nom.setEdited(isEdited);
		sexe.setEdited(isEdited);
		dateNaissance.setEdited(isEdited);
		age.setEdited(isEdited);
		profession.setEdited(isEdited);
		centres.setEdited(isEdited);
		nationalite.setEdited(isEdited);
		precisionSurNationalite.setEdited(isEdited);
		recevoirCarnetTelPortable.setEdited(isEdited);

		/* Contact section widgets */
		telephoneUn.setEdited(isEdited);
		telephoneDeux.setEdited(isEdited);
		telephoneTrois.setEdited(isEdited);
		email.setEdited(isEdited);
		libelle.setEdited(isEdited);
		complementAdresse.setEdited(isEdited);
		quartier.setEdited(isEdited);
		ville.setEdited(isEdited);
		codePostal.setEdited(isEdited);
		lieuxDits.setEdited(isEdited);

		/* PersonneContact section widgets */
		pacNom.setEdited(isEdited);
		pacTelephoneUn.setEdited(isEdited);
		pacTelephoneDeux.setEdited(isEdited);
		pacTelephoneTrois.setEdited(isEdited);
		pacEmail.setEdited(isEdited);
		pacLibelle.setEdited(isEdited);
		pacComplementAdresse.setEdited(isEdited);
		pacQuartier.setEdited(isEdited);
		pacVille.setEdited(isEdited);
		pacCodePostal.setEdited(isEdited);

		/* Tuberculose section widgets */
		casTuberculose.setEdited(isEdited);
		casIndex.setEdited(isEdited);

		/* Examens section widgets */
		examensBiologiques.setEdited(isEdited);
		serologies.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* Identification section widgets visibility */
		if (!AccessManager.canReadPatientIdentification())
			identificationSection.setVisible(false);

		/* Contact section widgets visibility */
		if (!AccessManager.canReadPatientContact())
			contactSection.setVisible(false);

		/* PersonneContact section widgets visibility */
		if (!AccessManager.canReadPatientPersonneContact())
			personneContactSection.setVisible(false);

		/* Tuberculose section widgets visibility */
		if (!AccessManager.canReadPatientTuberculose())
			tuberculoseSection.setVisible(false);

		/* Examens section widgets visibility */
		if (!AccessManager.canReadPatientExamens())
			examensSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* Identification section widgets visibility */
		if (!AccessManager.canEditPatientIdentification())
			identificationSection.setVisible(false);

		/* Contact section widgets visibility */
		if (!AccessManager.canEditPatientContact())
			contactSection.setVisible(false);

		/* PersonneContact section widgets visibility */
		if (!AccessManager.canEditPatientPersonneContact())
			personneContactSection.setVisible(false);

		/* Tuberculose section widgets visibility */
		if (!AccessManager.canEditPatientTuberculose())
			tuberculoseSection.setVisible(false);

		/* Examens section widgets visibility */
		if (!AccessManager.canEditPatientExamens())
			examensSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(PatientRequest ctx) {
		casIndex.setRequestContextForListEditors(ctx);
		examensBiologiques.setRequestContextForListEditors(ctx);
		serologies.setRequestContextForListEditors(ctx);
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

						if (field.equals(dateNaissance)) {
							Date birthday = dateNaissance.getValue();
							if (birthday != null) {
								age.setValue(getAge(birthday));
							} else {
								age.setValue(null);
							}
						} else if (field.equals(age)) {
							Integer agei = age.getValue();
							if (agei != null) {
								dateNaissance.setValue(getBirthday(agei));
							} else {
								dateNaissance.setValue(null);
							}
						}
						// Generation of the identification of patient
						if (field.equals(centres) || field.equals(nationalite)) {
							updateIdentifiant();
						}
					}
				}));
	}

	/**
	 * Computes the field visibility
	 */
	public void computeVisibility(ImogField<?> source, boolean allValidation) {
		casIndex.computeVisibility(source, allValidation);
		examensBiologiques.computeVisibility(source, allValidation);
		serologies.computeVisibility(source, allValidation);

		// the visibility of field precisionSurNationalite depends on the value of other fields
		if (allValidation || source.equals(nationalite)) {
			if ((nationalite.getValue() != null && nationalite.getValue()
					.matches("1"))) {
				precisionSurNationalite.setVisible(true);
			} else {
				precisionSurNationalite.setVisible(false);
			}
		}

	}

	/** Widget update for field centres */
	private void clearCentresWidget() {
		centres.emptyList();
	}

	/** Widget update for field lieuxDits */
	private void clearLieuxDitsWidget() {
		lieuxDits.emptyList();
	}

	/** Widget update for field casTuberculose */
	private void clearCasTuberculoseWidget() {
		casTuberculose.emptyList();
	}

	/**
	 * Configures the handlers of the widgets that manage relation fields
	 */
	private void setRelationHandlers() {

		/* 'Information' button for field Centres */
		centres.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				if (!centres.isEmpty()
						&& centres.getSelectedEntities().size() > 0) {

					CentreDiagTraitProxy value = centres.getSelectedEntities()
							.get(0);
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					CentreDiagTraitFormPanel form = new CentreDiagTraitFormPanel(
							requestFactory, value.getId(), relationPopup,
							"centres");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field Centres */
		centres.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				RelationPopupPanel relationPopup = new RelationPopupPanel();
				CentreDiagTraitFormPanel form = new CentreDiagTraitFormPanel(
						requestFactory, null, relationPopup, "centres");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a CentreDiagTrait is created or updated from the relation field Centres */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveCentreDiagTraitEvent.TYPE,
				new SaveCentreDiagTraitEvent.Handler() {
					@Override
					public void saveCentreDiagTrait(CentreDiagTraitProxy value) {
						if (!centres.isPresent(value))
							centres.addValue(value);
					}
					public void saveCentreDiagTrait(CentreDiagTraitProxy value,
							String initField) {
						if (initField.equals("centres")) {
							if (centres.isPresent(value))
								centres.replaceValue(value);
							else
								centres.addValue(value, true);
						}
					}
				}));

		/* 'Information' button for field LieuxDits */
		lieuxDits.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				if (!lieuxDits.isEmpty()
						&& lieuxDits.getSelectedEntities().size() > 0) {

					LieuDitProxy value = lieuxDits.getSelectedEntities().get(0);
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					LieuDitFormPanel form = new LieuDitFormPanel(
							requestFactory, value.getId(), relationPopup,
							"lieuxDits");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field LieuxDits */
		lieuxDits.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				RelationPopupPanel relationPopup = new RelationPopupPanel();
				LieuDitFormPanel form = new LieuDitFormPanel(requestFactory,
						null, relationPopup, "lieuxDits");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a LieuDit is created or updated from the relation field LieuxDits */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveLieuDitEvent.TYPE, new SaveLieuDitEvent.Handler() {
					@Override
					public void saveLieuDit(LieuDitProxy value) {
						if (!lieuxDits.isPresent(value))
							lieuxDits.addValue(value);
					}
					public void saveLieuDit(LieuDitProxy value, String initField) {
						if (initField.equals("lieuxDits")) {
							if (lieuxDits.isPresent(value))
								lieuxDits.replaceValue(value);
							else
								lieuxDits.addValue(value, true);
						}
					}
				}));

		/* 'Information' button for field CasTuberculose */
		casTuberculose.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				if (!casTuberculose.isEmpty()
						&& casTuberculose.getSelectedEntities().size() > 0) {

					CasTuberculoseProxy value = casTuberculose
							.getSelectedEntities().get(0);
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					CasTuberculoseFormPanel form = new CasTuberculoseFormPanel(
							requestFactory, value.getId(), relationPopup,
							"casTuberculose");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field CasTuberculose */
		casTuberculose.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				RelationPopupPanel relationPopup = new RelationPopupPanel();
				CasTuberculoseFormPanel form = new CasTuberculoseFormPanel(
						requestFactory, null, relationPopup, "casTuberculose");
				form.setPatient(editedValue, true);
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a CasTuberculose is created or updated from the relation field CasTuberculose */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveCasTuberculoseEvent.TYPE,
				new SaveCasTuberculoseEvent.Handler() {
					@Override
					public void saveCasTuberculose(CasTuberculoseProxy value) {
						if (!casTuberculose.isPresent(value))
							casTuberculose.addValue(value);
					}
					public void saveCasTuberculose(CasTuberculoseProxy value,
							String initField) {
						if (initField.equals("casTuberculose")) {
							if (casTuberculose.isPresent(value))
								casTuberculose.replaceValue(value);
							else
								casTuberculose.addValue(value, true);
						}
					}
				}));

	}

	/**
	 * Gets the PatientProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public PatientProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the PatientProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(PatientProxy editedValue) {
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
		// age shall be superior or equal to '0'
		if (age.getValueWithoutParseException() != null
				&& !(age.getValueWithoutParseException() >= 0))
			delegate.recordError(
					BaseNLS.messages().error_min_num(
							NLS.constants().patient_field_age_min()), null,
					"age");
		// age shall be inferior or equal to '120'
		if (age.getValueWithoutParseException() != null
				&& !(age.getValueWithoutParseException() <= 120))
			delegate.recordError(
					BaseNLS.messages().error_max_num(
							NLS.constants().patient_field_age_max()), null,
					"age");

		// centres is a required field
		if (centres.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"centres");
		// casIndex nested form shall be validated
		casIndex.validateFields();
		// examensBiologiques nested form shall be validated
		examensBiologiques.validateFields();
		// serologies nested form shall be validated
		serologies.validateFields();
	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* Identification field group */
		identifiant.setLabelWidth(width);
		nom.setLabelWidth(width);
		sexe.setLabelWidth(width);
		dateNaissance.setLabelWidth(width);
		age.setLabelWidth(width);
		profession.setLabelWidth(width);
		centres.setLabelWidth(width);
		nationalite.setLabelWidth(width);
		precisionSurNationalite.setLabelWidth(width);
		recevoirCarnetTelPortable.setLabelWidth(width);

		/* Contact field group */
		telephoneUn.setLabelWidth(width);
		telephoneDeux.setLabelWidth(width);
		telephoneTrois.setLabelWidth(width);
		email.setLabelWidth(width);
		libelle.setLabelWidth(width);
		complementAdresse.setLabelWidth(width);
		quartier.setLabelWidth(width);
		ville.setLabelWidth(width);
		codePostal.setLabelWidth(width);
		lieuxDits.setLabelWidth(width);

		/* PersonneContact field group */
		pacNom.setLabelWidth(width);
		pacTelephoneUn.setLabelWidth(width);
		pacTelephoneDeux.setLabelWidth(width);
		pacTelephoneTrois.setLabelWidth(width);
		pacEmail.setLabelWidth(width);
		pacLibelle.setLabelWidth(width);
		pacComplementAdresse.setLabelWidth(width);
		pacQuartier.setLabelWidth(width);
		pacVille.setLabelWidth(width);
		pacCodePostal.setLabelWidth(width);

		/* Tuberculose field group */
		casTuberculose.setLabelWidth(width);

		/* Examens field group */

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* Identification field group */
		identifiant.setBoxWidth(width);
		nom.setBoxWidth(width);
		sexe.setBoxWidth(width);
		profession.setBoxWidth(width);
		centres.setBoxWidth(width);
		nationalite.setBoxWidth(width);
		precisionSurNationalite.setBoxWidth(width);

		/* Contact field group */
		telephoneUn.setBoxWidth(width);
		telephoneDeux.setBoxWidth(width);
		telephoneTrois.setBoxWidth(width);
		email.setBoxWidth(width);
		libelle.setBoxWidth(width);
		complementAdresse.setBoxWidth(width);
		quartier.setBoxWidth(width);
		ville.setBoxWidth(width);
		codePostal.setBoxWidth(width);
		lieuxDits.setBoxWidth(width);

		/* PersonneContact field group */
		pacNom.setBoxWidth(width);
		pacTelephoneUn.setBoxWidth(width);
		pacTelephoneDeux.setBoxWidth(width);
		pacTelephoneTrois.setBoxWidth(width);
		pacEmail.setBoxWidth(width);
		pacLibelle.setBoxWidth(width);
		pacComplementAdresse.setBoxWidth(width);
		pacQuartier.setBoxWidth(width);
		pacVille.setBoxWidth(width);
		pacCodePostal.setBoxWidth(width);

		/* Tuberculose field group */
		casTuberculose.setBoxWidth(width);

		/* Examens field group */

	}

	@Override
	public void setDelegate(EditorDelegate<PatientProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> nomFieldErrors = new ArrayList<EditorError>();
			List<EditorError> ageFieldErrors = new ArrayList<EditorError>();
			List<EditorError> centresFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("nom"))
						nomFieldErrors.add(error);
					if (field.equals("age"))
						ageFieldErrors.add(error);
					if (field.equals("centres"))
						centresFieldErrors.add(error);

				}
			}
			if (nomFieldErrors.size() > 0)
				nom.showErrors(nomFieldErrors);
			if (ageFieldErrors.size() > 0)
				age.showErrors(ageFieldErrors);
			if (centresFieldErrors.size() > 0)
				centres.showErrors(centresFieldErrors);
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

	private int getAge(Date birthday) {
		Date now = new Date();
		int nowMonth = now.getMonth();
		int nowYear = now.getYear();
		int result = nowYear - birthday.getYear();

		if (birthday.getMonth() > nowMonth) {
			result--;
		} else if (birthday.getMonth() == nowMonth) {
			int nowDay = now.getDate();
			if (birthday.getDate() > nowDay) {
				result--;
			}
		}
		return result;
	}

	private Date getBirthday(int age) {
		Date birthday = new Date();
		birthday.setDate(1);
		birthday.setMonth(0);
		birthday.setYear(birthday.getYear() - age);
		return birthday;
	}

	private void updateIdentifiant() {
		Request<Long> nbPat = requestFactory.patientRequest().countPatient();
		nbPat.fire(new Receiver<Long>() {
			@Override
			public void onSuccess(Long response) {
				StringBuilder builder = new StringBuilder();
				if (nationalite.getValue() != null) {
					if (EpicamEnumConstants.PATIENT_NATIONALITE_CAMEROUNAIS
							.equals(nationalite.getValue())) {
						builder.append('N');
					} else if (EpicamEnumConstants.PATIENT_NATIONALITE_ETRANGER
							.equals(nationalite.getValue())) {
						builder.append('E');
					}
				}
				if (centres.getValue() != null && centres.getValue().size() > 0) {
					builder.append(centres.getValue().get(0).getCode());
				}
				builder.append('_');
				builder.append(new Date().getYear() + 1900);
				builder.append('_');
				switch (response.toString().length()) {
					case 1 :
						builder.append("0000");
						break;
					case 2 :
						builder.append("000");
						break;
					case 3 :
						builder.append("00");
						break;
					case 4 :
						builder.append("0");
						break;
					default :
						break;
				}
				builder.append(response);
				identifiant.setValue(builder.toString());
			}
		});
	}
}
