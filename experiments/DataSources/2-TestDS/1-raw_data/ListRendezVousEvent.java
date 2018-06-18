package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of RendezVous form entries
 * @author MEDES-IMPS
 */
public class ListRendezVousEvent extends GwtEvent<ListRendezVousEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listRendezVous();
		void listRendezVous(String searchText);
	}

	public ListRendezVousEvent() {
	}

	public ListRendezVousEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listRendezVous();
		else
			handler.listRendezVous(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
