package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.Intrant;
import org.imogene.epicam.server.handler.IntrantHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate Intrant beans 
 * @author Medes-IMPS
 */
public class IntrantLocator extends Locator<Intrant, String> {

	private IntrantHandler handler;

	public IntrantLocator() {

	}

	@Override
	public Intrant create(Class<? extends Intrant> clazz) {
		return new Intrant();
	}

	@Override
	public Intrant find(Class<? extends Intrant> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<Intrant> getDomainType() {
		return Intrant.class;
	}

	@Override
	public String getId(Intrant domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(Intrant domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (IntrantHandler) context.getBean("intrantHandler");
	}
}
