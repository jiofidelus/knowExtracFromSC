package org.imogene.epicam.shared.proxy ; @ProxyFor(value = SmsPredefini.class, locator = SmsPredefiniLocator.class)public interface SmsPredefiniProxy extends ImogBeanProxy { public String getType() ;  public void setType(String value) ;  public LocalizedTextProxy getObjet() ;  public void setObjet(LocalizedTextProxy value) ;  public String getPeriodicite() ;  public void setPeriodicite(String value) ;  public Date getDateEnvoyeSMSPonctuel() ;  public void setDateEnvoyeSMSPonctuel(Date value) ;  public String getStatut() ;  public void setStatut(String value) ;  public LocalizedTextProxy getMessage() ;  public void setMessage(LocalizedTextProxy value) ;  public String getReponse1() ;  public void setReponse1(String value) ;  public String getReponse2() ;  public void setReponse2(String value) ;  public String getBonneReponse() ;  public void setBonneReponse(String value) ;  public Date getEnvoyerAPartirDe() ;  public void setEnvoyerAPartirDe(Date value) ;  public Date getArreterEnvoyerA() ;  public void setArreterEnvoyerA(Date value) ; }