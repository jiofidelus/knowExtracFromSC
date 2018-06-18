package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.DepartPersonnel;
import org.imogene.epicam.server.locator.DepartPersonnelLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * DepartPersonnel proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = DepartPersonnel.class, locator = DepartPersonnelLocator.class)
public interface DepartPersonnelProxy extends ImogBeanProxy {

	/* Personnel section fields */

	public RegionProxy getRegion();
	public void setRegion(RegionProxy value);

	public DistrictSanteProxy getDistrictSante();
	public void setDistrictSante(DistrictSanteProxy value);

	public CentreDiagTraitProxy getCDT();
	public void setCDT(CentreDiagTraitProxy value);

	public PersonnelProxy getPersonnel();
	public void setPersonnel(PersonnelProxy value);

	/* Description section fields */

	public Date getDateDepart();
	public void setDateDepart(Date value);

	public String getMotifDepart();
	public void setMotifDepart(String value);

}
