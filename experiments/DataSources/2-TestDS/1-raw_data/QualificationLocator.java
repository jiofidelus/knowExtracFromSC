package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.Qualification;
import org.imogene.epicam.server.handler.QualificationHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate Qualification beans 
 * @author Medes-IMPS
 */
public class QualificationLocator extends Locator<Qualification, String> {

	private QualificationHandler handler;

	public QualificationLocator() {

	}

	@Override
	public Qualification create(Class<? extends Qualification> clazz) {
		return new Qualification();
	}

	@Override
	public Qualification find(Class<? extends Qualification> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<Qualification> getDomainType() {
		return Qualification.class;
	}

	@Override
	public String getId(Qualification domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(Qualification domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (QualificationHandler) context
				.getBean("qualificationHandler");
	}
}
