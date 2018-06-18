package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.SortieLotProxy;

/**
 * Event that is fired after that a SortieLot form has been saved
 * @author MEDES-IMPS
 */
public class SaveSortieLotEvent extends GwtEvent<SaveSortieLotEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveSortieLot(SortieLotProxy value);
		void saveSortieLot(SortieLotProxy value, String initField);
	}

	private final SortieLotProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveSortieLotEvent(SortieLotProxy value) {
		this(value, null);
	}

	public SaveSortieLotEvent(SortieLotProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveSortieLot(value);
		else
			handler.saveSortieLot(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
