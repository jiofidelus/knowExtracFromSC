package org.imogene.web.shared ; @ExtraTypes({ ImogConjunctionProxy.class,  ImogDisjunctionProxy.class,  BasicCriteriaProxy.class,  ProfileProxy.class, CardEntityProxy.class, EntityProfileProxy.class, FieldGroupProxy.class, FieldGroupProfileProxy.class, ImogActorProxy.class,  GeoFieldProxy.class, ImogBeanProxy.class})public interface ImogRequestFactory extends RequestFactory { BinaryRequest binaryRequest() ;  SessionRequest sessionInfoRequest() ;   DynamicFieldTemplateRequest dynamicFieldTemplateRequest() ;  DynamicFieldInstanceRequest dynamicFieldInstanceRequest() ; }