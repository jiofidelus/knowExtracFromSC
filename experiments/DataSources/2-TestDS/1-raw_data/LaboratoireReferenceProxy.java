package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.LaboratoireReference;
import org.imogene.epicam.server.locator.LaboratoireReferenceLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * LaboratoireReference proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = LaboratoireReference.class, locator = LaboratoireReferenceLocator.class)
public interface LaboratoireReferenceProxy extends ImogBeanProxy {

	/* Description section fields */

	public LocalizedTextProxy getNom();
	public void setNom(LocalizedTextProxy value);

	public String getNature();
	public void setNature(String value);

	public LocalizedTextProxy getDescription();
	public void setDescription(LocalizedTextProxy value);

	public RegionProxy getRegion();
	public void setRegion(RegionProxy value);

	public DistrictSanteProxy getDistrictSante();
	public void setDistrictSante(DistrictSanteProxy value);

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

	public List<LieuDitProxy> getLieuxDits();
	public void setLieuxDits(List<LieuDitProxy> value);

}
