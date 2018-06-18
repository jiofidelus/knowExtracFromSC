package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.RendezVous;
import org.imogene.epicam.server.locator.RendezVousLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * RendezVous proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = RendezVous.class, locator = RendezVousLocator.class)
public interface RendezVousProxy extends ImogBeanProxy {

	/* Description section fields */

	public CasTuberculoseProxy getCasTb();
	public void setCasTb(CasTuberculoseProxy value);

	public Date getDateRendezVous();
	public void setDateRendezVous(Date value);

	public Boolean getHonore();
	public void setHonore(Boolean value);

	public Integer getNombreSMSEnvoye();
	public void setNombreSMSEnvoye(Integer value);

	public String getCommentaire();
	public void setCommentaire(String value);

}
