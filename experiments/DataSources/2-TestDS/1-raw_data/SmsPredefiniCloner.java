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

import org.imogene.epicam.domain.entity.SmsPredefini;
import org.imogene.epicam.domain.entity.backup.SmsPredefiniBck;

/**
 * ImogBean implementation for the entity SmsPredefini
 * @author MEDES-IMPS
 */
public class SmsPredefiniCloner {

	public static SmsPredefiniBck cloneEntity(SmsPredefini entity) {
		SmsPredefiniBck bck = new SmsPredefiniBck();
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

		bck.setType(entity.getType());

		bck.setObjet(entity.getObjet());

		bck.setPeriodicite(entity.getPeriodicite());

		bck.setDateEnvoyeSMSPonctuel(entity.getDateEnvoyeSMSPonctuel());

		bck.setStatut(entity.getStatut());

		bck.setMessage(entity.getMessage());

		bck.setReponse1(entity.getReponse1());

		bck.setReponse2(entity.getReponse2());

		bck.setBonneReponse(entity.getBonneReponse());

		bck.setEnvoyerAPartirDe(entity.getEnvoyerAPartirDe());

		bck.setArreterEnvoyerA(entity.getArreterEnvoyerA());
		return bck;
	}
}
