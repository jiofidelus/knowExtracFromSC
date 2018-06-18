package org.imogene.web.client.ui.field.relation.single.listdataprovider ; public class ImogSingleRelationBoxForList<T extends ImogBeanProxy> extends Composite implements ImogField<T>, LeafValueEditor<T>, HasEditorErrors<T> { private static final Binder uiBinder = GWT.create(Binder.class) ;  @SuppressWarnings("rawtypes") interface Binder extends UiBinder<Widget, ImogSingleRelationBoxForList> { }  @UiField ImogErrorLabel errorLabel ;  @UiField @Ignore ImogFieldAbstract fieldBox ;  @UiField (provided=true) @Ignore ImogSingleRelationForList<T> singleRelationBox ;     public ImogSingleRelationBoxForList(ImogBeanRenderer beanRenderer, boolean hideButtons) { singleRelationBox = new ImogSingleRelationForList<T>(beanRenderer, hideButtons) ;  initWidget(uiBinder.createAndBindUi(this)) ;  }   public ImogSingleRelationBoxForList(ImogBeanRenderer beanRenderer) { this(true, beanRenderer) ;  }    public ImogSingleRelationBoxForList(boolean canCreateEntity, ImogBeanRenderer beanRenderer) { singleRelationBox = new ImogSingleRelationForList<T>(canCreateEntity, beanRenderer) ;  initWidget(uiBinder.createAndBindUi(this)) ;  }   public void addValuesToList(List<T> values) {  singleRelationBox.addValuesToList(values) ;  }  @Override public void setLabel(String label) { fieldBox.setLabel(label) ;  }   public void setLabel(String label, HorizontalAlignmentConstant alignment) { fieldBox.setLabel(label, alignment) ;  }  @Override public boolean isEdited() { return singleRelationBox.isEdited() ;  }  public void setLocked(boolean locked) { singleRelationBox.setLocked(locked) ;  }   public void setViewClickHandler(ClickHandler handler){ singleRelationBox.setViewClickHandler(handler) ;  }  * Get the value selected * @return the value selected public T getValue(){  return singleRelationBox.getValue() ;  }   * Get the display text of the selected value * @return the display text of the selected value public String getText(){  return singleRelationBox.getText() ;  }   * Sets the listbox selected value  * @param value the selected value  public void setValue(T value){ singleRelationBox.setValue(value) ;  }   public void setValue(T value, boolean notifyChange){ singleRelationBox.setValue(value, notifyChange) ;  }  public void setEdited(boolean enabled) {  singleRelationBox.setEnabled(enabled) ;  }   * Clear the content of the MedanyListBox TextBox public void clear() { singleRelationBox.clear() ;   }   public void hideButtons(boolean hideButtons) { singleRelationBox.hideButtons(hideButtons) ;  }  * Sets if the open button shall be hidden in edit mode * default is false * @param hideOpenButton public void setHideOpenButton(boolean hideOpenButton) { singleRelationBox.setHideOpenButton(hideOpenButton) ;  }   public void setHideClearButton(boolean hideClearButton) { singleRelationBox.setHideClearButton(hideClearButton) ;  }   public void hideButtonPanel(boolean hide) { singleRelationBox.hideButtonPanel(hide) ;  }   * Defines that the field shall notify value changes * @param eventBus the event bus that will be used to fire the value change events public void notifyChanges(final EventBus eventBus) { if(eventBus!=null) { singleRelationBox.addValueChangeHandler(new ValueChangeHandler<String>() {  @Override public void onValueChange(ValueChangeEvent<String> arg0) { eventBus.fireEvent(new FieldValueChangeEvent(ImogSingleRelationBoxForList.this)) ;  }  } ) ;   }  }  @Override public void showErrors(List<EditorError> errors) { errorLabel.showErrors(errors) ;  }   public void hideErrors() { errorLabel.hideErrors() ;  }   public void setLabelWidth(String width) { fieldBox.setLabelWidth(width) ;  }   public Label getLabelBox() { return fieldBox.getLabelBox() ;  }   * Sets the widget's width public void setBoxWidth(int width) { singleRelationBox.setBoxWidth(width) ;  }   public void setButtonPanelWidth(String width) { singleRelationBox.setButtonPanelWidth(width) ;  } }