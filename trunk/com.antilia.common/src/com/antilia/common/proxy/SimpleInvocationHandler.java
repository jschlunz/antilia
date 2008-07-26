package com.antilia.common.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * SimpleInvocationHandler is a dummy invocationHandler that simply
 * passes all calls to the underlying object. It can be used to create
 * dynamic proxies that narrow an object to an interface.
 * 
 */
public class SimpleInvocationHandler implements InvocationHandler {
	public Object invoke(Object object, Method method, Object[] args) throws Throwable {
		try {
			return(method.invoke(object,args));
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		}
	}
}
