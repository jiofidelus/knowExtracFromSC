package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.LotHandler;
import org.imogene.epicam.shared.proxy.LotProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the Lot Service.
 * @author Medes-IMPS
 */
@Service(value = LotHandler.class, locator = SpringServiceLocator.class)
public interface LotRequest extends ImogEntityRequest {

	Request<LotProxy> findById(String id);

	Request<Void> save(LotProxy c, boolean isNew);

	Request<List<LotProxy>> listLot(String sortProperty, boolean sortOrder);
	Request<List<LotProxy>> listLot(int first, int max, String sortProperty,
			boolean sortOrder);
	Request<List<LotProxy>> listLot(int first, int max, String sortProperty,
			boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<LotProxy>> listLot(int first, int max, String sortProperty,
			boolean sortOrder, List<BasicCriteriaProxy> criterions);
	Request<List<LotProxy>> listNonAffectedLot(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<LotProxy>> listNonAffectedLot(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<LotProxy>> listNonAffectedLotReverse(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<LotProxy>> listNonAffectedLotReverse(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<LotProxy>> getLotEmptyList();

	Request<Long> countLot();
	Request<Long> countLot(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedLot(String property);
	Request<Long> countNonAffectedLot(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedLotReverse(String property);
	Request<Long> countNonAffectedLotReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<LotProxy> entities);
	Request<Void> delete(LotProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

	Request<Boolean> isLotUniqueForCdt(String numero, String cdtId);
}
