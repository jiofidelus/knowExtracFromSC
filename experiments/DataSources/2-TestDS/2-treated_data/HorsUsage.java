package org.imogene.epicam.domain.entity ; @Entitypublic class HorsUsage extends ImogEntityImpl { public static interface Columns { public static final String LOT = "lot" ;  public static final String TYPE = "type" ;  public static final int TYPE_PERIMEE = 0 ;  public static final int TYPE_CASSE = 1 ;  public static final String MOTIF = "motif" ;  }  private static final long serialVersionUID = -3954666381192314807L ;  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) private SortieLot lot ;  private String type ;  @Column(columnDefinition = "TEXT") private String motif ;  * Constructor public HorsUsage() { }  public SortieLot getLot() { return lot ;  }  public void setLot(SortieLot value) { lot = value ;  }  public String getType() { return type ;  }  public void setType(String value) { type = value ;  }  public String getMotif() { return motif ;  }  public void setMotif(String value) { motif = value ;  } }