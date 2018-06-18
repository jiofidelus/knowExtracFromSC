package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListPriseMedicamentRegimeEvent;

/**
 * Event that is fired in order to display a PriseMedicamentRegime form in creation mode
 * @author MEDES-IMPS
 */
public class CreatePriseMedicamentRegimeEvent
		extends
			GwtEvent<CreatePriseMedicamentRegimeEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewPriseMedicamentRegime(GwtEvent<?> closeEvent);
	}

	public CreatePriseMedicamentRegimeEvent() {
		this(new ListPriseMedicamentRegimeEvent());
	}

	public CreatePriseMedicamentRegimeEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewPriseMedicamentRegime(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
