package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.PriseMedicamenteuseProxy;

/**
 * Event that is fired after that a PriseMedicamenteuse form has been saved
 * @author MEDES-IMPS
 */
public class SavePriseMedicamenteuseEvent
		extends
			GwtEvent<SavePriseMedicamenteuseEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void savePriseMedicamenteuse(PriseMedicamenteuseProxy value);
		void savePriseMedicamenteuse(PriseMedicamenteuseProxy value,
				String initField);
	}

	private final PriseMedicamenteuseProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SavePriseMedicamenteuseEvent(PriseMedicamenteuseProxy value) {
		this(value, null);
	}

	public SavePriseMedicamenteuseEvent(PriseMedicamenteuseProxy value,
			String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.savePriseMedicamenteuse(value);
		else
			handler.savePriseMedicamenteuse(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
