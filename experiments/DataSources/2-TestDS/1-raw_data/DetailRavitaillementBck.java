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
 * ImogBean implementation for the entity DetailRavitaillement Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "DetailRavitaillement_Bck")
public class DetailRavitaillementBck extends ImogBeanBck {

	private static final long serialVersionUID = 9026942301399398194L;

	/* Description group fields */

	private String ravitaillement;

	/* Sortie group fields */

	private String sortieLot;

	/* Entree group fields */

	private String entreeLot;

	/**
	 * Constructor
	 */
	public DetailRavitaillementBck() {
	}

	/* Getters and Setters for Description group fields */

	public String getRavitaillement() {
		return ravitaillement;
	}

	public void setRavitaillement(String value) {
		ravitaillement = value;
	}

	/* Getters and Setters for Sortie group fields */

	public String getSortieLot() {
		return sortieLot;
	}

	public void setSortieLot(String value) {
		sortieLot = value;
	}

	/* Getters and Setters for Entree group fields */

	public String getEntreeLot() {
		return entreeLot;
	}

	public void setEntreeLot(String value) {
		entreeLot = value;
	}

}
