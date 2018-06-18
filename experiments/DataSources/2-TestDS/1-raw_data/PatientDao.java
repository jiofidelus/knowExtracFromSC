package org.imogene.epicam.domain.dao;

import java.util.List;

import org.imogene.lib.common.dao.ImogBeanDao;
import org.imogene.epicam.domain.entity.Patient;

import org.imogene.epicam.domain.entity.CentreDiagTrait;

import org.imogene.epicam.domain.entity.LieuDit;

import org.imogene.epicam.domain.entity.CasTuberculose;

import org.imogene.epicam.domain.entity.CasIndex;

import org.imogene.epicam.domain.entity.ExamenBiologique;

import org.imogene.epicam.domain.entity.ExamenSerologie;
import org.imogene.lib.common.criteria.ImogJunction;

/**
 * Manage persistence for Patient
 * @author MEDES-IMPS
 */
public interface PatientDao extends ImogBeanDao<Patient> {

	/* relation dependencies */

	/**
	 * List associated CentreDiagTrait, 
	 * on the field centres
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<CentreDiagTrait> loadCentres(Patient parent);

	/**
	 * List associated LieuDit, 
	 * on the field lieuxDits
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<LieuDit> loadLieuxDits(Patient parent);

	/**
	 * List associated CasTuberculose, 
	 * on the field casTuberculose
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<CasTuberculose> loadCasTuberculose(Patient parent);

	/**
	 * List associated CasIndex, 
	 * on the field casIndex
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<CasIndex> loadCasIndex(Patient parent);

	/**
	 * List associated ExamenBiologique, 
	 * on the field examensBiologiques
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<ExamenBiologique> loadExamensBiologiques(Patient parent);

	/**
	 * List associated ExamenSerologie, 
	 * on the field serologies
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<ExamenSerologie> loadSerologies(Patient parent);

	public Long countPatientFilteredByCdt(ImogJunction criterions, String cdtId);

	public List<Patient> listPatientFilteredByCdt(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunction criterions,
			String cdtId);

}
