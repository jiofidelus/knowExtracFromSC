package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.HorsUsageHandler;
import org.imogene.epicam.shared.proxy.HorsUsageProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the HorsUsage Service.
 * @author Medes-IMPS
 */
@Service(value = HorsUsageHandler.class, locator = SpringServiceLocator.class)
public interface HorsUsageRequest extends ImogEntityRequest {

	Request<HorsUsageProxy> findById(String id);

	Request<Void> save(HorsUsageProxy c, boolean isNew);

	Request<List<HorsUsageProxy>> listHorsUsage(String sortProperty,
			boolean sortOrder);
	Request<List<HorsUsageProxy>> listHorsUsage(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<HorsUsageProxy>> listHorsUsage(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<HorsUsageProxy>> listHorsUsage(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<HorsUsageProxy>> listNonAffectedHorsUsage(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<HorsUsageProxy>> listNonAffectedHorsUsage(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<HorsUsageProxy>> listNonAffectedHorsUsageReverse(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<HorsUsageProxy>> listNonAffectedHorsUsageReverse(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<HorsUsageProxy>> getHorsUsageEmptyList();

	Request<Long> countHorsUsage();
	Request<Long> countHorsUsage(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedHorsUsage(String property);
	Request<Long> countNonAffectedHorsUsage(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedHorsUsageReverse(String property);
	Request<Long> countNonAffectedHorsUsageReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<HorsUsageProxy> entities);
	Request<Void> delete(HorsUsageProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
