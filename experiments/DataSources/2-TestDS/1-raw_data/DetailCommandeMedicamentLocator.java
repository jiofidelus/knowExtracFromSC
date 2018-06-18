package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.DetailCommandeMedicament;
import org.imogene.epicam.server.handler.DetailCommandeMedicamentHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate DetailCommandeMedicament beans 
 * @author Medes-IMPS
 */
public class DetailCommandeMedicamentLocator
		extends
			Locator<DetailCommandeMedicament, String> {

	private DetailCommandeMedicamentHandler handler;

	public DetailCommandeMedicamentLocator() {

	}

	@Override
	public DetailCommandeMedicament create(
			Class<? extends DetailCommandeMedicament> clazz) {
		return new DetailCommandeMedicament();
	}

	@Override
	public DetailCommandeMedicament find(
			Class<? extends DetailCommandeMedicament> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<DetailCommandeMedicament> getDomainType() {
		return DetailCommandeMedicament.class;
	}

	@Override
	public String getId(DetailCommandeMedicament domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(DetailCommandeMedicament domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (DetailCommandeMedicamentHandler) context
				.getBean("detailCommandeMedicamentHandler");
	}
}
