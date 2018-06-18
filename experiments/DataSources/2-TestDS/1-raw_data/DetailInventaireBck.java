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
 * ImogBean implementation for the entity DetailInventaire Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "DetailInventaire_Bck")
public class DetailInventaireBck extends ImogBeanBck {

	private static final long serialVersionUID = -9096461658356789799L;

	/* Description group fields */

	private String inventaire;

	private String CDT;

	private String lot;

	private Integer quantiteReelle;

	private Integer quantiteTheorique;

	/**
	 * Constructor
	 */
	public DetailInventaireBck() {
	}

	/* Getters and Setters for Description group fields */

	public String getInventaire() {
		return inventaire;
	}

	public void setInventaire(String value) {
		inventaire = value;
	}

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

	public Integer getQuantiteReelle() {
		return quantiteReelle;
	}

	public void setQuantiteReelle(Integer value) {
		quantiteReelle = value;
	}

	public Integer getQuantiteTheorique() {
		return quantiteTheorique;
	}

	public void setQuantiteTheorique(Integer value) {
		quantiteTheorique = value;
	}

}
