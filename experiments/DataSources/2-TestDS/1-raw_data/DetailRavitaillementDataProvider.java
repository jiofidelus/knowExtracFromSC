package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.DetailRavitaillementProxy;
import org.imogene.epicam.shared.request.DetailRavitaillementRequest;
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
 * Data provider to feed the DetailRavitaillement entry Table and Selection List
 * @author MEDES-IMPS
 */
public class DetailRavitaillementDataProvider
		extends
			ImogBeanDataProvider<DetailRavitaillementProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity DetailRavitaillement with pagination
	 */
	public DetailRavitaillementDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity DetailRavitaillement that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public DetailRavitaillementDataProvider(
			EpicamRequestFactory requestFactory, String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity DetailRavitaillement that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public DetailRavitaillementDataProvider(
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
	public Request<List<DetailRavitaillementProxy>> getList(int start,
			int numRows) {

		DetailRavitaillementRequest request = (DetailRavitaillementRequest) getContext();
		Request<List<DetailRavitaillementProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request
									.listNonAffectedDetailRavitaillement(start,
											numRows, "modified", false,
											searchCriterions, property);
						else
							result = request
									.listNonAffectedDetailRavitaillementReverse(
											start, numRows, "modified", false,
											searchCriterions, property);
					} else
						result = request.listDetailRavitaillement(start,
								numRows, "modified", false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request
									.listNonAffectedDetailRavitaillement(start,
											numRows, "modified", false,
											filterCriteria, property);
						else
							result = request
									.listNonAffectedDetailRavitaillementReverse(
											start, numRows, "modified", false,
											filterCriteria, property);
					} else
						result = request.listDetailRavitaillement(start,
								numRows, "modified", false, filterCriteria);
				}

			} else
				result = request.getDetailRavitaillementEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedDetailRavitaillement(
								start, numRows, "modified", false,
								searchCriterions, property);
					else
						result = request
								.listNonAffectedDetailRavitaillementReverse(
										start, numRows, "modified", false,
										searchCriterions, property);
				} else
					result = request.listDetailRavitaillement(start, numRows,
							"modified", false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedDetailRavitaillement(
								start, numRows, "modified", false, property);
					else
						result = request
								.listNonAffectedDetailRavitaillementReverse(
										start, numRows, "modified", false,
										property);
				} else
					result = request.listDetailRavitaillement(start, numRows,
							"modified", false);
			}
		}

		if (isFiltered) {
			result.with("ravitaillement");
			result.with("ravitaillement.CDTDepart");
			result.with("ravitaillement.CDTArrivee");
			result.with("sortieLot");
			result.with("sortieLot.lot");
			result.with("entreeLot");
			result.with("entreeLot.lot");
		}

		else {
			result.with("ravitaillement");
			result.with("ravitaillement.CDTDepart");
			result.with("ravitaillement.CDTArrivee");
			result.with("sortieLot");
			result.with("sortieLot.lot");
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<DetailRavitaillementProxy>> getList(String property,
			int start, int numRows, boolean asc) {

		DetailRavitaillementRequest request = (DetailRavitaillementRequest) getContext();
		Request<List<DetailRavitaillementProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listDetailRavitaillement(start, numRows,
							property, asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listDetailRavitaillement(start, numRows,
							property, asc, filterCriteria);

			} else
				result = request.getDetailRavitaillementEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listDetailRavitaillement(start, numRows,
						property, asc, searchCriterions);
			else
				result = request.listDetailRavitaillement(start, numRows,
						property, asc);
		}

		result.with("ravitaillement");
		result.with("ravitaillement.CDTDepart");
		result.with("ravitaillement.CDTArrivee");
		result.with("sortieLot");
		result.with("sortieLot.lot");
		result.with("entreeLot");
		result.with("entreeLot.lot");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		DetailRavitaillementRequest request = (DetailRavitaillementRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request
									.countNonAffectedDetailRavitaillement(
											property, searchCriterions);
						else
							return request
									.countNonAffectedDetailRavitaillementReverse(
											property, searchCriterions);
					} else
						return request
								.countDetailRavitaillement(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request
									.countNonAffectedDetailRavitaillement(
											property, filterCriteria);
						else
							return request
									.countNonAffectedDetailRavitaillementReverse(
											property, filterCriteria);
					} else
						return request
								.countDetailRavitaillement(filterCriteria);
				}

			} else
				return request.countNonAffectedDetailRavitaillement("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedDetailRavitaillement(
								property, searchCriterions);
					else
						return request
								.countNonAffectedDetailRavitaillementReverse(
										property, searchCriterions);
				} else
					return request.countDetailRavitaillement(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request
								.countNonAffectedDetailRavitaillement(property);
					else
						return request
								.countNonAffectedDetailRavitaillementReverse(property);
				} else
					return request.countDetailRavitaillement();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.detailRavitaillementRequest();
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

			DetailRavitaillementRequest request = (DetailRavitaillementRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field Nom
			BasicCriteriaProxy ravitaillement_cDTDepart_nomCrit = request
					.create(BasicCriteriaProxy.class);
			ravitaillement_cDTDepart_nomCrit
					.setField("ravitaillement.cDTDepart.nom");
			ravitaillement_cDTDepart_nomCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			ravitaillement_cDTDepart_nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().centreDiagTrait_field_nom()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(ravitaillement_cDTDepart_nomCrit);
			// Search field DateDepart
			try {
				DateUtil.parseDate(text);
				BasicCriteriaProxy ravitaillement_dateDepartCrit = request
						.create(BasicCriteriaProxy.class);
				ravitaillement_dateDepartCrit
						.setField("ravitaillement.dateDepart");
				ravitaillement_dateDepartCrit
						.setOperation(CriteriaConstants.DATE_OPERATOR_EQUAL);
				ravitaillement_dateDepartCrit.setValue(text);
				buffer.append("("
						+ NLS.constants().ravitaillement_field_dateDepart()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(ravitaillement_dateDepartCrit);
			} catch (Exception ex) {/*criteria not added*/
			}
			// Search field Nom
			BasicCriteriaProxy ravitaillement_cDTArrivee_nomCrit = request
					.create(BasicCriteriaProxy.class);
			ravitaillement_cDTArrivee_nomCrit
					.setField("ravitaillement.cDTArrivee.nom");
			ravitaillement_cDTArrivee_nomCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			ravitaillement_cDTArrivee_nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().centreDiagTrait_field_nom()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(ravitaillement_cDTArrivee_nomCrit);
			// Search field DateArrivee
			try {
				DateUtil.parseDate(text);
				BasicCriteriaProxy ravitaillement_dateArriveeCrit = request
						.create(BasicCriteriaProxy.class);
				ravitaillement_dateArriveeCrit
						.setField("ravitaillement.dateArrivee");
				ravitaillement_dateArriveeCrit
						.setOperation(CriteriaConstants.DATE_OPERATOR_EQUAL);
				ravitaillement_dateArriveeCrit.setValue(text);
				buffer.append("("
						+ NLS.constants().ravitaillement_field_dateArrivee()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(ravitaillement_dateArriveeCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field Numero
			BasicCriteriaProxy sortieLot_lot_numeroCrit = request
					.create(BasicCriteriaProxy.class);
			sortieLot_lot_numeroCrit.setField("sortieLot.lot.numero");
			sortieLot_lot_numeroCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			sortieLot_lot_numeroCrit.setValue(text);
			buffer.append("(" + NLS.constants().lot_field_numero() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(sortieLot_lot_numeroCrit);
			// Search field Identifiant
			BasicCriteriaProxy sortieLot_lot_intrant_identifiantCrit = request
					.create(BasicCriteriaProxy.class);
			sortieLot_lot_intrant_identifiantCrit
					.setField("sortieLot.lot.intrant.identifiant");
			sortieLot_lot_intrant_identifiantCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			sortieLot_lot_intrant_identifiantCrit.setValue(text);
			buffer.append("(" + NLS.constants().intrant_field_identifiant()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(sortieLot_lot_intrant_identifiantCrit);
			// Search field Designation
			BasicCriteriaProxy sortieLot_lot_medicament_designationCrit = request
					.create(BasicCriteriaProxy.class);
			sortieLot_lot_medicament_designationCrit
					.setField("sortieLot.lot.medicament.designation");
			sortieLot_lot_medicament_designationCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			sortieLot_lot_medicament_designationCrit.setValue(text);
			buffer.append("(" + NLS.constants().medicament_field_designation()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(sortieLot_lot_medicament_designationCrit);
			// Search field Quantite
			try {
				Integer.valueOf(text);
				BasicCriteriaProxy sortieLot_lot_quantiteCrit = request
						.create(BasicCriteriaProxy.class);
				sortieLot_lot_quantiteCrit.setField("sortieLot.lot.quantite");
				sortieLot_lot_quantiteCrit
						.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
				sortieLot_lot_quantiteCrit.setValue(text);
				buffer.append("(" + NLS.constants().lot_field_quantite() + ": "
						+ text + ") " + SYMBOL_OR + " ");
				criterionList.add(sortieLot_lot_quantiteCrit);
			} catch (Exception ex) {/*criteria not added*/
			}
			// Search field Quantite
			try {
				Integer.valueOf(text);
				BasicCriteriaProxy sortieLot_quantiteCrit = request
						.create(BasicCriteriaProxy.class);
				sortieLot_quantiteCrit.setField("sortieLot.quantite");
				sortieLot_quantiteCrit
						.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
				sortieLot_quantiteCrit.setValue(text);
				buffer.append("(" + NLS.constants().sortieLot_field_quantite()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(sortieLot_quantiteCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field Numero
			BasicCriteriaProxy entreeLot_lot_numeroCrit = request
					.create(BasicCriteriaProxy.class);
			entreeLot_lot_numeroCrit.setField("entreeLot.lot.numero");
			entreeLot_lot_numeroCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			entreeLot_lot_numeroCrit.setValue(text);
			buffer.append("(" + NLS.constants().lot_field_numero() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(entreeLot_lot_numeroCrit);
			// Search field Identifiant
			BasicCriteriaProxy entreeLot_lot_intrant_identifiantCrit = request
					.create(BasicCriteriaProxy.class);
			entreeLot_lot_intrant_identifiantCrit
					.setField("entreeLot.lot.intrant.identifiant");
			entreeLot_lot_intrant_identifiantCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			entreeLot_lot_intrant_identifiantCrit.setValue(text);
			buffer.append("(" + NLS.constants().intrant_field_identifiant()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(entreeLot_lot_intrant_identifiantCrit);
			// Search field Designation
			BasicCriteriaProxy entreeLot_lot_medicament_designationCrit = request
					.create(BasicCriteriaProxy.class);
			entreeLot_lot_medicament_designationCrit
					.setField("entreeLot.lot.medicament.designation");
			entreeLot_lot_medicament_designationCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			entreeLot_lot_medicament_designationCrit.setValue(text);
			buffer.append("(" + NLS.constants().medicament_field_designation()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(entreeLot_lot_medicament_designationCrit);
			// Search field Quantite
			try {
				Integer.valueOf(text);
				BasicCriteriaProxy entreeLot_lot_quantiteCrit = request
						.create(BasicCriteriaProxy.class);
				entreeLot_lot_quantiteCrit.setField("entreeLot.lot.quantite");
				entreeLot_lot_quantiteCrit
						.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
				entreeLot_lot_quantiteCrit.setValue(text);
				buffer.append("(" + NLS.constants().lot_field_quantite() + ": "
						+ text + ") " + SYMBOL_OR + " ");
				criterionList.add(entreeLot_lot_quantiteCrit);
			} catch (Exception ex) {/*criteria not added*/
			}
			// Search field Quantite
			try {
				Integer.valueOf(text);
				BasicCriteriaProxy entreeLot_quantiteCrit = request
						.create(BasicCriteriaProxy.class);
				entreeLot_quantiteCrit.setField("entreeLot.quantite");
				entreeLot_quantiteCrit
						.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
				entreeLot_quantiteCrit.setValue(text);
				buffer.append("(" + NLS.constants().entreeLot_field_quantite()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(entreeLot_quantiteCrit);
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
