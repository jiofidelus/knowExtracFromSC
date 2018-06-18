package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.TransfertReference;
import org.imogene.epicam.server.locator.TransfertReferenceLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * TransfertReference proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = TransfertReference.class, locator = TransfertReferenceLocator.class)
public interface TransfertReferenceProxy extends ImogBeanProxy {

	/* InformationsDepart section fields */

	public String getNature();
	public void setNature(String value);

	public RegionProxy getRegion();
	public void setRegion(RegionProxy value);

	public DistrictSanteProxy getDistrictSante();
	public void setDistrictSante(DistrictSanteProxy value);

	public CentreDiagTraitProxy getCDTDepart();
	public void setCDTDepart(CentreDiagTraitProxy value);

	public PatientProxy getPatient();
	public void setPatient(PatientProxy value);

	public Date getDateDepart();
	public void setDateDepart(Date value);

	public String getMotifDepart();
	public void setMotifDepart(String value);

	/* InformationArrivee section fields */

	public RegionProxy getRegionArrivee();
	public void setRegionArrivee(RegionProxy value);

	public DistrictSanteProxy getDistrictSanteArrivee();
	public void setDistrictSanteArrivee(DistrictSanteProxy value);

	public CentreDiagTraitProxy getCDTArrivee();
	public void setCDTArrivee(CentreDiagTraitProxy value);

	public Date getDateArrivee();
	public void setDateArrivee(Date value);

}
