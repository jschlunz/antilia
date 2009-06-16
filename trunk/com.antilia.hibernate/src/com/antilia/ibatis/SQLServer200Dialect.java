/**
 * 
 */
package com.antilia.ibatis;

import java.io.Serializable;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class SQLServer200Dialect implements IBatisDialect {

	/**
	 * 
	 */
	public SQLServer200Dialect() {
	}

	/* (non-Javadoc)
	 * @see com.antilia.ibatis.IBatisDialect#buildCountQuery(com.antilia.ibatis.IBatisQuery)
	 */
	public <B extends Serializable> String buildCountQuery(IBatisQuery<B> batisQuery) {
		StringBuffer sb = new StringBuffer();
		if(batisQuery.getMaxResults()>0) {
			sb.append("SELECT ");
			sb.append(batisQuery.getTotalSize());
			sb.append("* FROM ");
			sb.append(batisQuery.getTableName());
			sb.append(" ");
			sb.append(batisQuery.getWhereClause());
			sb.append(" ORDER BY ");
			sb.append(batisQuery.getSort());
		} else {
			sb.append("SELECT * FROM (SELECT TOP ");
			sb.append(batisQuery.getMaxResults());
			sb.append("FROM (SELECT TOP ");
			sb.append(batisQuery.getTotalSize());
			sb.append(" * FROM ");
			sb.append(" ");
			sb.append(batisQuery.getTableName());
			sb.append(batisQuery.getWhereClause());
			sb.append(" ORDER BY ");
			sb.append(batisQuery.getSort());
			sb.append("AS T ORDER BY T.");
			sb.append(batisQuery.getReverseSort());
			sb.append(") AS T ORDER BY T.");
			sb.append(batisQuery.getSort());
		}		
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see com.antilia.ibatis.IBatisDialect#buildListQuery(com.antilia.ibatis.IBatisQuery)
	 */
	public <B extends Serializable> String buildListQuery(IBatisQuery<B> batisQuery) {		
		return "SELECT count(*) FROM " + batisQuery.getTableName() + " " + batisQuery.getWhereClause();
	}

}
