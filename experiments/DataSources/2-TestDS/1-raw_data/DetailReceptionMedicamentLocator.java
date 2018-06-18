package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.DetailReceptionMedicament;
import org.imogene.epicam.server.handler.DetailReceptionMedicamentHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate DetailReceptionMedicament beans 
 * @author Medes-IMPS
 */
public class DetailReceptionMedicamentLocator
		extends
			Locator<DetailReceptionMedicament, String> {

	private DetailReceptionMedicamentHandler handler;

	public DetailReceptionMedicamentLocator() {

	}

	@Override
	public DetailReceptionMedicament create(
			Class<? extends DetailReceptionMedicament> clazz) {
		return new DetailReceptionMedicament();
	}

	@Override
	public DetailReceptionMedicament find(
			Class<? extends DetailReceptionMedicament> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<DetailReceptionMedicament> getDomainType() {
		return DetailReceptionMedicament.class;
	}

	@Override
	public String getId(DetailReceptionMedicament domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(DetailReceptionMedicament domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (DetailReceptionMedicamentHandler) context
				.getBean("detailReceptionMedicamentHandler");
	}
}
