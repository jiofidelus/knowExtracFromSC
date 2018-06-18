package org.imogene.epicam.domain.entity ; @Entitypublic class RendezVous extends ImogEntityImpl { public static interface Columns { public static final String CASTB = "castb" ;  public static final String DATERENDEZVOUS = "daterendezvous" ;  public static final String HONORE = "honore" ;  public static final String NOMBRESMSENVOYE = "nombresmsenvoye" ;  public static final String COMMENTAIRE = "commentaire" ;  }  private static final long serialVersionUID = -7853272534974294613L ;  @ManyToOne @JoinColumn(name = "rendezVousCasTuberculose_id") private CasTuberculose casTb ;  @Temporal(TemporalType.TIMESTAMP) private Date dateRendezVous ;  private Boolean honore ;  private Integer nombreSMSEnvoye ;  @Column(columnDefinition = "TEXT") private String commentaire ;  * Constructor public RendezVous() { }  public CasTuberculose getCasTb() { return casTb ;  }  public void setCasTb(CasTuberculose value) { casTb = value ;  }  public Date getDateRendezVous() { return dateRendezVous ;  }  public void setDateRendezVous(Date value) { dateRendezVous = value ;  }  public Boolean getHonore() { return honore ;  }  public void setHonore(Boolean value) { honore = value ;  }  public Integer getNombreSMSEnvoye() { return nombreSMSEnvoye ;  }  public void setNombreSMSEnvoye(Integer value) { nombreSMSEnvoye = value ;  }  public String getCommentaire() { return commentaire ;  }  public void setCommentaire(String value) { commentaire = value ;  } }