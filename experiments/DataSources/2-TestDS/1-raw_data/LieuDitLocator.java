package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.LieuDit;
import org.imogene.epicam.server.handler.LieuDitHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate LieuDit beans 
 * @author Medes-IMPS
 */
public class LieuDitLocator extends Locator<LieuDit, String> {

	private LieuDitHandler handler;

	public LieuDitLocator() {

	}

	@Override
	public LieuDit create(Class<? extends LieuDit> clazz) {
		return new LieuDit();
	}

	@Override
	public LieuDit find(Class<? extends LieuDit> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<LieuDit> getDomainType() {
		return LieuDit.class;
	}

	@Override
	public String getId(LieuDit domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(LieuDit domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (LieuDitHandler) context.getBean("lieuDitHandler");
	}
}
