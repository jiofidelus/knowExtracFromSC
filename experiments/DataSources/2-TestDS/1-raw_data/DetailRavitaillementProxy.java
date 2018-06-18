package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.DetailRavitaillement;
import org.imogene.epicam.server.locator.DetailRavitaillementLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * DetailRavitaillement proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = DetailRavitaillement.class, locator = DetailRavitaillementLocator.class)
public interface DetailRavitaillementProxy extends ImogBeanProxy {

	/* Description section fields */

	public RavitaillementProxy getRavitaillement();
	public void setRavitaillement(RavitaillementProxy value);

	/* Sortie section fields */

	public SortieLotProxy getSortieLot();
	public void setSortieLot(SortieLotProxy value);

	/* Entree section fields */

	public EntreeLotProxy getEntreeLot();
	public void setEntreeLot(EntreeLotProxy value);

}
