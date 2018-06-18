package org.imogene.epicam.client.ui.workflow ; public class SortieLotEditorWorkflow extends EditorWorkflowComposite { interface Driver extends RequestFactoryEditorDriver<SortieLotProxy, SortieLotEditor> { }  private EpicamRequestFactory requestFactory ;  private SortieLotRequest request ;  public SortieLotProxy current ;  private Driver editorDriver ;  private SortieLotEditor editor ;  private String initField ;  private boolean showGlassPanel = false ;  * Workflow constructor for the creation of a SortieLot instance * @param factory the application request factory * @param titleContainer the Label that will display the workflow title public SortieLotEditorWorkflow(EpicamRequestFactory factory, Label titleContainer) { this(factory, titleContainer, null, null) ;  }  * Workflow constructor for the creation of a SortieLot instance * @param factory the application request factory * @param titleContainer the Label that will display the workflow title * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field * @param initField the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field public SortieLotEditorWorkflow(EpicamRequestFactory factory, Label titleContainer, RelationPopupPanel parent, String initField) { super(factory.getEventBus(), titleContainer, parent) ;  requestFactory = factory ;  if (parent != null) { editor = new SortieLotEditor(factory, true) ;  this.initField = initField ;  }  else editor = new SortieLotEditor(factory) ;  isNew = true ;  setEditable(true) ;  setTitle(NLS.constants().sortieLot_create_title()) ;  createDriver() ;  createNewSortieLot() ;  this.setContent(editor) ;  }  * Workflow constructor for the visualization and edition of an existing SortieLot instance * @param factory the application request factory * @param entityId the id of the SortieLot instance to be visualized and edited  * @param titleContainer the Label that will display the workflow title  public SortieLotEditorWorkflow(EpicamRequestFactory factory, String entityId, Label titleContainer) { this(factory, entityId, titleContainer, null, null) ;  }  * Workflow constructor for the visualization and edition of an existing SortieLot instance * @param factory the application request factory * @param entityId the id of the SortieLot instance to be visualized and edited  * @param titleContainer the label  * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field * @param initField the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field public SortieLotEditorWorkflow(EpicamRequestFactory factory, String entityId, Label titleContainer, RelationPopupPanel parent, String initField) { super(factory.getEventBus(), titleContainer, parent) ;  requestFactory = factory ;  if (parent != null) { editor = new SortieLotEditor(factory, true) ;  this.initField = initField ;  }  else editor = new SortieLotEditor(factory) ;  setModifiable(false) ;  isNew = false ;  setEditable(false) ;  createDriver() ;  fetchSortieLot(entityId) ;  this.setContent(editor) ;  showGlassPanel = true ;  }  @Override protected void onAttach() { super.onAttach() ;  if (showGlassPanel) { EpicamEntryPoint.GP.showAndAdapt(this) ;  }  }  * Create a new instance of SortieLot private void createNewSortieLot() { request = requestFactory.sortieLotRequest() ;  SortieLotProxy newSortieLot = request.create(SortieLotProxy.class) ;  newSortieLot.setId(ImogKeyGenerator.generateKeyId("SORT")) ;  current = newSortieLot ;  editorDriver.edit(current, request) ;  editor.setRequestContextForListEditors(request) ;  editor.computeVisibility(null, true) ;  // Field lot depends on the value of field cDT editor.getLotFilteredByCDT(null) ;  editor.setEdited(true) ;  }  * Get an existing instance of SortieLot * @param entityId the id of the SortieLotProxy to be fetched private void fetchSortieLot(String entityId) { SortieLotRequest request = requestFactory.sortieLotRequest() ;  Request<SortieLotProxy> fetchRequest = request.findById(entityId) ;  fetchRequest.with("CDT") ;  fetchRequest.with("lot") ;  fetchRequest.with("lot.intrant") ;  fetchRequest.with("lot.medicament") ;  fetchRequest.to(new Receiver<SortieLotProxy>() { @Override public void onSuccess(SortieLotProxy entity) { viewSortieLot(entity) ;  }  } ).fire() ;  }  * Display the current instance of SortieLot in editor * @param entity the SortieLotProxy to be displayed private void viewSortieLot(SortieLotProxy entity) { setTitle(NLS.constants().sortieLot_name() + ": " + EpicamRenderer.get().getDisplayValue(entity)) ;  setMetaData((ImogBeanProxy) entity) ;  request = requestFactory.sortieLotRequest() ;  current = request.edit(entity) ;  editor.setEditedValue(current) ;  editor.setRequestContextForListEditors(request) ;  editorDriver.edit(current, request) ;  editor.setEdited(false) ;  editor.computeVisibility(null, true) ;  if (AccessManager.canEditSortieLot()) setModifiable(true) ;  showGlassPanel = false ;  EpicamEntryPoint.GP.hide() ;  }  * Edit the current instance of SortieLot in editor @Override protected void edit() { editor.setEdited(true) ;  // Field lot depends on the value of field cDT editor.getLotFilteredByCDT(current.getCDT()) ;  }  * Initialize the editor driver private void createDriver() { if (editorDriver == null) { editorDriver = GWT.create(Driver.class) ;  editorDriver.initialize(requestFactory, editor) ;  }  }  * Persist the current instance of SortieLot @Override protected void save() { editor.validateFields() ;  editorDriver.flush() ;  // Check for errors on the client side if (editorDriver.hasErrors()) { //Window.alert("SortieLot form not validated locally") ;  return ;  }  Request<Void> saveRequest = request.save(current, isNew) ;  saveRequest.to(new Receiver<Void>() { @Override public void onSuccess(Void response) { requestFactory.getEventBus().fireEvent( new SaveSortieLotEvent(current, initField)) ;  closeForm() ;  }  @Override public void onConstraintViolation(Set<ConstraintViolation<?>> errors) { //Window.alert("SortieLot form not validated on server") ;  //TODO manage errors on client side when made available by GWT  if (errors != null && errors.size() > 0) { // convert ConstraintViolation to get localized messages EpicamRenderer renderer = EpicamRenderer.get() ;  Set<ConstraintViolation<?>> imogErrors = new HashSet<ConstraintViolation<?>>() ;  for (ConstraintViolation<?> error : errors) { ImogConstraintViolation violation = new ImogConstraintViolation() ;  violation.setLeafBean((BaseProxy) error.getLeafBean()) ;  violation.setPropertyPath(error.getPropertyPath()) ;  violation.setRootBean((BaseProxy) error.getRootBean()) ;  violation.setMessage(renderer.getI18nErrorMessage(error .getMessage())) ;  imogErrors.add(violation) ;  }  editorDriver.setConstraintViolations(imogErrors) ;  }  }  @Override public void onFailure(ServerFailure error) { Window.alert("Error updating the SortieLot") ;  super.onFailure(error) ;  }  } ) ;  request.fire() ;  }  @Override protected void cancel() { if (parent != null) parent.hide() ;  else { if (isNew) requestFactory.getEventBus().fireEvent(closeEvent) ;  else requestFactory.getEventBus().fireEvent( new ViewSortieLotEvent(current.getId(), closeEvent)) ;  }  }  @Override protected void returnToList() { requestFactory.getEventBus().fireEvent(new ListSortieLotEvent()) ;  }  * Setter to inject a CentreDiagTrait value * @param value the value to be injected * @param isLocked true if relation field shall be locked (non editable) public void setCDT(CentreDiagTraitProxy value, boolean isLocked) { editor.setCDT(value, isLocked) ;  }  * Setter to inject a Lot value * @param value the value to be injected * @param isLocked true if relation field shall be locked (non editable) public void setLot(LotProxy value, boolean isLocked) { editor.setLot(value, isLocked) ;  } }