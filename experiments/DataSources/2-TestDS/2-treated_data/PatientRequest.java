package org.imogene.epicam.shared.request ; @Service(value = PatientHandler.class, locator = SpringServiceLocator.class)public interface PatientRequest extends ImogEntityRequest { Request<PatientProxy> findById(String id) ;  Request<Void> save(PatientProxy c, boolean isNew) ;  Request<List<PatientProxy>> listPatient(String sortProperty, boolean sortOrder) ;  Request<List<PatientProxy>> listPatient(int first, int max, String sortProperty, boolean sortOrder) ;  Request<List<PatientProxy>> listPatient(int first, int max, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions) ;  Request<List<PatientProxy>> listPatient(int first, int max, String sortProperty, boolean sortOrder, List<BasicCriteriaProxy> criterions) ;  Request<List<PatientProxy>> listNonAffectedPatient(int i, int j, String sortProperty, boolean sortOrder, String property) ;  Request<List<PatientProxy>> listNonAffectedPatient(int i, int j, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions, String property) ;  Request<List<PatientProxy>> listNonAffectedPatientReverse(int i, int j, String sortProperty, boolean sortOrder, String property) ;  Request<List<PatientProxy>> listNonAffectedPatientReverse(int i, int j, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions, String property) ;  Request<List<PatientProxy>> getPatientEmptyList() ;  Request<Long> countPatient() ;  Request<Long> countPatient(ImogJunctionProxy criterions) ;  Request<Long> countNonAffectedPatient(String property) ;  Request<Long> countNonAffectedPatient(String property, ImogJunctionProxy criterions) ;  Request<Long> countNonAffectedPatientReverse(String property) ;  Request<Long> countNonAffectedPatientReverse(String property, ImogJunctionProxy criterions) ;  Request<Void> delete(Set<PatientProxy> entities) ;  Request<Void> delete(PatientProxy entity) ;  Request<Void> save(ImogBeanProxy entity, boolean isNew) ;  Request<Void> delete(ImogBeanProxy entity) ;  Request<Long> countPatientFilteredByCdt(ImogJunctionProxy criterions, String cdtId) ;  Request<List<PatientProxy>> listPatientFilteredByCdt(int first, int max, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions, String cdtId) ; }