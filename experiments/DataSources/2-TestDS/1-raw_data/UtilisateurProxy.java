package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.Utilisateur;
import org.imogene.epicam.server.locator.UtilisateurLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.admin.client.i18n.AdminNLS;
import org.imogene.web.shared.proxy.ImogActorProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * Utilisateur proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = Utilisateur.class, locator = UtilisateurLocator.class)
public interface UtilisateurProxy extends ImogActorProxy {

	/* Identification section fields */

	public String getNom();
	public void setNom(String value);

	public Date getDateNaissance();
	public void setDateNaissance(Date value);

	public String getProfession();
	public void setProfession(String value);

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

}
