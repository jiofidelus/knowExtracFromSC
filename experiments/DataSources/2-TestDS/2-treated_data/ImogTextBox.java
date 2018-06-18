package org.imogene.web.client.ui.field ; public class ImogTextBox extends Composite implements ImogField<String>, LeafValueEditor<String>, HasEditorErrors<String> { private static final Binder uiBinder = GWT.create(Binder.class) ;  interface Binder extends UiBinder<Widget, ImogTextBox> { }  private boolean edited = false ;  @UiField ImogErrorLabel errorLabel ;  @UiField @Ignore ImogFieldAbstract fieldBox ;  @UiField @Ignore TextBox textBox ;  public ImogTextBox() { initWidget(uiBinder.createAndBindUi(this)) ;  }  public void setEdited(boolean enabled) { textBox.setEnabled(enabled) ;  if (!enabled) { textBox.addStyleDependentName("disabled") ;  }  else { textBox.removeStyleDependentName("disabled") ;  }  edited = enabled ;  }  @Override public void setLabel(String label) { fieldBox.setLabel(label) ;  }   public void setLabel(String label, HorizontalAlignmentConstant alignment) { fieldBox.setLabel(label, alignment) ;  }  @Override public boolean isEdited() { return edited ;  }  @Override public void setValue(String value) { textBox.setValue(value) ;   }  @Override public String getValue() { if (textBox.getValue().isEmpty()) return null ;  else return textBox.getValue() ;  }   * Defines that the field shall notify value changes * @param eventBus the event bus that will be used to fire the value change events public void notifyChanges(final EventBus eventBus) { if(eventBus!=null) { textBox.addValueChangeHandler(new ValueChangeHandler<String>() {  @Override public void onValueChange(ValueChangeEvent<String> arg0) { eventBus.fireEvent(new FieldValueChangeEvent(ImogTextBox.this)) ;  }  } ) ;   }  }  * Defines the width of cell that contains the label * @param width width of cell that contains the label public void setLabelWidth(String width) { fieldBox.setLabelWidth(width) ;  }   * Sets the widget's width public void setBoxWidth(int width) { textBox.getElement().getStyle().setProperty("width", width + "px") ;  }   @Override public void showErrors(List<EditorError> errors) { errorLabel.showErrors(errors) ;  } }