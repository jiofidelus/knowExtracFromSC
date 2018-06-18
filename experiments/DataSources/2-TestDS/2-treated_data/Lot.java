package org.imogene.epicam.domain.entity ; @Entitypublic class Lot extends ImogEntityImpl { public static interface Columns { public static final String REGION = "region" ;  public static final String DISTRICTSANTE = "districtsante" ;  public static final String CDT = "cdt" ;  public static final String NUMERO = "numero" ;  public static final String TYPE = "type" ;  public static final int TYPE_MEDICAMENT = 0 ;  public static final int TYPE_INTRANT = 1 ;  public static final String MEDICAMENT = "medicament" ;  public static final String INTRANT = "intrant" ;  public static final String QUANTITEINITIALE = "quantiteinitiale" ;  public static final String QUANTITE = "quantite" ;  public static final String DATEPEREMPTION = "dateperemption" ;  }  private static final long serialVersionUID = -8165081761405461608L ;  @ManyToOne private Region region ;  @ManyToOne private DistrictSante districtSante ;  @ManyToOne private CentreDiagTrait CDT ;  private String numero ;  private String type = "" ;  @ManyToOne private Medicament medicament ;  @ManyToOne private Intrant intrant ;  private Integer quantiteInitiale ;  @Formula("(SELECT sum(lt.qte) from lottotal as lt where lt.lot=id and lt.modified >= (select ltlt.modified from lottotal as ltlt where ltlt.lot=id and ltlt.thetype=1 order by ltlt.modified desc limit 1))") private Integer quantite ;  @Temporal(TemporalType.TIMESTAMP) private Date datePeremption ;  * Constructor public Lot() { }  public Region getRegion() { return region ;  }  public void setRegion(Region value) { region = value ;  }  public DistrictSante getDistrictSante() { return districtSante ;  }  public void setDistrictSante(DistrictSante value) { districtSante = value ;  }  public CentreDiagTrait getCDT() { return CDT ;  }  public void setCDT(CentreDiagTrait value) { CDT = value ;  }  public String getNumero() { return numero ;  }  public void setNumero(String value) { numero = value ;  }  public String getType() { return type ;  }  public void setType(String value) { type = value ;  }  public Medicament getMedicament() { return medicament ;  }  public void setMedicament(Medicament value) { medicament = value ;  }  public Intrant getIntrant() { return intrant ;  }  public void setIntrant(Intrant value) { intrant = value ;  }  public Integer getQuantiteInitiale() { return quantiteInitiale ;  }  public void setQuantiteInitiale(Integer value) { quantiteInitiale = value ;  }  public Integer getQuantite() { return quantite ;  }  public void setQuantite(Integer value) { quantite = value ;  }  public Date getDatePeremption() { return datePeremption ;  }  public void setDatePeremption(Date value) { datePeremption = value ;  } }