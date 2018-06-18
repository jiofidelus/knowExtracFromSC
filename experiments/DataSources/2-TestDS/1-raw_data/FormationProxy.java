package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.Formation;
import org.imogene.epicam.server.locator.FormationLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * Formation proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = Formation.class, locator = FormationLocator.class)
public interface FormationProxy extends ImogBeanProxy {

	/* Description section fields */

	public LocalizedTextProxy getLibelle();
	public void setLibelle(LocalizedTextProxy value);

	public Date getDateDebut();
	public void setDateDebut(Date value);

	public Date getDateFin();
	public void setDateFin(Date value);

	public LocalizedTextProxy getLieu();
	public void setLieu(LocalizedTextProxy value);

	public LocalizedTextProxy getObjectifs();
	public void setObjectifs(LocalizedTextProxy value);

	public Boolean getEffectuee();
	public void setEffectuee(Boolean value);

	public List<CandidatureFormationProxy> getCandidatures();
	public void setCandidatures(List<CandidatureFormationProxy> value);

}
