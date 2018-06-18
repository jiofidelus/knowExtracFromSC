package org.imogene.epicam.client.event.save ; public class SavePersonnelEvent extends GwtEvent<SavePersonnelEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  public interface Handler extends EventHandler { void savePersonnel(PersonnelProxy value) ;  void savePersonnel(PersonnelProxy value, String initField) ;  }  private final PersonnelProxy value ;  private final String initField ;  public SavePersonnelEvent(PersonnelProxy value) { this(value, null) ;  }  public SavePersonnelEvent(PersonnelProxy value, String initField) { this.value = value ;  this.initField = initField ;  }  @Override protected void dispatch(Handler handler) { if (initField == null) handler.savePersonnel(value) ;  else handler.savePersonnel(value, initField) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }