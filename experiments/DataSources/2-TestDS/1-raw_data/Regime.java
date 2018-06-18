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
 * ImogBean implementation for the entity Regime
 * @author MEDES-IMPS
 */
@Entity
public class Regime extends ImogEntityImpl {

	public static interface Columns {
		public static final String NOM = "nom";

		public static final String TYPE = "type";

		public static final int TYPE_PHASEINITIALE = 0;

		public static final int TYPE_PHASECONTINUATION = 1;

		public static final int TYPE_INDEPENDANT = 2;

		public static final String DUREETRAITEMENT = "dureetraitement";

		public static final String POIDSMIN = "poidsmin";

		public static final String POIDSMAX = "poidsmax";

		public static final String DESCRIPTION = "description";

		public static final String PRISESMEDICAMENTEUSES = "prisesmedicamenteuses";

		public static final String ACTIF = "actif";

	}

	private static final long serialVersionUID = 1680309346587577955L;

	/* Description group fields */

	private String nom;

	private String type;

	private Integer dureeTraitement;

	private Integer poidsMin;

	private Integer poidsMax;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "francais", column = @Column(name = "description_fr", columnDefinition = "TEXT")),
			@AttributeOverride(name = "english", column = @Column(name = "description_en", columnDefinition = "TEXT"))})
	private LocalizedText description;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = false, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "prisesMedicamenteusesRegime_id")
	private List<PriseMedicamentRegime> prisesMedicamenteuses;

	private Boolean actif;

	/**
	 * Constructor
	 */
	public Regime() {
		prisesMedicamenteuses = new ArrayList<PriseMedicamentRegime>();
	}

	/* Getters and Setters for Description group fields */

	public String getNom() {
		return nom;
	}

	public void setNom(String value) {
		nom = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String value) {
		type = value;
	}

	public Integer getDureeTraitement() {
		return dureeTraitement;
	}

	public void setDureeTraitement(Integer value) {
		dureeTraitement = value;
	}

	public Integer getPoidsMin() {
		return poidsMin;
	}

	public void setPoidsMin(Integer value) {
		poidsMin = value;
	}

	public Integer getPoidsMax() {
		return poidsMax;
	}

	public void setPoidsMax(Integer value) {
		poidsMax = value;
	}

	public LocalizedText getDescription() {
		return description;
	}

	public void setDescription(LocalizedText value) {
		description = value;
	}

	public List<PriseMedicamentRegime> getPrisesMedicamenteuses() {
		return prisesMedicamenteuses;
	}

	public void setPrisesMedicamenteuses(List<PriseMedicamentRegime> value) {
		prisesMedicamenteuses = value;
	}

	/**
	 * @param param the PriseMedicamentRegime to add to the prisesMedicamenteuses collection
	 */
	public void addToprisesMedicamenteuses(PriseMedicamentRegime param) {
		param.setRegime(this);
		prisesMedicamenteuses.add(param);
	}

	/**
	 * @param param the PriseMedicamentRegime to remove from the prisesMedicamenteuses collection
	 */
	public void removeFromprisesMedicamenteuses(PriseMedicamentRegime param) {
		param.setRegime(null);
		prisesMedicamenteuses.remove(param);
	}

	public Boolean getActif() {
		return actif;
	}

	public void setActif(Boolean value) {
		actif = value;
	}

}
