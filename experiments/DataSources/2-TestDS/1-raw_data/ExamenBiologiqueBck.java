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
 * ImogBean implementation for the entity ExamenBiologique Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "ExamenBiologique_Bck")
public class ExamenBiologiqueBck extends ImogBeanBck {

	private static final long serialVersionUID = -1203197741937726249L;

	/* Description group fields */

	private String patient;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private Double poids;

	@Column(columnDefinition = "TEXT")
	private String observations;

	/**
	 * Constructor
	 */
	public ExamenBiologiqueBck() {
	}

	/* Getters and Setters for Description group fields */

	public String getPatient() {
		return patient;
	}

	public void setPatient(String value) {
		patient = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date value) {
		date = value;
	}

	public Double getPoids() {
		return poids;
	}

	public void setPoids(Double value) {
		poids = value;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String value) {
		observations = value;
	}

}
