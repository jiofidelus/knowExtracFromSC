package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.CasTuberculoseHandler;
import org.imogene.epicam.shared.proxy.CasTuberculoseProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the CasTuberculose Service.
 * @author Medes-IMPS
 */
@Service(value = CasTuberculoseHandler.class, locator = SpringServiceLocator.class)
public interface CasTuberculoseRequest extends ImogEntityRequest {

	Request<CasTuberculoseProxy> findById(String id);

	Request<Void> save(CasTuberculoseProxy c, boolean isNew);

	Request<List<CasTuberculoseProxy>> listCasTuberculose(String sortProperty,
			boolean sortOrder);
	Request<List<CasTuberculoseProxy>> listCasTuberculose(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<CasTuberculoseProxy>> listCasTuberculose(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<CasTuberculoseProxy>> listCasTuberculose(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<CasTuberculoseProxy>> listNonAffectedCasTuberculose(int i,
			int j, String sortProperty, boolean sortOrder, String property);
	Request<List<CasTuberculoseProxy>> listNonAffectedCasTuberculose(int i,
			int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<CasTuberculoseProxy>> listNonAffectedCasTuberculoseReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<CasTuberculoseProxy>> listNonAffectedCasTuberculoseReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<CasTuberculoseProxy>> getCasTuberculoseEmptyList();

	Request<Long> countCasTuberculose();
	Request<Long> countCasTuberculose(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedCasTuberculose(String property);
	Request<Long> countNonAffectedCasTuberculose(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedCasTuberculoseReverse(String property);
	Request<Long> countNonAffectedCasTuberculoseReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<CasTuberculoseProxy> entities);
	Request<Void> delete(CasTuberculoseProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
