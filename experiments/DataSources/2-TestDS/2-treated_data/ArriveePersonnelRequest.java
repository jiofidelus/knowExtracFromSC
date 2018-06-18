package org.imogene.epicam.shared.request ; @Service(value = ArriveePersonnelHandler.class, locator = SpringServiceLocator.class)public interface ArriveePersonnelRequest extends ImogEntityRequest { Request<ArriveePersonnelProxy> findById(String id) ;  Request<Void> save(ArriveePersonnelProxy c, boolean isNew) ;  Request<List<ArriveePersonnelProxy>> listArriveePersonnel( String sortProperty, boolean sortOrder) ;  Request<List<ArriveePersonnelProxy>> listArriveePersonnel(int first, int max, String sortProperty, boolean sortOrder) ;  Request<List<ArriveePersonnelProxy>> listArriveePersonnel(int first, int max, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions) ;  Request<List<ArriveePersonnelProxy>> listArriveePersonnel(int first, int max, String sortProperty, boolean sortOrder, List<BasicCriteriaProxy> criterions) ;  Request<List<ArriveePersonnelProxy>> listNonAffectedArriveePersonnel(int i, int j, String sortProperty, boolean sortOrder, String property) ;  Request<List<ArriveePersonnelProxy>> listNonAffectedArriveePersonnel(int i, int j, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions, String property) ;  Request<List<ArriveePersonnelProxy>> listNonAffectedArriveePersonnelReverse( int i, int j, String sortProperty, boolean sortOrder, String property) ;  Request<List<ArriveePersonnelProxy>> listNonAffectedArriveePersonnelReverse( int i, int j, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions, String property) ;  Request<List<ArriveePersonnelProxy>> getArriveePersonnelEmptyList() ;  Request<Long> countArriveePersonnel() ;  Request<Long> countArriveePersonnel(ImogJunctionProxy criterions) ;  Request<Long> countNonAffectedArriveePersonnel(String property) ;  Request<Long> countNonAffectedArriveePersonnel(String property, ImogJunctionProxy criterions) ;  Request<Long> countNonAffectedArriveePersonnelReverse(String property) ;  Request<Long> countNonAffectedArriveePersonnelReverse(String property, ImogJunctionProxy criterions) ;  Request<Void> delete(Set<ArriveePersonnelProxy> entities) ;  Request<Void> delete(ArriveePersonnelProxy entity) ;  Request<Void> save(ImogBeanProxy entity, boolean isNew) ;  Request<Void> delete(ImogBeanProxy entity) ; }