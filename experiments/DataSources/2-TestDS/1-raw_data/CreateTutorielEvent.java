package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListTutorielEvent;

/**
 * Event that is fired in order to display a Tutoriel form in creation mode
 * @author MEDES-IMPS
 */
public class CreateTutorielEvent extends GwtEvent<CreateTutorielEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewTutoriel(GwtEvent<?> closeEvent);
	}

	public CreateTutorielEvent() {
		this(new ListTutorielEvent());
	}

	public CreateTutorielEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewTutoriel(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
