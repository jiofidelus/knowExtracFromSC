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
 * ImogBean implementation for the entity PriseMedicamentRegime Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "PriseMedicamentRegime_Bck")
public class PriseMedicamentRegimeBck extends ImogBeanBck {

	private static final long serialVersionUID = 411415813378663559L;

	/* Description group fields */

	private String regime;

	private String medicament;

	private Double quantite;

	private String quantiteUnite;

	/**
	 * Constructor
	 */
	public PriseMedicamentRegimeBck() {
	}

	/* Getters and Setters for Description group fields */

	public String getRegime() {
		return regime;
	}

	public void setRegime(String value) {
		regime = value;
	}

	public String getMedicament() {
		return medicament;
	}

	public void setMedicament(String value) {
		medicament = value;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double value) {
		quantite = value;
	}

	public String getQuantiteUnite() {
		return quantiteUnite;
	}

	public void setQuantiteUnite(String value) {
		quantiteUnite = value;
	}

}
