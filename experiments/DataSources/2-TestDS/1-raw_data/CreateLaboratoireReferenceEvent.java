package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListLaboratoireReferenceEvent;

/**
 * Event that is fired in order to display a LaboratoireReference form in creation mode
 * @author MEDES-IMPS
 */
public class CreateLaboratoireReferenceEvent
		extends
			GwtEvent<CreateLaboratoireReferenceEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewLaboratoireReference(GwtEvent<?> closeEvent);
	}

	public CreateLaboratoireReferenceEvent() {
		this(new ListLaboratoireReferenceEvent());
	}

	public CreateLaboratoireReferenceEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewLaboratoireReference(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
