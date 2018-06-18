package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListCasIndexEvent;

/**
 * Event that is fired in order to display a CasIndex form in creation mode
 * @author MEDES-IMPS
 */
public class CreateCasIndexEvent extends GwtEvent<CreateCasIndexEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewCasIndex(GwtEvent<?> closeEvent);
	}

	public CreateCasIndexEvent() {
		this(new ListCasIndexEvent());
	}

	public CreateCasIndexEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewCasIndex(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
