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
 * ImogBean implementation for the entity RendezVous
 * @author MEDES-IMPS
 */
@Entity
public class RendezVous extends ImogEntityImpl {

	public static interface Columns {
		public static final String CASTB = "castb";

		public static final String DATERENDEZVOUS = "daterendezvous";

		public static final String HONORE = "honore";

		public static final String NOMBRESMSENVOYE = "nombresmsenvoye";

		public static final String COMMENTAIRE = "commentaire";

	}

	private static final long serialVersionUID = -7853272534974294613L;

	/* Description group fields */

	@ManyToOne
	@JoinColumn(name = "rendezVousCasTuberculose_id")
	private CasTuberculose casTb;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateRendezVous;

	private Boolean honore;

	private Integer nombreSMSEnvoye;

	@Column(columnDefinition = "TEXT")
	private String commentaire;

	/**
	 * Constructor
	 */
	public RendezVous() {
	}

	/* Getters and Setters for Description group fields */

	public CasTuberculose getCasTb() {
		return casTb;
	}

	public void setCasTb(CasTuberculose value) {
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
