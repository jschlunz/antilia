/**
 * 
 */
package com.antilia.ibatis;

import java.io.Serializable;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 */
public interface IBatisDialect  {
	
	/**
	 * 
	 * @param <B>
	 * @param iBatisQuery
	 * @return
	 */
	<B extends Serializable> String buildListQuery(IBatisQuery<B> iBatisQuery);
	
	/**
	 * 
	 * @param <B>
	 * @param iBatisQuery
	 * @return
	 */
	<B extends Serializable> String buildCountQuery(IBatisQuery<B> iBatisQuery);
	
	
	boolean useNativePagination();

}
