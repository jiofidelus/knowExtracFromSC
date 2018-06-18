package org.imogene.epicam.client.event.view ; public class ViewMedicamentEvent extends GwtEvent<ViewMedicamentEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  public interface Handler extends EventHandler { void viewMedicament(String entityId, GwtEvent<?> closeEvent) ;  }  private final String entityId ;  private final GwtEvent<?> closeEvent ;  public ViewMedicamentEvent(String entityId) { this(entityId, new ListMedicamentEvent()) ;  }  public ViewMedicamentEvent(String entityId, GwtEvent<?> closeEvent) { this.entityId = entityId ;  this.closeEvent = closeEvent ;  }  @Override protected void dispatch(Handler handler) { handler.viewMedicament(entityId, closeEvent) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }