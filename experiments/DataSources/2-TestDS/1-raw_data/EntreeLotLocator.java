package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.EntreeLot;
import org.imogene.epicam.server.handler.EntreeLotHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate EntreeLot beans 
 * @author Medes-IMPS
 */
public class EntreeLotLocator extends Locator<EntreeLot, String> {

	private EntreeLotHandler handler;

	public EntreeLotLocator() {

	}

	@Override
	public EntreeLot create(Class<? extends EntreeLot> clazz) {
		return new EntreeLot();
	}

	@Override
	public EntreeLot find(Class<? extends EntreeLot> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<EntreeLot> getDomainType() {
		return EntreeLot.class;
	}

	@Override
	public String getId(EntreeLot domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(EntreeLot domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (EntreeLotHandler) context.getBean("entreeLotHandler");
	}
}
