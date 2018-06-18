package org.imogene.epicam.domain.entity ; @Entitypublic class DetailReceptionIntrant extends ImogEntityImpl { public static interface Columns { public static final String RECEPTION = "reception" ;  public static final String COMMANDE = "commande" ;  public static final String DETAILCOMMANDE = "detailcommande" ;  public static final String ENTREELOT = "entreelot" ;  }  private static final long serialVersionUID = -3585616210728719499L ;  @ManyToOne @JoinColumn(name = "intrantsReception_id") private Reception reception ;  @ManyToOne private Commande commande ;  @ManyToOne private DetailCommandeIntrant detailCommande ;  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) private EntreeLot entreeLot ;  * Constructor public DetailReceptionIntrant() { }  public Reception getReception() { return reception ;  }  public void setReception(Reception value) { reception = value ;  }  public Commande getCommande() { return commande ;  }  public void setCommande(Commande value) { commande = value ;  }  public DetailCommandeIntrant getDetailCommande() { return detailCommande ;  }  public void setDetailCommande(DetailCommandeIntrant value) { detailCommande = value ;  }  public EntreeLot getEntreeLot() { return entreeLot ;  }  public void setEntreeLot(EntreeLot value) { entreeLot = value ;  } }