package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListTransfertReferenceEvent;

/**
 * Event that is fired in order to display a TransfertReference form in view mode
 * @author MEDES-IMPS
 */
public class ViewTransfertReferenceEvent
		extends
			GwtEvent<ViewTransfertReferenceEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewTransfertReference(String entityId, GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewTransfertReferenceEvent(String entityId) {
		this(entityId, new ListTransfertReferenceEvent());
	}

	public ViewTransfertReferenceEvent(String entityId, GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewTransfertReference(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
