package org.imogene.epicam.domain.dao;

import java.util.List;

import org.imogene.lib.common.dao.ImogBeanDao;
import org.imogene.epicam.domain.entity.Ravitaillement;

import org.imogene.epicam.domain.entity.Region;

import org.imogene.epicam.domain.entity.DistrictSante;

import org.imogene.epicam.domain.entity.CentreDiagTrait;

import org.imogene.epicam.domain.entity.Region;

import org.imogene.epicam.domain.entity.DistrictSante;

import org.imogene.epicam.domain.entity.CentreDiagTrait;

import org.imogene.epicam.domain.entity.DetailRavitaillement;

/**
 * Manage persistence for Ravitaillement
 * @author MEDES-IMPS
 */
public interface RavitaillementDao extends ImogBeanDao<Ravitaillement> {

	/* relation dependencies */

	/**
	 * List associated DetailRavitaillement, 
	 * on the field details
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<DetailRavitaillement> loadDetails(Ravitaillement parent);

}
