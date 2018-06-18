package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.QualificationHandler;
import org.imogene.epicam.shared.proxy.QualificationProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the Qualification Service.
 * @author Medes-IMPS
 */
@Service(value = QualificationHandler.class, locator = SpringServiceLocator.class)
public interface QualificationRequest extends ImogEntityRequest {

	Request<QualificationProxy> findById(String id);

	Request<Void> save(QualificationProxy c, boolean isNew);

	Request<List<QualificationProxy>> listQualification(String sortProperty,
			boolean sortOrder);
	Request<List<QualificationProxy>> listQualification(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<QualificationProxy>> listQualification(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<QualificationProxy>> listQualification(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<QualificationProxy>> listNonAffectedQualification(int i,
			int j, String sortProperty, boolean sortOrder, String property);
	Request<List<QualificationProxy>> listNonAffectedQualification(int i,
			int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<QualificationProxy>> listNonAffectedQualificationReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<QualificationProxy>> listNonAffectedQualificationReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<QualificationProxy>> getQualificationEmptyList();

	Request<Long> countQualification();
	Request<Long> countQualification(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedQualification(String property);
	Request<Long> countNonAffectedQualification(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedQualificationReverse(String property);
	Request<Long> countNonAffectedQualificationReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<QualificationProxy> entities);
	Request<Void> delete(QualificationProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
