package org.imogene.epicam.shared.proxy ; @ProxyFor(value = DetailRavitaillement.class, locator = DetailRavitaillementLocator.class)public interface DetailRavitaillementProxy extends ImogBeanProxy { public RavitaillementProxy getRavitaillement() ;  public void setRavitaillement(RavitaillementProxy value) ;  public SortieLotProxy getSortieLot() ;  public void setSortieLot(SortieLotProxy value) ;  public EntreeLotProxy getEntreeLot() ;  public void setEntreeLot(EntreeLotProxy value) ; }