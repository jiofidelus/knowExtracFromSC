package org.imogene.epicam.shared.request ; @Service(value = PriseMedicamenteuseHandler.class, locator = SpringServiceLocator.class)public interface PriseMedicamenteuseRequest extends ImogEntityRequest { Request<PriseMedicamenteuseProxy> findById(String id) ;  Request<Void> save(PriseMedicamenteuseProxy c, boolean isNew) ;  Request<List<PriseMedicamenteuseProxy>> listPriseMedicamenteuse( String sortProperty, boolean sortOrder) ;  Request<List<PriseMedicamenteuseProxy>> listPriseMedicamenteuse(int first, int max, String sortProperty, boolean sortOrder) ;  Request<List<PriseMedicamenteuseProxy>> listPriseMedicamenteuse(int first, int max, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions) ;  Request<List<PriseMedicamenteuseProxy>> listPriseMedicamenteuse(int first, int max, String sortProperty, boolean sortOrder, List<BasicCriteriaProxy> criterions) ;  Request<List<PriseMedicamenteuseProxy>> listNonAffectedPriseMedicamenteuse( int i, int j, String sortProperty, boolean sortOrder, String property) ;  Request<List<PriseMedicamenteuseProxy>> listNonAffectedPriseMedicamenteuse( int i, int j, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions, String property) ;  Request<List<PriseMedicamenteuseProxy>> listNonAffectedPriseMedicamenteuseReverse( int i, int j, String sortProperty, boolean sortOrder, String property) ;  Request<List<PriseMedicamenteuseProxy>> listNonAffectedPriseMedicamenteuseReverse( int i, int j, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions, String property) ;  Request<List<PriseMedicamenteuseProxy>> getPriseMedicamenteuseEmptyList() ;  Request<Long> countPriseMedicamenteuse() ;  Request<Long> countPriseMedicamenteuse(ImogJunctionProxy criterions) ;  Request<Long> countNonAffectedPriseMedicamenteuse(String property) ;  Request<Long> countNonAffectedPriseMedicamenteuse(String property, ImogJunctionProxy criterions) ;  Request<Long> countNonAffectedPriseMedicamenteuseReverse(String property) ;  Request<Long> countNonAffectedPriseMedicamenteuseReverse(String property, ImogJunctionProxy criterions) ;  Request<Void> delete(Set<PriseMedicamenteuseProxy> entities) ;  Request<Void> delete(PriseMedicamenteuseProxy entity) ;  Request<Void> save(ImogBeanProxy entity, boolean isNew) ;  Request<Void> delete(ImogBeanProxy entity) ; }