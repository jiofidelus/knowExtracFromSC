package org.imogene.epicam.client.event.save ; public class SaveLaboratoireReferenceEvent extends GwtEvent<SaveLaboratoireReferenceEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  public interface Handler extends EventHandler { void saveLaboratoireReference(LaboratoireReferenceProxy value) ;  void saveLaboratoireReference(LaboratoireReferenceProxy value, String initField) ;  }  private final LaboratoireReferenceProxy value ;  private final String initField ;  public SaveLaboratoireReferenceEvent(LaboratoireReferenceProxy value) { this(value, null) ;  }  public SaveLaboratoireReferenceEvent(LaboratoireReferenceProxy value, String initField) { this.value = value ;  this.initField = initField ;  }  @Override protected void dispatch(Handler handler) { if (initField == null) handler.saveLaboratoireReference(value) ;  else handler.saveLaboratoireReference(value, initField) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }