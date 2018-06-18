package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of DetailCommandeMedicament form entries
 * @author MEDES-IMPS
 */
public class ListDetailCommandeMedicamentEvent
		extends
			GwtEvent<ListDetailCommandeMedicamentEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listDetailCommandeMedicament();
		void listDetailCommandeMedicament(String searchText);
	}

	public ListDetailCommandeMedicamentEvent() {
	}

	public ListDetailCommandeMedicamentEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listDetailCommandeMedicament();
		else
			handler.listDetailCommandeMedicament(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
