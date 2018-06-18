package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of CandidatureFormation form entries
 * @author MEDES-IMPS
 */
public class ListCandidatureFormationEvent
		extends
			GwtEvent<ListCandidatureFormationEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listCandidatureFormation();
		void listCandidatureFormation(String searchText);
	}

	public ListCandidatureFormationEvent() {
	}

	public ListCandidatureFormationEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listCandidatureFormation();
		else
			handler.listCandidatureFormation(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
