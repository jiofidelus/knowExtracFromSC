package org.imogene.epicam.domain.entity ; @Entitypublic class DetailCommandeIntrant extends ImogEntityImpl { public static interface Columns { public static final String COMMANDE = "commande" ;  public static final String INTRANT = "intrant" ;  public static final String QUANTITEREQUISE = "quantiterequise" ;  public static final String QUANTITEENSTOCK = "quantiteenstock" ;  }  private static final long serialVersionUID = -1955542381915903557L ;  @ManyToOne @JoinColumn(name = "intrantsCommande_id") private Commande commande ;  @ManyToOne private Intrant intrant ;  private Integer quantiteRequise ;  private Integer quantiteEnStock ;  * Constructor public DetailCommandeIntrant() { }  public Commande getCommande() { return commande ;  }  public void setCommande(Commande value) { commande = value ;  }  public Intrant getIntrant() { return intrant ;  }  public void setIntrant(Intrant value) { intrant = value ;  }  public Integer getQuantiteRequise() { return quantiteRequise ;  }  public void setQuantiteRequise(Integer value) { quantiteRequise = value ;  }  public Integer getQuantiteEnStock() { return quantiteEnStock ;  }  public void setQuantiteEnStock(Integer value) { quantiteEnStock = value ;  } }