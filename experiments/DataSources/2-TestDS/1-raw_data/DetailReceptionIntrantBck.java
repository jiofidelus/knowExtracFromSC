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
 * ImogBean implementation for the entity DetailReceptionIntrant Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "DetailReceptionIntrant_Bck")
public class DetailReceptionIntrantBck extends ImogBeanBck {

	private static final long serialVersionUID = -8835922714697321096L;

	/* Description group fields */

	private String reception;

	private String commande;

	private String detailCommande;

	private String entreeLot;

	/**
	 * Constructor
	 */
	public DetailReceptionIntrantBck() {
	}

	/* Getters and Setters for Description group fields */

	public String getReception() {
		return reception;
	}

	public void setReception(String value) {
		reception = value;
	}

	public String getCommande() {
		return commande;
	}

	public void setCommande(String value) {
		commande = value;
	}

	public String getDetailCommande() {
		return detailCommande;
	}

	public void setDetailCommande(String value) {
		detailCommande = value;
	}

	public String getEntreeLot() {
		return entreeLot;
	}

	public void setEntreeLot(String value) {
		entreeLot = value;
	}

}
