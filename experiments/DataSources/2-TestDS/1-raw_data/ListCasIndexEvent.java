package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of CasIndex form entries
 * @author MEDES-IMPS
 */
public class ListCasIndexEvent extends GwtEvent<ListCasIndexEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listCasIndex();
		void listCasIndex(String searchText);
	}

	public ListCasIndexEvent() {
	}

	public ListCasIndexEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listCasIndex();
		else
			handler.listCasIndex(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
