package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.DetailCommandeMedicament;
import org.imogene.epicam.server.locator.DetailCommandeMedicamentLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * DetailCommandeMedicament proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = DetailCommandeMedicament.class, locator = DetailCommandeMedicamentLocator.class)
public interface DetailCommandeMedicamentProxy extends ImogBeanProxy {

	/* Description section fields */

	public CommandeProxy getCommande();
	public void setCommande(CommandeProxy value);

	public MedicamentProxy getMedicament();
	public void setMedicament(MedicamentProxy value);

	public Integer getQuantiteRequise();
	public void setQuantiteRequise(Integer value);

	public Integer getQuantiteEnStock();
	public void setQuantiteEnStock(Integer value);

}
