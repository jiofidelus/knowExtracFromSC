package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.CasIndexProxy;

/**
 * Event that is fired after that a CasIndex form has been saved
 * @author MEDES-IMPS
 */
public class SaveCasIndexEvent extends GwtEvent<SaveCasIndexEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveCasIndex(CasIndexProxy value);
		void saveCasIndex(CasIndexProxy value, String initField);
	}

	private final CasIndexProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveCasIndexEvent(CasIndexProxy value) {
		this(value, null);
	}

	public SaveCasIndexEvent(CasIndexProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveCasIndex(value);
		else
			handler.saveCasIndex(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
