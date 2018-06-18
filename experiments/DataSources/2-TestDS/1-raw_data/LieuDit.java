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
 * ImogBean implementation for the entity LieuDit
 * @author MEDES-IMPS
 */
@Entity
public class LieuDit extends ImogEntityImpl {

	public static interface Columns {
		public static final String CODE = "code";

		public static final String NOM = "nom";

		public static final String DESCRIPTION = "description";

		public static final String LIBELLE = "libelle";

		public static final int LIBELLE_DOMICILE = 0;

		public static final int LIBELLE_BUREAU = 1;

		public static final int LIBELLE_AUTRE = 2;

		public static final String COMPLEMENTADRESSE = "complementadresse";

		public static final String QUARTIER = "quartier";

		public static final String VILLE = "ville";

		public static final String CODEPOSTAL = "codepostal";

		public static final String COORDONNEES = "coordonnees";

	}

	private static final long serialVersionUID = 5017467023985160304L;

	/* Description group fields */

	private String code;

	private String nom;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "francais", column = @Column(name = "description_fr", columnDefinition = "TEXT")),
			@AttributeOverride(name = "english", column = @Column(name = "description_en", columnDefinition = "TEXT"))})
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

	/**
	 * Constructor
	 */
	public LieuDit() {
	}

	/* Getters and Setters for Description group fields */

	public String getCode() {
		return code;
	}

	public void setCode(String value) {
		code = value;
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

}
