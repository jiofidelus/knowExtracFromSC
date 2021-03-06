package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListReceptionEvent;

/**
 * Event that is fired in order to display a Reception form in creation mode
 * @author MEDES-IMPS
 */
public class CreateReceptionEvent
		extends
			GwtEvent<CreateReceptionEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewReception(GwtEvent<?> closeEvent);
	}

	public CreateReceptionEvent() {
		this(new ListReceptionEvent());
	}

	public CreateReceptionEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewReception(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
