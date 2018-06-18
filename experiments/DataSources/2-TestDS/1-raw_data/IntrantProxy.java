package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.Intrant;
import org.imogene.epicam.server.locator.IntrantLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * Intrant proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = Intrant.class, locator = IntrantLocator.class)
public interface IntrantProxy extends ImogBeanProxy {

	/* Description section fields */

	public String getIdentifiant();
	public void setIdentifiant(String value);

	public String getNom();
	public void setNom(String value);

	public String getDescription();
	public void setDescription(String value);

	public Double getSeuilPatient();
	public void setSeuilPatient(Double value);

}
