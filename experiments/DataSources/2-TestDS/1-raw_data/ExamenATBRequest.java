package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.ExamenATBHandler;
import org.imogene.epicam.shared.proxy.ExamenATBProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the ExamenATB Service.
 * @author Medes-IMPS
 */
@Service(value = ExamenATBHandler.class, locator = SpringServiceLocator.class)
public interface ExamenATBRequest extends ImogEntityRequest {

	Request<ExamenATBProxy> findById(String id);

	Request<Void> save(ExamenATBProxy c, boolean isNew);

	Request<List<ExamenATBProxy>> listExamenATB(String sortProperty,
			boolean sortOrder);
	Request<List<ExamenATBProxy>> listExamenATB(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<ExamenATBProxy>> listExamenATB(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<ExamenATBProxy>> listExamenATB(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<ExamenATBProxy>> listNonAffectedExamenATB(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<ExamenATBProxy>> listNonAffectedExamenATB(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<ExamenATBProxy>> listNonAffectedExamenATBReverse(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<ExamenATBProxy>> listNonAffectedExamenATBReverse(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<ExamenATBProxy>> getExamenATBEmptyList();

	Request<Long> countExamenATB();
	Request<Long> countExamenATB(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedExamenATB(String property);
	Request<Long> countNonAffectedExamenATB(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedExamenATBReverse(String property);
	Request<Long> countNonAffectedExamenATBReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<ExamenATBProxy> entities);
	Request<Void> delete(ExamenATBProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
