package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.PriseMedicamentRegimeHandler;
import org.imogene.epicam.shared.proxy.PriseMedicamentRegimeProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the PriseMedicamentRegime Service.
 * @author Medes-IMPS
 */
@Service(value = PriseMedicamentRegimeHandler.class, locator = SpringServiceLocator.class)
public interface PriseMedicamentRegimeRequest extends ImogEntityRequest {

	Request<PriseMedicamentRegimeProxy> findById(String id);

	Request<Void> save(PriseMedicamentRegimeProxy c, boolean isNew);

	Request<List<PriseMedicamentRegimeProxy>> listPriseMedicamentRegime(
			String sortProperty, boolean sortOrder);
	Request<List<PriseMedicamentRegimeProxy>> listPriseMedicamentRegime(
			int first, int max, String sortProperty, boolean sortOrder);
	Request<List<PriseMedicamentRegimeProxy>> listPriseMedicamentRegime(
			int first, int max, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions);
	Request<List<PriseMedicamentRegimeProxy>> listPriseMedicamentRegime(
			int first, int max, String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<PriseMedicamentRegimeProxy>> listNonAffectedPriseMedicamentRegime(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<PriseMedicamentRegimeProxy>> listNonAffectedPriseMedicamentRegime(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<PriseMedicamentRegimeProxy>> listNonAffectedPriseMedicamentRegimeReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property);
	Request<List<PriseMedicamentRegimeProxy>> listNonAffectedPriseMedicamentRegimeReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<PriseMedicamentRegimeProxy>> getPriseMedicamentRegimeEmptyList();

	Request<Long> countPriseMedicamentRegime();
	Request<Long> countPriseMedicamentRegime(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedPriseMedicamentRegime(String property);
	Request<Long> countNonAffectedPriseMedicamentRegime(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedPriseMedicamentRegimeReverse(String property);
	Request<Long> countNonAffectedPriseMedicamentRegimeReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<PriseMedicamentRegimeProxy> entities);
	Request<Void> delete(PriseMedicamentRegimeProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
