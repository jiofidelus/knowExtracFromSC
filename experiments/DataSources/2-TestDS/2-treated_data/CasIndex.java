package org.imogene.epicam.domain.entity ; @Entitypublic class CasIndex extends ImogEntityImpl { public static interface Columns { public static final String PATIENT = "patient" ;  public static final String PATIENTLIE = "patientlie" ;  public static final String TYPERELATION = "typerelation" ;  }  private static final long serialVersionUID = -8738610350173852599L ;  @ManyToOne @JoinColumn(name = "casIndexPatient_id") private Patient patient ;  @ManyToOne private Patient patientLie ;  private String typeRelation ;  * Constructor public CasIndex() { }  public Patient getPatient() { return patient ;  }  public void setPatient(Patient value) { patient = value ;  }  public Patient getPatientLie() { return patientLie ;  }  public void setPatientLie(Patient value) { patientLie = value ;  }  public String getTypeRelation() { return typeRelation ;  }  public void setTypeRelation(String value) { typeRelation = value ;  } }