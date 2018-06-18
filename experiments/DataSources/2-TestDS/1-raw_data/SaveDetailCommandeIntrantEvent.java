package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.DetailCommandeIntrantProxy;

/**
 * Event that is fired after that a DetailCommandeIntrant form has been saved
 * @author MEDES-IMPS
 */
public class SaveDetailCommandeIntrantEvent
		extends
			GwtEvent<SaveDetailCommandeIntrantEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveDetailCommandeIntrant(DetailCommandeIntrantProxy value);
		void saveDetailCommandeIntrant(DetailCommandeIntrantProxy value,
				String initField);
	}

	private final DetailCommandeIntrantProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveDetailCommandeIntrantEvent(DetailCommandeIntrantProxy value) {
		this(value, null);
	}

	public SaveDetailCommandeIntrantEvent(DetailCommandeIntrantProxy value,
			String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveDetailCommandeIntrant(value);
		else
			handler.saveDetailCommandeIntrant(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
