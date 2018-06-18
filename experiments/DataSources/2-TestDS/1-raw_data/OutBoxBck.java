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
 * ImogBean implementation for the entity OutBox Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "OutBox_Bck")
public class OutBoxBck extends ImogBeanBck {

	private static final long serialVersionUID = 903079243138804530L;

	/* Adresse group fields */

	private String patient;

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
	public OutBoxBck() {
	}

	/* Getters and Setters for Adresse group fields */

	public String getPatient() {
		return patient;
	}

	public void setPatient(String value) {
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
