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
 * ImogBean implementation for the entity CasTuberculose Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "CasTuberculose_Bck")
public class CasTuberculoseBck extends ImogBeanBck {

	private static final long serialVersionUID = -2406387431700326216L;

	/* Informations group fields */

	private String identifiant;

	private String numRegTB;

	private String patient;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDebutTraitement;

	private String typePatient;

	@Column(columnDefinition = "TEXT")
	private String typePatientPrecisions;

	private String formeMaladie;

	@Column(columnDefinition = "TEXT")
	private String extraPulmonairePrecisions;

	private String cotrimoxazole;

	private Boolean antiRetroViraux;

	private Boolean fumeur;

	private Boolean fumeurArreter;

	/* Examen group fields */

	private String examensMiscrocopies;

	private String examensATB;

	/* Traitement group fields */

	private String regimePhaseInitiale;

	private String regimePhaseContinuation;

	private String priseMedicamenteusePhaseInitiale;

	private String priseMedicamenteusePhaseContinuation;

	private String rendezVous;

	/* FinTraitement group fields */

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFinTraitement;

	private String devenirMalade;

	@Column(columnDefinition = "TEXT")
	private String observation;

	/**
	 * Constructor
	 */
	public CasTuberculoseBck() {
	}

	/* Getters and Setters for Informations group fields */

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String value) {
		identifiant = value;
	}

	public String getNumRegTB() {
		return numRegTB;
	}

	public void setNumRegTB(String value) {
		numRegTB = value;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String value) {
		patient = value;
	}

	public Date getDateDebutTraitement() {
		return dateDebutTraitement;
	}

	public void setDateDebutTraitement(Date value) {
		dateDebutTraitement = value;
	}

	public String getTypePatient() {
		return typePatient;
	}

	public void setTypePatient(String value) {
		typePatient = value;
	}

	public String getTypePatientPrecisions() {
		return typePatientPrecisions;
	}

	public void setTypePatientPrecisions(String value) {
		typePatientPrecisions = value;
	}

	public String getFormeMaladie() {
		return formeMaladie;
	}

	public void setFormeMaladie(String value) {
		formeMaladie = value;
	}

	public String getExtraPulmonairePrecisions() {
		return extraPulmonairePrecisions;
	}

	public void setExtraPulmonairePrecisions(String value) {
		extraPulmonairePrecisions = value;
	}

	public String getCotrimoxazole() {
		return cotrimoxazole;
	}

	public void setCotrimoxazole(String value) {
		cotrimoxazole = value;
	}

	public Boolean getAntiRetroViraux() {
		return antiRetroViraux;
	}

	public void setAntiRetroViraux(Boolean value) {
		antiRetroViraux = value;
	}

	public Boolean getFumeur() {
		return fumeur;
	}

	public void setFumeur(Boolean value) {
		fumeur = value;
	}

	public Boolean getFumeurArreter() {
		return fumeurArreter;
	}

	public void setFumeurArreter(Boolean value) {
		fumeurArreter = value;
	}

	/* Getters and Setters for Examen group fields */

	public String getExamensMiscrocopies() {
		return examensMiscrocopies;
	}

	public void setExamensMiscrocopies(String value) {
		examensMiscrocopies = value;
	}

	public String getExamensATB() {
		return examensATB;
	}

	public void setExamensATB(String value) {
		examensATB = value;
	}

	/* Getters and Setters for Traitement group fields */

	public String getRegimePhaseInitiale() {
		return regimePhaseInitiale;
	}

	public void setRegimePhaseInitiale(String value) {
		regimePhaseInitiale = value;
	}

	public String getRegimePhaseContinuation() {
		return regimePhaseContinuation;
	}

	public void setRegimePhaseContinuation(String value) {
		regimePhaseContinuation = value;
	}

	public String getPriseMedicamenteusePhaseInitiale() {
		return priseMedicamenteusePhaseInitiale;
	}

	public void setPriseMedicamenteusePhaseInitiale(String value) {
		priseMedicamenteusePhaseInitiale = value;
	}

	public String getPriseMedicamenteusePhaseContinuation() {
		return priseMedicamenteusePhaseContinuation;
	}

	public void setPriseMedicamenteusePhaseContinuation(String value) {
		priseMedicamenteusePhaseContinuation = value;
	}

	public String getRendezVous() {
		return rendezVous;
	}

	public void setRendezVous(String value) {
		rendezVous = value;
	}

	/* Getters and Setters for FinTraitement group fields */

	public Date getDateFinTraitement() {
		return dateFinTraitement;
	}

	public void setDateFinTraitement(Date value) {
		dateFinTraitement = value;
	}

	public String getDevenirMalade() {
		return devenirMalade;
	}

	public void setDevenirMalade(String value) {
		devenirMalade = value;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String value) {
		observation = value;
	}

}
