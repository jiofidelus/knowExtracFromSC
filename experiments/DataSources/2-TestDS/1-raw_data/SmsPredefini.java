package org.imogene.epicam.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.imogene.lib.common.binary.file.BinaryFile;
import org.imogene.lib.common.constants.NotificationConstants;
import org.imogene.lib.common.entity.GeoField;
import org.imogene.lib.common.entity.ImogActorImpl;
import org.imogene.lib.common.entity.ImogEntityImpl;
import org.imogene.lib.common.entity.IsGeoreferenced;

/**
 * ImogBean implementation for the entity SmsPredefini
 * @author MEDES-IMPS
 */
@Entity
public class SmsPredefini extends ImogEntityImpl {

	public static interface Columns {
		public static final String TYPE = "type";

		public static final int TYPE_SENSIBILISATION = 0;

		public static final int TYPE_QUIZZ = 1;

		public static final int TYPE_RAPPELRDV = 2;

		public static final int TYPE_MEDICALRECORD = 3;

		public static final String OBJET = "objet";

		public static final String PERIODICITE = "periodicite";

		public static final int PERIODICITE_JOUR = 0;

		public static final int PERIODICITE_SEMAINE = 1;

		public static final int PERIODICITE_MOIS = 2;

		public static final int PERIODICITE_TRIMESTRE = 3;

		public static final int PERIODICITE_SEMESTRE = 4;

		public static final int PERIODICITE_PONCTUELLE = 5;

		public static final String DATEENVOYESMSPONCTUEL = "dateenvoyesmsponctuel";

		public static final String STATUT = "statut";

		public static final int STATUT_ACTIF = 0;

		public static final int STATUT_INACTIF = 1;

		public static final String MESSAGE = "message";

		public static final String REPONSE1 = "reponse1";

		public static final String REPONSE2 = "reponse2";

		public static final String BONNEREPONSE = "bonnereponse";

		public static final String ENVOYERAPARTIRDE = "envoyerapartirde";

		public static final String ARRETERENVOYERA = "arreterenvoyera";

	}

	private static final long serialVersionUID = -793967091004939759L;

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
			@AttributeOverride(name = "francais", column = @Column(name = "message_fr", columnDefinition = "TEXT")),
			@AttributeOverride(name = "english", column = @Column(name = "message_en", columnDefinition = "TEXT"))})
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
	public SmsPredefini() {
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
