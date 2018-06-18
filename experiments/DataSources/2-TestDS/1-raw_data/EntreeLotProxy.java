package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.EntreeLot;
import org.imogene.epicam.server.locator.EntreeLotLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * EntreeLot proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = EntreeLot.class, locator = EntreeLotLocator.class)
public interface EntreeLotProxy extends ImogBeanProxy {

	/* Description section fields */

	public CentreDiagTraitProxy getCDT();
	public void setCDT(CentreDiagTraitProxy value);

	public LotProxy getLot();
	public void setLot(LotProxy value);

	public Integer getQuantite();
	public void setQuantite(Integer value);

}
