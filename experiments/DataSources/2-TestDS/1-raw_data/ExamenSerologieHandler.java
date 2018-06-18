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
 * A data handler for the ExamenSerologie beans 
 * @author Medes-IMPS
 */
public class ExamenSerologieHandler {

	private ExamenSerologieDao dao;

	private ImogBeanFilter filter;
	private HandlerHelper handlerHelper;

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public ExamenSerologie findById(String entityId) {
		return dao.load(entityId);
	}

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public ExamenSerologie getById(String entityId) {
		return dao.getById(entityId);
	}

	/**
	 * Saves or updates the entity
	 * @param entity the entity to be saved or updated
	 * @param isNew true if it is a new entity added for the first time.
	 */
	@Transactional
	public void save(ExamenSerologie entity, boolean isNew) {

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
	 * Lists the entities of type ExamenSerologie
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of examenSerologie
	 */
	@Transactional(readOnly = true)
	public List<ExamenSerologie> listExamenSerologie(String sortProperty,
			boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<ExamenSerologie> beans = dao.load(sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type ExamenSerologie
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of examenSerologie
	 */
	@Transactional(readOnly = true)
	public List<ExamenSerologie> listExamenSerologie(String sortProperty,
			boolean sortOrder, ImogJunction junction) {

		List<ExamenSerologie> beans = dao.load(sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type ExamenSerologie
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of examenSerologie
	 */
	@Transactional(readOnly = true)
	public List<ExamenSerologie> listExamenSerologie(int i, int j,
			String sortProperty, boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<ExamenSerologie> beans = dao.load(i, j, sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type ExamenSerologie
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of examenSerologie
	 */
	@Transactional(readOnly = true)
	public List<ExamenSerologie> listExamenSerologie(int i, int j,
			String sortProperty, boolean sortOrder, ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<ExamenSerologie> beans = dao.load(i, j, sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type ExamenSerologie
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of examenSerologie
	 */
	@Transactional(readOnly = true)
	public List<ExamenSerologie> listExamenSerologie(int i, int j,
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

		List<ExamenSerologie> beans = dao.load(i, j, sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type ExamenSerologie	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterion request criteria	 
	 * @param property the property which is not affected
	 * @return list of examenSerologie
	 */
	@Transactional(readOnly = true)
	public List<ExamenSerologie> listNonAffectedExamenSerologie(int i, int j,
			String sortProperty, boolean sortOrder, ImogJunction criterions,
			String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<ExamenSerologie> beans = dao.loadNonAffected(i, j, sortProperty,
				sortOrder, property, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type ExamenSerologie	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected
	 * @return list of examenSerologie
	 */
	@Transactional(readOnly = true)
	public List<ExamenSerologie> listNonAffectedExamenSerologie(int i, int j,
			String sortProperty, boolean sortOrder, String property) {
		return listNonAffectedExamenSerologie(i, j, sortProperty, sortOrder,
				null, property);
	}

	/**
	 * Used when ExamenSerologie is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of ExamenSerologie non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param ImogJunction request criteria
	 * @param property the property which is not affected	 
	 * @return list of examenSerologie
	 */
	@Transactional(readOnly = true)
	public List<ExamenSerologie> listNonAffectedExamenSerologieReverse(int i,
			int j, String sortProperty, boolean sortOrder,
			ImogJunction criterions, String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<ExamenSerologie> beans = dao.loadNonAffectedReverse(i, j,
				sortProperty, sortOrder, property, junction);

		return beans;
	}

	/**
	 * Used when ExamenSerologie is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of ExamenSerologie non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected	 
	 * @return list of examenSerologie
	 */
	@Transactional(readOnly = true)
	public List<ExamenSerologie> listNonAffectedExamenSerologieReverse(int i,
			int j, String sortProperty, boolean sortOrder, String property) {
		return listNonAffectedExamenSerologieReverse(i, j, sortProperty,
				sortOrder, null, property);
	}

	/**
	 * Gets an empty list of ExamenSerologie	
	 * @return an empty list of ExamenSerologie
	 */
	@Transactional(readOnly = true)
	public List<ExamenSerologie> getExamenSerologieEmptyList() {
		return new ArrayList<ExamenSerologie>();
	}

	/**
	 * Counts the number of examenSerologie in the database
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countExamenSerologie() {
		return countExamenSerologie(null);
	}

	/**
	 * Counts the number of examenSerologie in the database, 
	 * that match the criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countExamenSerologie(ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.count(junction);
	}

	/**
	 * Counts the number of non affected examenSerologie entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedExamenSerologie(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.countNonAffected(property, junction);
	}

	/**
	 * Counts the number of non affected examenSerologie entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedExamenSerologie(String property) {
		return countNonAffectedExamenSerologie(property, null);
	}

	/**
	 * Counts the number of non affected examenSerologie entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedExamenSerologieReverse(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);
		return dao.countNonAffectedReverse(property, junction);
	}

	/**
	 * Counts the number of non affected examenSerologie entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedExamenSerologieReverse(String property) {
		return countNonAffectedExamenSerologieReverse(property, null);
	}

	/**
	 * Deletes a group of entities identified by their IDs
	 * @param ids Entities to delete IDs
	 * @return The list of deleted entities IDs
	 */
	@Transactional
	public void delete(Set<ExamenSerologie> entities) {
		if (entities != null) {
			for (ExamenSerologie entity : entities)
				delete(entity);
		}
	}

	/**
	 * Removes the specified entity from the database 
	 * @param entity The entity to be deleted
	 */
	@Transactional
	public void delete(ExamenSerologie entity) {

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
	 * Lists the entities of type ExamenSerologie for the CSV export  
	 */
	@Transactional(readOnly = true)
	public List<ExamenSerologie> listForCsv(String sortProperty,
			boolean sortOrder, String patient_identifiant, String patient_nom,
			String dateTestBefore, String dateTestAfter, String nature,
			String resultatCD4, String resultatVIH) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		if (patient_identifiant != null && !patient_identifiant.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("patient.identifiant");
			criteria.setValue(patient_identifiant);
			junction.add(criteria);
		}
		if (patient_nom != null && !patient_nom.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("patient.nom");
			criteria.setValue(patient_nom);
			junction.add(criteria);
		}
		if (dateTestBefore != null && !dateTestBefore.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			criteria.setField("dateTest");
			criteria.setValue(dateTestBefore);
			junction.add(criteria);
		}
		if (dateTestAfter != null && !dateTestAfter.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			criteria.setField("dateTest");
			criteria.setValue(dateTestAfter);
			junction.add(criteria);
		}
		if (nature != null && !nature.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("nature");
			criteria.setValue(nature);
			junction.add(criteria);
		}
		if (resultatCD4 != null && !resultatCD4.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
			criteria.setField("resultatCD4");
			criteria.setValue(resultatCD4);
			junction.add(criteria);
		}
		if (resultatVIH != null && !resultatVIH.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("resultatVIH");
			criteria.setValue(resultatVIH);
			junction.add(criteria);
		}

		List<ExamenSerologie> beans = dao.load(sortProperty, sortOrder,
				junction);
		List<ExamenSerologie> securedBeans = filter
				.<ExamenSerologie> toSecure(beans);
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
	 * @param dao the ExamenSerologie Dao
	 */
	public void setDao(ExamenSerologieDao dao) {
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
