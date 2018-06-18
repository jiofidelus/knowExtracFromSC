package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.ReceptionProxy;
import org.imogene.epicam.shared.request.ReceptionRequest;
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
 * Data provider to feed the Reception entry Table and Selection List
 * @author MEDES-IMPS
 */
public class ReceptionDataProvider extends ImogBeanDataProvider<ReceptionProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity Reception with pagination
	 */
	public ReceptionDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity Reception that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public ReceptionDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity Reception that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public ReceptionDataProvider(EpicamRequestFactory requestFactory,
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
	public Request<List<ReceptionProxy>> getList(int start, int numRows) {

		ReceptionRequest request = (ReceptionRequest) getContext();
		Request<List<ReceptionProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedReception(start,
									numRows, "dateReception", false,
									searchCriterions, property);
						else
							result = request.listNonAffectedReceptionReverse(
									start, numRows, "dateReception", false,
									searchCriterions, property);
					} else
						result = request.listReception(start, numRows,
								"dateReception", false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedReception(start,
									numRows, "dateReception", false,
									filterCriteria, property);
						else
							result = request.listNonAffectedReceptionReverse(
									start, numRows, "dateReception", false,
									filterCriteria, property);
					} else
						result = request.listReception(start, numRows,
								"dateReception", false, filterCriteria);
				}

			} else
				result = request.getReceptionEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedReception(start,
								numRows, "dateReception", false,
								searchCriterions, property);
					else
						result = request.listNonAffectedReceptionReverse(start,
								numRows, "dateReception", false,
								searchCriterions, property);
				} else
					result = request.listReception(start, numRows,
							"dateReception", false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedReception(start,
								numRows, "dateReception", false, property);
					else
						result = request.listNonAffectedReceptionReverse(start,
								numRows, "dateReception", false, property);
				} else
					result = request.listReception(start, numRows,
							"dateReception", false);
			}
		}

		if (isFiltered) {
			result.with("CDT");
			result.with("commande");
			result.with("commande.CDT");
		}

		else {
			result.with("CDT");
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<ReceptionProxy>> getList(String property, int start,
			int numRows, boolean asc) {

		ReceptionRequest request = (ReceptionRequest) getContext();
		Request<List<ReceptionProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listReception(start, numRows, property,
							asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listReception(start, numRows, property,
							asc, filterCriteria);

			} else
				result = request.getReceptionEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listReception(start, numRows, property, asc,
						searchCriterions);
			else
				result = request.listReception(start, numRows, property, asc);
		}

		result.with("CDT");
		result.with("commande");
		result.with("commande.CDT");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		ReceptionRequest request = (ReceptionRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedReception(property,
									searchCriterions);
						else
							return request.countNonAffectedReceptionReverse(
									property, searchCriterions);
					} else
						return request.countReception(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedReception(property,
									filterCriteria);
						else
							return request.countNonAffectedReceptionReverse(
									property, filterCriteria);
					} else
						return request.countReception(filterCriteria);
				}

			} else
				return request.countNonAffectedReception("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedReception(property,
								searchCriterions);
					else
						return request.countNonAffectedReceptionReverse(
								property, searchCriterions);
				} else
					return request.countReception(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedReception(property);
					else
						return request
								.countNonAffectedReceptionReverse(property);
				} else
					return request.countReception();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.receptionRequest();
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

			ReceptionRequest request = (ReceptionRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field Nom
			BasicCriteriaProxy cDT_nomCrit = request
					.create(BasicCriteriaProxy.class);
			cDT_nomCrit.setField("cDT.nom");
			cDT_nomCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			cDT_nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().centreDiagTrait_field_nom()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(cDT_nomCrit);

			// Search field Nom
			BasicCriteriaProxy commande_cDT_nomCrit = request
					.create(BasicCriteriaProxy.class);
			commande_cDT_nomCrit.setField("commande.cDT.nom");
			commande_cDT_nomCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			commande_cDT_nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().centreDiagTrait_field_nom()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(commande_cDT_nomCrit);
			// Search field Date
			try {
				DateUtil.parseDate(text);
				BasicCriteriaProxy commande_dateCrit = request
						.create(BasicCriteriaProxy.class);
				commande_dateCrit.setField("commande.date");
				commande_dateCrit
						.setOperation(CriteriaConstants.DATE_OPERATOR_EQUAL);
				commande_dateCrit.setValue(text);
				buffer.append("(" + NLS.constants().commande_field_date()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(commande_dateCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field DateReception
			try {
				DateUtil.parseDate(text);
				BasicCriteriaProxy dateReceptionCrit = request
						.create(BasicCriteriaProxy.class);
				dateReceptionCrit.setField("dateReception");
				dateReceptionCrit
						.setOperation(CriteriaConstants.DATE_OPERATOR_EQUAL);
				dateReceptionCrit.setValue(text);
				buffer.append("("
						+ NLS.constants().reception_field_dateReception()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(dateReceptionCrit);
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
