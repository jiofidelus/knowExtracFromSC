package org.imogene.epicam.client.ui.workflow;

import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.imogene.epicam.client.event.list.ListDetailRavitaillementEvent;
import org.imogene.epicam.client.event.view.ViewDetailRavitaillementEvent;
import org.imogene.epicam.client.event.save.SaveDetailRavitaillementEvent;
import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.editor.DetailRavitaillementEditor;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.shared.request.DetailRavitaillementRequest;
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
 * Worflow that manages the life of a DetailRavitaillementProxy in the UI
 * @author MEDES-IMPS
 */
public class DetailRavitaillementEditorWorkflow extends EditorWorkflowComposite {

	interface Driver
			extends
				RequestFactoryEditorDriver<DetailRavitaillementProxy, DetailRavitaillementEditor> {
	}

	private EpicamRequestFactory requestFactory;

	private DetailRavitaillementRequest request;
	public DetailRavitaillementProxy current;
	private Driver editorDriver;
	private DetailRavitaillementEditor editor;
	private String initField;
	private boolean showGlassPanel = false;

	/**
	 * Workflow constructor for the creation of a DetailRavitaillement instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 */
	public DetailRavitaillementEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer) {
		this(factory, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the creation of a DetailRavitaillement instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public DetailRavitaillementEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer, RelationPopupPanel parent, String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new DetailRavitaillementEditor(factory, true);
			this.initField = initField;
		} else
			editor = new DetailRavitaillementEditor(factory);

		isNew = true;
		setEditable(true);

		setTitle(NLS.constants().detailRavitaillement_create_title());
		createDriver();
		createNewDetailRavitaillement();

		this.setContent(editor);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing DetailRavitaillement instance
	 * @param factory the application request factory
	 * @param entityId the id of the DetailRavitaillement instance to be visualized and edited	 
	 * @param titleContainer the Label that will display the workflow title	 
	 */
	public DetailRavitaillementEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer) {
		this(factory, entityId, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing DetailRavitaillement instance
	 * @param factory the application request factory
	 * @param entityId the id of the DetailRavitaillement instance to be visualized and edited	
	 * @param titleContainer the label	 	 
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public DetailRavitaillementEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer, RelationPopupPanel parent,
			String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new DetailRavitaillementEditor(factory, true);
			this.initField = initField;
		} else
			editor = new DetailRavitaillementEditor(factory);

		setModifiable(false);
		isNew = false;
		setEditable(false);

		createDriver();
		fetchDetailRavitaillement(entityId);

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
	 * Create a new instance of DetailRavitaillement
	 */
	private void createNewDetailRavitaillement() {

		request = requestFactory.detailRavitaillementRequest();

		/* create a new intance of DetailRavitaillement */
		DetailRavitaillementProxy newDetailRavitaillement = request
				.create(DetailRavitaillementProxy.class);
		newDetailRavitaillement
				.setId(ImogKeyGenerator.generateKeyId("DET_RAV"));
		//create an instance of SortieLot in editor 
		SortieLotProxy newSortieLot = request.create(SortieLotProxy.class);
		newSortieLot.setId(ImogKeyGenerator.generateKeyId("SORT"));

		newDetailRavitaillement.setSortieLot(newSortieLot);
		//create an instance of EntreeLot in editor 
		EntreeLotProxy newEntreeLot = request.create(EntreeLotProxy.class);
		newEntreeLot.setId(ImogKeyGenerator.generateKeyId("ENTR"));

		newDetailRavitaillement.setEntreeLot(newEntreeLot);

		/* push the instance to the editor */
		current = newDetailRavitaillement;
		editorDriver.edit(current, request);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);
		editor.setEdited(true);
	}

	/**
	 * Get an existing instance of DetailRavitaillement
	 * @param entityId the id of the DetailRavitaillementProxy to be fetched
	 */
	private void fetchDetailRavitaillement(String entityId) {

		DetailRavitaillementRequest request = requestFactory
				.detailRavitaillementRequest();

		/* get the DetailRavitaillement instance from database */
		Request<DetailRavitaillementProxy> fetchRequest = request
				.findById(entityId);
		fetchRequest.with("ravitaillement");
		fetchRequest.with("ravitaillement.CDTDepart");
		fetchRequest.with("ravitaillement.CDTArrivee");
		fetchRequest.with("sortieLot");
		fetchRequest.with("sortieLot.CDT");
		fetchRequest.with("sortieLot.lot");
		fetchRequest.with("sortieLot.lot.intrant");
		fetchRequest.with("sortieLot.lot.medicament");
		fetchRequest.with("entreeLot");
		fetchRequest.with("entreeLot.CDT");
		fetchRequest.with("entreeLot.lot");
		fetchRequest.with("entreeLot.lot.intrant");
		fetchRequest.with("entreeLot.lot.medicament");

		fetchRequest.to(new Receiver<DetailRavitaillementProxy>() {
			@Override
			public void onSuccess(DetailRavitaillementProxy entity) {
				viewDetailRavitaillement(entity);
			}
		}).fire();
	}

	/**
	 * Display the current instance of DetailRavitaillement in editor
	 * @param entity the DetailRavitaillementProxy to be displayed
	 */
	private void viewDetailRavitaillement(DetailRavitaillementProxy entity) {

		/* display instance information */
		setTitle(NLS.constants().detailRavitaillement_name() + ": "
				+ EpicamRenderer.get().getDisplayValue(entity));
		setMetaData((ImogBeanProxy) entity);

		/* push the instance to the editor in view mode */
		request = requestFactory.detailRavitaillementRequest();
		current = request.edit(entity);
		SortieLotProxy sortieLot = current.getSortieLot();
		if (sortieLot != null) {
		}
		EntreeLotProxy entreeLot = current.getEntreeLot();
		if (entreeLot != null) {
		}

		editor.setEditedValue(current);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		editorDriver.edit(current, request);
		editor.setEdited(false);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);

		/* display edit button */
		if (AccessManager.canEditDetailRavitaillement())
			setModifiable(true);

		showGlassPanel = false;
		EpicamEntryPoint.GP.hide();
	}

	/**
	 * Edit the current instance of DetailRavitaillement in editor
	 */
	@Override
	protected void edit() {

		/* set the instance in edit mode in the editor */
		editor.setEdited(true);

		/* update field widgets in editor */
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
	 * Persist the current instance of DetailRavitaillement
	 */
	@Override
	protected void save() {

		editor.validateFields();

		editorDriver.flush();

		// Check for errors on the client side
		if (editorDriver.hasErrors()) {
			//Window.alert("DetailRavitaillement form not validated locally");
			return;
		}

		Request<Void> saveRequest = request.save(current, isNew);
		saveRequest.to(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				requestFactory.getEventBus().fireEvent(
						new SaveDetailRavitaillementEvent(current, initField));
				closeForm();
			}

			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> errors) {
				//Window.alert("DetailRavitaillement form not validated on server");

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
				Window.alert("Error updating the DetailRavitaillement");
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
						new ViewDetailRavitaillementEvent(current.getId(),
								closeEvent));
		}

	}

	@Override
	protected void returnToList() {
		requestFactory.getEventBus().fireEvent(
				new ListDetailRavitaillementEvent());
	}

	/**
	 * Setter to inject a Ravitaillement value
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setRavitaillement(RavitaillementProxy value, boolean isLocked) {
		editor.setRavitaillement(value, isLocked);
	}

}
