package org.imogene.epicam.client.event.view ; public class ViewPersonnelEvent extends GwtEvent<ViewPersonnelEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  public interface Handler extends EventHandler { void viewPersonnel(String entityId, GwtEvent<?> closeEvent) ;  }  private final String entityId ;  private final GwtEvent<?> closeEvent ;  public ViewPersonnelEvent(String entityId) { this(entityId, new ListPersonnelEvent()) ;  }  public ViewPersonnelEvent(String entityId, GwtEvent<?> closeEvent) { this.entityId = entityId ;  this.closeEvent = closeEvent ;  }  @Override protected void dispatch(Handler handler) { handler.viewPersonnel(entityId, closeEvent) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }