package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.ExamenMicroscopieProxy;
import org.imogene.epicam.shared.request.ExamenMicroscopieRequest;
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
 * Data provider to feed the ExamenMicroscopie entry Table and Selection List
 * @author MEDES-IMPS
 */
public class ExamenMicroscopieDataProvider
		extends
			ImogBeanDataProvider<ExamenMicroscopieProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity ExamenMicroscopie with pagination
	 */
	public ExamenMicroscopieDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity ExamenMicroscopie that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public ExamenMicroscopieDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity ExamenMicroscopie that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public ExamenMicroscopieDataProvider(EpicamRequestFactory requestFactory,
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
	public Request<List<ExamenMicroscopieProxy>> getList(int start, int numRows) {

		ExamenMicroscopieRequest request = (ExamenMicroscopieRequest) getContext();
		Request<List<ExamenMicroscopieProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedExamenMicroscopie(
									start, numRows, "date", false,
									searchCriterions, property);
						else
							result = request
									.listNonAffectedExamenMicroscopieReverse(
											start, numRows, "date", false,
											searchCriterions, property);
					} else
						result = request.listExamenMicroscopie(start, numRows,
								"date", false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedExamenMicroscopie(
									start, numRows, "date", false,
									filterCriteria, property);
						else
							result = request
									.listNonAffectedExamenMicroscopieReverse(
											start, numRows, "date", false,
											filterCriteria, property);
					} else
						result = request.listExamenMicroscopie(start, numRows,
								"date", false, filterCriteria);
				}

			} else
				result = request.getExamenMicroscopieEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedExamenMicroscopie(
								start, numRows, "date", false,
								searchCriterions, property);
					else
						result = request
								.listNonAffectedExamenMicroscopieReverse(start,
										numRows, "date", false,
										searchCriterions, property);
				} else
					result = request.listExamenMicroscopie(start, numRows,
							"date", false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedExamenMicroscopie(
								start, numRows, "date", false, property);
					else
						result = request
								.listNonAffectedExamenMicroscopieReverse(start,
										numRows, "date", false, property);
				} else
					result = request.listExamenMicroscopie(start, numRows,
							"date", false);
			}
		}

		if (isFiltered) {
			result.with("casTb");
			result.with("casTb.patient");
		}

		else {
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<ExamenMicroscopieProxy>> getList(String property,
			int start, int numRows, boolean asc) {

		ExamenMicroscopieRequest request = (ExamenMicroscopieRequest) getContext();
		Request<List<ExamenMicroscopieProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listExamenMicroscopie(start, numRows,
							property, asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listExamenMicroscopie(start, numRows,
							property, asc, filterCriteria);

			} else
				result = request.getExamenMicroscopieEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listExamenMicroscopie(start, numRows,
						property, asc, searchCriterions);
			else
				result = request.listExamenMicroscopie(start, numRows,
						property, asc);
		}

		result.with("casTb");
		result.with("casTb.patient");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		ExamenMicroscopieRequest request = (ExamenMicroscopieRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedExamenMicroscopie(
									property, searchCriterions);
						else
							return request
									.countNonAffectedExamenMicroscopieReverse(
											property, searchCriterions);
					} else
						return request.countExamenMicroscopie(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedExamenMicroscopie(
									property, filterCriteria);
						else
							return request
									.countNonAffectedExamenMicroscopieReverse(
											property, filterCriteria);
					} else
						return request.countExamenMicroscopie(filterCriteria);
				}

			} else
				return request.countNonAffectedExamenMicroscopie("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedExamenMicroscopie(
								property, searchCriterions);
					else
						return request
								.countNonAffectedExamenMicroscopieReverse(
										property, searchCriterions);
				} else
					return request.countExamenMicroscopie(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request
								.countNonAffectedExamenMicroscopie(property);
					else
						return request
								.countNonAffectedExamenMicroscopieReverse(property);
				} else
					return request.countExamenMicroscopie();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.examenMicroscopieRequest();
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

			ExamenMicroscopieRequest request = (ExamenMicroscopieRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field Identifiant
			BasicCriteriaProxy casTb_patient_identifiantCrit = request
					.create(BasicCriteriaProxy.class);
			casTb_patient_identifiantCrit.setField("casTb.patient.identifiant");
			casTb_patient_identifiantCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			casTb_patient_identifiantCrit.setValue(text);
			buffer.append("(" + NLS.constants().patient_field_identifiant()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(casTb_patient_identifiantCrit);
			// Search field Nom
			BasicCriteriaProxy casTb_patient_nomCrit = request
					.create(BasicCriteriaProxy.class);
			casTb_patient_nomCrit.setField("casTb.patient.nom");
			casTb_patient_nomCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			casTb_patient_nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().patient_field_nom() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(casTb_patient_nomCrit);

			// Search field Date
			try {
				DateUtil.parseDate(text);
				BasicCriteriaProxy dateCrit = request
						.create(BasicCriteriaProxy.class);
				dateCrit.setField("date");
				dateCrit.setOperation(CriteriaConstants.DATE_OPERATOR_EQUAL);
				dateCrit.setValue(text);
				buffer.append("("
						+ NLS.constants().examenMicroscopie_field_date() + ": "
						+ text + ") " + SYMBOL_OR + " ");
				criterionList.add(dateCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field RaisonDepistage
			if (text.toLowerCase()
					.equals(NLS
							.constants()
							.examenMicroscopie_raisonDepistage_diagnostic_option()
							.toLowerCase())) {
				BasicCriteriaProxy raisonDepistageCrit = request
						.create(BasicCriteriaProxy.class);
				raisonDepistageCrit.setField("raisonDepistage");
				raisonDepistageCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				raisonDepistageCrit
						.setValue(EpicamEnumConstants.EXAMENMICROSCOPIE_RAISONDEPISTAGE_DIAGNOSTIC);
				buffer.append("("
						+ NLS.constants()
								.examenMicroscopie_field_raisonDepistage()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(raisonDepistageCrit);
			}
			if (text.toLowerCase().equals(
					NLS.constants()
							.examenMicroscopie_raisonDepistage_suivi_option()
							.toLowerCase())) {
				BasicCriteriaProxy raisonDepistageCrit = request
						.create(BasicCriteriaProxy.class);
				raisonDepistageCrit.setField("raisonDepistage");
				raisonDepistageCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				raisonDepistageCrit
						.setValue(EpicamEnumConstants.EXAMENMICROSCOPIE_RAISONDEPISTAGE_SUIVI);
				buffer.append("("
						+ NLS.constants()
								.examenMicroscopie_field_raisonDepistage()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(raisonDepistageCrit);
			}

			// Search field Resultat
			if (text.toLowerCase().equals(
					NLS.constants().examenMicroscopie_resultat_negatif_option()
							.toLowerCase())) {
				BasicCriteriaProxy resultatCrit = request
						.create(BasicCriteriaProxy.class);
				resultatCrit.setField("resultat");
				resultatCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				resultatCrit
						.setValue(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_NEGATIF);
				buffer.append("("
						+ NLS.constants().examenMicroscopie_field_resultat()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(resultatCrit);
			}
			if (text.toLowerCase().equals(
					NLS.constants().examenMicroscopie_resultat_rare_option()
							.toLowerCase())) {
				BasicCriteriaProxy resultatCrit = request
						.create(BasicCriteriaProxy.class);
				resultatCrit.setField("resultat");
				resultatCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				resultatCrit
						.setValue(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_RARE);
				buffer.append("("
						+ NLS.constants().examenMicroscopie_field_resultat()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(resultatCrit);
			}
			if (text.toLowerCase().equals(
					NLS.constants().examenMicroscopie_resultat_unPlus_option()
							.toLowerCase())) {
				BasicCriteriaProxy resultatCrit = request
						.create(BasicCriteriaProxy.class);
				resultatCrit.setField("resultat");
				resultatCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				resultatCrit
						.setValue(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_UNPLUS);
				buffer.append("("
						+ NLS.constants().examenMicroscopie_field_resultat()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(resultatCrit);
			}
			if (text.toLowerCase().equals(
					NLS.constants()
							.examenMicroscopie_resultat_deuxPlus_option()
							.toLowerCase())) {
				BasicCriteriaProxy resultatCrit = request
						.create(BasicCriteriaProxy.class);
				resultatCrit.setField("resultat");
				resultatCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				resultatCrit
						.setValue(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_DEUXPLUS);
				buffer.append("("
						+ NLS.constants().examenMicroscopie_field_resultat()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(resultatCrit);
			}
			if (text.toLowerCase().equals(
					NLS.constants()
							.examenMicroscopie_resultat_troisPlus_option()
							.toLowerCase())) {
				BasicCriteriaProxy resultatCrit = request
						.create(BasicCriteriaProxy.class);
				resultatCrit.setField("resultat");
				resultatCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				resultatCrit
						.setValue(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_TROISPLUS);
				buffer.append("("
						+ NLS.constants().examenMicroscopie_field_resultat()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(resultatCrit);
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
