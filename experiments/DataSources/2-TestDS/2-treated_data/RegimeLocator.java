package org.imogene.epicam.server.locator ; public class RegimeLocator extends Locator<Regime, String> { private RegimeHandler handler ;  public RegimeLocator() { }  @Override public Regime create(Class<? extends Regime> clazz) { return new Regime() ;  }  @Override public Regime find(Class<? extends Regime> clazz, String id) { if (handler == null) initHandler() ;  return handler.getById(id) ;  }  @Override public Class<Regime> getDomainType() { return Regime.class ;  }  @Override public String getId(Regime domainObject) { return domainObject.getId() ;  }  @Override public Class<String> getIdType() { return String.class ;  }  @Override public Object getVersion(Regime domainObject) { return domainObject.getVersion() ;  }  private void initHandler() { HttpServletRequest request = RequestFactoryServlet .getThreadLocalRequest() ;  ApplicationContext context = WebApplicationContextUtils .getWebApplicationContext(request.getSession() .getServletContext()) ;  handler = (RegimeHandler) context.getBean("regimeHandler") ;  } }