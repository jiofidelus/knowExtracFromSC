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
 * ImogBean implementation for the entity Tutoriel Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "Tutoriel_Bck")
public class TutorielBck extends ImogBeanBck {

	private static final long serialVersionUID = 4392357475084753748L;

	/* Description group fields */

	private String reference;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "francais", column = @Column(name = "nom_fr")),
			@AttributeOverride(name = "english", column = @Column(name = "nom_en"))})
	private LocalizedText nom;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "francais", column = @Column(name = "description_fr")),
			@AttributeOverride(name = "english", column = @Column(name = "description_en"))})
	private LocalizedText description;

	private String type;

	private String audioT;

	private String videoT;

	private String textT;

	/**
	 * Constructor
	 */
	public TutorielBck() {
	}

	/* Getters and Setters for Description group fields */

	public String getReference() {
		return reference;
	}

	public void setReference(String value) {
		reference = value;
	}

	public LocalizedText getNom() {
		return nom;
	}

	public void setNom(LocalizedText value) {
		nom = value;
	}

	public LocalizedText getDescription() {
		return description;
	}

	public void setDescription(LocalizedText value) {
		description = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String value) {
		type = value;
	}

	public String getAudioT() {
		return audioT;
	}

	public void setAudioT(String value) {
		audioT = value;
	}

	public String getVideoT() {
		return videoT;
	}

	public void setVideoT(String value) {
		videoT = value;
	}

	public String getTextT() {
		return textT;
	}

	public void setTextT(String value) {
		textT = value;
	}

}
