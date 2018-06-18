package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListRegimeEvent;

/**
 * Event that is fired in order to display a Regime form in creation mode
 * @author MEDES-IMPS
 */
public class CreateRegimeEvent extends GwtEvent<CreateRegimeEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewRegime(GwtEvent<?> closeEvent);
	}

	public CreateRegimeEvent() {
		this(new ListRegimeEvent());
	}

	public CreateRegimeEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewRegime(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
