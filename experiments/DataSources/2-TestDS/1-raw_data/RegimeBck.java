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
 * ImogBean implementation for the entity Regime Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "Regime_Bck")
public class RegimeBck extends ImogBeanBck {

	private static final long serialVersionUID = 7987353178910821784L;

	/* Description group fields */

	private String nom;

	private String type;

	private Integer dureeTraitement;

	private Integer poidsMin;

	private Integer poidsMax;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "francais", column = @Column(name = "description_fr")),
			@AttributeOverride(name = "english", column = @Column(name = "description_en"))})
	private LocalizedText description;

	private String prisesMedicamenteuses;

	private Boolean actif;

	/**
	 * Constructor
	 */
	public RegimeBck() {
	}

	/* Getters and Setters for Description group fields */

	public String getNom() {
		return nom;
	}

	public void setNom(String value) {
		nom = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String value) {
		type = value;
	}

	public Integer getDureeTraitement() {
		return dureeTraitement;
	}

	public void setDureeTraitement(Integer value) {
		dureeTraitement = value;
	}

	public Integer getPoidsMin() {
		return poidsMin;
	}

	public void setPoidsMin(Integer value) {
		poidsMin = value;
	}

	public Integer getPoidsMax() {
		return poidsMax;
	}

	public void setPoidsMax(Integer value) {
		poidsMax = value;
	}

	public LocalizedText getDescription() {
		return description;
	}

	public void setDescription(LocalizedText value) {
		description = value;
	}

	public String getPrisesMedicamenteuses() {
		return prisesMedicamenteuses;
	}

	public void setPrisesMedicamenteuses(String value) {
		prisesMedicamenteuses = value;
	}

	public Boolean getActif() {
		return actif;
	}

	public void setActif(Boolean value) {
		actif = value;
	}

}
