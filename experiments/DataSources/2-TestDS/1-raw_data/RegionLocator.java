package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.Region;
import org.imogene.epicam.server.handler.RegionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate Region beans 
 * @author Medes-IMPS
 */
public class RegionLocator extends Locator<Region, String> {

	private RegionHandler handler;

	public RegionLocator() {

	}

	@Override
	public Region create(Class<? extends Region> clazz) {
		return new Region();
	}

	@Override
	public Region find(Class<? extends Region> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<Region> getDomainType() {
		return Region.class;
	}

	@Override
	public String getId(Region domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(Region domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (RegionHandler) context.getBean("regionHandler");
	}
}
