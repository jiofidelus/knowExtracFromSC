package org.imogene.epicam.domain.entity.backup ; @Entity@Table(name = "LaboratoireReference_Bck")public class LaboratoireReferenceBck extends ImogBeanBck { private static final long serialVersionUID = 716680196317741696L ;  @Embedded @AttributeOverrides({ @AttributeOverride(name = "francais", column = @Column(name = "nom_fr")), @AttributeOverride(name = "english", column = @Column(name = "nom_en"))}) private LocalizedText nom ;  private String nature ;  @Embedded @AttributeOverrides({ @AttributeOverride(name = "francais", column = @Column(name = "description_fr")), @AttributeOverride(name = "english", column = @Column(name = "description_en"))}) private LocalizedText description ;  private String region ;  private String districtSante ;  private String libelle ;  @Column(columnDefinition = "TEXT") private String complementAdresse ;  private String quartier ;  private String ville ;  private String codePostal ;  @Embedded @AttributeOverrides({ @AttributeOverride(name = "latitude", column = @Column(name = "coordonnees_Latitude")), @AttributeOverride(name = "longitude", column = @Column(name = "coordonnees_Longitude"))}) private GeoField coordonnees ;  private String lieuxDits ;  * Constructor public LaboratoireReferenceBck() { }  public LocalizedText getNom() { return nom ;  }  public void setNom(LocalizedText value) { nom = value ;  }  public String getNature() { return nature ;  }  public void setNature(String value) { nature = value ;  }  public LocalizedText getDescription() { return description ;  }  public void setDescription(LocalizedText value) { description = value ;  }  public String getRegion() { return region ;  }  public void setRegion(String value) { region = value ;  }  public String getDistrictSante() { return districtSante ;  }  public void setDistrictSante(String value) { districtSante = value ;  }  public String getLibelle() { return libelle ;  }  public void setLibelle(String value) { libelle = value ;  }  public String getComplementAdresse() { return complementAdresse ;  }  public void setComplementAdresse(String value) { complementAdresse = value ;  }  public String getQuartier() { return quartier ;  }  public void setQuartier(String value) { quartier = value ;  }  public String getVille() { return ville ;  }  public void setVille(String value) { ville = value ;  }  public String getCodePostal() { return codePostal ;  }  public void setCodePostal(String value) { codePostal = value ;  }  public GeoField getCoordonnees() { return coordonnees ;  }  public void setCoordonnees(GeoField value) { coordonnees = value ;  }  public String getLieuxDits() { return lieuxDits ;  }  public void setLieuxDits(String value) { lieuxDits = value ;  } }