package org.imogene.epicam.client.event.list ; public class ListDetailReceptionIntrantEvent extends GwtEvent<ListDetailReceptionIntrantEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  private String searchText = null ;  public interface Handler extends EventHandler { void listDetailReceptionIntrant() ;  void listDetailReceptionIntrant(String searchText) ;  }  public ListDetailReceptionIntrantEvent() { }  public ListDetailReceptionIntrantEvent(String searchText) { this.searchText = searchText ;  }  @Override protected void dispatch(Handler handler) { if (searchText == null) handler.listDetailReceptionIntrant() ;  else handler.listDetailReceptionIntrant(searchText) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }