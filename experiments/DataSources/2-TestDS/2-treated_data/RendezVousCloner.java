package org.imogene.epicam.domain.entity.backup ; public class RendezVousCloner { public static RendezVousBck cloneEntity(RendezVous entity) { RendezVousBck bck = new RendezVousBck() ;  bck.setTraceId(UUID.randomUUID().toString()) ;  bck.setId(entity.getId()) ;  bck.setCreated(entity.getCreated()) ;  bck.setCreatedBy(entity.getCreatedBy()) ;  bck.setModified(entity.getModified()) ;  bck.setModifiedBy(entity.getModifiedBy()) ;  bck.setModifiedFrom(entity.getModifiedFrom()) ;  bck.setUploadDate(entity.getUploadDate()) ;  bck.setDeleted(entity.getDeleted()) ;  bck.setVersion(entity.getVersion()) ;  if (entity.getCasTb() != null) { bck.setCasTb(entity.getCasTb().getId()) ;  }  bck.setDateRendezVous(entity.getDateRendezVous()) ;  bck.setHonore(entity.getHonore()) ;  bck.setNombreSMSEnvoye(entity.getNombreSMSEnvoye()) ;  bck.setCommentaire(entity.getCommentaire()) ;  return bck ;  } }