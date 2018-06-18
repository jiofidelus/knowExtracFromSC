package org.imogene.epicam.server.handler;

import java.util.List;

import org.apache.log4j.Logger;
import org.imogene.lib.common.entity.ImogActor;
import org.imogene.lib.common.entity.ImogBean;
import org.imogene.web.server.handler.BasicHandlerHelper;
import org.imogene.epicam.domain.entity.*;

public class HandlerHelperImpl extends BasicHandlerHelper {

	private static final Logger logger = Logger
			.getLogger(HandlerHelperImpl.class);

	private PatientHandler patientHandler;
	private CasIndexHandler casIndexHandler;
	private CasTuberculoseHandler casTuberculoseHandler;
	private ExamenSerologieHandler examenSerologieHandler;
	private ExamenBiologiqueHandler examenBiologiqueHandler;
	private ExamenMicroscopieHandler examenMicroscopieHandler;
	private ExamenATBHandler examenATBHandler;
	private PriseMedicamenteuseHandler priseMedicamenteuseHandler;
	private RendezVousHandler rendezVousHandler;
	private TransfertReferenceHandler transfertReferenceHandler;
	private LotHandler lotHandler;
	private HorsUsageHandler horsUsageHandler;
	private EntreeLotHandler entreeLotHandler;
	private SortieLotHandler sortieLotHandler;
	private CommandeHandler commandeHandler;
	private DetailCommandeMedicamentHandler detailCommandeMedicamentHandler;
	private DetailCommandeIntrantHandler detailCommandeIntrantHandler;
	private ReceptionHandler receptionHandler;
	private DetailReceptionMedicamentHandler detailReceptionMedicamentHandler;
	private DetailReceptionIntrantHandler detailReceptionIntrantHandler;
	private RavitaillementHandler ravitaillementHandler;
	private DetailRavitaillementHandler detailRavitaillementHandler;
	private InventaireHandler inventaireHandler;
	private DetailInventaireHandler detailInventaireHandler;
	private PersonnelHandler personnelHandler;
	private DepartPersonnelHandler departPersonnelHandler;
	private ArriveePersonnelHandler arriveePersonnelHandler;
	private RegionHandler regionHandler;
	private DistrictSanteHandler districtSanteHandler;
	private CentreDiagTraitHandler centreDiagTraitHandler;
	private LaboratoireReferenceHandler laboratoireReferenceHandler;
	private LieuDitHandler lieuDitHandler;
	private RegimeHandler regimeHandler;
	private PriseMedicamentRegimeHandler priseMedicamentRegimeHandler;
	private MedicamentHandler medicamentHandler;
	private IntrantHandler intrantHandler;
	private FormationHandler formationHandler;
	private CandidatureFormationHandler candidatureFormationHandler;
	private QualificationHandler qualificationHandler;
	private TutorielHandler tutorielHandler;
	private SmsPredefiniHandler smsPredefiniHandler;
	private OutBoxHandler outBoxHandler;
	private UtilisateurHandler utilisateurHandler;

	@Override
	public void delete(ImogBean bean) {
		super.delete(bean);

		if (bean instanceof Patient) {
			patientHandler.delete((Patient) bean);
		} else if (bean instanceof CasIndex) {
			casIndexHandler.delete((CasIndex) bean);
		} else if (bean instanceof CasTuberculose) {
			casTuberculoseHandler.delete((CasTuberculose) bean);
		} else if (bean instanceof ExamenSerologie) {
			examenSerologieHandler.delete((ExamenSerologie) bean);
		} else if (bean instanceof ExamenBiologique) {
			examenBiologiqueHandler.delete((ExamenBiologique) bean);
		} else if (bean instanceof ExamenMicroscopie) {
			examenMicroscopieHandler.delete((ExamenMicroscopie) bean);
		} else if (bean instanceof ExamenATB) {
			examenATBHandler.delete((ExamenATB) bean);
		} else if (bean instanceof PriseMedicamenteuse) {
			priseMedicamenteuseHandler.delete((PriseMedicamenteuse) bean);
		} else if (bean instanceof RendezVous) {
			rendezVousHandler.delete((RendezVous) bean);
		} else if (bean instanceof TransfertReference) {
			transfertReferenceHandler.delete((TransfertReference) bean);
		} else if (bean instanceof Lot) {
			lotHandler.delete((Lot) bean);
		} else if (bean instanceof HorsUsage) {
			horsUsageHandler.delete((HorsUsage) bean);
		} else if (bean instanceof EntreeLot) {
			entreeLotHandler.delete((EntreeLot) bean);
		} else if (bean instanceof SortieLot) {
			sortieLotHandler.delete((SortieLot) bean);
		} else if (bean instanceof Commande) {
			commandeHandler.delete((Commande) bean);
		} else if (bean instanceof DetailCommandeMedicament) {
			detailCommandeMedicamentHandler
					.delete((DetailCommandeMedicament) bean);
		} else if (bean instanceof DetailCommandeIntrant) {
			detailCommandeIntrantHandler.delete((DetailCommandeIntrant) bean);
		} else if (bean instanceof Reception) {
			receptionHandler.delete((Reception) bean);
		} else if (bean instanceof DetailReceptionMedicament) {
			detailReceptionMedicamentHandler
					.delete((DetailReceptionMedicament) bean);
		} else if (bean instanceof DetailReceptionIntrant) {
			detailReceptionIntrantHandler.delete((DetailReceptionIntrant) bean);
		} else if (bean instanceof Ravitaillement) {
			ravitaillementHandler.delete((Ravitaillement) bean);
		} else if (bean instanceof DetailRavitaillement) {
			detailRavitaillementHandler.delete((DetailRavitaillement) bean);
		} else if (bean instanceof Inventaire) {
			inventaireHandler.delete((Inventaire) bean);
		} else if (bean instanceof DetailInventaire) {
			detailInventaireHandler.delete((DetailInventaire) bean);
		} else if (bean instanceof Personnel) {
			personnelHandler.delete((Personnel) bean);
		} else if (bean instanceof DepartPersonnel) {
			departPersonnelHandler.delete((DepartPersonnel) bean);
		} else if (bean instanceof ArriveePersonnel) {
			arriveePersonnelHandler.delete((ArriveePersonnel) bean);
		} else if (bean instanceof Region) {
			regionHandler.delete((Region) bean);
		} else if (bean instanceof DistrictSante) {
			districtSanteHandler.delete((DistrictSante) bean);
		} else if (bean instanceof CentreDiagTrait) {
			centreDiagTraitHandler.delete((CentreDiagTrait) bean);
		} else if (bean instanceof LaboratoireReference) {
			laboratoireReferenceHandler.delete((LaboratoireReference) bean);
		} else if (bean instanceof LieuDit) {
			lieuDitHandler.delete((LieuDit) bean);
		} else if (bean instanceof Regime) {
			regimeHandler.delete((Regime) bean);
		} else if (bean instanceof PriseMedicamentRegime) {
			priseMedicamentRegimeHandler.delete((PriseMedicamentRegime) bean);
		} else if (bean instanceof Medicament) {
			medicamentHandler.delete((Medicament) bean);
		} else if (bean instanceof Intrant) {
			intrantHandler.delete((Intrant) bean);
		} else if (bean instanceof Formation) {
			formationHandler.delete((Formation) bean);
		} else if (bean instanceof CandidatureFormation) {
			candidatureFormationHandler.delete((CandidatureFormation) bean);
		} else if (bean instanceof Qualification) {
			qualificationHandler.delete((Qualification) bean);
		} else if (bean instanceof Tutoriel) {
			tutorielHandler.delete((Tutoriel) bean);
		} else if (bean instanceof SmsPredefini) {
			smsPredefiniHandler.delete((SmsPredefini) bean);
		} else if (bean instanceof OutBox) {
			outBoxHandler.delete((OutBox) bean);
		} else if (bean instanceof Utilisateur) {
			utilisateurHandler.delete((Utilisateur) bean);
		}

	}

	@Override
	public void save(ImogBean bean, boolean isNew) {
		super.save(bean, isNew);

		if (bean instanceof Patient) {
			patientHandler.save((Patient) bean, isNew);
		} else if (bean instanceof CasIndex) {
			casIndexHandler.save((CasIndex) bean, isNew);
		} else if (bean instanceof CasTuberculose) {
			casTuberculoseHandler.save((CasTuberculose) bean, isNew);
		} else if (bean instanceof ExamenSerologie) {
			examenSerologieHandler.save((ExamenSerologie) bean, isNew);
		} else if (bean instanceof ExamenBiologique) {
			examenBiologiqueHandler.save((ExamenBiologique) bean, isNew);
		} else if (bean instanceof ExamenMicroscopie) {
			examenMicroscopieHandler.save((ExamenMicroscopie) bean, isNew);
		} else if (bean instanceof ExamenATB) {
			examenATBHandler.save((ExamenATB) bean, isNew);
		} else if (bean instanceof PriseMedicamenteuse) {
			priseMedicamenteuseHandler.save((PriseMedicamenteuse) bean, isNew);
		} else if (bean instanceof RendezVous) {
			rendezVousHandler.save((RendezVous) bean, isNew);
		} else if (bean instanceof TransfertReference) {
			transfertReferenceHandler.save((TransfertReference) bean, isNew);
		} else if (bean instanceof Lot) {
			lotHandler.save((Lot) bean, isNew);
		} else if (bean instanceof HorsUsage) {
			horsUsageHandler.save((HorsUsage) bean, isNew);
		} else if (bean instanceof EntreeLot) {
			entreeLotHandler.save((EntreeLot) bean, isNew);
		} else if (bean instanceof SortieLot) {
			sortieLotHandler.save((SortieLot) bean, isNew);
		} else if (bean instanceof Commande) {
			commandeHandler.save((Commande) bean, isNew);
		} else if (bean instanceof DetailCommandeMedicament) {
			detailCommandeMedicamentHandler.save(
					(DetailCommandeMedicament) bean, isNew);
		} else if (bean instanceof DetailCommandeIntrant) {
			detailCommandeIntrantHandler.save((DetailCommandeIntrant) bean,
					isNew);
		} else if (bean instanceof Reception) {
			receptionHandler.save((Reception) bean, isNew);
		} else if (bean instanceof DetailReceptionMedicament) {
			detailReceptionMedicamentHandler.save(
					(DetailReceptionMedicament) bean, isNew);
		} else if (bean instanceof DetailReceptionIntrant) {
			detailReceptionIntrantHandler.save((DetailReceptionIntrant) bean,
					isNew);
		} else if (bean instanceof Ravitaillement) {
			ravitaillementHandler.save((Ravitaillement) bean, isNew);
		} else if (bean instanceof DetailRavitaillement) {
			detailRavitaillementHandler
					.save((DetailRavitaillement) bean, isNew);
		} else if (bean instanceof Inventaire) {
			inventaireHandler.save((Inventaire) bean, isNew);
		} else if (bean instanceof DetailInventaire) {
			detailInventaireHandler.save((DetailInventaire) bean, isNew);
		} else if (bean instanceof Personnel) {
			personnelHandler.save((Personnel) bean, isNew);
		} else if (bean instanceof DepartPersonnel) {
			departPersonnelHandler.save((DepartPersonnel) bean, isNew);
		} else if (bean instanceof ArriveePersonnel) {
			arriveePersonnelHandler.save((ArriveePersonnel) bean, isNew);
		} else if (bean instanceof Region) {
			regionHandler.save((Region) bean, isNew);
		} else if (bean instanceof DistrictSante) {
			districtSanteHandler.save((DistrictSante) bean, isNew);
		} else if (bean instanceof CentreDiagTrait) {
			centreDiagTraitHandler.save((CentreDiagTrait) bean, isNew);
		} else if (bean instanceof LaboratoireReference) {
			laboratoireReferenceHandler
					.save((LaboratoireReference) bean, isNew);
		} else if (bean instanceof LieuDit) {
			lieuDitHandler.save((LieuDit) bean, isNew);
		} else if (bean instanceof Regime) {
			regimeHandler.save((Regime) bean, isNew);
		} else if (bean instanceof PriseMedicamentRegime) {
			priseMedicamentRegimeHandler.save((PriseMedicamentRegime) bean,
					isNew);
		} else if (bean instanceof Medicament) {
			medicamentHandler.save((Medicament) bean, isNew);
		} else if (bean instanceof Intrant) {
			intrantHandler.save((Intrant) bean, isNew);
		} else if (bean instanceof Formation) {
			formationHandler.save((Formation) bean, isNew);
		} else if (bean instanceof CandidatureFormation) {
			candidatureFormationHandler
					.save((CandidatureFormation) bean, isNew);
		} else if (bean instanceof Qualification) {
			qualificationHandler.save((Qualification) bean, isNew);
		} else if (bean instanceof Tutoriel) {
			tutorielHandler.save((Tutoriel) bean, isNew);
		} else if (bean instanceof SmsPredefini) {
			smsPredefiniHandler.save((SmsPredefini) bean, isNew);
		} else if (bean instanceof OutBox) {
			outBoxHandler.save((OutBox) bean, isNew);
		} else if (bean instanceof Utilisateur) {
			utilisateurHandler.save((Utilisateur) bean, isNew);
		}

	}

	@Override
	public void detach(ImogActor actor) {
		super.detach(actor);
	}

	/**
	 * Setter for bean injection
	 * @param handler the Patient Handler
	 */
	public void setPatientHandler(PatientHandler handler) {
		this.patientHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the CasIndex Handler
	 */
	public void setCasIndexHandler(CasIndexHandler handler) {
		this.casIndexHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the CasTuberculose Handler
	 */
	public void setCasTuberculoseHandler(CasTuberculoseHandler handler) {
		this.casTuberculoseHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the ExamenSerologie Handler
	 */
	public void setExamenSerologieHandler(ExamenSerologieHandler handler) {
		this.examenSerologieHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the ExamenBiologique Handler
	 */
	public void setExamenBiologiqueHandler(ExamenBiologiqueHandler handler) {
		this.examenBiologiqueHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the ExamenMicroscopie Handler
	 */
	public void setExamenMicroscopieHandler(ExamenMicroscopieHandler handler) {
		this.examenMicroscopieHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the ExamenATB Handler
	 */
	public void setExamenATBHandler(ExamenATBHandler handler) {
		this.examenATBHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the PriseMedicamenteuse Handler
	 */
	public void setPriseMedicamenteuseHandler(PriseMedicamenteuseHandler handler) {
		this.priseMedicamenteuseHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the RendezVous Handler
	 */
	public void setRendezVousHandler(RendezVousHandler handler) {
		this.rendezVousHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the TransfertReference Handler
	 */
	public void setTransfertReferenceHandler(TransfertReferenceHandler handler) {
		this.transfertReferenceHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the Lot Handler
	 */
	public void setLotHandler(LotHandler handler) {
		this.lotHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the HorsUsage Handler
	 */
	public void setHorsUsageHandler(HorsUsageHandler handler) {
		this.horsUsageHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the EntreeLot Handler
	 */
	public void setEntreeLotHandler(EntreeLotHandler handler) {
		this.entreeLotHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the SortieLot Handler
	 */
	public void setSortieLotHandler(SortieLotHandler handler) {
		this.sortieLotHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the Commande Handler
	 */
	public void setCommandeHandler(CommandeHandler handler) {
		this.commandeHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the DetailCommandeMedicament Handler
	 */
	public void setDetailCommandeMedicamentHandler(
			DetailCommandeMedicamentHandler handler) {
		this.detailCommandeMedicamentHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the DetailCommandeIntrant Handler
	 */
	public void setDetailCommandeIntrantHandler(
			DetailCommandeIntrantHandler handler) {
		this.detailCommandeIntrantHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the Reception Handler
	 */
	public void setReceptionHandler(ReceptionHandler handler) {
		this.receptionHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the DetailReceptionMedicament Handler
	 */
	public void setDetailReceptionMedicamentHandler(
			DetailReceptionMedicamentHandler handler) {
		this.detailReceptionMedicamentHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the DetailReceptionIntrant Handler
	 */
	public void setDetailReceptionIntrantHandler(
			DetailReceptionIntrantHandler handler) {
		this.detailReceptionIntrantHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the Ravitaillement Handler
	 */
	public void setRavitaillementHandler(RavitaillementHandler handler) {
		this.ravitaillementHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the DetailRavitaillement Handler
	 */
	public void setDetailRavitaillementHandler(
			DetailRavitaillementHandler handler) {
		this.detailRavitaillementHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the Inventaire Handler
	 */
	public void setInventaireHandler(InventaireHandler handler) {
		this.inventaireHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the DetailInventaire Handler
	 */
	public void setDetailInventaireHandler(DetailInventaireHandler handler) {
		this.detailInventaireHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the Personnel Handler
	 */
	public void setPersonnelHandler(PersonnelHandler handler) {
		this.personnelHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the DepartPersonnel Handler
	 */
	public void setDepartPersonnelHandler(DepartPersonnelHandler handler) {
		this.departPersonnelHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the ArriveePersonnel Handler
	 */
	public void setArriveePersonnelHandler(ArriveePersonnelHandler handler) {
		this.arriveePersonnelHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the Region Handler
	 */
	public void setRegionHandler(RegionHandler handler) {
		this.regionHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the DistrictSante Handler
	 */
	public void setDistrictSanteHandler(DistrictSanteHandler handler) {
		this.districtSanteHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the CentreDiagTrait Handler
	 */
	public void setCentreDiagTraitHandler(CentreDiagTraitHandler handler) {
		this.centreDiagTraitHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the LaboratoireReference Handler
	 */
	public void setLaboratoireReferenceHandler(
			LaboratoireReferenceHandler handler) {
		this.laboratoireReferenceHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the LieuDit Handler
	 */
	public void setLieuDitHandler(LieuDitHandler handler) {
		this.lieuDitHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the Regime Handler
	 */
	public void setRegimeHandler(RegimeHandler handler) {
		this.regimeHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the PriseMedicamentRegime Handler
	 */
	public void setPriseMedicamentRegimeHandler(
			PriseMedicamentRegimeHandler handler) {
		this.priseMedicamentRegimeHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the Medicament Handler
	 */
	public void setMedicamentHandler(MedicamentHandler handler) {
		this.medicamentHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the Intrant Handler
	 */
	public void setIntrantHandler(IntrantHandler handler) {
		this.intrantHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the Formation Handler
	 */
	public void setFormationHandler(FormationHandler handler) {
		this.formationHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the CandidatureFormation Handler
	 */
	public void setCandidatureFormationHandler(
			CandidatureFormationHandler handler) {
		this.candidatureFormationHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the Qualification Handler
	 */
	public void setQualificationHandler(QualificationHandler handler) {
		this.qualificationHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the Tutoriel Handler
	 */
	public void setTutorielHandler(TutorielHandler handler) {
		this.tutorielHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the SmsPredefini Handler
	 */
	public void setSmsPredefiniHandler(SmsPredefiniHandler handler) {
		this.smsPredefiniHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the OutBox Handler
	 */
	public void setOutBoxHandler(OutBoxHandler handler) {
		this.outBoxHandler = handler;
	}
	/**
	 * Setter for bean injection
	 * @param handler the Utilisateur Handler
	 */
	public void setUtilisateurHandler(UtilisateurHandler handler) {
		this.utilisateurHandler = handler;
	}
}
