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
 * ImogBean implementation for the entity SmsPredefini Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "SmsPredefini_Bck")
public class SmsPredefiniBck extends ImogBeanBck {

	private static final long serialVersionUID = -2593607730806834076L;

	/* Description group fields */

	private String type;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "francais", column = @Column(name = "objet_fr")),
			@AttributeOverride(name = "english", column = @Column(name = "objet_en"))})
	private LocalizedText objet;

	private String periodicite;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateEnvoyeSMSPonctuel;

	private String statut;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "francais", column = @Column(name = "message_fr")),
			@AttributeOverride(name = "english", column = @Column(name = "message_en"))})
	private LocalizedText message;

	private String reponse1;

	private String reponse2;

	private String bonneReponse;

	@Temporal(TemporalType.TIMESTAMP)
	private Date envoyerAPartirDe;

	@Temporal(TemporalType.TIMESTAMP)
	private Date arreterEnvoyerA;

	/**
	 * Constructor
	 */
	public SmsPredefiniBck() {
	}

	/* Getters and Setters for Description group fields */

	public String getType() {
		return type;
	}

	public void setType(String value) {
		type = value;
	}

	public LocalizedText getObjet() {
		return objet;
	}

	public void setObjet(LocalizedText value) {
		objet = value;
	}

	public String getPeriodicite() {
		return periodicite;
	}

	public void setPeriodicite(String value) {
		periodicite = value;
	}

	public Date getDateEnvoyeSMSPonctuel() {
		return dateEnvoyeSMSPonctuel;
	}

	public void setDateEnvoyeSMSPonctuel(Date value) {
		dateEnvoyeSMSPonctuel = value;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String value) {
		statut = value;
	}

	public LocalizedText getMessage() {
		return message;
	}

	public void setMessage(LocalizedText value) {
		message = value;
	}

	public String getReponse1() {
		return reponse1;
	}

	public void setReponse1(String value) {
		reponse1 = value;
	}

	public String getReponse2() {
		return reponse2;
	}

	public void setReponse2(String value) {
		reponse2 = value;
	}

	public String getBonneReponse() {
		return bonneReponse;
	}

	public void setBonneReponse(String value) {
		bonneReponse = value;
	}

	public Date getEnvoyerAPartirDe() {
		return envoyerAPartirDe;
	}

	public void setEnvoyerAPartirDe(Date value) {
		envoyerAPartirDe = value;
	}

	public Date getArreterEnvoyerA() {
		return arreterEnvoyerA;
	}

	public void setArreterEnvoyerA(Date value) {
		arreterEnvoyerA = value;
	}

}
