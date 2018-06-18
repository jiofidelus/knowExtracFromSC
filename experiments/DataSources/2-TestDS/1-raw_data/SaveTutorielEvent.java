package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.TutorielProxy;

/**
 * Event that is fired after that a Tutoriel form has been saved
 * @author MEDES-IMPS
 */
public class SaveTutorielEvent extends GwtEvent<SaveTutorielEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveTutoriel(TutorielProxy value);
		void saveTutoriel(TutorielProxy value, String initField);
	}

	private final TutorielProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveTutorielEvent(TutorielProxy value) {
		this(value, null);
	}

	public SaveTutorielEvent(TutorielProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveTutoriel(value);
		else
			handler.saveTutoriel(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
