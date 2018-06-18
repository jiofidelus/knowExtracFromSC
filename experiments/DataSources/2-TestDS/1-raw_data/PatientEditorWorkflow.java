package org.imogene.epicam.client.ui.workflow;

import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.imogene.epicam.client.event.list.ListPatientEvent;
import org.imogene.epicam.client.event.view.ViewPatientEvent;
import org.imogene.epicam.client.event.save.SavePatientEvent;
import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.editor.PatientEditor;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.shared.request.PatientRequest;
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
 * Worflow that manages the life of a PatientProxy in the UI
 * @author MEDES-IMPS
 */
public class PatientEditorWorkflow extends EditorWorkflowComposite {

	interface Driver
			extends
				RequestFactoryEditorDriver<PatientProxy, PatientEditor> {
	}

	private EpicamRequestFactory requestFactory;

	private PatientRequest request;
	public PatientProxy current;
	private Driver editorDriver;
	private PatientEditor editor;
	private String initField;
	private boolean showGlassPanel = false;

	/**
	 * Workflow constructor for the creation of a Patient instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 */
	public PatientEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer) {
		this(factory, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the creation of a Patient instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public PatientEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer, RelationPopupPanel parent, String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new PatientEditor(factory, true);
			this.initField = initField;
		} else
			editor = new PatientEditor(factory);

		isNew = true;
		setEditable(true);

		setTitle(NLS.constants().patient_create_title());
		createDriver();
		createNewPatient();

		this.setContent(editor);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing Patient instance
	 * @param factory the application request factory
	 * @param entityId the id of the Patient instance to be visualized and edited	 
	 * @param titleContainer the Label that will display the workflow title	 
	 */
	public PatientEditorWorkflow(EpicamRequestFactory factory, String entityId,
			Label titleContainer) {
		this(factory, entityId, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing Patient instance
	 * @param factory the application request factory
	 * @param entityId the id of the Patient instance to be visualized and edited	
	 * @param titleContainer the label	 	 
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public PatientEditorWorkflow(EpicamRequestFactory factory, String entityId,
			Label titleContainer, RelationPopupPanel parent, String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new PatientEditor(factory, true);
			this.initField = initField;
		} else
			editor = new PatientEditor(factory);

		setModifiable(false);
		isNew = false;
		setEditable(false);

		createDriver();
		fetchPatient(entityId);

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
	 * Create a new instance of Patient
	 */
	private void createNewPatient() {

		request = requestFactory.patientRequest();

		/* create a new intance of Patient */
		PatientProxy newPatient = request.create(PatientProxy.class);
		newPatient.setId(ImogKeyGenerator.generateKeyId("PAT"));
		//create list of CasIndex in editor 
		newPatient.setCasIndex(new ArrayList<CasIndexProxy>());
		//create list of ExamenBiologique in editor 
		newPatient
				.setExamensBiologiques(new ArrayList<ExamenBiologiqueProxy>());
		//create list of ExamenSerologie in editor 
		newPatient.setSerologies(new ArrayList<ExamenSerologieProxy>());

		/* push the instance to the editor */
		current = newPatient;
		editorDriver.edit(current, request);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);
		editor.setEdited(true);
	}

	/**
	 * Get an existing instance of Patient
	 * @param entityId the id of the PatientProxy to be fetched
	 */
	private void fetchPatient(String entityId) {

		PatientRequest request = requestFactory.patientRequest();

		/* get the Patient instance from database */
		Request<PatientProxy> fetchRequest = request.findById(entityId);
		fetchRequest.with("centres");
		fetchRequest.with("lieuxDits");
		fetchRequest.with("casTuberculose");
		fetchRequest.with("casTuberculose.patient");
		fetchRequest.with("casIndex");
		fetchRequest.with("casIndex.patientLie");
		fetchRequest.with("examensBiologiques");
		fetchRequest.with("serologies");

		fetchRequest.to(new Receiver<PatientProxy>() {
			@Override
			public void onSuccess(PatientProxy entity) {
				viewPatient(entity);
			}
		}).fire();
	}

	/**
	 * Display the current instance of Patient in editor
	 * @param entity the PatientProxy to be displayed
	 */
	private void viewPatient(PatientProxy entity) {

		/* display instance information */
		setTitle(NLS.constants().patient_name() + ": "
				+ EpicamRenderer.get().getDisplayValue(entity));
		setMetaData((ImogBeanProxy) entity);

		/* push the instance to the editor in view mode */
		request = requestFactory.patientRequest();
		current = request.edit(entity);
		List<CasIndexProxy> casIndex = current.getCasIndex();
		if (casIndex != null && casIndex.size() > 0) {
			for (CasIndexProxy item : casIndex) {
			}
		}
		List<ExamenBiologiqueProxy> examensBiologiques = current
				.getExamensBiologiques();
		if (examensBiologiques != null && examensBiologiques.size() > 0) {
			for (ExamenBiologiqueProxy item : examensBiologiques) {
			}
		}
		List<ExamenSerologieProxy> serologies = current.getSerologies();
		if (serologies != null && serologies.size() > 0) {
			for (ExamenSerologieProxy item : serologies) {
			}
		}

		editor.setEditedValue(current);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		editorDriver.edit(current, request);
		editor.setEdited(false);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);

		/* display edit button */
		if (AccessManager.canEditPatient())
			setModifiable(true);

		showGlassPanel = false;
		EpicamEntryPoint.GP.hide();
	}

	/**
	 * Edit the current instance of Patient in editor
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
	 * Persist the current instance of Patient
	 */
	@Override
	protected void save() {

		editor.validateFields();

		editorDriver.flush();

		// Check for errors on the client side
		if (editorDriver.hasErrors()) {
			//Window.alert("Patient form not validated locally");
			return;
		}

		Request<Void> saveRequest = request.save(current, isNew);
		saveRequest.to(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				requestFactory.getEventBus().fireEvent(
						new SavePatientEvent(current, initField));
				closeForm();
			}

			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> errors) {
				//Window.alert("Patient form not validated on server");

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
				Window.alert("Error updating the Patient");
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
						new ViewPatientEvent(current.getId(), closeEvent));
		}

	}

	@Override
	protected void returnToList() {
		requestFactory.getEventBus().fireEvent(new ListPatientEvent());
	}

}
