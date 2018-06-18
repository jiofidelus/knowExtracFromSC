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
 * ImogBean implementation for the entity Commande Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "Commande_Bck")
public class CommandeBck extends ImogBeanBck {

	private static final long serialVersionUID = -6334929678437196252L;

	/* InformationsDepart group fields */

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String region;

	private String districtSante;

	private String CDT;

	private String medicaments;

	private String intrants;

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
	public CommandeBck() {
	}

	/* Getters and Setters for InformationsDepart group fields */

	public Date getDate() {
		return date;
	}

	public void setDate(Date value) {
		date = value;
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

	public String getMedicaments() {
		return medicaments;
	}

	public void setMedicaments(String value) {
		medicaments = value;
	}

	public String getIntrants() {
		return intrants;
	}

	public void setIntrants(String value) {
		intrants = value;
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
