package org.imogene.epicam.shared.proxy ; @ProxyFor(value = SortieLot.class, locator = SortieLotLocator.class)public interface SortieLotProxy extends ImogBeanProxy { public CentreDiagTraitProxy getCDT() ;  public void setCDT(CentreDiagTraitProxy value) ;  public LotProxy getLot() ;  public void setLot(LotProxy value) ;  public Integer getQuantite() ;  public void setQuantite(Integer value) ; }