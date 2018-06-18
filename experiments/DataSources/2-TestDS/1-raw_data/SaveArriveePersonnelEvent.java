package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.ArriveePersonnelProxy;

/**
 * Event that is fired after that a ArriveePersonnel form has been saved
 * @author MEDES-IMPS
 */
public class SaveArriveePersonnelEvent
		extends
			GwtEvent<SaveArriveePersonnelEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveArriveePersonnel(ArriveePersonnelProxy value);
		void saveArriveePersonnel(ArriveePersonnelProxy value, String initField);
	}

	private final ArriveePersonnelProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveArriveePersonnelEvent(ArriveePersonnelProxy value) {
		this(value, null);
	}

	public SaveArriveePersonnelEvent(ArriveePersonnelProxy value,
			String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveArriveePersonnel(value);
		else
			handler.saveArriveePersonnel(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
