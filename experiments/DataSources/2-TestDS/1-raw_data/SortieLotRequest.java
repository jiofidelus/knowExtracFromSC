package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.SortieLotHandler;
import org.imogene.epicam.shared.proxy.SortieLotProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the SortieLot Service.
 * @author Medes-IMPS
 */
@Service(value = SortieLotHandler.class, locator = SpringServiceLocator.class)
public interface SortieLotRequest extends ImogEntityRequest {

	Request<SortieLotProxy> findById(String id);

	Request<Void> save(SortieLotProxy c, boolean isNew);

	Request<List<SortieLotProxy>> listSortieLot(String sortProperty,
			boolean sortOrder);
	Request<List<SortieLotProxy>> listSortieLot(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<SortieLotProxy>> listSortieLot(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<SortieLotProxy>> listSortieLot(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<SortieLotProxy>> listNonAffectedSortieLot(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<SortieLotProxy>> listNonAffectedSortieLot(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<SortieLotProxy>> listNonAffectedSortieLotReverse(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<SortieLotProxy>> listNonAffectedSortieLotReverse(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<SortieLotProxy>> getSortieLotEmptyList();

	Request<Long> countSortieLot();
	Request<Long> countSortieLot(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedSortieLot(String property);
	Request<Long> countNonAffectedSortieLot(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedSortieLotReverse(String property);
	Request<Long> countNonAffectedSortieLotReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<SortieLotProxy> entities);
	Request<Void> delete(SortieLotProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
