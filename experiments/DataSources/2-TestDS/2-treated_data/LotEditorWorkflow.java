package org.imogene.epicam.client.ui.workflow ; public class LotEditorWorkflow extends EditorWorkflowComposite { interface Driver extends RequestFactoryEditorDriver<LotProxy, LotEditor> { }  private EpicamRequestFactory requestFactory ;  private LotRequest request ;  public LotProxy current ;  private Driver editorDriver ;  private LotEditor editor ;  private String initField ;  private boolean showGlassPanel = false ;  * Workflow constructor for the creation of a Lot instance * @param factory the application request factory * @param titleContainer the Label that will display the workflow title public LotEditorWorkflow(EpicamRequestFactory factory, Label titleContainer) { this(factory, titleContainer, null, null) ;  }  * Workflow constructor for the creation of a Lot instance * @param factory the application request factory * @param titleContainer the Label that will display the workflow title * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field * @param initField the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field public LotEditorWorkflow(EpicamRequestFactory factory, Label titleContainer, RelationPopupPanel parent, String initField) { super(factory.getEventBus(), titleContainer, parent) ;  requestFactory = factory ;  if (parent != null) { editor = new LotEditor(factory, true) ;  this.initField = initField ;  }  else editor = new LotEditor(factory) ;  isNew = true ;  setEditable(true) ;  setTitle(NLS.constants().lot_create_title()) ;  createDriver() ;  createNewLot() ;  this.setContent(editor) ;  }  * Workflow constructor for the visualization and edition of an existing Lot instance * @param factory the application request factory * @param entityId the id of the Lot instance to be visualized and edited  * @param titleContainer the Label that will display the workflow title  public LotEditorWorkflow(EpicamRequestFactory factory, String entityId, Label titleContainer) { this(factory, entityId, titleContainer, null, null) ;  }  * Workflow constructor for the visualization and edition of an existing Lot instance * @param factory the application request factory * @param entityId the id of the Lot instance to be visualized and edited  * @param titleContainer the label  * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field * @param initField the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field public LotEditorWorkflow(EpicamRequestFactory factory, String entityId, Label titleContainer, RelationPopupPanel parent, String initField) { super(factory.getEventBus(), titleContainer, parent) ;  requestFactory = factory ;  if (parent != null) { editor = new LotEditor(factory, true) ;  this.initField = initField ;  }  else editor = new LotEditor(factory) ;  setModifiable(false) ;  isNew = false ;  setEditable(false) ;  createDriver() ;  fetchLot(entityId) ;  this.setContent(editor) ;  showGlassPanel = true ;  }  @Override protected void onAttach() { super.onAttach() ;  if (showGlassPanel) { EpicamEntryPoint.GP.showAndAdapt(this) ;  }  }  * Create a new instance of Lot private void createNewLot() { request = requestFactory.lotRequest() ;  LotProxy newLot = request.create(LotProxy.class) ;  newLot.setId(ImogKeyGenerator.generateKeyId("LOT")) ;  newLot.setType("") ;  current = newLot ;  editorDriver.edit(current, request) ;  editor.setRequestContextForListEditors(request) ;  editor.computeVisibility(null, true) ;  // Field districtSante depends on the value of field region editor.getDistrictSanteFilteredByRegion(null) ;  // Field cDT depends on the value of field districtSante editor.getCDTFilteredByDistrictSante(null) ;  editor.setEdited(true) ;  }  * Get an existing instance of Lot * @param entityId the id of the LotProxy to be fetched private void fetchLot(String entityId) { LotRequest request = requestFactory.lotRequest() ;  Request<LotProxy> fetchRequest = request.findById(entityId) ;  fetchRequest.with("region") ;  fetchRequest.with("region.nom") ;  fetchRequest.with("districtSante") ;  fetchRequest.with("districtSante.nom") ;  fetchRequest.with("CDT") ;  fetchRequest.with("medicament") ;  fetchRequest.with("intrant") ;  fetchRequest.to(new Receiver<LotProxy>() { @Override public void onSuccess(LotProxy entity) { viewLot(entity) ;  }  } ).fire() ;  }  * Display the current instance of Lot in editor * @param entity the LotProxy to be displayed private void viewLot(LotProxy entity) { setTitle(NLS.constants().lot_name() + ": " + EpicamRenderer.get().getDisplayValue(entity)) ;  setMetaData((ImogBeanProxy) entity) ;  request = requestFactory.lotRequest() ;  current = request.edit(entity) ;  editor.setEditedValue(current) ;  editor.setRequestContextForListEditors(request) ;  editorDriver.edit(current, request) ;  editor.setEdited(false) ;  editor.computeVisibility(null, true) ;  if (AccessManager.canEditLot()) setModifiable(true) ;  showGlassPanel = false ;  EpicamEntryPoint.GP.hide() ;  }  * Edit the current instance of Lot in editor @Override protected void edit() { editor.setEdited(true) ;  // Field districtSante depends on the value of field region editor.getDistrictSanteFilteredByRegion(current.getRegion()) ;  // Field cDT depends on the value of field districtSante editor.getCDTFilteredByDistrictSante(current.getDistrictSante()) ;  }  * Initialize the editor driver private void createDriver() { if (editorDriver == null) { editorDriver = GWT.create(Driver.class) ;  editorDriver.initialize(requestFactory, editor) ;  }  }  * Persist the current instance of Lot @Override protected void save() { editor.validateFields() ;  if (isNew) { checkLotUnicityForCdt() ;  }  else fireSaveRequest() ;  }  *  private void checkLotUnicityForCdt() { Request<Boolean> checkLotRequest = requestFactory.lotRequest() .isLotUniqueForCdt(editor.getNumeroLot(), editor.getCdtLot().getId()) ;  checkLotRequest.to(new Receiver<Boolean>() { @Override public void onSuccess(Boolean isUnique) { if (isUnique != null && !isUnique) editor.raiseLotNotUniqueError() ;  fireSaveRequest() ;  }  @Override public void onFailure(ServerFailure error) { super.onFailure(error) ;  }  } ).fire() ;  }  * TODO changed private void fireSaveRequest() { editorDriver.flush() ;  // Check for errors on the client side if (editorDriver.hasErrors()) { //Window.alert("Lot form not validated locally") ;  return ;  }  Request<Void> saveRequest = request.save(current, isNew) ;  saveRequest.to(new Receiver<Void>() { @Override public void onSuccess(Void response) { requestFactory.getEventBus().fireEvent( new SaveLotEvent(current, initField)) ;  closeForm() ;  }  @Override public void onConstraintViolation(Set<ConstraintViolation<?>> errors) { //Window.alert("Lot form not validated on server") ;  //TODO manage errors on client side when made available by GWT  if (errors != null && errors.size() > 0) { // convert ConstraintViolation to get localized messages EpicamRenderer renderer = EpicamRenderer.get() ;  Set<ConstraintViolation<?>> imogErrors = new HashSet<ConstraintViolation<?>>() ;  for (ConstraintViolation<?> error : errors) { ImogConstraintViolation violation = new ImogConstraintViolation() ;  violation.setLeafBean((BaseProxy) error.getLeafBean()) ;  violation.setPropertyPath(error.getPropertyPath()) ;  violation.setRootBean((BaseProxy) error.getRootBean()) ;  violation.setMessage(renderer.getI18nErrorMessage(error .getMessage())) ;  imogErrors.add(violation) ;  }  editorDriver.setConstraintViolations(imogErrors) ;  }  }  @Override public void onFailure(ServerFailure error) { Window.alert("Error updating the Lot") ;  super.onFailure(error) ;  }  } ) ;  request.fire() ;  }  @Override protected void cancel() { if (parent != null) parent.hide() ;  else { if (isNew) requestFactory.getEventBus().fireEvent(closeEvent) ;  else requestFactory.getEventBus().fireEvent( new ViewLotEvent(current.getId(), closeEvent)) ;  }  }  @Override protected void returnToList() { requestFactory.getEventBus().fireEvent(new ListLotEvent()) ;  }  * Setter to inject a Region value * @param value the value to be injected * @param isLocked true if relation field shall be locked (non editable) public void setRegion(RegionProxy value, boolean isLocked) { editor.setRegion(value, isLocked) ;  }  * Setter to inject a DistrictSante value * @param value the value to be injected * @param isLocked true if relation field shall be locked (non editable) public void setDistrictSante(DistrictSanteProxy value, boolean isLocked) { editor.setDistrictSante(value, isLocked) ;  }  * Setter to inject a CentreDiagTrait value * @param value the value to be injected * @param isLocked true if relation field shall be locked (non editable) public void setCDT(CentreDiagTraitProxy value, boolean isLocked) { editor.setCDT(value, isLocked) ;  }  * Setter to inject a Medicament value * @param value the value to be injected * @param isLocked true if relation field shall be locked (non editable) public void setMedicament(MedicamentProxy value, boolean isLocked) { editor.setMedicament(value, isLocked) ;  }  * Setter to inject a Intrant value * @param value the value to be injected * @param isLocked true if relation field shall be locked (non editable) public void setIntrant(IntrantProxy value, boolean isLocked) { editor.setIntrant(value, isLocked) ;  } }