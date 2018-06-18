package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.ExamenBiologiqueProxy;
import org.imogene.epicam.shared.request.ExamenBiologiqueRequest;
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
 * Data provider to feed the ExamenBiologique entry Table and Selection List
 * @author MEDES-IMPS
 */
public class ExamenBiologiqueDataProvider
		extends
			ImogBeanDataProvider<ExamenBiologiqueProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity ExamenBiologique with pagination
	 */
	public ExamenBiologiqueDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity ExamenBiologique that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public ExamenBiologiqueDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity ExamenBiologique that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public ExamenBiologiqueDataProvider(EpicamRequestFactory requestFactory,
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
	public Request<List<ExamenBiologiqueProxy>> getList(int start, int numRows) {

		ExamenBiologiqueRequest request = (ExamenBiologiqueRequest) getContext();
		Request<List<ExamenBiologiqueProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedExamenBiologique(
									start, numRows, "modified", false,
									searchCriterions, property);
						else
							result = request
									.listNonAffectedExamenBiologiqueReverse(
											start, numRows, "modified", false,
											searchCriterions, property);
					} else
						result = request.listExamenBiologique(start, numRows,
								"modified", false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedExamenBiologique(
									start, numRows, "modified", false,
									filterCriteria, property);
						else
							result = request
									.listNonAffectedExamenBiologiqueReverse(
											start, numRows, "modified", false,
											filterCriteria, property);
					} else
						result = request.listExamenBiologique(start, numRows,
								"modified", false, filterCriteria);
				}

			} else
				result = request.getExamenBiologiqueEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedExamenBiologique(start,
								numRows, "modified", false, searchCriterions,
								property);
					else
						result = request
								.listNonAffectedExamenBiologiqueReverse(start,
										numRows, "modified", false,
										searchCriterions, property);
				} else
					result = request.listExamenBiologique(start, numRows,
							"modified", false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedExamenBiologique(start,
								numRows, "modified", false, property);
					else
						result = request
								.listNonAffectedExamenBiologiqueReverse(start,
										numRows, "modified", false, property);
				} else
					result = request.listExamenBiologique(start, numRows,
							"modified", false);
			}
		}

		if (isFiltered) {
			result.with("patient");
		}

		else {
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<ExamenBiologiqueProxy>> getList(String property,
			int start, int numRows, boolean asc) {

		ExamenBiologiqueRequest request = (ExamenBiologiqueRequest) getContext();
		Request<List<ExamenBiologiqueProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listExamenBiologique(start, numRows,
							property, asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listExamenBiologique(start, numRows,
							property, asc, filterCriteria);

			} else
				result = request.getExamenBiologiqueEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listExamenBiologique(start, numRows, property,
						asc, searchCriterions);
			else
				result = request.listExamenBiologique(start, numRows, property,
						asc);
		}

		result.with("patient");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		ExamenBiologiqueRequest request = (ExamenBiologiqueRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedExamenBiologique(
									property, searchCriterions);
						else
							return request
									.countNonAffectedExamenBiologiqueReverse(
											property, searchCriterions);
					} else
						return request.countExamenBiologique(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedExamenBiologique(
									property, filterCriteria);
						else
							return request
									.countNonAffectedExamenBiologiqueReverse(
											property, filterCriteria);
					} else
						return request.countExamenBiologique(filterCriteria);
				}

			} else
				return request.countNonAffectedExamenBiologique("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedExamenBiologique(
								property, searchCriterions);
					else
						return request.countNonAffectedExamenBiologiqueReverse(
								property, searchCriterions);
				} else
					return request.countExamenBiologique(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request
								.countNonAffectedExamenBiologique(property);
					else
						return request
								.countNonAffectedExamenBiologiqueReverse(property);
				} else
					return request.countExamenBiologique();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.examenBiologiqueRequest();
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

			ExamenBiologiqueRequest request = (ExamenBiologiqueRequest) getContext();
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

			// Search field Date
			try {
				DateUtil.parseDate(text);
				BasicCriteriaProxy dateCrit = request
						.create(BasicCriteriaProxy.class);
				dateCrit.setField("date");
				dateCrit.setOperation(CriteriaConstants.DATE_OPERATOR_EQUAL);
				dateCrit.setValue(text);
				buffer.append("("
						+ NLS.constants().examenBiologique_field_date() + ": "
						+ text + ") " + SYMBOL_OR + " ");
				criterionList.add(dateCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field Poids
			try {
				Float.valueOf(text);
				BasicCriteriaProxy poidsCrit = request
						.create(BasicCriteriaProxy.class);
				poidsCrit.setField("poids");
				poidsCrit.setOperation(CriteriaConstants.FLOAT_OPERATOR_EQUAL);
				poidsCrit.setValue(text);
				buffer.append("("
						+ NLS.constants().examenBiologique_field_poids() + ": "
						+ text + ") " + SYMBOL_OR + " ");
				criterionList.add(poidsCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

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
