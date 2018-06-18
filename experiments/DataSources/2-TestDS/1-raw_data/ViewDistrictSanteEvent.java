package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListDistrictSanteEvent;

/**
 * Event that is fired in order to display a DistrictSante form in view mode
 * @author MEDES-IMPS
 */
public class ViewDistrictSanteEvent
		extends
			GwtEvent<ViewDistrictSanteEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewDistrictSante(String entityId, GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewDistrictSanteEvent(String entityId) {
		this(entityId, new ListDistrictSanteEvent());
	}

	public ViewDistrictSanteEvent(String entityId, GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewDistrictSante(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
