package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.OutBoxHandler;
import org.imogene.epicam.shared.proxy.OutBoxProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the OutBox Service.
 * @author Medes-IMPS
 */
@Service(value = OutBoxHandler.class, locator = SpringServiceLocator.class)
public interface OutBoxRequest extends ImogEntityRequest {

	Request<OutBoxProxy> findById(String id);

	Request<Void> save(OutBoxProxy c, boolean isNew);

	Request<List<OutBoxProxy>> listOutBox(String sortProperty, boolean sortOrder);
	Request<List<OutBoxProxy>> listOutBox(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<OutBoxProxy>> listOutBox(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<OutBoxProxy>> listOutBox(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<OutBoxProxy>> listNonAffectedOutBox(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<OutBoxProxy>> listNonAffectedOutBox(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<OutBoxProxy>> listNonAffectedOutBoxReverse(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<OutBoxProxy>> listNonAffectedOutBoxReverse(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<OutBoxProxy>> getOutBoxEmptyList();

	Request<Long> countOutBox();
	Request<Long> countOutBox(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedOutBox(String property);
	Request<Long> countNonAffectedOutBox(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedOutBoxReverse(String property);
	Request<Long> countNonAffectedOutBoxReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<OutBoxProxy> entities);
	Request<Void> delete(OutBoxProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
