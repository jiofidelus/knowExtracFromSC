package org.imogene.epicam.client.ui.field ; public class ImogLocalizedTextAreaBox extends Composite implements ImogField<LocalizedTextProxy>, LeafValueEditor<LocalizedTextProxy>, HasEditorErrors<LocalizedTextProxy> { private static final Binder uiBinder = GWT.create(Binder.class) ;  interface Binder extends UiBinder<Widget, ImogLocalizedTextAreaBox> { }  private boolean edited = false ;  private LocalizedTextProxy values ;  private String currentLocale ;  @UiField @Ignore ImogFieldAbstract fieldBox ;  @UiField ImogErrorLabel errorLabel ;  @UiField @Ignore TextArea textBox ;  @UiField(provided = true) ListBox localeListBox ;  * Constructor * @param isoCodes List of available locales (iso codes), for the application * @param locale the selected locale for the field public ImogLocalizedTextAreaBox(List<String> isoCodes, String locale) { currentLocale = locale ;  localeListBox = new ListBox() ;  createLocaleList(isoCodes) ;  initWidget(uiBinder.createAndBindUi(this)) ;  }  * Creates the Listbox that lists the available locales for * the Localized TextBox * @param isoCodes list of iso codes private void createLocaleList(List<String> isoCodes) { if (isoCodes != null) { int i = 0 ;  for (String isoCode : isoCodes) { localeListBox.addItem(isoCode) ;  // select current locale if (isoCode.equals(currentLocale)) localeListBox.setSelectedIndex(i) ;  i++ ;  }  }  }  @UiHandler("localeListBox") void onChangeLocale(ChangeEvent e) { // in edition mode, save previously entered text if (edited) storeText(currentLocale) ;  // change text depending on locale currentLocale = localeListBox .getValue(localeListBox.getSelectedIndex()) ;  textBox.setText(getLocalizedText(currentLocale)) ;  }  * Gets the text corresponding to a given locale * @param locale the locale for which the text has to be returned * @return the text corresponding to a given locale private String getLocalizedText(String locale) { if (values != null) { if (locale.equals("fr")) return values.getFrancais() ;  if (locale.equals("en")) return values.getEnglish() ;  return "" ;  }  return "" ;  }  * Tells if the box value is null * @return private boolean isNull() { if ((values.getFrancais() == null || values.getFrancais().isEmpty()) && (values.getEnglish() == null || values.getEnglish() .isEmpty())) return true ;  else return false ;  }  * Stores the text that has been entered in the TextBox * for a given locale in the LocalizedText * @param locale the locale for which the text has to be stored private void storeText(String locale) { if (values != null) { if (locale.equals("fr")) values.setFrancais(textBox.getText()) ;  if (locale.equals("en")) values.setEnglish(textBox.getText()) ;  }  }  *  public void setEdited(boolean enabled) { textBox.setEnabled(enabled) ;  localeListBox.setEnabled(enabled) ;  if (!enabled) { textBox.addStyleDependentName("disabled") ;  localeListBox.addStyleDependentName("disabled") ;  }  else { textBox.removeStyleDependentName("disabled") ;  localeListBox.removeStyleDependentName("disabled") ;  }  edited = enabled ;  }  @Override public void setLabel(String label) { fieldBox.setLabel(label) ;  }  public void setLabel(String label, HorizontalAlignmentConstant alignment) { fieldBox.setLabel(label, alignment) ;  }  @Override public boolean isEdited() { return edited ;  }  @Override public void setValue(LocalizedTextProxy value) { this.values = value ;  if (value != null) { // display the text for the current locale textBox.setText(getLocalizedText(currentLocale)) ;  // if no text is available for the current locale, get the first one that is available if (textBox.getText() == null || textBox.getText().equals("")) { for (int i = 0 ;  i < localeListBox.getItemCount() ;  i++) { String locale = localeListBox.getValue(i) ;  String text = getLocalizedText(locale) ;  if (text != null && !text.equals("")) { textBox.setText(text) ;  localeListBox.setSelectedIndex(i) ;  currentLocale = locale ;  return ;  }  }  }  }  }  @Override public LocalizedTextProxy getValue() { if (edited) storeText(currentLocale) ;  if (isNull()) return null ;  else return values ;  }  * Defines that the field shall notify value changes * @param eventBus the event bus that will be used to fire the value change events public void notifyChanges(final EventBus eventBus) { if (eventBus != null) { textBox.addValueChangeHandler(new ValueChangeHandler<String>() { @Override public void onValueChange(ValueChangeEvent<String> arg0) { eventBus.fireEvent(new FieldValueChangeEvent( ImogLocalizedTextAreaBox.this)) ;  }  } ) ;  }  }  @Override public void showErrors(List<EditorError> errors) { errorLabel.showErrors(errors) ;  }  public void setLabelWidth(String width) { fieldBox.setLabelWidth(width) ;  }  * Sets the widget's width public void setBoxWidth(int width) { textBox.getElement().getStyle().setProperty("width", width + "px") ;  } }