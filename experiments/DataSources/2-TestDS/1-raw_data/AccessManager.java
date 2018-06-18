package org.imogene.epicam.client;

import org.imogene.web.client.util.ProfileUtil;
import org.imogene.web.client.util.LocalSession;
import org.imogene.web.shared.proxy.EntityProfileProxy;
import org.imogene.web.shared.proxy.FieldGroupProfileProxy;
import org.imogene.web.shared.proxy.ImogActorProxy;
import org.imogene.web.shared.proxy.ProfileProxy;

/**
 * Class to manage the access to the entity forms and 
 * form groups in read and write mode on the Client side
 * @author MEDES-IMPS
 */
public class AccessManager {

	private static boolean loaded = false;
	private static boolean canCreatePatient;
	private static boolean canDeletePatient;
	private static boolean canDirectAccessPatient;
	private static boolean canExportPatient;
	private static boolean canReadPatientIdentification;
	private static boolean canWritePatientIdentification;
	private static boolean canExportPatientIdentification;
	private static boolean canReadPatientContact;
	private static boolean canWritePatientContact;
	private static boolean canExportPatientContact;
	private static boolean canReadPatientPersonneContact;
	private static boolean canWritePatientPersonneContact;
	private static boolean canExportPatientPersonneContact;
	private static boolean canReadPatientTuberculose;
	private static boolean canWritePatientTuberculose;
	private static boolean canExportPatientTuberculose;
	private static boolean canReadPatientExamens;
	private static boolean canWritePatientExamens;
	private static boolean canExportPatientExamens;
	private static boolean canCreateCasIndex;
	private static boolean canDeleteCasIndex;
	private static boolean canDirectAccessCasIndex;
	private static boolean canExportCasIndex;
	private static boolean canReadCasIndexDescription;
	private static boolean canWriteCasIndexDescription;
	private static boolean canExportCasIndexDescription;
	private static boolean canCreateCasTuberculose;
	private static boolean canDeleteCasTuberculose;
	private static boolean canDirectAccessCasTuberculose;
	private static boolean canExportCasTuberculose;
	private static boolean canReadCasTuberculoseInformations;
	private static boolean canWriteCasTuberculoseInformations;
	private static boolean canExportCasTuberculoseInformations;
	private static boolean canReadCasTuberculoseExamen;
	private static boolean canWriteCasTuberculoseExamen;
	private static boolean canExportCasTuberculoseExamen;
	private static boolean canReadCasTuberculoseTraitement;
	private static boolean canWriteCasTuberculoseTraitement;
	private static boolean canExportCasTuberculoseTraitement;
	private static boolean canReadCasTuberculoseFinTraitement;
	private static boolean canWriteCasTuberculoseFinTraitement;
	private static boolean canExportCasTuberculoseFinTraitement;
	private static boolean canCreateExamenSerologie;
	private static boolean canDeleteExamenSerologie;
	private static boolean canDirectAccessExamenSerologie;
	private static boolean canExportExamenSerologie;
	private static boolean canReadExamenSerologieDescription;
	private static boolean canWriteExamenSerologieDescription;
	private static boolean canExportExamenSerologieDescription;
	private static boolean canCreateExamenBiologique;
	private static boolean canDeleteExamenBiologique;
	private static boolean canDirectAccessExamenBiologique;
	private static boolean canExportExamenBiologique;
	private static boolean canReadExamenBiologiqueDescription;
	private static boolean canWriteExamenBiologiqueDescription;
	private static boolean canExportExamenBiologiqueDescription;
	private static boolean canCreateExamenMicroscopie;
	private static boolean canDeleteExamenMicroscopie;
	private static boolean canDirectAccessExamenMicroscopie;
	private static boolean canExportExamenMicroscopie;
	private static boolean canReadExamenMicroscopieCentreExamen;
	private static boolean canWriteExamenMicroscopieCentreExamen;
	private static boolean canExportExamenMicroscopieCentreExamen;
	private static boolean canReadExamenMicroscopieExamen;
	private static boolean canWriteExamenMicroscopieExamen;
	private static boolean canExportExamenMicroscopieExamen;
	private static boolean canCreateExamenATB;
	private static boolean canDeleteExamenATB;
	private static boolean canDirectAccessExamenATB;
	private static boolean canExportExamenATB;
	private static boolean canReadExamenATBCentreExamen;
	private static boolean canWriteExamenATBCentreExamen;
	private static boolean canExportExamenATBCentreExamen;
	private static boolean canReadExamenATBExamen;
	private static boolean canWriteExamenATBExamen;
	private static boolean canExportExamenATBExamen;
	private static boolean canCreatePriseMedicamenteuse;
	private static boolean canDeletePriseMedicamenteuse;
	private static boolean canDirectAccessPriseMedicamenteuse;
	private static boolean canExportPriseMedicamenteuse;
	private static boolean canReadPriseMedicamenteuseDescription;
	private static boolean canWritePriseMedicamenteuseDescription;
	private static boolean canExportPriseMedicamenteuseDescription;
	private static boolean canCreateRendezVous;
	private static boolean canDeleteRendezVous;
	private static boolean canDirectAccessRendezVous;
	private static boolean canExportRendezVous;
	private static boolean canReadRendezVousDescription;
	private static boolean canWriteRendezVousDescription;
	private static boolean canExportRendezVousDescription;
	private static boolean canCreateTransfertReference;
	private static boolean canDeleteTransfertReference;
	private static boolean canDirectAccessTransfertReference;
	private static boolean canExportTransfertReference;
	private static boolean canReadTransfertReferenceInformationsDepart;
	private static boolean canWriteTransfertReferenceInformationsDepart;
	private static boolean canExportTransfertReferenceInformationsDepart;
	private static boolean canReadTransfertReferenceInformationArrivee;
	private static boolean canWriteTransfertReferenceInformationArrivee;
	private static boolean canExportTransfertReferenceInformationArrivee;
	private static boolean canCreateLot;
	private static boolean canDeleteLot;
	private static boolean canDirectAccessLot;
	private static boolean canExportLot;
	private static boolean canReadLotDescription;
	private static boolean canWriteLotDescription;
	private static boolean canExportLotDescription;
	private static boolean canCreateHorsUsage;
	private static boolean canDeleteHorsUsage;
	private static boolean canDirectAccessHorsUsage;
	private static boolean canExportHorsUsage;
	private static boolean canReadHorsUsageDescription;
	private static boolean canWriteHorsUsageDescription;
	private static boolean canExportHorsUsageDescription;
	private static boolean canCreateEntreeLot;
	private static boolean canDeleteEntreeLot;
	private static boolean canDirectAccessEntreeLot;
	private static boolean canExportEntreeLot;
	private static boolean canReadEntreeLotDescription;
	private static boolean canWriteEntreeLotDescription;
	private static boolean canExportEntreeLotDescription;
	private static boolean canCreateSortieLot;
	private static boolean canDeleteSortieLot;
	private static boolean canDirectAccessSortieLot;
	private static boolean canExportSortieLot;
	private static boolean canReadSortieLotDescription;
	private static boolean canWriteSortieLotDescription;
	private static boolean canExportSortieLotDescription;
	private static boolean canCreateCommande;
	private static boolean canDeleteCommande;
	private static boolean canDirectAccessCommande;
	private static boolean canExportCommande;
	private static boolean canReadCommandeInformationsDepart;
	private static boolean canWriteCommandeInformationsDepart;
	private static boolean canExportCommandeInformationsDepart;
	private static boolean canReadCommandeRegionApprobation;
	private static boolean canWriteCommandeRegionApprobation;
	private static boolean canExportCommandeRegionApprobation;
	private static boolean canReadCommandeGtcApprobation;
	private static boolean canWriteCommandeGtcApprobation;
	private static boolean canExportCommandeGtcApprobation;
	private static boolean canCreateDetailCommandeMedicament;
	private static boolean canDeleteDetailCommandeMedicament;
	private static boolean canDirectAccessDetailCommandeMedicament;
	private static boolean canExportDetailCommandeMedicament;
	private static boolean canReadDetailCommandeMedicamentDescription;
	private static boolean canWriteDetailCommandeMedicamentDescription;
	private static boolean canExportDetailCommandeMedicamentDescription;
	private static boolean canCreateDetailCommandeIntrant;
	private static boolean canDeleteDetailCommandeIntrant;
	private static boolean canDirectAccessDetailCommandeIntrant;
	private static boolean canExportDetailCommandeIntrant;
	private static boolean canReadDetailCommandeIntrantDescription;
	private static boolean canWriteDetailCommandeIntrantDescription;
	private static boolean canExportDetailCommandeIntrantDescription;
	private static boolean canCreateReception;
	private static boolean canDeleteReception;
	private static boolean canDirectAccessReception;
	private static boolean canExportReception;
	private static boolean canReadReceptionDescription;
	private static boolean canWriteReceptionDescription;
	private static boolean canExportReceptionDescription;
	private static boolean canCreateDetailReceptionMedicament;
	private static boolean canDeleteDetailReceptionMedicament;
	private static boolean canDirectAccessDetailReceptionMedicament;
	private static boolean canExportDetailReceptionMedicament;
	private static boolean canReadDetailReceptionMedicamentDescription;
	private static boolean canWriteDetailReceptionMedicamentDescription;
	private static boolean canExportDetailReceptionMedicamentDescription;
	private static boolean canCreateDetailReceptionIntrant;
	private static boolean canDeleteDetailReceptionIntrant;
	private static boolean canDirectAccessDetailReceptionIntrant;
	private static boolean canExportDetailReceptionIntrant;
	private static boolean canReadDetailReceptionIntrantDescription;
	private static boolean canWriteDetailReceptionIntrantDescription;
	private static boolean canExportDetailReceptionIntrantDescription;
	private static boolean canCreateRavitaillement;
	private static boolean canDeleteRavitaillement;
	private static boolean canDirectAccessRavitaillement;
	private static boolean canExportRavitaillement;
	private static boolean canReadRavitaillementInformationsDepart;
	private static boolean canWriteRavitaillementInformationsDepart;
	private static boolean canExportRavitaillementInformationsDepart;
	private static boolean canReadRavitaillementInformationArrivee;
	private static boolean canWriteRavitaillementInformationArrivee;
	private static boolean canExportRavitaillementInformationArrivee;
	private static boolean canReadRavitaillementDetail;
	private static boolean canWriteRavitaillementDetail;
	private static boolean canExportRavitaillementDetail;
	private static boolean canCreateDetailRavitaillement;
	private static boolean canDeleteDetailRavitaillement;
	private static boolean canDirectAccessDetailRavitaillement;
	private static boolean canExportDetailRavitaillement;
	private static boolean canReadDetailRavitaillementDescription;
	private static boolean canWriteDetailRavitaillementDescription;
	private static boolean canExportDetailRavitaillementDescription;
	private static boolean canReadDetailRavitaillementSortie;
	private static boolean canWriteDetailRavitaillementSortie;
	private static boolean canExportDetailRavitaillementSortie;
	private static boolean canReadDetailRavitaillementEntree;
	private static boolean canWriteDetailRavitaillementEntree;
	private static boolean canExportDetailRavitaillementEntree;
	private static boolean canCreateInventaire;
	private static boolean canDeleteInventaire;
	private static boolean canDirectAccessInventaire;
	private static boolean canExportInventaire;
	private static boolean canReadInventaireInformationsDepart;
	private static boolean canWriteInventaireInformationsDepart;
	private static boolean canExportInventaireInformationsDepart;
	private static boolean canCreateDetailInventaire;
	private static boolean canDeleteDetailInventaire;
	private static boolean canDirectAccessDetailInventaire;
	private static boolean canExportDetailInventaire;
	private static boolean canReadDetailInventaireDescription;
	private static boolean canWriteDetailInventaireDescription;
	private static boolean canExportDetailInventaireDescription;
	private static boolean canCreatePersonnel;
	private static boolean canDeletePersonnel;
	private static boolean canDirectAccessPersonnel;
	private static boolean canExportPersonnel;
	private static boolean canReadPersonnelIdentification;
	private static boolean canWritePersonnelIdentification;
	private static boolean canExportPersonnelIdentification;
	private static boolean canReadPersonnelContact;
	private static boolean canWritePersonnelContact;
	private static boolean canExportPersonnelContact;
	private static boolean canReadPersonnelSituation;
	private static boolean canWritePersonnelSituation;
	private static boolean canExportPersonnelSituation;
	private static boolean canReadPersonnelNiveauAccess;
	private static boolean canWritePersonnelNiveauAccess;
	private static boolean canExportPersonnelNiveauAccess;
	private static boolean canCreateDepartPersonnel;
	private static boolean canDeleteDepartPersonnel;
	private static boolean canDirectAccessDepartPersonnel;
	private static boolean canExportDepartPersonnel;
	private static boolean canReadDepartPersonnelPersonnel;
	private static boolean canWriteDepartPersonnelPersonnel;
	private static boolean canExportDepartPersonnelPersonnel;
	private static boolean canReadDepartPersonnelDescription;
	private static boolean canWriteDepartPersonnelDescription;
	private static boolean canExportDepartPersonnelDescription;
	private static boolean canCreateArriveePersonnel;
	private static boolean canDeleteArriveePersonnel;
	private static boolean canDirectAccessArriveePersonnel;
	private static boolean canExportArriveePersonnel;
	private static boolean canReadArriveePersonnelDescription;
	private static boolean canWriteArriveePersonnelDescription;
	private static boolean canExportArriveePersonnelDescription;
	private static boolean canCreateRegion;
	private static boolean canDeleteRegion;
	private static boolean canDirectAccessRegion;
	private static boolean canExportRegion;
	private static boolean canReadRegionDescription;
	private static boolean canWriteRegionDescription;
	private static boolean canExportRegionDescription;
	private static boolean canReadRegionAdresse;
	private static boolean canWriteRegionAdresse;
	private static boolean canExportRegionAdresse;
	private static boolean canCreateDistrictSante;
	private static boolean canDeleteDistrictSante;
	private static boolean canDirectAccessDistrictSante;
	private static boolean canExportDistrictSante;
	private static boolean canReadDistrictSanteDescription;
	private static boolean canWriteDistrictSanteDescription;
	private static boolean canExportDistrictSanteDescription;
	private static boolean canReadDistrictSanteAdresse;
	private static boolean canWriteDistrictSanteAdresse;
	private static boolean canExportDistrictSanteAdresse;
	private static boolean canCreateCentreDiagTrait;
	private static boolean canDeleteCentreDiagTrait;
	private static boolean canDirectAccessCentreDiagTrait;
	private static boolean canExportCentreDiagTrait;
	private static boolean canReadCentreDiagTraitDescription;
	private static boolean canWriteCentreDiagTraitDescription;
	private static boolean canExportCentreDiagTraitDescription;
	private static boolean canReadCentreDiagTraitAdresse;
	private static boolean canWriteCentreDiagTraitAdresse;
	private static boolean canExportCentreDiagTraitAdresse;
	private static boolean canCreateLaboratoireReference;
	private static boolean canDeleteLaboratoireReference;
	private static boolean canDirectAccessLaboratoireReference;
	private static boolean canExportLaboratoireReference;
	private static boolean canReadLaboratoireReferenceDescription;
	private static boolean canWriteLaboratoireReferenceDescription;
	private static boolean canExportLaboratoireReferenceDescription;
	private static boolean canReadLaboratoireReferenceAdresse;
	private static boolean canWriteLaboratoireReferenceAdresse;
	private static boolean canExportLaboratoireReferenceAdresse;
	private static boolean canCreateLieuDit;
	private static boolean canDeleteLieuDit;
	private static boolean canDirectAccessLieuDit;
	private static boolean canExportLieuDit;
	private static boolean canReadLieuDitDescription;
	private static boolean canWriteLieuDitDescription;
	private static boolean canExportLieuDitDescription;
	private static boolean canReadLieuDitAdresse;
	private static boolean canWriteLieuDitAdresse;
	private static boolean canExportLieuDitAdresse;
	private static boolean canCreateRegime;
	private static boolean canDeleteRegime;
	private static boolean canDirectAccessRegime;
	private static boolean canExportRegime;
	private static boolean canReadRegimeDescription;
	private static boolean canWriteRegimeDescription;
	private static boolean canExportRegimeDescription;
	private static boolean canCreatePriseMedicamentRegime;
	private static boolean canDeletePriseMedicamentRegime;
	private static boolean canDirectAccessPriseMedicamentRegime;
	private static boolean canExportPriseMedicamentRegime;
	private static boolean canReadPriseMedicamentRegimeDescription;
	private static boolean canWritePriseMedicamentRegimeDescription;
	private static boolean canExportPriseMedicamentRegimeDescription;
	private static boolean canCreateMedicament;
	private static boolean canDeleteMedicament;
	private static boolean canDirectAccessMedicament;
	private static boolean canExportMedicament;
	private static boolean canReadMedicamentDescription;
	private static boolean canWriteMedicamentDescription;
	private static boolean canExportMedicamentDescription;
	private static boolean canCreateIntrant;
	private static boolean canDeleteIntrant;
	private static boolean canDirectAccessIntrant;
	private static boolean canExportIntrant;
	private static boolean canReadIntrantDescription;
	private static boolean canWriteIntrantDescription;
	private static boolean canExportIntrantDescription;
	private static boolean canCreateFormation;
	private static boolean canDeleteFormation;
	private static boolean canDirectAccessFormation;
	private static boolean canExportFormation;
	private static boolean canReadFormationDescription;
	private static boolean canWriteFormationDescription;
	private static boolean canExportFormationDescription;
	private static boolean canCreateCandidatureFormation;
	private static boolean canDeleteCandidatureFormation;
	private static boolean canDirectAccessCandidatureFormation;
	private static boolean canExportCandidatureFormation;
	private static boolean canReadCandidatureFormationDescription;
	private static boolean canWriteCandidatureFormationDescription;
	private static boolean canExportCandidatureFormationDescription;
	private static boolean canReadCandidatureFormationRegionApprobation;
	private static boolean canWriteCandidatureFormationRegionApprobation;
	private static boolean canExportCandidatureFormationRegionApprobation;
	private static boolean canReadCandidatureFormationGtcApprobation;
	private static boolean canWriteCandidatureFormationGtcApprobation;
	private static boolean canExportCandidatureFormationGtcApprobation;
	private static boolean canCreateQualification;
	private static boolean canDeleteQualification;
	private static boolean canDirectAccessQualification;
	private static boolean canExportQualification;
	private static boolean canReadQualificationDescription;
	private static boolean canWriteQualificationDescription;
	private static boolean canExportQualificationDescription;
	private static boolean canCreateTutoriel;
	private static boolean canDeleteTutoriel;
	private static boolean canDirectAccessTutoriel;
	private static boolean canExportTutoriel;
	private static boolean canReadTutorielDescription;
	private static boolean canWriteTutorielDescription;
	private static boolean canExportTutorielDescription;
	private static boolean canCreateSmsPredefini;
	private static boolean canDeleteSmsPredefini;
	private static boolean canDirectAccessSmsPredefini;
	private static boolean canExportSmsPredefini;
	private static boolean canReadSmsPredefiniDescription;
	private static boolean canWriteSmsPredefiniDescription;
	private static boolean canExportSmsPredefiniDescription;
	private static boolean canCreateOutBox;
	private static boolean canDeleteOutBox;
	private static boolean canDirectAccessOutBox;
	private static boolean canExportOutBox;
	private static boolean canReadOutBoxAdresse;
	private static boolean canWriteOutBoxAdresse;
	private static boolean canExportOutBoxAdresse;
	private static boolean canReadOutBoxMessageInformation;
	private static boolean canWriteOutBoxMessageInformation;
	private static boolean canExportOutBoxMessageInformation;
	private static boolean canCreateUtilisateur;
	private static boolean canDeleteUtilisateur;
	private static boolean canDirectAccessUtilisateur;
	private static boolean canExportUtilisateur;
	private static boolean canReadUtilisateurIdentification;
	private static boolean canWriteUtilisateurIdentification;
	private static boolean canExportUtilisateurIdentification;
	private static boolean canReadUtilisateurContact;
	private static boolean canWriteUtilisateurContact;
	private static boolean canExportUtilisateurContact;

	public static boolean canReadPatient() {
		ensureLoaded();
		return canReadPatientIdentification || canReadPatientContact
				|| canReadPatientPersonneContact || canReadPatientTuberculose
				|| canReadPatientExamens;
	}

	public static boolean canEditPatient() {
		ensureLoaded();
		return canWritePatientIdentification || canWritePatientContact
				|| canWritePatientPersonneContact || canWritePatientTuberculose
				|| canWritePatientExamens;
	}

	public static boolean canCreatePatient() {
		ensureLoaded();
		return canCreatePatient;
	}

	public static boolean canDeletePatient() {
		ensureLoaded();
		return canDeletePatient;
	}

	public static boolean canDirectAccessPatient() {
		ensureLoaded();
		return canDirectAccessPatient;
	}

	public static boolean canExportPatient() {
		ensureLoaded();
		return canExportPatient;
	}

	public static boolean canReadPatientIdentification() {
		ensureLoaded();
		return canReadPatientIdentification;
	}

	public static boolean canEditPatientIdentification() {
		ensureLoaded();
		return canWritePatientIdentification;
	}

	public static boolean canExportPatientIdentification() {
		ensureLoaded();
		return canExportPatientIdentification;
	}
	public static boolean canReadPatientContact() {
		ensureLoaded();
		return canReadPatientContact;
	}

	public static boolean canEditPatientContact() {
		ensureLoaded();
		return canWritePatientContact;
	}

	public static boolean canExportPatientContact() {
		ensureLoaded();
		return canExportPatientContact;
	}
	public static boolean canReadPatientPersonneContact() {
		ensureLoaded();
		return canReadPatientPersonneContact;
	}

	public static boolean canEditPatientPersonneContact() {
		ensureLoaded();
		return canWritePatientPersonneContact;
	}

	public static boolean canExportPatientPersonneContact() {
		ensureLoaded();
		return canExportPatientPersonneContact;
	}
	public static boolean canReadPatientTuberculose() {
		ensureLoaded();
		return canReadPatientTuberculose;
	}

	public static boolean canEditPatientTuberculose() {
		ensureLoaded();
		return canWritePatientTuberculose;
	}

	public static boolean canExportPatientTuberculose() {
		ensureLoaded();
		return canExportPatientTuberculose;
	}
	public static boolean canReadPatientExamens() {
		ensureLoaded();
		return canReadPatientExamens;
	}

	public static boolean canEditPatientExamens() {
		ensureLoaded();
		return canWritePatientExamens;
	}

	public static boolean canExportPatientExamens() {
		ensureLoaded();
		return canExportPatientExamens;
	}
	public static boolean canReadCasIndex() {
		ensureLoaded();
		return canReadCasIndexDescription;
	}

	public static boolean canEditCasIndex() {
		ensureLoaded();
		return canWriteCasIndexDescription;
	}

	public static boolean canCreateCasIndex() {
		ensureLoaded();
		return canCreateCasIndex;
	}

	public static boolean canDeleteCasIndex() {
		ensureLoaded();
		return canDeleteCasIndex;
	}

	public static boolean canDirectAccessCasIndex() {
		ensureLoaded();
		return canDirectAccessCasIndex;
	}

	public static boolean canExportCasIndex() {
		ensureLoaded();
		return canExportCasIndex;
	}

	public static boolean canReadCasIndexDescription() {
		ensureLoaded();
		return canReadCasIndexDescription;
	}

	public static boolean canEditCasIndexDescription() {
		ensureLoaded();
		return canWriteCasIndexDescription;
	}

	public static boolean canExportCasIndexDescription() {
		ensureLoaded();
		return canExportCasIndexDescription;
	}
	public static boolean canReadCasTuberculose() {
		ensureLoaded();
		return canReadCasTuberculoseInformations || canReadCasTuberculoseExamen
				|| canReadCasTuberculoseTraitement
				|| canReadCasTuberculoseFinTraitement;
	}

	public static boolean canEditCasTuberculose() {
		ensureLoaded();
		return canWriteCasTuberculoseInformations
				|| canWriteCasTuberculoseExamen
				|| canWriteCasTuberculoseTraitement
				|| canWriteCasTuberculoseFinTraitement;
	}

	public static boolean canCreateCasTuberculose() {
		ensureLoaded();
		return canCreateCasTuberculose;
	}

	public static boolean canDeleteCasTuberculose() {
		ensureLoaded();
		return canDeleteCasTuberculose;
	}

	public static boolean canDirectAccessCasTuberculose() {
		ensureLoaded();
		return canDirectAccessCasTuberculose;
	}

	public static boolean canExportCasTuberculose() {
		ensureLoaded();
		return canExportCasTuberculose;
	}

	public static boolean canReadCasTuberculoseInformations() {
		ensureLoaded();
		return canReadCasTuberculoseInformations;
	}

	public static boolean canEditCasTuberculoseInformations() {
		ensureLoaded();
		return canWriteCasTuberculoseInformations;
	}

	public static boolean canExportCasTuberculoseInformations() {
		ensureLoaded();
		return canExportCasTuberculoseInformations;
	}
	public static boolean canReadCasTuberculoseExamen() {
		ensureLoaded();
		return canReadCasTuberculoseExamen;
	}

	public static boolean canEditCasTuberculoseExamen() {
		ensureLoaded();
		return canWriteCasTuberculoseExamen;
	}

	public static boolean canExportCasTuberculoseExamen() {
		ensureLoaded();
		return canExportCasTuberculoseExamen;
	}
	public static boolean canReadCasTuberculoseTraitement() {
		ensureLoaded();
		return canReadCasTuberculoseTraitement;
	}

	public static boolean canEditCasTuberculoseTraitement() {
		ensureLoaded();
		return canWriteCasTuberculoseTraitement;
	}

	public static boolean canExportCasTuberculoseTraitement() {
		ensureLoaded();
		return canExportCasTuberculoseTraitement;
	}
	public static boolean canReadCasTuberculoseFinTraitement() {
		ensureLoaded();
		return canReadCasTuberculoseFinTraitement;
	}

	public static boolean canEditCasTuberculoseFinTraitement() {
		ensureLoaded();
		return canWriteCasTuberculoseFinTraitement;
	}

	public static boolean canExportCasTuberculoseFinTraitement() {
		ensureLoaded();
		return canExportCasTuberculoseFinTraitement;
	}
	public static boolean canReadExamenSerologie() {
		ensureLoaded();
		return canReadExamenSerologieDescription;
	}

	public static boolean canEditExamenSerologie() {
		ensureLoaded();
		return canWriteExamenSerologieDescription;
	}

	public static boolean canCreateExamenSerologie() {
		ensureLoaded();
		return canCreateExamenSerologie;
	}

	public static boolean canDeleteExamenSerologie() {
		ensureLoaded();
		return canDeleteExamenSerologie;
	}

	public static boolean canDirectAccessExamenSerologie() {
		ensureLoaded();
		return canDirectAccessExamenSerologie;
	}

	public static boolean canExportExamenSerologie() {
		ensureLoaded();
		return canExportExamenSerologie;
	}

	public static boolean canReadExamenSerologieDescription() {
		ensureLoaded();
		return canReadExamenSerologieDescription;
	}

	public static boolean canEditExamenSerologieDescription() {
		ensureLoaded();
		return canWriteExamenSerologieDescription;
	}

	public static boolean canExportExamenSerologieDescription() {
		ensureLoaded();
		return canExportExamenSerologieDescription;
	}
	public static boolean canReadExamenBiologique() {
		ensureLoaded();
		return canReadExamenBiologiqueDescription;
	}

	public static boolean canEditExamenBiologique() {
		ensureLoaded();
		return canWriteExamenBiologiqueDescription;
	}

	public static boolean canCreateExamenBiologique() {
		ensureLoaded();
		return canCreateExamenBiologique;
	}

	public static boolean canDeleteExamenBiologique() {
		ensureLoaded();
		return canDeleteExamenBiologique;
	}

	public static boolean canDirectAccessExamenBiologique() {
		ensureLoaded();
		return canDirectAccessExamenBiologique;
	}

	public static boolean canExportExamenBiologique() {
		ensureLoaded();
		return canExportExamenBiologique;
	}

	public static boolean canReadExamenBiologiqueDescription() {
		ensureLoaded();
		return canReadExamenBiologiqueDescription;
	}

	public static boolean canEditExamenBiologiqueDescription() {
		ensureLoaded();
		return canWriteExamenBiologiqueDescription;
	}

	public static boolean canExportExamenBiologiqueDescription() {
		ensureLoaded();
		return canExportExamenBiologiqueDescription;
	}
	public static boolean canReadExamenMicroscopie() {
		ensureLoaded();
		return canReadExamenMicroscopieCentreExamen
				|| canReadExamenMicroscopieExamen;
	}

	public static boolean canEditExamenMicroscopie() {
		ensureLoaded();
		return canWriteExamenMicroscopieCentreExamen
				|| canWriteExamenMicroscopieExamen;
	}

	public static boolean canCreateExamenMicroscopie() {
		ensureLoaded();
		return canCreateExamenMicroscopie;
	}

	public static boolean canDeleteExamenMicroscopie() {
		ensureLoaded();
		return canDeleteExamenMicroscopie;
	}

	public static boolean canDirectAccessExamenMicroscopie() {
		ensureLoaded();
		return canDirectAccessExamenMicroscopie;
	}

	public static boolean canExportExamenMicroscopie() {
		ensureLoaded();
		return canExportExamenMicroscopie;
	}

	public static boolean canReadExamenMicroscopieCentreExamen() {
		ensureLoaded();
		return canReadExamenMicroscopieCentreExamen;
	}

	public static boolean canEditExamenMicroscopieCentreExamen() {
		ensureLoaded();
		return canWriteExamenMicroscopieCentreExamen;
	}

	public static boolean canExportExamenMicroscopieCentreExamen() {
		ensureLoaded();
		return canExportExamenMicroscopieCentreExamen;
	}
	public static boolean canReadExamenMicroscopieExamen() {
		ensureLoaded();
		return canReadExamenMicroscopieExamen;
	}

	public static boolean canEditExamenMicroscopieExamen() {
		ensureLoaded();
		return canWriteExamenMicroscopieExamen;
	}

	public static boolean canExportExamenMicroscopieExamen() {
		ensureLoaded();
		return canExportExamenMicroscopieExamen;
	}
	public static boolean canReadExamenATB() {
		ensureLoaded();
		return canReadExamenATBCentreExamen || canReadExamenATBExamen;
	}

	public static boolean canEditExamenATB() {
		ensureLoaded();
		return canWriteExamenATBCentreExamen || canWriteExamenATBExamen;
	}

	public static boolean canCreateExamenATB() {
		ensureLoaded();
		return canCreateExamenATB;
	}

	public static boolean canDeleteExamenATB() {
		ensureLoaded();
		return canDeleteExamenATB;
	}

	public static boolean canDirectAccessExamenATB() {
		ensureLoaded();
		return canDirectAccessExamenATB;
	}

	public static boolean canExportExamenATB() {
		ensureLoaded();
		return canExportExamenATB;
	}

	public static boolean canReadExamenATBCentreExamen() {
		ensureLoaded();
		return canReadExamenATBCentreExamen;
	}

	public static boolean canEditExamenATBCentreExamen() {
		ensureLoaded();
		return canWriteExamenATBCentreExamen;
	}

	public static boolean canExportExamenATBCentreExamen() {
		ensureLoaded();
		return canExportExamenATBCentreExamen;
	}
	public static boolean canReadExamenATBExamen() {
		ensureLoaded();
		return canReadExamenATBExamen;
	}

	public static boolean canEditExamenATBExamen() {
		ensureLoaded();
		return canWriteExamenATBExamen;
	}

	public static boolean canExportExamenATBExamen() {
		ensureLoaded();
		return canExportExamenATBExamen;
	}
	public static boolean canReadPriseMedicamenteuse() {
		ensureLoaded();
		return canReadPriseMedicamenteuseDescription;
	}

	public static boolean canEditPriseMedicamenteuse() {
		ensureLoaded();
		return canWritePriseMedicamenteuseDescription;
	}

	public static boolean canCreatePriseMedicamenteuse() {
		ensureLoaded();
		return canCreatePriseMedicamenteuse;
	}

	public static boolean canDeletePriseMedicamenteuse() {
		ensureLoaded();
		return canDeletePriseMedicamenteuse;
	}

	public static boolean canDirectAccessPriseMedicamenteuse() {
		ensureLoaded();
		return canDirectAccessPriseMedicamenteuse;
	}

	public static boolean canExportPriseMedicamenteuse() {
		ensureLoaded();
		return canExportPriseMedicamenteuse;
	}

	public static boolean canReadPriseMedicamenteuseDescription() {
		ensureLoaded();
		return canReadPriseMedicamenteuseDescription;
	}

	public static boolean canEditPriseMedicamenteuseDescription() {
		ensureLoaded();
		return canWritePriseMedicamenteuseDescription;
	}

	public static boolean canExportPriseMedicamenteuseDescription() {
		ensureLoaded();
		return canExportPriseMedicamenteuseDescription;
	}
	public static boolean canReadRendezVous() {
		ensureLoaded();
		return canReadRendezVousDescription;
	}

	public static boolean canEditRendezVous() {
		ensureLoaded();
		return canWriteRendezVousDescription;
	}

	public static boolean canCreateRendezVous() {
		ensureLoaded();
		return canCreateRendezVous;
	}

	public static boolean canDeleteRendezVous() {
		ensureLoaded();
		return canDeleteRendezVous;
	}

	public static boolean canDirectAccessRendezVous() {
		ensureLoaded();
		return canDirectAccessRendezVous;
	}

	public static boolean canExportRendezVous() {
		ensureLoaded();
		return canExportRendezVous;
	}

	public static boolean canReadRendezVousDescription() {
		ensureLoaded();
		return canReadRendezVousDescription;
	}

	public static boolean canEditRendezVousDescription() {
		ensureLoaded();
		return canWriteRendezVousDescription;
	}

	public static boolean canExportRendezVousDescription() {
		ensureLoaded();
		return canExportRendezVousDescription;
	}
	public static boolean canReadTransfertReference() {
		ensureLoaded();
		return canReadTransfertReferenceInformationsDepart
				|| canReadTransfertReferenceInformationArrivee;
	}

	public static boolean canEditTransfertReference() {
		ensureLoaded();
		return canWriteTransfertReferenceInformationsDepart
				|| canWriteTransfertReferenceInformationArrivee;
	}

	public static boolean canCreateTransfertReference() {
		ensureLoaded();
		return canCreateTransfertReference;
	}

	public static boolean canDeleteTransfertReference() {
		ensureLoaded();
		return canDeleteTransfertReference;
	}

	public static boolean canDirectAccessTransfertReference() {
		ensureLoaded();
		return canDirectAccessTransfertReference;
	}

	public static boolean canExportTransfertReference() {
		ensureLoaded();
		return canExportTransfertReference;
	}

	public static boolean canReadTransfertReferenceInformationsDepart() {
		ensureLoaded();
		return canReadTransfertReferenceInformationsDepart;
	}

	public static boolean canEditTransfertReferenceInformationsDepart() {
		ensureLoaded();
		return canWriteTransfertReferenceInformationsDepart;
	}

	public static boolean canExportTransfertReferenceInformationsDepart() {
		ensureLoaded();
		return canExportTransfertReferenceInformationsDepart;
	}
	public static boolean canReadTransfertReferenceInformationArrivee() {
		ensureLoaded();
		return canReadTransfertReferenceInformationArrivee;
	}

	public static boolean canEditTransfertReferenceInformationArrivee() {
		ensureLoaded();
		return canWriteTransfertReferenceInformationArrivee;
	}

	public static boolean canExportTransfertReferenceInformationArrivee() {
		ensureLoaded();
		return canExportTransfertReferenceInformationArrivee;
	}
	public static boolean canReadLot() {
		ensureLoaded();
		return canReadLotDescription;
	}

	public static boolean canEditLot() {
		ensureLoaded();
		return canWriteLotDescription;
	}

	public static boolean canCreateLot() {
		ensureLoaded();
		return canCreateLot;
	}

	public static boolean canDeleteLot() {
		ensureLoaded();
		return canDeleteLot;
	}

	public static boolean canDirectAccessLot() {
		ensureLoaded();
		return canDirectAccessLot;
	}

	public static boolean canExportLot() {
		ensureLoaded();
		return canExportLot;
	}

	public static boolean canReadLotDescription() {
		ensureLoaded();
		return canReadLotDescription;
	}

	public static boolean canEditLotDescription() {
		ensureLoaded();
		return canWriteLotDescription;
	}

	public static boolean canExportLotDescription() {
		ensureLoaded();
		return canExportLotDescription;
	}
	public static boolean canReadHorsUsage() {
		ensureLoaded();
		return canReadHorsUsageDescription;
	}

	public static boolean canEditHorsUsage() {
		ensureLoaded();
		return canWriteHorsUsageDescription;
	}

	public static boolean canCreateHorsUsage() {
		ensureLoaded();
		return canCreateHorsUsage;
	}

	public static boolean canDeleteHorsUsage() {
		ensureLoaded();
		return canDeleteHorsUsage;
	}

	public static boolean canDirectAccessHorsUsage() {
		ensureLoaded();
		return canDirectAccessHorsUsage;
	}

	public static boolean canExportHorsUsage() {
		ensureLoaded();
		return canExportHorsUsage;
	}

	public static boolean canReadHorsUsageDescription() {
		ensureLoaded();
		return canReadHorsUsageDescription;
	}

	public static boolean canEditHorsUsageDescription() {
		ensureLoaded();
		return canWriteHorsUsageDescription;
	}

	public static boolean canExportHorsUsageDescription() {
		ensureLoaded();
		return canExportHorsUsageDescription;
	}
	public static boolean canReadEntreeLot() {
		ensureLoaded();
		return canReadEntreeLotDescription;
	}

	public static boolean canEditEntreeLot() {
		ensureLoaded();
		return canWriteEntreeLotDescription;
	}

	public static boolean canCreateEntreeLot() {
		ensureLoaded();
		return canCreateEntreeLot;
	}

	public static boolean canDeleteEntreeLot() {
		ensureLoaded();
		return canDeleteEntreeLot;
	}

	public static boolean canDirectAccessEntreeLot() {
		ensureLoaded();
		return canDirectAccessEntreeLot;
	}

	public static boolean canExportEntreeLot() {
		ensureLoaded();
		return canExportEntreeLot;
	}

	public static boolean canReadEntreeLotDescription() {
		ensureLoaded();
		return canReadEntreeLotDescription;
	}

	public static boolean canEditEntreeLotDescription() {
		ensureLoaded();
		return canWriteEntreeLotDescription;
	}

	public static boolean canExportEntreeLotDescription() {
		ensureLoaded();
		return canExportEntreeLotDescription;
	}
	public static boolean canReadSortieLot() {
		ensureLoaded();
		return canReadSortieLotDescription;
	}

	public static boolean canEditSortieLot() {
		ensureLoaded();
		return canWriteSortieLotDescription;
	}

	public static boolean canCreateSortieLot() {
		ensureLoaded();
		return canCreateSortieLot;
	}

	public static boolean canDeleteSortieLot() {
		ensureLoaded();
		return canDeleteSortieLot;
	}

	public static boolean canDirectAccessSortieLot() {
		ensureLoaded();
		return canDirectAccessSortieLot;
	}

	public static boolean canExportSortieLot() {
		ensureLoaded();
		return canExportSortieLot;
	}

	public static boolean canReadSortieLotDescription() {
		ensureLoaded();
		return canReadSortieLotDescription;
	}

	public static boolean canEditSortieLotDescription() {
		ensureLoaded();
		return canWriteSortieLotDescription;
	}

	public static boolean canExportSortieLotDescription() {
		ensureLoaded();
		return canExportSortieLotDescription;
	}
	public static boolean canReadCommande() {
		ensureLoaded();
		return canReadCommandeInformationsDepart
				|| canReadCommandeRegionApprobation
				|| canReadCommandeGtcApprobation;
	}

	public static boolean canEditCommande() {
		ensureLoaded();
		return canWriteCommandeInformationsDepart
				|| canWriteCommandeRegionApprobation
				|| canWriteCommandeGtcApprobation;
	}

	public static boolean canCreateCommande() {
		ensureLoaded();
		return canCreateCommande;
	}

	public static boolean canDeleteCommande() {
		ensureLoaded();
		return canDeleteCommande;
	}

	public static boolean canDirectAccessCommande() {
		ensureLoaded();
		return canDirectAccessCommande;
	}

	public static boolean canExportCommande() {
		ensureLoaded();
		return canExportCommande;
	}

	public static boolean canReadCommandeInformationsDepart() {
		ensureLoaded();
		return canReadCommandeInformationsDepart;
	}

	public static boolean canEditCommandeInformationsDepart() {
		ensureLoaded();
		return canWriteCommandeInformationsDepart;
	}

	public static boolean canExportCommandeInformationsDepart() {
		ensureLoaded();
		return canExportCommandeInformationsDepart;
	}
	public static boolean canReadCommandeRegionApprobation() {
		ensureLoaded();
		return canReadCommandeRegionApprobation;
	}

	public static boolean canEditCommandeRegionApprobation() {
		ensureLoaded();
		return canWriteCommandeRegionApprobation;
	}

	public static boolean canExportCommandeRegionApprobation() {
		ensureLoaded();
		return canExportCommandeRegionApprobation;
	}
	public static boolean canReadCommandeGtcApprobation() {
		ensureLoaded();
		return canReadCommandeGtcApprobation;
	}

	public static boolean canEditCommandeGtcApprobation() {
		ensureLoaded();
		return canWriteCommandeGtcApprobation;
	}

	public static boolean canExportCommandeGtcApprobation() {
		ensureLoaded();
		return canExportCommandeGtcApprobation;
	}
	public static boolean canReadDetailCommandeMedicament() {
		ensureLoaded();
		return canReadDetailCommandeMedicamentDescription;
	}

	public static boolean canEditDetailCommandeMedicament() {
		ensureLoaded();
		return canWriteDetailCommandeMedicamentDescription;
	}

	public static boolean canCreateDetailCommandeMedicament() {
		ensureLoaded();
		return canCreateDetailCommandeMedicament;
	}

	public static boolean canDeleteDetailCommandeMedicament() {
		ensureLoaded();
		return canDeleteDetailCommandeMedicament;
	}

	public static boolean canDirectAccessDetailCommandeMedicament() {
		ensureLoaded();
		return canDirectAccessDetailCommandeMedicament;
	}

	public static boolean canExportDetailCommandeMedicament() {
		ensureLoaded();
		return canExportDetailCommandeMedicament;
	}

	public static boolean canReadDetailCommandeMedicamentDescription() {
		ensureLoaded();
		return canReadDetailCommandeMedicamentDescription;
	}

	public static boolean canEditDetailCommandeMedicamentDescription() {
		ensureLoaded();
		return canWriteDetailCommandeMedicamentDescription;
	}

	public static boolean canExportDetailCommandeMedicamentDescription() {
		ensureLoaded();
		return canExportDetailCommandeMedicamentDescription;
	}
	public static boolean canReadDetailCommandeIntrant() {
		ensureLoaded();
		return canReadDetailCommandeIntrantDescription;
	}

	public static boolean canEditDetailCommandeIntrant() {
		ensureLoaded();
		return canWriteDetailCommandeIntrantDescription;
	}

	public static boolean canCreateDetailCommandeIntrant() {
		ensureLoaded();
		return canCreateDetailCommandeIntrant;
	}

	public static boolean canDeleteDetailCommandeIntrant() {
		ensureLoaded();
		return canDeleteDetailCommandeIntrant;
	}

	public static boolean canDirectAccessDetailCommandeIntrant() {
		ensureLoaded();
		return canDirectAccessDetailCommandeIntrant;
	}

	public static boolean canExportDetailCommandeIntrant() {
		ensureLoaded();
		return canExportDetailCommandeIntrant;
	}

	public static boolean canReadDetailCommandeIntrantDescription() {
		ensureLoaded();
		return canReadDetailCommandeIntrantDescription;
	}

	public static boolean canEditDetailCommandeIntrantDescription() {
		ensureLoaded();
		return canWriteDetailCommandeIntrantDescription;
	}

	public static boolean canExportDetailCommandeIntrantDescription() {
		ensureLoaded();
		return canExportDetailCommandeIntrantDescription;
	}
	public static boolean canReadReception() {
		ensureLoaded();
		return canReadReceptionDescription;
	}

	public static boolean canEditReception() {
		ensureLoaded();
		return canWriteReceptionDescription;
	}

	public static boolean canCreateReception() {
		ensureLoaded();
		return canCreateReception;
	}

	public static boolean canDeleteReception() {
		ensureLoaded();
		return canDeleteReception;
	}

	public static boolean canDirectAccessReception() {
		ensureLoaded();
		return canDirectAccessReception;
	}

	public static boolean canExportReception() {
		ensureLoaded();
		return canExportReception;
	}

	public static boolean canReadReceptionDescription() {
		ensureLoaded();
		return canReadReceptionDescription;
	}

	public static boolean canEditReceptionDescription() {
		ensureLoaded();
		return canWriteReceptionDescription;
	}

	public static boolean canExportReceptionDescription() {
		ensureLoaded();
		return canExportReceptionDescription;
	}
	public static boolean canReadDetailReceptionMedicament() {
		ensureLoaded();
		return canReadDetailReceptionMedicamentDescription;
	}

	public static boolean canEditDetailReceptionMedicament() {
		ensureLoaded();
		return canWriteDetailReceptionMedicamentDescription;
	}

	public static boolean canCreateDetailReceptionMedicament() {
		ensureLoaded();
		return canCreateDetailReceptionMedicament;
	}

	public static boolean canDeleteDetailReceptionMedicament() {
		ensureLoaded();
		return canDeleteDetailReceptionMedicament;
	}

	public static boolean canDirectAccessDetailReceptionMedicament() {
		ensureLoaded();
		return canDirectAccessDetailReceptionMedicament;
	}

	public static boolean canExportDetailReceptionMedicament() {
		ensureLoaded();
		return canExportDetailReceptionMedicament;
	}

	public static boolean canReadDetailReceptionMedicamentDescription() {
		ensureLoaded();
		return canReadDetailReceptionMedicamentDescription;
	}

	public static boolean canEditDetailReceptionMedicamentDescription() {
		ensureLoaded();
		return canWriteDetailReceptionMedicamentDescription;
	}

	public static boolean canExportDetailReceptionMedicamentDescription() {
		ensureLoaded();
		return canExportDetailReceptionMedicamentDescription;
	}
	public static boolean canReadDetailReceptionIntrant() {
		ensureLoaded();
		return canReadDetailReceptionIntrantDescription;
	}

	public static boolean canEditDetailReceptionIntrant() {
		ensureLoaded();
		return canWriteDetailReceptionIntrantDescription;
	}

	public static boolean canCreateDetailReceptionIntrant() {
		ensureLoaded();
		return canCreateDetailReceptionIntrant;
	}

	public static boolean canDeleteDetailReceptionIntrant() {
		ensureLoaded();
		return canDeleteDetailReceptionIntrant;
	}

	public static boolean canDirectAccessDetailReceptionIntrant() {
		ensureLoaded();
		return canDirectAccessDetailReceptionIntrant;
	}

	public static boolean canExportDetailReceptionIntrant() {
		ensureLoaded();
		return canExportDetailReceptionIntrant;
	}

	public static boolean canReadDetailReceptionIntrantDescription() {
		ensureLoaded();
		return canReadDetailReceptionIntrantDescription;
	}

	public static boolean canEditDetailReceptionIntrantDescription() {
		ensureLoaded();
		return canWriteDetailReceptionIntrantDescription;
	}

	public static boolean canExportDetailReceptionIntrantDescription() {
		ensureLoaded();
		return canExportDetailReceptionIntrantDescription;
	}
	public static boolean canReadRavitaillement() {
		ensureLoaded();
		return canReadRavitaillementInformationsDepart
				|| canReadRavitaillementInformationArrivee
				|| canReadRavitaillementDetail;
	}

	public static boolean canEditRavitaillement() {
		ensureLoaded();
		return canWriteRavitaillementInformationsDepart
				|| canWriteRavitaillementInformationArrivee
				|| canWriteRavitaillementDetail;
	}

	public static boolean canCreateRavitaillement() {
		ensureLoaded();
		return canCreateRavitaillement;
	}

	public static boolean canDeleteRavitaillement() {
		ensureLoaded();
		return canDeleteRavitaillement;
	}

	public static boolean canDirectAccessRavitaillement() {
		ensureLoaded();
		return canDirectAccessRavitaillement;
	}

	public static boolean canExportRavitaillement() {
		ensureLoaded();
		return canExportRavitaillement;
	}

	public static boolean canReadRavitaillementInformationsDepart() {
		ensureLoaded();
		return canReadRavitaillementInformationsDepart;
	}

	public static boolean canEditRavitaillementInformationsDepart() {
		ensureLoaded();
		return canWriteRavitaillementInformationsDepart;
	}

	public static boolean canExportRavitaillementInformationsDepart() {
		ensureLoaded();
		return canExportRavitaillementInformationsDepart;
	}
	public static boolean canReadRavitaillementInformationArrivee() {
		ensureLoaded();
		return canReadRavitaillementInformationArrivee;
	}

	public static boolean canEditRavitaillementInformationArrivee() {
		ensureLoaded();
		return canWriteRavitaillementInformationArrivee;
	}

	public static boolean canExportRavitaillementInformationArrivee() {
		ensureLoaded();
		return canExportRavitaillementInformationArrivee;
	}
	public static boolean canReadRavitaillementDetail() {
		ensureLoaded();
		return canReadRavitaillementDetail;
	}

	public static boolean canEditRavitaillementDetail() {
		ensureLoaded();
		return canWriteRavitaillementDetail;
	}

	public static boolean canExportRavitaillementDetail() {
		ensureLoaded();
		return canExportRavitaillementDetail;
	}
	public static boolean canReadDetailRavitaillement() {
		ensureLoaded();
		return canReadDetailRavitaillementDescription
				|| canReadDetailRavitaillementSortie
				|| canReadDetailRavitaillementEntree;
	}

	public static boolean canEditDetailRavitaillement() {
		ensureLoaded();
		return canWriteDetailRavitaillementDescription
				|| canWriteDetailRavitaillementSortie
				|| canWriteDetailRavitaillementEntree;
	}

	public static boolean canCreateDetailRavitaillement() {
		ensureLoaded();
		return canCreateDetailRavitaillement;
	}

	public static boolean canDeleteDetailRavitaillement() {
		ensureLoaded();
		return canDeleteDetailRavitaillement;
	}

	public static boolean canDirectAccessDetailRavitaillement() {
		ensureLoaded();
		return canDirectAccessDetailRavitaillement;
	}

	public static boolean canExportDetailRavitaillement() {
		ensureLoaded();
		return canExportDetailRavitaillement;
	}

	public static boolean canReadDetailRavitaillementDescription() {
		ensureLoaded();
		return canReadDetailRavitaillementDescription;
	}

	public static boolean canEditDetailRavitaillementDescription() {
		ensureLoaded();
		return canWriteDetailRavitaillementDescription;
	}

	public static boolean canExportDetailRavitaillementDescription() {
		ensureLoaded();
		return canExportDetailRavitaillementDescription;
	}
	public static boolean canReadDetailRavitaillementSortie() {
		ensureLoaded();
		return canReadDetailRavitaillementSortie;
	}

	public static boolean canEditDetailRavitaillementSortie() {
		ensureLoaded();
		return canWriteDetailRavitaillementSortie;
	}

	public static boolean canExportDetailRavitaillementSortie() {
		ensureLoaded();
		return canExportDetailRavitaillementSortie;
	}
	public static boolean canReadDetailRavitaillementEntree() {
		ensureLoaded();
		return canReadDetailRavitaillementEntree;
	}

	public static boolean canEditDetailRavitaillementEntree() {
		ensureLoaded();
		return canWriteDetailRavitaillementEntree;
	}

	public static boolean canExportDetailRavitaillementEntree() {
		ensureLoaded();
		return canExportDetailRavitaillementEntree;
	}
	public static boolean canReadInventaire() {
		ensureLoaded();
		return canReadInventaireInformationsDepart;
	}

	public static boolean canEditInventaire() {
		ensureLoaded();
		return canWriteInventaireInformationsDepart;
	}

	public static boolean canCreateInventaire() {
		ensureLoaded();
		return canCreateInventaire;
	}

	public static boolean canDeleteInventaire() {
		ensureLoaded();
		return canDeleteInventaire;
	}

	public static boolean canDirectAccessInventaire() {
		ensureLoaded();
		return canDirectAccessInventaire;
	}

	public static boolean canExportInventaire() {
		ensureLoaded();
		return canExportInventaire;
	}

	public static boolean canReadInventaireInformationsDepart() {
		ensureLoaded();
		return canReadInventaireInformationsDepart;
	}

	public static boolean canEditInventaireInformationsDepart() {
		ensureLoaded();
		return canWriteInventaireInformationsDepart;
	}

	public static boolean canExportInventaireInformationsDepart() {
		ensureLoaded();
		return canExportInventaireInformationsDepart;
	}
	public static boolean canReadDetailInventaire() {
		ensureLoaded();
		return canReadDetailInventaireDescription;
	}

	public static boolean canEditDetailInventaire() {
		ensureLoaded();
		return canWriteDetailInventaireDescription;
	}

	public static boolean canCreateDetailInventaire() {
		ensureLoaded();
		return canCreateDetailInventaire;
	}

	public static boolean canDeleteDetailInventaire() {
		ensureLoaded();
		return canDeleteDetailInventaire;
	}

	public static boolean canDirectAccessDetailInventaire() {
		ensureLoaded();
		return canDirectAccessDetailInventaire;
	}

	public static boolean canExportDetailInventaire() {
		ensureLoaded();
		return canExportDetailInventaire;
	}

	public static boolean canReadDetailInventaireDescription() {
		ensureLoaded();
		return canReadDetailInventaireDescription;
	}

	public static boolean canEditDetailInventaireDescription() {
		ensureLoaded();
		return canWriteDetailInventaireDescription;
	}

	public static boolean canExportDetailInventaireDescription() {
		ensureLoaded();
		return canExportDetailInventaireDescription;
	}
	public static boolean canReadPersonnel() {
		ensureLoaded();
		return canReadPersonnelIdentification || canReadPersonnelContact
				|| canReadPersonnelSituation || canReadPersonnelNiveauAccess;
	}

	public static boolean canEditPersonnel() {
		ensureLoaded();
		return canWritePersonnelIdentification || canWritePersonnelContact
				|| canWritePersonnelSituation || canWritePersonnelNiveauAccess;
	}

	public static boolean canCreatePersonnel() {
		ensureLoaded();
		return canCreatePersonnel;
	}

	public static boolean canDeletePersonnel() {
		ensureLoaded();
		return canDeletePersonnel;
	}

	public static boolean canDirectAccessPersonnel() {
		ensureLoaded();
		return canDirectAccessPersonnel;
	}

	public static boolean canExportPersonnel() {
		ensureLoaded();
		return canExportPersonnel;
	}

	public static boolean canReadPersonnelIdentification() {
		ensureLoaded();
		return canReadPersonnelIdentification;
	}

	public static boolean canEditPersonnelIdentification() {
		ensureLoaded();
		return canWritePersonnelIdentification;
	}

	public static boolean canExportPersonnelIdentification() {
		ensureLoaded();
		return canExportPersonnelIdentification;
	}
	public static boolean canReadPersonnelContact() {
		ensureLoaded();
		return canReadPersonnelContact;
	}

	public static boolean canEditPersonnelContact() {
		ensureLoaded();
		return canWritePersonnelContact;
	}

	public static boolean canExportPersonnelContact() {
		ensureLoaded();
		return canExportPersonnelContact;
	}
	public static boolean canReadPersonnelSituation() {
		ensureLoaded();
		return canReadPersonnelSituation;
	}

	public static boolean canEditPersonnelSituation() {
		ensureLoaded();
		return canWritePersonnelSituation;
	}

	public static boolean canExportPersonnelSituation() {
		ensureLoaded();
		return canExportPersonnelSituation;
	}
	public static boolean canReadPersonnelNiveauAccess() {
		ensureLoaded();
		return canReadPersonnelNiveauAccess;
	}

	public static boolean canEditPersonnelNiveauAccess() {
		ensureLoaded();
		return canWritePersonnelNiveauAccess;
	}

	public static boolean canExportPersonnelNiveauAccess() {
		ensureLoaded();
		return canExportPersonnelNiveauAccess;
	}
	public static boolean canReadDepartPersonnel() {
		ensureLoaded();
		return canReadDepartPersonnelPersonnel
				|| canReadDepartPersonnelDescription;
	}

	public static boolean canEditDepartPersonnel() {
		ensureLoaded();
		return canWriteDepartPersonnelPersonnel
				|| canWriteDepartPersonnelDescription;
	}

	public static boolean canCreateDepartPersonnel() {
		ensureLoaded();
		return canCreateDepartPersonnel;
	}

	public static boolean canDeleteDepartPersonnel() {
		ensureLoaded();
		return canDeleteDepartPersonnel;
	}

	public static boolean canDirectAccessDepartPersonnel() {
		ensureLoaded();
		return canDirectAccessDepartPersonnel;
	}

	public static boolean canExportDepartPersonnel() {
		ensureLoaded();
		return canExportDepartPersonnel;
	}

	public static boolean canReadDepartPersonnelPersonnel() {
		ensureLoaded();
		return canReadDepartPersonnelPersonnel;
	}

	public static boolean canEditDepartPersonnelPersonnel() {
		ensureLoaded();
		return canWriteDepartPersonnelPersonnel;
	}

	public static boolean canExportDepartPersonnelPersonnel() {
		ensureLoaded();
		return canExportDepartPersonnelPersonnel;
	}
	public static boolean canReadDepartPersonnelDescription() {
		ensureLoaded();
		return canReadDepartPersonnelDescription;
	}

	public static boolean canEditDepartPersonnelDescription() {
		ensureLoaded();
		return canWriteDepartPersonnelDescription;
	}

	public static boolean canExportDepartPersonnelDescription() {
		ensureLoaded();
		return canExportDepartPersonnelDescription;
	}
	public static boolean canReadArriveePersonnel() {
		ensureLoaded();
		return canReadArriveePersonnelDescription;
	}

	public static boolean canEditArriveePersonnel() {
		ensureLoaded();
		return canWriteArriveePersonnelDescription;
	}

	public static boolean canCreateArriveePersonnel() {
		ensureLoaded();
		return canCreateArriveePersonnel;
	}

	public static boolean canDeleteArriveePersonnel() {
		ensureLoaded();
		return canDeleteArriveePersonnel;
	}

	public static boolean canDirectAccessArriveePersonnel() {
		ensureLoaded();
		return canDirectAccessArriveePersonnel;
	}

	public static boolean canExportArriveePersonnel() {
		ensureLoaded();
		return canExportArriveePersonnel;
	}

	public static boolean canReadArriveePersonnelDescription() {
		ensureLoaded();
		return canReadArriveePersonnelDescription;
	}

	public static boolean canEditArriveePersonnelDescription() {
		ensureLoaded();
		return canWriteArriveePersonnelDescription;
	}

	public static boolean canExportArriveePersonnelDescription() {
		ensureLoaded();
		return canExportArriveePersonnelDescription;
	}
	public static boolean canReadRegion() {
		ensureLoaded();
		return canReadRegionDescription || canReadRegionAdresse;
	}

	public static boolean canEditRegion() {
		ensureLoaded();
		return canWriteRegionDescription || canWriteRegionAdresse;
	}

	public static boolean canCreateRegion() {
		ensureLoaded();
		return canCreateRegion;
	}

	public static boolean canDeleteRegion() {
		ensureLoaded();
		return canDeleteRegion;
	}

	public static boolean canDirectAccessRegion() {
		ensureLoaded();
		return canDirectAccessRegion;
	}

	public static boolean canExportRegion() {
		ensureLoaded();
		return canExportRegion;
	}

	public static boolean canReadRegionDescription() {
		ensureLoaded();
		return canReadRegionDescription;
	}

	public static boolean canEditRegionDescription() {
		ensureLoaded();
		return canWriteRegionDescription;
	}

	public static boolean canExportRegionDescription() {
		ensureLoaded();
		return canExportRegionDescription;
	}
	public static boolean canReadRegionAdresse() {
		ensureLoaded();
		return canReadRegionAdresse;
	}

	public static boolean canEditRegionAdresse() {
		ensureLoaded();
		return canWriteRegionAdresse;
	}

	public static boolean canExportRegionAdresse() {
		ensureLoaded();
		return canExportRegionAdresse;
	}
	public static boolean canReadDistrictSante() {
		ensureLoaded();
		return canReadDistrictSanteDescription || canReadDistrictSanteAdresse;
	}

	public static boolean canEditDistrictSante() {
		ensureLoaded();
		return canWriteDistrictSanteDescription || canWriteDistrictSanteAdresse;
	}

	public static boolean canCreateDistrictSante() {
		ensureLoaded();
		return canCreateDistrictSante;
	}

	public static boolean canDeleteDistrictSante() {
		ensureLoaded();
		return canDeleteDistrictSante;
	}

	public static boolean canDirectAccessDistrictSante() {
		ensureLoaded();
		return canDirectAccessDistrictSante;
	}

	public static boolean canExportDistrictSante() {
		ensureLoaded();
		return canExportDistrictSante;
	}

	public static boolean canReadDistrictSanteDescription() {
		ensureLoaded();
		return canReadDistrictSanteDescription;
	}

	public static boolean canEditDistrictSanteDescription() {
		ensureLoaded();
		return canWriteDistrictSanteDescription;
	}

	public static boolean canExportDistrictSanteDescription() {
		ensureLoaded();
		return canExportDistrictSanteDescription;
	}
	public static boolean canReadDistrictSanteAdresse() {
		ensureLoaded();
		return canReadDistrictSanteAdresse;
	}

	public static boolean canEditDistrictSanteAdresse() {
		ensureLoaded();
		return canWriteDistrictSanteAdresse;
	}

	public static boolean canExportDistrictSanteAdresse() {
		ensureLoaded();
		return canExportDistrictSanteAdresse;
	}
	public static boolean canReadCentreDiagTrait() {
		ensureLoaded();
		return canReadCentreDiagTraitDescription
				|| canReadCentreDiagTraitAdresse;
	}

	public static boolean canEditCentreDiagTrait() {
		ensureLoaded();
		return canWriteCentreDiagTraitDescription
				|| canWriteCentreDiagTraitAdresse;
	}

	public static boolean canCreateCentreDiagTrait() {
		ensureLoaded();
		return canCreateCentreDiagTrait;
	}

	public static boolean canDeleteCentreDiagTrait() {
		ensureLoaded();
		return canDeleteCentreDiagTrait;
	}

	public static boolean canDirectAccessCentreDiagTrait() {
		ensureLoaded();
		return canDirectAccessCentreDiagTrait;
	}

	public static boolean canExportCentreDiagTrait() {
		ensureLoaded();
		return canExportCentreDiagTrait;
	}

	public static boolean canReadCentreDiagTraitDescription() {
		ensureLoaded();
		return canReadCentreDiagTraitDescription;
	}

	public static boolean canEditCentreDiagTraitDescription() {
		ensureLoaded();
		return canWriteCentreDiagTraitDescription;
	}

	public static boolean canExportCentreDiagTraitDescription() {
		ensureLoaded();
		return canExportCentreDiagTraitDescription;
	}
	public static boolean canReadCentreDiagTraitAdresse() {
		ensureLoaded();
		return canReadCentreDiagTraitAdresse;
	}

	public static boolean canEditCentreDiagTraitAdresse() {
		ensureLoaded();
		return canWriteCentreDiagTraitAdresse;
	}

	public static boolean canExportCentreDiagTraitAdresse() {
		ensureLoaded();
		return canExportCentreDiagTraitAdresse;
	}
	public static boolean canReadLaboratoireReference() {
		ensureLoaded();
		return canReadLaboratoireReferenceDescription
				|| canReadLaboratoireReferenceAdresse;
	}

	public static boolean canEditLaboratoireReference() {
		ensureLoaded();
		return canWriteLaboratoireReferenceDescription
				|| canWriteLaboratoireReferenceAdresse;
	}

	public static boolean canCreateLaboratoireReference() {
		ensureLoaded();
		return canCreateLaboratoireReference;
	}

	public static boolean canDeleteLaboratoireReference() {
		ensureLoaded();
		return canDeleteLaboratoireReference;
	}

	public static boolean canDirectAccessLaboratoireReference() {
		ensureLoaded();
		return canDirectAccessLaboratoireReference;
	}

	public static boolean canExportLaboratoireReference() {
		ensureLoaded();
		return canExportLaboratoireReference;
	}

	public static boolean canReadLaboratoireReferenceDescription() {
		ensureLoaded();
		return canReadLaboratoireReferenceDescription;
	}

	public static boolean canEditLaboratoireReferenceDescription() {
		ensureLoaded();
		return canWriteLaboratoireReferenceDescription;
	}

	public static boolean canExportLaboratoireReferenceDescription() {
		ensureLoaded();
		return canExportLaboratoireReferenceDescription;
	}
	public static boolean canReadLaboratoireReferenceAdresse() {
		ensureLoaded();
		return canReadLaboratoireReferenceAdresse;
	}

	public static boolean canEditLaboratoireReferenceAdresse() {
		ensureLoaded();
		return canWriteLaboratoireReferenceAdresse;
	}

	public static boolean canExportLaboratoireReferenceAdresse() {
		ensureLoaded();
		return canExportLaboratoireReferenceAdresse;
	}
	public static boolean canReadLieuDit() {
		ensureLoaded();
		return canReadLieuDitDescription || canReadLieuDitAdresse;
	}

	public static boolean canEditLieuDit() {
		ensureLoaded();
		return canWriteLieuDitDescription || canWriteLieuDitAdresse;
	}

	public static boolean canCreateLieuDit() {
		ensureLoaded();
		return canCreateLieuDit;
	}

	public static boolean canDeleteLieuDit() {
		ensureLoaded();
		return canDeleteLieuDit;
	}

	public static boolean canDirectAccessLieuDit() {
		ensureLoaded();
		return canDirectAccessLieuDit;
	}

	public static boolean canExportLieuDit() {
		ensureLoaded();
		return canExportLieuDit;
	}

	public static boolean canReadLieuDitDescription() {
		ensureLoaded();
		return canReadLieuDitDescription;
	}

	public static boolean canEditLieuDitDescription() {
		ensureLoaded();
		return canWriteLieuDitDescription;
	}

	public static boolean canExportLieuDitDescription() {
		ensureLoaded();
		return canExportLieuDitDescription;
	}
	public static boolean canReadLieuDitAdresse() {
		ensureLoaded();
		return canReadLieuDitAdresse;
	}

	public static boolean canEditLieuDitAdresse() {
		ensureLoaded();
		return canWriteLieuDitAdresse;
	}

	public static boolean canExportLieuDitAdresse() {
		ensureLoaded();
		return canExportLieuDitAdresse;
	}
	public static boolean canReadRegime() {
		ensureLoaded();
		return canReadRegimeDescription;
	}

	public static boolean canEditRegime() {
		ensureLoaded();
		return canWriteRegimeDescription;
	}

	public static boolean canCreateRegime() {
		ensureLoaded();
		return canCreateRegime;
	}

	public static boolean canDeleteRegime() {
		ensureLoaded();
		return canDeleteRegime;
	}

	public static boolean canDirectAccessRegime() {
		ensureLoaded();
		return canDirectAccessRegime;
	}

	public static boolean canExportRegime() {
		ensureLoaded();
		return canExportRegime;
	}

	public static boolean canReadRegimeDescription() {
		ensureLoaded();
		return canReadRegimeDescription;
	}

	public static boolean canEditRegimeDescription() {
		ensureLoaded();
		return canWriteRegimeDescription;
	}

	public static boolean canExportRegimeDescription() {
		ensureLoaded();
		return canExportRegimeDescription;
	}
	public static boolean canReadPriseMedicamentRegime() {
		ensureLoaded();
		return canReadPriseMedicamentRegimeDescription;
	}

	public static boolean canEditPriseMedicamentRegime() {
		ensureLoaded();
		return canWritePriseMedicamentRegimeDescription;
	}

	public static boolean canCreatePriseMedicamentRegime() {
		ensureLoaded();
		return canCreatePriseMedicamentRegime;
	}

	public static boolean canDeletePriseMedicamentRegime() {
		ensureLoaded();
		return canDeletePriseMedicamentRegime;
	}

	public static boolean canDirectAccessPriseMedicamentRegime() {
		ensureLoaded();
		return canDirectAccessPriseMedicamentRegime;
	}

	public static boolean canExportPriseMedicamentRegime() {
		ensureLoaded();
		return canExportPriseMedicamentRegime;
	}

	public static boolean canReadPriseMedicamentRegimeDescription() {
		ensureLoaded();
		return canReadPriseMedicamentRegimeDescription;
	}

	public static boolean canEditPriseMedicamentRegimeDescription() {
		ensureLoaded();
		return canWritePriseMedicamentRegimeDescription;
	}

	public static boolean canExportPriseMedicamentRegimeDescription() {
		ensureLoaded();
		return canExportPriseMedicamentRegimeDescription;
	}
	public static boolean canReadMedicament() {
		ensureLoaded();
		return canReadMedicamentDescription;
	}

	public static boolean canEditMedicament() {
		ensureLoaded();
		return canWriteMedicamentDescription;
	}

	public static boolean canCreateMedicament() {
		ensureLoaded();
		return canCreateMedicament;
	}

	public static boolean canDeleteMedicament() {
		ensureLoaded();
		return canDeleteMedicament;
	}

	public static boolean canDirectAccessMedicament() {
		ensureLoaded();
		return canDirectAccessMedicament;
	}

	public static boolean canExportMedicament() {
		ensureLoaded();
		return canExportMedicament;
	}

	public static boolean canReadMedicamentDescription() {
		ensureLoaded();
		return canReadMedicamentDescription;
	}

	public static boolean canEditMedicamentDescription() {
		ensureLoaded();
		return canWriteMedicamentDescription;
	}

	public static boolean canExportMedicamentDescription() {
		ensureLoaded();
		return canExportMedicamentDescription;
	}
	public static boolean canReadIntrant() {
		ensureLoaded();
		return canReadIntrantDescription;
	}

	public static boolean canEditIntrant() {
		ensureLoaded();
		return canWriteIntrantDescription;
	}

	public static boolean canCreateIntrant() {
		ensureLoaded();
		return canCreateIntrant;
	}

	public static boolean canDeleteIntrant() {
		ensureLoaded();
		return canDeleteIntrant;
	}

	public static boolean canDirectAccessIntrant() {
		ensureLoaded();
		return canDirectAccessIntrant;
	}

	public static boolean canExportIntrant() {
		ensureLoaded();
		return canExportIntrant;
	}

	public static boolean canReadIntrantDescription() {
		ensureLoaded();
		return canReadIntrantDescription;
	}

	public static boolean canEditIntrantDescription() {
		ensureLoaded();
		return canWriteIntrantDescription;
	}

	public static boolean canExportIntrantDescription() {
		ensureLoaded();
		return canExportIntrantDescription;
	}
	public static boolean canReadFormation() {
		ensureLoaded();
		return canReadFormationDescription;
	}

	public static boolean canEditFormation() {
		ensureLoaded();
		return canWriteFormationDescription;
	}

	public static boolean canCreateFormation() {
		ensureLoaded();
		return canCreateFormation;
	}

	public static boolean canDeleteFormation() {
		ensureLoaded();
		return canDeleteFormation;
	}

	public static boolean canDirectAccessFormation() {
		ensureLoaded();
		return canDirectAccessFormation;
	}

	public static boolean canExportFormation() {
		ensureLoaded();
		return canExportFormation;
	}

	public static boolean canReadFormationDescription() {
		ensureLoaded();
		return canReadFormationDescription;
	}

	public static boolean canEditFormationDescription() {
		ensureLoaded();
		return canWriteFormationDescription;
	}

	public static boolean canExportFormationDescription() {
		ensureLoaded();
		return canExportFormationDescription;
	}
	public static boolean canReadCandidatureFormation() {
		ensureLoaded();
		return canReadCandidatureFormationDescription
				|| canReadCandidatureFormationRegionApprobation
				|| canReadCandidatureFormationGtcApprobation;
	}

	public static boolean canEditCandidatureFormation() {
		ensureLoaded();
		return canWriteCandidatureFormationDescription
				|| canWriteCandidatureFormationRegionApprobation
				|| canWriteCandidatureFormationGtcApprobation;
	}

	public static boolean canCreateCandidatureFormation() {
		ensureLoaded();
		return canCreateCandidatureFormation;
	}

	public static boolean canDeleteCandidatureFormation() {
		ensureLoaded();
		return canDeleteCandidatureFormation;
	}

	public static boolean canDirectAccessCandidatureFormation() {
		ensureLoaded();
		return canDirectAccessCandidatureFormation;
	}

	public static boolean canExportCandidatureFormation() {
		ensureLoaded();
		return canExportCandidatureFormation;
	}

	public static boolean canReadCandidatureFormationDescription() {
		ensureLoaded();
		return canReadCandidatureFormationDescription;
	}

	public static boolean canEditCandidatureFormationDescription() {
		ensureLoaded();
		return canWriteCandidatureFormationDescription;
	}

	public static boolean canExportCandidatureFormationDescription() {
		ensureLoaded();
		return canExportCandidatureFormationDescription;
	}
	public static boolean canReadCandidatureFormationRegionApprobation() {
		ensureLoaded();
		return canReadCandidatureFormationRegionApprobation;
	}

	public static boolean canEditCandidatureFormationRegionApprobation() {
		ensureLoaded();
		return canWriteCandidatureFormationRegionApprobation;
	}

	public static boolean canExportCandidatureFormationRegionApprobation() {
		ensureLoaded();
		return canExportCandidatureFormationRegionApprobation;
	}
	public static boolean canReadCandidatureFormationGtcApprobation() {
		ensureLoaded();
		return canReadCandidatureFormationGtcApprobation;
	}

	public static boolean canEditCandidatureFormationGtcApprobation() {
		ensureLoaded();
		return canWriteCandidatureFormationGtcApprobation;
	}

	public static boolean canExportCandidatureFormationGtcApprobation() {
		ensureLoaded();
		return canExportCandidatureFormationGtcApprobation;
	}
	public static boolean canReadQualification() {
		ensureLoaded();
		return canReadQualificationDescription;
	}

	public static boolean canEditQualification() {
		ensureLoaded();
		return canWriteQualificationDescription;
	}

	public static boolean canCreateQualification() {
		ensureLoaded();
		return canCreateQualification;
	}

	public static boolean canDeleteQualification() {
		ensureLoaded();
		return canDeleteQualification;
	}

	public static boolean canDirectAccessQualification() {
		ensureLoaded();
		return canDirectAccessQualification;
	}

	public static boolean canExportQualification() {
		ensureLoaded();
		return canExportQualification;
	}

	public static boolean canReadQualificationDescription() {
		ensureLoaded();
		return canReadQualificationDescription;
	}

	public static boolean canEditQualificationDescription() {
		ensureLoaded();
		return canWriteQualificationDescription;
	}

	public static boolean canExportQualificationDescription() {
		ensureLoaded();
		return canExportQualificationDescription;
	}
	public static boolean canReadTutoriel() {
		ensureLoaded();
		return canReadTutorielDescription;
	}

	public static boolean canEditTutoriel() {
		ensureLoaded();
		return canWriteTutorielDescription;
	}

	public static boolean canCreateTutoriel() {
		ensureLoaded();
		return canCreateTutoriel;
	}

	public static boolean canDeleteTutoriel() {
		ensureLoaded();
		return canDeleteTutoriel;
	}

	public static boolean canDirectAccessTutoriel() {
		ensureLoaded();
		return canDirectAccessTutoriel;
	}

	public static boolean canExportTutoriel() {
		ensureLoaded();
		return canExportTutoriel;
	}

	public static boolean canReadTutorielDescription() {
		ensureLoaded();
		return canReadTutorielDescription;
	}

	public static boolean canEditTutorielDescription() {
		ensureLoaded();
		return canWriteTutorielDescription;
	}

	public static boolean canExportTutorielDescription() {
		ensureLoaded();
		return canExportTutorielDescription;
	}
	public static boolean canReadSmsPredefini() {
		ensureLoaded();
		return canReadSmsPredefiniDescription;
	}

	public static boolean canEditSmsPredefini() {
		ensureLoaded();
		return canWriteSmsPredefiniDescription;
	}

	public static boolean canCreateSmsPredefini() {
		ensureLoaded();
		return canCreateSmsPredefini;
	}

	public static boolean canDeleteSmsPredefini() {
		ensureLoaded();
		return canDeleteSmsPredefini;
	}

	public static boolean canDirectAccessSmsPredefini() {
		ensureLoaded();
		return canDirectAccessSmsPredefini;
	}

	public static boolean canExportSmsPredefini() {
		ensureLoaded();
		return canExportSmsPredefini;
	}

	public static boolean canReadSmsPredefiniDescription() {
		ensureLoaded();
		return canReadSmsPredefiniDescription;
	}

	public static boolean canEditSmsPredefiniDescription() {
		ensureLoaded();
		return canWriteSmsPredefiniDescription;
	}

	public static boolean canExportSmsPredefiniDescription() {
		ensureLoaded();
		return canExportSmsPredefiniDescription;
	}
	public static boolean canReadOutBox() {
		ensureLoaded();
		return canReadOutBoxAdresse || canReadOutBoxMessageInformation;
	}

	public static boolean canEditOutBox() {
		ensureLoaded();
		return canWriteOutBoxAdresse || canWriteOutBoxMessageInformation;
	}

	public static boolean canCreateOutBox() {
		ensureLoaded();
		return canCreateOutBox;
	}

	public static boolean canDeleteOutBox() {
		ensureLoaded();
		return canDeleteOutBox;
	}

	public static boolean canDirectAccessOutBox() {
		ensureLoaded();
		return canDirectAccessOutBox;
	}

	public static boolean canExportOutBox() {
		ensureLoaded();
		return canExportOutBox;
	}

	public static boolean canReadOutBoxAdresse() {
		ensureLoaded();
		return canReadOutBoxAdresse;
	}

	public static boolean canEditOutBoxAdresse() {
		ensureLoaded();
		return canWriteOutBoxAdresse;
	}

	public static boolean canExportOutBoxAdresse() {
		ensureLoaded();
		return canExportOutBoxAdresse;
	}
	public static boolean canReadOutBoxMessageInformation() {
		ensureLoaded();
		return canReadOutBoxMessageInformation;
	}

	public static boolean canEditOutBoxMessageInformation() {
		ensureLoaded();
		return canWriteOutBoxMessageInformation;
	}

	public static boolean canExportOutBoxMessageInformation() {
		ensureLoaded();
		return canExportOutBoxMessageInformation;
	}
	public static boolean canReadUtilisateur() {
		ensureLoaded();
		return canReadUtilisateurIdentification || canReadUtilisateurContact;
	}

	public static boolean canEditUtilisateur() {
		ensureLoaded();
		return canWriteUtilisateurIdentification || canWriteUtilisateurContact;
	}

	public static boolean canCreateUtilisateur() {
		ensureLoaded();
		return canCreateUtilisateur;
	}

	public static boolean canDeleteUtilisateur() {
		ensureLoaded();
		return canDeleteUtilisateur;
	}

	public static boolean canDirectAccessUtilisateur() {
		ensureLoaded();
		return canDirectAccessUtilisateur;
	}

	public static boolean canExportUtilisateur() {
		ensureLoaded();
		return canExportUtilisateur;
	}

	public static boolean canReadUtilisateurIdentification() {
		ensureLoaded();
		return canReadUtilisateurIdentification;
	}

	public static boolean canEditUtilisateurIdentification() {
		ensureLoaded();
		return canWriteUtilisateurIdentification;
	}

	public static boolean canExportUtilisateurIdentification() {
		ensureLoaded();
		return canExportUtilisateurIdentification;
	}
	public static boolean canReadUtilisateurContact() {
		ensureLoaded();
		return canReadUtilisateurContact;
	}

	public static boolean canEditUtilisateurContact() {
		ensureLoaded();
		return canWriteUtilisateurContact;
	}

	public static boolean canExportUtilisateurContact() {
		ensureLoaded();
		return canExportUtilisateurContact;
	}

	public static boolean canCreateEnvoiSMS() {

		return true;
	}

	public static boolean canCreateRapport() {
		return true;
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isAdmin() {
		return ProfileUtil.isAdmin();
	}

	private static void ensureLoaded() {
		if (!loaded) {
			ImogActorProxy actor = LocalSession.get().getCurrentUser();
			load(actor);
		}
	}

	private static void load(ImogActorProxy actor) {
		loaded = true;
		canCreatePatient = true;
		canDeletePatient = true;
		canDirectAccessPatient = true;
		canExportPatient = true;
		canReadPatientIdentification = true;
		canWritePatientIdentification = true;
		canExportPatientIdentification = true;
		canReadPatientContact = true;
		canWritePatientContact = true;
		canExportPatientContact = true;
		canReadPatientPersonneContact = true;
		canWritePatientPersonneContact = true;
		canExportPatientPersonneContact = true;
		canReadPatientTuberculose = true;
		canWritePatientTuberculose = true;
		canExportPatientTuberculose = true;
		canReadPatientExamens = true;
		canWritePatientExamens = true;
		canExportPatientExamens = true;
		canCreateCasIndex = true;
		canDeleteCasIndex = true;
		canDirectAccessCasIndex = true;
		canExportCasIndex = true;
		canReadCasIndexDescription = true;
		canWriteCasIndexDescription = true;
		canExportCasIndexDescription = true;
		canCreateCasTuberculose = true;
		canDeleteCasTuberculose = true;
		canDirectAccessCasTuberculose = true;
		canExportCasTuberculose = true;
		canReadCasTuberculoseInformations = true;
		canWriteCasTuberculoseInformations = true;
		canExportCasTuberculoseInformations = true;
		canReadCasTuberculoseExamen = true;
		canWriteCasTuberculoseExamen = true;
		canExportCasTuberculoseExamen = true;
		canReadCasTuberculoseTraitement = true;
		canWriteCasTuberculoseTraitement = true;
		canExportCasTuberculoseTraitement = true;
		canReadCasTuberculoseFinTraitement = true;
		canWriteCasTuberculoseFinTraitement = true;
		canExportCasTuberculoseFinTraitement = true;
		canCreateExamenSerologie = true;
		canDeleteExamenSerologie = true;
		canDirectAccessExamenSerologie = true;
		canExportExamenSerologie = true;
		canReadExamenSerologieDescription = true;
		canWriteExamenSerologieDescription = true;
		canExportExamenSerologieDescription = true;
		canCreateExamenBiologique = true;
		canDeleteExamenBiologique = true;
		canDirectAccessExamenBiologique = true;
		canExportExamenBiologique = true;
		canReadExamenBiologiqueDescription = true;
		canWriteExamenBiologiqueDescription = true;
		canExportExamenBiologiqueDescription = true;
		canCreateExamenMicroscopie = true;
		canDeleteExamenMicroscopie = true;
		canDirectAccessExamenMicroscopie = true;
		canExportExamenMicroscopie = true;
		canReadExamenMicroscopieCentreExamen = true;
		canWriteExamenMicroscopieCentreExamen = true;
		canExportExamenMicroscopieCentreExamen = true;
		canReadExamenMicroscopieExamen = true;
		canWriteExamenMicroscopieExamen = true;
		canExportExamenMicroscopieExamen = true;
		canCreateExamenATB = true;
		canDeleteExamenATB = true;
		canDirectAccessExamenATB = true;
		canExportExamenATB = true;
		canReadExamenATBCentreExamen = true;
		canWriteExamenATBCentreExamen = true;
		canExportExamenATBCentreExamen = true;
		canReadExamenATBExamen = true;
		canWriteExamenATBExamen = true;
		canExportExamenATBExamen = true;
		canCreatePriseMedicamenteuse = true;
		canDeletePriseMedicamenteuse = true;
		canDirectAccessPriseMedicamenteuse = true;
		canExportPriseMedicamenteuse = true;
		canReadPriseMedicamenteuseDescription = true;
		canWritePriseMedicamenteuseDescription = true;
		canExportPriseMedicamenteuseDescription = true;
		canCreateRendezVous = true;
		canDeleteRendezVous = true;
		canDirectAccessRendezVous = true;
		canExportRendezVous = true;
		canReadRendezVousDescription = true;
		canWriteRendezVousDescription = true;
		canExportRendezVousDescription = true;
		canCreateTransfertReference = true;
		canDeleteTransfertReference = true;
		canDirectAccessTransfertReference = true;
		canExportTransfertReference = true;
		canReadTransfertReferenceInformationsDepart = true;
		canWriteTransfertReferenceInformationsDepart = true;
		canExportTransfertReferenceInformationsDepart = true;
		canReadTransfertReferenceInformationArrivee = true;
		canWriteTransfertReferenceInformationArrivee = true;
		canExportTransfertReferenceInformationArrivee = true;
		canCreateLot = true;
		canDeleteLot = true;
		canDirectAccessLot = true;
		canExportLot = true;
		canReadLotDescription = true;
		canWriteLotDescription = true;
		canExportLotDescription = true;
		canCreateHorsUsage = true;
		canDeleteHorsUsage = true;
		canDirectAccessHorsUsage = true;
		canExportHorsUsage = true;
		canReadHorsUsageDescription = true;
		canWriteHorsUsageDescription = true;
		canExportHorsUsageDescription = true;
		canCreateEntreeLot = true;
		canDeleteEntreeLot = true;
		canDirectAccessEntreeLot = true;
		canExportEntreeLot = true;
		canReadEntreeLotDescription = true;
		canWriteEntreeLotDescription = true;
		canExportEntreeLotDescription = true;
		canCreateSortieLot = true;
		canDeleteSortieLot = true;
		canDirectAccessSortieLot = true;
		canExportSortieLot = true;
		canReadSortieLotDescription = true;
		canWriteSortieLotDescription = true;
		canExportSortieLotDescription = true;
		canCreateCommande = true;
		canDeleteCommande = true;
		canDirectAccessCommande = true;
		canExportCommande = true;
		canReadCommandeInformationsDepart = true;
		canWriteCommandeInformationsDepart = true;
		canExportCommandeInformationsDepart = true;
		canReadCommandeRegionApprobation = true;
		canWriteCommandeRegionApprobation = true;
		canExportCommandeRegionApprobation = true;
		canReadCommandeGtcApprobation = true;
		canWriteCommandeGtcApprobation = true;
		canExportCommandeGtcApprobation = true;
		canCreateDetailCommandeMedicament = true;
		canDeleteDetailCommandeMedicament = true;
		canDirectAccessDetailCommandeMedicament = true;
		canExportDetailCommandeMedicament = true;
		canReadDetailCommandeMedicamentDescription = true;
		canWriteDetailCommandeMedicamentDescription = true;
		canExportDetailCommandeMedicamentDescription = true;
		canCreateDetailCommandeIntrant = true;
		canDeleteDetailCommandeIntrant = true;
		canDirectAccessDetailCommandeIntrant = true;
		canExportDetailCommandeIntrant = true;
		canReadDetailCommandeIntrantDescription = true;
		canWriteDetailCommandeIntrantDescription = true;
		canExportDetailCommandeIntrantDescription = true;
		canCreateReception = true;
		canDeleteReception = true;
		canDirectAccessReception = true;
		canExportReception = true;
		canReadReceptionDescription = true;
		canWriteReceptionDescription = true;
		canExportReceptionDescription = true;
		canCreateDetailReceptionMedicament = true;
		canDeleteDetailReceptionMedicament = true;
		canDirectAccessDetailReceptionMedicament = true;
		canExportDetailReceptionMedicament = true;
		canReadDetailReceptionMedicamentDescription = true;
		canWriteDetailReceptionMedicamentDescription = true;
		canExportDetailReceptionMedicamentDescription = true;
		canCreateDetailReceptionIntrant = true;
		canDeleteDetailReceptionIntrant = true;
		canDirectAccessDetailReceptionIntrant = true;
		canExportDetailReceptionIntrant = true;
		canReadDetailReceptionIntrantDescription = true;
		canWriteDetailReceptionIntrantDescription = true;
		canExportDetailReceptionIntrantDescription = true;
		canCreateRavitaillement = true;
		canDeleteRavitaillement = true;
		canDirectAccessRavitaillement = true;
		canExportRavitaillement = true;
		canReadRavitaillementInformationsDepart = true;
		canWriteRavitaillementInformationsDepart = true;
		canExportRavitaillementInformationsDepart = true;
		canReadRavitaillementInformationArrivee = true;
		canWriteRavitaillementInformationArrivee = true;
		canExportRavitaillementInformationArrivee = true;
		canReadRavitaillementDetail = true;
		canWriteRavitaillementDetail = true;
		canExportRavitaillementDetail = true;
		canCreateDetailRavitaillement = true;
		canDeleteDetailRavitaillement = true;
		canDirectAccessDetailRavitaillement = true;
		canExportDetailRavitaillement = true;
		canReadDetailRavitaillementDescription = true;
		canWriteDetailRavitaillementDescription = true;
		canExportDetailRavitaillementDescription = true;
		canReadDetailRavitaillementSortie = true;
		canWriteDetailRavitaillementSortie = true;
		canExportDetailRavitaillementSortie = true;
		canReadDetailRavitaillementEntree = true;
		canWriteDetailRavitaillementEntree = true;
		canExportDetailRavitaillementEntree = true;
		canCreateInventaire = true;
		canDeleteInventaire = true;
		canDirectAccessInventaire = true;
		canExportInventaire = true;
		canReadInventaireInformationsDepart = true;
		canWriteInventaireInformationsDepart = true;
		canExportInventaireInformationsDepart = true;
		canCreateDetailInventaire = true;
		canDeleteDetailInventaire = true;
		canDirectAccessDetailInventaire = true;
		canExportDetailInventaire = true;
		canReadDetailInventaireDescription = true;
		canWriteDetailInventaireDescription = true;
		canExportDetailInventaireDescription = true;
		canCreatePersonnel = true;
		canDeletePersonnel = true;
		canDirectAccessPersonnel = true;
		canExportPersonnel = true;
		canReadPersonnelIdentification = true;
		canWritePersonnelIdentification = true;
		canExportPersonnelIdentification = true;
		canReadPersonnelContact = true;
		canWritePersonnelContact = true;
		canExportPersonnelContact = true;
		canReadPersonnelSituation = true;
		canWritePersonnelSituation = true;
		canExportPersonnelSituation = true;
		canReadPersonnelNiveauAccess = true;
		canWritePersonnelNiveauAccess = true;
		canExportPersonnelNiveauAccess = true;
		canCreateDepartPersonnel = true;
		canDeleteDepartPersonnel = true;
		canDirectAccessDepartPersonnel = true;
		canExportDepartPersonnel = true;
		canReadDepartPersonnelPersonnel = true;
		canWriteDepartPersonnelPersonnel = true;
		canExportDepartPersonnelPersonnel = true;
		canReadDepartPersonnelDescription = true;
		canWriteDepartPersonnelDescription = true;
		canExportDepartPersonnelDescription = true;
		canCreateArriveePersonnel = true;
		canDeleteArriveePersonnel = true;
		canDirectAccessArriveePersonnel = true;
		canExportArriveePersonnel = true;
		canReadArriveePersonnelDescription = true;
		canWriteArriveePersonnelDescription = true;
		canExportArriveePersonnelDescription = true;
		canCreateRegion = true;
		canDeleteRegion = true;
		canDirectAccessRegion = true;
		canExportRegion = true;
		canReadRegionDescription = true;
		canWriteRegionDescription = true;
		canExportRegionDescription = true;
		canReadRegionAdresse = true;
		canWriteRegionAdresse = true;
		canExportRegionAdresse = true;
		canCreateDistrictSante = true;
		canDeleteDistrictSante = true;
		canDirectAccessDistrictSante = true;
		canExportDistrictSante = true;
		canReadDistrictSanteDescription = true;
		canWriteDistrictSanteDescription = true;
		canExportDistrictSanteDescription = true;
		canReadDistrictSanteAdresse = true;
		canWriteDistrictSanteAdresse = true;
		canExportDistrictSanteAdresse = true;
		canCreateCentreDiagTrait = true;
		canDeleteCentreDiagTrait = true;
		canDirectAccessCentreDiagTrait = true;
		canExportCentreDiagTrait = true;
		canReadCentreDiagTraitDescription = true;
		canWriteCentreDiagTraitDescription = true;
		canExportCentreDiagTraitDescription = true;
		canReadCentreDiagTraitAdresse = true;
		canWriteCentreDiagTraitAdresse = true;
		canExportCentreDiagTraitAdresse = true;
		canCreateLaboratoireReference = true;
		canDeleteLaboratoireReference = true;
		canDirectAccessLaboratoireReference = true;
		canExportLaboratoireReference = true;
		canReadLaboratoireReferenceDescription = true;
		canWriteLaboratoireReferenceDescription = true;
		canExportLaboratoireReferenceDescription = true;
		canReadLaboratoireReferenceAdresse = true;
		canWriteLaboratoireReferenceAdresse = true;
		canExportLaboratoireReferenceAdresse = true;
		canCreateLieuDit = true;
		canDeleteLieuDit = true;
		canDirectAccessLieuDit = true;
		canExportLieuDit = true;
		canReadLieuDitDescription = true;
		canWriteLieuDitDescription = true;
		canExportLieuDitDescription = true;
		canReadLieuDitAdresse = true;
		canWriteLieuDitAdresse = true;
		canExportLieuDitAdresse = true;
		canCreateRegime = true;
		canDeleteRegime = true;
		canDirectAccessRegime = true;
		canExportRegime = true;
		canReadRegimeDescription = true;
		canWriteRegimeDescription = true;
		canExportRegimeDescription = true;
		canCreatePriseMedicamentRegime = true;
		canDeletePriseMedicamentRegime = true;
		canDirectAccessPriseMedicamentRegime = true;
		canExportPriseMedicamentRegime = true;
		canReadPriseMedicamentRegimeDescription = true;
		canWritePriseMedicamentRegimeDescription = true;
		canExportPriseMedicamentRegimeDescription = true;
		canCreateMedicament = true;
		canDeleteMedicament = true;
		canDirectAccessMedicament = true;
		canExportMedicament = true;
		canReadMedicamentDescription = true;
		canWriteMedicamentDescription = true;
		canExportMedicamentDescription = true;
		canCreateIntrant = true;
		canDeleteIntrant = true;
		canDirectAccessIntrant = true;
		canExportIntrant = true;
		canReadIntrantDescription = true;
		canWriteIntrantDescription = true;
		canExportIntrantDescription = true;
		canCreateFormation = true;
		canDeleteFormation = true;
		canDirectAccessFormation = true;
		canExportFormation = true;
		canReadFormationDescription = true;
		canWriteFormationDescription = true;
		canExportFormationDescription = true;
		canCreateCandidatureFormation = true;
		canDeleteCandidatureFormation = true;
		canDirectAccessCandidatureFormation = true;
		canExportCandidatureFormation = true;
		canReadCandidatureFormationDescription = true;
		canWriteCandidatureFormationDescription = true;
		canExportCandidatureFormationDescription = true;
		canReadCandidatureFormationRegionApprobation = true;
		canWriteCandidatureFormationRegionApprobation = true;
		canExportCandidatureFormationRegionApprobation = true;
		canReadCandidatureFormationGtcApprobation = true;
		canWriteCandidatureFormationGtcApprobation = true;
		canExportCandidatureFormationGtcApprobation = true;
		canCreateQualification = true;
		canDeleteQualification = true;
		canDirectAccessQualification = true;
		canExportQualification = true;
		canReadQualificationDescription = true;
		canWriteQualificationDescription = true;
		canExportQualificationDescription = true;
		canCreateTutoriel = true;
		canDeleteTutoriel = true;
		canDirectAccessTutoriel = true;
		canExportTutoriel = true;
		canReadTutorielDescription = true;
		canWriteTutorielDescription = true;
		canExportTutorielDescription = true;
		canCreateSmsPredefini = true;
		canDeleteSmsPredefini = true;
		canDirectAccessSmsPredefini = true;
		canExportSmsPredefini = true;
		canReadSmsPredefiniDescription = true;
		canWriteSmsPredefiniDescription = true;
		canExportSmsPredefiniDescription = true;
		canCreateOutBox = true;
		canDeleteOutBox = true;
		canDirectAccessOutBox = true;
		canExportOutBox = true;
		canReadOutBoxAdresse = true;
		canWriteOutBoxAdresse = true;
		canExportOutBoxAdresse = true;
		canReadOutBoxMessageInformation = true;
		canWriteOutBoxMessageInformation = true;
		canExportOutBoxMessageInformation = true;
		canCreateUtilisateur = true;
		canDeleteUtilisateur = true;
		canDirectAccessUtilisateur = true;
		canExportUtilisateur = true;
		canReadUtilisateurIdentification = true;
		canWriteUtilisateurIdentification = true;
		canExportUtilisateurIdentification = true;
		canReadUtilisateurContact = true;
		canWriteUtilisateurContact = true;
		canExportUtilisateurContact = true;
		if (isAdmin()) {
			return;
		}
		if (actor.getProfiles() == null) {
			return;
		}
		for (ProfileProxy profile : actor.getProfiles()) {
			if (profile.getEntityProfiles() != null) {
				for (EntityProfileProxy entity : profile.getEntityProfiles()) {
					String entityId = entity.getEntity().getId();
					if ("patient".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreatePatient &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeletePatient &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessPatient &= entity.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportPatient &= entity.getExport();
						}
					} else if ("casindex".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateCasIndex &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteCasIndex &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessCasIndex &= entity.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportCasIndex &= entity.getExport();
						}
					} else if ("castuberculose".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateCasTuberculose &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteCasTuberculose &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessCasTuberculose &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportCasTuberculose &= entity.getExport();
						}
					} else if ("examenserologie".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateExamenSerologie &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteExamenSerologie &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessExamenSerologie &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportExamenSerologie &= entity.getExport();
						}
					} else if ("examenbiologique".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateExamenBiologique &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteExamenBiologique &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessExamenBiologique &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportExamenBiologique &= entity.getExport();
						}
					} else if ("examenmicroscopie".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateExamenMicroscopie &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteExamenMicroscopie &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessExamenMicroscopie &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportExamenMicroscopie &= entity.getExport();
						}
					} else if ("examenatb".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateExamenATB &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteExamenATB &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessExamenATB &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportExamenATB &= entity.getExport();
						}
					} else if ("prisemedicamenteuse".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreatePriseMedicamenteuse &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeletePriseMedicamenteuse &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessPriseMedicamenteuse &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportPriseMedicamenteuse &= entity.getExport();
						}
					} else if ("rendezvous".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateRendezVous &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteRendezVous &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessRendezVous &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportRendezVous &= entity.getExport();
						}
					} else if ("transfertreference".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateTransfertReference &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteTransfertReference &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessTransfertReference &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportTransfertReference &= entity.getExport();
						}
					} else if ("lot".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateLot &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteLot &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessLot &= entity.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportLot &= entity.getExport();
						}
					} else if ("horsusage".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateHorsUsage &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteHorsUsage &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessHorsUsage &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportHorsUsage &= entity.getExport();
						}
					} else if ("entreelot".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateEntreeLot &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteEntreeLot &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessEntreeLot &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportEntreeLot &= entity.getExport();
						}
					} else if ("sortielot".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateSortieLot &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteSortieLot &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessSortieLot &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportSortieLot &= entity.getExport();
						}
					} else if ("commande".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateCommande &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteCommande &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessCommande &= entity.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportCommande &= entity.getExport();
						}
					} else if ("detailcommandemedicament".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateDetailCommandeMedicament &= entity
									.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteDetailCommandeMedicament &= entity
									.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessDetailCommandeMedicament &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportDetailCommandeMedicament &= entity
									.getExport();
						}
					} else if ("detailcommandeintrant".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateDetailCommandeIntrant &= entity
									.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteDetailCommandeIntrant &= entity
									.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessDetailCommandeIntrant &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportDetailCommandeIntrant &= entity
									.getExport();
						}
					} else if ("reception".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateReception &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteReception &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessReception &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportReception &= entity.getExport();
						}
					} else if ("detailreceptionmedicament".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateDetailReceptionMedicament &= entity
									.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteDetailReceptionMedicament &= entity
									.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessDetailReceptionMedicament &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportDetailReceptionMedicament &= entity
									.getExport();
						}
					} else if ("detailreceptionintrant".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateDetailReceptionIntrant &= entity
									.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteDetailReceptionIntrant &= entity
									.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessDetailReceptionIntrant &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportDetailReceptionIntrant &= entity
									.getExport();
						}
					} else if ("ravitaillement".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateRavitaillement &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteRavitaillement &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessRavitaillement &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportRavitaillement &= entity.getExport();
						}
					} else if ("detailravitaillement".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateDetailRavitaillement &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteDetailRavitaillement &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessDetailRavitaillement &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportDetailRavitaillement &= entity.getExport();
						}
					} else if ("inventaire".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateInventaire &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteInventaire &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessInventaire &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportInventaire &= entity.getExport();
						}
					} else if ("detailinventaire".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateDetailInventaire &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteDetailInventaire &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessDetailInventaire &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportDetailInventaire &= entity.getExport();
						}
					} else if ("personnel".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreatePersonnel &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeletePersonnel &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessPersonnel &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportPersonnel &= entity.getExport();
						}
					} else if ("departpersonnel".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateDepartPersonnel &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteDepartPersonnel &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessDepartPersonnel &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportDepartPersonnel &= entity.getExport();
						}
					} else if ("arriveepersonnel".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateArriveePersonnel &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteArriveePersonnel &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessArriveePersonnel &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportArriveePersonnel &= entity.getExport();
						}
					} else if ("region".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateRegion &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteRegion &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessRegion &= entity.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportRegion &= entity.getExport();
						}
					} else if ("districtsante".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateDistrictSante &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteDistrictSante &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessDistrictSante &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportDistrictSante &= entity.getExport();
						}
					} else if ("centrediagtrait".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateCentreDiagTrait &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteCentreDiagTrait &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessCentreDiagTrait &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportCentreDiagTrait &= entity.getExport();
						}
					} else if ("laboratoirereference".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateLaboratoireReference &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteLaboratoireReference &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessLaboratoireReference &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportLaboratoireReference &= entity.getExport();
						}
					} else if ("lieudit".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateLieuDit &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteLieuDit &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessLieuDit &= entity.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportLieuDit &= entity.getExport();
						}
					} else if ("regime".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateRegime &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteRegime &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessRegime &= entity.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportRegime &= entity.getExport();
						}
					} else if ("prisemedicamentregime".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreatePriseMedicamentRegime &= entity
									.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeletePriseMedicamentRegime &= entity
									.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessPriseMedicamentRegime &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportPriseMedicamentRegime &= entity
									.getExport();
						}
					} else if ("medicament".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateMedicament &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteMedicament &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessMedicament &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportMedicament &= entity.getExport();
						}
					} else if ("intrant".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateIntrant &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteIntrant &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessIntrant &= entity.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportIntrant &= entity.getExport();
						}
					} else if ("formation".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateFormation &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteFormation &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessFormation &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportFormation &= entity.getExport();
						}
					} else if ("candidatureformation".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateCandidatureFormation &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteCandidatureFormation &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessCandidatureFormation &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportCandidatureFormation &= entity.getExport();
						}
					} else if ("qualification".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateQualification &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteQualification &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessQualification &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportQualification &= entity.getExport();
						}
					} else if ("tutoriel".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateTutoriel &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteTutoriel &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessTutoriel &= entity.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportTutoriel &= entity.getExport();
						}
					} else if ("smspredefini".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateSmsPredefini &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteSmsPredefini &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessSmsPredefini &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportSmsPredefini &= entity.getExport();
						}
					} else if ("outbox".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateOutBox &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteOutBox &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessOutBox &= entity.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportOutBox &= entity.getExport();
						}
					} else if ("utilisateur".equals(entityId)) {
						if (entity.getCreate() != null) {
							canCreateUtilisateur &= entity.getCreate();
						}
						if (entity.getDelete() != null) {
							canDeleteUtilisateur &= entity.getDelete();
						}
						if (entity.getDirectAccess() != null) {
							canDirectAccessUtilisateur &= entity
									.getDirectAccess();
						}
						if (entity.getExport() != null) {
							canExportUtilisateur &= entity.getExport();
						}
					}
				}
			}
			if (profile.getFieldGroupProfiles() != null) {
				for (FieldGroupProfileProxy group : profile
						.getFieldGroupProfiles()) {
					String fieldGroupId = group.getFieldGroup().getId();
					if ("patient.identification".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadPatientIdentification &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWritePatientIdentification &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportPatientIdentification &= group.getExport();
						}
					} else if ("patient.contact".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadPatientContact &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWritePatientContact &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportPatientContact &= group.getExport();
						}
					} else if ("patient.personnecontact".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadPatientPersonneContact &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWritePatientPersonneContact &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportPatientPersonneContact &= group
									.getExport();
						}
					} else if ("patient.tuberculose".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadPatientTuberculose &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWritePatientTuberculose &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportPatientTuberculose &= group.getExport();
						}
					} else if ("patient.examens".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadPatientExamens &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWritePatientExamens &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportPatientExamens &= group.getExport();
						}
					} else if ("casindex.description".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadCasIndexDescription &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteCasIndexDescription &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportCasIndexDescription &= group.getExport();
						}
					} else if ("castuberculose.informations"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadCasTuberculoseInformations &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteCasTuberculoseInformations &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportCasTuberculoseInformations &= group
									.getExport();
						}
					} else if ("castuberculose.examen".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadCasTuberculoseExamen &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteCasTuberculoseExamen &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportCasTuberculoseExamen &= group.getExport();
						}
					} else if ("castuberculose.traitement".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadCasTuberculoseTraitement &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteCasTuberculoseTraitement &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportCasTuberculoseTraitement &= group
									.getExport();
						}
					} else if ("castuberculose.fintraitement"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadCasTuberculoseFinTraitement &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteCasTuberculoseFinTraitement &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportCasTuberculoseFinTraitement &= group
									.getExport();
						}
					} else if ("examenserologie.description"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadExamenSerologieDescription &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteExamenSerologieDescription &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportExamenSerologieDescription &= group
									.getExport();
						}
					} else if ("examenbiologique.description"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadExamenBiologiqueDescription &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteExamenBiologiqueDescription &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportExamenBiologiqueDescription &= group
									.getExport();
						}
					} else if ("examenmicroscopie.centreexamen"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadExamenMicroscopieCentreExamen &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteExamenMicroscopieCentreExamen &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportExamenMicroscopieCentreExamen &= group
									.getExport();
						}
					} else if ("examenmicroscopie.examen".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadExamenMicroscopieExamen &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteExamenMicroscopieExamen &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportExamenMicroscopieExamen &= group
									.getExport();
						}
					} else if ("examenatb.centreexamen".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadExamenATBCentreExamen &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteExamenATBCentreExamen &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportExamenATBCentreExamen &= group.getExport();
						}
					} else if ("examenatb.examen".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadExamenATBExamen &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteExamenATBExamen &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportExamenATBExamen &= group.getExport();
						}
					} else if ("prisemedicamenteuse.description"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadPriseMedicamenteuseDescription &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWritePriseMedicamenteuseDescription &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportPriseMedicamenteuseDescription &= group
									.getExport();
						}
					} else if ("rendezvous.description".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadRendezVousDescription &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteRendezVousDescription &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportRendezVousDescription &= group.getExport();
						}
					} else if ("transfertreference.informationsdepart"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadTransfertReferenceInformationsDepart &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteTransfertReferenceInformationsDepart &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportTransfertReferenceInformationsDepart &= group
									.getExport();
						}
					} else if ("transfertreference.informationarrivee"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadTransfertReferenceInformationArrivee &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteTransfertReferenceInformationArrivee &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportTransfertReferenceInformationArrivee &= group
									.getExport();
						}
					} else if ("lot.description".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadLotDescription &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteLotDescription &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportLotDescription &= group.getExport();
						}
					} else if ("horsusage.description".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadHorsUsageDescription &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteHorsUsageDescription &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportHorsUsageDescription &= group.getExport();
						}
					} else if ("entreelot.description".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadEntreeLotDescription &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteEntreeLotDescription &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportEntreeLotDescription &= group.getExport();
						}
					} else if ("sortielot.description".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadSortieLotDescription &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteSortieLotDescription &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportSortieLotDescription &= group.getExport();
						}
					} else if ("commande.informationsdepart"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadCommandeInformationsDepart &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteCommandeInformationsDepart &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportCommandeInformationsDepart &= group
									.getExport();
						}
					} else if ("commande.regionapprobation"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadCommandeRegionApprobation &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteCommandeRegionApprobation &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportCommandeRegionApprobation &= group
									.getExport();
						}
					} else if ("commande.gtcapprobation".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadCommandeGtcApprobation &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteCommandeGtcApprobation &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportCommandeGtcApprobation &= group
									.getExport();
						}
					} else if ("detailcommandemedicament.description"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadDetailCommandeMedicamentDescription &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteDetailCommandeMedicamentDescription &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportDetailCommandeMedicamentDescription &= group
									.getExport();
						}
					} else if ("detailcommandeintrant.description"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadDetailCommandeIntrantDescription &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteDetailCommandeIntrantDescription &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportDetailCommandeIntrantDescription &= group
									.getExport();
						}
					} else if ("reception.description".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadReceptionDescription &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteReceptionDescription &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportReceptionDescription &= group.getExport();
						}
					} else if ("detailreceptionmedicament.description"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadDetailReceptionMedicamentDescription &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteDetailReceptionMedicamentDescription &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportDetailReceptionMedicamentDescription &= group
									.getExport();
						}
					} else if ("detailreceptionintrant.description"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadDetailReceptionIntrantDescription &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteDetailReceptionIntrantDescription &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportDetailReceptionIntrantDescription &= group
									.getExport();
						}
					} else if ("ravitaillement.informationsdepart"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadRavitaillementInformationsDepart &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteRavitaillementInformationsDepart &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportRavitaillementInformationsDepart &= group
									.getExport();
						}
					} else if ("ravitaillement.informationarrivee"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadRavitaillementInformationArrivee &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteRavitaillementInformationArrivee &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportRavitaillementInformationArrivee &= group
									.getExport();
						}
					} else if ("ravitaillement.detail".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadRavitaillementDetail &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteRavitaillementDetail &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportRavitaillementDetail &= group.getExport();
						}
					} else if ("detailravitaillement.description"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadDetailRavitaillementDescription &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteDetailRavitaillementDescription &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportDetailRavitaillementDescription &= group
									.getExport();
						}
					} else if ("detailravitaillement.sortie"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadDetailRavitaillementSortie &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteDetailRavitaillementSortie &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportDetailRavitaillementSortie &= group
									.getExport();
						}
					} else if ("detailravitaillement.entree"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadDetailRavitaillementEntree &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteDetailRavitaillementEntree &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportDetailRavitaillementEntree &= group
									.getExport();
						}
					} else if ("inventaire.informationsdepart"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadInventaireInformationsDepart &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteInventaireInformationsDepart &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportInventaireInformationsDepart &= group
									.getExport();
						}
					} else if ("detailinventaire.description"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadDetailInventaireDescription &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteDetailInventaireDescription &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportDetailInventaireDescription &= group
									.getExport();
						}
					} else if ("personnel.identification".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadPersonnelIdentification &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWritePersonnelIdentification &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportPersonnelIdentification &= group
									.getExport();
						}
					} else if ("personnel.contact".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadPersonnelContact &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWritePersonnelContact &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportPersonnelContact &= group.getExport();
						}
					} else if ("personnel.situation".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadPersonnelSituation &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWritePersonnelSituation &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportPersonnelSituation &= group.getExport();
						}
					} else if ("personnel.niveauaccess".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadPersonnelNiveauAccess &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWritePersonnelNiveauAccess &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportPersonnelNiveauAccess &= group.getExport();
						}
					} else if ("departpersonnel.personnel".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadDepartPersonnelPersonnel &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteDepartPersonnelPersonnel &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportDepartPersonnelPersonnel &= group
									.getExport();
						}
					} else if ("departpersonnel.description"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadDepartPersonnelDescription &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteDepartPersonnelDescription &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportDepartPersonnelDescription &= group
									.getExport();
						}
					} else if ("arriveepersonnel.description"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadArriveePersonnelDescription &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteArriveePersonnelDescription &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportArriveePersonnelDescription &= group
									.getExport();
						}
					} else if ("region.description".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadRegionDescription &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteRegionDescription &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportRegionDescription &= group.getExport();
						}
					} else if ("region.adresse".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadRegionAdresse &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteRegionAdresse &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportRegionAdresse &= group.getExport();
						}
					} else if ("districtsante.description".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadDistrictSanteDescription &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteDistrictSanteDescription &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportDistrictSanteDescription &= group
									.getExport();
						}
					} else if ("districtsante.adresse".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadDistrictSanteAdresse &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteDistrictSanteAdresse &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportDistrictSanteAdresse &= group.getExport();
						}
					} else if ("centrediagtrait.description"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadCentreDiagTraitDescription &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteCentreDiagTraitDescription &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportCentreDiagTraitDescription &= group
									.getExport();
						}
					} else if ("centrediagtrait.adresse".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadCentreDiagTraitAdresse &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteCentreDiagTraitAdresse &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportCentreDiagTraitAdresse &= group
									.getExport();
						}
					} else if ("laboratoirereference.description"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadLaboratoireReferenceDescription &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteLaboratoireReferenceDescription &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportLaboratoireReferenceDescription &= group
									.getExport();
						}
					} else if ("laboratoirereference.adresse"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadLaboratoireReferenceAdresse &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteLaboratoireReferenceAdresse &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportLaboratoireReferenceAdresse &= group
									.getExport();
						}
					} else if ("lieudit.description".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadLieuDitDescription &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteLieuDitDescription &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportLieuDitDescription &= group.getExport();
						}
					} else if ("lieudit.adresse".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadLieuDitAdresse &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteLieuDitAdresse &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportLieuDitAdresse &= group.getExport();
						}
					} else if ("regime.description".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadRegimeDescription &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteRegimeDescription &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportRegimeDescription &= group.getExport();
						}
					} else if ("prisemedicamentregime.description"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadPriseMedicamentRegimeDescription &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWritePriseMedicamentRegimeDescription &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportPriseMedicamentRegimeDescription &= group
									.getExport();
						}
					} else if ("medicament.description".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadMedicamentDescription &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteMedicamentDescription &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportMedicamentDescription &= group.getExport();
						}
					} else if ("intrant.description".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadIntrantDescription &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteIntrantDescription &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportIntrantDescription &= group.getExport();
						}
					} else if ("formation.description".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadFormationDescription &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteFormationDescription &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportFormationDescription &= group.getExport();
						}
					} else if ("candidatureformation.description"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadCandidatureFormationDescription &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteCandidatureFormationDescription &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportCandidatureFormationDescription &= group
									.getExport();
						}
					} else if ("candidatureformation.regionapprobation"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadCandidatureFormationRegionApprobation &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteCandidatureFormationRegionApprobation &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportCandidatureFormationRegionApprobation &= group
									.getExport();
						}
					} else if ("candidatureformation.gtcapprobation"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadCandidatureFormationGtcApprobation &= group
									.getRead();
						}
						if (group.getWrite() != null) {
							canWriteCandidatureFormationGtcApprobation &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportCandidatureFormationGtcApprobation &= group
									.getExport();
						}
					} else if ("qualification.description".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadQualificationDescription &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteQualificationDescription &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportQualificationDescription &= group
									.getExport();
						}
					} else if ("tutoriel.description".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadTutorielDescription &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteTutorielDescription &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportTutorielDescription &= group.getExport();
						}
					} else if ("smspredefini.description".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadSmsPredefiniDescription &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteSmsPredefiniDescription &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportSmsPredefiniDescription &= group
									.getExport();
						}
					} else if ("outbox.adresse".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadOutBoxAdresse &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteOutBoxAdresse &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportOutBoxAdresse &= group.getExport();
						}
					} else if ("outbox.messageinformation".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadOutBoxMessageInformation &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteOutBoxMessageInformation &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportOutBoxMessageInformation &= group
									.getExport();
						}
					} else if ("utilisateur.identification"
							.equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadUtilisateurIdentification &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteUtilisateurIdentification &= group
									.getWrite();
						}
						if (group.getExport() != null) {
							canExportUtilisateurIdentification &= group
									.getExport();
						}
					} else if ("utilisateur.contact".equals(fieldGroupId)) {
						if (group.getRead() != null) {
							canReadUtilisateurContact &= group.getRead();
						}
						if (group.getWrite() != null) {
							canWriteUtilisateurContact &= group.getWrite();
						}
						if (group.getExport() != null) {
							canExportUtilisateurContact &= group.getExport();
						}
					}
				}
			}
		}
	}

}
