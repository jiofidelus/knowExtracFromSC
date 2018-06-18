package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.ExamenBiologiqueHandler;
import org.imogene.epicam.shared.proxy.ExamenBiologiqueProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the ExamenBiologique Service.
 * @author Medes-IMPS
 */
@Service(value = ExamenBiologiqueHandler.class, locator = SpringServiceLocator.class)
public interface ExamenBiologiqueRequest extends ImogEntityRequest {

	Request<ExamenBiologiqueProxy> findById(String id);

	Request<Void> save(ExamenBiologiqueProxy c, boolean isNew);

	Request<List<ExamenBiologiqueProxy>> listExamenBiologique(
			String sortProperty, boolean sortOrder);
	Request<List<ExamenBiologiqueProxy>> listExamenBiologique(int first,
			int max, String sortProperty, boolean sortOrder);
	Request<List<ExamenBiologiqueProxy>> listExamenBiologique(int first,
			int max, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions);
	Request<List<ExamenBiologiqueProxy>> listExamenBiologique(int first,
			int max, String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<ExamenBiologiqueProxy>> listNonAffectedExamenBiologique(int i,
			int j, String sortProperty, boolean sortOrder, String property);
	Request<List<ExamenBiologiqueProxy>> listNonAffectedExamenBiologique(int i,
			int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<ExamenBiologiqueProxy>> listNonAffectedExamenBiologiqueReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<ExamenBiologiqueProxy>> listNonAffectedExamenBiologiqueReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<ExamenBiologiqueProxy>> getExamenBiologiqueEmptyList();

	Request<Long> countExamenBiologique();
	Request<Long> countExamenBiologique(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedExamenBiologique(String property);
	Request<Long> countNonAffectedExamenBiologique(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedExamenBiologiqueReverse(String property);
	Request<Long> countNonAffectedExamenBiologiqueReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<ExamenBiologiqueProxy> entities);
	Request<Void> delete(ExamenBiologiqueProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
