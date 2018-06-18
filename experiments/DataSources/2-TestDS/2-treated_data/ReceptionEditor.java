package org.imogene.epicam.client.ui.editor ; public class ReceptionEditor extends Composite implements Editor<ReceptionProxy>, HasEditorDelegate<ReceptionProxy>, HasEditorErrors<ReceptionProxy> { interface Binder extends UiBinder<Widget, ReceptionEditor> { }  private static final Binder BINDER = GWT.create(Binder.class) ;  protected final EpicamRequestFactory requestFactory ;  private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>() ;  private EditorDelegate<ReceptionProxy> delegate ;  private CommonFieldUtil commonFieldUtil = CommonFieldUtil.get() ;  private ReceptionProxy editedValue ;  //Not used by the editor private boolean hideButtons = false ;  @UiField @Ignore FieldGroupPanel descriptionSection ;  @UiField(provided = true) ImogSingleRelationBox<RegionProxy> region ;  private RegionDataProvider regionDataProvider ;  @UiField(provided = true) ImogSingleRelationBox<DistrictSanteProxy> districtSante ;  private DistrictSanteDataProvider districtSanteDataProvider ;  @UiField(provided = true) ImogSingleRelationBox<CentreDiagTraitProxy> CDT ;  private CentreDiagTraitDataProvider cDTDataProvider ;  @UiField(provided = true) ImogSingleRelationBox<CommandeProxy> commande ;  private CommandeDataProvider commandeDataProvider ;  @UiField ImogDateBox dateReception ;  @UiField(provided = true) ReceptionMedicamentsListEditor medicaments ;  private DetailReceptionMedicamentDataProvider medicamentsDataProvider ;  @UiField(provided = true) ReceptionIntrantsListEditor intrants ;  private DetailReceptionIntrantDataProvider intrantsDataProvider ;  * Constructor * @param factory the application request factory * @param hideButtons true if the relation field buttons shall be hidden public ReceptionEditor(EpicamRequestFactory factory, boolean hideButtons) { this.requestFactory = factory ;  this.hideButtons = hideButtons ;  setRelationFields() ;  initWidget(BINDER.createAndBindUi(this)) ;  properties() ;  }  * Constructor * @param factory the application request factory public ReceptionEditor(EpicamRequestFactory factory) { this(factory, false) ;  }  * Sets the properties of the fields private void properties() { descriptionSection.setGroupTitle(NLS.constants() .reception_group_description()) ;  region.setLabel(NLS.constants().reception_field_region()) ;  // the value of region affects the value of other fields region.notifyChanges(requestFactory.getEventBus()) ;  districtSante.setLabel(NLS.constants().reception_field_districtSante()) ;  // the value of districtSante affects the value of other fields districtSante.notifyChanges(requestFactory.getEventBus()) ;  CDT.setLabel(NLS.constants().reception_field_cDT()) ;  // the value of CDT affects the value of other fields CDT.notifyChanges(requestFactory.getEventBus()) ;  commande.setLabel(NLS.constants().reception_field_commande()) ;  dateReception.setLabel(NLS.constants().reception_field_dateReception()) ;  // the value of commande affects the visibility of other fields commande.notifyChanges(requestFactory.getEventBus()) ;  // the value of CDT affects the value of other fields CDT.notifyChanges(requestFactory.getEventBus()) ;  }  * Configures the widgets that manage relation fields private void setRelationFields() { regionDataProvider = new RegionDataProvider(requestFactory) ;  if (hideButtons) // in popup, relation buttons hidden region = new ImogSingleRelationBox<RegionProxy>(regionDataProvider, EpicamRenderer.get(), true) ;  else {// in wrapper panel, relation buttons enabled  if (AccessManager.canCreateRegion() && AccessManager.canEditRegion()) region = new ImogSingleRelationBox<RegionProxy>( regionDataProvider, EpicamRenderer.get()) ;  else region = new ImogSingleRelationBox<RegionProxy>(false, regionDataProvider, EpicamRenderer.get()) ;  }  districtSanteDataProvider = new DistrictSanteDataProvider( requestFactory) ;  if (hideButtons) // in popup, relation buttons hidden districtSante = new ImogSingleRelationBox<DistrictSanteProxy>( districtSanteDataProvider, EpicamRenderer.get(), true) ;  else {// in wrapper panel, relation buttons enabled  if (AccessManager.canCreateDistrictSante() && AccessManager.canEditDistrictSante()) districtSante = new ImogSingleRelationBox<DistrictSanteProxy>( districtSanteDataProvider, EpicamRenderer.get()) ;  else districtSante = new ImogSingleRelationBox<DistrictSanteProxy>( false, districtSanteDataProvider, EpicamRenderer.get()) ;  }  cDTDataProvider = new CentreDiagTraitDataProvider(requestFactory) ;  if (hideButtons) // in popup, relation buttons hidden CDT = new ImogSingleRelationBox<CentreDiagTraitProxy>( cDTDataProvider, EpicamRenderer.get(), true) ;  else {// in wrapper panel, relation buttons enabled  if (AccessManager.canCreateCentreDiagTrait() && AccessManager.canEditCentreDiagTrait()) CDT = new ImogSingleRelationBox<CentreDiagTraitProxy>( cDTDataProvider, EpicamRenderer.get()) ;  else CDT = new ImogSingleRelationBox<CentreDiagTraitProxy>(false, cDTDataProvider, EpicamRenderer.get()) ;  }  commandeDataProvider = new CommandeDataProvider(requestFactory) ;  if (hideButtons) // in popup, relation buttons hidden commande = new ImogSingleRelationBox<CommandeProxy>( commandeDataProvider, EpicamRenderer.get(), true) ;  else {// in wrapper panel, relation buttons enabled  if (AccessManager.canCreateCommande() && AccessManager.canEditCommande()) commande = new ImogSingleRelationBox<CommandeProxy>( commandeDataProvider, EpicamRenderer.get()) ;  else commande = new ImogSingleRelationBox<CommandeProxy>(false, commandeDataProvider, EpicamRenderer.get()) ;  }  medicaments = new ReceptionMedicamentsListEditor(requestFactory) ;  intrants = new ReceptionIntrantsListEditor(requestFactory) ;  }  * Sets the edition mode * @param isEdited true to enable the edition of the form public void setEdited(boolean isEdited) { if (isEdited) setFieldEditAccess() ;  else setFieldReadAccess() ;  region.setEdited(isEdited) ;  districtSante.setEdited(isEdited) ;  CDT.setEdited(isEdited) ;  commande.setEdited(isEdited) ;  dateReception.setEdited(isEdited) ;  medicaments.setEdited(isEdited) ;  intrants.setEdited(isEdited) ;  }  * Configures the visibility of the fields  * in view mode depending on the user privileges private void setFieldReadAccess() { if (!AccessManager.canReadReceptionDescription()) descriptionSection.setVisible(false) ;  }  * Configures the visibility of the fields  * in edit mode depending on the user privileges private void setFieldEditAccess() { if (!AccessManager.canEditReceptionDescription()) descriptionSection.setVisible(false) ;  }  * Sets the Request Context for the List Editors. public void setRequestContextForListEditors(ReceptionRequest ctx) { medicaments.setRequestContextForListEditors(ctx) ;  intrants.setRequestContextForListEditors(ctx) ;  }  * Manages editor updates when a field value changes private void setFieldValueChangeHandler() { registrations.add(requestFactory.getEventBus().addHandler( FieldValueChangeEvent.TYPE, new FieldValueChangeEvent.Handler() { @Override public void onValueChange(ImogField<?> field) { // field dependent visibility management computeVisibility(field, false) ;  if (field.equals(region)) { clearDistrictSanteWidget() ;  getDistrictSanteFilteredByRegion(region.getValue()) ;  commonFieldUtil.setRegion(region.getValue()) ;  }  if (field.equals(districtSante)) { clearCDTWidget() ;  getCDTFilteredByDistrictSante(districtSante .getValue()) ;  commonFieldUtil.setDistrict(districtSante .getValue()) ;  if (districtSante.getValue() != null) { RegionProxy proxy = districtSante.getValue() .getRegion() ;  region.setValue(proxy) ;  commonFieldUtil.setRegion(proxy) ;  }  }  if (field.equals(CDT)) { clearCommandeWidget() ;  getCommandeFilteredByCDT(CDT.getValue()) ;  }  // NestedForm fields content depends on the value of field Commande if (field.equals(commande)) { updateCommandeInNestedForms(commande.getValue()) ;  }  if (field.equals(CDT)) { updateCDTInNestedForms(CDT.getValue()) ;  }  if (field.equals(CDT)) { CentreDiagTraitProxy cdtValue = CDT.getValue() ;  commonFieldUtil.setCdt(cdtValue) ;  if (cdtValue != null) { RegionProxy regionValue = cdtValue.getRegion() ;  region.setValue(regionValue) ;  commonFieldUtil.setRegion(regionValue) ;  DistrictSanteProxy districtValue = cdtValue .getDistrictSante() ;  districtSante.setValue(districtValue) ;  commonFieldUtil.setDistrict(districtValue) ;  }  }  }  } )) ;  }  * Computes the field visibility public void computeVisibility(ImogField<?> source, boolean allValidation) { medicaments.computeVisibility(source, allValidation) ;  intrants.computeVisibility(source, allValidation) ;  }  * Filters the content of the RelationField DistrictSante by the value of  * the RelationField Region * @param region the value of  * the RelationField Region  public void getDistrictSanteFilteredByRegion(RegionProxy pRegion) { if (pRegion != null) { if (!hideButtons) districtSante.hideButtons(false) ;  districtSanteDataProvider.setFilterCriteria(pRegion.getId(), "region.id") ;  }  else { districtSanteDataProvider.setIsFiltered(false) ;  }  getCDTFilteredByRegion(pRegion) ;  }  * Filters the content of the RelationField CDT by the value of  * the RelationField DistrictSante * @param districtSante the value of  * the RelationField DistrictSante  public void getCDTFilteredByDistrictSante(DistrictSanteProxy pDistrictSante) { if (pDistrictSante != null) { if (!hideButtons) CDT.hideButtons(false) ;  cDTDataProvider.setFilterCriteria(pDistrictSante.getId(), "districtSante.id") ;  }  }  * Filters the content of the RelationField Commande by the value of  * the RelationField CDT * @param cDT the value of  * the RelationField CDT  public void getCommandeFilteredByCDT(CentreDiagTraitProxy pCDT) { if (pCDT != null) { if (!hideButtons) commande.hideButtons(false) ;  commandeDataProvider.setFilterCriteria(pCDT.getId(), "CDT.id") ;  }  else { commande.hideButtons(true) ;  commandeDataProvider.setFilterCriteria(null) ;  }  }  public void getCDTFilteredByRegion(RegionProxy pRegion) { if (pRegion != null) { if (!hideButtons) CDT.hideButtons(false) ;  cDTDataProvider.setFilterCriteria(pRegion.getId(), "region.id") ;  }  else { cDTDataProvider.setIsFiltered(false) ;  }  }  * Setter to inject a Region value * @param value the value to be injected into the editor * @param isLocked true if relation field shall be locked (non editable) public void setRegion(RegionProxy value, boolean isLocked) { region.setLocked(isLocked) ;  region.setValue(value) ;  // Field DistrictSante depends on the value of field region clearDistrictSanteWidget() ;  getDistrictSanteFilteredByRegion(value) ;  }  private void clearRegionWidget() { region.clear() ;  }  * Setter to inject a DistrictSante value * @param value the value to be injected into the editor * @param isLocked true if relation field shall be locked (non editable) public void setDistrictSante(DistrictSanteProxy value, boolean isLocked) { districtSante.setLocked(isLocked) ;  districtSante.setValue(value) ;  // Field CDT depends on the value of field districtSante clearCDTWidget() ;  getCDTFilteredByDistrictSante(value) ;  }  private void clearDistrictSanteWidget() { districtSante.clear() ;  clearCDTWidget() ;  commonFieldUtil.setDistrict(null) ;  }  * Setter to inject a CentreDiagTrait value * @param value the value to be injected into the editor * @param isLocked true if relation field shall be locked (non editable) public void setCDT(CentreDiagTraitProxy value, boolean isLocked) { CDT.setLocked(isLocked) ;  CDT.setValue(value) ;  // Field Commande depends on the value of field cDT clearCommandeWidget() ;  getCommandeFilteredByCDT(value) ;  }  private void clearCDTWidget() { CDT.clear() ;  clearCommandeWidget() ;  updateCDTInNestedForms(null) ;  commonFieldUtil.setCdt(null) ;  }  * Setter to inject a Commande value * @param value the value to be injected into the editor * @param isLocked true if relation field shall be locked (non editable) public void setCommande(CommandeProxy value, boolean isLocked) { commande.setLocked(isLocked) ;  commande.setValue(value) ;  }  private void clearCommandeWidget() { commande.clear() ;  updateCommandeInNestedForms(null) ;  }  private void setCommandeInNestedForms(CommandeProxy value) { medicaments.setCommande(value) ;  intrants.setCommande(value) ;  }  private void updateCommandeInNestedForms(CommandeProxy value) { medicaments.updateCommande(value) ;  intrants.updateCommande(value) ;  }  private void setCDTInNestedForms(CentreDiagTraitProxy value) { medicaments.setCDT(value) ;  intrants.setCDT(value) ;  }  private void updateCDTInNestedForms(CentreDiagTraitProxy value) { medicaments.updateCDT(value) ;  intrants.updateCDT(value) ;  }  * Configures the handlers of the widgets that manage relation fields private void setRelationHandlers() { region.setViewClickHandler(new ClickHandler() { @Override public void onClick(ClickEvent event) { if (region.getValue() != null) { RelationPopupPanel relationPopup = new RelationPopupPanel() ;  RegionFormPanel form = new RegionFormPanel(requestFactory, region.getValue().getId(), relationPopup, "region") ;  relationPopup.addWidget(form) ;  relationPopup.show() ;  }  }  } ) ;  region.setAddClickHandler(new ClickHandler() { @Override public void onClick(ClickEvent event) { RelationPopupPanel relationPopup = new RelationPopupPanel() ;  RegionFormPanel form = new RegionFormPanel(requestFactory, null, relationPopup, "region") ;  relationPopup.addWidget(form) ;  relationPopup.show() ;  }  } ) ;  registrations.add(requestFactory.getEventBus().addHandler( SaveRegionEvent.TYPE, new SaveRegionEvent.Handler() { @Override public void saveRegion(RegionProxy value) { region.setValue(value) ;  }  @Override public void saveRegion(RegionProxy value, String initField) { if (initField.equals("region")) region.setValue(value, true) ;  }  } )) ;  districtSante.setViewClickHandler(new ClickHandler() { @Override public void onClick(ClickEvent event) { if (districtSante.getValue() != null) { RelationPopupPanel relationPopup = new RelationPopupPanel() ;  DistrictSanteFormPanel form = new DistrictSanteFormPanel( requestFactory, districtSante.getValue().getId(), relationPopup, "districtSante") ;  relationPopup.addWidget(form) ;  relationPopup.show() ;  }  }  } ) ;  districtSante.setAddClickHandler(new ClickHandler() { @Override public void onClick(ClickEvent event) { RelationPopupPanel relationPopup = new RelationPopupPanel() ;  DistrictSanteFormPanel form = new DistrictSanteFormPanel( requestFactory, null, relationPopup, "districtSante") ;  if (region.getValue() != null) form.setRegion(region.getValue(), true) ;  relationPopup.addWidget(form) ;  relationPopup.show() ;  }  } ) ;  registrations.add(requestFactory.getEventBus().addHandler( SaveDistrictSanteEvent.TYPE, new SaveDistrictSanteEvent.Handler() { @Override public void saveDistrictSante(DistrictSanteProxy value) { districtSante.setValue(value) ;  }  @Override public void saveDistrictSante(DistrictSanteProxy value, String initField) { if (initField.equals("districtSante")) districtSante.setValue(value, true) ;  }  } )) ;  CDT.setViewClickHandler(new ClickHandler() { @Override public void onClick(ClickEvent event) { if (CDT.getValue() != null) { RelationPopupPanel relationPopup = new RelationPopupPanel() ;  CentreDiagTraitFormPanel form = new CentreDiagTraitFormPanel( requestFactory, CDT.getValue().getId(), relationPopup, "CDT") ;  relationPopup.addWidget(form) ;  relationPopup.show() ;  }  }  } ) ;  CDT.setAddClickHandler(new ClickHandler() { @Override public void onClick(ClickEvent event) { RelationPopupPanel relationPopup = new RelationPopupPanel() ;  CentreDiagTraitFormPanel form = new CentreDiagTraitFormPanel( requestFactory, null, relationPopup, "CDT") ;  if (districtSante.getValue() != null) form.setDistrictSante(districtSante.getValue(), true) ;  if (region.getValue() != null) form.setRegion(region.getValue(), true) ;  relationPopup.addWidget(form) ;  relationPopup.show() ;  }  } ) ;  registrations.add(requestFactory.getEventBus().addHandler( SaveCentreDiagTraitEvent.TYPE, new SaveCentreDiagTraitEvent.Handler() { @Override public void saveCentreDiagTrait(CentreDiagTraitProxy value) { CDT.setValue(value) ;  }  @Override public void saveCentreDiagTrait(CentreDiagTraitProxy value, String initField) { if (initField.equals("CDT")) CDT.setValue(value, true) ;  }  } )) ;  commande.setViewClickHandler(new ClickHandler() { @Override public void onClick(ClickEvent event) { if (commande.getValue() != null) { RelationPopupPanel relationPopup = new RelationPopupPanel() ;  CommandeFormPanel form = new CommandeFormPanel( requestFactory, commande.getValue().getId(), relationPopup, "commande") ;  relationPopup.addWidget(form) ;  relationPopup.show() ;  }  }  } ) ;  commande.setAddClickHandler(new ClickHandler() { @Override public void onClick(ClickEvent event) { RelationPopupPanel relationPopup = new RelationPopupPanel() ;  CommandeFormPanel form = new CommandeFormPanel(requestFactory, null, relationPopup, "commande") ;  relationPopup.addWidget(form) ;  relationPopup.show() ;  }  } ) ;  registrations.add(requestFactory.getEventBus().addHandler( SaveCommandeEvent.TYPE, new SaveCommandeEvent.Handler() { @Override public void saveCommande(CommandeProxy value) { commande.setValue(value) ;  }  @Override public void saveCommande(CommandeProxy value, String initField) { if (initField.equals("commande")) commande.setValue(value, true) ;  }  } )) ;  }  * Gets the ReceptionProxy that is edited in the Workflow * Not used by the editor * Temporary storage used to transmit the proxy to related entities * @return public ReceptionProxy getEditedValue() { return editedValue ;  }  * Sets the ReceptionProxy that is edited in the Workflow * Not used by the editor * Temporary storage used to transmit the proxy to related entities  * @param editedValue  public void setEditedValue(ReceptionProxy editedValue) { this.editedValue = editedValue ;  if (editedValue != null) { setCommandeInNestedForms(editedValue.getCommande()) ;  setCDTInNestedForms(editedValue.getCDT()) ;  // sets value for common fields when creating a new lot in child editors commonFieldUtil.setRegion(editedValue.getRegion()) ;  commonFieldUtil.setDistrict(editedValue.getDistrictSante()) ;  commonFieldUtil.setCdt(editedValue.getCDT()) ;  }  }  * public void raiseNotUniqueError(String field) { delegate.recordError(BaseNLS.messages().error_not_unique(), null, field) ;  }  * Validate fields values public void validateFields() { // region is a required field if (region.getValue() == null) delegate.recordError(BaseNLS.messages().error_required(), null, "region") ;  // districtSante is a required field if (districtSante.getValue() == null) delegate.recordError(BaseNLS.messages().error_required(), null, "districtSante") ;  // cDT is a required field if (CDT.getValue() == null) delegate.recordError(BaseNLS.messages().error_required(), null, "cDT") ;  // commande is a required field if (commande.getValue() == null) delegate.recordError(BaseNLS.messages().error_required(), null, "commande") ;  // dateReception is a required field if (dateReception.getValueWithoutParseException() == null && dateReception.isValid()) delegate.recordError(BaseNLS.messages().error_required(), null, "dateReception") ;  // medicaments nested form shall be validated medicaments.validateFields() ;  // intrants nested form shall be validated intrants.validateFields() ;  }  private void setAllLabelWith(String width) { region.setLabelWidth(width) ;  districtSante.setLabelWidth(width) ;  CDT.setLabelWidth(width) ;  commande.setLabelWidth(width) ;  dateReception.setLabelWidth(width) ;  }  private void setAllBoxWith(int width) { region.setBoxWidth(width) ;  districtSante.setBoxWidth(width) ;  CDT.setBoxWidth(width) ;  commande.setBoxWidth(width) ;  }  @Override public void setDelegate(EditorDelegate<ReceptionProxy> delegate) { this.delegate = delegate ;  }  @Override public void showErrors(List<EditorError> errors) { if (errors != null && errors.size() > 0) { List<EditorError> regionFieldErrors = new ArrayList<EditorError>() ;  List<EditorError> districtSanteFieldErrors = new ArrayList<EditorError>() ;  List<EditorError> cDTFieldErrors = new ArrayList<EditorError>() ;  List<EditorError> commandeFieldErrors = new ArrayList<EditorError>() ;  List<EditorError> dateReceptionFieldErrors = new ArrayList<EditorError>() ;  for (EditorError error : errors) { Object userData = error.getUserData() ;  if (userData != null && userData instanceof String) { String field = (String) userData ;  if (field.equals("region")) regionFieldErrors.add(error) ;  if (field.equals("districtSante")) districtSanteFieldErrors.add(error) ;  if (field.equals("cDT")) cDTFieldErrors.add(error) ;  if (field.equals("commande")) commandeFieldErrors.add(error) ;  if (field.equals("dateReception")) dateReceptionFieldErrors.add(error) ;  }  }  if (regionFieldErrors.size() > 0) region.showErrors(regionFieldErrors) ;  if (districtSanteFieldErrors.size() > 0) districtSante.showErrors(districtSanteFieldErrors) ;  if (cDTFieldErrors.size() > 0) CDT.showErrors(cDTFieldErrors) ;  if (commandeFieldErrors.size() > 0) commande.showErrors(commandeFieldErrors) ;  if (dateReceptionFieldErrors.size() > 0) dateReception.showErrors(dateReceptionFieldErrors) ;  }  }  @Override protected void onUnload() { for (HandlerRegistration r : registrations) r.removeHandler() ;  registrations.clear() ;  // reset common fields values commonFieldUtil.setRegion(null) ;  commonFieldUtil.setDistrict(null) ;  commonFieldUtil.setCdt(null) ;  super.onUnload() ;  }  @Override protected void onLoad() { setRelationHandlers() ;  setFieldValueChangeHandler() ;  super.onLoad() ;  } }