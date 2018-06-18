package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.TransfertReferenceHandler;
import org.imogene.epicam.shared.proxy.TransfertReferenceProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the TransfertReference Service.
 * @author Medes-IMPS
 */
@Service(value = TransfertReferenceHandler.class, locator = SpringServiceLocator.class)
public interface TransfertReferenceRequest extends ImogEntityRequest {

	Request<TransfertReferenceProxy> findById(String id);

	Request<Void> save(TransfertReferenceProxy c, boolean isNew);

	Request<List<TransfertReferenceProxy>> listTransfertReference(
			String sortProperty, boolean sortOrder);
	Request<List<TransfertReferenceProxy>> listTransfertReference(int first,
			int max, String sortProperty, boolean sortOrder);
	Request<List<TransfertReferenceProxy>> listTransfertReference(int first,
			int max, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions);
	Request<List<TransfertReferenceProxy>> listTransfertReference(int first,
			int max, String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<TransfertReferenceProxy>> listNonAffectedTransfertReference(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<TransfertReferenceProxy>> listNonAffectedTransfertReference(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<TransfertReferenceProxy>> listNonAffectedTransfertReferenceReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<TransfertReferenceProxy>> listNonAffectedTransfertReferenceReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<TransfertReferenceProxy>> getTransfertReferenceEmptyList();

	Request<Long> countTransfertReference();
	Request<Long> countTransfertReference(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedTransfertReference(String property);
	Request<Long> countNonAffectedTransfertReference(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedTransfertReferenceReverse(String property);
	Request<Long> countNonAffectedTransfertReferenceReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<TransfertReferenceProxy> entities);
	Request<Void> delete(TransfertReferenceProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
