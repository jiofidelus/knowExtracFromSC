package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListPriseMedicamentRegimeEvent;

/**
 * Event that is fired in order to display a PriseMedicamentRegime form in view mode
 * @author MEDES-IMPS
 */
public class ViewPriseMedicamentRegimeEvent
		extends
			GwtEvent<ViewPriseMedicamentRegimeEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewPriseMedicamentRegime(String entityId, GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewPriseMedicamentRegimeEvent(String entityId) {
		this(entityId, new ListPriseMedicamentRegimeEvent());
	}

	public ViewPriseMedicamentRegimeEvent(String entityId,
			GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewPriseMedicamentRegime(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
