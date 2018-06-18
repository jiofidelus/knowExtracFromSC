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
 * ImogBean implementation for the entity Inventaire
 * @author MEDES-IMPS
 */
@Entity
public class Inventaire extends ImogEntityImpl {

	public static interface Columns {
		public static final String DATE = "date";

		public static final String REGION = "region";

		public static final String DISTRICTSANTE = "districtsante";

		public static final String CDT = "cdt";

		public static final String DETAILS = "details";

	}

	private static final long serialVersionUID = -7674270591871678159L;

	/* InformationsDepart group fields */

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@ManyToOne
	private Region region;

	@ManyToOne
	private DistrictSante districtSante;

	@ManyToOne
	private CentreDiagTrait CDT;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = false, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "detailsInventaire_id")
	private List<DetailInventaire> details;

	/**
	 * Constructor
	 */
	public Inventaire() {
		details = new ArrayList<DetailInventaire>();
	}

	/* Getters and Setters for InformationsDepart group fields */

	public Date getDate() {
		return date;
	}

	public void setDate(Date value) {
		date = value;
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

	public List<DetailInventaire> getDetails() {
		return details;
	}

	public void setDetails(List<DetailInventaire> value) {
		details = value;
	}

	/**
	 * @param param the DetailInventaire to add to the details collection
	 */
	public void addTodetails(DetailInventaire param) {
		param.setInventaire(this);
		details.add(param);
	}

	/**
	 * @param param the DetailInventaire to remove from the details collection
	 */
	public void removeFromdetails(DetailInventaire param) {
		param.setInventaire(null);
		details.remove(param);
	}

}
