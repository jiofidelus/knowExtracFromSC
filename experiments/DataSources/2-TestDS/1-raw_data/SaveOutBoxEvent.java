package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.OutBoxProxy;

/**
 * Event that is fired after that a OutBox form has been saved
 * @author MEDES-IMPS
 */
public class SaveOutBoxEvent extends GwtEvent<SaveOutBoxEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveOutBox(OutBoxProxy value);
		void saveOutBox(OutBoxProxy value, String initField);
	}

	private final OutBoxProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveOutBoxEvent(OutBoxProxy value) {
		this(value, null);
	}

	public SaveOutBoxEvent(OutBoxProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveOutBox(value);
		else
			handler.saveOutBox(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
