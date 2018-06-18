package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.LieuDitProxy;

/**
 * Event that is fired after that a LieuDit form has been saved
 * @author MEDES-IMPS
 */
public class SaveLieuDitEvent extends GwtEvent<SaveLieuDitEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveLieuDit(LieuDitProxy value);
		void saveLieuDit(LieuDitProxy value, String initField);
	}

	private final LieuDitProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveLieuDitEvent(LieuDitProxy value) {
		this(value, null);
	}

	public SaveLieuDitEvent(LieuDitProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveLieuDit(value);
		else
			handler.saveLieuDit(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
