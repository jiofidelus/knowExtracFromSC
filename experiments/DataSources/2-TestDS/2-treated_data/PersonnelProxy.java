package org.imogene.epicam.shared.proxy ; @ProxyFor(value = Personnel.class, locator = PersonnelLocator.class)public interface PersonnelProxy extends ImogActorProxy { public String getNom() ;  public void setNom(String value) ;  public Date getDateNaissance() ;  public void setDateNaissance(Date value) ;  public String getProfession() ;  public void setProfession(String value) ;  public String getTelephoneUn() ;  public void setTelephoneUn(String value) ;  public String getTelephoneDeux() ;  public void setTelephoneDeux(String value) ;  public String getTelephoneTrois() ;  public void setTelephoneTrois(String value) ;  public String getEmail() ;  public void setEmail(String value) ;  public String getLibelle() ;  public void setLibelle(String value) ;  public String getComplementAdresse() ;  public void setComplementAdresse(String value) ;  public String getQuartier() ;  public void setQuartier(String value) ;  public String getVille() ;  public void setVille(String value) ;  public String getCodePostal() ;  public void setCodePostal(String value) ;  public Date getDateDepart() ;  public void setDateDepart(Date value) ;  public Date getDateArrivee() ;  public void setDateArrivee(Date value) ;  public Boolean getAEteForme() ;  public void setAEteForme(Boolean value) ;  public List<QualificationProxy> getQualification() ;  public void setQualification(List<QualificationProxy> value) ;  public String getNiveau() ;  public void setNiveau(String value) ;  public RegionProxy getRegion() ;  public void setRegion(RegionProxy value) ;  public DistrictSanteProxy getDistrictSante() ;  public void setDistrictSante(DistrictSanteProxy value) ;  public CentreDiagTraitProxy getCDT() ;  public void setCDT(CentreDiagTraitProxy value) ;  public Boolean getActif() ;  public void setActif(Boolean value) ; }