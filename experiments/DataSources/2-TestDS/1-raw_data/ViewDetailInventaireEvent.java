package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListDetailInventaireEvent;

/**
 * Event that is fired in order to display a DetailInventaire form in view mode
 * @author MEDES-IMPS
 */
public class ViewDetailInventaireEvent
		extends
			GwtEvent<ViewDetailInventaireEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewDetailInventaire(String entityId, GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewDetailInventaireEvent(String entityId) {
		this(entityId, new ListDetailInventaireEvent());
	}

	public ViewDetailInventaireEvent(String entityId, GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewDetailInventaire(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
