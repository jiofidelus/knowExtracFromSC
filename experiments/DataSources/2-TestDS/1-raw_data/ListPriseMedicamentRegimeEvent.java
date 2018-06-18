package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of PriseMedicamentRegime form entries
 * @author MEDES-IMPS
 */
public class ListPriseMedicamentRegimeEvent
		extends
			GwtEvent<ListPriseMedicamentRegimeEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listPriseMedicamentRegime();
		void listPriseMedicamentRegime(String searchText);
	}

	public ListPriseMedicamentRegimeEvent() {
	}

	public ListPriseMedicamentRegimeEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listPriseMedicamentRegime();
		else
			handler.listPriseMedicamentRegime(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
