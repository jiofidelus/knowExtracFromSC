package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.Lot;
import org.imogene.epicam.server.locator.LotLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * Lot proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = Lot.class, locator = LotLocator.class)
public interface LotProxy extends ImogBeanProxy {

	/* Description section fields */

	public RegionProxy getRegion();
	public void setRegion(RegionProxy value);

	public DistrictSanteProxy getDistrictSante();
	public void setDistrictSante(DistrictSanteProxy value);

	public CentreDiagTraitProxy getCDT();
	public void setCDT(CentreDiagTraitProxy value);

	public String getNumero();
	public void setNumero(String value);

	public String getType();
	public void setType(String value);

	public MedicamentProxy getMedicament();
	public void setMedicament(MedicamentProxy value);

	public IntrantProxy getIntrant();
	public void setIntrant(IntrantProxy value);

	public Integer getQuantiteInitiale();
	public void setQuantiteInitiale(Integer value);

	public Integer getQuantite();
	public void setQuantite(Integer value);

	public Date getDatePeremption();
	public void setDatePeremption(Date value);

}
