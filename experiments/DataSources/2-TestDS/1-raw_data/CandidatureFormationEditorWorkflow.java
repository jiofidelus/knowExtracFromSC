package org.imogene.epicam.client.ui.workflow;

import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.imogene.epicam.client.event.list.ListCandidatureFormationEvent;
import org.imogene.epicam.client.event.view.ViewCandidatureFormationEvent;
import org.imogene.epicam.client.event.save.SaveCandidatureFormationEvent;
import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.editor.CandidatureFormationEditor;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.shared.request.CandidatureFormationRequest;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.epicam.client.EpicamEntryPoint;
import org.imogene.web.client.event.GoHomeEvent;
import org.imogene.web.client.ui.field.error.ImogConstraintViolation;
import org.imogene.web.client.ui.workflow.EditorWorkflowComposite;
import org.imogene.web.client.util.ImogKeyGenerator;
import org.imogene.web.client.ui.panel.RelationPopupPanel;
import org.imogene.web.shared.proxy.GeoFieldProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.BaseProxy;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * Worflow that manages the life of a CandidatureFormationProxy in the UI
 * @author MEDES-IMPS
 */
public class CandidatureFormationEditorWorkflow extends EditorWorkflowComposite {

	interface Driver
			extends
				RequestFactoryEditorDriver<CandidatureFormationProxy, CandidatureFormationEditor> {
	}

	private EpicamRequestFactory requestFactory;

	private CandidatureFormationRequest request;
	public CandidatureFormationProxy current;
	private Driver editorDriver;
	private CandidatureFormationEditor editor;
	private String initField;
	private boolean showGlassPanel = false;

	/**
	 * Workflow constructor for the creation of a CandidatureFormation instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 */
	public CandidatureFormationEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer) {
		this(factory, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the creation of a CandidatureFormation instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public CandidatureFormationEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer, RelationPopupPanel parent, String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new CandidatureFormationEditor(factory, true);
			this.initField = initField;
		} else
			editor = new CandidatureFormationEditor(factory);

		isNew = true;
		setEditable(true);

		setTitle(NLS.constants().candidatureFormation_create_title());
		createDriver();
		createNewCandidatureFormation();

		this.setContent(editor);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing CandidatureFormation instance
	 * @param factory the application request factory
	 * @param entityId the id of the CandidatureFormation instance to be visualized and edited	 
	 * @param titleContainer the Label that will display the workflow title	 
	 */
	public CandidatureFormationEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer) {
		this(factory, entityId, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing CandidatureFormation instance
	 * @param factory the application request factory
	 * @param entityId the id of the CandidatureFormation instance to be visualized and edited	
	 * @param titleContainer the label	 	 
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public CandidatureFormationEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer, RelationPopupPanel parent,
			String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new CandidatureFormationEditor(factory, true);
			this.initField = initField;
		} else
			editor = new CandidatureFormationEditor(factory);

		setModifiable(false);
		isNew = false;
		setEditable(false);

		createDriver();
		fetchCandidatureFormation(entityId);

		this.setContent(editor);
		showGlassPanel = true;
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		if (showGlassPanel) {
			EpicamEntryPoint.GP.showAndAdapt(this);
		}
	}

	/**
	 * Create a new instance of CandidatureFormation
	 */
	private void createNewCandidatureFormation() {

		request = requestFactory.candidatureFormationRequest();

		/* create a new intance of CandidatureFormation */
		CandidatureFormationProxy newCandidatureFormation = request
				.create(CandidatureFormationProxy.class);
		newCandidatureFormation.setId(ImogKeyGenerator
				.generateKeyId("CAND_FORM"));

		/* push the instance to the editor */
		current = newCandidatureFormation;
		editorDriver.edit(current, request);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);
		// Field districtSante depends on the value of field region
		editor.getDistrictSanteFilteredByRegion(null);
		// Field cDT depends on the value of field districtSante
		editor.getCDTFilteredByDistrictSante(null);
		// Field personnel depends on the value of field cDT
		editor.getPersonnelFilteredByCDT(null);
		editor.setEdited(true);
	}

	/**
	 * Get an existing instance of CandidatureFormation
	 * @param entityId the id of the CandidatureFormationProxy to be fetched
	 */
	private void fetchCandidatureFormation(String entityId) {

		CandidatureFormationRequest request = requestFactory
				.candidatureFormationRequest();

		/* get the CandidatureFormation instance from database */
		Request<CandidatureFormationProxy> fetchRequest = request
				.findById(entityId);
		fetchRequest.with("formation");
		fetchRequest.with("formation.libelle");
		fetchRequest.with("region");
		fetchRequest.with("region.nom");
		fetchRequest.with("districtSante");
		fetchRequest.with("districtSante.nom");
		fetchRequest.with("CDT");
		fetchRequest.with("personnel");

		fetchRequest.to(new Receiver<CandidatureFormationProxy>() {
			@Override
			public void onSuccess(CandidatureFormationProxy entity) {
				viewCandidatureFormation(entity);
			}
		}).fire();
	}

	/**
	 * Display the current instance of CandidatureFormation in editor
	 * @param entity the CandidatureFormationProxy to be displayed
	 */
	private void viewCandidatureFormation(CandidatureFormationProxy entity) {

		/* display instance information */
		setTitle(NLS.constants().candidatureFormation_name() + ": "
				+ EpicamRenderer.get().getDisplayValue(entity));
		setMetaData((ImogBeanProxy) entity);

		/* push the instance to the editor in view mode */
		request = requestFactory.candidatureFormationRequest();
		current = request.edit(entity);

		editor.setEditedValue(current);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		editorDriver.edit(current, request);
		editor.setEdited(false);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);

		/* display edit button */
		if (AccessManager.canEditCandidatureFormation())
			setModifiable(true);

		showGlassPanel = false;
		EpicamEntryPoint.GP.hide();
	}

	/**
	 * Edit the current instance of CandidatureFormation in editor
	 */
	@Override
	protected void edit() {

		/* set the instance in edit mode in the editor */
		editor.setEdited(true);

		/* update field widgets in editor */
		// Field districtSante depends on the value of field region
		editor.getDistrictSanteFilteredByRegion(current.getRegion());
		// Field cDT depends on the value of field districtSante
		editor.getCDTFilteredByDistrictSante(current.getDistrictSante());
		// Field personnel depends on the value of field cDT
		editor.getPersonnelFilteredByCDT(current.getCDT());
	}

	/**
	 * Initialize the editor driver
	 */
	private void createDriver() {
		if (editorDriver == null) {
			editorDriver = GWT.create(Driver.class);
			editorDriver.initialize(requestFactory, editor);
		}
	}

	/**
	 * Persist the current instance of CandidatureFormation
	 */
	@Override
	protected void save() {

		editor.validateFields();

		editorDriver.flush();

		// Check for errors on the client side
		if (editorDriver.hasErrors()) {
			//Window.alert("CandidatureFormation form not validated locally");
			return;
		}

		Request<Void> saveRequest = request.save(current, isNew);
		saveRequest.to(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				requestFactory.getEventBus().fireEvent(
						new SaveCandidatureFormationEvent(current, initField));
				closeForm();
			}

			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> errors) {
				//Window.alert("CandidatureFormation form not validated on server");

				//TODO manage errors on client side when made available by GWT				
				if (errors != null && errors.size() > 0) {
					// convert ConstraintViolation to get localized messages
					EpicamRenderer renderer = EpicamRenderer.get();
					Set<ConstraintViolation<?>> imogErrors = new HashSet<ConstraintViolation<?>>();
					for (ConstraintViolation<?> error : errors) {
						ImogConstraintViolation violation = new ImogConstraintViolation();
						violation.setLeafBean((BaseProxy) error.getLeafBean());
						violation.setPropertyPath(error.getPropertyPath());
						violation.setRootBean((BaseProxy) error.getRootBean());
						violation.setMessage(renderer.getI18nErrorMessage(error
								.getMessage()));
						imogErrors.add(violation);
					}
					editorDriver.setConstraintViolations(imogErrors);
				}
			}

			@Override
			public void onFailure(ServerFailure error) {
				Window.alert("Error updating the CandidatureFormation");
				super.onFailure(error);
			}
		});

		request.fire();
	}

	@Override
	protected void cancel() {
		if (parent != null)
			parent.hide();
		else {
			if (isNew)
				requestFactory.getEventBus().fireEvent(closeEvent);
			else
				requestFactory.getEventBus().fireEvent(
						new ViewCandidatureFormationEvent(current.getId(),
								closeEvent));
		}

	}

	@Override
	protected void returnToList() {
		requestFactory.getEventBus().fireEvent(
				new ListCandidatureFormationEvent());
	}

	/**
	 * Setter to inject a Formation value
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setFormation(FormationProxy value, boolean isLocked) {
		editor.setFormation(value, isLocked);
	}
	/**
	 * Setter to inject a Region value
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setRegion(RegionProxy value, boolean isLocked) {
		editor.setRegion(value, isLocked);
	}
	/**
	 * Setter to inject a DistrictSante value
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setDistrictSante(DistrictSanteProxy value, boolean isLocked) {
		editor.setDistrictSante(value, isLocked);
	}
	/**
	 * Setter to inject a CentreDiagTrait value
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setCDT(CentreDiagTraitProxy value, boolean isLocked) {
		editor.setCDT(value, isLocked);
	}
	/**
	 * Setter to inject a Personnel value
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setPersonnel(PersonnelProxy value, boolean isLocked) {
		editor.setPersonnel(value, isLocked);
	}

}
