package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListPriseMedicamenteuseEvent;

/**
 * Event that is fired in order to display a PriseMedicamenteuse form in view mode
 * @author MEDES-IMPS
 */
public class ViewPriseMedicamenteuseEvent
		extends
			GwtEvent<ViewPriseMedicamenteuseEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewPriseMedicamenteuse(String entityId, GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewPriseMedicamenteuseEvent(String entityId) {
		this(entityId, new ListPriseMedicamenteuseEvent());
	}

	public ViewPriseMedicamenteuseEvent(String entityId, GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewPriseMedicamenteuse(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
