package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.DistrictSante;
import org.imogene.epicam.server.handler.DistrictSanteHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate DistrictSante beans 
 * @author Medes-IMPS
 */
public class DistrictSanteLocator extends Locator<DistrictSante, String> {

	private DistrictSanteHandler handler;

	public DistrictSanteLocator() {

	}

	@Override
	public DistrictSante create(Class<? extends DistrictSante> clazz) {
		return new DistrictSante();
	}

	@Override
	public DistrictSante find(Class<? extends DistrictSante> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<DistrictSante> getDomainType() {
		return DistrictSante.class;
	}

	@Override
	public String getId(DistrictSante domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(DistrictSante domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (DistrictSanteHandler) context
				.getBean("districtSanteHandler");
	}
}
