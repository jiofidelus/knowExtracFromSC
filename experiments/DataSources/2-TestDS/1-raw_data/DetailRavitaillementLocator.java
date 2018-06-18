package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.DetailRavitaillement;
import org.imogene.epicam.server.handler.DetailRavitaillementHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate DetailRavitaillement beans 
 * @author Medes-IMPS
 */
public class DetailRavitaillementLocator
		extends
			Locator<DetailRavitaillement, String> {

	private DetailRavitaillementHandler handler;

	public DetailRavitaillementLocator() {

	}

	@Override
	public DetailRavitaillement create(
			Class<? extends DetailRavitaillement> clazz) {
		return new DetailRavitaillement();
	}

	@Override
	public DetailRavitaillement find(
			Class<? extends DetailRavitaillement> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<DetailRavitaillement> getDomainType() {
		return DetailRavitaillement.class;
	}

	@Override
	public String getId(DetailRavitaillement domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(DetailRavitaillement domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (DetailRavitaillementHandler) context
				.getBean("detailRavitaillementHandler");
	}
}
