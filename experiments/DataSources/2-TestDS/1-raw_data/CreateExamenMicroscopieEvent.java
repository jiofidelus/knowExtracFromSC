package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListExamenMicroscopieEvent;

/**
 * Event that is fired in order to display a ExamenMicroscopie form in creation mode
 * @author MEDES-IMPS
 */
public class CreateExamenMicroscopieEvent
		extends
			GwtEvent<CreateExamenMicroscopieEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewExamenMicroscopie(GwtEvent<?> closeEvent);
	}

	public CreateExamenMicroscopieEvent() {
		this(new ListExamenMicroscopieEvent());
	}

	public CreateExamenMicroscopieEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewExamenMicroscopie(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
