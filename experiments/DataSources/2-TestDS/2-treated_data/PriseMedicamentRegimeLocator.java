package org.imogene.epicam.server.locator ; public class PriseMedicamentRegimeLocator extends Locator<PriseMedicamentRegime, String> { private PriseMedicamentRegimeHandler handler ;  public PriseMedicamentRegimeLocator() { }  @Override public PriseMedicamentRegime create( Class<? extends PriseMedicamentRegime> clazz) { return new PriseMedicamentRegime() ;  }  @Override public PriseMedicamentRegime find( Class<? extends PriseMedicamentRegime> clazz, String id) { if (handler == null) initHandler() ;  return handler.getById(id) ;  }  @Override public Class<PriseMedicamentRegime> getDomainType() { return PriseMedicamentRegime.class ;  }  @Override public String getId(PriseMedicamentRegime domainObject) { return domainObject.getId() ;  }  @Override public Class<String> getIdType() { return String.class ;  }  @Override public Object getVersion(PriseMedicamentRegime domainObject) { return domainObject.getVersion() ;  }  private void initHandler() { HttpServletRequest request = RequestFactoryServlet .getThreadLocalRequest() ;  ApplicationContext context = WebApplicationContextUtils .getWebApplicationContext(request.getSession() .getServletContext()) ;  handler = (PriseMedicamentRegimeHandler) context .getBean("priseMedicamentRegimeHandler") ;  } }