package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.DetailInventaireHandler;
import org.imogene.epicam.shared.proxy.DetailInventaireProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the DetailInventaire Service.
 * @author Medes-IMPS
 */
@Service(value = DetailInventaireHandler.class, locator = SpringServiceLocator.class)
public interface DetailInventaireRequest extends ImogEntityRequest {

	Request<DetailInventaireProxy> findById(String id);

	Request<Void> save(DetailInventaireProxy c, boolean isNew);

	Request<List<DetailInventaireProxy>> listDetailInventaire(
			String sortProperty, boolean sortOrder);
	Request<List<DetailInventaireProxy>> listDetailInventaire(int first,
			int max, String sortProperty, boolean sortOrder);
	Request<List<DetailInventaireProxy>> listDetailInventaire(int first,
			int max, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions);
	Request<List<DetailInventaireProxy>> listDetailInventaire(int first,
			int max, String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<DetailInventaireProxy>> listNonAffectedDetailInventaire(int i,
			int j, String sortProperty, boolean sortOrder, String property);
	Request<List<DetailInventaireProxy>> listNonAffectedDetailInventaire(int i,
			int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<DetailInventaireProxy>> listNonAffectedDetailInventaireReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<DetailInventaireProxy>> listNonAffectedDetailInventaireReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<DetailInventaireProxy>> getDetailInventaireEmptyList();

	Request<Long> countDetailInventaire();
	Request<Long> countDetailInventaire(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedDetailInventaire(String property);
	Request<Long> countNonAffectedDetailInventaire(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedDetailInventaireReverse(String property);
	Request<Long> countNonAffectedDetailInventaireReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<DetailInventaireProxy> entities);
	Request<Void> delete(DetailInventaireProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
