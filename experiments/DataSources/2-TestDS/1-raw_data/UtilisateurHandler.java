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
 * A data handler for the Utilisateur beans 
 * @author Medes-IMPS
 */
public class UtilisateurHandler {

	private UtilisateurDao dao;

	private ImogBeanFilter filter;
	private HandlerHelper handlerHelper;

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public Utilisateur findById(String entityId) {
		return dao.load(entityId);
	}

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public Utilisateur getById(String entityId) {
		return dao.getById(entityId);
	}

	/**
	 * Saves or updates the entity
	 * @param entity the entity to be saved or updated
	 * @param isNew true if it is a new entity added for the first time.
	 */
	@Transactional
	public void save(Utilisateur entity, boolean isNew) {
		save(entity, isNew, false);
	}

	/**
	 * Saves or updates the entity
	 * @param entity the entity to be saved or updated
	 * @param isNew true if it is a new entity added for the first time.
	 * @param passwordChanged true if the password has changed
	 */
	@Transactional
	public void save(Utilisateur entity, boolean isNew, boolean passwordChanged) {

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
	 * Lists the entities of type Utilisateur
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of utilisateur
	 */
	@Transactional(readOnly = true)
	public List<Utilisateur> listUtilisateur(String sortProperty,
			boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<Utilisateur> beans = dao.load(sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type Utilisateur
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of utilisateur
	 */
	@Transactional(readOnly = true)
	public List<Utilisateur> listUtilisateur(String sortProperty,
			boolean sortOrder, ImogJunction junction) {

		List<Utilisateur> beans = dao.load(sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type Utilisateur
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of utilisateur
	 */
	@Transactional(readOnly = true)
	public List<Utilisateur> listUtilisateur(int i, int j, String sortProperty,
			boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<Utilisateur> beans = dao.load(i, j, sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type Utilisateur
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of utilisateur
	 */
	@Transactional(readOnly = true)
	public List<Utilisateur> listUtilisateur(int i, int j, String sortProperty,
			boolean sortOrder, ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<Utilisateur> beans = dao.load(i, j, sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type Utilisateur
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of utilisateur
	 */
	@Transactional(readOnly = true)
	public List<Utilisateur> listUtilisateur(int i, int j, String sortProperty,
			boolean sortOrder, List<BasicCriteria> criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		ImogJunction junctionForCrit = new ImogConjunction();
		if (criterions != null) {
			for (BasicCriteria crit : criterions)
				junctionForCrit.add(crit);
		}
		junction.add(junctionForCrit);

		List<Utilisateur> beans = dao.load(i, j, sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type Utilisateur	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterion request criteria	 
	 * @param property the property which is not affected
	 * @return list of utilisateur
	 */
	@Transactional(readOnly = true)
	public List<Utilisateur> listNonAffectedUtilisateur(int i, int j,
			String sortProperty, boolean sortOrder, ImogJunction criterions,
			String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<Utilisateur> beans = dao.loadNonAffected(i, j, sortProperty,
				sortOrder, property, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type Utilisateur	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected
	 * @return list of utilisateur
	 */
	@Transactional(readOnly = true)
	public List<Utilisateur> listNonAffectedUtilisateur(int i, int j,
			String sortProperty, boolean sortOrder, String property) {
		return listNonAffectedUtilisateur(i, j, sortProperty, sortOrder, null,
				property);
	}

	/**
	 * Used when Utilisateur is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of Utilisateur non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param ImogJunction request criteria
	 * @param property the property which is not affected	 
	 * @return list of utilisateur
	 */
	@Transactional(readOnly = true)
	public List<Utilisateur> listNonAffectedUtilisateurReverse(int i, int j,
			String sortProperty, boolean sortOrder, ImogJunction criterions,
			String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<Utilisateur> beans = dao.loadNonAffectedReverse(i, j,
				sortProperty, sortOrder, property, junction);

		return beans;
	}

	/**
	 * Used when Utilisateur is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of Utilisateur non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected	 
	 * @return list of utilisateur
	 */
	@Transactional(readOnly = true)
	public List<Utilisateur> listNonAffectedUtilisateurReverse(int i, int j,
			String sortProperty, boolean sortOrder, String property) {
		return listNonAffectedUtilisateurReverse(i, j, sortProperty, sortOrder,
				null, property);
	}

	/**
	 * Gets an empty list of Utilisateur	
	 * @return an empty list of Utilisateur
	 */
	@Transactional(readOnly = true)
	public List<Utilisateur> getUtilisateurEmptyList() {
		return new ArrayList<Utilisateur>();
	}

	/**
	 * Counts the number of utilisateur in the database
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countUtilisateur() {
		return countUtilisateur(null);
	}

	/**
	 * Counts the number of utilisateur in the database, 
	 * that match the criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countUtilisateur(ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.count(junction);
	}

	/**
	 * Counts the number of non affected utilisateur entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedUtilisateur(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.countNonAffected(property, junction);
	}

	/**
	 * Counts the number of non affected utilisateur entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedUtilisateur(String property) {
		return countNonAffectedUtilisateur(property, null);
	}

	/**
	 * Counts the number of non affected utilisateur entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedUtilisateurReverse(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);
		return dao.countNonAffectedReverse(property, junction);
	}

	/**
	 * Counts the number of non affected utilisateur entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedUtilisateurReverse(String property) {
		return countNonAffectedUtilisateurReverse(property, null);
	}

	/**
	 * Deletes a group of entities identified by their IDs
	 * @param ids Entities to delete IDs
	 * @return The list of deleted entities IDs
	 */
	@Transactional
	public void delete(Set<Utilisateur> entities) {
		if (entities != null) {
			for (Utilisateur entity : entities)
				delete(entity);
		}
	}

	/**
	 * Removes the specified entity from the database 
	 * @param entity The entity to be deleted
	 */
	@Transactional
	public void delete(Utilisateur entity) {

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
	 * Lists the entities of type Utilisateur for the CSV export  
	 */
	@Transactional(readOnly = true)
	public List<Utilisateur> listForCsv(String sortProperty, boolean sortOrder,
			String nom, String profession, String dateNaissanceBefore,
			String dateNaissanceAfter) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		if (nom != null && !nom.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("nom");
			criteria.setValue(nom);
			junction.add(criteria);
		}
		if (profession != null && !profession.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("profession");
			criteria.setValue(profession);
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

		List<Utilisateur> beans = dao.load(sortProperty, sortOrder, junction);
		List<Utilisateur> securedBeans = filter.<Utilisateur> toSecure(beans);
		return securedBeans;
	}

	/**
	 * Lists the entities of type Utilisateur that have a given login
	 * @param login the login of the Utilisateur
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Utilisateur> loadFromLogin(String login) {
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
	 * @param dao the Utilisateur Dao
	 */
	public void setDao(UtilisateurDao dao) {
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
