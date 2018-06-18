package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of SmsPredefini form entries
 * @author MEDES-IMPS
 */
public class ListSmsPredefiniEvent
		extends
			GwtEvent<ListSmsPredefiniEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listSmsPredefini();
		void listSmsPredefini(String searchText);
	}

	public ListSmsPredefiniEvent() {
	}

	public ListSmsPredefiniEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listSmsPredefini();
		else
			handler.listSmsPredefini(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
