package org.imogene.epicam.client;

import java.util.ArrayList;
import java.util.List;

import org.imogene.admin.client.i18n.AdminNLS;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.web.client.dynamicfields.ui.field.FormType;
import org.imogene.web.client.dynamicfields.ui.field.FormTypeUtil;

/**
 * Provides Form types codes and display values when
 * a entity references a form type and not an instance of a form
 * @author MEDES-IMPS
 */
public class EpicamFormTypes implements FormTypeUtil {

	private static FormTypeUtil instance = new EpicamFormTypes();

	public static FormTypeUtil get() {
		return instance;
	}

	public List<FormType> getFormTypes() {

		List<FormType> list = new ArrayList<FormType>();

		FormType patient = new FormType("PAT", NLS.constants().patient_name());
		list.add(patient);
		FormType casIndex = new FormType("CAS_INDEX", NLS.constants()
				.casIndex_name());
		list.add(casIndex);
		FormType casTuberculose = new FormType("TBCASE", NLS.constants()
				.casTuberculose_name());
		list.add(casTuberculose);
		FormType examenSerologie = new FormType("EXAM_SER", NLS.constants()
				.examenSerologie_name());
		list.add(examenSerologie);
		FormType examenBiologique = new FormType("EXAM_BIO", NLS.constants()
				.examenBiologique_name());
		list.add(examenBiologique);
		FormType examenMicroscopie = new FormType("EXAM_MICRO", NLS.constants()
				.examenMicroscopie_name());
		list.add(examenMicroscopie);
		FormType examenATB = new FormType("EXAM_ATB", NLS.constants()
				.examenATB_name());
		list.add(examenATB);
		FormType priseMedicamenteuse = new FormType("PRIS_MED_INIT", NLS
				.constants().priseMedicamenteuse_name());
		list.add(priseMedicamenteuse);
		FormType rendezVous = new FormType("RDV", NLS.constants()
				.rendezVous_name());
		list.add(rendezVous);
		FormType transfertReference = new FormType("TRANS_REF", NLS.constants()
				.transfertReference_name());
		list.add(transfertReference);
		FormType lot = new FormType("LOT", NLS.constants().lot_name());
		list.add(lot);
		FormType horsUsage = new FormType("HO_US", NLS.constants()
				.horsUsage_name());
		list.add(horsUsage);
		FormType entreeLot = new FormType("ENTR", NLS.constants()
				.entreeLot_name());
		list.add(entreeLot);
		FormType sortieLot = new FormType("SORT", NLS.constants()
				.sortieLot_name());
		list.add(sortieLot);
		FormType commande = new FormType("CMD", NLS.constants().commande_name());
		list.add(commande);
		FormType detailCommandeMedicament = new FormType("DET_CMD_MED", NLS
				.constants().detailCommandeMedicament_name());
		list.add(detailCommandeMedicament);
		FormType detailCommandeIntrant = new FormType("DET_CMD_INT", NLS
				.constants().detailCommandeIntrant_name());
		list.add(detailCommandeIntrant);
		FormType reception = new FormType("REC", NLS.constants()
				.reception_name());
		list.add(reception);
		FormType detailReceptionMedicament = new FormType("DET_REC_MED", NLS
				.constants().detailReceptionMedicament_name());
		list.add(detailReceptionMedicament);
		FormType detailReceptionIntrant = new FormType("DET_REC_INT", NLS
				.constants().detailReceptionIntrant_name());
		list.add(detailReceptionIntrant);
		FormType ravitaillement = new FormType("RAV", NLS.constants()
				.ravitaillement_name());
		list.add(ravitaillement);
		FormType detailRavitaillement = new FormType("DET_RAV", NLS.constants()
				.detailRavitaillement_name());
		list.add(detailRavitaillement);
		FormType inventaire = new FormType("INV", NLS.constants()
				.inventaire_name());
		list.add(inventaire);
		FormType detailInventaire = new FormType("DET_INV", NLS.constants()
				.detailInventaire_name());
		list.add(detailInventaire);
		FormType personnel = new FormType("PERS", NLS.constants()
				.personnel_name());
		list.add(personnel);
		FormType departPersonnel = new FormType("DEP_PERS", NLS.constants()
				.departPersonnel_name());
		list.add(departPersonnel);
		FormType arriveePersonnel = new FormType("ARR_PERS", NLS.constants()
				.arriveePersonnel_name());
		list.add(arriveePersonnel);
		FormType region = new FormType("REG", NLS.constants().region_name());
		list.add(region);
		FormType districtSante = new FormType("DST", NLS.constants()
				.districtSante_name());
		list.add(districtSante);
		FormType centreDiagTrait = new FormType("CDT", NLS.constants()
				.centreDiagTrait_name());
		list.add(centreDiagTrait);
		FormType laboratoireReference = new FormType("LAB_REF", NLS.constants()
				.laboratoireReference_name());
		list.add(laboratoireReference);
		FormType lieuDit = new FormType("LD", NLS.constants().lieuDit_name());
		list.add(lieuDit);
		FormType regime = new FormType("REGIM", NLS.constants().regime_name());
		list.add(regime);
		FormType priseMedicamentRegime = new FormType("PRIS_REG", NLS
				.constants().priseMedicamentRegime_name());
		list.add(priseMedicamentRegime);
		FormType medicament = new FormType("MED", NLS.constants()
				.medicament_name());
		list.add(medicament);
		FormType intrant = new FormType("INTR", NLS.constants().intrant_name());
		list.add(intrant);
		FormType formation = new FormType("FORM", NLS.constants()
				.formation_name());
		list.add(formation);
		FormType candidatureFormation = new FormType("CAND_FORM", NLS
				.constants().candidatureFormation_name());
		list.add(candidatureFormation);
		FormType qualification = new FormType("QUA", NLS.constants()
				.qualification_name());
		list.add(qualification);
		FormType tutoriel = new FormType("TUTO", NLS.constants()
				.tutoriel_name());
		list.add(tutoriel);
		FormType smsPredefini = new FormType("SMS", NLS.constants()
				.smsPredefini_name());
		list.add(smsPredefini);
		FormType outBox = new FormType("OUTBOX", NLS.constants().outBox_name());
		list.add(outBox);
		FormType utilisateur = new FormType("USR", NLS.constants()
				.utilisateur_name());
		list.add(utilisateur);
		FormType binaryFile = new FormType("BIN", AdminNLS.constants()
				.binary_name());
		list.add(binaryFile);
		return list;
	}

	public List<FormType> getDynamicFieldFormTypes() {

		List<FormType> list = new ArrayList<FormType>();

		return list;
	}

}
