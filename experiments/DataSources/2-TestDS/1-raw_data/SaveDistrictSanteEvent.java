package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.DistrictSanteProxy;

/**
 * Event that is fired after that a DistrictSante form has been saved
 * @author MEDES-IMPS
 */
public class SaveDistrictSanteEvent
		extends
			GwtEvent<SaveDistrictSanteEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveDistrictSante(DistrictSanteProxy value);
		void saveDistrictSante(DistrictSanteProxy value, String initField);
	}

	private final DistrictSanteProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveDistrictSanteEvent(DistrictSanteProxy value) {
		this(value, null);
	}

	public SaveDistrictSanteEvent(DistrictSanteProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveDistrictSante(value);
		else
			handler.saveDistrictSante(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
