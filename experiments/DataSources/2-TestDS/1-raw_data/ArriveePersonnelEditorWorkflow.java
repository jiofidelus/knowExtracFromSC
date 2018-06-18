package org.imogene.epicam.client.ui.workflow;

import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.imogene.epicam.client.event.list.ListArriveePersonnelEvent;
import org.imogene.epicam.client.event.view.ViewArriveePersonnelEvent;
import org.imogene.epicam.client.event.save.SaveArriveePersonnelEvent;
import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.editor.ArriveePersonnelEditor;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.shared.request.ArriveePersonnelRequest;
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
 * Worflow that manages the life of a ArriveePersonnelProxy in the UI
 * @author MEDES-IMPS
 */
public class ArriveePersonnelEditorWorkflow extends EditorWorkflowComposite {

	interface Driver
			extends
				RequestFactoryEditorDriver<ArriveePersonnelProxy, ArriveePersonnelEditor> {
	}

	private EpicamRequestFactory requestFactory;

	private ArriveePersonnelRequest request;
	public ArriveePersonnelProxy current;
	private Driver editorDriver;
	private ArriveePersonnelEditor editor;
	private String initField;
	private boolean showGlassPanel = false;

	/**
	 * Workflow constructor for the creation of a ArriveePersonnel instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 */
	public ArriveePersonnelEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer) {
		this(factory, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the creation of a ArriveePersonnel instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public ArriveePersonnelEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer, RelationPopupPanel parent, String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new ArriveePersonnelEditor(factory, true);
			this.initField = initField;
		} else
			editor = new ArriveePersonnelEditor(factory);

		isNew = true;
		setEditable(true);

		setTitle(NLS.constants().arriveePersonnel_create_title());
		createDriver();
		createNewArriveePersonnel();

		this.setContent(editor);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing ArriveePersonnel instance
	 * @param factory the application request factory
	 * @param entityId the id of the ArriveePersonnel instance to be visualized and edited	 
	 * @param titleContainer the Label that will display the workflow title	 
	 */
	public ArriveePersonnelEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer) {
		this(factory, entityId, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing ArriveePersonnel instance
	 * @param factory the application request factory
	 * @param entityId the id of the ArriveePersonnel instance to be visualized and edited	
	 * @param titleContainer the label	 	 
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public ArriveePersonnelEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer, RelationPopupPanel parent,
			String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new ArriveePersonnelEditor(factory, true);
			this.initField = initField;
		} else
			editor = new ArriveePersonnelEditor(factory);

		setModifiable(false);
		isNew = false;
		setEditable(false);

		createDriver();
		fetchArriveePersonnel(entityId);

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
	 * Create a new instance of ArriveePersonnel
	 */
	private void createNewArriveePersonnel() {

		request = requestFactory.arriveePersonnelRequest();

		/* create a new intance of ArriveePersonnel */
		ArriveePersonnelProxy newArriveePersonnel = request
				.create(ArriveePersonnelProxy.class);
		newArriveePersonnel.setId(ImogKeyGenerator.generateKeyId("ARR_PERS"));

		/* push the instance to the editor */
		current = newArriveePersonnel;
		editorDriver.edit(current, request);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);
		// Field districtSante depends on the value of field region
		editor.getDistrictSanteFilteredByRegion(null);
		// Field cDT depends on the value of field districtSante
		editor.getCDTFilteredByDistrictSante(null);
		editor.setEdited(true);
	}

	/**
	 * Get an existing instance of ArriveePersonnel
	 * @param entityId the id of the ArriveePersonnelProxy to be fetched
	 */
	private void fetchArriveePersonnel(String entityId) {

		ArriveePersonnelRequest request = requestFactory
				.arriveePersonnelRequest();

		/* get the ArriveePersonnel instance from database */
		Request<ArriveePersonnelProxy> fetchRequest = request
				.findById(entityId);
		fetchRequest.with("region");
		fetchRequest.with("region.nom");
		fetchRequest.with("districtSante");
		fetchRequest.with("districtSante.nom");
		fetchRequest.with("CDT");
		fetchRequest.with("personnel");

		fetchRequest.to(new Receiver<ArriveePersonnelProxy>() {
			@Override
			public void onSuccess(ArriveePersonnelProxy entity) {
				viewArriveePersonnel(entity);
			}
		}).fire();
	}

	/**
	 * Display the current instance of ArriveePersonnel in editor
	 * @param entity the ArriveePersonnelProxy to be displayed
	 */
	private void viewArriveePersonnel(ArriveePersonnelProxy entity) {

		/* display instance information */
		setTitle(NLS.constants().arriveePersonnel_name() + ": "
				+ EpicamRenderer.get().getDisplayValue(entity));
		setMetaData((ImogBeanProxy) entity);

		/* push the instance to the editor in view mode */
		request = requestFactory.arriveePersonnelRequest();
		current = request.edit(entity);

		editor.setEditedValue(current);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		editorDriver.edit(current, request);
		editor.setEdited(false);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);

		/* display edit button */
		if (AccessManager.canEditArriveePersonnel())
			setModifiable(true);

		showGlassPanel = false;
		EpicamEntryPoint.GP.hide();
	}

	/**
	 * Edit the current instance of ArriveePersonnel in editor
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
	 * Persist the current instance of ArriveePersonnel
	 */
	@Override
	protected void save() {

		editor.validateFields();

		editorDriver.flush();

		// Check for errors on the client side
		if (editorDriver.hasErrors()) {
			//Window.alert("ArriveePersonnel form not validated locally");
			return;
		}

		Request<Void> saveRequest = request.save(current, isNew);
		saveRequest.to(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				requestFactory.getEventBus().fireEvent(
						new SaveArriveePersonnelEvent(current, initField));
				closeForm();
			}

			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> errors) {
				//Window.alert("ArriveePersonnel form not validated on server");

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
				Window.alert("Error updating the ArriveePersonnel");
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
						new ViewArriveePersonnelEvent(current.getId(),
								closeEvent));
		}

	}

	@Override
	protected void returnToList() {
		requestFactory.getEventBus().fireEvent(new ListArriveePersonnelEvent());
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
