package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.LaboratoireReferenceHandler;
import org.imogene.epicam.shared.proxy.LaboratoireReferenceProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the LaboratoireReference Service.
 * @author Medes-IMPS
 */
@Service(value = LaboratoireReferenceHandler.class, locator = SpringServiceLocator.class)
public interface LaboratoireReferenceRequest extends ImogEntityRequest {

	Request<LaboratoireReferenceProxy> findById(String id);

	Request<Void> save(LaboratoireReferenceProxy c, boolean isNew);

	Request<List<LaboratoireReferenceProxy>> listLaboratoireReference(
			String sortProperty, boolean sortOrder);
	Request<List<LaboratoireReferenceProxy>> listLaboratoireReference(
			int first, int max, String sortProperty, boolean sortOrder);
	Request<List<LaboratoireReferenceProxy>> listLaboratoireReference(
			int first, int max, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions);
	Request<List<LaboratoireReferenceProxy>> listLaboratoireReference(
			int first, int max, String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<LaboratoireReferenceProxy>> listNonAffectedLaboratoireReference(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<LaboratoireReferenceProxy>> listNonAffectedLaboratoireReference(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<LaboratoireReferenceProxy>> listNonAffectedLaboratoireReferenceReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<LaboratoireReferenceProxy>> listNonAffectedLaboratoireReferenceReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<LaboratoireReferenceProxy>> getLaboratoireReferenceEmptyList();

	Request<Long> countLaboratoireReference();
	Request<Long> countLaboratoireReference(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedLaboratoireReference(String property);
	Request<Long> countNonAffectedLaboratoireReference(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedLaboratoireReferenceReverse(String property);
	Request<Long> countNonAffectedLaboratoireReferenceReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<LaboratoireReferenceProxy> entities);
	Request<Void> delete(LaboratoireReferenceProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
