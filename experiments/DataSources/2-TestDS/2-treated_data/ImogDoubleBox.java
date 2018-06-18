package org.imogene.web.client.ui.field ; public class ImogDoubleBox extends Composite implements ImogField<Double>, LeafValueEditor<Double>, HasEditorErrors<Double>, HasEditorDelegate<Double> { private static final Binder uiBinder = GWT.create(Binder.class) ;  interface Binder extends UiBinder<Widget, ImogDoubleBox> { }  private boolean edited = false ;  private boolean isValid = true ;  private EditorDelegate<Double> delegate ;  @UiField @Ignore ImogFieldAbstract fieldBox ;  @UiField ImogErrorLabel errorLabel ;  @UiField @Ignore ImogDblBox textBox ;  @UiField @Ignore Label unitBox ;  public ImogDoubleBox() { initWidget(uiBinder.createAndBindUi(this)) ;  }  public void setEdited(boolean enabled) { textBox.setEnabled(enabled) ;  if (!enabled) { textBox.addStyleDependentName("disabled") ;  }  else { textBox.removeStyleDependentName("disabled") ;  }  edited = enabled ;  }  @Override public void setLabel(String label) { fieldBox.setLabel(label) ;  }   public void setLabel(String label, HorizontalAlignmentConstant alignment) { fieldBox.setLabel(label, alignment) ;  }  @Override public boolean isEdited() { return edited ;  }  @Override public void setValue(Double value) { textBox.setValue(value) ;   }  @Override public Double getValue() {  Double result = null ;  try { result = textBox.getValueOrThrow() ;  isValid = true ;  }  catch (ParseException e) { isValid = false ;  if(delegate!=null) delegate.recordError(BaseNLS.messages().error_format_float(), textBox.getText(), null) ;  }   return result ;   }  * Used to get the value without throwing an exception nor an error message * @return public Double getValueWithoutParseException() {  Double result = null ;  try { result = textBox.getValueOrThrow() ;  isValid = true ;  }  catch (ParseException e) { isValid = false ;  }   return result ;  }   * Tells if the entered value is a valid Double * @return public boolean isValid() { return isValid ;  }   * Can be used when the box is not used as an editor but just as a widget * Throws an exception when an invalid integer is entered * @return * @throws ParseException public Double getValueWithParseException() throws ParseException { return textBox.getValueOrThrow() ;   }   * Gets the text of the box * @return public String getText() { return textBox.getText() ;  }   * Defines that the field shall notify value changes * @param eventBus the event bus that will be used to fire the value change events public void notifyChanges(final EventBus eventBus) { if(eventBus!=null) { textBox.addValueChangeHandler(new ValueChangeHandler<Double>() {  @Override public void onValueChange(ValueChangeEvent<Double> arg0) { eventBus.fireEvent(new FieldValueChangeEvent(ImogDoubleBox.this)) ;  }  } ) ;   }  }  @Override public void showErrors(List<EditorError> errors) { errorLabel.showErrors(errors) ;  }   @Override public void setDelegate(EditorDelegate<Double> delegate) { this.delegate = delegate ;  }   public void setLabelWidth(String width) { fieldBox.setLabelWidth(width) ;  }   * Sets the widget's width public void setBoxWidth(int width) { textBox.getElement().getStyle().setProperty("width", width + "px") ;  }   public void setUnit(String unit) { unitBox.setVisible(true) ;  unitBox.setText(unit) ;  } }