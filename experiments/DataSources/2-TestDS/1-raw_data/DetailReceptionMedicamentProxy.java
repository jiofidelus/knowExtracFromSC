package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.DetailReceptionMedicament;
import org.imogene.epicam.server.locator.DetailReceptionMedicamentLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * DetailReceptionMedicament proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = DetailReceptionMedicament.class, locator = DetailReceptionMedicamentLocator.class)
public interface DetailReceptionMedicamentProxy extends ImogBeanProxy {

	/* Description section fields */

	public ReceptionProxy getReception();
	public void setReception(ReceptionProxy value);

	public CommandeProxy getCommande();
	public void setCommande(CommandeProxy value);

	public DetailCommandeMedicamentProxy getDetailCommande();
	public void setDetailCommande(DetailCommandeMedicamentProxy value);

	public EntreeLotProxy getEntreeLot();
	public void setEntreeLot(EntreeLotProxy value);

}
