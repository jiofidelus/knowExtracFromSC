package org.imogene.epicam.server.locator ; public class CasIndexLocator extends Locator<CasIndex, String> { private CasIndexHandler handler ;  public CasIndexLocator() { }  @Override public CasIndex create(Class<? extends CasIndex> clazz) { return new CasIndex() ;  }  @Override public CasIndex find(Class<? extends CasIndex> clazz, String id) { if (handler == null) initHandler() ;  return handler.getById(id) ;  }  @Override public Class<CasIndex> getDomainType() { return CasIndex.class ;  }  @Override public String getId(CasIndex domainObject) { return domainObject.getId() ;  }  @Override public Class<String> getIdType() { return String.class ;  }  @Override public Object getVersion(CasIndex domainObject) { return domainObject.getVersion() ;  }  private void initHandler() { HttpServletRequest request = RequestFactoryServlet .getThreadLocalRequest() ;  ApplicationContext context = WebApplicationContextUtils .getWebApplicationContext(request.getSession() .getServletContext()) ;  handler = (CasIndexHandler) context.getBean("casIndexHandler") ;  } }