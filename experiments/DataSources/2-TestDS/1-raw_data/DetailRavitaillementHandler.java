package org.imogene.epicam.server.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.imogene.epicam.domain.dao.*;
import org.imogene.epicam.domain.entity.*;
import org.imogene.lib.common.binary.Binary;
import org.imogene.lib.common.constants.CriteriaConstants;
import org.imogene.lib.common.criteria.BasicCriteria;
import org.imogene.lib.common.criteria.ImogConjunction;
import org.imogene.lib.common.criteria.ImogDisjunction;
import org.imogene.lib.common.criteria.ImogJunction;
import org.imogene.lib.common.entity.ImogActor;
import org.imogene.lib.common.entity.ImogBean;
import org.imogene.lib.common.security.ImogBeanFilter;
import org.imogene.web.server.util.FilterFieldsHelper;
import org.imogene.web.server.util.HttpSessionUtil;
import org.imogene.web.server.util.ProfileUtil;
import org.imogene.web.server.handler.HandlerHelper;

import org.springframework.transaction.annotation.Transactional;

/**
 * A data handler for the DetailRavitaillement beans 
 * @author Medes-IMPS
 */
public class DetailRavitaillementHandler {

	private DetailRavitaillementDao dao;

	private ImogBeanFilter filter;
	private HandlerHelper handlerHelper;

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public DetailRavitaillement findById(String entityId) {
		return dao.load(entityId);
	}

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public DetailRavitaillement getById(String entityId) {
		return dao.getById(entityId);
	}

	/**
	 * Saves or updates the entity
	 * @param entity the entity to be saved or updated
	 * @param isNew true if it is a new entity added for the first time.
	 */
	@Transactional
	public void save(DetailRavitaillement entity, boolean isNew) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();

		if (entity != null) {

			handlerHelper.prepare(entity);
			if (entity.getDeleted() != null)
				entity.setDeleted(null);

			// manage related sortieLot
			SortieLot det_ravba = entity.getSortieLot();
			if (det_ravba != null) {
				handlerHelper.prepare(det_ravba);
			}
			// manage related entreeLot
			EntreeLot det_ravca = entity.getEntreeLot();
			if (det_ravca != null) {
				handlerHelper.prepare(det_ravca);
			}

			dao.saveOrUpdate(entity, isNew);

		}
	}

	/**
	 * Saves or updates the bean
	 * @param entity the bean to be saved or updated
	 * @param isNew true if it is a new entity added for the first time.
	 */
	@Transactional
	public void save(ImogBean entity, boolean isNew) {
		handlerHelper.save(entity, isNew);
	}

	/**
	 * Lists the entities of type DetailRavitaillement
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of detailRavitaillement
	 */
	@Transactional(readOnly = true)
	public List<DetailRavitaillement> listDetailRavitaillement(
			String sortProperty, boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<DetailRavitaillement> beans = dao.load(sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type DetailRavitaillement
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of detailRavitaillement
	 */
	@Transactional(readOnly = true)
	public List<DetailRavitaillement> listDetailRavitaillement(
			String sortProperty, boolean sortOrder, ImogJunction junction) {

		List<DetailRavitaillement> beans = dao.load(sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type DetailRavitaillement
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of detailRavitaillement
	 */
	@Transactional(readOnly = true)
	public List<DetailRavitaillement> listDetailRavitaillement(int i, int j,
			String sortProperty, boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<DetailRavitaillement> beans = dao.load(i, j, sortProperty,
				sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type DetailRavitaillement
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of detailRavitaillement
	 */
	@Transactional(readOnly = true)
	public List<DetailRavitaillement> listDetailRavitaillement(int i, int j,
			String sortProperty, boolean sortOrder, ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<DetailRavitaillement> beans = dao.load(i, j, sortProperty,
				sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type DetailRavitaillement
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of detailRavitaillement
	 */
	@Transactional(readOnly = true)
	public List<DetailRavitaillement> listDetailRavitaillement(int i, int j,
			String sortProperty, boolean sortOrder,
			List<BasicCriteria> criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		ImogJunction junctionForCrit = new ImogConjunction();
		if (criterions != null) {
			for (BasicCriteria crit : criterions)
				junctionForCrit.add(crit);
		}
		junction.add(junctionForCrit);

		List<DetailRavitaillement> beans = dao.load(i, j, sortProperty,
				sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type DetailRavitaillement	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterion request criteria	 
	 * @param property the property which is not affected
	 * @return list of detailRavitaillement
	 */
	@Transactional(readOnly = true)
	public List<DetailRavitaillement> listNonAffectedDetailRavitaillement(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunction criterions, String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<DetailRavitaillement> beans = dao.loadNonAffected(i, j,
				sortProperty, sortOrder, property, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type DetailRavitaillement	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected
	 * @return list of detailRavitaillement
	 */
	@Transactional(readOnly = true)
	public List<DetailRavitaillement> listNonAffectedDetailRavitaillement(
			int i, int j, String sortProperty, boolean sortOrder,
			String property) {
		return listNonAffectedDetailRavitaillement(i, j, sortProperty,
				sortOrder, null, property);
	}

	/**
	 * Used when DetailRavitaillement is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of DetailRavitaillement non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param ImogJunction request criteria
	 * @param property the property which is not affected	 
	 * @return list of detailRavitaillement
	 */
	@Transactional(readOnly = true)
	public List<DetailRavitaillement> listNonAffectedDetailRavitaillementReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunction criterions, String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<DetailRavitaillement> beans = dao.loadNonAffectedReverse(i, j,
				sortProperty, sortOrder, property, junction);

		return beans;
	}

	/**
	 * Used when DetailRavitaillement is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of DetailRavitaillement non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected	 
	 * @return list of detailRavitaillement
	 */
	@Transactional(readOnly = true)
	public List<DetailRavitaillement> listNonAffectedDetailRavitaillementReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property) {
		return listNonAffectedDetailRavitaillementReverse(i, j, sortProperty,
				sortOrder, null, property);
	}

	/**
	 * Gets an empty list of DetailRavitaillement	
	 * @return an empty list of DetailRavitaillement
	 */
	@Transactional(readOnly = true)
	public List<DetailRavitaillement> getDetailRavitaillementEmptyList() {
		return new ArrayList<DetailRavitaillement>();
	}

	/**
	 * Counts the number of detailRavitaillement in the database
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countDetailRavitaillement() {
		return countDetailRavitaillement(null);
	}

	/**
	 * Counts the number of detailRavitaillement in the database, 
	 * that match the criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countDetailRavitaillement(ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.count(junction);
	}

	/**
	 * Counts the number of non affected detailRavitaillement entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedDetailRavitaillement(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.countNonAffected(property, junction);
	}

	/**
	 * Counts the number of non affected detailRavitaillement entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedDetailRavitaillement(String property) {
		return countNonAffectedDetailRavitaillement(property, null);
	}

	/**
	 * Counts the number of non affected detailRavitaillement entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedDetailRavitaillementReverse(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);
		return dao.countNonAffectedReverse(property, junction);
	}

	/**
	 * Counts the number of non affected detailRavitaillement entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedDetailRavitaillementReverse(String property) {
		return countNonAffectedDetailRavitaillementReverse(property, null);
	}

	/**
	 * Deletes a group of entities identified by their IDs
	 * @param ids Entities to delete IDs
	 * @return The list of deleted entities IDs
	 */
	@Transactional
	public void delete(Set<DetailRavitaillement> entities) {
		if (entities != null) {
			for (DetailRavitaillement entity : entities)
				delete(entity);
		}
	}

	/**
	 * Removes the specified entity from the database 
	 * @param entity The entity to be deleted
	 */
	@Transactional
	public void delete(DetailRavitaillement entity) {

		handlerHelper.prepareForDelete(entity);
		dao.saveOrUpdate(entity, false);
	}

	/**
	 * Removes the specified bean from the database 
	 * @param entity The bean to be deleted
	 */
	@Transactional
	public void delete(ImogBean entity) {
		handlerHelper.delete(entity);
	}

	/**
	 * Lists the entities of type DetailRavitaillement for the CSV export  
	 */
	@Transactional(readOnly = true)
	public List<DetailRavitaillement> listForCsv(String sortProperty,
			boolean sortOrder, String ravitaillement_cDTDepart_nom,
			String ravitaillement_dateDepartBefore,
			String ravitaillement_dateDepartAfter,
			String ravitaillement_cDTArrivee_nom,
			String ravitaillement_dateArriveeBefore,
			String ravitaillement_dateArriveeAfter,
			String sortieLot_lot_numero,
			String sortieLot_lot_intrant_identifiant,
			String sortieLot_lot_medicament_designation,
			String sortieLot_lot_quantite, String sortieLot_quantite,
			String entreeLot_lot_numero,
			String entreeLot_lot_intrant_identifiant,
			String entreeLot_lot_medicament_designation,
			String entreeLot_lot_quantite, String entreeLot_quantite) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		if (ravitaillement_cDTDepart_nom != null
				&& !ravitaillement_cDTDepart_nom.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("ravitaillement.cDTDepart.nom");
			criteria.setValue(ravitaillement_cDTDepart_nom);
			junction.add(criteria);
		}
		if (ravitaillement_dateDepartBefore != null
				&& !ravitaillement_dateDepartBefore.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			criteria.setField("ravitaillement.dateDepart");
			criteria.setValue(ravitaillement_dateDepartBefore);
			junction.add(criteria);
		}
		if (ravitaillement_dateDepartAfter != null
				&& !ravitaillement_dateDepartAfter.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			criteria.setField("ravitaillement.dateDepart");
			criteria.setValue(ravitaillement_dateDepartAfter);
			junction.add(criteria);
		}
		if (ravitaillement_cDTArrivee_nom != null
				&& !ravitaillement_cDTArrivee_nom.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("ravitaillement.cDTArrivee.nom");
			criteria.setValue(ravitaillement_cDTArrivee_nom);
			junction.add(criteria);
		}
		if (ravitaillement_dateArriveeBefore != null
				&& !ravitaillement_dateArriveeBefore.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			criteria.setField("ravitaillement.dateArrivee");
			criteria.setValue(ravitaillement_dateArriveeBefore);
			junction.add(criteria);
		}
		if (ravitaillement_dateArriveeAfter != null
				&& !ravitaillement_dateArriveeAfter.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			criteria.setField("ravitaillement.dateArrivee");
			criteria.setValue(ravitaillement_dateArriveeAfter);
			junction.add(criteria);
		}
		if (sortieLot_lot_numero != null && !sortieLot_lot_numero.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("sortieLot.lot.numero");
			criteria.setValue(sortieLot_lot_numero);
			junction.add(criteria);
		}
		if (sortieLot_lot_intrant_identifiant != null
				&& !sortieLot_lot_intrant_identifiant.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("sortieLot.lot.intrant.identifiant");
			criteria.setValue(sortieLot_lot_intrant_identifiant);
			junction.add(criteria);
		}
		if (sortieLot_lot_medicament_designation != null
				&& !sortieLot_lot_medicament_designation.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("sortieLot.lot.medicament.designation");
			criteria.setValue(sortieLot_lot_medicament_designation);
			junction.add(criteria);
		}
		if (sortieLot_lot_quantite != null && !sortieLot_lot_quantite.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
			criteria.setField("sortieLot.lot.quantite");
			criteria.setValue(sortieLot_lot_quantite);
			junction.add(criteria);
		}
		if (sortieLot_quantite != null && !sortieLot_quantite.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
			criteria.setField("sortieLot.quantite");
			criteria.setValue(sortieLot_quantite);
			junction.add(criteria);
		}
		if (entreeLot_lot_numero != null && !entreeLot_lot_numero.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("entreeLot.lot.numero");
			criteria.setValue(entreeLot_lot_numero);
			junction.add(criteria);
		}
		if (entreeLot_lot_intrant_identifiant != null
				&& !entreeLot_lot_intrant_identifiant.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("entreeLot.lot.intrant.identifiant");
			criteria.setValue(entreeLot_lot_intrant_identifiant);
			junction.add(criteria);
		}
		if (entreeLot_lot_medicament_designation != null
				&& !entreeLot_lot_medicament_designation.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("entreeLot.lot.medicament.designation");
			criteria.setValue(entreeLot_lot_medicament_designation);
			junction.add(criteria);
		}
		if (entreeLot_lot_quantite != null && !entreeLot_lot_quantite.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
			criteria.setField("entreeLot.lot.quantite");
			criteria.setValue(entreeLot_lot_quantite);
			junction.add(criteria);
		}
		if (entreeLot_quantite != null && !entreeLot_quantite.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
			criteria.setField("entreeLot.quantite");
			criteria.setValue(entreeLot_quantite);
			junction.add(criteria);
		}

		List<DetailRavitaillement> beans = dao.load(sortProperty, sortOrder,
				junction);
		List<DetailRavitaillement> securedBeans = filter
				.<DetailRavitaillement> toSecure(beans);
		return securedBeans;
	}

	/**
	 * Creates a junction based on the filter field declarations, for the current actor.
	 * @param actor the current actor
	 */
	private ImogJunction createFilterJuntion(ImogActor actor) {
		ImogConjunction filterConjunction = new ImogConjunction();
		if (!ProfileUtil.isAdmin(actor))
			filterConjunction.add(handlerHelper.getNotDeletedFilterCriteria());
		return filterConjunction;
	}

	/**
	 * Setter for bean injection
	 * @param dao the DetailRavitaillement Dao
	 */
	public void setDao(DetailRavitaillementDao dao) {
		this.dao = dao;
	}

	/**
	 * Setter for bean injection
	 * @param imogBeanFilter
	 */
	public void setFilter(ImogBeanFilter filter) {
		this.filter = filter;
	}

	/**
	 * Setter for bean injection.
	 * @param helper
	 */
	public void setHandlerHelper(HandlerHelper helper) {
		this.handlerHelper = helper;
	}

}
