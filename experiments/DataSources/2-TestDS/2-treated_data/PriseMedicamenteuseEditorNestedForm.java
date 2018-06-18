package org.imogene.epicam.client.ui.editor.nested ; public class PriseMedicamenteuseEditorNestedForm extends Composite implements Editor<PriseMedicamenteuseProxy>, HasEditorDelegate<PriseMedicamenteuseProxy>, HasEditorErrors<PriseMedicamenteuseProxy> { interface Binder extends UiBinder<Widget, PriseMedicamenteuseEditorNestedForm> { }  private static final Binder BINDER = GWT.create(Binder.class) ;  protected final EpicamRequestFactory requestFactory ;  private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>() ;  private EditorDelegate<PriseMedicamenteuseProxy> delegate ;  private boolean hideButtons = false ;  private int index = 0 ;  private boolean isNewProxy = false ;  private boolean isClearImageActive = false ;  @UiField Image clearImage ;  @UiField @Ignore FieldGroupPanel priseMedicamenteuseSection ;  @UiField ImogDateBox dateEffective ;  @UiField ImogSingleEnumBox prise ;  @UiField ImogBooleanBox cotrimoxazole ;  * Constructor * @param factory the application request factory * @param hideButtons true if the relation field buttons shall be hidden public PriseMedicamenteuseEditorNestedForm(EpicamRequestFactory factory, boolean hideButtons) { this.requestFactory = factory ;  this.hideButtons = hideButtons ;  initWidget(BINDER.createAndBindUi(this)) ;  clearImage.setTitle(BaseNLS.constants().button_remove()) ;  clearImage .setUrl(GWT.getModuleBaseURL() + "images/relation_remove.png") ;  properties() ;  }  * Constructor * @param factory the application request factory public PriseMedicamenteuseEditorNestedForm(EpicamRequestFactory factory) { this(factory, false) ;  }  * Sets the properties of the fields public void properties() { priseMedicamenteuseSection.setGroupTitle(NLS.constants() .priseMedicamenteuse_name()) ;  priseMedicamenteuseSection.setLabelFontSize("12px") ;  dateEffective.setLabel(NLS.constants() .priseMedicamenteuse_field_dateEffective()) ;  prise.setLabel(NLS.constants().priseMedicamenteuse_field_prise()) ;  prise.addItem( EpicamEnumConstants.PRISEMEDICAMENTEUSE_PRISE_PRISESUPERVISEE, NLS.constants() .priseMedicamenteuse_prise_priseSupervisee_option()) ;  prise.addItem( EpicamEnumConstants.PRISEMEDICAMENTEUSE_PRISE_AUTOMEDICATION, NLS.constants() .priseMedicamenteuse_prise_automedication_option()) ;  prise.addItem(EpicamEnumConstants.PRISEMEDICAMENTEUSE_PRISE_NONVENU, NLS.constants().priseMedicamenteuse_prise_nonVenu_option()) ;  cotrimoxazole.setLabel(NLS.constants() .priseMedicamenteuse_field_cotrimoxazole()) ;  cotrimoxazole.isStrict(true) ;  }  * Sets the edition mode * @param isEdited true to enable the edition of the form public void setEdited(boolean isEdited) { if (isClearImageActive) clearImage.setVisible(isEdited) ;  else clearImage.setVisible(false) ;  if (isEdited) setFieldEditAccess() ;  else setFieldReadAccess() ;  dateEffective.setEdited(isEdited) ;  prise.setEdited(isEdited) ;  cotrimoxazole.setEdited(isEdited) ;  }  * Configures the visibility of the fields  * in view mode depending on the user privileges public void setFieldReadAccess() { if (!AccessManager.canReadPriseMedicamenteuseDescription()) dateEffective.setVisible(false) ;  if (!AccessManager.canReadPriseMedicamenteuseDescription()) prise.setVisible(false) ;  if (!AccessManager.canReadPriseMedicamenteuseDescription()) cotrimoxazole.setVisible(false) ;  }  * Configures the visibility of the fields  * in edit mode depending on the user privileges public void setFieldEditAccess() { if (!AccessManager.canEditPriseMedicamenteuseDescription()) dateEffective.setVisible(false) ;  if (!AccessManager.canEditPriseMedicamenteuseDescription()) prise.setVisible(false) ;  if (!AccessManager.canEditPriseMedicamenteuseDescription()) cotrimoxazole.setVisible(false) ;  }  * Sets the Request Context for the List Editors public void setRequestContextForListEditors(ImogEntityRequest ctx) { }  * Manages editor updates when a field value changes private void setFieldValueChangeHandler() { registrations.add(requestFactory.getEventBus().addHandler( FieldValueChangeEvent.TYPE, new FieldValueChangeEvent.Handler() { @Override public void onValueChange(ImogField<?> field) { // field dependent visibility management computeVisibility(field, false) ;  }  } )) ;  }  * Computes the field visibility public void computeVisibility(ImogField<?> source, boolean allValidation) { }  public void setDeleteClickHandler(ClickHandler handler) { //registrations.add(clearImage.addClickHandler(handler)) ;  clearImage.addClickHandler(handler) ;  isClearImageActive = true ;  }  public void setIndex(int newIndex) { this.index = newIndex ;  }  public int getIndex() { return index ;  }  public boolean isNewProxy() { return isNewProxy ;  }  public void setNewProxy(boolean isNewProxy) { this.isNewProxy = isNewProxy ;  }  * Validate fields values public void validateFields() { // dateEffective is a required field if (dateEffective.getValueWithoutParseException() == null && dateEffective.isValid()) delegate.recordError(BaseNLS.messages().error_required(), null, "dateEffective") ;  // prise is a required field if (prise.getValue() == null) delegate.recordError(BaseNLS.messages().error_required(), null, "prise") ;  // cotrimoxazole is a required field if (cotrimoxazole.getValue() == null) delegate.recordError(BaseNLS.messages().error_required(), null, "cotrimoxazole") ;  }  public void updatepriseMedicamenteuseSectionLabel(String label) { priseMedicamenteuseSection.setGroupTitle(label) ;  }  @Override public void setDelegate(EditorDelegate<PriseMedicamenteuseProxy> delegate) { this.delegate = delegate ;  }  @Override public void showErrors(List<EditorError> errors) { if (errors != null && errors.size() > 0) { List<EditorError> dateEffectiveFieldErrors = new ArrayList<EditorError>() ;  List<EditorError> priseFieldErrors = new ArrayList<EditorError>() ;  List<EditorError> cotrimoxazoleFieldErrors = new ArrayList<EditorError>() ;  for (EditorError error : errors) { Object userData = error.getUserData() ;  if (userData != null && userData instanceof String) { String field = (String) userData ;  if (field.equals("dateEffective")) dateEffectiveFieldErrors.add(error) ;  if (field.equals("prise")) priseFieldErrors.add(error) ;  if (field.equals("cotrimoxazole")) cotrimoxazoleFieldErrors.add(error) ;  }  }  if (dateEffectiveFieldErrors.size() > 0) dateEffective.showErrors(dateEffectiveFieldErrors) ;  if (priseFieldErrors.size() > 0) prise.showErrors(priseFieldErrors) ;  if (cotrimoxazoleFieldErrors.size() > 0) cotrimoxazole.showErrors(cotrimoxazoleFieldErrors) ;  }  }  @Override protected void onUnload() { for (HandlerRegistration r : registrations) r.removeHandler() ;  registrations.clear() ;  super.onUnload() ;  }  @Override protected void onLoad() { setFieldValueChangeHandler() ;  super.onLoad() ;  } }