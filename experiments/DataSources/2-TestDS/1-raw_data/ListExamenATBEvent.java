package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of ExamenATB form entries
 * @author MEDES-IMPS
 */
public class ListExamenATBEvent extends GwtEvent<ListExamenATBEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listExamenATB();
		void listExamenATB(String searchText);
	}

	public ListExamenATBEvent() {
	}

	public ListExamenATBEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listExamenATB();
		else
			handler.listExamenATB(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
