package org.imogene.epicam.client;

import com.google.gwt.core.client.GWT;
import org.imogene.epicam.shared.proxy.PatientProxy;
import org.imogene.epicam.shared.proxy.CasIndexProxy;
import org.imogene.epicam.shared.proxy.CasTuberculoseProxy;
import org.imogene.epicam.shared.proxy.ExamenSerologieProxy;
import org.imogene.epicam.shared.proxy.ExamenBiologiqueProxy;
import org.imogene.epicam.shared.proxy.ExamenMicroscopieProxy;
import org.imogene.epicam.shared.proxy.ExamenATBProxy;
import org.imogene.epicam.shared.proxy.PriseMedicamenteuseProxy;
import org.imogene.epicam.shared.proxy.RendezVousProxy;
import org.imogene.epicam.shared.proxy.TransfertReferenceProxy;
import org.imogene.epicam.shared.proxy.LotProxy;
import org.imogene.epicam.shared.proxy.HorsUsageProxy;
import org.imogene.epicam.shared.proxy.EntreeLotProxy;
import org.imogene.epicam.shared.proxy.SortieLotProxy;
import org.imogene.epicam.shared.proxy.CommandeProxy;
import org.imogene.epicam.shared.proxy.DetailCommandeMedicamentProxy;
import org.imogene.epicam.shared.proxy.DetailCommandeIntrantProxy;
import org.imogene.epicam.shared.proxy.ReceptionProxy;
import org.imogene.epicam.shared.proxy.DetailReceptionMedicamentProxy;
import org.imogene.epicam.shared.proxy.DetailReceptionIntrantProxy;
import org.imogene.epicam.shared.proxy.RavitaillementProxy;
import org.imogene.epicam.shared.proxy.DetailRavitaillementProxy;
import org.imogene.epicam.shared.proxy.InventaireProxy;
import org.imogene.epicam.shared.proxy.DetailInventaireProxy;
import org.imogene.epicam.shared.proxy.PersonnelProxy;
import org.imogene.epicam.shared.proxy.DepartPersonnelProxy;
import org.imogene.epicam.shared.proxy.ArriveePersonnelProxy;
import org.imogene.epicam.shared.proxy.RegionProxy;
import org.imogene.epicam.shared.proxy.DistrictSanteProxy;
import org.imogene.epicam.shared.proxy.CentreDiagTraitProxy;
import org.imogene.epicam.shared.proxy.LaboratoireReferenceProxy;
import org.imogene.epicam.shared.proxy.LieuDitProxy;
import org.imogene.epicam.shared.proxy.RegimeProxy;
import org.imogene.epicam.shared.proxy.PriseMedicamentRegimeProxy;
import org.imogene.epicam.shared.proxy.MedicamentProxy;
import org.imogene.epicam.shared.proxy.IntrantProxy;
import org.imogene.epicam.shared.proxy.FormationProxy;
import org.imogene.epicam.shared.proxy.CandidatureFormationProxy;
import org.imogene.epicam.shared.proxy.QualificationProxy;
import org.imogene.epicam.shared.proxy.TutorielProxy;
import org.imogene.epicam.shared.proxy.SmsPredefiniProxy;
import org.imogene.epicam.shared.proxy.OutBoxProxy;
import org.imogene.epicam.shared.proxy.UtilisateurProxy;

/**
 * Constants that give the path to the entity icons
 * @author Medes-IMPS
 */
public class EpicamIconConstants {

	public static final String PATIENT_ICON = GWT.getModuleBaseURL()
			+ "images/Patient.png";

	public static final String CASINDEX_ICON = null;

	public static final String CASTUBERCULOSE_ICON = null;

	public static final String EXAMENSEROLOGIE_ICON = GWT.getModuleBaseURL()
			+ "images/Serologie.png";

	public static final String EXAMENBIOLOGIQUE_ICON = GWT.getModuleBaseURL()
			+ "images/Stethoscope.png";

	public static final String EXAMENMICROSCOPIE_ICON = GWT.getModuleBaseURL()
			+ "images/Microscope.png";

	public static final String EXAMENATB_ICON = GWT.getModuleBaseURL()
			+ "images/Chemicals.png";

	public static final String PRISEMEDICAMENTEUSE_ICON = GWT
			.getModuleBaseURL() + "images/Pill.png";

	public static final String RENDEZVOUS_ICON = null;

	public static final String TRANSFERTREFERENCE_ICON = null;

	public static final String LOT_ICON = GWT.getModuleBaseURL()
			+ "images/MedicinePills.png";

	public static final String HORSUSAGE_ICON = GWT.getModuleBaseURL()
			+ "images/HorsUsage.png";

	public static final String ENTREELOT_ICON = null;

	public static final String SORTIELOT_ICON = null;

	public static final String COMMANDE_ICON = GWT.getModuleBaseURL()
			+ "images/Commande.png";

	public static final String DETAILCOMMANDEMEDICAMENT_ICON = null;

	public static final String DETAILCOMMANDEINTRANT_ICON = null;

	public static final String RECEPTION_ICON = GWT.getModuleBaseURL()
			+ "images/Reception.png";

	public static final String DETAILRECEPTIONMEDICAMENT_ICON = null;

	public static final String DETAILRECEPTIONINTRANT_ICON = null;

	public static final String RAVITAILLEMENT_ICON = null;

	public static final String DETAILRAVITAILLEMENT_ICON = null;

	public static final String INVENTAIRE_ICON = null;

	public static final String DETAILINVENTAIRE_ICON = null;

	public static final String PERSONNEL_ICON = GWT.getModuleBaseURL()
			+ "images/Doctor.png";

	public static final String DEPARTPERSONNEL_ICON = null;

	public static final String ARRIVEEPERSONNEL_ICON = null;

	public static final String REGION_ICON = null;

	public static final String DISTRICTSANTE_ICON = GWT.getModuleBaseURL()
			+ "images/Hospital.png";

	public static final String CENTREDIAGTRAIT_ICON = GWT.getModuleBaseURL()
			+ "images/CentreDiagTrait.png";

	public static final String LABORATOIREREFERENCE_ICON = GWT
			.getModuleBaseURL() + "images/Microscope.png";

	public static final String LIEUDIT_ICON = null;

	public static final String REGIME_ICON = GWT.getModuleBaseURL()
			+ "images/MedicinePills.png";

	public static final String PRISEMEDICAMENTREGIME_ICON = null;

	public static final String MEDICAMENT_ICON = GWT.getModuleBaseURL()
			+ "images/MedicinePills.png";

	public static final String INTRANT_ICON = GWT.getModuleBaseURL()
			+ "images/Intrants.png";

	public static final String FORMATION_ICON = GWT.getModuleBaseURL()
			+ "images/MedicalChart.png";

	public static final String CANDIDATUREFORMATION_ICON = null;

	public static final String QUALIFICATION_ICON = GWT.getModuleBaseURL()
			+ "images/Qualifications.png";

	public static final String TUTORIEL_ICON = GWT.getModuleBaseURL()
			+ "images/Insurance.png";

	public static final String SMSPREDEFINI_ICON = GWT.getModuleBaseURL()
			+ "images/Message.png";

	public static final String OUTBOX_ICON = null;

	public static final String UTILISATEUR_ICON = GWT.getModuleBaseURL()
			+ "images/User.png";

	public static String getIconPath(String name) {

		if (name.equals(PatientProxy.class.getName())) {
			return PATIENT_ICON;
		}

		if (name.equals(CasIndexProxy.class.getName())) {
			return CASINDEX_ICON;
		}

		if (name.equals(CasTuberculoseProxy.class.getName())) {
			return CASTUBERCULOSE_ICON;
		}

		if (name.equals(ExamenSerologieProxy.class.getName())) {
			return EXAMENSEROLOGIE_ICON;
		}

		if (name.equals(ExamenBiologiqueProxy.class.getName())) {
			return EXAMENBIOLOGIQUE_ICON;
		}

		if (name.equals(ExamenMicroscopieProxy.class.getName())) {
			return EXAMENMICROSCOPIE_ICON;
		}

		if (name.equals(ExamenATBProxy.class.getName())) {
			return EXAMENATB_ICON;
		}

		if (name.equals(PriseMedicamenteuseProxy.class.getName())) {
			return PRISEMEDICAMENTEUSE_ICON;
		}

		if (name.equals(RendezVousProxy.class.getName())) {
			return RENDEZVOUS_ICON;
		}

		if (name.equals(TransfertReferenceProxy.class.getName())) {
			return TRANSFERTREFERENCE_ICON;
		}

		if (name.equals(LotProxy.class.getName())) {
			return LOT_ICON;
		}

		if (name.equals(HorsUsageProxy.class.getName())) {
			return HORSUSAGE_ICON;
		}

		if (name.equals(EntreeLotProxy.class.getName())) {
			return ENTREELOT_ICON;
		}

		if (name.equals(SortieLotProxy.class.getName())) {
			return SORTIELOT_ICON;
		}

		if (name.equals(CommandeProxy.class.getName())) {
			return COMMANDE_ICON;
		}

		if (name.equals(DetailCommandeMedicamentProxy.class.getName())) {
			return DETAILCOMMANDEMEDICAMENT_ICON;
		}

		if (name.equals(DetailCommandeIntrantProxy.class.getName())) {
			return DETAILCOMMANDEINTRANT_ICON;
		}

		if (name.equals(ReceptionProxy.class.getName())) {
			return RECEPTION_ICON;
		}

		if (name.equals(DetailReceptionMedicamentProxy.class.getName())) {
			return DETAILRECEPTIONMEDICAMENT_ICON;
		}

		if (name.equals(DetailReceptionIntrantProxy.class.getName())) {
			return DETAILRECEPTIONINTRANT_ICON;
		}

		if (name.equals(RavitaillementProxy.class.getName())) {
			return RAVITAILLEMENT_ICON;
		}

		if (name.equals(DetailRavitaillementProxy.class.getName())) {
			return DETAILRAVITAILLEMENT_ICON;
		}

		if (name.equals(InventaireProxy.class.getName())) {
			return INVENTAIRE_ICON;
		}

		if (name.equals(DetailInventaireProxy.class.getName())) {
			return DETAILINVENTAIRE_ICON;
		}

		if (name.equals(PersonnelProxy.class.getName())) {
			return PERSONNEL_ICON;
		}

		if (name.equals(DepartPersonnelProxy.class.getName())) {
			return DEPARTPERSONNEL_ICON;
		}

		if (name.equals(ArriveePersonnelProxy.class.getName())) {
			return ARRIVEEPERSONNEL_ICON;
		}

		if (name.equals(RegionProxy.class.getName())) {
			return REGION_ICON;
		}

		if (name.equals(DistrictSanteProxy.class.getName())) {
			return DISTRICTSANTE_ICON;
		}

		if (name.equals(CentreDiagTraitProxy.class.getName())) {
			return CENTREDIAGTRAIT_ICON;
		}

		if (name.equals(LaboratoireReferenceProxy.class.getName())) {
			return LABORATOIREREFERENCE_ICON;
		}

		if (name.equals(LieuDitProxy.class.getName())) {
			return LIEUDIT_ICON;
		}

		if (name.equals(RegimeProxy.class.getName())) {
			return REGIME_ICON;
		}

		if (name.equals(PriseMedicamentRegimeProxy.class.getName())) {
			return PRISEMEDICAMENTREGIME_ICON;
		}

		if (name.equals(MedicamentProxy.class.getName())) {
			return MEDICAMENT_ICON;
		}

		if (name.equals(IntrantProxy.class.getName())) {
			return INTRANT_ICON;
		}

		if (name.equals(FormationProxy.class.getName())) {
			return FORMATION_ICON;
		}

		if (name.equals(CandidatureFormationProxy.class.getName())) {
			return CANDIDATUREFORMATION_ICON;
		}

		if (name.equals(QualificationProxy.class.getName())) {
			return QUALIFICATION_ICON;
		}

		if (name.equals(TutorielProxy.class.getName())) {
			return TUTORIEL_ICON;
		}

		if (name.equals(SmsPredefiniProxy.class.getName())) {
			return SMSPREDEFINI_ICON;
		}

		if (name.equals(OutBoxProxy.class.getName())) {
			return OUTBOX_ICON;
		}

		if (name.equals(UtilisateurProxy.class.getName())) {
			return UTILISATEUR_ICON;
		}

		return "";
	}

}
