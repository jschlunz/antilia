package com.antilia.web.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.IdentityHashMap;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.collection.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;

import com.antilia.common.proxy.VirtualProxyHandler;

public class PersistenceUtils {

	/**
	 * attaches an object that must be passed to the entityManager,
	 * replacing our proxies by the original object.
	 * 
	 * @param object
	 */
	public static<T> T attachObject(T object) {
		return(attachObject(object,new IdentityHashMap<Object,Object>()));
	}
	
	private static<T> T attachObject(T object, IdentityHashMap<Object,Object> attachedObjects) {
		if (object == null)
			return(null);

		// if it's a collection, call attach on each element
		if (object instanceof Iterable)
			for (Object element : (Iterable<?>)object)
				attachObject(element,attachedObjects);
		
		// don't attach objects that are already attached
		if (attachedObjects.containsKey(object))
			return(object);
		
		// register this object as attached
		attachedObjects.put(object,null);
		
		// look or any virtual proxies to replace	
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);				
			try {
				Object value = field.get(object);
				if (value == null)
					continue;

				// it's an association collection
				if (field.isAnnotationPresent(OneToMany.class) || field.isAnnotationPresent(ManyToMany.class)) {
					// if it's a virtual proxy, replace it by the proxy's actual object
					if (value instanceof Proxy) {
						InvocationHandler invocationHandler = Proxy.getInvocationHandler(value);
						if (invocationHandler instanceof VirtualProxyHandler) {
							value = ((VirtualProxyHandler<?>)invocationHandler).getObject();
							field.set(object,value);
						}
					}
					
					// if it's an uninitialized PersistentCollection, don't touch it
					if (value instanceof PersistentCollection && !((PersistentCollection)value).wasInitialized())
						continue;
					
					// recursively attach the collection
					attachObject(value,attachedObjects);
				}

				// it's an association entity
				else if (field.isAnnotationPresent(ManyToOne.class) || field.isAnnotationPresent(OneToOne.class)) {
					// if it's an uninitialized HibernateProxy, don't touch it
					if (value instanceof HibernateProxy && ((HibernateProxy)value).getHibernateLazyInitializer().isUninitialized())
						continue;
					
					// recursively attach the entity
					attachObject(value,attachedObjects);
				}
			} catch (IllegalAccessException e) {}
		}
		
		return(object);
	}
}
