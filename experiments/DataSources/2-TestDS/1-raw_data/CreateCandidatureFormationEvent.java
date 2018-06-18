package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListCandidatureFormationEvent;

/**
 * Event that is fired in order to display a CandidatureFormation form in creation mode
 * @author MEDES-IMPS
 */
public class CreateCandidatureFormationEvent
		extends
			GwtEvent<CreateCandidatureFormationEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewCandidatureFormation(GwtEvent<?> closeEvent);
	}

	public CreateCandidatureFormationEvent() {
		this(new ListCandidatureFormationEvent());
	}

	public CreateCandidatureFormationEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewCandidatureFormation(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
