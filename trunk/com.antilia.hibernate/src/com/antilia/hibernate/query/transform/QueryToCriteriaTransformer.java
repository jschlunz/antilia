/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query.transform;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import com.antilia.hibernate.context.RequestContext;
import com.antilia.hibernate.query.IFilter;
import com.antilia.hibernate.query.IOrder;
import com.antilia.hibernate.query.IQuery;
import com.antilia.hibernate.query.IOrder.OrderType;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class QueryToCriteriaTransformer<E extends Serializable> implements IQueryTransformer<E, Criteria> {

	@Override
	public Criteria transform(IQuery<E> source) {
		Criteria criteria = RequestContext.get().getSession().createCriteria(source.getEntityClass());
		if(source.getMaxResults() > 0) {
			criteria.setMaxResults(source.getMaxResults());
		}
		if(source.getFirstResult() > 0) {
			criteria.setFirstResult(source.getFirstResult());
		}
		for(IFilter filter: source.getFilters()) {
			criteria.add(filter.getTransformer().transform(filter));
		}
		ProjectionList projection = Projections.projectionList();
		for(IOrder<E> order: source.getOrders()) {		
			/*
			if(order.getType().equals(OrderType.ASCENDING)) {
				criteria.addOrder(Order.asc(order.getPropertyPath()));
			}
			if(order.getType().equals(OrderType.DESCENDING)) {
				criteria.addOrder(Order.desc(order.getPropertyPath()));
			}
			*/
			//projection.add(Projections.groupProperty(order.getPropertyPath()));
		}
		if(projection.getLength() > 0) {
			criteria.setProjection(projection);
		}
		return criteria;
	}
}
