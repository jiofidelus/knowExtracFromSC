package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.DetailCommandeIntrant;
import org.imogene.epicam.server.handler.DetailCommandeIntrantHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate DetailCommandeIntrant beans 
 * @author Medes-IMPS
 */
public class DetailCommandeIntrantLocator
		extends
			Locator<DetailCommandeIntrant, String> {

	private DetailCommandeIntrantHandler handler;

	public DetailCommandeIntrantLocator() {

	}

	@Override
	public DetailCommandeIntrant create(
			Class<? extends DetailCommandeIntrant> clazz) {
		return new DetailCommandeIntrant();
	}

	@Override
	public DetailCommandeIntrant find(
			Class<? extends DetailCommandeIntrant> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<DetailCommandeIntrant> getDomainType() {
		return DetailCommandeIntrant.class;
	}

	@Override
	public String getId(DetailCommandeIntrant domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(DetailCommandeIntrant domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (DetailCommandeIntrantHandler) context
				.getBean("detailCommandeIntrantHandler");
	}
}
