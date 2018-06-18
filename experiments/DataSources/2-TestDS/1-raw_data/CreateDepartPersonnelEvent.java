package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListDepartPersonnelEvent;

/**
 * Event that is fired in order to display a DepartPersonnel form in creation mode
 * @author MEDES-IMPS
 */
public class CreateDepartPersonnelEvent
		extends
			GwtEvent<CreateDepartPersonnelEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewDepartPersonnel(GwtEvent<?> closeEvent);
	}

	public CreateDepartPersonnelEvent() {
		this(new ListDepartPersonnelEvent());
	}

	public CreateDepartPersonnelEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewDepartPersonnel(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
