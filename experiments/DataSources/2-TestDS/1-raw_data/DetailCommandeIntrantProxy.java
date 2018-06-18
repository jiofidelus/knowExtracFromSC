package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.DetailCommandeIntrant;
import org.imogene.epicam.server.locator.DetailCommandeIntrantLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * DetailCommandeIntrant proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = DetailCommandeIntrant.class, locator = DetailCommandeIntrantLocator.class)
public interface DetailCommandeIntrantProxy extends ImogBeanProxy {

	/* Description section fields */

	public CommandeProxy getCommande();
	public void setCommande(CommandeProxy value);

	public IntrantProxy getIntrant();
	public void setIntrant(IntrantProxy value);

	public Integer getQuantiteRequise();
	public void setQuantiteRequise(Integer value);

	public Integer getQuantiteEnStock();
	public void setQuantiteEnStock(Integer value);

}
