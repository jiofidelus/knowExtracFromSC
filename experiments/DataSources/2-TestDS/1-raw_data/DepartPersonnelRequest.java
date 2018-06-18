package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.DepartPersonnelHandler;
import org.imogene.epicam.shared.proxy.DepartPersonnelProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the DepartPersonnel Service.
 * @author Medes-IMPS
 */
@Service(value = DepartPersonnelHandler.class, locator = SpringServiceLocator.class)
public interface DepartPersonnelRequest extends ImogEntityRequest {

	Request<DepartPersonnelProxy> findById(String id);

	Request<Void> save(DepartPersonnelProxy c, boolean isNew);

	Request<List<DepartPersonnelProxy>> listDepartPersonnel(
			String sortProperty, boolean sortOrder);
	Request<List<DepartPersonnelProxy>> listDepartPersonnel(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<DepartPersonnelProxy>> listDepartPersonnel(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<DepartPersonnelProxy>> listDepartPersonnel(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<DepartPersonnelProxy>> listNonAffectedDepartPersonnel(int i,
			int j, String sortProperty, boolean sortOrder, String property);
	Request<List<DepartPersonnelProxy>> listNonAffectedDepartPersonnel(int i,
			int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<DepartPersonnelProxy>> listNonAffectedDepartPersonnelReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<DepartPersonnelProxy>> listNonAffectedDepartPersonnelReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<DepartPersonnelProxy>> getDepartPersonnelEmptyList();

	Request<Long> countDepartPersonnel();
	Request<Long> countDepartPersonnel(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedDepartPersonnel(String property);
	Request<Long> countNonAffectedDepartPersonnel(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedDepartPersonnelReverse(String property);
	Request<Long> countNonAffectedDepartPersonnelReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<DepartPersonnelProxy> entities);
	Request<Void> delete(DepartPersonnelProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
