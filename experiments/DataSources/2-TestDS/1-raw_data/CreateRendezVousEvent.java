package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListRendezVousEvent;

/**
 * Event that is fired in order to display a RendezVous form in creation mode
 * @author MEDES-IMPS
 */
public class CreateRendezVousEvent
		extends
			GwtEvent<CreateRendezVousEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewRendezVous(GwtEvent<?> closeEvent);
	}

	public CreateRendezVousEvent() {
		this(new ListRendezVousEvent());
	}

	public CreateRendezVousEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewRendezVous(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
