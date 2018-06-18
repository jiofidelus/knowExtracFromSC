package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListDetailInventaireEvent;

/**
 * Event that is fired in order to display a DetailInventaire form in creation mode
 * @author MEDES-IMPS
 */
public class CreateDetailInventaireEvent
		extends
			GwtEvent<CreateDetailInventaireEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewDetailInventaire(GwtEvent<?> closeEvent);
	}

	public CreateDetailInventaireEvent() {
		this(new ListDetailInventaireEvent());
	}

	public CreateDetailInventaireEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewDetailInventaire(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
