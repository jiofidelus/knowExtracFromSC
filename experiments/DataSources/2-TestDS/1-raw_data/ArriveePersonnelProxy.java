package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.ArriveePersonnel;
import org.imogene.epicam.server.locator.ArriveePersonnelLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * ArriveePersonnel proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = ArriveePersonnel.class, locator = ArriveePersonnelLocator.class)
public interface ArriveePersonnelProxy extends ImogBeanProxy {

	/* Description section fields */

	public RegionProxy getRegion();
	public void setRegion(RegionProxy value);

	public DistrictSanteProxy getDistrictSante();
	public void setDistrictSante(DistrictSanteProxy value);

	public CentreDiagTraitProxy getCDT();
	public void setCDT(CentreDiagTraitProxy value);

	public PersonnelProxy getPersonnel();
	public void setPersonnel(PersonnelProxy value);

	public Date getDateArrivee();
	public void setDateArrivee(Date value);

}
