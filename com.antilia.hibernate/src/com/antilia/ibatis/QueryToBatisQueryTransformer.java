/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.ibatis;

import java.io.Serializable;

import com.antilia.hibernate.query.IOrder;
import com.antilia.hibernate.query.IQuery;
import com.antilia.hibernate.query.IOrder.OrderType;
import com.antilia.hibernate.query.transform.IQueryTransformer;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class QueryToBatisQueryTransformer<E extends Serializable> implements IQueryTransformer<E, IBatisQuery<E>> {

	public IBatisQuery<E> transform(IQuery<E> source, boolean includeOrdering) {
		IBatisQuery<E> iBatisQuery = new IBatisQuery<E>(source.getEntityClass());
		return transform(iBatisQuery, source, includeOrdering);
	}
	
	public IBatisQuery<E> transform(IBatisQuery<E> iBatisQuery, IQuery<E> source,	boolean includeOrdering) {
		if(source.getMaxResults() > 0) {
			iBatisQuery.setMaxResults(source.getMaxResults());
		}
		if(source.getFirstResult() > 0) {
			iBatisQuery.setFirstResult(source.getFirstResult());
		}
		/*
		for(IFilter filter: source.getFilters()) {
			iBatisQuery.add(filter.getTransformer().transform(filter));
		}
		*/		
		if(includeOrdering) {
			for(IOrder<E> order: source.getOrders()) {					
				if(order.getType().equals(OrderType.ASCENDING)) {
					iBatisQuery.addSort(order.getPropertyPath(), com.antilia.ibatis.IBatisQuery.Order.ASC);
				}
				if(order.getType().equals(OrderType.DESCENDING)) {
					iBatisQuery.addSort(order.getPropertyPath(), com.antilia.ibatis.IBatisQuery.Order.DESC);
				}							
			}
		}
		//TODO: handle projections
		//ProjectionList projection = Projections.projectionList();
		//projection.add(Projections.groupProperty(order.getPropertyPath()));
		//if(projection.getLength() > 0) {
		//	criteria.setProjection(projection);
		//}
		return iBatisQuery;
	}	
}
