package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.Commande;
import org.imogene.epicam.server.handler.CommandeHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate Commande beans 
 * @author Medes-IMPS
 */
public class CommandeLocator extends Locator<Commande, String> {

	private CommandeHandler handler;

	public CommandeLocator() {

	}

	@Override
	public Commande create(Class<? extends Commande> clazz) {
		return new Commande();
	}

	@Override
	public Commande find(Class<? extends Commande> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<Commande> getDomainType() {
		return Commande.class;
	}

	@Override
	public String getId(Commande domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(Commande domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (CommandeHandler) context.getBean("commandeHandler");
	}
}
