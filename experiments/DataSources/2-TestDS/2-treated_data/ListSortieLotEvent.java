package org.imogene.epicam.client.event.list ; public class ListSortieLotEvent extends GwtEvent<ListSortieLotEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  private String searchText = null ;  public interface Handler extends EventHandler { void listSortieLot() ;  void listSortieLot(String searchText) ;  }  public ListSortieLotEvent() { }  public ListSortieLotEvent(String searchText) { this.searchText = searchText ;  }  @Override protected void dispatch(Handler handler) { if (searchText == null) handler.listSortieLot() ;  else handler.listSortieLot(searchText) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }