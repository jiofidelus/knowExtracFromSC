package org.imogene.epicam.client.event.create ; public class CreateSmsPredefiniEvent extends GwtEvent<CreateSmsPredefiniEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  private final GwtEvent<?> closeEvent ;  public interface Handler extends EventHandler { void createNewSmsPredefini(GwtEvent<?> closeEvent) ;  }  public CreateSmsPredefiniEvent() { this(new ListSmsPredefiniEvent()) ;  }  public CreateSmsPredefiniEvent(GwtEvent<?> closeEvent) { this.closeEvent = closeEvent ;  }  @Override protected void dispatch(Handler handler) { handler.createNewSmsPredefini(closeEvent) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }