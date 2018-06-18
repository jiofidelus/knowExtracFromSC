package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListSmsPredefiniEvent;

/**
 * Event that is fired in order to display a SmsPredefini form in creation mode
 * @author MEDES-IMPS
 */
public class CreateSmsPredefiniEvent
		extends
			GwtEvent<CreateSmsPredefiniEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewSmsPredefini(GwtEvent<?> closeEvent);
	}

	public CreateSmsPredefiniEvent() {
		this(new ListSmsPredefiniEvent());
	}

	public CreateSmsPredefiniEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewSmsPredefini(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
