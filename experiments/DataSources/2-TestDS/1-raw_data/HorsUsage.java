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
 * ImogBean implementation for the entity HorsUsage
 * @author MEDES-IMPS
 */
@Entity
public class HorsUsage extends ImogEntityImpl {

	public static interface Columns {
		public static final String LOT = "lot";

		public static final String TYPE = "type";

		public static final int TYPE_PERIMEE = 0;

		public static final int TYPE_CASSE = 1;

		public static final String MOTIF = "motif";

	}

	private static final long serialVersionUID = -3954666381192314807L;

	/* Description group fields */

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private SortieLot lot;

	private String type;

	@Column(columnDefinition = "TEXT")
	private String motif;

	/**
	 * Constructor
	 */
	public HorsUsage() {
	}

	/* Getters and Setters for Description group fields */

	public SortieLot getLot() {
		return lot;
	}

	public void setLot(SortieLot value) {
		lot = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String value) {
		type = value;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String value) {
		motif = value;
	}

}
