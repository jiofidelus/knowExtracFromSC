package org.imogene.epicam.client.ui.table ; public class ExamenSerologieDynaTable extends ImogDynaTable<ExamenSerologieProxy> { private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>() ;  private PushButton deleteButton ;  public ExamenSerologieDynaTable(EpicamRequestFactory requestFactory, ImogBeanDataProvider<ExamenSerologieProxy> provider, boolean checkBoxesVisible) { super(requestFactory, provider, checkBoxesVisible) ;  }  public ImogFilterPanel getFilterPanel() { ImogFilterPanel filterPanel = new ExamenSerologieFilterPanel() ;  super.configureFilterPanel(filterPanel) ;  return filterPanel ;  }  *  @Override protected void setColumns() { if (AccessManager.canReadExamenSerologieDescription()) { Column<ExamenSerologieProxy, String> patientColumn = new PatientColumn() ;  patientColumn.setSortable(true) ;  table.addColumn(patientColumn, NLS.constants() .examenSerologie_field_s_patient()) ;  }  if (AccessManager.canReadExamenSerologieDescription()) { Column<ExamenSerologieProxy, String> dateTestColumn = new DateTestColumn() ;  dateTestColumn.setSortable(true) ;  table.addColumn(dateTestColumn, NLS.constants() .examenSerologie_field_s_dateTest()) ;  }  if (AccessManager.canReadExamenSerologieDescription()) { Column<ExamenSerologieProxy, String> natureColumn = new NatureColumn() ;  natureColumn.setSortable(true) ;  table.addColumn(natureColumn, NLS.constants() .examenSerologie_field_s_nature()) ;  }  if (AccessManager.canReadExamenSerologieDescription()) { Column<ExamenSerologieProxy, String> resultatCD4Column = new ResultatCD4Column() ;  resultatCD4Column.setSortable(true) ;  table.addColumn(resultatCD4Column, NLS.constants() .examenSerologie_field_s_resultatCD4()) ;  }  if (AccessManager.canReadExamenSerologieDescription()) { Column<ExamenSerologieProxy, String> resultatVIHColumn = new ResultatVIHColumn() ;  resultatVIHColumn.setSortable(true) ;  table.addColumn(resultatVIHColumn, NLS.constants() .examenSerologie_field_s_resultatVIH()) ;  }  }  @Override protected GwtEvent<?> getViewEvent(ExamenSerologieProxy value) { History.newItem( TokenHelper.TK_VIEW + "/examenserologie/" + value.getId(), true) ;  return null ;  }  @Override protected String getDefaultSortProperty() { return "nature" ;  }  @Override protected boolean getDefaultSortPropertyOrder() { return true ;  }  * Creates the Create action command for the entity * @return the create command public Command getCreateCommand() { if (AccessManager.canCreateExamenSerologie() && AccessManager.canEditExamenSerologie()) { Command command = new Command() { public void execute() { History.newItem(TokenHelper.TK_NEW + "/examenserologie/", true) ;  }  }  ;  return command ;  }  else return null ;  }  * Creates the Delete action command for the entity * @return the delete command public PushButton getDeleteButton() { if (AccessManager.canDeleteExamenSerologie() && AccessManager.canEditExamenSerologie()) { deleteButton = new PushButton(BaseNLS.constants().button_delete()) ;  deleteButton.setStyleName(imogResources.imogStyle().imogButton()) ;  deleteButton.addStyleName("Dynatable-Button") ;  deleteButton.setVisible(false) ;  return deleteButton ;  }  return null ;  }  * Creates the Handlers linked to the delete button private void setDeleteButtonHandlers() { if (AccessManager.canDeleteExamenSerologie() && AccessManager.canEditExamenSerologie()) { // Click handler registrations.add(deleteButton.addClickHandler(new ClickHandler() { @Override public void onClick(ClickEvent event) { Set<ExamenSerologieProxy> selectedEntities = selectionModel .getSelectedSet() ;  int count = selectedEntities.size() ;  if (count > 0) { EpicamRenderer renderer = EpicamRenderer.get() ;  StringBuffer msg = new StringBuffer() ;  msg.append(BaseNLS.constants() .confirmation_delete_several1() + " " + NLS.constants().examenSerologie_name() + " " + BaseNLS.constants() .confirmation_delete_several2() + ": ") ;  int i = 0 ;  for (ExamenSerologieProxy entity : selectedEntities) { if (count == 1 || i == count - 1) msg.append("'" + renderer.getDisplayValue(entity) + "' ?") ;  else msg.append("'" + renderer.getDisplayValue(entity) + "', ") ;  i = i + 1 ;  }  boolean toDelete = Window.confirm(msg.toString()) ;  if (toDelete) { Request<Void> deleteRequest = getExamenSerologieRequest() .delete(selectedEntities) ;  deleteRequest.fire(new Receiver<Void>() { @Override public void onSuccess(Void response) { //Window.alert("The selected ExamenSerologie entries have been deleted") ;  requestFactory.getEventBus().fireEvent( new ListExamenSerologieEvent()) ;  }  @Override public void onFailure(ServerFailure error) { Window.alert("Error deleting the ExamenSerologie entries") ;  super.onFailure(error) ;  }  } ) ;  }  }  }  } )) ;  // Selection changed handler  registrations.add(requestFactory.getEventBus().addHandler( SelectionChangedInTableEvent.TYPE, new SelectionChangedInTableEvent.Handler() { @Override public void noticeSelectionChange(int selectedItems) { if (selectedItems > 0) deleteButton.setVisible(true) ;  else deleteButton.setVisible(false) ;  }  } )) ;  }  }  * Creates the action command that enables to export the ExamenSerologie * entries in a csv file * @return the command public Command getCsvExportButton() { if (AccessManager.canExportExamenSerologie()) { Command command = new Command() { public void execute() { String url = GWT.getHostPageBaseURL() + EpicamBirtConstants.EXAM_SER_CSV_KEY + "?" + EpicamBirtConstants.REPORT_NAME + "=examenSerologie_csv" + "&" + EpicamBirtConstants.REPORT_LOCALE + "=" + NLS.constants().locale() + "&" + EpicamBirtConstants.REPORT_FORMAT + "=" + EpicamBirtConstants.CSV ;  if (beanDataProvider.getSearchCriterions() != null) url = url + getDataProviderCriteria() ;  Window.open(url, "_blank", "") ;  }  }  ;  return command ;  }  else return null ;  }  private ExamenSerologieRequest getExamenSerologieRequest() { EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory ;  return epicamRequestFactory.examenSerologieRequest() ;  }  @Override protected void onUnload() { for (HandlerRegistration r : registrations) r.removeHandler() ;  registrations.clear() ;  super.onUnload() ;  }  @Override protected void onLoad() { setDeleteButtonHandlers() ;  super.onLoad() ;  }  * --------------------- * Internal classes * ---------------------- * Column for field Patient * @author MEDES-IMPS private class PatientColumn extends ImogColumn<ExamenSerologieProxy, String> { private EpicamRenderer renderer = EpicamRenderer.get() ;  public PatientColumn() { super(new TextCell()) ;  }  @Override public String getValue(ExamenSerologieProxy object) { String value = null ;  if (object != null) { if (object.getPatient() == null) value = "" ;  else value = renderer.getDisplayValue(object.getPatient()) ;  }  return value ;  }  public String getPropertyName() { return "patient" ;  }  }  * Column for field DateTest * @author MEDES-IMPS private class DateTestColumn extends ImogColumn<ExamenSerologieProxy, String> { public DateTestColumn() { super(new TextCell()) ;  }  @Override public String getValue(ExamenSerologieProxy object) { String value = null ;  if (object != null) { if (object.getDateTest() == null) value = "" ;  else value = DateUtil.getFormatedDate(object.getDateTest()) ;  }  return value ;  }  public String getPropertyName() { return "dateTest" ;  }  }  * Column for field Nature * @author MEDES-IMPS private class NatureColumn extends ImogColumn<ExamenSerologieProxy, String> { private EpicamRenderer renderer = EpicamRenderer.get() ;  public NatureColumn() { super(new TextCell()) ;  }  @Override public String getValue(ExamenSerologieProxy object) { String value = null ;  if (object != null) { if (object.getNature() == null) value = "" ;  else value = renderer.getEnumDisplayValue( ExamenSerologieProxy.class, "nature", object.getNature()) ;  }  return value ;  }  public String getPropertyName() { return "nature" ;  }  }  * Column for field ResultatCD4 * @author MEDES-IMPS private class ResultatCD4Column extends ImogColumn<ExamenSerologieProxy, String> { public ResultatCD4Column() { super(new TextCell()) ;  }  @Override public String getValue(ExamenSerologieProxy object) { String value = null ;  if (object != null) { if (object.getResultatCD4() == null) value = "" ;  else value = object.getResultatCD4().toString() ;  }  return value ;  }  public String getPropertyName() { return "resultatCD4" ;  }  }  * Column for field ResultatVIH * @author MEDES-IMPS private class ResultatVIHColumn extends ImogColumn<ExamenSerologieProxy, String> { private EpicamRenderer renderer = EpicamRenderer.get() ;  public ResultatVIHColumn() { super(new TextCell()) ;  }  @Override public String getValue(ExamenSerologieProxy object) { String value = null ;  if (object != null) { if (object.getResultatVIH() == null) value = "" ;  else value = renderer.getEnumDisplayValue( ExamenSerologieProxy.class, "resultatVIH", object.getResultatVIH()) ;  }  return value ;  }  public String getPropertyName() { return "resultatVIH" ;  }  } }