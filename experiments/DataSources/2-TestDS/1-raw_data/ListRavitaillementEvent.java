package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of Ravitaillement form entries
 * @author MEDES-IMPS
 */
public class ListRavitaillementEvent
		extends
			GwtEvent<ListRavitaillementEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listRavitaillement();
		void listRavitaillement(String searchText);
	}

	public ListRavitaillementEvent() {
	}

	public ListRavitaillementEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listRavitaillement();
		else
			handler.listRavitaillement(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
