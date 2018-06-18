package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.DetailCommandeMedicamentProxy;

/**
 * Event that is fired after that a DetailCommandeMedicament form has been saved
 * @author MEDES-IMPS
 */
public class SaveDetailCommandeMedicamentEvent
		extends
			GwtEvent<SaveDetailCommandeMedicamentEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveDetailCommandeMedicament(DetailCommandeMedicamentProxy value);
		void saveDetailCommandeMedicament(DetailCommandeMedicamentProxy value,
				String initField);
	}

	private final DetailCommandeMedicamentProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveDetailCommandeMedicamentEvent(DetailCommandeMedicamentProxy value) {
		this(value, null);
	}

	public SaveDetailCommandeMedicamentEvent(
			DetailCommandeMedicamentProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveDetailCommandeMedicament(value);
		else
			handler.saveDetailCommandeMedicament(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
