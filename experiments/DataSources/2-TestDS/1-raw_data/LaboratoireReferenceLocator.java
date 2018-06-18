package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.LaboratoireReference;
import org.imogene.epicam.server.handler.LaboratoireReferenceHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate LaboratoireReference beans 
 * @author Medes-IMPS
 */
public class LaboratoireReferenceLocator
		extends
			Locator<LaboratoireReference, String> {

	private LaboratoireReferenceHandler handler;

	public LaboratoireReferenceLocator() {

	}

	@Override
	public LaboratoireReference create(
			Class<? extends LaboratoireReference> clazz) {
		return new LaboratoireReference();
	}

	@Override
	public LaboratoireReference find(
			Class<? extends LaboratoireReference> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<LaboratoireReference> getDomainType() {
		return LaboratoireReference.class;
	}

	@Override
	public String getId(LaboratoireReference domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(LaboratoireReference domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (LaboratoireReferenceHandler) context
				.getBean("laboratoireReferenceHandler");
	}
}
