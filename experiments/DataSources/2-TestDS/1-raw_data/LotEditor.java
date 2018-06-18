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
import org.imogene.epicam.shared.proxy.LotProxy;
import org.imogene.epicam.shared.request.LotRequest;

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
import org.imogene.epicam.client.ui.workflow.panel.CentreDiagTraitFormPanel;
import org.imogene.epicam.client.event.save.SaveCentreDiagTraitEvent;
import org.imogene.epicam.client.dataprovider.CentreDiagTraitDataProvider;
import org.imogene.epicam.client.ui.filter.CentreDiagTraitFilterPanel;
import org.imogene.epicam.shared.proxy.CentreDiagTraitProxy;
import org.imogene.epicam.client.ui.workflow.panel.MedicamentFormPanel;
import org.imogene.epicam.client.event.save.SaveMedicamentEvent;
import org.imogene.epicam.client.dataprovider.MedicamentDataProvider;
import org.imogene.epicam.client.ui.filter.MedicamentFilterPanel;
import org.imogene.epicam.shared.proxy.MedicamentProxy;
import org.imogene.epicam.client.ui.workflow.panel.IntrantFormPanel;
import org.imogene.epicam.client.event.save.SaveIntrantEvent;
import org.imogene.epicam.client.dataprovider.IntrantDataProvider;
import org.imogene.epicam.client.ui.filter.IntrantFilterPanel;
import org.imogene.epicam.shared.proxy.IntrantProxy;

/**
 * Editor that provides the UI components that allow a LotProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class LotEditor extends Composite
		implements
			Editor<LotProxy>,
			HasEditorDelegate<LotProxy>,
			HasEditorErrors<LotProxy> {

	interface Binder extends UiBinder<Widget, LotEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<LotProxy> delegate;

	private LotProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;

	/* Description section widgets */
	@UiField
	@Ignore
	FieldGroupPanel descriptionSection;
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
	ImogTextBox numero;
	@UiField
	ImogSingleEnumBox type;
	@UiField(provided = true)
	ImogSingleRelationBox<MedicamentProxy> medicament;
	private MedicamentDataProvider medicamentDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<IntrantProxy> intrant;
	private IntrantDataProvider intrantDataProvider;
	@UiField
	ImogIntegerBox quantiteInitiale;
	@UiField
	ImogIntegerBox quantite;
	@UiField
	ImogDateBox datePeremption;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public LotEditor(EpicamRequestFactory factory, boolean hideButtons) {

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
	public LotEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* Description section widgets */
		descriptionSection.setGroupTitle(NLS.constants()
				.lot_group_description());
		region.setLabel(NLS.constants().lot_field_region());
		// the value of region affects the value of other fields
		region.notifyChanges(requestFactory.getEventBus());
		districtSante.setLabel(NLS.constants().lot_field_districtSante());
		// the value of districtSante affects the value of other fields
		districtSante.notifyChanges(requestFactory.getEventBus());
		CDT.setLabel(NLS.constants().lot_field_cDT());
		numero.setLabel(NLS.constants().lot_field_numero());
		type.setLabel(NLS.constants().lot_field_type());
		type.addItem(EpicamEnumConstants.LOT_TYPE_MEDICAMENT, NLS.constants()
				.lot_type_medicament_option());
		type.addItem(EpicamEnumConstants.LOT_TYPE_INTRANT, NLS.constants()
				.lot_type_intrant_option());
		// the value of type affects the visibility of other fields
		type.notifyChanges(requestFactory.getEventBus());
		medicament.setLabel(NLS.constants().lot_field_medicament());
		// the visibility of medicament depends on the value of other fields
		medicament.addStyleName("dependentVisibility");
		intrant.setLabel(NLS.constants().lot_field_intrant());
		// the visibility of intrant depends on the value of other fields
		intrant.addStyleName("dependentVisibility");
		quantiteInitiale.setLabel(NLS.constants().lot_field_quantiteInitiale());
		quantite.setLabel(NLS.constants().lot_field_quantite());
		datePeremption.setLabel(NLS.constants().lot_field_datePeremption());

		// the value of CDT affects the value of other fields
		CDT.notifyChanges(requestFactory.getEventBus());

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

		/* field  medicament */
		medicamentDataProvider = new MedicamentDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			medicament = new ImogSingleRelationBox<MedicamentProxy>(
					medicamentDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateMedicament()
					&& AccessManager.canEditMedicament())
				medicament = new ImogSingleRelationBox<MedicamentProxy>(
						medicamentDataProvider, EpicamRenderer.get());
			else
				medicament = new ImogSingleRelationBox<MedicamentProxy>(false,
						medicamentDataProvider, EpicamRenderer.get());
		}

		/* field  intrant */
		intrantDataProvider = new IntrantDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			intrant = new ImogSingleRelationBox<IntrantProxy>(
					intrantDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateIntrant()
					&& AccessManager.canEditIntrant())
				intrant = new ImogSingleRelationBox<IntrantProxy>(
						intrantDataProvider, EpicamRenderer.get());
			else
				intrant = new ImogSingleRelationBox<IntrantProxy>(false,
						intrantDataProvider, EpicamRenderer.get());
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
		region.setEdited(isEdited);
		districtSante.setEdited(isEdited);
		CDT.setEdited(isEdited);
		numero.setEdited(isEdited);
		type.setEdited(isEdited);
		medicament.setEdited(isEdited);
		intrant.setEdited(isEdited);
		quantiteInitiale.setEdited(isEdited);
		// readonly field
		quantite.setEdited(false);
		datePeremption.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canReadLotDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canEditLotDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(LotRequest ctx) {
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

		// the visibility of field medicament depends on the value of other fields
		if (allValidation || source.equals(type)) {
			if ((type.getValue() != null && type.getValue().matches("0"))) {
				medicament.setVisible(true);
			} else {
				medicament.setVisible(false);
			}
		}

		// the visibility of field intrant depends on the value of other fields
		if (allValidation || source.equals(type)) {
			if ((type.getValue() != null && type.getValue().matches("1"))) {
				intrant.setVisible(true);
			} else {
				intrant.setVisible(false);
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
	 * Setter to inject a Medicament value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setMedicament(MedicamentProxy value, boolean isLocked) {
		medicament.setLocked(isLocked);
		medicament.setValue(value);

	}

	/** Widget update for field medicament */
	private void clearMedicamentWidget() {
		medicament.clear();
	}

	/**
	 * Setter to inject a Intrant value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setIntrant(IntrantProxy value, boolean isLocked) {
		intrant.setLocked(isLocked);
		intrant.setValue(value);

	}

	/** Widget update for field intrant */
	private void clearIntrantWidget() {
		intrant.clear();
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

		/* 'Information' button for field Medicament */
		medicament.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (medicament.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					MedicamentFormPanel form = new MedicamentFormPanel(
							requestFactory, medicament.getValue().getId(),
							relationPopup, "medicament");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field Medicament */
		medicament.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				MedicamentFormPanel form = new MedicamentFormPanel(
						requestFactory, null, relationPopup, "medicament");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a Medicament is created or updated from the relation field Medicament */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveMedicamentEvent.TYPE, new SaveMedicamentEvent.Handler() {
					@Override
					public void saveMedicament(MedicamentProxy value) {
						medicament.setValue(value);
					}
					@Override
					public void saveMedicament(MedicamentProxy value,
							String initField) {
						if (initField.equals("medicament"))
							medicament.setValue(value, true);
					}
				}));

		/* 'Information' button for field Intrant */
		intrant.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (intrant.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					IntrantFormPanel form = new IntrantFormPanel(
							requestFactory, intrant.getValue().getId(),
							relationPopup, "intrant");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field Intrant */
		intrant.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				IntrantFormPanel form = new IntrantFormPanel(requestFactory,
						null, relationPopup, "intrant");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a Intrant is created or updated from the relation field Intrant */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveIntrantEvent.TYPE, new SaveIntrantEvent.Handler() {
					@Override
					public void saveIntrant(IntrantProxy value) {
						intrant.setValue(value);
					}
					@Override
					public void saveIntrant(IntrantProxy value, String initField) {
						if (initField.equals("intrant"))
							intrant.setValue(value, true);
					}
				}));

	}

	/**
	 * Gets the LotProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public LotProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the LotProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(LotProxy editedValue) {
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

		// region is a required field
		if (region.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"region");
		// districtSante is a required field
		if (districtSante.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"districtSante");
		// cDT is a required field
		if (CDT.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"cDT");
		// numero is a required field
		if (numero.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"numero");
		// type is a required field
		if (type.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"type");
		// quantiteInitiale is a required field
		if (quantiteInitiale.getValueWithoutParseException() == null
				&& quantiteInitiale.isValid())
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"quantiteInitiale");
		// quantiteInitiale shall be superior or equal to '0'
		if (quantiteInitiale.getValueWithoutParseException() != null
				&& !(quantiteInitiale.getValueWithoutParseException() >= 0))
			delegate.recordError(
					BaseNLS.messages().error_min_num(
							NLS.constants().lot_field_quantiteInitiale_min()),
					null, "quantiteInitiale");

		// quantite shall be superior or equal to '0'
		if (quantite.getValueWithoutParseException() != null
				&& !(quantite.getValueWithoutParseException() >= 0))
			delegate.recordError(
					BaseNLS.messages().error_min_num(
							NLS.constants().lot_field_quantite_min()), null,
					"quantite");

		// datePeremption is a required field
		if (datePeremption.getValueWithoutParseException() == null
				&& datePeremption.isValid())
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"datePeremption");
	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* Description field group */
		region.setLabelWidth(width);
		districtSante.setLabelWidth(width);
		CDT.setLabelWidth(width);
		numero.setLabelWidth(width);
		type.setLabelWidth(width);
		medicament.setLabelWidth(width);
		intrant.setLabelWidth(width);
		quantiteInitiale.setLabelWidth(width);
		quantite.setLabelWidth(width);
		datePeremption.setLabelWidth(width);

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* Description field group */
		region.setBoxWidth(width);
		districtSante.setBoxWidth(width);
		CDT.setBoxWidth(width);
		numero.setBoxWidth(width);
		type.setBoxWidth(width);
		medicament.setBoxWidth(width);
		intrant.setBoxWidth(width);

	}

	public String getNumeroLot() {
		return numero.getValue();
	}

	public CentreDiagTraitProxy getCdtLot() {
		return CDT.getValue();
	}

	public void raiseLotNotUniqueError() {
		delegate.recordError(BaseNLS.messages().error_not_unique(), null,
				"numero");
	}

	@Override
	public void setDelegate(EditorDelegate<LotProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> regionFieldErrors = new ArrayList<EditorError>();
			List<EditorError> districtSanteFieldErrors = new ArrayList<EditorError>();
			List<EditorError> cDTFieldErrors = new ArrayList<EditorError>();
			List<EditorError> numeroFieldErrors = new ArrayList<EditorError>();
			List<EditorError> typeFieldErrors = new ArrayList<EditorError>();
			List<EditorError> quantiteInitialeFieldErrors = new ArrayList<EditorError>();
			List<EditorError> quantiteFieldErrors = new ArrayList<EditorError>();
			List<EditorError> datePeremptionFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("region"))
						regionFieldErrors.add(error);
					if (field.equals("districtSante"))
						districtSanteFieldErrors.add(error);
					if (field.equals("cDT"))
						cDTFieldErrors.add(error);
					if (field.equals("numero"))
						numeroFieldErrors.add(error);
					if (field.equals("type"))
						typeFieldErrors.add(error);
					if (field.equals("quantiteInitiale"))
						quantiteInitialeFieldErrors.add(error);
					if (field.equals("quantite"))
						quantiteFieldErrors.add(error);
					if (field.equals("datePeremption"))
						datePeremptionFieldErrors.add(error);

				}
			}
			if (regionFieldErrors.size() > 0)
				region.showErrors(regionFieldErrors);
			if (districtSanteFieldErrors.size() > 0)
				districtSante.showErrors(districtSanteFieldErrors);
			if (cDTFieldErrors.size() > 0)
				CDT.showErrors(cDTFieldErrors);
			if (numeroFieldErrors.size() > 0)
				numero.showErrors(numeroFieldErrors);
			if (typeFieldErrors.size() > 0)
				type.showErrors(typeFieldErrors);
			if (quantiteInitialeFieldErrors.size() > 0)
				quantiteInitiale.showErrors(quantiteInitialeFieldErrors);
			if (quantiteFieldErrors.size() > 0)
				quantite.showErrors(quantiteFieldErrors);
			if (datePeremptionFieldErrors.size() > 0)
				datePeremption.showErrors(datePeremptionFieldErrors);
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
