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
 * ImogBean implementation for the entity OutBox
 * @author MEDES-IMPS
 */
@Entity
public class OutBox extends ImogEntityImpl {

	public static interface Columns {
		public static final String PATIENT = "patient";

		public static final String MESSAGE = "message";

		public static final String REPONSE = "reponse";

		public static final String STATUT = "statut";

		public static final int STATUT_ERREUR = 0;

		public static final int STATUT_AENVOYER = 1;

		public static final int STATUT_SUCCES = 2;

		public static final String DATEDERNIERESSAI = "datedernieressai";

	}

	private static final long serialVersionUID = 2398853410873923097L;

	/* Adresse group fields */

	@ManyToOne
	private Patient patient;

	/* MessageInformation group fields */

	@Column(columnDefinition = "TEXT")
	private String message;

	@Column(columnDefinition = "TEXT")
	private String reponse;

	private String statut;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDernierEssai;

	/**
	 * Constructor
	 */
	public OutBox() {
	}

	/* Getters and Setters for Adresse group fields */

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient value) {
		patient = value;
	}

	/* Getters and Setters for MessageInformation group fields */

	public String getMessage() {
		return message;
	}

	public void setMessage(String value) {
		message = value;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String value) {
		reponse = value;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String value) {
		statut = value;
	}

	public Date getDateDernierEssai() {
		return dateDernierEssai;
	}

	public void setDateDernierEssai(Date value) {
		dateDernierEssai = value;
	}

}
