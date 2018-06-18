package org.imogene.epicam.shared.proxy ; @ProxyFor(value = CasTuberculose.class, locator = CasTuberculoseLocator.class)public interface CasTuberculoseProxy extends ImogBeanProxy { public String getIdentifiant() ;  public void setIdentifiant(String value) ;  public String getNumRegTB() ;  public void setNumRegTB(String value) ;  public PatientProxy getPatient() ;  public void setPatient(PatientProxy value) ;  public Date getDateDebutTraitement() ;  public void setDateDebutTraitement(Date value) ;  public String getTypePatient() ;  public void setTypePatient(String value) ;  public String getTypePatientPrecisions() ;  public void setTypePatientPrecisions(String value) ;  public String getFormeMaladie() ;  public void setFormeMaladie(String value) ;  public String getExtraPulmonairePrecisions() ;  public void setExtraPulmonairePrecisions(String value) ;  public String getCotrimoxazole() ;  public void setCotrimoxazole(String value) ;  public Boolean getAntiRetroViraux() ;  public void setAntiRetroViraux(Boolean value) ;  public Boolean getFumeur() ;  public void setFumeur(Boolean value) ;  public Boolean getFumeurArreter() ;  public void setFumeurArreter(Boolean value) ;  public List<ExamenMicroscopieProxy> getExamensMiscrocopies() ;  public void setExamensMiscrocopies(List<ExamenMicroscopieProxy> value) ;  public List<ExamenATBProxy> getExamensATB() ;  public void setExamensATB(List<ExamenATBProxy> value) ;  public RegimeProxy getRegimePhaseInitiale() ;  public void setRegimePhaseInitiale(RegimeProxy value) ;  public RegimeProxy getRegimePhaseContinuation() ;  public void setRegimePhaseContinuation(RegimeProxy value) ;  public List<PriseMedicamenteuseProxy> getPriseMedicamenteusePhaseInitiale() ;  public void setPriseMedicamenteusePhaseInitiale( List<PriseMedicamenteuseProxy> value) ;  public List<PriseMedicamenteuseProxy> getPriseMedicamenteusePhaseContinuation() ;  public void setPriseMedicamenteusePhaseContinuation( List<PriseMedicamenteuseProxy> value) ;  public List<RendezVousProxy> getRendezVous() ;  public void setRendezVous(List<RendezVousProxy> value) ;  public Date getDateFinTraitement() ;  public void setDateFinTraitement(Date value) ;  public String getDevenirMalade() ;  public void setDevenirMalade(String value) ;  public String getObservation() ;  public void setObservation(String value) ; }