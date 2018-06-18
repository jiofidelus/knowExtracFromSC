package org.imogene.epicam.domain.entity ; @Entitypublic class DetailInventaire extends ImogEntityImpl { public static interface Columns { public static final String INVENTAIRE = "inventaire" ;  public static final String CDT = "cdt" ;  public static final String LOT = "lot" ;  public static final String QUANTITEREELLE = "quantitereelle" ;  public static final String QUANTITETHEORIQUE = "quantitetheorique" ;  }  private static final long serialVersionUID = 8903626675278973603L ;  @ManyToOne @JoinColumn(name = "detailsInventaire_id") private Inventaire inventaire ;  @ManyToOne private CentreDiagTrait CDT ;  @ManyToOne private Lot lot ;  private Integer quantiteReelle ;  private Integer quantiteTheorique ;  * Constructor public DetailInventaire() { }  public Inventaire getInventaire() { return inventaire ;  }  public void setInventaire(Inventaire value) { inventaire = value ;  }  public CentreDiagTrait getCDT() { return CDT ;  }  public void setCDT(CentreDiagTrait value) { CDT = value ;  }  public Lot getLot() { return lot ;  }  public void setLot(Lot value) { lot = value ;  }  public Integer getQuantiteReelle() { return quantiteReelle ;  }  public void setQuantiteReelle(Integer value) { quantiteReelle = value ;  }  public Integer getQuantiteTheorique() { return quantiteTheorique ;  }  public void setQuantiteTheorique(Integer value) { quantiteTheorique = value ;  } }