package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.PriseMedicamentRegime;
import org.imogene.epicam.server.locator.PriseMedicamentRegimeLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * PriseMedicamentRegime proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = PriseMedicamentRegime.class, locator = PriseMedicamentRegimeLocator.class)
public interface PriseMedicamentRegimeProxy extends ImogBeanProxy {

	/* Description section fields */

	public RegimeProxy getRegime();
	public void setRegime(RegimeProxy value);

	public MedicamentProxy getMedicament();
	public void setMedicament(MedicamentProxy value);

	public Double getQuantite();
	public void setQuantite(Double value);

	public String getQuantiteUnite();
	public void setQuantiteUnite(String value);

}
