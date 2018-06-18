package org.imogene.epicam.domain.dao;

import java.util.List;

import org.imogene.lib.common.dao.ImogBeanDao;
import org.imogene.epicam.domain.entity.Formation;

import org.imogene.epicam.domain.entity.CandidatureFormation;

/**
 * Manage persistence for Formation
 * @author MEDES-IMPS
 */
public interface FormationDao extends ImogBeanDao<Formation> {

	/* relation dependencies */

	/**
	 * List associated CandidatureFormation, 
	 * on the field candidatures
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<CandidatureFormation> loadCandidatures(Formation parent);

}
