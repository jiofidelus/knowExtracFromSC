package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.Lot;
import org.imogene.epicam.server.handler.LotHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate Lot beans 
 * @author Medes-IMPS
 */
public class LotLocator extends Locator<Lot, String> {

	private LotHandler handler;

	public LotLocator() {

	}

	@Override
	public Lot create(Class<? extends Lot> clazz) {
		return new Lot();
	}

	@Override
	public Lot find(Class<? extends Lot> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<Lot> getDomainType() {
		return Lot.class;
	}

	@Override
	public String getId(Lot domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(Lot domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (LotHandler) context.getBean("lotHandler");
	}
}
