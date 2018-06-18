package org.imogene.epicam.client.ui.editor.nested ; public class PriseMedicamentRegimeEditorNestedRow extends Composite implements Editor<PriseMedicamentRegimeProxy>, HasEditorDelegate<PriseMedicamentRegimeProxy>, HasEditorErrors<PriseMedicamentRegimeProxy> { interface Binder extends UiBinder<Widget, PriseMedicamentRegimeEditorNestedRow> { }  private static final Binder BINDER = GWT.create(Binder.class) ;  protected final EpicamRequestFactory requestFactory ;  private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>() ;  private EditorDelegate<PriseMedicamentRegimeProxy> delegate ;  private boolean hideButtons = false ;  private int index = 0 ;  private boolean isNewProxy = false ;  @UiField Image clearImage ;  @UiField(provided = true) ImogSingleRelationBox<MedicamentProxy> medicament ;  private MedicamentDataProvider medicamentDataProvider ;  @UiField ImogDoubleBox quantite ;  @UiField ImogTextBox quantiteUnite ;  * Constructor * @param factory the application request factory * @param hideButtons true if the relation field buttons shall be hidden public PriseMedicamentRegimeEditorNestedRow(EpicamRequestFactory factory, boolean hideButtons) { this.requestFactory = factory ;  this.hideButtons = hideButtons ;  setRelationFields() ;  initWidget(BINDER.createAndBindUi(this)) ;  clearImage.setTitle(BaseNLS.constants().button_remove()) ;  clearImage .setUrl(GWT.getModuleBaseURL() + "images/relation_remove.png") ;  properties() ;  }  * Constructor * @param factory the application request factory public PriseMedicamentRegimeEditorNestedRow(EpicamRequestFactory factory) { this(factory, false) ;  }  * Sets the properties of the fields public void properties() { //medicament.setLabel(NLS.constants().priseMedicamentRegime_field_medicament(), HasHorizontalAlignment.ALIGN_RIGHT) ;  medicament.setLabelWidth("0px") ;  //quantite.setLabel(NLS.constants().priseMedicamentRegime_field_quantite(), HasHorizontalAlignment.ALIGN_RIGHT) ;  quantite.setLabelWidth("0px") ;  //quantiteUnite.setLabel(NLS.constants().priseMedicamentRegime_field_quantiteUnite(), HasHorizontalAlignment.ALIGN_RIGHT) ;  quantiteUnite.setLabelWidth("0px") ;  medicament.hideButtonPanel(true) ;  medicament.setBoxWidth(150) ;  quantite.setBoxWidth(100) ;  quantiteUnite.setBoxWidth(100) ;  }  * Configures the widgets that manage relation fields public void setRelationFields() { medicamentDataProvider = new MedicamentDataProvider(requestFactory) ;  if (hideButtons) // in popup, relation buttons hidden medicament = new ImogSingleRelationBox<MedicamentProxy>( medicamentDataProvider, EpicamRenderer.get(), true) ;  else {// in wrapper panel, relation buttons enabled  if (AccessManager.canCreateMedicament() && AccessManager.canEditMedicament()) medicament = new ImogSingleRelationBox<MedicamentProxy>( medicamentDataProvider, EpicamRenderer.get()) ;  else medicament = new ImogSingleRelationBox<MedicamentProxy>(false, medicamentDataProvider, EpicamRenderer.get()) ;  }  }  * Sets the edition mode * @param isEdited true to enable the edition of the form public void setEdited(boolean isEdited) { clearImage.setVisible(isEdited) ;  if (isEdited) setFieldEditAccess() ;  else setFieldReadAccess() ;  medicament.setEdited(isEdited) ;  quantite.setEdited(isEdited) ;  quantiteUnite.setEdited(isEdited) ;  }  * Configures the visibility of the fields  * in view mode depending on the user privileges public void setFieldReadAccess() { if (!AccessManager.canReadPriseMedicamentRegimeDescription()) medicament.setVisible(false) ;  if (!AccessManager.canReadPriseMedicamentRegimeDescription()) quantite.setVisible(false) ;  if (!AccessManager.canReadPriseMedicamentRegimeDescription()) quantiteUnite.setVisible(false) ;  }  * Configures the visibility of the fields  * in edit mode depending on the user privileges public void setFieldEditAccess() { if (!AccessManager.canEditPriseMedicamentRegimeDescription()) medicament.setVisible(false) ;  if (!AccessManager.canEditPriseMedicamentRegimeDescription()) quantite.setVisible(false) ;  if (!AccessManager.canEditPriseMedicamentRegimeDescription()) quantiteUnite.setVisible(false) ;  }  * Manages editor updates when a field value changes private void setFieldValueChangeHandler() { registrations.add(requestFactory.getEventBus().addHandler( FieldValueChangeEvent.TYPE, new FieldValueChangeEvent.Handler() { @Override public void onValueChange(ImogField<?> field) { // field dependent visibility management computeVisibility(field, false) ;  }  } )) ;  }  * Computes the field visibility public void computeVisibility(ImogField<?> source, boolean allValidation) { }  public void setDeleteClickHandler(ClickHandler handler) { //registrations.add(clearImage.addClickHandler(handler)) ;  clearImage.addClickHandler(handler) ;  }  * Setter to inject a Medicament value * @param value the value to be injected into the editor * @param isLocked true if relation field shall be locked (non editable) public void setMedicament(MedicamentProxy value, boolean isLocked) { medicament.setLocked(isLocked) ;  medicament.setValue(value) ;  }  private void clearMedicamentWidget() { medicament.clear() ;  }  * Configures the handlers of the widgets that manage relation fields private void setRelationHandlers() { medicament.setViewClickHandler(new ClickHandler() { @Override public void onClick(ClickEvent event) { if (medicament.getValue() != null) { RelationPopupPanel relationPopup = new RelationPopupPanel() ;  MedicamentFormPanel form = new MedicamentFormPanel( requestFactory, medicament.getValue().getId(), relationPopup, "medicament") ;  relationPopup.addWidget(form) ;  relationPopup.show() ;  }  }  } ) ;  medicament.setAddClickHandler(new ClickHandler() { @Override public void onClick(ClickEvent event) { RelationPopupPanel relationPopup = new RelationPopupPanel() ;  MedicamentFormPanel form = new MedicamentFormPanel( requestFactory, null, relationPopup, "medicament") ;  relationPopup.addWidget(form) ;  relationPopup.show() ;  }  } ) ;  registrations.add(requestFactory.getEventBus().addHandler( SaveMedicamentEvent.TYPE, new SaveMedicamentEvent.Handler() { @Override public void saveMedicament(MedicamentProxy value) { medicament.setValue(value) ;  }  @Override public void saveMedicament(MedicamentProxy value, String initField) { if (initField.equals("medicament")) medicament.setValue(value, true) ;  }  } )) ;  }  public void setIndex(int newIndex) { this.index = newIndex ;  }  public int getIndex() { return index ;  }  public boolean isNewProxy() { return isNewProxy ;  }  public void setNewProxy(boolean isNewProxy) { this.isNewProxy = isNewProxy ;  }  * Validate fields values public void validateFields() { // medicament is a required field if (medicament.getValue() == null) delegate.recordError(BaseNLS.messages().error_required(), null, "medicament") ;  // quantite is a required field if (quantite.getValueWithoutParseException() == null && quantite.isValid()) delegate.recordError(BaseNLS.messages().error_required(), null, "quantite") ;  // quantite shall be superior or equal to '0' if (quantite.getValueWithoutParseException() != null && !(quantite.getValueWithoutParseException() >= 0)) delegate.recordError( BaseNLS.messages() .error_min_num( NLS.constants() .priseMedicamentRegime_field_quantite_min()), null, "quantite") ;  // quantiteUnite is a required field if (quantiteUnite.getValue() == null) delegate.recordError(BaseNLS.messages().error_required(), null, "quantiteUnite") ;  }  @Override public void setDelegate(EditorDelegate<PriseMedicamentRegimeProxy> delegate) { this.delegate = delegate ;  }  @Override public void showErrors(List<EditorError> errors) { if (errors != null && errors.size() > 0) { List<EditorError> medicamentFieldErrors = new ArrayList<EditorError>() ;  List<EditorError> quantiteFieldErrors = new ArrayList<EditorError>() ;  List<EditorError> quantiteUniteFieldErrors = new ArrayList<EditorError>() ;  for (EditorError error : errors) { Object userData = error.getUserData() ;  if (userData != null && userData instanceof String) { String field = (String) userData ;  if (field.equals("medicament")) medicamentFieldErrors.add(error) ;  if (field.equals("quantite")) quantiteFieldErrors.add(error) ;  if (field.equals("quantiteUnite")) quantiteUniteFieldErrors.add(error) ;  }  }  if (medicamentFieldErrors.size() > 0) medicament.showErrors(medicamentFieldErrors) ;  if (quantiteFieldErrors.size() > 0) quantite.showErrors(quantiteFieldErrors) ;  if (quantiteUniteFieldErrors.size() > 0) quantiteUnite.showErrors(quantiteUniteFieldErrors) ;  }  }  @Override protected void onUnload() { for (HandlerRegistration r : registrations) r.removeHandler() ;  registrations.clear() ;  super.onUnload() ;  }  @Override protected void onLoad() { setRelationHandlers() ;  setFieldValueChangeHandler() ;  super.onLoad() ;  } }