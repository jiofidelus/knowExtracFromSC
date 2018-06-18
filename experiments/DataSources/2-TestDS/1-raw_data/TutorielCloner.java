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

import org.imogene.epicam.domain.entity.Tutoriel;
import org.imogene.epicam.domain.entity.backup.TutorielBck;

/**
 * ImogBean implementation for the entity Tutoriel
 * @author MEDES-IMPS
 */
public class TutorielCloner {

	public static TutorielBck cloneEntity(Tutoriel entity) {
		TutorielBck bck = new TutorielBck();
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

		bck.setReference(entity.getReference());

		bck.setNom(entity.getNom());

		bck.setDescription(entity.getDescription());

		bck.setType(entity.getType());

		if (entity.getAudioT() != null) {
			bck.setAudioT(entity.getAudioT().getId());
		}

		if (entity.getVideoT() != null) {
			bck.setVideoT(entity.getVideoT().getId());
		}

		if (entity.getTextT() != null) {
			bck.setTextT(entity.getTextT().getId());
		}
		return bck;
	}
}
