package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListCentreDiagTraitEvent;

/**
 * Event that is fired in order to display a CentreDiagTrait form in creation mode
 * @author MEDES-IMPS
 */
public class CreateCentreDiagTraitEvent
		extends
			GwtEvent<CreateCentreDiagTraitEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewCentreDiagTrait(GwtEvent<?> closeEvent);
	}

	public CreateCentreDiagTraitEvent() {
		this(new ListCentreDiagTraitEvent());
	}

	public CreateCentreDiagTraitEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewCentreDiagTrait(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
