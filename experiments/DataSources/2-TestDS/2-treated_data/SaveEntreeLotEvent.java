package org.imogene.epicam.client.event.save ; public class SaveEntreeLotEvent extends GwtEvent<SaveEntreeLotEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  public interface Handler extends EventHandler { void saveEntreeLot(EntreeLotProxy value) ;  void saveEntreeLot(EntreeLotProxy value, String initField) ;  }  private final EntreeLotProxy value ;  private final String initField ;  public SaveEntreeLotEvent(EntreeLotProxy value) { this(value, null) ;  }  public SaveEntreeLotEvent(EntreeLotProxy value, String initField) { this.value = value ;  this.initField = initField ;  }  @Override protected void dispatch(Handler handler) { if (initField == null) handler.saveEntreeLot(value) ;  else handler.saveEntreeLot(value, initField) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }