package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListPersonnelEvent;

/**
 * Event that is fired in order to display a Personnel form in creation mode
 * @author MEDES-IMPS
 */
public class CreatePersonnelEvent
		extends
			GwtEvent<CreatePersonnelEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewPersonnel(GwtEvent<?> closeEvent);
	}

	public CreatePersonnelEvent() {
		this(new ListPersonnelEvent());
	}

	public CreatePersonnelEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewPersonnel(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
