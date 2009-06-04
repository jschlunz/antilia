/**
 * 
 */
package com.antilia.hibernate.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.antilia.common.util.AnnotationUtils;
import com.antilia.hibernate.dao.IQuerableDao;
import com.antilia.hibernate.query.IQuery;

/**
 * A querable DAO that uses Spring Hibernate support.
 *  
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
@Transactional(propagation = Propagation.SUPPORTS)
public class SpringHibernateQuerableDao<E extends Serializable> extends HibernateDaoSupport implements IQuerableDao<E>  {

	private static final long serialVersionUID = 1L;
	
	public SpringHibernateQuerableDao() {
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<E> findAll(final IQuery<E> query) {
		return (List<E>)(getHibernateTemplate().execute(new HibernateQueryCallback<E>(query) {			
			@Override
			public Object doInHibernate(Session session, Criteria criteria) throws HibernateException, SQLException {
				return criteria.list();
			}						
		}));
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<E> findAll(final Class<E> beanClass) {
		return (List<E>)(getHibernateTemplate().execute(new HibernateQueryCallback<E>(beanClass) {			
			public Object doInHibernate(Session session, Criteria criteria) throws HibernateException, SQLException {
				return (List<E>)criteria.list();
			}						
		}));		
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public Long count(final IQuery<E> query) {
		return (Long)(getHibernateTemplate().execute(new HibernateQueryCallback<E>(query, false) {			
			
			@Override
			public Object doInHibernate(Session session, Criteria criteria) throws HibernateException, SQLException {
				criteria.setProjection(Projections.rowCount());
				return new Long(criteria.uniqueResult().toString());
			}						
		}));
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public E findById(final Class<E> beanClass, final Serializable key) {
		return (E)(getHibernateTemplate().execute(new HibernateQueryCallback<E>(beanClass) {			
			
			@Override
			public Object doInHibernate(Session session, Criteria criteria) throws HibernateException, SQLException {
				Field[] field = AnnotationUtils.findAnnotatedFields(beanClass, Id.class);
				if(field == null || field.length == 0)
					field = AnnotationUtils.findAnnotatedFields(beanClass, EmbeddedId.class);
				criteria.add(Restrictions.eq(field[0].getName(), key));
				return (E)criteria.uniqueResult();
			}			
		}));						
	}	
}
