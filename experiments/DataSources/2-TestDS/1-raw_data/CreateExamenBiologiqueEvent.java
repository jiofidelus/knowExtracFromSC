package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListExamenBiologiqueEvent;

/**
 * Event that is fired in order to display a ExamenBiologique form in creation mode
 * @author MEDES-IMPS
 */
public class CreateExamenBiologiqueEvent
		extends
			GwtEvent<CreateExamenBiologiqueEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewExamenBiologique(GwtEvent<?> closeEvent);
	}

	public CreateExamenBiologiqueEvent() {
		this(new ListExamenBiologiqueEvent());
	}

	public CreateExamenBiologiqueEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewExamenBiologique(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
