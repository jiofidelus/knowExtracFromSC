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
import org.imogene.epicam.shared.proxy.LaboratoireReferenceProxy;
import org.imogene.epicam.shared.request.LaboratoireReferenceRequest;

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
import org.imogene.epicam.client.ui.workflow.panel.LieuDitFormPanel;
import org.imogene.epicam.client.event.save.SaveLieuDitEvent;
import org.imogene.epicam.client.dataprovider.LieuDitDataProvider;
import org.imogene.epicam.client.ui.filter.LieuDitFilterPanel;
import org.imogene.epicam.shared.proxy.LieuDitProxy;

/**
 * Editor that provides the UI components that allow a LaboratoireReferenceProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class LaboratoireReferenceEditor extends Composite
		implements
			Editor<LaboratoireReferenceProxy>,
			HasEditorDelegate<LaboratoireReferenceProxy>,
			HasEditorErrors<LaboratoireReferenceProxy> {

	interface Binder extends UiBinder<Widget, LaboratoireReferenceEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<LaboratoireReferenceProxy> delegate;

	private LaboratoireReferenceProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;
	private List<String> locales = Arrays.asList("fr", "en");

	/* Description section widgets */
	@UiField
	@Ignore
	FieldGroupPanel descriptionSection;
	@UiField(provided = true)
	ImogLocalizedTextBox nom;
	@UiField
	ImogSingleEnumBox nature;
	@UiField(provided = true)
	ImogLocalizedTextAreaBox description;
	@UiField(provided = true)
	ImogSingleRelationBox<RegionProxy> region;
	private RegionDataProvider regionDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<DistrictSanteProxy> districtSante;
	private DistrictSanteDataProvider districtSanteDataProvider;

	/* Adresse section widgets */
	@UiField
	@Ignore
	FieldGroupPanel adresseSection;
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
	@UiField
	ImogGeoBox coordonnees;
	@UiField(provided = true)
	ImogMultiRelationBox<LieuDitProxy> lieuxDits;
	private LieuDitDataProvider lieuxDitsDataProvider;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public LaboratoireReferenceEditor(EpicamRequestFactory factory,
			boolean hideButtons) {

		this.requestFactory = factory;
		this.hideButtons = hideButtons;

		setRelationFields();

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
	public LaboratoireReferenceEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* Description section widgets */
		descriptionSection.setGroupTitle(NLS.constants()
				.laboratoireReference_group_description());
		nom.setLabel(NLS.constants().laboratoireReference_field_nom());
		nature.setLabel(NLS.constants().laboratoireReference_field_nature());
		nature.addItem(
				EpicamEnumConstants.LABORATOIREREFERENCE_NATURE_NATIONAL, NLS
						.constants()
						.laboratoireReference_nature_national_option());
		nature.addItem(
				EpicamEnumConstants.LABORATOIREREFERENCE_NATURE_REGIONAL, NLS
						.constants()
						.laboratoireReference_nature_regional_option());
		description.setLabel(NLS.constants()
				.laboratoireReference_field_description());
		region.setLabel(NLS.constants().laboratoireReference_field_region());
		// the value of region affects the value of other fields
		region.notifyChanges(requestFactory.getEventBus());
		districtSante.setLabel(NLS.constants()
				.laboratoireReference_field_districtSante());

		/* Adresse section widgets */
		adresseSection.setGroupTitle(NLS.constants()
				.laboratoireReference_group_adresse());
		libelle.setLabel(NLS.constants().laboratoireReference_field_libelle());
		libelle.addItem(
				EpicamEnumConstants.LABORATOIREREFERENCE_LIBELLE_DOMICILE, NLS
						.constants()
						.laboratoireReference_libelle_domicile_option());
		libelle.addItem(
				EpicamEnumConstants.LABORATOIREREFERENCE_LIBELLE_BUREAU, NLS
						.constants()
						.laboratoireReference_libelle_bureau_option());
		libelle.addItem(EpicamEnumConstants.LABORATOIREREFERENCE_LIBELLE_AUTRE,
				NLS.constants().laboratoireReference_libelle_autre_option());
		complementAdresse.setLabel(NLS.constants()
				.laboratoireReference_field_complementAdresse());
		quartier.setLabel(NLS.constants().laboratoireReference_field_quartier());
		ville.setLabel(NLS.constants().laboratoireReference_field_ville());
		codePostal.setLabel(NLS.constants()
				.laboratoireReference_field_codePostal());
		coordonnees.setLabel(NLS.constants()
				.laboratoireReference_field_coordonnees());
		lieuxDits.setLabel(NLS.constants()
				.laboratoireReference_field_lieuxDits());

	}

	/**
	 * Configures the widgets that manage relation fields
	 */
	private void setRelationFields() {

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
		nom.setEdited(isEdited);
		nature.setEdited(isEdited);
		description.setEdited(isEdited);
		region.setEdited(isEdited);
		districtSante.setEdited(isEdited);

		/* Adresse section widgets */
		libelle.setEdited(isEdited);
		complementAdresse.setEdited(isEdited);
		quartier.setEdited(isEdited);
		ville.setEdited(isEdited);
		codePostal.setEdited(isEdited);
		coordonnees.setEdited(isEdited);
		lieuxDits.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canReadLaboratoireReferenceDescription())
			descriptionSection.setVisible(false);

		/* Adresse section widgets visibility */
		if (!AccessManager.canReadLaboratoireReferenceAdresse())
			adresseSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canEditLaboratoireReferenceDescription())
			descriptionSection.setVisible(false);

		/* Adresse section widgets visibility */
		if (!AccessManager.canEditLaboratoireReferenceAdresse())
			adresseSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(LaboratoireReferenceRequest ctx) {
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

					}
				}));
	}

	/**
	 * Computes the field visibility
	 */
	public void computeVisibility(ImogField<?> source, boolean allValidation) {

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
			districtSante.hideButtons(true);
			districtSanteDataProvider.setFilterCriteria(null);
		}
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

	}

	/** Widget update for field districtSante */
	private void clearDistrictSanteWidget() {
		districtSante.clear();

	}

	/** Widget update for field lieuxDits */
	private void clearLieuxDitsWidget() {
		lieuxDits.emptyList();
	}

	/**
	 * Configures the handlers of the widgets that manage relation fields
	 */
	private void setRelationHandlers() {

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

	}

	/**
	 * Gets the LaboratoireReferenceProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public LaboratoireReferenceProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the LaboratoireReferenceProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(LaboratoireReferenceProxy editedValue) {
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
		// nature is a required field
		if (nature.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"nature");
		// region is a required field
		if (region.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"region");
	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* Description field group */
		nom.setLabelWidth(width);
		nature.setLabelWidth(width);
		description.setLabelWidth(width);
		region.setLabelWidth(width);
		districtSante.setLabelWidth(width);

		/* Adresse field group */
		libelle.setLabelWidth(width);
		complementAdresse.setLabelWidth(width);
		quartier.setLabelWidth(width);
		ville.setLabelWidth(width);
		codePostal.setLabelWidth(width);
		coordonnees.setLabelWidth(width);
		lieuxDits.setLabelWidth(width);

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* Description field group */
		nom.setBoxWidth(width);
		nature.setBoxWidth(width);
		description.setBoxWidth(width);
		region.setBoxWidth(width);
		districtSante.setBoxWidth(width);

		/* Adresse field group */
		libelle.setBoxWidth(width);
		complementAdresse.setBoxWidth(width);
		quartier.setBoxWidth(width);
		ville.setBoxWidth(width);
		codePostal.setBoxWidth(width);
		coordonnees.setBoxWidth(width);
		lieuxDits.setBoxWidth(width);

	}

	@Override
	public void setDelegate(EditorDelegate<LaboratoireReferenceProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> nomFieldErrors = new ArrayList<EditorError>();
			List<EditorError> natureFieldErrors = new ArrayList<EditorError>();
			List<EditorError> regionFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("nom"))
						nomFieldErrors.add(error);
					if (field.equals("nature"))
						natureFieldErrors.add(error);
					if (field.equals("region"))
						regionFieldErrors.add(error);

				}
			}
			if (nomFieldErrors.size() > 0)
				nom.showErrors(nomFieldErrors);
			if (natureFieldErrors.size() > 0)
				nature.showErrors(natureFieldErrors);
			if (regionFieldErrors.size() > 0)
				region.showErrors(regionFieldErrors);
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
