package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of LaboratoireReference form entries
 * @author MEDES-IMPS
 */
public class ListLaboratoireReferenceEvent
		extends
			GwtEvent<ListLaboratoireReferenceEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listLaboratoireReference();
		void listLaboratoireReference(String searchText);
	}

	public ListLaboratoireReferenceEvent() {
	}

	public ListLaboratoireReferenceEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listLaboratoireReference();
		else
			handler.listLaboratoireReference(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
