package org.imogene.epicam.client.event.view ; public class ViewCasIndexEvent extends GwtEvent<ViewCasIndexEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  public interface Handler extends EventHandler { void viewCasIndex(String entityId, GwtEvent<?> closeEvent) ;  }  private final String entityId ;  private final GwtEvent<?> closeEvent ;  public ViewCasIndexEvent(String entityId) { this(entityId, new ListCasIndexEvent()) ;  }  public ViewCasIndexEvent(String entityId, GwtEvent<?> closeEvent) { this.entityId = entityId ;  this.closeEvent = closeEvent ;  }  @Override protected void dispatch(Handler handler) { handler.viewCasIndex(entityId, closeEvent) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }