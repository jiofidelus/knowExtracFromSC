package org.imogene.epicam.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;
import java.util.Set;

import org.imogene.epicam.server.handler.MedicamentHandler;
import org.imogene.epicam.shared.proxy.MedicamentProxy;
import org.imogene.web.server.locator.SpringServiceLocator;
import org.imogene.web.shared.proxy.ImogBeanProxy;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

/**
 * Source of request objects for the Medicament Service.
 * @author Medes-IMPS
 */
@Service(value = MedicamentHandler.class, locator = SpringServiceLocator.class)
public interface MedicamentRequest extends ImogEntityRequest {

	Request<MedicamentProxy> findById(String id);

	Request<Void> save(MedicamentProxy c, boolean isNew);

	Request<List<MedicamentProxy>> listMedicament(String sortProperty,
			boolean sortOrder);
	Request<List<MedicamentProxy>> listMedicament(int first, int max,
			String sortProperty, boolean sortOrder);
	Request<List<MedicamentProxy>> listMedicament(int first, int max,
			String sortProperty, boolean sortOrder, ImogJunctionProxy criterions);
	Request<List<MedicamentProxy>> listMedicament(int first, int max,
			String sortProperty, boolean sortOrder,
			List<BasicCriteriaProxy> criterions);
	Request<List<MedicamentProxy>> listNonAffectedMedicament(int i, int j,
			String sortProperty, boolean sortOrder, String property);
	Request<List<MedicamentProxy>> listNonAffectedMedicament(int i, int j,
			String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<MedicamentProxy>> listNonAffectedMedicamentReverse(int i,
			int j, String sortProperty, boolean sortOrder, String property);
	Request<List<MedicamentProxy>> listNonAffectedMedicamentReverse(int i,
			int j, String sortProperty, boolean sortOrder,
			ImogJunctionProxy criterions, String property);
	Request<List<MedicamentProxy>> getMedicamentEmptyList();

	Request<Long> countMedicament();
	Request<Long> countMedicament(ImogJunctionProxy criterions);
	Request<Long> countNonAffectedMedicament(String property);
	Request<Long> countNonAffectedMedicament(String property,
			ImogJunctionProxy criterions);
	Request<Long> countNonAffectedMedicamentReverse(String property);
	Request<Long> countNonAffectedMedicamentReverse(String property,
			ImogJunctionProxy criterions);

	Request<Void> delete(Set<MedicamentProxy> entities);
	Request<Void> delete(MedicamentProxy entity);

	Request<Void> save(ImogBeanProxy entity, boolean isNew);
	Request<Void> delete(ImogBeanProxy entity);

}
