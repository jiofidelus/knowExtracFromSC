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
 * ImogBean implementation for the entity Reception
 * @author MEDES-IMPS
 */
@Entity
public class Reception extends ImogEntityImpl {

	public static interface Columns {
		public static final String REGION = "region";

		public static final String DISTRICTSANTE = "districtsante";

		public static final String CDT = "cdt";

		public static final String COMMANDE = "commande";

		public static final String DATERECEPTION = "datereception";

		public static final String MEDICAMENTS = "medicaments";

		public static final String INTRANTS = "intrants";

	}

	private static final long serialVersionUID = 4284286411856988234L;

	/* Description group fields */

	@ManyToOne
	private Region region;

	@ManyToOne
	private DistrictSante districtSante;

	@ManyToOne
	private CentreDiagTrait CDT;

	@ManyToOne
	private Commande commande;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateReception;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = false, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "medicamentsReception_id")
	private List<DetailReceptionMedicament> medicaments;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = false, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "intrantsReception_id")
	private List<DetailReceptionIntrant> intrants;

	/**
	 * Constructor
	 */
	public Reception() {
		medicaments = new ArrayList<DetailReceptionMedicament>();
		intrants = new ArrayList<DetailReceptionIntrant>();
	}

	/* Getters and Setters for Description group fields */

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region value) {
		region = value;
	}

	public DistrictSante getDistrictSante() {
		return districtSante;
	}

	public void setDistrictSante(DistrictSante value) {
		districtSante = value;
	}

	public CentreDiagTrait getCDT() {
		return CDT;
	}

	public void setCDT(CentreDiagTrait value) {
		CDT = value;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande value) {
		commande = value;
	}

	public Date getDateReception() {
		return dateReception;
	}

	public void setDateReception(Date value) {
		dateReception = value;
	}

	public List<DetailReceptionMedicament> getMedicaments() {
		return medicaments;
	}

	public void setMedicaments(List<DetailReceptionMedicament> value) {
		medicaments = value;
	}

	/**
	 * @param param the DetailReceptionMedicament to add to the medicaments collection
	 */
	public void addTomedicaments(DetailReceptionMedicament param) {
		param.setReception(this);
		medicaments.add(param);
	}

	/**
	 * @param param the DetailReceptionMedicament to remove from the medicaments collection
	 */
	public void removeFrommedicaments(DetailReceptionMedicament param) {
		param.setReception(null);
		medicaments.remove(param);
	}

	public List<DetailReceptionIntrant> getIntrants() {
		return intrants;
	}

	public void setIntrants(List<DetailReceptionIntrant> value) {
		intrants = value;
	}

	/**
	 * @param param the DetailReceptionIntrant to add to the intrants collection
	 */
	public void addTointrants(DetailReceptionIntrant param) {
		param.setReception(this);
		intrants.add(param);
	}

	/**
	 * @param param the DetailReceptionIntrant to remove from the intrants collection
	 */
	public void removeFromintrants(DetailReceptionIntrant param) {
		param.setReception(null);
		intrants.remove(param);
	}

}
