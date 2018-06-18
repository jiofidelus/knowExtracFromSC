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
 * Manage persistence for Patient
 */
public class PatientDaoImpl extends ImogBeanDaoImpl<Patient>
		implements
			PatientDao {

	protected PatientDaoImpl() {
		super(Patient.class);
	}

	@Override
	public void delete() {
		//TODO
	}

	/* relation dependencies */

	/**
	 * List associated CentreDiagTrait, 
	 * on the field centres
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	@Override
	public List<CentreDiagTrait> loadCentres(Patient parent) {
		if (parent == null) {
			return new Vector<CentreDiagTrait>();
		}
		return parent.getCentres();
	}

	/**
	 * List associated LieuDit, 
	 * on the field lieuxDits
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	@Override
	public List<LieuDit> loadLieuxDits(Patient parent) {
		if (parent == null) {
			return new Vector<LieuDit>();
		}
		return parent.getLieuxDits();
	}

	/**
	 * List associated CasTuberculose, 
	 * on the field casTuberculose
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	@Override
	public List<CasTuberculose> loadCasTuberculose(Patient parent) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<CasTuberculose> query = builder
				.createQuery(CasTuberculose.class);
		Root<CasTuberculose> root = query.from(CasTuberculose.class);
		query.select(root);
		query.where(builder.equal(root.<Patient> get("patient"), parent));
		return em.createQuery(query).getResultList();
	}

	/**
	 * List associated CasIndex, 
	 * on the field casIndex
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	@Override
	public List<CasIndex> loadCasIndex(Patient parent) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<CasIndex> query = builder.createQuery(CasIndex.class);
		Root<CasIndex> root = query.from(CasIndex.class);
		query.select(root);
		query.where(builder.equal(root.<Patient> get("patient"), parent));
		return em.createQuery(query).getResultList();
	}

	/**
	 * List associated ExamenBiologique, 
	 * on the field examensBiologiques
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	@Override
	public List<ExamenBiologique> loadExamensBiologiques(Patient parent) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ExamenBiologique> query = builder
				.createQuery(ExamenBiologique.class);
		Root<ExamenBiologique> root = query.from(ExamenBiologique.class);
		query.select(root);
		query.where(builder.equal(root.<Patient> get("patient"), parent));
		return em.createQuery(query).getResultList();
	}

	/**
	 * List associated ExamenSerologie, 
	 * on the field serologies
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	@Override
	public List<ExamenSerologie> loadSerologies(Patient parent) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ExamenSerologie> query = builder
				.createQuery(ExamenSerologie.class);
		Root<ExamenSerologie> root = query.from(ExamenSerologie.class);
		query.select(root);
		query.where(builder.equal(root.<Patient> get("patient"), parent));
		return em.createQuery(query).getResultList();
	}

	@Override
	public Long countPatientFilteredByCdt(ImogJunction criterion, String cdtId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Patient> root = query.from(Patient.class);
		query.select(builder.count(root));
		Path<Object> join = root.join("centres", JoinType.LEFT).get("id");
		query.where(builder.equal(join, cdtId),
				DaoUtil.<Patient> toPredicate(criterion, builder, root));
		return em.createQuery(query).getSingleResult();
	}

	@Override
	public List<Patient> listPatientFilteredByCdt(int first, int max,
			String property, boolean asc, ImogJunction criterion, String cdtId) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Patient> query = builder.createQuery(clazz);
		Root<Patient> root = query.from(Patient.class);
		query.select(root);
		Path<Object> join = root.join("centres", JoinType.LEFT).get("id");
		query.where(builder.equal(join, cdtId),
				DaoUtil.<Patient> toPredicate(criterion, builder, root));
		if (property == null) {
			property = "modified";
		}
		Order o = asc
				? builder.asc(DaoUtil.getCascadeRoot(root, property))
				: builder.desc(DaoUtil.getCascadeRoot(root, property));
		query.orderBy(o, builder.desc(root.<String> get("id")));
		return em.createQuery(query).setFirstResult(first).setMaxResults(max)
				.getResultList();
	}

	@Override
	protected Predicate getFilter(Root<Patient> root, CriteriaBuilder builder) {
		ImogActor actor = ImogActorUtils.getCurrentActor();
		if (actor == null) {
			actor = HttpSessionUtil.getCurrentUser();
		}
		if (actor instanceof Personnel) {
			Personnel personnel = (Personnel) actor;
			String niveau = personnel.getNiveau();
			if ("1".equals(niveau)) {
				Path<?> join = DaoUtil.getCascadeRoot(
						root.join("centres", JoinType.LEFT), "region.id");
				return builder.equal(join, personnel.getRegion().getId());
			} else if ("2".equals(niveau)) {
				Path<?> join = DaoUtil
						.getCascadeRoot(root.join("centres", JoinType.LEFT),
								"districtSante.id");
				return builder
						.equal(join, personnel.getDistrictSante().getId());
			} else if ("3".equals(niveau)) {
				Path<?> join = root.join("centres", JoinType.LEFT).get("id");
				return builder.equal(join, personnel.getCDT().getId());
			}
		}
		return null;
	}

}
