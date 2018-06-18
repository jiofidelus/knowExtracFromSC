package org.imogene.epicam.client.ui.workflow;

import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.imogene.epicam.client.event.list.ListCentreDiagTraitEvent;
import org.imogene.epicam.client.event.view.ViewCentreDiagTraitEvent;
import org.imogene.epicam.client.event.save.SaveCentreDiagTraitEvent;
import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.editor.CentreDiagTraitEditor;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.shared.request.CentreDiagTraitRequest;
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
 * Worflow that manages the life of a CentreDiagTraitProxy in the UI
 * @author MEDES-IMPS
 */
public class CentreDiagTraitEditorWorkflow extends EditorWorkflowComposite {

	interface Driver
			extends
				RequestFactoryEditorDriver<CentreDiagTraitProxy, CentreDiagTraitEditor> {
	}

	private EpicamRequestFactory requestFactory;

	private CentreDiagTraitRequest request;
	public CentreDiagTraitProxy current;
	private Driver editorDriver;
	private CentreDiagTraitEditor editor;
	private String initField;
	private boolean showGlassPanel = false;

	/**
	 * Workflow constructor for the creation of a CentreDiagTrait instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 */
	public CentreDiagTraitEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer) {
		this(factory, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the creation of a CentreDiagTrait instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public CentreDiagTraitEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer, RelationPopupPanel parent, String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new CentreDiagTraitEditor(factory, true);
			this.initField = initField;
		} else
			editor = new CentreDiagTraitEditor(factory);

		isNew = true;
		setEditable(true);

		setTitle(NLS.constants().centreDiagTrait_create_title());
		createDriver();
		createNewCentreDiagTrait();

		this.setContent(editor);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing CentreDiagTrait instance
	 * @param factory the application request factory
	 * @param entityId the id of the CentreDiagTrait instance to be visualized and edited	 
	 * @param titleContainer the Label that will display the workflow title	 
	 */
	public CentreDiagTraitEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer) {
		this(factory, entityId, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing CentreDiagTrait instance
	 * @param factory the application request factory
	 * @param entityId the id of the CentreDiagTrait instance to be visualized and edited	
	 * @param titleContainer the label	 	 
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public CentreDiagTraitEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer, RelationPopupPanel parent,
			String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new CentreDiagTraitEditor(factory, true);
			this.initField = initField;
		} else
			editor = new CentreDiagTraitEditor(factory);

		setModifiable(false);
		isNew = false;
		setEditable(false);

		createDriver();
		fetchCentreDiagTrait(entityId);

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
	 * Create a new instance of CentreDiagTrait
	 */
	private void createNewCentreDiagTrait() {

		request = requestFactory.centreDiagTraitRequest();

		/* create a new intance of CentreDiagTrait */
		CentreDiagTraitProxy newCentreDiagTrait = request
				.create(CentreDiagTraitProxy.class);
		newCentreDiagTrait.setId(ImogKeyGenerator.generateKeyId("CDT"));
		LocalizedTextProxy newDescription = request
				.create(LocalizedTextProxy.class);
		newCentreDiagTrait.setDescription(newDescription);
		GeoFieldProxy newCoordonnees = request.create(GeoFieldProxy.class);
		newCentreDiagTrait.setCoordonnees(newCoordonnees);

		/* push the instance to the editor */
		current = newCentreDiagTrait;
		editorDriver.edit(current, request);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);
		// Field districtSante depends on the value of field region
		editor.getDistrictSanteFilteredByRegion(null);
		editor.setEdited(true);
	}

	/**
	 * Get an existing instance of CentreDiagTrait
	 * @param entityId the id of the CentreDiagTraitProxy to be fetched
	 */
	private void fetchCentreDiagTrait(String entityId) {

		CentreDiagTraitRequest request = requestFactory
				.centreDiagTraitRequest();

		/* get the CentreDiagTrait instance from database */
		Request<CentreDiagTraitProxy> fetchRequest = request.findById(entityId);
		fetchRequest.with("region");
		fetchRequest.with("region.nom");
		fetchRequest.with("districtSante");
		fetchRequest.with("districtSante.nom");
		fetchRequest.with("description");
		fetchRequest.with("coordonnees");
		fetchRequest.with("lieuxDits");

		fetchRequest.to(new Receiver<CentreDiagTraitProxy>() {
			@Override
			public void onSuccess(CentreDiagTraitProxy entity) {
				viewCentreDiagTrait(entity);
			}
		}).fire();
	}

	/**
	 * Display the current instance of CentreDiagTrait in editor
	 * @param entity the CentreDiagTraitProxy to be displayed
	 */
	private void viewCentreDiagTrait(CentreDiagTraitProxy entity) {

		/* display instance information */
		setTitle(NLS.constants().centreDiagTrait_name() + ": "
				+ EpicamRenderer.get().getDisplayValue(entity));
		setMetaData((ImogBeanProxy) entity);

		/* push the instance to the editor in view mode */
		request = requestFactory.centreDiagTraitRequest();
		current = request.edit(entity);
		if (current.getDescription() == null) {
			LocalizedTextProxy newDescription = request
					.create(LocalizedTextProxy.class);
			current.setDescription(newDescription);
		}
		if (current.getCoordonnees() == null) {
			GeoFieldProxy newCoordonnees = request.create(GeoFieldProxy.class);
			current.setCoordonnees(newCoordonnees);
		}

		editor.setEditedValue(current);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		editorDriver.edit(current, request);
		editor.setEdited(false);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);

		/* display edit button */
		if (AccessManager.canEditCentreDiagTrait())
			setModifiable(true);

		showGlassPanel = false;
		EpicamEntryPoint.GP.hide();
	}

	/**
	 * Edit the current instance of CentreDiagTrait in editor
	 */
	@Override
	protected void edit() {

		/* set the instance in edit mode in the editor */
		editor.setEdited(true);

		/* update field widgets in editor */
		// Field districtSante depends on the value of field region
		editor.getDistrictSanteFilteredByRegion(current.getRegion());
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
	 * Persist the current instance of CentreDiagTrait
	 */
	@Override
	protected void save() {

		editor.validateFields();

		editorDriver.flush();

		// Check for errors on the client side
		if (editorDriver.hasErrors()) {
			//Window.alert("CentreDiagTrait form not validated locally");
			return;
		}

		Request<Void> saveRequest = request.save(current, isNew);
		saveRequest.to(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				requestFactory.getEventBus().fireEvent(
						new SaveCentreDiagTraitEvent(current, initField));
				closeForm();
			}

			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> errors) {
				//Window.alert("CentreDiagTrait form not validated on server");

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
				Window.alert("Error updating the CentreDiagTrait");
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
						new ViewCentreDiagTraitEvent(current.getId(),
								closeEvent));
		}

	}

	@Override
	protected void returnToList() {
		requestFactory.getEventBus().fireEvent(new ListCentreDiagTraitEvent());
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

}
