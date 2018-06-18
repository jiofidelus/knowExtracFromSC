package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.DetailReceptionMedicamentProxy;

/**
 * Event that is fired after that a DetailReceptionMedicament form has been saved
 * @author MEDES-IMPS
 */
public class SaveDetailReceptionMedicamentEvent
		extends
			GwtEvent<SaveDetailReceptionMedicamentEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveDetailReceptionMedicament(DetailReceptionMedicamentProxy value);
		void saveDetailReceptionMedicament(
				DetailReceptionMedicamentProxy value, String initField);
	}

	private final DetailReceptionMedicamentProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveDetailReceptionMedicamentEvent(
			DetailReceptionMedicamentProxy value) {
		this(value, null);
	}

	public SaveDetailReceptionMedicamentEvent(
			DetailReceptionMedicamentProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveDetailReceptionMedicament(value);
		else
			handler.saveDetailReceptionMedicament(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
