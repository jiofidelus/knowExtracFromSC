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
 * ImogBean implementation for the entity DetailReceptionMedicament
 * @author MEDES-IMPS
 */
@Entity
public class DetailReceptionMedicament extends ImogEntityImpl {

	public static interface Columns {
		public static final String RECEPTION = "reception";

		public static final String COMMANDE = "commande";

		public static final String DETAILCOMMANDE = "detailcommande";

		public static final String ENTREELOT = "entreelot";

	}

	private static final long serialVersionUID = 1005481507238226320L;

	/* Description group fields */

	@ManyToOne
	@JoinColumn(name = "medicamentsReception_id")
	private Reception reception;

	@ManyToOne
	private Commande commande;

	@ManyToOne
	private DetailCommandeMedicament detailCommande;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private EntreeLot entreeLot;

	/**
	 * Constructor
	 */
	public DetailReceptionMedicament() {
	}

	/* Getters and Setters for Description group fields */

	public Reception getReception() {
		return reception;
	}

	public void setReception(Reception value) {
		reception = value;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande value) {
		commande = value;
	}

	public DetailCommandeMedicament getDetailCommande() {
		return detailCommande;
	}

	public void setDetailCommande(DetailCommandeMedicament value) {
		detailCommande = value;
	}

	public EntreeLot getEntreeLot() {
		return entreeLot;
	}

	public void setEntreeLot(EntreeLot value) {
		entreeLot = value;
	}

}
