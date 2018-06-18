package org.imogene.epicam.client.event.save ; public class SaveIntrantEvent extends GwtEvent<SaveIntrantEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  public interface Handler extends EventHandler { void saveIntrant(IntrantProxy value) ;  void saveIntrant(IntrantProxy value, String initField) ;  }  private final IntrantProxy value ;  private final String initField ;  public SaveIntrantEvent(IntrantProxy value) { this(value, null) ;  }  public SaveIntrantEvent(IntrantProxy value, String initField) { this.value = value ;  this.initField = initField ;  }  @Override protected void dispatch(Handler handler) { if (initField == null) handler.saveIntrant(value) ;  else handler.saveIntrant(value, initField) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }