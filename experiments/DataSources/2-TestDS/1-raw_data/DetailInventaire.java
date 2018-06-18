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
 * ImogBean implementation for the entity DetailInventaire
 * @author MEDES-IMPS
 */
@Entity
public class DetailInventaire extends ImogEntityImpl {

	public static interface Columns {
		public static final String INVENTAIRE = "inventaire";

		public static final String CDT = "cdt";

		public static final String LOT = "lot";

		public static final String QUANTITEREELLE = "quantitereelle";

		public static final String QUANTITETHEORIQUE = "quantitetheorique";

	}

	private static final long serialVersionUID = 8903626675278973603L;

	/* Description group fields */

	@ManyToOne
	@JoinColumn(name = "detailsInventaire_id")
	private Inventaire inventaire;

	@ManyToOne
	private CentreDiagTrait CDT;

	@ManyToOne
	private Lot lot;

	private Integer quantiteReelle;

	private Integer quantiteTheorique;

	/**
	 * Constructor
	 */
	public DetailInventaire() {
	}

	/* Getters and Setters for Description group fields */

	public Inventaire getInventaire() {
		return inventaire;
	}

	public void setInventaire(Inventaire value) {
		inventaire = value;
	}

	public CentreDiagTrait getCDT() {
		return CDT;
	}

	public void setCDT(CentreDiagTrait value) {
		CDT = value;
	}

	public Lot getLot() {
		return lot;
	}

	public void setLot(Lot value) {
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
