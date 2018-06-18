package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListDetailCommandeIntrantEvent;

/**
 * Event that is fired in order to display a DetailCommandeIntrant form in view mode
 * @author MEDES-IMPS
 */
public class ViewDetailCommandeIntrantEvent
		extends
			GwtEvent<ViewDetailCommandeIntrantEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewDetailCommandeIntrant(String entityId, GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewDetailCommandeIntrantEvent(String entityId) {
		this(entityId, new ListDetailCommandeIntrantEvent());
	}

	public ViewDetailCommandeIntrantEvent(String entityId,
			GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewDetailCommandeIntrant(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
