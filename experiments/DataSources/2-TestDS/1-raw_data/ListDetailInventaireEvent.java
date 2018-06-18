package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of DetailInventaire form entries
 * @author MEDES-IMPS
 */
public class ListDetailInventaireEvent
		extends
			GwtEvent<ListDetailInventaireEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listDetailInventaire();
		void listDetailInventaire(String searchText);
	}

	public ListDetailInventaireEvent() {
	}

	public ListDetailInventaireEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listDetailInventaire();
		else
			handler.listDetailInventaire(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
