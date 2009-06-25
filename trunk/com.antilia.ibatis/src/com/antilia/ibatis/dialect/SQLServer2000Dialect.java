/**
 * 
 */
package com.antilia.ibatis.dialect;

import java.io.Serializable;

import com.antilia.ibatis.query.IBatisQuery;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class SQLServer2000Dialect implements IBatisDialect {

	/**
	 * 
	 */
	public SQLServer2000Dialect() {
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
		/*
		sb.append("SELECT ");
		sb.append("* FROM ");
		sb.append(batisQuery.getTableName());
		sb.append(" ");
		sb.append(batisQuery.getWhereClause());
		sb.append(" ORDER BY ");
		sb.append(batisQuery.getSort(null));
		*/
		if(batisQuery.getMaxResults()==0) {
			sb.append("SELECT ");
			sb.append("* FROM ");
			sb.append(batisQuery.getTableName());
			sb.append(" ");
			sb.append(batisQuery.getWhereClause());
			sb.append(" ORDER BY ");
			sb.append(batisQuery.getSort(null));
		} else {
			sb.append("SELECT * FROM (SELECT TOP ");
			sb.append(batisQuery.getMaxResults());
			sb.append(" * FROM (SELECT TOP ");
			sb.append(batisQuery.getTotalSize());
			sb.append(" * FROM ");
			sb.append(" ");
			sb.append(batisQuery.getTableName());
			sb.append(batisQuery.getWhereClause());
			sb.append(" ORDER BY ");
			sb.append(batisQuery.getSort(null));
			sb.append(") AS T ORDER BY ");
			sb.append(batisQuery.getReverseSort("T"));
			sb.append(") AS T ORDER BY ");
			sb.append(batisQuery.getSort("T"));
		}				
		return sb.toString();
		
	}
	
	public boolean useNativePagination() {
		return true;
	}

}
