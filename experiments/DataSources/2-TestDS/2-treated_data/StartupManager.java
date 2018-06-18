package org.imogene.web.server.startup ; public class StartupManager implements ApplicationListener<ContextRefreshedEvent> { private static final Logger LOG = Logger.getLogger(StartupManager.class) ;  * Interface to implement to start a job when application context is fully started. *  * @author MEDES-IMPS *  public static interface StartupJob extends Runnable { }  private List<StartupJob> jobs ;  public void onApplicationEvent(ContextRefreshedEvent event) { ApplicationContext parent = event.getApplicationContext().getParent() ;  if (parent == null) { LOG.info("ContextRefreshEvent: Root Context") ;  return ;  }  LOG.info("ContextRefreshEvent: Application Context") ;  for (StartupJob job : jobs) { try { job.run() ;  }  catch (Exception e) { LOG.error("Error running startup job: " + job.getClass().getName(), e) ;  }  }  }  public void setJobs(List<StartupJob> jobs) { this.jobs = jobs ;  } }