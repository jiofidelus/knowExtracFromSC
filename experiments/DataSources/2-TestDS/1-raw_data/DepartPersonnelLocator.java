package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.DepartPersonnel;
import org.imogene.epicam.server.handler.DepartPersonnelHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate DepartPersonnel beans 
 * @author Medes-IMPS
 */
public class DepartPersonnelLocator extends Locator<DepartPersonnel, String> {

	private DepartPersonnelHandler handler;

	public DepartPersonnelLocator() {

	}

	@Override
	public DepartPersonnel create(Class<? extends DepartPersonnel> clazz) {
		return new DepartPersonnel();
	}

	@Override
	public DepartPersonnel find(Class<? extends DepartPersonnel> clazz,
			String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<DepartPersonnel> getDomainType() {
		return DepartPersonnel.class;
	}

	@Override
	public String getId(DepartPersonnel domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(DepartPersonnel domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (DepartPersonnelHandler) context
				.getBean("departPersonnelHandler");
	}
}
