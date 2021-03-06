package org.imogene.epicam.client;

import org.imogene.admin.client.i18n.AdminNLS;
import org.imogene.web.client.dynamicfields.i18n.DynFieldsNLS;
import org.imogene.web.client.util.ImogBeanRenderer;
import org.imogene.web.shared.proxy.CardEntityProxy;
import org.imogene.web.shared.proxy.FieldGroupProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.epicam.client.i18n.NLS;

/**
 * Singleton that enables to render a display value of the beans
 * @author MEDES-IMPS
 */
public class EpicamAdminUtilRenderer extends ImogBeanRenderer {

	private static EpicamAdminUtilRenderer instance = new EpicamAdminUtilRenderer();

	private EpicamAdminUtilRenderer() {
		super();
	}

	public static EpicamAdminUtilRenderer get() {
		return instance;
	}

	/**
	 * Get display representation for a ImogBeanProxy     
	 * @param bean the ImogBeanProxy
	 * @return string representation for IHM display
	 */
	public String getDisplayValue(ImogBeanProxy bean) {

		if (bean instanceof CardEntityProxy)
			return getDisplayValue((CardEntityProxy) bean);

		else if (bean instanceof FieldGroupProxy)
			return getDisplayValue((FieldGroupProxy) bean);

		return "";
	}

	private String getDisplayValue(CardEntityProxy bean) {
		String id = bean.getId();

		if (id.equals("patient")) {
			return NLS.constants().patient_name();
		}
		if (id.equals("casindex")) {
			return NLS.constants().casIndex_name();
		}
		if (id.equals("castuberculose")) {
			return NLS.constants().casTuberculose_name();
		}
		if (id.equals("examenserologie")) {
			return NLS.constants().examenSerologie_name();
		}
		if (id.equals("examenbiologique")) {
			return NLS.constants().examenBiologique_name();
		}
		if (id.equals("examenmicroscopie")) {
			return NLS.constants().examenMicroscopie_name();
		}
		if (id.equals("examenatb")) {
			return NLS.constants().examenATB_name();
		}
		if (id.equals("prisemedicamenteuse")) {
			return NLS.constants().priseMedicamenteuse_name();
		}
		if (id.equals("rendezvous")) {
			return NLS.constants().rendezVous_name();
		}
		if (id.equals("transfertreference")) {
			return NLS.constants().transfertReference_name();
		}
		if (id.equals("lot")) {
			return NLS.constants().lot_name();
		}
		if (id.equals("horsusage")) {
			return NLS.constants().horsUsage_name();
		}
		if (id.equals("entreelot")) {
			return NLS.constants().entreeLot_name();
		}
		if (id.equals("sortielot")) {
			return NLS.constants().sortieLot_name();
		}
		if (id.equals("commande")) {
			return NLS.constants().commande_name();
		}
		if (id.equals("detailcommandemedicament")) {
			return NLS.constants().detailCommandeMedicament_name();
		}
		if (id.equals("detailcommandeintrant")) {
			return NLS.constants().detailCommandeIntrant_name();
		}
		if (id.equals("reception")) {
			return NLS.constants().reception_name();
		}
		if (id.equals("detailreceptionmedicament")) {
			return NLS.constants().detailReceptionMedicament_name();
		}
		if (id.equals("detailreceptionintrant")) {
			return NLS.constants().detailReceptionIntrant_name();
		}
		if (id.equals("ravitaillement")) {
			return NLS.constants().ravitaillement_name();
		}
		if (id.equals("detailravitaillement")) {
			return NLS.constants().detailRavitaillement_name();
		}
		if (id.equals("inventaire")) {
			return NLS.constants().inventaire_name();
		}
		if (id.equals("detailinventaire")) {
			return NLS.constants().detailInventaire_name();
		}
		if (id.equals("personnel")) {
			return NLS.constants().personnel_name();
		}
		if (id.equals("departpersonnel")) {
			return NLS.constants().departPersonnel_name();
		}
		if (id.equals("arriveepersonnel")) {
			return NLS.constants().arriveePersonnel_name();
		}
		if (id.equals("region")) {
			return NLS.constants().region_name();
		}
		if (id.equals("districtsante")) {
			return NLS.constants().districtSante_name();
		}
		if (id.equals("centrediagtrait")) {
			return NLS.constants().centreDiagTrait_name();
		}
		if (id.equals("laboratoirereference")) {
			return NLS.constants().laboratoireReference_name();
		}
		if (id.equals("lieudit")) {
			return NLS.constants().lieuDit_name();
		}
		if (id.equals("regime")) {
			return NLS.constants().regime_name();
		}
		if (id.equals("prisemedicamentregime")) {
			return NLS.constants().priseMedicamentRegime_name();
		}
		if (id.equals("medicament")) {
			return NLS.constants().medicament_name();
		}
		if (id.equals("intrant")) {
			return NLS.constants().intrant_name();
		}
		if (id.equals("formation")) {
			return NLS.constants().formation_name();
		}
		if (id.equals("candidatureformation")) {
			return NLS.constants().candidatureFormation_name();
		}
		if (id.equals("qualification")) {
			return NLS.constants().qualification_name();
		}
		if (id.equals("tutoriel")) {
			return NLS.constants().tutoriel_name();
		}
		if (id.equals("smspredefini")) {
			return NLS.constants().smsPredefini_name();
		}
		if (id.equals("outbox")) {
			return NLS.constants().outBox_name();
		}
		if (id.equals("utilisateur")) {
			return NLS.constants().utilisateur_name();
		}

		// For admin card entities

		if (id.equals("profile")) {
			return AdminNLS.constants().profile_name();
		}

		if (id.equals("entityProfile")) {
			return AdminNLS.constants().entityProfile_name();
		}

		if (id.equals("fieldGroupProfile")) {
			return AdminNLS.constants().fieldGroupProfile_name();
		}

		if (id.equals("cardEntity")) {
			return AdminNLS.constants().cardEntity_name();
		}

		if (id.equals("fieldGroup")) {
			return AdminNLS.constants().fieldGroup_name();
		}

		if (id.equals("notification")) {
			return AdminNLS.constants().notification_name();
		}

		if (id.equals("dynamicFieldTemplate")) {
			return DynFieldsNLS.constants().dynamicField_Template_name();
		}

		if (id.equals("dynamicFieldInstance")) {
			return DynFieldsNLS.constants().dynamicField_Instance_name();
		}

		if (id.equals("binary")) {
			return AdminNLS.constants().binary_name();
		}

		if (id.equals("defaultUser")) {
			return AdminNLS.constants().imogActor_name();
		}
		return "";
	}

	private String getDisplayValue(FieldGroupProxy bean) {
		String id = bean.getId();

		if (id.equals("patient.identification")) {
			return NLS.constants().patient_group_identification();
		}
		if (id.equals("patient.contact")) {
			return NLS.constants().patient_group_contact();
		}
		if (id.equals("patient.personnecontact")) {
			return NLS.constants().patient_group_personneContact();
		}
		if (id.equals("patient.tuberculose")) {
			return NLS.constants().patient_group_tuberculose();
		}
		if (id.equals("patient.examens")) {
			return NLS.constants().patient_group_examens();
		}
		if (id.equals("casindex.description")) {
			return NLS.constants().casIndex_group_description();
		}
		if (id.equals("castuberculose.informations")) {
			return NLS.constants().casTuberculose_group_informations();
		}
		if (id.equals("castuberculose.examen")) {
			return NLS.constants().casTuberculose_group_examen();
		}
		if (id.equals("castuberculose.traitement")) {
			return NLS.constants().casTuberculose_group_traitement();
		}
		if (id.equals("castuberculose.fintraitement")) {
			return NLS.constants().casTuberculose_group_finTraitement();
		}
		if (id.equals("examenserologie.description")) {
			return NLS.constants().examenSerologie_group_description();
		}
		if (id.equals("examenbiologique.description")) {
			return NLS.constants().examenBiologique_group_description();
		}
		if (id.equals("examenmicroscopie.centreexamen")) {
			return NLS.constants().examenMicroscopie_group_centreExamen();
		}
		if (id.equals("examenmicroscopie.examen")) {
			return NLS.constants().examenMicroscopie_group_examen();
		}
		if (id.equals("examenatb.centreexamen")) {
			return NLS.constants().examenATB_group_centreExamen();
		}
		if (id.equals("examenatb.examen")) {
			return NLS.constants().examenATB_group_examen();
		}
		if (id.equals("prisemedicamenteuse.description")) {
			return NLS.constants().priseMedicamenteuse_group_description();
		}
		if (id.equals("rendezvous.description")) {
			return NLS.constants().rendezVous_group_description();
		}
		if (id.equals("transfertreference.informationsdepart")) {
			return NLS.constants()
					.transfertReference_group_informationsDepart();
		}
		if (id.equals("transfertreference.informationarrivee")) {
			return NLS.constants()
					.transfertReference_group_informationArrivee();
		}
		if (id.equals("lot.description")) {
			return NLS.constants().lot_group_description();
		}
		if (id.equals("horsusage.description")) {
			return NLS.constants().horsUsage_group_description();
		}
		if (id.equals("entreelot.description")) {
			return NLS.constants().entreeLot_group_description();
		}
		if (id.equals("sortielot.description")) {
			return NLS.constants().sortieLot_group_description();
		}
		if (id.equals("commande.informationsdepart")) {
			return NLS.constants().commande_group_informationsDepart();
		}
		if (id.equals("commande.regionapprobation")) {
			return NLS.constants().commande_group_regionApprobation();
		}
		if (id.equals("commande.gtcapprobation")) {
			return NLS.constants().commande_group_gtcApprobation();
		}
		if (id.equals("detailcommandemedicament.description")) {
			return NLS.constants().detailCommandeMedicament_group_description();
		}
		if (id.equals("detailcommandeintrant.description")) {
			return NLS.constants().detailCommandeIntrant_group_description();
		}
		if (id.equals("reception.description")) {
			return NLS.constants().reception_group_description();
		}
		if (id.equals("detailreceptionmedicament.description")) {
			return NLS.constants()
					.detailReceptionMedicament_group_description();
		}
		if (id.equals("detailreceptionintrant.description")) {
			return NLS.constants().detailReceptionIntrant_group_description();
		}
		if (id.equals("ravitaillement.informationsdepart")) {
			return NLS.constants().ravitaillement_group_informationsDepart();
		}
		if (id.equals("ravitaillement.informationarrivee")) {
			return NLS.constants().ravitaillement_group_informationArrivee();
		}
		if (id.equals("ravitaillement.detail")) {
			return NLS.constants().ravitaillement_group_detail();
		}
		if (id.equals("detailravitaillement.description")) {
			return NLS.constants().detailRavitaillement_group_description();
		}
		if (id.equals("detailravitaillement.sortie")) {
			return NLS.constants().detailRavitaillement_group_sortie();
		}
		if (id.equals("detailravitaillement.entree")) {
			return NLS.constants().detailRavitaillement_group_entree();
		}
		if (id.equals("inventaire.informationsdepart")) {
			return NLS.constants().inventaire_group_informationsDepart();
		}
		if (id.equals("detailinventaire.description")) {
			return NLS.constants().detailInventaire_group_description();
		}
		if (id.equals("personnel.identification")) {
			return NLS.constants().personnel_group_identification();
		}
		if (id.equals("personnel.contact")) {
			return NLS.constants().personnel_group_contact();
		}
		if (id.equals("personnel.situation")) {
			return NLS.constants().personnel_group_situation();
		}
		if (id.equals("personnel.niveauaccess")) {
			return NLS.constants().personnel_group_niveauAccess();
		}
		if (id.equals("departpersonnel.personnel")) {
			return NLS.constants().departPersonnel_group_personnel();
		}
		if (id.equals("departpersonnel.description")) {
			return NLS.constants().departPersonnel_group_description();
		}
		if (id.equals("arriveepersonnel.description")) {
			return NLS.constants().arriveePersonnel_group_description();
		}
		if (id.equals("region.description")) {
			return NLS.constants().region_group_description();
		}
		if (id.equals("region.adresse")) {
			return NLS.constants().region_group_adresse();
		}
		if (id.equals("districtsante.description")) {
			return NLS.constants().districtSante_group_description();
		}
		if (id.equals("districtsante.adresse")) {
			return NLS.constants().districtSante_group_adresse();
		}
		if (id.equals("centrediagtrait.description")) {
			return NLS.constants().centreDiagTrait_group_description();
		}
		if (id.equals("centrediagtrait.adresse")) {
			return NLS.constants().centreDiagTrait_group_adresse();
		}
		if (id.equals("laboratoirereference.description")) {
			return NLS.constants().laboratoireReference_group_description();
		}
		if (id.equals("laboratoirereference.adresse")) {
			return NLS.constants().laboratoireReference_group_adresse();
		}
		if (id.equals("lieudit.description")) {
			return NLS.constants().lieuDit_group_description();
		}
		if (id.equals("lieudit.adresse")) {
			return NLS.constants().lieuDit_group_adresse();
		}
		if (id.equals("regime.description")) {
			return NLS.constants().regime_group_description();
		}
		if (id.equals("prisemedicamentregime.description")) {
			return NLS.constants().priseMedicamentRegime_group_description();
		}
		if (id.equals("medicament.description")) {
			return NLS.constants().medicament_group_description();
		}
		if (id.equals("intrant.description")) {
			return NLS.constants().intrant_group_description();
		}
		if (id.equals("formation.description")) {
			return NLS.constants().formation_group_description();
		}
		if (id.equals("candidatureformation.description")) {
			return NLS.constants().candidatureFormation_group_description();
		}
		if (id.equals("candidatureformation.regionapprobation")) {
			return NLS.constants()
					.candidatureFormation_group_regionApprobation();
		}
		if (id.equals("candidatureformation.gtcapprobation")) {
			return NLS.constants().candidatureFormation_group_gtcApprobation();
		}
		if (id.equals("qualification.description")) {
			return NLS.constants().qualification_group_description();
		}
		if (id.equals("tutoriel.description")) {
			return NLS.constants().tutoriel_group_description();
		}
		if (id.equals("smspredefini.description")) {
			return NLS.constants().smsPredefini_group_description();
		}
		if (id.equals("outbox.adresse")) {
			return NLS.constants().outBox_group_adresse();
		}
		if (id.equals("outbox.messageinformation")) {
			return NLS.constants().outBox_group_messageInformation();
		}
		if (id.equals("utilisateur.identification")) {
			return NLS.constants().utilisateur_group_identification();
		}
		if (id.equals("utilisateur.contact")) {
			return NLS.constants().utilisateur_group_contact();
		}

		return "";
	}

	@Override
	public String getEnumDisplayValue(Class<?> beanClass, String fieldName,
			String fieldValue) {
		return "";
	}

}
