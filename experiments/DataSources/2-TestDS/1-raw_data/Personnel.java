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
 * ImogBean implementation for the entity Personnel
 * @author MEDES-IMPS
 */
@Entity
public class Personnel extends ImogActorImpl {

	public static interface Columns {
		public static final String NOM = "nom";

		public static final String DATENAISSANCE = "datenaissance";

		public static final String PROFESSION = "profession";

		public static final String TELEPHONEUN = "telephoneun";

		public static final String TELEPHONEDEUX = "telephonedeux";

		public static final String TELEPHONETROIS = "telephonetrois";

		public static final String EMAIL = "email";

		public static final String LIBELLE = "libelle";

		public static final int LIBELLE_DOMICILE = 0;

		public static final int LIBELLE_BUREAU = 1;

		public static final int LIBELLE_AUTRE = 2;

		public static final String COMPLEMENTADRESSE = "complementadresse";

		public static final String QUARTIER = "quartier";

		public static final String VILLE = "ville";

		public static final String CODEPOSTAL = "codepostal";

		public static final String DATEDEPART = "datedepart";

		public static final String DATEARRIVEE = "datearrivee";

		public static final String AETEFORME = "aeteforme";

		public static final String QUALIFICATION = "qualification";

		public static final String NIVEAU = "niveau";

		public static final int NIVEAU_CENTRAL = 0;

		public static final int NIVEAU_REGION = 1;

		public static final int NIVEAU_DISTRICTSANTE = 2;

		public static final int NIVEAU_CDT = 3;

		public static final String REGION = "region";

		public static final String DISTRICTSANTE = "districtsante";

		public static final String CDT = "cdt";

		public static final String ACTIF = "actif";

	}

	private static final long serialVersionUID = -1470154957478204639L;

	/* Identification group fields */

	private String nom;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateNaissance;

	private String profession;

	/* Contact group fields */

	private String telephoneUn;

	private String telephoneDeux;

	private String telephoneTrois;

	private String email;

	private String libelle;

	@Column(columnDefinition = "TEXT")
	private String complementAdresse;

	private String quartier;

	private String ville;

	private String codePostal;

	/* Situation group fields */

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDepart;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateArrivee;

	private Boolean AEteForme = false;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "QualificationPersonnelQualification", joinColumns = @JoinColumn(name = "personnel_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "qualification_id", referencedColumnName = "id"))
	private List<Qualification> qualification;

	/* NiveauAccess group fields */

	private String niveau;

	@ManyToOne
	private Region region;

	@ManyToOne
	private DistrictSante districtSante;

	@ManyToOne
	private CentreDiagTrait CDT;

	private Boolean actif = true;

	/**
	 * Constructor
	 */
	public Personnel() {
		qualification = new ArrayList<Qualification>();
	}

	/* Getters and Setters for Identification group fields */

	public String getNom() {
		return nom;
	}

	public void setNom(String value) {
		nom = value;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date value) {
		dateNaissance = value;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String value) {
		profession = value;
	}

	/* Getters and Setters for Contact group fields */

	public String getTelephoneUn() {
		return telephoneUn;
	}

	public void setTelephoneUn(String value) {
		telephoneUn = value;
	}

	public String getTelephoneDeux() {
		return telephoneDeux;
	}

	public void setTelephoneDeux(String value) {
		telephoneDeux = value;
	}

	public String getTelephoneTrois() {
		return telephoneTrois;
	}

	public void setTelephoneTrois(String value) {
		telephoneTrois = value;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String value) {
		email = value;
	}

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

	/* Getters and Setters for Situation group fields */

	public Date getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(Date value) {
		dateDepart = value;
	}

	public Date getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(Date value) {
		dateArrivee = value;
	}

	public Boolean getAEteForme() {
		return AEteForme;
	}

	public void setAEteForme(Boolean value) {
		AEteForme = value;
	}

	public List<Qualification> getQualification() {
		return qualification;
	}

	public void setQualification(List<Qualification> value) {
		qualification = value;
	}

	/**
	 * @param param the Qualification to add to the qualification collection
	 */
	public void addToqualification(Qualification param) {
		qualification.add(param);
	}

	/**
	 * @param param the Qualification to remove from the qualification collection
	 */
	public void removeFromqualification(Qualification param) {
		qualification.remove(param);
	}

	/* Getters and Setters for NiveauAccess group fields */

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String value) {
		niveau = value;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region value) {
		region = value;
	}

	public DistrictSante getDistrictSante() {
		return districtSante;
	}

	public void setDistrictSante(DistrictSante value) {
		districtSante = value;
	}

	public CentreDiagTrait getCDT() {
		return CDT;
	}

	public void setCDT(CentreDiagTrait value) {
		CDT = value;
	}

	public Boolean getActif() {
		return actif;
	}

	public void setActif(Boolean value) {
		actif = value;
	}

	@Override
	public String getNotificationData(String method) {

		return null;
	}

}
