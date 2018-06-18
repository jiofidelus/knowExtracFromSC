package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.ExamenATBProxy;

/**
 * Event that is fired after that a ExamenATB form has been saved
 * @author MEDES-IMPS
 */
public class SaveExamenATBEvent extends GwtEvent<SaveExamenATBEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveExamenATB(ExamenATBProxy value);
		void saveExamenATB(ExamenATBProxy value, String initField);
	}

	private final ExamenATBProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveExamenATBEvent(ExamenATBProxy value) {
		this(value, null);
	}

	public SaveExamenATBEvent(ExamenATBProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveExamenATB(value);
		else
			handler.saveExamenATB(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
