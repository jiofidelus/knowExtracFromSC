package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListTransfertReferenceEvent;

/**
 * Event that is fired in order to display a TransfertReference form in creation mode
 * @author MEDES-IMPS
 */
public class CreateTransfertReferenceEvent
		extends
			GwtEvent<CreateTransfertReferenceEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewTransfertReference(GwtEvent<?> closeEvent);
	}

	public CreateTransfertReferenceEvent() {
		this(new ListTransfertReferenceEvent());
	}

	public CreateTransfertReferenceEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewTransfertReference(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
