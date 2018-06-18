package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.CasIndexProxy;
import org.imogene.epicam.shared.request.CasIndexRequest;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.lib.common.constants.CriteriaConstants;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.table.ImogBeanDataProvider;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogConjunctionProxy;
import org.imogene.web.shared.proxy.criteria.ImogCriterionProxy;
import org.imogene.web.shared.proxy.criteria.ImogDisjunctionProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.client.util.DateUtil;
import org.imogene.web.client.util.LocalSession;
import org.imogene.web.client.util.ProfileUtil;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;

/**
 * Data provider to feed the CasIndex entry Table and Selection List
 * @author MEDES-IMPS
 */
public class CasIndexDataProvider extends ImogBeanDataProvider<CasIndexProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity CasIndex with pagination
	 */
	public CasIndexDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity CasIndex that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public CasIndexDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity CasIndex that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public CasIndexDataProvider(EpicamRequestFactory requestFactory,
			String pProperty, boolean searchInReverse) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		this.searchInReverse = searchInReverse;
		setSearchCriterions(null);
	}

	/**
	 * Sets criterions for which values have to be temporally searched
	 * @param criterions ImogJunctionProxy including the criterions for which the values have to be searched
	 */
	public void setSearchCriterions(ImogJunctionProxy criterions) {
		if (criterions == null) {
			if (ProfileUtil.isAdmin()) {
				filter(getDeletedEntityFilterCriteria(false));
				LocalSession.get().setSearchCriterions(getSearchCriterions(),
						null);
			} else
				searchCriterions = criterions;
		} else
			searchCriterions = criterions;
	}

	/**
	 * Called by Relation Boxes
	 */
	@Override
	public Request<List<CasIndexProxy>> getList(int start, int numRows) {

		CasIndexRequest request = (CasIndexRequest) getContext();
		Request<List<CasIndexProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedCasIndex(start,
									numRows, "modified", false,
									searchCriterions, property);
						else
							result = request.listNonAffectedCasIndexReverse(
									start, numRows, "modified", false,
									searchCriterions, property);
					} else
						result = request.listCasIndex(start, numRows,
								"modified", false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedCasIndex(start,
									numRows, "modified", false, filterCriteria,
									property);
						else
							result = request.listNonAffectedCasIndexReverse(
									start, numRows, "modified", false,
									filterCriteria, property);
					} else
						result = request.listCasIndex(start, numRows,
								"modified", false, filterCriteria);
				}

			} else
				result = request.getCasIndexEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedCasIndex(start,
								numRows, "modified", false, searchCriterions,
								property);
					else
						result = request.listNonAffectedCasIndexReverse(start,
								numRows, "modified", false, searchCriterions,
								property);
				} else
					result = request.listCasIndex(start, numRows, "modified",
							false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedCasIndex(start,
								numRows, "modified", false, property);
					else
						result = request.listNonAffectedCasIndexReverse(start,
								numRows, "modified", false, property);
				} else
					result = request.listCasIndex(start, numRows, "modified",
							false);
			}
		}

		if (isFiltered) {
			result.with("patient");
			result.with("patientLie");
		}

		else {
			result.with("patient");
			result.with("patientLie");
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<CasIndexProxy>> getList(String property, int start,
			int numRows, boolean asc) {

		CasIndexRequest request = (CasIndexRequest) getContext();
		Request<List<CasIndexProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listCasIndex(start, numRows, property,
							asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listCasIndex(start, numRows, property,
							asc, filterCriteria);

			} else
				result = request.getCasIndexEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listCasIndex(start, numRows, property, asc,
						searchCriterions);
			else
				result = request.listCasIndex(start, numRows, property, asc);
		}

		result.with("patient");
		result.with("patientLie");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		CasIndexRequest request = (CasIndexRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedCasIndex(property,
									searchCriterions);
						else
							return request.countNonAffectedCasIndexReverse(
									property, searchCriterions);
					} else
						return request.countCasIndex(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedCasIndex(property,
									filterCriteria);
						else
							return request.countNonAffectedCasIndexReverse(
									property, filterCriteria);
					} else
						return request.countCasIndex(filterCriteria);
				}

			} else
				return request.countNonAffectedCasIndex("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedCasIndex(property,
								searchCriterions);
					else
						return request.countNonAffectedCasIndexReverse(
								property, searchCriterions);
				} else
					return request.countCasIndex(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedCasIndex(property);
					else
						return request
								.countNonAffectedCasIndexReverse(property);
				} else
					return request.countCasIndex();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.casIndexRequest();
	}

	@Override
	public String fullTextSearch(String text) {

		boolean fullTextSearch = false;
		StringBuffer buffer = new StringBuffer(BaseNLS.constants()
				.label_filtered() + " ");

		if (text == null || (text != null && text.equals(""))) {
			setSearchCriterions(null);
		} else {

			String locale = NLS.constants().locale();

			CasIndexRequest request = (CasIndexRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field Identifiant
			BasicCriteriaProxy patient_identifiantCrit = request
					.create(BasicCriteriaProxy.class);
			patient_identifiantCrit.setField("patient.identifiant");
			patient_identifiantCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			patient_identifiantCrit.setValue(text);
			buffer.append("(" + NLS.constants().patient_field_identifiant()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(patient_identifiantCrit);
			// Search field Nom
			BasicCriteriaProxy patient_nomCrit = request
					.create(BasicCriteriaProxy.class);
			patient_nomCrit.setField("patient.nom");
			patient_nomCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			patient_nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().patient_field_nom() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(patient_nomCrit);

			// Search field TypeRelation
			BasicCriteriaProxy typeRelationCrit = request
					.create(BasicCriteriaProxy.class);
			typeRelationCrit.setField("typeRelation");
			typeRelationCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			typeRelationCrit.setValue(text);
			buffer.append("(" + NLS.constants().casIndex_field_typeRelation()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(typeRelationCrit);

			// Search field Identifiant
			BasicCriteriaProxy patientLie_identifiantCrit = request
					.create(BasicCriteriaProxy.class);
			patientLie_identifiantCrit.setField("patientLie.identifiant");
			patientLie_identifiantCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			patientLie_identifiantCrit.setValue(text);
			buffer.append("(" + NLS.constants().patient_field_identifiant()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(patientLie_identifiantCrit);
			// Search field Nom
			BasicCriteriaProxy patientLie_nomCrit = request
					.create(BasicCriteriaProxy.class);
			patientLie_nomCrit.setField("patientLie.nom");
			patientLie_nomCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			patientLie_nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().patient_field_nom() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(patientLie_nomCrit);

			disJunction.setCriterions(criterionList);
			criterias.add(disJunction);
			fullTextSearch = true;

			if (ProfileUtil.isAdmin()) {
				BasicCriteriaProxy isDeletedCrit = request
						.create(BasicCriteriaProxy.class);
				isDeletedCrit.setField("deleted");
				isDeletedCrit.setOperation(CriteriaConstants.OPERATOR_ISNULL);
				isDeletedCrit.setValue(text);
				criterias.add(isDeletedCrit);
			}
			junction.setCriterions(criterias);

			// add FilterCriteria if exists
			if (isFiltered && filterCriteria != null)
				setSearchCriterions(mergeFilterCriteriaAndFullTextSearchCriterion(
						request, junction));
			else
				setSearchCriterions(junction);

		}
		if (fullTextSearch) {
			String message = buffer.toString();
			int lastSymbolIndex = message.lastIndexOf(SYMBOL_OR);
			return message.substring(0, lastSymbolIndex);
		} else
			return null;
	}

}
