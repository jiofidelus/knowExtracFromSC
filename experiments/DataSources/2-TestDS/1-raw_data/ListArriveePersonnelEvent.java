package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of ArriveePersonnel form entries
 * @author MEDES-IMPS
 */
public class ListArriveePersonnelEvent
		extends
			GwtEvent<ListArriveePersonnelEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listArriveePersonnel();
		void listArriveePersonnel(String searchText);
	}

	public ListArriveePersonnelEvent() {
	}

	public ListArriveePersonnelEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listArriveePersonnel();
		else
			handler.listArriveePersonnel(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
