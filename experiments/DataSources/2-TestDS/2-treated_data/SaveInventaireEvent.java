package org.imogene.epicam.client.event.save ; public class SaveInventaireEvent extends GwtEvent<SaveInventaireEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  public interface Handler extends EventHandler { void saveInventaire(InventaireProxy value) ;  void saveInventaire(InventaireProxy value, String initField) ;  }  private final InventaireProxy value ;  private final String initField ;  public SaveInventaireEvent(InventaireProxy value) { this(value, null) ;  }  public SaveInventaireEvent(InventaireProxy value, String initField) { this.value = value ;  this.initField = initField ;  }  @Override protected void dispatch(Handler handler) { if (initField == null) handler.saveInventaire(value) ;  else handler.saveInventaire(value, initField) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }