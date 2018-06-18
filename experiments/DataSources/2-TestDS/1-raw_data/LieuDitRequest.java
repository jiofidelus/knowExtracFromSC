package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.LieuDitHandler;
import org.imogene.epicam.shared.proxy.LieuDitProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the LieuDit Service.
 * @author Medes-IMPS
 */
@Service(value = LieuDitHandler.class, locator = SpringServiceLocator.class)
public interface LieuDitRequest extends ImogEntityRequest {

	Request<LieuDitProxy> findById(String id);

	Request<Void> save(LieuDitProxy c, boolean isNew);

	Request<List<LieuDitProxy>> listLieuDit(String sortProperty,
			boolean sortOrder);
	Request<List<LieuDitProxy>> listLieuDit(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<LieuDitProxy>> listLieuDit(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<LieuDitProxy>> listLieuDit(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<LieuDitProxy>> listNonAffectedLieuDit(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<LieuDitProxy>> listNonAffectedLieuDit(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<LieuDitProxy>> listNonAffectedLieuDitReverse(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<LieuDitProxy>> listNonAffectedLieuDitReverse(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<LieuDitProxy>> getLieuDitEmptyList();

	Request<Long> countLieuDit();
	Request<Long> countLieuDit(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedLieuDit(String property);
	Request<Long> countNonAffectedLieuDit(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedLieuDitReverse(String property);
	Request<Long> countNonAffectedLieuDitReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<LieuDitProxy> entities);
	Request<Void> delete(LieuDitProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
