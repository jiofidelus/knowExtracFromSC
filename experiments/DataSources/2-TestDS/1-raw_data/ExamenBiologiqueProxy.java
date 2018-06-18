package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.ExamenBiologique;
import org.imogene.epicam.server.locator.ExamenBiologiqueLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * ExamenBiologique proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = ExamenBiologique.class, locator = ExamenBiologiqueLocator.class)
public interface ExamenBiologiqueProxy extends ImogBeanProxy {

	/* Description section fields */

	public PatientProxy getPatient();
	public void setPatient(PatientProxy value);

	public Date getDate();
	public void setDate(Date value);

	public Double getPoids();
	public void setPoids(Double value);

	public String getObservations();
	public void setObservations(String value);

}
