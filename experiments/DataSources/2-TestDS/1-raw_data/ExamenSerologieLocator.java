package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.ExamenSerologie;
import org.imogene.epicam.server.handler.ExamenSerologieHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate ExamenSerologie beans 
 * @author Medes-IMPS
 */
public class ExamenSerologieLocator extends Locator<ExamenSerologie, String> {

	private ExamenSerologieHandler handler;

	public ExamenSerologieLocator() {

	}

	@Override
	public ExamenSerologie create(Class<? extends ExamenSerologie> clazz) {
		return new ExamenSerologie();
	}

	@Override
	public ExamenSerologie find(Class<? extends ExamenSerologie> clazz,
			String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<ExamenSerologie> getDomainType() {
		return ExamenSerologie.class;
	}

	@Override
	public String getId(ExamenSerologie domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(ExamenSerologie domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (ExamenSerologieHandler) context
				.getBean("examenSerologieHandler");
	}
}
