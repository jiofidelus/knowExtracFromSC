package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of DetailReceptionIntrant form entries
 * @author MEDES-IMPS
 */
public class ListDetailReceptionIntrantEvent
		extends
			GwtEvent<ListDetailReceptionIntrantEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listDetailReceptionIntrant();
		void listDetailReceptionIntrant(String searchText);
	}

	public ListDetailReceptionIntrantEvent() {
	}

	public ListDetailReceptionIntrantEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listDetailReceptionIntrant();
		else
			handler.listDetailReceptionIntrant(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
