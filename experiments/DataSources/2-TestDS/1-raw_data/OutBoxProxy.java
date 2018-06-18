package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.OutBox;
import org.imogene.epicam.server.locator.OutBoxLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * OutBox proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = OutBox.class, locator = OutBoxLocator.class)
public interface OutBoxProxy extends ImogBeanProxy {

	/* Adresse section fields */

	public PatientProxy getPatient();
	public void setPatient(PatientProxy value);

	/* MessageInformation section fields */

	public String getMessage();
	public void setMessage(String value);

	public String getReponse();
	public void setReponse(String value);

	public String getStatut();
	public void setStatut(String value);

	public Date getDateDernierEssai();
	public void setDateDernierEssai(Date value);

}
