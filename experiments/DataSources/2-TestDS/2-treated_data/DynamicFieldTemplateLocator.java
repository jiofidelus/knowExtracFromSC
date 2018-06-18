package org.imogene.web.server.locator ; public class DynamicFieldTemplateLocator extends Locator<DynamicFieldTemplate, String> { private DynamicFieldTemplateHandler handler ;  public DynamicFieldTemplateLocator() { }  @Override public DynamicFieldTemplate create( Class<? extends DynamicFieldTemplate> clazz) { return new DynamicFieldTemplate() ;  }  @Override public DynamicFieldTemplate find( Class<? extends DynamicFieldTemplate> clazz, String id) { if (handler == null) initHandler() ;  return handler.findById(id) ;  }  @Override public Class<DynamicFieldTemplate> getDomainType() { return DynamicFieldTemplate.class ;  }  @Override public String getId(DynamicFieldTemplate domainObject) { return domainObject.getId() ;  }  @Override public Class<String> getIdType() { return String.class ;  }  @Override public Object getVersion(DynamicFieldTemplate domainObject) { return domainObject.getVersion() ;  }  private void initHandler() { HttpServletRequest request = RequestFactoryServlet .getThreadLocalRequest() ;  ApplicationContext context = WebApplicationContextUtils .getWebApplicationContext(request.getSession() .getServletContext()) ;  handler = (DynamicFieldTemplateHandler) context .getBean("dynamicFieldTemplateHandler") ;  } }