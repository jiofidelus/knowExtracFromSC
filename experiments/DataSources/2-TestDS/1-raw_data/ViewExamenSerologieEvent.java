package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListExamenSerologieEvent;

/**
 * Event that is fired in order to display a ExamenSerologie form in view mode
 * @author MEDES-IMPS
 */
public class ViewExamenSerologieEvent
		extends
			GwtEvent<ViewExamenSerologieEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewExamenSerologie(String entityId, GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewExamenSerologieEvent(String entityId) {
		this(entityId, new ListExamenSerologieEvent());
	}

	public ViewExamenSerologieEvent(String entityId, GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewExamenSerologie(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
