package org.imogene.epicam.domain.dao;

import java.util.List;

import org.imogene.lib.common.dao.ImogBeanDao;
import org.imogene.epicam.domain.entity.LaboratoireReference;

import org.imogene.epicam.domain.entity.Region;

import org.imogene.epicam.domain.entity.DistrictSante;

import org.imogene.epicam.domain.entity.LieuDit;

/**
 * Manage persistence for LaboratoireReference
 * @author MEDES-IMPS
 */
public interface LaboratoireReferenceDao
		extends
			ImogBeanDao<LaboratoireReference> {

	/* relation dependencies */

	/**
	 * List associated LieuDit, 
	 * on the field lieuxDits
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<LieuDit> loadLieuxDits(LaboratoireReference parent);

}
