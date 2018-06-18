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
 * ImogBean implementation for the entity DetailCommandeIntrant Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "DetailCommandeIntrant_Bck")
public class DetailCommandeIntrantBck extends ImogBeanBck {

	private static final long serialVersionUID = -3754318018410327128L;

	/* Description group fields */

	private String commande;

	private String intrant;

	private Integer quantiteRequise;

	private Integer quantiteEnStock;

	/**
	 * Constructor
	 */
	public DetailCommandeIntrantBck() {
	}

	/* Getters and Setters for Description group fields */

	public String getCommande() {
		return commande;
	}

	public void setCommande(String value) {
		commande = value;
	}

	public String getIntrant() {
		return intrant;
	}

	public void setIntrant(String value) {
		intrant = value;
	}

	public Integer getQuantiteRequise() {
		return quantiteRequise;
	}

	public void setQuantiteRequise(Integer value) {
		quantiteRequise = value;
	}

	public Integer getQuantiteEnStock() {
		return quantiteEnStock;
	}

	public void setQuantiteEnStock(Integer value) {
		quantiteEnStock = value;
	}

}
