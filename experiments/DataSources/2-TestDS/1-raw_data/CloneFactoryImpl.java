package org.imogene.epicam.domain.entity.backup;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.imogene.lib.common.binary.file.BinaryFile;
import org.imogene.lib.common.entity.CloneFactory;
import org.imogene.lib.common.entity.GeoField;
import org.imogene.lib.common.entity.ImogActorImpl;
import org.imogene.lib.common.entity.ImogBeanImpl;
import org.imogene.lib.common.entity.IsGeoreferenced;

import org.imogene.epicam.domain.entity.Patient;
import org.imogene.epicam.domain.entity.backup.PatientBck;
import org.imogene.epicam.domain.entity.CasIndex;
import org.imogene.epicam.domain.entity.backup.CasIndexBck;
import org.imogene.epicam.domain.entity.CasTuberculose;
import org.imogene.epicam.domain.entity.backup.CasTuberculoseBck;
import org.imogene.epicam.domain.entity.ExamenSerologie;
import org.imogene.epicam.domain.entity.backup.ExamenSerologieBck;
import org.imogene.epicam.domain.entity.ExamenBiologique;
import org.imogene.epicam.domain.entity.backup.ExamenBiologiqueBck;
import org.imogene.epicam.domain.entity.ExamenMicroscopie;
import org.imogene.epicam.domain.entity.backup.ExamenMicroscopieBck;
import org.imogene.epicam.domain.entity.ExamenATB;
import org.imogene.epicam.domain.entity.backup.ExamenATBBck;
import org.imogene.epicam.domain.entity.PriseMedicamenteuse;
import org.imogene.epicam.domain.entity.backup.PriseMedicamenteuseBck;
import org.imogene.epicam.domain.entity.RendezVous;
import org.imogene.epicam.domain.entity.backup.RendezVousBck;
import org.imogene.epicam.domain.entity.TransfertReference;
import org.imogene.epicam.domain.entity.backup.TransfertReferenceBck;
import org.imogene.epicam.domain.entity.Lot;
import org.imogene.epicam.domain.entity.backup.LotBck;
import org.imogene.epicam.domain.entity.HorsUsage;
import org.imogene.epicam.domain.entity.backup.HorsUsageBck;
import org.imogene.epicam.domain.entity.EntreeLot;
import org.imogene.epicam.domain.entity.backup.EntreeLotBck;
import org.imogene.epicam.domain.entity.SortieLot;
import org.imogene.epicam.domain.entity.backup.SortieLotBck;
import org.imogene.epicam.domain.entity.Commande;
import org.imogene.epicam.domain.entity.backup.CommandeBck;
import org.imogene.epicam.domain.entity.DetailCommandeMedicament;
import org.imogene.epicam.domain.entity.backup.DetailCommandeMedicamentBck;
import org.imogene.epicam.domain.entity.DetailCommandeIntrant;
import org.imogene.epicam.domain.entity.backup.DetailCommandeIntrantBck;
import org.imogene.epicam.domain.entity.Reception;
import org.imogene.epicam.domain.entity.backup.ReceptionBck;
import org.imogene.epicam.domain.entity.DetailReceptionMedicament;
import org.imogene.epicam.domain.entity.backup.DetailReceptionMedicamentBck;
import org.imogene.epicam.domain.entity.DetailReceptionIntrant;
import org.imogene.epicam.domain.entity.backup.DetailReceptionIntrantBck;
import org.imogene.epicam.domain.entity.Ravitaillement;
import org.imogene.epicam.domain.entity.backup.RavitaillementBck;
import org.imogene.epicam.domain.entity.DetailRavitaillement;
import org.imogene.epicam.domain.entity.backup.DetailRavitaillementBck;
import org.imogene.epicam.domain.entity.Inventaire;
import org.imogene.epicam.domain.entity.backup.InventaireBck;
import org.imogene.epicam.domain.entity.DetailInventaire;
import org.imogene.epicam.domain.entity.backup.DetailInventaireBck;
import org.imogene.epicam.domain.entity.Personnel;
import org.imogene.epicam.domain.entity.backup.PersonnelBck;
import org.imogene.epicam.domain.entity.DepartPersonnel;
import org.imogene.epicam.domain.entity.backup.DepartPersonnelBck;
import org.imogene.epicam.domain.entity.ArriveePersonnel;
import org.imogene.epicam.domain.entity.backup.ArriveePersonnelBck;
import org.imogene.epicam.domain.entity.Region;
import org.imogene.epicam.domain.entity.backup.RegionBck;
import org.imogene.epicam.domain.entity.DistrictSante;
import org.imogene.epicam.domain.entity.backup.DistrictSanteBck;
import org.imogene.epicam.domain.entity.CentreDiagTrait;
import org.imogene.epicam.domain.entity.backup.CentreDiagTraitBck;
import org.imogene.epicam.domain.entity.LaboratoireReference;
import org.imogene.epicam.domain.entity.backup.LaboratoireReferenceBck;
import org.imogene.epicam.domain.entity.LieuDit;
import org.imogene.epicam.domain.entity.backup.LieuDitBck;
import org.imogene.epicam.domain.entity.Regime;
import org.imogene.epicam.domain.entity.backup.RegimeBck;
import org.imogene.epicam.domain.entity.PriseMedicamentRegime;
import org.imogene.epicam.domain.entity.backup.PriseMedicamentRegimeBck;
import org.imogene.epicam.domain.entity.Medicament;
import org.imogene.epicam.domain.entity.backup.MedicamentBck;
import org.imogene.epicam.domain.entity.Intrant;
import org.imogene.epicam.domain.entity.backup.IntrantBck;
import org.imogene.epicam.domain.entity.Formation;
import org.imogene.epicam.domain.entity.backup.FormationBck;
import org.imogene.epicam.domain.entity.CandidatureFormation;
import org.imogene.epicam.domain.entity.backup.CandidatureFormationBck;
import org.imogene.epicam.domain.entity.Qualification;
import org.imogene.epicam.domain.entity.backup.QualificationBck;
import org.imogene.epicam.domain.entity.Tutoriel;
import org.imogene.epicam.domain.entity.backup.TutorielBck;
import org.imogene.epicam.domain.entity.SmsPredefini;
import org.imogene.epicam.domain.entity.backup.SmsPredefiniBck;
import org.imogene.epicam.domain.entity.OutBox;
import org.imogene.epicam.domain.entity.backup.OutBoxBck;
import org.imogene.epicam.domain.entity.Utilisateur;
import org.imogene.epicam.domain.entity.backup.UtilisateurBck;

public class CloneFactoryImpl implements CloneFactory {

	/* The unique instance of this object */
	private static CloneFactory instance = null;

	public static CloneFactory getInstance() {
		if (instance == null)
			instance = new CloneFactoryImpl();
		return instance;
	}

	public Object clone(Object source) {
		if (source instanceof Patient)
			return cloneEntity((Patient) source);
		if (source instanceof CasIndex)
			return cloneEntity((CasIndex) source);
		if (source instanceof CasTuberculose)
			return cloneEntity((CasTuberculose) source);
		if (source instanceof ExamenSerologie)
			return cloneEntity((ExamenSerologie) source);
		if (source instanceof ExamenBiologique)
			return cloneEntity((ExamenBiologique) source);
		if (source instanceof ExamenMicroscopie)
			return cloneEntity((ExamenMicroscopie) source);
		if (source instanceof ExamenATB)
			return cloneEntity((ExamenATB) source);
		if (source instanceof PriseMedicamenteuse)
			return cloneEntity((PriseMedicamenteuse) source);
		if (source instanceof RendezVous)
			return cloneEntity((RendezVous) source);
		if (source instanceof TransfertReference)
			return cloneEntity((TransfertReference) source);
		if (source instanceof Lot)
			return cloneEntity((Lot) source);
		if (source instanceof HorsUsage)
			return cloneEntity((HorsUsage) source);
		if (source instanceof EntreeLot)
			return cloneEntity((EntreeLot) source);
		if (source instanceof SortieLot)
			return cloneEntity((SortieLot) source);
		if (source instanceof Commande)
			return cloneEntity((Commande) source);
		if (source instanceof DetailCommandeMedicament)
			return cloneEntity((DetailCommandeMedicament) source);
		if (source instanceof DetailCommandeIntrant)
			return cloneEntity((DetailCommandeIntrant) source);
		if (source instanceof Reception)
			return cloneEntity((Reception) source);
		if (source instanceof DetailReceptionMedicament)
			return cloneEntity((DetailReceptionMedicament) source);
		if (source instanceof DetailReceptionIntrant)
			return cloneEntity((DetailReceptionIntrant) source);
		if (source instanceof Ravitaillement)
			return cloneEntity((Ravitaillement) source);
		if (source instanceof DetailRavitaillement)
			return cloneEntity((DetailRavitaillement) source);
		if (source instanceof Inventaire)
			return cloneEntity((Inventaire) source);
		if (source instanceof DetailInventaire)
			return cloneEntity((DetailInventaire) source);
		if (source instanceof Personnel)
			return cloneEntity((Personnel) source);
		if (source instanceof DepartPersonnel)
			return cloneEntity((DepartPersonnel) source);
		if (source instanceof ArriveePersonnel)
			return cloneEntity((ArriveePersonnel) source);
		if (source instanceof Region)
			return cloneEntity((Region) source);
		if (source instanceof DistrictSante)
			return cloneEntity((DistrictSante) source);
		if (source instanceof CentreDiagTrait)
			return cloneEntity((CentreDiagTrait) source);
		if (source instanceof LaboratoireReference)
			return cloneEntity((LaboratoireReference) source);
		if (source instanceof LieuDit)
			return cloneEntity((LieuDit) source);
		if (source instanceof Regime)
			return cloneEntity((Regime) source);
		if (source instanceof PriseMedicamentRegime)
			return cloneEntity((PriseMedicamentRegime) source);
		if (source instanceof Medicament)
			return cloneEntity((Medicament) source);
		if (source instanceof Intrant)
			return cloneEntity((Intrant) source);
		if (source instanceof Formation)
			return cloneEntity((Formation) source);
		if (source instanceof CandidatureFormation)
			return cloneEntity((CandidatureFormation) source);
		if (source instanceof Qualification)
			return cloneEntity((Qualification) source);
		if (source instanceof Tutoriel)
			return cloneEntity((Tutoriel) source);
		if (source instanceof SmsPredefini)
			return cloneEntity((SmsPredefini) source);
		if (source instanceof OutBox)
			return cloneEntity((OutBox) source);
		if (source instanceof Utilisateur)
			return cloneEntity((Utilisateur) source);

		return null;
	}

	public PatientBck cloneEntity(Patient entity) {
		return PatientCloner.cloneEntity(entity);
	}
	public CasIndexBck cloneEntity(CasIndex entity) {
		return CasIndexCloner.cloneEntity(entity);
	}
	public CasTuberculoseBck cloneEntity(CasTuberculose entity) {
		return CasTuberculoseCloner.cloneEntity(entity);
	}
	public ExamenSerologieBck cloneEntity(ExamenSerologie entity) {
		return ExamenSerologieCloner.cloneEntity(entity);
	}
	public ExamenBiologiqueBck cloneEntity(ExamenBiologique entity) {
		return ExamenBiologiqueCloner.cloneEntity(entity);
	}
	public ExamenMicroscopieBck cloneEntity(ExamenMicroscopie entity) {
		return ExamenMicroscopieCloner.cloneEntity(entity);
	}
	public ExamenATBBck cloneEntity(ExamenATB entity) {
		return ExamenATBCloner.cloneEntity(entity);
	}
	public PriseMedicamenteuseBck cloneEntity(PriseMedicamenteuse entity) {
		return PriseMedicamenteuseCloner.cloneEntity(entity);
	}
	public RendezVousBck cloneEntity(RendezVous entity) {
		return RendezVousCloner.cloneEntity(entity);
	}
	public TransfertReferenceBck cloneEntity(TransfertReference entity) {
		return TransfertReferenceCloner.cloneEntity(entity);
	}
	public LotBck cloneEntity(Lot entity) {
		return LotCloner.cloneEntity(entity);
	}
	public HorsUsageBck cloneEntity(HorsUsage entity) {
		return HorsUsageCloner.cloneEntity(entity);
	}
	public EntreeLotBck cloneEntity(EntreeLot entity) {
		return EntreeLotCloner.cloneEntity(entity);
	}
	public SortieLotBck cloneEntity(SortieLot entity) {
		return SortieLotCloner.cloneEntity(entity);
	}
	public CommandeBck cloneEntity(Commande entity) {
		return CommandeCloner.cloneEntity(entity);
	}
	public DetailCommandeMedicamentBck cloneEntity(
			DetailCommandeMedicament entity) {
		return DetailCommandeMedicamentCloner.cloneEntity(entity);
	}
	public DetailCommandeIntrantBck cloneEntity(DetailCommandeIntrant entity) {
		return DetailCommandeIntrantCloner.cloneEntity(entity);
	}
	public ReceptionBck cloneEntity(Reception entity) {
		return ReceptionCloner.cloneEntity(entity);
	}
	public DetailReceptionMedicamentBck cloneEntity(
			DetailReceptionMedicament entity) {
		return DetailReceptionMedicamentCloner.cloneEntity(entity);
	}
	public DetailReceptionIntrantBck cloneEntity(DetailReceptionIntrant entity) {
		return DetailReceptionIntrantCloner.cloneEntity(entity);
	}
	public RavitaillementBck cloneEntity(Ravitaillement entity) {
		return RavitaillementCloner.cloneEntity(entity);
	}
	public DetailRavitaillementBck cloneEntity(DetailRavitaillement entity) {
		return DetailRavitaillementCloner.cloneEntity(entity);
	}
	public InventaireBck cloneEntity(Inventaire entity) {
		return InventaireCloner.cloneEntity(entity);
	}
	public DetailInventaireBck cloneEntity(DetailInventaire entity) {
		return DetailInventaireCloner.cloneEntity(entity);
	}
	public PersonnelBck cloneEntity(Personnel entity) {
		return PersonnelCloner.cloneEntity(entity);
	}
	public DepartPersonnelBck cloneEntity(DepartPersonnel entity) {
		return DepartPersonnelCloner.cloneEntity(entity);
	}
	public ArriveePersonnelBck cloneEntity(ArriveePersonnel entity) {
		return ArriveePersonnelCloner.cloneEntity(entity);
	}
	public RegionBck cloneEntity(Region entity) {
		return RegionCloner.cloneEntity(entity);
	}
	public DistrictSanteBck cloneEntity(DistrictSante entity) {
		return DistrictSanteCloner.cloneEntity(entity);
	}
	public CentreDiagTraitBck cloneEntity(CentreDiagTrait entity) {
		return CentreDiagTraitCloner.cloneEntity(entity);
	}
	public LaboratoireReferenceBck cloneEntity(LaboratoireReference entity) {
		return LaboratoireReferenceCloner.cloneEntity(entity);
	}
	public LieuDitBck cloneEntity(LieuDit entity) {
		return LieuDitCloner.cloneEntity(entity);
	}
	public RegimeBck cloneEntity(Regime entity) {
		return RegimeCloner.cloneEntity(entity);
	}
	public PriseMedicamentRegimeBck cloneEntity(PriseMedicamentRegime entity) {
		return PriseMedicamentRegimeCloner.cloneEntity(entity);
	}
	public MedicamentBck cloneEntity(Medicament entity) {
		return MedicamentCloner.cloneEntity(entity);
	}
	public IntrantBck cloneEntity(Intrant entity) {
		return IntrantCloner.cloneEntity(entity);
	}
	public FormationBck cloneEntity(Formation entity) {
		return FormationCloner.cloneEntity(entity);
	}
	public CandidatureFormationBck cloneEntity(CandidatureFormation entity) {
		return CandidatureFormationCloner.cloneEntity(entity);
	}
	public QualificationBck cloneEntity(Qualification entity) {
		return QualificationCloner.cloneEntity(entity);
	}
	public TutorielBck cloneEntity(Tutoriel entity) {
		return TutorielCloner.cloneEntity(entity);
	}
	public SmsPredefiniBck cloneEntity(SmsPredefini entity) {
		return SmsPredefiniCloner.cloneEntity(entity);
	}
	public OutBoxBck cloneEntity(OutBox entity) {
		return OutBoxCloner.cloneEntity(entity);
	}
	public UtilisateurBck cloneEntity(Utilisateur entity) {
		return UtilisateurCloner.cloneEntity(entity);
	}
}
