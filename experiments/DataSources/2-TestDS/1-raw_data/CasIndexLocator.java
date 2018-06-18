package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.CasIndex;
import org.imogene.epicam.server.handler.CasIndexHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate CasIndex beans 
 * @author Medes-IMPS
 */
public class CasIndexLocator extends Locator<CasIndex, String> {

	private CasIndexHandler handler;

	public CasIndexLocator() {

	}

	@Override
	public CasIndex create(Class<? extends CasIndex> clazz) {
		return new CasIndex();
	}

	@Override
	public CasIndex find(Class<? extends CasIndex> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<CasIndex> getDomainType() {
		return CasIndex.class;
	}

	@Override
	public String getId(CasIndex domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(CasIndex domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (CasIndexHandler) context.getBean("casIndexHandler");
	}
}
