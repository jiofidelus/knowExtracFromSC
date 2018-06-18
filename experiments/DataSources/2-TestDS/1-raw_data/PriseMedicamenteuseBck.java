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
 * ImogBean implementation for the entity PriseMedicamenteuse Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "PriseMedicamenteuse_Bck")
public class PriseMedicamenteuseBck extends ImogBeanBck {

	private static final long serialVersionUID = 3097080460980797036L;

	/* Description group fields */

	private String phaseIntensive;

	private String phaseContinuation;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateEffective;

	private String prise;

	private Boolean cotrimoxazole;

	/**
	 * Constructor
	 */
	public PriseMedicamenteuseBck() {
	}

	/* Getters and Setters for Description group fields */

	public String getPhaseIntensive() {
		return phaseIntensive;
	}

	public void setPhaseIntensive(String value) {
		phaseIntensive = value;
	}

	public String getPhaseContinuation() {
		return phaseContinuation;
	}

	public void setPhaseContinuation(String value) {
		phaseContinuation = value;
	}

	public Date getDateEffective() {
		return dateEffective;
	}

	public void setDateEffective(Date value) {
		dateEffective = value;
	}

	public String getPrise() {
		return prise;
	}

	public void setPrise(String value) {
		prise = value;
	}

	public Boolean getCotrimoxazole() {
		return cotrimoxazole;
	}

	public void setCotrimoxazole(Boolean value) {
		cotrimoxazole = value;
	}

}
