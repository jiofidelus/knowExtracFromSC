package org.imogene.web.server.locator ; public class FieldGroupProfileLocator extends Locator<FieldGroupProfile, String> { private FieldGroupProfileHandler handler ;  public FieldGroupProfileLocator() { }  @Override public FieldGroupProfile create(Class<? extends FieldGroupProfile> clazz) { return new FieldGroupProfile() ;  }  @Override public FieldGroupProfile find(Class<? extends FieldGroupProfile> clazz, String id) { if (handler == null) initHandler() ;  return handler.getById(id) ;  }  @Override public Class<FieldGroupProfile> getDomainType() { return FieldGroupProfile.class ;  }  @Override public String getId(FieldGroupProfile domainObject) { return domainObject.getId() ;  }  @Override public Class<String> getIdType() { return String.class ;  }  @Override public Object getVersion(FieldGroupProfile domainObject) { return domainObject.getVersion() ;  }  private void initHandler() { HttpServletRequest request = RequestFactoryServlet.getThreadLocalRequest() ;  ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession() .getServletContext()) ;  handler = (FieldGroupProfileHandler) context.getBean("fieldGroupProfileHandler") ;  } }