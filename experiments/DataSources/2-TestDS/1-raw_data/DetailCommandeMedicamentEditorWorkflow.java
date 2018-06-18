package org.imogene.epicam.client.ui.workflow;

import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.imogene.epicam.client.event.list.ListDetailCommandeMedicamentEvent;
import org.imogene.epicam.client.event.view.ViewDetailCommandeMedicamentEvent;
import org.imogene.epicam.client.event.save.SaveDetailCommandeMedicamentEvent;
import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.editor.DetailCommandeMedicamentEditor;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.shared.request.DetailCommandeMedicamentRequest;
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
 * Worflow that manages the life of a DetailCommandeMedicamentProxy in the UI
 * @author MEDES-IMPS
 */
public class DetailCommandeMedicamentEditorWorkflow
		extends
			EditorWorkflowComposite {

	interface Driver
			extends
				RequestFactoryEditorDriver<DetailCommandeMedicamentProxy, DetailCommandeMedicamentEditor> {
	}

	private EpicamRequestFactory requestFactory;

	private DetailCommandeMedicamentRequest request;
	public DetailCommandeMedicamentProxy current;
	private Driver editorDriver;
	private DetailCommandeMedicamentEditor editor;
	private String initField;
	private boolean showGlassPanel = false;

	/**
	 * Workflow constructor for the creation of a DetailCommandeMedicament instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 */
	public DetailCommandeMedicamentEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer) {
		this(factory, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the creation of a DetailCommandeMedicament instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public DetailCommandeMedicamentEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer, RelationPopupPanel parent, String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new DetailCommandeMedicamentEditor(factory, true);
			this.initField = initField;
		} else
			editor = new DetailCommandeMedicamentEditor(factory);

		isNew = true;
		setEditable(true);

		setTitle(NLS.constants().detailCommandeMedicament_create_title());
		createDriver();
		createNewDetailCommandeMedicament();

		this.setContent(editor);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing DetailCommandeMedicament instance
	 * @param factory the application request factory
	 * @param entityId the id of the DetailCommandeMedicament instance to be visualized and edited	 
	 * @param titleContainer the Label that will display the workflow title	 
	 */
	public DetailCommandeMedicamentEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer) {
		this(factory, entityId, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing DetailCommandeMedicament instance
	 * @param factory the application request factory
	 * @param entityId the id of the DetailCommandeMedicament instance to be visualized and edited	
	 * @param titleContainer the label	 	 
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public DetailCommandeMedicamentEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer, RelationPopupPanel parent,
			String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new DetailCommandeMedicamentEditor(factory, true);
			this.initField = initField;
		} else
			editor = new DetailCommandeMedicamentEditor(factory);

		setModifiable(false);
		isNew = false;
		setEditable(false);

		createDriver();
		fetchDetailCommandeMedicament(entityId);

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
	 * Create a new instance of DetailCommandeMedicament
	 */
	private void createNewDetailCommandeMedicament() {

		request = requestFactory.detailCommandeMedicamentRequest();

		/* create a new intance of DetailCommandeMedicament */
		DetailCommandeMedicamentProxy newDetailCommandeMedicament = request
				.create(DetailCommandeMedicamentProxy.class);
		newDetailCommandeMedicament.setId(ImogKeyGenerator
				.generateKeyId("DET_CMD_MED"));

		/* push the instance to the editor */
		current = newDetailCommandeMedicament;
		editorDriver.edit(current, request);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);
		editor.setEdited(true);
	}

	/**
	 * Get an existing instance of DetailCommandeMedicament
	 * @param entityId the id of the DetailCommandeMedicamentProxy to be fetched
	 */
	private void fetchDetailCommandeMedicament(String entityId) {

		DetailCommandeMedicamentRequest request = requestFactory
				.detailCommandeMedicamentRequest();

		/* get the DetailCommandeMedicament instance from database */
		Request<DetailCommandeMedicamentProxy> fetchRequest = request
				.findById(entityId);
		fetchRequest.with("commande");
		fetchRequest.with("commande.CDT");
		fetchRequest.with("medicament");

		fetchRequest.to(new Receiver<DetailCommandeMedicamentProxy>() {
			@Override
			public void onSuccess(DetailCommandeMedicamentProxy entity) {
				viewDetailCommandeMedicament(entity);
			}
		}).fire();
	}

	/**
	 * Display the current instance of DetailCommandeMedicament in editor
	 * @param entity the DetailCommandeMedicamentProxy to be displayed
	 */
	private void viewDetailCommandeMedicament(
			DetailCommandeMedicamentProxy entity) {

		/* display instance information */
		setTitle(NLS.constants().detailCommandeMedicament_name() + ": "
				+ EpicamRenderer.get().getDisplayValue(entity));
		setMetaData((ImogBeanProxy) entity);

		/* push the instance to the editor in view mode */
		request = requestFactory.detailCommandeMedicamentRequest();
		current = request.edit(entity);

		editor.setEditedValue(current);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		editorDriver.edit(current, request);
		editor.setEdited(false);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);

		/* display edit button */
		if (AccessManager.canEditDetailCommandeMedicament())
			setModifiable(true);

		showGlassPanel = false;
		EpicamEntryPoint.GP.hide();
	}

	/**
	 * Edit the current instance of DetailCommandeMedicament in editor
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
	 * Persist the current instance of DetailCommandeMedicament
	 */
	@Override
	protected void save() {

		editor.validateFields();

		editorDriver.flush();

		// Check for errors on the client side
		if (editorDriver.hasErrors()) {
			//Window.alert("DetailCommandeMedicament form not validated locally");
			return;
		}

		Request<Void> saveRequest = request.save(current, isNew);
		saveRequest.to(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				requestFactory.getEventBus().fireEvent(
						new SaveDetailCommandeMedicamentEvent(current,
								initField));
				closeForm();
			}

			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> errors) {
				//Window.alert("DetailCommandeMedicament form not validated on server");

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
				Window.alert("Error updating the DetailCommandeMedicament");
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
						new ViewDetailCommandeMedicamentEvent(current.getId(),
								closeEvent));
		}

	}

	@Override
	protected void returnToList() {
		requestFactory.getEventBus().fireEvent(
				new ListDetailCommandeMedicamentEvent());
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
	 * Setter to inject a Medicament value
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setMedicament(MedicamentProxy value, boolean isLocked) {
		editor.setMedicament(value, isLocked);
	}

}
