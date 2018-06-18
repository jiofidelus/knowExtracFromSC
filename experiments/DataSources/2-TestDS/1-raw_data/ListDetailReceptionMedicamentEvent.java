package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of DetailReceptionMedicament form entries
 * @author MEDES-IMPS
 */
public class ListDetailReceptionMedicamentEvent
		extends
			GwtEvent<ListDetailReceptionMedicamentEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listDetailReceptionMedicament();
		void listDetailReceptionMedicament(String searchText);
	}

	public ListDetailReceptionMedicamentEvent() {
	}

	public ListDetailReceptionMedicamentEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listDetailReceptionMedicament();
		else
			handler.listDetailReceptionMedicament(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
