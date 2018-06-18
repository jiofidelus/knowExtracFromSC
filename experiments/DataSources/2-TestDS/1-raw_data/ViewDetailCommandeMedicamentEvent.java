package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListDetailCommandeMedicamentEvent;

/**
 * Event that is fired in order to display a DetailCommandeMedicament form in view mode
 * @author MEDES-IMPS
 */
public class ViewDetailCommandeMedicamentEvent
		extends
			GwtEvent<ViewDetailCommandeMedicamentEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewDetailCommandeMedicament(String entityId,
				GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewDetailCommandeMedicamentEvent(String entityId) {
		this(entityId, new ListDetailCommandeMedicamentEvent());
	}

	public ViewDetailCommandeMedicamentEvent(String entityId,
			GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewDetailCommandeMedicament(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
