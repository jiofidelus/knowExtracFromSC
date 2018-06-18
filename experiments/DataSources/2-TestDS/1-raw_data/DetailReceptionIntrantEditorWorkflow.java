package org.imogene.epicam.client.ui.workflow;

import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.imogene.epicam.client.event.list.ListDetailReceptionIntrantEvent;
import org.imogene.epicam.client.event.view.ViewDetailReceptionIntrantEvent;
import org.imogene.epicam.client.event.save.SaveDetailReceptionIntrantEvent;
import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.editor.DetailReceptionIntrantEditor;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.shared.request.DetailReceptionIntrantRequest;
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
 * Worflow that manages the life of a DetailReceptionIntrantProxy in the UI
 * @author MEDES-IMPS
 */
public class DetailReceptionIntrantEditorWorkflow
		extends
			EditorWorkflowComposite {

	interface Driver
			extends
				RequestFactoryEditorDriver<DetailReceptionIntrantProxy, DetailReceptionIntrantEditor> {
	}

	private EpicamRequestFactory requestFactory;

	private DetailReceptionIntrantRequest request;
	public DetailReceptionIntrantProxy current;
	private Driver editorDriver;
	private DetailReceptionIntrantEditor editor;
	private String initField;
	private boolean showGlassPanel = false;

	/**
	 * Workflow constructor for the creation of a DetailReceptionIntrant instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 */
	public DetailReceptionIntrantEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer) {
		this(factory, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the creation of a DetailReceptionIntrant instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public DetailReceptionIntrantEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer, RelationPopupPanel parent, String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new DetailReceptionIntrantEditor(factory, true);
			this.initField = initField;
		} else
			editor = new DetailReceptionIntrantEditor(factory);

		isNew = true;
		setEditable(true);

		setTitle(NLS.constants().detailReceptionIntrant_create_title());
		createDriver();
		createNewDetailReceptionIntrant();

		this.setContent(editor);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing DetailReceptionIntrant instance
	 * @param factory the application request factory
	 * @param entityId the id of the DetailReceptionIntrant instance to be visualized and edited	 
	 * @param titleContainer the Label that will display the workflow title	 
	 */
	public DetailReceptionIntrantEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer) {
		this(factory, entityId, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing DetailReceptionIntrant instance
	 * @param factory the application request factory
	 * @param entityId the id of the DetailReceptionIntrant instance to be visualized and edited	
	 * @param titleContainer the label	 	 
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public DetailReceptionIntrantEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer, RelationPopupPanel parent,
			String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new DetailReceptionIntrantEditor(factory, true);
			this.initField = initField;
		} else
			editor = new DetailReceptionIntrantEditor(factory);

		setModifiable(false);
		isNew = false;
		setEditable(false);

		createDriver();
		fetchDetailReceptionIntrant(entityId);

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
	 * Create a new instance of DetailReceptionIntrant
	 */
	private void createNewDetailReceptionIntrant() {

		request = requestFactory.detailReceptionIntrantRequest();

		/* create a new intance of DetailReceptionIntrant */
		DetailReceptionIntrantProxy newDetailReceptionIntrant = request
				.create(DetailReceptionIntrantProxy.class);
		newDetailReceptionIntrant.setId(ImogKeyGenerator
				.generateKeyId("DET_REC_INT"));
		//create an instance of EntreeLot in editor 
		EntreeLotProxy newEntreeLot = request.create(EntreeLotProxy.class);
		newEntreeLot.setId(ImogKeyGenerator.generateKeyId("ENTR"));

		newDetailReceptionIntrant.setEntreeLot(newEntreeLot);

		/* push the instance to the editor */
		current = newDetailReceptionIntrant;
		editorDriver.edit(current, request);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);
		// Field detailCommande depends on the value of field commande
		editor.getDetailCommandeFilteredByCommande(null);
		editor.setEdited(true);
	}

	/**
	 * Get an existing instance of DetailReceptionIntrant
	 * @param entityId the id of the DetailReceptionIntrantProxy to be fetched
	 */
	private void fetchDetailReceptionIntrant(String entityId) {

		DetailReceptionIntrantRequest request = requestFactory
				.detailReceptionIntrantRequest();

		/* get the DetailReceptionIntrant instance from database */
		Request<DetailReceptionIntrantProxy> fetchRequest = request
				.findById(entityId);
		fetchRequest.with("reception");
		fetchRequest.with("reception.CDT");
		fetchRequest.with("commande");
		fetchRequest.with("commande.CDT");
		fetchRequest.with("detailCommande");
		fetchRequest.with("detailCommande.intrant");
		fetchRequest.with("entreeLot");
		fetchRequest.with("entreeLot.CDT");
		fetchRequest.with("entreeLot.lot");
		fetchRequest.with("entreeLot.lot.intrant");
		fetchRequest.with("entreeLot.lot.medicament");

		fetchRequest.to(new Receiver<DetailReceptionIntrantProxy>() {
			@Override
			public void onSuccess(DetailReceptionIntrantProxy entity) {
				viewDetailReceptionIntrant(entity);
			}
		}).fire();
	}

	/**
	 * Display the current instance of DetailReceptionIntrant in editor
	 * @param entity the DetailReceptionIntrantProxy to be displayed
	 */
	private void viewDetailReceptionIntrant(DetailReceptionIntrantProxy entity) {

		/* display instance information */
		setTitle(NLS.constants().detailReceptionIntrant_name() + ": "
				+ EpicamRenderer.get().getDisplayValue(entity));
		setMetaData((ImogBeanProxy) entity);

		/* push the instance to the editor in view mode */
		request = requestFactory.detailReceptionIntrantRequest();
		current = request.edit(entity);
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
		if (AccessManager.canEditDetailReceptionIntrant())
			setModifiable(true);

		showGlassPanel = false;
		EpicamEntryPoint.GP.hide();
	}

	/**
	 * Edit the current instance of DetailReceptionIntrant in editor
	 */
	@Override
	protected void edit() {

		/* set the instance in edit mode in the editor */
		editor.setEdited(true);

		/* update field widgets in editor */
		// Field detailCommande depends on the value of field commande
		editor.getDetailCommandeFilteredByCommande(current.getCommande());
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
	 * Persist the current instance of DetailReceptionIntrant
	 */
	@Override
	protected void save() {

		editor.validateFields();

		editorDriver.flush();

		// Check for errors on the client side
		if (editorDriver.hasErrors()) {
			//Window.alert("DetailReceptionIntrant form not validated locally");
			return;
		}

		Request<Void> saveRequest = request.save(current, isNew);
		saveRequest.to(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				requestFactory.getEventBus()
						.fireEvent(
								new SaveDetailReceptionIntrantEvent(current,
										initField));
				closeForm();
			}

			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> errors) {
				//Window.alert("DetailReceptionIntrant form not validated on server");

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
				Window.alert("Error updating the DetailReceptionIntrant");
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
						new ViewDetailReceptionIntrantEvent(current.getId(),
								closeEvent));
		}

	}

	@Override
	protected void returnToList() {
		requestFactory.getEventBus().fireEvent(
				new ListDetailReceptionIntrantEvent());
	}

	/**
	 * Setter to inject a Reception value
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setReception(ReceptionProxy value, boolean isLocked) {
		editor.setReception(value, isLocked);
	}
	/**
	 * Setter to inject a Commande value
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setCommande(CommandeProxy value, boolean isLocked) {
		editor.setCommande(value, isLocked);
	}
	/**
	 * Setter to inject a DetailCommandeIntrant value
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setDetailCommande(DetailCommandeIntrantProxy value,
			boolean isLocked) {
		editor.setDetailCommande(value, isLocked);
	}

}
