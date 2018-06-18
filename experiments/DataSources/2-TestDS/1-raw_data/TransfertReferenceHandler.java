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
 * A data handler for the TransfertReference beans 
 * @author Medes-IMPS
 */
public class TransfertReferenceHandler {

	private TransfertReferenceDao dao;

	private PatientDao patientDao;

	private ImogBeanFilter filter;
	private HandlerHelper handlerHelper;

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public TransfertReference findById(String entityId) {
		return dao.load(entityId);
	}

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public TransfertReference getById(String entityId) {
		return dao.getById(entityId);
	}

	/**
	 * Saves or updates the entity
	 * @param entity the entity to be saved or updated
	 * @param isNew true if it is a new entity added for the first time.
	 */
	@Transactional
	public void save(TransfertReference entity, boolean isNew) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();

		if (entity != null) {

			handlerHelper.prepare(entity);
			if (entity.getDeleted() != null)
				entity.setDeleted(null);

			dao.saveOrUpdate(entity, isNew);

			// update CDT list for patient (remove CDT depart and add CDT arrivee)
			Patient patient = entity.getPatient();
			if (patient != null) {
				List<CentreDiagTrait> centresPatient = patient.getCentres();

				CentreDiagTrait centreArrivee = entity.getCDTArrivee();
				CentreDiagTrait centreDepart = entity.getCDTDepart();

				boolean updatePatient = false;
				if (centreDepart != null
						&& centresPatient.contains(centreDepart)) {
					if (centresPatient.remove(centreDepart))
						updatePatient = true;
				}

				if (centreArrivee != null
						&& !centresPatient.contains(centreArrivee)) {
					if (centresPatient.add(centreArrivee))
						updatePatient = true;
				}

				if (updatePatient)
					patientDao.saveOrUpdate(patient, false);
			}
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
	 * Lists the entities of type TransfertReference
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of transfertReference
	 */
	@Transactional(readOnly = true)
	public List<TransfertReference> listTransfertReference(String sortProperty,
			boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<TransfertReference> beans = dao.load(sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type TransfertReference
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of transfertReference
	 */
	@Transactional(readOnly = true)
	public List<TransfertReference> listTransfertReference(String sortProperty,
			boolean sortOrder, ImogJunction junction) {

		List<TransfertReference> beans = dao.load(sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type TransfertReference
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of transfertReference
	 */
	@Transactional(readOnly = true)
	public List<TransfertReference> listTransfertReference(int i, int j,
			String sortProperty, boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<TransfertReference> beans = dao.load(i, j, sortProperty,
				sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type TransfertReference
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of transfertReference
	 */
	@Transactional(readOnly = true)
	public List<TransfertReference> listTransfertReference(int i, int j,
			String sortProperty, boolean sortOrder, ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<TransfertReference> beans = dao.load(i, j, sortProperty,
				sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type TransfertReference
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of transfertReference
	 */
	@Transactional(readOnly = true)
	public List<TransfertReference> listTransfertReference(int i, int j,
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

		List<TransfertReference> beans = dao.load(i, j, sortProperty,
				sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type TransfertReference	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterion request criteria	 
	 * @param property the property which is not affected
	 * @return list of transfertReference
	 */
	@Transactional(readOnly = true)
	public List<TransfertReference> listNonAffectedTransfertReference(int i,
			int j, String sortProperty, boolean sortOrder,
			ImogJunction criterions, String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<TransfertReference> beans = dao.loadNonAffected(i, j,
				sortProperty, sortOrder, property, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type TransfertReference	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected
	 * @return list of transfertReference
	 */
	@Transactional(readOnly = true)
	public List<TransfertReference> listNonAffectedTransfertReference(int i,
			int j, String sortProperty, boolean sortOrder, String property) {
		return listNonAffectedTransfertReference(i, j, sortProperty, sortOrder,
				null, property);
	}

	/**
	 * Used when TransfertReference is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of TransfertReference non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param ImogJunction request criteria
	 * @param property the property which is not affected	 
	 * @return list of transfertReference
	 */
	@Transactional(readOnly = true)
	public List<TransfertReference> listNonAffectedTransfertReferenceReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunction criterions, String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<TransfertReference> beans = dao.loadNonAffectedReverse(i, j,
				sortProperty, sortOrder, property, junction);

		return beans;
	}

	/**
	 * Used when TransfertReference is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of TransfertReference non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected	 
	 * @return list of transfertReference
	 */
	@Transactional(readOnly = true)
	public List<TransfertReference> listNonAffectedTransfertReferenceReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property) {
		return listNonAffectedTransfertReferenceReverse(i, j, sortProperty,
				sortOrder, null, property);
	}

	/**
	 * Gets an empty list of TransfertReference	
	 * @return an empty list of TransfertReference
	 */
	@Transactional(readOnly = true)
	public List<TransfertReference> getTransfertReferenceEmptyList() {
		return new ArrayList<TransfertReference>();
	}

	/**
	 * Counts the number of transfertReference in the database
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countTransfertReference() {
		return countTransfertReference(null);
	}

	/**
	 * Counts the number of transfertReference in the database, 
	 * that match the criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countTransfertReference(ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.count(junction);
	}

	/**
	 * Counts the number of non affected transfertReference entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedTransfertReference(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.countNonAffected(property, junction);
	}

	/**
	 * Counts the number of non affected transfertReference entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedTransfertReference(String property) {
		return countNonAffectedTransfertReference(property, null);
	}

	/**
	 * Counts the number of non affected transfertReference entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedTransfertReferenceReverse(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);
		return dao.countNonAffectedReverse(property, junction);
	}

	/**
	 * Counts the number of non affected transfertReference entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedTransfertReferenceReverse(String property) {
		return countNonAffectedTransfertReferenceReverse(property, null);
	}

	/**
	 * Deletes a group of entities identified by their IDs
	 * @param ids Entities to delete IDs
	 * @return The list of deleted entities IDs
	 */
	@Transactional
	public void delete(Set<TransfertReference> entities) {
		if (entities != null) {
			for (TransfertReference entity : entities)
				delete(entity);
		}
	}

	/**
	 * Removes the specified entity from the database 
	 * @param entity The entity to be deleted
	 */
	@Transactional
	public void delete(TransfertReference entity) {

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
	 * Lists the entities of type TransfertReference for the CSV export  
	 */
	@Transactional(readOnly = true)
	public List<TransfertReference> listForCsv(String sortProperty,
			boolean sortOrder, String patient_identifiant, String patient_nom,
			String nature, String cDTDepart_nom, String dateDepartBefore,
			String dateDepartAfter, String cDTArrivee_nom,
			String dateArriveeBefore, String dateArriveeAfter) {

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
		if (nature != null && !nature.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("nature");
			criteria.setValue(nature);
			junction.add(criteria);
		}
		if (cDTDepart_nom != null && !cDTDepart_nom.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("cDTDepart.nom");
			criteria.setValue(cDTDepart_nom);
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
		if (cDTArrivee_nom != null && !cDTArrivee_nom.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("cDTArrivee.nom");
			criteria.setValue(cDTArrivee_nom);
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

		List<TransfertReference> beans = dao.load(sortProperty, sortOrder,
				junction);
		List<TransfertReference> securedBeans = filter
				.<TransfertReference> toSecure(beans);
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
	 * @param dao the TransfertReference Dao
	 */
	public void setDao(TransfertReferenceDao dao) {
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

	public void setPatientDao(PatientDao patientDao) {
		this.patientDao = patientDao;
	}

}
