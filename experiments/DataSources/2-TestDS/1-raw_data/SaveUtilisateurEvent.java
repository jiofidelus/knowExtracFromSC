package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.UtilisateurProxy;

/**
 * Event that is fired after that a Utilisateur form has been saved
 * @author MEDES-IMPS
 */
public class SaveUtilisateurEvent
		extends
			GwtEvent<SaveUtilisateurEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveUtilisateur(UtilisateurProxy value);
		void saveUtilisateur(UtilisateurProxy value, String initField);
	}

	private final UtilisateurProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveUtilisateurEvent(UtilisateurProxy value) {
		this(value, null);
	}

	public SaveUtilisateurEvent(UtilisateurProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveUtilisateur(value);
		else
			handler.saveUtilisateur(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
