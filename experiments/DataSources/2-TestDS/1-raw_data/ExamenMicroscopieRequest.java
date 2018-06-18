package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.ExamenMicroscopieHandler;
import org.imogene.epicam.shared.proxy.ExamenMicroscopieProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the ExamenMicroscopie Service.
 * @author Medes-IMPS
 */
@Service(value = ExamenMicroscopieHandler.class, locator = SpringServiceLocator.class)
public interface ExamenMicroscopieRequest extends ImogEntityRequest {

	Request<ExamenMicroscopieProxy> findById(String id);

	Request<Void> save(ExamenMicroscopieProxy c, boolean isNew);

	Request<List<ExamenMicroscopieProxy>> listExamenMicroscopie(
			String sortProperty, boolean sortOrder);
	Request<List<ExamenMicroscopieProxy>> listExamenMicroscopie(int first,
			int max, String sortProperty, boolean sortOrder);
	Request<List<ExamenMicroscopieProxy>> listExamenMicroscopie(int first,
			int max, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions);
	Request<List<ExamenMicroscopieProxy>> listExamenMicroscopie(int first,
			int max, String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<ExamenMicroscopieProxy>> listNonAffectedExamenMicroscopie(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<ExamenMicroscopieProxy>> listNonAffectedExamenMicroscopie(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<ExamenMicroscopieProxy>> listNonAffectedExamenMicroscopieReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<ExamenMicroscopieProxy>> listNonAffectedExamenMicroscopieReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<ExamenMicroscopieProxy>> getExamenMicroscopieEmptyList();

	Request<Long> countExamenMicroscopie();
	Request<Long> countExamenMicroscopie(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedExamenMicroscopie(String property);
	Request<Long> countNonAffectedExamenMicroscopie(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedExamenMicroscopieReverse(String property);
	Request<Long> countNonAffectedExamenMicroscopieReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<ExamenMicroscopieProxy> entities);
	Request<Void> delete(ExamenMicroscopieProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
