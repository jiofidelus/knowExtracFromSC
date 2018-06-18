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
 * ImogBean implementation for the entity Ravitaillement Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "Ravitaillement_Bck")
public class RavitaillementBck extends ImogBeanBck {

	private static final long serialVersionUID = 4366182799185473357L;

	/* InformationsDepart group fields */

	private String region;

	private String districtSante;

	private String CDTDepart;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDepart;

	/* InformationArrivee group fields */

	private String regionArrivee;

	private String districtSanteArrivee;

	private String CDTArrivee;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateArrivee;

	/* Detail group fields */

	private String details;

	/**
	 * Constructor
	 */
	public RavitaillementBck() {
	}

	/* Getters and Setters for InformationsDepart group fields */

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

	public String getCDTDepart() {
		return CDTDepart;
	}

	public void setCDTDepart(String value) {
		CDTDepart = value;
	}

	public Date getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(Date value) {
		dateDepart = value;
	}

	/* Getters and Setters for InformationArrivee group fields */

	public String getRegionArrivee() {
		return regionArrivee;
	}

	public void setRegionArrivee(String value) {
		regionArrivee = value;
	}

	public String getDistrictSanteArrivee() {
		return districtSanteArrivee;
	}

	public void setDistrictSanteArrivee(String value) {
		districtSanteArrivee = value;
	}

	public String getCDTArrivee() {
		return CDTArrivee;
	}

	public void setCDTArrivee(String value) {
		CDTArrivee = value;
	}

	public Date getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(Date value) {
		dateArrivee = value;
	}

	/* Getters and Setters for Detail group fields */

	public String getDetails() {
		return details;
	}

	public void setDetails(String value) {
		details = value;
	}

}
