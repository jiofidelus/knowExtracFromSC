package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of CentreDiagTrait form entries
 * @author MEDES-IMPS
 */
public class ListCentreDiagTraitEvent
		extends
			GwtEvent<ListCentreDiagTraitEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listCentreDiagTrait();
		void listCentreDiagTrait(String searchText);
	}

	public ListCentreDiagTraitEvent() {
	}

	public ListCentreDiagTraitEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listCentreDiagTrait();
		else
			handler.listCentreDiagTrait(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
