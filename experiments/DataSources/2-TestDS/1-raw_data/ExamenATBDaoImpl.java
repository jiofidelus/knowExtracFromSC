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
 * Manage persistence for ExamenATB
 */
public class ExamenATBDaoImpl extends ImogBeanDaoImpl<ExamenATB>
		implements
			ExamenATBDao {

	protected ExamenATBDaoImpl() {
		super(ExamenATB.class);
	}

	@Override
	public void delete() {
		//TODO
	}

	/* relation dependencies */

	@Override
	protected Predicate getFilter(Root<ExamenATB> root, CriteriaBuilder builder) {
		ImogActor actor = ImogActorUtils.getCurrentActor();
		if (actor == null) {
			actor = HttpSessionUtil.getCurrentUser();
		}
		if (actor instanceof Personnel) {
			Personnel personnel = (Personnel) actor;
			String niveau = personnel.getNiveau();
			if ("1".equals(niveau)) {
				Path<?> join = DaoUtil.getCascadeRoot(
						root.join("casTb").join("patient")
								.join("centres", JoinType.LEFT), "region.id");
				return builder.equal(join, personnel.getRegion().getId());
			} else if ("2".equals(niveau)) {
				Path<?> join = DaoUtil.getCascadeRoot(
						root.join("casTb").join("patient")
								.join("centres", JoinType.LEFT),
						"districtSante.id");
				return builder
						.equal(join, personnel.getDistrictSante().getId());
			} else if ("3".equals(niveau)) {
				Path<?> join = root.join("casTb").join("patient")
						.join("centres", JoinType.LEFT).get("id");
				return builder.equal(join, personnel.getCDT().getId());
			}
		}
		return null;
	}

}
