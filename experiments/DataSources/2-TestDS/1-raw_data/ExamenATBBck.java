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
 * ImogBean implementation for the entity ExamenATB Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "ExamenATB_Bck")
public class ExamenATBBck extends ImogBeanBck {

	private static final long serialVersionUID = 2531214122384524454L;

	/* CentreExamen group fields */

	private String CDT;

	private String laboratoireReference;

	/* Examen group fields */

	private String casTb;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateExamen;

	private String raisonDepistage;

	private String resultat;

	@Column(columnDefinition = "TEXT")
	private String observations;

	/**
	 * Constructor
	 */
	public ExamenATBBck() {
	}

	/* Getters and Setters for CentreExamen group fields */

	public String getCDT() {
		return CDT;
	}

	public void setCDT(String value) {
		CDT = value;
	}

	public String getLaboratoireReference() {
		return laboratoireReference;
	}

	public void setLaboratoireReference(String value) {
		laboratoireReference = value;
	}

	/* Getters and Setters for Examen group fields */

	public String getCasTb() {
		return casTb;
	}

	public void setCasTb(String value) {
		casTb = value;
	}

	public Date getDateExamen() {
		return dateExamen;
	}

	public void setDateExamen(Date value) {
		dateExamen = value;
	}

	public String getRaisonDepistage() {
		return raisonDepistage;
	}

	public void setRaisonDepistage(String value) {
		raisonDepistage = value;
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String value) {
		resultat = value;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String value) {
		observations = value;
	}

}
