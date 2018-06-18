package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.CandidatureFormation;
import org.imogene.epicam.server.handler.CandidatureFormationHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate CandidatureFormation beans 
 * @author Medes-IMPS
 */
public class CandidatureFormationLocator
		extends
			Locator<CandidatureFormation, String> {

	private CandidatureFormationHandler handler;

	public CandidatureFormationLocator() {

	}

	@Override
	public CandidatureFormation create(
			Class<? extends CandidatureFormation> clazz) {
		return new CandidatureFormation();
	}

	@Override
	public CandidatureFormation find(
			Class<? extends CandidatureFormation> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<CandidatureFormation> getDomainType() {
		return CandidatureFormation.class;
	}

	@Override
	public String getId(CandidatureFormation domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(CandidatureFormation domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (CandidatureFormationHandler) context
				.getBean("candidatureFormationHandler");
	}
}
