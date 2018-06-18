package org.imogene.epicam.domain.dao;

import java.util.List;
import java.util.Vector;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.imogene.lib.common.criteria.DaoUtil;
import org.imogene.lib.common.criteria.ImogJunction;
import org.imogene.lib.common.dao.ImogBeanDaoImpl;
import org.imogene.lib.common.entity.ImogActor;
import org.imogene.epicam.server.ImogActorUtils;
import org.imogene.web.server.util.HttpSessionUtil;
import org.imogene.epicam.domain.entity.*;
/**
 * Manage persistence for LaboratoireReference
 */
public class LaboratoireReferenceDaoImpl
		extends
			ImogBeanDaoImpl<LaboratoireReference>
		implements
			LaboratoireReferenceDao {

	protected LaboratoireReferenceDaoImpl() {
		super(LaboratoireReference.class);
	}

	@Override
	public void delete() {
		//TODO
	}

	/* relation dependencies */

	/**
	 * List associated LieuDit, 
	 * on the field lieuxDits
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	@Override
	public List<LieuDit> loadLieuxDits(LaboratoireReference parent) {
		if (parent == null) {
			return new Vector<LieuDit>();
		}
		return parent.getLieuxDits();
	}

}
