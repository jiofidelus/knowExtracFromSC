package org.imogene.epicam.shared.request ; @Service(value = CandidatureFormationHandler.class, locator = SpringServiceLocator.class)public interface CandidatureFormationRequest extends ImogEntityRequest { Request<CandidatureFormationProxy> findById(String id) ;  Request<Void> save(CandidatureFormationProxy c, boolean isNew) ;  Request<List<CandidatureFormationProxy>> listCandidatureFormation( String sortProperty, boolean sortOrder) ;  Request<List<CandidatureFormationProxy>> listCandidatureFormation( int first, int max, String sortProperty, boolean sortOrder) ;  Request<List<CandidatureFormationProxy>> listCandidatureFormation( int first, int max, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions) ;  Request<List<CandidatureFormationProxy>> listCandidatureFormation( int first, int max, String sortProperty, boolean sortOrder, List<BasicCriteriaProxy> criterions) ;  Request<List<CandidatureFormationProxy>> listNonAffectedCandidatureFormation( int i, int j, String sortProperty, boolean sortOrder, String property) ;  Request<List<CandidatureFormationProxy>> listNonAffectedCandidatureFormation( int i, int j, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions, String property) ;  Request<List<CandidatureFormationProxy>> listNonAffectedCandidatureFormationReverse( int i, int j, String sortProperty, boolean sortOrder, String property) ;  Request<List<CandidatureFormationProxy>> listNonAffectedCandidatureFormationReverse( int i, int j, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions, String property) ;  Request<List<CandidatureFormationProxy>> getCandidatureFormationEmptyList() ;  Request<Long> countCandidatureFormation() ;  Request<Long> countCandidatureFormation(ImogJunctionProxy criterions) ;  Request<Long> countNonAffectedCandidatureFormation(String property) ;  Request<Long> countNonAffectedCandidatureFormation(String property, ImogJunctionProxy criterions) ;  Request<Long> countNonAffectedCandidatureFormationReverse(String property) ;  Request<Long> countNonAffectedCandidatureFormationReverse(String property, ImogJunctionProxy criterions) ;  Request<Void> delete(Set<CandidatureFormationProxy> entities) ;  Request<Void> delete(CandidatureFormationProxy entity) ;  Request<Void> save(ImogBeanProxy entity, boolean isNew) ;  Request<Void> delete(ImogBeanProxy entity) ; }