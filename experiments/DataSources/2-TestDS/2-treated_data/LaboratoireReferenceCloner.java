package org.imogene.epicam.domain.entity.backup ; public class LaboratoireReferenceCloner { public static LaboratoireReferenceBck cloneEntity( LaboratoireReference entity) { LaboratoireReferenceBck bck = new LaboratoireReferenceBck() ;  bck.setTraceId(UUID.randomUUID().toString()) ;  bck.setId(entity.getId()) ;  bck.setCreated(entity.getCreated()) ;  bck.setCreatedBy(entity.getCreatedBy()) ;  bck.setModified(entity.getModified()) ;  bck.setModifiedBy(entity.getModifiedBy()) ;  bck.setModifiedFrom(entity.getModifiedFrom()) ;  bck.setUploadDate(entity.getUploadDate()) ;  bck.setDeleted(entity.getDeleted()) ;  bck.setVersion(entity.getVersion()) ;  bck.setNom(entity.getNom()) ;  bck.setNature(entity.getNature()) ;  bck.setDescription(entity.getDescription()) ;  if (entity.getRegion() != null) { bck.setRegion(entity.getRegion().getId()) ;  }  if (entity.getDistrictSante() != null) { bck.setDistrictSante(entity.getDistrictSante().getId()) ;  }  bck.setLibelle(entity.getLibelle()) ;  bck.setComplementAdresse(entity.getComplementAdresse()) ;  bck.setQuartier(entity.getQuartier()) ;  bck.setVille(entity.getVille()) ;  bck.setCodePostal(entity.getCodePostal()) ;  bck.setCoordonnees(entity.getCoordonnees()) ;  if (entity.getLieuxDits() != null) { StringBuilder builder = new StringBuilder() ;  for (LieuDit i : entity.getLieuxDits()) { builder.append(i.getId()).append(" ; ") ;  }  bck.setLieuxDits(builder.toString()) ;  }  return bck ;  } }