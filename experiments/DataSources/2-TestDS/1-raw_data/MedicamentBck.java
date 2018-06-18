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
 * ImogBean implementation for the entity Medicament Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "Medicament_Bck")
public class MedicamentBck extends ImogBeanBck {

	private static final long serialVersionUID = -2277618560874315023L;

	/* Description group fields */

	private String code;

	private String designation;

	private Double seuilPatient;

	private Boolean estMedicamentAntituberculeux;

	/**
	 * Constructor
	 */
	public MedicamentBck() {
	}

	/* Getters and Setters for Description group fields */

	public String getCode() {
		return code;
	}

	public void setCode(String value) {
		code = value;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String value) {
		designation = value;
	}

	public Double getSeuilPatient() {
		return seuilPatient;
	}

	public void setSeuilPatient(Double value) {
		seuilPatient = value;
	}

	public Boolean getEstMedicamentAntituberculeux() {
		return estMedicamentAntituberculeux;
	}

	public void setEstMedicamentAntituberculeux(Boolean value) {
		estMedicamentAntituberculeux = value;
	}

}
