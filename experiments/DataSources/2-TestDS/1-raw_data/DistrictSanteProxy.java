package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.DistrictSante;
import org.imogene.epicam.server.locator.DistrictSanteLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * DistrictSante proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = DistrictSante.class, locator = DistrictSanteLocator.class)
public interface DistrictSanteProxy extends ImogBeanProxy {

	/* Description section fields */

	public String getCode();
	public void setCode(String value);

	public LocalizedTextProxy getNom();
	public void setNom(LocalizedTextProxy value);

	public LocalizedTextProxy getDescription();
	public void setDescription(LocalizedTextProxy value);

	public RegionProxy getRegion();
	public void setRegion(RegionProxy value);

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
