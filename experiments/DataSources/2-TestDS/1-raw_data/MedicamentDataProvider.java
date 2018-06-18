package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.MedicamentProxy;
import org.imogene.epicam.shared.request.MedicamentRequest;
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
 * Data provider to feed the Medicament entry Table and Selection List
 * @author MEDES-IMPS
 */
public class MedicamentDataProvider
		extends
			ImogBeanDataProvider<MedicamentProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity Medicament with pagination
	 */
	public MedicamentDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity Medicament that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public MedicamentDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity Medicament that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public MedicamentDataProvider(EpicamRequestFactory requestFactory,
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
	public Request<List<MedicamentProxy>> getList(int start, int numRows) {

		MedicamentRequest request = (MedicamentRequest) getContext();
		Request<List<MedicamentProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedMedicament(start,
									numRows, "designation", true,
									searchCriterions, property);
						else
							result = request.listNonAffectedMedicamentReverse(
									start, numRows, "designation", true,
									searchCriterions, property);
					} else
						result = request.listMedicament(start, numRows,
								"designation", true, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedMedicament(start,
									numRows, "designation", true,
									filterCriteria, property);
						else
							result = request.listNonAffectedMedicamentReverse(
									start, numRows, "designation", true,
									filterCriteria, property);
					} else
						result = request.listMedicament(start, numRows,
								"designation", true, filterCriteria);
				}

			} else
				result = request.getMedicamentEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedMedicament(start,
								numRows, "designation", true, searchCriterions,
								property);
					else
						result = request.listNonAffectedMedicamentReverse(
								start, numRows, "designation", true,
								searchCriterions, property);
				} else
					result = request.listMedicament(start, numRows,
							"designation", true, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedMedicament(start,
								numRows, "designation", true, property);
					else
						result = request.listNonAffectedMedicamentReverse(
								start, numRows, "designation", true, property);
				} else
					result = request.listMedicament(start, numRows,
							"designation", true);
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
	public Request<List<MedicamentProxy>> getList(String property, int start,
			int numRows, boolean asc) {

		MedicamentRequest request = (MedicamentRequest) getContext();
		Request<List<MedicamentProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listMedicament(start, numRows, property,
							asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listMedicament(start, numRows, property,
							asc, filterCriteria);

			} else
				result = request.getMedicamentEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listMedicament(start, numRows, property, asc,
						searchCriterions);
			else
				result = request.listMedicament(start, numRows, property, asc);
		}

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		MedicamentRequest request = (MedicamentRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedMedicament(property,
									searchCriterions);
						else
							return request.countNonAffectedMedicamentReverse(
									property, searchCriterions);
					} else
						return request.countMedicament(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedMedicament(property,
									filterCriteria);
						else
							return request.countNonAffectedMedicamentReverse(
									property, filterCriteria);
					} else
						return request.countMedicament(filterCriteria);
				}

			} else
				return request.countNonAffectedMedicament("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedMedicament(property,
								searchCriterions);
					else
						return request.countNonAffectedMedicamentReverse(
								property, searchCriterions);
				} else
					return request.countMedicament(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedMedicament(property);
					else
						return request
								.countNonAffectedMedicamentReverse(property);
				} else
					return request.countMedicament();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.medicamentRequest();
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

			MedicamentRequest request = (MedicamentRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field Code
			BasicCriteriaProxy codeCrit = request
					.create(BasicCriteriaProxy.class);
			codeCrit.setField("code");
			codeCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			codeCrit.setValue(text);
			buffer.append("(" + NLS.constants().medicament_field_code() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(codeCrit);

			// Search field Designation
			BasicCriteriaProxy designationCrit = request
					.create(BasicCriteriaProxy.class);
			designationCrit.setField("designation");
			designationCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			designationCrit.setValue(text);
			buffer.append("(" + NLS.constants().medicament_field_designation()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(designationCrit);

			// Search field EstMedicamentAntituberculeux
			if (text.toLowerCase().equals("true")
					|| text.toLowerCase().equals("false")) {
				BasicCriteriaProxy estMedicamentAntituberculeuxCrit = request
						.create(BasicCriteriaProxy.class);
				estMedicamentAntituberculeuxCrit
						.setField("estMedicamentAntituberculeux");
				estMedicamentAntituberculeuxCrit
						.setOperation(CriteriaConstants.BOOLEAN_OPERATOR_EQUAL);
				estMedicamentAntituberculeuxCrit.setValue(text);
				buffer.append("("
						+ NLS.constants()
								.medicament_field_estMedicamentAntituberculeux()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(estMedicamentAntituberculeuxCrit);
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
