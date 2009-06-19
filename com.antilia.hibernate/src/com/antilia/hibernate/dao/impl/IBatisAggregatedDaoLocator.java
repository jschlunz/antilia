/**
 * 
 */
package com.antilia.hibernate.dao.impl;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.antilia.hibernate.dao.IDaoLocator;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class IBatisAggregatedDaoLocator extends AggregatedDaoLocator {
	
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	protected boolean configured = false;
	
	protected IBatisAggregatedDaoLocator() {		
	}	

	@Override
	protected IDaoLocator getDefaultDaoLocator() {
		return null;
	}

	/**
	 * @return the sqlMapClientTemplate
	 */
	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	/**
	 * @param sqlMapClientTemplate the sqlMapClientTemplate to set
	 */
	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
	
}
