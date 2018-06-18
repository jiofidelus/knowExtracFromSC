package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.Reception;
import org.imogene.epicam.server.handler.ReceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate Reception beans 
 * @author Medes-IMPS
 */
public class ReceptionLocator extends Locator<Reception, String> {

	private ReceptionHandler handler;

	public ReceptionLocator() {

	}

	@Override
	public Reception create(Class<? extends Reception> clazz) {
		return new Reception();
	}

	@Override
	public Reception find(Class<? extends Reception> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<Reception> getDomainType() {
		return Reception.class;
	}

	@Override
	public String getId(Reception domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(Reception domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (ReceptionHandler) context.getBean("receptionHandler");
	}
}
