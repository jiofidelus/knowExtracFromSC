package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.RendezVousHandler;
import org.imogene.epicam.shared.proxy.RendezVousProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the RendezVous Service.
 * @author Medes-IMPS
 */
@Service(value = RendezVousHandler.class, locator = SpringServiceLocator.class)
public interface RendezVousRequest extends ImogEntityRequest {

	Request<RendezVousProxy> findById(String id);

	Request<Void> save(RendezVousProxy c, boolean isNew);

	Request<List<RendezVousProxy>> listRendezVous(String sortProperty,
			boolean sortOrder);
	Request<List<RendezVousProxy>> listRendezVous(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<RendezVousProxy>> listRendezVous(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<RendezVousProxy>> listRendezVous(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<RendezVousProxy>> listNonAffectedRendezVous(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<RendezVousProxy>> listNonAffectedRendezVous(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<RendezVousProxy>> listNonAffectedRendezVousReverse(int i,
			int j, String sortProperty, boolean sortOrder, String property);
	Request<List<RendezVousProxy>> listNonAffectedRendezVousReverse(int i,
			int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<RendezVousProxy>> getRendezVousEmptyList();

	Request<Long> countRendezVous();
	Request<Long> countRendezVous(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedRendezVous(String property);
	Request<Long> countNonAffectedRendezVous(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedRendezVousReverse(String property);
	Request<Long> countNonAffectedRendezVousReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<RendezVousProxy> entities);
	Request<Void> delete(RendezVousProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
