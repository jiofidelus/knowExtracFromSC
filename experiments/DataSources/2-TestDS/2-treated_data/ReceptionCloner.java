package org.imogene.epicam.domain.entity.backup ; public class ReceptionCloner { public static ReceptionBck cloneEntity(Reception entity) { ReceptionBck bck = new ReceptionBck() ;  bck.setTraceId(UUID.randomUUID().toString()) ;  bck.setId(entity.getId()) ;  bck.setCreated(entity.getCreated()) ;  bck.setCreatedBy(entity.getCreatedBy()) ;  bck.setModified(entity.getModified()) ;  bck.setModifiedBy(entity.getModifiedBy()) ;  bck.setModifiedFrom(entity.getModifiedFrom()) ;  bck.setUploadDate(entity.getUploadDate()) ;  bck.setDeleted(entity.getDeleted()) ;  bck.setVersion(entity.getVersion()) ;  if (entity.getRegion() != null) { bck.setRegion(entity.getRegion().getId()) ;  }  if (entity.getDistrictSante() != null) { bck.setDistrictSante(entity.getDistrictSante().getId()) ;  }  if (entity.getCDT() != null) { bck.setCDT(entity.getCDT().getId()) ;  }  if (entity.getCommande() != null) { bck.setCommande(entity.getCommande().getId()) ;  }  bck.setDateReception(entity.getDateReception()) ;  if (entity.getMedicaments() != null) { StringBuilder builder = new StringBuilder() ;  for (DetailReceptionMedicament i : entity.getMedicaments()) { builder.append(i.getId()).append(" ; ") ;  }  bck.setMedicaments(builder.toString()) ;  }  if (entity.getIntrants() != null) { StringBuilder builder = new StringBuilder() ;  for (DetailReceptionIntrant i : entity.getIntrants()) { builder.append(i.getId()).append(" ; ") ;  }  bck.setIntrants(builder.toString()) ;  }  return bck ;  } }