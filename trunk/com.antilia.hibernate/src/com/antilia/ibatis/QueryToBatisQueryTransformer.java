/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.ibatis;

import java.io.Serializable;
import java.util.Iterator;

import com.antilia.common.query.IOrder;
import com.antilia.common.query.IQuery;
import com.antilia.common.query.IRestriction;
import com.antilia.common.query.Operator;
import com.antilia.common.query.PropertyRestriction;
import com.antilia.common.query.SimpleRestriction;
import com.antilia.common.query.IOrder.OrderType;
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
		buildWhereClause(iBatisQuery, source.getRestrictions());
		
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
	
	private void buildWhereClause(IBatisQuery<E> iBatisQuery, Iterable<IRestriction> filters) {				
		Iterator<IRestriction> it = filters.iterator();
		if(it.hasNext()) {
			// It is very difficult to get this to work right.
			// ... and is somehow reinventing the wheel.
			StringBuffer sb = new StringBuffer();
			sb.append(" WHERE ");
			while(it.hasNext()) {			
				IRestriction filter =  it.next();
				if(filter instanceof PropertyRestriction) {
					PropertyRestriction propertyRestriction = (PropertyRestriction)filter;					
					sb.append(getColumnName(iBatisQuery, propertyRestriction.getPropertyName()));
					sb.append(operatorToString(propertyRestriction.getOp()));
					sb.append(getColumnName(iBatisQuery, propertyRestriction.getOtherPropertyName()));
				} else if(filter instanceof SimpleRestriction) {
					SimpleRestriction restriction = (SimpleRestriction)filter;
					sb.append(getColumnName(iBatisQuery, restriction.getPropertyName()));
					sb.append(operatorToString(restriction.getOp()));
					sb.append("'"+restriction.getValue()+"'");
				}
				if(it.hasNext()) {
					sb.append(" AND ");
				} else {
					sb.append(" ");
				}
			}
			iBatisQuery.setWhereClause(sb.toString());
		} else {
			iBatisQuery.setWhereClause(" ");
		}
		
	}
	
	private String getColumnName(IBatisQuery<E> iBatisQuery, String propertyName) {
		return iBatisQuery.getColumnInfo(propertyName).getColumnName();
	}
	
	private String operatorToString(Operator operator) {
		return operator.getValue();
	}
}
