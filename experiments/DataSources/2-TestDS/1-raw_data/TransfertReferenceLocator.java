package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.TransfertReference;
import org.imogene.epicam.server.handler.TransfertReferenceHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate TransfertReference beans 
 * @author Medes-IMPS
 */
public class TransfertReferenceLocator
		extends
			Locator<TransfertReference, String> {

	private TransfertReferenceHandler handler;

	public TransfertReferenceLocator() {

	}

	@Override
	public TransfertReference create(Class<? extends TransfertReference> clazz) {
		return new TransfertReference();
	}

	@Override
	public TransfertReference find(Class<? extends TransfertReference> clazz,
			String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<TransfertReference> getDomainType() {
		return TransfertReference.class;
	}

	@Override
	public String getId(TransfertReference domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(TransfertReference domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (TransfertReferenceHandler) context
				.getBean("transfertReferenceHandler");
	}
}
