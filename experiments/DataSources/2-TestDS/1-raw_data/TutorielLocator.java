package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.Tutoriel;
import org.imogene.epicam.server.handler.TutorielHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate Tutoriel beans 
 * @author Medes-IMPS
 */
public class TutorielLocator extends Locator<Tutoriel, String> {

	private TutorielHandler handler;

	public TutorielLocator() {

	}

	@Override
	public Tutoriel create(Class<? extends Tutoriel> clazz) {
		return new Tutoriel();
	}

	@Override
	public Tutoriel find(Class<? extends Tutoriel> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<Tutoriel> getDomainType() {
		return Tutoriel.class;
	}

	@Override
	public String getId(Tutoriel domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(Tutoriel domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (TutorielHandler) context.getBean("tutorielHandler");
	}
}
