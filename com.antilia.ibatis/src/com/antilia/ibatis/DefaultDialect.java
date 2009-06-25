/**
 * 
 */
package com.antilia.ibatis;

import java.io.Serializable;

import com.antilia.ibatis.dialect.IBatisDialect;
import com.antilia.ibatis.query.IBatisQuery;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class DefaultDialect implements IBatisDialect {

	/**
	 * 
	 */
	public DefaultDialect() {
	}

	/* (non-Javadoc)
	 * @see com.antilia.ibatis.IBatisDialect#buildCountQuery(com.antilia.ibatis.IBatisQuery)
	 */
	public <B extends Serializable> String buildCountQuery(IBatisQuery<B> batisQuery) {
		return "SELECT count(*) FROM " + batisQuery.getTableName() + " " + batisQuery.getWhereClause();
	}

	/* (non-Javadoc)
	 * @see com.antilia.ibatis.IBatisDialect#buildListQuery(com.antilia.ibatis.IBatisQuery)
	 */
	public <B extends Serializable> String buildListQuery(IBatisQuery<B> batisQuery) {		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ");
		sb.append("* FROM ");
		sb.append(batisQuery.getTableName());
		sb.append(" ");
		sb.append(batisQuery.getWhereClause());
		sb.append(" ORDER BY ");
		sb.append(batisQuery.getSort(null));						
		return sb.toString();
		
	}
	
	public boolean useNativePagination() {
		return false;
	}

}
