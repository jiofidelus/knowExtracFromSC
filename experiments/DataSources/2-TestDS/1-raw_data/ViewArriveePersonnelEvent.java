package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListArriveePersonnelEvent;

/**
 * Event that is fired in order to display a ArriveePersonnel form in view mode
 * @author MEDES-IMPS
 */
public class ViewArriveePersonnelEvent
		extends
			GwtEvent<ViewArriveePersonnelEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewArriveePersonnel(String entityId, GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewArriveePersonnelEvent(String entityId) {
		this(entityId, new ListArriveePersonnelEvent());
	}

	public ViewArriveePersonnelEvent(String entityId, GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewArriveePersonnel(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
