package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.ExamenATB;
import org.imogene.epicam.server.handler.ExamenATBHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate ExamenATB beans 
 * @author Medes-IMPS
 */
public class ExamenATBLocator extends Locator<ExamenATB, String> {

	private ExamenATBHandler handler;

	public ExamenATBLocator() {

	}

	@Override
	public ExamenATB create(Class<? extends ExamenATB> clazz) {
		return new ExamenATB();
	}

	@Override
	public ExamenATB find(Class<? extends ExamenATB> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<ExamenATB> getDomainType() {
		return ExamenATB.class;
	}

	@Override
	public String getId(ExamenATB domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(ExamenATB domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (ExamenATBHandler) context.getBean("examenATBHandler");
	}
}
