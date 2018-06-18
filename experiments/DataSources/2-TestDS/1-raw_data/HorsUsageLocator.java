package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.HorsUsage;
import org.imogene.epicam.server.handler.HorsUsageHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate HorsUsage beans 
 * @author Medes-IMPS
 */
public class HorsUsageLocator extends Locator<HorsUsage, String> {

	private HorsUsageHandler handler;

	public HorsUsageLocator() {

	}

	@Override
	public HorsUsage create(Class<? extends HorsUsage> clazz) {
		return new HorsUsage();
	}

	@Override
	public HorsUsage find(Class<? extends HorsUsage> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<HorsUsage> getDomainType() {
		return HorsUsage.class;
	}

	@Override
	public String getId(HorsUsage domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(HorsUsage domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (HorsUsageHandler) context.getBean("horsUsageHandler");
	}
}
