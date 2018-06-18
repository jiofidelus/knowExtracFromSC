package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListUtilisateurEvent;

/**
 * Event that is fired in order to display a Utilisateur form in view mode
 * @author MEDES-IMPS
 */
public class ViewUtilisateurEvent
		extends
			GwtEvent<ViewUtilisateurEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewUtilisateur(String entityId, GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewUtilisateurEvent(String entityId) {
		this(entityId, new ListUtilisateurEvent());
	}

	public ViewUtilisateurEvent(String entityId, GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewUtilisateur(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
