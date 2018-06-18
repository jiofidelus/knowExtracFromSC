package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.DetailReceptionIntrant;
import org.imogene.epicam.server.handler.DetailReceptionIntrantHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate DetailReceptionIntrant beans 
 * @author Medes-IMPS
 */
public class DetailReceptionIntrantLocator
		extends
			Locator<DetailReceptionIntrant, String> {

	private DetailReceptionIntrantHandler handler;

	public DetailReceptionIntrantLocator() {

	}

	@Override
	public DetailReceptionIntrant create(
			Class<? extends DetailReceptionIntrant> clazz) {
		return new DetailReceptionIntrant();
	}

	@Override
	public DetailReceptionIntrant find(
			Class<? extends DetailReceptionIntrant> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<DetailReceptionIntrant> getDomainType() {
		return DetailReceptionIntrant.class;
	}

	@Override
	public String getId(DetailReceptionIntrant domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(DetailReceptionIntrant domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (DetailReceptionIntrantHandler) context
				.getBean("detailReceptionIntrantHandler");
	}
}
