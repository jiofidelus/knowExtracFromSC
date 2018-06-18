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
 * ImogBean implementation for the entity CandidatureFormation
 * @author MEDES-IMPS
 */
@Entity
public class CandidatureFormation extends ImogEntityImpl {

	public static interface Columns {
		public static final String FORMATION = "formation";

		public static final String REGION = "region";

		public static final String DISTRICTSANTE = "districtsante";

		public static final String CDT = "cdt";

		public static final String PERSONNEL = "personnel";

		public static final String APPROUVEEREGION = "approuveeregion";

		public static final String MOTIFREJETREGION = "motifrejetregion";

		public static final String APPROUVEEGTC = "approuveegtc";

		public static final String MOTIFREJETGTC = "motifrejetgtc";

	}

	private static final long serialVersionUID = -9009096986318309410L;

	/* Description group fields */

	@ManyToOne
	private Formation formation;

	@ManyToOne
	private Region region;

	@ManyToOne
	private DistrictSante districtSante;

	@ManyToOne
	private CentreDiagTrait CDT;

	@ManyToOne
	private Personnel personnel;

	/* RegionApprobation group fields */

	private Boolean approuveeRegion;

	@Column(columnDefinition = "TEXT")
	private String motifRejetRegion;

	/* GtcApprobation group fields */

	private Boolean approuveeGTC;

	@Column(columnDefinition = "TEXT")
	private String motifRejetGTC;

	/**
	 * Constructor
	 */
	public CandidatureFormation() {
	}

	/* Getters and Setters for Description group fields */

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation value) {
		formation = value;
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

	/* Getters and Setters for RegionApprobation group fields */

	public Boolean getApprouveeRegion() {
		return approuveeRegion;
	}

	public void setApprouveeRegion(Boolean value) {
		approuveeRegion = value;
	}

	public String getMotifRejetRegion() {
		return motifRejetRegion;
	}

	public void setMotifRejetRegion(String value) {
		motifRejetRegion = value;
	}

	/* Getters and Setters for GtcApprobation group fields */

	public Boolean getApprouveeGTC() {
		return approuveeGTC;
	}

	public void setApprouveeGTC(Boolean value) {
		approuveeGTC = value;
	}

	public String getMotifRejetGTC() {
		return motifRejetGTC;
	}

	public void setMotifRejetGTC(String value) {
		motifRejetGTC = value;
	}

}
