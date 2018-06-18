package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListSmsPredefiniEvent;

/**
 * Event that is fired in order to display a SmsPredefini form in view mode
 * @author MEDES-IMPS
 */
public class ViewSmsPredefiniEvent
		extends
			GwtEvent<ViewSmsPredefiniEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewSmsPredefini(String entityId, GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewSmsPredefiniEvent(String entityId) {
		this(entityId, new ListSmsPredefiniEvent());
	}

	public ViewSmsPredefiniEvent(String entityId, GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewSmsPredefini(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
