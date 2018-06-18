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
 * Manage persistence for DetailRavitaillement
 */
public class DetailRavitaillementDaoImpl
		extends
			ImogBeanDaoImpl<DetailRavitaillement>
		implements
			DetailRavitaillementDao {

	protected DetailRavitaillementDaoImpl() {
		super(DetailRavitaillement.class);
	}

	@Override
	public void delete() {
		//TODO
	}

	/* relation dependencies */

	@Override
	protected Predicate getFilter(Root<DetailRavitaillement> root,
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
						"ravitaillement.region.id"), personnel.getRegion()
						.getId()), builder.equal(DaoUtil.getCascadeRoot(root,
						"ravitaillement.regionArrivee.id"), personnel
						.getRegion().getId()));
			} else if ("2".equals(niveau)) {
				return builder.or(builder.equal(DaoUtil.getCascadeRoot(root,
						"ravitaillement.districtSante.id"), personnel
						.getDistrictSante().getId()), builder.equal(DaoUtil
						.getCascadeRoot(root,
								"ravitaillement.districtSanteArrivee.id"),
						personnel.getDistrictSante().getId()));
			} else if ("3".equals(niveau)) {
				return builder.or(builder.equal(DaoUtil.getCascadeRoot(root,
						"ravitaillement.CDTDepart.id"), personnel.getCDT()
						.getId()), builder.equal(DaoUtil.getCascadeRoot(root,
						"ravitaillement.CDTArrivee.id"), personnel.getCDT()
						.getId()));
			}
		}
		return null;
	}

}
