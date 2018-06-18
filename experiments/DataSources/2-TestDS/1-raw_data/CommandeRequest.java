package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.CommandeHandler;
import org.imogene.epicam.shared.proxy.CommandeProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the Commande Service.
 * @author Medes-IMPS
 */
@Service(value = CommandeHandler.class, locator = SpringServiceLocator.class)
public interface CommandeRequest extends ImogEntityRequest {

	Request<CommandeProxy> findById(String id);

	Request<Void> save(CommandeProxy c, boolean isNew);

	Request<List<CommandeProxy>> listCommande(String sortProperty,
			boolean sortOrder);
	Request<List<CommandeProxy>> listCommande(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<CommandeProxy>> listCommande(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<CommandeProxy>> listCommande(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<CommandeProxy>> listNonAffectedCommande(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<CommandeProxy>> listNonAffectedCommande(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<CommandeProxy>> listNonAffectedCommandeReverse(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<CommandeProxy>> listNonAffectedCommandeReverse(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<CommandeProxy>> getCommandeEmptyList();

	Request<Long> countCommande();
	Request<Long> countCommande(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedCommande(String property);
	Request<Long> countNonAffectedCommande(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedCommandeReverse(String property);
	Request<Long> countNonAffectedCommandeReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<CommandeProxy> entities);
	Request<Void> delete(CommandeProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
