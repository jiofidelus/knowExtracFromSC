package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListRegionEvent;

/**
 * Event that is fired in order to display a Region form in creation mode
 * @author MEDES-IMPS
 */
public class CreateRegionEvent extends GwtEvent<CreateRegionEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewRegion(GwtEvent<?> closeEvent);
	}

	public CreateRegionEvent() {
		this(new ListRegionEvent());
	}

	public CreateRegionEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewRegion(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
