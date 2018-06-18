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
 * Manage persistence for Formation
 */
public class FormationDaoImpl extends ImogBeanDaoImpl<Formation>
		implements
			FormationDao {

	protected FormationDaoImpl() {
		super(Formation.class);
	}

	@Override
	public void delete() {
		//TODO
	}

	/* relation dependencies */

	/**
	 * List associated CandidatureFormation, 
	 * on the field candidatures
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	@Override
	public List<CandidatureFormation> loadCandidatures(Formation parent) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<CandidatureFormation> query = builder
				.createQuery(CandidatureFormation.class);
		Root<CandidatureFormation> root = query
				.from(CandidatureFormation.class);
		query.select(root);
		query.where(builder.equal(root.<Formation> get("formation"), parent));
		return em.createQuery(query).getResultList();
	}

}
