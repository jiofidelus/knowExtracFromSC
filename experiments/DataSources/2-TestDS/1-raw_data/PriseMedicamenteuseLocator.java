package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.PriseMedicamenteuse;
import org.imogene.epicam.server.handler.PriseMedicamenteuseHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate PriseMedicamenteuse beans 
 * @author Medes-IMPS
 */
public class PriseMedicamenteuseLocator
		extends
			Locator<PriseMedicamenteuse, String> {

	private PriseMedicamenteuseHandler handler;

	public PriseMedicamenteuseLocator() {

	}

	@Override
	public PriseMedicamenteuse create(Class<? extends PriseMedicamenteuse> clazz) {
		return new PriseMedicamenteuse();
	}

	@Override
	public PriseMedicamenteuse find(Class<? extends PriseMedicamenteuse> clazz,
			String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<PriseMedicamenteuse> getDomainType() {
		return PriseMedicamenteuse.class;
	}

	@Override
	public String getId(PriseMedicamenteuse domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(PriseMedicamenteuse domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (PriseMedicamenteuseHandler) context
				.getBean("priseMedicamenteuseHandler");
	}
}
