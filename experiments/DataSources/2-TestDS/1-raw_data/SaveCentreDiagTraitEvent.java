package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.CentreDiagTraitProxy;

/**
 * Event that is fired after that a CentreDiagTrait form has been saved
 * @author MEDES-IMPS
 */
public class SaveCentreDiagTraitEvent
		extends
			GwtEvent<SaveCentreDiagTraitEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveCentreDiagTrait(CentreDiagTraitProxy value);
		void saveCentreDiagTrait(CentreDiagTraitProxy value, String initField);
	}

	private final CentreDiagTraitProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveCentreDiagTraitEvent(CentreDiagTraitProxy value) {
		this(value, null);
	}

	public SaveCentreDiagTraitEvent(CentreDiagTraitProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveCentreDiagTrait(value);
		else
			handler.saveCentreDiagTrait(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
