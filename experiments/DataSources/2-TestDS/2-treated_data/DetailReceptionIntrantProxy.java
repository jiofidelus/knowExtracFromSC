package org.imogene.epicam.shared.proxy ; @ProxyFor(value = DetailReceptionIntrant.class, locator = DetailReceptionIntrantLocator.class)public interface DetailReceptionIntrantProxy extends ImogBeanProxy { public ReceptionProxy getReception() ;  public void setReception(ReceptionProxy value) ;  public CommandeProxy getCommande() ;  public void setCommande(CommandeProxy value) ;  public DetailCommandeIntrantProxy getDetailCommande() ;  public void setDetailCommande(DetailCommandeIntrantProxy value) ;  public EntreeLotProxy getEntreeLot() ;  public void setEntreeLot(EntreeLotProxy value) ; }