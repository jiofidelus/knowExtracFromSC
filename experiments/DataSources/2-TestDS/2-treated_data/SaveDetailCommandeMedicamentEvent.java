package org.imogene.epicam.client.event.save ; public class SaveDetailCommandeMedicamentEvent extends GwtEvent<SaveDetailCommandeMedicamentEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  public interface Handler extends EventHandler { void saveDetailCommandeMedicament(DetailCommandeMedicamentProxy value) ;  void saveDetailCommandeMedicament(DetailCommandeMedicamentProxy value, String initField) ;  }  private final DetailCommandeMedicamentProxy value ;  private final String initField ;  public SaveDetailCommandeMedicamentEvent(DetailCommandeMedicamentProxy value) { this(value, null) ;  }  public SaveDetailCommandeMedicamentEvent( DetailCommandeMedicamentProxy value, String initField) { this.value = value ;  this.initField = initField ;  }  @Override protected void dispatch(Handler handler) { if (initField == null) handler.saveDetailCommandeMedicament(value) ;  else handler.saveDetailCommandeMedicament(value, initField) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }