package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListHorsUsageEvent;

/**
 * Event that is fired in order to display a HorsUsage form in creation mode
 * @author MEDES-IMPS
 */
public class CreateHorsUsageEvent
		extends
			GwtEvent<CreateHorsUsageEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewHorsUsage(GwtEvent<?> closeEvent);
	}

	public CreateHorsUsageEvent() {
		this(new ListHorsUsageEvent());
	}

	public CreateHorsUsageEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewHorsUsage(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
