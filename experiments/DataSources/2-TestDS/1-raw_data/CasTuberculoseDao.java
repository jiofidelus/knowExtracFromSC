package org.imogene.epicam.domain.dao;

import java.util.List;

import org.imogene.lib.common.dao.ImogBeanDao;
import org.imogene.epicam.domain.entity.CasTuberculose;

import org.imogene.epicam.domain.entity.Patient;

import org.imogene.epicam.domain.entity.ExamenMicroscopie;

import org.imogene.epicam.domain.entity.ExamenATB;

import org.imogene.epicam.domain.entity.Regime;

import org.imogene.epicam.domain.entity.Regime;

import org.imogene.epicam.domain.entity.PriseMedicamenteuse;

import org.imogene.epicam.domain.entity.PriseMedicamenteuse;

import org.imogene.epicam.domain.entity.RendezVous;

/**
 * Manage persistence for CasTuberculose
 * @author MEDES-IMPS
 */
public interface CasTuberculoseDao extends ImogBeanDao<CasTuberculose> {

	/* relation dependencies */

	/**
	 * List associated ExamenMicroscopie, 
	 * on the field examensMiscrocopies
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<ExamenMicroscopie> loadExamensMiscrocopies(CasTuberculose parent);

	/**
	 * List associated ExamenATB, 
	 * on the field examensATB
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<ExamenATB> loadExamensATB(CasTuberculose parent);

	/**
	 * List associated PriseMedicamenteuse, 
	 * on the field priseMedicamenteusePhaseInitiale
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<PriseMedicamenteuse> loadPriseMedicamenteusePhaseInitiale(
			CasTuberculose parent);

	/**
	 * List associated PriseMedicamenteuse, 
	 * on the field priseMedicamenteusePhaseContinuation
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<PriseMedicamenteuse> loadPriseMedicamenteusePhaseContinuation(
			CasTuberculose parent);

	/**
	 * List associated RendezVous, 
	 * on the field rendezVous
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	public List<RendezVous> loadRendezVous(CasTuberculose parent);

}
