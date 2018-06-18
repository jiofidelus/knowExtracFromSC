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

import org.imogene.epicam.domain.entity.DetailReceptionMedicament;
import org.imogene.epicam.domain.entity.backup.DetailReceptionMedicamentBck;

/**
 * ImogBean implementation for the entity DetailReceptionMedicament
 * @author MEDES-IMPS
 */
public class DetailReceptionMedicamentCloner {

	public static DetailReceptionMedicamentBck cloneEntity(
			DetailReceptionMedicament entity) {
		DetailReceptionMedicamentBck bck = new DetailReceptionMedicamentBck();
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

		if (entity.getReception() != null) {
			bck.setReception(entity.getReception().getId());
		}

		if (entity.getCommande() != null) {
			bck.setCommande(entity.getCommande().getId());
		}

		if (entity.getDetailCommande() != null) {
			bck.setDetailCommande(entity.getDetailCommande().getId());
		}

		if (entity.getEntreeLot() != null) {
			bck.setEntreeLot(entity.getEntreeLot().getId());
		}
		return bck;
	}
}
