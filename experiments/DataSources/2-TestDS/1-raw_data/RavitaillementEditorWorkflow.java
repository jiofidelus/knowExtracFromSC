package org.imogene.epicam.client.ui.workflow;

import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.imogene.epicam.client.event.list.ListRavitaillementEvent;
import org.imogene.epicam.client.event.view.ViewRavitaillementEvent;
import org.imogene.epicam.client.event.save.SaveRavitaillementEvent;
import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.editor.RavitaillementEditor;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.shared.request.RavitaillementRequest;
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
 * Worflow that manages the life of a RavitaillementProxy in the UI
 * @author MEDES-IMPS
 */
public class RavitaillementEditorWorkflow extends EditorWorkflowComposite {

	interface Driver
			extends
				RequestFactoryEditorDriver<RavitaillementProxy, RavitaillementEditor> {
	}

	private EpicamRequestFactory requestFactory;

	private RavitaillementRequest request;
	public RavitaillementProxy current;
	private Driver editorDriver;
	private RavitaillementEditor editor;
	private String initField;
	private boolean showGlassPanel = false;

	/**
	 * Workflow constructor for the creation of a Ravitaillement instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 */
	public RavitaillementEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer) {
		this(factory, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the creation of a Ravitaillement instance
	 * @param factory the application request factory
	 * @param titleContainer the Label that will display the workflow title
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public RavitaillementEditorWorkflow(EpicamRequestFactory factory,
			Label titleContainer, RelationPopupPanel parent, String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new RavitaillementEditor(factory, true);
			this.initField = initField;
		} else
			editor = new RavitaillementEditor(factory);

		isNew = true;
		setEditable(true);

		setTitle(NLS.constants().ravitaillement_create_title());
		createDriver();
		createNewRavitaillement();

		this.setContent(editor);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing Ravitaillement instance
	 * @param factory the application request factory
	 * @param entityId the id of the Ravitaillement instance to be visualized and edited	 
	 * @param titleContainer the Label that will display the workflow title	 
	 */
	public RavitaillementEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer) {
		this(factory, entityId, titleContainer, null, null);
	}

	/**
	 * Workflow constructor for the visualization and edition of an existing Ravitaillement instance
	 * @param factory the application request factory
	 * @param entityId the id of the Ravitaillement instance to be visualized and edited	
	 * @param titleContainer the label	 	 
	 * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field
	 * @param initField  the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field
	 */
	public RavitaillementEditorWorkflow(EpicamRequestFactory factory,
			String entityId, Label titleContainer, RelationPopupPanel parent,
			String initField) {

		super(factory.getEventBus(), titleContainer, parent);

		requestFactory = factory;
		if (parent != null) {
			editor = new RavitaillementEditor(factory, true);
			this.initField = initField;
		} else
			editor = new RavitaillementEditor(factory);

		setModifiable(false);
		isNew = false;
		setEditable(false);

		createDriver();
		fetchRavitaillement(entityId);

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
	 * Create a new instance of Ravitaillement
	 */
	private void createNewRavitaillement() {

		request = requestFactory.ravitaillementRequest();

		/* create a new intance of Ravitaillement */
		RavitaillementProxy newRavitaillement = request
				.create(RavitaillementProxy.class);
		newRavitaillement.setId(ImogKeyGenerator.generateKeyId("RAV"));
		//create list of DetailRavitaillement in editor 
		newRavitaillement
				.setDetails(new ArrayList<DetailRavitaillementProxy>());

		/* push the instance to the editor */
		current = newRavitaillement;
		editorDriver.edit(current, request);

		/* set request context for list editor operations */
		editor.setRequestContextForListEditors(request);

		/* update field widgets in editor */
		editor.computeVisibility(null, true);
		// Field districtSante depends on the value of field region
		editor.getDistrictSanteFilteredByRegion(null);
		// Field cDTDepart depends on the value of field districtSante
		editor.getCDTDepartFilteredByDistrictSante(null);
		// Field districtSanteArrivee depends on the value of field regionArrivee
		editor.getDistrictSanteArriveeFilteredByRegionArrivee(null);
		// Field cDTArrivee depends on the value of field districtSanteArrivee
		editor.getCDTArriveeFilteredByDistrictSanteArrivee(null);
		editor.setEdited(true);
	}

	/**
	 * Get an existing instance of Ravitaillement
	 * @param entityId the id of the RavitaillementProxy to be fetched
	 */
	private void fetchRavitaillement(String entityId) {

		RavitaillementRequest request = requestFactory.ravitaillementRequest();

		/* get the Ravitaillement instance from database */
		Request<RavitaillementProxy> fetchRequest = request.findById(entityId);
		fetchRequest.with("region");
		fetchRequest.with("region.nom");
		fetchRequest.with("districtSante");
		fetchRequest.with("districtSante.nom");
		fetchRequest.with("CDTDepart");
		fetchRequest.with("regionArrivee");
		fetchRequest.with("regionArrivee.nom");
		fetchRequest.with("districtSanteArrivee");
		fetchRequest.with("districtSanteArrivee.nom");
		fetchRequest.with("CDTArrivee");
		fetchRequest.with("details");
		fetchRequest.with("details.sortieLot");
		fetchRequest.with("details.sortieLot.CDT");
		fetchRequest.with("details.sortieLot.lot");
		fetchRequest.with("details.sortieLot.lot.intrant");
		fetchRequest.with("details.sortieLot.lot.medicament");
		fetchRequest.with("details.entreeLot");
		fetchRequest.with("details.entreeLot.CDT");
		fetchRequest.with("details.entreeLot.lot");
		fetchRequest.with("details.entreeLot.lot.intrant");
		fetchRequest.with("details.entreeLot.lot.medicament");

		fetchRequest.to(new Receiver<RavitaillementProxy>() {
			@Override
			public void onSuccess(RavitaillementProxy entity) {
				viewRavitaillement(entity);
			}
		}).fire();
	}

	/**
	 * Display the current instance of Ravitaillement in editor
	 * @param entity the RavitaillementProxy to be displayed
	 */
	private void viewRavitaillement(RavitaillementProxy entity) {

		/* display instance information */
		setTitle(NLS.constants().ravitaillement_name() + ": "
				+ EpicamRenderer.get().getDisplayValue(entity));
		setMetaData((ImogBeanProxy) entity);

		/* push the instance to the editor in view mode */
		request = requestFactory.ravitaillementRequest();
		current = request.edit(entity);
		List<DetailRavitaillementProxy> details = current.getDetails();
		if (details != null && details.size() > 0) {
			for (DetailRavitaillementProxy item : details) {
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
		if (AccessManager.canEditRavitaillement())
			setModifiable(true);

		showGlassPanel = false;
		EpicamEntryPoint.GP.hide();
	}

	/**
	 * Edit the current instance of Ravitaillement in editor
	 */
	@Override
	protected void edit() {

		/* set the instance in edit mode in the editor */
		editor.setEdited(true);

		/* update field widgets in editor */
		// Field districtSante depends on the value of field region
		editor.getDistrictSanteFilteredByRegion(current.getRegion());
		// Field cDTDepart depends on the value of field districtSante
		editor.getCDTDepartFilteredByDistrictSante(current.getDistrictSante());
		// Field districtSanteArrivee depends on the value of field regionArrivee
		editor.getDistrictSanteArriveeFilteredByRegionArrivee(current
				.getRegionArrivee());
		// Field cDTArrivee depends on the value of field districtSanteArrivee
		editor.getCDTArriveeFilteredByDistrictSanteArrivee(current
				.getDistrictSanteArrivee());
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
	 * Persist the current instance of Ravitaillement
	 */
	@Override
	protected void save() {

		editor.validateFields();

		editorDriver.flush();

		// Check for errors on the client side
		if (editorDriver.hasErrors()) {
			//Window.alert("Ravitaillement form not validated locally");
			return;
		}

		Request<Void> saveRequest = request.save(current, isNew);
		saveRequest.to(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				requestFactory.getEventBus().fireEvent(
						new SaveRavitaillementEvent(current, initField));
				closeForm();
			}

			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> errors) {
				//Window.alert("Ravitaillement form not validated on server");

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
				Window.alert("Error updating the Ravitaillement");
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
				requestFactory.getEventBus()
						.fireEvent(
								new ViewRavitaillementEvent(current.getId(),
										closeEvent));
		}

	}

	@Override
	protected void returnToList() {
		requestFactory.getEventBus().fireEvent(new ListRavitaillementEvent());
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
	public void setCDTDepart(CentreDiagTraitProxy value, boolean isLocked) {
		editor.setCDTDepart(value, isLocked);
	}
	/**
	 * Setter to inject a Region value
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setRegionArrivee(RegionProxy value, boolean isLocked) {
		editor.setRegionArrivee(value, isLocked);
	}
	/**
	 * Setter to inject a DistrictSante value
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setDistrictSanteArrivee(DistrictSanteProxy value,
			boolean isLocked) {
		editor.setDistrictSanteArrivee(value, isLocked);
	}
	/**
	 * Setter to inject a CentreDiagTrait value
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setCDTArrivee(CentreDiagTraitProxy value, boolean isLocked) {
		editor.setCDTArrivee(value, isLocked);
	}

}
