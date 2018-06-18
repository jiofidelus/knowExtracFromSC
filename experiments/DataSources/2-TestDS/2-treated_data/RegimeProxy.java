package org.imogene.epicam.shared.proxy ; @ProxyFor(value = Regime.class, locator = RegimeLocator.class)public interface RegimeProxy extends ImogBeanProxy { public String getNom() ;  public void setNom(String value) ;  public String getType() ;  public void setType(String value) ;  public Integer getDureeTraitement() ;  public void setDureeTraitement(Integer value) ;  public Integer getPoidsMin() ;  public void setPoidsMin(Integer value) ;  public Integer getPoidsMax() ;  public void setPoidsMax(Integer value) ;  public LocalizedTextProxy getDescription() ;  public void setDescription(LocalizedTextProxy value) ;  public List<PriseMedicamentRegimeProxy> getPrisesMedicamenteuses() ;  public void setPrisesMedicamenteuses(List<PriseMedicamentRegimeProxy> value) ;  public Boolean getActif() ;  public void setActif(Boolean value) ; }