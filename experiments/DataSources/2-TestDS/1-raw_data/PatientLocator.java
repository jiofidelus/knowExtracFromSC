package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.Patient;
import org.imogene.epicam.server.handler.PatientHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate Patient beans 
 * @author Medes-IMPS
 */
public class PatientLocator extends Locator<Patient, String> {

	private PatientHandler handler;

	public PatientLocator() {

	}

	@Override
	public Patient create(Class<? extends Patient> clazz) {
		return new Patient();
	}

	@Override
	public Patient find(Class<? extends Patient> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<Patient> getDomainType() {
		return Patient.class;
	}

	@Override
	public String getId(Patient domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(Patient domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (PatientHandler) context.getBean("patientHandler");
	}
}
