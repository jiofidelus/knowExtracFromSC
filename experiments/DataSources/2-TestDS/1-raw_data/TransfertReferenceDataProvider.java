package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.TransfertReferenceProxy;
import org.imogene.epicam.shared.request.TransfertReferenceRequest;
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
 * Data provider to feed the TransfertReference entry Table and Selection List
 * @author MEDES-IMPS
 */
public class TransfertReferenceDataProvider
		extends
			ImogBeanDataProvider<TransfertReferenceProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity TransfertReference with pagination
	 */
	public TransfertReferenceDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity TransfertReference that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public TransfertReferenceDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity TransfertReference that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public TransfertReferenceDataProvider(EpicamRequestFactory requestFactory,
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
	public Request<List<TransfertReferenceProxy>> getList(int start, int numRows) {

		TransfertReferenceRequest request = (TransfertReferenceRequest) getContext();
		Request<List<TransfertReferenceProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedTransfertReference(
									start, numRows, "dateDepart", false,
									searchCriterions, property);
						else
							result = request
									.listNonAffectedTransfertReferenceReverse(
											start, numRows, "dateDepart",
											false, searchCriterions, property);
					} else
						result = request.listTransfertReference(start, numRows,
								"dateDepart", false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedTransfertReference(
									start, numRows, "dateDepart", false,
									filterCriteria, property);
						else
							result = request
									.listNonAffectedTransfertReferenceReverse(
											start, numRows, "dateDepart",
											false, filterCriteria, property);
					} else
						result = request.listTransfertReference(start, numRows,
								"dateDepart", false, filterCriteria);
				}

			} else
				result = request.getTransfertReferenceEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedTransfertReference(
								start, numRows, "dateDepart", false,
								searchCriterions, property);
					else
						result = request
								.listNonAffectedTransfertReferenceReverse(
										start, numRows, "dateDepart", false,
										searchCriterions, property);
				} else
					result = request.listTransfertReference(start, numRows,
							"dateDepart", false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedTransfertReference(
								start, numRows, "dateDepart", false, property);
					else
						result = request
								.listNonAffectedTransfertReferenceReverse(
										start, numRows, "dateDepart", false,
										property);
				} else
					result = request.listTransfertReference(start, numRows,
							"dateDepart", false);
			}
		}

		if (isFiltered) {
			result.with("patient");
			result.with("CDTDepart");
			result.with("CDTArrivee");
		}

		else {
			result.with("patient");
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<TransfertReferenceProxy>> getList(String property,
			int start, int numRows, boolean asc) {

		TransfertReferenceRequest request = (TransfertReferenceRequest) getContext();
		Request<List<TransfertReferenceProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listTransfertReference(start, numRows,
							property, asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listTransfertReference(start, numRows,
							property, asc, filterCriteria);

			} else
				result = request.getTransfertReferenceEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listTransfertReference(start, numRows,
						property, asc, searchCriterions);
			else
				result = request.listTransfertReference(start, numRows,
						property, asc);
		}

		result.with("patient");
		result.with("CDTDepart");
		result.with("CDTArrivee");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		TransfertReferenceRequest request = (TransfertReferenceRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedTransfertReference(
									property, searchCriterions);
						else
							return request
									.countNonAffectedTransfertReferenceReverse(
											property, searchCriterions);
					} else
						return request
								.countTransfertReference(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedTransfertReference(
									property, filterCriteria);
						else
							return request
									.countNonAffectedTransfertReferenceReverse(
											property, filterCriteria);
					} else
						return request.countTransfertReference(filterCriteria);
				}

			} else
				return request.countNonAffectedTransfertReference("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedTransfertReference(
								property, searchCriterions);
					else
						return request
								.countNonAffectedTransfertReferenceReverse(
										property, searchCriterions);
				} else
					return request.countTransfertReference(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request
								.countNonAffectedTransfertReference(property);
					else
						return request
								.countNonAffectedTransfertReferenceReverse(property);
				} else
					return request.countTransfertReference();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.transfertReferenceRequest();
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

			TransfertReferenceRequest request = (TransfertReferenceRequest) getContext();
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

			// Search field Nature
			if (text.toLowerCase().equals(
					NLS.constants()
							.transfertReference_nature_transfert_option()
							.toLowerCase())) {
				BasicCriteriaProxy natureCrit = request
						.create(BasicCriteriaProxy.class);
				natureCrit.setField("nature");
				natureCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				natureCrit
						.setValue(EpicamEnumConstants.TRANSFERTREFERENCE_NATURE_TRANSFERT);
				buffer.append("("
						+ NLS.constants().transfertReference_field_nature()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(natureCrit);
			}
			if (text.toLowerCase().equals(
					NLS.constants()
							.transfertReference_nature_reference_option()
							.toLowerCase())) {
				BasicCriteriaProxy natureCrit = request
						.create(BasicCriteriaProxy.class);
				natureCrit.setField("nature");
				natureCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				natureCrit
						.setValue(EpicamEnumConstants.TRANSFERTREFERENCE_NATURE_REFERENCE);
				buffer.append("("
						+ NLS.constants().transfertReference_field_nature()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(natureCrit);
			}

			// Search field Nom
			BasicCriteriaProxy cDTDepart_nomCrit = request
					.create(BasicCriteriaProxy.class);
			cDTDepart_nomCrit.setField("cDTDepart.nom");
			cDTDepart_nomCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			cDTDepart_nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().centreDiagTrait_field_nom()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(cDTDepart_nomCrit);

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
						+ NLS.constants().transfertReference_field_dateDepart()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(dateDepartCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field Nom
			BasicCriteriaProxy cDTArrivee_nomCrit = request
					.create(BasicCriteriaProxy.class);
			cDTArrivee_nomCrit.setField("cDTArrivee.nom");
			cDTArrivee_nomCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			cDTArrivee_nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().centreDiagTrait_field_nom()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(cDTArrivee_nomCrit);

			// Search field DateArrivee
			try {
				DateUtil.parseDate(text);
				BasicCriteriaProxy dateArriveeCrit = request
						.create(BasicCriteriaProxy.class);
				dateArriveeCrit.setField("dateArrivee");
				dateArriveeCrit
						.setOperation(CriteriaConstants.DATE_OPERATOR_EQUAL);
				dateArriveeCrit.setValue(text);
				buffer.append("("
						+ NLS.constants()
								.transfertReference_field_dateArrivee() + ": "
						+ text + ") " + SYMBOL_OR + " ");
				criterionList.add(dateArriveeCrit);
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
