package org.imogene.epicam.shared;

import org.imogene.epicam.shared.proxy.LocalizedTextProxy;
import org.imogene.epicam.shared.request.PatientRequest;
import org.imogene.epicam.shared.request.CasIndexRequest;
import org.imogene.epicam.shared.request.CasTuberculoseRequest;
import org.imogene.epicam.shared.request.ExamenSerologieRequest;
import org.imogene.epicam.shared.request.ExamenBiologiqueRequest;
import org.imogene.epicam.shared.request.ExamenMicroscopieRequest;
import org.imogene.epicam.shared.request.ExamenATBRequest;
import org.imogene.epicam.shared.request.PriseMedicamenteuseRequest;
import org.imogene.epicam.shared.request.RendezVousRequest;
import org.imogene.epicam.shared.request.TransfertReferenceRequest;
import org.imogene.epicam.shared.request.LotRequest;
import org.imogene.epicam.shared.request.HorsUsageRequest;
import org.imogene.epicam.shared.request.EntreeLotRequest;
import org.imogene.epicam.shared.request.SortieLotRequest;
import org.imogene.epicam.shared.request.CommandeRequest;
import org.imogene.epicam.shared.request.DetailCommandeMedicamentRequest;
import org.imogene.epicam.shared.request.DetailCommandeIntrantRequest;
import org.imogene.epicam.shared.request.ReceptionRequest;
import org.imogene.epicam.shared.request.DetailReceptionMedicamentRequest;
import org.imogene.epicam.shared.request.DetailReceptionIntrantRequest;
import org.imogene.epicam.shared.request.RavitaillementRequest;
import org.imogene.epicam.shared.request.DetailRavitaillementRequest;
import org.imogene.epicam.shared.request.InventaireRequest;
import org.imogene.epicam.shared.request.DetailInventaireRequest;
import org.imogene.epicam.shared.request.PersonnelRequest;
import org.imogene.epicam.shared.request.DepartPersonnelRequest;
import org.imogene.epicam.shared.request.ArriveePersonnelRequest;
import org.imogene.epicam.shared.request.RegionRequest;
import org.imogene.epicam.shared.request.DistrictSanteRequest;
import org.imogene.epicam.shared.request.CentreDiagTraitRequest;
import org.imogene.epicam.shared.request.LaboratoireReferenceRequest;
import org.imogene.epicam.shared.request.LieuDitRequest;
import org.imogene.epicam.shared.request.RegimeRequest;
import org.imogene.epicam.shared.request.PriseMedicamentRegimeRequest;
import org.imogene.epicam.shared.request.MedicamentRequest;
import org.imogene.epicam.shared.request.IntrantRequest;
import org.imogene.epicam.shared.request.FormationRequest;
import org.imogene.epicam.shared.request.CandidatureFormationRequest;
import org.imogene.epicam.shared.request.QualificationRequest;
import org.imogene.epicam.shared.request.TutorielRequest;
import org.imogene.epicam.shared.request.SmsPredefiniRequest;
import org.imogene.epicam.shared.request.OutBoxRequest;
import org.imogene.epicam.shared.request.UtilisateurRequest;

import org.imogene.admin.shared.AdminRequestFactory;
import org.imogene.epicam.shared.proxy.PersonnelProxy;
import org.imogene.epicam.shared.proxy.UtilisateurProxy;

import com.google.web.bindery.requestfactory.shared.ExtraTypes;

/**
 * RequestFactory of the application
 * @author Medes-IMPS
 */
@ExtraTypes({LocalizedTextProxy.class, PersonnelProxy.class,
		UtilisateurProxy.class})
public interface EpicamRequestFactory extends AdminRequestFactory {

	PatientRequest patientRequest();
	CasIndexRequest casIndexRequest();
	CasTuberculoseRequest casTuberculoseRequest();
	ExamenSerologieRequest examenSerologieRequest();
	ExamenBiologiqueRequest examenBiologiqueRequest();
	ExamenMicroscopieRequest examenMicroscopieRequest();
	ExamenATBRequest examenATBRequest();
	PriseMedicamenteuseRequest priseMedicamenteuseRequest();
	RendezVousRequest rendezVousRequest();
	TransfertReferenceRequest transfertReferenceRequest();
	LotRequest lotRequest();
	HorsUsageRequest horsUsageRequest();
	EntreeLotRequest entreeLotRequest();
	SortieLotRequest sortieLotRequest();
	CommandeRequest commandeRequest();
	DetailCommandeMedicamentRequest detailCommandeMedicamentRequest();
	DetailCommandeIntrantRequest detailCommandeIntrantRequest();
	ReceptionRequest receptionRequest();
	DetailReceptionMedicamentRequest detailReceptionMedicamentRequest();
	DetailReceptionIntrantRequest detailReceptionIntrantRequest();
	RavitaillementRequest ravitaillementRequest();
	DetailRavitaillementRequest detailRavitaillementRequest();
	InventaireRequest inventaireRequest();
	DetailInventaireRequest detailInventaireRequest();
	PersonnelRequest personnelRequest();
	DepartPersonnelRequest departPersonnelRequest();
	ArriveePersonnelRequest arriveePersonnelRequest();
	RegionRequest regionRequest();
	DistrictSanteRequest districtSanteRequest();
	CentreDiagTraitRequest centreDiagTraitRequest();
	LaboratoireReferenceRequest laboratoireReferenceRequest();
	LieuDitRequest lieuDitRequest();
	RegimeRequest regimeRequest();
	PriseMedicamentRegimeRequest priseMedicamentRegimeRequest();
	MedicamentRequest medicamentRequest();
	IntrantRequest intrantRequest();
	FormationRequest formationRequest();
	CandidatureFormationRequest candidatureFormationRequest();
	QualificationRequest qualificationRequest();
	TutorielRequest tutorielRequest();
	SmsPredefiniRequest smsPredefiniRequest();
	OutBoxRequest outBoxRequest();
	UtilisateurRequest utilisateurRequest();

}
