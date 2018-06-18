package org.imogene.admin.client.ui.editor ; public class NotificationEditor extends Composite implements Editor<NotificationProxy> { interface Binder extends UiBinder<Widget, NotificationEditor> { }  private static final Binder BINDER = GWT.create(Binder.class) ;  protected final AdminRequestFactory requestFactory ;  private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>() ;  private FormTypeUtil formTypeUtil ;  private ImogBeanRenderer renderer ;  @UiField @Ignore FieldGroupPanel descriptionSection ;  @UiField ImogTextBox name ;  @UiField ImogSingleEnumBox method ;  @UiField ImogSingleEnumBox formType ;  @UiField ImogSingleEnumBox operation ;  @UiField ImogTextAreaBox title ;  @UiField ImogTextAreaBox message ;  @UiField @Ignore FieldGroupPanel recipientsSection ;  @UiField(provided = true) ImogMultiRelationBox<ImogActorProxy> actorRecipients ;  * Constructor * @param factory the application request factory * @param hideButtons true if the relation field buttons shall be hidden public NotificationEditor(AdminRequestFactory factory, boolean hideButtons, FormTypeUtil formTypeUtil, ImogBeanRenderer renderer) { this.requestFactory = factory ;  // this.hideButtons = hideButtons ;  this.formTypeUtil = formTypeUtil ;  this.renderer = renderer ;  setRelationFields() ;  initWidget(BINDER.createAndBindUi(this)) ;  properties() ;  }  * Constructor * @param factory the application request factory public NotificationEditor(AdminRequestFactory factory, FormTypeUtil formTypeUtil, ImogBeanRenderer renderer) { this(factory, false, formTypeUtil, renderer) ;  }  * Sets the properties of the fields public void properties() { descriptionSection.setGroupTitle(AdminNLS.constants().notification_group_description()) ;  name.setLabel(AdminNLS.constants().notification_field_name()) ;  method.setLabel(AdminNLS.constants().notification_field_method()) ;  method.addItem(NotificationConstants.EMAIL_NOTIF, AdminNLS.constants().notification_method_mail_option()) ;  method.addItem(NotificationConstants.SMS_NOTIF, AdminNLS.constants().notification_method_sMS_option()) ;  formType.setLabel(AdminNLS.constants().notification_field_formType()) ;  List<FormType> formTypes = formTypeUtil.getFormTypes() ;  for (FormType type : formTypes) formType.addItem(type.getValue(), type.getDisplayValue()) ;  operation.setLabel(AdminNLS.constants().notification_field_operation()) ;  operation.addItem(NotificationConstants.CREATE_OP, AdminNLS.constants().notification_operation_create_option()) ;  operation.addItem(NotificationConstants.UPDATE_OP, AdminNLS.constants().notification_operation_update_option()) ;  operation.addItem(NotificationConstants.DELETE_OP, AdminNLS.constants().notification_operation_delete_option()) ;  title.setLabel(AdminNLS.constants().notification_field_title()) ;  message.setLabel(AdminNLS.constants().notification_field_message()) ;  recipientsSection.setGroupTitle(AdminNLS.constants().notification_group_recipients()) ;  actorRecipients.setLabel(AdminNLS.constants().notification_field_actorRecipients()) ;  }  * Configures the widgets that manage relation fields public void setRelationFields() { ImogActorDataProvider actorRecipientsDataProvider ;  actorRecipientsDataProvider = new ImogActorDataProvider(requestFactory) ;  // if (hideButtons) // in popup, relation buttons hidden actorRecipients = new ImogMultiRelationBox<ImogActorProxy>(actorRecipientsDataProvider, renderer, true) ;  // else {// in wrapper panel, relation buttons enabled // actorRecipients = new ImogMultiRelationBox<ImogActorProxy>( // false, actorRecipientsDataProvider, // AdminRenderer.get(), null) ;  // }  actorRecipients.setPopUpTitle(AdminNLS.constants().imogActor_select_title()) ;  // if (hideButtons) // in popup, relation buttons hidden // else {// in wrapper panel, relation buttons enabled // roleRecipients = new ImogMultiRelationBox<ImogRoleProxy>(false, // roleRecipientsDataProvider, AdminRenderer.get(), null) ;  // }  // roleRecipients.setFilterPanel(new ImogRoleFilterPanel()) ;  }  * Sets the edition mode * @param isEdited true to enable the edition of the form public void setEdited(boolean isEdited) { if (isEdited) setFieldEditAccess() ;  else setFieldReadAccess() ;  name.setEdited(isEdited) ;  method.setEdited(isEdited) ;  formType.setEdited(isEdited) ;  operation.setEdited(isEdited) ;  title.setEdited(isEdited) ;  message.setEdited(isEdited) ;  actorRecipients.setEdited(isEdited) ;  }  * Configures the visibility of the fields in view mode depending on the user privileges public void setFieldReadAccess() { if (!ProfileUtil.isAdmin()) descriptionSection.setVisible(false) ;  if (!ProfileUtil.isAdmin()) recipientsSection.setVisible(false) ;  }  * Configures the visibility of the fields in edit mode depending on the user privileges public void setFieldEditAccess() { if (!ProfileUtil.isAdmin()) descriptionSection.setVisible(false) ;  if (!ProfileUtil.isAdmin()) recipientsSection.setVisible(false) ;  }  * Manages editor updates when a field value changes private void setFieldValueChangeHandler() { registrations.add(requestFactory.getEventBus().addHandler(FieldValueChangeEvent.TYPE, new FieldValueChangeEvent.Handler() { @Override public void onValueChange(ImogField<?> field) { // field dependent visibility management computeVisibility(field, false) ;  }  } )) ;  }  * Computes the field visibility public void computeVisibility(ImogField<?> source, boolean allValidation) { }  * Configures the handlers of the widgets that manage relation fields private void setRelationHandlers() { // actorRecipients.setViewClickHandler(new ClickHandler() { // @Override // public void onClick(ClickEvent event) { // // if (!actorRecipients.isEmpty() // && actorRecipients.getSelectedEntities().size() > 0) { // // ImogActorProxy value = actorRecipients // .getSelectedEntities().get(0) ;  // RelationPopupPanel relationPopup = new RelationPopupPanel() ;  // AppliUserFormPanel form = new AppliUserFormPanel( // requestFactory, value.getId(), relationPopup, // "actorRecipients", false) ;  // relationPopup.addWidget(form) ;  // relationPopup.show() ;  // }  // }  // } ) ;  // roleRecipients.setViewClickHandler(new ClickHandler() { // @Override // public void onClick(ClickEvent event) { // // if (!roleRecipients.isEmpty() // && roleRecipients.getSelectedEntities().size() > 0) { // // ImogRoleProxy value = roleRecipients.getSelectedEntities() // .get(0) ;  // RelationPopupPanel relationPopup = new RelationPopupPanel() ;  // ImogRoleFormPanel form = new ImogRoleFormPanel( // requestFactory, value.getId(), relationPopup, // "roleRecipients", false) ;  // relationPopup.addWidget(form) ;  // relationPopup.show() ;  // }  // }  // } ) ;  }  @Override protected void onUnload() { for (HandlerRegistration r : registrations) r.removeHandler() ;  registrations.clear() ;  super.onUnload() ;  }  @Override protected void onLoad() { setRelationHandlers() ;  setFieldValueChangeHandler() ;  super.onLoad() ;  } }