package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.DepartPersonnelProxy;

/**
 * Event that is fired after that a DepartPersonnel form has been saved
 * @author MEDES-IMPS
 */
public class SaveDepartPersonnelEvent
		extends
			GwtEvent<SaveDepartPersonnelEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveDepartPersonnel(DepartPersonnelProxy value);
		void saveDepartPersonnel(DepartPersonnelProxy value, String initField);
	}

	private final DepartPersonnelProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveDepartPersonnelEvent(DepartPersonnelProxy value) {
		this(value, null);
	}

	public SaveDepartPersonnelEvent(DepartPersonnelProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveDepartPersonnel(value);
		else
			handler.saveDepartPersonnel(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
