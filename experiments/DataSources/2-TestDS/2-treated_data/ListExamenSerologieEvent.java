package org.imogene.epicam.client.event.list ; public class ListExamenSerologieEvent extends GwtEvent<ListExamenSerologieEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  private String searchText = null ;  public interface Handler extends EventHandler { void listExamenSerologie() ;  void listExamenSerologie(String searchText) ;  }  public ListExamenSerologieEvent() { }  public ListExamenSerologieEvent(String searchText) { this.searchText = searchText ;  }  @Override protected void dispatch(Handler handler) { if (searchText == null) handler.listExamenSerologie() ;  else handler.listExamenSerologie(searchText) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }