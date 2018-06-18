package org.imogene.epicam.client.ui.workflow;

import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.imogene.epicam.client.event.list.ListDetailReceptionMedicamentEvent;
import org.imogene.epicam.client.event.view.ViewDetailReceptionMedicamentEvent;
import org.imogene.epicam.client.event.save.SaveDetailReceptionMedicamentEvent;
import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.editor.DetailReceptionMedicamentEditor;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.shared.request.DetailReceptionMedicamentRequest;
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
 * Worflow that manages the life of a DetailReceptionMedicamentProxy in the UI
 * @author MEDES-IMPS
 */
public class DetailReceptionMedicamentEditorWorkflow
		extends
			EditorWorkflowComposite {

	interface Driver
			extends
				RequestFactoryEditorDriver<DetailReceptionMedicamentProxy, DetailReceptionMedicamentEditor> {
	}

	private EpicamRequestFactory requestFactory;

	private DetailReceptionMedicamentRequest request;
	public DetailReceptionMedicamentProxy current;
	private Driver editorDriver;
	private DetailReceptionMedicamentEditor editor;
	private String initField;
	private boolean showGlassPanel = false;

	/**
	 * Workflow constructor for the creation of a DetailReceptionMedicament instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 */
	public DetailReceptionMedicamentEditorWorkflow(
			EpicamRequestFactory factory, Label titleContainer) {
		this(factory, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the creation of a DetailReceptionMedicament instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public DetailReceptionMedicamentEditorWorkflow(
			EpicamRequestFactory factory, Label titleContainer,
			RelationPopupPanel parent, String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new DetailReceptionMedicamentEditor(factory, true);
			this.initField = initField;
		} else
			editor = new DetailReceptionMedicamentEditor(factory);

		isNew = true;
		setEditable(true);

		setTitle(NLS.constants().detailReceptionMedicament_create_title());
		createDriver();
		createNewDetailReceptionMedicament();

		this.setContent(editor);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing DetailReceptionMedicament instance
	 * @param factory the application request factory
	 * @param entityId the id of the DetailReceptionMedicament instance to be visualized and edited	 
	 * @param titleContainer the Label that will display the workflow title	 
	 */
	public DetailReceptionMedicamentEditorWorkflow(
			EpicamRequestFactory factory, String entityId, Label titleContainer) {
		this(factory, entityId, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing DetailReceptionMedicament instance
	 * @param factory the application request factory
	 * @param entityId the id of the DetailReceptionMedicament instance to be visualized and edited	
	 * @param titleContainer the label	 	 
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public DetailReceptionMedicamentEditorWorkflow(
			EpicamRequestFactory factory, String entityId,
			Label titleContainer, RelationPopupPanel parent, String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new DetailReceptionMedicamentEditor(factory, true);
			this.initField = initField;
		} else
			editor = new DetailReceptionMedicamentEditor(factory);

		setModifiable(false);
		isNew = false;
		setEditable(false);

		createDriver();
		fetchDetailReceptionMedicament(entityId);

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
	 * Create a new instance of DetailReceptionMedicament
	 */
	private void createNewDetailReceptionMedicament() {

		request = requestFactory.detailReceptionMedicamentRequest();

		/* create a new intance of DetailReceptionMedicament */
		DetailReceptionMedicamentProxy newDetailReceptionMedicament = request
				.create(DetailReceptionMedicamentProxy.class);
		newDetailReceptionMedicament.setId(ImogKeyGenerator
				.generateKeyId("DET_REC_MED"));
		//create an instance of EntreeLot in editor 
		EntreeLotProxy newEntreeLot = request.create(EntreeLotProxy.class);
		newEntreeLot.setId(ImogKeyGenerator.generateKeyId("ENTR"));

		newDetailReceptionMedicament.setEntreeLot(newEntreeLot);

		/* push the instance to the editor */
		current = newDetailReceptionMedicament;
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
	 * Get an existing instance of DetailReceptionMedicament
	 * @param entityId the id of the DetailReceptionMedicamentProxy to be fetched
	 */
	private void fetchDetailReceptionMedicament(String entityId) {

		DetailReceptionMedicamentRequest request = requestFactory
				.detailReceptionMedicamentRequest();

		/* get the DetailReceptionMedicament instance from database */
		Request<DetailReceptionMedicamentProxy> fetchRequest = request
				.findById(entityId);
		fetchRequest.with("reception");
		fetchRequest.with("reception.CDT");
		fetchRequest.with("commande");
		fetchRequest.with("commande.CDT");
		fetchRequest.with("detailCommande");
		fetchRequest.with("detailCommande.medicament");
		fetchRequest.with("entreeLot");
		fetchRequest.with("entreeLot.CDT");
		fetchRequest.with("entreeLot.lot");
		fetchRequest.with("entreeLot.lot.intrant");
		fetchRequest.with("entreeLot.lot.medicament");

		fetchRequest.to(new Receiver<DetailReceptionMedicamentProxy>() {
			@Override
			public void onSuccess(DetailReceptionMedicamentProxy entity) {
				viewDetailReceptionMedicament(entity);
			}
		}).fire();
	}

	/**
	 * Display the current instance of DetailReceptionMedicament in editor
	 * @param entity the DetailReceptionMedicamentProxy to be displayed
	 */
	private void viewDetailReceptionMedicament(
			DetailReceptionMedicamentProxy entity) {

		/* display instance information */
		setTitle(NLS.constants().detailReceptionMedicament_name() + ": "
				+ EpicamRenderer.get().getDisplayValue(entity));
		setMetaData((ImogBeanProxy) entity);

		/* push the instance to the editor in view mode */
		request = requestFactory.detailReceptionMedicamentRequest();
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
		if (AccessManager.canEditDetailReceptionMedicament())
			setModifiable(true);

		showGlassPanel = false;
		EpicamEntryPoint.GP.hide();
	}

	/**
	 * Edit the current instance of DetailReceptionMedicament in editor
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
	 * Persist the current instance of DetailReceptionMedicament
	 */
	@Override
	protected void save() {

		editor.validateFields();

		editorDriver.flush();

		// Check for errors on the client side
		if (editorDriver.hasErrors()) {
			//Window.alert("DetailReceptionMedicament form not validated locally");
			return;
		}

		Request<Void> saveRequest = request.save(current, isNew);
		saveRequest.to(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				requestFactory.getEventBus().fireEvent(
						new SaveDetailReceptionMedicamentEvent(current,
								initField));
				closeForm();
			}

			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> errors) {
				//Window.alert("DetailReceptionMedicament form not validated on server");

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
				Window.alert("Error updating the DetailReceptionMedicament");
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
						new ViewDetailReceptionMedicamentEvent(current.getId(),
								closeEvent));
		}

	}

	@Override
	protected void returnToList() {
		requestFactory.getEventBus().fireEvent(
				new ListDetailReceptionMedicamentEvent());
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
	 * Setter to inject a DetailCommandeMedicament value
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setDetailCommande(DetailCommandeMedicamentProxy value,
			boolean isLocked) {
		editor.setDetailCommande(value, isLocked);
	}

}
