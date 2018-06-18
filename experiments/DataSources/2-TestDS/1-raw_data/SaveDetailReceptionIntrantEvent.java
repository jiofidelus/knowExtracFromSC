package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.DetailReceptionIntrantProxy;

/**
 * Event that is fired after that a DetailReceptionIntrant form has been saved
 * @author MEDES-IMPS
 */
public class SaveDetailReceptionIntrantEvent
		extends
			GwtEvent<SaveDetailReceptionIntrantEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveDetailReceptionIntrant(DetailReceptionIntrantProxy value);
		void saveDetailReceptionIntrant(DetailReceptionIntrantProxy value,
				String initField);
	}

	private final DetailReceptionIntrantProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveDetailReceptionIntrantEvent(DetailReceptionIntrantProxy value) {
		this(value, null);
	}

	public SaveDetailReceptionIntrantEvent(DetailReceptionIntrantProxy value,
			String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveDetailReceptionIntrant(value);
		else
			handler.saveDetailReceptionIntrant(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
