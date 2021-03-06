package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.DetailCommandeIntrantProxy;
import org.imogene.epicam.shared.request.DetailCommandeIntrantRequest;
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
 * Data provider to feed the DetailCommandeIntrant entry Table and Selection List
 * @author MEDES-IMPS
 */
public class DetailCommandeIntrantDataProvider
		extends
			ImogBeanDataProvider<DetailCommandeIntrantProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity DetailCommandeIntrant with pagination
	 */
	public DetailCommandeIntrantDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity DetailCommandeIntrant that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public DetailCommandeIntrantDataProvider(
			EpicamRequestFactory requestFactory, String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity DetailCommandeIntrant that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public DetailCommandeIntrantDataProvider(
			EpicamRequestFactory requestFactory, String pProperty,
			boolean searchInReverse) {
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
	public Request<List<DetailCommandeIntrantProxy>> getList(int start,
			int numRows) {

		DetailCommandeIntrantRequest request = (DetailCommandeIntrantRequest) getContext();
		Request<List<DetailCommandeIntrantProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request
									.listNonAffectedDetailCommandeIntrant(
											start, numRows, "modified", false,
											searchCriterions, property);
						else
							result = request
									.listNonAffectedDetailCommandeIntrantReverse(
											start, numRows, "modified", false,
											searchCriterions, property);
					} else
						result = request.listDetailCommandeIntrant(start,
								numRows, "modified", false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request
									.listNonAffectedDetailCommandeIntrant(
											start, numRows, "modified", false,
											filterCriteria, property);
						else
							result = request
									.listNonAffectedDetailCommandeIntrantReverse(
											start, numRows, "modified", false,
											filterCriteria, property);
					} else
						result = request.listDetailCommandeIntrant(start,
								numRows, "modified", false, filterCriteria);
				}

			} else
				result = request.getDetailCommandeIntrantEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedDetailCommandeIntrant(
								start, numRows, "modified", false,
								searchCriterions, property);
					else
						result = request
								.listNonAffectedDetailCommandeIntrantReverse(
										start, numRows, "modified", false,
										searchCriterions, property);
				} else
					result = request.listDetailCommandeIntrant(start, numRows,
							"modified", false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedDetailCommandeIntrant(
								start, numRows, "modified", false, property);
					else
						result = request
								.listNonAffectedDetailCommandeIntrantReverse(
										start, numRows, "modified", false,
										property);
				} else
					result = request.listDetailCommandeIntrant(start, numRows,
							"modified", false);
			}
		}

		if (isFiltered) {
			result.with("intrant");
		}

		else {
			result.with("intrant");
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<DetailCommandeIntrantProxy>> getList(String property,
			int start, int numRows, boolean asc) {

		DetailCommandeIntrantRequest request = (DetailCommandeIntrantRequest) getContext();
		Request<List<DetailCommandeIntrantProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listDetailCommandeIntrant(start, numRows,
							property, asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listDetailCommandeIntrant(start, numRows,
							property, asc, filterCriteria);

			} else
				result = request.getDetailCommandeIntrantEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listDetailCommandeIntrant(start, numRows,
						property, asc, searchCriterions);
			else
				result = request.listDetailCommandeIntrant(start, numRows,
						property, asc);
		}

		result.with("intrant");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		DetailCommandeIntrantRequest request = (DetailCommandeIntrantRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request
									.countNonAffectedDetailCommandeIntrant(
											property, searchCriterions);
						else
							return request
									.countNonAffectedDetailCommandeIntrantReverse(
											property, searchCriterions);
					} else
						return request
								.countDetailCommandeIntrant(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request
									.countNonAffectedDetailCommandeIntrant(
											property, filterCriteria);
						else
							return request
									.countNonAffectedDetailCommandeIntrantReverse(
											property, filterCriteria);
					} else
						return request
								.countDetailCommandeIntrant(filterCriteria);
				}

			} else
				return request.countNonAffectedDetailCommandeIntrant("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedDetailCommandeIntrant(
								property, searchCriterions);
					else
						return request
								.countNonAffectedDetailCommandeIntrantReverse(
										property, searchCriterions);
				} else
					return request.countDetailCommandeIntrant(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request
								.countNonAffectedDetailCommandeIntrant(property);
					else
						return request
								.countNonAffectedDetailCommandeIntrantReverse(property);
				} else
					return request.countDetailCommandeIntrant();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.detailCommandeIntrantRequest();
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

			DetailCommandeIntrantRequest request = (DetailCommandeIntrantRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field Identifiant
			BasicCriteriaProxy intrant_identifiantCrit = request
					.create(BasicCriteriaProxy.class);
			intrant_identifiantCrit.setField("intrant.identifiant");
			intrant_identifiantCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			intrant_identifiantCrit.setValue(text);
			buffer.append("(" + NLS.constants().intrant_field_identifiant()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(intrant_identifiantCrit);

			// Search field QuantiteRequise
			try {
				Integer.valueOf(text);
				BasicCriteriaProxy quantiteRequiseCrit = request
						.create(BasicCriteriaProxy.class);
				quantiteRequiseCrit.setField("quantiteRequise");
				quantiteRequiseCrit
						.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
				quantiteRequiseCrit.setValue(text);
				buffer.append("("
						+ NLS.constants()
								.detailCommandeIntrant_field_quantiteRequise()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(quantiteRequiseCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field QuantiteEnStock
			try {
				Integer.valueOf(text);
				BasicCriteriaProxy quantiteEnStockCrit = request
						.create(BasicCriteriaProxy.class);
				quantiteEnStockCrit.setField("quantiteEnStock");
				quantiteEnStockCrit
						.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
				quantiteEnStockCrit.setValue(text);
				buffer.append("("
						+ NLS.constants()
								.detailCommandeIntrant_field_quantiteEnStock()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(quantiteEnStockCrit);
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
