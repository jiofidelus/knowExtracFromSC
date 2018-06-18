package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.DistrictSanteHandler;
import org.imogene.epicam.shared.proxy.DistrictSanteProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the DistrictSante Service.
 * @author Medes-IMPS
 */
@Service(value = DistrictSanteHandler.class, locator = SpringServiceLocator.class)
public interface DistrictSanteRequest extends ImogEntityRequest {

	Request<DistrictSanteProxy> findById(String id);

	Request<Void> save(DistrictSanteProxy c, boolean isNew);

	Request<List<DistrictSanteProxy>> listDistrictSante(String sortProperty,
			boolean sortOrder);
	Request<List<DistrictSanteProxy>> listDistrictSante(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<DistrictSanteProxy>> listDistrictSante(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<DistrictSanteProxy>> listDistrictSante(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<DistrictSanteProxy>> listNonAffectedDistrictSante(int i,
			int j, String sortProperty, boolean sortOrder, String property);
	Request<List<DistrictSanteProxy>> listNonAffectedDistrictSante(int i,
			int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<DistrictSanteProxy>> listNonAffectedDistrictSanteReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<DistrictSanteProxy>> listNonAffectedDistrictSanteReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<DistrictSanteProxy>> getDistrictSanteEmptyList();

	Request<Long> countDistrictSante();
	Request<Long> countDistrictSante(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedDistrictSante(String property);
	Request<Long> countNonAffectedDistrictSante(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedDistrictSanteReverse(String property);
	Request<Long> countNonAffectedDistrictSanteReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<DistrictSanteProxy> entities);
	Request<Void> delete(DistrictSanteProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
