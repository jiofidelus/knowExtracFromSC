package org.imogene.epicam.domain.dao;

import java.util.List;

import org.imogene.lib.common.dao.ImogActorDao;
import org.imogene.epicam.domain.entity.Personnel;

import org.imogene.epicam.domain.entity.Qualification;

import org.imogene.epicam.domain.entity.Region;

import org.imogene.epicam.domain.entity.DistrictSante;

import org.imogene.epicam.domain.entity.CentreDiagTrait;

/**
 * Manage persistence for Personnel
 * @author MEDES-IMPS
 */
public interface PersonnelDao extends ImogActorDao<Personnel> {

	/* relation dependencies */

	/**
	 * List associated Qualification, 
	 * on the field qualification
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<Qualification> loadQualification(Personnel parent);

}
