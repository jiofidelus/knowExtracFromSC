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
 * ImogBean implementation for the entity DetailCommandeIntrant
 * @author MEDES-IMPS
 */
@Entity
public class DetailCommandeIntrant extends ImogEntityImpl {

	public static interface Columns {
		public static final String COMMANDE = "commande";

		public static final String INTRANT = "intrant";

		public static final String QUANTITEREQUISE = "quantiterequise";

		public static final String QUANTITEENSTOCK = "quantiteenstock";

	}

	private static final long serialVersionUID = -1955542381915903557L;

	/* Description group fields */

	@ManyToOne
	@JoinColumn(name = "intrantsCommande_id")
	private Commande commande;

	@ManyToOne
	private Intrant intrant;

	private Integer quantiteRequise;

	private Integer quantiteEnStock;

	/**
	 * Constructor
	 */
	public DetailCommandeIntrant() {
	}

	/* Getters and Setters for Description group fields */

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande value) {
		commande = value;
	}

	public Intrant getIntrant() {
		return intrant;
	}

	public void setIntrant(Intrant value) {
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
