package org.imogene.epicam.shared.proxy ; @ProxyFor(value = Lot.class, locator = LotLocator.class)public interface LotProxy extends ImogBeanProxy { public RegionProxy getRegion() ;  public void setRegion(RegionProxy value) ;  public DistrictSanteProxy getDistrictSante() ;  public void setDistrictSante(DistrictSanteProxy value) ;  public CentreDiagTraitProxy getCDT() ;  public void setCDT(CentreDiagTraitProxy value) ;  public String getNumero() ;  public void setNumero(String value) ;  public String getType() ;  public void setType(String value) ;  public MedicamentProxy getMedicament() ;  public void setMedicament(MedicamentProxy value) ;  public IntrantProxy getIntrant() ;  public void setIntrant(IntrantProxy value) ;  public Integer getQuantiteInitiale() ;  public void setQuantiteInitiale(Integer value) ;  public Integer getQuantite() ;  public void setQuantite(Integer value) ;  public Date getDatePeremption() ;  public void setDatePeremption(Date value) ; }