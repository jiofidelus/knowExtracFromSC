package org.imogene.epicam.client.ui.workflow;

import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.imogene.epicam.client.event.list.ListSmsPredefiniEvent;
import org.imogene.epicam.client.event.view.ViewSmsPredefiniEvent;
import org.imogene.epicam.client.event.save.SaveSmsPredefiniEvent;
import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.editor.SmsPredefiniEditor;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.shared.request.SmsPredefiniRequest;
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
 * Worflow that manages the life of a SmsPredefiniProxy in the UI
 * @author MEDES-IMPS
 */
public class SmsPredefiniEditorWorkflow extends EditorWorkflowComposite {

	interface Driver
			extends
				RequestFactoryEditorDriver<SmsPredefiniProxy, SmsPredefiniEditor> {
	}

	private EpicamRequestFactory requestFactory;

	private SmsPredefiniRequest request;
	public SmsPredefiniProxy current;
	private Driver editorDriver;
	private SmsPredefiniEditor editor;
	private String initField;
	private boolean showGlassPanel = false;

	/**
	 * Workflow constructor for the creation of a SmsPredefini instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 */
	public SmsPredefiniEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer) {
		this(factory, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the creation of a SmsPredefini instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public SmsPredefiniEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer, RelationPopupPanel parent, String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new SmsPredefiniEditor(factory, true);
			this.initField = initField;
		} else
			editor = new SmsPredefiniEditor(factory);

		isNew = true;
		setEditable(true);

		setTitle(NLS.constants().smsPredefini_create_title());
		createDriver();
		createNewSmsPredefini();

		this.setContent(editor);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing SmsPredefini instance
	 * @param factory the application request factory
	 * @param entityId the id of the SmsPredefini instance to be visualized and edited	 
	 * @param titleContainer the Label that will display the workflow title	 
	 */
	public SmsPredefiniEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer) {
		this(factory, entityId, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing SmsPredefini instance
	 * @param factory the application request factory
	 * @param entityId the id of the SmsPredefini instance to be visualized and edited	
	 * @param titleContainer the label	 	 
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public SmsPredefiniEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer, RelationPopupPanel parent,
			String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new SmsPredefiniEditor(factory, true);
			this.initField = initField;
		} else
			editor = new SmsPredefiniEditor(factory);

		setModifiable(false);
		isNew = false;
		setEditable(false);

		createDriver();
		fetchSmsPredefini(entityId);

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
	 * Create a new instance of SmsPredefini
	 */
	private void createNewSmsPredefini() {

		request = requestFactory.smsPredefiniRequest();

		/* create a new intance of SmsPredefini */
		SmsPredefiniProxy newSmsPredefini = request
				.create(SmsPredefiniProxy.class);
		newSmsPredefini.setId(ImogKeyGenerator.generateKeyId("SMS"));
		LocalizedTextProxy newObjet = request.create(LocalizedTextProxy.class);
		newSmsPredefini.setObjet(newObjet);
		LocalizedTextProxy newMessage = request
				.create(LocalizedTextProxy.class);
		newSmsPredefini.setMessage(newMessage);

		/* push the instance to the editor */
		current = newSmsPredefini;
		editorDriver.edit(current, request);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);
		editor.setEdited(true);
	}

	/**
	 * Get an existing instance of SmsPredefini
	 * @param entityId the id of the SmsPredefiniProxy to be fetched
	 */
	private void fetchSmsPredefini(String entityId) {

		SmsPredefiniRequest request = requestFactory.smsPredefiniRequest();

		/* get the SmsPredefini instance from database */
		Request<SmsPredefiniProxy> fetchRequest = request.findById(entityId);
		fetchRequest.with("objet");
		fetchRequest.with("message");

		fetchRequest.to(new Receiver<SmsPredefiniProxy>() {
			@Override
			public void onSuccess(SmsPredefiniProxy entity) {
				viewSmsPredefini(entity);
			}
		}).fire();
	}

	/**
	 * Display the current instance of SmsPredefini in editor
	 * @param entity the SmsPredefiniProxy to be displayed
	 */
	private void viewSmsPredefini(SmsPredefiniProxy entity) {

		/* display instance information */
		setTitle(NLS.constants().smsPredefini_name() + ": "
				+ EpicamRenderer.get().getDisplayValue(entity));
		setMetaData((ImogBeanProxy) entity);

		/* push the instance to the editor in view mode */
		request = requestFactory.smsPredefiniRequest();
		current = request.edit(entity);
		if (current.getObjet() == null) {
			LocalizedTextProxy newObjet = request
					.create(LocalizedTextProxy.class);
			current.setObjet(newObjet);
		}
		if (current.getMessage() == null) {
			LocalizedTextProxy newMessage = request
					.create(LocalizedTextProxy.class);
			current.setMessage(newMessage);
		}

		editor.setEditedValue(current);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		editorDriver.edit(current, request);
		editor.setEdited(false);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);

		/* display edit button */
		if (AccessManager.canEditSmsPredefini())
			setModifiable(true);

		showGlassPanel = false;
		EpicamEntryPoint.GP.hide();
	}

	/**
	 * Edit the current instance of SmsPredefini in editor
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
	 * Persist the current instance of SmsPredefini
	 */
	@Override
	protected void save() {

		editor.validateFields();

		editorDriver.flush();

		// Check for errors on the client side
		if (editorDriver.hasErrors()) {
			//Window.alert("SmsPredefini form not validated locally");
			return;
		}

		Request<Void> saveRequest = request.save(current, isNew);
		saveRequest.to(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				requestFactory.getEventBus().fireEvent(
						new SaveSmsPredefiniEvent(current, initField));
				closeForm();
			}

			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> errors) {
				//Window.alert("SmsPredefini form not validated on server");

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
				Window.alert("Error updating the SmsPredefini");
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
						new ViewSmsPredefiniEvent(current.getId(), closeEvent));
		}

	}

	@Override
	protected void returnToList() {
		requestFactory.getEventBus().fireEvent(new ListSmsPredefiniEvent());
	}

}
