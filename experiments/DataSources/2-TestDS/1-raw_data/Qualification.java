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
 * ImogBean implementation for the entity Qualification
 * @author MEDES-IMPS
 */
@Entity
public class Qualification extends ImogEntityImpl {

	public static interface Columns {
		public static final String CODE = "code";

		public static final String NOM = "nom";

		public static final String DESCRIPTION = "description";

	}

	private static final long serialVersionUID = -34047332927218505L;

	/* Description group fields */

	private String code;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "francais", column = @Column(name = "nom_fr")),
			@AttributeOverride(name = "english", column = @Column(name = "nom_en"))})
	private LocalizedText nom;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "francais", column = @Column(name = "description_fr", columnDefinition = "TEXT")),
			@AttributeOverride(name = "english", column = @Column(name = "description_en", columnDefinition = "TEXT"))})
	private LocalizedText description;

	/**
	 * Constructor
	 */
	public Qualification() {
	}

	/* Getters and Setters for Description group fields */

	public String getCode() {
		return code;
	}

	public void setCode(String value) {
		code = value;
	}

	public LocalizedText getNom() {
		return nom;
	}

	public void setNom(LocalizedText value) {
		nom = value;
	}

	public LocalizedText getDescription() {
		return description;
	}

	public void setDescription(LocalizedText value) {
		description = value;
	}

}
