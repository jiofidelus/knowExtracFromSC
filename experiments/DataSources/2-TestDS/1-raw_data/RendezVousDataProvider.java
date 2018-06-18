package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.RendezVousProxy;
import org.imogene.epicam.shared.request.RendezVousRequest;
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
 * Data provider to feed the RendezVous entry Table and Selection List
 * @author MEDES-IMPS
 */
public class RendezVousDataProvider
		extends
			ImogBeanDataProvider<RendezVousProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity RendezVous with pagination
	 */
	public RendezVousDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity RendezVous that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public RendezVousDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity RendezVous that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public RendezVousDataProvider(EpicamRequestFactory requestFactory,
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
	public Request<List<RendezVousProxy>> getList(int start, int numRows) {

		RendezVousRequest request = (RendezVousRequest) getContext();
		Request<List<RendezVousProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedRendezVous(start,
									numRows, "modified", false,
									searchCriterions, property);
						else
							result = request.listNonAffectedRendezVousReverse(
									start, numRows, "modified", false,
									searchCriterions, property);
					} else
						result = request.listRendezVous(start, numRows,
								"modified", false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedRendezVous(start,
									numRows, "modified", false, filterCriteria,
									property);
						else
							result = request.listNonAffectedRendezVousReverse(
									start, numRows, "modified", false,
									filterCriteria, property);
					} else
						result = request.listRendezVous(start, numRows,
								"modified", false, filterCriteria);
				}

			} else
				result = request.getRendezVousEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedRendezVous(start,
								numRows, "modified", false, searchCriterions,
								property);
					else
						result = request.listNonAffectedRendezVousReverse(
								start, numRows, "modified", false,
								searchCriterions, property);
				} else
					result = request.listRendezVous(start, numRows, "modified",
							false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedRendezVous(start,
								numRows, "modified", false, property);
					else
						result = request.listNonAffectedRendezVousReverse(
								start, numRows, "modified", false, property);
				} else
					result = request.listRendezVous(start, numRows, "modified",
							false);
			}
		}

		if (isFiltered) {
		}

		else {
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<RendezVousProxy>> getList(String property, int start,
			int numRows, boolean asc) {

		RendezVousRequest request = (RendezVousRequest) getContext();
		Request<List<RendezVousProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listRendezVous(start, numRows, property,
							asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listRendezVous(start, numRows, property,
							asc, filterCriteria);

			} else
				result = request.getRendezVousEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listRendezVous(start, numRows, property, asc,
						searchCriterions);
			else
				result = request.listRendezVous(start, numRows, property, asc);
		}

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		RendezVousRequest request = (RendezVousRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedRendezVous(property,
									searchCriterions);
						else
							return request.countNonAffectedRendezVousReverse(
									property, searchCriterions);
					} else
						return request.countRendezVous(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedRendezVous(property,
									filterCriteria);
						else
							return request.countNonAffectedRendezVousReverse(
									property, filterCriteria);
					} else
						return request.countRendezVous(filterCriteria);
				}

			} else
				return request.countNonAffectedRendezVous("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedRendezVous(property,
								searchCriterions);
					else
						return request.countNonAffectedRendezVousReverse(
								property, searchCriterions);
				} else
					return request.countRendezVous(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedRendezVous(property);
					else
						return request
								.countNonAffectedRendezVousReverse(property);
				} else
					return request.countRendezVous();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.rendezVousRequest();
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

			RendezVousRequest request = (RendezVousRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field DateRendezVous
			try {
				DateUtil.parseDate(text);
				BasicCriteriaProxy dateRendezVousCrit = request
						.create(BasicCriteriaProxy.class);
				dateRendezVousCrit.setField("dateRendezVous");
				dateRendezVousCrit
						.setOperation(CriteriaConstants.DATE_OPERATOR_EQUAL);
				dateRendezVousCrit.setValue(text);
				buffer.append("("
						+ NLS.constants().rendezVous_field_dateRendezVous()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(dateRendezVousCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field Honore
			if (text.toLowerCase().equals("true")
					|| text.toLowerCase().equals("false")) {
				BasicCriteriaProxy honoreCrit = request
						.create(BasicCriteriaProxy.class);
				honoreCrit.setField("honore");
				honoreCrit
						.setOperation(CriteriaConstants.BOOLEAN_OPERATOR_EQUAL);
				honoreCrit.setValue(text);
				buffer.append("(" + NLS.constants().rendezVous_field_honore()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(honoreCrit);
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
