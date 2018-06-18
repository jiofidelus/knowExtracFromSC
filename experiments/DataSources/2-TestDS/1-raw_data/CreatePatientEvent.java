package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListPatientEvent;

/**
 * Event that is fired in order to display a Patient form in creation mode
 * @author MEDES-IMPS
 */
public class CreatePatientEvent extends GwtEvent<CreatePatientEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewPatient(GwtEvent<?> closeEvent);
	}

	public CreatePatientEvent() {
		this(new ListPatientEvent());
	}

	public CreatePatientEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewPatient(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
