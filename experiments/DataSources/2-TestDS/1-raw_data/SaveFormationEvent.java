package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.FormationProxy;

/**
 * Event that is fired after that a Formation form has been saved
 * @author MEDES-IMPS
 */
public class SaveFormationEvent extends GwtEvent<SaveFormationEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveFormation(FormationProxy value);
		void saveFormation(FormationProxy value, String initField);
	}

	private final FormationProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveFormationEvent(FormationProxy value) {
		this(value, null);
	}

	public SaveFormationEvent(FormationProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveFormation(value);
		else
			handler.saveFormation(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
