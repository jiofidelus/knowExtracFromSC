package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.FormationHandler;
import org.imogene.epicam.shared.proxy.FormationProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the Formation Service.
 * @author Medes-IMPS
 */
@Service(value = FormationHandler.class, locator = SpringServiceLocator.class)
public interface FormationRequest extends ImogEntityRequest {

	Request<FormationProxy> findById(String id);

	Request<Void> save(FormationProxy c, boolean isNew);

	Request<List<FormationProxy>> listFormation(String sortProperty,
			boolean sortOrder);
	Request<List<FormationProxy>> listFormation(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<FormationProxy>> listFormation(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<FormationProxy>> listFormation(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<FormationProxy>> listNonAffectedFormation(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<FormationProxy>> listNonAffectedFormation(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<FormationProxy>> listNonAffectedFormationReverse(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<FormationProxy>> listNonAffectedFormationReverse(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<FormationProxy>> getFormationEmptyList();

	Request<Long> countFormation();
	Request<Long> countFormation(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedFormation(String property);
	Request<Long> countNonAffectedFormation(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedFormationReverse(String property);
	Request<Long> countNonAffectedFormationReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<FormationProxy> entities);
	Request<Void> delete(FormationProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
