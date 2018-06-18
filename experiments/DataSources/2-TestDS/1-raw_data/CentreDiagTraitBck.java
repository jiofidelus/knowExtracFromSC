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
 * ImogBean implementation for the entity CentreDiagTrait Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "CentreDiagTrait_Bck")
public class CentreDiagTraitBck extends ImogBeanBck {

	private static final long serialVersionUID = -6285787369660692675L;

	/* Description group fields */

	private String code;

	private String region;

	private String districtSante;

	private String nom;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "francais", column = @Column(name = "description_fr")),
			@AttributeOverride(name = "english", column = @Column(name = "description_en"))})
	private LocalizedText description;

	/* Adresse group fields */

	private String libelle;

	@Column(columnDefinition = "TEXT")
	private String complementAdresse;

	private String quartier;

	private String ville;

	private String codePostal;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "latitude", column = @Column(name = "coordonnees_Latitude")),
			@AttributeOverride(name = "longitude", column = @Column(name = "coordonnees_Longitude"))})
	private GeoField coordonnees;

	private String lieuxDits;

	/**
	 * Constructor
	 */
	public CentreDiagTraitBck() {
	}

	/* Getters and Setters for Description group fields */

	public String getCode() {
		return code;
	}

	public void setCode(String value) {
		code = value;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String value) {
		region = value;
	}

	public String getDistrictSante() {
		return districtSante;
	}

	public void setDistrictSante(String value) {
		districtSante = value;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String value) {
		nom = value;
	}

	public LocalizedText getDescription() {
		return description;
	}

	public void setDescription(LocalizedText value) {
		description = value;
	}

	/* Getters and Setters for Adresse group fields */

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String value) {
		libelle = value;
	}

	public String getComplementAdresse() {
		return complementAdresse;
	}

	public void setComplementAdresse(String value) {
		complementAdresse = value;
	}

	public String getQuartier() {
		return quartier;
	}

	public void setQuartier(String value) {
		quartier = value;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String value) {
		ville = value;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String value) {
		codePostal = value;
	}

	public GeoField getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(GeoField value) {
		coordonnees = value;
	}

	public String getLieuxDits() {
		return lieuxDits;
	}

	public void setLieuxDits(String value) {
		lieuxDits = value;
	}

}
