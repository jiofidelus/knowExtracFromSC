package org.imogene.epicam.domain.dao;

import java.util.List;

import org.imogene.lib.common.dao.ImogBeanDao;
import org.imogene.epicam.domain.entity.Inventaire;

import org.imogene.epicam.domain.entity.Region;

import org.imogene.epicam.domain.entity.DistrictSante;

import org.imogene.epicam.domain.entity.CentreDiagTrait;

import org.imogene.epicam.domain.entity.DetailInventaire;

/**
 * Manage persistence for Inventaire
 * @author MEDES-IMPS
 */
public interface InventaireDao extends ImogBeanDao<Inventaire> {

	/* relation dependencies */

	/**
	 * List associated DetailInventaire, 
	 * on the field details
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<DetailInventaire> loadDetails(Inventaire parent);

}
