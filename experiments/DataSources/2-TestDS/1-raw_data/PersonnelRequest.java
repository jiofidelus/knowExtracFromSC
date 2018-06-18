package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.PersonnelHandler;
import org.imogene.epicam.shared.proxy.PersonnelProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the Personnel Service.
 * @author Medes-IMPS
 */
@Service(value = PersonnelHandler.class, locator = SpringServiceLocator.class)
public interface PersonnelRequest extends ImogEntityRequest {

	Request<PersonnelProxy> findById(String id);

	Request<Void> save(PersonnelProxy c, boolean isNew);
	Request<Void> save(PersonnelProxy c, boolean isNew, boolean passwordChanged);

	Request<List<PersonnelProxy>> listPersonnel(String sortProperty,
			boolean sortOrder);
	Request<List<PersonnelProxy>> listPersonnel(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<PersonnelProxy>> listPersonnel(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<PersonnelProxy>> listPersonnel(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<PersonnelProxy>> listNonAffectedPersonnel(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<PersonnelProxy>> listNonAffectedPersonnel(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<PersonnelProxy>> listNonAffectedPersonnelReverse(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<PersonnelProxy>> listNonAffectedPersonnelReverse(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<PersonnelProxy>> getPersonnelEmptyList();

	Request<Long> countPersonnel();
	Request<Long> countPersonnel(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedPersonnel(String property);
	Request<Long> countNonAffectedPersonnel(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedPersonnelReverse(String property);
	Request<Long> countNonAffectedPersonnelReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<PersonnelProxy> entities);
	Request<Void> delete(PersonnelProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
