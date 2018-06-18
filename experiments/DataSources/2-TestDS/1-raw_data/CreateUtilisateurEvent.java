package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListUtilisateurEvent;

/**
 * Event that is fired in order to display a Utilisateur form in creation mode
 * @author MEDES-IMPS
 */
public class CreateUtilisateurEvent
		extends
			GwtEvent<CreateUtilisateurEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewUtilisateur(GwtEvent<?> closeEvent);
	}

	public CreateUtilisateurEvent() {
		this(new ListUtilisateurEvent());
	}

	public CreateUtilisateurEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewUtilisateur(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
