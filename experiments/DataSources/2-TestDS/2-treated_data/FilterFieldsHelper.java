package org.imogene.web.server.util ; public class FilterFieldsHelper { public static final String EMPTY_FILTER = "undefined-filter" ;   public static void addEmptyFilter(ImogJunction junction){ BasicCriteria criteria = new BasicCriteria() ;  criteria.setOperation(CriteriaConstants.OPERATOR_ISNULL) ;  criteria.setField("id") ;  criteria.setValue(EMPTY_FILTER) ;  junction.add(criteria) ;  } }