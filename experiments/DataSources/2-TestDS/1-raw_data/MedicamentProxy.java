package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.Medicament;
import org.imogene.epicam.server.locator.MedicamentLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * Medicament proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = Medicament.class, locator = MedicamentLocator.class)
public interface MedicamentProxy extends ImogBeanProxy {

	/* Description section fields */

	public String getCode();
	public void setCode(String value);

	public String getDesignation();
	public void setDesignation(String value);

	public Double getSeuilPatient();
	public void setSeuilPatient(Double value);

	public Boolean getEstMedicamentAntituberculeux();
	public void setEstMedicamentAntituberculeux(Boolean value);

}
