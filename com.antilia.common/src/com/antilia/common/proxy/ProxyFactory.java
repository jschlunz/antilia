package com.antilia.common.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * ProxyFactory is a factory for dynamic proxy instances.
 */
public class ProxyFactory {
	/**
	 * creates a simple proxy that narrows an object to a single interface
	 * 
	 * @param object the object for which to create a proxy
	 * @param interfaze the interface the proxy should implement
	 * @return a proxy instance of type T
	 */
	public static<T> T createProxy(final T object, Class<T> interfaze) {
		return(createProxy(object,interfaze,new SimpleInvocationHandler()));
	}
	
	/**
	 * creates a proxy that narrows an object to a single interface,
	 * and uses the given invocationHandler to invoke its methods.
	 * 
	 * @param object the object for which to create a proxy
	 * @param interfaze the interface the proxy should implement
	 * @param handler
	 * @return a proxy instance of type T
	 */
	@SuppressWarnings("unchecked")
	public static<T> T createProxy(final T object, Class<T> interfaze, InvocationHandler handler) {
		return((T) Proxy.newProxyInstance(
			(object != null)?(object.getClass().getClassLoader()):(interfaze.getClassLoader()),
			new Class[]{interfaze},
			handler));
	}

	/**
	 * creates a virtual proxy that allows the real object to be lazily initialized
	 * by the VirtualInvocationHandler.
	 * 
	 * @param object the object for which to create a proxy
	 * @param interfaze the interface the proxy should implement
	 * @param handler a VirtualInvokationHandler that provides the real object
	 * @return a proxy instance of type T
	 */
	@SuppressWarnings("unchecked")
	public static<T> T createVirtualProxy(Class<T> interfaze, VirtualProxyHandler handler) {
		return((T) Proxy.newProxyInstance(
			interfaze.getClassLoader(),
			new Class[]{interfaze},
			handler));
	}
}