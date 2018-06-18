package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListDetailRavitaillementEvent;

/**
 * Event that is fired in order to display a DetailRavitaillement form in creation mode
 * @author MEDES-IMPS
 */
public class CreateDetailRavitaillementEvent
		extends
			GwtEvent<CreateDetailRavitaillementEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewDetailRavitaillement(GwtEvent<?> closeEvent);
	}

	public CreateDetailRavitaillementEvent() {
		this(new ListDetailRavitaillementEvent());
	}

	public CreateDetailRavitaillementEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewDetailRavitaillement(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
