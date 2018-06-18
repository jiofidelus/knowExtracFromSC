package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListCasTuberculoseEvent;

/**
 * Event that is fired in order to display a CasTuberculose form in view mode
 * @author MEDES-IMPS
 */
public class ViewCasTuberculoseEvent
		extends
			GwtEvent<ViewCasTuberculoseEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewCasTuberculose(String entityId, GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewCasTuberculoseEvent(String entityId) {
		this(entityId, new ListCasTuberculoseEvent());
	}

	public ViewCasTuberculoseEvent(String entityId, GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewCasTuberculose(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
