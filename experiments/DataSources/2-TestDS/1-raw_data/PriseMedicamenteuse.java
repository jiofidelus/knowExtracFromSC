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
 * ImogBean implementation for the entity PriseMedicamenteuse
 * @author MEDES-IMPS
 */
@Entity
public class PriseMedicamenteuse extends ImogEntityImpl {

	public static interface Columns {
		public static final String PHASEINTENSIVE = "phaseintensive";

		public static final String PHASECONTINUATION = "phasecontinuation";

		public static final String DATEEFFECTIVE = "dateeffective";

		public static final String PRISE = "prise";

		public static final int PRISE_PRISESUPERVISEE = 0;

		public static final int PRISE_AUTOMEDICATION = 1;

		public static final int PRISE_NONVENU = 2;

		public static final String COTRIMOXAZOLE = "cotrimoxazole";

	}

	private static final long serialVersionUID = 7711369810290956610L;

	/* Description group fields */

	@ManyToOne
	@JoinColumn(name = "priseMedicamenteusePhaseInitialeCasTuberculose_id")
	private CasTuberculose phaseIntensive;

	@ManyToOne
	@JoinColumn(name = "priseMedicamenteusePhaseContinuationCasTuberculose_id")
	private CasTuberculose phaseContinuation;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateEffective;

	private String prise;

	private Boolean cotrimoxazole;

	/**
	 * Constructor
	 */
	public PriseMedicamenteuse() {
	}

	/* Getters and Setters for Description group fields */

	public CasTuberculose getPhaseIntensive() {
		return phaseIntensive;
	}

	public void setPhaseIntensive(CasTuberculose value) {
		phaseIntensive = value;
	}

	public CasTuberculose getPhaseContinuation() {
		return phaseContinuation;
	}

	public void setPhaseContinuation(CasTuberculose value) {
		phaseContinuation = value;
	}

	public Date getDateEffective() {
		return dateEffective;
	}

	public void setDateEffective(Date value) {
		dateEffective = value;
	}

	public String getPrise() {
		return prise;
	}

	public void setPrise(String value) {
		prise = value;
	}

	public Boolean getCotrimoxazole() {
		return cotrimoxazole;
	}

	public void setCotrimoxazole(Boolean value) {
		cotrimoxazole = value;
	}

}
