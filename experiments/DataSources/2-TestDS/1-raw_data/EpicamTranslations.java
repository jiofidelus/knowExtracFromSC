package org.imogene.epicam.client.i18n;

import com.google.gwt.i18n.client.Constants;

public interface EpicamTranslations extends Constants {

	/* ------------------------------------------------------------------- */
	/*                        GENERATED TEXTS                              */
	/* ------------------------------------------------------------------- */

	/* Languages texts */
	String locale();
	String menu_francais();
	String menu_english();

	/* Enumeration texts */
	String enumeration_unknown();

	/* Thema texts */
	String thema_malade();
	String thema_examens();
	String thema_stock();
	String thema_aCMS();
	String thema_formationContinue();
	String thema_administration();
	String thema_administrationMedicament();
	String thema_rH();

	String application_title();

	/* Patient texts */
	String patient_name();
	String patient_name_plur();
	String patient_create_title();
	String patient_select_title();
	String patient_table_text();
	String patient_excel_title();

	/* Patient field group texts */
	String patient_group_identification();
	String patient_group_contact();
	String patient_group_personneContact();
	String patient_group_tuberculose();
	String patient_group_examens();

	/* Patient fields texts */
	String patient_field_identifiant();

	String patient_field_nom();

	String patient_field_sexe();
	String patient_sexe_masculin_option();
	String patient_sexe_feminin_option();
	String patient_field_dateNaissance();
	String patient_field_age();
	String patient_field_age_min();
	String patient_field_age_max();
	String patient_field_profession();

	String patient_field_centres();
	String patient_field_nationalite();
	String patient_nationalite_camerounais_option();
	String patient_nationalite_etranger_option();
	String patient_field_precisionSurNationalite();

	String patient_field_recevoirCarnetTelPortable();
	String patient_field_telephoneUn();

	String patient_field_telephoneDeux();

	String patient_field_telephoneTrois();

	String patient_field_email();

	String patient_field_libelle();
	String patient_libelle_domicile_option();
	String patient_libelle_bureau_option();
	String patient_libelle_autre_option();
	String patient_field_complementAdresse();

	String patient_field_quartier();

	String patient_field_ville();

	String patient_field_codePostal();

	String patient_field_lieuxDits();
	String patient_field_pacNom();

	String patient_field_pacTelephoneUn();

	String patient_field_pacTelephoneDeux();

	String patient_field_pacTelephoneTrois();

	String patient_field_pacEmail();

	String patient_field_pacLibelle();
	String patient_pacLibelle_domicile_option();
	String patient_pacLibelle_bureau_option();
	String patient_pacLibelle_autre_option();
	String patient_field_pacComplementAdresse();

	String patient_field_pacQuartier();

	String patient_field_pacVille();

	String patient_field_pacCodePostal();

	String patient_field_casTuberculose();
	String patient_field_casIndex();
	String patient_field_examensBiologiques();
	String patient_field_serologies();

	String patient_field_s_identifiant();
	String patient_field_s_nom();
	String patient_field_s_dateNaissance();
	String patient_field_s_age();
	String patient_field_s_sexe();
	String patient_field_s_profession();
	String patient_field_s_telephoneUn();
	String patient_field_s_telephoneDeux();
	String patient_field_s_pacNom();
	String patient_field_s_pacTelephoneUn();

	/* CasIndex texts */
	String casIndex_name();
	String casIndex_name_plur();
	String casIndex_create_title();
	String casIndex_select_title();
	String casIndex_table_text();
	String casIndex_excel_title();

	/* CasIndex field group texts */
	String casIndex_group_description();

	/* CasIndex fields texts */
	String casIndex_field_patient();
	String casIndex_field_patientLie();
	String casIndex_field_typeRelation();

	String casIndex_field_s_patient();
	String casIndex_field_s_typeRelation();
	String casIndex_field_s_patientLie();

	/* CasTuberculose texts */
	String casTuberculose_name();
	String casTuberculose_name_plur();
	String casTuberculose_create_title();
	String casTuberculose_select_title();
	String casTuberculose_table_text();
	String casTuberculose_excel_title();

	/* CasTuberculose field group texts */
	String casTuberculose_group_informations();
	String casTuberculose_group_examen();
	String casTuberculose_group_traitement();
	String casTuberculose_group_finTraitement();

	/* CasTuberculose fields texts */
	String casTuberculose_field_identifiant();

	String casTuberculose_field_numRegTB();

	String casTuberculose_field_patient();
	String casTuberculose_field_dateDebutTraitement();
	String casTuberculose_field_typePatient();
	String casTuberculose_typePatient_nouveauCas_option();
	String casTuberculose_typePatient_repriseApresAbandon_option();
	String casTuberculose_typePatient_echec_option();
	String casTuberculose_typePatient_rechute_option();
	String casTuberculose_typePatient_autre_option();
	String casTuberculose_field_typePatientPrecisions();

	String casTuberculose_field_formeMaladie();
	String casTuberculose_formeMaladie_tPMPlus_option();
	String casTuberculose_formeMaladie_tPMMoins_option();
	String casTuberculose_formeMaladie_extra_Pulmonaire_option();
	String casTuberculose_field_extraPulmonairePrecisions();

	String casTuberculose_field_cotrimoxazole();
	String casTuberculose_cotrimoxazole_non_option();
	String casTuberculose_cotrimoxazole_cotrimoxazole_960_option();
	String casTuberculose_cotrimoxazole_cotrimoxazole_480_option();
	String casTuberculose_field_antiRetroViraux();
	String casTuberculose_field_fumeur();
	String casTuberculose_field_fumeurArreter();
	String casTuberculose_field_examensMiscrocopies();
	String casTuberculose_field_examensATB();
	String casTuberculose_field_regimePhaseInitiale();
	String casTuberculose_field_regimePhaseContinuation();
	String casTuberculose_field_priseMedicamenteusePhaseInitiale();
	String casTuberculose_field_priseMedicamenteusePhaseContinuation();
	String casTuberculose_field_rendezVous();
	String casTuberculose_field_dateFinTraitement();
	String casTuberculose_field_devenirMalade();
	String casTuberculose_devenirMalade_guerris_option();
	String casTuberculose_devenirMalade_termine_option();
	String casTuberculose_devenirMalade_echec_option();
	String casTuberculose_devenirMalade_decede_option();
	String casTuberculose_devenirMalade_perduDeVue_option();
	String casTuberculose_devenirMalade_arretPrescripteur_option();
	String casTuberculose_devenirMalade_arretEffetsIndesi_option();
	String casTuberculose_devenirMalade_arretSurvenuTB_option();
	String casTuberculose_field_observation();

	String casTuberculose_field_s_patient();
	String casTuberculose_field_s_dateDebutTraitement();
	String casTuberculose_field_s_typePatient();
	String casTuberculose_field_s_formeMaladie();

	/* ExamenSerologie texts */
	String examenSerologie_name();
	String examenSerologie_name_plur();
	String examenSerologie_create_title();
	String examenSerologie_select_title();
	String examenSerologie_table_text();
	String examenSerologie_excel_title();

	/* ExamenSerologie field group texts */
	String examenSerologie_group_description();

	/* ExamenSerologie fields texts */
	String examenSerologie_field_patient();
	String examenSerologie_field_dateTest();
	String examenSerologie_field_nature();
	String examenSerologie_nature_vIH_option();
	String examenSerologie_nature_cD4_option();
	String examenSerologie_field_resultatVIH();
	String examenSerologie_resultatVIH_positif_option();
	String examenSerologie_resultatVIH_negatif_option();
	String examenSerologie_field_resultatCD4();

	String examenSerologie_field_s_patient();
	String examenSerologie_field_s_dateTest();
	String examenSerologie_field_s_nature();
	String examenSerologie_field_s_resultatCD4();
	String examenSerologie_field_s_resultatVIH();

	/* ExamenBiologique texts */
	String examenBiologique_name();
	String examenBiologique_name_plur();
	String examenBiologique_create_title();
	String examenBiologique_select_title();
	String examenBiologique_table_text();
	String examenBiologique_excel_title();

	/* ExamenBiologique field group texts */
	String examenBiologique_group_description();

	/* ExamenBiologique fields texts */
	String examenBiologique_field_patient();
	String examenBiologique_field_date();
	String examenBiologique_field_poids();
	String examenBiologique_field_poids_decimalNumber();
	String examenBiologique_field_observations();

	String examenBiologique_field_s_patient();
	String examenBiologique_field_s_date();
	String examenBiologique_field_s_poids();

	/* ExamenMicroscopie texts */
	String examenMicroscopie_name();
	String examenMicroscopie_name_plur();
	String examenMicroscopie_create_title();
	String examenMicroscopie_select_title();
	String examenMicroscopie_table_text();
	String examenMicroscopie_excel_title();

	/* ExamenMicroscopie field group texts */
	String examenMicroscopie_group_centreExamen();
	String examenMicroscopie_group_examen();

	/* ExamenMicroscopie fields texts */
	String examenMicroscopie_field_cDT();
	String examenMicroscopie_field_laboratoireReference();
	String examenMicroscopie_field_casTb();
	String examenMicroscopie_field_date();
	String examenMicroscopie_field_raisonDepistage();
	String examenMicroscopie_raisonDepistage_diagnostic_option();
	String examenMicroscopie_raisonDepistage_suivi_option();
	String examenMicroscopie_field_resultat();
	String examenMicroscopie_resultat_negatif_option();
	String examenMicroscopie_resultat_rare_option();
	String examenMicroscopie_resultat_unPlus_option();
	String examenMicroscopie_resultat_deuxPlus_option();
	String examenMicroscopie_resultat_troisPlus_option();
	String examenMicroscopie_field_observations();

	String examenMicroscopie_field_s_casTb();
	String examenMicroscopie_field_s_date();
	String examenMicroscopie_field_s_raisonDepistage();
	String examenMicroscopie_field_s_resultat();

	/* ExamenATB texts */
	String examenATB_name();
	String examenATB_name_plur();
	String examenATB_create_title();
	String examenATB_select_title();
	String examenATB_table_text();
	String examenATB_excel_title();

	/* ExamenATB field group texts */
	String examenATB_group_centreExamen();
	String examenATB_group_examen();

	/* ExamenATB fields texts */
	String examenATB_field_cDT();
	String examenATB_field_laboratoireReference();
	String examenATB_field_casTb();
	String examenATB_field_dateExamen();
	String examenATB_field_raisonDepistage();
	String examenATB_raisonDepistage_diagnostic_option();
	String examenATB_raisonDepistage_suivi_option();
	String examenATB_field_resultat();

	String examenATB_field_observations();

	String examenATB_field_s_casTb();
	String examenATB_field_s_dateExamen();
	String examenATB_field_s_raisonDepistage();
	String examenATB_field_s_resultat();

	/* PriseMedicamenteuse texts */
	String priseMedicamenteuse_name();
	String priseMedicamenteuse_name_plur();
	String priseMedicamenteuse_create_title();
	String priseMedicamenteuse_select_title();
	String priseMedicamenteuse_table_text();
	String priseMedicamenteuse_excel_title();

	/* PriseMedicamenteuse field group texts */
	String priseMedicamenteuse_group_description();

	/* PriseMedicamenteuse fields texts */
	String priseMedicamenteuse_field_phaseIntensive();
	String priseMedicamenteuse_field_phaseContinuation();
	String priseMedicamenteuse_field_dateEffective();
	String priseMedicamenteuse_field_prise();
	String priseMedicamenteuse_prise_priseSupervisee_option();
	String priseMedicamenteuse_prise_automedication_option();
	String priseMedicamenteuse_prise_nonVenu_option();
	String priseMedicamenteuse_field_cotrimoxazole();

	String priseMedicamenteuse_field_s_dateEffective();

	/* RendezVous texts */
	String rendezVous_name();
	String rendezVous_name_plur();
	String rendezVous_create_title();
	String rendezVous_select_title();
	String rendezVous_table_text();
	String rendezVous_excel_title();

	/* RendezVous field group texts */
	String rendezVous_group_description();

	/* RendezVous fields texts */
	String rendezVous_field_casTb();
	String rendezVous_field_dateRendezVous();
	String rendezVous_field_honore();
	String rendezVous_field_nombreSMSEnvoye();
	String rendezVous_field_commentaire();

	String rendezVous_field_s_dateRendezVous();
	String rendezVous_field_s_honore();

	/* TransfertReference texts */
	String transfertReference_name();
	String transfertReference_name_plur();
	String transfertReference_create_title();
	String transfertReference_select_title();
	String transfertReference_table_text();
	String transfertReference_excel_title();

	/* TransfertReference field group texts */
	String transfertReference_group_informationsDepart();
	String transfertReference_group_informationArrivee();

	/* TransfertReference fields texts */
	String transfertReference_field_nature();
	String transfertReference_nature_transfert_option();
	String transfertReference_nature_reference_option();
	String transfertReference_field_region();
	String transfertReference_field_districtSante();
	String transfertReference_field_cDTDepart();
	String transfertReference_field_patient();
	String transfertReference_field_dateDepart();
	String transfertReference_field_motifDepart();

	String transfertReference_field_regionArrivee();
	String transfertReference_field_districtSanteArrivee();
	String transfertReference_field_cDTArrivee();
	String transfertReference_field_dateArrivee();

	String transfertReference_field_s_patient();
	String transfertReference_field_s_nature();
	String transfertReference_field_s_cDTDepart();
	String transfertReference_field_s_dateDepart();
	String transfertReference_field_s_cDTArrivee();
	String transfertReference_field_s_dateArrivee();

	/* Lot texts */
	String lot_name();
	String lot_name_plur();
	String lot_create_title();
	String lot_select_title();
	String lot_table_text();
	String lot_excel_title();

	/* Lot field group texts */
	String lot_group_description();

	/* Lot fields texts */
	String lot_field_region();
	String lot_field_districtSante();
	String lot_field_cDT();
	String lot_field_numero();

	String lot_field_type();
	String lot_type_medicament_option();
	String lot_type_intrant_option();
	String lot_field_medicament();
	String lot_field_intrant();
	String lot_field_quantiteInitiale();
	String lot_field_quantiteInitiale_min();
	String lot_field_quantite();
	String lot_field_quantite_min();
	String lot_field_datePeremption();

	String lot_field_s_cDT();
	String lot_field_s_numero();
	String lot_field_s_quantite();
	String lot_field_s_datePeremption();
	String lot_field_s_intrant();
	String lot_field_s_medicament();

	/* HorsUsage texts */
	String horsUsage_name();
	String horsUsage_name_plur();
	String horsUsage_create_title();
	String horsUsage_select_title();
	String horsUsage_table_text();
	String horsUsage_excel_title();

	/* HorsUsage field group texts */
	String horsUsage_group_description();

	/* HorsUsage fields texts */
	String horsUsage_field_lot();
	String horsUsage_field_type();
	String horsUsage_type_perimee_option();
	String horsUsage_type_casse_option();
	String horsUsage_field_motif();

	String horsUsage_field_s_lot();
	String horsUsage_field_s_type();

	/* EntreeLot texts */
	String entreeLot_name();
	String entreeLot_name_plur();
	String entreeLot_create_title();
	String entreeLot_select_title();
	String entreeLot_table_text();
	String entreeLot_excel_title();

	/* EntreeLot field group texts */
	String entreeLot_group_description();

	/* EntreeLot fields texts */
	String entreeLot_field_cDT();
	String entreeLot_field_lot();
	String entreeLot_field_quantite();
	String entreeLot_field_quantite_min();

	String entreeLot_field_s_lot();
	String entreeLot_field_s_quantite();

	/* SortieLot texts */
	String sortieLot_name();
	String sortieLot_name_plur();
	String sortieLot_create_title();
	String sortieLot_select_title();
	String sortieLot_table_text();
	String sortieLot_excel_title();

	/* SortieLot field group texts */
	String sortieLot_group_description();

	/* SortieLot fields texts */
	String sortieLot_field_cDT();
	String sortieLot_field_lot();
	String sortieLot_field_quantite();
	String sortieLot_field_quantite_min();

	String sortieLot_field_s_lot();
	String sortieLot_field_s_quantite();

	/* Commande texts */
	String commande_name();
	String commande_name_plur();
	String commande_create_title();
	String commande_select_title();
	String commande_table_text();
	String commande_excel_title();

	/* Commande field group texts */
	String commande_group_informationsDepart();
	String commande_group_regionApprobation();
	String commande_group_gtcApprobation();

	/* Commande fields texts */
	String commande_field_date();
	String commande_field_region();
	String commande_field_districtSante();
	String commande_field_cDT();
	String commande_field_medicaments();
	String commande_field_intrants();
	String commande_field_approuveeRegion();
	String commande_field_motifRejetRegion();

	String commande_field_approuveeGTC();
	String commande_field_motifRejetGTC();

	String commande_field_s_cDT();
	String commande_field_s_date();

	/* DetailCommandeMedicament texts */
	String detailCommandeMedicament_name();
	String detailCommandeMedicament_name_plur();
	String detailCommandeMedicament_create_title();
	String detailCommandeMedicament_select_title();
	String detailCommandeMedicament_table_text();
	String detailCommandeMedicament_excel_title();

	/* DetailCommandeMedicament field group texts */
	String detailCommandeMedicament_group_description();

	/* DetailCommandeMedicament fields texts */
	String detailCommandeMedicament_field_commande();
	String detailCommandeMedicament_field_medicament();
	String detailCommandeMedicament_field_quantiteRequise();
	String detailCommandeMedicament_field_quantiteRequise_min();
	String detailCommandeMedicament_field_quantiteEnStock();
	String detailCommandeMedicament_field_quantiteEnStock_min();

	String detailCommandeMedicament_field_s_quantiteRequise();
	String detailCommandeMedicament_field_s_quantiteEnStock();
	String detailCommandeMedicament_field_s_medicament();

	/* DetailCommandeIntrant texts */
	String detailCommandeIntrant_name();
	String detailCommandeIntrant_name_plur();
	String detailCommandeIntrant_create_title();
	String detailCommandeIntrant_select_title();
	String detailCommandeIntrant_table_text();
	String detailCommandeIntrant_excel_title();

	/* DetailCommandeIntrant field group texts */
	String detailCommandeIntrant_group_description();

	/* DetailCommandeIntrant fields texts */
	String detailCommandeIntrant_field_commande();
	String detailCommandeIntrant_field_intrant();
	String detailCommandeIntrant_field_quantiteRequise();
	String detailCommandeIntrant_field_quantiteRequise_min();
	String detailCommandeIntrant_field_quantiteEnStock();
	String detailCommandeIntrant_field_quantiteEnStock_min();

	String detailCommandeIntrant_field_s_intrant();
	String detailCommandeIntrant_field_s_quantiteRequise();
	String detailCommandeIntrant_field_s_quantiteEnStock();

	/* Reception texts */
	String reception_name();
	String reception_name_plur();
	String reception_create_title();
	String reception_select_title();
	String reception_table_text();
	String reception_excel_title();

	/* Reception field group texts */
	String reception_group_description();

	/* Reception fields texts */
	String reception_field_region();
	String reception_field_districtSante();
	String reception_field_cDT();
	String reception_field_commande();
	String reception_field_dateReception();
	String reception_field_medicaments();
	String reception_field_intrants();

	String reception_field_s_cDT();
	String reception_field_s_commande();
	String reception_field_s_dateReception();

	/* DetailReceptionMedicament texts */
	String detailReceptionMedicament_name();
	String detailReceptionMedicament_name_plur();
	String detailReceptionMedicament_create_title();
	String detailReceptionMedicament_select_title();
	String detailReceptionMedicament_table_text();
	String detailReceptionMedicament_excel_title();

	/* DetailReceptionMedicament field group texts */
	String detailReceptionMedicament_group_description();

	/* DetailReceptionMedicament fields texts */
	String detailReceptionMedicament_field_reception();
	String detailReceptionMedicament_field_commande();
	String detailReceptionMedicament_field_detailCommande();
	String detailReceptionMedicament_field_entreeLot();

	String detailReceptionMedicament_field_s_commande();
	String detailReceptionMedicament_field_s_detailCommande();

	/* DetailReceptionIntrant texts */
	String detailReceptionIntrant_name();
	String detailReceptionIntrant_name_plur();
	String detailReceptionIntrant_create_title();
	String detailReceptionIntrant_select_title();
	String detailReceptionIntrant_table_text();
	String detailReceptionIntrant_excel_title();

	/* DetailReceptionIntrant field group texts */
	String detailReceptionIntrant_group_description();

	/* DetailReceptionIntrant fields texts */
	String detailReceptionIntrant_field_reception();
	String detailReceptionIntrant_field_commande();
	String detailReceptionIntrant_field_detailCommande();
	String detailReceptionIntrant_field_entreeLot();

	String detailReceptionIntrant_field_s_commande();
	String detailReceptionIntrant_field_s_detailCommande();

	/* Ravitaillement texts */
	String ravitaillement_name();
	String ravitaillement_name_plur();
	String ravitaillement_create_title();
	String ravitaillement_select_title();
	String ravitaillement_table_text();
	String ravitaillement_excel_title();

	/* Ravitaillement field group texts */
	String ravitaillement_group_informationsDepart();
	String ravitaillement_group_informationArrivee();
	String ravitaillement_group_detail();

	/* Ravitaillement fields texts */
	String ravitaillement_field_region();
	String ravitaillement_field_districtSante();
	String ravitaillement_field_cDTDepart();
	String ravitaillement_field_dateDepart();
	String ravitaillement_field_regionArrivee();
	String ravitaillement_field_districtSanteArrivee();
	String ravitaillement_field_cDTArrivee();
	String ravitaillement_field_dateArrivee();
	String ravitaillement_field_details();

	String ravitaillement_field_s_cDTDepart();
	String ravitaillement_field_s_dateDepart();
	String ravitaillement_field_s_cDTArrivee();
	String ravitaillement_field_s_dateArrivee();

	/* DetailRavitaillement texts */
	String detailRavitaillement_name();
	String detailRavitaillement_name_plur();
	String detailRavitaillement_create_title();
	String detailRavitaillement_select_title();
	String detailRavitaillement_table_text();
	String detailRavitaillement_excel_title();

	/* DetailRavitaillement field group texts */
	String detailRavitaillement_group_description();
	String detailRavitaillement_group_sortie();
	String detailRavitaillement_group_entree();

	/* DetailRavitaillement fields texts */
	String detailRavitaillement_field_ravitaillement();
	String detailRavitaillement_field_sortieLot();
	String detailRavitaillement_field_entreeLot();

	String detailRavitaillement_field_s_ravitaillement();
	String detailRavitaillement_field_s_sortieLot();
	String detailRavitaillement_field_s_entreeLot();

	/* Inventaire texts */
	String inventaire_name();
	String inventaire_name_plur();
	String inventaire_create_title();
	String inventaire_select_title();
	String inventaire_table_text();
	String inventaire_excel_title();

	/* Inventaire field group texts */
	String inventaire_group_informationsDepart();

	/* Inventaire fields texts */
	String inventaire_field_date();
	String inventaire_field_region();
	String inventaire_field_districtSante();
	String inventaire_field_cDT();
	String inventaire_field_details();

	String inventaire_field_s_cDT();
	String inventaire_field_s_date();

	/* DetailInventaire texts */
	String detailInventaire_name();
	String detailInventaire_name_plur();
	String detailInventaire_create_title();
	String detailInventaire_select_title();
	String detailInventaire_table_text();
	String detailInventaire_excel_title();

	/* DetailInventaire field group texts */
	String detailInventaire_group_description();

	/* DetailInventaire fields texts */
	String detailInventaire_field_inventaire();
	String detailInventaire_field_cDT();
	String detailInventaire_field_lot();
	String detailInventaire_field_quantiteReelle();
	String detailInventaire_field_quantiteReelle_min();
	String detailInventaire_field_quantiteTheorique();
	String detailInventaire_field_quantiteTheorique_min();

	String detailInventaire_field_s_lot();
	String detailInventaire_field_s_quantiteTheorique();
	String detailInventaire_field_s_quantiteReelle();

	/* Personnel texts */
	String personnel_name();
	String personnel_name_plur();
	String personnel_create_title();
	String personnel_select_title();
	String personnel_table_text();
	String personnel_excel_title();

	/* Personnel field group texts */
	String personnel_group_identification();
	String personnel_group_contact();
	String personnel_group_situation();
	String personnel_group_niveauAccess();

	/* Personnel fields texts */
	String personnel_field_nom();

	String personnel_field_dateNaissance();
	String personnel_field_profession();

	String personnel_field_telephoneUn();

	String personnel_field_telephoneDeux();

	String personnel_field_telephoneTrois();

	String personnel_field_email();

	String personnel_field_libelle();
	String personnel_libelle_domicile_option();
	String personnel_libelle_bureau_option();
	String personnel_libelle_autre_option();
	String personnel_field_complementAdresse();

	String personnel_field_quartier();

	String personnel_field_ville();

	String personnel_field_codePostal();

	String personnel_field_dateDepart();
	String personnel_field_dateArrivee();
	String personnel_field_aEteForme();
	String personnel_field_qualification();
	String personnel_field_niveau();
	String personnel_niveau_central_option();
	String personnel_niveau_region_option();
	String personnel_niveau_districtSante_option();
	String personnel_niveau_cDT_option();
	String personnel_field_region();
	String personnel_field_districtSante();
	String personnel_field_cDT();
	String personnel_field_actif();

	String personnel_field_s_nom();
	String personnel_field_s_dateNaissance();
	String personnel_field_s_dateArrivee();
	String personnel_field_s_dateDepart();
	String personnel_field_s_niveau();
	String personnel_field_s_actif();

	/* DepartPersonnel texts */
	String departPersonnel_name();
	String departPersonnel_name_plur();
	String departPersonnel_create_title();
	String departPersonnel_select_title();
	String departPersonnel_table_text();
	String departPersonnel_excel_title();

	/* DepartPersonnel field group texts */
	String departPersonnel_group_personnel();
	String departPersonnel_group_description();

	/* DepartPersonnel fields texts */
	String departPersonnel_field_region();
	String departPersonnel_field_districtSante();
	String departPersonnel_field_cDT();
	String departPersonnel_field_personnel();
	String departPersonnel_field_dateDepart();
	String departPersonnel_field_motifDepart();

	String departPersonnel_field_s_dateDepart();
	String departPersonnel_field_s_region();
	String departPersonnel_field_s_districtSante();
	String departPersonnel_field_s_cDT();
	String departPersonnel_field_s_personnel();

	/* ArriveePersonnel texts */
	String arriveePersonnel_name();
	String arriveePersonnel_name_plur();
	String arriveePersonnel_create_title();
	String arriveePersonnel_select_title();
	String arriveePersonnel_table_text();
	String arriveePersonnel_excel_title();

	/* ArriveePersonnel field group texts */
	String arriveePersonnel_group_description();

	/* ArriveePersonnel fields texts */
	String arriveePersonnel_field_region();
	String arriveePersonnel_field_districtSante();
	String arriveePersonnel_field_cDT();
	String arriveePersonnel_field_personnel();
	String arriveePersonnel_field_dateArrivee();

	String arriveePersonnel_field_s_dateArrivee();
	String arriveePersonnel_field_s_region();
	String arriveePersonnel_field_s_districtSante();
	String arriveePersonnel_field_s_cDT();
	String arriveePersonnel_field_s_personnel();

	/* Region texts */
	String region_name();
	String region_name_plur();
	String region_create_title();
	String region_select_title();
	String region_table_text();
	String region_excel_title();

	/* Region field group texts */
	String region_group_description();
	String region_group_adresse();

	/* Region fields texts */
	String region_field_code();

	String region_field_nom();

	String region_field_description();

	String region_field_districtSantes();
	String region_field_libelle();
	String region_libelle_domicile_option();
	String region_libelle_bureau_option();
	String region_libelle_autre_option();
	String region_field_complementAdresse();

	String region_field_quartier();

	String region_field_ville();

	String region_field_codePostal();

	String region_field_coordonnees();

	String region_field_s_code();
	String region_field_s_nom();

	/* DistrictSante texts */
	String districtSante_name();
	String districtSante_name_plur();
	String districtSante_create_title();
	String districtSante_select_title();
	String districtSante_table_text();
	String districtSante_excel_title();

	/* DistrictSante field group texts */
	String districtSante_group_description();
	String districtSante_group_adresse();

	/* DistrictSante fields texts */
	String districtSante_field_code();

	String districtSante_field_nom();

	String districtSante_field_description();

	String districtSante_field_region();
	String districtSante_field_libelle();
	String districtSante_libelle_domicile_option();
	String districtSante_libelle_bureau_option();
	String districtSante_libelle_autre_option();
	String districtSante_field_complementAdresse();

	String districtSante_field_quartier();

	String districtSante_field_ville();

	String districtSante_field_codePostal();

	String districtSante_field_coordonnees();

	String districtSante_field_s_region();
	String districtSante_field_s_nom();

	/* CentreDiagTrait texts */
	String centreDiagTrait_name();
	String centreDiagTrait_name_plur();
	String centreDiagTrait_create_title();
	String centreDiagTrait_select_title();
	String centreDiagTrait_table_text();
	String centreDiagTrait_excel_title();

	/* CentreDiagTrait field group texts */
	String centreDiagTrait_group_description();
	String centreDiagTrait_group_adresse();

	/* CentreDiagTrait fields texts */
	String centreDiagTrait_field_code();

	String centreDiagTrait_field_region();
	String centreDiagTrait_field_districtSante();
	String centreDiagTrait_field_nom();

	String centreDiagTrait_field_description();

	String centreDiagTrait_field_libelle();
	String centreDiagTrait_libelle_domicile_option();
	String centreDiagTrait_libelle_bureau_option();
	String centreDiagTrait_libelle_autre_option();
	String centreDiagTrait_field_complementAdresse();

	String centreDiagTrait_field_quartier();

	String centreDiagTrait_field_ville();

	String centreDiagTrait_field_codePostal();

	String centreDiagTrait_field_coordonnees();
	String centreDiagTrait_field_lieuxDits();

	String centreDiagTrait_field_s_region();
	String centreDiagTrait_field_s_districtSante();
	String centreDiagTrait_field_s_nom();

	/* LaboratoireReference texts */
	String laboratoireReference_name();
	String laboratoireReference_name_plur();
	String laboratoireReference_create_title();
	String laboratoireReference_select_title();
	String laboratoireReference_table_text();
	String laboratoireReference_excel_title();

	/* LaboratoireReference field group texts */
	String laboratoireReference_group_description();
	String laboratoireReference_group_adresse();

	/* LaboratoireReference fields texts */
	String laboratoireReference_field_nom();

	String laboratoireReference_field_nature();
	String laboratoireReference_nature_national_option();
	String laboratoireReference_nature_regional_option();
	String laboratoireReference_field_description();

	String laboratoireReference_field_region();
	String laboratoireReference_field_districtSante();
	String laboratoireReference_field_libelle();
	String laboratoireReference_libelle_domicile_option();
	String laboratoireReference_libelle_bureau_option();
	String laboratoireReference_libelle_autre_option();
	String laboratoireReference_field_complementAdresse();

	String laboratoireReference_field_quartier();

	String laboratoireReference_field_ville();

	String laboratoireReference_field_codePostal();

	String laboratoireReference_field_coordonnees();
	String laboratoireReference_field_lieuxDits();

	String laboratoireReference_field_s_nom();
	String laboratoireReference_field_s_nature();

	/* LieuDit texts */
	String lieuDit_name();
	String lieuDit_name_plur();
	String lieuDit_create_title();
	String lieuDit_select_title();
	String lieuDit_table_text();
	String lieuDit_excel_title();

	/* LieuDit field group texts */
	String lieuDit_group_description();
	String lieuDit_group_adresse();

	/* LieuDit fields texts */
	String lieuDit_field_code();

	String lieuDit_field_nom();

	String lieuDit_field_description();

	String lieuDit_field_libelle();
	String lieuDit_libelle_domicile_option();
	String lieuDit_libelle_bureau_option();
	String lieuDit_libelle_autre_option();
	String lieuDit_field_complementAdresse();

	String lieuDit_field_quartier();

	String lieuDit_field_ville();

	String lieuDit_field_codePostal();

	String lieuDit_field_coordonnees();

	String lieuDit_field_s_nom();

	/* Regime texts */
	String regime_name();
	String regime_name_plur();
	String regime_create_title();
	String regime_select_title();
	String regime_table_text();
	String regime_excel_title();

	/* Regime field group texts */
	String regime_group_description();

	/* Regime fields texts */
	String regime_field_nom();

	String regime_field_type();
	String regime_type_phaseInitiale_option();
	String regime_type_phaseContinuation_option();
	String regime_type_independant_option();
	String regime_field_dureeTraitement();
	String regime_field_dureeTraitement_min();
	String regime_field_poidsMin();
	String regime_field_poidsMin_min();
	String regime_field_poidsMax();
	String regime_field_poidsMax_min();
	String regime_field_description();

	String regime_field_prisesMedicamenteuses();
	String regime_field_actif();

	String regime_field_s_nom();
	String regime_field_s_type();
	String regime_field_s_dureeTraitement();
	String regime_field_s_poidsMin();
	String regime_field_s_poidsMax();

	/* PriseMedicamentRegime texts */
	String priseMedicamentRegime_name();
	String priseMedicamentRegime_name_plur();
	String priseMedicamentRegime_create_title();
	String priseMedicamentRegime_select_title();
	String priseMedicamentRegime_table_text();
	String priseMedicamentRegime_excel_title();

	/* PriseMedicamentRegime field group texts */
	String priseMedicamentRegime_group_description();

	/* PriseMedicamentRegime fields texts */
	String priseMedicamentRegime_field_regime();
	String priseMedicamentRegime_field_medicament();
	String priseMedicamentRegime_field_quantite();
	String priseMedicamentRegime_field_quantite_min();
	String priseMedicamentRegime_field_quantite_decimalNumber();
	String priseMedicamentRegime_field_quantiteUnite();

	String priseMedicamentRegime_field_s_medicament();

	/* Medicament texts */
	String medicament_name();
	String medicament_name_plur();
	String medicament_create_title();
	String medicament_select_title();
	String medicament_table_text();
	String medicament_excel_title();

	/* Medicament field group texts */
	String medicament_group_description();

	/* Medicament fields texts */
	String medicament_field_code();

	String medicament_field_designation();

	String medicament_field_seuilPatient();
	String medicament_field_seuilPatient_decimalNumber();
	String medicament_field_estMedicamentAntituberculeux();

	String medicament_field_s_code();
	String medicament_field_s_designation();
	String medicament_field_s_estMedicamentAntituberculeux();

	/* Intrant texts */
	String intrant_name();
	String intrant_name_plur();
	String intrant_create_title();
	String intrant_select_title();
	String intrant_table_text();
	String intrant_excel_title();

	/* Intrant field group texts */
	String intrant_group_description();

	/* Intrant fields texts */
	String intrant_field_identifiant();

	String intrant_field_nom();

	String intrant_field_description();

	String intrant_field_seuilPatient();
	String intrant_field_seuilPatient_decimalNumber();

	String intrant_field_s_identifiant();
	String intrant_field_s_nom();

	/* Formation texts */
	String formation_name();
	String formation_name_plur();
	String formation_create_title();
	String formation_select_title();
	String formation_table_text();
	String formation_excel_title();

	/* Formation field group texts */
	String formation_group_description();

	/* Formation fields texts */
	String formation_field_libelle();

	String formation_field_dateDebut();
	String formation_field_dateFin();
	String formation_field_lieu();

	String formation_field_objectifs();

	String formation_field_effectuee();
	String formation_field_candidatures();

	String formation_field_s_dateDebut();
	String formation_field_s_libelle();
	String formation_field_s_lieu();
	String formation_field_s_effectuee();

	/* CandidatureFormation texts */
	String candidatureFormation_name();
	String candidatureFormation_name_plur();
	String candidatureFormation_create_title();
	String candidatureFormation_select_title();
	String candidatureFormation_table_text();
	String candidatureFormation_excel_title();

	/* CandidatureFormation field group texts */
	String candidatureFormation_group_description();
	String candidatureFormation_group_regionApprobation();
	String candidatureFormation_group_gtcApprobation();

	/* CandidatureFormation fields texts */
	String candidatureFormation_field_formation();
	String candidatureFormation_field_region();
	String candidatureFormation_field_districtSante();
	String candidatureFormation_field_cDT();
	String candidatureFormation_field_personnel();
	String candidatureFormation_field_approuveeRegion();
	String candidatureFormation_field_motifRejetRegion();

	String candidatureFormation_field_approuveeGTC();
	String candidatureFormation_field_motifRejetGTC();

	String candidatureFormation_field_s_personnel();
	String candidatureFormation_field_s_cDT();
	String candidatureFormation_field_s_approuveeRegion();
	String candidatureFormation_field_s_approuveeGTC();
	String candidatureFormation_field_s_districtSante();

	/* Qualification texts */
	String qualification_name();
	String qualification_name_plur();
	String qualification_create_title();
	String qualification_select_title();
	String qualification_table_text();
	String qualification_excel_title();

	/* Qualification field group texts */
	String qualification_group_description();

	/* Qualification fields texts */
	String qualification_field_code();

	String qualification_field_nom();

	String qualification_field_description();

	String qualification_field_s_code();
	String qualification_field_s_nom();

	/* Tutoriel texts */
	String tutoriel_name();
	String tutoriel_name_plur();
	String tutoriel_create_title();
	String tutoriel_select_title();
	String tutoriel_table_text();
	String tutoriel_excel_title();

	/* Tutoriel field group texts */
	String tutoriel_group_description();

	/* Tutoriel fields texts */
	String tutoriel_field_reference();

	String tutoriel_field_nom();

	String tutoriel_field_description();

	String tutoriel_field_type();
	String tutoriel_type_texte_option();
	String tutoriel_type_audio_option();
	String tutoriel_type_video_option();
	String tutoriel_field_audioT();
	String tutoriel_field_videoT();
	String tutoriel_field_textT();

	String tutoriel_field_s_nom();
	String tutoriel_field_s_reference();
	String tutoriel_field_s_type();

	/* SmsPredefini texts */
	String smsPredefini_name();
	String smsPredefini_name_plur();
	String smsPredefini_create_title();
	String smsPredefini_select_title();
	String smsPredefini_table_text();
	String smsPredefini_excel_title();

	/* SmsPredefini field group texts */
	String smsPredefini_group_description();

	/* SmsPredefini fields texts */
	String smsPredefini_field_type();
	String smsPredefini_type_sensibilisation_option();
	String smsPredefini_type_quizz_option();
	String smsPredefini_type_rappelRDV_option();
	String smsPredefini_type_medicalRecord_option();
	String smsPredefini_field_objet();

	String smsPredefini_field_periodicite();
	String smsPredefini_periodicite_jour_option();
	String smsPredefini_periodicite_semaine_option();
	String smsPredefini_periodicite_mois_option();
	String smsPredefini_periodicite_trimestre_option();
	String smsPredefini_periodicite_semestre_option();
	String smsPredefini_periodicite_ponctuelle_option();
	String smsPredefini_field_dateEnvoyeSMSPonctuel();
	String smsPredefini_field_statut();
	String smsPredefini_statut_actif_option();
	String smsPredefini_statut_inactif_option();
	String smsPredefini_field_message();

	String smsPredefini_field_reponse1();

	String smsPredefini_field_reponse2();

	String smsPredefini_field_bonneReponse();

	String smsPredefini_field_envoyerAPartirDe();
	String smsPredefini_field_arreterEnvoyerA();

	String smsPredefini_field_s_type();
	String smsPredefini_field_s_objet();
	String smsPredefini_field_s_message();

	/* OutBox texts */
	String outBox_name();
	String outBox_name_plur();
	String outBox_create_title();
	String outBox_select_title();
	String outBox_table_text();
	String outBox_excel_title();

	/* OutBox field group texts */
	String outBox_group_adresse();
	String outBox_group_messageInformation();

	/* OutBox fields texts */
	String outBox_field_patient();
	String outBox_field_message();

	String outBox_field_reponse();

	String outBox_field_statut();
	String outBox_statut_erreur_option();
	String outBox_statut_aEnvoyer_option();
	String outBox_statut_succes_option();
	String outBox_field_dateDernierEssai();

	String outBox_field_s_message();

	/* Utilisateur texts */
	String utilisateur_name();
	String utilisateur_name_plur();
	String utilisateur_create_title();
	String utilisateur_select_title();
	String utilisateur_table_text();
	String utilisateur_excel_title();

	/* Utilisateur field group texts */
	String utilisateur_group_identification();
	String utilisateur_group_contact();

	/* Utilisateur fields texts */
	String utilisateur_field_nom();

	String utilisateur_field_dateNaissance();
	String utilisateur_field_profession();

	String utilisateur_field_telephoneUn();

	String utilisateur_field_telephoneDeux();

	String utilisateur_field_telephoneTrois();

	String utilisateur_field_email();

	String utilisateur_field_libelle();
	String utilisateur_libelle_domicile_option();
	String utilisateur_libelle_bureau_option();
	String utilisateur_libelle_autre_option();
	String utilisateur_field_complementAdresse();

	String utilisateur_field_quartier();

	String utilisateur_field_ville();

	String utilisateur_field_codePostal();

	String utilisateur_field_s_nom();
	String utilisateur_field_s_profession();
	String utilisateur_field_s_dateNaissance();

}
