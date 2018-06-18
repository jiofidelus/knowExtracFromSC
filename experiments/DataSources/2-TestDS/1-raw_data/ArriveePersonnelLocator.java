package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.ArriveePersonnel;
import org.imogene.epicam.server.handler.ArriveePersonnelHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate ArriveePersonnel beans 
 * @author Medes-IMPS
 */
public class ArriveePersonnelLocator extends Locator<ArriveePersonnel, String> {

	private ArriveePersonnelHandler handler;

	public ArriveePersonnelLocator() {

	}

	@Override
	public ArriveePersonnel create(Class<? extends ArriveePersonnel> clazz) {
		return new ArriveePersonnel();
	}

	@Override
	public ArriveePersonnel find(Class<? extends ArriveePersonnel> clazz,
			String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<ArriveePersonnel> getDomainType() {
		return ArriveePersonnel.class;
	}

	@Override
	public String getId(ArriveePersonnel domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(ArriveePersonnel domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (ArriveePersonnelHandler) context
				.getBean("arriveePersonnelHandler");
	}
}
