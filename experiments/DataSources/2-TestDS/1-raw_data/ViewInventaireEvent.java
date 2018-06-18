package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListInventaireEvent;

/**
 * Event that is fired in order to display a Inventaire form in view mode
 * @author MEDES-IMPS
 */
public class ViewInventaireEvent extends GwtEvent<ViewInventaireEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewInventaire(String entityId, GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewInventaireEvent(String entityId) {
		this(entityId, new ListInventaireEvent());
	}

	public ViewInventaireEvent(String entityId, GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewInventaire(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
