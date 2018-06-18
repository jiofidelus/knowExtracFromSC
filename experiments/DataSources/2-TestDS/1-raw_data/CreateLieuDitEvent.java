package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListLieuDitEvent;

/**
 * Event that is fired in order to display a LieuDit form in creation mode
 * @author MEDES-IMPS
 */
public class CreateLieuDitEvent extends GwtEvent<CreateLieuDitEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewLieuDit(GwtEvent<?> closeEvent);
	}

	public CreateLieuDitEvent() {
		this(new ListLieuDitEvent());
	}

	public CreateLieuDitEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewLieuDit(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
