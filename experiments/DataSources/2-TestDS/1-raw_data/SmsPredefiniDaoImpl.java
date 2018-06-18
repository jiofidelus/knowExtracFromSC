package org.imogene.epicam.domain.dao;

import java.util.List;
import java.util.Vector;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.imogene.lib.common.criteria.DaoUtil;
import org.imogene.lib.common.criteria.ImogJunction;
import org.imogene.lib.common.dao.ImogBeanDaoImpl;
import org.imogene.lib.common.entity.ImogActor;
import org.imogene.epicam.server.ImogActorUtils;
import org.imogene.web.server.util.HttpSessionUtil;
import org.imogene.epicam.domain.entity.*;
/**
 * Manage persistence for SmsPredefini
 */
public class SmsPredefiniDaoImpl extends ImogBeanDaoImpl<SmsPredefini>
		implements
			SmsPredefiniDao {

	protected SmsPredefiniDaoImpl() {
		super(SmsPredefini.class);
	}

	@Override
	public void delete() {
		//TODO
	}

	/* relation dependencies */

}
