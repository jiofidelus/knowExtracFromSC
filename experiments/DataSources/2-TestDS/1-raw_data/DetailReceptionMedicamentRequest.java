package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.DetailReceptionMedicamentHandler;
import org.imogene.epicam.shared.proxy.DetailReceptionMedicamentProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the DetailReceptionMedicament Service.
 * @author Medes-IMPS
 */
@Service(value = DetailReceptionMedicamentHandler.class, locator = SpringServiceLocator.class)
public interface DetailReceptionMedicamentRequest extends ImogEntityRequest {

	Request<DetailReceptionMedicamentProxy> findById(String id);

	Request<Void> save(DetailReceptionMedicamentProxy c, boolean isNew);

	Request<List<DetailReceptionMedicamentProxy>> listDetailReceptionMedicament(
			String sortProperty, boolean sortOrder);
	Request<List<DetailReceptionMedicamentProxy>> listDetailReceptionMedicament(
			int first, int max, String sortProperty, boolean sortOrder);
	Request<List<DetailReceptionMedicamentProxy>> listDetailReceptionMedicament(
			int first, int max, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions);
	Request<List<DetailReceptionMedicamentProxy>> listDetailReceptionMedicament(
			int first, int max, String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<DetailReceptionMedicamentProxy>> listNonAffectedDetailReceptionMedicament(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<DetailReceptionMedicamentProxy>> listNonAffectedDetailReceptionMedicament(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<DetailReceptionMedicamentProxy>> listNonAffectedDetailReceptionMedicamentReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<DetailReceptionMedicamentProxy>> listNonAffectedDetailReceptionMedicamentReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<DetailReceptionMedicamentProxy>> getDetailReceptionMedicamentEmptyList();

	Request<Long> countDetailReceptionMedicament();
	Request<Long> countDetailReceptionMedicament(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedDetailReceptionMedicament(String property);
	Request<Long> countNonAffectedDetailReceptionMedicament(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedDetailReceptionMedicamentReverse(
			String property);
	Request<Long> countNonAffectedDetailReceptionMedicamentReverse(
			String property, ImogJunctionProxy criterions);

	Request<Void> delete(Set<DetailReceptionMedicamentProxy> entities);
	Request<Void> delete(DetailReceptionMedicamentProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
