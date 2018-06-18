package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.RavitaillementProxy;

/**
 * Event that is fired after that a Ravitaillement form has been saved
 * @author MEDES-IMPS
 */
public class SaveRavitaillementEvent
		extends
			GwtEvent<SaveRavitaillementEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveRavitaillement(RavitaillementProxy value);
		void saveRavitaillement(RavitaillementProxy value, String initField);
	}

	private final RavitaillementProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveRavitaillementEvent(RavitaillementProxy value) {
		this(value, null);
	}

	public SaveRavitaillementEvent(RavitaillementProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveRavitaillement(value);
		else
			handler.saveRavitaillement(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
