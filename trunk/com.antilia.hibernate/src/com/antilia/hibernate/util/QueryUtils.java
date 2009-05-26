package com.antilia.hibernate.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.antilia.common.util.ReflectionUtils;
import com.antilia.hibernate.query.IFilter;
import com.antilia.hibernate.query.IOrder;
import com.antilia.hibernate.query.IQuery;
import com.antilia.hibernate.query.IlikeRestriction;
import com.antilia.hibernate.query.IOrder.OrderType;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class QueryUtils {

	private static class IOrderComparator<T extends Serializable> implements Comparator<T> {
		
		private IOrder<T> order;
		
		public IOrderComparator(IOrder<T> order) {
			this.order = order;
		}
		
		@SuppressWarnings("unchecked")
		public int compare(T o1, T o2) {
			try {
				Object ov1 = ReflectionUtils.getPropertyValue(o1, order.getPropertyPath());
				Object ov2 = ReflectionUtils.getPropertyValue(o2, order.getPropertyPath());
				if(ov1 instanceof Comparable && ov2 instanceof Comparable){
					Comparable c1 = (Comparable)ov1;
					Comparable c2 = (Comparable)ov2;
					if(order.getType().equals(OrderType.ASCENDING))
						return c1.compareTo(c2);
					else if(order.getType().equals(OrderType.DESCENDING))
						return c2.compareTo(c1);
				}				
			}catch (Exception e) {				
			}
			return 0;
		};
	}
	public static <T extends Serializable> T matchQuery(T bean, IQuery<T> query) {
		if(bean == null )
			return null;
		if(query == null)
			return bean;
		for(IFilter filter: query.getFilters()) {
			if(filter instanceof IlikeRestriction) {
				IlikeRestriction restriction = (IlikeRestriction)filter;
				String propertyName = restriction.getPropertyName();				
				try {
					Object rValue  = restriction.getValue();
					if(rValue != null) {
						Object value = EntityUtils.getPropertyValue(bean, propertyName);
						String strVale = value.toString();
						if(!strVale.startsWith(rValue.toString())) {
							return null;
						}
					}
				} catch (Exception e) {					
					return null;
				}
			}
		}
		return bean;
	}
		
	
	public static <T extends Serializable> List<T> sortList(List<T> list, IQuery<T> query) {
		IOrderComparator<T> comparator = null;
		for(IOrder<T> order : query.getOrders()) {
			comparator = new IOrderComparator<T>(order);
		}
		if(comparator != null) {
			Collections.sort(list, comparator);
		}
		return list;
	}
	
	public static <T extends Serializable> List<T> findSubList(List<T> list, IQuery<T> query) {
		if(list == null)
			return null;
		List<T> nlist = new ArrayList<T>();
		for(T bean: list) {
			T result = matchQuery(bean, query);
			if(result != null) {
				nlist.add(bean);
			}
		}
		return sortList(nlist, query);
	}
}
