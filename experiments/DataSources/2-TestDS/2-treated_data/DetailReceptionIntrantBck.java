package org.imogene.epicam.domain.entity.backup ; @Entity@Table(name = "DetailReceptionIntrant_Bck")public class DetailReceptionIntrantBck extends ImogBeanBck { private static final long serialVersionUID = -8835922714697321096L ;  private String reception ;  private String commande ;  private String detailCommande ;  private String entreeLot ;  * Constructor public DetailReceptionIntrantBck() { }  public String getReception() { return reception ;  }  public void setReception(String value) { reception = value ;  }  public String getCommande() { return commande ;  }  public void setCommande(String value) { commande = value ;  }  public String getDetailCommande() { return detailCommande ;  }  public void setDetailCommande(String value) { detailCommande = value ;  }  public String getEntreeLot() { return entreeLot ;  }  public void setEntreeLot(String value) { entreeLot = value ;  } }