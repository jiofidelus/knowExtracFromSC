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
 * ImogBean implementation for the entity Medicament
 * @author MEDES-IMPS
 */
@Entity
public class Medicament extends ImogEntityImpl {

	public static interface Columns {
		public static final String CODE = "code";

		public static final String DESIGNATION = "designation";

		public static final String SEUILPATIENT = "seuilpatient";

		public static final String ESTMEDICAMENTANTITUBERCULEUX = "estmedicamentantituberculeux";

	}

	private static final long serialVersionUID = 8948656573374294147L;

	/* Description group fields */

	private String code;

	private String designation;

	private Double seuilPatient;

	private Boolean estMedicamentAntituberculeux;

	/**
	 * Constructor
	 */
	public Medicament() {
	}

	/* Getters and Setters for Description group fields */

	public String getCode() {
		return code;
	}

	public void setCode(String value) {
		code = value;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String value) {
		designation = value;
	}

	public Double getSeuilPatient() {
		return seuilPatient;
	}

	public void setSeuilPatient(Double value) {
		seuilPatient = value;
	}

	public Boolean getEstMedicamentAntituberculeux() {
		return estMedicamentAntituberculeux;
	}

	public void setEstMedicamentAntituberculeux(Boolean value) {
		estMedicamentAntituberculeux = value;
	}

}
