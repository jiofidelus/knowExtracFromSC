package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.Region;
import org.imogene.epicam.server.locator.RegionLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * Region proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = Region.class, locator = RegionLocator.class)
public interface RegionProxy extends ImogBeanProxy {

	/* Description section fields */

	public String getCode();
	public void setCode(String value);

	public LocalizedTextProxy getNom();
	public void setNom(LocalizedTextProxy value);

	public LocalizedTextProxy getDescription();
	public void setDescription(LocalizedTextProxy value);

	public List<DistrictSanteProxy> getDistrictSantes();
	public void setDistrictSantes(List<DistrictSanteProxy> value);

	/* Adresse section fields */

	public String getLibelle();
	public void setLibelle(String value);

	public String getComplementAdresse();
	public void setComplementAdresse(String value);

	public String getQuartier();
	public void setQuartier(String value);

	public String getVille();
	public void setVille(String value);

	public String getCodePostal();
	public void setCodePostal(String value);

	public GeoFieldProxy getCoordonnees();
	public void setCoordonnees(GeoFieldProxy value);

}
