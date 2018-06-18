package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.Ravitaillement;
import org.imogene.epicam.server.handler.RavitaillementHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate Ravitaillement beans 
 * @author Medes-IMPS
 */
public class RavitaillementLocator extends Locator<Ravitaillement, String> {

	private RavitaillementHandler handler;

	public RavitaillementLocator() {

	}

	@Override
	public Ravitaillement create(Class<? extends Ravitaillement> clazz) {
		return new Ravitaillement();
	}

	@Override
	public Ravitaillement find(Class<? extends Ravitaillement> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<Ravitaillement> getDomainType() {
		return Ravitaillement.class;
	}

	@Override
	public String getId(Ravitaillement domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(Ravitaillement domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (RavitaillementHandler) context
				.getBean("ravitaillementHandler");
	}
}
