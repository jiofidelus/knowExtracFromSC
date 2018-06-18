package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.PriseMedicamenteuse;
import org.imogene.epicam.server.locator.PriseMedicamenteuseLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * PriseMedicamenteuse proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = PriseMedicamenteuse.class, locator = PriseMedicamenteuseLocator.class)
public interface PriseMedicamenteuseProxy extends ImogBeanProxy {

	/* Description section fields */

	public CasTuberculoseProxy getPhaseIntensive();
	public void setPhaseIntensive(CasTuberculoseProxy value);

	public CasTuberculoseProxy getPhaseContinuation();
	public void setPhaseContinuation(CasTuberculoseProxy value);

	public Date getDateEffective();
	public void setDateEffective(Date value);

	public String getPrise();
	public void setPrise(String value);

	public Boolean getCotrimoxazole();
	public void setCotrimoxazole(Boolean value);

}
