package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.DetailCommandeIntrantHandler;
import org.imogene.epicam.shared.proxy.DetailCommandeIntrantProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the DetailCommandeIntrant Service.
 * @author Medes-IMPS
 */
@Service(value = DetailCommandeIntrantHandler.class, locator = SpringServiceLocator.class)
public interface DetailCommandeIntrantRequest extends ImogEntityRequest {

	Request<DetailCommandeIntrantProxy> findById(String id);

	Request<Void> save(DetailCommandeIntrantProxy c, boolean isNew);

	Request<List<DetailCommandeIntrantProxy>> listDetailCommandeIntrant(
			String sortProperty, boolean sortOrder);
	Request<List<DetailCommandeIntrantProxy>> listDetailCommandeIntrant(
			int first, int max, String sortProperty, boolean sortOrder);
	Request<List<DetailCommandeIntrantProxy>> listDetailCommandeIntrant(
			int first, int max, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions);
	Request<List<DetailCommandeIntrantProxy>> listDetailCommandeIntrant(
			int first, int max, String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<DetailCommandeIntrantProxy>> listNonAffectedDetailCommandeIntrant(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<DetailCommandeIntrantProxy>> listNonAffectedDetailCommandeIntrant(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<DetailCommandeIntrantProxy>> listNonAffectedDetailCommandeIntrantReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<DetailCommandeIntrantProxy>> listNonAffectedDetailCommandeIntrantReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<DetailCommandeIntrantProxy>> getDetailCommandeIntrantEmptyList();

	Request<Long> countDetailCommandeIntrant();
	Request<Long> countDetailCommandeIntrant(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedDetailCommandeIntrant(String property);
	Request<Long> countNonAffectedDetailCommandeIntrant(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedDetailCommandeIntrantReverse(String property);
	Request<Long> countNonAffectedDetailCommandeIntrantReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<DetailCommandeIntrantProxy> entities);
	Request<Void> delete(DetailCommandeIntrantProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
