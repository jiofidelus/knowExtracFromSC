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
 * ImogBean implementation for the entity Lot Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "Lot_Bck")
public class LotBck extends ImogBeanBck {

	private static final long serialVersionUID = 6807354236181343188L;

	/* Description group fields */

	private String region;

	private String districtSante;

	private String CDT;

	private String numero;

	private String type;

	private String medicament;

	private String intrant;

	private Integer quantiteInitiale;

	private Integer quantite;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datePeremption;

	/**
	 * Constructor
	 */
	public LotBck() {
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String value) {
		numero = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String value) {
		type = value;
	}

	public String getMedicament() {
		return medicament;
	}

	public void setMedicament(String value) {
		medicament = value;
	}

	public String getIntrant() {
		return intrant;
	}

	public void setIntrant(String value) {
		intrant = value;
	}

	public Integer getQuantiteInitiale() {
		return quantiteInitiale;
	}

	public void setQuantiteInitiale(Integer value) {
		quantiteInitiale = value;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer value) {
		quantite = value;
	}

	public Date getDatePeremption() {
		return datePeremption;
	}

	public void setDatePeremption(Date value) {
		datePeremption = value;
	}

}
