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
 * ImogBean implementation for the entity Qualification Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "Qualification_Bck")
public class QualificationBck extends ImogBeanBck {

	private static final long serialVersionUID = -8492103081148402324L;

	/* Description group fields */

	private String code;

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

	/**
	 * Constructor
	 */
	public QualificationBck() {
	}

	/* Getters and Setters for Description group fields */

	public String getCode() {
		return code;
	}

	public void setCode(String value) {
		code = value;
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

}
