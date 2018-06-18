package org.imogene.epicam.client.event.create ; public class CreateIntrantEvent extends GwtEvent<CreateIntrantEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  private final GwtEvent<?> closeEvent ;  public interface Handler extends EventHandler { void createNewIntrant(GwtEvent<?> closeEvent) ;  }  public CreateIntrantEvent() { this(new ListIntrantEvent()) ;  }  public CreateIntrantEvent(GwtEvent<?> closeEvent) { this.closeEvent = closeEvent ;  }  @Override protected void dispatch(Handler handler) { handler.createNewIntrant(closeEvent) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }