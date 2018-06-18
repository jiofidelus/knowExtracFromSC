package org.imogene.epicam.client.ui.workflow;

import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.imogene.epicam.client.event.list.ListExamenMicroscopieEvent;
import org.imogene.epicam.client.event.view.ViewExamenMicroscopieEvent;
import org.imogene.epicam.client.event.save.SaveExamenMicroscopieEvent;
import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.editor.ExamenMicroscopieEditor;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.shared.request.ExamenMicroscopieRequest;
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
 * Worflow that manages the life of a ExamenMicroscopieProxy in the UI
 * @author MEDES-IMPS
 */
public class ExamenMicroscopieEditorWorkflow extends EditorWorkflowComposite {

	interface Driver
			extends
				RequestFactoryEditorDriver<ExamenMicroscopieProxy, ExamenMicroscopieEditor> {
	}

	private EpicamRequestFactory requestFactory;

	private ExamenMicroscopieRequest request;
	public ExamenMicroscopieProxy current;
	private Driver editorDriver;
	private ExamenMicroscopieEditor editor;
	private String initField;
	private boolean showGlassPanel = false;

	/**
	 * Workflow constructor for the creation of a ExamenMicroscopie instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 */
	public ExamenMicroscopieEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer) {
		this(factory, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the creation of a ExamenMicroscopie instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public ExamenMicroscopieEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer, RelationPopupPanel parent, String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new ExamenMicroscopieEditor(factory, true);
			this.initField = initField;
		} else
			editor = new ExamenMicroscopieEditor(factory);

		isNew = true;
		setEditable(true);

		setTitle(NLS.constants().examenMicroscopie_create_title());
		createDriver();
		createNewExamenMicroscopie();

		this.setContent(editor);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing ExamenMicroscopie instance
	 * @param factory the application request factory
	 * @param entityId the id of the ExamenMicroscopie instance to be visualized and edited	 
	 * @param titleContainer the Label that will display the workflow title	 
	 */
	public ExamenMicroscopieEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer) {
		this(factory, entityId, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing ExamenMicroscopie instance
	 * @param factory the application request factory
	 * @param entityId the id of the ExamenMicroscopie instance to be visualized and edited	
	 * @param titleContainer the label	 	 
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public ExamenMicroscopieEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer, RelationPopupPanel parent,
			String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new ExamenMicroscopieEditor(factory, true);
			this.initField = initField;
		} else
			editor = new ExamenMicroscopieEditor(factory);

		setModifiable(false);
		isNew = false;
		setEditable(false);

		createDriver();
		fetchExamenMicroscopie(entityId);

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
	 * Create a new instance of ExamenMicroscopie
	 */
	private void createNewExamenMicroscopie() {

		request = requestFactory.examenMicroscopieRequest();

		/* create a new intance of ExamenMicroscopie */
		ExamenMicroscopieProxy newExamenMicroscopie = request
				.create(ExamenMicroscopieProxy.class);
		newExamenMicroscopie
				.setId(ImogKeyGenerator.generateKeyId("EXAM_MICRO"));

		/* push the instance to the editor */
		current = newExamenMicroscopie;
		editorDriver.edit(current, request);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);
		editor.setEdited(true);
	}

	/**
	 * Get an existing instance of ExamenMicroscopie
	 * @param entityId the id of the ExamenMicroscopieProxy to be fetched
	 */
	private void fetchExamenMicroscopie(String entityId) {

		ExamenMicroscopieRequest request = requestFactory
				.examenMicroscopieRequest();

		/* get the ExamenMicroscopie instance from database */
		Request<ExamenMicroscopieProxy> fetchRequest = request
				.findById(entityId);
		fetchRequest.with("CDT");
		fetchRequest.with("laboratoireReference");
		fetchRequest.with("laboratoireReference.nom");
		fetchRequest.with("casTb");
		fetchRequest.with("casTb.patient");

		fetchRequest.to(new Receiver<ExamenMicroscopieProxy>() {
			@Override
			public void onSuccess(ExamenMicroscopieProxy entity) {
				viewExamenMicroscopie(entity);
			}
		}).fire();
	}

	/**
	 * Display the current instance of ExamenMicroscopie in editor
	 * @param entity the ExamenMicroscopieProxy to be displayed
	 */
	private void viewExamenMicroscopie(ExamenMicroscopieProxy entity) {

		/* display instance information */
		setTitle(NLS.constants().examenMicroscopie_name() + ": "
				+ EpicamRenderer.get().getDisplayValue(entity));
		setMetaData((ImogBeanProxy) entity);

		/* push the instance to the editor in view mode */
		request = requestFactory.examenMicroscopieRequest();
		current = request.edit(entity);

		editor.setEditedValue(current);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		editorDriver.edit(current, request);
		editor.setEdited(false);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);

		/* display edit button */
		if (AccessManager.canEditExamenMicroscopie())
			setModifiable(true);

		showGlassPanel = false;
		EpicamEntryPoint.GP.hide();
	}

	/**
	 * Edit the current instance of ExamenMicroscopie in editor
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
	 * Persist the current instance of ExamenMicroscopie
	 */
	@Override
	protected void save() {

		editor.validateFields();

		editorDriver.flush();

		// Check for errors on the client side
		if (editorDriver.hasErrors()) {
			//Window.alert("ExamenMicroscopie form not validated locally");
			return;
		}

		Request<Void> saveRequest = request.save(current, isNew);
		saveRequest.to(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				requestFactory.getEventBus().fireEvent(
						new SaveExamenMicroscopieEvent(current, initField));
				closeForm();
			}

			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> errors) {
				//Window.alert("ExamenMicroscopie form not validated on server");

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
				Window.alert("Error updating the ExamenMicroscopie");
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
						new ViewExamenMicroscopieEvent(current.getId(),
								closeEvent));
		}

	}

	@Override
	protected void returnToList() {
		requestFactory.getEventBus()
				.fireEvent(new ListExamenMicroscopieEvent());
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
	 * Setter to inject a LaboratoireReference value
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setLaboratoireReference(LaboratoireReferenceProxy value,
			boolean isLocked) {
		editor.setLaboratoireReference(value, isLocked);
	}
	/**
	 * Setter to inject a CasTuberculose value
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setCasTb(CasTuberculoseProxy value, boolean isLocked) {
		editor.setCasTb(value, isLocked);
	}

}
