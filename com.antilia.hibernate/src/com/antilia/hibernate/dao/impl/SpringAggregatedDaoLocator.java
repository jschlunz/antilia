/**
 * 
 */
package com.antilia.hibernate.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.antilia.common.dao.IDaoLocator;
import com.antilia.common.dao.impl.AggregatedDaoLocator;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class SpringAggregatedDaoLocator extends AggregatedDaoLocator {
	
	private HibernateTemplate template;
	
	private SessionFactory sessionFactory;
	
	protected boolean configured = false;
	
	protected SpringAggregatedDaoLocator() {		
	}	

	@Override
	protected IDaoLocator getDefaultDaoLocator() {
		if(!configured) {
			SpringDaoLocator locator = SpringDaoLocator.getInstance();
			if(getTemplate() != null) {
				locator.setTemplate(getTemplate());
			} else if(getSessionFactory() != null) {
				locator.setSessionFactory(getSessionFactory());
			}
		}
		return SpringDaoLocator.getInstance();
	}

	/**
	 * @return the template
	 */
	public HibernateTemplate getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
