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
 * Manage persistence for Commande
 */
public class CommandeDaoImpl extends ImogBeanDaoImpl<Commande>
		implements
			CommandeDao {

	protected CommandeDaoImpl() {
		super(Commande.class);
	}

	@Override
	public void delete() {
		//TODO
	}

	/* relation dependencies */

	/**
	 * List associated DetailCommandeMedicament, 
	 * on the field medicaments
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	@Override
	public List<DetailCommandeMedicament> loadMedicaments(Commande parent) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<DetailCommandeMedicament> query = builder
				.createQuery(DetailCommandeMedicament.class);
		Root<DetailCommandeMedicament> root = query
				.from(DetailCommandeMedicament.class);
		query.select(root);
		query.where(builder.equal(root.<Commande> get("commande"), parent));
		return em.createQuery(query).getResultList();
	}

	/**
	 * List associated DetailCommandeIntrant, 
	 * on the field intrants
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	@Override
	public List<DetailCommandeIntrant> loadIntrants(Commande parent) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<DetailCommandeIntrant> query = builder
				.createQuery(DetailCommandeIntrant.class);
		Root<DetailCommandeIntrant> root = query
				.from(DetailCommandeIntrant.class);
		query.select(root);
		query.where(builder.equal(root.<Commande> get("commande"), parent));
		return em.createQuery(query).getResultList();
	}

	@Override
	protected Predicate getFilter(Root<Commande> root, CriteriaBuilder builder) {
		ImogActor actor = ImogActorUtils.getCurrentActor();
		if (actor == null) {
			actor = HttpSessionUtil.getCurrentUser();
		}
		if (actor instanceof Personnel) {
			Personnel personnel = (Personnel) actor;
			String niveau = personnel.getNiveau();
			if ("1".equals(niveau)) {
				return builder.equal(DaoUtil.getCascadeRoot(root, "region.id"),
						personnel.getRegion().getId());
			} else if ("2".equals(niveau)) {
				return builder.equal(
						DaoUtil.getCascadeRoot(root, "districtSante.id"),
						personnel.getDistrictSante().getId());
			} else if ("3".equals(niveau)) {
				return builder.equal(DaoUtil.getCascadeRoot(root, "CDT.id"),
						personnel.getCDT().getId());
			}
		}
		return null;
	}

}
