package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.DetailRavitaillementProxy;

/**
 * Event that is fired after that a DetailRavitaillement form has been saved
 * @author MEDES-IMPS
 */
public class SaveDetailRavitaillementEvent
		extends
			GwtEvent<SaveDetailRavitaillementEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveDetailRavitaillement(DetailRavitaillementProxy value);
		void saveDetailRavitaillement(DetailRavitaillementProxy value,
				String initField);
	}

	private final DetailRavitaillementProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveDetailRavitaillementEvent(DetailRavitaillementProxy value) {
		this(value, null);
	}

	public SaveDetailRavitaillementEvent(DetailRavitaillementProxy value,
			String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveDetailRavitaillement(value);
		else
			handler.saveDetailRavitaillement(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
