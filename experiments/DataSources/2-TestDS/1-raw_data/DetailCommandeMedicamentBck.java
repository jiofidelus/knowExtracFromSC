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
 * ImogBean implementation for the entity DetailCommandeMedicament Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "DetailCommandeMedicament_Bck")
public class DetailCommandeMedicamentBck extends ImogBeanBck {

	private static final long serialVersionUID = 2768506557041344782L;

	/* Description group fields */

	private String commande;

	private String medicament;

	private Integer quantiteRequise;

	private Integer quantiteEnStock;

	/**
	 * Constructor
	 */
	public DetailCommandeMedicamentBck() {
	}

	/* Getters and Setters for Description group fields */

	public String getCommande() {
		return commande;
	}

	public void setCommande(String value) {
		commande = value;
	}

	public String getMedicament() {
		return medicament;
	}

	public void setMedicament(String value) {
		medicament = value;
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
