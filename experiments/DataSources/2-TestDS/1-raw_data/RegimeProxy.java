package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.Regime;
import org.imogene.epicam.server.locator.RegimeLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * Regime proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = Regime.class, locator = RegimeLocator.class)
public interface RegimeProxy extends ImogBeanProxy {

	/* Description section fields */

	public String getNom();
	public void setNom(String value);

	public String getType();
	public void setType(String value);

	public Integer getDureeTraitement();
	public void setDureeTraitement(Integer value);

	public Integer getPoidsMin();
	public void setPoidsMin(Integer value);

	public Integer getPoidsMax();
	public void setPoidsMax(Integer value);

	public LocalizedTextProxy getDescription();
	public void setDescription(LocalizedTextProxy value);

	public List<PriseMedicamentRegimeProxy> getPrisesMedicamenteuses();
	public void setPrisesMedicamenteuses(List<PriseMedicamentRegimeProxy> value);

	public Boolean getActif();
	public void setActif(Boolean value);

}
