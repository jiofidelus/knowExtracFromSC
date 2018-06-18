package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.DetailReceptionIntrantHandler;
import org.imogene.epicam.shared.proxy.DetailReceptionIntrantProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the DetailReceptionIntrant Service.
 * @author Medes-IMPS
 */
@Service(value = DetailReceptionIntrantHandler.class, locator = SpringServiceLocator.class)
public interface DetailReceptionIntrantRequest extends ImogEntityRequest {

	Request<DetailReceptionIntrantProxy> findById(String id);

	Request<Void> save(DetailReceptionIntrantProxy c, boolean isNew);

	Request<List<DetailReceptionIntrantProxy>> listDetailReceptionIntrant(
			String sortProperty, boolean sortOrder);
	Request<List<DetailReceptionIntrantProxy>> listDetailReceptionIntrant(
			int first, int max, String sortProperty, boolean sortOrder);
	Request<List<DetailReceptionIntrantProxy>> listDetailReceptionIntrant(
			int first, int max, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions);
	Request<List<DetailReceptionIntrantProxy>> listDetailReceptionIntrant(
			int first, int max, String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<DetailReceptionIntrantProxy>> listNonAffectedDetailReceptionIntrant(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<DetailReceptionIntrantProxy>> listNonAffectedDetailReceptionIntrant(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<DetailReceptionIntrantProxy>> listNonAffectedDetailReceptionIntrantReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<DetailReceptionIntrantProxy>> listNonAffectedDetailReceptionIntrantReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<DetailReceptionIntrantProxy>> getDetailReceptionIntrantEmptyList();

	Request<Long> countDetailReceptionIntrant();
	Request<Long> countDetailReceptionIntrant(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedDetailReceptionIntrant(String property);
	Request<Long> countNonAffectedDetailReceptionIntrant(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedDetailReceptionIntrantReverse(String property);
	Request<Long> countNonAffectedDetailReceptionIntrantReverse(
			String property, ImogJunctionProxy criterions);

	Request<Void> delete(Set<DetailReceptionIntrantProxy> entities);
	Request<Void> delete(DetailReceptionIntrantProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
