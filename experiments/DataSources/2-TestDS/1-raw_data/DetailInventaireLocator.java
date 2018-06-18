package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.DetailInventaire;
import org.imogene.epicam.server.handler.DetailInventaireHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate DetailInventaire beans 
 * @author Medes-IMPS
 */
public class DetailInventaireLocator extends Locator<DetailInventaire, String> {

	private DetailInventaireHandler handler;

	public DetailInventaireLocator() {

	}

	@Override
	public DetailInventaire create(Class<? extends DetailInventaire> clazz) {
		return new DetailInventaire();
	}

	@Override
	public DetailInventaire find(Class<? extends DetailInventaire> clazz,
			String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<DetailInventaire> getDomainType() {
		return DetailInventaire.class;
	}

	@Override
	public String getId(DetailInventaire domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(DetailInventaire domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (DetailInventaireHandler) context
				.getBean("detailInventaireHandler");
	}
}
