package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of ExamenSerologie form entries
 * @author MEDES-IMPS
 */
public class ListExamenSerologieEvent
		extends
			GwtEvent<ListExamenSerologieEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listExamenSerologie();
		void listExamenSerologie(String searchText);
	}

	public ListExamenSerologieEvent() {
	}

	public ListExamenSerologieEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listExamenSerologie();
		else
			handler.listExamenSerologie(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
