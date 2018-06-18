package org.imogene.epicam.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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

import org.hibernate.annotations.Formula;
import org.imogene.lib.common.binary.file.BinaryFile;
import org.imogene.lib.common.constants.NotificationConstants;
import org.imogene.lib.common.entity.GeoField;
import org.imogene.lib.common.entity.ImogActorImpl;
import org.imogene.lib.common.entity.ImogEntityImpl;
import org.imogene.lib.common.entity.IsGeoreferenced;

/**
 * ImogBean implementation for the entity CasIndex
 * @author MEDES-IMPS
 */
@Entity
public class CasIndex extends ImogEntityImpl {

	public static interface Columns {
		public static final String PATIENT = "patient";

		public static final String PATIENTLIE = "patientlie";

		public static final String TYPERELATION = "typerelation";

	}

	private static final long serialVersionUID = -8738610350173852599L;

	/* Description group fields */

	@ManyToOne
	@JoinColumn(name = "casIndexPatient_id")
	private Patient patient;

	@ManyToOne
	private Patient patientLie;

	private String typeRelation;

	/**
	 * Constructor
	 */
	public CasIndex() {
	}

	/* Getters and Setters for Description group fields */

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient value) {
		patient = value;
	}

	public Patient getPatientLie() {
		return patientLie;
	}

	public void setPatientLie(Patient value) {
		patientLie = value;
	}

	public String getTypeRelation() {
		return typeRelation;
	}

	public void setTypeRelation(String value) {
		typeRelation = value;
	}

}
