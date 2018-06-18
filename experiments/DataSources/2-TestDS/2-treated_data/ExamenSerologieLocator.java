package org.imogene.epicam.server.locator ; public class ExamenSerologieLocator extends Locator<ExamenSerologie, String> { private ExamenSerologieHandler handler ;  public ExamenSerologieLocator() { }  @Override public ExamenSerologie create(Class<? extends ExamenSerologie> clazz) { return new ExamenSerologie() ;  }  @Override public ExamenSerologie find(Class<? extends ExamenSerologie> clazz, String id) { if (handler == null) initHandler() ;  return handler.getById(id) ;  }  @Override public Class<ExamenSerologie> getDomainType() { return ExamenSerologie.class ;  }  @Override public String getId(ExamenSerologie domainObject) { return domainObject.getId() ;  }  @Override public Class<String> getIdType() { return String.class ;  }  @Override public Object getVersion(ExamenSerologie domainObject) { return domainObject.getVersion() ;  }  private void initHandler() { HttpServletRequest request = RequestFactoryServlet .getThreadLocalRequest() ;  ApplicationContext context = WebApplicationContextUtils .getWebApplicationContext(request.getSession() .getServletContext()) ;  handler = (ExamenSerologieHandler) context .getBean("examenSerologieHandler") ;  } }