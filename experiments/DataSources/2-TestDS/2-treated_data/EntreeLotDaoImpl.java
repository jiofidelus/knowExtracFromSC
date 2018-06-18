package org.imogene.epicam.domain.dao ; public class EntreeLotDaoImpl extends ImogBeanDaoImpl<EntreeLot> implements EntreeLotDao { protected EntreeLotDaoImpl() { super(EntreeLot.class) ;  }  @Override public void delete() { //TODO }  @Override protected Predicate getFilter(Root<EntreeLot> root, CriteriaBuilder builder) { ImogActor actor = ImogActorUtils.getCurrentActor() ;  if (actor == null) { actor = HttpSessionUtil.getCurrentUser() ;  }  if (actor instanceof Personnel) { Personnel personnel = (Personnel) actor ;  String niveau = personnel.getNiveau() ;  if ("1".equals(niveau)) { return builder.equal( DaoUtil.getCascadeRoot(root, "lot.region.id"), personnel.getRegion().getId()) ;  }  else if ("2".equals(niveau)) { return builder.equal( DaoUtil.getCascadeRoot(root, "lot.districtSante.id"), personnel.getDistrictSante().getId()) ;  }  else if ("3".equals(niveau)) { return builder.equal( DaoUtil.getCascadeRoot(root, "lot.CDT.id"), personnel .getCDT().getId()) ;  }  }  return null ;  } }