package org.imogene.epicam.client.ui.editor ; public class EnvoiSMSEditor extends Composite { interface Binder extends UiBinder<Widget, EnvoiSMSEditor> { }  private static final Binder BINDER = GWT.create(Binder.class) ;  protected final EpicamRequestFactory requestFactory ;  private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>() ;  private boolean hideButtons = false ;  @UiField @Ignore FieldGroupPanel envoiSmsSection ;  @UiField(provided = true) ImogMultiRelationBox<PatientProxy> patient ;  @UiField(provided = true) ImogSingleRelationBox<SmsPredefiniProxy> sms ;  * Constructor *  * @param factory the application request factory * @param hideButtons true if the relation field buttons shall be hidden public EnvoiSMSEditor(EpicamRequestFactory factory, boolean hideButtons) { this.requestFactory = factory ;  this.hideButtons = hideButtons ;  setRelationFields() ;  initWidget(BINDER.createAndBindUi(this)) ;  properties() ;  }  * Constructor *  * @param factory the application request factory public EnvoiSMSEditor(EpicamRequestFactory factory) { this(factory, false) ;  }  * Sets the properties of the fields private void properties() { envoiSmsSection.setGroupTitle(BaseNLS.constants().envoiSMS_group_envoiSMSPatient()) ;  patient.setLabel(BaseNLS.constants().envoiSMS_field_patient()) ;  sms.setLabel(BaseNLS.constants().envoiSMS_field_sms()) ;  }  * Configures the widgets that manage relation fields private void setRelationFields() { PatientDataProvider patientDataProvider ;  patientDataProvider = new PatientDataProvider(requestFactory) ;  if (hideButtons) // in popup, relation buttons hidden patient = new ImogMultiRelationBox<PatientProxy>(patientDataProvider, EpicamRenderer.get(), true) ;  else {// in wrapper panel, relation buttons enabled if (AccessManager.canCreatePatient() && AccessManager.canEditPatient()) patient = new ImogMultiRelationBox<PatientProxy>(patientDataProvider, EpicamRenderer.get(), null) ;  else patient = new ImogMultiRelationBox<PatientProxy>(false, patientDataProvider, EpicamRenderer.get(), null) ;  }  patient.setPopUpTitle(NLS.constants().patient_select_title()) ;  patient.setFilterPanel(new PatientFilterPanel()) ;  SmsPredefiniDataProvider smsDataProvider ;  smsDataProvider = new SmsPredefiniDataProvider(requestFactory) ;  if (hideButtons) // in popup, relation buttons hidden sms = new ImogSingleRelationBox<SmsPredefiniProxy>(smsDataProvider, EpicamRenderer.get(), true) ;  else {// in wrapper panel, relation buttons enabled if (AccessManager.canCreateSmsPredefini() && AccessManager.canEditSmsPredefini()) sms = new ImogSingleRelationBox<SmsPredefiniProxy>(smsDataProvider, EpicamRenderer.get()) ;  else sms = new ImogSingleRelationBox<SmsPredefiniProxy>(false, smsDataProvider, EpicamRenderer.get()) ;  }  }  * Sets the edition mode *  * @param isEdited true to enable the edition of the form public void setEdited(boolean isEdited) { if (isEdited) setFieldEditAccess() ;  else setFieldReadAccess() ;  patient.setEdited(isEdited) ;  sms.setEdited(isEdited) ;  }  * Configures the visibility of the fields in view mode depending on the user privileges private void setFieldReadAccess() { }  * Configures the visibility of the fields in edit mode depending on the user privileges private void setFieldEditAccess() { }  * Sets the Request Context for the List Editors. *  * IMPORTANT: We are passing an ImogEntityRequest rather than a specific entity request because this editor can be * used in embedded forms where it may be difficult to now in advance with request will be used. public void setRequestContextForListEditors(ImogEntityRequest ctx) { }  * Manages editor updates when a field value changes private void setFieldValueChangeHandler() { registrations.add(requestFactory.getEventBus().addHandler(FieldValueChangeEvent.TYPE, new FieldValueChangeEvent.Handler() { @Override public void onValueChange(ImogField<?> field) { // field dependent visibility management computeVisibility(field, false) ;  }  } )) ;  }  * Computes the field visibility public void computeVisibility(ImogField<?> source, boolean allValidation) { }  private void clearPatientWidget() { patient.emptyList() ;  }  * Setter to inject a SmsPredefini value *  * @param value the value to be injected into the editor * @param isLocked true if relation field shall be locked (non editable) public void setSms(SmsPredefiniProxy value, boolean isLocked) { sms.setLocked(isLocked) ;  sms.setValue(value) ;  }  private void clearSmsWidget() { sms.clear() ;  }  * Configures the handlers of the widgets that manage relation fields private void setRelationHandlers() { patient.setViewClickHandler(new ClickHandler() { @Override public void onClick(ClickEvent event) { if (!patient.isEmpty() && patient.getSelectedEntities().size() > 0) { PatientProxy value = patient.getSelectedEntities().get(0) ;  RelationPopupPanel relationPopup = new RelationPopupPanel() ;  PatientFormPanel form = new PatientFormPanel(requestFactory, value.getId(), relationPopup, "patient") ;  relationPopup.addWidget(form) ;  relationPopup.show() ;  }  }  } ) ;  patient.setAddClickHandler(new ClickHandler() { @Override public void onClick(ClickEvent event) { RelationPopupPanel relationPopup = new RelationPopupPanel() ;  PatientFormPanel form = new PatientFormPanel(requestFactory, null, relationPopup, "patient") ;  relationPopup.addWidget(form) ;  relationPopup.show() ;  }  } ) ;  registrations.add(requestFactory.getEventBus().addHandler(SavePatientEvent.TYPE, new SavePatientEvent.Handler() { @Override public void savePatient(PatientProxy value) { if (!patient.isPresent(value)) patient.addValue(value) ;  }  public void savePatient(PatientProxy value, String initField) { if (initField.equals("patient")) { if (patient.isPresent(value)) patient.replaceValue(value) ;  else patient.addValue(value, true) ;  }  }  } )) ;  sms.setViewClickHandler(new ClickHandler() { @Override public void onClick(ClickEvent event) { if (sms.getValue() != null) { RelationPopupPanel relationPopup = new RelationPopupPanel() ;  SmsPredefiniFormPanel form = new SmsPredefiniFormPanel(requestFactory, sms.getValue().getId(), relationPopup, "sms") ;  relationPopup.addWidget(form) ;  relationPopup.show() ;  }  }  } ) ;  sms.setAddClickHandler(new ClickHandler() { @Override public void onClick(ClickEvent event) { RelationPopupPanel relationPopup = new RelationPopupPanel() ;  SmsPredefiniFormPanel form = new SmsPredefiniFormPanel(requestFactory, null, relationPopup, "sms") ;  relationPopup.addWidget(form) ;  relationPopup.show() ;  }  } ) ;  registrations.add(requestFactory.getEventBus().addHandler(SaveSmsPredefiniEvent.TYPE, new SaveSmsPredefiniEvent.Handler() { @Override public void saveSmsPredefini(SmsPredefiniProxy value) { sms.setValue(value) ;  }  @Override public void saveSmsPredefini(SmsPredefiniProxy value, String initField) { if (initField.equals("sms")) sms.setValue(value, true) ;  }  } )) ;  }  * Validate fields values public void validateFields() { }  private void setAllLabelWith(String width) { patient.setLabelWidth(width) ;  sms.setLabelWidth(width) ;  }  private void setAllBoxWith(int width) { patient.setBoxWidth(width) ;  sms.setBoxWidth(width) ;  }  @Override protected void onUnload() { for (HandlerRegistration r : registrations) r.removeHandler() ;  registrations.clear() ;  super.onUnload() ;  }  @Override protected void onLoad() { setRelationHandlers() ;  setFieldValueChangeHandler() ;  super.onLoad() ;  }  public List<PatientProxy> getPatientList() { return patient.getValue() ;  }  public SmsPredefiniProxy getSms() { return sms.getValue() ;  } }