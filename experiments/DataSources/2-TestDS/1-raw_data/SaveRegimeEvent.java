package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.RegimeProxy;

/**
 * Event that is fired after that a Regime form has been saved
 * @author MEDES-IMPS
 */
public class SaveRegimeEvent extends GwtEvent<SaveRegimeEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveRegime(RegimeProxy value);
		void saveRegime(RegimeProxy value, String initField);
	}

	private final RegimeProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveRegimeEvent(RegimeProxy value) {
		this(value, null);
	}

	public SaveRegimeEvent(RegimeProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveRegime(value);
		else
			handler.saveRegime(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
