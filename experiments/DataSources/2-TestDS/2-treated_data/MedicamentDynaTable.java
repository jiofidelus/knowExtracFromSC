package org.imogene.epicam.client.ui.table ; public class MedicamentDynaTable extends ImogDynaTable<MedicamentProxy> { private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>() ;  private PushButton deleteButton ;  public MedicamentDynaTable(EpicamRequestFactory requestFactory, ImogBeanDataProvider<MedicamentProxy> provider, boolean checkBoxesVisible) { super(requestFactory, provider, checkBoxesVisible) ;  }  public ImogFilterPanel getFilterPanel() { ImogFilterPanel filterPanel = new MedicamentFilterPanel() ;  super.configureFilterPanel(filterPanel) ;  return filterPanel ;  }  *  @Override protected void setColumns() { if (AccessManager.canReadMedicamentDescription()) { Column<MedicamentProxy, String> codeColumn = new CodeColumn() ;  codeColumn.setSortable(true) ;  table.addColumn(codeColumn, NLS.constants() .medicament_field_s_code()) ;  }  if (AccessManager.canReadMedicamentDescription()) { Column<MedicamentProxy, String> designationColumn = new DesignationColumn() ;  designationColumn.setSortable(true) ;  table.addColumn(designationColumn, NLS.constants() .medicament_field_s_designation()) ;  }  if (AccessManager.canReadMedicamentDescription()) { Column<MedicamentProxy, String> estMedicamentAntituberculeuxColumn = new EstMedicamentAntituberculeuxColumn() ;  estMedicamentAntituberculeuxColumn.setSortable(true) ;  table.addColumn(estMedicamentAntituberculeuxColumn, NLS.constants() .medicament_field_s_estMedicamentAntituberculeux()) ;  }  }  @Override protected GwtEvent<?> getViewEvent(MedicamentProxy value) { History.newItem(TokenHelper.TK_VIEW + "/medicament/" + value.getId(), true) ;  return null ;  }  @Override protected String getDefaultSortProperty() { return "designation" ;  }  @Override protected boolean getDefaultSortPropertyOrder() { return true ;  }  * Creates the Create action command for the entity * @return the create command public Command getCreateCommand() { if (AccessManager.canCreateMedicament() && AccessManager.canEditMedicament()) { Command command = new Command() { public void execute() { History.newItem(TokenHelper.TK_NEW + "/medicament/", true) ;  }  }  ;  return command ;  }  else return null ;  }  * Creates the Delete action command for the entity * @return the delete command public PushButton getDeleteButton() { if (AccessManager.canDeleteMedicament() && AccessManager.canEditMedicament()) { deleteButton = new PushButton(BaseNLS.constants().button_delete()) ;  deleteButton.setStyleName(imogResources.imogStyle().imogButton()) ;  deleteButton.addStyleName("Dynatable-Button") ;  deleteButton.setVisible(false) ;  return deleteButton ;  }  return null ;  }  * Creates the Handlers linked to the delete button private void setDeleteButtonHandlers() { if (AccessManager.canDeleteMedicament() && AccessManager.canEditMedicament()) { // Click handler registrations.add(deleteButton.addClickHandler(new ClickHandler() { @Override public void onClick(ClickEvent event) { Set<MedicamentProxy> selectedEntities = selectionModel .getSelectedSet() ;  int count = selectedEntities.size() ;  if (count > 0) { EpicamRenderer renderer = EpicamRenderer.get() ;  StringBuffer msg = new StringBuffer() ;  msg.append(BaseNLS.constants() .confirmation_delete_several1() + " " + NLS.constants().medicament_name() + " " + BaseNLS.constants() .confirmation_delete_several2() + ": ") ;  int i = 0 ;  for (MedicamentProxy entity : selectedEntities) { if (count == 1 || i == count - 1) msg.append("'" + renderer.getDisplayValue(entity) + "' ?") ;  else msg.append("'" + renderer.getDisplayValue(entity) + "', ") ;  i = i + 1 ;  }  boolean toDelete = Window.confirm(msg.toString()) ;  if (toDelete) { Request<Void> deleteRequest = getMedicamentRequest() .delete(selectedEntities) ;  deleteRequest.fire(new Receiver<Void>() { @Override public void onSuccess(Void response) { //Window.alert("The selected Medicament entries have been deleted") ;  requestFactory.getEventBus().fireEvent( new ListMedicamentEvent()) ;  }  @Override public void onFailure(ServerFailure error) { Window.alert("Error deleting the Medicament entries") ;  super.onFailure(error) ;  }  } ) ;  }  }  }  } )) ;  // Selection changed handler  registrations.add(requestFactory.getEventBus().addHandler( SelectionChangedInTableEvent.TYPE, new SelectionChangedInTableEvent.Handler() { @Override public void noticeSelectionChange(int selectedItems) { if (selectedItems > 0) deleteButton.setVisible(true) ;  else deleteButton.setVisible(false) ;  }  } )) ;  }  }  * Creates the action command that enables to export the Medicament * entries in a csv file * @return the command public Command getCsvExportButton() { if (AccessManager.canExportMedicament()) { Command command = new Command() { public void execute() { String url = GWT.getHostPageBaseURL() + EpicamBirtConstants.MED_CSV_KEY + "?" + EpicamBirtConstants.REPORT_NAME + "=medicament_csv" + "&" + EpicamBirtConstants.REPORT_LOCALE + "=" + NLS.constants().locale() + "&" + EpicamBirtConstants.REPORT_FORMAT + "=" + EpicamBirtConstants.CSV ;  if (beanDataProvider.getSearchCriterions() != null) url = url + getDataProviderCriteria() ;  Window.open(url, "_blank", "") ;  }  }  ;  return command ;  }  else return null ;  }  private MedicamentRequest getMedicamentRequest() { EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory ;  return epicamRequestFactory.medicamentRequest() ;  }  @Override protected void onUnload() { for (HandlerRegistration r : registrations) r.removeHandler() ;  registrations.clear() ;  super.onUnload() ;  }  @Override protected void onLoad() { setDeleteButtonHandlers() ;  super.onLoad() ;  }  * --------------------- * Internal classes * ---------------------- * Column for field Code * @author MEDES-IMPS private class CodeColumn extends ImogColumn<MedicamentProxy, String> { public CodeColumn() { super(new TextCell()) ;  }  @Override public String getValue(MedicamentProxy object) { String value = null ;  if (object != null) { if (object.getCode() == null) value = "" ;  else value = object.getCode() ;  }  return value ;  }  public String getPropertyName() { return "code" ;  }  }  * Column for field Designation * @author MEDES-IMPS private class DesignationColumn extends ImogColumn<MedicamentProxy, String> { public DesignationColumn() { super(new TextCell()) ;  }  @Override public String getValue(MedicamentProxy object) { String value = null ;  if (object != null) { if (object.getDesignation() == null) value = "" ;  else value = object.getDesignation() ;  }  return value ;  }  public String getPropertyName() { return "designation" ;  }  }  * Column for field EstMedicamentAntituberculeux * @author MEDES-IMPS private class EstMedicamentAntituberculeuxColumn extends ImogColumn<MedicamentProxy, String> { public EstMedicamentAntituberculeuxColumn() { super(new TextCell()) ;  }  @Override public String getValue(MedicamentProxy object) { String value = null ;  if (object != null) { if (object.getEstMedicamentAntituberculeux() == null) value = "" ;  else value = BooleanUtil.getBooleanDisplayValue(object .getEstMedicamentAntituberculeux()) ;  }  return value ;  }  public String getPropertyName() { return "estMedicamentAntituberculeux" ;  }  } }