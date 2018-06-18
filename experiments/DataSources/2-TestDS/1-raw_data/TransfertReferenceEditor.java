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
import org.imogene.epicam.shared.proxy.TransfertReferenceProxy;
import org.imogene.epicam.shared.request.TransfertReferenceRequest;

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
import org.imogene.epicam.client.ui.workflow.panel.PatientFormPanel;
import org.imogene.epicam.client.event.save.SavePatientEvent;
import org.imogene.epicam.client.dataprovider.PatientDataProvider;
import org.imogene.epicam.client.ui.filter.PatientFilterPanel;
import org.imogene.epicam.shared.proxy.PatientProxy;
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
 * Editor that provides the UI components that allow a TransfertReferenceProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class TransfertReferenceEditor extends Composite
		implements
			Editor<TransfertReferenceProxy>,
			HasEditorDelegate<TransfertReferenceProxy>,
			HasEditorErrors<TransfertReferenceProxy> {

	interface Binder extends UiBinder<Widget, TransfertReferenceEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<TransfertReferenceProxy> delegate;

	private TransfertReferenceProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;

	/* InformationsDepart section widgets */
	@UiField
	@Ignore
	FieldGroupPanel informationsDepartSection;
	@UiField
	ImogSingleEnumBox nature;
	@UiField(provided = true)
	ImogSingleRelationBox<RegionProxy> region;
	private RegionDataProvider regionDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<DistrictSanteProxy> districtSante;
	private DistrictSanteDataProvider districtSanteDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<CentreDiagTraitProxy> CDTDepart;
	private CentreDiagTraitDataProvider cDTDepartDataProvider;
	@UiField(provided = true)
	ImogSingleRelationBox<PatientProxy> patient;
	private PatientDataProvider patientDataProvider;
	@UiField
	ImogDateBox dateDepart;
	@UiField
	ImogTextAreaBox motifDepart;

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

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public TransfertReferenceEditor(EpicamRequestFactory factory,
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
	public TransfertReferenceEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* InformationsDepart section widgets */
		informationsDepartSection.setGroupTitle(NLS.constants()
				.transfertReference_group_informationsDepart());
		nature.setLabel(NLS.constants().transfertReference_field_nature());
		nature.addItem(EpicamEnumConstants.TRANSFERTREFERENCE_NATURE_TRANSFERT,
				NLS.constants().transfertReference_nature_transfert_option());
		nature.addItem(EpicamEnumConstants.TRANSFERTREFERENCE_NATURE_REFERENCE,
				NLS.constants().transfertReference_nature_reference_option());
		region.setLabel(NLS.constants().transfertReference_field_region());
		// the value of region affects the value of other fields
		region.notifyChanges(requestFactory.getEventBus());
		districtSante.setLabel(NLS.constants()
				.transfertReference_field_districtSante());
		// the value of districtSante affects the value of other fields
		districtSante.notifyChanges(requestFactory.getEventBus());
		CDTDepart
				.setLabel(NLS.constants().transfertReference_field_cDTDepart());
		patient.setLabel(NLS.constants().transfertReference_field_patient());
		dateDepart.setLabel(NLS.constants()
				.transfertReference_field_dateDepart());
		motifDepart.setLabel(NLS.constants()
				.transfertReference_field_motifDepart());

		/* InformationArrivee section widgets */
		informationArriveeSection.setGroupTitle(NLS.constants()
				.transfertReference_group_informationArrivee());
		regionArrivee.setLabel(NLS.constants()
				.transfertReference_field_regionArrivee());
		// the value of regionArrivee affects the value of other fields
		regionArrivee.notifyChanges(requestFactory.getEventBus());
		districtSanteArrivee.setLabel(NLS.constants()
				.transfertReference_field_districtSanteArrivee());
		// the value of districtSanteArrivee affects the value of other fields
		districtSanteArrivee.notifyChanges(requestFactory.getEventBus());
		CDTArrivee.setLabel(NLS.constants()
				.transfertReference_field_cDTArrivee());
		dateArrivee.setLabel(NLS.constants()
				.transfertReference_field_dateArrivee());

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
		nature.setEdited(isEdited);
		region.setEdited(isEdited);
		districtSante.setEdited(isEdited);
		CDTDepart.setEdited(isEdited);
		patient.setEdited(isEdited);
		dateDepart.setEdited(isEdited);
		motifDepart.setEdited(isEdited);

		/* InformationArrivee section widgets */
		regionArrivee.setEdited(isEdited);
		districtSanteArrivee.setEdited(isEdited);
		CDTArrivee.setEdited(isEdited);
		dateArrivee.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* InformationsDepart section widgets visibility */
		if (!AccessManager.canReadTransfertReferenceInformationsDepart())
			informationsDepartSection.setVisible(false);

		/* InformationArrivee section widgets visibility */
		if (!AccessManager.canReadTransfertReferenceInformationArrivee())
			informationArriveeSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* InformationsDepart section widgets visibility */
		if (!AccessManager.canEditTransfertReferenceInformationsDepart())
			informationsDepartSection.setVisible(false);

		/* InformationArrivee section widgets visibility */
		if (!AccessManager.canEditTransfertReferenceInformationArrivee())
			informationArriveeSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(TransfertReferenceRequest ctx) {
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

						}
						/* CDTArrivee list content depends on the value of field DistrictSanteArrivee */
						if (field.equals(districtSanteArrivee)) {
							clearCDTArriveeWidget();
							getCDTArriveeFilteredByDistrictSanteArrivee(districtSanteArrivee
									.getValue());

							if (districtSanteArrivee.getValue() != null) {
								RegionProxy proxy = districtSanteArrivee
										.getValue().getRegion();
								regionArrivee.setValue(proxy);
							}

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

								clearPatientWidget();
								getPatientFilteredByCDTDepart(cdtValue);
								patient.setEdited(true);
							} else {
								patient.setEdited(false);
							}
						}

						if (field.equals(CDTArrivee)) {
							CentreDiagTraitProxy cdtValue = CDTArrivee
									.getValue();
							if (cdtValue != null) {
								RegionProxy regionValue = cdtValue.getRegion();
								regionArrivee.setValue(regionValue);
								DistrictSanteProxy districtValue = cdtValue
										.getDistrictSante();
								districtSanteArrivee.setValue(districtValue);
							}
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

	public void getPatientFilteredByCDTDepart(CentreDiagTraitProxy pCDTDepart) {

		if (pCDTDepart != null) {
			if (!hideButtons)
				patient.hideButtons(false);
			patientDataProvider.filterByCdt(pCDTDepart.getId());
		} else {
			patientDataProvider.setIsFiltered(false);
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

		clearPatientWidget();
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
				if (regionArrivee.getValue() != null)
					form.setRegion(regionArrivee.getValue(), true);
				if (districtSanteArrivee.getValue() != null)
					form.setDistrictSante(districtSanteArrivee.getValue(), true);

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
	 * Gets the TransfertReferenceProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public TransfertReferenceProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the TransfertReferenceProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(TransfertReferenceProxy editedValue) {
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

		// nature is a required field
		if (nature.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"nature");
		// region is a required field
		if (region.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"region");
		// patient is a required field
		if (patient.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"patient");
		// dateDepart is a required field
		if (dateDepart.getValueWithoutParseException() == null
				&& dateDepart.isValid())
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"dateDepart");
		// regionArrivee is a required field
		if (regionArrivee.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"regionArrivee");
	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* InformationsDepart field group */
		nature.setLabelWidth(width);
		region.setLabelWidth(width);
		districtSante.setLabelWidth(width);
		CDTDepart.setLabelWidth(width);
		patient.setLabelWidth(width);
		dateDepart.setLabelWidth(width);
		motifDepart.setLabelWidth(width);

		/* InformationArrivee field group */
		regionArrivee.setLabelWidth(width);
		districtSanteArrivee.setLabelWidth(width);
		CDTArrivee.setLabelWidth(width);
		dateArrivee.setLabelWidth(width);

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* InformationsDepart field group */
		nature.setBoxWidth(width);
		region.setBoxWidth(width);
		districtSante.setBoxWidth(width);
		CDTDepart.setBoxWidth(width);
		patient.setBoxWidth(width);
		motifDepart.setBoxWidth(width);

		/* InformationArrivee field group */
		regionArrivee.setBoxWidth(width);
		districtSanteArrivee.setBoxWidth(width);
		CDTArrivee.setBoxWidth(width);

	}

	@Override
	public void setDelegate(EditorDelegate<TransfertReferenceProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> natureFieldErrors = new ArrayList<EditorError>();
			List<EditorError> regionFieldErrors = new ArrayList<EditorError>();
			List<EditorError> patientFieldErrors = new ArrayList<EditorError>();
			List<EditorError> dateDepartFieldErrors = new ArrayList<EditorError>();
			List<EditorError> regionArriveeFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("nature"))
						natureFieldErrors.add(error);
					if (field.equals("region"))
						regionFieldErrors.add(error);
					if (field.equals("patient"))
						patientFieldErrors.add(error);
					if (field.equals("dateDepart"))
						dateDepartFieldErrors.add(error);
					if (field.equals("regionArrivee"))
						regionArriveeFieldErrors.add(error);

				}
			}
			if (natureFieldErrors.size() > 0)
				nature.showErrors(natureFieldErrors);
			if (regionFieldErrors.size() > 0)
				region.showErrors(regionFieldErrors);
			if (patientFieldErrors.size() > 0)
				patient.showErrors(patientFieldErrors);
			if (dateDepartFieldErrors.size() > 0)
				dateDepart.showErrors(dateDepartFieldErrors);
			if (regionArriveeFieldErrors.size() > 0)
				regionArrivee.showErrors(regionArriveeFieldErrors);
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
