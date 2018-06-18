package org.imogene.epicam.client.ui.workflow;

import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.imogene.epicam.client.event.list.ListFormationEvent;
import org.imogene.epicam.client.event.view.ViewFormationEvent;
import org.imogene.epicam.client.event.save.SaveFormationEvent;
import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.editor.FormationEditor;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.shared.request.FormationRequest;
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
 * Worflow that manages the life of a FormationProxy in the UI
 * @author MEDES-IMPS
 */
public class FormationEditorWorkflow extends EditorWorkflowComposite {

	interface Driver
			extends
				RequestFactoryEditorDriver<FormationProxy, FormationEditor> {
	}

	private EpicamRequestFactory requestFactory;

	private FormationRequest request;
	public FormationProxy current;
	private Driver editorDriver;
	private FormationEditor editor;
	private String initField;
	private boolean showGlassPanel = false;

	/**
	 * Workflow constructor for the creation of a Formation instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 */
	public FormationEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer) {
		this(factory, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the creation of a Formation instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public FormationEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer, RelationPopupPanel parent, String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new FormationEditor(factory, true);
			this.initField = initField;
		} else
			editor = new FormationEditor(factory);

		isNew = true;
		setEditable(true);

		setTitle(NLS.constants().formation_create_title());
		createDriver();
		createNewFormation();

		this.setContent(editor);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing Formation instance
	 * @param factory the application request factory
	 * @param entityId the id of the Formation instance to be visualized and edited	 
	 * @param titleContainer the Label that will display the workflow title	 
	 */
	public FormationEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer) {
		this(factory, entityId, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing Formation instance
	 * @param factory the application request factory
	 * @param entityId the id of the Formation instance to be visualized and edited	
	 * @param titleContainer the label	 	 
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public FormationEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer, RelationPopupPanel parent,
			String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new FormationEditor(factory, true);
			this.initField = initField;
		} else
			editor = new FormationEditor(factory);

		setModifiable(false);
		isNew = false;
		setEditable(false);

		createDriver();
		fetchFormation(entityId);

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
	 * Create a new instance of Formation
	 */
	private void createNewFormation() {

		request = requestFactory.formationRequest();

		/* create a new intance of Formation */
		FormationProxy newFormation = request.create(FormationProxy.class);
		newFormation.setId(ImogKeyGenerator.generateKeyId("FORM"));
		LocalizedTextProxy newLibelle = request
				.create(LocalizedTextProxy.class);
		newFormation.setLibelle(newLibelle);
		LocalizedTextProxy newLieu = request.create(LocalizedTextProxy.class);
		newFormation.setLieu(newLieu);
		LocalizedTextProxy newObjectifs = request
				.create(LocalizedTextProxy.class);
		newFormation.setObjectifs(newObjectifs);
		newFormation.setEffectuee(false);

		/* push the instance to the editor */
		current = newFormation;
		editorDriver.edit(current, request);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);
		editor.setEdited(true);
	}

	/**
	 * Get an existing instance of Formation
	 * @param entityId the id of the FormationProxy to be fetched
	 */
	private void fetchFormation(String entityId) {

		FormationRequest request = requestFactory.formationRequest();

		/* get the Formation instance from database */
		Request<FormationProxy> fetchRequest = request.findById(entityId);
		fetchRequest.with("libelle");
		fetchRequest.with("lieu");
		fetchRequest.with("objectifs");
		fetchRequest.with("candidatures");
		fetchRequest.with("candidatures.personnel");
		fetchRequest.with("candidatures.districtSante");
		fetchRequest.with("candidatures.districtSante.nom");
		fetchRequest.with("candidatures.CDT");

		fetchRequest.to(new Receiver<FormationProxy>() {
			@Override
			public void onSuccess(FormationProxy entity) {
				viewFormation(entity);
			}
		}).fire();
	}

	/**
	 * Display the current instance of Formation in editor
	 * @param entity the FormationProxy to be displayed
	 */
	private void viewFormation(FormationProxy entity) {

		/* display instance information */
		setTitle(NLS.constants().formation_name() + ": "
				+ EpicamRenderer.get().getDisplayValue(entity));
		setMetaData((ImogBeanProxy) entity);

		/* push the instance to the editor in view mode */
		request = requestFactory.formationRequest();
		current = request.edit(entity);
		if (current.getLibelle() == null) {
			LocalizedTextProxy newLibelle = request
					.create(LocalizedTextProxy.class);
			current.setLibelle(newLibelle);
		}
		if (current.getLieu() == null) {
			LocalizedTextProxy newLieu = request
					.create(LocalizedTextProxy.class);
			current.setLieu(newLieu);
		}
		if (current.getObjectifs() == null) {
			LocalizedTextProxy newObjectifs = request
					.create(LocalizedTextProxy.class);
			current.setObjectifs(newObjectifs);
		}

		editor.setEditedValue(current);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		editorDriver.edit(current, request);
		editor.setEdited(false);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);

		/* display edit button */
		if (AccessManager.canEditFormation())
			setModifiable(true);

		showGlassPanel = false;
		EpicamEntryPoint.GP.hide();
	}

	/**
	 * Edit the current instance of Formation in editor
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
	 * Persist the current instance of Formation
	 */
	@Override
	protected void save() {

		editor.validateFields();

		editorDriver.flush();

		// Check for errors on the client side
		if (editorDriver.hasErrors()) {
			//Window.alert("Formation form not validated locally");
			return;
		}

		Request<Void> saveRequest = request.save(current, isNew);
		saveRequest.to(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				requestFactory.getEventBus().fireEvent(
						new SaveFormationEvent(current, initField));
				closeForm();
			}

			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> errors) {
				//Window.alert("Formation form not validated on server");

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
				Window.alert("Error updating the Formation");
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
						new ViewFormationEvent(current.getId(), closeEvent));
		}

	}

	@Override
	protected void returnToList() {
		requestFactory.getEventBus().fireEvent(new ListFormationEvent());
	}

}
