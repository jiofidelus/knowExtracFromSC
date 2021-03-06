package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.DetailReceptionIntrantProxy;
import org.imogene.epicam.shared.request.DetailReceptionIntrantRequest;
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
 * Data provider to feed the DetailReceptionIntrant entry Table and Selection List
 * @author MEDES-IMPS
 */
public class DetailReceptionIntrantDataProvider
		extends
			ImogBeanDataProvider<DetailReceptionIntrantProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity DetailReceptionIntrant with pagination
	 */
	public DetailReceptionIntrantDataProvider(
			EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity DetailReceptionIntrant that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public DetailReceptionIntrantDataProvider(
			EpicamRequestFactory requestFactory, String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity DetailReceptionIntrant that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public DetailReceptionIntrantDataProvider(
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
	public Request<List<DetailReceptionIntrantProxy>> getList(int start,
			int numRows) {

		DetailReceptionIntrantRequest request = (DetailReceptionIntrantRequest) getContext();
		Request<List<DetailReceptionIntrantProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request
									.listNonAffectedDetailReceptionIntrant(
											start, numRows, "modified", false,
											searchCriterions, property);
						else
							result = request
									.listNonAffectedDetailReceptionIntrantReverse(
											start, numRows, "modified", false,
											searchCriterions, property);
					} else
						result = request.listDetailReceptionIntrant(start,
								numRows, "modified", false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request
									.listNonAffectedDetailReceptionIntrant(
											start, numRows, "modified", false,
											filterCriteria, property);
						else
							result = request
									.listNonAffectedDetailReceptionIntrantReverse(
											start, numRows, "modified", false,
											filterCriteria, property);
					} else
						result = request.listDetailReceptionIntrant(start,
								numRows, "modified", false, filterCriteria);
				}

			} else
				result = request.getDetailReceptionIntrantEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedDetailReceptionIntrant(
								start, numRows, "modified", false,
								searchCriterions, property);
					else
						result = request
								.listNonAffectedDetailReceptionIntrantReverse(
										start, numRows, "modified", false,
										searchCriterions, property);
				} else
					result = request.listDetailReceptionIntrant(start, numRows,
							"modified", false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedDetailReceptionIntrant(
								start, numRows, "modified", false, property);
					else
						result = request
								.listNonAffectedDetailReceptionIntrantReverse(
										start, numRows, "modified", false,
										property);
				} else
					result = request.listDetailReceptionIntrant(start, numRows,
							"modified", false);
			}
		}

		if (isFiltered) {
			result.with("commande");
			result.with("commande.CDT");
			result.with("detailCommande");
			result.with("detailCommande.intrant");
		}

		else {
			result.with("detailCommande");
			result.with("detailCommande.intrant");
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<DetailReceptionIntrantProxy>> getList(String property,
			int start, int numRows, boolean asc) {

		DetailReceptionIntrantRequest request = (DetailReceptionIntrantRequest) getContext();
		Request<List<DetailReceptionIntrantProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listDetailReceptionIntrant(start, numRows,
							property, asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listDetailReceptionIntrant(start, numRows,
							property, asc, filterCriteria);

			} else
				result = request.getDetailReceptionIntrantEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listDetailReceptionIntrant(start, numRows,
						property, asc, searchCriterions);
			else
				result = request.listDetailReceptionIntrant(start, numRows,
						property, asc);
		}

		result.with("commande");
		result.with("commande.CDT");
		result.with("detailCommande");
		result.with("detailCommande.intrant");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		DetailReceptionIntrantRequest request = (DetailReceptionIntrantRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request
									.countNonAffectedDetailReceptionIntrant(
											property, searchCriterions);
						else
							return request
									.countNonAffectedDetailReceptionIntrantReverse(
											property, searchCriterions);
					} else
						return request
								.countDetailReceptionIntrant(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request
									.countNonAffectedDetailReceptionIntrant(
											property, filterCriteria);
						else
							return request
									.countNonAffectedDetailReceptionIntrantReverse(
											property, filterCriteria);
					} else
						return request
								.countDetailReceptionIntrant(filterCriteria);
				}

			} else
				return request.countNonAffectedDetailReceptionIntrant("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedDetailReceptionIntrant(
								property, searchCriterions);
					else
						return request
								.countNonAffectedDetailReceptionIntrantReverse(
										property, searchCriterions);
				} else
					return request
							.countDetailReceptionIntrant(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request
								.countNonAffectedDetailReceptionIntrant(property);
					else
						return request
								.countNonAffectedDetailReceptionIntrantReverse(property);
				} else
					return request.countDetailReceptionIntrant();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.detailReceptionIntrantRequest();
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

			DetailReceptionIntrantRequest request = (DetailReceptionIntrantRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

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

			// Search field Identifiant
			BasicCriteriaProxy detailCommande_intrant_identifiantCrit = request
					.create(BasicCriteriaProxy.class);
			detailCommande_intrant_identifiantCrit
					.setField("detailCommande.intrant.identifiant");
			detailCommande_intrant_identifiantCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			detailCommande_intrant_identifiantCrit.setValue(text);
			buffer.append("(" + NLS.constants().intrant_field_identifiant()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(detailCommande_intrant_identifiantCrit);
			// Search field QuantiteRequise
			try {
				Integer.valueOf(text);
				BasicCriteriaProxy detailCommande_quantiteRequiseCrit = request
						.create(BasicCriteriaProxy.class);
				detailCommande_quantiteRequiseCrit
						.setField("detailCommande.quantiteRequise");
				detailCommande_quantiteRequiseCrit
						.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
				detailCommande_quantiteRequiseCrit.setValue(text);
				buffer.append("("
						+ NLS.constants()
								.detailCommandeIntrant_field_quantiteRequise()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(detailCommande_quantiteRequiseCrit);
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
