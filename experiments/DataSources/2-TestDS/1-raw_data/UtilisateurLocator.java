package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.Utilisateur;
import org.imogene.epicam.server.handler.UtilisateurHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate Utilisateur beans 
 * @author Medes-IMPS
 */
public class UtilisateurLocator extends Locator<Utilisateur, String> {

	private UtilisateurHandler handler;

	public UtilisateurLocator() {

	}

	@Override
	public Utilisateur create(Class<? extends Utilisateur> clazz) {
		return new Utilisateur();
	}

	@Override
	public Utilisateur find(Class<? extends Utilisateur> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<Utilisateur> getDomainType() {
		return Utilisateur.class;
	}

	@Override
	public String getId(Utilisateur domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(Utilisateur domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (UtilisateurHandler) context.getBean("utilisateurHandler");
	}
}
