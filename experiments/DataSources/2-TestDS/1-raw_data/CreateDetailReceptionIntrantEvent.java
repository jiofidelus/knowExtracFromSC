package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListDetailReceptionIntrantEvent;

/**
 * Event that is fired in order to display a DetailReceptionIntrant form in creation mode
 * @author MEDES-IMPS
 */
public class CreateDetailReceptionIntrantEvent
		extends
			GwtEvent<CreateDetailReceptionIntrantEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewDetailReceptionIntrant(GwtEvent<?> closeEvent);
	}

	public CreateDetailReceptionIntrantEvent() {
		this(new ListDetailReceptionIntrantEvent());
	}

	public CreateDetailReceptionIntrantEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewDetailReceptionIntrant(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
