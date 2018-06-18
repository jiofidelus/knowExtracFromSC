package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.RegimeProxy;
import org.imogene.epicam.shared.request.RegimeRequest;
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
 * Data provider to feed the Regime entry Table and Selection List
 * @author MEDES-IMPS
 */
public class RegimeDataProvider extends ImogBeanDataProvider<RegimeProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity Regime with pagination
	 */
	public RegimeDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity Regime that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public RegimeDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity Regime that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public RegimeDataProvider(EpicamRequestFactory requestFactory,
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
	public Request<List<RegimeProxy>> getList(int start, int numRows) {

		RegimeRequest request = (RegimeRequest) getContext();
		Request<List<RegimeProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedRegime(start,
									numRows, "modified", false,
									searchCriterions, property);
						else
							result = request.listNonAffectedRegimeReverse(
									start, numRows, "modified", false,
									searchCriterions, property);
					} else
						result = request.listRegime(start, numRows, "modified",
								false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedRegime(start,
									numRows, "modified", false, filterCriteria,
									property);
						else
							result = request.listNonAffectedRegimeReverse(
									start, numRows, "modified", false,
									filterCriteria, property);
					} else
						result = request.listRegime(start, numRows, "modified",
								false, filterCriteria);
				}

			} else
				result = request.getRegimeEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedRegime(start, numRows,
								"modified", false, searchCriterions, property);
					else
						result = request.listNonAffectedRegimeReverse(start,
								numRows, "modified", false, searchCriterions,
								property);
				} else
					result = request.listRegime(start, numRows, "modified",
							false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedRegime(start, numRows,
								"modified", false, property);
					else
						result = request.listNonAffectedRegimeReverse(start,
								numRows, "modified", false, property);
				} else
					result = request.listRegime(start, numRows, "modified",
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
	public Request<List<RegimeProxy>> getList(String property, int start,
			int numRows, boolean asc) {

		RegimeRequest request = (RegimeRequest) getContext();
		Request<List<RegimeProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listRegime(start, numRows, property, asc,
							searchCriterions);
				else
					/* permanent filter only */
					result = request.listRegime(start, numRows, property, asc,
							filterCriteria);

			} else
				result = request.getRegimeEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listRegime(start, numRows, property, asc,
						searchCriterions);
			else
				result = request.listRegime(start, numRows, property, asc);
		}

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		RegimeRequest request = (RegimeRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedRegime(property,
									searchCriterions);
						else
							return request.countNonAffectedRegimeReverse(
									property, searchCriterions);
					} else
						return request.countRegime(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedRegime(property,
									filterCriteria);
						else
							return request.countNonAffectedRegimeReverse(
									property, filterCriteria);
					} else
						return request.countRegime(filterCriteria);
				}

			} else
				return request.countNonAffectedRegime("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedRegime(property,
								searchCriterions);
					else
						return request.countNonAffectedRegimeReverse(property,
								searchCriterions);
				} else
					return request.countRegime(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedRegime(property);
					else
						return request.countNonAffectedRegimeReverse(property);
				} else
					return request.countRegime();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.regimeRequest();
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

			RegimeRequest request = (RegimeRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field Nom
			BasicCriteriaProxy nomCrit = request
					.create(BasicCriteriaProxy.class);
			nomCrit.setField("nom");
			nomCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().regime_field_nom() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(nomCrit);

			// Search field Type
			if (text.toLowerCase().equals(
					NLS.constants().regime_type_phaseInitiale_option()
							.toLowerCase())) {
				BasicCriteriaProxy typeCrit = request
						.create(BasicCriteriaProxy.class);
				typeCrit.setField("type");
				typeCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				typeCrit.setValue(EpicamEnumConstants.REGIME_TYPE_PHASEINITIALE);
				buffer.append("(" + NLS.constants().regime_field_type() + ": "
						+ text + ") " + SYMBOL_OR + " ");
				criterionList.add(typeCrit);
			}
			if (text.toLowerCase().equals(
					NLS.constants().regime_type_phaseContinuation_option()
							.toLowerCase())) {
				BasicCriteriaProxy typeCrit = request
						.create(BasicCriteriaProxy.class);
				typeCrit.setField("type");
				typeCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				typeCrit.setValue(EpicamEnumConstants.REGIME_TYPE_PHASECONTINUATION);
				buffer.append("(" + NLS.constants().regime_field_type() + ": "
						+ text + ") " + SYMBOL_OR + " ");
				criterionList.add(typeCrit);
			}
			if (text.toLowerCase().equals(
					NLS.constants().regime_type_independant_option()
							.toLowerCase())) {
				BasicCriteriaProxy typeCrit = request
						.create(BasicCriteriaProxy.class);
				typeCrit.setField("type");
				typeCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				typeCrit.setValue(EpicamEnumConstants.REGIME_TYPE_INDEPENDANT);
				buffer.append("(" + NLS.constants().regime_field_type() + ": "
						+ text + ") " + SYMBOL_OR + " ");
				criterionList.add(typeCrit);
			}

			// Search field DureeTraitement
			try {
				Integer.valueOf(text);
				BasicCriteriaProxy dureeTraitementCrit = request
						.create(BasicCriteriaProxy.class);
				dureeTraitementCrit.setField("dureeTraitement");
				dureeTraitementCrit
						.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
				dureeTraitementCrit.setValue(text);
				buffer.append("("
						+ NLS.constants().regime_field_dureeTraitement() + ": "
						+ text + ") " + SYMBOL_OR + " ");
				criterionList.add(dureeTraitementCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field PoidsMin
			try {
				Integer.valueOf(text);
				BasicCriteriaProxy poidsMinCrit = request
						.create(BasicCriteriaProxy.class);
				poidsMinCrit.setField("poidsMin");
				poidsMinCrit.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
				poidsMinCrit.setValue(text);
				buffer.append("(" + NLS.constants().regime_field_poidsMin()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(poidsMinCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field PoidsMax
			try {
				Integer.valueOf(text);
				BasicCriteriaProxy poidsMaxCrit = request
						.create(BasicCriteriaProxy.class);
				poidsMaxCrit.setField("poidsMax");
				poidsMaxCrit.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
				poidsMaxCrit.setValue(text);
				buffer.append("(" + NLS.constants().regime_field_poidsMax()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(poidsMaxCrit);
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
