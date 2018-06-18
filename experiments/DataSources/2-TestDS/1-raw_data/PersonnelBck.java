package org.imogene.epicam.domain.entity.backup;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.imogene.lib.common.entity.GeoField;
import org.imogene.lib.common.entity.ImogBeanImpl;
import org.imogene.lib.common.entity.ImogBeanBck;
import org.imogene.epicam.domain.entity.LocalizedText;

/**
 * ImogBean implementation for the entity Personnel Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "Personnel_Bck")
public class PersonnelBck extends ImogBeanBck {

	private static final long serialVersionUID = -6990780428803940119L;

	/* Identification group fields */

	private String nom;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateNaissance;

	private String profession;

	/* Contact group fields */

	private String telephoneUn;

	private String telephoneDeux;

	private String telephoneTrois;

	private String email;

	private String libelle;

	@Column(columnDefinition = "TEXT")
	private String complementAdresse;

	private String quartier;

	private String ville;

	private String codePostal;

	/* Situation group fields */

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDepart;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateArrivee;

	private Boolean AEteForme;

	private String qualification;

	/* NiveauAccess group fields */

	private String niveau;

	private String region;

	private String districtSante;

	private String CDT;

	private Boolean actif;

	/**
	 * Constructor
	 */
	public PersonnelBck() {
	}

	/* Getters and Setters for Identification group fields */

	public String getNom() {
		return nom;
	}

	public void setNom(String value) {
		nom = value;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date value) {
		dateNaissance = value;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String value) {
		profession = value;
	}

	/* Getters and Setters for Contact group fields */

	public String getTelephoneUn() {
		return telephoneUn;
	}

	public void setTelephoneUn(String value) {
		telephoneUn = value;
	}

	public String getTelephoneDeux() {
		return telephoneDeux;
	}

	public void setTelephoneDeux(String value) {
		telephoneDeux = value;
	}

	public String getTelephoneTrois() {
		return telephoneTrois;
	}

	public void setTelephoneTrois(String value) {
		telephoneTrois = value;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String value) {
		email = value;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String value) {
		libelle = value;
	}

	public String getComplementAdresse() {
		return complementAdresse;
	}

	public void setComplementAdresse(String value) {
		complementAdresse = value;
	}

	public String getQuartier() {
		return quartier;
	}

	public void setQuartier(String value) {
		quartier = value;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String value) {
		ville = value;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String value) {
		codePostal = value;
	}

	/* Getters and Setters for Situation group fields */

	public Date getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(Date value) {
		dateDepart = value;
	}

	public Date getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(Date value) {
		dateArrivee = value;
	}

	public Boolean getAEteForme() {
		return AEteForme;
	}

	public void setAEteForme(Boolean value) {
		AEteForme = value;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String value) {
		qualification = value;
	}

	/* Getters and Setters for NiveauAccess group fields */

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String value) {
		niveau = value;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String value) {
		region = value;
	}

	public String getDistrictSante() {
		return districtSante;
	}

	public void setDistrictSante(String value) {
		districtSante = value;
	}

	public String getCDT() {
		return CDT;
	}

	public void setCDT(String value) {
		CDT = value;
	}

	public Boolean getActif() {
		return actif;
	}

	public void setActif(Boolean value) {
		actif = value;
	}

}
