package org.imogene.epicam.domain.dao ; public class ExamenATBDaoImpl extends ImogBeanDaoImpl<ExamenATB> implements ExamenATBDao { protected ExamenATBDaoImpl() { super(ExamenATB.class) ;  }  @Override public void delete() { //TODO }  @Override protected Predicate getFilter(Root<ExamenATB> root, CriteriaBuilder builder) { ImogActor actor = ImogActorUtils.getCurrentActor() ;  if (actor == null) { actor = HttpSessionUtil.getCurrentUser() ;  }  if (actor instanceof Personnel) { Personnel personnel = (Personnel) actor ;  String niveau = personnel.getNiveau() ;  if ("1".equals(niveau)) { Path<?> join = DaoUtil.getCascadeRoot( root.join("casTb").join("patient") .join("centres", JoinType.LEFT), "region.id") ;  return builder.equal(join, personnel.getRegion().getId()) ;  }  else if ("2".equals(niveau)) { Path<?> join = DaoUtil.getCascadeRoot( root.join("casTb").join("patient") .join("centres", JoinType.LEFT), "districtSante.id") ;  return builder .equal(join, personnel.getDistrictSante().getId()) ;  }  else if ("3".equals(niveau)) { Path<?> join = root.join("casTb").join("patient") .join("centres", JoinType.LEFT).get("id") ;  return builder.equal(join, personnel.getCDT().getId()) ;  }  }  return null ;  } }