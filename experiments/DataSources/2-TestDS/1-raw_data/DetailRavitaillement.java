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
 * ImogBean implementation for the entity DetailRavitaillement
 * @author MEDES-IMPS
 */
@Entity
public class DetailRavitaillement extends ImogEntityImpl {

	public static interface Columns {
		public static final String RAVITAILLEMENT = "ravitaillement";

		public static final String SORTIELOT = "sortielot";

		public static final String ENTREELOT = "entreelot";

	}

	private static final long serialVersionUID = -4114335365392892484L;

	/* Description group fields */

	@ManyToOne
	@JoinColumn(name = "detailsRavitaillement_id")
	private Ravitaillement ravitaillement;

	/* Sortie group fields */

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private SortieLot sortieLot;

	/* Entree group fields */

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private EntreeLot entreeLot;

	/**
	 * Constructor
	 */
	public DetailRavitaillement() {
	}

	/* Getters and Setters for Description group fields */

	public Ravitaillement getRavitaillement() {
		return ravitaillement;
	}

	public void setRavitaillement(Ravitaillement value) {
		ravitaillement = value;
	}

	/* Getters and Setters for Sortie group fields */

	public SortieLot getSortieLot() {
		return sortieLot;
	}

	public void setSortieLot(SortieLot value) {
		sortieLot = value;
	}

	/* Getters and Setters for Entree group fields */

	public EntreeLot getEntreeLot() {
		return entreeLot;
	}

	public void setEntreeLot(EntreeLot value) {
		entreeLot = value;
	}

}
