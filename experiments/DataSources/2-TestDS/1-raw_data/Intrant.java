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
 * ImogBean implementation for the entity Intrant
 * @author MEDES-IMPS
 */
@Entity
public class Intrant extends ImogEntityImpl {

	public static interface Columns {
		public static final String IDENTIFIANT = "identifiant";

		public static final String NOM = "nom";

		public static final String DESCRIPTION = "description";

		public static final String SEUILPATIENT = "seuilpatient";

	}

	private static final long serialVersionUID = -5862815233176500522L;

	/* Description group fields */

	private String identifiant;

	private String nom;

	@Column(columnDefinition = "TEXT")
	private String description;

	private Double seuilPatient;

	/**
	 * Constructor
	 */
	public Intrant() {
	}

	/* Getters and Setters for Description group fields */

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String value) {
		identifiant = value;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String value) {
		nom = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String value) {
		description = value;
	}

	public Double getSeuilPatient() {
		return seuilPatient;
	}

	public void setSeuilPatient(Double value) {
		seuilPatient = value;
	}

}
