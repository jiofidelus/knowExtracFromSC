package org.imogene.epicam.domain.entity ; @Entitypublic class ExamenATB extends ImogEntityImpl { public static interface Columns { public static final String CDT = "cdt" ;  public static final String LABORATOIREREFERENCE = "laboratoirereference" ;  public static final String CASTB = "castb" ;  public static final String DATEEXAMEN = "dateexamen" ;  public static final String RAISONDEPISTAGE = "raisondepistage" ;  public static final int RAISONDEPISTAGE_DIAGNOSTIC = 0 ;  public static final int RAISONDEPISTAGE_SUIVI = 1 ;  public static final String RESULTAT = "resultat" ;  public static final String OBSERVATIONS = "observations" ;  }  private static final long serialVersionUID = -5968355055566542769L ;  @ManyToOne private CentreDiagTrait CDT ;  @ManyToOne private LaboratoireReference laboratoireReference ;  @ManyToOne @JoinColumn(name = "examensATBCasTuberculose_id") private CasTuberculose casTb ;  @Temporal(TemporalType.TIMESTAMP) private Date dateExamen ;  private String raisonDepistage ;  private String resultat ;  @Column(columnDefinition = "TEXT") private String observations ;  * Constructor public ExamenATB() { }  public CentreDiagTrait getCDT() { return CDT ;  }  public void setCDT(CentreDiagTrait value) { CDT = value ;  }  public LaboratoireReference getLaboratoireReference() { return laboratoireReference ;  }  public void setLaboratoireReference(LaboratoireReference value) { laboratoireReference = value ;  }  public CasTuberculose getCasTb() { return casTb ;  }  public void setCasTb(CasTuberculose value) { casTb = value ;  }  public Date getDateExamen() { return dateExamen ;  }  public void setDateExamen(Date value) { dateExamen = value ;  }  public String getRaisonDepistage() { return raisonDepistage ;  }  public void setRaisonDepistage(String value) { raisonDepistage = value ;  }  public String getResultat() { return resultat ;  }  public void setResultat(String value) { resultat = value ;  }  public String getObservations() { return observations ;  }  public void setObservations(String value) { observations = value ;  } }