package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.PriseMedicamentRegime;
import org.imogene.epicam.server.handler.PriseMedicamentRegimeHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate PriseMedicamentRegime beans 
 * @author Medes-IMPS
 */
public class PriseMedicamentRegimeLocator
		extends
			Locator<PriseMedicamentRegime, String> {

	private PriseMedicamentRegimeHandler handler;

	public PriseMedicamentRegimeLocator() {

	}

	@Override
	public PriseMedicamentRegime create(
			Class<? extends PriseMedicamentRegime> clazz) {
		return new PriseMedicamentRegime();
	}

	@Override
	public PriseMedicamentRegime find(
			Class<? extends PriseMedicamentRegime> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<PriseMedicamentRegime> getDomainType() {
		return PriseMedicamentRegime.class;
	}

	@Override
	public String getId(PriseMedicamentRegime domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(PriseMedicamentRegime domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (PriseMedicamentRegimeHandler) context
				.getBean("priseMedicamentRegimeHandler");
	}
}
