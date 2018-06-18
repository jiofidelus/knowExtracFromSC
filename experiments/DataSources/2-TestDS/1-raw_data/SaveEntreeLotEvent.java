package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.EntreeLotProxy;

/**
 * Event that is fired after that a EntreeLot form has been saved
 * @author MEDES-IMPS
 */
public class SaveEntreeLotEvent extends GwtEvent<SaveEntreeLotEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveEntreeLot(EntreeLotProxy value);
		void saveEntreeLot(EntreeLotProxy value, String initField);
	}

	private final EntreeLotProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveEntreeLotEvent(EntreeLotProxy value) {
		this(value, null);
	}

	public SaveEntreeLotEvent(EntreeLotProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveEntreeLot(value);
		else
			handler.saveEntreeLot(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
