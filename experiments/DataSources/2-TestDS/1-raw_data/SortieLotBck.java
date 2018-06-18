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
 * ImogBean implementation for the entity SortieLot Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "SortieLot_Bck")
public class SortieLotBck extends ImogBeanBck {

	private static final long serialVersionUID = -6536575857249376137L;

	/* Description group fields */

	private String CDT;

	private String lot;

	private Integer quantite;

	/**
	 * Constructor
	 */
	public SortieLotBck() {
	}

	/* Getters and Setters for Description group fields */

	public String getCDT() {
		return CDT;
	}

	public void setCDT(String value) {
		CDT = value;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String value) {
		lot = value;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer value) {
		quantite = value;
	}

}
