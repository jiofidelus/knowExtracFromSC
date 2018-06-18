package org.imogene.epicam.client.ui.workflow;

import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.imogene.epicam.client.event.list.ListReceptionEvent;
import org.imogene.epicam.client.event.view.ViewReceptionEvent;
import org.imogene.epicam.client.event.save.SaveReceptionEvent;
import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.editor.ReceptionEditor;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.shared.request.ReceptionRequest;
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
 * Worflow that manages the life of a ReceptionProxy in the UI
 * @author MEDES-IMPS
 */
public class ReceptionEditorWorkflow extends EditorWorkflowComposite {

	interface Driver
			extends
				RequestFactoryEditorDriver<ReceptionProxy, ReceptionEditor> {
	}

	private EpicamRequestFactory requestFactory;

	private ReceptionRequest request;
	public ReceptionProxy current;
	private Driver editorDriver;
	private ReceptionEditor editor;
	private String initField;
	private boolean showGlassPanel = false;

	/**
	 * Workflow constructor for the creation of a Reception instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 */
	public ReceptionEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer) {
		this(factory, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the creation of a Reception instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public ReceptionEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer, RelationPopupPanel parent, String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new ReceptionEditor(factory, true);
			this.initField = initField;
		} else
			editor = new ReceptionEditor(factory);

		isNew = true;
		setEditable(true);

		setTitle(NLS.constants().reception_create_title());
		createDriver();
		createNewReception();

		this.setContent(editor);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing Reception instance
	 * @param factory the application request factory
	 * @param entityId the id of the Reception instance to be visualized and edited	 
	 * @param titleContainer the Label that will display the workflow title	 
	 */
	public ReceptionEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer) {
		this(factory, entityId, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing Reception instance
	 * @param factory the application request factory
	 * @param entityId the id of the Reception instance to be visualized and edited	
	 * @param titleContainer the label	 	 
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public ReceptionEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer, RelationPopupPanel parent,
			String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new ReceptionEditor(factory, true);
			this.initField = initField;
		} else
			editor = new ReceptionEditor(factory);

		setModifiable(false);
		isNew = false;
		setEditable(false);

		createDriver();
		fetchReception(entityId);

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
	 * Create a new instance of Reception
	 */
	private void createNewReception() {

		request = requestFactory.receptionRequest();

		/* create a new intance of Reception */
		ReceptionProxy newReception = request.create(ReceptionProxy.class);
		newReception.setId(ImogKeyGenerator.generateKeyId("REC"));
		//create list of DetailReceptionMedicament in editor 
		newReception
				.setMedicaments(new ArrayList<DetailReceptionMedicamentProxy>());
		//create list of DetailReceptionIntrant in editor 
		newReception.setIntrants(new ArrayList<DetailReceptionIntrantProxy>());

		/* push the instance to the editor */
		current = newReception;
		editorDriver.edit(current, request);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);
		// Field districtSante depends on the value of field region
		editor.getDistrictSanteFilteredByRegion(null);
		// Field cDT depends on the value of field districtSante
		editor.getCDTFilteredByDistrictSante(null);
		// Field commande depends on the value of field cDT
		editor.getCommandeFilteredByCDT(null);
		editor.setEdited(true);
	}

	/**
	 * Get an existing instance of Reception
	 * @param entityId the id of the ReceptionProxy to be fetched
	 */
	private void fetchReception(String entityId) {

		ReceptionRequest request = requestFactory.receptionRequest();

		/* get the Reception instance from database */
		Request<ReceptionProxy> fetchRequest = request.findById(entityId);
		fetchRequest.with("region");
		fetchRequest.with("region.nom");
		fetchRequest.with("districtSante");
		fetchRequest.with("districtSante.nom");
		fetchRequest.with("CDT");
		fetchRequest.with("commande");
		fetchRequest.with("commande.CDT");
		fetchRequest.with("medicaments");
		fetchRequest.with("medicaments.commande");
		fetchRequest.with("medicaments.commande.CDT");
		fetchRequest.with("medicaments.detailCommande");
		fetchRequest.with("medicaments.detailCommande.medicament");
		fetchRequest.with("medicaments.entreeLot");
		fetchRequest.with("medicaments.entreeLot.CDT");
		fetchRequest.with("medicaments.entreeLot.lot");
		fetchRequest.with("medicaments.entreeLot.lot.intrant");
		fetchRequest.with("medicaments.entreeLot.lot.medicament");
		fetchRequest.with("intrants");
		fetchRequest.with("intrants.commande");
		fetchRequest.with("intrants.commande.CDT");
		fetchRequest.with("intrants.detailCommande");
		fetchRequest.with("intrants.detailCommande.intrant");
		fetchRequest.with("intrants.entreeLot");
		fetchRequest.with("intrants.entreeLot.CDT");
		fetchRequest.with("intrants.entreeLot.lot");
		fetchRequest.with("intrants.entreeLot.lot.intrant");
		fetchRequest.with("intrants.entreeLot.lot.medicament");

		fetchRequest.to(new Receiver<ReceptionProxy>() {
			@Override
			public void onSuccess(ReceptionProxy entity) {
				viewReception(entity);
			}
		}).fire();
	}

	/**
	 * Display the current instance of Reception in editor
	 * @param entity the ReceptionProxy to be displayed
	 */
	private void viewReception(ReceptionProxy entity) {

		/* display instance information */
		setTitle(NLS.constants().reception_name() + ": "
				+ EpicamRenderer.get().getDisplayValue(entity));
		setMetaData((ImogBeanProxy) entity);

		/* push the instance to the editor in view mode */
		request = requestFactory.receptionRequest();
		current = request.edit(entity);
		List<DetailReceptionMedicamentProxy> medicaments = current
				.getMedicaments();
		if (medicaments != null && medicaments.size() > 0) {
			for (DetailReceptionMedicamentProxy item : medicaments) {
			}
		}
		List<DetailReceptionIntrantProxy> intrants = current.getIntrants();
		if (intrants != null && intrants.size() > 0) {
			for (DetailReceptionIntrantProxy item : intrants) {
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
		if (AccessManager.canEditReception())
			setModifiable(true);

		showGlassPanel = false;
		EpicamEntryPoint.GP.hide();
	}

	/**
	 * Edit the current instance of Reception in editor
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
		// Field commande depends on the value of field cDT
		editor.getCommandeFilteredByCDT(current.getCDT());
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
	 * Persist the current instance of Reception
	 */
	@Override
	protected void save() {

		editor.validateFields();

		editorDriver.flush();

		// Check for errors on the client side
		if (editorDriver.hasErrors()) {
			//Window.alert("Reception form not validated locally");
			return;
		}

		Request<Void> saveRequest = request.save(current, isNew);
		saveRequest.to(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				requestFactory.getEventBus().fireEvent(
						new SaveReceptionEvent(current, initField));
				closeForm();
			}

			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> errors) {
				//Window.alert("Reception form not validated on server");

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
				Window.alert("Error updating the Reception");
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
						new ViewReceptionEvent(current.getId(), closeEvent));
		}

	}

	@Override
	protected void returnToList() {
		requestFactory.getEventBus().fireEvent(new ListReceptionEvent());
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
	 * Setter to inject a Commande value
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setCommande(CommandeProxy value, boolean isLocked) {
		editor.setCommande(value, isLocked);
	}

}
