package org.imogene.admin.shared.request ; @Service(value = EntityProfileHandler.class, locator = SpringServiceLocator.class)public interface EntityProfileRequest extends ImogEntityRequest { Request<EntityProfileProxy> findById(String id) ;  Request<Void> save(EntityProfileProxy c, boolean isNew) ;  Request<List<EntityProfileProxy>> listEntityProfile(String sortProperty, boolean sortOrder) ;  Request<List<EntityProfileProxy>> listEntityProfile(int first, int max, String sortProperty, boolean sortOrder) ;  Request<List<EntityProfileProxy>> listEntityProfile(int first, int max, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions) ;  Request<List<EntityProfileProxy>> listEntityProfile(int first, int max, String sortProperty, boolean sortOrder, List<BasicCriteriaProxy> criterions) ;  Request<List<EntityProfileProxy>> listNonAffectedEntityProfile(int i, int j, String sortProperty, boolean sortOrder, String property) ;  Request<List<EntityProfileProxy>> listNonAffectedEntityProfile(int i, int j, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions, String property) ;  Request<List<EntityProfileProxy>> listNonAffectedEntityProfileReverse(int i, int j, String sortProperty, boolean sortOrder, String property) ;  Request<List<EntityProfileProxy>> listNonAffectedEntityProfileReverse(int i, int j, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions, String property) ;  Request<List<EntityProfileProxy>> getEntityProfileEmptyList() ;  Request<Long> countEntityProfile() ;  Request<Long> countEntityProfile(ImogJunctionProxy criterions) ;  Request<Long> countNonAffectedEntityProfile(String property) ;  Request<Long> countNonAffectedEntityProfile(String property, ImogJunctionProxy criterions) ;  Request<Long> countNonAffectedEntityProfileReverse(String property) ;  Request<Long> countNonAffectedEntityProfileReverse(String property, ImogJunctionProxy criterions) ;  Request<Void> delete(Set<EntityProfileProxy> entities) ;  Request<Void> delete(EntityProfileProxy entity) ;  Request<Void> save(ImogBeanProxy entity, boolean isNew) ;  Request<Void> delete(ImogBeanProxy entity) ; }