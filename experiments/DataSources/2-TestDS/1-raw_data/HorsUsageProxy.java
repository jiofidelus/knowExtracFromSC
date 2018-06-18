package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.HorsUsage;
import org.imogene.epicam.server.locator.HorsUsageLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * HorsUsage proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = HorsUsage.class, locator = HorsUsageLocator.class)
public interface HorsUsageProxy extends ImogBeanProxy {

	/* Description section fields */

	public SortieLotProxy getLot();
	public void setLot(SortieLotProxy value);

	public String getType();
	public void setType(String value);

	public String getMotif();
	public void setMotif(String value);

}
