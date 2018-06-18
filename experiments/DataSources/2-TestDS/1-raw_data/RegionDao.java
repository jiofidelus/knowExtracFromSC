package org.imogene.epicam.domain.dao;

import java.util.List;

import org.imogene.lib.common.dao.ImogBeanDao;
import org.imogene.epicam.domain.entity.Region;

import org.imogene.epicam.domain.entity.DistrictSante;

/**
 * Manage persistence for Region
 * @author MEDES-IMPS
 */
public interface RegionDao extends ImogBeanDao<Region> {

	/* relation dependencies */

	/**
	 * List associated DistrictSante, 
	 * on the field districtSantes
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<DistrictSante> loadDistrictSantes(Region parent);

}
