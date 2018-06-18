package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.EntreeLotHandler;
import org.imogene.epicam.shared.proxy.EntreeLotProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the EntreeLot Service.
 * @author Medes-IMPS
 */
@Service(value = EntreeLotHandler.class, locator = SpringServiceLocator.class)
public interface EntreeLotRequest extends ImogEntityRequest {

	Request<EntreeLotProxy> findById(String id);

	Request<Void> save(EntreeLotProxy c, boolean isNew);

	Request<List<EntreeLotProxy>> listEntreeLot(String sortProperty,
			boolean sortOrder);
	Request<List<EntreeLotProxy>> listEntreeLot(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<EntreeLotProxy>> listEntreeLot(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<EntreeLotProxy>> listEntreeLot(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<EntreeLotProxy>> listNonAffectedEntreeLot(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<EntreeLotProxy>> listNonAffectedEntreeLot(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<EntreeLotProxy>> listNonAffectedEntreeLotReverse(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<EntreeLotProxy>> listNonAffectedEntreeLotReverse(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<EntreeLotProxy>> getEntreeLotEmptyList();

	Request<Long> countEntreeLot();
	Request<Long> countEntreeLot(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedEntreeLot(String property);
	Request<Long> countNonAffectedEntreeLot(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedEntreeLotReverse(String property);
	Request<Long> countNonAffectedEntreeLotReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<EntreeLotProxy> entities);
	Request<Void> delete(EntreeLotProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
