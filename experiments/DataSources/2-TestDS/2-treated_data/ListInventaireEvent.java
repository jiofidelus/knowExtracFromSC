package org.imogene.epicam.client.event.list ; public class ListInventaireEvent extends GwtEvent<ListInventaireEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  private String searchText = null ;  public interface Handler extends EventHandler { void listInventaire() ;  void listInventaire(String searchText) ;  }  public ListInventaireEvent() { }  public ListInventaireEvent(String searchText) { this.searchText = searchText ;  }  @Override protected void dispatch(Handler handler) { if (searchText == null) handler.listInventaire() ;  else handler.listInventaire(searchText) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }