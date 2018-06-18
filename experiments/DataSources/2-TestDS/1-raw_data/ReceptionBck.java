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
 * ImogBean implementation for the entity Reception Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "Reception_Bck")
public class ReceptionBck extends ImogBeanBck {

	private static final long serialVersionUID = -8714437426609124134L;

	/* Description group fields */

	private String region;

	private String districtSante;

	private String CDT;

	private String commande;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateReception;

	private String medicaments;

	private String intrants;

	/**
	 * Constructor
	 */
	public ReceptionBck() {
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

	public String getCommande() {
		return commande;
	}

	public void setCommande(String value) {
		commande = value;
	}

	public Date getDateReception() {
		return dateReception;
	}

	public void setDateReception(Date value) {
		dateReception = value;
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

}
