package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.IntrantHandler;
import org.imogene.epicam.shared.proxy.IntrantProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the Intrant Service.
 * @author Medes-IMPS
 */
@Service(value = IntrantHandler.class, locator = SpringServiceLocator.class)
public interface IntrantRequest extends ImogEntityRequest {

	Request<IntrantProxy> findById(String id);

	Request<Void> save(IntrantProxy c, boolean isNew);

	Request<List<IntrantProxy>> listIntrant(String sortProperty,
			boolean sortOrder);
	Request<List<IntrantProxy>> listIntrant(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<IntrantProxy>> listIntrant(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<IntrantProxy>> listIntrant(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<IntrantProxy>> listNonAffectedIntrant(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<IntrantProxy>> listNonAffectedIntrant(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<IntrantProxy>> listNonAffectedIntrantReverse(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<IntrantProxy>> listNonAffectedIntrantReverse(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<IntrantProxy>> getIntrantEmptyList();

	Request<Long> countIntrant();
	Request<Long> countIntrant(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedIntrant(String property);
	Request<Long> countNonAffectedIntrant(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedIntrantReverse(String property);
	Request<Long> countNonAffectedIntrantReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<IntrantProxy> entities);
	Request<Void> delete(IntrantProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
