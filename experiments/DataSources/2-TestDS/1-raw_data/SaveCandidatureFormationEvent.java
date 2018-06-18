package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.CandidatureFormationProxy;

/**
 * Event that is fired after that a CandidatureFormation form has been saved
 * @author MEDES-IMPS
 */
public class SaveCandidatureFormationEvent
		extends
			GwtEvent<SaveCandidatureFormationEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveCandidatureFormation(CandidatureFormationProxy value);
		void saveCandidatureFormation(CandidatureFormationProxy value,
				String initField);
	}

	private final CandidatureFormationProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveCandidatureFormationEvent(CandidatureFormationProxy value) {
		this(value, null);
	}

	public SaveCandidatureFormationEvent(CandidatureFormationProxy value,
			String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveCandidatureFormation(value);
		else
			handler.saveCandidatureFormation(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
