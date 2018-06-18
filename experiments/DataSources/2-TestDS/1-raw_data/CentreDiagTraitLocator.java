package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.CentreDiagTrait;
import org.imogene.epicam.server.handler.CentreDiagTraitHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate CentreDiagTrait beans 
 * @author Medes-IMPS
 */
public class CentreDiagTraitLocator extends Locator<CentreDiagTrait, String> {

	private CentreDiagTraitHandler handler;

	public CentreDiagTraitLocator() {

	}

	@Override
	public CentreDiagTrait create(Class<? extends CentreDiagTrait> clazz) {
		return new CentreDiagTrait();
	}

	@Override
	public CentreDiagTrait find(Class<? extends CentreDiagTrait> clazz,
			String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<CentreDiagTrait> getDomainType() {
		return CentreDiagTrait.class;
	}

	@Override
	public String getId(CentreDiagTrait domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(CentreDiagTrait domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (CentreDiagTraitHandler) context
				.getBean("centreDiagTraitHandler");
	}
}
