package com.antilia.hibernate.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.antilia.hibernate.query.IFilter;
import com.antilia.hibernate.query.IQuery;
import com.antilia.hibernate.query.IlikeRestriction;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class QueryUtils {

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
		return nlist;
	}
}
