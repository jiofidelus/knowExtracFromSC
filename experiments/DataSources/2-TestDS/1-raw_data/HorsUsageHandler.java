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
 * A data handler for the HorsUsage beans 
 * @author Medes-IMPS
 */
public class HorsUsageHandler {

	private HorsUsageDao dao;

	private ImogBeanFilter filter;
	private HandlerHelper handlerHelper;

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public HorsUsage findById(String entityId) {
		return dao.load(entityId);
	}

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public HorsUsage getById(String entityId) {
		return dao.getById(entityId);
	}

	/**
	 * Saves or updates the entity
	 * @param entity the entity to be saved or updated
	 * @param isNew true if it is a new entity added for the first time.
	 */
	@Transactional
	public void save(HorsUsage entity, boolean isNew) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();

		if (entity != null) {

			handlerHelper.prepare(entity);
			if (entity.getDeleted() != null)
				entity.setDeleted(null);

			// manage related lot
			SortieLot ho_usaa = entity.getLot();
			if (ho_usaa != null) {
				handlerHelper.prepare(ho_usaa);
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
	 * Lists the entities of type HorsUsage
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of horsUsage
	 */
	@Transactional(readOnly = true)
	public List<HorsUsage> listHorsUsage(String sortProperty, boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<HorsUsage> beans = dao.load(sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type HorsUsage
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of horsUsage
	 */
	@Transactional(readOnly = true)
	public List<HorsUsage> listHorsUsage(String sortProperty,
			boolean sortOrder, ImogJunction junction) {

		List<HorsUsage> beans = dao.load(sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type HorsUsage
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of horsUsage
	 */
	@Transactional(readOnly = true)
	public List<HorsUsage> listHorsUsage(int i, int j, String sortProperty,
			boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<HorsUsage> beans = dao.load(i, j, sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type HorsUsage
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of horsUsage
	 */
	@Transactional(readOnly = true)
	public List<HorsUsage> listHorsUsage(int i, int j, String sortProperty,
			boolean sortOrder, ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<HorsUsage> beans = dao.load(i, j, sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type HorsUsage
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of horsUsage
	 */
	@Transactional(readOnly = true)
	public List<HorsUsage> listHorsUsage(int i, int j, String sortProperty,
			boolean sortOrder, List<BasicCriteria> criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		ImogJunction junctionForCrit = new ImogConjunction();
		if (criterions != null) {
			for (BasicCriteria crit : criterions)
				junctionForCrit.add(crit);
		}
		junction.add(junctionForCrit);

		List<HorsUsage> beans = dao.load(i, j, sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type HorsUsage	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterion request criteria	 
	 * @param property the property which is not affected
	 * @return list of horsUsage
	 */
	@Transactional(readOnly = true)
	public List<HorsUsage> listNonAffectedHorsUsage(int i, int j,
			String sortProperty, boolean sortOrder, ImogJunction criterions,
			String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<HorsUsage> beans = dao.loadNonAffected(i, j, sortProperty,
				sortOrder, property, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type HorsUsage	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected
	 * @return list of horsUsage
	 */
	@Transactional(readOnly = true)
	public List<HorsUsage> listNonAffectedHorsUsage(int i, int j,
			String sortProperty, boolean sortOrder, String property) {
		return listNonAffectedHorsUsage(i, j, sortProperty, sortOrder, null,
				property);
	}

	/**
	 * Used when HorsUsage is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of HorsUsage non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param ImogJunction request criteria
	 * @param property the property which is not affected	 
	 * @return list of horsUsage
	 */
	@Transactional(readOnly = true)
	public List<HorsUsage> listNonAffectedHorsUsageReverse(int i, int j,
			String sortProperty, boolean sortOrder, ImogJunction criterions,
			String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<HorsUsage> beans = dao.loadNonAffectedReverse(i, j, sortProperty,
				sortOrder, property, junction);

		return beans;
	}

	/**
	 * Used when HorsUsage is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of HorsUsage non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected	 
	 * @return list of horsUsage
	 */
	@Transactional(readOnly = true)
	public List<HorsUsage> listNonAffectedHorsUsageReverse(int i, int j,
			String sortProperty, boolean sortOrder, String property) {
		return listNonAffectedHorsUsageReverse(i, j, sortProperty, sortOrder,
				null, property);
	}

	/**
	 * Gets an empty list of HorsUsage	
	 * @return an empty list of HorsUsage
	 */
	@Transactional(readOnly = true)
	public List<HorsUsage> getHorsUsageEmptyList() {
		return new ArrayList<HorsUsage>();
	}

	/**
	 * Counts the number of horsUsage in the database
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countHorsUsage() {
		return countHorsUsage(null);
	}

	/**
	 * Counts the number of horsUsage in the database, 
	 * that match the criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countHorsUsage(ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.count(junction);
	}

	/**
	 * Counts the number of non affected horsUsage entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedHorsUsage(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.countNonAffected(property, junction);
	}

	/**
	 * Counts the number of non affected horsUsage entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedHorsUsage(String property) {
		return countNonAffectedHorsUsage(property, null);
	}

	/**
	 * Counts the number of non affected horsUsage entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedHorsUsageReverse(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);
		return dao.countNonAffectedReverse(property, junction);
	}

	/**
	 * Counts the number of non affected horsUsage entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedHorsUsageReverse(String property) {
		return countNonAffectedHorsUsageReverse(property, null);
	}

	/**
	 * Deletes a group of entities identified by their IDs
	 * @param ids Entities to delete IDs
	 * @return The list of deleted entities IDs
	 */
	@Transactional
	public void delete(Set<HorsUsage> entities) {
		if (entities != null) {
			for (HorsUsage entity : entities)
				delete(entity);
		}
	}

	/**
	 * Removes the specified entity from the database 
	 * @param entity The entity to be deleted
	 */
	@Transactional
	public void delete(HorsUsage entity) {

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
	 * Lists the entities of type HorsUsage for the CSV export  
	 */
	@Transactional(readOnly = true)
	public List<HorsUsage> listForCsv(String sortProperty, boolean sortOrder,
			String lot_lot_numero, String lot_lot_intrant_identifiant,
			String lot_lot_medicament_designation, String lot_lot_quantite,
			String lot_quantite, String type) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		if (lot_lot_numero != null && !lot_lot_numero.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("lot.lot.numero");
			criteria.setValue(lot_lot_numero);
			junction.add(criteria);
		}
		if (lot_lot_intrant_identifiant != null
				&& !lot_lot_intrant_identifiant.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("lot.lot.intrant.identifiant");
			criteria.setValue(lot_lot_intrant_identifiant);
			junction.add(criteria);
		}
		if (lot_lot_medicament_designation != null
				&& !lot_lot_medicament_designation.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("lot.lot.medicament.designation");
			criteria.setValue(lot_lot_medicament_designation);
			junction.add(criteria);
		}
		if (lot_lot_quantite != null && !lot_lot_quantite.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
			criteria.setField("lot.lot.quantite");
			criteria.setValue(lot_lot_quantite);
			junction.add(criteria);
		}
		if (lot_quantite != null && !lot_quantite.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
			criteria.setField("lot.quantite");
			criteria.setValue(lot_quantite);
			junction.add(criteria);
		}
		if (type != null && !type.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("type");
			criteria.setValue(type);
			junction.add(criteria);
		}

		List<HorsUsage> beans = dao.load(sortProperty, sortOrder, junction);
		List<HorsUsage> securedBeans = filter.<HorsUsage> toSecure(beans);
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
	 * @param dao the HorsUsage Dao
	 */
	public void setDao(HorsUsageDao dao) {
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
