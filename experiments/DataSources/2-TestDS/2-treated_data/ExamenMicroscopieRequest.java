package org.imogene.epicam.shared.request ; @Service(value = ExamenMicroscopieHandler.class, locator = SpringServiceLocator.class)public interface ExamenMicroscopieRequest extends ImogEntityRequest { Request<ExamenMicroscopieProxy> findById(String id) ;  Request<Void> save(ExamenMicroscopieProxy c, boolean isNew) ;  Request<List<ExamenMicroscopieProxy>> listExamenMicroscopie( String sortProperty, boolean sortOrder) ;  Request<List<ExamenMicroscopieProxy>> listExamenMicroscopie(int first, int max, String sortProperty, boolean sortOrder) ;  Request<List<ExamenMicroscopieProxy>> listExamenMicroscopie(int first, int max, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions) ;  Request<List<ExamenMicroscopieProxy>> listExamenMicroscopie(int first, int max, String sortProperty, boolean sortOrder, List<BasicCriteriaProxy> criterions) ;  Request<List<ExamenMicroscopieProxy>> listNonAffectedExamenMicroscopie( int i, int j, String sortProperty, boolean sortOrder, String property) ;  Request<List<ExamenMicroscopieProxy>> listNonAffectedExamenMicroscopie( int i, int j, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions, String property) ;  Request<List<ExamenMicroscopieProxy>> listNonAffectedExamenMicroscopieReverse( int i, int j, String sortProperty, boolean sortOrder, String property) ;  Request<List<ExamenMicroscopieProxy>> listNonAffectedExamenMicroscopieReverse( int i, int j, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions, String property) ;  Request<List<ExamenMicroscopieProxy>> getExamenMicroscopieEmptyList() ;  Request<Long> countExamenMicroscopie() ;  Request<Long> countExamenMicroscopie(ImogJunctionProxy criterions) ;  Request<Long> countNonAffectedExamenMicroscopie(String property) ;  Request<Long> countNonAffectedExamenMicroscopie(String property, ImogJunctionProxy criterions) ;  Request<Long> countNonAffectedExamenMicroscopieReverse(String property) ;  Request<Long> countNonAffectedExamenMicroscopieReverse(String property, ImogJunctionProxy criterions) ;  Request<Void> delete(Set<ExamenMicroscopieProxy> entities) ;  Request<Void> delete(ExamenMicroscopieProxy entity) ;  Request<Void> save(ImogBeanProxy entity, boolean isNew) ;  Request<Void> delete(ImogBeanProxy entity) ; }