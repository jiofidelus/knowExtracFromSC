package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.Reception;
import org.imogene.epicam.server.locator.ReceptionLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * Reception proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = Reception.class, locator = ReceptionLocator.class)
public interface ReceptionProxy extends ImogBeanProxy {

	/* Description section fields */

	public RegionProxy getRegion();
	public void setRegion(RegionProxy value);

	public DistrictSanteProxy getDistrictSante();
	public void setDistrictSante(DistrictSanteProxy value);

	public CentreDiagTraitProxy getCDT();
	public void setCDT(CentreDiagTraitProxy value);

	public CommandeProxy getCommande();
	public void setCommande(CommandeProxy value);

	public Date getDateReception();
	public void setDateReception(Date value);

	public List<DetailReceptionMedicamentProxy> getMedicaments();
	public void setMedicaments(List<DetailReceptionMedicamentProxy> value);

	public List<DetailReceptionIntrantProxy> getIntrants();
	public void setIntrants(List<DetailReceptionIntrantProxy> value);

}
