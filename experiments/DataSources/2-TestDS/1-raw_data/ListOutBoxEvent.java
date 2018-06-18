package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of OutBox form entries
 * @author MEDES-IMPS
 */
public class ListOutBoxEvent extends GwtEvent<ListOutBoxEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listOutBox();
		void listOutBox(String searchText);
	}

	public ListOutBoxEvent() {
	}

	public ListOutBoxEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listOutBox();
		else
			handler.listOutBox(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
