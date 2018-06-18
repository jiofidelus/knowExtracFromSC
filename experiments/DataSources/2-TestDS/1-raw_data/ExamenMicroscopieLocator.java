package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.ExamenMicroscopie;
import org.imogene.epicam.server.handler.ExamenMicroscopieHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate ExamenMicroscopie beans 
 * @author Medes-IMPS
 */
public class ExamenMicroscopieLocator
		extends
			Locator<ExamenMicroscopie, String> {

	private ExamenMicroscopieHandler handler;

	public ExamenMicroscopieLocator() {

	}

	@Override
	public ExamenMicroscopie create(Class<? extends ExamenMicroscopie> clazz) {
		return new ExamenMicroscopie();
	}

	@Override
	public ExamenMicroscopie find(Class<? extends ExamenMicroscopie> clazz,
			String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<ExamenMicroscopie> getDomainType() {
		return ExamenMicroscopie.class;
	}

	@Override
	public String getId(ExamenMicroscopie domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(ExamenMicroscopie domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (ExamenMicroscopieHandler) context
				.getBean("examenMicroscopieHandler");
	}
}
