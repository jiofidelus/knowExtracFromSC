package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.IntrantProxy;

/**
 * Event that is fired after that a Intrant form has been saved
 * @author MEDES-IMPS
 */
public class SaveIntrantEvent extends GwtEvent<SaveIntrantEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveIntrant(IntrantProxy value);
		void saveIntrant(IntrantProxy value, String initField);
	}

	private final IntrantProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveIntrantEvent(IntrantProxy value) {
		this(value, null);
	}

	public SaveIntrantEvent(IntrantProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveIntrant(value);
		else
			handler.saveIntrant(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
