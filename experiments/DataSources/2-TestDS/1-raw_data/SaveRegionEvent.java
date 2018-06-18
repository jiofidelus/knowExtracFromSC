package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.RegionProxy;

/**
 * Event that is fired after that a Region form has been saved
 * @author MEDES-IMPS
 */
public class SaveRegionEvent extends GwtEvent<SaveRegionEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveRegion(RegionProxy value);
		void saveRegion(RegionProxy value, String initField);
	}

	private final RegionProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveRegionEvent(RegionProxy value) {
		this(value, null);
	}

	public SaveRegionEvent(RegionProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveRegion(value);
		else
			handler.saveRegion(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
