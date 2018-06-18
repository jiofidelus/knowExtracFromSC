package org.imogene.epicam.client.ui.editor ; public class RegionEditor extends Composite implements Editor<RegionProxy>, HasEditorDelegate<RegionProxy>, HasEditorErrors<RegionProxy> { interface Binder extends UiBinder<Widget, RegionEditor> { }  private static final Binder BINDER = GWT.create(Binder.class) ;  protected final EpicamRequestFactory requestFactory ;  private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>() ;  private EditorDelegate<RegionProxy> delegate ;  private RegionProxy editedValue ;  //Not used by the editor private boolean hideButtons = false ;  private List<String> locales = Arrays.asList("fr", "en") ;  @UiField @Ignore FieldGroupPanel descriptionSection ;  @UiField ImogTextBox code ;  @UiField(provided = true) ImogLocalizedTextBox nom ;  @UiField(provided = true) ImogLocalizedTextAreaBox description ;  @UiField(provided = true) ImogMultiRelationBox<DistrictSanteProxy> districtSantes ;  private DistrictSanteDataProvider districtSantesDataProvider ;  @UiField @Ignore FieldGroupPanel adresseSection ;  @UiField ImogSingleEnumBox libelle ;  @UiField ImogTextAreaBox complementAdresse ;  @UiField ImogTextBox quartier ;  @UiField ImogTextBox ville ;  @UiField ImogTextBox codePostal ;  @UiField ImogGeoBox coordonnees ;  * Constructor * @param factory the application request factory * @param hideButtons true if the relation field buttons shall be hidden public RegionEditor(EpicamRequestFactory factory, boolean hideButtons) { this.requestFactory = factory ;  this.hideButtons = hideButtons ;  setRelationFields() ;  nom = new ImogLocalizedTextBox(locales, NLS.constants().locale()) ;  description = new ImogLocalizedTextAreaBox(locales, NLS.constants() .locale()) ;  initWidget(BINDER.createAndBindUi(this)) ;  properties() ;  }  * Constructor * @param factory the application request factory public RegionEditor(EpicamRequestFactory factory) { this(factory, false) ;  }  * Sets the properties of the fields private void properties() { descriptionSection.setGroupTitle(NLS.constants() .region_group_description()) ;  code.setLabel(NLS.constants().region_field_code()) ;  nom.setLabel(NLS.constants().region_field_nom()) ;  description.setLabel(NLS.constants().region_field_description()) ;  districtSantes.setLabel(NLS.constants().region_field_districtSantes()) ;  adresseSection.setGroupTitle(NLS.constants().region_group_adresse()) ;  libelle.setLabel(NLS.constants().region_field_libelle()) ;  libelle.addItem(EpicamEnumConstants.REGION_LIBELLE_DOMICILE, NLS .constants().region_libelle_domicile_option()) ;  libelle.addItem(EpicamEnumConstants.REGION_LIBELLE_BUREAU, NLS .constants().region_libelle_bureau_option()) ;  libelle.addItem(EpicamEnumConstants.REGION_LIBELLE_AUTRE, NLS .constants().region_libelle_autre_option()) ;  complementAdresse.setLabel(NLS.constants() .region_field_complementAdresse()) ;  quartier.setLabel(NLS.constants().region_field_quartier()) ;  ville.setLabel(NLS.constants().region_field_ville()) ;  codePostal.setLabel(NLS.constants().region_field_codePostal()) ;  coordonnees.setLabel(NLS.constants().region_field_coordonnees()) ;  }  * Configures the widgets that manage relation fields private void setRelationFields() { districtSantesDataProvider = new DistrictSanteDataProvider( requestFactory, "region") ;  if (hideButtons) // in popup, relation buttons hidden  districtSantes = new ImogMultiRelationBox<DistrictSanteProxy>( districtSantesDataProvider, EpicamRenderer.get(), true) ;  else {// in wrapper panel, relation buttons enabled if (AccessManager.canCreateDistrictSante() && AccessManager.canEditDistrictSante()) districtSantes = new ImogMultiRelationBox<DistrictSanteProxy>( districtSantesDataProvider, EpicamRenderer.get(), null) ;  else districtSantes = new ImogMultiRelationBox<DistrictSanteProxy>( false, districtSantesDataProvider, EpicamRenderer.get(), null) ;  }  districtSantes.setPopUpTitle(NLS.constants() .districtSante_select_title()) ;  districtSantes.setFilterPanel(new DistrictSanteFilterPanel()) ;  }  * Sets the edition mode * @param isEdited true to enable the edition of the form public void setEdited(boolean isEdited) { if (isEdited) setFieldEditAccess() ;  else setFieldReadAccess() ;  code.setEdited(isEdited) ;  nom.setEdited(isEdited) ;  description.setEdited(isEdited) ;  districtSantes.setEdited(isEdited) ;  libelle.setEdited(isEdited) ;  complementAdresse.setEdited(isEdited) ;  quartier.setEdited(isEdited) ;  ville.setEdited(isEdited) ;  codePostal.setEdited(isEdited) ;  coordonnees.setEdited(isEdited) ;  }  * Configures the visibility of the fields  * in view mode depending on the user privileges private void setFieldReadAccess() { if (!AccessManager.canReadRegionDescription()) descriptionSection.setVisible(false) ;  if (!AccessManager.canReadRegionAdresse()) adresseSection.setVisible(false) ;  }  * Configures the visibility of the fields  * in edit mode depending on the user privileges private void setFieldEditAccess() { if (!AccessManager.canEditRegionDescription()) descriptionSection.setVisible(false) ;  if (!AccessManager.canEditRegionAdresse()) adresseSection.setVisible(false) ;  }  * Sets the Request Context for the List Editors. public void setRequestContextForListEditors(RegionRequest ctx) { }  * Manages editor updates when a field value changes private void setFieldValueChangeHandler() { registrations.add(requestFactory.getEventBus().addHandler( FieldValueChangeEvent.TYPE, new FieldValueChangeEvent.Handler() { @Override public void onValueChange(ImogField<?> field) { // field dependent visibility management computeVisibility(field, false) ;  }  } )) ;  }  * Computes the field visibility public void computeVisibility(ImogField<?> source, boolean allValidation) { }  private void clearDistrictSantesWidget() { districtSantes.emptyList() ;  }  * Configures the handlers of the widgets that manage relation fields private void setRelationHandlers() { districtSantes.setViewClickHandler(new ClickHandler() { @Override public void onClick(ClickEvent event) { if (!districtSantes.isEmpty() && districtSantes.getSelectedEntities().size() > 0) { DistrictSanteProxy value = districtSantes .getSelectedEntities().get(0) ;  RelationPopupPanel relationPopup = new RelationPopupPanel() ;  DistrictSanteFormPanel form = new DistrictSanteFormPanel( requestFactory, value.getId(), relationPopup, "districtSantes") ;  relationPopup.addWidget(form) ;  relationPopup.show() ;  }  }  } ) ;  districtSantes.setAddClickHandler(new ClickHandler() { @Override public void onClick(ClickEvent event) { RelationPopupPanel relationPopup = new RelationPopupPanel() ;  DistrictSanteFormPanel form = new DistrictSanteFormPanel( requestFactory, null, relationPopup, "districtSantes") ;  form.setRegion(editedValue, true) ;  relationPopup.addWidget(form) ;  relationPopup.show() ;  }  } ) ;  registrations.add(requestFactory.getEventBus().addHandler( SaveDistrictSanteEvent.TYPE, new SaveDistrictSanteEvent.Handler() { @Override public void saveDistrictSante(DistrictSanteProxy value) { if (!districtSantes.isPresent(value)) districtSantes.addValue(value) ;  }  public void saveDistrictSante(DistrictSanteProxy value, String initField) { if (initField.equals("districtSantes")) { if (districtSantes.isPresent(value)) districtSantes.replaceValue(value) ;  else districtSantes.addValue(value, true) ;  }  }  } )) ;  }  * Gets the RegionProxy that is edited in the Workflow * Not used by the editor * Temporary storage used to transmit the proxy to related entities * @return public RegionProxy getEditedValue() { return editedValue ;  }  * Sets the RegionProxy that is edited in the Workflow * Not used by the editor * Temporary storage used to transmit the proxy to related entities  * @param editedValue  public void setEditedValue(RegionProxy editedValue) { this.editedValue = editedValue ;  }  * public void raiseNotUniqueError(String field) { delegate.recordError(BaseNLS.messages().error_not_unique(), null, field) ;  }  * Validate fields values public void validateFields() { // code is a required field if (code.getValue() == null) delegate.recordError(BaseNLS.messages().error_required(), null, "code") ;  // nom is a required field if (nom.getValue() == null) delegate.recordError(BaseNLS.messages().error_required(), null, "nom") ;  }  private void setAllLabelWith(String width) { code.setLabelWidth(width) ;  nom.setLabelWidth(width) ;  description.setLabelWidth(width) ;  districtSantes.setLabelWidth(width) ;  libelle.setLabelWidth(width) ;  complementAdresse.setLabelWidth(width) ;  quartier.setLabelWidth(width) ;  ville.setLabelWidth(width) ;  codePostal.setLabelWidth(width) ;  coordonnees.setLabelWidth(width) ;  }  private void setAllBoxWith(int width) { code.setBoxWidth(width) ;  nom.setBoxWidth(width) ;  description.setBoxWidth(width) ;  districtSantes.setBoxWidth(width) ;  libelle.setBoxWidth(width) ;  complementAdresse.setBoxWidth(width) ;  quartier.setBoxWidth(width) ;  ville.setBoxWidth(width) ;  codePostal.setBoxWidth(width) ;  coordonnees.setBoxWidth(width) ;  }  @Override public void setDelegate(EditorDelegate<RegionProxy> delegate) { this.delegate = delegate ;  }  @Override public void showErrors(List<EditorError> errors) { if (errors != null && errors.size() > 0) { List<EditorError> codeFieldErrors = new ArrayList<EditorError>() ;  List<EditorError> nomFieldErrors = new ArrayList<EditorError>() ;  for (EditorError error : errors) { Object userData = error.getUserData() ;  if (userData != null && userData instanceof String) { String field = (String) userData ;  if (field.equals("code")) codeFieldErrors.add(error) ;  if (field.equals("nom")) nomFieldErrors.add(error) ;  }  }  if (codeFieldErrors.size() > 0) code.showErrors(codeFieldErrors) ;  if (nomFieldErrors.size() > 0) nom.showErrors(nomFieldErrors) ;  }  }  @Override protected void onUnload() { for (HandlerRegistration r : registrations) r.removeHandler() ;  registrations.clear() ;  super.onUnload() ;  }  @Override protected void onLoad() { setRelationHandlers() ;  setFieldValueChangeHandler() ;  super.onLoad() ;  } }