package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListOutBoxEvent;

/**
 * Event that is fired in order to display a OutBox form in creation mode
 * @author MEDES-IMPS
 */
public class CreateOutBoxEvent extends GwtEvent<CreateOutBoxEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewOutBox(GwtEvent<?> closeEvent);
	}

	public CreateOutBoxEvent() {
		this(new ListOutBoxEvent());
	}

	public CreateOutBoxEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewOutBox(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
