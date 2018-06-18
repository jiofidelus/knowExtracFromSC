package org.imogene.epicam.shared.request ; @Service(value = HorsUsageHandler.class, locator = SpringServiceLocator.class)public interface HorsUsageRequest extends ImogEntityRequest { Request<HorsUsageProxy> findById(String id) ;  Request<Void> save(HorsUsageProxy c, boolean isNew) ;  Request<List<HorsUsageProxy>> listHorsUsage(String sortProperty, boolean sortOrder) ;  Request<List<HorsUsageProxy>> listHorsUsage(int first, int max, String sortProperty, boolean sortOrder) ;  Request<List<HorsUsageProxy>> listHorsUsage(int first, int max, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions) ;  Request<List<HorsUsageProxy>> listHorsUsage(int first, int max, String sortProperty, boolean sortOrder, List<BasicCriteriaProxy> criterions) ;  Request<List<HorsUsageProxy>> listNonAffectedHorsUsage(int i, int j, String sortProperty, boolean sortOrder, String property) ;  Request<List<HorsUsageProxy>> listNonAffectedHorsUsage(int i, int j, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions, String property) ;  Request<List<HorsUsageProxy>> listNonAffectedHorsUsageReverse(int i, int j, String sortProperty, boolean sortOrder, String property) ;  Request<List<HorsUsageProxy>> listNonAffectedHorsUsageReverse(int i, int j, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions, String property) ;  Request<List<HorsUsageProxy>> getHorsUsageEmptyList() ;  Request<Long> countHorsUsage() ;  Request<Long> countHorsUsage(ImogJunctionProxy criterions) ;  Request<Long> countNonAffectedHorsUsage(String property) ;  Request<Long> countNonAffectedHorsUsage(String property, ImogJunctionProxy criterions) ;  Request<Long> countNonAffectedHorsUsageReverse(String property) ;  Request<Long> countNonAffectedHorsUsageReverse(String property, ImogJunctionProxy criterions) ;  Request<Void> delete(Set<HorsUsageProxy> entities) ;  Request<Void> delete(HorsUsageProxy entity) ;  Request<Void> save(ImogBeanProxy entity, boolean isNew) ;  Request<Void> delete(ImogBeanProxy entity) ; }