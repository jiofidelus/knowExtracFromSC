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
 * ImogBean implementation for the entity ArriveePersonnel Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "ArriveePersonnel_Bck")
public class ArriveePersonnelBck extends ImogBeanBck {

	private static final long serialVersionUID = 4799262815646153273L;

	/* Description group fields */

	private String region;

	private String districtSante;

	private String CDT;

	private String personnel;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateArrivee;

	/**
	 * Constructor
	 */
	public ArriveePersonnelBck() {
	}

	/* Getters and Setters for Description group fields */

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

	public Date getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(Date value) {
		dateArrivee = value;
	}

}
