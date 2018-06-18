package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListDetailCommandeIntrantEvent;

/**
 * Event that is fired in order to display a DetailCommandeIntrant form in creation mode
 * @author MEDES-IMPS
 */
public class CreateDetailCommandeIntrantEvent
		extends
			GwtEvent<CreateDetailCommandeIntrantEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewDetailCommandeIntrant(GwtEvent<?> closeEvent);
	}

	public CreateDetailCommandeIntrantEvent() {
		this(new ListDetailCommandeIntrantEvent());
	}

	public CreateDetailCommandeIntrantEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewDetailCommandeIntrant(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
