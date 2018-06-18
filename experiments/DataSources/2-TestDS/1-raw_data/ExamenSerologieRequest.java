package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.ExamenSerologieHandler;
import org.imogene.epicam.shared.proxy.ExamenSerologieProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the ExamenSerologie Service.
 * @author Medes-IMPS
 */
@Service(value = ExamenSerologieHandler.class, locator = SpringServiceLocator.class)
public interface ExamenSerologieRequest extends ImogEntityRequest {

	Request<ExamenSerologieProxy> findById(String id);

	Request<Void> save(ExamenSerologieProxy c, boolean isNew);

	Request<List<ExamenSerologieProxy>> listExamenSerologie(
			String sortProperty, boolean sortOrder);
	Request<List<ExamenSerologieProxy>> listExamenSerologie(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<ExamenSerologieProxy>> listExamenSerologie(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<ExamenSerologieProxy>> listExamenSerologie(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<ExamenSerologieProxy>> listNonAffectedExamenSerologie(int i,
			int j, String sortProperty, boolean sortOrder, String property);
	Request<List<ExamenSerologieProxy>> listNonAffectedExamenSerologie(int i,
			int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<ExamenSerologieProxy>> listNonAffectedExamenSerologieReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<ExamenSerologieProxy>> listNonAffectedExamenSerologieReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<ExamenSerologieProxy>> getExamenSerologieEmptyList();

	Request<Long> countExamenSerologie();
	Request<Long> countExamenSerologie(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedExamenSerologie(String property);
	Request<Long> countNonAffectedExamenSerologie(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedExamenSerologieReverse(String property);
	Request<Long> countNonAffectedExamenSerologieReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<ExamenSerologieProxy> entities);
	Request<Void> delete(ExamenSerologieProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
