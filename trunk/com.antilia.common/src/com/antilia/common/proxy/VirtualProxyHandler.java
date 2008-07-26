package com.antilia.common.proxy;

import java.io.Serializable;
import java.lang.reflect.Method;

public abstract class VirtualProxyHandler<T> extends SimpleInvocationHandler implements Serializable {
	private T realObject = null;
	
	public abstract T initialize(T object) throws Throwable;
	public abstract boolean wasInitialized(T object) throws Throwable;
	
	public VirtualProxyHandler(T object) {
		realObject = object;
	}
	
	public T getObject() {
		return(realObject);
	}
	
	@Override
	public Object invoke(Object object, Method method, Object[] args) throws Throwable {
		if (!wasInitialized(realObject))
			realObject = initialize(realObject);
		
		return(super.invoke(realObject,method,args));
	}
}
