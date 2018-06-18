package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListPatientEvent;

/**
 * Event that is fired in order to display a Patient form in view mode
 * @author MEDES-IMPS
 */
public class ViewPatientEvent extends GwtEvent<ViewPatientEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewPatient(String entityId, GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewPatientEvent(String entityId) {
		this(entityId, new ListPatientEvent());
	}

	public ViewPatientEvent(String entityId, GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewPatient(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
