package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.SortieLot;
import org.imogene.epicam.server.locator.SortieLotLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * SortieLot proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = SortieLot.class, locator = SortieLotLocator.class)
public interface SortieLotProxy extends ImogBeanProxy {

	/* Description section fields */

	public CentreDiagTraitProxy getCDT();
	public void setCDT(CentreDiagTraitProxy value);

	public LotProxy getLot();
	public void setLot(LotProxy value);

	public Integer getQuantite();
	public void setQuantite(Integer value);

}
