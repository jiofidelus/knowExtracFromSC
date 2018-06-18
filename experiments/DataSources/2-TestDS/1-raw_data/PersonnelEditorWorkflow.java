package org.imogene.epicam.client.ui.workflow;

import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.imogene.epicam.client.event.list.ListPersonnelEvent;
import org.imogene.epicam.client.event.view.ViewPersonnelEvent;
import org.imogene.epicam.client.event.save.SavePersonnelEvent;
import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.editor.PersonnelEditor;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.shared.request.PersonnelRequest;
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
 * Worflow that manages the life of a PersonnelProxy in the UI
 * @author MEDES-IMPS
 */
public class PersonnelEditorWorkflow extends EditorWorkflowComposite {

	interface Driver
			extends
				RequestFactoryEditorDriver<PersonnelProxy, PersonnelEditor> {
	}

	private EpicamRequestFactory requestFactory;

	private PersonnelRequest request;
	public PersonnelProxy current;
	private Driver editorDriver;
	private PersonnelEditor editor;
	private String initField;
	private boolean showGlassPanel = false;

	/**
	 * Workflow constructor for the creation of a Personnel instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 */
	public PersonnelEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer) {
		this(factory, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the creation of a Personnel instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public PersonnelEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer, RelationPopupPanel parent, String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new PersonnelEditor(factory, true);
			this.initField = initField;
		} else
			editor = new PersonnelEditor(factory);

		isNew = true;
		setEditable(true);

		setTitle(NLS.constants().personnel_create_title());
		createDriver();
		createNewPersonnel();

		this.setContent(editor);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing Personnel instance
	 * @param factory the application request factory
	 * @param entityId the id of the Personnel instance to be visualized and edited	 
	 * @param titleContainer the Label that will display the workflow title	 
	 */
	public PersonnelEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer) {
		this(factory, entityId, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing Personnel instance
	 * @param factory the application request factory
	 * @param entityId the id of the Personnel instance to be visualized and edited	
	 * @param titleContainer the label	 	 
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public PersonnelEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer, RelationPopupPanel parent,
			String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new PersonnelEditor(factory, true);
			this.initField = initField;
		} else
			editor = new PersonnelEditor(factory);

		setModifiable(false);
		isNew = false;
		setEditable(false);

		createDriver();
		fetchPersonnel(entityId);

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
	 * Create a new instance of Personnel
	 */
	private void createNewPersonnel() {

		request = requestFactory.personnelRequest();

		/* create a new intance of Personnel */
		PersonnelProxy newPersonnel = request.create(PersonnelProxy.class);
		newPersonnel.setId(ImogKeyGenerator.generateKeyId("PERS"));
		newPersonnel.setAEteForme(false);
		newPersonnel.setActif(true);

		/* push the instance to the editor */
		current = newPersonnel;
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
	 * Get an existing instance of Personnel
	 * @param entityId the id of the PersonnelProxy to be fetched
	 */
	private void fetchPersonnel(String entityId) {

		PersonnelRequest request = requestFactory.personnelRequest();

		/* get the Personnel instance from database */
		Request<PersonnelProxy> fetchRequest = request.findById(entityId);
		fetchRequest.with("qualification");
		fetchRequest.with("qualification.nom");
		fetchRequest.with("region");
		fetchRequest.with("region.nom");
		fetchRequest.with("districtSante");
		fetchRequest.with("districtSante.nom");
		fetchRequest.with("CDT");
		fetchRequest.with("profiles");
		fetchRequest.with("synchronizables");

		fetchRequest.to(new Receiver<PersonnelProxy>() {
			@Override
			public void onSuccess(PersonnelProxy entity) {
				viewPersonnel(entity);
			}
		}).fire();
	}

	/**
	 * Display the current instance of Personnel in editor
	 * @param entity the PersonnelProxy to be displayed
	 */
	private void viewPersonnel(PersonnelProxy entity) {

		/* display instance information */
		setTitle(NLS.constants().personnel_name() + ": "
				+ EpicamRenderer.get().getDisplayValue(entity));
		setMetaData((ImogBeanProxy) entity);

		/* push the instance to the editor in view mode */
		request = requestFactory.personnelRequest();
		current = request.edit(entity);

		editor.setEditedValue(current);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		editorDriver.edit(current, request);
		editor.setEdited(false);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);
		editor.updateIdLink(current.getId());
		editor.setCurrentLogin();

		/* display edit button */
		if (AccessManager.canEditPersonnel())
			setModifiable(true);

		showGlassPanel = false;
		EpicamEntryPoint.GP.hide();
	}

	/**
	 * Edit the current instance of Personnel in editor
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
	 * Persist the current instance of Personnel
	 */
	@Override
	protected void save() {

		editor.validateFields();
		boolean passwordValid = editor.validatePasword();
		editor.validateLoginWithPassword();

		editorDriver.flush();

		// Check for errors on the client side
		if (editorDriver.hasErrors()) {
			//Window.alert("Personnel form not validated locally");
			return;
		}

		Request<Void> saveRequest = null;
		if (passwordValid && editor.passwordChanged())
			saveRequest = request.save(current, isNew, true);
		else
			saveRequest = request.save(current, isNew, false);
		saveRequest.to(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				requestFactory.getEventBus().fireEvent(
						new SavePersonnelEvent(current, initField));
				closeForm();
			}

			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> errors) {
				//Window.alert("Personnel form not validated on server");

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
				Window.alert("Error updating the Personnel");
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
						new ViewPersonnelEvent(current.getId(), closeEvent));
		}

	}

	@Override
	protected void returnToList() {
		requestFactory.getEventBus().fireEvent(new ListPersonnelEvent());
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

}
