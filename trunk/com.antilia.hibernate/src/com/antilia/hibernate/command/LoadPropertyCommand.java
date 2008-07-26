package com.antilia.hibernate.command;

import java.io.Serializable;
import java.lang.reflect.Proxy;

import org.hibernate.Session;
import org.hibernate.collection.PersistentCollection;

import com.antilia.common.proxy.VirtualProxyHandler;
import com.antilia.common.util.StringUtils;
import com.antilia.hibernate.PersistenceException;
import com.antilia.hibernate.util.EntityUtils;


public class LoadPropertyCommand<E extends Serializable, T extends Object> extends AbstractPersistentCommand<E, T> {
	private static final long serialVersionUID = 1L;

	protected Class<E> beanClass;
	protected Serializable key;
	protected String property;

	@SuppressWarnings("unchecked")
	public LoadPropertyCommand(Class<E> beanClass, Serializable key, String property) {
		super(beanClass);
		if (StringUtils.isEmpty(property))
			throw new PersistenceException(PersistenceException.NO_PROPERTIES_TO_LOAD);

		this.beanClass = beanClass;
		this.key = key;
		this.property = property;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected T doExecute() throws Throwable {
		Session session = getSession();
		E entity = (E)session.get(getPersistentClass(),key);

		T value = (T)EntityUtils.getPropertyValue(entity,property);
		
		if (value instanceof Proxy) {
			//			System.out.println(this+": found value is proxy !");
			value = ((VirtualProxyHandler<T>)Proxy.getInvocationHandler(value)).getObject(); 
		}

		// force lazy loading
		if (value instanceof PersistentCollection) {
			PersistentCollection persistentCollection = ((PersistentCollection)value);
			persistentCollection.forceInitialization();
		} else
			System.out.println(this+": unknown lazy property !");
		
		return(value);
	}
	

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Loading property ");
		buffer.append("'");
		buffer.append(property);
		buffer.append("'");
		buffer.append(" for ");
		buffer.append(beanClass.getName());		
		return(buffer.toString());
	}
}