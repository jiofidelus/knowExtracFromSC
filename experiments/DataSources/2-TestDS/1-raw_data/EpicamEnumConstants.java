package org.imogene.epicam.shared.constants;

public class EpicamEnumConstants {

	/** Patient enumeration constants */

	// Sexe values
	public static final String PATIENT_SEXE_MASCULIN = "0";
	public static final String PATIENT_SEXE_FEMININ = "1";

	// Nationalite values
	public static final String PATIENT_NATIONALITE_CAMEROUNAIS = "0";
	public static final String PATIENT_NATIONALITE_ETRANGER = "1";

	// Libelle values
	public static final String PATIENT_LIBELLE_DOMICILE = "0";
	public static final String PATIENT_LIBELLE_BUREAU = "1";
	public static final String PATIENT_LIBELLE_AUTRE = "2";

	// PacLibelle values
	public static final String PATIENT_PACLIBELLE_DOMICILE = "0";
	public static final String PATIENT_PACLIBELLE_BUREAU = "1";
	public static final String PATIENT_PACLIBELLE_AUTRE = "2";

	/** CasIndex enumeration constants */

	/** CasTuberculose enumeration constants */

	// TypePatient values
	public static final String CASTUBERCULOSE_TYPEPATIENT_NOUVEAUCAS = "0";
	public static final String CASTUBERCULOSE_TYPEPATIENT_REPRISEAPRESABANDON = "1";
	public static final String CASTUBERCULOSE_TYPEPATIENT_ECHEC = "2";
	public static final String CASTUBERCULOSE_TYPEPATIENT_RECHUTE = "3";
	public static final String CASTUBERCULOSE_TYPEPATIENT_AUTRE = "4";

	// FormeMaladie values
	public static final String CASTUBERCULOSE_FORMEMALADIE_TPMPLUS = "0";
	public static final String CASTUBERCULOSE_FORMEMALADIE_TPMMOINS = "1";
	public static final String CASTUBERCULOSE_FORMEMALADIE_EXTRA_PULMONAIRE = "2";

	// Cotrimoxazole values
	public static final String CASTUBERCULOSE_COTRIMOXAZOLE_NON = "0";
	public static final String CASTUBERCULOSE_COTRIMOXAZOLE_COTRIMOXAZOLE_960 = "1";
	public static final String CASTUBERCULOSE_COTRIMOXAZOLE_COTRIMOXAZOLE_480 = "2";

	// DevenirMalade values
	public static final String CASTUBERCULOSE_DEVENIRMALADE_GUERRIS = "0";
	public static final String CASTUBERCULOSE_DEVENIRMALADE_TERMINE = "1";
	public static final String CASTUBERCULOSE_DEVENIRMALADE_ECHEC = "2";
	public static final String CASTUBERCULOSE_DEVENIRMALADE_DECEDE = "3";
	public static final String CASTUBERCULOSE_DEVENIRMALADE_PERDUDEVUE = "4";
	public static final String CASTUBERCULOSE_DEVENIRMALADE_ARRETPRESCRIPTEUR = "5";
	public static final String CASTUBERCULOSE_DEVENIRMALADE_ARRETEFFETSINDESI = "6";
	public static final String CASTUBERCULOSE_DEVENIRMALADE_ARRETSURVENUTB = "7";

	/** ExamenSerologie enumeration constants */

	// Nature values
	public static final String EXAMENSEROLOGIE_NATURE_VIH = "0";
	public static final String EXAMENSEROLOGIE_NATURE_CD4 = "1";

	// ResultatVIH values
	public static final String EXAMENSEROLOGIE_RESULTATVIH_POSITIF = "0";
	public static final String EXAMENSEROLOGIE_RESULTATVIH_NEGATIF = "1";

	/** ExamenBiologique enumeration constants */

	/** ExamenMicroscopie enumeration constants */

	// RaisonDepistage values
	public static final String EXAMENMICROSCOPIE_RAISONDEPISTAGE_DIAGNOSTIC = "0";
	public static final String EXAMENMICROSCOPIE_RAISONDEPISTAGE_SUIVI = "1";

	// Resultat values
	public static final String EXAMENMICROSCOPIE_RESULTAT_NEGATIF = "0";
	public static final String EXAMENMICROSCOPIE_RESULTAT_RARE = "1";
	public static final String EXAMENMICROSCOPIE_RESULTAT_UNPLUS = "2";
	public static final String EXAMENMICROSCOPIE_RESULTAT_DEUXPLUS = "3";
	public static final String EXAMENMICROSCOPIE_RESULTAT_TROISPLUS = "4";

	/** ExamenATB enumeration constants */

	// RaisonDepistage values
	public static final String EXAMENATB_RAISONDEPISTAGE_DIAGNOSTIC = "0";
	public static final String EXAMENATB_RAISONDEPISTAGE_SUIVI = "1";

	/** PriseMedicamenteuse enumeration constants */

	// Prise values
	public static final String PRISEMEDICAMENTEUSE_PRISE_PRISESUPERVISEE = "0";
	public static final String PRISEMEDICAMENTEUSE_PRISE_AUTOMEDICATION = "1";
	public static final String PRISEMEDICAMENTEUSE_PRISE_NONVENU = "2";

	/** RendezVous enumeration constants */

	/** TransfertReference enumeration constants */

	// Nature values
	public static final String TRANSFERTREFERENCE_NATURE_TRANSFERT = "0";
	public static final String TRANSFERTREFERENCE_NATURE_REFERENCE = "1";

	/** Lot enumeration constants */

	// Type values
	public static final String LOT_TYPE_MEDICAMENT = "0";
	public static final String LOT_TYPE_INTRANT = "1";

	/** HorsUsage enumeration constants */

	// Type values
	public static final String HORSUSAGE_TYPE_PERIMEE = "0";
	public static final String HORSUSAGE_TYPE_CASSE = "1";

	/** EntreeLot enumeration constants */

	/** SortieLot enumeration constants */

	/** Commande enumeration constants */

	/** DetailCommandeMedicament enumeration constants */

	/** DetailCommandeIntrant enumeration constants */

	/** Reception enumeration constants */

	/** DetailReceptionMedicament enumeration constants */

	/** DetailReceptionIntrant enumeration constants */

	/** Ravitaillement enumeration constants */

	/** DetailRavitaillement enumeration constants */

	/** Inventaire enumeration constants */

	/** DetailInventaire enumeration constants */

	/** Personnel enumeration constants */

	// Libelle values
	public static final String PERSONNEL_LIBELLE_DOMICILE = "0";
	public static final String PERSONNEL_LIBELLE_BUREAU = "1";
	public static final String PERSONNEL_LIBELLE_AUTRE = "2";

	// Niveau values
	public static final String PERSONNEL_NIVEAU_CENTRAL = "0";
	public static final String PERSONNEL_NIVEAU_REGION = "1";
	public static final String PERSONNEL_NIVEAU_DISTRICTSANTE = "2";
	public static final String PERSONNEL_NIVEAU_CDT = "3";

	/** DepartPersonnel enumeration constants */

	/** ArriveePersonnel enumeration constants */

	/** Region enumeration constants */

	// Libelle values
	public static final String REGION_LIBELLE_DOMICILE = "0";
	public static final String REGION_LIBELLE_BUREAU = "1";
	public static final String REGION_LIBELLE_AUTRE = "2";

	/** DistrictSante enumeration constants */

	// Libelle values
	public static final String DISTRICTSANTE_LIBELLE_DOMICILE = "0";
	public static final String DISTRICTSANTE_LIBELLE_BUREAU = "1";
	public static final String DISTRICTSANTE_LIBELLE_AUTRE = "2";

	/** CentreDiagTrait enumeration constants */

	// Libelle values
	public static final String CENTREDIAGTRAIT_LIBELLE_DOMICILE = "0";
	public static final String CENTREDIAGTRAIT_LIBELLE_BUREAU = "1";
	public static final String CENTREDIAGTRAIT_LIBELLE_AUTRE = "2";

	/** LaboratoireReference enumeration constants */

	// Nature values
	public static final String LABORATOIREREFERENCE_NATURE_NATIONAL = "0";
	public static final String LABORATOIREREFERENCE_NATURE_REGIONAL = "1";

	// Libelle values
	public static final String LABORATOIREREFERENCE_LIBELLE_DOMICILE = "0";
	public static final String LABORATOIREREFERENCE_LIBELLE_BUREAU = "1";
	public static final String LABORATOIREREFERENCE_LIBELLE_AUTRE = "2";

	/** LieuDit enumeration constants */

	// Libelle values
	public static final String LIEUDIT_LIBELLE_DOMICILE = "0";
	public static final String LIEUDIT_LIBELLE_BUREAU = "1";
	public static final String LIEUDIT_LIBELLE_AUTRE = "2";

	/** Regime enumeration constants */

	// Type values
	public static final String REGIME_TYPE_PHASEINITIALE = "0";
	public static final String REGIME_TYPE_PHASECONTINUATION = "1";
	public static final String REGIME_TYPE_INDEPENDANT = "2";

	/** PriseMedicamentRegime enumeration constants */

	/** Medicament enumeration constants */

	/** Intrant enumeration constants */

	/** Formation enumeration constants */

	/** CandidatureFormation enumeration constants */

	/** Qualification enumeration constants */

	/** Tutoriel enumeration constants */

	// Type values
	public static final String TUTORIEL_TYPE_TEXTE = "0";
	public static final String TUTORIEL_TYPE_AUDIO = "1";
	public static final String TUTORIEL_TYPE_VIDEO = "2";

	/** SmsPredefini enumeration constants */

	// Type values
	public static final String SMSPREDEFINI_TYPE_SENSIBILISATION = "0";
	public static final String SMSPREDEFINI_TYPE_QUIZZ = "1";
	public static final String SMSPREDEFINI_TYPE_RAPPELRDV = "2";
	public static final String SMSPREDEFINI_TYPE_MEDICALRECORD = "3";

	// Periodicite values
	public static final String SMSPREDEFINI_PERIODICITE_JOUR = "0";
	public static final String SMSPREDEFINI_PERIODICITE_SEMAINE = "1";
	public static final String SMSPREDEFINI_PERIODICITE_MOIS = "2";
	public static final String SMSPREDEFINI_PERIODICITE_TRIMESTRE = "3";
	public static final String SMSPREDEFINI_PERIODICITE_SEMESTRE = "4";
	public static final String SMSPREDEFINI_PERIODICITE_PONCTUELLE = "5";

	// Statut values
	public static final String SMSPREDEFINI_STATUT_ACTIF = "0";
	public static final String SMSPREDEFINI_STATUT_INACTIF = "1";

	/** OutBox enumeration constants */

	// Statut values
	public static final String OUTBOX_STATUT_ERREUR = "0";
	public static final String OUTBOX_STATUT_AENVOYER = "1";
	public static final String OUTBOX_STATUT_SUCCES = "2";

	/** Utilisateur enumeration constants */

	// Libelle values
	public static final String UTILISATEUR_LIBELLE_DOMICILE = "0";
	public static final String UTILISATEUR_LIBELLE_BUREAU = "1";
	public static final String UTILISATEUR_LIBELLE_AUTRE = "2";

}
