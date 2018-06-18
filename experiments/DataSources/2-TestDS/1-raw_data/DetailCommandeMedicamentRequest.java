package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.DetailCommandeMedicamentHandler;
import org.imogene.epicam.shared.proxy.DetailCommandeMedicamentProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the DetailCommandeMedicament Service.
 * @author Medes-IMPS
 */
@Service(value = DetailCommandeMedicamentHandler.class, locator = SpringServiceLocator.class)
public interface DetailCommandeMedicamentRequest extends ImogEntityRequest {

	Request<DetailCommandeMedicamentProxy> findById(String id);

	Request<Void> save(DetailCommandeMedicamentProxy c, boolean isNew);

	Request<List<DetailCommandeMedicamentProxy>> listDetailCommandeMedicament(
			String sortProperty, boolean sortOrder);
	Request<List<DetailCommandeMedicamentProxy>> listDetailCommandeMedicament(
			int first, int max, String sortProperty, boolean sortOrder);
	Request<List<DetailCommandeMedicamentProxy>> listDetailCommandeMedicament(
			int first, int max, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions);
	Request<List<DetailCommandeMedicamentProxy>> listDetailCommandeMedicament(
			int first, int max, String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<DetailCommandeMedicamentProxy>> listNonAffectedDetailCommandeMedicament(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<DetailCommandeMedicamentProxy>> listNonAffectedDetailCommandeMedicament(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<DetailCommandeMedicamentProxy>> listNonAffectedDetailCommandeMedicamentReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<DetailCommandeMedicamentProxy>> listNonAffectedDetailCommandeMedicamentReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<DetailCommandeMedicamentProxy>> getDetailCommandeMedicamentEmptyList();

	Request<Long> countDetailCommandeMedicament();
	Request<Long> countDetailCommandeMedicament(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedDetailCommandeMedicament(String property);
	Request<Long> countNonAffectedDetailCommandeMedicament(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedDetailCommandeMedicamentReverse(
			String property);
	Request<Long> countNonAffectedDetailCommandeMedicamentReverse(
			String property, ImogJunctionProxy criterions);

	Request<Void> delete(Set<DetailCommandeMedicamentProxy> entities);
	Request<Void> delete(DetailCommandeMedicamentProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
