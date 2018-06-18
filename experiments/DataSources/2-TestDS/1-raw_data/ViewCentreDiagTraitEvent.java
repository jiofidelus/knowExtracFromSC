package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListCentreDiagTraitEvent;

/**
 * Event that is fired in order to display a CentreDiagTrait form in view mode
 * @author MEDES-IMPS
 */
public class ViewCentreDiagTraitEvent
		extends
			GwtEvent<ViewCentreDiagTraitEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewCentreDiagTrait(String entityId, GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewCentreDiagTraitEvent(String entityId) {
		this(entityId, new ListCentreDiagTraitEvent());
	}

	public ViewCentreDiagTraitEvent(String entityId, GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewCentreDiagTrait(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
