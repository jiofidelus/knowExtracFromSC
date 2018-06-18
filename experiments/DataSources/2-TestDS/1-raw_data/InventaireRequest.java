package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.InventaireHandler;
import org.imogene.epicam.shared.proxy.InventaireProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the Inventaire Service.
 * @author Medes-IMPS
 */
@Service(value = InventaireHandler.class, locator = SpringServiceLocator.class)
public interface InventaireRequest extends ImogEntityRequest {

	Request<InventaireProxy> findById(String id);

	Request<Void> save(InventaireProxy c, boolean isNew);

	Request<List<InventaireProxy>> listInventaire(String sortProperty,
			boolean sortOrder);
	Request<List<InventaireProxy>> listInventaire(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<InventaireProxy>> listInventaire(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<InventaireProxy>> listInventaire(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<InventaireProxy>> listNonAffectedInventaire(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<InventaireProxy>> listNonAffectedInventaire(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<InventaireProxy>> listNonAffectedInventaireReverse(int i,
			int j, String sortProperty, boolean sortOrder, String property);
	Request<List<InventaireProxy>> listNonAffectedInventaireReverse(int i,
			int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<InventaireProxy>> getInventaireEmptyList();

	Request<Long> countInventaire();
	Request<Long> countInventaire(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedInventaire(String property);
	Request<Long> countNonAffectedInventaire(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedInventaireReverse(String property);
	Request<Long> countNonAffectedInventaireReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<InventaireProxy> entities);
	Request<Void> delete(InventaireProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
