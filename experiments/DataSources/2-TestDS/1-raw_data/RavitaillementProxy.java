package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.Ravitaillement;
import org.imogene.epicam.server.locator.RavitaillementLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * Ravitaillement proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = Ravitaillement.class, locator = RavitaillementLocator.class)
public interface RavitaillementProxy extends ImogBeanProxy {

	/* InformationsDepart section fields */

	public RegionProxy getRegion();
	public void setRegion(RegionProxy value);

	public DistrictSanteProxy getDistrictSante();
	public void setDistrictSante(DistrictSanteProxy value);

	public CentreDiagTraitProxy getCDTDepart();
	public void setCDTDepart(CentreDiagTraitProxy value);

	public Date getDateDepart();
	public void setDateDepart(Date value);

	/* InformationArrivee section fields */

	public RegionProxy getRegionArrivee();
	public void setRegionArrivee(RegionProxy value);

	public DistrictSanteProxy getDistrictSanteArrivee();
	public void setDistrictSanteArrivee(DistrictSanteProxy value);

	public CentreDiagTraitProxy getCDTArrivee();
	public void setCDTArrivee(CentreDiagTraitProxy value);

	public Date getDateArrivee();
	public void setDateArrivee(Date value);

	/* Detail section fields */

	public List<DetailRavitaillementProxy> getDetails();
	public void setDetails(List<DetailRavitaillementProxy> value);

}
