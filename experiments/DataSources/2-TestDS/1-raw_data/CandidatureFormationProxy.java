package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.CandidatureFormation;
import org.imogene.epicam.server.locator.CandidatureFormationLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * CandidatureFormation proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = CandidatureFormation.class, locator = CandidatureFormationLocator.class)
public interface CandidatureFormationProxy extends ImogBeanProxy {

	/* Description section fields */

	public FormationProxy getFormation();
	public void setFormation(FormationProxy value);

	public RegionProxy getRegion();
	public void setRegion(RegionProxy value);

	public DistrictSanteProxy getDistrictSante();
	public void setDistrictSante(DistrictSanteProxy value);

	public CentreDiagTraitProxy getCDT();
	public void setCDT(CentreDiagTraitProxy value);

	public PersonnelProxy getPersonnel();
	public void setPersonnel(PersonnelProxy value);

	/* RegionApprobation section fields */

	public Boolean getApprouveeRegion();
	public void setApprouveeRegion(Boolean value);

	public String getMotifRejetRegion();
	public void setMotifRejetRegion(String value);

	/* GtcApprobation section fields */

	public Boolean getApprouveeGTC();
	public void setApprouveeGTC(Boolean value);

	public String getMotifRejetGTC();
	public void setMotifRejetGTC(String value);

}
