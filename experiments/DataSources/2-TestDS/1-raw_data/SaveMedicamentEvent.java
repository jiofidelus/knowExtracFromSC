package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.MedicamentProxy;

/**
 * Event that is fired after that a Medicament form has been saved
 * @author MEDES-IMPS
 */
public class SaveMedicamentEvent extends GwtEvent<SaveMedicamentEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveMedicament(MedicamentProxy value);
		void saveMedicament(MedicamentProxy value, String initField);
	}

	private final MedicamentProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveMedicamentEvent(MedicamentProxy value) {
		this(value, null);
	}

	public SaveMedicamentEvent(MedicamentProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveMedicament(value);
		else
			handler.saveMedicament(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
