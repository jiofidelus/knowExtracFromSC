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
 * Manage persistence for Regime
 */
public class RegimeDaoImpl extends ImogBeanDaoImpl<Regime> implements RegimeDao {

	protected RegimeDaoImpl() {
		super(Regime.class);
	}

	@Override
	public void delete() {
		//TODO
	}

	/* relation dependencies */

	/**
	 * List associated PriseMedicamentRegime, 
	 * on the field prisesMedicamenteuses
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	@Override
	public List<PriseMedicamentRegime> loadPrisesMedicamenteuses(Regime parent) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<PriseMedicamentRegime> query = builder
				.createQuery(PriseMedicamentRegime.class);
		Root<PriseMedicamentRegime> root = query
				.from(PriseMedicamentRegime.class);
		query.select(root);
		query.where(builder.equal(root.<Regime> get("regime"), parent));
		return em.createQuery(query).getResultList();
	}

}
