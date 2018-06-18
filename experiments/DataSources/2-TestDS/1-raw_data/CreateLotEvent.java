package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListLotEvent;

/**
 * Event that is fired in order to display a Lot form in creation mode
 * @author MEDES-IMPS
 */
public class CreateLotEvent extends GwtEvent<CreateLotEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewLot(GwtEvent<?> closeEvent);
	}

	public CreateLotEvent() {
		this(new ListLotEvent());
	}

	public CreateLotEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewLot(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
