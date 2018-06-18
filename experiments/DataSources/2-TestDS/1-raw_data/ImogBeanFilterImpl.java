package org.imogene.epicam.server.security;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.imogene.lib.common.entity.ImogBean;
import org.imogene.lib.common.security.ImogBeanFilter;
import org.imogene.web.server.util.HttpSessionUtil;
import org.imogene.epicam.domain.entity.Patient;
import org.imogene.epicam.domain.entity.CasIndex;
import org.imogene.epicam.domain.entity.CasTuberculose;
import org.imogene.epicam.domain.entity.ExamenSerologie;
import org.imogene.epicam.domain.entity.ExamenBiologique;
import org.imogene.epicam.domain.entity.ExamenMicroscopie;
import org.imogene.epicam.domain.entity.ExamenATB;
import org.imogene.epicam.domain.entity.PriseMedicamenteuse;
import org.imogene.epicam.domain.entity.RendezVous;
import org.imogene.epicam.domain.entity.TransfertReference;
import org.imogene.epicam.domain.entity.Lot;
import org.imogene.epicam.domain.entity.HorsUsage;
import org.imogene.epicam.domain.entity.EntreeLot;
import org.imogene.epicam.domain.entity.SortieLot;
import org.imogene.epicam.domain.entity.Commande;
import org.imogene.epicam.domain.entity.DetailCommandeMedicament;
import org.imogene.epicam.domain.entity.DetailCommandeIntrant;
import org.imogene.epicam.domain.entity.Reception;
import org.imogene.epicam.domain.entity.DetailReceptionMedicament;
import org.imogene.epicam.domain.entity.DetailReceptionIntrant;
import org.imogene.epicam.domain.entity.Ravitaillement;
import org.imogene.epicam.domain.entity.DetailRavitaillement;
import org.imogene.epicam.domain.entity.Inventaire;
import org.imogene.epicam.domain.entity.DetailInventaire;
import org.imogene.epicam.domain.entity.Personnel;
import org.imogene.epicam.domain.entity.DepartPersonnel;
import org.imogene.epicam.domain.entity.ArriveePersonnel;
import org.imogene.epicam.domain.entity.Region;
import org.imogene.epicam.domain.entity.DistrictSante;
import org.imogene.epicam.domain.entity.CentreDiagTrait;
import org.imogene.epicam.domain.entity.LaboratoireReference;
import org.imogene.epicam.domain.entity.LieuDit;
import org.imogene.epicam.domain.entity.Regime;
import org.imogene.epicam.domain.entity.PriseMedicamentRegime;
import org.imogene.epicam.domain.entity.Medicament;
import org.imogene.epicam.domain.entity.Intrant;
import org.imogene.epicam.domain.entity.Formation;
import org.imogene.epicam.domain.entity.CandidatureFormation;
import org.imogene.epicam.domain.entity.Qualification;
import org.imogene.epicam.domain.entity.Tutoriel;
import org.imogene.epicam.domain.entity.SmsPredefini;
import org.imogene.epicam.domain.entity.OutBox;
import org.imogene.epicam.domain.entity.Utilisateur;

/**
 * Class to filter beans so that only 
 * allowed bean data is accessible in read mode
 * @author Medes-IMPS
 */
public class ImogBeanFilterImpl implements ImogBeanFilter {

	@Override
	@SuppressWarnings("unchecked")
	public <T extends ImogBean> T toSecure(T bean) {

		if (bean instanceof Patient)
			return (T) toSecurePatient((Patient) bean);

		if (bean instanceof CasIndex)
			return (T) toSecureCasIndex((CasIndex) bean);

		if (bean instanceof CasTuberculose)
			return (T) toSecureCasTuberculose((CasTuberculose) bean);

		if (bean instanceof ExamenSerologie)
			return (T) toSecureExamenSerologie((ExamenSerologie) bean);

		if (bean instanceof ExamenBiologique)
			return (T) toSecureExamenBiologique((ExamenBiologique) bean);

		if (bean instanceof ExamenMicroscopie)
			return (T) toSecureExamenMicroscopie((ExamenMicroscopie) bean);

		if (bean instanceof ExamenATB)
			return (T) toSecureExamenATB((ExamenATB) bean);

		if (bean instanceof PriseMedicamenteuse)
			return (T) toSecurePriseMedicamenteuse((PriseMedicamenteuse) bean);

		if (bean instanceof RendezVous)
			return (T) toSecureRendezVous((RendezVous) bean);

		if (bean instanceof TransfertReference)
			return (T) toSecureTransfertReference((TransfertReference) bean);

		if (bean instanceof Lot)
			return (T) toSecureLot((Lot) bean);

		if (bean instanceof HorsUsage)
			return (T) toSecureHorsUsage((HorsUsage) bean);

		if (bean instanceof EntreeLot)
			return (T) toSecureEntreeLot((EntreeLot) bean);

		if (bean instanceof SortieLot)
			return (T) toSecureSortieLot((SortieLot) bean);

		if (bean instanceof Commande)
			return (T) toSecureCommande((Commande) bean);

		if (bean instanceof DetailCommandeMedicament)
			return (T) toSecureDetailCommandeMedicament((DetailCommandeMedicament) bean);

		if (bean instanceof DetailCommandeIntrant)
			return (T) toSecureDetailCommandeIntrant((DetailCommandeIntrant) bean);

		if (bean instanceof Reception)
			return (T) toSecureReception((Reception) bean);

		if (bean instanceof DetailReceptionMedicament)
			return (T) toSecureDetailReceptionMedicament((DetailReceptionMedicament) bean);

		if (bean instanceof DetailReceptionIntrant)
			return (T) toSecureDetailReceptionIntrant((DetailReceptionIntrant) bean);

		if (bean instanceof Ravitaillement)
			return (T) toSecureRavitaillement((Ravitaillement) bean);

		if (bean instanceof DetailRavitaillement)
			return (T) toSecureDetailRavitaillement((DetailRavitaillement) bean);

		if (bean instanceof Inventaire)
			return (T) toSecureInventaire((Inventaire) bean);

		if (bean instanceof DetailInventaire)
			return (T) toSecureDetailInventaire((DetailInventaire) bean);

		if (bean instanceof Personnel)
			return (T) toSecurePersonnel((Personnel) bean);

		if (bean instanceof DepartPersonnel)
			return (T) toSecureDepartPersonnel((DepartPersonnel) bean);

		if (bean instanceof ArriveePersonnel)
			return (T) toSecureArriveePersonnel((ArriveePersonnel) bean);

		if (bean instanceof Region)
			return (T) toSecureRegion((Region) bean);

		if (bean instanceof DistrictSante)
			return (T) toSecureDistrictSante((DistrictSante) bean);

		if (bean instanceof CentreDiagTrait)
			return (T) toSecureCentreDiagTrait((CentreDiagTrait) bean);

		if (bean instanceof LaboratoireReference)
			return (T) toSecureLaboratoireReference((LaboratoireReference) bean);

		if (bean instanceof LieuDit)
			return (T) toSecureLieuDit((LieuDit) bean);

		if (bean instanceof Regime)
			return (T) toSecureRegime((Regime) bean);

		if (bean instanceof PriseMedicamentRegime)
			return (T) toSecurePriseMedicamentRegime((PriseMedicamentRegime) bean);

		if (bean instanceof Medicament)
			return (T) toSecureMedicament((Medicament) bean);

		if (bean instanceof Intrant)
			return (T) toSecureIntrant((Intrant) bean);

		if (bean instanceof Formation)
			return (T) toSecureFormation((Formation) bean);

		if (bean instanceof CandidatureFormation)
			return (T) toSecureCandidatureFormation((CandidatureFormation) bean);

		if (bean instanceof Qualification)
			return (T) toSecureQualification((Qualification) bean);

		if (bean instanceof Tutoriel)
			return (T) toSecureTutoriel((Tutoriel) bean);

		if (bean instanceof SmsPredefini)
			return (T) toSecureSmsPredefini((SmsPredefini) bean);

		if (bean instanceof OutBox)
			return (T) toSecureOutBox((OutBox) bean);

		if (bean instanceof Utilisateur)
			return (T) toSecureUtilisateur((Utilisateur) bean);

		return bean;
	}

	@Override
	public <T extends ImogBean> List<T> toSecure(List<T> beans) {
		List<T> securedList = new Vector<T>();
		for (T bean : beans) {
			T isSecured = toSecure(bean);
			if (isSecured != null) {
				securedList.add(isSecured);
			}
		}
		return securedList;
	}

	@Override
	public <T extends ImogBean> T toHibernate(T bean) {
		return null;
	}

	@Override
	public <T extends ImogBean> List<T> toHibernate(List<T> list) {
		return null;
	}

	/**
	 * Secure a Patient bean.
	 * @param bean The Patient bean to secure
	 * @param policy security policy 
	 * @return A secured Patient bean
	 */
	private Patient toSecurePatient(Patient bean) {
		boolean isSecured = false;
		Patient transformedBean = new Patient();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadPatientIdentification()) {
			isSecured = true;
			transformedBean.setIdentifiant(bean.getIdentifiant());
			transformedBean.setNom(bean.getNom());
			transformedBean.setSexe(bean.getSexe());
			transformedBean.setDateNaissance(bean.getDateNaissance());
			transformedBean.setAge(bean.getAge());
			transformedBean.setProfession(bean.getProfession());
			transformedBean.setCentres(bean.getCentres());
			transformedBean.setNationalite(bean.getNationalite());
			transformedBean.setPrecisionSurNationalite(bean
					.getPrecisionSurNationalite());
			transformedBean.setRecevoirCarnetTelPortable(bean
					.getRecevoirCarnetTelPortable());
		} else {
			transformedBean.setIdentifiant(null);
			transformedBean.setNom(null);
			transformedBean.setSexe(null);
			transformedBean.setDateNaissance(null);
			transformedBean.setAge(null);
			transformedBean.setProfession(null);
			transformedBean.setCentres(new Vector<CentreDiagTrait>());
			transformedBean.setNationalite(null);
			transformedBean.setPrecisionSurNationalite(null);
			transformedBean.setRecevoirCarnetTelPortable(null);
		}
		if (policy.canReadPatientContact()) {
			isSecured = true;
			transformedBean.setTelephoneUn(bean.getTelephoneUn());
			transformedBean.setTelephoneDeux(bean.getTelephoneDeux());
			transformedBean.setTelephoneTrois(bean.getTelephoneTrois());
			transformedBean.setEmail(bean.getEmail());
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
			transformedBean.setLieuxDits(bean.getLieuxDits());
		} else {
			transformedBean.setTelephoneUn(null);
			transformedBean.setTelephoneDeux(null);
			transformedBean.setTelephoneTrois(null);
			transformedBean.setEmail(null);
			transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
			transformedBean.setLieuxDits(new Vector<LieuDit>());
		}
		if (policy.canReadPatientPersonneContact()) {
			isSecured = true;
			transformedBean.setPacNom(bean.getPacNom());
			transformedBean.setPacTelephoneUn(bean.getPacTelephoneUn());
			transformedBean.setPacTelephoneDeux(bean.getPacTelephoneDeux());
			transformedBean.setPacTelephoneTrois(bean.getPacTelephoneTrois());
			transformedBean.setPacEmail(bean.getPacEmail());
			transformedBean.setPacLibelle(bean.getPacLibelle());
			transformedBean.setPacComplementAdresse(bean
					.getPacComplementAdresse());
			transformedBean.setPacQuartier(bean.getPacQuartier());
			transformedBean.setPacVille(bean.getPacVille());
			transformedBean.setPacCodePostal(bean.getPacCodePostal());
		} else {
			transformedBean.setPacNom(null);
			transformedBean.setPacTelephoneUn(null);
			transformedBean.setPacTelephoneDeux(null);
			transformedBean.setPacTelephoneTrois(null);
			transformedBean.setPacEmail(null);
			transformedBean.setPacLibelle(null);
			transformedBean.setPacComplementAdresse(null);
			transformedBean.setPacQuartier(null);
			transformedBean.setPacVille(null);
			transformedBean.setPacCodePostal(null);
		}
		if (policy.canReadPatientTuberculose()) {
			isSecured = true;
			transformedBean.setCasTuberculose(bean.getCasTuberculose());
			transformedBean.setCasIndex(bean.getCasIndex());
		} else {
			transformedBean.setCasTuberculose(new Vector<CasTuberculose>());
			transformedBean.setCasIndex(new Vector<CasIndex>());
		}
		if (policy.canReadPatientExamens()) {
			isSecured = true;
			transformedBean.setExamensBiologiques(bean.getExamensBiologiques());
			transformedBean.setSerologies(bean.getSerologies());
		} else {
			transformedBean
					.setExamensBiologiques(new Vector<ExamenBiologique>());
			transformedBean.setSerologies(new Vector<ExamenSerologie>());
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a CasIndex bean.
	 * @param bean The CasIndex bean to secure
	 * @param policy security policy 
	 * @return A secured CasIndex bean
	 */
	private CasIndex toSecureCasIndex(CasIndex bean) {
		boolean isSecured = false;
		CasIndex transformedBean = new CasIndex();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadCasIndexDescription()) {
			isSecured = true;

			if (transformedBean.getPatient() == null
					|| bean.getPatient() == null
					|| !transformedBean.getPatient().getId()
							.equals(bean.getPatient().getId())) {
				transformedBean.setPatient(bean.getPatient());
			}

			if (transformedBean.getPatientLie() == null
					|| bean.getPatientLie() == null
					|| !transformedBean.getPatientLie().getId()
							.equals(bean.getPatientLie().getId())) {
				transformedBean.setPatientLie(bean.getPatientLie());
			}
			transformedBean.setTypeRelation(bean.getTypeRelation());
		} else {
			transformedBean.setPatient(null);
			transformedBean.setPatientLie(null);
			transformedBean.setTypeRelation(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a CasTuberculose bean.
	 * @param bean The CasTuberculose bean to secure
	 * @param policy security policy 
	 * @return A secured CasTuberculose bean
	 */
	private CasTuberculose toSecureCasTuberculose(CasTuberculose bean) {
		boolean isSecured = false;
		CasTuberculose transformedBean = new CasTuberculose();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadCasTuberculoseInformations()) {
			isSecured = true;
			transformedBean.setIdentifiant(bean.getIdentifiant());
			transformedBean.setNumRegTB(bean.getNumRegTB());

			if (transformedBean.getPatient() == null
					|| bean.getPatient() == null
					|| !transformedBean.getPatient().getId()
							.equals(bean.getPatient().getId())) {
				transformedBean.setPatient(bean.getPatient());
			}
			transformedBean.setDateDebutTraitement(bean
					.getDateDebutTraitement());
			transformedBean.setTypePatient(bean.getTypePatient());
			transformedBean.setTypePatientPrecisions(bean
					.getTypePatientPrecisions());
			transformedBean.setFormeMaladie(bean.getFormeMaladie());
			transformedBean.setExtraPulmonairePrecisions(bean
					.getExtraPulmonairePrecisions());
			transformedBean.setCotrimoxazole(bean.getCotrimoxazole());
			transformedBean.setAntiRetroViraux(bean.getAntiRetroViraux());
			transformedBean.setFumeur(bean.getFumeur());
			transformedBean.setFumeurArreter(bean.getFumeurArreter());
		} else {
			transformedBean.setIdentifiant(null);
			transformedBean.setNumRegTB(null);
			transformedBean.setPatient(null);
			transformedBean.setDateDebutTraitement(null);
			transformedBean.setTypePatient(null);
			transformedBean.setTypePatientPrecisions(null);
			transformedBean.setFormeMaladie(null);
			transformedBean.setExtraPulmonairePrecisions(null);
			transformedBean.setCotrimoxazole(null);
			transformedBean.setAntiRetroViraux(null);
			transformedBean.setFumeur(null);
			transformedBean.setFumeurArreter(null);
		}
		if (policy.canReadCasTuberculoseExamen()) {
			isSecured = true;
			transformedBean.setExamensMiscrocopies(bean
					.getExamensMiscrocopies());
			transformedBean.setExamensATB(bean.getExamensATB());
		} else {
			transformedBean
					.setExamensMiscrocopies(new Vector<ExamenMicroscopie>());
			transformedBean.setExamensATB(new Vector<ExamenATB>());
		}
		if (policy.canReadCasTuberculoseTraitement()) {
			isSecured = true;

			if (transformedBean.getRegimePhaseInitiale() == null
					|| bean.getRegimePhaseInitiale() == null
					|| !transformedBean.getRegimePhaseInitiale().getId()
							.equals(bean.getRegimePhaseInitiale().getId())) {
				transformedBean.setRegimePhaseInitiale(bean
						.getRegimePhaseInitiale());
			}

			if (transformedBean.getRegimePhaseContinuation() == null
					|| bean.getRegimePhaseContinuation() == null
					|| !transformedBean.getRegimePhaseContinuation().getId()
							.equals(bean.getRegimePhaseContinuation().getId())) {
				transformedBean.setRegimePhaseContinuation(bean
						.getRegimePhaseContinuation());
			}
			transformedBean.setPriseMedicamenteusePhaseInitiale(bean
					.getPriseMedicamenteusePhaseInitiale());
			transformedBean.setPriseMedicamenteusePhaseContinuation(bean
					.getPriseMedicamenteusePhaseContinuation());
			transformedBean.setRendezVous(bean.getRendezVous());
		} else {
			transformedBean.setRegimePhaseInitiale(null);
			transformedBean.setRegimePhaseContinuation(null);
			transformedBean
					.setPriseMedicamenteusePhaseInitiale(new Vector<PriseMedicamenteuse>());
			transformedBean
					.setPriseMedicamenteusePhaseContinuation(new Vector<PriseMedicamenteuse>());
			transformedBean.setRendezVous(new Vector<RendezVous>());
		}
		if (policy.canReadCasTuberculoseFinTraitement()) {
			isSecured = true;
			transformedBean.setDateFinTraitement(bean.getDateFinTraitement());
			transformedBean.setDevenirMalade(bean.getDevenirMalade());
			transformedBean.setObservation(bean.getObservation());
		} else {
			transformedBean.setDateFinTraitement(null);
			transformedBean.setDevenirMalade(null);
			transformedBean.setObservation(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a ExamenSerologie bean.
	 * @param bean The ExamenSerologie bean to secure
	 * @param policy security policy 
	 * @return A secured ExamenSerologie bean
	 */
	private ExamenSerologie toSecureExamenSerologie(ExamenSerologie bean) {
		boolean isSecured = false;
		ExamenSerologie transformedBean = new ExamenSerologie();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadExamenSerologieDescription()) {
			isSecured = true;

			if (transformedBean.getPatient() == null
					|| bean.getPatient() == null
					|| !transformedBean.getPatient().getId()
							.equals(bean.getPatient().getId())) {
				transformedBean.setPatient(bean.getPatient());
			}
			transformedBean.setDateTest(bean.getDateTest());
			transformedBean.setNature(bean.getNature());
			transformedBean.setResultatVIH(bean.getResultatVIH());
			transformedBean.setResultatCD4(bean.getResultatCD4());
		} else {
			transformedBean.setPatient(null);
			transformedBean.setDateTest(null);
			transformedBean.setNature(null);
			transformedBean.setResultatVIH(null);
			transformedBean.setResultatCD4(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a ExamenBiologique bean.
	 * @param bean The ExamenBiologique bean to secure
	 * @param policy security policy 
	 * @return A secured ExamenBiologique bean
	 */
	private ExamenBiologique toSecureExamenBiologique(ExamenBiologique bean) {
		boolean isSecured = false;
		ExamenBiologique transformedBean = new ExamenBiologique();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadExamenBiologiqueDescription()) {
			isSecured = true;

			if (transformedBean.getPatient() == null
					|| bean.getPatient() == null
					|| !transformedBean.getPatient().getId()
							.equals(bean.getPatient().getId())) {
				transformedBean.setPatient(bean.getPatient());
			}
			transformedBean.setDate(bean.getDate());
			transformedBean.setPoids(bean.getPoids());
			transformedBean.setObservations(bean.getObservations());
		} else {
			transformedBean.setPatient(null);
			transformedBean.setDate(null);
			transformedBean.setPoids(null);
			transformedBean.setObservations(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a ExamenMicroscopie bean.
	 * @param bean The ExamenMicroscopie bean to secure
	 * @param policy security policy 
	 * @return A secured ExamenMicroscopie bean
	 */
	private ExamenMicroscopie toSecureExamenMicroscopie(ExamenMicroscopie bean) {
		boolean isSecured = false;
		ExamenMicroscopie transformedBean = new ExamenMicroscopie();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadExamenMicroscopieCentreExamen()) {
			isSecured = true;

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getLaboratoireReference() == null
					|| bean.getLaboratoireReference() == null
					|| !transformedBean.getLaboratoireReference().getId()
							.equals(bean.getLaboratoireReference().getId())) {
				transformedBean.setLaboratoireReference(bean
						.getLaboratoireReference());
			}
		} else {
			transformedBean.setCDT(null);
			transformedBean.setLaboratoireReference(null);
		}
		if (policy.canReadExamenMicroscopieExamen()) {
			isSecured = true;

			if (transformedBean.getCasTb() == null
					|| bean.getCasTb() == null
					|| !transformedBean.getCasTb().getId()
							.equals(bean.getCasTb().getId())) {
				transformedBean.setCasTb(bean.getCasTb());
			}
			transformedBean.setDate(bean.getDate());
			transformedBean.setRaisonDepistage(bean.getRaisonDepistage());
			transformedBean.setResultat(bean.getResultat());
			transformedBean.setObservations(bean.getObservations());
		} else {
			transformedBean.setCasTb(null);
			transformedBean.setDate(null);
			transformedBean.setRaisonDepistage(null);
			transformedBean.setResultat(null);
			transformedBean.setObservations(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a ExamenATB bean.
	 * @param bean The ExamenATB bean to secure
	 * @param policy security policy 
	 * @return A secured ExamenATB bean
	 */
	private ExamenATB toSecureExamenATB(ExamenATB bean) {
		boolean isSecured = false;
		ExamenATB transformedBean = new ExamenATB();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadExamenATBCentreExamen()) {
			isSecured = true;

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getLaboratoireReference() == null
					|| bean.getLaboratoireReference() == null
					|| !transformedBean.getLaboratoireReference().getId()
							.equals(bean.getLaboratoireReference().getId())) {
				transformedBean.setLaboratoireReference(bean
						.getLaboratoireReference());
			}
		} else {
			transformedBean.setCDT(null);
			transformedBean.setLaboratoireReference(null);
		}
		if (policy.canReadExamenATBExamen()) {
			isSecured = true;

			if (transformedBean.getCasTb() == null
					|| bean.getCasTb() == null
					|| !transformedBean.getCasTb().getId()
							.equals(bean.getCasTb().getId())) {
				transformedBean.setCasTb(bean.getCasTb());
			}
			transformedBean.setDateExamen(bean.getDateExamen());
			transformedBean.setRaisonDepistage(bean.getRaisonDepistage());
			transformedBean.setResultat(bean.getResultat());
			transformedBean.setObservations(bean.getObservations());
		} else {
			transformedBean.setCasTb(null);
			transformedBean.setDateExamen(null);
			transformedBean.setRaisonDepistage(null);
			transformedBean.setResultat(null);
			transformedBean.setObservations(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a PriseMedicamenteuse bean.
	 * @param bean The PriseMedicamenteuse bean to secure
	 * @param policy security policy 
	 * @return A secured PriseMedicamenteuse bean
	 */
	private PriseMedicamenteuse toSecurePriseMedicamenteuse(
			PriseMedicamenteuse bean) {
		boolean isSecured = false;
		PriseMedicamenteuse transformedBean = new PriseMedicamenteuse();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadPriseMedicamenteuseDescription()) {
			isSecured = true;

			if (transformedBean.getPhaseIntensive() == null
					|| bean.getPhaseIntensive() == null
					|| !transformedBean.getPhaseIntensive().getId()
							.equals(bean.getPhaseIntensive().getId())) {
				transformedBean.setPhaseIntensive(bean.getPhaseIntensive());
			}

			if (transformedBean.getPhaseContinuation() == null
					|| bean.getPhaseContinuation() == null
					|| !transformedBean.getPhaseContinuation().getId()
							.equals(bean.getPhaseContinuation().getId())) {
				transformedBean.setPhaseContinuation(bean
						.getPhaseContinuation());
			}
			transformedBean.setDateEffective(bean.getDateEffective());
			transformedBean.setPrise(bean.getPrise());
			transformedBean.setCotrimoxazole(bean.getCotrimoxazole());
		} else {
			transformedBean.setPhaseIntensive(null);
			transformedBean.setPhaseContinuation(null);
			transformedBean.setDateEffective(null);
			transformedBean.setPrise(null);
			transformedBean.setCotrimoxazole(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a RendezVous bean.
	 * @param bean The RendezVous bean to secure
	 * @param policy security policy 
	 * @return A secured RendezVous bean
	 */
	private RendezVous toSecureRendezVous(RendezVous bean) {
		boolean isSecured = false;
		RendezVous transformedBean = new RendezVous();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadRendezVousDescription()) {
			isSecured = true;

			if (transformedBean.getCasTb() == null
					|| bean.getCasTb() == null
					|| !transformedBean.getCasTb().getId()
							.equals(bean.getCasTb().getId())) {
				transformedBean.setCasTb(bean.getCasTb());
			}
			transformedBean.setDateRendezVous(bean.getDateRendezVous());
			transformedBean.setHonore(bean.getHonore());
			transformedBean.setNombreSMSEnvoye(bean.getNombreSMSEnvoye());
			transformedBean.setCommentaire(bean.getCommentaire());
		} else {
			transformedBean.setCasTb(null);
			transformedBean.setDateRendezVous(null);
			transformedBean.setHonore(null);
			transformedBean.setNombreSMSEnvoye(null);
			transformedBean.setCommentaire(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a TransfertReference bean.
	 * @param bean The TransfertReference bean to secure
	 * @param policy security policy 
	 * @return A secured TransfertReference bean
	 */
	private TransfertReference toSecureTransfertReference(
			TransfertReference bean) {
		boolean isSecured = false;
		TransfertReference transformedBean = new TransfertReference();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadTransfertReferenceInformationsDepart()) {
			isSecured = true;
			transformedBean.setNature(bean.getNature());

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDTDepart() == null
					|| bean.getCDTDepart() == null
					|| !transformedBean.getCDTDepart().getId()
							.equals(bean.getCDTDepart().getId())) {
				transformedBean.setCDTDepart(bean.getCDTDepart());
			}

			if (transformedBean.getPatient() == null
					|| bean.getPatient() == null
					|| !transformedBean.getPatient().getId()
							.equals(bean.getPatient().getId())) {
				transformedBean.setPatient(bean.getPatient());
			}
			transformedBean.setDateDepart(bean.getDateDepart());
			transformedBean.setMotifDepart(bean.getMotifDepart());
		} else {
			transformedBean.setNature(null);
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDTDepart(null);
			transformedBean.setPatient(null);
			transformedBean.setDateDepart(null);
			transformedBean.setMotifDepart(null);
		}
		if (policy.canReadTransfertReferenceInformationArrivee()) {
			isSecured = true;

			if (transformedBean.getRegionArrivee() == null
					|| bean.getRegionArrivee() == null
					|| !transformedBean.getRegionArrivee().getId()
							.equals(bean.getRegionArrivee().getId())) {
				transformedBean.setRegionArrivee(bean.getRegionArrivee());
			}

			if (transformedBean.getDistrictSanteArrivee() == null
					|| bean.getDistrictSanteArrivee() == null
					|| !transformedBean.getDistrictSanteArrivee().getId()
							.equals(bean.getDistrictSanteArrivee().getId())) {
				transformedBean.setDistrictSanteArrivee(bean
						.getDistrictSanteArrivee());
			}

			if (transformedBean.getCDTArrivee() == null
					|| bean.getCDTArrivee() == null
					|| !transformedBean.getCDTArrivee().getId()
							.equals(bean.getCDTArrivee().getId())) {
				transformedBean.setCDTArrivee(bean.getCDTArrivee());
			}
			transformedBean.setDateArrivee(bean.getDateArrivee());
		} else {
			transformedBean.setRegionArrivee(null);
			transformedBean.setDistrictSanteArrivee(null);
			transformedBean.setCDTArrivee(null);
			transformedBean.setDateArrivee(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a Lot bean.
	 * @param bean The Lot bean to secure
	 * @param policy security policy 
	 * @return A secured Lot bean
	 */
	private Lot toSecureLot(Lot bean) {
		boolean isSecured = false;
		Lot transformedBean = new Lot();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadLotDescription()) {
			isSecured = true;

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}
			transformedBean.setNumero(bean.getNumero());
			transformedBean.setType(bean.getType());

			if (transformedBean.getMedicament() == null
					|| bean.getMedicament() == null
					|| !transformedBean.getMedicament().getId()
							.equals(bean.getMedicament().getId())) {
				transformedBean.setMedicament(bean.getMedicament());
			}

			if (transformedBean.getIntrant() == null
					|| bean.getIntrant() == null
					|| !transformedBean.getIntrant().getId()
							.equals(bean.getIntrant().getId())) {
				transformedBean.setIntrant(bean.getIntrant());
			}
			transformedBean.setQuantiteInitiale(bean.getQuantiteInitiale());
			transformedBean.setQuantite(bean.getQuantite());
			transformedBean.setDatePeremption(bean.getDatePeremption());
		} else {
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setNumero(null);
			transformedBean.setType(null);
			transformedBean.setMedicament(null);
			transformedBean.setIntrant(null);
			transformedBean.setQuantiteInitiale(null);
			transformedBean.setQuantite(null);
			transformedBean.setDatePeremption(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a HorsUsage bean.
	 * @param bean The HorsUsage bean to secure
	 * @param policy security policy 
	 * @return A secured HorsUsage bean
	 */
	private HorsUsage toSecureHorsUsage(HorsUsage bean) {
		boolean isSecured = false;
		HorsUsage transformedBean = new HorsUsage();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadHorsUsageDescription()) {
			isSecured = true;

			if (transformedBean.getLot() == null
					|| bean.getLot() == null
					|| !transformedBean.getLot().getId()
							.equals(bean.getLot().getId())) {
				transformedBean.setLot(bean.getLot());
			}
			transformedBean.setType(bean.getType());
			transformedBean.setMotif(bean.getMotif());
		} else {
			transformedBean.setLot(null);
			transformedBean.setType(null);
			transformedBean.setMotif(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a EntreeLot bean.
	 * @param bean The EntreeLot bean to secure
	 * @param policy security policy 
	 * @return A secured EntreeLot bean
	 */
	private EntreeLot toSecureEntreeLot(EntreeLot bean) {
		boolean isSecured = false;
		EntreeLot transformedBean = new EntreeLot();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadEntreeLotDescription()) {
			isSecured = true;

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getLot() == null
					|| bean.getLot() == null
					|| !transformedBean.getLot().getId()
							.equals(bean.getLot().getId())) {
				transformedBean.setLot(bean.getLot());
			}
			transformedBean.setQuantite(bean.getQuantite());
		} else {
			transformedBean.setCDT(null);
			transformedBean.setLot(null);
			transformedBean.setQuantite(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a SortieLot bean.
	 * @param bean The SortieLot bean to secure
	 * @param policy security policy 
	 * @return A secured SortieLot bean
	 */
	private SortieLot toSecureSortieLot(SortieLot bean) {
		boolean isSecured = false;
		SortieLot transformedBean = new SortieLot();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadSortieLotDescription()) {
			isSecured = true;

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getLot() == null
					|| bean.getLot() == null
					|| !transformedBean.getLot().getId()
							.equals(bean.getLot().getId())) {
				transformedBean.setLot(bean.getLot());
			}
			transformedBean.setQuantite(bean.getQuantite());
		} else {
			transformedBean.setCDT(null);
			transformedBean.setLot(null);
			transformedBean.setQuantite(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a Commande bean.
	 * @param bean The Commande bean to secure
	 * @param policy security policy 
	 * @return A secured Commande bean
	 */
	private Commande toSecureCommande(Commande bean) {
		boolean isSecured = false;
		Commande transformedBean = new Commande();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadCommandeInformationsDepart()) {
			isSecured = true;
			transformedBean.setDate(bean.getDate());

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}
			transformedBean.setMedicaments(bean.getMedicaments());
			transformedBean.setIntrants(bean.getIntrants());
		} else {
			transformedBean.setDate(null);
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean
					.setMedicaments(new Vector<DetailCommandeMedicament>());
			transformedBean.setIntrants(new Vector<DetailCommandeIntrant>());
		}
		if (policy.canReadCommandeRegionApprobation()) {
			isSecured = true;
			transformedBean.setApprouveeRegion(bean.getApprouveeRegion());
			transformedBean.setMotifRejetRegion(bean.getMotifRejetRegion());
		} else {
			transformedBean.setApprouveeRegion(null);
			transformedBean.setMotifRejetRegion(null);
		}
		if (policy.canReadCommandeGtcApprobation()) {
			isSecured = true;
			transformedBean.setApprouveeGTC(bean.getApprouveeGTC());
			transformedBean.setMotifRejetGTC(bean.getMotifRejetGTC());
		} else {
			transformedBean.setApprouveeGTC(null);
			transformedBean.setMotifRejetGTC(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a DetailCommandeMedicament bean.
	 * @param bean The DetailCommandeMedicament bean to secure
	 * @param policy security policy 
	 * @return A secured DetailCommandeMedicament bean
	 */
	private DetailCommandeMedicament toSecureDetailCommandeMedicament(
			DetailCommandeMedicament bean) {
		boolean isSecured = false;
		DetailCommandeMedicament transformedBean = new DetailCommandeMedicament();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadDetailCommandeMedicamentDescription()) {
			isSecured = true;

			if (transformedBean.getCommande() == null
					|| bean.getCommande() == null
					|| !transformedBean.getCommande().getId()
							.equals(bean.getCommande().getId())) {
				transformedBean.setCommande(bean.getCommande());
			}

			if (transformedBean.getMedicament() == null
					|| bean.getMedicament() == null
					|| !transformedBean.getMedicament().getId()
							.equals(bean.getMedicament().getId())) {
				transformedBean.setMedicament(bean.getMedicament());
			}
			transformedBean.setQuantiteRequise(bean.getQuantiteRequise());
			transformedBean.setQuantiteEnStock(bean.getQuantiteEnStock());
		} else {
			transformedBean.setCommande(null);
			transformedBean.setMedicament(null);
			transformedBean.setQuantiteRequise(null);
			transformedBean.setQuantiteEnStock(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a DetailCommandeIntrant bean.
	 * @param bean The DetailCommandeIntrant bean to secure
	 * @param policy security policy 
	 * @return A secured DetailCommandeIntrant bean
	 */
	private DetailCommandeIntrant toSecureDetailCommandeIntrant(
			DetailCommandeIntrant bean) {
		boolean isSecured = false;
		DetailCommandeIntrant transformedBean = new DetailCommandeIntrant();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadDetailCommandeIntrantDescription()) {
			isSecured = true;

			if (transformedBean.getCommande() == null
					|| bean.getCommande() == null
					|| !transformedBean.getCommande().getId()
							.equals(bean.getCommande().getId())) {
				transformedBean.setCommande(bean.getCommande());
			}

			if (transformedBean.getIntrant() == null
					|| bean.getIntrant() == null
					|| !transformedBean.getIntrant().getId()
							.equals(bean.getIntrant().getId())) {
				transformedBean.setIntrant(bean.getIntrant());
			}
			transformedBean.setQuantiteRequise(bean.getQuantiteRequise());
			transformedBean.setQuantiteEnStock(bean.getQuantiteEnStock());
		} else {
			transformedBean.setCommande(null);
			transformedBean.setIntrant(null);
			transformedBean.setQuantiteRequise(null);
			transformedBean.setQuantiteEnStock(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a Reception bean.
	 * @param bean The Reception bean to secure
	 * @param policy security policy 
	 * @return A secured Reception bean
	 */
	private Reception toSecureReception(Reception bean) {
		boolean isSecured = false;
		Reception transformedBean = new Reception();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadReceptionDescription()) {
			isSecured = true;

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getCommande() == null
					|| bean.getCommande() == null
					|| !transformedBean.getCommande().getId()
							.equals(bean.getCommande().getId())) {
				transformedBean.setCommande(bean.getCommande());
			}
			transformedBean.setDateReception(bean.getDateReception());
			transformedBean.setMedicaments(bean.getMedicaments());
			transformedBean.setIntrants(bean.getIntrants());
		} else {
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setCommande(null);
			transformedBean.setDateReception(null);
			transformedBean
					.setMedicaments(new Vector<DetailReceptionMedicament>());
			transformedBean.setIntrants(new Vector<DetailReceptionIntrant>());
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a DetailReceptionMedicament bean.
	 * @param bean The DetailReceptionMedicament bean to secure
	 * @param policy security policy 
	 * @return A secured DetailReceptionMedicament bean
	 */
	private DetailReceptionMedicament toSecureDetailReceptionMedicament(
			DetailReceptionMedicament bean) {
		boolean isSecured = false;
		DetailReceptionMedicament transformedBean = new DetailReceptionMedicament();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadDetailReceptionMedicamentDescription()) {
			isSecured = true;

			if (transformedBean.getReception() == null
					|| bean.getReception() == null
					|| !transformedBean.getReception().getId()
							.equals(bean.getReception().getId())) {
				transformedBean.setReception(bean.getReception());
			}

			if (transformedBean.getCommande() == null
					|| bean.getCommande() == null
					|| !transformedBean.getCommande().getId()
							.equals(bean.getCommande().getId())) {
				transformedBean.setCommande(bean.getCommande());
			}

			if (transformedBean.getDetailCommande() == null
					|| bean.getDetailCommande() == null
					|| !transformedBean.getDetailCommande().getId()
							.equals(bean.getDetailCommande().getId())) {
				transformedBean.setDetailCommande(bean.getDetailCommande());
			}

			if (transformedBean.getEntreeLot() == null
					|| bean.getEntreeLot() == null
					|| !transformedBean.getEntreeLot().getId()
							.equals(bean.getEntreeLot().getId())) {
				transformedBean.setEntreeLot(bean.getEntreeLot());
			}
		} else {
			transformedBean.setReception(null);
			transformedBean.setCommande(null);
			transformedBean.setDetailCommande(null);
			transformedBean.setEntreeLot(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a DetailReceptionIntrant bean.
	 * @param bean The DetailReceptionIntrant bean to secure
	 * @param policy security policy 
	 * @return A secured DetailReceptionIntrant bean
	 */
	private DetailReceptionIntrant toSecureDetailReceptionIntrant(
			DetailReceptionIntrant bean) {
		boolean isSecured = false;
		DetailReceptionIntrant transformedBean = new DetailReceptionIntrant();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadDetailReceptionIntrantDescription()) {
			isSecured = true;

			if (transformedBean.getReception() == null
					|| bean.getReception() == null
					|| !transformedBean.getReception().getId()
							.equals(bean.getReception().getId())) {
				transformedBean.setReception(bean.getReception());
			}

			if (transformedBean.getCommande() == null
					|| bean.getCommande() == null
					|| !transformedBean.getCommande().getId()
							.equals(bean.getCommande().getId())) {
				transformedBean.setCommande(bean.getCommande());
			}

			if (transformedBean.getDetailCommande() == null
					|| bean.getDetailCommande() == null
					|| !transformedBean.getDetailCommande().getId()
							.equals(bean.getDetailCommande().getId())) {
				transformedBean.setDetailCommande(bean.getDetailCommande());
			}

			if (transformedBean.getEntreeLot() == null
					|| bean.getEntreeLot() == null
					|| !transformedBean.getEntreeLot().getId()
							.equals(bean.getEntreeLot().getId())) {
				transformedBean.setEntreeLot(bean.getEntreeLot());
			}
		} else {
			transformedBean.setReception(null);
			transformedBean.setCommande(null);
			transformedBean.setDetailCommande(null);
			transformedBean.setEntreeLot(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a Ravitaillement bean.
	 * @param bean The Ravitaillement bean to secure
	 * @param policy security policy 
	 * @return A secured Ravitaillement bean
	 */
	private Ravitaillement toSecureRavitaillement(Ravitaillement bean) {
		boolean isSecured = false;
		Ravitaillement transformedBean = new Ravitaillement();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadRavitaillementInformationsDepart()) {
			isSecured = true;

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDTDepart() == null
					|| bean.getCDTDepart() == null
					|| !transformedBean.getCDTDepart().getId()
							.equals(bean.getCDTDepart().getId())) {
				transformedBean.setCDTDepart(bean.getCDTDepart());
			}
			transformedBean.setDateDepart(bean.getDateDepart());
		} else {
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDTDepart(null);
			transformedBean.setDateDepart(null);
		}
		if (policy.canReadRavitaillementInformationArrivee()) {
			isSecured = true;

			if (transformedBean.getRegionArrivee() == null
					|| bean.getRegionArrivee() == null
					|| !transformedBean.getRegionArrivee().getId()
							.equals(bean.getRegionArrivee().getId())) {
				transformedBean.setRegionArrivee(bean.getRegionArrivee());
			}

			if (transformedBean.getDistrictSanteArrivee() == null
					|| bean.getDistrictSanteArrivee() == null
					|| !transformedBean.getDistrictSanteArrivee().getId()
							.equals(bean.getDistrictSanteArrivee().getId())) {
				transformedBean.setDistrictSanteArrivee(bean
						.getDistrictSanteArrivee());
			}

			if (transformedBean.getCDTArrivee() == null
					|| bean.getCDTArrivee() == null
					|| !transformedBean.getCDTArrivee().getId()
							.equals(bean.getCDTArrivee().getId())) {
				transformedBean.setCDTArrivee(bean.getCDTArrivee());
			}
			transformedBean.setDateArrivee(bean.getDateArrivee());
		} else {
			transformedBean.setRegionArrivee(null);
			transformedBean.setDistrictSanteArrivee(null);
			transformedBean.setCDTArrivee(null);
			transformedBean.setDateArrivee(null);
		}
		if (policy.canReadRavitaillementDetail()) {
			isSecured = true;
			transformedBean.setDetails(bean.getDetails());
		} else {
			transformedBean.setDetails(new Vector<DetailRavitaillement>());
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a DetailRavitaillement bean.
	 * @param bean The DetailRavitaillement bean to secure
	 * @param policy security policy 
	 * @return A secured DetailRavitaillement bean
	 */
	private DetailRavitaillement toSecureDetailRavitaillement(
			DetailRavitaillement bean) {
		boolean isSecured = false;
		DetailRavitaillement transformedBean = new DetailRavitaillement();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadDetailRavitaillementDescription()) {
			isSecured = true;

			if (transformedBean.getRavitaillement() == null
					|| bean.getRavitaillement() == null
					|| !transformedBean.getRavitaillement().getId()
							.equals(bean.getRavitaillement().getId())) {
				transformedBean.setRavitaillement(bean.getRavitaillement());
			}
		} else {
			transformedBean.setRavitaillement(null);
		}
		if (policy.canReadDetailRavitaillementSortie()) {
			isSecured = true;

			if (transformedBean.getSortieLot() == null
					|| bean.getSortieLot() == null
					|| !transformedBean.getSortieLot().getId()
							.equals(bean.getSortieLot().getId())) {
				transformedBean.setSortieLot(bean.getSortieLot());
			}
		} else {
			transformedBean.setSortieLot(null);
		}
		if (policy.canReadDetailRavitaillementEntree()) {
			isSecured = true;

			if (transformedBean.getEntreeLot() == null
					|| bean.getEntreeLot() == null
					|| !transformedBean.getEntreeLot().getId()
							.equals(bean.getEntreeLot().getId())) {
				transformedBean.setEntreeLot(bean.getEntreeLot());
			}
		} else {
			transformedBean.setEntreeLot(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a Inventaire bean.
	 * @param bean The Inventaire bean to secure
	 * @param policy security policy 
	 * @return A secured Inventaire bean
	 */
	private Inventaire toSecureInventaire(Inventaire bean) {
		boolean isSecured = false;
		Inventaire transformedBean = new Inventaire();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadInventaireInformationsDepart()) {
			isSecured = true;
			transformedBean.setDate(bean.getDate());

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}
			transformedBean.setDetails(bean.getDetails());
		} else {
			transformedBean.setDate(null);
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setDetails(new Vector<DetailInventaire>());
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a DetailInventaire bean.
	 * @param bean The DetailInventaire bean to secure
	 * @param policy security policy 
	 * @return A secured DetailInventaire bean
	 */
	private DetailInventaire toSecureDetailInventaire(DetailInventaire bean) {
		boolean isSecured = false;
		DetailInventaire transformedBean = new DetailInventaire();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadDetailInventaireDescription()) {
			isSecured = true;

			if (transformedBean.getInventaire() == null
					|| bean.getInventaire() == null
					|| !transformedBean.getInventaire().getId()
							.equals(bean.getInventaire().getId())) {
				transformedBean.setInventaire(bean.getInventaire());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getLot() == null
					|| bean.getLot() == null
					|| !transformedBean.getLot().getId()
							.equals(bean.getLot().getId())) {
				transformedBean.setLot(bean.getLot());
			}
			transformedBean.setQuantiteReelle(bean.getQuantiteReelle());
			transformedBean.setQuantiteTheorique(bean.getQuantiteTheorique());
		} else {
			transformedBean.setInventaire(null);
			transformedBean.setCDT(null);
			transformedBean.setLot(null);
			transformedBean.setQuantiteReelle(null);
			transformedBean.setQuantiteTheorique(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a Personnel bean.
	 * @param bean The Personnel bean to secure
	 * @param policy security policy 
	 * @return A secured Personnel bean
	 */
	private Personnel toSecurePersonnel(Personnel bean) {
		boolean isSecured = false;
		Personnel transformedBean = new Personnel();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadPersonnelIdentification()) {
			isSecured = true;
			transformedBean.setNom(bean.getNom());
			transformedBean.setDateNaissance(bean.getDateNaissance());
			transformedBean.setProfession(bean.getProfession());
		} else {
			transformedBean.setNom(null);
			transformedBean.setDateNaissance(null);
			transformedBean.setProfession(null);
		}
		if (policy.canReadPersonnelContact()) {
			isSecured = true;
			transformedBean.setTelephoneUn(bean.getTelephoneUn());
			transformedBean.setTelephoneDeux(bean.getTelephoneDeux());
			transformedBean.setTelephoneTrois(bean.getTelephoneTrois());
			transformedBean.setEmail(bean.getEmail());
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
		} else {
			transformedBean.setTelephoneUn(null);
			transformedBean.setTelephoneDeux(null);
			transformedBean.setTelephoneTrois(null);
			transformedBean.setEmail(null);
			transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
		}
		if (policy.canReadPersonnelSituation()) {
			isSecured = true;
			transformedBean.setDateDepart(bean.getDateDepart());
			transformedBean.setDateArrivee(bean.getDateArrivee());
			transformedBean.setAEteForme(bean.getAEteForme());
			transformedBean.setQualification(bean.getQualification());
		} else {
			transformedBean.setDateDepart(null);
			transformedBean.setDateArrivee(null);
			transformedBean.setAEteForme(null);
			transformedBean.setQualification(new Vector<Qualification>());
		}
		if (policy.canReadPersonnelNiveauAccess()) {
			isSecured = true;
			transformedBean.setNiveau(bean.getNiveau());

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}
			transformedBean.setActif(bean.getActif());
		} else {
			transformedBean.setNiveau(null);
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setActif(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a DepartPersonnel bean.
	 * @param bean The DepartPersonnel bean to secure
	 * @param policy security policy 
	 * @return A secured DepartPersonnel bean
	 */
	private DepartPersonnel toSecureDepartPersonnel(DepartPersonnel bean) {
		boolean isSecured = false;
		DepartPersonnel transformedBean = new DepartPersonnel();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadDepartPersonnelPersonnel()) {
			isSecured = true;

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getPersonnel() == null
					|| bean.getPersonnel() == null
					|| !transformedBean.getPersonnel().getId()
							.equals(bean.getPersonnel().getId())) {
				transformedBean.setPersonnel(bean.getPersonnel());
			}
		} else {
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setPersonnel(null);
		}
		if (policy.canReadDepartPersonnelDescription()) {
			isSecured = true;
			transformedBean.setDateDepart(bean.getDateDepart());
			transformedBean.setMotifDepart(bean.getMotifDepart());
		} else {
			transformedBean.setDateDepart(null);
			transformedBean.setMotifDepart(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a ArriveePersonnel bean.
	 * @param bean The ArriveePersonnel bean to secure
	 * @param policy security policy 
	 * @return A secured ArriveePersonnel bean
	 */
	private ArriveePersonnel toSecureArriveePersonnel(ArriveePersonnel bean) {
		boolean isSecured = false;
		ArriveePersonnel transformedBean = new ArriveePersonnel();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadArriveePersonnelDescription()) {
			isSecured = true;

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getPersonnel() == null
					|| bean.getPersonnel() == null
					|| !transformedBean.getPersonnel().getId()
							.equals(bean.getPersonnel().getId())) {
				transformedBean.setPersonnel(bean.getPersonnel());
			}
			transformedBean.setDateArrivee(bean.getDateArrivee());
		} else {
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setPersonnel(null);
			transformedBean.setDateArrivee(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a Region bean.
	 * @param bean The Region bean to secure
	 * @param policy security policy 
	 * @return A secured Region bean
	 */
	private Region toSecureRegion(Region bean) {
		boolean isSecured = false;
		Region transformedBean = new Region();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadRegionDescription()) {
			isSecured = true;
			transformedBean.setCode(bean.getCode());
			transformedBean.setNom(bean.getNom());
			transformedBean.setDescription(bean.getDescription());
			transformedBean.setDistrictSantes(bean.getDistrictSantes());
		} else {
			transformedBean.setCode(null);
			transformedBean.setNom(null);
			transformedBean.setDescription(null);
			transformedBean.setDistrictSantes(new Vector<DistrictSante>());
		}
		if (policy.canReadRegionAdresse()) {
			isSecured = true;
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
			transformedBean.setCoordonnees(bean.getCoordonnees());
		} else {
			transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
			transformedBean.setCoordonnees(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a DistrictSante bean.
	 * @param bean The DistrictSante bean to secure
	 * @param policy security policy 
	 * @return A secured DistrictSante bean
	 */
	private DistrictSante toSecureDistrictSante(DistrictSante bean) {
		boolean isSecured = false;
		DistrictSante transformedBean = new DistrictSante();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadDistrictSanteDescription()) {
			isSecured = true;
			transformedBean.setCode(bean.getCode());
			transformedBean.setNom(bean.getNom());
			transformedBean.setDescription(bean.getDescription());

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}
		} else {
			transformedBean.setCode(null);
			transformedBean.setNom(null);
			transformedBean.setDescription(null);
			transformedBean.setRegion(null);
		}
		if (policy.canReadDistrictSanteAdresse()) {
			isSecured = true;
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
			transformedBean.setCoordonnees(bean.getCoordonnees());
		} else {
			transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
			transformedBean.setCoordonnees(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a CentreDiagTrait bean.
	 * @param bean The CentreDiagTrait bean to secure
	 * @param policy security policy 
	 * @return A secured CentreDiagTrait bean
	 */
	private CentreDiagTrait toSecureCentreDiagTrait(CentreDiagTrait bean) {
		boolean isSecured = false;
		CentreDiagTrait transformedBean = new CentreDiagTrait();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadCentreDiagTraitDescription()) {
			isSecured = true;
			transformedBean.setCode(bean.getCode());

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}
			transformedBean.setNom(bean.getNom());
			transformedBean.setDescription(bean.getDescription());
		} else {
			transformedBean.setCode(null);
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setNom(null);
			transformedBean.setDescription(null);
		}
		if (policy.canReadCentreDiagTraitAdresse()) {
			isSecured = true;
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
			transformedBean.setCoordonnees(bean.getCoordonnees());
			transformedBean.setLieuxDits(bean.getLieuxDits());
		} else {
			transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
			transformedBean.setCoordonnees(null);
			transformedBean.setLieuxDits(new Vector<LieuDit>());
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a LaboratoireReference bean.
	 * @param bean The LaboratoireReference bean to secure
	 * @param policy security policy 
	 * @return A secured LaboratoireReference bean
	 */
	private LaboratoireReference toSecureLaboratoireReference(
			LaboratoireReference bean) {
		boolean isSecured = false;
		LaboratoireReference transformedBean = new LaboratoireReference();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadLaboratoireReferenceDescription()) {
			isSecured = true;
			transformedBean.setNom(bean.getNom());
			transformedBean.setNature(bean.getNature());
			transformedBean.setDescription(bean.getDescription());

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}
		} else {
			transformedBean.setNom(null);
			transformedBean.setNature(null);
			transformedBean.setDescription(null);
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
		}
		if (policy.canReadLaboratoireReferenceAdresse()) {
			isSecured = true;
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
			transformedBean.setCoordonnees(bean.getCoordonnees());
			transformedBean.setLieuxDits(bean.getLieuxDits());
		} else {
			transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
			transformedBean.setCoordonnees(null);
			transformedBean.setLieuxDits(new Vector<LieuDit>());
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a LieuDit bean.
	 * @param bean The LieuDit bean to secure
	 * @param policy security policy 
	 * @return A secured LieuDit bean
	 */
	private LieuDit toSecureLieuDit(LieuDit bean) {
		boolean isSecured = false;
		LieuDit transformedBean = new LieuDit();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadLieuDitDescription()) {
			isSecured = true;
			transformedBean.setCode(bean.getCode());
			transformedBean.setNom(bean.getNom());
			transformedBean.setDescription(bean.getDescription());
		} else {
			transformedBean.setCode(null);
			transformedBean.setNom(null);
			transformedBean.setDescription(null);
		}
		if (policy.canReadLieuDitAdresse()) {
			isSecured = true;
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
			transformedBean.setCoordonnees(bean.getCoordonnees());
		} else {
			transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
			transformedBean.setCoordonnees(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a Regime bean.
	 * @param bean The Regime bean to secure
	 * @param policy security policy 
	 * @return A secured Regime bean
	 */
	private Regime toSecureRegime(Regime bean) {
		boolean isSecured = false;
		Regime transformedBean = new Regime();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadRegimeDescription()) {
			isSecured = true;
			transformedBean.setNom(bean.getNom());
			transformedBean.setType(bean.getType());
			transformedBean.setDureeTraitement(bean.getDureeTraitement());
			transformedBean.setPoidsMin(bean.getPoidsMin());
			transformedBean.setPoidsMax(bean.getPoidsMax());
			transformedBean.setDescription(bean.getDescription());
			transformedBean.setPrisesMedicamenteuses(bean
					.getPrisesMedicamenteuses());
			transformedBean.setActif(bean.getActif());
		} else {
			transformedBean.setNom(null);
			transformedBean.setType(null);
			transformedBean.setDureeTraitement(null);
			transformedBean.setPoidsMin(null);
			transformedBean.setPoidsMax(null);
			transformedBean.setDescription(null);
			transformedBean
					.setPrisesMedicamenteuses(new Vector<PriseMedicamentRegime>());
			transformedBean.setActif(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a PriseMedicamentRegime bean.
	 * @param bean The PriseMedicamentRegime bean to secure
	 * @param policy security policy 
	 * @return A secured PriseMedicamentRegime bean
	 */
	private PriseMedicamentRegime toSecurePriseMedicamentRegime(
			PriseMedicamentRegime bean) {
		boolean isSecured = false;
		PriseMedicamentRegime transformedBean = new PriseMedicamentRegime();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadPriseMedicamentRegimeDescription()) {
			isSecured = true;

			if (transformedBean.getRegime() == null
					|| bean.getRegime() == null
					|| !transformedBean.getRegime().getId()
							.equals(bean.getRegime().getId())) {
				transformedBean.setRegime(bean.getRegime());
			}

			if (transformedBean.getMedicament() == null
					|| bean.getMedicament() == null
					|| !transformedBean.getMedicament().getId()
							.equals(bean.getMedicament().getId())) {
				transformedBean.setMedicament(bean.getMedicament());
			}
			transformedBean.setQuantite(bean.getQuantite());
			transformedBean.setQuantiteUnite(bean.getQuantiteUnite());
		} else {
			transformedBean.setRegime(null);
			transformedBean.setMedicament(null);
			transformedBean.setQuantite(null);
			transformedBean.setQuantiteUnite(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a Medicament bean.
	 * @param bean The Medicament bean to secure
	 * @param policy security policy 
	 * @return A secured Medicament bean
	 */
	private Medicament toSecureMedicament(Medicament bean) {
		boolean isSecured = false;
		Medicament transformedBean = new Medicament();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadMedicamentDescription()) {
			isSecured = true;
			transformedBean.setCode(bean.getCode());
			transformedBean.setDesignation(bean.getDesignation());
			transformedBean.setSeuilPatient(bean.getSeuilPatient());
			transformedBean.setEstMedicamentAntituberculeux(bean
					.getEstMedicamentAntituberculeux());
		} else {
			transformedBean.setCode(null);
			transformedBean.setDesignation(null);
			transformedBean.setSeuilPatient(null);
			transformedBean.setEstMedicamentAntituberculeux(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a Intrant bean.
	 * @param bean The Intrant bean to secure
	 * @param policy security policy 
	 * @return A secured Intrant bean
	 */
	private Intrant toSecureIntrant(Intrant bean) {
		boolean isSecured = false;
		Intrant transformedBean = new Intrant();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadIntrantDescription()) {
			isSecured = true;
			transformedBean.setIdentifiant(bean.getIdentifiant());
			transformedBean.setNom(bean.getNom());
			transformedBean.setDescription(bean.getDescription());
			transformedBean.setSeuilPatient(bean.getSeuilPatient());
		} else {
			transformedBean.setIdentifiant(null);
			transformedBean.setNom(null);
			transformedBean.setDescription(null);
			transformedBean.setSeuilPatient(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a Formation bean.
	 * @param bean The Formation bean to secure
	 * @param policy security policy 
	 * @return A secured Formation bean
	 */
	private Formation toSecureFormation(Formation bean) {
		boolean isSecured = false;
		Formation transformedBean = new Formation();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadFormationDescription()) {
			isSecured = true;
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setDateDebut(bean.getDateDebut());
			transformedBean.setDateFin(bean.getDateFin());
			transformedBean.setLieu(bean.getLieu());
			transformedBean.setObjectifs(bean.getObjectifs());
			transformedBean.setEffectuee(bean.getEffectuee());
			transformedBean.setCandidatures(bean.getCandidatures());
		} else {
			transformedBean.setLibelle(null);
			transformedBean.setDateDebut(null);
			transformedBean.setDateFin(null);
			transformedBean.setLieu(null);
			transformedBean.setObjectifs(null);
			transformedBean.setEffectuee(null);
			transformedBean.setCandidatures(new Vector<CandidatureFormation>());
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a CandidatureFormation bean.
	 * @param bean The CandidatureFormation bean to secure
	 * @param policy security policy 
	 * @return A secured CandidatureFormation bean
	 */
	private CandidatureFormation toSecureCandidatureFormation(
			CandidatureFormation bean) {
		boolean isSecured = false;
		CandidatureFormation transformedBean = new CandidatureFormation();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadCandidatureFormationDescription()) {
			isSecured = true;

			if (transformedBean.getFormation() == null
					|| bean.getFormation() == null
					|| !transformedBean.getFormation().getId()
							.equals(bean.getFormation().getId())) {
				transformedBean.setFormation(bean.getFormation());
			}

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getPersonnel() == null
					|| bean.getPersonnel() == null
					|| !transformedBean.getPersonnel().getId()
							.equals(bean.getPersonnel().getId())) {
				transformedBean.setPersonnel(bean.getPersonnel());
			}
		} else {
			transformedBean.setFormation(null);
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setPersonnel(null);
		}
		if (policy.canReadCandidatureFormationRegionApprobation()) {
			isSecured = true;
			transformedBean.setApprouveeRegion(bean.getApprouveeRegion());
			transformedBean.setMotifRejetRegion(bean.getMotifRejetRegion());
		} else {
			transformedBean.setApprouveeRegion(null);
			transformedBean.setMotifRejetRegion(null);
		}
		if (policy.canReadCandidatureFormationGtcApprobation()) {
			isSecured = true;
			transformedBean.setApprouveeGTC(bean.getApprouveeGTC());
			transformedBean.setMotifRejetGTC(bean.getMotifRejetGTC());
		} else {
			transformedBean.setApprouveeGTC(null);
			transformedBean.setMotifRejetGTC(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a Qualification bean.
	 * @param bean The Qualification bean to secure
	 * @param policy security policy 
	 * @return A secured Qualification bean
	 */
	private Qualification toSecureQualification(Qualification bean) {
		boolean isSecured = false;
		Qualification transformedBean = new Qualification();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadQualificationDescription()) {
			isSecured = true;
			transformedBean.setCode(bean.getCode());
			transformedBean.setNom(bean.getNom());
			transformedBean.setDescription(bean.getDescription());
		} else {
			transformedBean.setCode(null);
			transformedBean.setNom(null);
			transformedBean.setDescription(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a Tutoriel bean.
	 * @param bean The Tutoriel bean to secure
	 * @param policy security policy 
	 * @return A secured Tutoriel bean
	 */
	private Tutoriel toSecureTutoriel(Tutoriel bean) {
		boolean isSecured = false;
		Tutoriel transformedBean = new Tutoriel();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadTutorielDescription()) {
			isSecured = true;
			transformedBean.setReference(bean.getReference());
			transformedBean.setNom(bean.getNom());
			transformedBean.setDescription(bean.getDescription());
			transformedBean.setType(bean.getType());
			transformedBean.setAudioT(bean.getAudioT());
			transformedBean.setVideoT(bean.getVideoT());
			transformedBean.setTextT(bean.getTextT());
		} else {
			transformedBean.setReference(null);
			transformedBean.setNom(null);
			transformedBean.setDescription(null);
			transformedBean.setType(null);
			transformedBean.setAudioT(null);
			transformedBean.setVideoT(null);
			transformedBean.setTextT(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a SmsPredefini bean.
	 * @param bean The SmsPredefini bean to secure
	 * @param policy security policy 
	 * @return A secured SmsPredefini bean
	 */
	private SmsPredefini toSecureSmsPredefini(SmsPredefini bean) {
		boolean isSecured = false;
		SmsPredefini transformedBean = new SmsPredefini();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadSmsPredefiniDescription()) {
			isSecured = true;
			transformedBean.setType(bean.getType());
			transformedBean.setObjet(bean.getObjet());
			transformedBean.setPeriodicite(bean.getPeriodicite());
			transformedBean.setDateEnvoyeSMSPonctuel(bean
					.getDateEnvoyeSMSPonctuel());
			transformedBean.setStatut(bean.getStatut());
			transformedBean.setMessage(bean.getMessage());
			transformedBean.setReponse1(bean.getReponse1());
			transformedBean.setReponse2(bean.getReponse2());
			transformedBean.setBonneReponse(bean.getBonneReponse());
			transformedBean.setEnvoyerAPartirDe(bean.getEnvoyerAPartirDe());
			transformedBean.setArreterEnvoyerA(bean.getArreterEnvoyerA());
		} else {
			transformedBean.setType(null);
			transformedBean.setObjet(null);
			transformedBean.setPeriodicite(null);
			transformedBean.setDateEnvoyeSMSPonctuel(null);
			transformedBean.setStatut(null);
			transformedBean.setMessage(null);
			transformedBean.setReponse1(null);
			transformedBean.setReponse2(null);
			transformedBean.setBonneReponse(null);
			transformedBean.setEnvoyerAPartirDe(null);
			transformedBean.setArreterEnvoyerA(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a OutBox bean.
	 * @param bean The OutBox bean to secure
	 * @param policy security policy 
	 * @return A secured OutBox bean
	 */
	private OutBox toSecureOutBox(OutBox bean) {
		boolean isSecured = false;
		OutBox transformedBean = new OutBox();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadOutBoxAdresse()) {
			isSecured = true;

			if (transformedBean.getPatient() == null
					|| bean.getPatient() == null
					|| !transformedBean.getPatient().getId()
							.equals(bean.getPatient().getId())) {
				transformedBean.setPatient(bean.getPatient());
			}
		} else {
			transformedBean.setPatient(null);
		}
		if (policy.canReadOutBoxMessageInformation()) {
			isSecured = true;
			transformedBean.setMessage(bean.getMessage());
			transformedBean.setReponse(bean.getReponse());
			transformedBean.setStatut(bean.getStatut());
			transformedBean.setDateDernierEssai(bean.getDateDernierEssai());
		} else {
			transformedBean.setMessage(null);
			transformedBean.setReponse(null);
			transformedBean.setStatut(null);
			transformedBean.setDateDernierEssai(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

	/**
	 * Secure a Utilisateur bean.
	 * @param bean The Utilisateur bean to secure
	 * @param policy security policy 
	 * @return A secured Utilisateur bean
	 */
	private Utilisateur toSecureUtilisateur(Utilisateur bean) {
		boolean isSecured = false;
		Utilisateur transformedBean = new Utilisateur();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		if (policy.canReadUtilisateurIdentification()) {
			isSecured = true;
			transformedBean.setNom(bean.getNom());
			transformedBean.setDateNaissance(bean.getDateNaissance());
			transformedBean.setProfession(bean.getProfession());
		} else {
			transformedBean.setNom(null);
			transformedBean.setDateNaissance(null);
			transformedBean.setProfession(null);
		}
		if (policy.canReadUtilisateurContact()) {
			isSecured = true;
			transformedBean.setTelephoneUn(bean.getTelephoneUn());
			transformedBean.setTelephoneDeux(bean.getTelephoneDeux());
			transformedBean.setTelephoneTrois(bean.getTelephoneTrois());
			transformedBean.setEmail(bean.getEmail());
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
		} else {
			transformedBean.setTelephoneUn(null);
			transformedBean.setTelephoneDeux(null);
			transformedBean.setTelephoneTrois(null);
			transformedBean.setEmail(null);
			transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
		}

		if (isSecured) {
			return transformedBean;
		} else {
			return null;
		}
	}

}
