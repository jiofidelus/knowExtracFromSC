package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.ExamenMicroscopie;
import org.imogene.epicam.server.locator.ExamenMicroscopieLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * ExamenMicroscopie proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = ExamenMicroscopie.class, locator = ExamenMicroscopieLocator.class)
public interface ExamenMicroscopieProxy extends ImogBeanProxy {

	/* CentreExamen section fields */

	public CentreDiagTraitProxy getCDT();
	public void setCDT(CentreDiagTraitProxy value);

	public LaboratoireReferenceProxy getLaboratoireReference();
	public void setLaboratoireReference(LaboratoireReferenceProxy value);

	/* Examen section fields */

	public CasTuberculoseProxy getCasTb();
	public void setCasTb(CasTuberculoseProxy value);

	public Date getDate();
	public void setDate(Date value);

	public String getRaisonDepistage();
	public void setRaisonDepistage(String value);

	public String getResultat();
	public void setResultat(String value);

	public String getObservations();
	public void setObservations(String value);

}
