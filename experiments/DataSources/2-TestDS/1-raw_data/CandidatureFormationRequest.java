package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.CandidatureFormationHandler;
import org.imogene.epicam.shared.proxy.CandidatureFormationProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the CandidatureFormation Service.
 * @author Medes-IMPS
 */
@Service(value = CandidatureFormationHandler.class, locator = SpringServiceLocator.class)
public interface CandidatureFormationRequest extends ImogEntityRequest {

	Request<CandidatureFormationProxy> findById(String id);

	Request<Void> save(CandidatureFormationProxy c, boolean isNew);

	Request<List<CandidatureFormationProxy>> listCandidatureFormation(
			String sortProperty, boolean sortOrder);
	Request<List<CandidatureFormationProxy>> listCandidatureFormation(
			int first, int max, String sortProperty, boolean sortOrder);
	Request<List<CandidatureFormationProxy>> listCandidatureFormation(
			int first, int max, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions);
	Request<List<CandidatureFormationProxy>> listCandidatureFormation(
			int first, int max, String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<CandidatureFormationProxy>> listNonAffectedCandidatureFormation(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<CandidatureFormationProxy>> listNonAffectedCandidatureFormation(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<CandidatureFormationProxy>> listNonAffectedCandidatureFormationReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<CandidatureFormationProxy>> listNonAffectedCandidatureFormationReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<CandidatureFormationProxy>> getCandidatureFormationEmptyList();

	Request<Long> countCandidatureFormation();
	Request<Long> countCandidatureFormation(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedCandidatureFormation(String property);
	Request<Long> countNonAffectedCandidatureFormation(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedCandidatureFormationReverse(String property);
	Request<Long> countNonAffectedCandidatureFormationReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<CandidatureFormationProxy> entities);
	Request<Void> delete(CandidatureFormationProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
