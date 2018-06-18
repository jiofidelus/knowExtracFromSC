package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.CommandeProxy;

/**
 * Event that is fired after that a Commande form has been saved
 * @author MEDES-IMPS
 */
public class SaveCommandeEvent extends GwtEvent<SaveCommandeEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveCommande(CommandeProxy value);
		void saveCommande(CommandeProxy value, String initField);
	}

	private final CommandeProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveCommandeEvent(CommandeProxy value) {
		this(value, null);
	}

	public SaveCommandeEvent(CommandeProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveCommande(value);
		else
			handler.saveCommande(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
