package org.imogene.web.shared.request ; @Service(value= BinaryHandler.class, locator = SpringServiceLocator.class)public interface BinaryRequest extends RequestContext {   Request<BinaryDescProxy> getBinaryDesc(String binaryId) ;   Request<BinaryProxy> getBinary(String id) ; }