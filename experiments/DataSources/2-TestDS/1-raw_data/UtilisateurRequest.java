package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.UtilisateurHandler;
import org.imogene.epicam.shared.proxy.UtilisateurProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the Utilisateur Service.
 * @author Medes-IMPS
 */
@Service(value = UtilisateurHandler.class, locator = SpringServiceLocator.class)
public interface UtilisateurRequest extends ImogEntityRequest {

	Request<UtilisateurProxy> findById(String id);

	Request<Void> save(UtilisateurProxy c, boolean isNew);
	Request<Void> save(UtilisateurProxy c, boolean isNew,
			boolean passwordChanged);

	Request<List<UtilisateurProxy>> listUtilisateur(String sortProperty,
			boolean sortOrder);
	Request<List<UtilisateurProxy>> listUtilisateur(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<UtilisateurProxy>> listUtilisateur(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<UtilisateurProxy>> listUtilisateur(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<UtilisateurProxy>> listNonAffectedUtilisateur(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<UtilisateurProxy>> listNonAffectedUtilisateur(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<UtilisateurProxy>> listNonAffectedUtilisateurReverse(int i,
			int j, String sortProperty, boolean sortOrder, String property);
	Request<List<UtilisateurProxy>> listNonAffectedUtilisateurReverse(int i,
			int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<UtilisateurProxy>> getUtilisateurEmptyList();

	Request<Long> countUtilisateur();
	Request<Long> countUtilisateur(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedUtilisateur(String property);
	Request<Long> countNonAffectedUtilisateur(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedUtilisateurReverse(String property);
	Request<Long> countNonAffectedUtilisateurReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<UtilisateurProxy> entities);
	Request<Void> delete(UtilisateurProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
