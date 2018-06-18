package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.OutBox;
import org.imogene.epicam.server.handler.OutBoxHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate OutBox beans 
 * @author Medes-IMPS
 */
public class OutBoxLocator extends Locator<OutBox, String> {

	private OutBoxHandler handler;

	public OutBoxLocator() {

	}

	@Override
	public OutBox create(Class<? extends OutBox> clazz) {
		return new OutBox();
	}

	@Override
	public OutBox find(Class<? extends OutBox> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<OutBox> getDomainType() {
		return OutBox.class;
	}

	@Override
	public String getId(OutBox domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(OutBox domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (OutBoxHandler) context.getBean("outBoxHandler");
	}
}
