package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListSortieLotEvent;

/**
 * Event that is fired in order to display a SortieLot form in creation mode
 * @author MEDES-IMPS
 */
public class CreateSortieLotEvent
		extends
			GwtEvent<CreateSortieLotEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewSortieLot(GwtEvent<?> closeEvent);
	}

	public CreateSortieLotEvent() {
		this(new ListSortieLotEvent());
	}

	public CreateSortieLotEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewSortieLot(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
