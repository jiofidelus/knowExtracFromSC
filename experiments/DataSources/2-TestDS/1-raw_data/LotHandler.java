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
 * A data handler for the Lot beans 
 * @author Medes-IMPS
 */
public class LotHandler {

	private LotDao dao;

	private ImogBeanFilter filter;
	private HandlerHelper handlerHelper;

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public Lot findById(String entityId) {
		return dao.load(entityId);
	}

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public Lot getById(String entityId) {
		return dao.getById(entityId);
	}

	/**
	 * Saves or updates the entity
	 * @param entity the entity to be saved or updated
	 * @param isNew true if it is a new entity added for the first time.
	 */
	@Transactional
	public void save(Lot entity, boolean isNew) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();

		if (entity != null) {

			handlerHelper.prepare(entity);
			if (entity.getDeleted() != null)
				entity.setDeleted(null);

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
	 * Lists the entities of type Lot
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of lot
	 */
	@Transactional(readOnly = true)
	public List<Lot> listLot(String sortProperty, boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<Lot> beans = dao.load(sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type Lot
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of lot
	 */
	@Transactional(readOnly = true)
	public List<Lot> listLot(String sortProperty, boolean sortOrder,
			ImogJunction junction) {

		List<Lot> beans = dao.load(sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type Lot
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of lot
	 */
	@Transactional(readOnly = true)
	public List<Lot> listLot(int i, int j, String sortProperty,
			boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<Lot> beans = dao.load(i, j, sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type Lot
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of lot
	 */
	@Transactional(readOnly = true)
	public List<Lot> listLot(int i, int j, String sortProperty,
			boolean sortOrder, ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<Lot> beans = dao.load(i, j, sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type Lot
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of lot
	 */
	@Transactional(readOnly = true)
	public List<Lot> listLot(int i, int j, String sortProperty,
			boolean sortOrder, List<BasicCriteria> criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		ImogJunction junctionForCrit = new ImogConjunction();
		if (criterions != null) {
			for (BasicCriteria crit : criterions)
				junctionForCrit.add(crit);
		}
		junction.add(junctionForCrit);

		List<Lot> beans = dao.load(i, j, sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type Lot	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterion request criteria	 
	 * @param property the property which is not affected
	 * @return list of lot
	 */
	@Transactional(readOnly = true)
	public List<Lot> listNonAffectedLot(int i, int j, String sortProperty,
			boolean sortOrder, ImogJunction criterions, String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<Lot> beans = dao.loadNonAffected(i, j, sortProperty, sortOrder,
				property, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type Lot	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected
	 * @return list of lot
	 */
	@Transactional(readOnly = true)
	public List<Lot> listNonAffectedLot(int i, int j, String sortProperty,
			boolean sortOrder, String property) {
		return listNonAffectedLot(i, j, sortProperty, sortOrder, null, property);
	}

	/**
	 * Used when Lot is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of Lot non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param ImogJunction request criteria
	 * @param property the property which is not affected	 
	 * @return list of lot
	 */
	@Transactional(readOnly = true)
	public List<Lot> listNonAffectedLotReverse(int i, int j,
			String sortProperty, boolean sortOrder, ImogJunction criterions,
			String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<Lot> beans = dao.loadNonAffectedReverse(i, j, sortProperty,
				sortOrder, property, junction);

		return beans;
	}

	/**
	 * Used when Lot is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of Lot non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected	 
	 * @return list of lot
	 */
	@Transactional(readOnly = true)
	public List<Lot> listNonAffectedLotReverse(int i, int j,
			String sortProperty, boolean sortOrder, String property) {
		return listNonAffectedLotReverse(i, j, sortProperty, sortOrder, null,
				property);
	}

	/**
	 * Gets an empty list of Lot	
	 * @return an empty list of Lot
	 */
	@Transactional(readOnly = true)
	public List<Lot> getLotEmptyList() {
		return new ArrayList<Lot>();
	}

	/**
	 * Counts the number of lot in the database
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countLot() {
		return countLot(null);
	}

	/**
	 * Counts the number of lot in the database, 
	 * that match the criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countLot(ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.count(junction);
	}

	/**
	 * Counts the number of non affected lot entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedLot(String property, ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.countNonAffected(property, junction);
	}

	/**
	 * Counts the number of non affected lot entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedLot(String property) {
		return countNonAffectedLot(property, null);
	}

	/**
	 * Counts the number of non affected lot entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedLotReverse(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);
		return dao.countNonAffectedReverse(property, junction);
	}

	/**
	 * Counts the number of non affected lot entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedLotReverse(String property) {
		return countNonAffectedLotReverse(property, null);
	}

	/**
	 * Deletes a group of entities identified by their IDs
	 * @param ids Entities to delete IDs
	 * @return The list of deleted entities IDs
	 */
	@Transactional
	public void delete(Set<Lot> entities) {
		if (entities != null) {
			for (Lot entity : entities)
				delete(entity);
		}
	}

	/**
	 * Removes the specified entity from the database 
	 * @param entity The entity to be deleted
	 */
	@Transactional
	public void delete(Lot entity) {

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
	 * Lists the entities of type Lot for the CSV export  
	 */
	@Transactional(readOnly = true)
	public List<Lot> listForCsv(String sortProperty, boolean sortOrder,
			String cDT_nom, String numero, String quantite,
			String datePeremptionBefore, String datePeremptionAfter,
			String intrant_identifiant, String medicament_designation) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		if (cDT_nom != null && !cDT_nom.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("cDT.nom");
			criteria.setValue(cDT_nom);
			junction.add(criteria);
		}
		if (numero != null && !numero.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("numero");
			criteria.setValue(numero);
			junction.add(criteria);
		}
		if (quantite != null && !quantite.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
			criteria.setField("quantite");
			criteria.setValue(quantite);
			junction.add(criteria);
		}
		if (datePeremptionBefore != null && !datePeremptionBefore.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			criteria.setField("datePeremption");
			criteria.setValue(datePeremptionBefore);
			junction.add(criteria);
		}
		if (datePeremptionAfter != null && !datePeremptionAfter.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			criteria.setField("datePeremption");
			criteria.setValue(datePeremptionAfter);
			junction.add(criteria);
		}
		if (intrant_identifiant != null && !intrant_identifiant.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("intrant.identifiant");
			criteria.setValue(intrant_identifiant);
			junction.add(criteria);
		}
		if (medicament_designation != null && !medicament_designation.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("medicament.designation");
			criteria.setValue(medicament_designation);
			junction.add(criteria);
		}

		List<Lot> beans = dao.load(sortProperty, sortOrder, junction);
		List<Lot> securedBeans = filter.<Lot> toSecure(beans);
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
	 * @param dao the Lot Dao
	 */
	public void setDao(LotDao dao) {
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

	@Transactional(readOnly = true)
	public Boolean isLotUniqueForCdt(String numero, String cdtId) {

		if (cdtId != null) {

			if (numero != null && !numero.isEmpty()) {
				ImogJunction junction = new ImogConjunction();

				BasicCriteria criteria1 = new BasicCriteria();
				criteria1
						.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
				criteria1.setField("numero");
				criteria1.setValue(numero);
				junction.add(criteria1);

				BasicCriteria criteria2 = new BasicCriteria();
				criteria2
						.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
				criteria2.setField("CDT.id");
				criteria2.setValue(cdtId);
				junction.add(criteria2);

				List<Lot> entities = dao.load(junction);
				if (entities == null || entities.size() == 0)
					return true;
				else
					return false;
			} else
				return false;
		}
		return false;
	}

}
