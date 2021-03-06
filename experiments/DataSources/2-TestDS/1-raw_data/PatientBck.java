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
 * ImogBean implementation for the entity Patient Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "Patient_Bck")
public class PatientBck extends ImogBeanBck {

	private static final long serialVersionUID = 6725711287772541797L;

	/* Identification group fields */

	private String identifiant;

	private String nom;

	private String sexe;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateNaissance;

	private Integer age;

	private String profession;

	private String centres;

	private String nationalite;

	private String precisionSurNationalite;

	private Boolean recevoirCarnetTelPortable;

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

	private String lieuxDits;

	/* PersonneContact group fields */

	private String pacNom;

	private String pacTelephoneUn;

	private String pacTelephoneDeux;

	private String pacTelephoneTrois;

	private String pacEmail;

	private String pacLibelle;

	@Column(columnDefinition = "TEXT")
	private String pacComplementAdresse;

	private String pacQuartier;

	private String pacVille;

	private String pacCodePostal;

	/* Tuberculose group fields */

	private String casTuberculose;

	private String casIndex;

	/* Examens group fields */

	private String examensBiologiques;

	private String serologies;

	/**
	 * Constructor
	 */
	public PatientBck() {
	}

	/* Getters and Setters for Identification group fields */

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String value) {
		identifiant = value;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String value) {
		nom = value;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String value) {
		sexe = value;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date value) {
		dateNaissance = value;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer value) {
		age = value;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String value) {
		profession = value;
	}

	public String getCentres() {
		return centres;
	}

	public void setCentres(String value) {
		centres = value;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String value) {
		nationalite = value;
	}

	public String getPrecisionSurNationalite() {
		return precisionSurNationalite;
	}

	public void setPrecisionSurNationalite(String value) {
		precisionSurNationalite = value;
	}

	public Boolean getRecevoirCarnetTelPortable() {
		return recevoirCarnetTelPortable;
	}

	public void setRecevoirCarnetTelPortable(Boolean value) {
		recevoirCarnetTelPortable = value;
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

	public String getLieuxDits() {
		return lieuxDits;
	}

	public void setLieuxDits(String value) {
		lieuxDits = value;
	}

	/* Getters and Setters for PersonneContact group fields */

	public String getPacNom() {
		return pacNom;
	}

	public void setPacNom(String value) {
		pacNom = value;
	}

	public String getPacTelephoneUn() {
		return pacTelephoneUn;
	}

	public void setPacTelephoneUn(String value) {
		pacTelephoneUn = value;
	}

	public String getPacTelephoneDeux() {
		return pacTelephoneDeux;
	}

	public void setPacTelephoneDeux(String value) {
		pacTelephoneDeux = value;
	}

	public String getPacTelephoneTrois() {
		return pacTelephoneTrois;
	}

	public void setPacTelephoneTrois(String value) {
		pacTelephoneTrois = value;
	}

	public String getPacEmail() {
		return pacEmail;
	}

	public void setPacEmail(String value) {
		pacEmail = value;
	}

	public String getPacLibelle() {
		return pacLibelle;
	}

	public void setPacLibelle(String value) {
		pacLibelle = value;
	}

	public String getPacComplementAdresse() {
		return pacComplementAdresse;
	}

	public void setPacComplementAdresse(String value) {
		pacComplementAdresse = value;
	}

	public String getPacQuartier() {
		return pacQuartier;
	}

	public void setPacQuartier(String value) {
		pacQuartier = value;
	}

	public String getPacVille() {
		return pacVille;
	}

	public void setPacVille(String value) {
		pacVille = value;
	}

	public String getPacCodePostal() {
		return pacCodePostal;
	}

	public void setPacCodePostal(String value) {
		pacCodePostal = value;
	}

	/* Getters and Setters for Tuberculose group fields */

	public String getCasTuberculose() {
		return casTuberculose;
	}

	public void setCasTuberculose(String value) {
		casTuberculose = value;
	}

	public String getCasIndex() {
		return casIndex;
	}

	public void setCasIndex(String value) {
		casIndex = value;
	}

	/* Getters and Setters for Examens group fields */

	public String getExamensBiologiques() {
		return examensBiologiques;
	}

	public void setExamensBiologiques(String value) {
		examensBiologiques = value;
	}

	public String getSerologies() {
		return serologies;
	}

	public void setSerologies(String value) {
		serologies = value;
	}

}
