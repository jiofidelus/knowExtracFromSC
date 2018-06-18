package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.RendezVousProxy;

/**
 * Event that is fired after that a RendezVous form has been saved
 * @author MEDES-IMPS
 */
public class SaveRendezVousEvent extends GwtEvent<SaveRendezVousEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveRendezVous(RendezVousProxy value);
		void saveRendezVous(RendezVousProxy value, String initField);
	}

	private final RendezVousProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveRendezVousEvent(RendezVousProxy value) {
		this(value, null);
	}

	public SaveRendezVousEvent(RendezVousProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveRendezVous(value);
		else
			handler.saveRendezVous(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
