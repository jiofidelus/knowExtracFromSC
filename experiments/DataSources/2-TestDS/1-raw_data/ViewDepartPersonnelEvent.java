package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListDepartPersonnelEvent;

/**
 * Event that is fired in order to display a DepartPersonnel form in view mode
 * @author MEDES-IMPS
 */
public class ViewDepartPersonnelEvent
		extends
			GwtEvent<ViewDepartPersonnelEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewDepartPersonnel(String entityId, GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewDepartPersonnelEvent(String entityId) {
		this(entityId, new ListDepartPersonnelEvent());
	}

	public ViewDepartPersonnelEvent(String entityId, GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewDepartPersonnel(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
