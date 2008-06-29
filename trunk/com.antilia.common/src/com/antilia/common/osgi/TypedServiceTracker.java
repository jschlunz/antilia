/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.osgi;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Typed extension of standard OSGi service tracker (it allows to track down a certain OSGi 
 * service and do something when the service starts or services stops). The same logic 
 * is also called if the bundle containing the tracker is started/stopped.
 * 
 * @param <B> The class of the service to track down.
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class TypedServiceTracker<B> extends ServiceTracker {

		/**
	 * @param context
	 * @param clazz
	 * @param customizer
	 */
	public TypedServiceTracker(BundleContext context, Class<B> clazz) {
		super(context, clazz.getName(), null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object addingService(ServiceReference reference) {
		try {
			B service = (B) (context.getService(reference));
			onAddingService(service);
			return service;
		} catch (Throwable e) {
			e.printStackTrace();
			throw new OsgiException(OsgiException.SERVICE_TRACKER_FAILURE, e);
		}
	}
	
	/**
	 * Override this method in order to do the logic associated with service initialization.
	 * 
	 * @param service
	 */
	public abstract  void onAddingService(B service);
	
	/**
	 * Override this method in order to do the logic associated with service removal.
	 * 
	 * @param service
	 */
	public abstract  void onServiceRemoved(B service);
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void removedService(ServiceReference reference, Object service) {
		try {
			onServiceRemoved((B)service);
			super.removedService(reference, service);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}	
}
