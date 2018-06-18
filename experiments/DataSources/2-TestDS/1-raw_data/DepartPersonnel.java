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
 * ImogBean implementation for the entity DepartPersonnel
 * @author MEDES-IMPS
 */
@Entity
public class DepartPersonnel extends ImogEntityImpl {

	public static interface Columns {
		public static final String REGION = "region";

		public static final String DISTRICTSANTE = "districtsante";

		public static final String CDT = "cdt";

		public static final String PERSONNEL = "personnel";

		public static final String DATEDEPART = "datedepart";

		public static final String MOTIFDEPART = "motifdepart";

	}

	private static final long serialVersionUID = 2225146226433633386L;

	/* Personnel group fields */

	@ManyToOne
	private Region region;

	@ManyToOne
	private DistrictSante districtSante;

	@ManyToOne
	private CentreDiagTrait CDT;

	@ManyToOne
	private Personnel personnel;

	/* Description group fields */

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDepart;

	@Column(columnDefinition = "TEXT")
	private String motifDepart;

	/**
	 * Constructor
	 */
	public DepartPersonnel() {
	}

	/* Getters and Setters for Personnel group fields */

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

	public CentreDiagTrait getCDT() {
		return CDT;
	}

	public void setCDT(CentreDiagTrait value) {
		CDT = value;
	}

	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel value) {
		personnel = value;
	}

	/* Getters and Setters for Description group fields */

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

}
