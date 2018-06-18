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
 * ImogBean implementation for the entity Intrant Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "Intrant_Bck")
public class IntrantBck extends ImogBeanBck {

	private static final long serialVersionUID = 1652769730310760695L;

	/* Description group fields */

	private String identifiant;

	private String nom;

	@Column(columnDefinition = "TEXT")
	private String description;

	private Double seuilPatient;

	/**
	 * Constructor
	 */
	public IntrantBck() {
	}

	/* Getters and Setters for Description group fields */

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String value) {
		identifiant = value;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String value) {
		nom = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String value) {
		description = value;
	}

	public Double getSeuilPatient() {
		return seuilPatient;
	}

	public void setSeuilPatient(Double value) {
		seuilPatient = value;
	}

}
