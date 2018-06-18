package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.RavitaillementHandler;
import org.imogene.epicam.shared.proxy.RavitaillementProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the Ravitaillement Service.
 * @author Medes-IMPS
 */
@Service(value = RavitaillementHandler.class, locator = SpringServiceLocator.class)
public interface RavitaillementRequest extends ImogEntityRequest {

	Request<RavitaillementProxy> findById(String id);

	Request<Void> save(RavitaillementProxy c, boolean isNew);

	Request<List<RavitaillementProxy>> listRavitaillement(String sortProperty,
			boolean sortOrder);
	Request<List<RavitaillementProxy>> listRavitaillement(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<RavitaillementProxy>> listRavitaillement(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<RavitaillementProxy>> listRavitaillement(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<RavitaillementProxy>> listNonAffectedRavitaillement(int i,
			int j, String sortProperty, boolean sortOrder, String property);
	Request<List<RavitaillementProxy>> listNonAffectedRavitaillement(int i,
			int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<RavitaillementProxy>> listNonAffectedRavitaillementReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<RavitaillementProxy>> listNonAffectedRavitaillementReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<RavitaillementProxy>> getRavitaillementEmptyList();

	Request<Long> countRavitaillement();
	Request<Long> countRavitaillement(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedRavitaillement(String property);
	Request<Long> countNonAffectedRavitaillement(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedRavitaillementReverse(String property);
	Request<Long> countNonAffectedRavitaillementReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<RavitaillementProxy> entities);
	Request<Void> delete(RavitaillementProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
