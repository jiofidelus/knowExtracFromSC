package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.ExamenMicroscopieProxy;

/**
 * Event that is fired after that a ExamenMicroscopie form has been saved
 * @author MEDES-IMPS
 */
public class SaveExamenMicroscopieEvent
		extends
			GwtEvent<SaveExamenMicroscopieEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveExamenMicroscopie(ExamenMicroscopieProxy value);
		void saveExamenMicroscopie(ExamenMicroscopieProxy value,
				String initField);
	}

	private final ExamenMicroscopieProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveExamenMicroscopieEvent(ExamenMicroscopieProxy value) {
		this(value, null);
	}

	public SaveExamenMicroscopieEvent(ExamenMicroscopieProxy value,
			String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveExamenMicroscopie(value);
		else
			handler.saveExamenMicroscopie(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
