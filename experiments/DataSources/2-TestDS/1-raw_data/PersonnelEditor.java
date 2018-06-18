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
import org.imogene.epicam.shared.proxy.PersonnelProxy;
import org.imogene.epicam.shared.request.PersonnelRequest;

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

import org.imogene.admin.client.AdminRenderer;
import org.imogene.admin.client.dataprovider.CardEntityDataProvider;
import org.imogene.admin.client.dataprovider.ProfileDataProvider;
import org.imogene.admin.client.i18n.AdminNLS;
import org.imogene.admin.client.ui.filter.CardEntityFilterPanel;
import org.imogene.web.client.ui.field.ImogPasswordBox;
import org.imogene.web.client.util.ProfileUtil;
import org.imogene.web.shared.proxy.CardEntityProxy;
import org.imogene.web.shared.proxy.ProfileProxy;
import org.imogene.epicam.client.EpicamAdminUtilRenderer;

import org.imogene.epicam.client.ui.workflow.panel.QualificationFormPanel;
import org.imogene.epicam.client.event.save.SaveQualificationEvent;
import org.imogene.epicam.client.dataprovider.QualificationDataProvider;
import org.imogene.epicam.client.ui.filter.QualificationFilterPanel;
import org.imogene.epicam.shared.proxy.QualificationProxy;
import org.imogene.epicam.client.ui.workflow.panel.RegionFormPanel;
import org.imogene.epicam.client.event.save.SaveRegionEvent;
import org.imogene.epicam.client.dataprovider.RegionDataProvider;
import org.imogene.epicam.client.ui.filter.RegionFilterPanel;
import org.imogene.epicam.shared.proxy.RegionProxy;
import org.imogene.epicam.client.ui.workflow.panel.DistrictSanteFormPanel;
import org.imogene.epicam.client.event.save.SaveDistrictSanteEvent;
import org.imogene.epicam.client.dataprovider.DistrictSanteDataProvider;
import org.imogene.epicam.client.ui.filter.DistrictSanteFilterPanel;
import org.imogene.epicam.shared.proxy.DistrictSanteProxy;
import org.imogene.epicam.client.ui.workflow.panel.CentreDiagTraitFormPanel;
import org.imogene.epicam.client.event.save.SaveCentreDiagTraitEvent;
import org.imogene.epicam.client.dataprovider.CentreDiagTraitDataProvider;
import org.imogene.epicam.client.ui.filter.CentreDiagTraitFilterPanel;
import org.imogene.epicam.shared.proxy.CentreDiagTraitProxy;

/**
 * Editor that provides the UI components that allow a PersonnelProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class PersonnelEditor extends Composite
		implements
			Editor<PersonnelProxy>,
			HasEditorDelegate<PersonnelProxy>,
			HasEditorErrors<PersonnelProxy> {

	interface Binder extends UiBinder<Widget, PersonnelEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<PersonnelProxy> delegate;

	private PersonnelProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;

	private String currentLogin = null;
	private boolean showIdentificationSection = false;
	private boolean showContactSection = false;
	private boolean showSituationSection = false;
	private boolean showNiveauAccessSection = false;

	/* Identification section widgets */
	@UiField
	@Ignore
	FieldGroupPanel identificationSection;
	@UiField
	ImogTextBox nom;
	@UiField
	ImogDateBox dateNaissance;
	@UiField
	ImogTextBox profession;

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

	/* Situation section widgets */
	@UiField
	@Ignore
	FieldGroupPanel situationSection;
	@UiField
	ImogDateBox dateDepart;
	@UiField
	ImogDateBox dateArrivee;
	@UiField
	ImogBooleanBox AEteForme;
	@UiField(provided = true)
	ImogMultiRelationBox<QualificationProxy> qualification;
	private QualificationDataProvider qualificationDataProvider;

	/* NiveauAccess section widgets */
	@UiField
	@Ignore
	FieldGroupPanel niveauAccessSection;
	@UiField
	ImogSingleEnumBox niveau;
	@UiField(provided = true)
	ImogSingleRelationBox<RegionProxy> region;
	private RegionDataProvider regionDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<DistrictSanteProxy> districtSante;
	private DistrictSanteDataProvider districtSanteDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<CentreDiagTraitProxy> CDT;
	private CentreDiagTraitDataProvider cDTDataProvider;
	@UiField
	ImogBooleanBox actif;

	/* Administration section widgets */
	@UiField
	@Ignore
	FieldGroupPanel administrationSection;
	@UiField
	ImogTextBox login;
	@UiField
	ImogPasswordBox password;
	@UiField
	@Ignore
	ImogPasswordBox passwordConfirm;
	@UiField(provided = true)
	ImogMultiRelationBox<ProfileProxy> profiles;
	@UiField
	@Ignore
	ImogLinkBox idLink;

	/* Synchronization section widgets */
	@UiField
	@Ignore
	FieldGroupPanel synchronizationSection;
	@UiField(provided = true)
	ImogMultiRelationBox<CardEntityProxy> synchronizables;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public PersonnelEditor(EpicamRequestFactory factory, boolean hideButtons) {

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
	public PersonnelEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* Identification section widgets */
		identificationSection.setGroupTitle(NLS.constants()
				.personnel_group_identification());
		nom.setLabel(NLS.constants().personnel_field_nom());
		dateNaissance.setLabel(NLS.constants().personnel_field_dateNaissance());
		profession.setLabel(NLS.constants().personnel_field_profession());

		/* Contact section widgets */
		contactSection.setGroupTitle(NLS.constants().personnel_group_contact());
		telephoneUn.setLabel(NLS.constants().personnel_field_telephoneUn());
		telephoneDeux.setLabel(NLS.constants().personnel_field_telephoneDeux());
		telephoneTrois.setLabel(NLS.constants()
				.personnel_field_telephoneTrois());
		email.setLabel(NLS.constants().personnel_field_email());
		libelle.setLabel(NLS.constants().personnel_field_libelle());
		libelle.addItem(EpicamEnumConstants.PERSONNEL_LIBELLE_DOMICILE, NLS
				.constants().personnel_libelle_domicile_option());
		libelle.addItem(EpicamEnumConstants.PERSONNEL_LIBELLE_BUREAU, NLS
				.constants().personnel_libelle_bureau_option());
		libelle.addItem(EpicamEnumConstants.PERSONNEL_LIBELLE_AUTRE, NLS
				.constants().personnel_libelle_autre_option());
		complementAdresse.setLabel(NLS.constants()
				.personnel_field_complementAdresse());
		quartier.setLabel(NLS.constants().personnel_field_quartier());
		ville.setLabel(NLS.constants().personnel_field_ville());
		codePostal.setLabel(NLS.constants().personnel_field_codePostal());

		/* Situation section widgets */
		situationSection.setGroupTitle(NLS.constants()
				.personnel_group_situation());
		dateDepart.setLabel(NLS.constants().personnel_field_dateDepart());
		// hidden field
		dateDepart.setVisible(false);
		dateArrivee.setLabel(NLS.constants().personnel_field_dateArrivee());
		// hidden field
		dateArrivee.setVisible(false);
		AEteForme.setLabel(NLS.constants().personnel_field_aEteForme());
		AEteForme.isStrict(true);
		qualification.setLabel(NLS.constants().personnel_field_qualification());

		/* NiveauAccess section widgets */
		niveauAccessSection.setGroupTitle(NLS.constants()
				.personnel_group_niveauAccess());
		niveau.setLabel(NLS.constants().personnel_field_niveau());
		niveau.addItem(EpicamEnumConstants.PERSONNEL_NIVEAU_CENTRAL, NLS
				.constants().personnel_niveau_central_option());
		niveau.addItem(EpicamEnumConstants.PERSONNEL_NIVEAU_REGION, NLS
				.constants().personnel_niveau_region_option());
		niveau.addItem(EpicamEnumConstants.PERSONNEL_NIVEAU_DISTRICTSANTE, NLS
				.constants().personnel_niveau_districtSante_option());
		niveau.addItem(EpicamEnumConstants.PERSONNEL_NIVEAU_CDT, NLS
				.constants().personnel_niveau_cDT_option());
		// the value of niveau affects the visibility of other fields
		niveau.notifyChanges(requestFactory.getEventBus());
		region.setLabel(NLS.constants().personnel_field_region());
		// the value of region affects the value of other fields
		region.notifyChanges(requestFactory.getEventBus());
		// the visibility of region depends on the value of other fields
		region.addStyleName("dependentVisibility");
		districtSante.setLabel(NLS.constants().personnel_field_districtSante());
		// the value of districtSante affects the value of other fields
		districtSante.notifyChanges(requestFactory.getEventBus());
		// the visibility of districtSante depends on the value of other fields
		districtSante.addStyleName("dependentVisibility");
		CDT.setLabel(NLS.constants().personnel_field_cDT());
		// the visibility of CDT depends on the value of other fields
		CDT.addStyleName("dependentVisibility");
		actif.setLabel(NLS.constants().personnel_field_actif());
		actif.isStrict(true);

		// the value of CDT affects the value of other fields
		CDT.notifyChanges(requestFactory.getEventBus());

		/* Administration section widgets */
		administrationSection.setGroupTitle(AdminNLS.constants()
				.imogActor_group_administration());
		login.setLabel(AdminNLS.constants().imogActor_field_login());
		password.setLabel(AdminNLS.constants().imogActor_field_password());
		passwordConfirm.setLabel(AdminNLS.constants()
				.imogActor_field_passwordConfirm());
		profiles.setLabel(AdminNLS.constants().imogActor_field_roleList());
		profiles.hideButtons(true);
		idLink.setLabel(AdminNLS.constants().imogActor_field_idFile());

		/* Synchronization section widgets */
		synchronizationSection.setGroupTitle(AdminNLS.constants()
				.imogActor_group_synchronization());
		synchronizables.setLabel(AdminNLS.constants()
				.imogActor_field_synchronizableList());
		synchronizables.hideButtons(true);

		// hide the fields that are not admin fields and show only the field groups that contain admin fields
		showIdentificationSection = true;
		showIdentificationSection = true;
		showIdentificationSection = true;
		telephoneUn.setVisible(false);
		telephoneDeux.setVisible(false);
		telephoneTrois.setVisible(false);
		email.setVisible(false);
		libelle.setVisible(false);
		complementAdresse.setVisible(false);
		quartier.setVisible(false);
		ville.setVisible(false);
		codePostal.setVisible(false);
		dateDepart.setVisible(false);
		dateArrivee.setVisible(false);
		AEteForme.setVisible(false);
		qualification.setVisible(false);
		niveau.setVisible(false);
		region.setVisible(false);
		districtSante.setVisible(false);
		CDT.setVisible(false);
		actif.setVisible(false);

	}

	/**
	 * Configures the widgets that manage relation fields
	 */
	private void setRelationFields() {

		/* field  qualification */
		qualificationDataProvider = new QualificationDataProvider(
				requestFactory);
		if (hideButtons) // in popup, relation buttons hidden		
			qualification = new ImogMultiRelationBox<QualificationProxy>(
					qualificationDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled
			if (AccessManager.canCreateQualification()
					&& AccessManager.canEditQualification())
				qualification = new ImogMultiRelationBox<QualificationProxy>(
						qualificationDataProvider, EpicamRenderer.get(), null);
			else
				qualification = new ImogMultiRelationBox<QualificationProxy>(
						false, qualificationDataProvider, EpicamRenderer.get(),
						null);
		}
		qualification.setPopUpTitle(NLS.constants()
				.qualification_select_title());
		qualification.setFilterPanel(new QualificationFilterPanel());

		/* field  region */
		regionDataProvider = new RegionDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			region = new ImogSingleRelationBox<RegionProxy>(regionDataProvider,
					EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateRegion()
					&& AccessManager.canEditRegion())
				region = new ImogSingleRelationBox<RegionProxy>(
						regionDataProvider, EpicamRenderer.get());
			else
				region = new ImogSingleRelationBox<RegionProxy>(false,
						regionDataProvider, EpicamRenderer.get());
		}

		/* field  districtSante */
		districtSanteDataProvider = new DistrictSanteDataProvider(
				requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			districtSante = new ImogSingleRelationBox<DistrictSanteProxy>(
					districtSanteDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateDistrictSante()
					&& AccessManager.canEditDistrictSante())
				districtSante = new ImogSingleRelationBox<DistrictSanteProxy>(
						districtSanteDataProvider, EpicamRenderer.get());
			else
				districtSante = new ImogSingleRelationBox<DistrictSanteProxy>(
						false, districtSanteDataProvider, EpicamRenderer.get());
		}

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

		/* field  roleList */
		ProfileDataProvider profilesDataProvider;
		profilesDataProvider = new ProfileDataProvider(requestFactory);
		profiles = new ImogMultiRelationBox<ProfileProxy>(profilesDataProvider,
				AdminRenderer.get(), true);
		profiles.setPopUpTitle(AdminNLS.constants().profile_select_title());

		/* field synchronizables */
		CardEntityDataProvider synchronizablesDataProvider;
		synchronizablesDataProvider = new CardEntityDataProvider(requestFactory);
		synchronizables = new ImogMultiRelationBox<CardEntityProxy>(
				synchronizablesDataProvider, EpicamAdminUtilRenderer.get(),
				true, 100);
		synchronizables.setPopUpTitle(AdminNLS.constants()
				.cardEntity_select_title());
		synchronizables.setFilterPanel(new CardEntityFilterPanel());

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
		nom.setEdited(isEdited);
		dateNaissance.setEdited(isEdited);
		profession.setEdited(isEdited);

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

		/* Situation section widgets */
		dateDepart.setEdited(isEdited);
		dateArrivee.setEdited(isEdited);
		// readonly field
		AEteForme.setEdited(false);
		qualification.setEdited(isEdited);

		/* NiveauAccess section widgets */
		niveau.setEdited(isEdited);
		region.setEdited(isEdited);
		districtSante.setEdited(isEdited);
		CDT.setEdited(isEdited);
		actif.setEdited(isEdited);

		/* Administration section widgets */
		login.setEdited(isEdited);
		password.setEdited(isEdited);
		passwordConfirm.setEdited(isEdited);
		profiles.setEdited(isEdited);
		idLink.setVisible(!isEdited);

		/* Synchronization section widgets */
		synchronizables.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* Identification section widgets visibility */
		if (!AccessManager.canReadPersonnelIdentification()
				|| !showIdentificationSection)
			identificationSection.setVisible(false);

		/* Contact section widgets visibility */
		if (!AccessManager.canReadPersonnelContact() || !showContactSection)
			contactSection.setVisible(false);

		/* Situation section widgets visibility */
		if (!AccessManager.canReadPersonnelSituation() || !showSituationSection)
			situationSection.setVisible(false);

		/* NiveauAccess section widgets visibility */
		if (!AccessManager.canReadPersonnelNiveauAccess()
				|| !showNiveauAccessSection)
			niveauAccessSection.setVisible(false);

		/* Administration && Synchronization && FilterFields sections visibility */
		if (!ProfileUtil.isAdmin()) {
			administrationSection.setVisible(false);
			synchronizationSection.setVisible(false);
		}
	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* Identification section widgets visibility */
		if (!AccessManager.canEditPersonnelIdentification()
				|| !showIdentificationSection)
			identificationSection.setVisible(false);

		/* Contact section widgets visibility */
		if (!AccessManager.canEditPersonnelContact() || !showContactSection)
			contactSection.setVisible(false);

		/* Situation section widgets visibility */
		if (!AccessManager.canEditPersonnelSituation() || !showSituationSection)
			situationSection.setVisible(false);

		/* NiveauAccess section widgets visibility */
		if (!AccessManager.canEditPersonnelNiveauAccess()
				|| !showNiveauAccessSection)
			niveauAccessSection.setVisible(false);

		/* Administration && Synchronization && FilterFields sections visibility */
		if (!ProfileUtil.isAdmin()) {
			administrationSection.setVisible(false);
			synchronizationSection.setVisible(false);
		}
	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(PersonnelRequest ctx) {
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

						/* DistrictSante list content depends on the value of field Region */
						if (field.equals(region)) {
							clearDistrictSanteWidget();
							getDistrictSanteFilteredByRegion(region.getValue());

						}
						/* CDT list content depends on the value of field DistrictSante */
						if (field.equals(districtSante)) {
							clearCDTWidget();
							getCDTFilteredByDistrictSante(districtSante
									.getValue());

							if (districtSante.getValue() != null) {
								RegionProxy proxy = districtSante.getValue()
										.getRegion();
								region.setValue(proxy);
							}

						}

						if (field.equals(CDT)) {
							CentreDiagTraitProxy cdtValue = CDT.getValue();
							if (cdtValue != null) {
								RegionProxy regionValue = cdtValue.getRegion();
								region.setValue(regionValue);
								DistrictSanteProxy districtValue = cdtValue
										.getDistrictSante();
								districtSante.setValue(districtValue);
							}
						}
					}
				}));
	}

	/**
	 * Computes the field visibility
	 */
	public void computeVisibility(ImogField<?> source, boolean allValidation) {

		// the visibility of field region depends on the value of other fields
		if (allValidation || source.equals(niveau)) {
			if ((niveau.getValue() != null && niveau.getValue()
					.matches("1|2|3"))) {
				region.setVisible(true);
			} else {
				region.setVisible(false);
			}
		}

		// the visibility of field districtSante depends on the value of other fields
		if (allValidation || source.equals(niveau)) {
			if ((niveau.getValue() != null && niveau.getValue().matches("2|3"))) {
				districtSante.setVisible(true);
			} else {
				districtSante.setVisible(false);
			}
		}

		// the visibility of field CDT depends on the value of other fields
		if (allValidation || source.equals(niveau)) {
			if ((niveau.getValue() != null && niveau.getValue().matches("3"))) {
				CDT.setVisible(true);
			} else {
				CDT.setVisible(false);
			}
		}

	}

	/**
	 * Filters the content of the RelationField DistrictSante by the value of 
	 * the RelationField Region
	 * @param region the value of 
	 * the RelationField Region 
	 */
	public void getDistrictSanteFilteredByRegion(RegionProxy pRegion) {

		if (pRegion != null) {
			if (!hideButtons)
				districtSante.hideButtons(false);
			districtSanteDataProvider.setFilterCriteria(pRegion.getId(),
					"region.id");
		} else {
			districtSanteDataProvider.setIsFiltered(false);
		}
		getCDTFilteredByRegion(pRegion);
	}

	/**
	 * Filters the content of the RelationField CDT by the value of 
	 * the RelationField DistrictSante
	 * @param districtSante the value of 
	 * the RelationField DistrictSante 
	 */
	public void getCDTFilteredByDistrictSante(DistrictSanteProxy pDistrictSante) {

		if (pDistrictSante != null) {
			if (!hideButtons)
				CDT.hideButtons(false);
			cDTDataProvider.setFilterCriteria(pDistrictSante.getId(),
					"districtSante.id");
		}
	}

	public void getCDTFilteredByRegion(RegionProxy pRegion) {

		if (pRegion != null) {
			if (!hideButtons)
				CDT.hideButtons(false);
			cDTDataProvider.setFilterCriteria(pRegion.getId(), "region.id");
		} else {
			cDTDataProvider.setIsFiltered(false);
		}

	}

	/** Widget update for field qualification */
	private void clearQualificationWidget() {
		qualification.emptyList();
	}

	/**
	 * Setter to inject a Region value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setRegion(RegionProxy value, boolean isLocked) {
		region.setLocked(isLocked);
		region.setValue(value);

		// Field DistrictSante depends on the value of field region
		clearDistrictSanteWidget();
		getDistrictSanteFilteredByRegion(value);
	}

	/** Widget update for field region */
	private void clearRegionWidget() {
		region.clear();
	}

	/**
	 * Setter to inject a DistrictSante value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setDistrictSante(DistrictSanteProxy value, boolean isLocked) {
		districtSante.setLocked(isLocked);
		districtSante.setValue(value);

		// Field CDT depends on the value of field districtSante
		clearCDTWidget();
		getCDTFilteredByDistrictSante(value);
	}

	/** Widget update for field districtSante */
	private void clearDistrictSanteWidget() {
		districtSante.clear();
		clearCDTWidget();

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
	 * Configures the handlers of the widgets that manage relation fields
	 */
	private void setRelationHandlers() {

		/* 'Information' button for field Qualification */
		qualification.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				if (!qualification.isEmpty()
						&& qualification.getSelectedEntities().size() > 0) {

					QualificationProxy value = qualification
							.getSelectedEntities().get(0);
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					QualificationFormPanel form = new QualificationFormPanel(
							requestFactory, value.getId(), relationPopup,
							"qualification");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field Qualification */
		qualification.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				RelationPopupPanel relationPopup = new RelationPopupPanel();
				QualificationFormPanel form = new QualificationFormPanel(
						requestFactory, null, relationPopup, "qualification");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a Qualification is created or updated from the relation field Qualification */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveQualificationEvent.TYPE,
				new SaveQualificationEvent.Handler() {
					@Override
					public void saveQualification(QualificationProxy value) {
						if (!qualification.isPresent(value))
							qualification.addValue(value);
					}
					public void saveQualification(QualificationProxy value,
							String initField) {
						if (initField.equals("qualification")) {
							if (qualification.isPresent(value))
								qualification.replaceValue(value);
							else
								qualification.addValue(value, true);
						}
					}
				}));

		/* 'Information' button for field Region */
		region.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (region.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					RegionFormPanel form = new RegionFormPanel(requestFactory,
							region.getValue().getId(), relationPopup, "region");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field Region */
		region.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				RegionFormPanel form = new RegionFormPanel(requestFactory,
						null, relationPopup, "region");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a Region is created or updated from the relation field Region */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveRegionEvent.TYPE, new SaveRegionEvent.Handler() {
					@Override
					public void saveRegion(RegionProxy value) {
						region.setValue(value);
					}
					@Override
					public void saveRegion(RegionProxy value, String initField) {
						if (initField.equals("region"))
							region.setValue(value, true);
					}
				}));

		/* 'Information' button for field DistrictSante */
		districtSante.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (districtSante.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					DistrictSanteFormPanel form = new DistrictSanteFormPanel(
							requestFactory, districtSante.getValue().getId(),
							relationPopup, "districtSante");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field DistrictSante */
		districtSante.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				DistrictSanteFormPanel form = new DistrictSanteFormPanel(
						requestFactory, null, relationPopup, "districtSante");
				/* common fields */
				if (region.getValue() != null)
					form.setRegion(region.getValue(), true);

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a DistrictSante is created or updated from the relation field DistrictSante */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveDistrictSanteEvent.TYPE,
				new SaveDistrictSanteEvent.Handler() {
					@Override
					public void saveDistrictSante(DistrictSanteProxy value) {
						districtSante.setValue(value);
					}
					@Override
					public void saveDistrictSante(DistrictSanteProxy value,
							String initField) {
						if (initField.equals("districtSante"))
							districtSante.setValue(value, true);
					}
				}));

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
				if (districtSante.getValue() != null)
					form.setDistrictSante(districtSante.getValue(), true);
				if (region.getValue() != null)
					form.setRegion(region.getValue(), true);

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

	}

	/**
	 * Gets the PersonnelProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public PersonnelProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the PersonnelProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(PersonnelProxy editedValue) {
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
	}

	/**
	 * Validates that when the login is changed, the password is changed too
	 */
	public void validateLoginWithPassword() {

		String newLogin = login.getValue();
		if (newLogin != null) {

			if (currentLogin != null) {
				if (!currentLogin.equals(newLogin)) {
					if (!passwordChanged())
						delegate.recordError(AdminNLS.constants()
								.login_without_password_error(), null, "login");
				}
			} else {
				if (!passwordChanged())
					delegate.recordError(AdminNLS.constants()
							.login_without_password_error(), null, "login");
			}
		}
	}

	/**
	 * Validates that the password is confirmed by the second entry
	 */
	public boolean validatePasword() {
		boolean isValid = true;

		String password1 = password.getValue();
		String password2 = passwordConfirm.getValue();

		if (!(password1 == null && password2 == null)) {

			if ((password1 != null && password2 == null)
					|| (password2 != null && password1 == null)) {
				delegate.recordError(AdminNLS.constants()
						.password_confirm_error(), null, "password");
				isValid = false;
			}
			if (!password1.equals(password2)) {
				delegate.recordError(AdminNLS.constants()
						.password_confirm_error(), null, "password");
				isValid = false;
			}
		}
		return isValid;
	}

	/**
	 * Tells if the password has been changed
	 */
	public boolean passwordChanged() {
		return !(password.getValue() == null && passwordConfirm.getValue() == null);
	}

	/**
	 * Stores the login of the actor
	 */
	public void setCurrentLogin() {
		this.currentLogin = login.getValue();
	}

	/**
	 * Set the link to download 
	 * the associated identification file
	 */
	public void updateIdLink(String entityId) {
		idLink.setValue("<a href=\"" + GWT.getHostPageBaseURL()
				+ "encrypt?type=PERS&id=" + entityId + "\">"
				+ AdminNLS.constants().imogActor_field_idFile_text() + "</a>");
	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* Identification field group */
		nom.setLabelWidth(width);
		dateNaissance.setLabelWidth(width);
		profession.setLabelWidth(width);

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

		/* Situation field group */
		dateDepart.setLabelWidth(width);
		dateArrivee.setLabelWidth(width);
		AEteForme.setLabelWidth(width);
		qualification.setLabelWidth(width);

		/* NiveauAccess field group */
		niveau.setLabelWidth(width);
		region.setLabelWidth(width);
		districtSante.setLabelWidth(width);
		CDT.setLabelWidth(width);
		actif.setLabelWidth(width);

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* Identification field group */
		nom.setBoxWidth(width);
		profession.setBoxWidth(width);

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

		/* Situation field group */
		qualification.setBoxWidth(width);

		/* NiveauAccess field group */
		niveau.setBoxWidth(width);
		region.setBoxWidth(width);
		districtSante.setBoxWidth(width);
		CDT.setBoxWidth(width);

	}

	@Override
	public void setDelegate(EditorDelegate<PersonnelProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> nomFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("nom"))
						nomFieldErrors.add(error);

					if (field.equals("password")) {
						List<EditorError> fieldErrors = new ArrayList<EditorError>();
						fieldErrors.add(error);
						passwordConfirm.showErrors(fieldErrors);
					}

					if (field.equals("login")) {
						List<EditorError> fieldErrors = new ArrayList<EditorError>();
						fieldErrors.add(error);
						login.showErrors(fieldErrors);
					}
				}
			}
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
		setRelationHandlers();
		setFieldValueChangeHandler();
		super.onLoad();
	}

}
