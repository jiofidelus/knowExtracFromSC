package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.ExamenSerologie;
import org.imogene.epicam.server.locator.ExamenSerologieLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * ExamenSerologie proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = ExamenSerologie.class, locator = ExamenSerologieLocator.class)
public interface ExamenSerologieProxy extends ImogBeanProxy {

	/* Description section fields */

	public PatientProxy getPatient();
	public void setPatient(PatientProxy value);

	public Date getDateTest();
	public void setDateTest(Date value);

	public String getNature();
	public void setNature(String value);

	public String getResultatVIH();
	public void setResultatVIH(String value);

	public Integer getResultatCD4();
	public void setResultatCD4(Integer value);

}
