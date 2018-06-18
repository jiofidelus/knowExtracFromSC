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
 * ImogBean implementation for the entity Formation
 * @author MEDES-IMPS
 */
@Entity
public class Formation extends ImogEntityImpl {

	public static interface Columns {
		public static final String LIBELLE = "libelle";

		public static final String DATEDEBUT = "datedebut";

		public static final String DATEFIN = "datefin";

		public static final String LIEU = "lieu";

		public static final String OBJECTIFS = "objectifs";

		public static final String EFFECTUEE = "effectuee";

		public static final String CANDIDATURES = "candidatures";

	}

	private static final long serialVersionUID = -5154435237465112858L;

	/* Description group fields */

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "francais", column = @Column(name = "libelle_fr")),
			@AttributeOverride(name = "english", column = @Column(name = "libelle_en"))})
	private LocalizedText libelle;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDebut;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFin;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "francais", column = @Column(name = "lieu_fr")),
			@AttributeOverride(name = "english", column = @Column(name = "lieu_en"))})
	private LocalizedText lieu;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "francais", column = @Column(name = "objectifs_fr", columnDefinition = "TEXT")),
			@AttributeOverride(name = "english", column = @Column(name = "objectifs_en", columnDefinition = "TEXT"))})
	private LocalizedText objectifs;

	private Boolean effectuee = false;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = false)
	@JoinColumn(name = "candidaturesFormation_id")
	private List<CandidatureFormation> candidatures;

	/**
	 * Constructor
	 */
	public Formation() {
		candidatures = new ArrayList<CandidatureFormation>();
	}

	/* Getters and Setters for Description group fields */

	public LocalizedText getLibelle() {
		return libelle;
	}

	public void setLibelle(LocalizedText value) {
		libelle = value;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date value) {
		dateDebut = value;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date value) {
		dateFin = value;
	}

	public LocalizedText getLieu() {
		return lieu;
	}

	public void setLieu(LocalizedText value) {
		lieu = value;
	}

	public LocalizedText getObjectifs() {
		return objectifs;
	}

	public void setObjectifs(LocalizedText value) {
		objectifs = value;
	}

	public Boolean getEffectuee() {
		return effectuee;
	}

	public void setEffectuee(Boolean value) {
		effectuee = value;
	}

	public List<CandidatureFormation> getCandidatures() {
		return candidatures;
	}

	public void setCandidatures(List<CandidatureFormation> value) {
		candidatures = value;
	}

	/**
	 * @param param the CandidatureFormation to add to the candidatures collection
	 */
	public void addTocandidatures(CandidatureFormation param) {
		param.setFormation(this);
		candidatures.add(param);
	}

	/**
	 * @param param the CandidatureFormation to remove from the candidatures collection
	 */
	public void removeFromcandidatures(CandidatureFormation param) {
		param.setFormation(null);
		candidatures.remove(param);
	}

}
