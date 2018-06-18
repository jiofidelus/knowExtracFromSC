package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.CasTuberculoseProxy;
import org.imogene.epicam.shared.request.CasTuberculoseRequest;
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
 * Data provider to feed the CasTuberculose entry Table and Selection List
 * @author MEDES-IMPS
 */
public class CasTuberculoseDataProvider
		extends
			ImogBeanDataProvider<CasTuberculoseProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity CasTuberculose with pagination
	 */
	public CasTuberculoseDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity CasTuberculose that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public CasTuberculoseDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity CasTuberculose that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public CasTuberculoseDataProvider(EpicamRequestFactory requestFactory,
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
	public Request<List<CasTuberculoseProxy>> getList(int start, int numRows) {

		CasTuberculoseRequest request = (CasTuberculoseRequest) getContext();
		Request<List<CasTuberculoseProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedCasTuberculose(
									start, numRows, "modified", false,
									searchCriterions, property);
						else
							result = request
									.listNonAffectedCasTuberculoseReverse(
											start, numRows, "modified", false,
											searchCriterions, property);
					} else
						result = request.listCasTuberculose(start, numRows,
								"modified", false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedCasTuberculose(
									start, numRows, "modified", false,
									filterCriteria, property);
						else
							result = request
									.listNonAffectedCasTuberculoseReverse(
											start, numRows, "modified", false,
											filterCriteria, property);
					} else
						result = request.listCasTuberculose(start, numRows,
								"modified", false, filterCriteria);
				}

			} else
				result = request.getCasTuberculoseEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedCasTuberculose(start,
								numRows, "modified", false, searchCriterions,
								property);
					else
						result = request.listNonAffectedCasTuberculoseReverse(
								start, numRows, "modified", false,
								searchCriterions, property);
				} else
					result = request.listCasTuberculose(start, numRows,
							"modified", false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedCasTuberculose(start,
								numRows, "modified", false, property);
					else
						result = request.listNonAffectedCasTuberculoseReverse(
								start, numRows, "modified", false, property);
				} else
					result = request.listCasTuberculose(start, numRows,
							"modified", false);
			}
		}

		if (isFiltered) {
			result.with("patient");
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
	public Request<List<CasTuberculoseProxy>> getList(String property,
			int start, int numRows, boolean asc) {

		CasTuberculoseRequest request = (CasTuberculoseRequest) getContext();
		Request<List<CasTuberculoseProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listCasTuberculose(start, numRows,
							property, asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listCasTuberculose(start, numRows,
							property, asc, filterCriteria);

			} else
				result = request.getCasTuberculoseEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listCasTuberculose(start, numRows, property,
						asc, searchCriterions);
			else
				result = request.listCasTuberculose(start, numRows, property,
						asc);
		}

		result.with("patient");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		CasTuberculoseRequest request = (CasTuberculoseRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedCasTuberculose(
									property, searchCriterions);
						else
							return request
									.countNonAffectedCasTuberculoseReverse(
											property, searchCriterions);
					} else
						return request.countCasTuberculose(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedCasTuberculose(
									property, filterCriteria);
						else
							return request
									.countNonAffectedCasTuberculoseReverse(
											property, filterCriteria);
					} else
						return request.countCasTuberculose(filterCriteria);
				}

			} else
				return request.countNonAffectedCasTuberculose("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedCasTuberculose(property,
								searchCriterions);
					else
						return request.countNonAffectedCasTuberculoseReverse(
								property, searchCriterions);
				} else
					return request.countCasTuberculose(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedCasTuberculose(property);
					else
						return request
								.countNonAffectedCasTuberculoseReverse(property);
				} else
					return request.countCasTuberculose();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.casTuberculoseRequest();
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

			CasTuberculoseRequest request = (CasTuberculoseRequest) getContext();
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

			// Search field DateDebutTraitement
			try {
				DateUtil.parseDate(text);
				BasicCriteriaProxy dateDebutTraitementCrit = request
						.create(BasicCriteriaProxy.class);
				dateDebutTraitementCrit.setField("dateDebutTraitement");
				dateDebutTraitementCrit
						.setOperation(CriteriaConstants.DATE_OPERATOR_EQUAL);
				dateDebutTraitementCrit.setValue(text);
				buffer.append("("
						+ NLS.constants()
								.casTuberculose_field_dateDebutTraitement()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(dateDebutTraitementCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field TypePatient
			if (text.toLowerCase().equals(
					NLS.constants()
							.casTuberculose_typePatient_nouveauCas_option()
							.toLowerCase())) {
				BasicCriteriaProxy typePatientCrit = request
						.create(BasicCriteriaProxy.class);
				typePatientCrit.setField("typePatient");
				typePatientCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				typePatientCrit
						.setValue(EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_NOUVEAUCAS);
				buffer.append("("
						+ NLS.constants().casTuberculose_field_typePatient()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(typePatientCrit);
			}
			if (text.toLowerCase()
					.equals(NLS
							.constants()
							.casTuberculose_typePatient_repriseApresAbandon_option()
							.toLowerCase())) {
				BasicCriteriaProxy typePatientCrit = request
						.create(BasicCriteriaProxy.class);
				typePatientCrit.setField("typePatient");
				typePatientCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				typePatientCrit
						.setValue(EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_REPRISEAPRESABANDON);
				buffer.append("("
						+ NLS.constants().casTuberculose_field_typePatient()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(typePatientCrit);
			}
			if (text.toLowerCase().equals(
					NLS.constants().casTuberculose_typePatient_echec_option()
							.toLowerCase())) {
				BasicCriteriaProxy typePatientCrit = request
						.create(BasicCriteriaProxy.class);
				typePatientCrit.setField("typePatient");
				typePatientCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				typePatientCrit
						.setValue(EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_ECHEC);
				buffer.append("("
						+ NLS.constants().casTuberculose_field_typePatient()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(typePatientCrit);
			}
			if (text.toLowerCase().equals(
					NLS.constants().casTuberculose_typePatient_rechute_option()
							.toLowerCase())) {
				BasicCriteriaProxy typePatientCrit = request
						.create(BasicCriteriaProxy.class);
				typePatientCrit.setField("typePatient");
				typePatientCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				typePatientCrit
						.setValue(EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_RECHUTE);
				buffer.append("("
						+ NLS.constants().casTuberculose_field_typePatient()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(typePatientCrit);
			}
			if (text.toLowerCase().equals(
					NLS.constants().casTuberculose_typePatient_autre_option()
							.toLowerCase())) {
				BasicCriteriaProxy typePatientCrit = request
						.create(BasicCriteriaProxy.class);
				typePatientCrit.setField("typePatient");
				typePatientCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				typePatientCrit
						.setValue(EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_AUTRE);
				buffer.append("("
						+ NLS.constants().casTuberculose_field_typePatient()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(typePatientCrit);
			}

			// Search field FormeMaladie
			if (text.toLowerCase().equals(
					NLS.constants()
							.casTuberculose_formeMaladie_tPMPlus_option()
							.toLowerCase())) {
				BasicCriteriaProxy formeMaladieCrit = request
						.create(BasicCriteriaProxy.class);
				formeMaladieCrit.setField("formeMaladie");
				formeMaladieCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				formeMaladieCrit
						.setValue(EpicamEnumConstants.CASTUBERCULOSE_FORMEMALADIE_TPMPLUS);
				buffer.append("("
						+ NLS.constants().casTuberculose_field_formeMaladie()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(formeMaladieCrit);
			}
			if (text.toLowerCase().equals(
					NLS.constants()
							.casTuberculose_formeMaladie_tPMMoins_option()
							.toLowerCase())) {
				BasicCriteriaProxy formeMaladieCrit = request
						.create(BasicCriteriaProxy.class);
				formeMaladieCrit.setField("formeMaladie");
				formeMaladieCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				formeMaladieCrit
						.setValue(EpicamEnumConstants.CASTUBERCULOSE_FORMEMALADIE_TPMMOINS);
				buffer.append("("
						+ NLS.constants().casTuberculose_field_formeMaladie()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(formeMaladieCrit);
			}
			if (text.toLowerCase()
					.equals(NLS
							.constants()
							.casTuberculose_formeMaladie_extra_Pulmonaire_option()
							.toLowerCase())) {
				BasicCriteriaProxy formeMaladieCrit = request
						.create(BasicCriteriaProxy.class);
				formeMaladieCrit.setField("formeMaladie");
				formeMaladieCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				formeMaladieCrit
						.setValue(EpicamEnumConstants.CASTUBERCULOSE_FORMEMALADIE_EXTRA_PULMONAIRE);
				buffer.append("("
						+ NLS.constants().casTuberculose_field_formeMaladie()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(formeMaladieCrit);
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
