/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query.transform.impl;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.antilia.common.query.IFilter;
import com.antilia.common.query.IOrder;
import com.antilia.common.query.IQuery;
import com.antilia.common.query.IOrder.OrderType;
import com.antilia.hibernate.context.RequestContext;
import com.antilia.hibernate.query.transform.IFilterTransformer;
import com.antilia.hibernate.query.transform.IQueryTransformer;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class QueryToCriteriaTransformer<E extends Serializable> implements IQueryTransformer<E, Criteria> {

	public Criteria transform(IQuery<E> source, boolean includeOrdering) {
		Criteria criteria = RequestContext.get().getSession().createCriteria(source.getEntityClass());		
		return transform(criteria, source, includeOrdering);
	}
	
	public Criteria transform(Criteria criteria, IQuery<E> source,	boolean includeOrdering) {
		if(source.getMaxResults() > 0) {
			criteria.setMaxResults(source.getMaxResults());
		}
		if(source.getFirstResult() > 0) {
			criteria.setFirstResult(source.getFirstResult());
		}
		
		for(IFilter filter: source.getFilters()) {
			IFilterTransformer<Criterion> transformer = HibernateTransformerLocator.getInstance().getTransformer(filter);
			criteria.add(transformer.transform(filter));
		}		
		if(includeOrdering) {
			for(IOrder<E> order: source.getOrders()) {					
				if(order.getType().equals(OrderType.ASCENDING)) {
					criteria.addOrder(Order.asc(order.getPropertyPath()));
				}
				if(order.getType().equals(OrderType.DESCENDING)) {
					criteria.addOrder(Order.desc(order.getPropertyPath()));
				}							
			}
		}
		//TODO: handle projections
		//ProjectionList projection = Projections.projectionList();
		//projection.add(Projections.groupProperty(order.getPropertyPath()));
		//if(projection.getLength() > 0) {
		//	criteria.setProjection(projection);
		//}
		return criteria;
	}	
}
