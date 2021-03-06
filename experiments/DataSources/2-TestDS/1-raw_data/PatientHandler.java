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
 * A data handler for the Patient beans 
 * @author Medes-IMPS
 */
public class PatientHandler {

	private PatientDao dao;

	private ImogBeanFilter filter;
	private HandlerHelper handlerHelper;

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public Patient findById(String entityId) {
		return dao.load(entityId);
	}

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public Patient getById(String entityId) {
		return dao.getById(entityId);
	}

	/**
	 * Saves or updates the entity
	 * @param entity the entity to be saved or updated
	 * @param isNew true if it is a new entity added for the first time.
	 */
	@Transactional
	public void save(Patient entity, boolean isNew) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();

		if (entity != null) {

			handlerHelper.prepare(entity);
			if (entity.getDeleted() != null)
				entity.setDeleted(null);

			// manage related casIndex
			List<CasIndex> patdb = entity.getCasIndex();
			if (patdb != null) {
				for (CasIndex patdbItem : patdb) {
					handlerHelper.prepare(patdbItem);
				}
			}
			// manage related examensBiologiques
			List<ExamenBiologique> patea = entity.getExamensBiologiques();
			if (patea != null) {
				for (ExamenBiologique pateaItem : patea) {
					handlerHelper.prepare(pateaItem);
				}
			}
			// manage related serologies
			List<ExamenSerologie> pateb = entity.getSerologies();
			if (pateb != null) {
				for (ExamenSerologie patebItem : pateb) {
					handlerHelper.prepare(patebItem);
				}
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
	 * Lists the entities of type Patient
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of patient
	 */
	@Transactional(readOnly = true)
	public List<Patient> listPatient(String sortProperty, boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<Patient> beans = dao.load(sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type Patient
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of patient
	 */
	@Transactional(readOnly = true)
	public List<Patient> listPatient(String sortProperty, boolean sortOrder,
			ImogJunction junction) {

		List<Patient> beans = dao.load(sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type Patient
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of patient
	 */
	@Transactional(readOnly = true)
	public List<Patient> listPatient(int i, int j, String sortProperty,
			boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<Patient> beans = dao.load(i, j, sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type Patient
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of patient
	 */
	@Transactional(readOnly = true)
	public List<Patient> listPatient(int i, int j, String sortProperty,
			boolean sortOrder, ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<Patient> beans = dao.load(i, j, sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type Patient
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of patient
	 */
	@Transactional(readOnly = true)
	public List<Patient> listPatient(int i, int j, String sortProperty,
			boolean sortOrder, List<BasicCriteria> criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		ImogJunction junctionForCrit = new ImogConjunction();
		if (criterions != null) {
			for (BasicCriteria crit : criterions)
				junctionForCrit.add(crit);
		}
		junction.add(junctionForCrit);

		List<Patient> beans = dao.load(i, j, sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type Patient	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterion request criteria	 
	 * @param property the property which is not affected
	 * @return list of patient
	 */
	@Transactional(readOnly = true)
	public List<Patient> listNonAffectedPatient(int i, int j,
			String sortProperty, boolean sortOrder, ImogJunction criterions,
			String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<Patient> beans = dao.loadNonAffected(i, j, sortProperty,
				sortOrder, property, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type Patient	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected
	 * @return list of patient
	 */
	@Transactional(readOnly = true)
	public List<Patient> listNonAffectedPatient(int i, int j,
			String sortProperty, boolean sortOrder, String property) {
		return listNonAffectedPatient(i, j, sortProperty, sortOrder, null,
				property);
	}

	/**
	 * Used when Patient is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of Patient non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param ImogJunction request criteria
	 * @param property the property which is not affected	 
	 * @return list of patient
	 */
	@Transactional(readOnly = true)
	public List<Patient> listNonAffectedPatientReverse(int i, int j,
			String sortProperty, boolean sortOrder, ImogJunction criterions,
			String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<Patient> beans = dao.loadNonAffectedReverse(i, j, sortProperty,
				sortOrder, property, junction);

		return beans;
	}

	/**
	 * Used when Patient is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of Patient non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected	 
	 * @return list of patient
	 */
	@Transactional(readOnly = true)
	public List<Patient> listNonAffectedPatientReverse(int i, int j,
			String sortProperty, boolean sortOrder, String property) {
		return listNonAffectedPatientReverse(i, j, sortProperty, sortOrder,
				null, property);
	}

	/**
	 * Gets an empty list of Patient	
	 * @return an empty list of Patient
	 */
	@Transactional(readOnly = true)
	public List<Patient> getPatientEmptyList() {
		return new ArrayList<Patient>();
	}

	/**
	 * Counts the number of patient in the database
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countPatient() {
		return countPatient(null);
	}

	/**
	 * Counts the number of patient in the database, 
	 * that match the criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countPatient(ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.count(junction);
	}

	/**
	 * Counts the number of non affected patient entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedPatient(String property, ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.countNonAffected(property, junction);
	}

	/**
	 * Counts the number of non affected patient entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedPatient(String property) {
		return countNonAffectedPatient(property, null);
	}

	/**
	 * Counts the number of non affected patient entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedPatientReverse(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);
		return dao.countNonAffectedReverse(property, junction);
	}

	/**
	 * Counts the number of non affected patient entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedPatientReverse(String property) {
		return countNonAffectedPatientReverse(property, null);
	}

	/**
	 * Deletes a group of entities identified by their IDs
	 * @param ids Entities to delete IDs
	 * @return The list of deleted entities IDs
	 */
	@Transactional
	public void delete(Set<Patient> entities) {
		if (entities != null) {
			for (Patient entity : entities)
				delete(entity);
		}
	}

	/**
	 * Removes the specified entity from the database 
	 * @param entity The entity to be deleted
	 */
	@Transactional
	public void delete(Patient entity) {

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
	 * Lists the entities of type Patient for the CSV export  
	 */
	@Transactional(readOnly = true)
	public List<Patient> listForCsv(String sortProperty, boolean sortOrder,
			String identifiant, String nom, String dateNaissanceBefore,
			String dateNaissanceAfter, String age, String sexe,
			String profession, String telephoneUn, String telephoneDeux,
			String pacNom, String pacTelephoneUn) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		if (identifiant != null && !identifiant.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("identifiant");
			criteria.setValue(identifiant);
			junction.add(criteria);
		}
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
		if (age != null && !age.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
			criteria.setField("age");
			criteria.setValue(age);
			junction.add(criteria);
		}
		if (sexe != null && !sexe.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("sexe");
			criteria.setValue(sexe);
			junction.add(criteria);
		}
		if (profession != null && !profession.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("profession");
			criteria.setValue(profession);
			junction.add(criteria);
		}
		if (telephoneUn != null && !telephoneUn.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("telephoneUn");
			criteria.setValue(telephoneUn);
			junction.add(criteria);
		}
		if (telephoneDeux != null && !telephoneDeux.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("telephoneDeux");
			criteria.setValue(telephoneDeux);
			junction.add(criteria);
		}
		if (pacNom != null && !pacNom.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("pacNom");
			criteria.setValue(pacNom);
			junction.add(criteria);
		}
		if (pacTelephoneUn != null && !pacTelephoneUn.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("pacTelephoneUn");
			criteria.setValue(pacTelephoneUn);
			junction.add(criteria);
		}

		List<Patient> beans = dao.load(sortProperty, sortOrder, junction);
		List<Patient> securedBeans = filter.<Patient> toSecure(beans);
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
	 * @param dao the Patient Dao
	 */
	public void setDao(PatientDao dao) {
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
	public Long countPatientFilteredByCdt(ImogJunction criterions, String cdtId) {
		return dao.countPatientFilteredByCdt(criterions, cdtId);
	}

	@Transactional(readOnly = true)
	public List<Patient> listPatientFilteredByCdt(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunction criterions,
			String cdtId) {
		return dao.listPatientFilteredByCdt(first, max, sortProperty,
				sortOrder, criterions, cdtId);
	}

}
