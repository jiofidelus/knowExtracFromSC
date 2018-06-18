package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.Inventaire;
import org.imogene.epicam.server.handler.InventaireHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate Inventaire beans 
 * @author Medes-IMPS
 */
public class InventaireLocator extends Locator<Inventaire, String> {

	private InventaireHandler handler;

	public InventaireLocator() {

	}

	@Override
	public Inventaire create(Class<? extends Inventaire> clazz) {
		return new Inventaire();
	}

	@Override
	public Inventaire find(Class<? extends Inventaire> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<Inventaire> getDomainType() {
		return Inventaire.class;
	}

	@Override
	public String getId(Inventaire domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(Inventaire domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (InventaireHandler) context.getBean("inventaireHandler");
	}
}
