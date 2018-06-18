package org.imogene.epicam.domain.dao;

import java.util.List;

import org.imogene.lib.common.dao.ImogBeanDao;
import org.imogene.epicam.domain.entity.Reception;

import org.imogene.epicam.domain.entity.Region;

import org.imogene.epicam.domain.entity.DistrictSante;

import org.imogene.epicam.domain.entity.CentreDiagTrait;

import org.imogene.epicam.domain.entity.Commande;

import org.imogene.epicam.domain.entity.DetailReceptionMedicament;

import org.imogene.epicam.domain.entity.DetailReceptionIntrant;

/**
 * Manage persistence for Reception
 * @author MEDES-IMPS
 */
public interface ReceptionDao extends ImogBeanDao<Reception> {

	/* relation dependencies */

	/**
	 * List associated DetailReceptionMedicament, 
	 * on the field medicaments
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<DetailReceptionMedicament> loadMedicaments(Reception parent);

	/**
	 * List associated DetailReceptionIntrant, 
	 * on the field intrants
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<DetailReceptionIntrant> loadIntrants(Reception parent);

}
