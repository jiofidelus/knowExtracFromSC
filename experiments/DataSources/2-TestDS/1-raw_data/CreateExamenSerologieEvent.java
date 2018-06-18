package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListExamenSerologieEvent;

/**
 * Event that is fired in order to display a ExamenSerologie form in creation mode
 * @author MEDES-IMPS
 */
public class CreateExamenSerologieEvent
		extends
			GwtEvent<CreateExamenSerologieEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewExamenSerologie(GwtEvent<?> closeEvent);
	}

	public CreateExamenSerologieEvent() {
		this(new ListExamenSerologieEvent());
	}

	public CreateExamenSerologieEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewExamenSerologie(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
