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

import org.imogene.admin.server.security.SecurityUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data handler for the Personnel beans 
 * @author Medes-IMPS
 */
public class PersonnelHandler {

	private PersonnelDao dao;

	private ImogBeanFilter filter;
	private HandlerHelper handlerHelper;

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public Personnel findById(String entityId) {
		return dao.load(entityId);
	}

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public Personnel getById(String entityId) {
		return dao.getById(entityId);
	}

	/**
	 * Saves or updates the entity
	 * @param entity the entity to be saved or updated
	 * @param isNew true if it is a new entity added for the first time.
	 */
	@Transactional
	public void save(Personnel entity, boolean isNew) {
		save(entity, isNew, false);
	}

	/**
	 * Saves or updates the entity
	 * @param entity the entity to be saved or updated
	 * @param isNew true if it is a new entity added for the first time.
	 * @param passwordChanged true if the password has changed
	 */
	@Transactional
	public void save(Personnel entity, boolean isNew, boolean passwordChanged) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();

		if (entity != null) {

			if (passwordChanged)
				entity.setPassword(SecurityUtils.passwordHashAsBase64(
						entity.getPassword(), entity.getLogin()));

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
	 * Lists the entities of type Personnel
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of personnel
	 */
	@Transactional(readOnly = true)
	public List<Personnel> listPersonnel(String sortProperty, boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<Personnel> beans = dao.load(sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type Personnel
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of personnel
	 */
	@Transactional(readOnly = true)
	public List<Personnel> listPersonnel(String sortProperty,
			boolean sortOrder, ImogJunction junction) {

		List<Personnel> beans = dao.load(sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type Personnel
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of personnel
	 */
	@Transactional(readOnly = true)
	public List<Personnel> listPersonnel(int i, int j, String sortProperty,
			boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<Personnel> beans = dao.load(i, j, sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type Personnel
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of personnel
	 */
	@Transactional(readOnly = true)
	public List<Personnel> listPersonnel(int i, int j, String sortProperty,
			boolean sortOrder, ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<Personnel> beans = dao.load(i, j, sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type Personnel
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of personnel
	 */
	@Transactional(readOnly = true)
	public List<Personnel> listPersonnel(int i, int j, String sortProperty,
			boolean sortOrder, List<BasicCriteria> criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		ImogJunction junctionForCrit = new ImogConjunction();
		if (criterions != null) {
			for (BasicCriteria crit : criterions)
				junctionForCrit.add(crit);
		}
		junction.add(junctionForCrit);

		List<Personnel> beans = dao.load(i, j, sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type Personnel	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterion request criteria	 
	 * @param property the property which is not affected
	 * @return list of personnel
	 */
	@Transactional(readOnly = true)
	public List<Personnel> listNonAffectedPersonnel(int i, int j,
			String sortProperty, boolean sortOrder, ImogJunction criterions,
			String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<Personnel> beans = dao.loadNonAffected(i, j, sortProperty,
				sortOrder, property, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type Personnel	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected
	 * @return list of personnel
	 */
	@Transactional(readOnly = true)
	public List<Personnel> listNonAffectedPersonnel(int i, int j,
			String sortProperty, boolean sortOrder, String property) {
		return listNonAffectedPersonnel(i, j, sortProperty, sortOrder, null,
				property);
	}

	/**
	 * Used when Personnel is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of Personnel non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param ImogJunction request criteria
	 * @param property the property which is not affected	 
	 * @return list of personnel
	 */
	@Transactional(readOnly = true)
	public List<Personnel> listNonAffectedPersonnelReverse(int i, int j,
			String sortProperty, boolean sortOrder, ImogJunction criterions,
			String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<Personnel> beans = dao.loadNonAffectedReverse(i, j, sortProperty,
				sortOrder, property, junction);

		return beans;
	}

	/**
	 * Used when Personnel is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of Personnel non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected	 
	 * @return list of personnel
	 */
	@Transactional(readOnly = true)
	public List<Personnel> listNonAffectedPersonnelReverse(int i, int j,
			String sortProperty, boolean sortOrder, String property) {
		return listNonAffectedPersonnelReverse(i, j, sortProperty, sortOrder,
				null, property);
	}

	/**
	 * Gets an empty list of Personnel	
	 * @return an empty list of Personnel
	 */
	@Transactional(readOnly = true)
	public List<Personnel> getPersonnelEmptyList() {
		return new ArrayList<Personnel>();
	}

	/**
	 * Counts the number of personnel in the database
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countPersonnel() {
		return countPersonnel(null);
	}

	/**
	 * Counts the number of personnel in the database, 
	 * that match the criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countPersonnel(ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.count(junction);
	}

	/**
	 * Counts the number of non affected personnel entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedPersonnel(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.countNonAffected(property, junction);
	}

	/**
	 * Counts the number of non affected personnel entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedPersonnel(String property) {
		return countNonAffectedPersonnel(property, null);
	}

	/**
	 * Counts the number of non affected personnel entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedPersonnelReverse(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);
		return dao.countNonAffectedReverse(property, junction);
	}

	/**
	 * Counts the number of non affected personnel entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedPersonnelReverse(String property) {
		return countNonAffectedPersonnelReverse(property, null);
	}

	/**
	 * Deletes a group of entities identified by their IDs
	 * @param ids Entities to delete IDs
	 * @return The list of deleted entities IDs
	 */
	@Transactional
	public void delete(Set<Personnel> entities) {
		if (entities != null) {
			for (Personnel entity : entities)
				delete(entity);
		}
	}

	/**
	 * Removes the specified entity from the database 
	 * @param entity The entity to be deleted
	 */
	@Transactional
	public void delete(Personnel entity) {

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
	 * Lists the entities of type Personnel for the CSV export  
	 */
	@Transactional(readOnly = true)
	public List<Personnel> listForCsv(String sortProperty, boolean sortOrder,
			String nom, String dateNaissanceBefore, String dateNaissanceAfter,
			String dateArriveeBefore, String dateArriveeAfter,
			String dateDepartBefore, String dateDepartAfter, String niveau,
			String actif) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		if (nom != null && !nom.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("nom");
			criteria.setValue(nom);
			junction.add(criteria);
		}
		if (dateNaissanceBefore != null && !dateNaissanceBefore.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			criteria.setField("dateNaissance");
			criteria.setValue(dateNaissanceBefore);
			junction.add(criteria);
		}
		if (dateNaissanceAfter != null && !dateNaissanceAfter.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			criteria.setField("dateNaissance");
			criteria.setValue(dateNaissanceAfter);
			junction.add(criteria);
		}
		if (dateArriveeBefore != null && !dateArriveeBefore.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			criteria.setField("dateArrivee");
			criteria.setValue(dateArriveeBefore);
			junction.add(criteria);
		}
		if (dateArriveeAfter != null && !dateArriveeAfter.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			criteria.setField("dateArrivee");
			criteria.setValue(dateArriveeAfter);
			junction.add(criteria);
		}
		if (dateDepartBefore != null && !dateDepartBefore.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			criteria.setField("dateDepart");
			criteria.setValue(dateDepartBefore);
			junction.add(criteria);
		}
		if (dateDepartAfter != null && !dateDepartAfter.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			criteria.setField("dateDepart");
			criteria.setValue(dateDepartAfter);
			junction.add(criteria);
		}
		if (niveau != null && !niveau.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("niveau");
			criteria.setValue(niveau);
			junction.add(criteria);
		}
		if (actif != null && !actif.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.BOOLEAN_OPERATOR_EQUAL);
			criteria.setField("actif");
			criteria.setValue(actif);
			junction.add(criteria);
		}

		List<Personnel> beans = dao.load(sortProperty, sortOrder, junction);
		List<Personnel> securedBeans = filter.<Personnel> toSecure(beans);
		return securedBeans;
	}

	/**
	 * Lists the entities of type Personnel that have a given login
	 * @param login the login of the Personnel
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Personnel> loadFromLogin(String login) {
		return dao.loadFromLogin(login);
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
	 * @param dao the Personnel Dao
	 */
	public void setDao(PersonnelDao dao) {
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
