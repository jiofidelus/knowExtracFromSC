package org.imogene.epicam.shared.proxy ; @ProxyFor(value = Commande.class, locator = CommandeLocator.class)public interface CommandeProxy extends ImogBeanProxy { public Date getDate() ;  public void setDate(Date value) ;  public RegionProxy getRegion() ;  public void setRegion(RegionProxy value) ;  public DistrictSanteProxy getDistrictSante() ;  public void setDistrictSante(DistrictSanteProxy value) ;  public CentreDiagTraitProxy getCDT() ;  public void setCDT(CentreDiagTraitProxy value) ;  public List<DetailCommandeMedicamentProxy> getMedicaments() ;  public void setMedicaments(List<DetailCommandeMedicamentProxy> value) ;  public List<DetailCommandeIntrantProxy> getIntrants() ;  public void setIntrants(List<DetailCommandeIntrantProxy> value) ;  public Boolean getApprouveeRegion() ;  public void setApprouveeRegion(Boolean value) ;  public String getMotifRejetRegion() ;  public void setMotifRejetRegion(String value) ;  public Boolean getApprouveeGTC() ;  public void setApprouveeGTC(Boolean value) ;  public String getMotifRejetGTC() ;  public void setMotifRejetGTC(String value) ; }