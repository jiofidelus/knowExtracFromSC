package org.imogene.epicam.domain.dao;

import java.util.List;

import org.imogene.lib.common.dao.ImogBeanDao;
import org.imogene.epicam.domain.entity.Regime;

import org.imogene.epicam.domain.entity.PriseMedicamentRegime;

/**
 * Manage persistence for Regime
 * @author MEDES-IMPS
 */
public interface RegimeDao extends ImogBeanDao<Regime> {

	/* relation dependencies */

	/**
	 * List associated PriseMedicamentRegime, 
	 * on the field prisesMedicamenteuses
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<PriseMedicamentRegime> loadPrisesMedicamenteuses(Regime parent);

}
