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
import org.imogene.epicam.shared.proxy.RavitaillementProxy;
import org.imogene.epicam.shared.request.RavitaillementRequest;

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

import org.imogene.epicam.client.ui.util.CommonFieldUtil;

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
import org.imogene.epicam.client.ui.workflow.panel.DetailRavitaillementFormPanel;
import org.imogene.epicam.client.event.save.SaveDetailRavitaillementEvent;
import org.imogene.epicam.client.dataprovider.DetailRavitaillementDataProvider;
import org.imogene.epicam.client.ui.filter.DetailRavitaillementFilterPanel;
import org.imogene.epicam.shared.proxy.DetailRavitaillementProxy;
import org.imogene.epicam.client.ui.editor.nested.RavitaillementDetailsListEditor;
import org.imogene.epicam.shared.request.RavitaillementRequest;

/**
 * Editor that provides the UI components that allow a RavitaillementProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class RavitaillementEditor extends Composite
		implements
			Editor<RavitaillementProxy>,
			HasEditorDelegate<RavitaillementProxy>,
			HasEditorErrors<RavitaillementProxy> {

	interface Binder extends UiBinder<Widget, RavitaillementEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<RavitaillementProxy> delegate;
	private CommonFieldUtil commonFieldUtil = CommonFieldUtil.get();

	private RavitaillementProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;

	/* InformationsDepart section widgets */
	@UiField
	@Ignore
	FieldGroupPanel informationsDepartSection;
	@UiField(provided = true)
	ImogSingleRelationBox<RegionProxy> region;
	private RegionDataProvider regionDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<DistrictSanteProxy> districtSante;
	private DistrictSanteDataProvider districtSanteDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<CentreDiagTraitProxy> CDTDepart;
	private CentreDiagTraitDataProvider cDTDepartDataProvider;
	@UiField
	ImogDateBox dateDepart;

	/* InformationArrivee section widgets */
	@UiField
	@Ignore
	FieldGroupPanel informationArriveeSection;
	@UiField(provided = true)
	ImogSingleRelationBox<RegionProxy> regionArrivee;
	private RegionDataProvider regionArriveeDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<DistrictSanteProxy> districtSanteArrivee;
	private DistrictSanteDataProvider districtSanteArriveeDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<CentreDiagTraitProxy> CDTArrivee;
	private CentreDiagTraitDataProvider cDTArriveeDataProvider;
	@UiField
	ImogDateBox dateArrivee;

	/* Detail section widgets */
	@UiField
	@Ignore
	FieldGroupPanel detailSection;
	@UiField(provided = true)
	RavitaillementDetailsListEditor details;
	private DetailRavitaillementDataProvider detailsDataProvider;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public RavitaillementEditor(EpicamRequestFactory factory,
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
	public RavitaillementEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* InformationsDepart section widgets */
		informationsDepartSection.setGroupTitle(NLS.constants()
				.ravitaillement_group_informationsDepart());
		region.setLabel(NLS.constants().ravitaillement_field_region());
		// the value of region affects the value of other fields
		region.notifyChanges(requestFactory.getEventBus());
		districtSante.setLabel(NLS.constants()
				.ravitaillement_field_districtSante());
		// the value of districtSante affects the value of other fields
		districtSante.notifyChanges(requestFactory.getEventBus());
		CDTDepart.setLabel(NLS.constants().ravitaillement_field_cDTDepart());
		dateDepart.setLabel(NLS.constants().ravitaillement_field_dateDepart());

		/* InformationArrivee section widgets */
		informationArriveeSection.setGroupTitle(NLS.constants()
				.ravitaillement_group_informationArrivee());
		regionArrivee.setLabel(NLS.constants()
				.ravitaillement_field_regionArrivee());
		// the value of regionArrivee affects the value of other fields
		regionArrivee.notifyChanges(requestFactory.getEventBus());
		districtSanteArrivee.setLabel(NLS.constants()
				.ravitaillement_field_districtSanteArrivee());
		// the value of districtSanteArrivee affects the value of other fields
		districtSanteArrivee.notifyChanges(requestFactory.getEventBus());
		CDTArrivee.setLabel(NLS.constants().ravitaillement_field_cDTArrivee());
		dateArrivee
				.setLabel(NLS.constants().ravitaillement_field_dateArrivee());

		/* Detail section widgets */
		detailSection.setGroupTitle(NLS.constants()
				.ravitaillement_group_detail());

		// the value of CDT affects the value of other fields
		CDTDepart.notifyChanges(requestFactory.getEventBus());
		CDTArrivee.notifyChanges(requestFactory.getEventBus());

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

		/* field  CDTDepart */
		cDTDepartDataProvider = new CentreDiagTraitDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			CDTDepart = new ImogSingleRelationBox<CentreDiagTraitProxy>(
					cDTDepartDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateCentreDiagTrait()
					&& AccessManager.canEditCentreDiagTrait())
				CDTDepart = new ImogSingleRelationBox<CentreDiagTraitProxy>(
						cDTDepartDataProvider, EpicamRenderer.get());
			else
				CDTDepart = new ImogSingleRelationBox<CentreDiagTraitProxy>(
						false, cDTDepartDataProvider, EpicamRenderer.get());
		}

		/* field  regionArrivee */
		regionArriveeDataProvider = new RegionDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			regionArrivee = new ImogSingleRelationBox<RegionProxy>(
					regionArriveeDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateRegion()
					&& AccessManager.canEditRegion())
				regionArrivee = new ImogSingleRelationBox<RegionProxy>(
						regionArriveeDataProvider, EpicamRenderer.get());
			else
				regionArrivee = new ImogSingleRelationBox<RegionProxy>(false,
						regionArriveeDataProvider, EpicamRenderer.get());
		}

		/* field  districtSanteArrivee */
		districtSanteArriveeDataProvider = new DistrictSanteDataProvider(
				requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			districtSanteArrivee = new ImogSingleRelationBox<DistrictSanteProxy>(
					districtSanteArriveeDataProvider, EpicamRenderer.get(),
					true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateDistrictSante()
					&& AccessManager.canEditDistrictSante())
				districtSanteArrivee = new ImogSingleRelationBox<DistrictSanteProxy>(
						districtSanteArriveeDataProvider, EpicamRenderer.get());
			else
				districtSanteArrivee = new ImogSingleRelationBox<DistrictSanteProxy>(
						false, districtSanteArriveeDataProvider,
						EpicamRenderer.get());
		}

		/* field  CDTArrivee */
		cDTArriveeDataProvider = new CentreDiagTraitDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			CDTArrivee = new ImogSingleRelationBox<CentreDiagTraitProxy>(
					cDTArriveeDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreateCentreDiagTrait()
					&& AccessManager.canEditCentreDiagTrait())
				CDTArrivee = new ImogSingleRelationBox<CentreDiagTraitProxy>(
						cDTArriveeDataProvider, EpicamRenderer.get());
			else
				CDTArrivee = new ImogSingleRelationBox<CentreDiagTraitProxy>(
						false, cDTArriveeDataProvider, EpicamRenderer.get());
		}

		/* field  details */
		details = new RavitaillementDetailsListEditor(requestFactory);

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
		region.setEdited(isEdited);
		districtSante.setEdited(isEdited);
		CDTDepart.setEdited(isEdited);
		dateDepart.setEdited(isEdited);

		/* InformationArrivee section widgets */
		regionArrivee.setEdited(isEdited);
		districtSanteArrivee.setEdited(isEdited);
		CDTArrivee.setEdited(isEdited);
		dateArrivee.setEdited(isEdited);

		/* Detail section widgets */
		details.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* InformationsDepart section widgets visibility */
		if (!AccessManager.canReadRavitaillementInformationsDepart())
			informationsDepartSection.setVisible(false);

		/* InformationArrivee section widgets visibility */
		if (!AccessManager.canReadRavitaillementInformationArrivee())
			informationArriveeSection.setVisible(false);

		/* Detail section widgets visibility */
		if (!AccessManager.canReadRavitaillementDetail())
			detailSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* InformationsDepart section widgets visibility */
		if (!AccessManager.canEditRavitaillementInformationsDepart())
			informationsDepartSection.setVisible(false);

		/* InformationArrivee section widgets visibility */
		if (!AccessManager.canEditRavitaillementInformationArrivee())
			informationArriveeSection.setVisible(false);

		/* Detail section widgets visibility */
		if (!AccessManager.canEditRavitaillementDetail())
			detailSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(RavitaillementRequest ctx) {
		details.setRequestContextForListEditors(ctx);
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
						/* CDTDepart list content depends on the value of field DistrictSante */
						if (field.equals(districtSante)) {
							clearCDTDepartWidget();
							getCDTDepartFilteredByDistrictSante(districtSante
									.getValue());

							if (districtSante.getValue() != null) {
								RegionProxy proxy = districtSante.getValue()
										.getRegion();
								region.setValue(proxy);
							}

						}
						/* DistrictSanteArrivee list content depends on the value of field RegionArrivee */
						if (field.equals(regionArrivee)) {
							clearDistrictSanteArriveeWidget();
							getDistrictSanteArriveeFilteredByRegionArrivee(regionArrivee
									.getValue());

							commonFieldUtil.setRegion(regionArrivee.getValue());

						}
						/* CDTArrivee list content depends on the value of field DistrictSanteArrivee */
						if (field.equals(districtSanteArrivee)) {
							clearCDTArriveeWidget();
							getCDTArriveeFilteredByDistrictSanteArrivee(districtSanteArrivee
									.getValue());

							commonFieldUtil.setDistrict(districtSanteArrivee
									.getValue());

							if (districtSanteArrivee.getValue() != null) {
								RegionProxy proxy = districtSanteArrivee
										.getValue().getRegion();
								regionArrivee.setValue(proxy);
								commonFieldUtil.setRegion(proxy);
							}

						}

						if (field.equals(CDTDepart)) {
							updateCDTDepartInNestedForms(CDTDepart.getValue());
						}
						if (field.equals(CDTArrivee)) {
							updateCDTArriveeInNestedForms(CDTArrivee.getValue());
						}
						if (field.equals(CDTDepart)) {
							CentreDiagTraitProxy cdtValue = CDTDepart
									.getValue();
							if (cdtValue != null) {
								RegionProxy regionValue = cdtValue.getRegion();
								region.setValue(regionValue);
								DistrictSanteProxy districtValue = cdtValue
										.getDistrictSante();
								districtSante.setValue(districtValue);
							}
						}

						if (field.equals(CDTArrivee)) {
							CentreDiagTraitProxy cdtValue = CDTArrivee
									.getValue();
							commonFieldUtil.setCdt(cdtValue);
							if (cdtValue != null) {
								RegionProxy regionValue = cdtValue.getRegion();
								regionArrivee.setValue(regionValue);
								commonFieldUtil.setRegion(regionValue);
								DistrictSanteProxy districtValue = cdtValue
										.getDistrictSante();
								districtSanteArrivee.setValue(districtValue);
								commonFieldUtil.setDistrict(districtValue);
							}
						}
					}
				}));
	}

	/**
	 * Computes the field visibility
	 */
	public void computeVisibility(ImogField<?> source, boolean allValidation) {
		details.computeVisibility(source, allValidation);

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
		getCDTDepartFilteredByRegion(pRegion);
	}

	/**
	 * Filters the content of the RelationField CDTDepart by the value of 
	 * the RelationField DistrictSante
	 * @param districtSante the value of 
	 * the RelationField DistrictSante 
	 */
	public void getCDTDepartFilteredByDistrictSante(
			DistrictSanteProxy pDistrictSante) {

		if (pDistrictSante != null) {
			if (!hideButtons)
				CDTDepart.hideButtons(false);
			cDTDepartDataProvider.setFilterCriteria(pDistrictSante.getId(),
					"districtSante.id");
		}
	}

	/**
	 * Filters the content of the RelationField DistrictSanteArrivee by the value of 
	 * the RelationField RegionArrivee
	 * @param regionArrivee the value of 
	 * the RelationField RegionArrivee 
	 */
	public void getDistrictSanteArriveeFilteredByRegionArrivee(
			RegionProxy pRegionArrivee) {

		if (pRegionArrivee != null) {
			if (!hideButtons)
				districtSanteArrivee.hideButtons(false);
			districtSanteArriveeDataProvider.setFilterCriteria(
					pRegionArrivee.getId(), "region.id");
		} else {
			districtSanteArriveeDataProvider.setIsFiltered(false);
		}
		getCDTArriveeFilteredByRegion(pRegionArrivee);
	}

	/**
	 * Filters the content of the RelationField CDTArrivee by the value of 
	 * the RelationField DistrictSanteArrivee
	 * @param districtSanteArrivee the value of 
	 * the RelationField DistrictSanteArrivee 
	 */
	public void getCDTArriveeFilteredByDistrictSanteArrivee(
			DistrictSanteProxy pDistrictSanteArrivee) {

		if (pDistrictSanteArrivee != null) {
			if (!hideButtons)
				CDTArrivee.hideButtons(false);
			cDTArriveeDataProvider.setFilterCriteria(
					pDistrictSanteArrivee.getId(), "districtSante.id");
		}
	}

	public void getCDTDepartFilteredByRegion(RegionProxy pRegion) {

		if (pRegion != null) {
			if (!hideButtons)
				CDTDepart.hideButtons(false);
			cDTDepartDataProvider.setFilterCriteria(pRegion.getId(),
					"region.id");
		} else {
			cDTDepartDataProvider.setIsFiltered(false);
		}

	}

	public void getCDTArriveeFilteredByRegion(RegionProxy pRegion) {

		if (pRegion != null) {
			if (!hideButtons)
				CDTArrivee.hideButtons(false);
			cDTArriveeDataProvider.setFilterCriteria(pRegion.getId(),
					"region.id");
		} else {
			cDTArriveeDataProvider.setIsFiltered(false);
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

		// Field CDTDepart depends on the value of field districtSante
		clearCDTDepartWidget();
		getCDTDepartFilteredByDistrictSante(value);
	}

	/** Widget update for field districtSante */
	private void clearDistrictSanteWidget() {
		districtSante.clear();
		clearCDTDepartWidget();

	}

	/**
	 * Setter to inject a CentreDiagTrait value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setCDTDepart(CentreDiagTraitProxy value, boolean isLocked) {
		CDTDepart.setLocked(isLocked);
		CDTDepart.setValue(value);

	}

	/** Widget update for field CDTDepart */
	private void clearCDTDepartWidget() {
		CDTDepart.clear();

		updateCDTDepartInNestedForms(null);
	}

	/**
	 * Setter to inject a Region value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setRegionArrivee(RegionProxy value, boolean isLocked) {
		regionArrivee.setLocked(isLocked);
		regionArrivee.setValue(value);

		// Field DistrictSanteArrivee depends on the value of field regionArrivee
		clearDistrictSanteArriveeWidget();
		getDistrictSanteArriveeFilteredByRegionArrivee(value);
	}

	/** Widget update for field regionArrivee */
	private void clearRegionArriveeWidget() {
		regionArrivee.clear();
	}

	/**
	 * Setter to inject a DistrictSante value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setDistrictSanteArrivee(DistrictSanteProxy value,
			boolean isLocked) {
		districtSanteArrivee.setLocked(isLocked);
		districtSanteArrivee.setValue(value);

		// Field CDTArrivee depends on the value of field districtSanteArrivee
		clearCDTArriveeWidget();
		getCDTArriveeFilteredByDistrictSanteArrivee(value);
	}

	/** Widget update for field districtSanteArrivee */
	private void clearDistrictSanteArriveeWidget() {
		districtSanteArrivee.clear();
		clearCDTArriveeWidget();

		commonFieldUtil.setDistrict(null);
	}

	/**
	 * Setter to inject a CentreDiagTrait value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setCDTArrivee(CentreDiagTraitProxy value, boolean isLocked) {
		CDTArrivee.setLocked(isLocked);
		CDTArrivee.setValue(value);

	}

	/** Widget update for field CDTArrivee */
	private void clearCDTArriveeWidget() {
		CDTArrivee.clear();

		updateCDTArriveeInNestedForms(null);
		commonFieldUtil.setCdt(null);
	}

	private void setCDTDepartInNestedForms(CentreDiagTraitProxy value) {
		details.setCDTSortant(value);
	}

	private void updateCDTDepartInNestedForms(CentreDiagTraitProxy value) {
		details.updateCDTSortant(value);
	}

	private void setCDTArriveeInNestedForms(CentreDiagTraitProxy value) {
		details.setCDTEntrant(value);
	}

	private void updateCDTArriveeInNestedForms(CentreDiagTraitProxy value) {
		details.updateCDTEntrant(value);
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

		/* 'Information' button for field CDTDepart */
		CDTDepart.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (CDTDepart.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					CentreDiagTraitFormPanel form = new CentreDiagTraitFormPanel(
							requestFactory, CDTDepart.getValue().getId(),
							relationPopup, "CDTDepart");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field CDTDepart */
		CDTDepart.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				CentreDiagTraitFormPanel form = new CentreDiagTraitFormPanel(
						requestFactory, null, relationPopup, "CDTDepart");
				/* common fields */
				if (districtSante.getValue() != null)
					form.setDistrictSante(districtSante.getValue(), true);
				if (region.getValue() != null)
					form.setRegion(region.getValue(), true);

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a CentreDiagTrait is created or updated from the relation field CDTDepart */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveCentreDiagTraitEvent.TYPE,
				new SaveCentreDiagTraitEvent.Handler() {
					@Override
					public void saveCentreDiagTrait(CentreDiagTraitProxy value) {
						CDTDepart.setValue(value);
					}
					@Override
					public void saveCentreDiagTrait(CentreDiagTraitProxy value,
							String initField) {
						if (initField.equals("CDTDepart"))
							CDTDepart.setValue(value, true);
					}
				}));

		/* 'Information' button for field RegionArrivee */
		regionArrivee.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (regionArrivee.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					RegionFormPanel form = new RegionFormPanel(requestFactory,
							regionArrivee.getValue().getId(), relationPopup,
							"regionArrivee");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field RegionArrivee */
		regionArrivee.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				RegionFormPanel form = new RegionFormPanel(requestFactory,
						null, relationPopup, "regionArrivee");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a Region is created or updated from the relation field RegionArrivee */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveRegionEvent.TYPE, new SaveRegionEvent.Handler() {
					@Override
					public void saveRegion(RegionProxy value) {
						regionArrivee.setValue(value);
					}
					@Override
					public void saveRegion(RegionProxy value, String initField) {
						if (initField.equals("regionArrivee"))
							regionArrivee.setValue(value, true);
					}
				}));

		/* 'Information' button for field DistrictSanteArrivee */
		districtSanteArrivee.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (districtSanteArrivee.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					DistrictSanteFormPanel form = new DistrictSanteFormPanel(
							requestFactory, districtSanteArrivee.getValue()
									.getId(), relationPopup,
							"districtSanteArrivee");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field DistrictSanteArrivee */
		districtSanteArrivee.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				DistrictSanteFormPanel form = new DistrictSanteFormPanel(
						requestFactory, null, relationPopup,
						"districtSanteArrivee");
				/* common fields */
				if (regionArrivee.getValue() != null)
					form.setRegion(regionArrivee.getValue(), true);

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a DistrictSante is created or updated from the relation field DistrictSanteArrivee */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveDistrictSanteEvent.TYPE,
				new SaveDistrictSanteEvent.Handler() {
					@Override
					public void saveDistrictSante(DistrictSanteProxy value) {
						districtSanteArrivee.setValue(value);
					}
					@Override
					public void saveDistrictSante(DistrictSanteProxy value,
							String initField) {
						if (initField.equals("districtSanteArrivee"))
							districtSanteArrivee.setValue(value, true);
					}
				}));

		/* 'Information' button for field CDTArrivee */
		CDTArrivee.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (CDTArrivee.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					CentreDiagTraitFormPanel form = new CentreDiagTraitFormPanel(
							requestFactory, CDTArrivee.getValue().getId(),
							relationPopup, "CDTArrivee");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field CDTArrivee */
		CDTArrivee.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				CentreDiagTraitFormPanel form = new CentreDiagTraitFormPanel(
						requestFactory, null, relationPopup, "CDTArrivee");
				/* common fields */
				if (districtSanteArrivee.getValue() != null)
					form.setDistrictSante(districtSanteArrivee.getValue(), true);
				if (regionArrivee.getValue() != null)
					form.setRegion(regionArrivee.getValue(), true);

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a CentreDiagTrait is created or updated from the relation field CDTArrivee */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveCentreDiagTraitEvent.TYPE,
				new SaveCentreDiagTraitEvent.Handler() {
					@Override
					public void saveCentreDiagTrait(CentreDiagTraitProxy value) {
						CDTArrivee.setValue(value);
					}
					@Override
					public void saveCentreDiagTrait(CentreDiagTraitProxy value,
							String initField) {
						if (initField.equals("CDTArrivee"))
							CDTArrivee.setValue(value, true);
					}
				}));

	}

	/**
	 * Gets the RavitaillementProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public RavitaillementProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the RavitaillementProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(RavitaillementProxy editedValue) {
		this.editedValue = editedValue;

		if (editedValue != null) {
			setCDTDepartInNestedForms(editedValue.getCDTDepart());
			setCDTArriveeInNestedForms(editedValue.getCDTArrivee());
			// sets value for common fields when creating a new lot in child editors
			commonFieldUtil.setRegion(editedValue.getRegionArrivee());
			commonFieldUtil.setDistrict(editedValue.getDistrictSanteArrivee());
			commonFieldUtil.setCdt(editedValue.getCDTArrivee());
		}
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
		// cDTDepart is a required field
		if (CDTDepart.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"cDTDepart");
		// dateDepart is a required field
		if (dateDepart.getValueWithoutParseException() == null
				&& dateDepart.isValid())
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"dateDepart");
		// regionArrivee is a required field
		if (regionArrivee.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"regionArrivee");
		// districtSanteArrivee is a required field
		if (districtSanteArrivee.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"districtSanteArrivee");
		// cDTArrivee is a required field
		if (CDTArrivee.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"cDTArrivee");
		// details nested form shall be validated
		details.validateFields();
	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* InformationsDepart field group */
		region.setLabelWidth(width);
		districtSante.setLabelWidth(width);
		CDTDepart.setLabelWidth(width);
		dateDepart.setLabelWidth(width);

		/* InformationArrivee field group */
		regionArrivee.setLabelWidth(width);
		districtSanteArrivee.setLabelWidth(width);
		CDTArrivee.setLabelWidth(width);
		dateArrivee.setLabelWidth(width);

		/* Detail field group */

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* InformationsDepart field group */
		region.setBoxWidth(width);
		districtSante.setBoxWidth(width);
		CDTDepart.setBoxWidth(width);

		/* InformationArrivee field group */
		regionArrivee.setBoxWidth(width);
		districtSanteArrivee.setBoxWidth(width);
		CDTArrivee.setBoxWidth(width);

		/* Detail field group */

	}

	@Override
	public void setDelegate(EditorDelegate<RavitaillementProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> regionFieldErrors = new ArrayList<EditorError>();
			List<EditorError> districtSanteFieldErrors = new ArrayList<EditorError>();
			List<EditorError> cDTDepartFieldErrors = new ArrayList<EditorError>();
			List<EditorError> dateDepartFieldErrors = new ArrayList<EditorError>();
			List<EditorError> regionArriveeFieldErrors = new ArrayList<EditorError>();
			List<EditorError> districtSanteArriveeFieldErrors = new ArrayList<EditorError>();
			List<EditorError> cDTArriveeFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("region"))
						regionFieldErrors.add(error);
					if (field.equals("districtSante"))
						districtSanteFieldErrors.add(error);
					if (field.equals("cDTDepart"))
						cDTDepartFieldErrors.add(error);
					if (field.equals("dateDepart"))
						dateDepartFieldErrors.add(error);
					if (field.equals("regionArrivee"))
						regionArriveeFieldErrors.add(error);
					if (field.equals("districtSanteArrivee"))
						districtSanteArriveeFieldErrors.add(error);
					if (field.equals("cDTArrivee"))
						cDTArriveeFieldErrors.add(error);

				}
			}
			if (regionFieldErrors.size() > 0)
				region.showErrors(regionFieldErrors);
			if (districtSanteFieldErrors.size() > 0)
				districtSante.showErrors(districtSanteFieldErrors);
			if (cDTDepartFieldErrors.size() > 0)
				CDTDepart.showErrors(cDTDepartFieldErrors);
			if (dateDepartFieldErrors.size() > 0)
				dateDepart.showErrors(dateDepartFieldErrors);
			if (regionArriveeFieldErrors.size() > 0)
				regionArrivee.showErrors(regionArriveeFieldErrors);
			if (districtSanteArriveeFieldErrors.size() > 0)
				districtSanteArrivee
						.showErrors(districtSanteArriveeFieldErrors);
			if (cDTArriveeFieldErrors.size() > 0)
				CDTArrivee.showErrors(cDTArriveeFieldErrors);
		}
	}

	@Override
	protected void onUnload() {
		for (HandlerRegistration r : registrations)
			r.removeHandler();
		registrations.clear();
		// reset common fields values
		commonFieldUtil.setRegion(null);
		commonFieldUtil.setDistrict(null);
		commonFieldUtil.setCdt(null);
		super.onUnload();
	}

	@Override
	protected void onLoad() {
		setRelationHandlers();
		setFieldValueChangeHandler();
		super.onLoad();
	}

}
