package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.RegionHandler;
import org.imogene.epicam.shared.proxy.RegionProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the Region Service.
 * @author Medes-IMPS
 */
@Service(value = RegionHandler.class, locator = SpringServiceLocator.class)
public interface RegionRequest extends ImogEntityRequest {

	Request<RegionProxy> findById(String id);

	Request<Void> save(RegionProxy c, boolean isNew);

	Request<List<RegionProxy>> listRegion(String sortProperty, boolean sortOrder);
	Request<List<RegionProxy>> listRegion(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<RegionProxy>> listRegion(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<RegionProxy>> listRegion(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<RegionProxy>> listNonAffectedRegion(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<RegionProxy>> listNonAffectedRegion(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<RegionProxy>> listNonAffectedRegionReverse(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<RegionProxy>> listNonAffectedRegionReverse(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<RegionProxy>> getRegionEmptyList();

	Request<Long> countRegion();
	Request<Long> countRegion(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedRegion(String property);
	Request<Long> countNonAffectedRegion(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedRegionReverse(String property);
	Request<Long> countNonAffectedRegionReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<RegionProxy> entities);
	Request<Void> delete(RegionProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
