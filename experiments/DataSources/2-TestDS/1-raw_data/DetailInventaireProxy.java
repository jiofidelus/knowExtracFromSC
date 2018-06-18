package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.DetailInventaire;
import org.imogene.epicam.server.locator.DetailInventaireLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * DetailInventaire proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = DetailInventaire.class, locator = DetailInventaireLocator.class)
public interface DetailInventaireProxy extends ImogBeanProxy {

	/* Description section fields */

	public InventaireProxy getInventaire();
	public void setInventaire(InventaireProxy value);

	public CentreDiagTraitProxy getCDT();
	public void setCDT(CentreDiagTraitProxy value);

	public LotProxy getLot();
	public void setLot(LotProxy value);

	public Integer getQuantiteReelle();
	public void setQuantiteReelle(Integer value);

	public Integer getQuantiteTheorique();
	public void setQuantiteTheorique(Integer value);

}
