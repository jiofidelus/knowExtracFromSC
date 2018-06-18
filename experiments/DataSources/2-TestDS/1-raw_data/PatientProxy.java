package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.Patient;
import org.imogene.epicam.server.locator.PatientLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * Patient proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = Patient.class, locator = PatientLocator.class)
public interface PatientProxy extends ImogBeanProxy {

	/* Identification section fields */

	public String getIdentifiant();
	public void setIdentifiant(String value);

	public String getNom();
	public void setNom(String value);

	public String getSexe();
	public void setSexe(String value);

	public Date getDateNaissance();
	public void setDateNaissance(Date value);

	public Integer getAge();
	public void setAge(Integer value);

	public String getProfession();
	public void setProfession(String value);

	public List<CentreDiagTraitProxy> getCentres();
	public void setCentres(List<CentreDiagTraitProxy> value);

	public String getNationalite();
	public void setNationalite(String value);

	public String getPrecisionSurNationalite();
	public void setPrecisionSurNationalite(String value);

	public Boolean getRecevoirCarnetTelPortable();
	public void setRecevoirCarnetTelPortable(Boolean value);

	/* Contact section fields */

	public String getTelephoneUn();
	public void setTelephoneUn(String value);

	public String getTelephoneDeux();
	public void setTelephoneDeux(String value);

	public String getTelephoneTrois();
	public void setTelephoneTrois(String value);

	public String getEmail();
	public void setEmail(String value);

	public String getLibelle();
	public void setLibelle(String value);

	public String getComplementAdresse();
	public void setComplementAdresse(String value);

	public String getQuartier();
	public void setQuartier(String value);

	public String getVille();
	public void setVille(String value);

	public String getCodePostal();
	public void setCodePostal(String value);

	public List<LieuDitProxy> getLieuxDits();
	public void setLieuxDits(List<LieuDitProxy> value);

	/* PersonneContact section fields */

	public String getPacNom();
	public void setPacNom(String value);

	public String getPacTelephoneUn();
	public void setPacTelephoneUn(String value);

	public String getPacTelephoneDeux();
	public void setPacTelephoneDeux(String value);

	public String getPacTelephoneTrois();
	public void setPacTelephoneTrois(String value);

	public String getPacEmail();
	public void setPacEmail(String value);

	public String getPacLibelle();
	public void setPacLibelle(String value);

	public String getPacComplementAdresse();
	public void setPacComplementAdresse(String value);

	public String getPacQuartier();
	public void setPacQuartier(String value);

	public String getPacVille();
	public void setPacVille(String value);

	public String getPacCodePostal();
	public void setPacCodePostal(String value);

	/* Tuberculose section fields */

	public List<CasTuberculoseProxy> getCasTuberculose();
	public void setCasTuberculose(List<CasTuberculoseProxy> value);

	public List<CasIndexProxy> getCasIndex();
	public void setCasIndex(List<CasIndexProxy> value);

	/* Examens section fields */

	public List<ExamenBiologiqueProxy> getExamensBiologiques();
	public void setExamensBiologiques(List<ExamenBiologiqueProxy> value);

	public List<ExamenSerologieProxy> getSerologies();
	public void setSerologies(List<ExamenSerologieProxy> value);

}
