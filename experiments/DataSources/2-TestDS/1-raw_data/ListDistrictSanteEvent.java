package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of DistrictSante form entries
 * @author MEDES-IMPS
 */
public class ListDistrictSanteEvent
		extends
			GwtEvent<ListDistrictSanteEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listDistrictSante();
		void listDistrictSante(String searchText);
	}

	public ListDistrictSanteEvent() {
	}

	public ListDistrictSanteEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listDistrictSante();
		else
			handler.listDistrictSante(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
