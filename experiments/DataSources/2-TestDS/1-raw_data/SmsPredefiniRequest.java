package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.SmsPredefiniHandler;
import org.imogene.epicam.shared.proxy.SmsPredefiniProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the SmsPredefini Service.
 * @author Medes-IMPS
 */
@Service(value = SmsPredefiniHandler.class, locator = SpringServiceLocator.class)
public interface SmsPredefiniRequest extends ImogEntityRequest {

	Request<SmsPredefiniProxy> findById(String id);

	Request<Void> save(SmsPredefiniProxy c, boolean isNew);

	Request<List<SmsPredefiniProxy>> listSmsPredefini(String sortProperty,
			boolean sortOrder);
	Request<List<SmsPredefiniProxy>> listSmsPredefini(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<SmsPredefiniProxy>> listSmsPredefini(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<SmsPredefiniProxy>> listSmsPredefini(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<SmsPredefiniProxy>> listNonAffectedSmsPredefini(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<SmsPredefiniProxy>> listNonAffectedSmsPredefini(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<SmsPredefiniProxy>> listNonAffectedSmsPredefiniReverse(int i,
			int j, String sortProperty, boolean sortOrder, String property);
	Request<List<SmsPredefiniProxy>> listNonAffectedSmsPredefiniReverse(int i,
			int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<SmsPredefiniProxy>> getSmsPredefiniEmptyList();

	Request<Long> countSmsPredefini();
	Request<Long> countSmsPredefini(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedSmsPredefini(String property);
	Request<Long> countNonAffectedSmsPredefini(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedSmsPredefiniReverse(String property);
	Request<Long> countNonAffectedSmsPredefiniReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<SmsPredefiniProxy> entities);
	Request<Void> delete(SmsPredefiniProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
