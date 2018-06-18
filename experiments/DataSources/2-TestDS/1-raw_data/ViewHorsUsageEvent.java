package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListHorsUsageEvent;

/**
 * Event that is fired in order to display a HorsUsage form in view mode
 * @author MEDES-IMPS
 */
public class ViewHorsUsageEvent extends GwtEvent<ViewHorsUsageEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewHorsUsage(String entityId, GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewHorsUsageEvent(String entityId) {
		this(entityId, new ListHorsUsageEvent());
	}

	public ViewHorsUsageEvent(String entityId, GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewHorsUsage(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
