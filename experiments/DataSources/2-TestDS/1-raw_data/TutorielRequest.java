package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.TutorielHandler;
import org.imogene.epicam.shared.proxy.TutorielProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the Tutoriel Service.
 * @author Medes-IMPS
 */
@Service(value = TutorielHandler.class, locator = SpringServiceLocator.class)
public interface TutorielRequest extends ImogEntityRequest {

	Request<TutorielProxy> findById(String id);

	Request<Void> save(TutorielProxy c, boolean isNew);

	Request<List<TutorielProxy>> listTutoriel(String sortProperty,
			boolean sortOrder);
	Request<List<TutorielProxy>> listTutoriel(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<TutorielProxy>> listTutoriel(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<TutorielProxy>> listTutoriel(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<TutorielProxy>> listNonAffectedTutoriel(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<TutorielProxy>> listNonAffectedTutoriel(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<TutorielProxy>> listNonAffectedTutorielReverse(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<TutorielProxy>> listNonAffectedTutorielReverse(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<TutorielProxy>> getTutorielEmptyList();

	Request<Long> countTutoriel();
	Request<Long> countTutoriel(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedTutoriel(String property);
	Request<Long> countNonAffectedTutoriel(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedTutorielReverse(String property);
	Request<Long> countNonAffectedTutorielReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<TutorielProxy> entities);
	Request<Void> delete(TutorielProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
