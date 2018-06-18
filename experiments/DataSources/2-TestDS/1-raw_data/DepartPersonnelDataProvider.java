package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.DepartPersonnelProxy;
import org.imogene.epicam.shared.request.DepartPersonnelRequest;
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
 * Data provider to feed the DepartPersonnel entry Table and Selection List
 * @author MEDES-IMPS
 */
public class DepartPersonnelDataProvider
		extends
			ImogBeanDataProvider<DepartPersonnelProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity DepartPersonnel with pagination
	 */
	public DepartPersonnelDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity DepartPersonnel that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public DepartPersonnelDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity DepartPersonnel that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public DepartPersonnelDataProvider(EpicamRequestFactory requestFactory,
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
	public Request<List<DepartPersonnelProxy>> getList(int start, int numRows) {

		DepartPersonnelRequest request = (DepartPersonnelRequest) getContext();
		Request<List<DepartPersonnelProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedDepartPersonnel(
									start, numRows, "dateDepart", false,
									searchCriterions, property);
						else
							result = request
									.listNonAffectedDepartPersonnelReverse(
											start, numRows, "dateDepart",
											false, searchCriterions, property);
					} else
						result = request.listDepartPersonnel(start, numRows,
								"dateDepart", false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedDepartPersonnel(
									start, numRows, "dateDepart", false,
									filterCriteria, property);
						else
							result = request
									.listNonAffectedDepartPersonnelReverse(
											start, numRows, "dateDepart",
											false, filterCriteria, property);
					} else
						result = request.listDepartPersonnel(start, numRows,
								"dateDepart", false, filterCriteria);
				}

			} else
				result = request.getDepartPersonnelEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedDepartPersonnel(start,
								numRows, "dateDepart", false, searchCriterions,
								property);
					else
						result = request.listNonAffectedDepartPersonnelReverse(
								start, numRows, "dateDepart", false,
								searchCriterions, property);
				} else
					result = request.listDepartPersonnel(start, numRows,
							"dateDepart", false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedDepartPersonnel(start,
								numRows, "dateDepart", false, property);
					else
						result = request.listNonAffectedDepartPersonnelReverse(
								start, numRows, "dateDepart", false, property);
				} else
					result = request.listDepartPersonnel(start, numRows,
							"dateDepart", false);
			}
		}

		if (isFiltered) {
			result.with("region");
			result.with("region.nom");
			result.with("districtSante");
			result.with("districtSante.nom");
			result.with("CDT");
			result.with("personnel");
		}

		else {
			result.with("personnel");
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<DepartPersonnelProxy>> getList(String property,
			int start, int numRows, boolean asc) {

		DepartPersonnelRequest request = (DepartPersonnelRequest) getContext();
		Request<List<DepartPersonnelProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listDepartPersonnel(start, numRows,
							property, asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listDepartPersonnel(start, numRows,
							property, asc, filterCriteria);

			} else
				result = request.getDepartPersonnelEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listDepartPersonnel(start, numRows, property,
						asc, searchCriterions);
			else
				result = request.listDepartPersonnel(start, numRows, property,
						asc);
		}

		result.with("region");
		result.with("region.nom");
		result.with("districtSante");
		result.with("districtSante.nom");
		result.with("CDT");
		result.with("personnel");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		DepartPersonnelRequest request = (DepartPersonnelRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedDepartPersonnel(
									property, searchCriterions);
						else
							return request
									.countNonAffectedDepartPersonnelReverse(
											property, searchCriterions);
					} else
						return request.countDepartPersonnel(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedDepartPersonnel(
									property, filterCriteria);
						else
							return request
									.countNonAffectedDepartPersonnelReverse(
											property, filterCriteria);
					} else
						return request.countDepartPersonnel(filterCriteria);
				}

			} else
				return request.countNonAffectedDepartPersonnel("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedDepartPersonnel(
								property, searchCriterions);
					else
						return request.countNonAffectedDepartPersonnelReverse(
								property, searchCriterions);
				} else
					return request.countDepartPersonnel(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request
								.countNonAffectedDepartPersonnel(property);
					else
						return request
								.countNonAffectedDepartPersonnelReverse(property);
				} else
					return request.countDepartPersonnel();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.departPersonnelRequest();
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

			DepartPersonnelRequest request = (DepartPersonnelRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field DateDepart
			try {
				DateUtil.parseDate(text);
				BasicCriteriaProxy dateDepartCrit = request
						.create(BasicCriteriaProxy.class);
				dateDepartCrit.setField("dateDepart");
				dateDepartCrit
						.setOperation(CriteriaConstants.DATE_OPERATOR_EQUAL);
				dateDepartCrit.setValue(text);
				buffer.append("("
						+ NLS.constants().departPersonnel_field_dateDepart()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(dateDepartCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field Nom
			BasicCriteriaProxy region_nomCrit = request
					.create(BasicCriteriaProxy.class);
			if (locale.equals("fr"))
				region_nomCrit.setField("region.nom.francais");
			if (locale.equals("en"))
				region_nomCrit.setField("region.nom.english");
			region_nomCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			region_nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().region_field_nom() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(region_nomCrit);

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
			BasicCriteriaProxy personnel_nomCrit = request
					.create(BasicCriteriaProxy.class);
			personnel_nomCrit.setField("personnel.nom");
			personnel_nomCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			personnel_nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().personnel_field_nom() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(personnel_nomCrit);

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
