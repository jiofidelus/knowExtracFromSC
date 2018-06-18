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
 * Manage persistence for DetailCommandeIntrant
 */
public class DetailCommandeIntrantDaoImpl
		extends
			ImogBeanDaoImpl<DetailCommandeIntrant>
		implements
			DetailCommandeIntrantDao {

	protected DetailCommandeIntrantDaoImpl() {
		super(DetailCommandeIntrant.class);
	}

	@Override
	public void delete() {
		//TODO
	}

	/* relation dependencies */

	@Override
	protected Predicate getFilter(Root<DetailCommandeIntrant> root,
			CriteriaBuilder builder) {
		ImogActor actor = ImogActorUtils.getCurrentActor();
		if (actor == null) {
			actor = HttpSessionUtil.getCurrentUser();
		}
		if (actor instanceof Personnel) {
			Personnel personnel = (Personnel) actor;
			String niveau = personnel.getNiveau();
			if ("1".equals(niveau)) {
				return builder.equal(
						DaoUtil.getCascadeRoot(root, "commande.region.id"),
						personnel.getRegion().getId());
			} else if ("2".equals(niveau)) {
				return builder.equal(DaoUtil.getCascadeRoot(root,
						"commande.districtSante.id"), personnel
						.getDistrictSante().getId());
			} else if ("3".equals(niveau)) {
				return builder.equal(
						DaoUtil.getCascadeRoot(root, "commande.CDT.id"),
						personnel.getCDT().getId());
			}
		}
		return null;
	}

}
