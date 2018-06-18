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
 * ImogBean implementation for the entity ExamenMicroscopie
 * @author MEDES-IMPS
 */
@Entity
public class ExamenMicroscopie extends ImogEntityImpl {

	public static interface Columns {
		public static final String CDT = "cdt";

		public static final String LABORATOIREREFERENCE = "laboratoirereference";

		public static final String CASTB = "castb";

		public static final String DATE = "date";

		public static final String RAISONDEPISTAGE = "raisondepistage";

		public static final int RAISONDEPISTAGE_DIAGNOSTIC = 0;

		public static final int RAISONDEPISTAGE_SUIVI = 1;

		public static final String RESULTAT = "resultat";

		public static final int RESULTAT_NEGATIF = 0;

		public static final int RESULTAT_RARE = 1;

		public static final int RESULTAT_UNPLUS = 2;

		public static final int RESULTAT_DEUXPLUS = 3;

		public static final int RESULTAT_TROISPLUS = 4;

		public static final String OBSERVATIONS = "observations";

	}

	private static final long serialVersionUID = 2517373820782116206L;

	/* CentreExamen group fields */

	@ManyToOne
	private CentreDiagTrait CDT;

	@ManyToOne
	private LaboratoireReference laboratoireReference;

	/* Examen group fields */

	@ManyToOne
	@JoinColumn(name = "examensMiscrocopiesCasTuberculose_id")
	private CasTuberculose casTb;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String raisonDepistage;

	private String resultat;

	@Column(columnDefinition = "TEXT")
	private String observations;

	/**
	 * Constructor
	 */
	public ExamenMicroscopie() {
	}

	/* Getters and Setters for CentreExamen group fields */

	public CentreDiagTrait getCDT() {
		return CDT;
	}

	public void setCDT(CentreDiagTrait value) {
		CDT = value;
	}

	public LaboratoireReference getLaboratoireReference() {
		return laboratoireReference;
	}

	public void setLaboratoireReference(LaboratoireReference value) {
		laboratoireReference = value;
	}

	/* Getters and Setters for Examen group fields */

	public CasTuberculose getCasTb() {
		return casTb;
	}

	public void setCasTb(CasTuberculose value) {
		casTb = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date value) {
		date = value;
	}

	public String getRaisonDepistage() {
		return raisonDepistage;
	}

	public void setRaisonDepistage(String value) {
		raisonDepistage = value;
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String value) {
		resultat = value;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String value) {
		observations = value;
	}

}
