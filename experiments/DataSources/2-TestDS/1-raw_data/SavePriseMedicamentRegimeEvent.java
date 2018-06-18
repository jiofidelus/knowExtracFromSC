package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.PriseMedicamentRegimeProxy;

/**
 * Event that is fired after that a PriseMedicamentRegime form has been saved
 * @author MEDES-IMPS
 */
public class SavePriseMedicamentRegimeEvent
		extends
			GwtEvent<SavePriseMedicamentRegimeEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void savePriseMedicamentRegime(PriseMedicamentRegimeProxy value);
		void savePriseMedicamentRegime(PriseMedicamentRegimeProxy value,
				String initField);
	}

	private final PriseMedicamentRegimeProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SavePriseMedicamentRegimeEvent(PriseMedicamentRegimeProxy value) {
		this(value, null);
	}

	public SavePriseMedicamentRegimeEvent(PriseMedicamentRegimeProxy value,
			String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.savePriseMedicamentRegime(value);
		else
			handler.savePriseMedicamentRegime(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
