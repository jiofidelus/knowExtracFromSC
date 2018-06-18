package org.imogene.epicam.client.ui.table ; public class SmsPredefiniDynaTable extends ImogDynaTable<SmsPredefiniProxy> { private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>() ;  private PushButton deleteButton ;  public SmsPredefiniDynaTable(EpicamRequestFactory requestFactory, ImogBeanDataProvider<SmsPredefiniProxy> provider, boolean checkBoxesVisible) { super(requestFactory, provider, checkBoxesVisible) ;  }  public ImogFilterPanel getFilterPanel() { ImogFilterPanel filterPanel = new SmsPredefiniFilterPanel() ;  super.configureFilterPanel(filterPanel) ;  return filterPanel ;  }  *  @Override protected void setColumns() { if (AccessManager.canReadSmsPredefiniDescription()) { Column<SmsPredefiniProxy, String> typeColumn = new TypeColumn() ;  typeColumn.setSortable(true) ;  table.addColumn(typeColumn, NLS.constants() .smsPredefini_field_s_type()) ;  }  if (AccessManager.canReadSmsPredefiniDescription()) { Column<SmsPredefiniProxy, String> objetColumn = new ObjetColumn() ;  objetColumn.setSortable(true) ;  table.addColumn(objetColumn, NLS.constants() .smsPredefini_field_s_objet()) ;  }  if (AccessManager.canReadSmsPredefiniDescription()) { Column<SmsPredefiniProxy, String> messageColumn = new MessageColumn() ;  messageColumn.setSortable(true) ;  table.addColumn(messageColumn, NLS.constants() .smsPredefini_field_s_message()) ;  }  }  @Override protected GwtEvent<?> getViewEvent(SmsPredefiniProxy value) { History.newItem(TokenHelper.TK_VIEW + "/smspredefini/" + value.getId(), true) ;  return null ;  }  @Override protected String getDefaultSortProperty() { return "objet" ;  }  @Override protected boolean getDefaultSortPropertyOrder() { return true ;  }  * Creates the Create action command for the entity * @return the create command public Command getCreateCommand() { if (AccessManager.canCreateSmsPredefini() && AccessManager.canEditSmsPredefini()) { Command command = new Command() { public void execute() { History.newItem(TokenHelper.TK_NEW + "/smspredefini/", true) ;  }  }  ;  return command ;  }  else return null ;  }  * Creates the Delete action command for the entity * @return the delete command public PushButton getDeleteButton() { if (AccessManager.canDeleteSmsPredefini() && AccessManager.canEditSmsPredefini()) { deleteButton = new PushButton(BaseNLS.constants().button_delete()) ;  deleteButton.setStyleName(imogResources.imogStyle().imogButton()) ;  deleteButton.addStyleName("Dynatable-Button") ;  deleteButton.setVisible(false) ;  return deleteButton ;  }  return null ;  }  * Creates the Handlers linked to the delete button private void setDeleteButtonHandlers() { if (AccessManager.canDeleteSmsPredefini() && AccessManager.canEditSmsPredefini()) { // Click handler registrations.add(deleteButton.addClickHandler(new ClickHandler() { @Override public void onClick(ClickEvent event) { Set<SmsPredefiniProxy> selectedEntities = selectionModel .getSelectedSet() ;  int count = selectedEntities.size() ;  if (count > 0) { EpicamRenderer renderer = EpicamRenderer.get() ;  StringBuffer msg = new StringBuffer() ;  msg.append(BaseNLS.constants() .confirmation_delete_several1() + " " + NLS.constants().smsPredefini_name() + " " + BaseNLS.constants() .confirmation_delete_several2() + ": ") ;  int i = 0 ;  for (SmsPredefiniProxy entity : selectedEntities) { if (count == 1 || i == count - 1) msg.append("'" + renderer.getDisplayValue(entity) + "' ?") ;  else msg.append("'" + renderer.getDisplayValue(entity) + "', ") ;  i = i + 1 ;  }  boolean toDelete = Window.confirm(msg.toString()) ;  if (toDelete) { Request<Void> deleteRequest = getSmsPredefiniRequest() .delete(selectedEntities) ;  deleteRequest.fire(new Receiver<Void>() { @Override public void onSuccess(Void response) { //Window.alert("The selected SmsPredefini entries have been deleted") ;  requestFactory.getEventBus().fireEvent( new ListSmsPredefiniEvent()) ;  }  @Override public void onFailure(ServerFailure error) { Window.alert("Error deleting the SmsPredefini entries") ;  super.onFailure(error) ;  }  } ) ;  }  }  }  } )) ;  // Selection changed handler  registrations.add(requestFactory.getEventBus().addHandler( SelectionChangedInTableEvent.TYPE, new SelectionChangedInTableEvent.Handler() { @Override public void noticeSelectionChange(int selectedItems) { if (selectedItems > 0) deleteButton.setVisible(true) ;  else deleteButton.setVisible(false) ;  }  } )) ;  }  }  * Creates the action command that enables to export the SmsPredefini * entries in a csv file * @return the command public Command getCsvExportButton() { if (AccessManager.canExportSmsPredefini()) { Command command = new Command() { public void execute() { String url = GWT.getHostPageBaseURL() + EpicamBirtConstants.SMS_CSV_KEY + "?" + EpicamBirtConstants.REPORT_NAME + "=smsPredefini_csv" + "&" + EpicamBirtConstants.REPORT_LOCALE + "=" + NLS.constants().locale() + "&" + EpicamBirtConstants.REPORT_FORMAT + "=" + EpicamBirtConstants.CSV ;  if (beanDataProvider.getSearchCriterions() != null) url = url + getDataProviderCriteria() ;  Window.open(url, "_blank", "") ;  }  }  ;  return command ;  }  else return null ;  }  private SmsPredefiniRequest getSmsPredefiniRequest() { EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory ;  return epicamRequestFactory.smsPredefiniRequest() ;  }  @Override protected void onUnload() { for (HandlerRegistration r : registrations) r.removeHandler() ;  registrations.clear() ;  super.onUnload() ;  }  @Override protected void onLoad() { setDeleteButtonHandlers() ;  super.onLoad() ;  }  * --------------------- * Internal classes * ---------------------- * Column for field Type * @author MEDES-IMPS private class TypeColumn extends ImogColumn<SmsPredefiniProxy, String> { private EpicamRenderer renderer = EpicamRenderer.get() ;  public TypeColumn() { super(new TextCell()) ;  }  @Override public String getValue(SmsPredefiniProxy object) { String value = null ;  if (object != null) { if (object.getType() == null) value = "" ;  else value = renderer.getEnumDisplayValue( SmsPredefiniProxy.class, "type", object.getType()) ;  }  return value ;  }  public String getPropertyName() { return "type" ;  }  }  * Column for field Objet * @author MEDES-IMPS private class ObjetColumn extends ImogColumn<SmsPredefiniProxy, String> { private EpicamRenderer renderer = EpicamRenderer.get() ;  public ObjetColumn() { super(new TextCell()) ;  }  @Override public String getValue(SmsPredefiniProxy object) { String value = null ;  if (object != null) { if (object.getObjet() == null) value = "" ;  else value = renderer.getLocalizedText(object.getObjet()) ;  }  return value ;  }  public String getPropertyName() { String locale = NLS.constants().locale() ;  if (locale.equals("fr")) return "objet.francais" ;  if (locale.equals("en")) return "objet.english" ;  return "objet" ;  }  }  * Column for field Message * @author MEDES-IMPS private class MessageColumn extends ImogColumn<SmsPredefiniProxy, String> { private EpicamRenderer renderer = EpicamRenderer.get() ;  public MessageColumn() { super(new TextCell()) ;  }  @Override public String getValue(SmsPredefiniProxy object) { String value = null ;  if (object != null) { if (object.getMessage() == null) value = "" ;  else value = renderer.getLocalizedText(object.getMessage()) ;  }  return value ;  }  public String getPropertyName() { String locale = NLS.constants().locale() ;  if (locale.equals("fr")) return "message.francais" ;  if (locale.equals("en")) return "message.english" ;  return "message" ;  }  } }