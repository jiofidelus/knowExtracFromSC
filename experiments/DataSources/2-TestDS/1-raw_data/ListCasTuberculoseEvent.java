package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of CasTuberculose form entries
 * @author MEDES-IMPS
 */
public class ListCasTuberculoseEvent
		extends
			GwtEvent<ListCasTuberculoseEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listCasTuberculose();
		void listCasTuberculose(String searchText);
	}

	public ListCasTuberculoseEvent() {
	}

	public ListCasTuberculoseEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listCasTuberculose();
		else
			handler.listCasTuberculose(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
