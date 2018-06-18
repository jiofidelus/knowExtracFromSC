package org.imogene.epicam.domain.entity.backup;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.imogene.lib.common.entity.GeoField;
import org.imogene.lib.common.entity.ImogBeanImpl;
import org.imogene.lib.common.entity.ImogBeanBck;
import org.imogene.epicam.domain.entity.LocalizedText;

/**
 * ImogBean implementation for the entity CandidatureFormation Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "CandidatureFormation_Bck")
public class CandidatureFormationBck extends ImogBeanBck {

	private static final long serialVersionUID = 4620465159396830767L;

	/* Description group fields */

	private String formation;

	private String region;

	private String districtSante;

	private String CDT;

	private String personnel;

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
	public CandidatureFormationBck() {
	}

	/* Getters and Setters for Description group fields */

	public String getFormation() {
		return formation;
	}

	public void setFormation(String value) {
		formation = value;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String value) {
		region = value;
	}

	public String getDistrictSante() {
		return districtSante;
	}

	public void setDistrictSante(String value) {
		districtSante = value;
	}

	public String getCDT() {
		return CDT;
	}

	public void setCDT(String value) {
		CDT = value;
	}

	public String getPersonnel() {
		return personnel;
	}

	public void setPersonnel(String value) {
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
