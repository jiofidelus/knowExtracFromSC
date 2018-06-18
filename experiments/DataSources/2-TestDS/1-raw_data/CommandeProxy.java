package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.Commande;
import org.imogene.epicam.server.locator.CommandeLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * Commande proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = Commande.class, locator = CommandeLocator.class)
public interface CommandeProxy extends ImogBeanProxy {

	/* InformationsDepart section fields */

	public Date getDate();
	public void setDate(Date value);

	public RegionProxy getRegion();
	public void setRegion(RegionProxy value);

	public DistrictSanteProxy getDistrictSante();
	public void setDistrictSante(DistrictSanteProxy value);

	public CentreDiagTraitProxy getCDT();
	public void setCDT(CentreDiagTraitProxy value);

	public List<DetailCommandeMedicamentProxy> getMedicaments();
	public void setMedicaments(List<DetailCommandeMedicamentProxy> value);

	public List<DetailCommandeIntrantProxy> getIntrants();
	public void setIntrants(List<DetailCommandeIntrantProxy> value);

	/* RegionApprobation section fields */

	public Boolean getApprouveeRegion();
	public void setApprouveeRegion(Boolean value);

	public String getMotifRejetRegion();
	public void setMotifRejetRegion(String value);

	/* GtcApprobation section fields */

	public Boolean getApprouveeGTC();
	public void setApprouveeGTC(Boolean value);

	public String getMotifRejetGTC();
	public void setMotifRejetGTC(String value);

}
