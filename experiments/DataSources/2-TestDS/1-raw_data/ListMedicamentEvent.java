package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of Medicament form entries
 * @author MEDES-IMPS
 */
public class ListMedicamentEvent extends GwtEvent<ListMedicamentEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listMedicament();
		void listMedicament(String searchText);
	}

	public ListMedicamentEvent() {
	}

	public ListMedicamentEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listMedicament();
		else
			handler.listMedicament(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
