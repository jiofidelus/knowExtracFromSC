package org.imogene.epicam.domain.dao;

import java.util.List;

import org.imogene.lib.common.dao.ImogBeanDao;
import org.imogene.epicam.domain.entity.Commande;

import org.imogene.epicam.domain.entity.Region;

import org.imogene.epicam.domain.entity.DistrictSante;

import org.imogene.epicam.domain.entity.CentreDiagTrait;

import org.imogene.epicam.domain.entity.DetailCommandeMedicament;

import org.imogene.epicam.domain.entity.DetailCommandeIntrant;

/**
 * Manage persistence for Commande
 * @author MEDES-IMPS
 */
public interface CommandeDao extends ImogBeanDao<Commande> {

	/* relation dependencies */

	/**
	 * List associated DetailCommandeMedicament, 
	 * on the field medicaments
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<DetailCommandeMedicament> loadMedicaments(Commande parent);

	/**
	 * List associated DetailCommandeIntrant, 
	 * on the field intrants
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<DetailCommandeIntrant> loadIntrants(Commande parent);

}
