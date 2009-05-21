package com.antilia.hibernate.command;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.hibernate.collection.PersistentCollection;

import com.antilia.common.proxy.VirtualProxyHandler;
import com.antilia.hibernate.PersistenceException;
import com.antilia.hibernate.util.EntityUtils;

/**
 * PersistenCollectionHandler is a Proxy InvocationHandler implementation
 * that supports lazy loading of Hibernate PersistentCollections
 * outside of a persistence context.
  */
public class PersistentCollectionHandler<E extends Serializable> extends VirtualProxyHandler<PersistentCollection> {
	private static final long serialVersionUID = 1L;
	
	private Class<E> beanClass;
	private Serializable key;
	private String property;
	
	@SuppressWarnings("unchecked")
	public PersistentCollectionHandler(PersistentCollection collection,  E entity, String property) {
		super(collection);
		
		this.beanClass = (Class<E>)entity.getClass();
		this.key = (Serializable)EntityUtils.getKeyValue(entity);
		this.property = property;
	}

	public PersistentCollection initialize(PersistentCollection collection) throws Throwable {
		if (beanClass == null || key == null || property == null)
			throw new PersistenceException(PersistenceException.LAZY_LOAD_FAILED);
	
		
		try {
			return((PersistentCollection)DefaultCommander.execute(new LoadPropertyCommand<E, Serializable>(beanClass,key,property)));
		} finally {
			beanClass = null;
			key = null;
			property = null;
		}
	}
	
	@Override
	public Object invoke(Object object, Method method, Object[] args) throws Throwable {
		if (method.getName().equals("hashCode"))
			return(hashCode());
			
		return(super.invoke(object,method,args));
	}
	
	public boolean wasInitialized(PersistentCollection collection) {
		return(collection.wasInitialized());
	}
}