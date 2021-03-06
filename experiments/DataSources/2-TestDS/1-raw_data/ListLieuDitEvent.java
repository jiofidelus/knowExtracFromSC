package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of LieuDit form entries
 * @author MEDES-IMPS
 */
public class ListLieuDitEvent extends GwtEvent<ListLieuDitEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listLieuDit();
		void listLieuDit(String searchText);
	}

	public ListLieuDitEvent() {
	}

	public ListLieuDitEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listLieuDit();
		else
			handler.listLieuDit(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
