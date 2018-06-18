package org.imogene.epicam.client.ui.workflow.panel ; public class CentreDiagTraitFormPanel extends Composite { private static final Binder uiBinder = GWT.create(Binder.class) ;  interface Binder extends UiBinder<Widget, CentreDiagTraitFormPanel> { }  @UiField(provided = true) WrapperPanel wrapperPanel ;  @UiField(provided = true) CentreDiagTraitEditorWorkflow editorWorkflow ;  * Constructor * @param requestFactory the application requestFactory * @param entityId the id of the entity to be managed by the workflow. null if an entity is being created * @param parent parent composite if the panel is contained in a RelationPopupPanel * @param initField the field that initiated the display in a RelationPopupPanel * @param returnToList true if after closing the wokflow, the application shall display the list of entities, false otherwise public CentreDiagTraitFormPanel(EpicamRequestFactory requestFactory, String entityId, RelationPopupPanel parent, String initField) { wrapperPanel = new WrapperPanel() ;  wrapperPanel.setWidth("90%") ;  Label titleContainer = wrapperPanel.getTitleLabel() ;  if (entityId != null) { if (parent == null) editorWorkflow = new CentreDiagTraitEditorWorkflow( requestFactory, entityId, titleContainer) ;  else { editorWorkflow = new CentreDiagTraitEditorWorkflow( requestFactory, entityId, titleContainer, parent, initField) ;  }  }  else { if (parent == null) { editorWorkflow = new CentreDiagTraitEditorWorkflow( requestFactory, titleContainer) ;  }  else { editorWorkflow = new CentreDiagTraitEditorWorkflow( requestFactory, titleContainer, parent, initField) ;  }  }  if (EpicamIconConstants.CENTREDIAGTRAIT_ICON != null) wrapperPanel.setIcon(EpicamIconConstants.CENTREDIAGTRAIT_ICON) ;  if (editorWorkflow.getEditButton() != null) wrapperPanel.addHeaderWidget(editorWorkflow.getEditButton()) ;  if (editorWorkflow.getCloseButton() != null) wrapperPanel.addHeaderWidget(editorWorkflow.getCloseButton()) ;  if (editorWorkflow.getSaveButton() != null) wrapperPanel.addHeaderWidget(editorWorkflow.getSaveButton()) ;  if (editorWorkflow.getCancelButton() != null) wrapperPanel.addHeaderWidget(editorWorkflow.getCancelButton()) ;  initWidget(uiBinder.createAndBindUi(this)) ;  }  * Constructor * @param requestFactory the application requestFactory * @param entityId the id of the entity to be managed by the workflow. null if an entity is being created  public CentreDiagTraitFormPanel(EpicamRequestFactory requestFactory, String entityId) { this(requestFactory, entityId, null, null) ;  }  public void setCloseEvent(GwtEvent<?> closeEvent) { editorWorkflow.setCloseEvent(closeEvent) ;  }  * Setter to inject a Region value into the workflow and the editor * @param value the value to be injected * @param isLocked true if relation field shall be locked (non editable) public void setRegion(RegionProxy value, boolean isLocked) { editorWorkflow.setRegion(value, isLocked) ;  }  * Setter to inject a Region value into the workflow and the editor * @param value the value to be injected public void setRegion(RegionProxy value) { setRegion(value, false) ;  }  * Setter to inject a DistrictSante value into the workflow and the editor * @param value the value to be injected * @param isLocked true if relation field shall be locked (non editable) public void setDistrictSante(DistrictSanteProxy value, boolean isLocked) { editorWorkflow.setDistrictSante(value, isLocked) ;  }  * Setter to inject a DistrictSante value into the workflow and the editor * @param value the value to be injected public void setDistrictSante(DistrictSanteProxy value) { setDistrictSante(value, false) ;  } }