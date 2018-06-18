package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.DetailReceptionIntrant;
import org.imogene.epicam.server.locator.DetailReceptionIntrantLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * DetailReceptionIntrant proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = DetailReceptionIntrant.class, locator = DetailReceptionIntrantLocator.class)
public interface DetailReceptionIntrantProxy extends ImogBeanProxy {

	/* Description section fields */

	public ReceptionProxy getReception();
	public void setReception(ReceptionProxy value);

	public CommandeProxy getCommande();
	public void setCommande(CommandeProxy value);

	public DetailCommandeIntrantProxy getDetailCommande();
	public void setDetailCommande(DetailCommandeIntrantProxy value);

	public EntreeLotProxy getEntreeLot();
	public void setEntreeLot(EntreeLotProxy value);

}
