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
import org.imogene.epicam.shared.proxy.CommandeProxy;
import org.imogene.epicam.shared.request.CommandeRequest;

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
import org.imogene.epicam.client.ui.workflow.panel.DetailCommandeMedicamentFormPanel;
import org.imogene.epicam.client.event.save.SaveDetailCommandeMedicamentEvent;
import org.imogene.epicam.client.dataprovider.DetailCommandeMedicamentDataProvider;
import org.imogene.epicam.client.ui.filter.DetailCommandeMedicamentFilterPanel;
import org.imogene.epicam.shared.proxy.DetailCommandeMedicamentProxy;
import org.imogene.epicam.client.ui.editor.nested.CommandeMedicamentsListEditor;
import org.imogene.epicam.shared.request.CommandeRequest;
import org.imogene.epicam.client.ui.workflow.panel.DetailCommandeIntrantFormPanel;
import org.imogene.epicam.client.event.save.SaveDetailCommandeIntrantEvent;
import org.imogene.epicam.client.dataprovider.DetailCommandeIntrantDataProvider;
import org.imogene.epicam.client.ui.filter.DetailCommandeIntrantFilterPanel;
import org.imogene.epicam.shared.proxy.DetailCommandeIntrantProxy;
import org.imogene.epicam.client.ui.editor.nested.CommandeIntrantsListEditor;
import org.imogene.epicam.shared.request.CommandeRequest;

/**
 * Editor that provides the UI components that allow a CommandeProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class CommandeEditor extends Composite
		implements
			Editor<CommandeProxy>,
			HasEditorDelegate<CommandeProxy>,
			HasEditorErrors<CommandeProxy> {

	interface Binder extends UiBinder<Widget, CommandeEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<CommandeProxy> delegate;

	private CommandeProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;

	/* InformationsDepart section widgets */
	@UiField
	@Ignore
	FieldGroupPanel informationsDepartSection;
	@UiField
	ImogDateBox date;
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
	CommandeMedicamentsListEditor medicaments;
	private DetailCommandeMedicamentDataProvider medicamentsDataProvider;
	@UiField(provided = true)
	CommandeIntrantsListEditor intrants;
	private DetailCommandeIntrantDataProvider intrantsDataProvider;

	/* RegionApprobation section widgets */
	@UiField
	@Ignore
	FieldGroupPanel regionApprobationSection;
	@UiField
	ImogBooleanBox approuveeRegion;
	@UiField
	ImogTextAreaBox motifRejetRegion;

	/* GtcApprobation section widgets */
	@UiField
	@Ignore
	FieldGroupPanel gtcApprobationSection;
	@UiField
	ImogBooleanBox approuveeGTC;
	@UiField
	ImogTextAreaBox motifRejetGTC;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public CommandeEditor(EpicamRequestFactory factory, boolean hideButtons) {

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
	public CommandeEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* InformationsDepart section widgets */
		informationsDepartSection.setGroupTitle(NLS.constants()
				.commande_group_informationsDepart());
		date.setLabel(NLS.constants().commande_field_date());
		region.setLabel(NLS.constants().commande_field_region());
		// the value of region affects the value of other fields
		region.notifyChanges(requestFactory.getEventBus());
		districtSante.setLabel(NLS.constants().commande_field_districtSante());
		// the value of districtSante affects the value of other fields
		districtSante.notifyChanges(requestFactory.getEventBus());
		CDT.setLabel(NLS.constants().commande_field_cDT());

		/* RegionApprobation section widgets */
		regionApprobationSection.setGroupTitle(NLS.constants()
				.commande_group_regionApprobation());
		approuveeRegion.setLabel(NLS.constants()
				.commande_field_approuveeRegion());
		// the value of approuveeRegion affects the visibility of other fields
		approuveeRegion.notifyChanges(requestFactory.getEventBus());
		motifRejetRegion.setLabel(NLS.constants()
				.commande_field_motifRejetRegion());
		// the visibility of motifRejetRegion depends on the value of other fields
		motifRejetRegion.addStyleName("dependentVisibility");

		/* GtcApprobation section widgets */
		gtcApprobationSection.setGroupTitle(NLS.constants()
				.commande_group_gtcApprobation());
		approuveeGTC.setLabel(NLS.constants().commande_field_approuveeGTC());
		// the value of approuveeGTC affects the visibility of other fields
		approuveeGTC.notifyChanges(requestFactory.getEventBus());
		motifRejetGTC.setLabel(NLS.constants().commande_field_motifRejetGTC());
		// the visibility of motifRejetGTC depends on the value of other fields
		motifRejetGTC.addStyleName("dependentVisibility");

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

		/* field  medicaments */
		medicaments = new CommandeMedicamentsListEditor(requestFactory);

		/* field  intrants */
		intrants = new CommandeIntrantsListEditor(requestFactory);

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

		/* InformationsDepart section widgets */
		date.setEdited(isEdited);
		region.setEdited(isEdited);
		districtSante.setEdited(isEdited);
		CDT.setEdited(isEdited);
		medicaments.setEdited(isEdited);
		intrants.setEdited(isEdited);

		/* RegionApprobation section widgets */
		approuveeRegion.setEdited(isEdited);
		motifRejetRegion.setEdited(isEdited);

		/* GtcApprobation section widgets */
		approuveeGTC.setEdited(isEdited);
		motifRejetGTC.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* InformationsDepart section widgets visibility */
		if (!AccessManager.canReadCommandeInformationsDepart())
			informationsDepartSection.setVisible(false);

		/* RegionApprobation section widgets visibility */
		if (!AccessManager.canReadCommandeRegionApprobation())
			regionApprobationSection.setVisible(false);

		/* GtcApprobation section widgets visibility */
		if (!AccessManager.canReadCommandeGtcApprobation())
			gtcApprobationSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* InformationsDepart section widgets visibility */
		if (!AccessManager.canEditCommandeInformationsDepart())
			informationsDepartSection.setVisible(false);

		/* RegionApprobation section widgets visibility */
		if (!AccessManager.canEditCommandeRegionApprobation())
			regionApprobationSection.setVisible(false);

		/* GtcApprobation section widgets visibility */
		if (!AccessManager.canEditCommandeGtcApprobation())
			gtcApprobationSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(CommandeRequest ctx) {
		medicaments.setRequestContextForListEditors(ctx);
		intrants.setRequestContextForListEditors(ctx);
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
		medicaments.computeVisibility(source, allValidation);
		intrants.computeVisibility(source, allValidation);

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

	}

	/**
	 * Gets the CommandeProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public CommandeProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the CommandeProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(CommandeProxy editedValue) {
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

		// date is a required field
		if (date.getValueWithoutParseException() == null && date.isValid())
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"date");
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
		// medicaments nested form shall be validated
		medicaments.validateFields();
		// intrants nested form shall be validated
		intrants.validateFields();
	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* InformationsDepart field group */
		date.setLabelWidth(width);
		region.setLabelWidth(width);
		districtSante.setLabelWidth(width);
		CDT.setLabelWidth(width);

		/* RegionApprobation field group */
		approuveeRegion.setLabelWidth(width);
		motifRejetRegion.setLabelWidth(width);

		/* GtcApprobation field group */
		approuveeGTC.setLabelWidth(width);
		motifRejetGTC.setLabelWidth(width);

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* InformationsDepart field group */
		region.setBoxWidth(width);
		districtSante.setBoxWidth(width);
		CDT.setBoxWidth(width);

		/* RegionApprobation field group */
		motifRejetRegion.setBoxWidth(width);

		/* GtcApprobation field group */
		motifRejetGTC.setBoxWidth(width);

	}

	@Override
	public void setDelegate(EditorDelegate<CommandeProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> dateFieldErrors = new ArrayList<EditorError>();
			List<EditorError> regionFieldErrors = new ArrayList<EditorError>();
			List<EditorError> districtSanteFieldErrors = new ArrayList<EditorError>();
			List<EditorError> cDTFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("date"))
						dateFieldErrors.add(error);
					if (field.equals("region"))
						regionFieldErrors.add(error);
					if (field.equals("districtSante"))
						districtSanteFieldErrors.add(error);
					if (field.equals("cDT"))
						cDTFieldErrors.add(error);

				}
			}
			if (dateFieldErrors.size() > 0)
				date.showErrors(dateFieldErrors);
			if (regionFieldErrors.size() > 0)
				region.showErrors(regionFieldErrors);
			if (districtSanteFieldErrors.size() > 0)
				districtSante.showErrors(districtSanteFieldErrors);
			if (cDTFieldErrors.size() > 0)
				CDT.showErrors(cDTFieldErrors);
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
