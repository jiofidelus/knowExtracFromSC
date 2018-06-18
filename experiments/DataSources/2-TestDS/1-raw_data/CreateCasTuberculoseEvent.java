package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListCasTuberculoseEvent;

/**
 * Event that is fired in order to display a CasTuberculose form in creation mode
 * @author MEDES-IMPS
 */
public class CreateCasTuberculoseEvent
		extends
			GwtEvent<CreateCasTuberculoseEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewCasTuberculose(GwtEvent<?> closeEvent);
	}

	public CreateCasTuberculoseEvent() {
		this(new ListCasTuberculoseEvent());
	}

	public CreateCasTuberculoseEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewCasTuberculose(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
