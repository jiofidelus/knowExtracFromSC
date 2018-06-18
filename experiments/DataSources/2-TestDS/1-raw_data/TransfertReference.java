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
 * ImogBean implementation for the entity TransfertReference
 * @author MEDES-IMPS
 */
@Entity
public class TransfertReference extends ImogEntityImpl {

	public static interface Columns {
		public static final String NATURE = "nature";

		public static final int NATURE_TRANSFERT = 0;

		public static final int NATURE_REFERENCE = 1;

		public static final String REGION = "region";

		public static final String DISTRICTSANTE = "districtsante";

		public static final String CDTDEPART = "cdtdepart";

		public static final String PATIENT = "patient";

		public static final String DATEDEPART = "datedepart";

		public static final String MOTIFDEPART = "motifdepart";

		public static final String REGIONARRIVEE = "regionarrivee";

		public static final String DISTRICTSANTEARRIVEE = "districtsantearrivee";

		public static final String CDTARRIVEE = "cdtarrivee";

		public static final String DATEARRIVEE = "datearrivee";

	}

	private static final long serialVersionUID = 1769599556660563728L;

	/* InformationsDepart group fields */

	private String nature;

	@ManyToOne
	private Region region;

	@ManyToOne
	private DistrictSante districtSante;

	@ManyToOne
	private CentreDiagTrait CDTDepart;

	@ManyToOne
	private Patient patient;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDepart;

	@Column(columnDefinition = "TEXT")
	private String motifDepart;

	/* InformationArrivee group fields */

	@ManyToOne
	private Region regionArrivee;

	@ManyToOne
	private DistrictSante districtSanteArrivee;

	@ManyToOne
	private CentreDiagTrait CDTArrivee;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateArrivee;

	/**
	 * Constructor
	 */
	public TransfertReference() {
	}

	/* Getters and Setters for InformationsDepart group fields */

	public String getNature() {
		return nature;
	}

	public void setNature(String value) {
		nature = value;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region value) {
		region = value;
	}

	public DistrictSante getDistrictSante() {
		return districtSante;
	}

	public void setDistrictSante(DistrictSante value) {
		districtSante = value;
	}

	public CentreDiagTrait getCDTDepart() {
		return CDTDepart;
	}

	public void setCDTDepart(CentreDiagTrait value) {
		CDTDepart = value;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient value) {
		patient = value;
	}

	public Date getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(Date value) {
		dateDepart = value;
	}

	public String getMotifDepart() {
		return motifDepart;
	}

	public void setMotifDepart(String value) {
		motifDepart = value;
	}

	/* Getters and Setters for InformationArrivee group fields */

	public Region getRegionArrivee() {
		return regionArrivee;
	}

	public void setRegionArrivee(Region value) {
		regionArrivee = value;
	}

	public DistrictSante getDistrictSanteArrivee() {
		return districtSanteArrivee;
	}

	public void setDistrictSanteArrivee(DistrictSante value) {
		districtSanteArrivee = value;
	}

	public CentreDiagTrait getCDTArrivee() {
		return CDTArrivee;
	}

	public void setCDTArrivee(CentreDiagTrait value) {
		CDTArrivee = value;
	}

	public Date getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(Date value) {
		dateArrivee = value;
	}

}
