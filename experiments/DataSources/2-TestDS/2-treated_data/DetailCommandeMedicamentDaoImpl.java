package org.imogene.epicam.domain.dao ; public class DetailCommandeMedicamentDaoImpl extends ImogBeanDaoImpl<DetailCommandeMedicament> implements DetailCommandeMedicamentDao { protected DetailCommandeMedicamentDaoImpl() { super(DetailCommandeMedicament.class) ;  }  @Override public void delete() { //TODO }  @Override protected Predicate getFilter(Root<DetailCommandeMedicament> root, CriteriaBuilder builder) { ImogActor actor = ImogActorUtils.getCurrentActor() ;  if (actor == null) { actor = HttpSessionUtil.getCurrentUser() ;  }  if (actor instanceof Personnel) { Personnel personnel = (Personnel) actor ;  String niveau = personnel.getNiveau() ;  if ("1".equals(niveau)) { return builder.equal( DaoUtil.getCascadeRoot(root, "commande.region.id"), personnel.getRegion().getId()) ;  }  else if ("2".equals(niveau)) { return builder.equal(DaoUtil.getCascadeRoot(root, "commande.districtSante.id"), personnel .getDistrictSante().getId()) ;  }  else if ("3".equals(niveau)) { return builder.equal( DaoUtil.getCascadeRoot(root, "commande.CDT.id"), personnel.getCDT().getId()) ;  }  }  return null ;  } }