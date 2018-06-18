package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of Tutoriel form entries
 * @author MEDES-IMPS
 */
public class ListTutorielEvent extends GwtEvent<ListTutorielEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listTutoriel();
		void listTutoriel(String searchText);
	}

	public ListTutorielEvent() {
	}

	public ListTutorielEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listTutoriel();
		else
			handler.listTutoriel(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
