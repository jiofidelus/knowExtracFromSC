package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.CandidatureFormationProxy;
import org.imogene.epicam.shared.request.CandidatureFormationRequest;
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
 * Data provider to feed the CandidatureFormation entry Table and Selection List
 * @author MEDES-IMPS
 */
public class CandidatureFormationDataProvider
		extends
			ImogBeanDataProvider<CandidatureFormationProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity CandidatureFormation with pagination
	 */
	public CandidatureFormationDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity CandidatureFormation that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public CandidatureFormationDataProvider(
			EpicamRequestFactory requestFactory, String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity CandidatureFormation that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public CandidatureFormationDataProvider(
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
	public Request<List<CandidatureFormationProxy>> getList(int start,
			int numRows) {

		CandidatureFormationRequest request = (CandidatureFormationRequest) getContext();
		Request<List<CandidatureFormationProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request
									.listNonAffectedCandidatureFormation(start,
											numRows, "personnel", true,
											searchCriterions, property);
						else
							result = request
									.listNonAffectedCandidatureFormationReverse(
											start, numRows, "personnel", true,
											searchCriterions, property);
					} else
						result = request.listCandidatureFormation(start,
								numRows, "personnel", true, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request
									.listNonAffectedCandidatureFormation(start,
											numRows, "personnel", true,
											filterCriteria, property);
						else
							result = request
									.listNonAffectedCandidatureFormationReverse(
											start, numRows, "personnel", true,
											filterCriteria, property);
					} else
						result = request.listCandidatureFormation(start,
								numRows, "personnel", true, filterCriteria);
				}

			} else
				result = request.getCandidatureFormationEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedCandidatureFormation(
								start, numRows, "personnel", true,
								searchCriterions, property);
					else
						result = request
								.listNonAffectedCandidatureFormationReverse(
										start, numRows, "personnel", true,
										searchCriterions, property);
				} else
					result = request.listCandidatureFormation(start, numRows,
							"personnel", true, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedCandidatureFormation(
								start, numRows, "personnel", true, property);
					else
						result = request
								.listNonAffectedCandidatureFormationReverse(
										start, numRows, "personnel", true,
										property);
				} else
					result = request.listCandidatureFormation(start, numRows,
							"personnel", true);
			}
		}

		if (isFiltered) {
			result.with("personnel");
			result.with("CDT");
			result.with("districtSante");
			result.with("districtSante.nom");
		}

		else {
			result.with("personnel");
			result.with("districtSante");
			result.with("districtSante.nom");
			result.with("CDT");
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<CandidatureFormationProxy>> getList(String property,
			int start, int numRows, boolean asc) {

		CandidatureFormationRequest request = (CandidatureFormationRequest) getContext();
		Request<List<CandidatureFormationProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listCandidatureFormation(start, numRows,
							property, asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listCandidatureFormation(start, numRows,
							property, asc, filterCriteria);

			} else
				result = request.getCandidatureFormationEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listCandidatureFormation(start, numRows,
						property, asc, searchCriterions);
			else
				result = request.listCandidatureFormation(start, numRows,
						property, asc);
		}

		result.with("personnel");
		result.with("CDT");
		result.with("districtSante");
		result.with("districtSante.nom");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		CandidatureFormationRequest request = (CandidatureFormationRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request
									.countNonAffectedCandidatureFormation(
											property, searchCriterions);
						else
							return request
									.countNonAffectedCandidatureFormationReverse(
											property, searchCriterions);
					} else
						return request
								.countCandidatureFormation(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request
									.countNonAffectedCandidatureFormation(
											property, filterCriteria);
						else
							return request
									.countNonAffectedCandidatureFormationReverse(
											property, filterCriteria);
					} else
						return request
								.countCandidatureFormation(filterCriteria);
				}

			} else
				return request.countNonAffectedCandidatureFormation("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedCandidatureFormation(
								property, searchCriterions);
					else
						return request
								.countNonAffectedCandidatureFormationReverse(
										property, searchCriterions);
				} else
					return request.countCandidatureFormation(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request
								.countNonAffectedCandidatureFormation(property);
					else
						return request
								.countNonAffectedCandidatureFormationReverse(property);
				} else
					return request.countCandidatureFormation();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.candidatureFormationRequest();
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

			CandidatureFormationRequest request = (CandidatureFormationRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field Nom
			BasicCriteriaProxy personnel_nomCrit = request
					.create(BasicCriteriaProxy.class);
			personnel_nomCrit.setField("personnel.nom");
			personnel_nomCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			personnel_nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().personnel_field_nom() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(personnel_nomCrit);

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

			// Search field ApprouveeRegion
			if (text.toLowerCase().equals("true")
					|| text.toLowerCase().equals("false")) {
				BasicCriteriaProxy approuveeRegionCrit = request
						.create(BasicCriteriaProxy.class);
				approuveeRegionCrit.setField("approuveeRegion");
				approuveeRegionCrit
						.setOperation(CriteriaConstants.BOOLEAN_OPERATOR_EQUAL);
				approuveeRegionCrit.setValue(text);
				buffer.append("("
						+ NLS.constants()
								.candidatureFormation_field_approuveeRegion()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(approuveeRegionCrit);
			}

			// Search field ApprouveeGTC
			if (text.toLowerCase().equals("true")
					|| text.toLowerCase().equals("false")) {
				BasicCriteriaProxy approuveeGTCCrit = request
						.create(BasicCriteriaProxy.class);
				approuveeGTCCrit.setField("approuveeGTC");
				approuveeGTCCrit
						.setOperation(CriteriaConstants.BOOLEAN_OPERATOR_EQUAL);
				approuveeGTCCrit.setValue(text);
				buffer.append("("
						+ NLS.constants()
								.candidatureFormation_field_approuveeGTC()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(approuveeGTCCrit);
			}

			// Search field Nom
			BasicCriteriaProxy districtSante_nomCrit = request
					.create(BasicCriteriaProxy.class);
			if (locale.equals("fr"))
				districtSante_nomCrit.setField("districtSante.nom.francais");
			if (locale.equals("en"))
				districtSante_nomCrit.setField("districtSante.nom.english");
			districtSante_nomCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			districtSante_nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().districtSante_field_nom()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(districtSante_nomCrit);

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
