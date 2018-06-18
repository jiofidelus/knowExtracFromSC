package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListExamenATBEvent;

/**
 * Event that is fired in order to display a ExamenATB form in creation mode
 * @author MEDES-IMPS
 */
public class CreateExamenATBEvent
		extends
			GwtEvent<CreateExamenATBEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewExamenATB(GwtEvent<?> closeEvent);
	}

	public CreateExamenATBEvent() {
		this(new ListExamenATBEvent());
	}

	public CreateExamenATBEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewExamenATB(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
