package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListDetailCommandeMedicamentEvent;

/**
 * Event that is fired in order to display a DetailCommandeMedicament form in creation mode
 * @author MEDES-IMPS
 */
public class CreateDetailCommandeMedicamentEvent
		extends
			GwtEvent<CreateDetailCommandeMedicamentEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewDetailCommandeMedicament(GwtEvent<?> closeEvent);
	}

	public CreateDetailCommandeMedicamentEvent() {
		this(new ListDetailCommandeMedicamentEvent());
	}

	public CreateDetailCommandeMedicamentEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewDetailCommandeMedicament(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
