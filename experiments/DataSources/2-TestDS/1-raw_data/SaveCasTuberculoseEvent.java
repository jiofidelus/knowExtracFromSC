package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.CasTuberculoseProxy;

/**
 * Event that is fired after that a CasTuberculose form has been saved
 * @author MEDES-IMPS
 */
public class SaveCasTuberculoseEvent
		extends
			GwtEvent<SaveCasTuberculoseEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveCasTuberculose(CasTuberculoseProxy value);
		void saveCasTuberculose(CasTuberculoseProxy value, String initField);
	}

	private final CasTuberculoseProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveCasTuberculoseEvent(CasTuberculoseProxy value) {
		this(value, null);
	}

	public SaveCasTuberculoseEvent(CasTuberculoseProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveCasTuberculose(value);
		else
			handler.saveCasTuberculose(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
