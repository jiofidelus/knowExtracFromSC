package org.imogene.epicam.client;

import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.util.BooleanUtil;
import org.imogene.web.client.util.DateUtil;
import org.imogene.web.client.util.ImogBeanRenderer;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.LocalizedTextProxy;

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
 * Singleton that enables to render a display value of the beans
 * @author MEDES-IMPS
 */
public class EpicamRenderer extends ImogBeanRenderer {

	private static EpicamRenderer instance = new EpicamRenderer();

	private EpicamRenderer() {
		super();
	}

	public static EpicamRenderer get() {
		return instance;
	}

	/**
	 * Get display representation for a ImogBeanProxy     
	 * @param bean the ImogBeanProxy
	 * @return string representation for IHM display
	 */
	public String getDisplayValue(ImogBeanProxy bean) {

		if (bean.getDeleted() != null)
			return BaseNLS.constants().entity_deleted();

		if (bean instanceof PatientProxy) {
			return getDisplayValue((PatientProxy) bean);
		}
		if (bean instanceof CasIndexProxy) {
			return getDisplayValue((CasIndexProxy) bean);
		}
		if (bean instanceof CasTuberculoseProxy) {
			return getDisplayValue((CasTuberculoseProxy) bean);
		}
		if (bean instanceof ExamenSerologieProxy) {
			return getDisplayValue((ExamenSerologieProxy) bean);
		}
		if (bean instanceof ExamenBiologiqueProxy) {
			return getDisplayValue((ExamenBiologiqueProxy) bean);
		}
		if (bean instanceof ExamenMicroscopieProxy) {
			return getDisplayValue((ExamenMicroscopieProxy) bean);
		}
		if (bean instanceof ExamenATBProxy) {
			return getDisplayValue((ExamenATBProxy) bean);
		}
		if (bean instanceof PriseMedicamenteuseProxy) {
			return getDisplayValue((PriseMedicamenteuseProxy) bean);
		}
		if (bean instanceof RendezVousProxy) {
			return getDisplayValue((RendezVousProxy) bean);
		}
		if (bean instanceof TransfertReferenceProxy) {
			return getDisplayValue((TransfertReferenceProxy) bean);
		}
		if (bean instanceof LotProxy) {
			return getDisplayValue((LotProxy) bean);
		}
		if (bean instanceof HorsUsageProxy) {
			return getDisplayValue((HorsUsageProxy) bean);
		}
		if (bean instanceof EntreeLotProxy) {
			return getDisplayValue((EntreeLotProxy) bean);
		}
		if (bean instanceof SortieLotProxy) {
			return getDisplayValue((SortieLotProxy) bean);
		}
		if (bean instanceof CommandeProxy) {
			return getDisplayValue((CommandeProxy) bean);
		}
		if (bean instanceof DetailCommandeMedicamentProxy) {
			return getDisplayValue((DetailCommandeMedicamentProxy) bean);
		}
		if (bean instanceof DetailCommandeIntrantProxy) {
			return getDisplayValue((DetailCommandeIntrantProxy) bean);
		}
		if (bean instanceof ReceptionProxy) {
			return getDisplayValue((ReceptionProxy) bean);
		}
		if (bean instanceof DetailReceptionMedicamentProxy) {
			return getDisplayValue((DetailReceptionMedicamentProxy) bean);
		}
		if (bean instanceof DetailReceptionIntrantProxy) {
			return getDisplayValue((DetailReceptionIntrantProxy) bean);
		}
		if (bean instanceof RavitaillementProxy) {
			return getDisplayValue((RavitaillementProxy) bean);
		}
		if (bean instanceof DetailRavitaillementProxy) {
			return getDisplayValue((DetailRavitaillementProxy) bean);
		}
		if (bean instanceof InventaireProxy) {
			return getDisplayValue((InventaireProxy) bean);
		}
		if (bean instanceof DetailInventaireProxy) {
			return getDisplayValue((DetailInventaireProxy) bean);
		}
		if (bean instanceof PersonnelProxy) {
			return getDisplayValue((PersonnelProxy) bean);
		}
		if (bean instanceof DepartPersonnelProxy) {
			return getDisplayValue((DepartPersonnelProxy) bean);
		}
		if (bean instanceof ArriveePersonnelProxy) {
			return getDisplayValue((ArriveePersonnelProxy) bean);
		}
		if (bean instanceof RegionProxy) {
			return getDisplayValue((RegionProxy) bean);
		}
		if (bean instanceof DistrictSanteProxy) {
			return getDisplayValue((DistrictSanteProxy) bean);
		}
		if (bean instanceof CentreDiagTraitProxy) {
			return getDisplayValue((CentreDiagTraitProxy) bean);
		}
		if (bean instanceof LaboratoireReferenceProxy) {
			return getDisplayValue((LaboratoireReferenceProxy) bean);
		}
		if (bean instanceof LieuDitProxy) {
			return getDisplayValue((LieuDitProxy) bean);
		}
		if (bean instanceof RegimeProxy) {
			return getDisplayValue((RegimeProxy) bean);
		}
		if (bean instanceof PriseMedicamentRegimeProxy) {
			return getDisplayValue((PriseMedicamentRegimeProxy) bean);
		}
		if (bean instanceof MedicamentProxy) {
			return getDisplayValue((MedicamentProxy) bean);
		}
		if (bean instanceof IntrantProxy) {
			return getDisplayValue((IntrantProxy) bean);
		}
		if (bean instanceof FormationProxy) {
			return getDisplayValue((FormationProxy) bean);
		}
		if (bean instanceof CandidatureFormationProxy) {
			return getDisplayValue((CandidatureFormationProxy) bean);
		}
		if (bean instanceof QualificationProxy) {
			return getDisplayValue((QualificationProxy) bean);
		}
		if (bean instanceof TutorielProxy) {
			return getDisplayValue((TutorielProxy) bean);
		}
		if (bean instanceof SmsPredefiniProxy) {
			return getDisplayValue((SmsPredefiniProxy) bean);
		}
		if (bean instanceof OutBoxProxy) {
			return getDisplayValue((OutBoxProxy) bean);
		}
		if (bean instanceof UtilisateurProxy) {
			return getDisplayValue((UtilisateurProxy) bean);
		}
		return "";
	}

	/**	 */
	private String getDisplayValue(PatientProxy bean) {
		String value = new String();
		if (bean.getIdentifiant() != null) {
			value = value + bean.getIdentifiant() + " ";
		}
		if (bean.getNom() != null) {
			value = value + bean.getNom() + " ";
		}

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(CasIndexProxy bean) {
		String value = new String();

		if (bean.getPatient() != null)
			value = value + getDisplayValue(bean.getPatient()) + " ";

		if (bean.getTypeRelation() != null) {
			value = value + bean.getTypeRelation() + " ";
		}

		if (bean.getPatientLie() != null)
			value = value + getDisplayValue(bean.getPatientLie()) + " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(CasTuberculoseProxy bean) {
		String value = new String();

		if (bean.getPatient() != null)
			value = value + getDisplayValue(bean.getPatient()) + " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(ExamenSerologieProxy bean) {
		String value = new String();
		if (bean.getDateTest() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateTest()) + " ";
		String nature = bean.getNature();
		if (nature != null) {
			if (nature.equals(EpicamEnumConstants.EXAMENSEROLOGIE_NATURE_VIH))
				value = value
						+ NLS.constants().examenSerologie_nature_vIH_option()
						+ " ";
			else if (nature
					.equals(EpicamEnumConstants.EXAMENSEROLOGIE_NATURE_CD4))
				value = value
						+ NLS.constants().examenSerologie_nature_cD4_option()
						+ " ";
		}
		if (bean.getResultatCD4() != null)
			value = value + bean.getResultatCD4() + " ";
		String resultatVIH = bean.getResultatVIH();
		if (resultatVIH != null) {
			if (resultatVIH
					.equals(EpicamEnumConstants.EXAMENSEROLOGIE_RESULTATVIH_POSITIF))
				value = value
						+ NLS.constants()
								.examenSerologie_resultatVIH_positif_option()
						+ " ";
			else if (resultatVIH
					.equals(EpicamEnumConstants.EXAMENSEROLOGIE_RESULTATVIH_NEGATIF))
				value = value
						+ NLS.constants()
								.examenSerologie_resultatVIH_negatif_option()
						+ " ";
		}

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(ExamenBiologiqueProxy bean) {
		String value = new String();
		if (bean.getDate() != null)
			value = value + DateUtil.getFormatedDate(bean.getDate()) + " ";
		if (bean.getPoids() != null)
			value = value + bean.getPoids() + " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(ExamenMicroscopieProxy bean) {
		String value = new String();
		if (bean.getDate() != null)
			value = value + DateUtil.getFormatedDate(bean.getDate()) + " ";
		String resultat = bean.getResultat();
		if (resultat != null) {
			if (resultat
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_NEGATIF))
				value = value
						+ NLS.constants()
								.examenMicroscopie_resultat_negatif_option()
						+ " ";
			else if (resultat
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_RARE))
				value = value
						+ NLS.constants()
								.examenMicroscopie_resultat_rare_option() + " ";
			else if (resultat
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_UNPLUS))
				value = value
						+ NLS.constants()
								.examenMicroscopie_resultat_unPlus_option()
						+ " ";
			else if (resultat
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_DEUXPLUS))
				value = value
						+ NLS.constants()
								.examenMicroscopie_resultat_deuxPlus_option()
						+ " ";
			else if (resultat
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_TROISPLUS))
				value = value
						+ NLS.constants()
								.examenMicroscopie_resultat_troisPlus_option()
						+ " ";
		}

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(ExamenATBProxy bean) {
		String value = new String();
		if (bean.getDateExamen() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateExamen())
					+ " ";
		if (bean.getResultat() != null) {
			value = value + bean.getResultat() + " ";
		}

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(PriseMedicamenteuseProxy bean) {
		String value = new String();
		if (bean.getDateEffective() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateEffective())
					+ " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(RendezVousProxy bean) {
		String value = new String();
		if (bean.getDateRendezVous() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateRendezVous())
					+ " ";

		value = value + BooleanUtil.getBooleanDisplayValue(bean.getHonore())
				+ " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(TransfertReferenceProxy bean) {
		String value = new String();

		if (bean.getPatient() != null)
			value = value + getDisplayValue(bean.getPatient()) + " ";

		String nature = bean.getNature();
		if (nature != null) {
			if (nature
					.equals(EpicamEnumConstants.TRANSFERTREFERENCE_NATURE_TRANSFERT))
				value = value
						+ NLS.constants()
								.transfertReference_nature_transfert_option()
						+ " ";
			else if (nature
					.equals(EpicamEnumConstants.TRANSFERTREFERENCE_NATURE_REFERENCE))
				value = value
						+ NLS.constants()
								.transfertReference_nature_reference_option()
						+ " ";
		}
		if (bean.getDateDepart() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateDepart())
					+ " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(LotProxy bean) {
		String value = new String();
		if (bean.getNumero() != null) {
			value = value + bean.getNumero() + " ";
		}

		if (bean.getIntrant() != null)
			value = value + getDisplayValue(bean.getIntrant()) + " ";

		if (bean.getMedicament() != null)
			value = value + getDisplayValue(bean.getMedicament()) + " ";

		if (bean.getQuantite() != null)
			value = value + bean.getQuantite() + " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(HorsUsageProxy bean) {
		String value = new String();
		String type = bean.getType();
		if (type != null) {
			if (type.equals(EpicamEnumConstants.HORSUSAGE_TYPE_PERIMEE))
				value = value + NLS.constants().horsUsage_type_perimee_option()
						+ " ";
			else if (type.equals(EpicamEnumConstants.HORSUSAGE_TYPE_CASSE))
				value = value + NLS.constants().horsUsage_type_casse_option()
						+ " ";
		}

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(EntreeLotProxy bean) {
		String value = new String();

		if (bean.getLot() != null)
			value = value + getDisplayValue(bean.getLot()) + " ";

		if (bean.getQuantite() != null)
			value = value + bean.getQuantite() + " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(SortieLotProxy bean) {
		String value = new String();

		if (bean.getLot() != null)
			value = value + getDisplayValue(bean.getLot()) + " ";

		if (bean.getQuantite() != null)
			value = value + bean.getQuantite() + " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(CommandeProxy bean) {
		String value = new String();

		if (bean.getCDT() != null)
			value = value + getDisplayValue(bean.getCDT()) + " ";

		if (bean.getDate() != null)
			value = value + DateUtil.getFormatedDate(bean.getDate()) + " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(DetailCommandeMedicamentProxy bean) {
		String value = new String();

		if (bean.getMedicament() != null)
			value = value + getDisplayValue(bean.getMedicament()) + " ";

		if (bean.getQuantiteRequise() != null)
			value = value + bean.getQuantiteRequise() + " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(DetailCommandeIntrantProxy bean) {
		String value = new String();

		if (bean.getIntrant() != null)
			value = value + getDisplayValue(bean.getIntrant()) + " ";

		if (bean.getQuantiteRequise() != null)
			value = value + bean.getQuantiteRequise() + " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(ReceptionProxy bean) {
		String value = new String();

		if (bean.getCDT() != null)
			value = value + getDisplayValue(bean.getCDT()) + " ";

		if (bean.getDateReception() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateReception())
					+ " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(DetailReceptionMedicamentProxy bean) {
		String value = new String();

		if (bean.getDetailCommande() != null)
			value = value + getDisplayValue(bean.getDetailCommande()) + " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(DetailReceptionIntrantProxy bean) {
		String value = new String();

		if (bean.getDetailCommande() != null)
			value = value + getDisplayValue(bean.getDetailCommande()) + " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(RavitaillementProxy bean) {
		String value = new String();

		if (bean.getCDTDepart() != null)
			value = value + getDisplayValue(bean.getCDTDepart()) + " ";

		if (bean.getDateDepart() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateDepart())
					+ " ";

		if (bean.getCDTArrivee() != null)
			value = value + getDisplayValue(bean.getCDTArrivee()) + " ";

		if (bean.getDateArrivee() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateArrivee())
					+ " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(DetailRavitaillementProxy bean) {
		String value = new String();

		if (bean.getRavitaillement() != null)
			value = value + getDisplayValue(bean.getRavitaillement()) + " ";

		if (bean.getSortieLot() != null)
			value = value + getDisplayValue(bean.getSortieLot()) + " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(InventaireProxy bean) {
		String value = new String();

		if (bean.getCDT() != null)
			value = value + getDisplayValue(bean.getCDT()) + " ";

		if (bean.getDate() != null)
			value = value + DateUtil.getFormatedDate(bean.getDate()) + " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(DetailInventaireProxy bean) {
		String value = new String();

		if (bean.getLot() != null)
			value = value + getDisplayValue(bean.getLot()) + " ";

		if (bean.getQuantiteTheorique() != null)
			value = value + bean.getQuantiteTheorique() + " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(PersonnelProxy bean) {
		String value = new String();
		if (bean.getNom() != null) {
			value = value + bean.getNom() + " ";
		}

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(DepartPersonnelProxy bean) {
		String value = new String();
		if (bean.getDateDepart() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateDepart())
					+ " ";

		if (bean.getPersonnel() != null)
			value = value + getDisplayValue(bean.getPersonnel()) + " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(ArriveePersonnelProxy bean) {
		String value = new String();
		if (bean.getDateArrivee() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateArrivee())
					+ " ";

		if (bean.getPersonnel() != null)
			value = value + getDisplayValue(bean.getPersonnel()) + " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(RegionProxy bean) {
		String value = new String();
		if (bean.getNom() != null) {
			value = value + getLocalizedText(bean.getNom()) + " ";
		}

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(DistrictSanteProxy bean) {
		String value = new String();
		if (bean.getNom() != null) {
			value = value + getLocalizedText(bean.getNom()) + " ";
		}

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(CentreDiagTraitProxy bean) {
		String value = new String();
		if (bean.getNom() != null) {
			value = value + bean.getNom() + " ";
		}

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(LaboratoireReferenceProxy bean) {
		String value = new String();
		if (bean.getNom() != null) {
			value = value + getLocalizedText(bean.getNom()) + " ";
		}

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(LieuDitProxy bean) {
		String value = new String();
		if (bean.getNom() != null) {
			value = value + bean.getNom() + " ";
		}

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(RegimeProxy bean) {
		String value = new String();
		if (bean.getNom() != null) {
			value = value + bean.getNom() + " ";
		}

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(PriseMedicamentRegimeProxy bean) {
		String value = new String();

		if (bean.getMedicament() != null)
			value = value + getDisplayValue(bean.getMedicament()) + " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(MedicamentProxy bean) {
		String value = new String();
		if (bean.getDesignation() != null) {
			value = value + bean.getDesignation() + " ";
		}

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(IntrantProxy bean) {
		String value = new String();
		if (bean.getIdentifiant() != null) {
			value = value + bean.getIdentifiant() + " ";
		}

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(FormationProxy bean) {
		String value = new String();
		if (bean.getLibelle() != null) {
			value = value + getLocalizedText(bean.getLibelle()) + " ";
		}
		if (bean.getDateDebut() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateDebut()) + " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(CandidatureFormationProxy bean) {
		String value = new String();

		if (bean.getPersonnel() != null)
			value = value + getDisplayValue(bean.getPersonnel()) + " ";

		value = value
				+ BooleanUtil.getBooleanDisplayValue(bean.getApprouveeRegion())
				+ " ";

		value = value
				+ BooleanUtil.getBooleanDisplayValue(bean.getApprouveeGTC())
				+ " ";

		if (bean.getDistrictSante() != null)
			value = value + getDisplayValue(bean.getDistrictSante()) + " ";

		if (bean.getCDT() != null)
			value = value + getDisplayValue(bean.getCDT()) + " ";

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(QualificationProxy bean) {
		String value = new String();
		if (bean.getNom() != null) {
			value = value + getLocalizedText(bean.getNom()) + " ";
		}

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(TutorielProxy bean) {
		String value = new String();
		if (bean.getNom() != null) {
			value = value + getLocalizedText(bean.getNom()) + " ";
		}

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(SmsPredefiniProxy bean) {
		String value = new String();
		String type = bean.getType();
		if (type != null) {
			if (type.equals(EpicamEnumConstants.SMSPREDEFINI_TYPE_SENSIBILISATION))
				value = value
						+ NLS.constants()
								.smsPredefini_type_sensibilisation_option()
						+ " ";
			else if (type.equals(EpicamEnumConstants.SMSPREDEFINI_TYPE_QUIZZ))
				value = value
						+ NLS.constants().smsPredefini_type_quizz_option()
						+ " ";
			else if (type
					.equals(EpicamEnumConstants.SMSPREDEFINI_TYPE_RAPPELRDV))
				value = value
						+ NLS.constants().smsPredefini_type_rappelRDV_option()
						+ " ";
			else if (type
					.equals(EpicamEnumConstants.SMSPREDEFINI_TYPE_MEDICALRECORD))
				value = value
						+ NLS.constants()
								.smsPredefini_type_medicalRecord_option() + " ";
		}
		if (bean.getObjet() != null) {
			value = value + getLocalizedText(bean.getObjet()) + " ";
		}

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(OutBoxProxy bean) {
		String value = new String();
		if (bean.getMessage() != null) {
			value = value + bean.getMessage() + " ";
		}

		return value.trim();
	}
	/**	 */
	private String getDisplayValue(UtilisateurProxy bean) {
		String value = new String();
		if (bean.getNom() != null) {
			value = value + bean.getNom() + " ";
		}

		return value.trim();
	}

	/**
	 * Get an enumeration representation for a ImogBeanProxy type enumeration field
	 * @param beanClass a ImogBeanProxy class type
	 * @param fieldName the ImogBeanProxy field name     
	 * @param fieldValue the bean field value    
	 * @return string representation for IHM display
	 */
	public String getEnumDisplayValue(Class<?> beanClass, String fieldName,
			String fieldValue) {

		if (fieldValue != null && !fieldValue.equals("")) {

			if (beanClass.equals(PatientProxy.class)) {
				return getPatientEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(CasIndexProxy.class)) {
				return getCasIndexEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(CasTuberculoseProxy.class)) {
				return getCasTuberculoseEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(ExamenSerologieProxy.class)) {
				return getExamenSerologieEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(ExamenBiologiqueProxy.class)) {
				return getExamenBiologiqueEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(ExamenMicroscopieProxy.class)) {
				return getExamenMicroscopieEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(ExamenATBProxy.class)) {
				return getExamenATBEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(PriseMedicamenteuseProxy.class)) {
				return getPriseMedicamenteuseEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(RendezVousProxy.class)) {
				return getRendezVousEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(TransfertReferenceProxy.class)) {
				return getTransfertReferenceEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(LotProxy.class)) {
				return getLotEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(HorsUsageProxy.class)) {
				return getHorsUsageEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(EntreeLotProxy.class)) {
				return getEntreeLotEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(SortieLotProxy.class)) {
				return getSortieLotEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(CommandeProxy.class)) {
				return getCommandeEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(DetailCommandeMedicamentProxy.class)) {
				return getDetailCommandeMedicamentEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(DetailCommandeIntrantProxy.class)) {
				return getDetailCommandeIntrantEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(ReceptionProxy.class)) {
				return getReceptionEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(DetailReceptionMedicamentProxy.class)) {
				return getDetailReceptionMedicamentEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(DetailReceptionIntrantProxy.class)) {
				return getDetailReceptionIntrantEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(RavitaillementProxy.class)) {
				return getRavitaillementEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(DetailRavitaillementProxy.class)) {
				return getDetailRavitaillementEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(InventaireProxy.class)) {
				return getInventaireEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(DetailInventaireProxy.class)) {
				return getDetailInventaireEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(PersonnelProxy.class)) {
				return getPersonnelEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(DepartPersonnelProxy.class)) {
				return getDepartPersonnelEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(ArriveePersonnelProxy.class)) {
				return getArriveePersonnelEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(RegionProxy.class)) {
				return getRegionEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(DistrictSanteProxy.class)) {
				return getDistrictSanteEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(CentreDiagTraitProxy.class)) {
				return getCentreDiagTraitEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(LaboratoireReferenceProxy.class)) {
				return getLaboratoireReferenceEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(LieuDitProxy.class)) {
				return getLieuDitEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(RegimeProxy.class)) {
				return getRegimeEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(PriseMedicamentRegimeProxy.class)) {
				return getPriseMedicamentRegimeEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(MedicamentProxy.class)) {
				return getMedicamentEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(IntrantProxy.class)) {
				return getIntrantEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(FormationProxy.class)) {
				return getFormationEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(CandidatureFormationProxy.class)) {
				return getCandidatureFormationEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(QualificationProxy.class)) {
				return getQualificationEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(TutorielProxy.class)) {
				return getTutorielEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(SmsPredefiniProxy.class)) {
				return getSmsPredefiniEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(OutBoxProxy.class)) {
				return getOutBoxEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(UtilisateurProxy.class)) {
				return getUtilisateurEnumDisplayValue(fieldName, fieldValue);
			}

		}
		return "";
	}

	/**
	 *
	 */
	private String getPatientEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		if (fieldName.equals("sexe")) {

			if (fieldValue.equals(EpicamEnumConstants.PATIENT_SEXE_MASCULIN))
				value = NLS.constants().patient_sexe_masculin_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.PATIENT_SEXE_FEMININ))
				value = NLS.constants().patient_sexe_feminin_option();

		}

		if (fieldName.equals("nationalite")) {

			if (fieldValue
					.equals(EpicamEnumConstants.PATIENT_NATIONALITE_CAMEROUNAIS))
				value = NLS.constants()
						.patient_nationalite_camerounais_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.PATIENT_NATIONALITE_ETRANGER))
				value = NLS.constants().patient_nationalite_etranger_option();

		}

		if (fieldName.equals("libelle")) {

			if (fieldValue.equals(EpicamEnumConstants.PATIENT_LIBELLE_DOMICILE))
				value = NLS.constants().patient_libelle_domicile_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.PATIENT_LIBELLE_BUREAU))
				value = NLS.constants().patient_libelle_bureau_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.PATIENT_LIBELLE_AUTRE))
				value = NLS.constants().patient_libelle_autre_option();

		}

		if (fieldName.equals("pacLibelle")) {

			if (fieldValue
					.equals(EpicamEnumConstants.PATIENT_PACLIBELLE_DOMICILE))
				value = NLS.constants().patient_pacLibelle_domicile_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.PATIENT_PACLIBELLE_BUREAU))
				value = NLS.constants().patient_pacLibelle_bureau_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.PATIENT_PACLIBELLE_AUTRE))
				value = NLS.constants().patient_pacLibelle_autre_option();

		}

		return value.trim();
	}

	/**
	 *
	 */
	private String getCasIndexEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getCasTuberculoseEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		if (fieldName.equals("typePatient")) {

			if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_NOUVEAUCAS))
				value = NLS.constants()
						.casTuberculose_typePatient_nouveauCas_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_REPRISEAPRESABANDON))
				value = NLS
						.constants()
						.casTuberculose_typePatient_repriseApresAbandon_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_ECHEC))
				value = NLS.constants()
						.casTuberculose_typePatient_echec_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_RECHUTE))
				value = NLS.constants()
						.casTuberculose_typePatient_rechute_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_AUTRE))
				value = NLS.constants()
						.casTuberculose_typePatient_autre_option();

		}

		if (fieldName.equals("formeMaladie")) {

			if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_FORMEMALADIE_TPMPLUS))
				value = NLS.constants()
						.casTuberculose_formeMaladie_tPMPlus_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_FORMEMALADIE_TPMMOINS))
				value = NLS.constants()
						.casTuberculose_formeMaladie_tPMMoins_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_FORMEMALADIE_EXTRA_PULMONAIRE))
				value = NLS.constants()
						.casTuberculose_formeMaladie_extra_Pulmonaire_option();

		}

		if (fieldName.equals("cotrimoxazole")) {

			if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_COTRIMOXAZOLE_NON))
				value = NLS.constants()
						.casTuberculose_cotrimoxazole_non_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_COTRIMOXAZOLE_COTRIMOXAZOLE_960))
				value = NLS
						.constants()
						.casTuberculose_cotrimoxazole_cotrimoxazole_960_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_COTRIMOXAZOLE_COTRIMOXAZOLE_480))
				value = NLS
						.constants()
						.casTuberculose_cotrimoxazole_cotrimoxazole_480_option();

		}

		if (fieldName.equals("devenirMalade")) {

			if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_GUERRIS))
				value = NLS.constants()
						.casTuberculose_devenirMalade_guerris_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_TERMINE))
				value = NLS.constants()
						.casTuberculose_devenirMalade_termine_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_ECHEC))
				value = NLS.constants()
						.casTuberculose_devenirMalade_echec_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_DECEDE))
				value = NLS.constants()
						.casTuberculose_devenirMalade_decede_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_PERDUDEVUE))
				value = NLS.constants()
						.casTuberculose_devenirMalade_perduDeVue_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_ARRETPRESCRIPTEUR))
				value = NLS
						.constants()
						.casTuberculose_devenirMalade_arretPrescripteur_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_ARRETEFFETSINDESI))
				value = NLS
						.constants()
						.casTuberculose_devenirMalade_arretEffetsIndesi_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_ARRETSURVENUTB))
				value = NLS.constants()
						.casTuberculose_devenirMalade_arretSurvenuTB_option();

		}

		return value.trim();
	}

	/**
	 *
	 */
	private String getExamenSerologieEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		if (fieldName.equals("nature")) {

			if (fieldValue
					.equals(EpicamEnumConstants.EXAMENSEROLOGIE_NATURE_VIH))
				value = NLS.constants().examenSerologie_nature_vIH_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.EXAMENSEROLOGIE_NATURE_CD4))
				value = NLS.constants().examenSerologie_nature_cD4_option();

		}

		if (fieldName.equals("resultatVIH")) {

			if (fieldValue
					.equals(EpicamEnumConstants.EXAMENSEROLOGIE_RESULTATVIH_POSITIF))
				value = NLS.constants()
						.examenSerologie_resultatVIH_positif_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.EXAMENSEROLOGIE_RESULTATVIH_NEGATIF))
				value = NLS.constants()
						.examenSerologie_resultatVIH_negatif_option();

		}

		return value.trim();
	}

	/**
	 *
	 */
	private String getExamenBiologiqueEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getExamenMicroscopieEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		if (fieldName.equals("raisonDepistage")) {

			if (fieldValue
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RAISONDEPISTAGE_DIAGNOSTIC))
				value = NLS.constants()
						.examenMicroscopie_raisonDepistage_diagnostic_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RAISONDEPISTAGE_SUIVI))
				value = NLS.constants()
						.examenMicroscopie_raisonDepistage_suivi_option();

		}

		if (fieldName.equals("resultat")) {

			if (fieldValue
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_NEGATIF))
				value = NLS.constants()
						.examenMicroscopie_resultat_negatif_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_RARE))
				value = NLS.constants()
						.examenMicroscopie_resultat_rare_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_UNPLUS))
				value = NLS.constants()
						.examenMicroscopie_resultat_unPlus_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_DEUXPLUS))
				value = NLS.constants()
						.examenMicroscopie_resultat_deuxPlus_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_TROISPLUS))
				value = NLS.constants()
						.examenMicroscopie_resultat_troisPlus_option();

		}

		return value.trim();
	}

	/**
	 *
	 */
	private String getExamenATBEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		if (fieldName.equals("raisonDepistage")) {

			if (fieldValue
					.equals(EpicamEnumConstants.EXAMENATB_RAISONDEPISTAGE_DIAGNOSTIC))
				value = NLS.constants()
						.examenATB_raisonDepistage_diagnostic_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.EXAMENATB_RAISONDEPISTAGE_SUIVI))
				value = NLS.constants()
						.examenATB_raisonDepistage_suivi_option();

		}

		return value.trim();
	}

	/**
	 *
	 */
	private String getPriseMedicamenteuseEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		if (fieldName.equals("prise")) {

			if (fieldValue
					.equals(EpicamEnumConstants.PRISEMEDICAMENTEUSE_PRISE_PRISESUPERVISEE))
				value = NLS.constants()
						.priseMedicamenteuse_prise_priseSupervisee_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.PRISEMEDICAMENTEUSE_PRISE_AUTOMEDICATION))
				value = NLS.constants()
						.priseMedicamenteuse_prise_automedication_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.PRISEMEDICAMENTEUSE_PRISE_NONVENU))
				value = NLS.constants()
						.priseMedicamenteuse_prise_nonVenu_option();

		}

		return value.trim();
	}

	/**
	 *
	 */
	private String getRendezVousEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getTransfertReferenceEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		if (fieldName.equals("nature")) {

			if (fieldValue
					.equals(EpicamEnumConstants.TRANSFERTREFERENCE_NATURE_TRANSFERT))
				value = NLS.constants()
						.transfertReference_nature_transfert_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.TRANSFERTREFERENCE_NATURE_REFERENCE))
				value = NLS.constants()
						.transfertReference_nature_reference_option();

		}

		return value.trim();
	}

	/**
	 *
	 */
	private String getLotEnumDisplayValue(String fieldName, String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		if (fieldName.equals("type")) {

			if (fieldValue.equals(EpicamEnumConstants.LOT_TYPE_MEDICAMENT))
				value = NLS.constants().lot_type_medicament_option();
			else if (fieldValue.equals(EpicamEnumConstants.LOT_TYPE_INTRANT))
				value = NLS.constants().lot_type_intrant_option();

		}

		return value.trim();
	}

	/**
	 *
	 */
	private String getHorsUsageEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		if (fieldName.equals("type")) {

			if (fieldValue.equals(EpicamEnumConstants.HORSUSAGE_TYPE_PERIMEE))
				value = NLS.constants().horsUsage_type_perimee_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.HORSUSAGE_TYPE_CASSE))
				value = NLS.constants().horsUsage_type_casse_option();

		}

		return value.trim();
	}

	/**
	 *
	 */
	private String getEntreeLotEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getSortieLotEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getCommandeEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getDetailCommandeMedicamentEnumDisplayValue(
			String fieldName, String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getDetailCommandeIntrantEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getReceptionEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getDetailReceptionMedicamentEnumDisplayValue(
			String fieldName, String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getDetailReceptionIntrantEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getRavitaillementEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getDetailRavitaillementEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getInventaireEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getDetailInventaireEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getPersonnelEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		if (fieldName.equals("libelle")) {

			if (fieldValue
					.equals(EpicamEnumConstants.PERSONNEL_LIBELLE_DOMICILE))
				value = NLS.constants().personnel_libelle_domicile_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.PERSONNEL_LIBELLE_BUREAU))
				value = NLS.constants().personnel_libelle_bureau_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.PERSONNEL_LIBELLE_AUTRE))
				value = NLS.constants().personnel_libelle_autre_option();

		}

		if (fieldName.equals("niveau")) {

			if (fieldValue.equals(EpicamEnumConstants.PERSONNEL_NIVEAU_CENTRAL))
				value = NLS.constants().personnel_niveau_central_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.PERSONNEL_NIVEAU_REGION))
				value = NLS.constants().personnel_niveau_region_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.PERSONNEL_NIVEAU_DISTRICTSANTE))
				value = NLS.constants().personnel_niveau_districtSante_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.PERSONNEL_NIVEAU_CDT))
				value = NLS.constants().personnel_niveau_cDT_option();

		}

		return value.trim();
	}

	/**
	 *
	 */
	private String getDepartPersonnelEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getArriveePersonnelEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getRegionEnumDisplayValue(String fieldName, String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		if (fieldName.equals("libelle")) {

			if (fieldValue.equals(EpicamEnumConstants.REGION_LIBELLE_DOMICILE))
				value = NLS.constants().region_libelle_domicile_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.REGION_LIBELLE_BUREAU))
				value = NLS.constants().region_libelle_bureau_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.REGION_LIBELLE_AUTRE))
				value = NLS.constants().region_libelle_autre_option();

		}

		return value.trim();
	}

	/**
	 *
	 */
	private String getDistrictSanteEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		if (fieldName.equals("libelle")) {

			if (fieldValue
					.equals(EpicamEnumConstants.DISTRICTSANTE_LIBELLE_DOMICILE))
				value = NLS.constants().districtSante_libelle_domicile_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.DISTRICTSANTE_LIBELLE_BUREAU))
				value = NLS.constants().districtSante_libelle_bureau_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.DISTRICTSANTE_LIBELLE_AUTRE))
				value = NLS.constants().districtSante_libelle_autre_option();

		}

		return value.trim();
	}

	/**
	 *
	 */
	private String getCentreDiagTraitEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		if (fieldName.equals("libelle")) {

			if (fieldValue
					.equals(EpicamEnumConstants.CENTREDIAGTRAIT_LIBELLE_DOMICILE))
				value = NLS.constants()
						.centreDiagTrait_libelle_domicile_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.CENTREDIAGTRAIT_LIBELLE_BUREAU))
				value = NLS.constants().centreDiagTrait_libelle_bureau_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.CENTREDIAGTRAIT_LIBELLE_AUTRE))
				value = NLS.constants().centreDiagTrait_libelle_autre_option();

		}

		return value.trim();
	}

	/**
	 *
	 */
	private String getLaboratoireReferenceEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		if (fieldName.equals("nature")) {

			if (fieldValue
					.equals(EpicamEnumConstants.LABORATOIREREFERENCE_NATURE_NATIONAL))
				value = NLS.constants()
						.laboratoireReference_nature_national_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.LABORATOIREREFERENCE_NATURE_REGIONAL))
				value = NLS.constants()
						.laboratoireReference_nature_regional_option();

		}

		if (fieldName.equals("libelle")) {

			if (fieldValue
					.equals(EpicamEnumConstants.LABORATOIREREFERENCE_LIBELLE_DOMICILE))
				value = NLS.constants()
						.laboratoireReference_libelle_domicile_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.LABORATOIREREFERENCE_LIBELLE_BUREAU))
				value = NLS.constants()
						.laboratoireReference_libelle_bureau_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.LABORATOIREREFERENCE_LIBELLE_AUTRE))
				value = NLS.constants()
						.laboratoireReference_libelle_autre_option();

		}

		return value.trim();
	}

	/**
	 *
	 */
	private String getLieuDitEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		if (fieldName.equals("libelle")) {

			if (fieldValue.equals(EpicamEnumConstants.LIEUDIT_LIBELLE_DOMICILE))
				value = NLS.constants().lieuDit_libelle_domicile_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.LIEUDIT_LIBELLE_BUREAU))
				value = NLS.constants().lieuDit_libelle_bureau_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.LIEUDIT_LIBELLE_AUTRE))
				value = NLS.constants().lieuDit_libelle_autre_option();

		}

		return value.trim();
	}

	/**
	 *
	 */
	private String getRegimeEnumDisplayValue(String fieldName, String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		if (fieldName.equals("type")) {

			if (fieldValue
					.equals(EpicamEnumConstants.REGIME_TYPE_PHASEINITIALE))
				value = NLS.constants().regime_type_phaseInitiale_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.REGIME_TYPE_PHASECONTINUATION))
				value = NLS.constants().regime_type_phaseContinuation_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.REGIME_TYPE_INDEPENDANT))
				value = NLS.constants().regime_type_independant_option();

		}

		return value.trim();
	}

	/**
	 *
	 */
	private String getPriseMedicamentRegimeEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getMedicamentEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getIntrantEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getFormationEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getCandidatureFormationEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getQualificationEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		return value.trim();
	}

	/**
	 *
	 */
	private String getTutorielEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		if (fieldName.equals("type")) {

			if (fieldValue.equals(EpicamEnumConstants.TUTORIEL_TYPE_TEXTE))
				value = NLS.constants().tutoriel_type_texte_option();
			else if (fieldValue.equals(EpicamEnumConstants.TUTORIEL_TYPE_AUDIO))
				value = NLS.constants().tutoriel_type_audio_option();
			else if (fieldValue.equals(EpicamEnumConstants.TUTORIEL_TYPE_VIDEO))
				value = NLS.constants().tutoriel_type_video_option();

		}

		return value.trim();
	}

	/**
	 *
	 */
	private String getSmsPredefiniEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		if (fieldName.equals("type")) {

			if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_TYPE_SENSIBILISATION))
				value = NLS.constants()
						.smsPredefini_type_sensibilisation_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_TYPE_QUIZZ))
				value = NLS.constants().smsPredefini_type_quizz_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_TYPE_RAPPELRDV))
				value = NLS.constants().smsPredefini_type_rappelRDV_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_TYPE_MEDICALRECORD))
				value = NLS.constants()
						.smsPredefini_type_medicalRecord_option();

		}

		if (fieldName.equals("periodicite")) {

			if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_PERIODICITE_JOUR))
				value = NLS.constants().smsPredefini_periodicite_jour_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_PERIODICITE_SEMAINE))
				value = NLS.constants()
						.smsPredefini_periodicite_semaine_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_PERIODICITE_MOIS))
				value = NLS.constants().smsPredefini_periodicite_mois_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_PERIODICITE_TRIMESTRE))
				value = NLS.constants()
						.smsPredefini_periodicite_trimestre_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_PERIODICITE_SEMESTRE))
				value = NLS.constants()
						.smsPredefini_periodicite_semestre_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_PERIODICITE_PONCTUELLE))
				value = NLS.constants()
						.smsPredefini_periodicite_ponctuelle_option();

		}

		if (fieldName.equals("statut")) {

			if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_STATUT_ACTIF))
				value = NLS.constants().smsPredefini_statut_actif_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_STATUT_INACTIF))
				value = NLS.constants().smsPredefini_statut_inactif_option();

		}

		return value.trim();
	}

	/**
	 *
	 */
	private String getOutBoxEnumDisplayValue(String fieldName, String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		if (fieldName.equals("statut")) {

			if (fieldValue.equals(EpicamEnumConstants.OUTBOX_STATUT_ERREUR))
				value = NLS.constants().outBox_statut_erreur_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.OUTBOX_STATUT_AENVOYER))
				value = NLS.constants().outBox_statut_aEnvoyer_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.OUTBOX_STATUT_SUCCES))
				value = NLS.constants().outBox_statut_succes_option();

		}

		return value.trim();
	}

	/**
	 *
	 */
	private String getUtilisateurEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = BaseNLS.constants().enumeration_unknown();

		if (fieldName.equals("libelle")) {

			if (fieldValue
					.equals(EpicamEnumConstants.UTILISATEUR_LIBELLE_DOMICILE))
				value = NLS.constants().utilisateur_libelle_domicile_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.UTILISATEUR_LIBELLE_BUREAU))
				value = NLS.constants().utilisateur_libelle_bureau_option();
			else if (fieldValue
					.equals(EpicamEnumConstants.UTILISATEUR_LIBELLE_AUTRE))
				value = NLS.constants().utilisateur_libelle_autre_option();

		}

		return value.trim();
	}

	/**
	 * Gets the text corresponding to a given locale
	 * @param localizedText the localizedText for which the text has to be returned
	 * @return the text corresponding to a given locale (if empty, returns the first non empty text)
	 */
	public String getLocalizedText(LocalizedTextProxy localizedText) {

		String locale = NLS.constants().locale();

		if (localizedText != null) {

			String text = "";

			if (locale.equals("fr"))
				text = localizedText.getFrancais();

			if (locale.equals("en"))
				text = localizedText.getEnglish();

			if (text != null && !text.isEmpty())
				return text;
			else { // return first not empty text

				if (localizedText.getFrancais() != null
						&& !localizedText.getFrancais().isEmpty())
					return localizedText.getFrancais();

				if (localizedText.getEnglish() != null
						&& !localizedText.getEnglish().isEmpty())
					return localizedText.getEnglish();

			}
		}
		return "";
	}

	/**
	 * Internationalize the errors that are thrown on the server side
	 * Based on the implementation that error messages are keys to i18n property files
	 * @param key the message key
	 * @return the internalionalized message
	 */
	public String getI18nErrorMessage(String key) {

		if (key.equals("error_required"))
			return BaseNLS.messages().error_required();
		else if (key.equals("patient_field_age_min"))
			return BaseNLS.messages().error_min_num(
					NLS.constants().patient_field_age_min());
		else if (key.equals("patient_field_age_max"))
			return BaseNLS.messages().error_max_num(
					NLS.constants().patient_field_age_max());
		else if (key.equals("examenBiologique_field_poids_decimalNumber"))
			return BaseNLS.messages().error_float_dec(
					NLS.constants()
							.examenBiologique_field_poids_decimalNumber());
		else if (key.equals("lot_field_quantiteInitiale_min"))
			return BaseNLS.messages().error_min_num(
					NLS.constants().lot_field_quantiteInitiale_min());
		else if (key.equals("lot_field_quantite_min"))
			return BaseNLS.messages().error_min_num(
					NLS.constants().lot_field_quantite_min());
		else if (key.equals("entreeLot_field_quantite_min"))
			return BaseNLS.messages().error_min_num(
					NLS.constants().entreeLot_field_quantite_min());
		else if (key.equals("sortieLot_field_quantite_min"))
			return BaseNLS.messages().error_min_num(
					NLS.constants().sortieLot_field_quantite_min());
		else if (key
				.equals("detailCommandeMedicament_field_quantiteRequise_min"))
			return BaseNLS
					.messages()
					.error_min_num(
							NLS.constants()
									.detailCommandeMedicament_field_quantiteRequise_min());
		else if (key
				.equals("detailCommandeMedicament_field_quantiteEnStock_min"))
			return BaseNLS
					.messages()
					.error_min_num(
							NLS.constants()
									.detailCommandeMedicament_field_quantiteEnStock_min());
		else if (key.equals("detailCommandeIntrant_field_quantiteRequise_min"))
			return BaseNLS.messages().error_min_num(
					NLS.constants()
							.detailCommandeIntrant_field_quantiteRequise_min());
		else if (key.equals("detailCommandeIntrant_field_quantiteEnStock_min"))
			return BaseNLS.messages().error_min_num(
					NLS.constants()
							.detailCommandeIntrant_field_quantiteEnStock_min());
		else if (key.equals("detailInventaire_field_quantiteReelle_min"))
			return BaseNLS
					.messages()
					.error_min_num(
							NLS.constants()
									.detailInventaire_field_quantiteReelle_min());
		else if (key.equals("detailInventaire_field_quantiteTheorique_min"))
			return BaseNLS.messages().error_min_num(
					NLS.constants()
							.detailInventaire_field_quantiteTheorique_min());
		else if (key.equals("regime_field_dureeTraitement_min"))
			return BaseNLS.messages().error_min_num(
					NLS.constants().regime_field_dureeTraitement_min());
		else if (key.equals("regime_field_poidsMin_min"))
			return BaseNLS.messages().error_min_num(
					NLS.constants().regime_field_poidsMin_min());
		else if (key.equals("regime_field_poidsMax_min"))
			return BaseNLS.messages().error_min_num(
					NLS.constants().regime_field_poidsMax_min());
		else if (key.equals("priseMedicamentRegime_field_quantite_min"))
			return BaseNLS.messages().error_min_num(
					NLS.constants().priseMedicamentRegime_field_quantite_min());
		else if (key
				.equals("priseMedicamentRegime_field_quantite_decimalNumber"))
			return BaseNLS
					.messages()
					.error_float_dec(
							NLS.constants()
									.priseMedicamentRegime_field_quantite_decimalNumber());
		else if (key.equals("medicament_field_seuilPatient_decimalNumber"))
			return BaseNLS.messages().error_float_dec(
					NLS.constants()
							.medicament_field_seuilPatient_decimalNumber());
		else if (key.equals("intrant_field_seuilPatient_decimalNumber"))
			return BaseNLS.messages().error_float_dec(
					NLS.constants().intrant_field_seuilPatient_decimalNumber());
		else
			return key;
	}

	public String getEntityName(ImogBeanProxy proxy) {
		if (PatientProxy.class.equals(proxy.getClass())) {
			return NLS.constants().patient_name_plur();
		}
		if (CasIndexProxy.class.equals(proxy.getClass())) {
			return NLS.constants().casIndex_name_plur();
		}
		if (CasTuberculoseProxy.class.equals(proxy.getClass())) {
			return NLS.constants().casTuberculose_name_plur();
		}
		if (ExamenSerologieProxy.class.equals(proxy.getClass())) {
			return NLS.constants().examenSerologie_name_plur();
		}
		if (ExamenBiologiqueProxy.class.equals(proxy.getClass())) {
			return NLS.constants().examenBiologique_name_plur();
		}
		if (ExamenMicroscopieProxy.class.equals(proxy.getClass())) {
			return NLS.constants().examenMicroscopie_name_plur();
		}
		if (ExamenATBProxy.class.equals(proxy.getClass())) {
			return NLS.constants().examenATB_name_plur();
		}
		if (PriseMedicamenteuseProxy.class.equals(proxy.getClass())) {
			return NLS.constants().priseMedicamenteuse_name_plur();
		}
		if (RendezVousProxy.class.equals(proxy.getClass())) {
			return NLS.constants().rendezVous_name_plur();
		}
		if (TransfertReferenceProxy.class.equals(proxy.getClass())) {
			return NLS.constants().transfertReference_name_plur();
		}
		if (LotProxy.class.equals(proxy.getClass())) {
			return NLS.constants().lot_name_plur();
		}
		if (HorsUsageProxy.class.equals(proxy.getClass())) {
			return NLS.constants().horsUsage_name_plur();
		}
		if (EntreeLotProxy.class.equals(proxy.getClass())) {
			return NLS.constants().entreeLot_name_plur();
		}
		if (SortieLotProxy.class.equals(proxy.getClass())) {
			return NLS.constants().sortieLot_name_plur();
		}
		if (CommandeProxy.class.equals(proxy.getClass())) {
			return NLS.constants().commande_name_plur();
		}
		if (DetailCommandeMedicamentProxy.class.equals(proxy.getClass())) {
			return NLS.constants().detailCommandeMedicament_name_plur();
		}
		if (DetailCommandeIntrantProxy.class.equals(proxy.getClass())) {
			return NLS.constants().detailCommandeIntrant_name_plur();
		}
		if (ReceptionProxy.class.equals(proxy.getClass())) {
			return NLS.constants().reception_name_plur();
		}
		if (DetailReceptionMedicamentProxy.class.equals(proxy.getClass())) {
			return NLS.constants().detailReceptionMedicament_name_plur();
		}
		if (DetailReceptionIntrantProxy.class.equals(proxy.getClass())) {
			return NLS.constants().detailReceptionIntrant_name_plur();
		}
		if (RavitaillementProxy.class.equals(proxy.getClass())) {
			return NLS.constants().ravitaillement_name_plur();
		}
		if (DetailRavitaillementProxy.class.equals(proxy.getClass())) {
			return NLS.constants().detailRavitaillement_name_plur();
		}
		if (InventaireProxy.class.equals(proxy.getClass())) {
			return NLS.constants().inventaire_name_plur();
		}
		if (DetailInventaireProxy.class.equals(proxy.getClass())) {
			return NLS.constants().detailInventaire_name_plur();
		}
		if (PersonnelProxy.class.equals(proxy.getClass())) {
			return NLS.constants().personnel_name_plur();
		}
		if (DepartPersonnelProxy.class.equals(proxy.getClass())) {
			return NLS.constants().departPersonnel_name_plur();
		}
		if (ArriveePersonnelProxy.class.equals(proxy.getClass())) {
			return NLS.constants().arriveePersonnel_name_plur();
		}
		if (RegionProxy.class.equals(proxy.getClass())) {
			return NLS.constants().region_name_plur();
		}
		if (DistrictSanteProxy.class.equals(proxy.getClass())) {
			return NLS.constants().districtSante_name_plur();
		}
		if (CentreDiagTraitProxy.class.equals(proxy.getClass())) {
			return NLS.constants().centreDiagTrait_name_plur();
		}
		if (LaboratoireReferenceProxy.class.equals(proxy.getClass())) {
			return NLS.constants().laboratoireReference_name_plur();
		}
		if (LieuDitProxy.class.equals(proxy.getClass())) {
			return NLS.constants().lieuDit_name_plur();
		}
		if (RegimeProxy.class.equals(proxy.getClass())) {
			return NLS.constants().regime_name_plur();
		}
		if (PriseMedicamentRegimeProxy.class.equals(proxy.getClass())) {
			return NLS.constants().priseMedicamentRegime_name_plur();
		}
		if (MedicamentProxy.class.equals(proxy.getClass())) {
			return NLS.constants().medicament_name_plur();
		}
		if (IntrantProxy.class.equals(proxy.getClass())) {
			return NLS.constants().intrant_name_plur();
		}
		if (FormationProxy.class.equals(proxy.getClass())) {
			return NLS.constants().formation_name_plur();
		}
		if (CandidatureFormationProxy.class.equals(proxy.getClass())) {
			return NLS.constants().candidatureFormation_name_plur();
		}
		if (QualificationProxy.class.equals(proxy.getClass())) {
			return NLS.constants().qualification_name_plur();
		}
		if (TutorielProxy.class.equals(proxy.getClass())) {
			return NLS.constants().tutoriel_name_plur();
		}
		if (SmsPredefiniProxy.class.equals(proxy.getClass())) {
			return NLS.constants().smsPredefini_name_plur();
		}
		if (OutBoxProxy.class.equals(proxy.getClass())) {
			return NLS.constants().outBox_name_plur();
		}
		if (UtilisateurProxy.class.equals(proxy.getClass())) {
			return NLS.constants().utilisateur_name_plur();
		}
		return "";
	}

	public String getEntityTypeDisplayValue(String name) {

		if (name.equals(PatientProxy.class.getName())) {
			return NLS.constants().patient_name();
		}
		if (name.equals(CasIndexProxy.class.getName())) {
			return NLS.constants().casIndex_name();
		}
		if (name.equals(CasTuberculoseProxy.class.getName())) {
			return NLS.constants().casTuberculose_name();
		}
		if (name.equals(ExamenSerologieProxy.class.getName())) {
			return NLS.constants().examenSerologie_name();
		}
		if (name.equals(ExamenBiologiqueProxy.class.getName())) {
			return NLS.constants().examenBiologique_name();
		}
		if (name.equals(ExamenMicroscopieProxy.class.getName())) {
			return NLS.constants().examenMicroscopie_name();
		}
		if (name.equals(ExamenATBProxy.class.getName())) {
			return NLS.constants().examenATB_name();
		}
		if (name.equals(PriseMedicamenteuseProxy.class.getName())) {
			return NLS.constants().priseMedicamenteuse_name();
		}
		if (name.equals(RendezVousProxy.class.getName())) {
			return NLS.constants().rendezVous_name();
		}
		if (name.equals(TransfertReferenceProxy.class.getName())) {
			return NLS.constants().transfertReference_name();
		}
		if (name.equals(LotProxy.class.getName())) {
			return NLS.constants().lot_name();
		}
		if (name.equals(HorsUsageProxy.class.getName())) {
			return NLS.constants().horsUsage_name();
		}
		if (name.equals(EntreeLotProxy.class.getName())) {
			return NLS.constants().entreeLot_name();
		}
		if (name.equals(SortieLotProxy.class.getName())) {
			return NLS.constants().sortieLot_name();
		}
		if (name.equals(CommandeProxy.class.getName())) {
			return NLS.constants().commande_name();
		}
		if (name.equals(DetailCommandeMedicamentProxy.class.getName())) {
			return NLS.constants().detailCommandeMedicament_name();
		}
		if (name.equals(DetailCommandeIntrantProxy.class.getName())) {
			return NLS.constants().detailCommandeIntrant_name();
		}
		if (name.equals(ReceptionProxy.class.getName())) {
			return NLS.constants().reception_name();
		}
		if (name.equals(DetailReceptionMedicamentProxy.class.getName())) {
			return NLS.constants().detailReceptionMedicament_name();
		}
		if (name.equals(DetailReceptionIntrantProxy.class.getName())) {
			return NLS.constants().detailReceptionIntrant_name();
		}
		if (name.equals(RavitaillementProxy.class.getName())) {
			return NLS.constants().ravitaillement_name();
		}
		if (name.equals(DetailRavitaillementProxy.class.getName())) {
			return NLS.constants().detailRavitaillement_name();
		}
		if (name.equals(InventaireProxy.class.getName())) {
			return NLS.constants().inventaire_name();
		}
		if (name.equals(DetailInventaireProxy.class.getName())) {
			return NLS.constants().detailInventaire_name();
		}
		if (name.equals(PersonnelProxy.class.getName())) {
			return NLS.constants().personnel_name();
		}
		if (name.equals(DepartPersonnelProxy.class.getName())) {
			return NLS.constants().departPersonnel_name();
		}
		if (name.equals(ArriveePersonnelProxy.class.getName())) {
			return NLS.constants().arriveePersonnel_name();
		}
		if (name.equals(RegionProxy.class.getName())) {
			return NLS.constants().region_name();
		}
		if (name.equals(DistrictSanteProxy.class.getName())) {
			return NLS.constants().districtSante_name();
		}
		if (name.equals(CentreDiagTraitProxy.class.getName())) {
			return NLS.constants().centreDiagTrait_name();
		}
		if (name.equals(LaboratoireReferenceProxy.class.getName())) {
			return NLS.constants().laboratoireReference_name();
		}
		if (name.equals(LieuDitProxy.class.getName())) {
			return NLS.constants().lieuDit_name();
		}
		if (name.equals(RegimeProxy.class.getName())) {
			return NLS.constants().regime_name();
		}
		if (name.equals(PriseMedicamentRegimeProxy.class.getName())) {
			return NLS.constants().priseMedicamentRegime_name();
		}
		if (name.equals(MedicamentProxy.class.getName())) {
			return NLS.constants().medicament_name();
		}
		if (name.equals(IntrantProxy.class.getName())) {
			return NLS.constants().intrant_name();
		}
		if (name.equals(FormationProxy.class.getName())) {
			return NLS.constants().formation_name();
		}
		if (name.equals(CandidatureFormationProxy.class.getName())) {
			return NLS.constants().candidatureFormation_name();
		}
		if (name.equals(QualificationProxy.class.getName())) {
			return NLS.constants().qualification_name();
		}
		if (name.equals(TutorielProxy.class.getName())) {
			return NLS.constants().tutoriel_name();
		}
		if (name.equals(SmsPredefiniProxy.class.getName())) {
			return NLS.constants().smsPredefini_name();
		}
		if (name.equals(OutBoxProxy.class.getName())) {
			return NLS.constants().outBox_name();
		}
		if (name.equals(UtilisateurProxy.class.getName())) {
			return NLS.constants().utilisateur_name();
		}

		return "";
	}
}
