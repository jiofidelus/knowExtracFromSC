package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.QualificationProxy;

/**
 * Event that is fired after that a Qualification form has been saved
 * @author MEDES-IMPS
 */
public class SaveQualificationEvent
		extends
			GwtEvent<SaveQualificationEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveQualification(QualificationProxy value);
		void saveQualification(QualificationProxy value, String initField);
	}

	private final QualificationProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveQualificationEvent(QualificationProxy value) {
		this(value, null);
	}

	public SaveQualificationEvent(QualificationProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveQualification(value);
		else
			handler.saveQualification(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
