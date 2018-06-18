package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.DetailInventaireProxy;

/**
 * Event that is fired after that a DetailInventaire form has been saved
 * @author MEDES-IMPS
 */
public class SaveDetailInventaireEvent
		extends
			GwtEvent<SaveDetailInventaireEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveDetailInventaire(DetailInventaireProxy value);
		void saveDetailInventaire(DetailInventaireProxy value, String initField);
	}

	private final DetailInventaireProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveDetailInventaireEvent(DetailInventaireProxy value) {
		this(value, null);
	}

	public SaveDetailInventaireEvent(DetailInventaireProxy value,
			String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveDetailInventaire(value);
		else
			handler.saveDetailInventaire(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
