package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListRavitaillementEvent;

/**
 * Event that is fired in order to display a Ravitaillement form in view mode
 * @author MEDES-IMPS
 */
public class ViewRavitaillementEvent
		extends
			GwtEvent<ViewRavitaillementEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewRavitaillement(String entityId, GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewRavitaillementEvent(String entityId) {
		this(entityId, new ListRavitaillementEvent());
	}

	public ViewRavitaillementEvent(String entityId, GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewRavitaillement(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
