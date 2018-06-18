package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.SortieLot;
import org.imogene.epicam.server.handler.SortieLotHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate SortieLot beans 
 * @author Medes-IMPS
 */
public class SortieLotLocator extends Locator<SortieLot, String> {

	private SortieLotHandler handler;

	public SortieLotLocator() {

	}

	@Override
	public SortieLot create(Class<? extends SortieLot> clazz) {
		return new SortieLot();
	}

	@Override
	public SortieLot find(Class<? extends SortieLot> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<SortieLot> getDomainType() {
		return SortieLot.class;
	}

	@Override
	public String getId(SortieLot domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(SortieLot domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (SortieLotHandler) context.getBean("sortieLotHandler");
	}
}
