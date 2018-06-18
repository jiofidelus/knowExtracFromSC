package org.imogene.epicam.domain.entity ; @Entitypublic class Region extends ImogEntityImpl { public static interface Columns { public static final String CODE = "code" ;  public static final String NOM = "nom" ;  public static final String DESCRIPTION = "description" ;  public static final String DISTRICTSANTES = "districtsantes" ;  public static final String LIBELLE = "libelle" ;  public static final int LIBELLE_DOMICILE = 0 ;  public static final int LIBELLE_BUREAU = 1 ;  public static final int LIBELLE_AUTRE = 2 ;  public static final String COMPLEMENTADRESSE = "complementadresse" ;  public static final String QUARTIER = "quartier" ;  public static final String VILLE = "ville" ;  public static final String CODEPOSTAL = "codepostal" ;  public static final String COORDONNEES = "coordonnees" ;  }  private static final long serialVersionUID = 1980047956793815472L ;  private String code ;  @Embedded @AttributeOverrides({ @AttributeOverride(name = "francais", column = @Column(name = "nom_fr")), @AttributeOverride(name = "english", column = @Column(name = "nom_en"))}) private LocalizedText nom ;  @Embedded @AttributeOverrides({ @AttributeOverride(name = "francais", column = @Column(name = "description_fr", columnDefinition = "TEXT")), @AttributeOverride(name = "english", column = @Column(name = "description_en", columnDefinition = "TEXT"))}) private LocalizedText description ;  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = false) @JoinColumn(name = "districtSantesRegion_id") private List<DistrictSante> districtSantes ;  private String libelle ;  @Column(columnDefinition = "TEXT") private String complementAdresse ;  private String quartier ;  private String ville ;  private String codePostal ;  @Embedded @AttributeOverrides({ @AttributeOverride(name = "latitude", column = @Column(name = "coordonnees_Latitude")), @AttributeOverride(name = "longitude", column = @Column(name = "coordonnees_Longitude"))}) private GeoField coordonnees ;  * Constructor public Region() { districtSantes = new ArrayList<DistrictSante>() ;  }  public String getCode() { return code ;  }  public void setCode(String value) { code = value ;  }  public LocalizedText getNom() { return nom ;  }  public void setNom(LocalizedText value) { nom = value ;  }  public LocalizedText getDescription() { return description ;  }  public void setDescription(LocalizedText value) { description = value ;  }  public List<DistrictSante> getDistrictSantes() { return districtSantes ;  }  public void setDistrictSantes(List<DistrictSante> value) { districtSantes = value ;  }  * @param param the DistrictSante to add to the districtSantes collection public void addTodistrictSantes(DistrictSante param) { param.setRegion(this) ;  districtSantes.add(param) ;  }  * @param param the DistrictSante to remove from the districtSantes collection public void removeFromdistrictSantes(DistrictSante param) { param.setRegion(null) ;  districtSantes.remove(param) ;  }  public String getLibelle() { return libelle ;  }  public void setLibelle(String value) { libelle = value ;  }  public String getComplementAdresse() { return complementAdresse ;  }  public void setComplementAdresse(String value) { complementAdresse = value ;  }  public String getQuartier() { return quartier ;  }  public void setQuartier(String value) { quartier = value ;  }  public String getVille() { return ville ;  }  public void setVille(String value) { ville = value ;  }  public String getCodePostal() { return codePostal ;  }  public void setCodePostal(String value) { codePostal = value ;  }  public GeoField getCoordonnees() { return coordonnees ;  }  public void setCoordonnees(GeoField value) { coordonnees = value ;  } }