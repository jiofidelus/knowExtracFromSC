package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.LotProxy;

/**
 * Event that is fired after that a Lot form has been saved
 * @author MEDES-IMPS
 */
public class SaveLotEvent extends GwtEvent<SaveLotEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveLot(LotProxy value);
		void saveLot(LotProxy value, String initField);
	}

	private final LotProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveLotEvent(LotProxy value) {
		this(value, null);
	}

	public SaveLotEvent(LotProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveLot(value);
		else
			handler.saveLot(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
