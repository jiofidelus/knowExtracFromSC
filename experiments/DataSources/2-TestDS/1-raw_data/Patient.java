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
 * ImogBean implementation for the entity Patient
 * @author MEDES-IMPS
 */
@Entity
public class Patient extends ImogEntityImpl {

	public static interface Columns {
		public static final String IDENTIFIANT = "identifiant";

		public static final String NOM = "nom";

		public static final String SEXE = "sexe";

		public static final int SEXE_MASCULIN = 0;

		public static final int SEXE_FEMININ = 1;

		public static final String DATENAISSANCE = "datenaissance";

		public static final String AGE = "age";

		public static final String PROFESSION = "profession";

		public static final String CENTRES = "centres";

		public static final String NATIONALITE = "nationalite";

		public static final int NATIONALITE_CAMEROUNAIS = 0;

		public static final int NATIONALITE_ETRANGER = 1;

		public static final String PRECISIONSURNATIONALITE = "precisionsurnationalite";

		public static final String RECEVOIRCARNETTELPORTABLE = "recevoircarnettelportable";

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

		public static final String LIEUXDITS = "lieuxdits";

		public static final String PACNOM = "pacnom";

		public static final String PACTELEPHONEUN = "pactelephoneun";

		public static final String PACTELEPHONEDEUX = "pactelephonedeux";

		public static final String PACTELEPHONETROIS = "pactelephonetrois";

		public static final String PACEMAIL = "pacemail";

		public static final String PACLIBELLE = "paclibelle";

		public static final int PACLIBELLE_DOMICILE = 0;

		public static final int PACLIBELLE_BUREAU = 1;

		public static final int PACLIBELLE_AUTRE = 2;

		public static final String PACCOMPLEMENTADRESSE = "paccomplementadresse";

		public static final String PACQUARTIER = "pacquartier";

		public static final String PACVILLE = "pacville";

		public static final String PACCODEPOSTAL = "paccodepostal";

		public static final String CASTUBERCULOSE = "castuberculose";

		public static final String CASINDEX = "casindex";

		public static final String EXAMENSBIOLOGIQUES = "examensbiologiques";

		public static final String SEROLOGIES = "serologies";

	}

	private static final long serialVersionUID = -788388473066563723L;

	/* Identification group fields */

	private String identifiant;

	private String nom;

	private String sexe;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateNaissance;

	private Integer age;

	private String profession;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CentresPatientCentreDiagTrait", joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "centrediagtrait_id", referencedColumnName = "id"))
	private List<CentreDiagTrait> centres;

	private String nationalite;

	private String precisionSurNationalite;

	private Boolean recevoirCarnetTelPortable;

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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "LieuxDitsPatientLieuDit", joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "lieudit_id", referencedColumnName = "id"))
	private List<LieuDit> lieuxDits;

	/* PersonneContact group fields */

	private String pacNom;

	private String pacTelephoneUn;

	private String pacTelephoneDeux;

	private String pacTelephoneTrois;

	private String pacEmail;

	private String pacLibelle;

	@Column(columnDefinition = "TEXT")
	private String pacComplementAdresse;

	private String pacQuartier;

	private String pacVille;

	private String pacCodePostal;

	/* Tuberculose group fields */

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = false)
	@JoinColumn(name = "casTuberculosePatient_id")
	private List<CasTuberculose> casTuberculose;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = false, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "casIndexPatient_id")
	private List<CasIndex> casIndex;

	/* Examens group fields */

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = false, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "examensBiologiquesPatient_id")
	private List<ExamenBiologique> examensBiologiques;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = false, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "serologiesPatient_id")
	private List<ExamenSerologie> serologies;

	/**
	 * Constructor
	 */
	public Patient() {
		centres = new ArrayList<CentreDiagTrait>();
		lieuxDits = new ArrayList<LieuDit>();
		casTuberculose = new ArrayList<CasTuberculose>();
		casIndex = new ArrayList<CasIndex>();
		examensBiologiques = new ArrayList<ExamenBiologique>();
		serologies = new ArrayList<ExamenSerologie>();
	}

	/* Getters and Setters for Identification group fields */

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

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String value) {
		sexe = value;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date value) {
		dateNaissance = value;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer value) {
		age = value;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String value) {
		profession = value;
	}

	public List<CentreDiagTrait> getCentres() {
		return centres;
	}

	public void setCentres(List<CentreDiagTrait> value) {
		centres = value;
	}

	/**
	 * @param param the CentreDiagTrait to add to the centres collection
	 */
	public void addTocentres(CentreDiagTrait param) {
		centres.add(param);
	}

	/**
	 * @param param the CentreDiagTrait to remove from the centres collection
	 */
	public void removeFromcentres(CentreDiagTrait param) {
		centres.remove(param);
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String value) {
		nationalite = value;
	}

	public String getPrecisionSurNationalite() {
		return precisionSurNationalite;
	}

	public void setPrecisionSurNationalite(String value) {
		precisionSurNationalite = value;
	}

	public Boolean getRecevoirCarnetTelPortable() {
		return recevoirCarnetTelPortable;
	}

	public void setRecevoirCarnetTelPortable(Boolean value) {
		recevoirCarnetTelPortable = value;
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

	public List<LieuDit> getLieuxDits() {
		return lieuxDits;
	}

	public void setLieuxDits(List<LieuDit> value) {
		lieuxDits = value;
	}

	/**
	 * @param param the LieuDit to add to the lieuxDits collection
	 */
	public void addTolieuxDits(LieuDit param) {
		lieuxDits.add(param);
	}

	/**
	 * @param param the LieuDit to remove from the lieuxDits collection
	 */
	public void removeFromlieuxDits(LieuDit param) {
		lieuxDits.remove(param);
	}

	/* Getters and Setters for PersonneContact group fields */

	public String getPacNom() {
		return pacNom;
	}

	public void setPacNom(String value) {
		pacNom = value;
	}

	public String getPacTelephoneUn() {
		return pacTelephoneUn;
	}

	public void setPacTelephoneUn(String value) {
		pacTelephoneUn = value;
	}

	public String getPacTelephoneDeux() {
		return pacTelephoneDeux;
	}

	public void setPacTelephoneDeux(String value) {
		pacTelephoneDeux = value;
	}

	public String getPacTelephoneTrois() {
		return pacTelephoneTrois;
	}

	public void setPacTelephoneTrois(String value) {
		pacTelephoneTrois = value;
	}

	public String getPacEmail() {
		return pacEmail;
	}

	public void setPacEmail(String value) {
		pacEmail = value;
	}

	public String getPacLibelle() {
		return pacLibelle;
	}

	public void setPacLibelle(String value) {
		pacLibelle = value;
	}

	public String getPacComplementAdresse() {
		return pacComplementAdresse;
	}

	public void setPacComplementAdresse(String value) {
		pacComplementAdresse = value;
	}

	public String getPacQuartier() {
		return pacQuartier;
	}

	public void setPacQuartier(String value) {
		pacQuartier = value;
	}

	public String getPacVille() {
		return pacVille;
	}

	public void setPacVille(String value) {
		pacVille = value;
	}

	public String getPacCodePostal() {
		return pacCodePostal;
	}

	public void setPacCodePostal(String value) {
		pacCodePostal = value;
	}

	/* Getters and Setters for Tuberculose group fields */

	public List<CasTuberculose> getCasTuberculose() {
		return casTuberculose;
	}

	public void setCasTuberculose(List<CasTuberculose> value) {
		casTuberculose = value;
	}

	/**
	 * @param param the CasTuberculose to add to the casTuberculose collection
	 */
	public void addTocasTuberculose(CasTuberculose param) {
		param.setPatient(this);
		casTuberculose.add(param);
	}

	/**
	 * @param param the CasTuberculose to remove from the casTuberculose collection
	 */
	public void removeFromcasTuberculose(CasTuberculose param) {
		param.setPatient(null);
		casTuberculose.remove(param);
	}

	public List<CasIndex> getCasIndex() {
		return casIndex;
	}

	public void setCasIndex(List<CasIndex> value) {
		casIndex = value;
	}

	/**
	 * @param param the CasIndex to add to the casIndex collection
	 */
	public void addTocasIndex(CasIndex param) {
		param.setPatient(this);
		casIndex.add(param);
	}

	/**
	 * @param param the CasIndex to remove from the casIndex collection
	 */
	public void removeFromcasIndex(CasIndex param) {
		param.setPatient(null);
		casIndex.remove(param);
	}

	/* Getters and Setters for Examens group fields */

	public List<ExamenBiologique> getExamensBiologiques() {
		return examensBiologiques;
	}

	public void setExamensBiologiques(List<ExamenBiologique> value) {
		examensBiologiques = value;
	}

	/**
	 * @param param the ExamenBiologique to add to the examensBiologiques collection
	 */
	public void addToexamensBiologiques(ExamenBiologique param) {
		param.setPatient(this);
		examensBiologiques.add(param);
	}

	/**
	 * @param param the ExamenBiologique to remove from the examensBiologiques collection
	 */
	public void removeFromexamensBiologiques(ExamenBiologique param) {
		param.setPatient(null);
		examensBiologiques.remove(param);
	}

	public List<ExamenSerologie> getSerologies() {
		return serologies;
	}

	public void setSerologies(List<ExamenSerologie> value) {
		serologies = value;
	}

	/**
	 * @param param the ExamenSerologie to add to the serologies collection
	 */
	public void addToserologies(ExamenSerologie param) {
		param.setPatient(this);
		serologies.add(param);
	}

	/**
	 * @param param the ExamenSerologie to remove from the serologies collection
	 */
	public void removeFromserologies(ExamenSerologie param) {
		param.setPatient(null);
		serologies.remove(param);
	}

}
