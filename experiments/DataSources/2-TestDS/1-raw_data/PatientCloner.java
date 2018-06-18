package org.imogene.epicam.domain.entity.backup;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.UUID;

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
import org.imogene.lib.common.entity.GeoField;
import org.imogene.lib.common.entity.ImogActorImpl;
import org.imogene.lib.common.entity.ImogBeanImpl;
import org.imogene.lib.common.entity.IsGeoreferenced;

import org.imogene.epicam.domain.entity.Patient;
import org.imogene.epicam.domain.entity.backup.PatientBck;
import org.imogene.epicam.domain.entity.CentreDiagTrait;
import org.imogene.epicam.domain.entity.LieuDit;
import org.imogene.epicam.domain.entity.CasTuberculose;
import org.imogene.epicam.domain.entity.CasIndex;
import org.imogene.epicam.domain.entity.ExamenBiologique;
import org.imogene.epicam.domain.entity.ExamenSerologie;

/**
 * ImogBean implementation for the entity Patient
 * @author MEDES-IMPS
 */
public class PatientCloner {

	public static PatientBck cloneEntity(Patient entity) {
		PatientBck bck = new PatientBck();
		bck.setTraceId(UUID.randomUUID().toString());
		bck.setId(entity.getId());
		bck.setCreated(entity.getCreated());
		bck.setCreatedBy(entity.getCreatedBy());
		bck.setModified(entity.getModified());
		bck.setModifiedBy(entity.getModifiedBy());
		bck.setModifiedFrom(entity.getModifiedFrom());
		bck.setUploadDate(entity.getUploadDate());
		bck.setDeleted(entity.getDeleted());
		bck.setVersion(entity.getVersion());

		bck.setIdentifiant(entity.getIdentifiant());

		bck.setNom(entity.getNom());

		bck.setSexe(entity.getSexe());

		bck.setDateNaissance(entity.getDateNaissance());

		bck.setAge(entity.getAge());

		bck.setProfession(entity.getProfession());

		if (entity.getCentres() != null) {
			StringBuilder builder = new StringBuilder();
			for (CentreDiagTrait i : entity.getCentres()) {
				builder.append(i.getId()).append(";");
			}
			bck.setCentres(builder.toString());
		}

		bck.setNationalite(entity.getNationalite());

		bck.setPrecisionSurNationalite(entity.getPrecisionSurNationalite());

		bck.setRecevoirCarnetTelPortable(entity.getRecevoirCarnetTelPortable());

		bck.setTelephoneUn(entity.getTelephoneUn());

		bck.setTelephoneDeux(entity.getTelephoneDeux());

		bck.setTelephoneTrois(entity.getTelephoneTrois());

		bck.setEmail(entity.getEmail());

		bck.setLibelle(entity.getLibelle());

		bck.setComplementAdresse(entity.getComplementAdresse());

		bck.setQuartier(entity.getQuartier());

		bck.setVille(entity.getVille());

		bck.setCodePostal(entity.getCodePostal());

		if (entity.getLieuxDits() != null) {
			StringBuilder builder = new StringBuilder();
			for (LieuDit i : entity.getLieuxDits()) {
				builder.append(i.getId()).append(";");
			}
			bck.setLieuxDits(builder.toString());
		}

		bck.setPacNom(entity.getPacNom());

		bck.setPacTelephoneUn(entity.getPacTelephoneUn());

		bck.setPacTelephoneDeux(entity.getPacTelephoneDeux());

		bck.setPacTelephoneTrois(entity.getPacTelephoneTrois());

		bck.setPacEmail(entity.getPacEmail());

		bck.setPacLibelle(entity.getPacLibelle());

		bck.setPacComplementAdresse(entity.getPacComplementAdresse());

		bck.setPacQuartier(entity.getPacQuartier());

		bck.setPacVille(entity.getPacVille());

		bck.setPacCodePostal(entity.getPacCodePostal());

		if (entity.getCasTuberculose() != null) {
			StringBuilder builder = new StringBuilder();
			for (CasTuberculose i : entity.getCasTuberculose()) {
				builder.append(i.getId()).append(";");
			}
			bck.setCasTuberculose(builder.toString());
		}

		if (entity.getCasIndex() != null) {
			StringBuilder builder = new StringBuilder();
			for (CasIndex i : entity.getCasIndex()) {
				builder.append(i.getId()).append(";");
			}
			bck.setCasIndex(builder.toString());
		}

		if (entity.getExamensBiologiques() != null) {
			StringBuilder builder = new StringBuilder();
			for (ExamenBiologique i : entity.getExamensBiologiques()) {
				builder.append(i.getId()).append(";");
			}
			bck.setExamensBiologiques(builder.toString());
		}

		if (entity.getSerologies() != null) {
			StringBuilder builder = new StringBuilder();
			for (ExamenSerologie i : entity.getSerologies()) {
				builder.append(i.getId()).append(";");
			}
			bck.setSerologies(builder.toString());
		}
		return bck;
	}
}
