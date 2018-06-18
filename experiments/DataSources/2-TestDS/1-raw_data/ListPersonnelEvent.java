package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of Personnel form entries
 * @author MEDES-IMPS
 */
public class ListPersonnelEvent extends GwtEvent<ListPersonnelEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listPersonnel();
		void listPersonnel(String searchText);
	}

	public ListPersonnelEvent() {
	}

	public ListPersonnelEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listPersonnel();
		else
			handler.listPersonnel(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
