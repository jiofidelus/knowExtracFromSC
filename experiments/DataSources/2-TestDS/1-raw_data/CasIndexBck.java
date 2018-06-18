package org.imogene.epicam.domain.entity.backup;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.imogene.lib.common.entity.GeoField;
import org.imogene.lib.common.entity.ImogBeanImpl;
import org.imogene.lib.common.entity.ImogBeanBck;
import org.imogene.epicam.domain.entity.LocalizedText;

/**
 * ImogBean implementation for the entity CasIndex Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "CasIndex_Bck")
public class CasIndexBck extends ImogBeanBck {

	private static final long serialVersionUID = -7265411935279541247L;

	/* Description group fields */

	private String patient;

	private String patientLie;

	private String typeRelation;

	/**
	 * Constructor
	 */
	public CasIndexBck() {
	}

	/* Getters and Setters for Description group fields */

	public String getPatient() {
		return patient;
	}

	public void setPatient(String value) {
		patient = value;
	}

	public String getPatientLie() {
		return patientLie;
	}

	public void setPatientLie(String value) {
		patientLie = value;
	}

	public String getTypeRelation() {
		return typeRelation;
	}

	public void setTypeRelation(String value) {
		typeRelation = value;
	}

}
