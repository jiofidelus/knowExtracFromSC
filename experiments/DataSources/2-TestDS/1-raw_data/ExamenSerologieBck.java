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
 * ImogBean implementation for the entity ExamenSerologie Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "ExamenSerologie_Bck")
public class ExamenSerologieBck extends ImogBeanBck {

	private static final long serialVersionUID = -301648672072343428L;

	/* Description group fields */

	private String patient;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTest;

	private String nature;

	private String resultatVIH;

	private Integer resultatCD4;

	/**
	 * Constructor
	 */
	public ExamenSerologieBck() {
	}

	/* Getters and Setters for Description group fields */

	public String getPatient() {
		return patient;
	}

	public void setPatient(String value) {
		patient = value;
	}

	public Date getDateTest() {
		return dateTest;
	}

	public void setDateTest(Date value) {
		dateTest = value;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String value) {
		nature = value;
	}

	public String getResultatVIH() {
		return resultatVIH;
	}

	public void setResultatVIH(String value) {
		resultatVIH = value;
	}

	public Integer getResultatCD4() {
		return resultatCD4;
	}

	public void setResultatCD4(Integer value) {
		resultatCD4 = value;
	}

}
