package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.CentreDiagTraitHandler;
import org.imogene.epicam.shared.proxy.CentreDiagTraitProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the CentreDiagTrait Service.
 * @author Medes-IMPS
 */
@Service(value = CentreDiagTraitHandler.class, locator = SpringServiceLocator.class)
public interface CentreDiagTraitRequest extends ImogEntityRequest {

	Request<CentreDiagTraitProxy> findById(String id);

	Request<Void> save(CentreDiagTraitProxy c, boolean isNew);

	Request<List<CentreDiagTraitProxy>> listCentreDiagTrait(
			String sortProperty, boolean sortOrder);
	Request<List<CentreDiagTraitProxy>> listCentreDiagTrait(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<CentreDiagTraitProxy>> listCentreDiagTrait(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<CentreDiagTraitProxy>> listCentreDiagTrait(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<CentreDiagTraitProxy>> listNonAffectedCentreDiagTrait(int i,
			int j, String sortProperty, boolean sortOrder, String property);
	Request<List<CentreDiagTraitProxy>> listNonAffectedCentreDiagTrait(int i,
			int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<CentreDiagTraitProxy>> listNonAffectedCentreDiagTraitReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<CentreDiagTraitProxy>> listNonAffectedCentreDiagTraitReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<CentreDiagTraitProxy>> getCentreDiagTraitEmptyList();

	Request<Long> countCentreDiagTrait();
	Request<Long> countCentreDiagTrait(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedCentreDiagTrait(String property);
	Request<Long> countNonAffectedCentreDiagTrait(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedCentreDiagTraitReverse(String property);
	Request<Long> countNonAffectedCentreDiagTraitReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<CentreDiagTraitProxy> entities);
	Request<Void> delete(CentreDiagTraitProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
