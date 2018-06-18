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
import org.imogene.epicam.shared.proxy.CandidatureFormationProxy;

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
import org.imogene.epicam.client.ui.workflow.panel.PersonnelFormPanel;
import org.imogene.epicam.client.event.save.SavePersonnelEvent;
import org.imogene.epicam.client.dataprovider.PersonnelDataProvider;
import org.imogene.epicam.client.ui.filter.PersonnelFilterPanel;
import org.imogene.epicam.shared.proxy.PersonnelProxy;

/**
 * Editor that provides the UI components that allow a CandidatureFormationProxy to be viewed and edited
 * as a Nested Form
 * @author MEDES-IMPS
 */
public class CandidatureFormationEditorNestedForm extends Composite
		implements
			Editor<CandidatureFormationProxy>,
			HasEditorDelegate<CandidatureFormationProxy>,
			HasEditorErrors<CandidatureFormationProxy> {

	interface Binder
			extends
				UiBinder<Widget, CandidatureFormationEditorNestedForm> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<CandidatureFormationProxy> delegate;

	private boolean hideButtons = false;
	private int index = 0;
	private boolean isNewProxy = false;
	private boolean isClearImageActive = false;

	@UiField
	Image clearImage;

	@UiField
	@Ignore
	FieldGroupPanel candidatureFormationSection;
	@UiField(provided = true)
	ImogSingleRelationBox<RegionProxy> region;
	private RegionDataProvider regionDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<DistrictSanteProxy> districtSante;
	private DistrictSanteDataProvider districtSanteDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<CentreDiagTraitProxy> CDT;
	private CentreDiagTraitDataProvider cDTDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<PersonnelProxy> personnel;
	private PersonnelDataProvider personnelDataProvider;
	@UiField
	ImogBooleanBox approuveeRegion;
	@UiField
	ImogTextAreaBox motifRejetRegion;
	@UiField
	ImogBooleanBox approuveeGTC;
	@UiField
	ImogTextAreaBox motifRejetGTC;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public CandidatureFormationEditorNestedForm(EpicamRequestFactory factory,
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
	public CandidatureFormationEditorNestedForm(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	public void properties() {

		candidatureFormationSection.setGroupTitle(NLS.constants()
				.candidatureFormation_name());
		candidatureFormationSection.setLabelFontSize("12px");

		region.setLabel(NLS.constants().candidatureFormation_field_region());
		// the value of region affects the value of other fields
		region.notifyChanges(requestFactory.getEventBus());
		districtSante.setLabel(NLS.constants()
				.candidatureFormation_field_districtSante());
		// the value of districtSante affects the value of other fields
		districtSante.notifyChanges(requestFactory.getEventBus());
		CDT.setLabel(NLS.constants().candidatureFormation_field_cDT());
		// the value of CDT affects the value of other fields
		CDT.notifyChanges(requestFactory.getEventBus());
		personnel.setLabel(NLS.constants()
				.candidatureFormation_field_personnel());
		approuveeRegion.setLabel(NLS.constants()
				.candidatureFormation_field_approuveeRegion());
		// the value of approuveeRegion affects the visibility of other fields
		approuveeRegion.notifyChanges(requestFactory.getEventBus());
		motifRejetRegion.setLabel(NLS.constants()
				.candidatureFormation_field_motifRejetRegion());
		// the visibility of motifRejetRegion depends on the value of other fields
		motifRejetRegion.addStyleName("dependentVisibility");
		approuveeGTC.setLabel(NLS.constants()
				.candidatureFormation_field_approuveeGTC());
		// the value of approuveeGTC affects the visibility of other fields
		approuveeGTC.notifyChanges(requestFactory.getEventBus());
		motifRejetGTC.setLabel(NLS.constants()
				.candidatureFormation_field_motifRejetGTC());
		// the visibility of motifRejetGTC depends on the value of other fields
		motifRejetGTC.addStyleName("dependentVisibility");
	}

	/**
	 * Configures the widgets that manage relation fields
	 */
	public void setRelationFields() {

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

		/* field  personnel */
		personnelDataProvider = new PersonnelDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			personnel = new ImogSingleRelationBox<PersonnelProxy>(
					personnelDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreatePersonnel()
					&& AccessManager.canEditPersonnel())
				personnel = new ImogSingleRelationBox<PersonnelProxy>(
						personnelDataProvider, EpicamRenderer.get());
			else
				personnel = new ImogSingleRelationBox<PersonnelProxy>(false,
						personnelDataProvider, EpicamRenderer.get());
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

		region.setEdited(isEdited);
		districtSante.setEdited(isEdited);
		CDT.setEdited(isEdited);
		personnel.setEdited(isEdited);
		approuveeRegion.setEdited(isEdited);
		motifRejetRegion.setEdited(isEdited);
		approuveeGTC.setEdited(isEdited);
		motifRejetGTC.setEdited(isEdited);
	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	public void setFieldReadAccess() {

		if (!AccessManager.canReadCandidatureFormationDescription())
			region.setVisible(false);

		if (!AccessManager.canReadCandidatureFormationDescription())
			districtSante.setVisible(false);

		if (!AccessManager.canReadCandidatureFormationDescription())
			CDT.setVisible(false);

		if (!AccessManager.canReadCandidatureFormationDescription())
			personnel.setVisible(false);

		if (!AccessManager.canReadCandidatureFormationRegionApprobation())
			approuveeRegion.setVisible(false);

		if (!AccessManager.canReadCandidatureFormationRegionApprobation())
			motifRejetRegion.setVisible(false);

		if (!AccessManager.canReadCandidatureFormationGtcApprobation())
			approuveeGTC.setVisible(false);

		if (!AccessManager.canReadCandidatureFormationGtcApprobation())
			motifRejetGTC.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	public void setFieldEditAccess() {

		if (!AccessManager.canEditCandidatureFormationDescription())
			region.setVisible(false);

		if (!AccessManager.canEditCandidatureFormationDescription())
			districtSante.setVisible(false);

		if (!AccessManager.canEditCandidatureFormationDescription())
			CDT.setVisible(false);

		if (!AccessManager.canEditCandidatureFormationDescription())
			personnel.setVisible(false);

		if (!AccessManager.canEditCandidatureFormationRegionApprobation())
			approuveeRegion.setVisible(false);

		if (!AccessManager.canEditCandidatureFormationRegionApprobation())
			motifRejetRegion.setVisible(false);

		if (!AccessManager.canEditCandidatureFormationGtcApprobation())
			approuveeGTC.setVisible(false);

		if (!AccessManager.canEditCandidatureFormationGtcApprobation())
			motifRejetGTC.setVisible(false);

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

						/* DistrictSante list content depends on the value of field Region */
						if (field.equals(region)) {
							clearDistrictSanteWidget();
							getDistrictSanteFilteredByRegion(region.getValue());

						}
						if (field.equals(CDT)) {
							clearPersonnelWidget();
							getPersonnelFilteredByCDT(CDT.getValue());

							CentreDiagTraitProxy cdtValue = CDT.getValue();
							if (cdtValue != null) {
								RegionProxy regionValue = cdtValue.getRegion();
								region.setValue(regionValue);
								DistrictSanteProxy districtValue = cdtValue
										.getDistrictSante();
								districtSante.setValue(districtValue);
							}
						}
						/* Personnel list content depends on the value of field CDT */
						if (field.equals(CDT)) {
							clearPersonnelWidget();
							getPersonnelFilteredByCDT(CDT.getValue());

						}
					}
				}));
	}

	/**
	 * Computes the field visibility
	 */
	public void computeVisibility(ImogField<?> source, boolean allValidation) {

		// the visibility of field motifRejetRegion depends on the value of other fields
		if (allValidation || source.equals(approuveeRegion)) {
			if ((approuveeRegion.getValue() != null && !approuveeRegion
					.getValue())) {
				motifRejetRegion.setVisible(true);
			} else {
				motifRejetRegion.setVisible(false);
			}
		}

		// the visibility of field motifRejetGTC depends on the value of other fields
		if (allValidation || source.equals(approuveeGTC)) {
			if ((approuveeGTC.getValue() != null && !approuveeGTC.getValue())) {
				motifRejetGTC.setVisible(true);
			} else {
				motifRejetGTC.setVisible(false);
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

	/**
	 * Filters the content of the RelationField Personnel by the value of 
	 * the RelationField CDT
	 * @param cDT the value of 
	 * the RelationField CDT 
	 */
	public void getPersonnelFilteredByCDT(CentreDiagTraitProxy pCDT) {

		if (pCDT != null) {
			if (!hideButtons)
				personnel.hideButtons(false);
			personnelDataProvider.setFilterCriteria(pCDT.getId(), "CDT.id");
		} else {
			personnel.hideButtons(true);
			personnelDataProvider.setFilterCriteria(null);
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

	public void setDeleteClickHandler(ClickHandler handler) {
		//registrations.add(clearImage.addClickHandler(handler));
		clearImage.addClickHandler(handler);
		isClearImageActive = true;
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

		// Field Personnel depends on the value of field cDT
		clearPersonnelWidget();
		getPersonnelFilteredByCDT(value);
	}

	/** Widget update for field CDT */
	private void clearCDTWidget() {
		CDT.clear();
		clearPersonnelWidget();

	}

	/**
	 * Setter to inject a Personnel value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setPersonnel(PersonnelProxy value, boolean isLocked) {
		personnel.setLocked(isLocked);
		personnel.setValue(value);

	}

	/** Widget update for field personnel */
	private void clearPersonnelWidget() {
		personnel.clear();

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

		/* 'Information' button for field Personnel */
		personnel.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (personnel.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					PersonnelFormPanel form = new PersonnelFormPanel(
							requestFactory, personnel.getValue().getId(),
							relationPopup, "personnel");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field Personnel */
		personnel.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				PersonnelFormPanel form = new PersonnelFormPanel(
						requestFactory, null, relationPopup, "personnel");
				/* common fields */
				if (region.getValue() != null)
					form.setRegion(region.getValue(), true);
				if (districtSante.getValue() != null)
					form.setDistrictSante(districtSante.getValue(), true);
				if (CDT.getValue() != null)
					form.setCDT(CDT.getValue(), true);

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a Personnel is created or updated from the relation field Personnel */
		registrations.add(requestFactory.getEventBus().addHandler(
				SavePersonnelEvent.TYPE, new SavePersonnelEvent.Handler() {
					@Override
					public void savePersonnel(PersonnelProxy value) {
						personnel.setValue(value);
					}
					@Override
					public void savePersonnel(PersonnelProxy value,
							String initField) {
						if (initField.equals("personnel"))
							personnel.setValue(value, true);
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

		// cDT is a required field
		if (CDT.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"cDT");
		// personnel is a required field
		if (personnel.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"personnel");
	}

	/**
	 */
	public void updatecandidatureFormationSectionLabel(String label) {
		candidatureFormationSection.setGroupTitle(label);
	}

	@Override
	public void setDelegate(EditorDelegate<CandidatureFormationProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> cDTFieldErrors = new ArrayList<EditorError>();
			List<EditorError> personnelFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("cDT"))
						cDTFieldErrors.add(error);
					if (field.equals("personnel"))
						personnelFieldErrors.add(error);
				}
			}
			if (cDTFieldErrors.size() > 0)
				CDT.showErrors(cDTFieldErrors);
			if (personnelFieldErrors.size() > 0)
				personnel.showErrors(personnelFieldErrors);
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
