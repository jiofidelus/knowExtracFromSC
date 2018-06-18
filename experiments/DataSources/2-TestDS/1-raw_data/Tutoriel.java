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
 * ImogBean implementation for the entity Tutoriel
 * @author MEDES-IMPS
 */
@Entity
public class Tutoriel extends ImogEntityImpl {

	public static interface Columns {
		public static final String REFERENCE = "reference";

		public static final String NOM = "nom";

		public static final String DESCRIPTION = "description";

		public static final String TYPE = "type";

		public static final int TYPE_TEXTE = 0;

		public static final int TYPE_AUDIO = 1;

		public static final int TYPE_VIDEO = 2;

		public static final String AUDIOT = "audiot";

		public static final String VIDEOT = "videot";

		public static final String TEXTT = "textt";

	}

	private static final long serialVersionUID = 6565920441644390363L;

	/* Description group fields */

	private String reference;

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

	private String type;

	@OneToOne
	private BinaryFile audioT;

	@OneToOne
	private BinaryFile videoT;

	@OneToOne
	private BinaryFile textT;

	/**
	 * Constructor
	 */
	public Tutoriel() {
	}

	/* Getters and Setters for Description group fields */

	public String getReference() {
		return reference;
	}

	public void setReference(String value) {
		reference = value;
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

	public String getType() {
		return type;
	}

	public void setType(String value) {
		type = value;
	}

	public BinaryFile getAudioT() {
		return audioT;
	}

	public void setAudioT(BinaryFile value) {
		audioT = value;
	}

	public BinaryFile getVideoT() {
		return videoT;
	}

	public void setVideoT(BinaryFile value) {
		videoT = value;
	}

	public BinaryFile getTextT() {
		return textT;
	}

	public void setTextT(BinaryFile value) {
		textT = value;
	}

}
