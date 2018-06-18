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
 * ImogBean implementation for the entity Formation Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "Formation_Bck")
public class FormationBck extends ImogBeanBck {

	private static final long serialVersionUID = -8589460122103501303L;

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
			@AttributeOverride(name = "francais", column = @Column(name = "objectifs_fr")),
			@AttributeOverride(name = "english", column = @Column(name = "objectifs_en"))})
	private LocalizedText objectifs;

	private Boolean effectuee;

	private String candidatures;

	/**
	 * Constructor
	 */
	public FormationBck() {
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

	public String getCandidatures() {
		return candidatures;
	}

	public void setCandidatures(String value) {
		candidatures = value;
	}

}
