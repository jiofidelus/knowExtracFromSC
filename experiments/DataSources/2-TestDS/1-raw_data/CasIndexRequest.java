package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.CasIndexHandler;
import org.imogene.epicam.shared.proxy.CasIndexProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the CasIndex Service.
 * @author Medes-IMPS
 */
@Service(value = CasIndexHandler.class, locator = SpringServiceLocator.class)
public interface CasIndexRequest extends ImogEntityRequest {

	Request<CasIndexProxy> findById(String id);

	Request<Void> save(CasIndexProxy c, boolean isNew);

	Request<List<CasIndexProxy>> listCasIndex(String sortProperty,
			boolean sortOrder);
	Request<List<CasIndexProxy>> listCasIndex(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<CasIndexProxy>> listCasIndex(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<CasIndexProxy>> listCasIndex(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<CasIndexProxy>> listNonAffectedCasIndex(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<CasIndexProxy>> listNonAffectedCasIndex(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<CasIndexProxy>> listNonAffectedCasIndexReverse(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<CasIndexProxy>> listNonAffectedCasIndexReverse(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<CasIndexProxy>> getCasIndexEmptyList();

	Request<Long> countCasIndex();
	Request<Long> countCasIndex(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedCasIndex(String property);
	Request<Long> countNonAffectedCasIndex(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedCasIndexReverse(String property);
	Request<Long> countNonAffectedCasIndexReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<CasIndexProxy> entities);
	Request<Void> delete(CasIndexProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
