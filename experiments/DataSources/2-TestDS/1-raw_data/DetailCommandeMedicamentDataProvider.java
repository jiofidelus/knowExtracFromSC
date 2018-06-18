package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.DetailCommandeMedicamentProxy;
import org.imogene.epicam.shared.request.DetailCommandeMedicamentRequest;
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
 * Data provider to feed the DetailCommandeMedicament entry Table and Selection List
 * @author MEDES-IMPS
 */
public class DetailCommandeMedicamentDataProvider
		extends
			ImogBeanDataProvider<DetailCommandeMedicamentProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity DetailCommandeMedicament with pagination
	 */
	public DetailCommandeMedicamentDataProvider(
			EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity DetailCommandeMedicament that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public DetailCommandeMedicamentDataProvider(
			EpicamRequestFactory requestFactory, String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity DetailCommandeMedicament that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public DetailCommandeMedicamentDataProvider(
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
	public Request<List<DetailCommandeMedicamentProxy>> getList(int start,
			int numRows) {

		DetailCommandeMedicamentRequest request = (DetailCommandeMedicamentRequest) getContext();
		Request<List<DetailCommandeMedicamentProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request
									.listNonAffectedDetailCommandeMedicament(
											start, numRows, "modified", false,
											searchCriterions, property);
						else
							result = request
									.listNonAffectedDetailCommandeMedicamentReverse(
											start, numRows, "modified", false,
											searchCriterions, property);
					} else
						result = request.listDetailCommandeMedicament(start,
								numRows, "modified", false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request
									.listNonAffectedDetailCommandeMedicament(
											start, numRows, "modified", false,
											filterCriteria, property);
						else
							result = request
									.listNonAffectedDetailCommandeMedicamentReverse(
											start, numRows, "modified", false,
											filterCriteria, property);
					} else
						result = request.listDetailCommandeMedicament(start,
								numRows, "modified", false, filterCriteria);
				}

			} else
				result = request.getDetailCommandeMedicamentEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request
								.listNonAffectedDetailCommandeMedicament(start,
										numRows, "modified", false,
										searchCriterions, property);
					else
						result = request
								.listNonAffectedDetailCommandeMedicamentReverse(
										start, numRows, "modified", false,
										searchCriterions, property);
				} else
					result = request.listDetailCommandeMedicament(start,
							numRows, "modified", false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request
								.listNonAffectedDetailCommandeMedicament(start,
										numRows, "modified", false, property);
					else
						result = request
								.listNonAffectedDetailCommandeMedicamentReverse(
										start, numRows, "modified", false,
										property);
				} else
					result = request.listDetailCommandeMedicament(start,
							numRows, "modified", false);
			}
		}

		if (isFiltered) {
			result.with("medicament");
		}

		else {
			result.with("medicament");
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<DetailCommandeMedicamentProxy>> getList(
			String property, int start, int numRows, boolean asc) {

		DetailCommandeMedicamentRequest request = (DetailCommandeMedicamentRequest) getContext();
		Request<List<DetailCommandeMedicamentProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listDetailCommandeMedicament(start,
							numRows, property, asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listDetailCommandeMedicament(start,
							numRows, property, asc, filterCriteria);

			} else
				result = request.getDetailCommandeMedicamentEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listDetailCommandeMedicament(start, numRows,
						property, asc, searchCriterions);
			else
				result = request.listDetailCommandeMedicament(start, numRows,
						property, asc);
		}

		result.with("medicament");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		DetailCommandeMedicamentRequest request = (DetailCommandeMedicamentRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request
									.countNonAffectedDetailCommandeMedicament(
											property, searchCriterions);
						else
							return request
									.countNonAffectedDetailCommandeMedicamentReverse(
											property, searchCriterions);
					} else
						return request
								.countDetailCommandeMedicament(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request
									.countNonAffectedDetailCommandeMedicament(
											property, filterCriteria);
						else
							return request
									.countNonAffectedDetailCommandeMedicamentReverse(
											property, filterCriteria);
					} else
						return request
								.countDetailCommandeMedicament(filterCriteria);
				}

			} else
				return request.countNonAffectedDetailCommandeMedicament("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request
								.countNonAffectedDetailCommandeMedicament(
										property, searchCriterions);
					else
						return request
								.countNonAffectedDetailCommandeMedicamentReverse(
										property, searchCriterions);
				} else
					return request
							.countDetailCommandeMedicament(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request
								.countNonAffectedDetailCommandeMedicament(property);
					else
						return request
								.countNonAffectedDetailCommandeMedicamentReverse(property);
				} else
					return request.countDetailCommandeMedicament();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.detailCommandeMedicamentRequest();
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

			DetailCommandeMedicamentRequest request = (DetailCommandeMedicamentRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

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
								.detailCommandeMedicament_field_quantiteRequise()
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
								.detailCommandeMedicament_field_quantiteEnStock()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(quantiteEnStockCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field Designation
			BasicCriteriaProxy medicament_designationCrit = request
					.create(BasicCriteriaProxy.class);
			medicament_designationCrit.setField("medicament.designation");
			medicament_designationCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			medicament_designationCrit.setValue(text);
			buffer.append("(" + NLS.constants().medicament_field_designation()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(medicament_designationCrit);

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
