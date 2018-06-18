package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.CasTuberculose;
import org.imogene.epicam.server.locator.CasTuberculoseLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * CasTuberculose proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = CasTuberculose.class, locator = CasTuberculoseLocator.class)
public interface CasTuberculoseProxy extends ImogBeanProxy {

	/* Informations section fields */

	public String getIdentifiant();
	public void setIdentifiant(String value);

	public String getNumRegTB();
	public void setNumRegTB(String value);

	public PatientProxy getPatient();
	public void setPatient(PatientProxy value);

	public Date getDateDebutTraitement();
	public void setDateDebutTraitement(Date value);

	public String getTypePatient();
	public void setTypePatient(String value);

	public String getTypePatientPrecisions();
	public void setTypePatientPrecisions(String value);

	public String getFormeMaladie();
	public void setFormeMaladie(String value);

	public String getExtraPulmonairePrecisions();
	public void setExtraPulmonairePrecisions(String value);

	public String getCotrimoxazole();
	public void setCotrimoxazole(String value);

	public Boolean getAntiRetroViraux();
	public void setAntiRetroViraux(Boolean value);

	public Boolean getFumeur();
	public void setFumeur(Boolean value);

	public Boolean getFumeurArreter();
	public void setFumeurArreter(Boolean value);

	/* Examen section fields */

	public List<ExamenMicroscopieProxy> getExamensMiscrocopies();
	public void setExamensMiscrocopies(List<ExamenMicroscopieProxy> value);

	public List<ExamenATBProxy> getExamensATB();
	public void setExamensATB(List<ExamenATBProxy> value);

	/* Traitement section fields */

	public RegimeProxy getRegimePhaseInitiale();
	public void setRegimePhaseInitiale(RegimeProxy value);

	public RegimeProxy getRegimePhaseContinuation();
	public void setRegimePhaseContinuation(RegimeProxy value);

	public List<PriseMedicamenteuseProxy> getPriseMedicamenteusePhaseInitiale();
	public void setPriseMedicamenteusePhaseInitiale(
			List<PriseMedicamenteuseProxy> value);

	public List<PriseMedicamenteuseProxy> getPriseMedicamenteusePhaseContinuation();
	public void setPriseMedicamenteusePhaseContinuation(
			List<PriseMedicamenteuseProxy> value);

	public List<RendezVousProxy> getRendezVous();
	public void setRendezVous(List<RendezVousProxy> value);

	/* FinTraitement section fields */

	public Date getDateFinTraitement();
	public void setDateFinTraitement(Date value);

	public String getDevenirMalade();
	public void setDevenirMalade(String value);

	public String getObservation();
	public void setObservation(String value);

}
