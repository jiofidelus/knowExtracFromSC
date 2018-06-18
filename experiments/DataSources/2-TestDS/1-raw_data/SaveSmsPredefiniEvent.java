package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.SmsPredefiniProxy;

/**
 * Event that is fired after that a SmsPredefini form has been saved
 * @author MEDES-IMPS
 */
public class SaveSmsPredefiniEvent
		extends
			GwtEvent<SaveSmsPredefiniEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveSmsPredefini(SmsPredefiniProxy value);
		void saveSmsPredefini(SmsPredefiniProxy value, String initField);
	}

	private final SmsPredefiniProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveSmsPredefiniEvent(SmsPredefiniProxy value) {
		this(value, null);
	}

	public SaveSmsPredefiniEvent(SmsPredefiniProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveSmsPredefini(value);
		else
			handler.saveSmsPredefini(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
