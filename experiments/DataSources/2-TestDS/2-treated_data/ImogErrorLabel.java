package org.imogene.web.client.ui.field.error ; public class ImogErrorLabel extends Composite { private static final Binder uiBinder = GWT.create(Binder.class) ;  interface Binder extends UiBinder<Widget, ImogErrorLabel> { }   private boolean errorDisplayed = false ;  @UiField DivElement errorLabel ;  public ImogErrorLabel() { initWidget(uiBinder.createAndBindUi(this)) ;  }  public void showErrors(List<EditorError> errors) { StringBuilder sb = new StringBuilder() ;  for (EditorError error : errors) { sb.append("\n").append(error.getMessage()) ;  }  if (sb.length() == 0) { errorLabel.setInnerText("") ;  errorLabel.getStyle().setDisplay(Display.NONE) ;  return ;  }  errorLabel.setInnerText(sb.substring(1)) ;  errorLabel.getStyle().setDisplay(Display.INLINE_BLOCK) ;  errorDisplayed = true ;   }   public void showError(String error) { StringBuilder sb = new StringBuilder() ;  sb.append("\n").append(error) ;  if (sb.length() == 0) { errorLabel.setInnerText("") ;  errorLabel.getStyle().setDisplay(Display.NONE) ;  return ;  }  errorLabel.setInnerText(sb.substring(1)) ;  errorLabel.getStyle().setDisplay(Display.INLINE_BLOCK) ;  errorDisplayed = true ;   }   public void hideErrors() { if(errorDisplayed) { errorLabel.setInnerText(null) ;  errorDisplayed = false ;  }  } }