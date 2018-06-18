package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListFormationEvent;

/**
 * Event that is fired in order to display a Formation form in creation mode
 * @author MEDES-IMPS
 */
public class CreateFormationEvent
		extends
			GwtEvent<CreateFormationEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewFormation(GwtEvent<?> closeEvent);
	}

	public CreateFormationEvent() {
		this(new ListFormationEvent());
	}

	public CreateFormationEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewFormation(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
