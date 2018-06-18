package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.Inventaire;
import org.imogene.epicam.server.locator.InventaireLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * Inventaire proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = Inventaire.class, locator = InventaireLocator.class)
public interface InventaireProxy extends ImogBeanProxy {

	/* InformationsDepart section fields */

	public Date getDate();
	public void setDate(Date value);

	public RegionProxy getRegion();
	public void setRegion(RegionProxy value);

	public DistrictSanteProxy getDistrictSante();
	public void setDistrictSante(DistrictSanteProxy value);

	public CentreDiagTraitProxy getCDT();
	public void setCDT(CentreDiagTraitProxy value);

	public List<DetailInventaireProxy> getDetails();
	public void setDetails(List<DetailInventaireProxy> value);

}
