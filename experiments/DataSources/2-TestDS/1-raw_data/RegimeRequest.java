package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.RegimeHandler;
import org.imogene.epicam.shared.proxy.RegimeProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the Regime Service.
 * @author Medes-IMPS
 */
@Service(value = RegimeHandler.class, locator = SpringServiceLocator.class)
public interface RegimeRequest extends ImogEntityRequest {

	Request<RegimeProxy> findById(String id);

	Request<Void> save(RegimeProxy c, boolean isNew);

	Request<List<RegimeProxy>> listRegime(String sortProperty, boolean sortOrder);
	Request<List<RegimeProxy>> listRegime(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<RegimeProxy>> listRegime(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<RegimeProxy>> listRegime(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<RegimeProxy>> listNonAffectedRegime(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<RegimeProxy>> listNonAffectedRegime(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<RegimeProxy>> listNonAffectedRegimeReverse(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<RegimeProxy>> listNonAffectedRegimeReverse(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<RegimeProxy>> getRegimeEmptyList();

	Request<Long> countRegime();
	Request<Long> countRegime(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedRegime(String property);
	Request<Long> countNonAffectedRegime(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedRegimeReverse(String property);
	Request<Long> countNonAffectedRegimeReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<RegimeProxy> entities);
	Request<Void> delete(RegimeProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
