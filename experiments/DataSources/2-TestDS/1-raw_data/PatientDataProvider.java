package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.PatientProxy;
import org.imogene.epicam.shared.request.PatientRequest;
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
 * Data provider to feed the Patient entry Table and Selection List
 * @author MEDES-IMPS
 */
public class PatientDataProvider extends ImogBeanDataProvider<PatientProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;
	private String cdtId = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity Patient with pagination
	 */
	public PatientDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity Patient that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public PatientDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity Patient that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public PatientDataProvider(EpicamRequestFactory requestFactory,
			String pProperty, boolean searchInReverse) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		this.searchInReverse = searchInReverse;
		setSearchCriterions(null);
	}

	public void filterByCdt(String id) {
		this.cdtId = id;
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
	public Request<List<PatientProxy>> getList(int start, int numRows) {

		PatientRequest request = (PatientRequest) getContext();
		Request<List<PatientProxy>> result = null;

		if (cdtId != null) {

			if (searchCriterions != null)
				result = request.listPatientFilteredByCdt(start, numRows,
						"modified", false, searchCriterions, cdtId);
			else
				result = request.listPatientFilteredByCdt(start, numRows,
						"modified", false, null, cdtId);
		} else {

			if (isFiltered) {
				/* permanently filtered - hierarchical lists */
				if (filterCriteria != null) {

					if (searchCriterions != null) {
						/* permanent filter added to search criterion */
						if (nonAffected) {
							if (!searchInReverse)
								result = request.listNonAffectedPatient(start,
										numRows, "modified", false,
										searchCriterions, property);
							else
								result = request.listNonAffectedPatientReverse(
										start, numRows, "modified", false,
										searchCriterions, property);
						} else
							result = request.listPatient(start, numRows,
									"modified", false, searchCriterions);
					} else {
						/* permanent filter only */
						if (nonAffected) {
							if (!searchInReverse)
								result = request.listNonAffectedPatient(start,
										numRows, "modified", false,
										filterCriteria, property);
							else
								result = request.listNonAffectedPatientReverse(
										start, numRows, "modified", false,
										filterCriteria, property);
						} else
							result = request.listPatient(start, numRows,
									"modified", false, filterCriteria);
					}

				} else
					result = request.getPatientEmptyList();
			} else {
				if (searchCriterions != null) {

					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedPatient(start,
									numRows, "modified", false,
									searchCriterions, property);
						else
							result = request.listNonAffectedPatientReverse(
									start, numRows, "modified", false,
									searchCriterions, property);
					} else
						result = request.listPatient(start, numRows,
								"modified", false, searchCriterions);
				} else {

					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedPatient(start,
									numRows, "modified", false, property);
						else
							result = request
									.listNonAffectedPatientReverse(start,
											numRows, "modified", false,
											property);
					} else
						result = request.listPatient(start, numRows,
								"modified", false);
				}
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
	public Request<List<PatientProxy>> getList(String property, int start,
			int numRows, boolean asc) {

		PatientRequest request = (PatientRequest) getContext();
		Request<List<PatientProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listPatient(start, numRows, property, asc,
							searchCriterions);
				else
					/* permanent filter only */
					result = request.listPatient(start, numRows, property, asc,
							filterCriteria);

			} else
				result = request.getPatientEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listPatient(start, numRows, property, asc,
						searchCriterions);
			else
				result = request.listPatient(start, numRows, property, asc);
		}

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		PatientRequest request = (PatientRequest) getContext();

		if (cdtId != null) {
			if (searchCriterions != null)
				return request.countPatientFilteredByCdt(searchCriterions,
						cdtId);
			else
				return request.countPatientFilteredByCdt(null, cdtId);
		} else {

			if (isFiltered) {
				/* permanently filtered - hierarchical lists */
				if (filterCriteria != null) {

					if (searchCriterions != null) {
						/* permanent filter added to search criterion */
						if (nonAffected) {
							if (!searchInReverse)
								return request.countNonAffectedPatient(
										property, searchCriterions);
							else
								return request.countNonAffectedPatientReverse(
										property, searchCriterions);
						} else
							return request.countPatient(searchCriterions);
					} else {
						/* permanent filter only */
						if (nonAffected) {
							if (!searchInReverse)
								return request.countNonAffectedPatient(
										property, filterCriteria);
							else
								return request.countNonAffectedPatientReverse(
										property, filterCriteria);
						} else
							return request.countPatient(filterCriteria);
					}

				} else
					return request.countNonAffectedPatient("id");
			} else {

				if (searchCriterions != null) {
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedPatient(property,
									searchCriterions);
						else
							return request.countNonAffectedPatientReverse(
									property, searchCriterions);
					} else
						return request.countPatient(searchCriterions);
				} else {

					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedPatient(property);
						else
							return request
									.countNonAffectedPatientReverse(property);
					} else
						return request.countPatient();
				}
			}

		}
	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.patientRequest();
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

			PatientRequest request = (PatientRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field Identifiant
			BasicCriteriaProxy identifiantCrit = request
					.create(BasicCriteriaProxy.class);
			identifiantCrit.setField("identifiant");
			identifiantCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			identifiantCrit.setValue(text);
			buffer.append("(" + NLS.constants().patient_field_identifiant()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(identifiantCrit);

			// Search field Nom
			BasicCriteriaProxy nomCrit = request
					.create(BasicCriteriaProxy.class);
			nomCrit.setField("nom");
			nomCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().patient_field_nom() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(nomCrit);

			// Search field DateNaissance
			try {
				DateUtil.parseDate(text);
				BasicCriteriaProxy dateNaissanceCrit = request
						.create(BasicCriteriaProxy.class);
				dateNaissanceCrit.setField("dateNaissance");
				dateNaissanceCrit
						.setOperation(CriteriaConstants.DATE_OPERATOR_EQUAL);
				dateNaissanceCrit.setValue(text);
				buffer.append("("
						+ NLS.constants().patient_field_dateNaissance() + ": "
						+ text + ") " + SYMBOL_OR + " ");
				criterionList.add(dateNaissanceCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field Age
			try {
				Integer.valueOf(text);
				BasicCriteriaProxy ageCrit = request
						.create(BasicCriteriaProxy.class);
				ageCrit.setField("age");
				ageCrit.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
				ageCrit.setValue(text);
				buffer.append("(" + NLS.constants().patient_field_age() + ": "
						+ text + ") " + SYMBOL_OR + " ");
				criterionList.add(ageCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field Sexe
			if (text.toLowerCase().equals(
					NLS.constants().patient_sexe_masculin_option()
							.toLowerCase())) {
				BasicCriteriaProxy sexeCrit = request
						.create(BasicCriteriaProxy.class);
				sexeCrit.setField("sexe");
				sexeCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				sexeCrit.setValue(EpicamEnumConstants.PATIENT_SEXE_MASCULIN);
				buffer.append("(" + NLS.constants().patient_field_sexe() + ": "
						+ text + ") " + SYMBOL_OR + " ");
				criterionList.add(sexeCrit);
			}
			if (text.toLowerCase()
					.equals(NLS.constants().patient_sexe_feminin_option()
							.toLowerCase())) {
				BasicCriteriaProxy sexeCrit = request
						.create(BasicCriteriaProxy.class);
				sexeCrit.setField("sexe");
				sexeCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				sexeCrit.setValue(EpicamEnumConstants.PATIENT_SEXE_FEMININ);
				buffer.append("(" + NLS.constants().patient_field_sexe() + ": "
						+ text + ") " + SYMBOL_OR + " ");
				criterionList.add(sexeCrit);
			}

			// Search field Profession
			BasicCriteriaProxy professionCrit = request
					.create(BasicCriteriaProxy.class);
			professionCrit.setField("profession");
			professionCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			professionCrit.setValue(text);
			buffer.append("(" + NLS.constants().patient_field_profession()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(professionCrit);

			// Search field TelephoneUn
			BasicCriteriaProxy telephoneUnCrit = request
					.create(BasicCriteriaProxy.class);
			telephoneUnCrit.setField("telephoneUn");
			telephoneUnCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			telephoneUnCrit.setValue(text);
			buffer.append("(" + NLS.constants().patient_field_telephoneUn()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(telephoneUnCrit);

			// Search field TelephoneDeux
			BasicCriteriaProxy telephoneDeuxCrit = request
					.create(BasicCriteriaProxy.class);
			telephoneDeuxCrit.setField("telephoneDeux");
			telephoneDeuxCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			telephoneDeuxCrit.setValue(text);
			buffer.append("(" + NLS.constants().patient_field_telephoneDeux()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(telephoneDeuxCrit);

			// Search field PacNom
			BasicCriteriaProxy pacNomCrit = request
					.create(BasicCriteriaProxy.class);
			pacNomCrit.setField("pacNom");
			pacNomCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			pacNomCrit.setValue(text);
			buffer.append("(" + NLS.constants().patient_field_pacNom() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(pacNomCrit);

			// Search field PacTelephoneUn
			BasicCriteriaProxy pacTelephoneUnCrit = request
					.create(BasicCriteriaProxy.class);
			pacTelephoneUnCrit.setField("pacTelephoneUn");
			pacTelephoneUnCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			pacTelephoneUnCrit.setValue(text);
			buffer.append("(" + NLS.constants().patient_field_pacTelephoneUn()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(pacTelephoneUnCrit);

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
