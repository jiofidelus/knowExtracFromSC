package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListPriseMedicamenteuseEvent;

/**
 * Event that is fired in order to display a PriseMedicamenteuse form in creation mode
 * @author MEDES-IMPS
 */
public class CreatePriseMedicamenteuseEvent
		extends
			GwtEvent<CreatePriseMedicamenteuseEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewPriseMedicamenteuse(GwtEvent<?> closeEvent);
	}

	public CreatePriseMedicamenteuseEvent() {
		this(new ListPriseMedicamenteuseEvent());
	}

	public CreatePriseMedicamenteuseEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewPriseMedicamenteuse(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
