package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.ReceptionHandler;
import org.imogene.epicam.shared.proxy.ReceptionProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the Reception Service.
 * @author Medes-IMPS
 */
@Service(value = ReceptionHandler.class, locator = SpringServiceLocator.class)
public interface ReceptionRequest extends ImogEntityRequest {

	Request<ReceptionProxy> findById(String id);

	Request<Void> save(ReceptionProxy c, boolean isNew);

	Request<List<ReceptionProxy>> listReception(String sortProperty,
			boolean sortOrder);
	Request<List<ReceptionProxy>> listReception(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<ReceptionProxy>> listReception(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<ReceptionProxy>> listReception(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<ReceptionProxy>> listNonAffectedReception(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<ReceptionProxy>> listNonAffectedReception(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<ReceptionProxy>> listNonAffectedReceptionReverse(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<ReceptionProxy>> listNonAffectedReceptionReverse(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<ReceptionProxy>> getReceptionEmptyList();

	Request<Long> countReception();
	Request<Long> countReception(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedReception(String property);
	Request<Long> countNonAffectedReception(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedReceptionReverse(String property);
	Request<Long> countNonAffectedReceptionReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<ReceptionProxy> entities);
	Request<Void> delete(ReceptionProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
