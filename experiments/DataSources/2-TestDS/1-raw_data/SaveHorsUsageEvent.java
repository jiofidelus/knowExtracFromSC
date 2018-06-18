package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.HorsUsageProxy;

/**
 * Event that is fired after that a HorsUsage form has been saved
 * @author MEDES-IMPS
 */
public class SaveHorsUsageEvent extends GwtEvent<SaveHorsUsageEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveHorsUsage(HorsUsageProxy value);
		void saveHorsUsage(HorsUsageProxy value, String initField);
	}

	private final HorsUsageProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveHorsUsageEvent(HorsUsageProxy value) {
		this(value, null);
	}

	public SaveHorsUsageEvent(HorsUsageProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveHorsUsage(value);
		else
			handler.saveHorsUsage(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
