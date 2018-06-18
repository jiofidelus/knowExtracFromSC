package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.PersonnelProxy;
import org.imogene.epicam.shared.request.PersonnelRequest;
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

import org.imogene.admin.client.i18n.AdminNLS;

/**
 * Data provider to feed the Personnel entry Table and Selection List
 * @author MEDES-IMPS
 */
public class PersonnelDataProvider extends ImogBeanDataProvider<PersonnelProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity Personnel with pagination
	 */
	public PersonnelDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity Personnel that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public PersonnelDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity Personnel that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public PersonnelDataProvider(EpicamRequestFactory requestFactory,
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
	public Request<List<PersonnelProxy>> getList(int start, int numRows) {

		PersonnelRequest request = (PersonnelRequest) getContext();
		Request<List<PersonnelProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedPersonnel(start,
									numRows, "modified", false,
									searchCriterions, property);
						else
							result = request.listNonAffectedPersonnelReverse(
									start, numRows, "modified", false,
									searchCriterions, property);
					} else
						result = request.listPersonnel(start, numRows,
								"modified", false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedPersonnel(start,
									numRows, "modified", false, filterCriteria,
									property);
						else
							result = request.listNonAffectedPersonnelReverse(
									start, numRows, "modified", false,
									filterCriteria, property);
					} else
						result = request.listPersonnel(start, numRows,
								"modified", false, filterCriteria);
				}

			} else
				result = request.getPersonnelEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedPersonnel(start,
								numRows, "modified", false, searchCriterions,
								property);
					else
						result = request.listNonAffectedPersonnelReverse(start,
								numRows, "modified", false, searchCriterions,
								property);
				} else
					result = request.listPersonnel(start, numRows, "modified",
							false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedPersonnel(start,
								numRows, "modified", false, property);
					else
						result = request.listNonAffectedPersonnelReverse(start,
								numRows, "modified", false, property);
				} else
					result = request.listPersonnel(start, numRows, "modified",
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
	public Request<List<PersonnelProxy>> getList(String property, int start,
			int numRows, boolean asc) {

		PersonnelRequest request = (PersonnelRequest) getContext();
		Request<List<PersonnelProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listPersonnel(start, numRows, property,
							asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listPersonnel(start, numRows, property,
							asc, filterCriteria);

			} else
				result = request.getPersonnelEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listPersonnel(start, numRows, property, asc,
						searchCriterions);
			else
				result = request.listPersonnel(start, numRows, property, asc);
		}

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		PersonnelRequest request = (PersonnelRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedPersonnel(property,
									searchCriterions);
						else
							return request.countNonAffectedPersonnelReverse(
									property, searchCriterions);
					} else
						return request.countPersonnel(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedPersonnel(property,
									filterCriteria);
						else
							return request.countNonAffectedPersonnelReverse(
									property, filterCriteria);
					} else
						return request.countPersonnel(filterCriteria);
				}

			} else
				return request.countNonAffectedPersonnel("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedPersonnel(property,
								searchCriterions);
					else
						return request.countNonAffectedPersonnelReverse(
								property, searchCriterions);
				} else
					return request.countPersonnel(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedPersonnel(property);
					else
						return request
								.countNonAffectedPersonnelReverse(property);
				} else
					return request.countPersonnel();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.personnelRequest();
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

			PersonnelRequest request = (PersonnelRequest) getContext();
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
			buffer.append("(" + NLS.constants().personnel_field_nom() + ": "
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
						+ NLS.constants().personnel_field_dateNaissance()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(dateNaissanceCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

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
						+ NLS.constants().personnel_field_dateArrivee() + ": "
						+ text + ") " + SYMBOL_OR + " ");
				criterionList.add(dateArriveeCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

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
						+ NLS.constants().personnel_field_dateDepart() + ": "
						+ text + ") " + SYMBOL_OR + " ");
				criterionList.add(dateDepartCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field Niveau
			if (text.toLowerCase().equals(
					NLS.constants().personnel_niveau_central_option()
							.toLowerCase())) {
				BasicCriteriaProxy niveauCrit = request
						.create(BasicCriteriaProxy.class);
				niveauCrit.setField("niveau");
				niveauCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				niveauCrit
						.setValue(EpicamEnumConstants.PERSONNEL_NIVEAU_CENTRAL);
				buffer.append("(" + NLS.constants().personnel_field_niveau()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(niveauCrit);
			}
			if (text.toLowerCase().equals(
					NLS.constants().personnel_niveau_region_option()
							.toLowerCase())) {
				BasicCriteriaProxy niveauCrit = request
						.create(BasicCriteriaProxy.class);
				niveauCrit.setField("niveau");
				niveauCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				niveauCrit
						.setValue(EpicamEnumConstants.PERSONNEL_NIVEAU_REGION);
				buffer.append("(" + NLS.constants().personnel_field_niveau()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(niveauCrit);
			}
			if (text.toLowerCase().equals(
					NLS.constants().personnel_niveau_districtSante_option()
							.toLowerCase())) {
				BasicCriteriaProxy niveauCrit = request
						.create(BasicCriteriaProxy.class);
				niveauCrit.setField("niveau");
				niveauCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				niveauCrit
						.setValue(EpicamEnumConstants.PERSONNEL_NIVEAU_DISTRICTSANTE);
				buffer.append("(" + NLS.constants().personnel_field_niveau()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(niveauCrit);
			}
			if (text.toLowerCase()
					.equals(NLS.constants().personnel_niveau_cDT_option()
							.toLowerCase())) {
				BasicCriteriaProxy niveauCrit = request
						.create(BasicCriteriaProxy.class);
				niveauCrit.setField("niveau");
				niveauCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				niveauCrit.setValue(EpicamEnumConstants.PERSONNEL_NIVEAU_CDT);
				buffer.append("(" + NLS.constants().personnel_field_niveau()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(niveauCrit);
			}

			// Search field Actif
			if (text.toLowerCase().equals("true")
					|| text.toLowerCase().equals("false")) {
				BasicCriteriaProxy actifCrit = request
						.create(BasicCriteriaProxy.class);
				actifCrit.setField("actif");
				actifCrit
						.setOperation(CriteriaConstants.BOOLEAN_OPERATOR_EQUAL);
				actifCrit.setValue(text);
				buffer.append("(" + NLS.constants().personnel_field_actif()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(actifCrit);
			}

			// Search field Login
			BasicCriteriaProxy loginCrit = request
					.create(BasicCriteriaProxy.class);
			loginCrit.setField("login");
			loginCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			loginCrit.setValue(text);
			buffer.append("(" + AdminNLS.constants().imogActor_field_login()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(loginCrit);

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
