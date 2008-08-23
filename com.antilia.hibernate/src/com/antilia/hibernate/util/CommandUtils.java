package com.antilia.hibernate.util;

import java.io.Serializable;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.antilia.common.proxy.ProxyFactory;
import com.antilia.common.proxy.VirtualProxyHandler;
import com.antilia.hibernate.command.PersistentCollectionHandler;

public class CommandUtils {
	
	private static Logger  logger = LoggerFactory.getLogger(CommandUtils.class);
	/**
	 * detaches an object that is returned by the entityManager,
	 * replacing lazy initilization proxies by more intelligent
	 * versions that support lazy loading outside a persistence context.
	 * 
	 * @param object the result to detach
	 */
	public static<T> T detachObject(T object) {
		return(detachObject(object,new IdentityHashMap<Object,Object>()));
	}
	
	public static<T> T detachObject(T object, IdentityHashMap<Object,Object> detachedObjects) {
		if (object == null)
			return(null);

		// if it's a collection, call detach on each element
		if (object instanceof Iterable<?>)
			for (Object element : (Iterable<?>)object)
				detachObject(element,detachedObjects);
		
		// don't detach objects that are already detached
		if (detachedObjects.containsKey(object))
			return(object);
		
		// register this object as detached
		detachedObjects.put(object,null);
		
		// look or any uninitialized proxies to replace	
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {				
			field.setAccessible(true);
			try {
				Object value = field.get(object);
				if (value == null)
					continue;
				
				// it's an association collection
				if (field.isAnnotationPresent(OneToMany.class) || field.isAnnotationPresent(ManyToMany.class)) {
					// if it's an uninitialized PersistentCollection, wrap it in a proxy
					if (value instanceof PersistentCollection && !((PersistentCollection)value).wasInitialized()) {
						field.set(object,ProxyFactory.createVirtualProxy(field.getType(),
							new PersistentCollectionHandler<Serializable>((PersistentCollection)value,(Serializable)object,field.getName())));
						logger.debug("detachObject: "+field.getName()+" of "+ object.getClass().getName()+" replaced by a proxy !");
					} else {
						// recursively detach the collection
						detachObject(value,detachedObjects);
					}
				}
				// it's an association entity
				else if (field.isAnnotationPresent(ManyToOne.class) || field.isAnnotationPresent(OneToOne.class)) {
					if (value instanceof HibernateProxy && ((HibernateProxy)value).getHibernateLazyInitializer().isUninitialized()) {						
						logger.debug("HibernateProxy....");
					} else {
						// recursively detach the entity
						detachObject(value,detachedObjects);
					}
				}
			} catch (IllegalAccessException e) {}
		}
		
		return(object);
	}
	
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
