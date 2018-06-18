package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of TransfertReference form entries
 * @author MEDES-IMPS
 */
public class ListTransfertReferenceEvent
		extends
			GwtEvent<ListTransfertReferenceEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listTransfertReference();
		void listTransfertReference(String searchText);
	}

	public ListTransfertReferenceEvent() {
	}

	public ListTransfertReferenceEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listTransfertReference();
		else
			handler.listTransfertReference(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
