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
 * ImogBean implementation for the entity RendezVous Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "RendezVous_Bck")
public class RendezVousBck extends ImogBeanBck {

	private static final long serialVersionUID = 3068820693711408479L;

	/* Description group fields */

	private String casTb;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateRendezVous;

	private Boolean honore;

	private Integer nombreSMSEnvoye;

	@Column(columnDefinition = "TEXT")
	private String commentaire;

	/**
	 * Constructor
	 */
	public RendezVousBck() {
	}

	/* Getters and Setters for Description group fields */

	public String getCasTb() {
		return casTb;
	}

	public void setCasTb(String value) {
		casTb = value;
	}

	public Date getDateRendezVous() {
		return dateRendezVous;
	}

	public void setDateRendezVous(Date value) {
		dateRendezVous = value;
	}

	public Boolean getHonore() {
		return honore;
	}

	public void setHonore(Boolean value) {
		honore = value;
	}

	public Integer getNombreSMSEnvoye() {
		return nombreSMSEnvoye;
	}

	public void setNombreSMSEnvoye(Integer value) {
		nombreSMSEnvoye = value;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String value) {
		commentaire = value;
	}

}
