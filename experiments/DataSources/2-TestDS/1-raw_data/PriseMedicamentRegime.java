package org.imogene.epicam.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.imogene.lib.common.binary.file.BinaryFile;
import org.imogene.lib.common.constants.NotificationConstants;
import org.imogene.lib.common.entity.GeoField;
import org.imogene.lib.common.entity.ImogActorImpl;
import org.imogene.lib.common.entity.ImogEntityImpl;
import org.imogene.lib.common.entity.IsGeoreferenced;

/**
 * ImogBean implementation for the entity PriseMedicamentRegime
 * @author MEDES-IMPS
 */
@Entity
public class PriseMedicamentRegime extends ImogEntityImpl {

	public static interface Columns {
		public static final String REGIME = "regime";

		public static final String MEDICAMENT = "medicament";

		public static final String QUANTITE = "quantite";

		public static final String QUANTITEUNITE = "quantiteunite";

	}

	private static final long serialVersionUID = -4167439923782055514L;

	/* Description group fields */

	@ManyToOne
	@JoinColumn(name = "prisesMedicamenteusesRegime_id")
	private Regime regime;

	@ManyToOne
	private Medicament medicament;

	private Double quantite;

	private String quantiteUnite;

	/**
	 * Constructor
	 */
	public PriseMedicamentRegime() {
	}

	/* Getters and Setters for Description group fields */

	public Regime getRegime() {
		return regime;
	}

	public void setRegime(Regime value) {
		regime = value;
	}

	public Medicament getMedicament() {
		return medicament;
	}

	public void setMedicament(Medicament value) {
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
