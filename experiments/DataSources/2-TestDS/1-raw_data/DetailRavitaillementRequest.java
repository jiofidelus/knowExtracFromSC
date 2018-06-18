package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.DetailRavitaillementHandler;
import org.imogene.epicam.shared.proxy.DetailRavitaillementProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the DetailRavitaillement Service.
 * @author Medes-IMPS
 */
@Service(value = DetailRavitaillementHandler.class, locator = SpringServiceLocator.class)
public interface DetailRavitaillementRequest extends ImogEntityRequest {

	Request<DetailRavitaillementProxy> findById(String id);

	Request<Void> save(DetailRavitaillementProxy c, boolean isNew);

	Request<List<DetailRavitaillementProxy>> listDetailRavitaillement(
			String sortProperty, boolean sortOrder);
	Request<List<DetailRavitaillementProxy>> listDetailRavitaillement(
			int first, int max, String sortProperty, boolean sortOrder);
	Request<List<DetailRavitaillementProxy>> listDetailRavitaillement(
			int first, int max, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions);
	Request<List<DetailRavitaillementProxy>> listDetailRavitaillement(
			int first, int max, String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<DetailRavitaillementProxy>> listNonAffectedDetailRavitaillement(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<DetailRavitaillementProxy>> listNonAffectedDetailRavitaillement(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<DetailRavitaillementProxy>> listNonAffectedDetailRavitaillementReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<DetailRavitaillementProxy>> listNonAffectedDetailRavitaillementReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<DetailRavitaillementProxy>> getDetailRavitaillementEmptyList();

	Request<Long> countDetailRavitaillement();
	Request<Long> countDetailRavitaillement(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedDetailRavitaillement(String property);
	Request<Long> countNonAffectedDetailRavitaillement(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedDetailRavitaillementReverse(String property);
	Request<Long> countNonAffectedDetailRavitaillementReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<DetailRavitaillementProxy> entities);
	Request<Void> delete(DetailRavitaillementProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
