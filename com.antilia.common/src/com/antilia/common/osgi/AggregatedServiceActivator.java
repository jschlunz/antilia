/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.osgi;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.antilia.common.ILoggable;

/**
 * An  IServiceActivator that acting as an aggregator of other services.
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class AggregatedServiceActivator implements IServiceActivator, ILoggable {

	List<IServiceActivator> activators;
	
	/**
	 * Constructor.
	 */
	public AggregatedServiceActivator() {
		activators = new ArrayList<IServiceActivator>();
	}

	
	public boolean isMandatory() throws Exception {
		return true;
	}

	public void start(BundleContext context) throws Exception {
		for(IServiceActivator activator:activators) {
			try {
				activator.start(context);
			} catch (Throwable e) {
				e.printStackTrace();
				getLogger().error("Error activating  service "+activator.getClass().getName(), e);
				if(activator.isMandatory())
					throw new OsgiException(OsgiException.SERVICE_ACTIVATION_FAILURE, e);
			}
		}
	}

	public void stop(BundleContext context) throws Exception {
		for(IServiceActivator activator:activators) {
			try {
				activator.stop(context);
			} catch (Throwable e) {
				getLogger().error("Error de-activating  service "+activator.getClass().getName(), e);
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Adds a service activator.
	 * 
	 * @param activator
	 * @return
	 */
	public AggregatedServiceActivator addServiceActivator(IServiceActivator activator) {
		if(activators == null)
			activators = new ArrayList<IServiceActivator>();
		activators.add(activator);
		return this;
	}

	/**
	 * Removes a service activator.
	 * 
	 * @param activator
	 * @return
	 */
	public AggregatedServiceActivator removeServiceActivator(IServiceActivator activator) {
		if(activators == null)
			return this;
		activators.remove(activator);
		return this;
	}
	
	public Logger getLogger() {
		return LoggerFactory.getLogger(this.getClass());
	}
}
