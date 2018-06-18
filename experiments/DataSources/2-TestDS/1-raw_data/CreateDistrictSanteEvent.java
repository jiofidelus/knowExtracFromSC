package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListDistrictSanteEvent;

/**
 * Event that is fired in order to display a DistrictSante form in creation mode
 * @author MEDES-IMPS
 */
public class CreateDistrictSanteEvent
		extends
			GwtEvent<CreateDistrictSanteEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewDistrictSante(GwtEvent<?> closeEvent);
	}

	public CreateDistrictSanteEvent() {
		this(new ListDistrictSanteEvent());
	}

	public CreateDistrictSanteEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewDistrictSante(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
