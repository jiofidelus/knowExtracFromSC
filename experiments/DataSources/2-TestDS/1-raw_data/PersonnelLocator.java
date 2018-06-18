package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.Personnel;
import org.imogene.epicam.server.handler.PersonnelHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate Personnel beans 
 * @author Medes-IMPS
 */
public class PersonnelLocator extends Locator<Personnel, String> {

	private PersonnelHandler handler;

	public PersonnelLocator() {

	}

	@Override
	public Personnel create(Class<? extends Personnel> clazz) {
		return new Personnel();
	}

	@Override
	public Personnel find(Class<? extends Personnel> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<Personnel> getDomainType() {
		return Personnel.class;
	}

	@Override
	public String getId(Personnel domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(Personnel domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (PersonnelHandler) context.getBean("personnelHandler");
	}
}
