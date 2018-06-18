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
 * ImogBean implementation for the entity Region Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "Region_Bck")
public class RegionBck extends ImogBeanBck {

	private static final long serialVersionUID = 534158274860499197L;

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

	private String districtSantes;

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

	/**
	 * Constructor
	 */
	public RegionBck() {
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

	public String getDistrictSantes() {
		return districtSantes;
	}

	public void setDistrictSantes(String value) {
		districtSantes = value;
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

}
