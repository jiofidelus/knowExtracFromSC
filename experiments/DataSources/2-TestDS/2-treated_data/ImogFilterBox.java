package org.imogene.web.client.ui.table.filter ; public class ImogFilterBox extends Composite { private static final Binder uiBinder = GWT.create(Binder.class) ;  interface Binder extends UiBinder<Widget, ImogFilterBox> { }  @UiField VerticalPanel filterPanel ;   @UiField Label filterLabel ;  @UiField (provided=true) Widget filterWidget ;   public ImogFilterBox(Widget widget) { filterWidget = widget ;  initWidget(uiBinder.createAndBindUi(this)) ;   }  public void setFilterLabel(String label) { filterLabel.setText(label) ;  } }