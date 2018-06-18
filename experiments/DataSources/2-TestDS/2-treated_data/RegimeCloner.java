package org.imogene.epicam.domain.entity.backup ; public class RegimeCloner { public static RegimeBck cloneEntity(Regime entity) { RegimeBck bck = new RegimeBck() ;  bck.setTraceId(UUID.randomUUID().toString()) ;  bck.setId(entity.getId()) ;  bck.setCreated(entity.getCreated()) ;  bck.setCreatedBy(entity.getCreatedBy()) ;  bck.setModified(entity.getModified()) ;  bck.setModifiedBy(entity.getModifiedBy()) ;  bck.setModifiedFrom(entity.getModifiedFrom()) ;  bck.setUploadDate(entity.getUploadDate()) ;  bck.setDeleted(entity.getDeleted()) ;  bck.setVersion(entity.getVersion()) ;  bck.setNom(entity.getNom()) ;  bck.setType(entity.getType()) ;  bck.setDureeTraitement(entity.getDureeTraitement()) ;  bck.setPoidsMin(entity.getPoidsMin()) ;  bck.setPoidsMax(entity.getPoidsMax()) ;  bck.setDescription(entity.getDescription()) ;  if (entity.getPrisesMedicamenteuses() != null) { StringBuilder builder = new StringBuilder() ;  for (PriseMedicamentRegime i : entity.getPrisesMedicamenteuses()) { builder.append(i.getId()).append(" ; ") ;  }  bck.setPrisesMedicamenteuses(builder.toString()) ;  }  bck.setActif(entity.getActif()) ;  return bck ;  } }