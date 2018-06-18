package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.Formation;
import org.imogene.epicam.server.handler.FormationHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate Formation beans 
 * @author Medes-IMPS
 */
public class FormationLocator extends Locator<Formation, String> {

	private FormationHandler handler;

	public FormationLocator() {

	}

	@Override
	public Formation create(Class<? extends Formation> clazz) {
		return new Formation();
	}

	@Override
	public Formation find(Class<? extends Formation> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<Formation> getDomainType() {
		return Formation.class;
	}

	@Override
	public String getId(Formation domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(Formation domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (FormationHandler) context.getBean("formationHandler");
	}
}
