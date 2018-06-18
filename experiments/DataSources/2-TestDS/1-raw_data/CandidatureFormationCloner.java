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

import org.imogene.epicam.domain.entity.CandidatureFormation;
import org.imogene.epicam.domain.entity.backup.CandidatureFormationBck;

/**
 * ImogBean implementation for the entity CandidatureFormation
 * @author MEDES-IMPS
 */
public class CandidatureFormationCloner {

	public static CandidatureFormationBck cloneEntity(
			CandidatureFormation entity) {
		CandidatureFormationBck bck = new CandidatureFormationBck();
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

		if (entity.getFormation() != null) {
			bck.setFormation(entity.getFormation().getId());
		}

		if (entity.getRegion() != null) {
			bck.setRegion(entity.getRegion().getId());
		}

		if (entity.getDistrictSante() != null) {
			bck.setDistrictSante(entity.getDistrictSante().getId());
		}

		if (entity.getCDT() != null) {
			bck.setCDT(entity.getCDT().getId());
		}

		if (entity.getPersonnel() != null) {
			bck.setPersonnel(entity.getPersonnel().getId());
		}

		bck.setApprouveeRegion(entity.getApprouveeRegion());

		bck.setMotifRejetRegion(entity.getMotifRejetRegion());

		bck.setApprouveeGTC(entity.getApprouveeGTC());

		bck.setMotifRejetGTC(entity.getMotifRejetGTC());
		return bck;
	}
}
