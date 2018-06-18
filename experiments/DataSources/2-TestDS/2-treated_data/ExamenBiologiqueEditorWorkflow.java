package org.imogene.epicam.client.ui.workflow ; public class ExamenBiologiqueEditorWorkflow extends EditorWorkflowComposite { interface Driver extends RequestFactoryEditorDriver<ExamenBiologiqueProxy, ExamenBiologiqueEditor> { }  private EpicamRequestFactory requestFactory ;  private ExamenBiologiqueRequest request ;  public ExamenBiologiqueProxy current ;  private Driver editorDriver ;  private ExamenBiologiqueEditor editor ;  private String initField ;  private boolean showGlassPanel = false ;  * Workflow constructor for the creation of a ExamenBiologique instance * @param factory the application request factory * @param titleContainer the Label that will display the workflow title public ExamenBiologiqueEditorWorkflow(EpicamRequestFactory factory, Label titleContainer) { this(factory, titleContainer, null, null) ;  }  * Workflow constructor for the creation of a ExamenBiologique instance * @param factory the application request factory * @param titleContainer the Label that will display the workflow title * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field * @param initField the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field public ExamenBiologiqueEditorWorkflow(EpicamRequestFactory factory, Label titleContainer, RelationPopupPanel parent, String initField) { super(factory.getEventBus(), titleContainer, parent) ;  requestFactory = factory ;  if (parent != null) { editor = new ExamenBiologiqueEditor(factory, true) ;  this.initField = initField ;  }  else editor = new ExamenBiologiqueEditor(factory) ;  isNew = true ;  setEditable(true) ;  setTitle(NLS.constants().examenBiologique_create_title()) ;  createDriver() ;  createNewExamenBiologique() ;  this.setContent(editor) ;  }  * Workflow constructor for the visualization and edition of an existing ExamenBiologique instance * @param factory the application request factory * @param entityId the id of the ExamenBiologique instance to be visualized and edited  * @param titleContainer the Label that will display the workflow title  public ExamenBiologiqueEditorWorkflow(EpicamRequestFactory factory, String entityId, Label titleContainer) { this(factory, entityId, titleContainer, null, null) ;  }  * Workflow constructor for the visualization and edition of an existing ExamenBiologique instance * @param factory the application request factory * @param entityId the id of the ExamenBiologique instance to be visualized and edited  * @param titleContainer the label  * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field * @param initField the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field public ExamenBiologiqueEditorWorkflow(EpicamRequestFactory factory, String entityId, Label titleContainer, RelationPopupPanel parent, String initField) { super(factory.getEventBus(), titleContainer, parent) ;  requestFactory = factory ;  if (parent != null) { editor = new ExamenBiologiqueEditor(factory, true) ;  this.initField = initField ;  }  else editor = new ExamenBiologiqueEditor(factory) ;  setModifiable(false) ;  isNew = false ;  setEditable(false) ;  createDriver() ;  fetchExamenBiologique(entityId) ;  this.setContent(editor) ;  showGlassPanel = true ;  }  @Override protected void onAttach() { super.onAttach() ;  if (showGlassPanel) { EpicamEntryPoint.GP.showAndAdapt(this) ;  }  }  * Create a new instance of ExamenBiologique private void createNewExamenBiologique() { request = requestFactory.examenBiologiqueRequest() ;  ExamenBiologiqueProxy newExamenBiologique = request .create(ExamenBiologiqueProxy.class) ;  newExamenBiologique.setId(ImogKeyGenerator.generateKeyId("EXAM_BIO")) ;  current = newExamenBiologique ;  editorDriver.edit(current, request) ;  editor.setRequestContextForListEditors(request) ;  editor.computeVisibility(null, true) ;  editor.setEdited(true) ;  }  * Get an existing instance of ExamenBiologique * @param entityId the id of the ExamenBiologiqueProxy to be fetched private void fetchExamenBiologique(String entityId) { ExamenBiologiqueRequest request = requestFactory .examenBiologiqueRequest() ;  Request<ExamenBiologiqueProxy> fetchRequest = request .findById(entityId) ;  fetchRequest.with("patient") ;  fetchRequest.to(new Receiver<ExamenBiologiqueProxy>() { @Override public void onSuccess(ExamenBiologiqueProxy entity) { viewExamenBiologique(entity) ;  }  } ).fire() ;  }  * Display the current instance of ExamenBiologique in editor * @param entity the ExamenBiologiqueProxy to be displayed private void viewExamenBiologique(ExamenBiologiqueProxy entity) { setTitle(NLS.constants().examenBiologique_name() + ": " + EpicamRenderer.get().getDisplayValue(entity)) ;  setMetaData((ImogBeanProxy) entity) ;  request = requestFactory.examenBiologiqueRequest() ;  current = request.edit(entity) ;  editor.setEditedValue(current) ;  editor.setRequestContextForListEditors(request) ;  editorDriver.edit(current, request) ;  editor.setEdited(false) ;  editor.computeVisibility(null, true) ;  if (AccessManager.canEditExamenBiologique()) setModifiable(true) ;  showGlassPanel = false ;  EpicamEntryPoint.GP.hide() ;  }  * Edit the current instance of ExamenBiologique in editor @Override protected void edit() { editor.setEdited(true) ;  }  * Initialize the editor driver private void createDriver() { if (editorDriver == null) { editorDriver = GWT.create(Driver.class) ;  editorDriver.initialize(requestFactory, editor) ;  }  }  * Persist the current instance of ExamenBiologique @Override protected void save() { editor.validateFields() ;  editorDriver.flush() ;  // Check for errors on the client side if (editorDriver.hasErrors()) { //Window.alert("ExamenBiologique form not validated locally") ;  return ;  }  Request<Void> saveRequest = request.save(current, isNew) ;  saveRequest.to(new Receiver<Void>() { @Override public void onSuccess(Void response) { requestFactory.getEventBus().fireEvent( new SaveExamenBiologiqueEvent(current, initField)) ;  closeForm() ;  }  @Override public void onConstraintViolation(Set<ConstraintViolation<?>> errors) { //Window.alert("ExamenBiologique form not validated on server") ;  //TODO manage errors on client side when made available by GWT  if (errors != null && errors.size() > 0) { // convert ConstraintViolation to get localized messages EpicamRenderer renderer = EpicamRenderer.get() ;  Set<ConstraintViolation<?>> imogErrors = new HashSet<ConstraintViolation<?>>() ;  for (ConstraintViolation<?> error : errors) { ImogConstraintViolation violation = new ImogConstraintViolation() ;  violation.setLeafBean((BaseProxy) error.getLeafBean()) ;  violation.setPropertyPath(error.getPropertyPath()) ;  violation.setRootBean((BaseProxy) error.getRootBean()) ;  violation.setMessage(renderer.getI18nErrorMessage(error .getMessage())) ;  imogErrors.add(violation) ;  }  editorDriver.setConstraintViolations(imogErrors) ;  }  }  @Override public void onFailure(ServerFailure error) { Window.alert("Error updating the ExamenBiologique") ;  super.onFailure(error) ;  }  } ) ;  request.fire() ;  }  @Override protected void cancel() { if (parent != null) parent.hide() ;  else { if (isNew) requestFactory.getEventBus().fireEvent(closeEvent) ;  else requestFactory.getEventBus().fireEvent( new ViewExamenBiologiqueEvent(current.getId(), closeEvent)) ;  }  }  @Override protected void returnToList() { requestFactory.getEventBus().fireEvent(new ListExamenBiologiqueEvent()) ;  }  * Setter to inject a Patient value * @param value the value to be injected * @param isLocked true if relation field shall be locked (non editable) public void setPatient(PatientProxy value, boolean isLocked) { editor.setPatient(value, isLocked) ;  } }