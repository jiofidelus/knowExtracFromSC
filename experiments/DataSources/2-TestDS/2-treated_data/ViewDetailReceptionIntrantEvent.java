package org.imogene.epicam.client.event.view ; public class ViewDetailReceptionIntrantEvent extends GwtEvent<ViewDetailReceptionIntrantEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  public interface Handler extends EventHandler { void viewDetailReceptionIntrant(String entityId, GwtEvent<?> closeEvent) ;  }  private final String entityId ;  private final GwtEvent<?> closeEvent ;  public ViewDetailReceptionIntrantEvent(String entityId) { this(entityId, new ListDetailReceptionIntrantEvent()) ;  }  public ViewDetailReceptionIntrantEvent(String entityId, GwtEvent<?> closeEvent) { this.entityId = entityId ;  this.closeEvent = closeEvent ;  }  @Override protected void dispatch(Handler handler) { handler.viewDetailReceptionIntrant(entityId, closeEvent) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }