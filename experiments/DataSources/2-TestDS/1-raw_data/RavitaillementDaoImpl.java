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
 * Manage persistence for Ravitaillement
 */
public class RavitaillementDaoImpl extends ImogBeanDaoImpl<Ravitaillement>
		implements
			RavitaillementDao {

	protected RavitaillementDaoImpl() {
		super(Ravitaillement.class);
	}

	@Override
	public void delete() {
		//TODO
	}

	/* relation dependencies */

	/**
	 * List associated DetailRavitaillement, 
	 * on the field details
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	@Override
	public List<DetailRavitaillement> loadDetails(Ravitaillement parent) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<DetailRavitaillement> query = builder
				.createQuery(DetailRavitaillement.class);
		Root<DetailRavitaillement> root = query
				.from(DetailRavitaillement.class);
		query.select(root);
		query.where(builder.equal(root.<Ravitaillement> get("ravitaillement"),
				parent));
		return em.createQuery(query).getResultList();
	}

	@Override
	protected Predicate getFilter(Root<Ravitaillement> root,
			CriteriaBuilder builder) {
		ImogActor actor = ImogActorUtils.getCurrentActor();
		if (actor == null) {
			actor = HttpSessionUtil.getCurrentUser();
		}
		if (actor instanceof Personnel) {
			Personnel personnel = (Personnel) actor;
			String niveau = personnel.getNiveau();
			if ("1".equals(niveau)) {
				return builder.or(builder.equal(DaoUtil.getCascadeRoot(root,
						"region.id"), personnel.getRegion().getId()),
						builder.equal(DaoUtil.getCascadeRoot(root,
								"regionArrivee.id"), personnel.getRegion()
								.getId()));
			} else if ("2".equals(niveau)) {
				return builder.or(builder.equal(
						DaoUtil.getCascadeRoot(root, "districtSante.id"),
						personnel.getDistrictSante().getId()),
						builder.equal(DaoUtil.getCascadeRoot(root,
								"districtSanteArrivee.id"), personnel
								.getDistrictSante().getId()));
			} else if ("3".equals(niveau)) {
				return builder.or(builder.equal(DaoUtil.getCascadeRoot(root,
						"CDTDepart.id"), personnel.getCDT().getId()), builder
						.equal(DaoUtil.getCascadeRoot(root, "CDTArrivee.id"),
								personnel.getCDT().getId()));
			}
		}
		return null;
	}

}
