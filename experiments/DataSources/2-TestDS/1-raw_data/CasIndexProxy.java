package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.CasIndex;
import org.imogene.epicam.server.locator.CasIndexLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * CasIndex proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = CasIndex.class, locator = CasIndexLocator.class)
public interface CasIndexProxy extends ImogBeanProxy {

	/* Description section fields */

	public PatientProxy getPatient();
	public void setPatient(PatientProxy value);

	public PatientProxy getPatientLie();
	public void setPatientLie(PatientProxy value);

	public String getTypeRelation();
	public void setTypeRelation(String value);

}
