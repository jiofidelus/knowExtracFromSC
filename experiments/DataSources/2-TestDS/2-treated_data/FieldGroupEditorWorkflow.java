package org.imogene.admin.client.ui.workflow ; public class FieldGroupEditorWorkflow extends EditorWorkflowComposite { interface Driver extends RequestFactoryEditorDriver<FieldGroupProxy, FieldGroupEditor> { }  private AdminRequestFactory requestFactory ;  private ImogBeanRenderer renderer ;  private FieldGroupRequest request ;  public FieldGroupProxy current ;  private Driver editorDriver ;  private FieldGroupEditor editor ;  private String initField ;  * Workflow constructor for the creation of a FieldGroup instance * @param factory the application request factory * @param titleContainer the Label that will display the workflow title public FieldGroupEditorWorkflow(AdminRequestFactory factory, Label titleContainer) { this(factory, titleContainer, null, null) ;  }  * Workflow constructor for the creation of a FieldGroup instance * @param factory the application request factory * @param titleContainer the Label that will display the workflow title * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field * @param initField the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field public FieldGroupEditorWorkflow(AdminRequestFactory factory, Label titleContainer, RelationPopupPanel parent, String initField) { super(factory.getEventBus(), titleContainer, parent) ;  requestFactory = factory ;  if (parent != null) { editor = new FieldGroupEditor(factory, true) ;  this.initField = initField ;  }  else editor = new FieldGroupEditor(factory) ;  isNew = true ;  setEditable(true) ;  setTitle(AdminNLS.constants().fieldGroup_create_title()) ;  createDriver() ;  createNewFieldGroup() ;  this.setContent(editor) ;  }  * Workflow constructor for the visualization and edition of an existing FieldGroup instance * @param factory the application request factory * @param entityId the id of the FieldGroup instance to be visualized and edited * @param titleContainer the Label that will display the workflow title public FieldGroupEditorWorkflow(AdminRequestFactory factory, String entityId, Label titleContainer, ImogBeanRenderer renderer) { this(factory, entityId, titleContainer, null, null, renderer) ;  }  * Workflow constructor for the visualization and edition of an existing FieldGroup instance * @param factory the application request factory * @param entityId the id of the FieldGroup instance to be visualized and edited * @param titleContainer the label * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field * @param initField the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field public FieldGroupEditorWorkflow(AdminRequestFactory factory, String entityId, Label titleContainer, RelationPopupPanel parent, String initField, ImogBeanRenderer renderer) { super(factory.getEventBus(), titleContainer, parent) ;  this.renderer = renderer ;  requestFactory = factory ;  if (parent != null) { editor = new FieldGroupEditor(factory, true) ;  this.initField = initField ;  }  else editor = new FieldGroupEditor(factory) ;  setModifiable(false) ;  isNew = false ;  setEditable(false) ;  createDriver() ;  fetchFieldGroup(entityId) ;  this.setContent(editor) ;  }  * Create a new instance of FieldGroup private void createNewFieldGroup() { request = requestFactory.fieldGroupRequest() ;  FieldGroupProxy newFieldGroup = request.create(FieldGroupProxy.class) ;  newFieldGroup.setId(ImogKeyGenerator.generateKeyId("GRP")) ;  current = newFieldGroup ;  editorDriver.edit(current, request) ;  editor.setRequestContextForListEditors(request) ;  editor.computeVisibility(null, true) ;  editor.setEdited(true) ;  }  * Get an existing instance of FieldGroup * @param entityId the id of the FieldGroupProxy to be fetched private void fetchFieldGroup(String entityId) { FieldGroupRequest request = requestFactory.fieldGroupRequest() ;  Request<FieldGroupProxy> fetchRequest = request.findById(entityId) ;  fetchRequest.with("entity") ;  fetchRequest.to(new Receiver<FieldGroupProxy>() { @Override public void onSuccess(FieldGroupProxy entity) { viewFieldGroup(entity) ;  }  } ).fire() ;  }  * Display the current instance of FieldGroup in editor * @param entity the FieldGroupProxy to be displayed private void viewFieldGroup(FieldGroupProxy entity) { setTitle(AdminNLS.constants().fieldGroup_name() + ": " + renderer.getDisplayValue(entity.getEntity()) + " / " + renderer.getDisplayValue(entity)) ;  setMetaData((ImogBeanProxy) entity) ;  request = requestFactory.fieldGroupRequest() ;  current = request.edit(entity) ;  editor.setEditedValue(current) ;  editor.setRequestContextForListEditors(request) ;  editorDriver.edit(current, request) ;  editor.setEdited(false) ;  editor.computeVisibility(null, true) ;  }  * Edit the current instance of FieldGroup in editor @Override protected void edit() { editor.setEdited(true) ;  }  * Initialize the editor driver private void createDriver() { if (editorDriver == null) { editorDriver = GWT.create(Driver.class) ;  editorDriver.initialize(requestFactory, editor) ;  }  }  * Persist the current instance of FieldGroup @Override protected void save() { editor.validateFields() ;  editorDriver.flush() ;  // Check for errors on the client side if (editorDriver.hasErrors()) { // Window.alert("FieldGroup form not validated locally") ;  return ;  }  Request<Void> saveRequest = request.save(current, isNew) ;  saveRequest.to(new Receiver<Void>() { @Override public void onSuccess(Void response) { requestFactory.getEventBus().fireEvent(new SaveFieldGroupEvent(current, initField)) ;  closeForm() ;  }  @Override public void onConstraintViolation(Set<ConstraintViolation<?>> errors) { // Window.alert("FieldGroup form not validated on server") ;  // TODO manage errors on client side when made available by GWT if (errors != null && errors.size() > 0) { // convert ConstraintViolation to get localized messages AdminRenderer renderer = AdminRenderer.get() ;  Set<ConstraintViolation<?>> imogErrors = new HashSet<ConstraintViolation<?>>() ;  for (ConstraintViolation<?> error : errors) { ImogConstraintViolation violation = new ImogConstraintViolation() ;  violation.setLeafBean((BaseProxy) error.getLeafBean()) ;  violation.setPropertyPath(error.getPropertyPath()) ;  violation.setRootBean((BaseProxy) error.getRootBean()) ;  violation.setMessage(renderer.getI18nErrorMessage(error.getMessage())) ;  imogErrors.add(violation) ;  }  editorDriver.setConstraintViolations(imogErrors) ;  }  }  @Override public void onFailure(ServerFailure error) { Window.alert("Error updating the FieldGroup") ;  super.onFailure(error) ;  }  } ) ;  request.fire() ;  }  @Override protected void cancel() { if (parent != null) parent.hide() ;  else { if (isNew) requestFactory.getEventBus().fireEvent(closeEvent) ;  else requestFactory.getEventBus().fireEvent(new ViewFieldGroupEvent(current.getId(), closeEvent)) ;  }  }  @Override protected void returnToList() { requestFactory.getEventBus().fireEvent(new ListFieldGroupEvent()) ;  }  * Setter to inject a CardEntity value * @param value the value to be injected * @param isLocked true if relation field shall be locked (non editable) public void setEntity(CardEntityProxy value, boolean isLocked) { editor.setEntity(value, isLocked) ;  } }