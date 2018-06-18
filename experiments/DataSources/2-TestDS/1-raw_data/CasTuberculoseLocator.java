package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.CasTuberculose;
import org.imogene.epicam.server.handler.CasTuberculoseHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate CasTuberculose beans 
 * @author Medes-IMPS
 */
public class CasTuberculoseLocator extends Locator<CasTuberculose, String> {

	private CasTuberculoseHandler handler;

	public CasTuberculoseLocator() {

	}

	@Override
	public CasTuberculose create(Class<? extends CasTuberculose> clazz) {
		return new CasTuberculose();
	}

	@Override
	public CasTuberculose find(Class<? extends CasTuberculose> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<CasTuberculose> getDomainType() {
		return CasTuberculose.class;
	}

	@Override
	public String getId(CasTuberculose domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(CasTuberculose domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (CasTuberculoseHandler) context
				.getBean("casTuberculoseHandler");
	}
}
