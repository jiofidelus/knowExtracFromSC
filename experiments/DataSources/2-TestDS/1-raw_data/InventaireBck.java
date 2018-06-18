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
 * ImogBean implementation for the entity Inventaire Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "Inventaire_Bck")
public class InventaireBck extends ImogBeanBck {

	private static final long serialVersionUID = -5579392035084043754L;

	/* InformationsDepart group fields */

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String region;

	private String districtSante;

	private String CDT;

	private String details;

	/**
	 * Constructor
	 */
	public InventaireBck() {
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

	public String getDetails() {
		return details;
	}

	public void setDetails(String value) {
		details = value;
	}

}
