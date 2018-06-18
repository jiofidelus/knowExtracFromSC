package org.imogene.epicam.client.ui.table ; public class RegionDynaTable extends ImogDynaTable<RegionProxy> { private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>() ;  private PushButton deleteButton ;  public RegionDynaTable(EpicamRequestFactory requestFactory, ImogBeanDataProvider<RegionProxy> provider, boolean checkBoxesVisible) { super(requestFactory, provider, checkBoxesVisible) ;  }  public ImogFilterPanel getFilterPanel() { ImogFilterPanel filterPanel = new RegionFilterPanel() ;  super.configureFilterPanel(filterPanel) ;  return filterPanel ;  }  *  @Override protected void setColumns() { if (AccessManager.canReadRegionDescription()) { Column<RegionProxy, String> codeColumn = new CodeColumn() ;  codeColumn.setSortable(true) ;  table.addColumn(codeColumn, NLS.constants().region_field_s_code()) ;  }  if (AccessManager.canReadRegionDescription()) { Column<RegionProxy, String> nomColumn = new NomColumn() ;  nomColumn.setSortable(true) ;  table.addColumn(nomColumn, NLS.constants().region_field_s_nom()) ;  }  }  @Override protected GwtEvent<?> getViewEvent(RegionProxy value) { History.newItem(TokenHelper.TK_VIEW + "/region/" + value.getId(), true) ;  return null ;  }  @Override protected String getDefaultSortProperty() { return "nom" ;  }  @Override protected boolean getDefaultSortPropertyOrder() { return true ;  }  * Creates the Create action command for the entity * @return the create command public Command getCreateCommand() { if (AccessManager.canCreateRegion() && AccessManager.canEditRegion()) { Command command = new Command() { public void execute() { History.newItem(TokenHelper.TK_NEW + "/region/", true) ;  }  }  ;  return command ;  }  else return null ;  }  * Creates the Delete action command for the entity * @return the delete command public PushButton getDeleteButton() { if (AccessManager.canDeleteRegion() && AccessManager.canEditRegion()) { deleteButton = new PushButton(BaseNLS.constants().button_delete()) ;  deleteButton.setStyleName(imogResources.imogStyle().imogButton()) ;  deleteButton.addStyleName("Dynatable-Button") ;  deleteButton.setVisible(false) ;  return deleteButton ;  }  return null ;  }  * Creates the Handlers linked to the delete button private void setDeleteButtonHandlers() { if (AccessManager.canDeleteRegion() && AccessManager.canEditRegion()) { // Click handler registrations.add(deleteButton.addClickHandler(new ClickHandler() { @Override public void onClick(ClickEvent event) { Set<RegionProxy> selectedEntities = selectionModel .getSelectedSet() ;  int count = selectedEntities.size() ;  if (count > 0) { EpicamRenderer renderer = EpicamRenderer.get() ;  StringBuffer msg = new StringBuffer() ;  msg.append(BaseNLS.constants() .confirmation_delete_several1() + " " + NLS.constants().region_name() + " " + BaseNLS.constants() .confirmation_delete_several2() + ": ") ;  int i = 0 ;  for (RegionProxy entity : selectedEntities) { if (count == 1 || i == count - 1) msg.append("'" + renderer.getDisplayValue(entity) + "' ?") ;  else msg.append("'" + renderer.getDisplayValue(entity) + "', ") ;  i = i + 1 ;  }  boolean toDelete = Window.confirm(msg.toString()) ;  if (toDelete) { Request<Void> deleteRequest = getRegionRequest() .delete(selectedEntities) ;  deleteRequest.fire(new Receiver<Void>() { @Override public void onSuccess(Void response) { //Window.alert("The selected Region entries have been deleted") ;  requestFactory.getEventBus().fireEvent( new ListRegionEvent()) ;  }  @Override public void onFailure(ServerFailure error) { Window.alert("Error deleting the Region entries") ;  super.onFailure(error) ;  }  } ) ;  }  }  }  } )) ;  // Selection changed handler  registrations.add(requestFactory.getEventBus().addHandler( SelectionChangedInTableEvent.TYPE, new SelectionChangedInTableEvent.Handler() { @Override public void noticeSelectionChange(int selectedItems) { if (selectedItems > 0) deleteButton.setVisible(true) ;  else deleteButton.setVisible(false) ;  }  } )) ;  }  }  * Creates the action command that enables to export the Region * entries in a csv file * @return the command public Command getCsvExportButton() { if (AccessManager.canExportRegion()) { Command command = new Command() { public void execute() { String url = GWT.getHostPageBaseURL() + EpicamBirtConstants.REG_CSV_KEY + "?" + EpicamBirtConstants.REPORT_NAME + "=region_csv" + "&" + EpicamBirtConstants.REPORT_LOCALE + "=" + NLS.constants().locale() + "&" + EpicamBirtConstants.REPORT_FORMAT + "=" + EpicamBirtConstants.CSV ;  if (beanDataProvider.getSearchCriterions() != null) url = url + getDataProviderCriteria() ;  Window.open(url, "_blank", "") ;  }  }  ;  return command ;  }  else return null ;  }  private RegionRequest getRegionRequest() { EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory ;  return epicamRequestFactory.regionRequest() ;  }  @Override protected void onUnload() { for (HandlerRegistration r : registrations) r.removeHandler() ;  registrations.clear() ;  super.onUnload() ;  }  @Override protected void onLoad() { setDeleteButtonHandlers() ;  super.onLoad() ;  }  * --------------------- * Internal classes * ---------------------- * Column for field Code * @author MEDES-IMPS private class CodeColumn extends ImogColumn<RegionProxy, String> { public CodeColumn() { super(new TextCell()) ;  }  @Override public String getValue(RegionProxy object) { String value = null ;  if (object != null) { if (object.getCode() == null) value = "" ;  else value = object.getCode() ;  }  return value ;  }  public String getPropertyName() { return "code" ;  }  }  * Column for field Nom * @author MEDES-IMPS private class NomColumn extends ImogColumn<RegionProxy, String> { private EpicamRenderer renderer = EpicamRenderer.get() ;  public NomColumn() { super(new TextCell()) ;  }  @Override public String getValue(RegionProxy object) { String value = null ;  if (object != null) { if (object.getNom() == null) value = "" ;  else value = renderer.getLocalizedText(object.getNom()) ;  }  return value ;  }  public String getPropertyName() { String locale = NLS.constants().locale() ;  if (locale.equals("fr")) return "nom.francais" ;  if (locale.equals("en")) return "nom.english" ;  return "nom" ;  }  } }