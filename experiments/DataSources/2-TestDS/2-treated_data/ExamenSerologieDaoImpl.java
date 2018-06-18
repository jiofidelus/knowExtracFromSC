package org.imogene.epicam.domain.dao ; public class ExamenSerologieDaoImpl extends ImogBeanDaoImpl<ExamenSerologie> implements ExamenSerologieDao { protected ExamenSerologieDaoImpl() { super(ExamenSerologie.class) ;  }  @Override public void delete() { //TODO }  @Override protected Predicate getFilter(Root<ExamenSerologie> root, CriteriaBuilder builder) { ImogActor actor = ImogActorUtils.getCurrentActor() ;  if (actor == null) { actor = HttpSessionUtil.getCurrentUser() ;  }  if (actor instanceof Personnel) { Personnel personnel = (Personnel) actor ;  String niveau = personnel.getNiveau() ;  if ("1".equals(niveau)) { Path<?> join = DaoUtil.getCascadeRoot(root.join("patient") .join("centres", JoinType.LEFT), "region.id") ;  return builder.equal(join, personnel.getRegion().getId()) ;  }  else if ("2".equals(niveau)) { Path<?> join = DaoUtil.getCascadeRoot(root.join("patient") .join("centres", JoinType.LEFT), "districtSante.id") ;  return builder .equal(join, personnel.getDistrictSante().getId()) ;  }  else if ("3".equals(niveau)) { Path<?> join = root.join("patient") .join("centres", JoinType.LEFT).get("id") ;  return builder.equal(join, personnel.getCDT().getId()) ;  }  }  return null ;  } }